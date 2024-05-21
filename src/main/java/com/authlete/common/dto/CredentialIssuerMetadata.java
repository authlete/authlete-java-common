/*
 * Copyright (C) 2023-2024 Authlete, Inc.
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

import static com.authlete.common.util.MapUtils.put;
import static com.authlete.common.util.MapUtils.putJsonObject;
import java.io.Serializable;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import com.authlete.common.types.JWEAlg;
import com.authlete.common.types.JWEEnc;


/**
 * A class that represents the set of credential issuer metadata.
 * The set consists of the following.
 *
 * <ul>
 * <li>{@code credential_issuer}
 * <li>{@code authorization_servers}
 * <li>{@code credential_endpoint}
 * <li>{@code batch_credential_endpoint}
 * <li>{@code deferred_credential_endpoint}
 * <li>{@code credential_response_encryption}
 * <li>{@code credential_configurations_supported}
 * </ul>
 *
 * <p>
 * A credential issuer announces these metadata at
 * {@code /.well-known/openid-credential-issuer}.
 * </p>
 *
 * <h3>Breaking Changes</h3>
 *
 * <p>
 * The "OpenID for Verifiable Credential Issuance" specification tends to
 * repeat breaking changes. Such changes affect this Java class.
 * The following are notable changes.
 * </p>
 *
 * <ol>
 * <li>
 * The type of the "{@code credentials_supported}" metadata has been changed
 * from a JSON array to a JSON object.
 * <li>
 * The "{@code authorization_server}" metadata has been renamed to
 * "{@code authorization_servers}", and its type has been changed from a string
 * to a JSON array.
 * <li>
 * The "{@code credentials_supported}" metadata has been renamed to
 * "{@code credential_configurations_supported}". (December, 2023)
 * <li>
 * The "{@code credential_response_encryption_alg_values_supported}" metadata,
 * the "{@code credential_response_encryption_enc_values_supported}" metadata,
 * and the "{@code require_credential_response_encryption}" metadata have been
 * packed into one JSON object, "{@code credential_response_encryption}".
 * (January, 2024)
 * </ol>
 *
 * @since 3.55
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 *
 * @see <a href="https://openid.github.io/OpenID4VCI/openid-4-verifiable-credential-issuance-wg-draft.html"
 *      >OpenID for Verifiable Credential Issuance, Working Draft</a>
 */
public class CredentialIssuerMetadata implements Serializable
{
    private static final long serialVersionUID = 4L;


    /**
     * The identifier of the credential issuer.
     */
    private URI credentialIssuer;


    /**
     * The identifiers of the authorization servers that the credential issuer
     * relies on for authorization.
     *
     * @since 3.86
     */
    private URI[] authorizationServers;


    /**
     * The URL of the credential endpoint of the credential issuer.
     */
    private URI credentialEndpoint;


    /**
     * The URL of the batch credential endpoint of the credential issuer.
     */
    private URI batchCredentialEndpoint;


    /**
     * The URL of the deferred credential endpoint of the credential issuer.
     */
    private URI deferredCredentialEndpoint;


    /**
     * The supported JWE alg algorithms for credential response encryption.
     *
     * @since 3.86
     */
    private JWEAlg[] credentialResponseEncryptionAlgValuesSupported;


    /**
     * The supported JWE enc algorithms for credential response encryption.
     *
     * @since 3.86
     */
    private JWEEnc[] credentialResponseEncryptionEncValuesSupported;


    /**
     * The boolean flag indicating whether credential response encryption is
     * required.
     *
     * @since 3.86
     */
    private boolean requireCredentialResponseEncryption;


    /**
     * A JSON object describing supported credential configurations.
     */
    private String credentialsSupported;


    /**
     * The default constructor.
     */
    public CredentialIssuerMetadata()
    {
    }


    /**
     * Copy constructor.
     *
     * @param metadata
     *         Source to copy data from. {@code null} won't raise any exception.
     */
    public CredentialIssuerMetadata(CredentialIssuerMetadata metadata)
    {
        if (metadata == null)
        {
            return;
        }

        credentialIssuer                               = metadata.getCredentialIssuer();
        authorizationServers                           = metadata.getAuthorizationServers();
        credentialEndpoint                             = metadata.getCredentialEndpoint();
        batchCredentialEndpoint                        = metadata.getBatchCredentialEndpoint();
        deferredCredentialEndpoint                     = metadata.getDeferredCredentialEndpoint();
        credentialResponseEncryptionAlgValuesSupported = metadata.getCredentialResponseEncryptionAlgValuesSupported();
        credentialResponseEncryptionEncValuesSupported = metadata.getCredentialResponseEncryptionEncValuesSupported();
        requireCredentialResponseEncryption            = metadata.isRequireCredentialResponseEncryption();
        credentialsSupported                           = metadata.getCredentialsSupported();
    }


