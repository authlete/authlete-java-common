/*
 * Copyright (C) 2023 Authlete, Inc.
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


import java.io.Serializable;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import static com.authlete.common.util.MapUtils.*;


/**
 * A class that represents the set of credential issuer metadata.
 * The set consists of the following.
 *
 * <ul>
 * <li>{@code credential_issuer}
 * <li>{@code authorization_server}
 * <li>{@code credential_endpoint}
 * <li>{@code batch_credential_endpoint}
 * <li>{@code deferred_credential_endpoint}
 * <li>{@code credentials_supported}
 * </ul>
 *
 * <p>
 * A credential issuer announces these metadata at
 * {@code /.well-known/openid-credential-issuer}.
 * </p>
 *
 * @since 3.55
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialIssuerMetadata implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The identifier of the credential issuer.
     */
    private URI credentialIssuer;


    /**
     * The identifier of the authorization server that the credential issuer
     * relies on for authorization.
     */
    private URI authorizationServer;


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
     * A JSON array describing supported credentials.
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

        credentialIssuer           = metadata.getCredentialIssuer();
        authorizationServer        = metadata.getAuthorizationServer();
        credentialEndpoint         = metadata.getCredentialEndpoint();
        batchCredentialEndpoint    = metadata.getBatchCredentialEndpoint();
        deferredCredentialEndpoint = metadata.getDeferredCredentialEndpoint();
        credentialsSupported       = metadata.getCredentialsSupported();
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
     * Get the identifier of the authorization server that the credential
     * issuer relies on for authorization. This property corresponds to the
     * {@code authorization_server} metadata.
     *
     * <p>
     * When the credential issuer works as an authorization server for itself,
     * this property should be omitted.
     * </p>
     *
     * @return
     *         The identifier of the authorization server that the credential
     *         issuer relies on for authorization.
     */
    public URI getAuthorizationServer()
    {
        return authorizationServer;
    }


    /**
     * Set the identifier of the authorization server that the credential
     * issuer relies on for authorization. This property corresponds to the
     * {@code authorization_server} metadata.
     *
     * <p>
     * When the credential issuer works as an authorization server for itself,
     * this property should be omitted.
     * </p>
     *
     * @param server
     *         The identifier of the authorization server that the credential
     *         issuer relies on for authorization.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuerMetadata setAuthorizationServer(URI server)
    {
        this.authorizationServer = server;

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
     * Get the information about supported credentials in the JSON format.
     * This property corresponds to the {@code credentials_supported} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * @return
     *         The supported credentials. If not null, the value is a string
     *         representing a JSON array.
     */
    public String getCredentialsSupported()
    {
        return credentialsSupported;
    }


    /**
     * Set the information about supported credentials in the JSON format.
     * This property corresponds to the {@code credentials_supported} metadata.
     *
     * <p>
     * To make the feature of credential issuance function, this property must
     * be set.
     * </p>
     *
     * @param credentialsSupported
     *         The supported credentials. If not null, the value must be a
     *         string representing a JSON array.
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
        return (credentialIssuer           == null) &&
               (authorizationServer        == null) &&
               (credentialEndpoint         == null) &&
               (batchCredentialEndpoint    == null) &&
               (deferredCredentialEndpoint == null) &&
               (credentialsSupported       == null);
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
     *   "credential_issuer": "https://credential-issuer.example.com",
     *   "authorization_server": "https://authorization-server.example.com",
     *   "credential_endpoint": "https://credential-issuer.example.com/credential",
     *   "batch_credential_endpoint": "https://credential-issuer.example.com/batch_credential",
     *   "deferred_credential_endpoint": "https://credential-issuer.example.com/deferred_credential",
     *   "credentials_supported": [
     *     {
     *       "format": "jwt_vc_json",
     *       "id": "UniversityDegree_JWT",
     *       "type": [
     *         "VerifiableCredential",
     *         "UniversityDegreeCredential"
     *       ],
     *       "cryptographic_binding_methods_supported": [
     *         "did:example"
     *       ],
     *       "cryptographic_suites_supported": [
     *         "ES256K"
     *       ],
     *       "display": [
     *         {
     *           "name": "University Credential"
     *         }
     *       ],
     *       "credentialSubject": {
     *         "given_name": {},
     *         "last_name": {},
     *         "degree": {}
     *       }
     *     }
     *   ]
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         A {@code Map} instance that represents a JSON object conforming
     *         to the format of the credential issuer metadata.
     *
     * @throws IllegalStateException
     *         The value of the {@code credentialsSupported} property failed
     *         to be parsed as a JSON array.
     */
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = new LinkedHashMap<>();

        put(map, "credential_issuer",            credentialIssuer,           false);
        put(map, "authorization_server",         authorizationServer,        false);
        put(map, "credential_endpoint",          credentialEndpoint,         false);
        put(map, "batch_credential_endpoint",    batchCredentialEndpoint,    false);
        put(map, "deferred_credential_endpoint", deferredCredentialEndpoint, false);

        try
        {
            // Parse the value of 'credentialsSupported' as a JSON array.
            putJsonArray(map, "credentials_supported", credentialsSupported, false);
        }
        catch (Exception cause)
        {
            throw new IllegalStateException(
                    "The value of the 'credentialsSupported' property failed to be parsed as a JSON array.", cause);
        }

        return map;
    }
}
