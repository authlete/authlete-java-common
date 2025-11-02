/*
 * Copyright (C) 2023-2025 Authlete, Inc.
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
import static com.authlete.common.util.MapUtils.putJsonArray;
import java.io.Serializable;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import com.authlete.common.types.JWEAlg;
import com.authlete.common.types.JWEEnc;
import com.authlete.common.types.JWEZip;
import com.nimbusds.jose.jwk.JWKSet;


/**
 * A class that represents the set of credential issuer metadata.
 * The set consists of the following:
 *
 * <blockquote>
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *   <tr bgcolor="orange">
 *     <th rowspan="2">Parameter</th>
 *     <th colspan="2">Spec Version</th>
 *   </tr>
 *   <tr>
 *     <td align="center">1.0-ID1</td>
 *     <td align="center">1.0-Final</td>
 *   </tr>
 *   <tr>
 *     <td>{@code credential_issuer}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code authorization_servers}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code credential_endpoint}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code batch_credential_endpoint}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">deprecated</td>
 *   </tr>
 *   <tr>
 *     <td>{@code deferred_credential_endpoint}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code notification_endpoint}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code nonce_endpoint}</td>
 *     <td align="center"></td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code credential_request_encryption}</td>
 *     <td align="center"></td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code credential_response_encryption}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code batch_credential_issuance}</td>
 *     <td align="center"></td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code display}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 *   <tr>
 *     <td>{@code credential_configurations_supported}</td>
 *     <td align="center">&check;</td>
 *     <td align="center">&check;</td>
 *   </tr>
 * </table>
 * </blockquote>
 *
 * <p>
 * The following parameters, which existed in the 1.0-ID1 version but have
 * removed from the 1.0-Final version, are not supported by Authlete
 * from the beginning:
 * </p>
 *
 * <ul>
 * <li>{@code credential_identifiers_supported}</li>
 * <li>{@code signed_metadata}</li>
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
 *      >OpenID for Verifiable Credential Issuance 1.0</a>
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0-ID1.html"
 *      >OpenID for Verifiable Credential Issuance 1.0, Implementer's Draft 1</a>
 */
public class CredentialIssuerMetadata implements Serializable
{
    private static final long serialVersionUID = 5L;


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
     * The URL of the notification endpoint.
     *
     * @since 4.26
     */
    private URI notificationEndpoint;


    /**
     * The URL of the nonce endpoint.
     *
     * @since 4.26
     */
    private URI nonceEndpoint;


    /**
     * The supported JWE alg algorithms for credential response encryption.
     *
     * @since 3.86
     */
    private JWEAlg[] credentialResponseEncryptionAlgValuesSupported;


    /**
     * A JSON Web Key Set containing private keys for credential request
     * encryption.
     *
     * @since 4.26
     */
    private String credentialRequestEncryptionJwks;


    /**
     * The supported JWE enc algorithms for credential request encryption.
     *
     * @since 4.26
     */
    private JWEEnc[] credentialRequestEncryptionEncValuesSupported;


    /**
     * The supported JWE zip algorithms for credential request encryption.
     *
     * @since 4.26
     */
    private JWEZip[] credentialRequestEncryptionZipValuesSupported;


    /**
     * The boolean flag indicating whether credential request encryption is
     * required.
     *
     * @since 4.26
     */
    private boolean requireCredentialRequestEncryption;


    /**
     * The supported JWE enc algorithms for credential response encryption.
     *
     * @since 3.86
     */
    private JWEEnc[] credentialResponseEncryptionEncValuesSupported;


    /**
     * The supported JWE zip algorithms for credential response encryption.
     *
     * @since 4.26
     */
    private JWEZip[] credentialResponseEncryptionZipValuesSupported;


    /**
     * The boolean flag indicating whether credential response encryption is
     * required.
     *
     * @since 3.86
     */
    private boolean requireCredentialResponseEncryption;


    /**
     * The maximum array size for the {@code proofs} parameter in a credential
     * request.
     *
     * @since 4.26
     */
    private int batchSize;


