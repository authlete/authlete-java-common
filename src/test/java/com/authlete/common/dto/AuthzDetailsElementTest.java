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
import com.google.gson.JsonParseException;
import com.neovisionaries.security.Digest;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthzDetailsElementTest
{
    private AuthzDetailsElement deserialize(String json)
    {
        return AuthzDetailsElement.fromJson(json);
    }


    private void verify(
            AuthzDetailsElement element, String type, String[] locations,
            String[] actions, String identifier, String otherFields)
    {
        assertNotNull(element);

        verifyType(       element, type);
        verifyLocations(  element, locations);
        verifyActions(    element, actions);
        verifyIdentifier( element, identifier);
        verifyOtherFields(element, otherFields);
    }


    private void verifyType(AuthzDetailsElement element, String type)
    {
        if (type == null)
        {
            assertNull(element.getType());
        }
        else
        {
            assertEquals(type, element.getType());
        }
    }


    private void verifyLocations(AuthzDetailsElement element, String[] locations)
    {
        if (locations == null)
        {
            assertNull(element.getLocations());
        }
        else
        {
            assertArrayEquals(locations, element.getLocations());
        }
    }


    private void verifyActions(AuthzDetailsElement element, String[] actions)
    {
        if (actions == null)
        {
            assertNull(element.getActions());
        }
        else
        {
            assertArrayEquals(actions, element.getActions());
        }
    }


    private void verifyIdentifier(AuthzDetailsElement element, String identifier)
    {
        if (identifier == null)
        {
            assertNull(element.getIdentifier());
        }
        else
        {
            assertEquals(identifier, element.getIdentifier());
        }
    }


    private void verifyOtherFields(AuthzDetailsElement element, String otherFields)
    {
        if (otherFields == null)
        {
            assertNull(element.getOtherFields());
            return;
        }

        String actualOtherFields = element.getOtherFields();
        assertNotNull(actualOtherFields);

        String actualDigest   = digestJson(actualOtherFields);
        String expectedDigest = digestJson(otherFields);

        if (actualDigest.equals(expectedDigest))
        {
            return;
        }

        String message = "Digests of otherFields don't match.\n"
                       + "\tActual   = " + actualOtherFields + "\n"
                       + "\tExpected = " + otherFields
                       ;

        fail(message);
    }


    private String digestJson(String json)
    {
        try
        {
            return Digest.getInstanceSHA256().updateJson(json).digestAsString();
        }
        catch (IOException e)
        {
            return null;
        }
    }


    @Test
    public void test00()
    {
        verify(deserialize("{}"), null, null, null, null, null);
    }


    @Test
    public void test01()
    {
        String json =
            "{" +
            "  \"type\":\"my_type\"," +
            "  \"locations\": [\"loc0\", \"loc1\"], " +
            "  \"actions\": [\"act0\", \"act1\"], " +
            "  \"identifier\": \"my_id\"" +
            "}";

        verify(deserialize(json),
            "my_type",
            new String[] { "loc0", "loc1" },
            new String[] { "act0", "act1" },
            "my_id",
            null
        );
    }


    @Test
    public void test02()
    {
        String otherProps =
            "  \"prop0\": \"a\",\n" +
            "  \"prop1\": [\"b\", \"c\"],\n" +
            "  \"prop2\": {\n" +
            "    \"sub0\": \"d\",\n" +
            "    \"sub1\": [\"e\", \"f\"]\n" +
            "  }\n";

        String json =
            "{\n" +
            "  \"type\": \"my_type\",\n" +
            otherProps +
            "}";

        verify(deserialize(json),
            "my_type",
            null,
            null,
            null,
            "{\n" + otherProps + "}"
        );
    }


    @Test
    public void test03()
    {
        String json =
            "{\n" +
            "  \"prop0\": \"a\",\n" +
            "  \"prop1\": [\"b\", \"c\"],\n" +
            "  \"prop2\": {\n" +
            "    \"sub0\": \"d\",\n" +
            "    \"sub1\": [\"e\", \"f\"]\n" +
            "  }\n" +
            "}";

        AuthzDetailsElement element = deserialize(json);

        Map<?,?> map = element.getOtherFieldsAsMap();

        // prop0
        assertTrue(map.containsKey("prop0"));
        assertEquals("a", map.get("prop0"));

        // prop1
        assertTrue(map.containsKey("prop1"));
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("b");
        expectedList.add("c");
        assertEquals(expectedList, map.get("prop1"));

        // prop2
        assertTrue(map.containsKey("prop2"));
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        expectedMap.put("sub0", "d");
        expectedList.clear();
        expectedList.add("e");
        expectedList.add("f");
        expectedMap.put("sub1", expectedList);
        assertEquals(expectedMap, map.get("prop2"));
    }


    @Test
    public void test04()
    {
        AuthzDetailsElement element = new AuthzDetailsElement();

        String json = element.toJson();
        Map<?,?> map = new Gson().fromJson(json, Map.class);

        // type
        assertFalse(map.containsKey("type"));

        // locations
        assertFalse(map.containsKey("locations"));

        // actions
        assertFalse(map.containsKey("actions"));

        // identifier
        assertFalse(map.containsKey("identifier"));
    }


    @Test
    public void test05()
    {
        String otherFields =
            "{\n" +
            "  \"prop0\": \"a\",\n" +
            "  \"prop1\": [\"b\", \"c\"]\n" +
            "}";

        AuthzDetailsElement element = new AuthzDetailsElement()
            .setType("my_type")
            .setLocations(new String[]{"loc0", "loc1"})
            .setActions(new String[]{"act0", "act1"})
            .setIdentifier("my_id")
            .setOtherFields(otherFields)
            ;

        String json = element.toJson();
        Map<?,?> map = new Gson().fromJson(json, Map.class);

        // type
        assertTrue(map.containsKey("type"));
        assertEquals("my_type", map.get("type"));

        // locations
        assertTrue(map.containsKey("locations"));
        List<String> locations = new ArrayList<String>();
        locations.add("loc0");
        locations.add("loc1");
        assertEquals(locations, map.get("locations"));

        // actions
        assertTrue(map.containsKey("actions"));
        List<String> actions = new ArrayList<String>();
        actions.add("act0");
        actions.add("act1");
        assertEquals(actions, map.get("actions"));

        // identifier
        assertTrue(map.containsKey("identifier"));
        assertEquals("my_id", map.get("identifier"));

        // Other fields
        assertTrue(map.containsKey("prop0"));
        assertEquals("a", map.get("prop0"));

        assertTrue(map.containsKey("prop1"));
        List<String> prop1 = new ArrayList<String>();
        prop1.add("b");
        prop1.add("c");
        assertEquals(prop1, map.get("prop1"));
    }


    @Test(expected = JsonParseException.class)
    public void test06()
    {
        deserialize("{\"locations\":false}");
    }
}
