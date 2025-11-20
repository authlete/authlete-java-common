/*
 * Copyright (C) 2019-2025 Authlete, Inc.
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
import java.util.Map;
import com.authlete.common.web.URLCoder;


/**
 * Request to Authlete's {@code /api/device/authorization} API.
 *
 * <p>
 * When the implementation of the device authorization endpoint of the
 * authorization server receives a device authorization request from a
 * client application, the first step is to call Authlete's {@code
 * /api/device/authorization} API. The API will parse the device
 * authorization request on behalf of the implementation of the device
 * authorization endpoint.
 * </p>
 *
 * <blockquote>
 * <dl>
 *
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * Parameters of a device authorization request which are the request
 * parameters that the device authorization endpoint of the authorization
 * server implementation received from the client application.
 * </p>
 * <p>
 * The value of {@code "parameters"} is the entire entity body (which
 * is formatted in {@code application/x-www-form-urlencoded}) of the
 * request from the client application.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientId</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client ID extracted from {@code Authorization} header of the
 * device authorization request from the client application.
 * </p>
 * <p>
 * If the device authorization endpoint of the authorization server
 * implementation supports Basic Authentication as a means of client
 * authentication, and the request from the client application contained
 * its client ID in {@code Authorization} header, the value should be
 * extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientSecret</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client secret extracted from {@code Authorization} header of the
 * device authorization request from the client application.
 * </p>
 * <p>
 * If the device authorization endpoint of the authorization server
 * implementation supports Basic Authentication as a means of client
 * authentication, and the request from the client application contained
 * its client secret in {@code Authorization} header, the value should be
 * extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate used in the TLS connection between the client
 * application and the device authorization endpoint of the authorization
 * server.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificatePath</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate path presented by the client during client
 * authentication. Each element is a string in PEM format.
 * </p>
 * </dd>
 *
 * <dt><b><code>oauthClientAttestation</code></b> (OPTIONAL; Authlete 3.0 onwards)</dt>
 * <dd>
 * <p>
 * The value of the {@code OAuth-Client-Attestation} HTTP header, which is
 * defined in the specification of <a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
 * >OAuth 2.0 Attestation-Based Client Authentication</a>.
 * </p>
 * </dd>
 *
 * <dt><b><code>oauthClientAttestationPop</code></b> (OPTIONAL; Authlete 3.0 onwards)</dt>
 * <dd>
 * <p>
 * The value of the {@code OAuth-Client-Attestation-PoP} HTTP header, which is
 * defined in the specification of <a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
 * >OAuth 2.0 Attestation-Based Client Authentication</a>.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @since 2.42
 */
public class DeviceAuthorizationRequest implements Serializable
{
    private static final long serialVersionUID = 3L;


    /**
     * Request parameters of a device authorization request.
     */
    private String parameters;


    /**
     * Client ID extracted from Authorization header.
     */
    private String clientId;


    /**
     * Client secret extracted from Authorization header.
     */
    private String clientSecret;


    /**
     * Client certificate.
     */
    private String clientCertificate;


    /**
     * Client certificate path.
     */
    private String[] clientCertificatePath;


    /**
     * The value of the {@code OAuth-Client-Attestation} HTTP header.
     *
     * @since 4.3
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
     *      >OAuth 2.0 Attestation-Based Client Authentication</a>
     */
    private String oauthClientAttestation;


    /**
     * The value of the {@code OAuth-Client-Attestation-PoP} HTTP header.
     *
     * @since 4.3
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
     *      >OAuth 2.0 Attestation-Based Client Authentication</a>
     */
    private String oauthClientAttestationPop;


    /**
     * Options for CIMD processing.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     */
    private CimdOptions cimdOptions;