    /**
     * The credential issuer's display properties in JSON array format.
     *
     * @since 4.26
     */
    private String display;


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
        notificationEndpoint                           = metadata.getNotificationEndpoint();
        nonceEndpoint                                  = metadata.getNonceEndpoint();
        credentialRequestEncryptionJwks                = metadata.getCredentialRequestEncryptionJwks();
        credentialRequestEncryptionEncValuesSupported  = metadata.getCredentialRequestEncryptionEncValuesSupported();
        credentialRequestEncryptionZipValuesSupported  = metadata.getCredentialRequestEncryptionZipValuesSupported();
        requireCredentialRequestEncryption             = metadata.isRequireCredentialRequestEncryption();
        credentialResponseEncryptionAlgValuesSupported = metadata.getCredentialResponseEncryptionAlgValuesSupported();
        credentialResponseEncryptionEncValuesSupported = metadata.getCredentialResponseEncryptionEncValuesSupported();
        credentialResponseEncryptionZipValuesSupported = metadata.getCredentialResponseEncryptionZipValuesSupported();
        requireCredentialResponseEncryption            = metadata.isRequireCredentialResponseEncryption();
        batchSize                                      = metadata.getBatchSize();
        display                                        = metadata.getDisplay();
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
     * <p>
     * NOTE: This metadata parameter was deprecated and is not available in the
     * 1.0-Final version of the OID4VCI specification.
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
     * <p>
     * NOTE: This metadata parameter was deprecated and is not available in the
     * 1.0-Final version of the OID4VCI specification.
     * </p>
     *
     * @param endpoint
     *         The URL of the batch credential endpoint.
     *
     * @return
     *         {@code this} object.
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
     * Get the URL of the notification endpoint. This property corresponds to
     * the {@code notification_endpoint} metadata.
     *
     * @return
     *         The URL of the notification endpoint.
     *
     * @since 4.26
     */
    public URI getNotificationEndpoint()
    {
        return notificationEndpoint;
    }


    /**
     * Set the URL of the notification endpoint. This property corresponds to
     * the {@code notification_endpoint} metadata.
     *
     * @param endpoint
     *         The URL of the notification endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setNotificationEndpoint(URI endpoint)
    {
        this.notificationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the URL of the nonce endpoint. This property corresponds to the
     * {@code nonce_endpoint} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @return
     *         The URL of the nonce endpoint.
     *
     * @since 4.26
     */
    public URI getNonceEndpoint()
    {
        return nonceEndpoint;
    }


    /**
     * Set the URL of the nonce endpoint. This property corresponds to the
     * {@code nonce_endpoint} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @param endpoint
     *         The URL of the nonce endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setNonceEndpoint(URI endpoint)
    {
        this.nonceEndpoint = endpoint;

        return this;
    }


    /**
     * Get the JWK Set for credential request encryption.
     *
     * <p>
     * The public part of this JWK Set is used as the value of the
     * {@code credential_request_encryption.jwks} metadata.
     * </p>
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @return
     *         The JWK Set for credential request encryption.
     *
     * @since 4.26
     */
    public String getCredentialRequestEncryptionJwks()
    {
        return credentialRequestEncryptionJwks;
    }


    /**
     * Set the JWK Set for credential request encryption.
     *
     * <p>
     * The public part of this JWK Set is used as the value of the
     * {@code credential_request_encryption.jwks} metadata.
     * </p>
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @param jwks
     *         The JWK Set for credential request encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setCredentialRequestEncryptionJwks(String jwks)
    {
        this.credentialRequestEncryptionJwks = jwks;

        return this;
    }


    /**
     * Get the supported JWE {@code enc} algorithms for credential request
     * encryption. This property corresponds to the
     * {@code credential_request_encryption.enc_values_supported} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @return
     *         The supported JWE {@code enc} algorithms for credential request
     *         encryption.
     *
     * @since 4.26
     */
    public JWEEnc[] getCredentialRequestEncryptionEncValuesSupported()
    {
        return credentialRequestEncryptionEncValuesSupported;
    }


    /**
     * Set the supported JWE {@code enc} algorithms for credential request
     * encryption. This property corresponds to the
     * {@code credential_request_encryption.enc_values_supported} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @param encs
     *         The supported JWE {@code enc} algorithms for credential request
     *         encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setCredentialRequestEncryptionEncValuesSupported(JWEEnc[] encs)
    {
        this.credentialRequestEncryptionEncValuesSupported = encs;

        return this;
    }


    /**
     * Get the supported JWE {@code zip} algorithms for credential request
     * encryption. This property corresponds to the
     * {@code credential_request_encryption.zip_values_supported} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @return
     *         The supported JWE {@code zip} algorithms for credential request
     *         encryption.
     *
     * @since 4.26
     */
    public JWEZip[] getCredentialRequestEncryptionZipValuesSupported()
    {
        return credentialRequestEncryptionZipValuesSupported;
    }


