/*
 * Copyright (C) 2015-2022 Authlete, Inc.
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


import com.authlete.common.types.GrantType;
import com.authlete.common.util.Utils;


/**
 * Response from Authlete's {@code /auth/token/create} API.
 *
 * <p>
 * Authlete's {@code /auth/token/create} API returns JSON which can
 * be mapped to this class. The first step that a caller should take is
 * to retrieve the value of {@code action} parameter from the response.
 * The list below shows possible values of {@code action} parameter and
 * their meanings.
 * </p>
 *
 * <blockquote>
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} parameter is {@code "INTERNAL_SERVER_ERROR"},
 * it means that an error occurred on Authlete side.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} parameter is {@code "BAD_REQUEST"},
 * it means that the request from the caller was wrong. For example,
 * this happens when the {@code grantType} request parameter is not
 * specified.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#FORBIDDEN FORBIDDEN}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} parameter is {@code "FORBIDDEN"},
 * it means that the request from the caller is not allowed. For example,
 * this happens when the client application identified by the {@code
 * clientId} request parameter does not belong to the service identified
 * by the API key used for the API call.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} parameter is {@code "OK"}, it means
 * that everything was processed successfully and an access token and
 * optionally a refresh token were issued.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * <p>
 * So, in short, when the value of {@code action} parameter in the response
 * from Authlete's {@code /auth/token/create} API is {@code "OK"}, you can
 * find a new access token and optionally a new refresh token in {@code
 * accessToken} parameter and {@code refreshToken} parameter.
 * </p>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.13
 */
public class TokenCreateResponse extends ApiResponse
{
    /**
     * The code indicating how the response should be interpreted.
     */
    public enum Action
    {
        /**
         * An error occurred on Authlete side.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The request from the caller was wrong. For example, this
         * happens when the {@code grantType} request parameter was
         * missing.
         */
        BAD_REQUEST,

        /**
         * The request from the caller was not allowed. For example,
         * this happens when the client application identified by the
         * {@code clientId} request parameter does not belong to the
         * service identified by the API key used for the API call.
         */
        FORBIDDEN,

        /**
         * An access token and optionally a refresh token were issued
         * successfully.
         */
        OK
    }


    private static final long serialVersionUID = 7L;
    private static final String SUMMARY_FORMAT
        = "action=%s, grantType=%s, clientId=%d, subject=%s, scopes=%s, "
        + "accessToken=%s, tokenType=%s, expiresIn=%d, expiresAt=%d, refreshToken=%s";


    /**
     * @since Authlete 0.1
     */
    private Action action;
    /**
     * @since Authlete 0.1
     */
    private GrantType grantType;
    /**
     * @since Authlete 0.1
     */
    private long clientId;
    /**
     * @since Authlete 0.1
     */
    private String subject;
    /**
     * @since Authlete 0.1
     */
    private String[] scopes;
    /**
     * @since Authlete 0.1
     */
    private String accessToken;
    /**
     * @since Authlete 0.1
     */
    private String tokenType;
    /**
     * @since Authlete 0.1
     */
    private long expiresIn;
    /**
     * @since Authlete 0.1
     */
    private long expiresAt;
    /**
     * @since Authlete 0.1
     */
    private String refreshToken;
    /**
     * @since Authlete 0.1
     */
    private Property[] properties;
    /**
     * @since Authlete 3.0.0
     */
    private String jwtAccessToken;
    /**
     * @since Authlete 2.2.14
     */
    private AuthzDetails authorizationDetails;
    /**
     * @since Authlete 3.0.0
     */
    private boolean forExternalAttachment;
    /**
     * @since Authlete 3.0.0
     */
    private String tokenId;


    /**
     * Get the code indicating how the response should be interpreted.
     * See the {@link TokenCreateResponse description} of this class
     * for details.
     *
     * @return
     *         The code indicating how the response should be interpreted.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the code indicating how the response should be interpreted.
     * See the {@link TokenCreateResponse description} of this class
     * for details.
     *
     * @param action
     *         The code indicating how the response should be interpreted.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the {@link GrantType grant type} for the newly issued
     * access token.
     *
     * @return
     *         Grant type.
     */
    public GrantType getGrantType()
    {
        return grantType;
    }


    /**
     * Set the {@link GrantType grant type} for the newly issued
     * access token.
     *
     * @param grantType
     *         Grant type.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setGrantType(GrantType grantType)
    {
        this.grantType = grantType;

        return this;
    }


    /**
     * Get the client ID associated with the newly issued access token.
     *
     * @return
     *         Client ID.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID associated with the newly issued access token.
     *
     * @param clientId
     *         Client ID.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the subject (= unique identifier) of the user associated
     * with the newly issued access token.  This value is {@code null}
     * when the {@link GrantType grant type} obtained by {@link
     * #getGrantType()} is {@link GrantType#CLIENT_CREDENTIALS
     * CLIENT_CREDENTIALS}.
     *
     * @return
     *         The subject of the user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the user associated
     * with the newly issued access token.
     *
     * @param subject
     *         The subject of the user.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the scopes associated with the newly issued access token.
     *
     * @return
     *         Scopes. May be {@code null}.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes associated with the newly issued access token.
     *
     * @param scopes
     *         Scopes.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the newly issued access token.
     *
     * @return
     *         Access token.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the newly issued access token.
     *
     * @param accessToken
     *         Access token.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the token type of the access token. For example, {@code "Bearer"}.
     *
     * @return
     *         Token type.
     */
    public String getTokenType()
    {
        return tokenType;
    }


