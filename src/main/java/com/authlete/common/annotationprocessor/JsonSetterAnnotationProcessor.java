package com.authlete.common.annotationprocessor;


import com.fasterxml.jackson.annotation.JsonSetter;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * This program is run during the "process-classes" and "process-test-classes" maven steps
 * to add the {@link JsonSetter} annotation to any of the classes in {@link JsonSetterAnnotationProcessor#PACKAGE_NAME} package
 * which have multiple ambiguous setters defined for specific member properties.
 * <p>
 * Since there are multiple setters defined, to determine which is the "correct" setter function we should set the annotation
 * to, we will look up the matching getter and use it's return type and use the setter whose argument matches the getter's
 * return type.
 * <p>
 *
 * E.g. given an object with multiple setters defined:
 * <pre>
 * {@code
 * class AJsonObject
 * {
 *      void setProperty(List<String>) { ... }
 *      // @JsonSetter would be added here since String[] is this functions argument which matches the getter's return value
 *      void setProperty(String[]) { ... }
 *      void String[] getProperty() { ... }
 * }
 * }
 * </pre>
 *
 * Using the above example object, the {@link JsonSetter} annotation would be added to the `setProperty(String[])` method since its
 * input argument of `String[]` matches the return type of the properties' getter method.
 *
 * @author kylegonzalez
 */
public class JsonSetterAnnotationProcessor
{
    private static final String PACKAGE_NAME = "com.authlete.common.dto";


    public static void main(String[] args) throws CannotCompileException, IOException
    {
        Reflections reflections = new Reflections(PACKAGE_NAME, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        System.out.println("Starting JsonSetterAnnotationProcessor, checking " + classes.size() + " class(es) in package: " + PACKAGE_NAME);

        for (Class<?> clazz : classes)
        {
            Map<String, List<Method>> setterMethods = new HashMap<>();
            for (Method method : clazz.getMethods())
            {
                // Skip any non "set" methods
                if (!method.getName().startsWith("set"))
                {
                    continue;
                }

                List<Method> methods = setterMethods.get(method.getName());
                if (methods == null)
                {
                    methods = new ArrayList<>();
                }
                methods.add(method);
                setterMethods.put(method.getName(), methods);
            }

            for (Map.Entry<String, List<Method>> entry : setterMethods.entrySet())
            {
                // If multiple setters are defined
                if (entry.getValue().size() > 1)
                {
                    // If none of the setters has the JsonSetter annotation defined
                    boolean oneIsAnnotated = entry.getValue().stream().anyMatch(m -> m.getAnnotation(JsonSetter.class) != null);
                    if (!oneIsAnnotated)
                    {
                        System.out.println("Identified ambiguous setters defined for method " + clazz.getName() + "." + entry.getKey());
                        addAnnotationToSetterMethod(JsonSetter.class, clazz, entry);
                    }
                }
            }
        }
    }


    /**
     * This function attempts to find the correct setter method that should be annotated as the {@link JsonSetter}.
     * <p>
     * To do this, we will first find the matching getter method by name, then using its return type
     * we will find the setter function whose first argument matches the getter's return type.
     *
     * @param clazz the class whose methods we are looking through
     * @param setterMethodName the setter method name that we are looking for
     * @param methods all the class' methods
     * @return the setter method whose argument matches the getter methods return type
     */
    private static CtMethod findGettersMatchingSetMethod(Class<?> clazz, String setterMethodName, CtMethod[] methods)
    {
        final String getMethodName = "g" + setterMethodName.substring(1);

        for (CtMethod method : methods)
        {
            if (method.getName().equals(getMethodName))
            {
                CtClass returnType;
                try
                {
                    returnType = method.getReturnType();
                }
                catch (NotFoundException e)
                {
                    System.out.println("Failed to find return type for " + clazz.getName() + "." + method.getName() + " Error: " + e.getMessage());
                    continue;
                }

                System.out.println("Found getter method " + clazz.getName() + "." + method.getName() + " with return type " + returnType.getName());
                return Arrays.stream(methods)
                        .filter( m -> m.getName().equals(setterMethodName))
                        .filter(m -> {
                            try
                            {
                                CtClass[] params = m.getParameterTypes();
                                return params.length == 1 && params[0].getName().equals(returnType.getName());
                            }
                            catch (NotFoundException e)
                            {
                                // unable to get parameter types from method
                                return false;
                            }
                        })
                        .findFirst()
                        .orElse(null);
            }
        }

        return null;
    }


    /**
     * Adds the provided annotation to the provided class and one of the provided methods in the entry map.
     * <p>
     * This function will read the compiled file's bytecode, identify which setter method to add the annotation to
     * then update the class' bytecode to contain this annotation on the correct method.
     *
     * @param annotation the annotation to add to the appropriate method
     * @param clazz the class we are operating on
     * @param entry a map entry of the setter method name and a list of all the setter methods with the same name
     * @throws CannotCompileException fails to write annotation changes to class file
     * @throws IOException fails to write annotation changes to class file
     */
    private static void addAnnotationToSetterMethod(Class<? extends java.lang.annotation.Annotation> annotation, Class<?> clazz, Map.Entry<String, List<Method>> entry)
            throws CannotCompileException, IOException
    {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass;

        try
        {
            ctClass = pool.get(clazz.getName());
        }
        catch (NotFoundException e)
        {
            System.out.println("Failed to find class: " + clazz.getName());
            return;
        }

        // The retrieved class can be in a frozen state
        if (ctClass.isFrozen())
        {
            ctClass.defrost();
        }
        ClassFile classFile = ctClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();

        AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annot = new Annotation(annotation.getName(), constPool);
        attr.addAnnotation(annot);

        CtMethod method = findGettersMatchingSetMethod(clazz, entry.getKey(), ctClass.getMethods());
        if (method == null)
        {
            System.out.println("Could not found the correct setter method to annotate: " + clazz.getName() + "." + entry.getKey() + " with available methods: " + entry.getValue());
            return;
        }

        try
        {
            CtClass[] params = method.getParameterTypes();
            System.out.println("Setting annotation: " + annotation.getName() + " to: " + clazz.getName() + "." + method.getName() + " with argument type: " + params[0].getName());
            method.getMethodInfo().addAttribute(attr);

            ctClass.writeFile("target/classes");
        }
        catch (NotFoundException e)
        {
            // Should not be thrown since we retrieve the method parameter types in the caller function
        }
    }
}
