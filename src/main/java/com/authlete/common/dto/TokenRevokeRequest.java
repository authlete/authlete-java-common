/*
 * Copyright (C) 2022 Authlete, Inc.
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
 * Request to Authlete's {@code /auth/token/revoke} API.
 *
 * <p>
 * The {@code /auth/token/revoke} API revokes access/refresh tokens. The API
 * processes the request in the order shown below.
 * </p>
 *
 * <ol>
 * <li>
 * <p>
 * When the {@code accessTokenIdentifier} request parameter is given, the
 * access token identified by the identifier (and the corresponding refresh
 * token) is revoked.
 * Other request parameters are ignored and the revocation process ends here.
 * </p>
 * <br/>
 *
 * <li>
 * <p>
 * When the {@code refreshTokenIdentifier} request parameter is given, the
 * refresh token identified by the identifier (and the corresponding access
 * token) is revoked.
 * Other request parameters are ignored and the revocation process ends here.
 * </p>
 * <br/>
 *
 * <li>
 * <p>
 * When both the {@code clientIdentifier} request parameter and the {@code
 * subject} request parameter are given, access/refresh tokens whose client
 * and subject match the specified values are revoked.
 * Other request parameters are ignored and the revocation process ends here.
 * </p>
 * <br/>
 *
 * <li>
 * <p>
 * When the {@code clientIdentifier} request parameter is given and the {@code
 * subject} request parameter is not given, access/refresh tokens whose client
 * matches the specified value are revoked.
 * Other request parameters are ignored and the revocation process ends here.
 * </p>
 * <br/>
 *
 * <li>
 * <p>
 * When the {@code subject} request parameter is given and the {@code
 * clientIdentifier} request parameter is not given, access/refresh tokens
 * whose subject matches the specified value are revoked.
 * Other request parameters are ignored and the revocation process ends here.
 * </p>
 * <br/>
 *
 * <li>
 * <p>
 * In other cases, that is, when none of the request parameters are given,
 * the API returns {@code 400 Bad Request}.
 * </p>
 * </ol>
 *
 * @since 3.26
 * @since Authlete 2.2.29
 */
public class TokenRevokeRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String accessTokenIdentifier;
    private String refreshTokenIdentifier;
    private String clientIdentifier;
    private String subject;


    /**
     * Get the identifier of an access token to revoke.
     *
     * <p>
     * The hash of an access token is recognized as an identifier
     * as well as the access token itself.
     * </p>
     *
     * @return
     *         The identifier of an access token to revoke.
     */
    public String getAccessTokenIdentifier()
    {
        return accessTokenIdentifier;
    }


    /**
     * Set the identifier of an access token to revoke.
     *
     * <p>
     * The hash of an access token is recognized as an identifier
     * as well as the access token itself.
     * </p>
     *
     * @param identifier
     *         The identifier of an access token to revoke.
     *
     * @return
     *         {@code this} instance.
     */
    public TokenRevokeRequest setAccessTokenIdentifier(String identifier)
    {
        this.accessTokenIdentifier = identifier;

        return this;
    }


    /**
     * Get the identifier of a refresh token to revoke.
     *
     * <p>
     * The hash of a refresh token is recognized as an identifier
     * as well as the refresh token itself.
     * </p>
     *
     * @return
     *         The identifier of a refresh token to revoke.
     */
    public String getRefreshTokenIdentifier()
    {
        return refreshTokenIdentifier;
    }


    /**
     * Set the identifier of a refresh token to revoke.
     *
     * <p>
     * The hash of a refresh token is recognized as an identifier
     * as well as the refresh token itself.
     * </p>
     *
     * @param identifier
     *         The identifier of a refresh token to revoke.
     *
     * @return
     *         {@code this} instance.
     */
    public TokenRevokeRequest setRefreshTokenIdentifier(String identifier)
    {
        this.refreshTokenIdentifier = identifier;

        return this;
    }


    /**
     * Get the identifier of a client.
     *
     * <p>
     * Both the numeric client ID and the alias are recognized as an identifier
     * of a client.
     * </p>
     *
     * @return
     *         The identifier of a client.
     */
    public String getClientIdentifier()
    {
        return clientIdentifier;
    }


    /**
     * Set the identifier of a client.
     *
     * <p>
     * Both the numeric client ID and the alias are recognized as an identifier
     * of a client.
     * </p>
     *
     * @param identifier
     *         The identifier of a client.
     *
     * @return
     *         {@code this} instance.
     */
    public TokenRevokeRequest setClientIdentifier(String identifier)
    {
        this.clientIdentifier = identifier;

        return this;
    }


    /**
     * Get the subject of a resource owner.
     *
     * @return
     *         The subject of a resource owner.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject of a resource owner.
     *
     * @param subject
     *         The subject of a resource owner.
     *
     * @return
     *         {@code this} object.
     */
    public TokenRevokeRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }
}
