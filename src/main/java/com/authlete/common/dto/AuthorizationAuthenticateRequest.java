/*
 * Copyright (C) 2014 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's {@code /auth/authorization/authenticate} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>ticket</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The ticket issued by Authlete's {@code /auth/authorization} API
 * to the service implementation. It is the value of {@code "ticket"}
 * contained in the response from Authlete's {@code
 * /auth/authorization} API ({@link AuthorizationResponse}).
 * </p>
 * </dd>
 *
 * <dt><b><code>loginId</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The login ID of the end-user to authenticate.
 * </p>
 * </dd>
 *
 * <dt><b><code>password</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The password of the end-user to authenticate.
 * </p>
 * </dd>
 *
 * <dt><b><code>claims</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * A space-delimited names of claims to request.
 * </p>
 * </dd>
 *
 * <dt><b><code>claimsLocales</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * A space-delimited locales for claims.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see AuthorizationResponse
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationAuthenticateRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The ticket issued by Authlete's /auth/authorization API.
     */
    private String ticket;


    /**
     * The login ID of the end-user to authenticate.
     */
    private String loginId;


    /**
     * The password of the end-user to authenticate.
     */
    private String password;


    /**
     * The claims to request.
     */
    private String claims;


    /**
     * Locales for claims.
     */
    private String claimsLocales;


    /**
     * Get the value of {@code "ticket"} which is the ticket
     * issued by Authlete's {@code /auth/authorization} API
     * to the service implementation.
     *
     * @return
     *         The ticket.
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the value of {@code "ticket"} which is the ticket
     * issued by Authlete's {@code /auth/authorization} API
     * to the service implementation.
     *
     * @param ticket
     *         The ticket.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationAuthenticateRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }


    /**
     * Get the login ID of the end-user to authenticate.
     *
     * @return
     *         The login ID of the end-user to authenticate.
     */
    public String getLoginId()
    {
        return loginId;
    }


    /**
     * Set the login ID of the end-user to authenticate.
     *
     * @param loginId
     *         The login ID of the end-user to authenticate.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationAuthenticateRequest setLoginId(String loginId)
    {
        this.loginId = loginId;

        return this;
    }


    /**
     * Get the password of the end-user to authenticate.
     *
     * @return
     *         The password of the end-user to authenticate.
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * Set the password of the end-user to authenticate.
     *
     * @param password
     *         The password of the end-user to authenticate.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationAuthenticateRequest setPassword(String password)
    {
        this.password = password;

        return this;
    }


    /**
     * Get the space-delimited names of claims to request.
     *
     * @return
     *         The space-delimited names of claims to request.
     */
    public String getClaims()
    {
        return claims;
    }


    /**
     * Set the space-delimited names of claims to request.
     *
     * @param claims
     *         The space-delimited names of claims to request.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationAuthenticateRequest setClaims(String claims)
    {
        this.claims = claims;

        return this;
    }


    /**
     * Get the space-delimited locales for claims.
     *
     * @return
     *         The space-delimited locales for claims.
     */
    public String getClaimsLocales()
    {
        return claimsLocales;
    }


    /**
     * Set the space-delimited locales for claims.
     *
     * @param claimsLocales
     *         The space-delimited locales for claims.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationAuthenticateRequest setClaimsLocales(String claimsLocales)
    {
        this.claimsLocales = claimsLocales;

        return this;
    }
}