    /**
     * Get the identifier of the credential issuer. This property corresponds
     * to the {@code credential_issuer} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * @return
     *         The identifier of the credential issuer.
     */
    public URI getCredentialIssuer()
    {
        return credentialIssuer;
    }


    /**
     * Set the identifier of the credential issuer. This property corresponds
     * to the {@code credential_issuer} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * @param issuer
     *         The identifier of the credential issuer.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuerMetadata setCredentialIssuer(URI issuer)
    {
        this.credentialIssuer = issuer;

        return this;
    }


    /**
     * Get the identifiers of the authorization servers that the credential
     * issuer relies on for authorization. This property corresponds to the
     * {@code authorization_servers} metadata.
     *
     * <p>
     * When the credential issuer works as an authorization server for itself,
     * this property should be omitted.
     * </p>
     *
     * @return
     *         The identifiers of the authorization servers that the credential
     *         issuer relies on for authorization.
     *
     * @since 3.86
     */
    public URI[] getAuthorizationServers()
    {
        return authorizationServers;
    }


    /**
     * Set the identifiers of the authorization servers that the credential
     * issuer relies on for authorization. This property corresponds to the
     * {@code authorization_servers} metadata.
     *
     * <p>
     * When the credential issuer works as an authorization server for itself,
     * this property should be omitted.
     * </p>
     *
     * @param servers
     *         The identifiers of the authorization servers that the credential
     *         issuer relies on for authorization.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.86
     */
    public CredentialIssuerMetadata setAuthorizationServers(URI[] servers)
    {
        this.authorizationServers = servers;

        return this;
    }


    /**
     * Get the URL of the credential endpoint. This property corresponds to the
     * {@code credential_endpoint} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * @return
     *         The URL of the credential endpoint.
     */
    public URI getCredentialEndpoint()
    {
        return credentialEndpoint;
    }


    /**
     * Set the URL of the credential endpoint. This property corresponds to the
     * {@code credential_endpoint} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * @param endpoint
     *         The URL of the credential endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuerMetadata setCredentialEndpoint(URI endpoint)
    {
        this.credentialEndpoint = endpoint;

        return this;
    }


    /**
     * Get the URL of the batch credential endpoint. This property corresponds
     * to the {@code batch_credential_endpoint} metadata.
     *
     * <p>
     * If the credential issuer does not support the batch credential endpoint,
     * this property should be omitted.
     * </p>
     *
     * @return
     *         The URL of the batch credential endpoint.
     */
    public URI getBatchCredentialEndpoint()
    {
        return batchCredentialEndpoint;
    }


    /**
     * Set the URL of the batch credential endpoint. This property corresponds
     * to the {@code batch_credential_endpoint} metadata.
     *
     * <p>
     * If the credential issuer does not support the batch credential endpoint,
     * this property should be omitted.
     * </p>
     *
     * @param endpoint
     *         The URL of the batch credential endpoint.
     *
     * @return
     */
    public CredentialIssuerMetadata setBatchCredentialEndpoint(URI endpoint)
    {
        this.batchCredentialEndpoint = endpoint;

        return this;
    }


    /**
     * Get the URL of the deferred credential endpoint. This property corresponds
     * to the {@code deferred_credential_endpoint} metadata.
     *
     * <p>
     * If the credential issuer does not support the deferred credential endpoint,
     * this property should be omitted.
     * </p>
     *
     * @return
     *         The URL of the deferred credential endpoint.
     *
     * @since 3.59
     */
    public URI getDeferredCredentialEndpoint()
    {
        return deferredCredentialEndpoint;
    }


    /**
     * Set the URL of the deferred credential endpoint. This property corresponds
     * to the {@code deferred_credential_endpoint} metadata.
     *
     * <p>
     * If the credential issuer does not support the deferred credential endpoint,
     * this property should be omitted.
     * </p>
     *
     * @param endpoint
     *         The URL of the deferred credential endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.59
     */
    public CredentialIssuerMetadata setDeferredCredentialEndpoint(URI endpoint)
    {
        this.deferredCredentialEndpoint = endpoint;

        return this;
    }