    /**
     * Set the token type of the access token. For example, {@code "Bearer"}.
     *
     * @param tokenType
     *         Token type.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setTokenType(String tokenType)
    {
        this.tokenType = tokenType;

        return this;
    }


    /**
     * Get the duration of the newly issued access token in seconds.
     *
     * @return
     *         The duration of the access token.
     */
    public long getExpiresIn()
    {
        return expiresIn;
    }


    /**
     * Set the duration of the newly issued access token in seconds.
     *
     * @param expiresIn
     *         The duration of the access token.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setExpiresIn(long expiresIn)
    {
        this.expiresIn = expiresIn;

        return this;
    }


    /**
     * Get the date at which the newly issued access token will expire.
     * The value is expressed in milliseconds since Unix epoch (1970-01-01).
     *
     * @return
     *         The date at which the newly issued access token will expire.
     */
    public long getExpiresAt()
    {
        return expiresAt;
    }


    /**
     * Set the date at which the newly issued access token will expire.
     *
     * @param expiresAt
     *         The date at which the newly issued access token will expire.
     *         The value is required to be expressed in milliseconds since
     *         Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setExpiresAt(long expiresAt)
    {
        this.expiresAt = expiresAt;

        return this;
    }


    /**
     * Get the newly issued refresh token.
     *
     * @return
     *         Refresh token. This is {@code null} when the grant type is
     *         either {@link GrantType#IMPLICIT IMPLICIT} or {@link
     *         GrantType#CLIENT_CREDENTIALS CLIENT_CREDENTIALS}.
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the newly issued refresh token.
     *
     * @param refreshToken
     *         Refresh token.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateResponse setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;

        return this;
    }


    /**
     * Get the properties associated with the access token.
     *
     * @return
     *         Properties.
     *
     * @since 1.34
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set the properties associated with the access token.
     *
     * @param properties
     *         Properties.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.34
     */
    public TokenCreateResponse setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
            action, grantType, clientId, subject, Utils.join(scopes, " "),
            accessToken, tokenType, expiresIn, expiresAt, refreshToken);
    }


    /**
     * Get the newly issued access token in JWT format.
     *
     * <p>
     * If the authorization server is configured to issue JWT-based access
     * tokens (= if {@link Service#getAccessTokenSignAlg()} returns a non-null
     * value), a JWT-based access token is issued along with the original
     * random-string one.
     * </p>
     *
     * <p>
     * Regarding the detailed format of the JWT-based access token, see the
     * description of the {@link Service} class.
     * </p>
     *
     * @return
     *         The newly issued access token in JWT format. If the service is
     *         not configured to issue JWT-based access tokens, this method
     *         always returns null.
     *
     * @see #getAccessToken()
     *
     * @since 3.11
     */
    public String getJwtAccessToken()
    {
        return jwtAccessToken;
    }


    /**
     * Set the newly issued access token in JWT format.
     *
     * @param jwtAccessToken
     *         The newly issued access token in JWT format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.11
     */
    public TokenCreateResponse setJwtAccessToken(String jwtAccessToken)
    {
        this.jwtAccessToken = jwtAccessToken;

        return this;
    }


    /**
     * Get the authorization details associated with the access token.
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
     * Set the authorization details associated with the access token.
     *
     * @param authorizationDetails
     *         Authorization details.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.99
     */
    public TokenCreateResponse setAuthorizationDetails(AuthzDetails authorizationDetails)
    {
        this.authorizationDetails = authorizationDetails;

        return this;
    }


    /**
     * Get the flag which indicates whether the access token is for an external
     * attachment.
     *
     * @return
     *         {@code true} if the access token is for an external attachment.
     *
     * @since 3.16
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#name-external-attachments"
     *      >OpenID Connect for Identity Assurance 1.0, External Attachments</a>
     */
    public boolean isForExternalAttachment()
    {
        return forExternalAttachment;
    }


    /**
     * Set the flag which indicates whether the access token is for an external
     * attachment.
     *
     * @param forExternalAttachment
     *            {@code true} to indicate that the access token is for an
     *            external attachment.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.16
     * @since Authlete API 3.0
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#name-external-attachments"
     *      >OpenID Connect for Identity Assurance 1.0, External Attachments</a>
     */
    public TokenCreateResponse setForExternalAttachment(boolean forExternalAttachment)
    {
        this.forExternalAttachment = forExternalAttachment;

        return this;
    }


    /**
     * Get the unique token identifier.
     *
     * @return
     *         The token identifier as a string.
     *
     * @since 3.23
     * @since Authlete API 3.0
     */
    public String getTokenId()
    {
        return tokenId;
    }


    /**
     * Set the unique token identifier.
     *
     * @param tokenId
     *         The token identifier as a string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.23
     * @since Authlete API 3.0
     */
    public TokenCreateResponse setTokenId(String tokenId)
    {
        this.tokenId = tokenId;

        return this;
    }
}
