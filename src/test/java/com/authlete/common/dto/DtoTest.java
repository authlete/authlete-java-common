/*
 * Copyright (C) 2025 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
     * <p>
     * Running this test directly without the "process-classes" maven step will cause the test to fail.
     */
    @Test
    public void checkAmbiguousJsonSetterMethodsForAllDtos()
    {
        Reflections reflections = new Reflections(PACKAGE_NAME, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for (Class<?> aClass : classes)
        {
            // Attempt to parse a blank object as each class this will ensure that the class itself passes the jackson setter validation
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
