/*
 * Copyright (C) 2021-2023 Authlete, Inc.
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
import com.authlete.common.types.GMAction;


/**
 * Request to Authlete's {@code /api/gm} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>gmAction</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The grant management action of the grant management request. Either
 * {@link GMAction#QUERY QUERY} or {@link GMAction#REVOKE REVOKE}.
 * </p>
 * </dd>
 *
 * <dl>
 * <dt><b><code>grantId</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The grant ID of the grant management request.
 * </p>
 * </dd>
 *
 * <dt><b><code>accessToken</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The access token included in the grant management request.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate used in the TLS connection established between the
 * client application and the grant management endpoint. See "<a href=
 * "https://www.rfc-editor.org/rfc/rfc8705.html">RFC 8705 OAuth 2.0 Mutual-TLS
 * Client Authentication and Certificate-Bound Access Tokens</a>" for details.
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
 * The HTTP method of the grant management request. Either {@code "GET"} or
 * {@code "DELETE"}. This parameter is used to validate the value of the
 * {@code DPoP} HTTP header.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * <p>
 * API callers don't have to specify this parameter unless they have a special
 * reason because the default value can be easily determined based on the value
 * of {@code gmAction}.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the grant management endpoint. This parameter is used to validate
 * the value of the {@code DPoP} HTTP header.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * <p>
 * When omitted, a string built by concatenating (1) the
 * {@code grantManagementEndpoint} property of the {@link Service}, (2) a slash
 * ({@code /}) and (3) the grant ID is used as the default value.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpopNonceRequired</code></b> (OPTIONAL; Authlete 3.0 onwards)</dt>
 * <dd>
 * <p>
 * The flag indicating whether to require the DPoP proof JWT to include
 * the {@code nonce} claim. Even if the service's {@code dpopNonceRequired}
 * property is false, calling the {@code /auth/gm} API with this
 * {@code dpopNonceRequired} parameter true will force the Authlete API to
 * check whether the DPoP proof JWT includes the expected nonce value.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @see <a href="https://openid.net/specs/fapi-grant-management.html"
 *      >Grant Management for OAuth 2.0</a>
 *
 * @since 3.1
 */
