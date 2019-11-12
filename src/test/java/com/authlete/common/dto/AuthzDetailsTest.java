/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.dto;


import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.Assert.*;
import java.util.Map;


public class AuthzDetailsTest
{
    private AuthzDetails deserialize(String json)
    {
        return AuthzDetails.fromJson(json);
    }


    private static Gson createDeserializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                AuthzDetails.class, new AuthzDetailsDeserializer())
            .registerTypeAdapter(
                AuthzDetailsElement.class, new AuthzDetailsElementDeserializer())
            .create();
    }


    @Test
    public void test00()
    {
        AuthzDetails details = deserialize("[]");
        assertNotNull(details);

        AuthzDetailsElement[] elements = details.getElements();
        assertNotNull(elements);
        assertEquals(0, elements.length);
    }


    @Test
    public void test01()
    {
        String json =
            "[\n" +
            "  {\n" +
            "    \"type\":\"typ0\",\n" +
            "    \"locations\": [\"loc0\", \"loc1\"],\n" +
            "    \"actions\": [\"act0\", \"act1\"],\n" +
            "    \"identifier\": \"my_id\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"type\":\"typ1\",\n" +
            "    \"prop0\":\"a\"\n" +
            "  }\n" +
            "]";

        AuthzDetails details = deserialize(json);

        AuthzDetailsElement[] elements = details.getElements();
        assertNotNull(elements);
        assertEquals(2, elements.length);

        // element 0
        AuthzDetailsElement element0 = elements[0];
        assertNotNull(element0);

        // element 0, type
        assertEquals("typ0", element0.getType());

        // element 0, locations
        String[] locations = new String[]{ "loc0", "loc1" };
        assertArrayEquals(locations, element0.getLocations());

        // element 0, actions
        String[] actions = new String[]{ "act0", "act1" };
        assertArrayEquals(actions, element0.getActions());

        // element 0, identifier
        assertEquals("my_id", element0.getIdentifier());

        // element 1
        AuthzDetailsElement element1 = elements[1];
        assertNotNull(element1);

        // element 1, type
        assertEquals("typ1", element1.getType());

        // element 1, other fields.
        Map<?,?> others = element1.getOtherFieldsAsMap();
        assertNotNull(others);

        // element 1, other fields, prop0
        assertTrue(others.containsKey("prop0"));
        assertEquals("a", others.get("prop0"));
    }


    @Test
    public void test02()
    {
        AuthzDetails details = new AuthzDetails();

        String json = details.toJson();

        assertEquals("null", json);
    }


    @Test
    public void test03()
    {
        AuthzDetails details = new AuthzDetails();
        details.setElements(new AuthzDetailsElement[0]);

        String json = details.toJson();
        AuthzDetailsElement[] elements =
            createDeserializer().fromJson(json, AuthzDetailsElement[].class);

        assertNotNull(elements);
        assertEquals(0, elements.length);
    }


    @Test
    public void test04()
    {
        AuthzDetails details = new AuthzDetails();
        details.setElements(new AuthzDetailsElement[]{
                new AuthzDetailsElement().setType("typ0"),
                new AuthzDetailsElement().setType("typ1"),
                null
        });

        String json = details.toJson();

        AuthzDetailsElement[] elements =
                createDeserializer().fromJson(json, AuthzDetailsElement[].class);

        assertNotNull(elements);
        assertEquals(3, elements.length);

        assertNotNull(elements[0]);
        assertEquals("typ0", elements[0].getType());

        assertNotNull(elements[1]);
        assertEquals("typ1", elements[1].getType());

        assertNull(elements[2]);
    }
}
