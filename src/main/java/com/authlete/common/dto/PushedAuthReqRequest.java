/*
 * Copyright (C) 2019-2023 Authlete, Inc.
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


/**
 * Request to Authlete's {@code /api/pushed_auth_req} API.
 *
 * <p>
 * The authorization server can implement a pushed authorization request
 * endpoint which is defined in "OAuth 2.0 Pushed Authorization Requests"
 * by using the Authlete API.
 * </p>
 *
 * <p>
 * Request parameters to the API are as follows.
 * </p>
 *
 * <blockquote>
 * <dl>
 *
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * Request parameters that the pushed authorization request endpoint of the
 * authorization server implementation received from the client application.
 * Its format is {@code application/x-www-form-urlencoded}.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientId</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client ID extracted from the {@code Authorization} header of the request
 * to the pushed authorization request endpoint.
 * </p>
 * <p>
 * If the pushed authorization request endpoint of the authorization server
 * implementation supports Basic Authentication as a means of client
 * authentication, and the request from the client application contained its
 * client ID in the {@code Authorization} header, the value should be extracted
 * and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientSecret</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client secret extracted from the {@code Authorization} header of the
 * request to the pushed authorization request endpoint.
 * </p>
 * <p>
 * If the pushed authorization request endpoint of the authorization server
 * implementation supports Basic Authentication as a means of client
 * authentication, and the request from the client application contained its
 * client secret in the {@code Authorization} header, the value should be
 * extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate used in the TLS connection between the client
 * application and the pushed authorization request endpoint of the
 * authorization server.
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
 * <dt><b><code>dpop</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code DPoP} HTTP header.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htm</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The HTTP method of the PAR request. In normal cases, the value is
 * {@code "POST"}. When this parameter is omitted, {@code "POST"} is used
 * as the default value.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the PAR endpoint, without query or path components. If omitted,
 * the {@code pushedAuthReqEndpoint} property of {@link Service} is used as
 * the default value.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpopNonceRequired</code></b> (OPTIONAL; Authlete 3.0 onwards)</dt>
 * <dd>
 * <p>
 * The flag indicating whether to require the DPoP proof JWT to include
 * the {@code nonce} claim. Even if the service's {@code dpopNonceRequired}
 * property is false, calling the {@code /pushed_auth_req} API with this
 * {@code dpopNonceRequired} parameter true will force the Authlete API to
 * check whether the DPoP proof JWT includes the expected nonce value.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @since 2.51
 */
