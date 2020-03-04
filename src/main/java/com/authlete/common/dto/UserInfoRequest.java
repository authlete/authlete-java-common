/*
 * Copyright (C) 2015-2019 Authlete, Inc.
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

    private String dpop;

    private String htm;

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
     * Get the DPoP header presented by the client during the request
     * to the token endpoint. This header contains a signed JWT which
     * includes the public key used to sign it.
     *
     * @return
     *         The DPoP header string.
     * 
     * @since 2.XX
     */
    public String getDpop()
    {
        return dpop;
    }


    /**
     * Set the DPoP header presented by the client during the request
     * to the token endpoint. This header contains a signed JWT which
     * includes the public key used to sign it.
     *
     * @param dpop
     *            The DPoP header string.
     * 
     * @return
     *         {@code this} object.
     *
     * @since 2.XX
     */
    public UserInfoRequest setDpop(String dpop)
    {
        this.dpop = dpop;

        return this;
    }


    /**
     * Get the HTTP Method used to make this request. This field is used
     * to validate the DPoP header.
     * 
     * @return
     *         The HTTP Method as a string.
     * 
     * @since 2.XX
     */
    public String getHtm()
    {
        return htm;
    }


    /**
     * Set the HTTP Method used to make this request. This field is used
     * to validate the DPoP header.
     * 
     * @param htm
     *            The HTTP Method as a string.
     * 
     * @return
     *         {@code this} object.
     *
     * @since 2.XX
     */
    public UserInfoRequest setHtm(String htm)
    {
        this.htm = htm;

        return this;
    }


    /**
     * Get the HTTP URL used to make this request. This field is used
     * to validate the DPoP header.
     * 
     * @return
     *         The HTTP URL as a string.
     * 
     * @since 2.XX
     */
    public String getHtu()
    {
        return htu;
    }


    /**
     * Set the HTTP URL used to make this request. This field is used
     * to validate the DPoP header.
     * 
     * @param htm
     *            The HTTP URL as a string.
     * 
     * @return
     *         {@code this} object.
     *
     * @since 2.XX
     */
    public UserInfoRequest setHtu(String htu)
    {
        this.htu = htu;

        return this;
    }
}
