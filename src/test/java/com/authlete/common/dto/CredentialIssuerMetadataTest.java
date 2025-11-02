/*
 * Copyright (C) 2024-2025 Authlete, Inc.
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
import com.authlete.common.types.JWEAlg;
import com.authlete.common.types.JWEEnc;
import com.authlete.common.types.JWEZip;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


public class CredentialIssuerMetadataTest
{
    @SuppressWarnings("unchecked")
    @Test
    public void test_toMap_request_encryption() throws ParseException
    {
        String jwks =
                "{\n"
                + "  \"keys\": [\n"
                + "    {\n"
                + "      \"kty\": \"EC\",\n"
                + "      \"crv\": \"P-256\",\n"
                + "      \"x\": \"8fpkqvB9-NjASgqR4KnM9s1gjyaDZpYYp9E65c7glns\",\n"
                + "      \"y\": \"SdbSFS314C2N415VQ7IaXkxHRH34aA32csKOs_JipSg\",\n"
                + "      \"d\": \"gTVIUWvmYAW2WT_lOMiQGBK2BbRHOhew-sO5JBTCkp4\",\n"
                + "      \"kid\": \"TxNdpxeiLUxuPXh_INadmBVkOqk77pmYLL7BthTpZsA\"\n"
                + "    }\n"
                + "  ]\n"
                + "}\n"
                + "";
        JWEEnc[] encs = new JWEEnc[] { JWEEnc.A128CBC_HS256 };
        JWEZip[] zips = new JWEZip[] { JWEZip.DEF };

        CredentialIssuerMetadata metadata = new CredentialIssuerMetadata()
                .setCredentialRequestEncryptionJwks(jwks)
                .setCredentialRequestEncryptionEncValuesSupported(encs)
                .setCredentialRequestEncryptionZipValuesSupported(zips)
                .setRequireCredentialRequestEncryption(true)
                ;

        Map<String, Object> map = metadata.toMap();

        // credential_request_encryption
        Map<String, Object> encryption = (Map<String, Object>)map.get("credential_request_encryption");
        assertNotNull(encryption);

        // credential_request_encryption.jwks
        Map<String, Object> publicJwks = (Map<String, Object>)encryption.get("jwks");

        List<JWK> publicKeys = JWKSet.parse(publicJwks).getKeys();
        assertEquals(1, publicKeys.size());

        for (JWK key : publicKeys)
        {
            assertFalse("Keys in 'jwks' must not be private keys.", key.isPrivate());
        }

        // credential_request_encryption.enc_values_supported
        List<String> encValues = (List<String>)encryption.get("enc_values_supported");
        assertNotNull(encValues);
        assertEquals(1, encValues.size());
        assertEquals("A128CBC-HS256", encValues.get(0));

        // credential_request_encryption.zip_values_supported
        List<String> zipValues = (List<String>)encryption.get("zip_values_supported");
        assertNotNull(zipValues);
        assertEquals(1, zipValues.size());
        assertEquals("DEF", zipValues.get(0));

        // credential_request_encryption.encrypition_required
        assertEquals(Boolean.TRUE, encryption.get("encryption_required"));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void test_toMap_response_encryption()
    {
        JWEAlg[] algs = new JWEAlg[] { JWEAlg.ECDH_ES };
        JWEEnc[] encs = new JWEEnc[] { JWEEnc.A128CBC_HS256 };
        JWEZip[] zips = new JWEZip[] { JWEZip.DEF };

        CredentialIssuerMetadata metadata = new CredentialIssuerMetadata()
                .setCredentialResponseEncryptionAlgValuesSupported(algs)
                .setCredentialResponseEncryptionEncValuesSupported(encs)
                .setCredentialResponseEncryptionZipValuesSupported(zips)
                .setRequireCredentialResponseEncryption(true)
                ;

        Map<String, Object> map = metadata.toMap();

        // credential_response_encryption
        Map<String, Object> encryption = (Map<String, Object>)map.get("credential_response_encryption");
        assertNotNull(encryption);

        // credential_response_encryption.alg_values_supported
        List<String> algValues = (List<String>)encryption.get("alg_values_supported");
        assertNotNull(algValues);
        assertEquals(1, algValues.size());
        assertEquals("ECDH-ES", algValues.get(0));

        // credential_response_encryption.enc_values_supported
        List<String> encValues = (List<String>)encryption.get("enc_values_supported");
        assertNotNull(encValues);
        assertEquals(1, encValues.size());
        assertEquals("A128CBC-HS256", encValues.get(0));

        // credential_response_encryption.zip_values_supported
        List<String> zipValues = (List<String>)encryption.get("zip_values_supported");
        assertNotNull(zipValues);
        assertEquals(1, zipValues.size());
        assertEquals("DEF", zipValues.get(0));

        // credential_response_encryption.encrypition_required
        assertEquals(Boolean.TRUE, encryption.get("encryption_required"));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void test_toMap_batch_size_1()
    {
        CredentialIssuerMetadata metadata = new CredentialIssuerMetadata()
                .setBatchSize(1)
                ;

        Map<String, Object> map = metadata.toMap();

        // batch_credential_issuance
        Map<String, Object> bci = (Map<String, Object>)map.get("batch_credential_issuance");
        assertNull(bci);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void test_toMap_batch_size_2()
    {
        CredentialIssuerMetadata metadata = new CredentialIssuerMetadata()
                .setBatchSize(2)
                ;

        Map<String, Object> map = metadata.toMap();

        // batch_credential_issuance
        Map<String, Object> bci = (Map<String, Object>)map.get("batch_credential_issuance");
        assertNotNull(bci);

        // batch_credential_issuance.batch_size
        Number bs = (Number)bci.get("batch_size");
        assertNotNull(bs);
        assertEquals(2, bs.intValue());
    }


    @SuppressWarnings("unchecked")
    @Test
    public void test_toMap_display()
    {
        String display =
                "[\n"
                + "  {\n"
                + "    \"name\": \"Example University\",\n"
                + "    \"locale\": \"en-US\",\n"
                + "    \"logo\": {\n"
                + "      \"uri\": \"https://university.example.edu/public/logo.png\",\n"
                + "      \"alt_text\":\"a square logo of a university\"\n"
                + "    }\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": \"Example Université\",\n"
                + "    \"locale\": \"fr-FR\",\n"
                + "    \"logo\": {\n"
                + "      \"uri\": \"https://university.example.edu/public/logo.png\",\n"
                + "      \"alt_text\":\"Un logo universitaire carré\"\n"
                + "    }\n"
                + "  }\n"
                + "]";

        CredentialIssuerMetadata metadata = new CredentialIssuerMetadata()
                .setDisplay(display)
                ;

        Map<String, Object> map = metadata.toMap();

        // display
        Object displayObject = map.get("display");
        assertNotNull(displayObject);

        assertTrue("The value of 'display' should be a list", displayObject instanceof List);
        List<Object> displayList = (List<Object>)displayObject;

        assertEquals(2, displayList.size());
    }
}
