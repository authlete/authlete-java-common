/*
 * Copyright (C) 2014-2023 Authlete, Inc.
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
 * <dt><b><code>jwtAtClaims</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * Additional claims in JSON object format that are added to the payload part
 * of the JWT access token. See the description of
 * {@link #getJwtAtClaims()} for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>accessToken</code></b> (OPTIONAL; Authlete 2.2.27 onwards)</dt>
 * <dd>
 * <p>
 * The representation of an access token that may be issued as a result of the
 * Authlete API call. See {@link #getAccessToken()} for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>accessTokenDuration</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The duration of the access token that may be issued as a result of the
 * Authlete API call. See {@link #getAccessTokenDuration()} for details.
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
    private static final long serialVersionUID = 7L;


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
     * The representation of an access token that may be issued as a
     * result of the Authlete API call.
     *
     * @since 3.24
     * @since Authlete 2.2.27
     */
    private String accessToken;


    /**
     * The duration of the access token that may be issued as a result
     * of the Authlete API call.
     *
     * @since 3.65
     * @since Authlete 2.2.41
     * @since Authlete 2.3.5
     * @since Authlete 3.0
     */
    private long accessTokenDuration;


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


    /**
     * Get the representation of an access token that may be issued as a
     * result of the Authlete API call.
     *
     * <p>
     * Basically, it is the Authlete server's role to generate an access token.
     * However, some systems may have inflexible restrictions on the format of
     * access tokens. Such systems may use this {@code accessToken} request
     * parameter to specify the representation of an access token by themselves
     * instead of leaving the access token generation task to the Authlete server.
     * </p>
     *
     * <p>
     * Usually, the Authlete server (1) generates a random 256-bit value, (2)
     * base64url-encodes the value into a 43-character string, and (3) uses the
     * resultant string as the representation of an access token. The Authlete
     * implementation is written on the assumption that the 256-bit entropy is
     * big enough. Therefore, <b>make sure that the entropy of the value of the
     * {@code accessToken} request parameter is big enough, too.</b>
     * </p>
     *
     * </p>
     * The entropy does not necessarily have to be equal to or greater than 256
     * bits. For example, 192-bit random values (which will become 32-character
     * strings when encoded by base64url) may be enough. However, note that if
     * the entropy is too low, access token string values will collide and
     * Authlete API calls will fail.
     * </p>
     *
     * <p>
     * When no access token is generated as a result of the Authlete API call,
     * this {@code accessToken} request parameter is not used.
     * </p>
     *
     * @return
     *         The representation of an access token that may be issued as a
     *         result of the Authlete API call.
     *
     * @since 3.24
     * @since Authlete 2.2.27
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the representation of an access token that may be issued as a
     * result of the Authlete API call.
     *
     * <p>
     * Basically, it is the Authlete server's role to generate an access token.
     * However, some systems may have inflexible restrictions on the format of
     * access tokens. Such systems may use this {@code accessToken} request
     * parameter to specify the representation of an access token by themselves
     * instead of leaving the access token generation task to the Authlete server.
     * </p>
     *
     * <p>
     * Usually, the Authlete server (1) generates a random 256-bit value, (2)
     * base64url-encodes the value into a 43-character string, and (3) uses the
     * resultant string as the representation of an access token. The Authlete
     * implementation is written on the assumption that the 256-bit entropy is
     * big enough. Therefore, <b>make sure that the entropy of the value of the
     * {@code accessToken} request parameter is big enough, too.</b>
     * </p>
     *
     * </p>
     * The entropy does not necessarily have to be equal to or greater than 256
     * bits. For example, 192-bit random values (which will become 32-character
     * strings when encoded by base64url) may be enough. However, note that if
     * the entropy is too low, access token string values will collide and
     * Authlete API calls will fail.
     * </p>
     *
     * <p>
     * When no access token is generated as a result of the Authlete API call,
     * this {@code accessToken} request parameter is not used.
     * </p>
     *
     * @param accessToken
     *         The representation of an access token that may be issued as a
     *         result of the Authlete API call.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.24
     * @since Authlete 2.2.27
     */
    public TokenIssueRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the duration of the access token that may be issued as a result of
     * the Authlete API call.
     *
     * <p>
     * When this request parameter holds a positive integer, it is used as the
     * duration of the access token. In other cases, this request parameter is
     * ignored.
     * </p>
     *
     * @return
     *         The duration of the access token in seconds.
     *
     * @since 3.65
     * @since Authlete 2.2.41
     * @since Authlete 2.3.5
     * @since Authlete 3.0
     */
    public long getAccessTokenDuration()
    {
        return accessTokenDuration;
    }


    /**
     * Set the duration of the access token that may be issued as a result of
     * the Authlete API call.
     *
     * <p>
     * When this request parameter holds a positive integer, it is used as the
     * duration of the access token. In other cases, this request parameter is
     * ignored.
     * </p>
     *
     * @param duration
     *         The duration of the access token in seconds.
     *
     * @return
     *         {@code this} request parameter.
     *
     * @since 3.65
     * @since Authlete 2.2.41
     * @since Authlete 2.3.5
     * @since Authlete 3.0
     */
    public TokenIssueRequest setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;

        return this;
    }
}
