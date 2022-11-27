/*
 * Copyright (C) 2022 Authlete, Inc.
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
import com.authlete.common.types.ApplicationType;
import com.authlete.common.types.ClientType;
import com.authlete.common.types.ResponseType;
import com.authlete.common.util.ClientMetadataControl;
import static org.junit.Assert.*;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ClientTest
{
    private static ClientMetadataControl control()
    {
        return new ClientMetadataControl();
    }


    private static void compareIntegers(int expected, Object actual)
    {
        assertNotNull(actual);
        assertTrue(actual instanceof Number);
        assertEquals(expected, ((Number)actual).intValue());
    }


    @Test
    public void testStandardMetadata_clientId()
    {
        Client client = new Client()
                .setClientId(1)
                .setClientIdAlias("alias")
                .setEntityId(URI.create("https://example.com/1"))
                ;

        Map<String, Object> metadata;

        metadata = client.toStandardMetadata();
        assertEquals("1", metadata.get("client_id"));

        metadata = client.toStandardMetadata(control().setAliasPreferred(true));
        assertEquals("1", metadata.get("client_id"));

        client.setClientIdAliasEnabled(true);
        metadata = client.toStandardMetadata(control().setAliasPreferred(true));
        assertEquals("alias", metadata.get("client_id"));

        metadata = client.toStandardMetadata(control().setEntityIdPreferred(true));
        assertEquals("https://example.com/1", metadata.get("client_id"));
    }


    @Test
    public void testStandardMetadata_clientSecret()
    {
        Client client = new Client()
                .setClientSecret("secret")
                ;

        Map<String, Object> metadata;

        metadata = client.toStandardMetadata();
        assertNull(metadata.get("client_secret"));

        metadata = client.toStandardMetadata(control().setSecretIncluded(true));
        assertNull(metadata.get("client_secret"));

        client.setClientType(ClientType.CONFIDENTIAL);
        metadata = client.toStandardMetadata(control().setSecretIncluded(true));
        assertEquals("secret", metadata.get("client_secret"));
    }


    @Test
    public void testStandardMetadata_responseTypes()
    {
        Client client = new Client()
                .setResponseTypes(new ResponseType[] {
                        ResponseType.CODE,
                        ResponseType.CODE_ID_TOKEN_TOKEN
                })
                ;

        Map<String, Object> metadata = client.toStandardMetadata();

        List<String> expected = Arrays.asList("code", "code id_token token");
        assertEquals(expected, metadata.get("response_types"));
    }


    @Test
    public void testStandardMetadata_applicationType()
    {
        Client client = new Client();

        Map<String, Object> metadata;

        metadata = client.toStandardMetadata();
        assertFalse(metadata.containsKey("application_type"));

        client.setApplicationType(ApplicationType.WEB);
        metadata = client.toStandardMetadata();
        assertEquals("web", metadata.get("application_type"));
    }


    @Test
    public void testStandardMetadata_clientName()
    {
        Client client = new Client()
                .setClientName("name")
                .setClientNames(new TaggedValue[] {
                        new TaggedValue("ja", "japanese-name"),
                        new TaggedValue("fr", "french-name")
                })
                ;

        Map<String, Object> metadata = client.toStandardMetadata();

        assertEquals("name",          metadata.get("client_name"));
        assertEquals("japanese-name", metadata.get("client_name#ja"));
        assertEquals("french-name",   metadata.get("client_name#fr"));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testStandardMetadata_jwks()
    {
        String json =
                "{\n" +
                "  \"keys\": [\n" +
                "    {\n" +
                "      \"kty\": \"EC\",\n" +
                "      \"alg\": \"ES256\",\n" +
                "      \"crv\": \"P-256\",\n" +
                "      \"x\": \"xgJzubjK27WB7SxgK9QiK6gqE1UJC1QFDKQfASiNhLI\",\n" +
                "      \"y\": \"vbXWiz19U_54p9w6dBiVXkes5EC1-_DpJSMZyzm6I0I\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Client client = new Client()
                .setJwks(json)
                ;

        Map<String, Object> metadata = client.toStandardMetadata();

        assertTrue(metadata.containsKey("jwks"));
        Object jwksValue = metadata.get("jwks");

        assertTrue(jwksValue instanceof Map);
        Map<String, Object> jwks = (Map<String, Object>)jwksValue;

        assertTrue(jwks.containsKey("keys"));
        Object keysValue = jwks.get("keys");

        assertTrue(keysValue instanceof List);
        List<?> keys = (List<?>)keysValue;

        assertEquals(1, keys.size());
        Object keyValue = keys.get(0);

        assertTrue(keyValue instanceof Map);
        Map<String, Object> key = (Map<String, Object>)keyValue;

        assertEquals("EC",    key.get("kty"));
        assertEquals("ES256", key.get("alg"));
        assertEquals("P-256", key.get("crv"));
    }


    @Test
    public void testStandardMetadata_defaultMaxAge()
    {
        Client client = new Client();

        Map<String, Object> metadata;

        metadata = client.toStandardMetadata();
        assertFalse(metadata.containsKey("default_max_age"));

        metadata = client.toStandardMetadata(control().setZeroIncluded(true));
        assertEquals(0, metadata.get("default_max_age"));

        client.setDefaultMaxAge(10);
        metadata = client.toStandardMetadata();
        assertEquals(10, metadata.get("default_max_age"));
    }


    @Test
    public void testStandardMetadata_requireAuthTime()
    {
        Client client = new Client();

        Map<String, Object> metadata;

        metadata = client.toStandardMetadata();
        assertFalse(metadata.containsKey("require_auth_time"));

        metadata = client.toStandardMetadata(control().setFalseIncluded(true));
        assertEquals(Boolean.FALSE, metadata.get("require_auth_time"));

        client.setAuthTimeRequired(true);
        metadata = client.toStandardMetadata();
        assertEquals(Boolean.TRUE, metadata.get("require_auth_time"));
    }


    @Test
    public void testStandardMetadata_scope()
    {
        Client client = new Client()
                .setExtension(new ClientExtension()
                        .setRequestableScopes(new String[] {
                                "scope1",
                                "scope2"
                        })
                )
                ;

        Map<String, Object> metadata;

        metadata = client.toStandardMetadata();
        assertNull(metadata.get("scope"));

        client.getExtension().setRequestableScopesEnabled(true);
        metadata = client.toStandardMetadata();
        String expected = "scope1 scope2";
        assertEquals(expected, metadata.get("scope"));
    }


    private static Client buildClientWithCustomMetadata()
    {
        String json =
                "{\n" +
                "  \"key_b_false\": false,\n" +
                "  \"key_b_true\": true,\n" +
                "  \"key_i_zero\": 0,\n" +
                "  \"key_i_one\": 1,\n" +
                "  \"key_s_null\": null,\n" +
                "  \"key_s_empty\": \"\",\n" +
                "  \"key_s_abc\": \"abc\",\n" +
                "  \"key_array\": [\"a\",\"b\",\"c\"],\n" +
                "  \"key_map\": {\n" +
                "    \"key\": \"value\"" +
                "  }" +
                "}";

        Client client = new Client()
                .setCustomMetadata(json)
                ;

        return client;
    }


    @Test
    public void testStandardMetadata_customMetadata_1()
    {
        Client client = buildClientWithCustomMetadata();

        Map<String, Object> metadata = client.toStandardMetadata();

        assertFalse(metadata.containsKey("key_s_abc"));
        assertFalse(metadata.containsKey("key_b_false"));
        assertFalse(metadata.containsKey("key_b_true"));
        assertFalse(metadata.containsKey("key_i_zero"));
        assertFalse(metadata.containsKey("key_i_one"));
        assertFalse(metadata.containsKey("key_s_null"));
        assertFalse(metadata.containsKey("key_s_empty"));
        assertFalse(metadata.containsKey("key_s_abc"));
        assertFalse(metadata.containsKey("key_array"));
        assertFalse(metadata.containsKey("key_map"));
    }


    @Test
    public void testStandardMetadata_customMetadata_2()
    {
        Client client = buildClientWithCustomMetadata();

        Map<String, Object> metadata =
                client.toStandardMetadata(control()
                        .setCustomIncluded(true)
                );

        assertFalse(metadata.containsKey("key_b_false"));
        assertTrue (metadata.containsKey("key_b_true"));
        assertFalse(metadata.containsKey("key_i_zero"));
        assertTrue (metadata.containsKey("key_i_one"));
        assertFalse(metadata.containsKey("key_s_null"));
        assertTrue (metadata.containsKey("key_s_empty"));
        assertTrue (metadata.containsKey("key_s_abc"));
        assertTrue (metadata.containsKey("key_array"));
        assertTrue (metadata.containsKey("key_map"));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testStandardMetadata_customMetadata_3()
    {
        Client client = buildClientWithCustomMetadata();

        Map<String, Object> metadata =
                client.toStandardMetadata(control()
                        .setCustomIncluded(true)
                        .setNullIncluded(true)
                        .setZeroIncluded(true)
                        .setFalseIncluded(true)
                );

        assertTrue(metadata.containsKey("key_b_false"));
        assertTrue(metadata.containsKey("key_b_true"));
        assertTrue(metadata.containsKey("key_i_zero"));
        assertTrue(metadata.containsKey("key_i_one"));
        assertTrue(metadata.containsKey("key_s_null"));
        assertTrue(metadata.containsKey("key_s_empty"));
        assertTrue(metadata.containsKey("key_s_abc"));
        assertTrue(metadata.containsKey("key_array"));
        assertTrue(metadata.containsKey("key_map"));

        assertEquals(Boolean.FALSE, metadata.get("key_b_false"));
        assertEquals(Boolean.TRUE,  metadata.get("key_b_true"));
        compareIntegers(0,          metadata.get("key_i_zero"));
        compareIntegers(1,          metadata.get("key_i_one"));
        assertNull(                 metadata.get("key_s_null"));
        assertEquals("",            metadata.get("key_s_empty"));
        assertEquals("abc",         metadata.get("key_s_abc"));

        List<String> expectedArray = Arrays.asList("a", "b", "c");
        assertEquals(expectedArray, metadata.get("key_array"));

        Object mapValue = metadata.get("key_map");

        assertTrue(mapValue instanceof Map);
        Map<String, Object> map = (Map<String, Object>)mapValue;

        assertTrue(map.containsKey("key"));
        assertEquals("value", map.get("key"));
    }


    @Test
    public void testStandardMetadata_clientIdIssuedAt()
    {
        Client client = new Client()
                .setCreatedAt(12345L)
                ;

        Map<String, Object> metadata = client.toStandardMetadata();

        compareIntegers(12, metadata.get("client_id_issued_at"));
    }
}
