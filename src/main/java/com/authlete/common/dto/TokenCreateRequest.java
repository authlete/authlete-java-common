/*
 * Copyright (C) 2015-2021 Authlete, Inc.
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
import java.net.URI;
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
 * <dt><b><code>properties</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Extra properties to associate with a newly created access token. Note that
 * {@code properties} parameter is accepted only when Content-Type of the
 * request is application/json, so don't use application/x-www-form-urlencoded
 * if you want to specify {@code properties}
 * </p>
 * </dd>
 *
 * <dt><b><code>clientIdAliasUsed</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * A boolean request parameter which indicates whether to emulate that the client
 * ID alias is used instead of the original numeric client ID when a new access
 * token is created.
 * </p>
 * <p>
 * This has an effect only on the value of the {@code aud} claim in a response from
 * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * endpoint</a>. When you access the UserInfo endpoint (which is expected to be
 * implemented using Authlete's {@code /api/auth/userinfo} API and {@code
 * /api/auth/userinfo/issue} API) with an access token which has been created using
 * Authlete's {@code /api/auth/token/create} API with this property ({@code
 * clientIdAliasUsed}) true, the client ID alias is used as the value of the {@code
 * aud} claim in a response from the UserInfo endpoint.
 * </p>
 * <p>
 * Note that if a client ID alias is not assigned to the client when Authlete's
 * {@code /api/auth/token/create} API is called, this property ({@code
 * clientIdAliasUsed}) has no effect (it is always regarded as {@code false}).
 * </p>
 * </dd>
 *
 * <dt><b><code>accessToken</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the new access token.
 * </p>
 * <p>
 * The {@code /api/auth/token/create} API generates an access token.
 * Therefore, callers of the API do not have to specify values of
 * newly created access tokens. However, in some cases, for example,
 * if you want to migrate existing access tokens from an old system
 * to Authlete, you may want to specify values of access tokens.
 * In such a case, you can specify the value of a newly created
 * access token by passing a non-null value as the value of
 * {@code accessToken} request parameter. The implementation of the
 * {@code /api/auth/token/create} uses the value of the {@code
 * accessToken} request parameter instead of generating a new value
 * when the request parameter holds a non-null value.
 * </p>
 * <p>
 * Note that if the hash value of the specified access token already
 * exists in Authlete's database, the access token cannot be inserted
 * and the {@code /api/auth/token/create} API will report an error.
 * </p>
 * </dd>
 *
 * <dt><b><code>refreshToken</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the new refresh token.
 * </p>
 * <p>
 * The {@code /api/auth/token/create} API may generate a refresh token.
 * Therefore, callers of the API do not have to specify values of
 * newly created refresh tokens. However, in some cases, for example,
 * if you want to migrate existing refresh tokens from an old system
 * to Authlete, you may want to specify values of refresh tokens.
 * In such a case, you can specify the value of a newly created
 * refresh token by passing a non-null value as the value of
 * {@code refreshToken} request parameter. The implementation of the
 * {@code /api/auth/token/create} uses the value of the {@code
 * refreshToken} request parameter instead of generating a new value
 * when the request parameter holds a non-null value.
 * </p>
 *
 * <p>
 * Note that if the hash value of the specified refresh token already
 * exists in Authlete's database, the refresh token cannot be inserted
 * and the {@code /api/auth/token/create} API will report an error.
 * </p>
 * </dd>
 *
 * <dt><b><code>accessTokenPersistent</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * A boolean request parameter which indicates whether the access token
 * expires or not. By default, all access tokens expire after a period of
 * time determined by their service. If this request parameter is {@code true}
 * then the access token will not automatically expire and must be revoked
 * or deleted manually at the service.
 * </p>
 *
 * <p>
 * If this request parameter is {@code true}, the {@code accessTokenDuration}
 * request parameter is ignored.
 * </p>
 * </dd>
 *
 * <dt><b><code>certificateThumbprint</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The thumbprint of the MTLS certificate bound to this token. If this field
 * is set, a certificate with the corresponding value MUST be presented with the
 * access token when it is used by a client.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpopKeyThumbprint</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The thumbprint of the public key used for DPoP presentation of this token.
 * If this field is set, a DPoP proof signed with the corresponding private key
 * MUST be presented with the access token when it is used by a client. Additionally,
 * the token's {@code token_type} will be set to 'DPoP'.
 * </p>
 * </dd>
 *
 * <dt><b><code>authorizationDetails</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code authorization_details} to associate with the token.
 * Can be {@code null}.
 * </p>
 * </dd>
 *
 * <dt><b><code>resources</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code resource}s to associate with the token.
 * Can be {@code null}.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @see TokenCreateResponse
 *
 * @since 1.13
 */
public class TokenCreateRequest implements Serializable
{
    private static final long serialVersionUID = 9L;