    /**
     * Set the supported JWE {@code zip} algorithms for credential request
     * encryption. This property corresponds to the
     * {@code credential_request_encryption.zip_values_supported} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @param zips
     *         The supported JWE {@code zip} algorithms for credential request
     *         encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setCredentialRequestEncryptionZipValuesSupported(JWEZip[] zips)
    {
        this.credentialRequestEncryptionZipValuesSupported = zips;

        return this;
    }


    /**
     * Get the boolean flag indicating whether credential request encryption
     * is required. This property corresponds to the
     * {@code credential_request_encryption.encryption_required} metadata.
     *
     * <p>
     * If this flag is {@code true}, every credential request to the credential
     * issuer must be encrypted.
     * </p>
     *
     * @return
     *         {@code true} if credential request encryption is required.
     *
     * @since 4.26
     */
    public boolean isRequireCredentialRequestEncryption()
    {
        return requireCredentialRequestEncryption;
    }


    /**
     * Set the boolean flag indicating whether credential request encryption
     * is required. This property corresponds to the
     * {@code credential_request_encryption.encryption_required} metadata.
     *
     * <p>
     * If this flag is {@code true}, every credential request to the credential
     * issuer must be encrypted
     * </p>
     *
     * @param required
     *         {@code true} to require credential request encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setRequireCredentialRequestEncryption(boolean required)
    {
        this.requireCredentialRequestEncryption = required;

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
     * Get the supported JWE {@code zip} algorithms for credential response
     * encryption. This property corresponds to the
     * {@code credential_response_encryption.zip_values_supported} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @return
     *         The supported JWE {@code zip} algorithms for credential response
     *         encryption.
     *
     * @since 4.26
     */
    public JWEZip[] getCredentialResponseEncryptionZipValuesSupported()
    {
        return credentialResponseEncryptionZipValuesSupported;
    }


    /**
     * Set the supported JWE {@code zip} algorithms for credential response
     * encryption. This property corresponds to the
     * {@code credential_response_encryption.zip_values_supported} metadata.
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @param zips
     *         The supported JWE {@code zip} algorithms for credential response
     *         encryption.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setCredentialResponseEncryptionZipValuesSupported(JWEZip[] zips)
    {
        this.credentialResponseEncryptionZipValuesSupported = zips;

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
     * Get the maximum array size for the {@code proofs} parameter in a
     * credential request. This property corresponds to the
     * {@code batch_credential_issuance.batch_size} metadata.
     *
     * <p>
     * If the value of this property is 2 or greater, the
     * {@code batch_credential_issuance} parameter will appear in the response
     * from the credential metadata endpoint.
     * </p>
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @return
     *         The maximum array size for the {@code proofs} parameter in a
     *         credential request.
     *
     * @since 4.26
     */
    public int getBatchSize()
    {
        return batchSize;
    }


    /**
     * Set the maximum array size for the {@code proofs} parameter in a
     * credential request. This property corresponds to the
     * {@code batch_credential_issuance.batch_size} metadata.
     *
     * <p>
     * If the value of this property is 2 or greater, the
     * {@code batch_credential_issuance} parameter will appear in the response
     * from the credential metadata endpoint.
     * </p>
     *
     * <p>
     * NOTE: This metadata parameter exists in the 1.0-Final version of the
     * OID4VCI specification but is not available in the 1.0-ID1 version.
     * </p>
     *
     * @param batchSize
     *         The maximum array size for the {@code proofs} parameter in a
     *         credential request.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setBatchSize(int batchSize)
    {
        this.batchSize = batchSize;

        return this;
    }


    /**
     * Get the display properties of this credential issuer in JSON array format.
     *
     * <p>
     * The following is an example of {@code display} value excerpted from <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html#appendix-I.1"
     * >Appendix I.1. Credential IssuerMetadata</a> of the OID4VCI specification.
     * </p>
     *
     * <pre style="width: stretch; margin-left: 1em; margin-right: 1em; border: 1px solid black;">
     * [
     *   {
     *     "name": "Example University",
     *     "locale": "en-US",
     *     "logo": {
     *       "uri": "https://university.example.edu/public/logo.png",
     *       "alt_text":"a square logo of a university"
     *     }
     *   },
     *   {
     *     "name": "Example Université",
     *     "locale": "fr-FR",
     *     "logo": {
     *       "uri": "https://university.example.edu/public/logo.png",
     *       "alt_text":"Un logo universitaire carré"
     *     }
     *   }
     * ]</pre>
     *
     * @return
     *         The display properties.
     *
     * @since 4.26
     */
    public String getDisplay()
    {
        return display;
    }


