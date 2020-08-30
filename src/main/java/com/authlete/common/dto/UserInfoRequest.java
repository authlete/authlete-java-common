/*
 * Copyright (C) 2015-2020 Authlete, Inc.
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
 * Request to Authlete's {@code /auth/userinfo} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>token</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * An access token to get user information.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate from the MTLS of the userinfo request from
 * the client application.
 * See <a href="https://tools.ietf.org/html/rfc8705">RFC 8705</a>
 * (OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access
 * Tokens) for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpop</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code DPoP} HTTP header.
 * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the Application
 * Layer (DPoP)"</i> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htm</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The HTTP method of the userinfo request. In normal cases, the value should
 * be either {@code "GET"} or {@code "POST"}.
 * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the Application
 * Layer (DPoP)"</i> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the userinfo endpoint. If omitted, the {@code userInfoEndpoint}
 * property of {@link Service} is used as the default value.
 * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the Application
 * Layer (DPoP)"</i> for details.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 */
public class UserInfoRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The access token.
     */
    private String token;


    /**
     * The client certificate used in the TLS connection established
     * between the client application and the userinfo endpoint.
     *
     * @since 2.37
     */
    private String clientCertificate;


    /**
     * The {@code DPoP} header.
     *
     * @since 2.70
     */
    private String dpop;


    /**
     * The HTTP method of the userinfo request.
     *
     * @since 2.70
     */
    private String htm;


    /**
     * The URL of the userinfo endpoint.
     *
     * @since 2.70
     */
    private String htu;


    /**
     * Get the access token which has come along with the userinfo
     * request from the client application.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token which has been issued by Authlete.
     * The access token is the one that has come along with the
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfoRequest"
     * >userinfo request</a> from the client application.
     */
    public UserInfoRequest setToken(String token)
    {
        this.token = token;

        return this;
    }


    /**
     * Get the client certificate used in the TLS connection established
     * between the client application and the userinfo endpoint.
     *
     * @return
     *         The client certificate in PEM format.
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the TLS connection established
     * between the client application and the userinfo endpoint.
     *
     * <p>
     * The value of this request parameter is referred to when the access
     * token given to the userinfo endpoint was bound to a client certificate
     * when it was issued. See <i>"<a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-mtls/?include_text=1"
     * >OAuth 2.0 Mutual TLS Client Authentication and Certificate-Bound Access
     * Tokens</a>"</i> for details about the specification of certificate-bound
     * access tokens.
     * </p>
     *
     * @param certificate
     *         The client certificate in PEM format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.37
     */
    public UserInfoRequest setClientCertificate(String certificate)
    {
        this.clientCertificate = certificate;

        return this;
    }


    /**
     * Get the {@code DPoP} header presented by the client during the request
     * to the userinfo endpoint. The header contains a signed JWT which
     * includes the public key that is paired with the private key used to
     * sign the JWT.
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @return
     *         The {@code DPoP} header string.
     *
     * @since 2.70
     */
    public String getDpop()
    {
        return dpop;
    }


    /**
     * Set the {@code DPoP} header presented by the client during the request
     * to the userinfo endpoint. The header contains a signed JWT which
     * includes the public key that is paired with the private key used to
     * sign the JWT.
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @param dpop
     *         The {@code DPoP} header string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     */
    public UserInfoRequest setDpop(String dpop)
    {
        this.dpop = dpop;

        return this;
    }


    /**
     * Get the HTTP method of the userinfo request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is either {@code "GET"} or {@code "POST"}.
     * </p>
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @return
     *         The HTTP method as a string. For example, {@code "GET"}.
     *
     * @since 2.70
     */
    public String getHtm()
    {
        return htm;
    }


    /**
     * Set the HTTP method of the userinfo request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is either {@code "GET"} or {@code "POST"}.
     * </p>
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @param htm
     *         The HTTP method as a string. For example, {@code "GET"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     */
    public UserInfoRequest setHtm(String htm)
    {
        this.htm = htm;

        return this;
    }


    /**
     * Get the URL of the userinfo endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code userInfoEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @return
     *         The URL of the userinfo endpoint.
     *
     * @since 2.70
     */
    public String getHtu()
    {
        return htu;
    }


    /**
     * Set the URL of the userinfo endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code userInfoEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @param htu
     *         The URL of the userinfo endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     */
    public UserInfoRequest setHtu(String htu)
    {
        this.htu = htu;

        return this;
    }
}
