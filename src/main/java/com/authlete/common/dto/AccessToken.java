/*
 * Copyright (C) 2018 Authlete, Inc.
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
 * Information about an access token.
 *
 * @since 2.22
 */
public class AccessToken implements Serializable
{
    private static final long serialVersionUID = 2L;


    private String accessTokenHash;
    private String refreshTokenHash;
    private long clientId;
    private String subject;
    private GrantType grantType;
    private String[] scopes;
    private long accessTokenExpiresAt;
    private long refreshTokenExpiresAt;
    private long createdAt;
    private long lastRefreshedAt;
    private Property[] properties;
    private String[] refreshTokenScopes;


    /**
     * Get the hash of the access token.
     *
     * @return
     *         The hash of the access token.
     */
    public String getAccessTokenHash()
    {
        return accessTokenHash;
    }


    /**
     * Set the hash of the access token.
     *
     * @param hash
     *         The hash of the access token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setAccessTokenHash(String hash)
    {
        this.accessTokenHash = hash;

        return this;
    }


    /**
     * Get the hash of the refresh token. {@code null} may be returned.
     *
     * @return
     *         The hash of the refresh token or {@code null}.
     */
    public String getRefreshTokenHash()
    {
        return refreshTokenHash;
    }


    /**
     * Set the hash of the refresh token.
     *
     * @param hash
     *         The hash of the refresh token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setRefreshTokenHash(String hash)
    {
        this.refreshTokenHash = hash;

        return this;
    }


    /**
     * Get the ID of the client associated with the access token.
     *
     * @return
     *         The ID of the client associated with the access token.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the ID of the client associated with the access token.
     *
     * @param clientId
     *         The ID of the client associated with the access token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the subject (= unique user ID) associated with the access token.
     * {@code null} is returned if the access token was created using the
     * <a href="https://tools.ietf.org/html/rfc6749#section-4.4">Client Credentials</a>
     * flow.
     *
     * @return
     *         The subject (= unique user ID) associated with the access token or
     *         {@code null} if the access token was created using the
     *         <a href="https://tools.ietf.org/html/rfc6749#section-4.4">Client Credentials</a>
     *         flow.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique user ID) associated with the access token.
     *
     * @param subject
     *         The subject (= unique user ID) associated with the access token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the grant type of the access token when the access token was created.
     * Note that the value of the grant type is not changed when the access token
     * is refreshed using the refresh token.
     *
     * @return
     *         The grant type of the access token when the access token was created.
     */
    public GrantType getGrantType()
    {
        return grantType;
    }


    /**
     * Set the grant type of the access token when the access token was created.
     *
     * @param grantType
     *         The grant type of the access token when the access token was created.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setGrantType(GrantType grantType)
    {
        this.grantType = grantType;

        return this;
    }


    /**
     * Get the scopes associated with the access token.
     *
     * @return
     *         The scopes associated with the access token.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes associated with the access token.
     *
     * @param scopes
     *         The scopes associated with the access token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the timestamp at which the access token will expire.
     *
     * @return
     *         The expiration timestamp in milliseconds since the Unix epoch (1970-01-01).
     */
    public long getAccessTokenExpiresAt()
    {
        return accessTokenExpiresAt;
    }


    /**
     * Set the timestamp at which the access token will expire.
     *
     * @param expiresAt
     *         The expiration timestamp in milliseconds since the Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setAccessTokenExpiresAt(long expiresAt)
    {
        this.accessTokenExpiresAt = expiresAt;

        return this;
    }


    /**
     * Get the timestamp at which the refresh token will expire. {@code 0} is
     * returned if {@link #getRefreshTokenHash()} returns {@code null}.
     *
     * @return
     *         The expiration timestamp in milliseconds since the Unix epoch (1970-01-01).
     */
    public long getRefreshTokenExpiresAt()
    {
        return refreshTokenExpiresAt;
    }


    /**
     * Set the timestamp at which the refresh token will expire.
     *
     * @param expiresAt
     *         The expiration timestamp in milliseconds since the Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setRefreshTokenExpiresAt(long expiresAt)
    {
        this.refreshTokenExpiresAt = expiresAt;

        return this;
    }


    /**
     * Get the timestamp at which the access token was first created. Note
     * that the value of the timestamp is not changed when the access token is
     * refreshed with the refresh token.
     *
     * @return
     *         The timestamp at which the access token was first created in
     *         milliseconds since the Unix epoch (1970-01-01).
     */
    public long getCreatedAt()
    {
        return createdAt;
    }


    /**
     * Set the timestamp at which the access token was first created.
     *
     * @param createdAt
     *         The timestamp at which the access token was first created in
     *         milliseconds since the Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setCreatedAt(long createdAt)
    {
        this.createdAt = createdAt;

        return this;
    }


    /**
     * Get the timestamp at which the access token was last refreshed using the
     * refresh token. {@code 0} is returned if it has never been refreshed.
     *
     * @return
     *         The timestamp at which the access token was last refreshed using
     *         the refreshed token in milliseconds since the Unix epoch (1970-01-01).
     *         {@code 0} is returned if it has never been refreshed.
     */
    public long getLastRefreshedAt()
    {
        return lastRefreshedAt;
    }


    /**
     * Set the timestamp at which the access token was last refreshed using the
     * refresh token.
     *
     * @param lastRefreshedAt
     *         The timestamp at which the access token was last refreshed using
     *         the refreshed token in milliseconds since the Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setLastRefreshedAt(long lastRefreshedAt)
    {
        this.lastRefreshedAt = lastRefreshedAt;

        return this;
    }


    /**
     * Get the properties associated with the access token.
     *
     * @return
     *         The properties associated with the access token.
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set the properties associated with the access token.
     *
     * @param properties
     *         The properties associated with the access token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the scopes associated with the refresh token.
     *
     * @return
     *         The scopes associated with the refresh token. May be {@code null}.
     *
     * @since 3.89
     * @since Authlete API 3.0
     */
    public String[] getRefreshTokenScopes()
    {
        return refreshTokenScopes;
    }


    /**
     * Set the scopes associated with the refresh token.
     *
     * @param refreshTokenScopes
     *         The scopes associated with the refresh token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.89
     * @since Authlete API 3.0
     */
    public AccessToken setRefreshTokenScopes(String[] refreshTokenScopes)
    {
        this.refreshTokenScopes = refreshTokenScopes;

        return this;
    }
}