    /**
     * Set the display properties of this credential issuer in JSON array format.
     *
     * <p>
     * The following is an example of {@code display} value excerpted from <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html#appendix-I.1"
     * >Appendix I.1. Credential IssuerMetadata</a> of the OID4VCI specification.
     * </p>
     *
     * <pre style="width: stretch; margin-left: 1em; margin-right: 1em; border: 1px solid black;">
     * [
     *   {
     *     "name": "Example University",
     *     "locale": "en-US",
     *     "logo": {
     *       "uri": "https://university.example.edu/public/logo.png",
     *       "alt_text":"a square logo of a university"
     *     }
     *   },
     *   {
     *     "name": "Example Université",
     *     "locale": "fr-FR",
     *     "logo": {
     *       "uri": "https://university.example.edu/public/logo.png",
     *       "alt_text":"Un logo universitaire carré"
     *     }
     *   }
     * ]</pre>
     *
     * @param display
     *         The display properties.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.26
     */
    public CredentialIssuerMetadata setDisplay(String display)
    {
        this.display = display;

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
               (notificationEndpoint                           == null) &&
               (nonceEndpoint                                  == null) &&
               (credentialRequestEncryptionJwks                == null) &&
               (credentialRequestEncryptionEncValuesSupported  == null) &&
               (credentialRequestEncryptionZipValuesSupported  == null) &&
               (credentialResponseEncryptionAlgValuesSupported == null) &&
               (credentialResponseEncryptionEncValuesSupported == null) &&
               (credentialResponseEncryptionZipValuesSupported == null) &&
               (display                                        == null) &&
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
        put(map, "notification_endpoint",          notificationEndpoint,           false);
        put(map, "nonce_endpoint",                 nonceEndpoint,                  false);
        put(map, "credential_request_encryption",  credentialRequestEncryption(),  false);
        put(map, "credential_response_encryption", credentialResponseEncryption(), false);
        put(map, "batch_credential_issuance",      batchCredentialIssuance(),      false);
        putDisplay(map);                           // display
        putCredentialConfigurationsSupported(map); // credential_configurations_supported

        return map;
    }


    private Map<String, Object> credentialRequestEncryption()
    {
        if (credentialRequestEncryptionJwks                      == null ||
            credentialRequestEncryptionEncValuesSupported        == null ||
            credentialRequestEncryptionEncValuesSupported.length == 0)
        {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();

        put(map, "jwks",                 credentialRequestEncryptionPublicJwks(),       true);
        put(map, "enc_values_supported", credentialRequestEncryptionEncValuesSupported, true);
        put(map, "zip_values_supported", credentialRequestEncryptionZipValuesSupported, false);
        put(map, "encryption_required",  requireCredentialRequestEncryption,            true);

        return map;
    }


    private Map<String, Object> credentialRequestEncryptionPublicJwks()
    {
        if (credentialRequestEncryptionJwks == null)
        {
            return null;
        }

        JWKSet jwks;

        try
        {
            // Parse the string as a JWK Set.
            jwks = JWKSet.parse(credentialRequestEncryptionJwks);
        }
        catch (Exception cause)
        {
            throw new IllegalStateException(
                    "The value of the 'credentialRequestEncryptionJwks' property failed to be parsed as a JWK Set.", cause);
        }

        // Public keys only.
        return jwks.toJSONObject(true);
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
        put(map, "zip_values_supported", credentialResponseEncryptionZipValuesSupported, false);
        put(map, "encryption_required",  requireCredentialResponseEncryption,            true);

        return map;
    }


    private Map<String, Object> batchCredentialIssuance()
    {
        // OpenID for Verifiable Credential Issuance 1.0
        // 12.2.4. Credential Issuer Metadata Parameters
        //
        //   batch_credential_issuance:
        //
        //     OPTIONAL. Object containing information about the Credential
        //     Issuer's support for issuance of multiple Credentials in a batch
        //     in the Credential Endpoint. The presence of this parameter means
        //     that the issuer supports more than one key proof in the proofs
        //     parameter in the Credential Request so can issue more than one
        //     Verifiable Credential for the same Credential Dataset in a
        //     single request/response.
        //
        //       batch_size:
        //         REQUIRED. Integer value specifying the maximum array size
        //         for the proofs parameter in a Credential Request. It MUST
        //         be 2 or greater.
        //
        if (batchSize < 2)
        {
            return null;
        }

        // {
        //   "batch_size": batchSize
        // }
        Map<String, Object> value = new LinkedHashMap<>();
        value.put("batch_size", batchSize);

        return value;
    }


    private void putDisplay(Map<String, Object> map)
    {
        try
        {
            // Parse the value of 'display as a JSON array.
            putJsonArray(map, "display", display, false);
        }
        catch (Exception cause)
        {
            throw new IllegalStateException(
                    "The value of the 'display' property failed to be parsed as a JSON array.", cause);
        }
    }


    private void putCredentialConfigurationsSupported(Map<String, Object> map)
    {
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
    }
}