public class PushedAuthReqRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * Request parameters of the request to the pushed authorization request
     * endpoint.
     */
    private String parameters;


    /**
     * Client ID extracted from the Authorization header.
     */
    private String clientId;


    /**
     * Client secret extracted from the Authorization header.
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
     * DPoP Header
     */
    private String dpop;


    /**
     * HTTP Method (for DPoP validation).
     */
    private String htm;


    /**
     * HTTP URL base (for DPoP validation).
     */
    private String htu;


    /**
     * Whether to check if the DPoP proof JWT includes the expected nonce value.
     *
     * @since 3.82
     * @since Authlete 3.0
     */
    private boolean dpopNonceRequired;


    /**
     * Get the request parameters that the pushed authorization request
     * endpoint received from the client application.
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
     * Set the request parameters that the pushed authorization request
     * endpoint received from the client application.
     *
     * @param parameters
     *         Request parameters in {@code application/x-www-form-urlencoded}
     *         format.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Get the client ID extracted from the {@code Authorization} header of the
     * request to the pushed authorization request endpoint.
     *
     * @return
     *         The client ID.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID extracted from the {@code Authorization} header of the
     * request to the pushed authorization request endpoint.
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client secret extracted from the {@code Authorization} header of
     * the request to the pushed authorization request endpoint.
     *
     * @return
     *         The client secret.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret extracted from the {@code Authorization} header of
     * the request to the pushed authorization request endpoint.
     *
     * @param clientSecret
     *         The client secret.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqRequest setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }


    /**
     * Get the client certificate used in the TLS connection between the client
     * application and the pushed authorization request endpoint.
     *
     * @return
     *         The client certificate.
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the TLS connection between the client
     * application and the pushed authorization request endpoint.
     *
     * @param certificate
     *         The client certificate.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqRequest setClientCertificate(String certificate)
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
    public PushedAuthReqRequest setClientCertificatePath(String[] path)
    {
        this.clientCertificatePath = path;

        return this;
    }


    /**
     * Get the {@code DPoP} header presented by the client during the request
     * to the PAR endpoint. The header contains a signed JWT which includes
     * the public key that is paired with the private key used to sign the JWT.
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The {@code DPoP} header string.
     *
     * @since 3.47
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getDpop()
    {
        return dpop;
    }


    /**
     * Set the {@code DPoP} header presented by the client during the request
     * to the PAR endpoint. The header contains a signed JWT which includes
     * the public key that is paired with the private key used to sign the JWT.
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param dpop
     *            The {@code DPoP} header string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.47
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public PushedAuthReqRequest setDpop(String dpop)
    {
        this.dpop = dpop;

        return this;
    }


    /**
     * Get the HTTP method of the pushed authorization request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is {@code "POST"}. When this parameter
     * is omitted, {@code "POST"} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The HTTP method as a string. For example, {@code "POST"}.
     *
     * @since 3.47
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtm()
    {
        return htm;
    }


    /**
     * Set the HTTP method of the pushed authorization request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is {@code "POST"}. When this parameter
     * is omitted, {@code "POST"} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param htm
     *            The HTTP method as a string. For example, {@code "POST"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.47
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public PushedAuthReqRequest setHtm(String htm)
    {
        this.htm = htm;

        return this;
    }


    /**
     * Get the URL of the PAR endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code pushedAuthReqEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The URL of the PAR endpoint.
     *
     * @since 3.47
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtu()
    {
        return htu;
    }


    /**
     * Set the URL of the PAR endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code pushedAuthReqEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param htu
     *            The URL of the PAR endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.47
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public PushedAuthReqRequest setHtu(String htu)
    {
        this.htu = htu;

        return this;
    }


    /**
     * Get the flag indicating whether to check if the DPoP proof JWT includes
     * the expected {@code nonce} value.
     *
     * <p>
     * If this request parameter is {@code true} or if the service's
     * {@code dpopNonceRequired} property ({@link Service#isDpopNonceRequired()})
     * is {@code true}, the {@code /pushed_auth_req} API checks if the DPoP
     * proof JWT includes the expected {@code nonce} value. In this case, the
     * response from the {@code /pushed_auth_req} API will include the
     * {@code dpopNonce} response parameter, which should be used as the value
     * of the {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @return
     *         {@code true} if the {@code /pushed_auth_req} API checks
     *         whether the DPoP proof JWT includes the expected {@code nonce}
     *         value, even if the service's {@code dpopNonceRequired} property
     *         is false.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public boolean isDpopNonceRequired()
    {
        return dpopNonceRequired;
    }


    /**
     * Set the flag indicating whether to check if the DPoP proof JWT includes
     * the expected {@code nonce} value.
     *
     * <p>
     * If this request parameter is {@code true} or if the service's
     * {@code dpopNonceRequired} property ({@link Service#isDpopNonceRequired()})
     * is {@code true}, the {@code /pushed_auth_req} API checks if the DPoP
     * proof JWT includes the expected {@code nonce} value. In this case, the
     * response from the {@code /pushed_auth_req} API will include the
     * {@code dpopNonce} response parameter, which should be used as the value
     * of the {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @param required
     *         {@code true} to have the {@code /pushed_auth_req} API check
     *         whether the DPoP proof JWT includes the expected {@code nonce} value,
     *         even if the service's {@code dpopNonceRequired} property is false.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public PushedAuthReqRequest setDpopNonceRequired(boolean required)
    {
        this.dpopNonceRequired = required;

        return this;
    }
}
