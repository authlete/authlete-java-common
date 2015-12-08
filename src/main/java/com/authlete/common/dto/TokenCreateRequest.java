/*
 * Copyright (C) 2015 Authlete, Inc.
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
import com.authlete.common.types.GrantType;


/**
 * Request to Authlete's {@code /auth/token/create} API.
 *
 * <p>
 * The API is used to create an arbitrary access token in a special way
 * that is different from standard grant flows.
 * </p>
 *
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>grantType</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The grant type for a newly created access token. One of the following.
 * {@link com.authlete.common.types.GrantType#REFRESH_TOKEN REFRESH_TOKEN}
 * is not allowed.
 * </p>
 * <ol>
 * <li>{@link com.authlete.common.types.GrantType#AUTHORIZATION_CODE AUTHORIZATION_CODE}
 * <li>{@link com.authlete.common.types.GrantType#IMPLICIT IMPLICIT}
 * <li>{@link com.authlete.common.types.GrantType#PASSWORD PASSWORD}
 * <li>{@link com.authlete.common.types.GrantType#CLIENT_CREDENTIALS CLIENT_CREDENTIALS}
 * </ol>
 * <p>
 * When {@code grantType} is either {@code IMPLICIT} or {@code CLIENT_CREDENTIALS},
 * a refresh token is not issued.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientId</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The ID of the client application which will be associated with
 * a newly created access token.
 * </p>
 * </dd>
 *
 * <dt><b><code>subject</code></b> (CONDITIONALLY REQUIRED)</dt>
 * <dd>
 * <p>
 * The subject (= unique identifier) of the user who will be associated with
 * a newly created access token. This parameter is required unless the grant
 * type is {@link com.authlete.common.types.GrantType#CLIENT_CREDENTIALS
 * CLIENT_CREDENTIALS}. The value must consist of only ASCII characters and
 * its length must not exceed 100.
 * </p>
 * </dd>
 *
 * <dt><b><code>scopes</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The scopes which will be associated with a newly created access token.
 * Scopes that are not supported by the service cannot be specified and
 * requesting them will cause an error.
 * </p>
 * </dd>
 *
 * <dt><b><code>accessTokenDuration</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The duration of a newly created access token in seconds. If the value is 0,
 * the duration is determined according to the settings of the service.
 * </p>
 * </dd>
 *
 * <dt><b><code>refreshTokenDuration</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The duration of a newly created refresh token in seconds. If the value is 0,
 * the duration is determined according to the settings of the service.
 * </p>
 * <p>
 * A refresh token is not created (1) if the service does not support
 * {@link com.authlete.common.types.GrantType#REFRESH_TOKEN REFRESH_TOKEN},
 * or (2) if the specified grant type is either {@link
 * com.authlete.common.types.GrantType#IMPLICIT IMPLICIT} or {@link
 * com.authlete.common.types.GrantType#CLIENT_CREDENTIALS CLIENT_CREDENTIALS}.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @see TokenCreateResponse
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.13
 */
public class TokenCreateRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    private GrantType grantType;
    private long clientId;
    private String subject;
    private String[] scopes;
    private long accessTokenDuration;
    private long refreshTokenDuration;


    /**
     * Get the grant type for a newly created access token.
     *
     * @return
     *         Grant type.
     */
    public GrantType getGrantType()
    {
        return grantType;
    }


    /**
     * Set the grant type for a newly created access token.
     *
     * @param grantType
     *         Grant type.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateRequest setGrantType(GrantType grantType)
    {
        this.grantType = grantType;

        return this;
    }


    /**
     * Get the client ID that will be associated with a newly created
     * access token.
     *
     * @return
     *         Client ID.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID that will be associated with a newly created
     * access token.
     *
     * @param clientId
     *         Client ID.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateRequest setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the subject (= unique identifier) of the user who will be
     * associated with a newly created access token.
     *
     * @return
     *         The subject of the user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the user who will be
     * associated with a newly created access token.
     *
     * @param subject
     *         The subject of the user.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the scopes that will be associated with a newly created
     * access token.
     *
     * @return
     *         Scopes.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes that will be associated with a newly created
     * access token.
     *
     * @param scopes
     *         Scopes.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateRequest setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the duration of a newly created access token in seconds.
     * 0 means that the duration will be determined according to the
     * settings of the service.
     *
     * @return
     *         The duration of a newly created access token.
     */
    public long getAccessTokenDuration()
    {
        return accessTokenDuration;
    }


    /**
     * Set the duration of a newly created access token in seconds.
     * 0 means that the duration will be determined according to the
     * settings of the service.
     *
     * @param accessTokenDuration
     *         The duration of a newly created access token.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateRequest setAccessTokenDuration(long accessTokenDuration)
    {
        this.accessTokenDuration = accessTokenDuration;

        return this;
    }


    /**
     * Get the duration of a newly created refresh token in seconds.
     * 0 means that the duration will be determined according to the
     * settings of the service.
     *
     * @return
     *         The duration of a newly created refresh token.
     */
    public long getRefreshTokenDuration()
    {
        return refreshTokenDuration;
    }


    /**
     * Set the duration of a newly created refresh token in seconds.
     * 0 means that the duration will be determined according to the
     * settings of the service.
     *
     * @param refreshTokenDuration
     *         The duration of a newly created refresh token.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateRequest setRefreshTokenDuration(long refreshTokenDuration)
    {
        this.refreshTokenDuration = refreshTokenDuration;

        return this;
    }
}