    /**
     * Get the value of {@code parameters} which are the request parameters
     * that the device authorization endpoint of the authorization server
     * implementation received from the client application.
     *
     * @return
     *         Request parameters in {@code application/x-www-form-urlencoded}
     *         format.
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code parameters} which are the request parameters
     * that the device authorization endpoint of the authorization server
     * implementation received from the client application.
     *
     * @param parameters
     *         Request parameters in {@code application/x-www-form-urlencoded}
     *         format.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Set the value of {@code parameters} which are the request parameters
     * that the device authorization endpoint of the authorization server
     * implementation received from the client application.
     *
     * <p>
     * This method converts the given map into a string in
     * {@code application/x-www-form-urlencoded} and passes it to
     * {@link #setParameters(String)} method.
     * </p>
     *
     * @param parameters
     *         Request parameters.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationRequest setParameters(Map<String, String[]> parameters)
    {
        return setParameters(URLCoder.formUrlEncode(parameters));
    }


    /**
     * Get the client ID extracted from {@code Authorization} header of the
     * device authorization request from the client application.
     *
     * @return
     *         The client ID.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID extracted from {@code Authorization} header of the
     * device authorization request from the client application.
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client secret extracted from {@code Authorization} header of
     * the device authorization request from the client application.
     *
     * @return
     *         The client secret.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret extracted from {@code Authorization} header of
     * the device authorization request from the client application.
     *
     * @param clientSecret
     *         The client secret.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationRequest setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }


    /**
     * Get the client certificate used in the TLS connection between the
     * client application and the device authorization endpoint of the
     * authorization server.
     *
     * @return
     *         The client certificate.
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the TLS connection between the
     * client application and the device authorization endpoint of the
     * authorization server.
     *
     * @param certificate
     *         The client certificate
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationRequest setClientCertificate(String certificate)
    {
        this.clientCertificate = certificate;

        return this;
    }


    /**
     * Get the client certificate path presented by the client during client
     * authentication.
     *
     * @return
     *          The client certificate path. Each element is a string in PEM
     *          format.
     */
    public String[] getClientCertificatePath()
    {
        return clientCertificatePath;
    }


    /**
     * Set the client certificate path presented by the client during client
     * authentication.
     *
     * @param path
     *         The client certificate path.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationRequest setClientCertificatePath(String[] path)
    {
        this.clientCertificatePath = path;

        return this;
    }


    /**
     * Get the value of the {@code OAuth-Client-Attestation} HTTP header.
     *
     * @return
     *         The value of the {@code OAuth-Client-Attestation} HTTP header.
     *
     * @since 4.3
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
     *      >OAuth 2.0 Attestation-Based Client Authentication</a>
     */
    public String getOauthClientAttestation()
    {
        return oauthClientAttestation;
    }


    /**
     * Set the value of the {@code OAuth-Client-Attestation} HTTP header.
     *
     * @param jwt
     *         The value of the {@code OAuth-Client-Attestation} HTTP header.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.3
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
     *      >OAuth 2.0 Attestation-Based Client Authentication</a>
     */
    public DeviceAuthorizationRequest setOauthClientAttestation(String jwt)
    {
        this.oauthClientAttestation = jwt;

        return this;
    }


    /**
     * Get the value of the {@code OAuth-Client-Attestation-PoP} HTTP header.
     *
     * @return
     *         The value of the {@code OAuth-Client-Attestation-PoP} HTTP header.
     *
     * @since 4.3
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
     *      >OAuth 2.0 Attestation-Based Client Authentication</a>
     */
    public String getOauthClientAttestationPop()
    {
        return oauthClientAttestationPop;
    }


    /**
     * Set the value of the {@code OAuth-Client-Attestation-PoP} HTTP header.
     *
     * @param jwt
     *         The value of the {@code OAuth-Client-Attestation-PoP} HTTP header.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.3
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
     *      >OAuth 2.0 Attestation-Based Client Authentication</a>
     */
    public DeviceAuthorizationRequest setOauthClientAttestationPop(String jwt)
    {
        this.oauthClientAttestationPop = jwt;

        return this;
    }


    /**
     * Get options for <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> processing.
     *
     * @return
     *         Options for CIMD processing.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public CimdOptions getCimdOptions()
    {
        return cimdOptions;
    }


    /**
     * Set options for <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> processing.
     *
     * @param options
     *         Options for CIMD processing.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public DeviceAuthorizationRequest setCimdOptions(CimdOptions options)
    {
        this.cimdOptions = options;

        return this;
    }
}
