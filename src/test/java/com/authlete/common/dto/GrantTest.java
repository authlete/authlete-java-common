/*
 * Copyright (C) 2021 Authlete, Inc.
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


public class GrantTest
{
    private Grant deserialize(String json)
    {
        return Grant.fromJson(json);
    }


    private static Gson createDeserializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                Grant.class, new GrantDeserializer())
            .create();
    }


    @Test
    public void test00()
    {
        Grant grant = deserialize("{}");
        assertNotNull(grant);

        assertNull(grant.getScopes());
        assertNull(grant.getClaims());
        assertNull(grant.getAuthorizationDetails());
    }


    @Test
    public void test01()
    {
        String json =
            "{\n" +
            "  \"scopes\": [\n" +
            "    {\n" +
            "      \"scope\": \"scp0\",\n" +
            "      \"resource\": [\n" +
            "        \"rsc0\",\n" +
            "        \"rsc1\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"scope\": \"scp1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"resource\": [\n" +
            "        \"rsc2\",\n" +
            "        \"rsc3\"\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"claims\": [\n" +
            "    \"clm0\",\n" +
            "    \"clm1\"\n" +
            "  ],\n" +
            "  \"authorization_details\": [\n" +
            "    {\n" +
            "      \"type\":\"typ0\",\n" +
            "      \"locations\": [\"loc0\", \"loc1\"],\n" +
            "      \"actions\": [\"act0\", \"act1\"],\n" +
            "      \"datatypes\": [\"dty0\", \"dty1\"],\n" +
            "      \"identifier\": \"my_id\",\n" +
            "      \"privileges\": [\"prv0\", \"prv1\"]\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\":\"typ1\",\n" +
            "      \"prop0\":\"a\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

        Grant grant = deserialize(json);

        //--------------------------------------------------
        // scopes
        //--------------------------------------------------

        GrantScope[] scopes = grant.getScopes();
        assertNotNull(scopes);
        assertEquals(3, scopes.length);

        // GrantScope 0
        GrantScope gs0 = scopes[0];
        assertNotNull(gs0);

        // GrantScope 0, scope
        assertEquals("scp0", gs0.getScope());

        // GrantScope 0, resource
        String[] gs0resource = gs0.getResource();
        assertEquals(2, gs0resource.length);
        assertEquals("rsc0", gs0resource[0]);
        assertEquals("rsc1", gs0resource[1]);

        // GrantScope 1
        GrantScope gs1 = scopes[1];
        assertNotNull(gs1);

        // GrantScope 1, scope
        assertEquals("scp1", gs1.getScope());

        // GrantScope 1, resource
        assertNull(gs1.getResource());

        // GrantScope 2
        GrantScope gs2 = scopes[2];
        assertNotNull(gs2);

        // GrantScope 2, scope
        assertNull(gs2.getScope());

        // GrantScope 2, resource
        String[] gs2resource = gs2.getResource();
        assertEquals(2, gs0resource.length);
        assertEquals("rsc2", gs2resource[0]);
        assertEquals("rsc3", gs2resource[1]);

        //--------------------------------------------------
        // claims
        //--------------------------------------------------

        String[] claims = grant.getClaims();
        assertNotNull(claims);
        assertEquals(2, claims.length);

        // claim 0
        assertEquals("clm0", claims[0]);

        // claim 1
        assertEquals("clm1", claims[1]);

        //--------------------------------------------------
        // authorization_details
        //--------------------------------------------------

        AuthzDetails details = grant.getAuthorizationDetails();

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

        // element 0, datatypes
        String[] datatypes = new String[]{ "dty0", "dty1" };
        assertArrayEquals(datatypes, element0.getDataTypes());

        // element 0, identifier
        assertEquals("my_id", element0.getIdentifier());

        // element 0, privileges
        String[] privileges = new String[]{ "prv0", "prv1" };
        assertArrayEquals(privileges, element0.getPrivileges());

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
        Grant grant = new Grant();

        String json = grant.toJson();

        assertEquals("{}", json);
    }


    @Test
    public void test03()
    {
        Grant grant = new Grant();

        GrantScope[] scopes = new GrantScope[3];
        scopes[0] = new GrantScope().setScope("scp0").setResource(new String[] { "rsc0", "rsc1" });
        scopes[1] = new GrantScope().setScope("scp1");
        scopes[2] = new GrantScope().setResource(new String[] { "rsc2", "rsc3" });
        grant.setScopes(scopes);

        String[] claims = new String[] { "clm0", "clm1" };
        grant.setClaims(claims);

        AuthzDetailsElement[] elements = new AuthzDetailsElement[] {
                new AuthzDetailsElement().setType("typ0"),
                new AuthzDetailsElement().setType("typ1")
        };
        AuthzDetails details = new AuthzDetails().setElements(elements);
        grant.setAuthorizationDetails(details);

        String json = grant.toJson();

        Grant grant2 = createDeserializer().fromJson(json, Grant.class);

        assertNotNull(grant2);

        GrantScope[] scopes2 = grant2.getScopes();
        assertNotNull(scopes2);
        assertEquals(3, scopes2.length);

        assertEquals("scp0", scopes[0].getScope());
        assertEquals("rsc0", scopes[0].getResource()[0]);
        assertEquals("rsc1", scopes[0].getResource()[1]);

        assertEquals("scp1", scopes[1].getScope());
        assertNull(scopes[1].getResource());

        assertNull(scopes[2].getScope());
        assertEquals("rsc2", scopes[2].getResource()[0]);
        assertEquals("rsc3", scopes[2].getResource()[1]);

        String[] claims2 = grant2.getClaims();
        assertNotNull(claims2);
        assertEquals(2, claims2.length);

        assertEquals("clm0", claims2[0]);
        assertEquals("clm1", claims2[1]);

        AuthzDetails details2 = grant2.getAuthorizationDetails();
        assertNotNull(details2);

        AuthzDetailsElement[] elements2 = details2.getElements();
        assertNotNull(elements2);
        assertEquals(2, elements2.length);

        assertEquals("typ0", elements2[0].getType());
        assertEquals("typ1", elements2[1].getType());
    }
}
