/*
 * Copyright (C) 2014 Authlete, Inc.
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
 * Request to Authlete's {@code /auth/authorization/issue} API.
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
 * <dt><b><code>subject</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The subject (= a user account managed by the service) who has
 * granted authorization to the client application.
 * </p>
 * </dd>
 *
 * <dt><b><code>authTime</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The time when the authentication of the end-user occurred.
 * </p>
 * </dd>
 *
 * <dt><b><code>acr</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The Authentication Context Class Reference performed for the
 * end-user authentication.
 * </p>
 * </dd>
 *
 * <dt><b><code>claims</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The claims of the end-user (= pieces of information about the
 * end-user) in JSON format. See
 * <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
 * >OpenID Connect Core 1.0, 5.1. Standard Claims</a> for details
 * about the format.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see AuthorizationResponse
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
 *      >OpenID Connect Core 1.0, 5.1. Standard Claims</a>
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationIssueRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The ticket issued by Authlete's endpoint.
     */
    private String ticket;


    /**
     * The subject (end-user) managed by the service.
     */
    private String subject;


    /**
     * The time when the end-user was authenticated.
     */
    private long authTime;


    /**
     * The authentication context class reference.
     */
    private String acr;


    /**
     * Claims in JSON format.
     */
    private String claims;


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
    public AuthorizationIssueRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }


    /**
     * Get the value of {@code "subject"} which is the subject
     * (= a user account managed by the service) who has granted
     * authorization to the client application.
     *
     * @return
     *         The subject.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the value of {@code "subject"} which is the subject
     * (= a user account managed by the service) who has granted
     * authorization to the client application.
     *
     * @param subject
     *         The subject.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationIssueRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the value of {@code "authTime"} which is the time
     * when the authentication of the end-user occurred.
     *
     * @return
     *         The time when the end-user authentication occurred.
     *         It is the number of seconds since 1970-01-01.
     */
    public long getAuthTime()
    {
        return authTime;
    }


    /**
     * Set the value of {@code "authTime"} which is the time
     * when the authentication of the end-user occurred.
     *
     * @param authTime
     *         The time when the end-user authentication occurred.
     *         It is the number of seconds since 1970-01-01.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationIssueRequest setAuthTime(long authTime)
    {
        this.authTime = authTime;

        return this;
    }


    /**
     * Get the value of {@code "acr"} which is the authentication
     * context class reference value which the end-user authentication
     * satisfied.
     *
     * @return
     *         The authentication context class reference.
     */
    public String getAcr()
    {
        return acr;
    }


    /**
     * Set the value of {@code "acr"} which is the authentication
     * context class reference value which the end-user authentication
     * satisfied.
     *
     * @param acr
     *         The authentication context class reference.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationIssueRequest setAcr(String acr)
    {
        this.acr = acr;

        return this;
    }


    /**
     * Get the value of {@code "claims"} which is the claims of the subject
     * in JSON format.
     *
     * @return
     *         The claims of the subject in JSON format. See the description
     *         of {@link #setClaims(String)} for details about the format.
     *
     * @see #setClaims(String)
     */
    public String getClaims()
    {
        return claims;
    }


    /**
     * Set the value of {@code "claims"} which is the claims of the subject
     * in JSON format.
     *
     * <p>
     * The service implementation is required to retrieve claims of the subject
     * (= information about the end-user) from its database and format them in
     * JSON format.
     * </p>
     *
     * <p>
     * For example, if <code>"given_name"</code> claim, <code>"family_name"</code>
     * claim and <code>"email"</code> claim are requested, the service implementation
     * should generate a JSON object like the following:
     * </p>
     *
     * <pre>
     * {
     *   "given_name": "Takahiko",
     *   "family_name": "Kawasaki",
     *   "email": "takahiko.kawasaki@example.com"
     * }
     * </pre>
     *
     * <p>
     * and set its String representation by this method.
     * </p>
     *
     * <p>
     * See <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >OpenID Connect Core 1.0, 5.1. Standard Claims</a> for further details
     * about the format.
     * </p>
     *
     * @param claims
     *         The claims of the subject in JSON format.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     *      >OpenID Connect Core 1.0, 5.1. Standard Claims</a>
     */
    public AuthorizationIssueRequest setClaims(String claims)
    {
        this.claims = claims;

        return this;
    }
}
