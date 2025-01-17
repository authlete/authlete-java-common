package com.authlete.common.annotationprocessor;


import com.fasterxml.jackson.annotation.JsonSetter;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * This class is responsible for taking a class and determining whether there are any ambiguous setter methods defined.
 * Then also set the {@link JsonSetter} annotation onto the correct setter method.
 * <p>
 * To determine which is the "correct" setter function we should set the annotation
 * to, we will look up the matching getter and use it's return type and use the setter whose argument matches the getter's
 * return type.
 * </p>
 *
 * E.g. Given the below object with multiple setters defined:
 * <pre>
 * {@code
 * class AJsonObject
 * {
 *      void setProperty(List<String>) { ... }
 *      // @JsonSetter would be added here since String[] is this function's argument which matches the getter's return value
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
    private static final Logger LOGGER = Logger.getLogger(JsonSetterAnnotationProcessor.class.getName());

    private static final String PACKAGE_NAME = "com.authlete.common.dto";

    /**
     * The "set" word that is used as a prefix for all setter functions.
     */
    private static final String SET_METHOD_PREFIX = "set";

    /**
     * The class directory where the updated bytecode classes will be written to.
     */
    private static final String OUTPUT_CLASS_DIRECTORY = "target/classes";

    /**
     * The class that is currently being inspected.
     */
    private final Class<?> clazz;

    /**
     * The resolved CTClass of the provided {@link #clazz}.
     */
    private final CtClass ctClass;

    /**
     * A map of method name String to a list of methods that have this name.
     * Only multiple defined methods where none have {@link JsonSetter} marked against them will
     * be placed into this map.
     */
    private final Map<String, List<Method>> duplicateSetterMethods;


    /**
     * The constructor which takes the {@link Class} that it will introspect with its methods.
     *
     * @param clazz the {@link Class} to introspect
     */
    public JsonSetterAnnotationProcessor(Class<?> clazz) throws NotFoundException
    {
        this.clazz = clazz;
        this.ctClass = ClassPool.getDefault().get(clazz.getName());
        duplicateSetterMethods = initializeDuplicateSetterMethods();
    }


    /**
     * Initializes the {@link #duplicateSetterMethods} property.
     * Only adding methods to the map if there are multiple defined where none have the {@link JsonSetter} annotation.
     *
     * @return the initialized {@link #duplicateSetterMethods}
     */
    private Map<String, List<Method>> initializeDuplicateSetterMethods()
    {
        Map<String, List<Method>> setterMethods = new HashMap<>();
        for (Method method : clazz.getMethods())
        {
            // Only include setter methods
            if (method.getName().startsWith(SET_METHOD_PREFIX))
            {
                setterMethods.computeIfAbsent(method.getName(), k -> new ArrayList<>()).add(method);
            }
        }

        // Only return entries where there are multiple setter methods defined
        // And where none of the setters have the @JsonSetter annotation
        return setterMethods.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .filter(e -> e.getValue().stream().allMatch(m -> m.getAnnotation(JsonSetter.class) == null))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    /**
     * Get the stored map of {@link #duplicateSetterMethods}.
     *
     * @return {@link #duplicateSetterMethods}
     */
    Map<String, List<Method>> getDuplicateSetterMethods()
    {
        return duplicateSetterMethods;
    }


    /**
     * This function attempts to find the correct setter method that should be annotated as the {@link JsonSetter}.
     * <p>
     * To do this, we will first find the matching getter method by name, then using its return type
     * we will find the setter function whose first argument matches the getter's return type.
     * </p>
     *
     * @param setterMethodName the setter method name that we are looking for
     * @return the setter method whose argument matches the getter methods return type
     */
    CtMethod getSetterMethodToAnnotate(String setterMethodName)
    {
        final String getMethodName = "g" + setterMethodName.substring(1);

        for (CtMethod ctMethod : ctClass.getMethods())
        {
            if (ctMethod.getName().equals(getMethodName))
            {
                CtClass returnType;
                try
                {
                    returnType = ctMethod.getReturnType();
                }
                catch (NotFoundException e)
                {
                    LOGGER.severe(String.format("Unable to retrieve return type for %s.%s Error: %s",
                            clazz.getName(), ctMethod.getName(), e.getMessage()));
                    continue;
                }

                LOGGER.info(String.format("Found getter method %s.%s with return type %s", clazz.getName(), ctMethod.getName(), returnType.getName()));
                return Arrays.stream(ctClass.getMethods())
                        .filter( method -> method.getName().equals(setterMethodName))
                        .filter(method -> {
                            try
                            {
                                CtClass[] params = method.getParameterTypes();
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
     * Writes the {@link JsonSetter} annotation to the provided class and one of the provided methods in the entry map.
     * <p>
     * This function will read the compiled file's bytecode, identify which setter method to add the annotation to
     * then update the class' bytecode with the new annotation on the correct method.
     * </p>
     *
     * @param entry a map entry of the setter method name and a list of all the setter methods with the same name
     * @throws CannotCompileException fails to write annotation changes to class file
     * @throws IOException fails to write annotation changes to class file
     */
    private void addAnnotationToSetterMethod(Map.Entry<String, List<Method>> entry) throws CannotCompileException, IOException
    {
        try
        {
            CtMethod method = getSetterMethodToAnnotate(entry.getKey());
            if (method == null)
            {
                LOGGER.severe(String.format("Could not find setter method with argument that matches its getter method." +
                        " Looked up setter method: %s.%s with available methods: %s",
                        clazz.getName(), entry.getKey(), entry.getValue()));
                return;
            }

            writeAnnotationToMethod(method);
        }
        catch (NotFoundException e)
        {
            LOGGER.severe(String.format("Failed to write to target class: %s.%s Error: %s",
                    clazz.getName(), entry.getKey(), e.getMessage()));
        }
    }


    /**
     * Write the {@link JsonSetter} annotation onto the provided {@link CtClass}'s {@link CtMethod}.
     * This will edit the bytecode in the class file in the {@link #OUTPUT_CLASS_DIRECTORY} directory.
     *
     * @param method the method that the {@link JsonSetter} annotation should be added to
     * @throws NotFoundException if method parameter types cannot be retrieved
     * @throws CannotCompileException when unable to compile the annotation change in the destination class file
     * @throws IOException if there is a problem writing the byte code change to the destination class file
     */
    private void writeAnnotationToMethod(CtMethod method) throws NotFoundException, CannotCompileException, IOException
    {
        if (ctClass.isFrozen()) // The retrieved class can be in a frozen state
        {
            ctClass.defrost();
        }

        CtClass[] params = method.getParameterTypes();
        LOGGER.info(String.format("Writing annotation: %s to: %s.%s with argument type: %s",
                JsonSetter.class.getName(), clazz.getName(), method.getName(), params[0].getName()));

        ConstPool constPool = ctClass.getClassFile().getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        annotationsAttribute.addAnnotation(new Annotation(JsonSetter.class.getName(), constPool));
        method.getMethodInfo().addAttribute(annotationsAttribute);

        ctClass.writeFile(OUTPUT_CLASS_DIRECTORY);
    }


    /**
     * This program is run during the "process-classes" maven step.
     * <p>
     * This program will add the {@link JsonSetter} annotation to any of the classes in {@link JsonSetterAnnotationProcessor#PACKAGE_NAME} package
     * which have multiple ambiguous setters defined so that the model object can be deserialized from json correctly.
     * </p>
     */
    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException
    {
        Reflections reflections = new Reflections(PACKAGE_NAME, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        LOGGER.info(String.format("Starting JsonSetterAnnotationProcessor main() checking %d class(es) in package: %s",
                classes.size(), PACKAGE_NAME));

        for (Class<?> clazz : classes)
        {
            JsonSetterAnnotationProcessor processor = new JsonSetterAnnotationProcessor(clazz);
            for (Map.Entry<String, List<Method>> entry : processor.getDuplicateSetterMethods().entrySet())
            {
                LOGGER.info(String.format("Identified ambiguous JSON setter methods for %s.%s",
                        clazz.getName(), entry.getKey()));
                processor.addAnnotationToSetterMethod(entry);
            }
        }
    }
}