public class GMRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * A grant management action; either QUERY or REVOKE.
     */
    private GMAction gmAction;


    /**
     * The grant ID.
     */
    private String grantId;


    /**
     * The access token.
     */
    private String accessToken;


    /**
     * The client certificate used in the TLS connection established
     * between the client application and the grant management endpoint.
     */
    private String clientCertificate;


    /**
     * The {@code DPoP} HTTP header.
     */
    private String dpop;


    /**
     * The HTTP method of the grant management request.
     */
    private String htm;


    /**
     * The URL of the grant management endpoint.
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
     * Get the grant management action of the grant management request.
     * Either {@link GMAction#QUERY QUERY} or {@link GMAction#REVOKE REVOKE}.
     *
     * @return
     *         The grant management action of the grant management request.
     */
    public GMAction getGmAction()
    {
        return gmAction;
    }


    /**
     * Set the grant management action of the grant management request.
     * Either {@link GMAction#QUERY QUERY} or {@link GMAction#REVOKE REVOKE}.
     *
     * @param gmAction
     *         The grant management action of the grant management request.
     *
     * @return
     *         {@code this} object.
     */
    public GMRequest setGmAction(GMAction gmAction)
    {
        this.gmAction = gmAction;

        return this;
    }


    /**
     * Get the grant ID of the grant management request.
     *
     * @return
     *         The grant ID.
     */
    public String getGrantId()
    {
        return grantId;
    }


    /**
     * Set the grant ID of the grant management request.
     *
     * @param grantId
     *         The grant ID.
     *
     * @return
     *         {@code this} object.
     */
    public GMRequest setGrantId(String grantId)
    {
        this.grantId = grantId;

        return this;
    }


    /**
     * Get the access token which has come along with the grant management
     * request from the client application.
     *
     * @return
     *         The access token.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token that has come along with the grant management
     * request from the client application.
     *
     * @param accessToken
     *         The access token.
     *
     * @return
     *         {@code this} object.
     */
    public GMRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the client certificate used in the TLS connection established
     * between the client application and the grant management endpoint.
     *
     * <p>
     * This property is checked when the access token is bound to a client
     * certificate. See RFC 8705 for details.
     * </p>
     *
     * @return
     *         The client certificate in PEM format.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8705.html"
     *      >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the TLS connection established
     * between the client application and the grant management endpoint.
     *
     * <p>
     * This property is checked when the access token is bound to a client
     * certificate. See RFC 8705 for details.
     * </p>
     *
     * @param certificate
     *         The client certificate in PEM format.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8705.html"
     *      >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     */
    public GMRequest setClientCertificate(String certificate)
    {
        this.clientCertificate = certificate;

        return this;
    }


    /**
     * Get the value of the {@code DPoP} header of the grant management request.
     *
     * <p>
     * This property is checked when the access token is bound to a public key.
     * </p>
     *
     * @return
     *         The value of the {@code DPoP} header.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getDpop()
    {
        return dpop;
    }


    /**
     * Set the value of the {@code DPoP} header of the grant management request.
     *
     * <p>
     * This property is checked when the access token is bound to a public key.
     * </p>
     *
     * @param dpop
     *         The value of the {@code DPoP} header.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public GMRequest setDpop(String dpop)
    {
        this.dpop = dpop;

        return this;
    }


    /**
     * Get the HTTP method of the grant management request.
     *
     * <p>
     * This property is used to validate the value of the {@code DPoP} header.
     * </p>
     *
     * <p>
     * When this property is omitted, {@code "GET"} is used as the default value
     * in the case of {@code gmAction=}{@link GMAction#QUERY QUERY}. Likewise,
     * {@code "DELETE"} is used as the default value in the case of
     * {@code gmAction=}{@link GMAction#REVOKE REVOKE}.
     * </p>
     *
     * @return
     *         The HTTP method of the grant management request.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtm()
    {
        return htm;
    }


    /**
     * Set the HTTP method of the grant management request.
     *
     * <p>
     * This property is used to validate the value of the {@code DPoP} header.
     * </p>
     *
     * <p>
     * When this property is omitted, {@code "GET"} is used as the default value
     * in the case of {@code gmAction=}{@link GMAction#QUERY QUERY}. Likewise,
     * {@code "DELETE"} is used as the default value in the case of
     * {@code gmAction=}{@link GMAction#REVOKE REVOKE}.
     * </p>
     *
     * @param htm
     *         The HTTP method of the grant management request.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public GMRequest setHtm(String htm)
    {
        this.htm = htm;

        return this;
    }


    /**
     * Get the URL of the grant management endpoint.
     *
     * <p>
     * This property is used to validate the value of the {@code DPoP} header.
     * </p>
     *
     * <p>
     * When this property is omitted, a string built by concatenating (1) the
     * {@code grantManagementEndpoint} property of the {@link Service}, (2) a
     * slash ({@code /}) and (3) the grant ID is used as the default value.
     * </p>
     *
     * @return
     *         The URL of the grant management endpoint.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtu()
    {
        return htu;
    }


    /**
     * Set the URL of the grant management endpoint.
     *
     * <p>
     * This property is used to validate the value of the {@code DPoP} header.
     * </p>
     *
     * <p>
     * When this property is omitted, a string built by concatenating (1) the
     * {@code grantManagementEndpoint} property of the {@link Service}, (2) a
     * slash ({@code /}) and (3) the grant ID is used as the default value.
     * </p>
     *
     * @param htu
     *         The URL of the userinfo endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public GMRequest setHtu(String htu)
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
     * is {@code true}, the {@code /auth/gm} API checks if the DPoP proof JWT
     * includes the expected {@code nonce} value. In this case, the response
     * from the {@code /auth/gm} API will include the {@code dpopNonce} response
     * parameter, which should be used as the value of the {@code DPoP-Nonce}
     * HTTP header.
     * </p>
     *
     * @return
     *         {@code true} if the {@code /auth/gm} API checks whether the
     *         DPoP proof JWT includes the expected {@code nonce} value, even
     *         if the service's {@code dpopNonceRequired} property is false.
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
     * is {@code true}, the {@code /auth/gm} API checks if the DPoP proof JWT
     * includes the expected {@code nonce} value. In this case, the response
     * from the {@code /auth/gm} API will include the {@code dpopNonce} response
     * parameter, which should be used as the value of the {@code DPoP-Nonce}
     * HTTP header.
     * </p>
     *
     * @param required
     *         {@code true} to have the {@code /auth/gm} API check whether
     *         the DPoP proof JWT includes the expected {@code nonce} value,
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
    public GMRequest setDpopNonceRequired(boolean required)
    {
        this.dpopNonceRequired = required;

        return this;
    }
}
