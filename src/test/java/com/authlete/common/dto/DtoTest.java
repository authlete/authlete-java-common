package com.authlete.common.dto;


import static org.junit.Assert.fail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.io.IOException;
import java.util.Set;


public class DtoTest
{
    private static final String PACKAGE_NAME = "com.authlete.common.dto";

    private final ObjectMapper mapper = new ObjectMapper();


    /**
     * Attempt to JSON parse all dto objects.
     * This will pick up any ambiguous setter related errors.
     */
    @Test
    public void checkAmbiguousJsonSetterMethodsForAllDtos()
    {
        Reflections reflections = new Reflections(PACKAGE_NAME, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for (Class<?> aClass : classes)
        {
            // Attempt to parse a blank object as each class
            // this will ensure that the class itself passes the jackson validation
            try
            {
                mapper.readValue("{}", aClass);
            }
            catch (IOException e)
            {
                // Most likely this is due to duplicate ambiguous setter definitions that exist within a class
                // One way to resolve is to use the @JsonSetter annotation on one of the setter methods
                // (generally the one that matches the getter return value).

                // You can check on JsonSetterAnnotationProcessor to check why methods are not being annotated correctly.
                fail(e.getMessage());
            }
        }
    }
}
