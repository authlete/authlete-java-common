package com.authlete.common.annotationprocessor;


import com.fasterxml.jackson.annotation.JsonSetter;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class JsonSetterAnnotationProcessorTest
{
    @Test
    public void testGetSetterMethodToAnnotate_AmbiguousSettersAndMatchingGetterDefined() throws NotFoundException
    {
        JsonSetterAnnotationProcessor processor = new JsonSetterAnnotationProcessor(AnnotationObject.class);
        assertTrue(processor.getDuplicateSetterMethods().containsKey("setA"));
        CtMethod method = processor.getSetterMethodToAnnotate("setA");

        // Appropriate setter is available
        Assert.assertNotNull(method);
        assertEquals(Integer.class.getName(), method.getParameterTypes()[0].getName());
    }


    @Test
    public void testGetSetterMethodToAnnotate_AmbiguousSettersButNoGetterDefined() throws NotFoundException
    {
        JsonSetterAnnotationProcessor processor = new JsonSetterAnnotationProcessor(AnnotationObject.class);
        assertTrue(processor.getDuplicateSetterMethods().containsKey("setB"));
        CtMethod method = processor.getSetterMethodToAnnotate("setB");

        // No getter defined in the class, we cannot determine which setter to annotate
        assertNull(method);
    }


    @Test
    public void testGetSetterMethodToAnnotate_NoAmbiguousSetters() throws NotFoundException
    {
        JsonSetterAnnotationProcessor processor = new JsonSetterAnnotationProcessor(AnnotationObject.class);
        // No ambiguous setters are defined,
        assertFalse(processor.getDuplicateSetterMethods().containsKey("setC"));
    }


    @Test
    public void testGetSetterMethodToAnnotate_AmbiguousSettersWithOneAnnotatedWithJsonSetter() throws NotFoundException
    {
        JsonSetterAnnotationProcessor processor = new JsonSetterAnnotationProcessor(AnnotationObject.class);
        // At least one of the ambiguous setters is annotated with @JsonSetter
        assertFalse(processor.getDuplicateSetterMethods().containsKey("setD"));
    }
}


/**
 * An object used in {@link JsonSetterAnnotationProcessorTest} to test different
 * ambiguous setter and getter configuration scenarios.
 */
class AnnotationObject
{
    /**
     * Ambiguous setters and a matching getter defined.
     */
    private String a;

    /**
     * Ambiguous setters but no getter methods defined.
     */
    private String b;

    /**
     * No ambiguous setters defined.
     */
    private String c;

    /**
     * Ambiguous setters defined with one having the
     * {@link com.fasterxml.jackson.annotation.JsonSetter} annotation.
     */
    private String d;


    public Integer getA()
    {
        return Integer.valueOf(a);
    }


    public void setA(String a)
    {
        this.a = a;
    }


    public void setA(Integer a)
    {
        this.a = a.toString();
    }


    public void setB(String b)
    {
        this.b = b;
    }


    public void setB(Integer b)
    {
        this.b = b.toString();
    }


    public void setC(String c)
    {
        this.c = c;
    }


    public String getC()
    {
        return c;
    }


    public void setD(String d)
    {
        this.d = d;
    }


    @JsonSetter
    public void setD(Integer d)
    {
        this.d = d.toString();
    }
}