    /**
     * Get the supported JWE {@code alg} algorithms for credential response
     * encryption. This property corresponds to the
     * {@code credential_response_encryption.alg_values_supported} metadata.
     *
     * @return
     *         The supported JWE {@code alg} algorithms for credential response
     *         encryption.
     *
     * @since 3.86
     */
    public JWEAlg[] getCredentialResponseEncryptionAlgValuesSupported()
    {
        return credentialResponseEncryptionAlgValuesSupported;
    }


    /**
     * Set the supported JWE {@code alg} algorithms for credential response
     * encryption. This property corresponds to the
     * {@code credential_response_encryption.alg_values_supported} metadata.
     *
     * @param algs
     *         The supported JWE {@code alg} algorithms for credential response
     *         encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.86
     */
    public CredentialIssuerMetadata setCredentialResponseEncryptionAlgValuesSupported(JWEAlg[] algs)
    {
        this.credentialResponseEncryptionAlgValuesSupported = algs;

        return this;
    }


    /**
     * Get the supported JWE {@code enc} algorithms for credential response
     * encryption. This property corresponds to the
     * {@code credential_response_encryption.enc_values_supported} metadata.
     *
     * @return
     *         The supported JWE {@code enc} algorithms for credential response
     *         encryption.
     *
     * @since 3.86
     */
    public JWEEnc[] getCredentialResponseEncryptionEncValuesSupported()
    {
        return credentialResponseEncryptionEncValuesSupported;
    }


    /**
     * Set the supported JWE {@code enc} algorithms for credential response
     * encryption. This property corresponds to the
     * {@code credential_response_encryption.enc_values_supported} metadata.
     *
     *
     * @param encs
     *         The supported JWE {@code enc} algorithms for credential response
     *         encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.86
     */
    public CredentialIssuerMetadata setCredentialResponseEncryptionEncValuesSupported(JWEEnc[] encs)
    {
        this.credentialResponseEncryptionEncValuesSupported = encs;

        return this;
    }


    /**
     * Get the boolean flag indicating whether credential response encryption
     * is required. This property corresponds to the
     * {@code credential_response_encryption.encryption_required} metadata.
     *
     * <p>
     * If this flag is {@code true}, every credential request to the credential
     * issuer must include the {@code credential_response_encryption} property.
     * </p>
     *
     * @return
     *         {@code true} if credential response encryption is required.
     *
     * @since 3.86
     */
    public boolean isRequireCredentialResponseEncryption()
    {
        return requireCredentialResponseEncryption;
    }


    /**
     * Set the boolean flag indicating whether credential response encryption
     * is required. This property corresponds to the
     * {@code credential_response_encryption.encryption_required} metadata.
     *
     * <p>
     * If this flag is {@code true}, every credential request to the credential
     * issuer must include the {@code credential_response_encryption} property.
     * </p>
     *
     * @param required
     *         {@code true} to require credential response encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.86
     */
    public CredentialIssuerMetadata setRequireCredentialResponseEncryption(boolean required)
    {
        this.requireCredentialResponseEncryption = required;

        return this;
    }


    /**
     * Get the information about supported credential configurations in the
     * JSON format. This property corresponds to the
     * {@code credential_configurations_supported} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * <p>
     * NOTE: Due to the breaking change of the "OpenID for Verifiable
     * Credential Issuance" specification, the type of the content of this
     * "{@code credentialsSupported}" property has been changed from a JSON
     * array to a JSON object.
     * </p>
     *
     * <p>
     * NOTE: Due to another breaking change made in December 2023, the
     * {@code credentials_supported} metadata has been renamed to
     * {@code credential_configurations_supported}.
     * </p>
     *
     * @return
     *         The supported credential configurations. If not null, the value
     *         is a string representing a JSON object.
     */
    public String getCredentialsSupported()
    {
        return credentialsSupported;
    }


