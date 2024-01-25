/*
 * Copyright (C) 2024 Authlete, Inc.
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
import static org.junit.Assert.*;
import java.net.URI;
import java.util.List;
import java.util.Map;


public class CredentialIssuerMetadataTest
{
    @SuppressWarnings("unchecked")
    @Test
    public void test_toMap()
    {
        URI      issuer = URI.create("https://example.com");
        JWEAlg[] algs   = new JWEAlg[] { JWEAlg.ECDH_ES };
        JWEEnc[] encs   = new JWEEnc[] { JWEEnc.A128CBC_HS256 };

        CredentialIssuerMetadata metadata = new CredentialIssuerMetadata()
                .setRequireCredentialResponseEncryption(true)
                .setCredentialIssuer(issuer)
                .setCredentialResponseEncryptionAlgValuesSupported(algs)
                .setCredentialResponseEncryptionEncValuesSupported(encs)
                ;

        Map<String, Object> map = metadata.toMap();

        // credential_issuer
        assertEquals("https://example.com", map.get("credential_issuer"));

        // credential_response_encryption
        Map<String, Object> encryption = (Map<String, Object>)map.get("credential_response_encryption");
        assertNotNull(encryption);

        // credential_response_encryption.encrypition_required
        assertEquals(Boolean.TRUE, encryption.get("encryption_required"));

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
    }
}
