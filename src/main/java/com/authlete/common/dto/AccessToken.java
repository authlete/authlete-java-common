/* Copyright (C) 2018 Authlete, Inc.
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
    private static final long serialVersionUID = 1L;


    private String accessTokenHash;
    private String refreshTokenHash;
    private long clientId;
    private String subject;
    private GrantType grantType;
    private String[] scopes;
    private long accessTokenExpiresAt;
    private long refreshTokenExpiresAt;
    private long createdAt;
    private long lastUsedAt;
    private Property[] properties;


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
     * Get the hash of the refresh token.
     *
     * @return
     *         The hash of the refresh token.
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
     *
     * @return
     *         The subject (= unique user ID) associated with the access token.
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
     * Get the grant type of the access token when the access token was generated.
     *
     * @return
     *         The grant type of the access token when the access token was generated.
     */
    public GrantType getGrantType()
    {
        return grantType;
    }


    /**
     * Set the grant type of the access token when the access token was generated..
     *
     * @param grantType
     *         The grant type of the access token when the access token was generated..
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
     * Set the timestamp at which the access token will expire.
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
     * Set the timestamp at which the refresh token will expire. This method may
     * return {@code null}.
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
     * Get the timestamp at which the access token was created.
     *
     * @return
     *         The creation timestamp in milliseconds since the Unix epoch (1970-01-01).
     *
     */
    public long getCreatedAt()
    {
        return createdAt;
    }


    /**
     * Set the timestamp at which the access token was created.
     *
     * @param createdAt
     *         The creation timestamp in milliseconds since the Unix epoch (1970-01-01).
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
     * Get the timestamp at which the access token was last used.
     *
     * @return
     *         The timestamp at which the acccess token was last used in milliseconds
     *         since the Unix epoch (1970-01-01).
     */
    public long getLastUsedAt()
    {
        return lastUsedAt;
    }


    /**
     * Set the timestamp at which the access token was last used.
     *
     * @param lastUsedAt
     *         The timestamp at which the acccess token was last used in milliseconds
     *         since the Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setLastUsedAt(long lastUsedAt)
    {
        this.lastUsedAt = lastUsedAt;

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
}