    /**
     * Set the information about supported credential configurations in the
     * JSON format. This property corresponds to the
     * {@code credential_configurations_supported} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * <p>
     * NOTE: Due to the breaking change of the "OpenID for Verifiable
     * Credential Issuance" specification, the type of the content of this
     * "{@code credentialsSupported}" property has been changed from a JSON
     * array to a JSON object.
     * </p>
     *
     * <p>
     * NOTE: Due to another breaking change made in December 2023, the
     * {@code credentials_supported} metadata has been renamed to
     * {@code credential_configurations_supported}.
     * </p>
     *
     * @param credentialsSupported
     *         The supported credential configurations. If not null, the value
     *         is a string representing a JSON object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuerMetadata setCredentialsSupported(String credentialsSupported)
    {
        this.credentialsSupported = credentialsSupported;

        return this;
    }


    /**
     * Check if all properties of this instance are null.
     *
     * @return
     *         {@code true} if all properties are null.
     */
    public boolean isEmpty()
    {
        return (credentialIssuer                               == null) &&
               (authorizationServers                           == null) &&
               (credentialEndpoint                             == null) &&
               (batchCredentialEndpoint                        == null) &&
               (deferredCredentialEndpoint                     == null) &&
               (credentialResponseEncryptionAlgValuesSupported == null) &&
               (credentialResponseEncryptionEncValuesSupported == null) &&
               (credentialsSupported                           == null);
    }


    /**
     * Create a {@code Map} instance that represents a JSON object conforming
     * to the format of the credential issuer metadata defined in "OpenID for
     * Verifiable Credential Issuance".
     *
     * <p>
     * The following is an example of {@code Map} content.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer":
     *     "https://credential-issuer.example.com",
     *   "authorization_servers": [
     *     "https://authorization-server.example.com"
     *   ],
     *   "credential_endpoint":
     *     "https://credential-issuer.example.com/credential",
     *   "batch_credential_endpoint":
     *     "https://credential-issuer.example.com/batch_credential",
     *   "deferred_credential_endpoint":
     *     "https://credential-issuer.example.com/deferred_credential",
     *   "credential_configurations_supported": {
     *     "UniversityDegreeCredential": {
     *       "format": "jwt_vc_json",
     *       "scope": "UniversityDegree",
     *       "cryptographic_binding_methods_supported": [
     *         "did:example"
     *       ],
     *       "cryptographic_suites_supported": [
     *         "ES256K"
     *       ],
     *       "credential_definition": {
     *         "type": [
     *           "VerifiableCredential",
     *           "UniversityDegreeCredential"
     *         ],
     *         "credentialSubject": {
     *           "given_name": {},
     *           "family_name": {},
     *           "degree": {},
     *           "gpa": {}
     *         }
     *       }
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * <p>
     * NOTE: Due to the breaking change of the "OpenID for Verifiable
     * Credential Issuance" specification, the type of the
     * "{@code credentials_supported}" property has been changed from
     * a JSON array to a JSON object.
     * </p>
     *
     * <p>
     * NOTE: Due to another breaking change made in December 2023, the
     * {@code credentials_supported} metadata has been renamed to
     * {@code credential_configurations_supported}.
     * </p>
     *
     * @return
     *         A {@code Map} instance that represents a JSON object conforming
     *         to the format of the credential issuer metadata.
     *
     * @throws IllegalStateException
     *         The value of the {@code credentialsSupported} property failed
     *         to be parsed as a JSON object.
     */
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = new LinkedHashMap<>();

        put(map, "credential_issuer",              credentialIssuer,               false);
        put(map, "authorization_servers",          authorizationServers,           false);
        put(map, "credential_endpoint",            credentialEndpoint,             false);
        put(map, "batch_credential_endpoint",      batchCredentialEndpoint,        false);
        put(map, "deferred_credential_endpoint",   deferredCredentialEndpoint,     false);
        put(map, "credential_response_encryption", credentialResponseEncryption(), false);

        try
        {
            // Parse the value of 'credentialsSupported' as a JSON object.
            putJsonObject(map, "credential_configurations_supported", credentialsSupported, false);
        }
        catch (Exception cause)
        {
            throw new IllegalStateException(
                    "The value of the 'credentialsSupported' property failed to be parsed as a JSON object.", cause);
        }

        return map;
    }


    private Map<String, Object> credentialResponseEncryption()
    {
        if (credentialResponseEncryptionAlgValuesSupported        == null ||
            credentialResponseEncryptionAlgValuesSupported.length == 0    ||
            credentialResponseEncryptionEncValuesSupported        == null ||
            credentialResponseEncryptionEncValuesSupported.length == 0)
        {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();

        put(map, "alg_values_supported", credentialResponseEncryptionAlgValuesSupported, true);
        put(map, "enc_values_supported", credentialResponseEncryptionEncValuesSupported, true);
        put(map, "encryption_required",  requireCredentialResponseEncryption,            true);

        return map;
    }
}
