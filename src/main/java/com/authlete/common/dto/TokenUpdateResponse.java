/*
 * Copyright (C) 2016-2023 Authlete, Inc.
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


import com.authlete.common.util.Utils;


/**
 * Response from Authlete's {@code /auth/token/update} API.
 *
 * <p>
 * Authlete's {@code /auth/token/update} API returns JSON which can
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
 * this happens when the {@code accessToken} request parameter is not
 * specified.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#FORBIDDEN FORBIDDEN}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} parameter is {@code "FORBIDDEN"},
 * it means that the request from the caller is not allowed. For example,
 * this happens when the access token identified by the {@code accessToken}
 * request parameter does not belong to the service identified by the API
 * key used for the API call.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#NOT_FOUND NOT_FOUND}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} parameter is {@code "NOT_FOUND"},
 * it means that the specified access token does not exist.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} parameter is {@code "OK"}, it means
 * that the access token was updated successfully.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.34
 */
public class TokenUpdateResponse extends ApiResponse
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
         * happens when the {@code accessToken} request parameter was
         * missing.
         */
        BAD_REQUEST,

        /**
         * The request from the caller was not allowed. For example,
         * this happens when the access token identified by the {@code
         * accessToken} request parameter does not belong to the
         * service identified by the API key used for the API call.
         */
        FORBIDDEN,

        /**
         * The specified access token does not exist.
         */
        NOT_FOUND,

        /**
         * The access token was updated successfully.
         */
        OK
    }


    private static final long serialVersionUID = 5L;
    private static final String SUMMARY_FORMAT =
            "action=%s, accessToken=%s, accessTokenExpiresAt=%d, scopes=%s, tokenType=%s";


    /**
     * @since Authlete 1.1
     */
    private Action action;

    /**
     * @since Authlete 1.1
     */
    private String accessToken;

    /**
     * @since Authlete 2.0.0
     */
    private String tokenType;

    /**
     * @since Authlete 1.1
     */
    private long accessTokenExpiresAt;

    private long refreshTokenExpiresAt;
    /**
     * @since Authlete 1.1
     */
    private String[] scopes;

    /**
     * @since Authlete 1.1
     */
    private Property[] properties;

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
     * See the {@link TokenUpdateResponse description} of this class
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
     * See the {@link TokenUpdateResponse description} of this class
     * for details.
     *
     * @param action
     *         The code indicating how the response should be interpreted.
     *
     * @return
     *         {@code this} object.
     */
    public TokenUpdateResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the access token which has been specified by {@link TokenUpdateRequest}.
     *
     * @return
     *         Access token.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token which has been specified by {@link TokenUpdateRequest}.
     *
     * @param accessToken
     *         Access token.
     *
     * @return
     *         {@code this} object.
     */
    public TokenUpdateResponse setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the date at which the access token will expire.
     *
     * @return
     *         The expiration date in milliseconds since the Unix epoch (1970-01-01).
     */
    public long getAccessTokenExpiresAt()
    {
        return accessTokenExpiresAt;
    }


    /**
     * Set the date at which the access token will expire.
     *
     * @param expiresAt
     *         The expiration date in milliseconds since the Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public TokenUpdateResponse setAccessTokenExpiresAt(long expiresAt)
    {
        this.accessTokenExpiresAt = expiresAt;

        return this;
    }


    /**
     * Get the date at which the refresh token will expire.
     *
     * @return
     *         The expiration date in milliseconds since the Unix epoch (1970-01-01).
     *
     * @since 3.84
     */
    public long getRefreshTokenExpiresAt()
    {
        return refreshTokenExpiresAt;
    }


    /**
     * Set the date at which the refresh token will expire.
     *
     * @param expiresAt
     *         The expiration date in milliseconds since the Unix epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.84
     */
    public TokenUpdateResponse setRefreshTokenExpiresAt(long expiresAt)
    {
        this.refreshTokenExpiresAt = expiresAt;

        return this;
    }


    /**
     * Get the scopes associated with the access token.
     *
     * @return
     *         Scopes. May be {@code null}.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes associated with the access token.
     *
     * @param scopes
     *         Scopes.
     *
     * @return
     *         {@code this} object.
     */
    public TokenUpdateResponse setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the properties associated with the access token.
     *
     * @return
     *         Properties. May be {@code null}.
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
     */
    public TokenUpdateResponse setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the token type associated with the access token.
     *
     * @return
     *         Token type. (example: Bearer)
     *
     * @since 2.31
     */
    public String getTokenType()
    {
        return tokenType;
    }


    /**
     * Set the token type associated with the access token.
     *
     * @param tokenType
     *         Token type. (example: Bearer)
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.31
     */
    public TokenUpdateResponse setTokenType(String tokenType)
    {
        this.tokenType = tokenType;

        return this;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT, action, accessToken,
                accessTokenExpiresAt, Utils.join(scopes, " "), tokenType);
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
    public TokenUpdateResponse setAuthorizationDetails(AuthzDetails authorizationDetails)
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
     *         {@code true} to indicate that the access token is for an
     *         external attachment.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.16
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#name-external-attachments"
     *      >OpenID Connect for Identity Assurance 1.0, External Attachments</a>
     */
    public TokenUpdateResponse setForExternalAttachment(boolean forExternalAttachment)
    {
        this.forExternalAttachment = forExternalAttachment;

        return this;
    }


    /**
     * Get the token identifier.
     *
     * @return
     *         The token identifier string.
     *
     * @since 3.23
     * @since Authlete API 3.0
     */
    public String getTokenId()
    {
        return tokenId;
    }


    /**
     * Set the token identifier.
     *
     * @param tokenId
     *         The token identifier string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.23
     * @since Authlete API 3.0
     */
    public TokenUpdateResponse setTokenId(String tokenId)
    {
        this.tokenId = tokenId;

        return this;
    }
}