    private GrantType grantType;
    private long clientId;
    private String subject;
    private String[] scopes;
    private long accessTokenDuration;
    private long refreshTokenDuration;
    private Property[] properties;
    private boolean clientIdAliasUsed;
    private String accessToken;
    private String refreshToken;
    private boolean accessTokenPersistent;
    private String certificateThumbprint;
    private String dpopKeyThumbprint;
    private AuthzDetails authorizationDetails;
    private URI[] resources;


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
    public TokenCreateRequest setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the flag which indicates whether to emulate that the client ID alias is used
     * instead of the original numeric client ID when a new access token is created.
     *
     * <p>
     * This has an effect only on the value of the {@code aud} claim in a response from
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
     * endpoint</a>. When you access the UserInfo endpoint (which is expected to be
     * implemented using Authlete's {@code /api/auth/userinfo} API and {@code
     * /api/auth/userinfo/issue} API) with an access token which has been created using
     * Authlete's {@code /api/auth/token/create} API with this property ({@code
     * clientIdAliasUsed}) true, the client ID alias is used as the value of the {@code
     * aud} claim in a response from the UserInfo endpoint.
     * </p>
     *
     * <p>
     * Note that if a client ID alias is not assigned to the client when Authlete's
     * {@code /api/auth/token/create} API is called, this property ({@code
     * clientIdAliasUsed}) has no effect (it is always regarded as {@code false}).
     * </p>
     *
     * @return
     *         {@code true} to emulate that the client ID alias is used when a new
     *         access token is created.
     *
     * @since 2.3
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether to emulate that the client ID alias is used
     * instead of the original numeric client ID when a new access token is created.
     *
     * <p>
     * This has an effect only on the value of the {@code aud} claim in a response from
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
     * endpoint</a>. When you access the UserInfo endpoint (which is expected to be
     * implemented using Authlete's {@code /api/auth/userinfo} API and {@code
     * /api/auth/userinfo/issue} API) with an access token which has been created using
     * Authlete's {@code /api/auth/token/create} API with this property ({@code
     * clientIdAliasUsed}) true, the client ID alias is used as the value of the {@code
     * aud} claim in a response from the UserInfo endpoint.
     * </p>
     *
     * <p>
     * Note that if a client ID alias is not assigned to the client when Authlete's
     * {@code /api/auth/token/create} API is called, this property ({@code
     * clientIdAliasUsed}) has no effect (it is always regarded as {@code false}).
     * </p>
     *
     * @param used
     *         {@code true} to emulate that the client ID alias is used when a new
     *         access token is created.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.3
     */
    public TokenCreateRequest setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;

        return this;
    }


    /**
     * Get the access token.
     *
     * <p>
     * When this method returns a non-null value, the implementation of
     * {@code /api/auth/token/create} uses the value instead of generating
     * a new one. See the description of {@link #setAccessToken(String)}
     * for details.
     * </p>
     *
     * @return
     *         The value of the access token. In normal cases, {@code null}
     *         is returned.
     *
     * @see #setAccessToken(String)
     *
     * @since 2.6
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token.
     *
     * <p>
     * The {@code /api/auth/token/create} API generates an access token.
     * Therefore, callers of the API do not have to specify values of
     * newly created access tokens. However, in some cases, for example,
     * if you want to migrate existing access tokens from an old system
     * to Authlete, you may want to specify values of access tokens.
     * In such a case, you can specify the value of a newly created
     * access token by passing a non-null value as the value of
     * {@code accessToken} request parameter. The implementation of the
     * {@code /api/auth/token/create} uses the value of the {@code
     * accessToken} request parameter instead of generating a new value
     * when the request parameter holds a non-null value.
     * </p>
     *
     * <p>
     * Note that if the hash value of the specified access token already
     * exists in Authlete's database, the access token cannot be inserted
     * and the {@code /api/auth/token/create} API will report an error.
     * </p>
     *
     * @param accessToken
     *         The value of the access token. If a non-null value is
     *         specified, the implementation of {@code /api/auth/token/create}
     *         API will use the value instead of generating a new one.
     *
     *         Because Authlete does not store the value of the access
     *         token into its database (Authlete stores the hash value
     *         of the access token only), any value is accepted as the
     *         value of this {@code accessToken} request parameter.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.6
     */
    public TokenCreateRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the refresh token.
     *
     * <p>
     * When this method returns a non-null value, the implementation of
     * {@code /api/auth/token/create} uses the value instead of generating
     * a new one. See the description of {@link #setRefreshToken(String)}
     * for details.
     * </p>
     *
     * @return
     *         The value of the refresh token. In normal cases, {@code null}
     *         is returned.
     *
     * @see #setRefreshToken(String)
     *
     * @since 2.6
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the refresh token.
     *
     * <p>
     * The {@code /api/auth/token/create} API may generate a refresh token.
     * Therefore, callers of the API do not have to specify values of
     * newly created refresh tokens. However, in some cases, for example,
     * if you want to migrate existing refresh tokens from an old system
     * to Authlete, you may want to specify values of refresh tokens.
     * In such a case, you can specify the value of a newly created
     * refresh token by passing a non-null value as the value of
     * {@code refreshToken} request parameter. The implementation of the
     * {@code /api/auth/token/create} uses the value of the {@code
     * refreshToken} request parameter instead of generating a new value
     * when the request parameter holds a non-null value.
     * </p>
     *
     * <p>
     * Note that if the hash value of the specified refresh token already
     * exists in Authlete's database, the refresh token cannot be inserted
     * and the {@code /api/auth/token/create} API will report an error.
     * </p>
     *
     * @param refreshToken
     *         The value of the refresh token. If a non-null value is
     *         specified, the implementation of {@code /api/auth/token/create}
     *         API will use the value instead of generating a new one.
     *
     *         Because Authlete does not store the value of the refresh
     *         token into its database (Authlete stores the hash value
     *         of the refresh token only), any value is accepted as the
     *         value of this {@code refreshToken} request parameter.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.6
     */
    public TokenCreateRequest setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;

        return this;
    }


    /**
     * Get whether the access token expires or not. By default, all access tokens
     * expire after a period of time determined by their service. If this request
     * parameter is {@code true} then the access token will not automatically
     * expire and must be revoked or deleted manually at the service.
     *
     * <p>
     * If this request parameter is {@code true}, the {@code accessTokenDuration}
     * request parameter is ignored.
     * </p>
     *
     * @return
     *         {@code false} if the access token expires (default).
     *         {@code true} if the access token does not expire.
     *
     * @since 2.30
     */
    public boolean isAccessTokenPersistent()
    {
        return accessTokenPersistent;
    }


    /**
     * Set whether the access token expires or not. By default, all access tokens
     * expire after a period of time determined by their service. If this request
     * parameter is {@code true} then the access token will not automatically
     * expire and must be revoked or deleted manually at the service.
     *
     * <p>
     * If this request parameter is {@code true}, the {@code accessTokenDuration}
     * request parameter is ignored.
     * </p>
     *
     * @param persistent
     *         {@code false} to make the access token expire (default).
     *         {@code true} to make the access token be persistent.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.30
     */
    public TokenCreateRequest setAccessTokenPersistent(boolean persistent)
    {
        this.accessTokenPersistent = persistent;

        return this;
    }


    /**
     * Get the thumbprint of the MTLS certificate bound to this token. If this field
     * is set, a certificate with the corresponding value MUST be presented with the
     * access token when it is used by a client.
     *
     * @return
     *         The SHA256 certificate thumbprint, base64url encoded.
     *
     * @since 2.72
     */
    public String getCertificateThumbprint()
    {
        return certificateThumbprint;
    }


    /**
     * Set the thumbprint of the MTLS certificate bound to this token. If this field
     * is set, a certificate with the corresponding value MUST be presented with the
     * access token when it is used by a client.
     *
     * @param certificateThumbprint
     *            The SHA256 certificate thumbprint, base64url encoded.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.72
     */
    public TokenCreateRequest setCertificateThumbprint(String certificateThumbprint)
    {
        this.certificateThumbprint = certificateThumbprint;

        return this;
    }


    /**
     * Get the thumbprint of the public key used for DPoP presentation of this token.
     * If this field is set, a DPoP proof signed with the corresponding private key
     * MUST be presented with the access token when it is used by a client. Additionally,
     * the token's {@code token_type} will be set to 'DPoP'.
     *
     * @return
     *         The JWK public key thumbprint.
     *
     * @since 2.72
     */
    public String getDpopKeyThumbprint()
    {
        return dpopKeyThumbprint;
    }


    /**
     * Set the thumbprint of the public key used for DPoP presentation of this token.
     * If this field is set, a DPoP proof signed with the corresponding private key
     * MUST be presented with the access token when it is used by a client. Additionally,
     * the token's {@code token_type} will be set to 'DPoP'.
     *
     * @param dpopKeyThumbprint
     *            The JWK public key thumbprint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.72
     */
    public TokenCreateRequest setDpopKeyThumbprint(String dpopKeyThumbprint)
    {
        this.dpopKeyThumbprint = dpopKeyThumbprint;

        return this;
    }


    /**
     * Get the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @return
     *         Authorization details.
     *
     * @since 2.99
     */
    public AuthzDetails getAuthorizationDetails()
    {
        return authorizationDetails;
    }


    /**
     * Set the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @param authorizationDetails
     *         Authorization details.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.99
     */
    public TokenCreateRequest setAuthorizationDetails(AuthzDetails authorizationDetails)
    {
        this.authorizationDetails = authorizationDetails;

        return this;
    }


    /**
     * Get the resources. This represents the value of one or more
     * {@code "resource"} request parameters which is defined in
     * <i>"RFC8707 Resource Indicators for OAuth 2.0"</i>.
     *
     * @return
     *         Array of resource URIs.
     *
     * @since 2.99
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Get the resources. This represents the value of one or more
     * {@code "resource"} request parameters which is defined in
     * <i>"RFC8707 Resource Indicators for OAuth 2.0"</i>.
     *
     * @param resources
     *         Array of resource URIs.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.99
     */
    public void setResources(URI[] resources)
    {
        this.resources = resources;
    }
}
