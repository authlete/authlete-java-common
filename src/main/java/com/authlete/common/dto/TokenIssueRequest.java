/*
 * Copyright (C) 2014-2022 Authlete, Inc.
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
 * Request to Authlete's {@code /auth/token/issue} API.
 *
 * <blockquote>
 * <dl>
 *
 * <dt><b><code>ticket</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The ticket issued by Authlete's {@code /auth/token} API to the
 * service implementation. It is the value of {@code "ticket"}
 * contained in the response from Authlete's {@code /auth/token}
 * API ({@link TokenResponse}).
 * </p>
 * </dd>
 *
 * <dt><b><code>subject</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The subject (= unique identifier) of the authenticated user.
 * </p>
 * </dd>
 *
 * <dt><b><code>properties</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Extra properties to associate with a newly created access token.
 * Note that {@code properties} parameter is accepted only when
 * Content-Type of the request is application/json, so don't use
 * application/x-www-form-urlencoded if you want to specify
 * {@code properties}
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * <p>
 * {@code subject} request parameter was added as a required parameter
 * on version 1.13.
 * </p>
 *
 * @see TokenResponse
 *
 * @author Takahiko Kawasaki
 */
public class TokenIssueRequest implements Serializable
{
    private static final long serialVersionUID = 5L;


    /**
     * The ticket issued by Authlete's endpoint.
     */
    private String ticket;


    /**
     * The subject (unique identifier) of the authenticated user.
     */
    private String subject;


    /**
     * Extra properties to associate with an access token.
     */
    private Property[] properties;


    /**
     * Additional claims that are added to the payload part of the JWT
     * access token.
     *
     * @since 3.23
     */
    private String jwtAtClaims;


    /**
     * Get the value of {@code "ticket"} which is the ticket
     * issued by Authlete's {@code /auth/token} API to the
     * service implementation.
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
     * issued by Authlete's {@code /auth/token} API to the
     * service implementation.
     *
     * @param ticket
     *         The ticket.
     *
     * @return
     *         {@code this} object.
     */
    public TokenIssueRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }


    /**
     * Get the value of {@code "subject"} which is the unique
     * identifier of the authenticated user.
     *
     * @return
     *         The subject of the authenticated user.
     *
     * @since 1.13
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the value of {@code "subject"} which is the unique
     * identifier of the authenticated user.
     *
     * @param subject
     *         The subject of the authenticated user.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.13
     */
    public TokenIssueRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the extra properties to associate with an access token which
     * will be issued by this request.
     *
     * @return
     *         Extra properties.
     *
     * @since 1.30
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set extra properties to associate with an access token which will
     * be issued by this request.
     *
     * <p>
     * Keys of extra properties will be used as labels of top-level
     * entries in a JSON response containing an access token which is
     * returned from an authorization server. An example is
     * {@code example_parameter}, which you can find in <a href=
     * "https://tools.ietf.org/html/rfc6749#section-5.1">5.1. Successful
     * Response</a> in RFC 6749. The following code snippet is an example
     * to set one extra property having {@code example_parameter} as its
     * key and {@code example_value} as its value.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {@link Property}[] properties = { new {@link Property#Property(String, String)
     * Property}("example_parameter", "example_value") };
     * request.{@link #setProperties(Property[]) setProperties}(properties);
     * </pre>
     * </blockquote>
     *
     * <p>
     * Keys listed below should not be used and they would be ignored on
     * the server side even if they were used. It's because they are reserved
     * in <a href="https://tools.ietf.org/html/rfc6749">RFC 6749</a> and
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html"
     * >OpenID Connect Core 1.0</a>.
     * </p>
     *
     * <ul>
     *   <li>{@code access_token}
     *   <li>{@code token_type}
     *   <li>{@code expires_in}
     *   <li>{@code refresh_token}
     *   <li>{@code scope}
     *   <li>{@code error}
     *   <li>{@code error_description}
     *   <li>{@code error_uri}
     *   <li>{@code id_token}
     * </ul>
     *
     * <p>
     * Note that <b>there is an upper limit on the total size of extra properties</b>.
     * On the server side, the properties will be (1) converted to a multidimensional
     * string array, (2) converted to JSON, (3) encrypted by AES/CBC/PKCS5Padding, (4)
     * encoded by base64url, and then stored into the database. The length of the
     * resultant string must not exceed 65,535 in bytes. This is the upper limit, but
     * we think it is big enough.
     * </p>
     *
     * @param properties
     *         Extra properties.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.30
     */
    public TokenIssueRequest setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This request parameter has a meaning only when the format of access
     * tokens issued by this service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * @return
     *         Additional claims that are added to the payload part of the JWT
     *         access token.
     *
     * @since 3.23
     */
    public String getJwtAtClaims()
    {
        return jwtAtClaims;
    }


    /**
     * Set the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This request parameter has a meaning only when the format of access
     * tokens issued by this service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * @param claims
     *         Additional claims that are added to the payload part of the JWT
     *         access token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.23
     */
    public TokenIssueRequest setJwtAtClaims(String claims)
    {
        this.jwtAtClaims = claims;

        return this;
    }
}
