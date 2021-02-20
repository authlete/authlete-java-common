/*
 * Copyright (C) 2014-2021 Authlete, Inc.
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


import java.net.URI;
import com.authlete.common.util.Utils;


/**
 * Response from Authlete's {@code /auth/token/issue} endpoint.
 *
 * <p>
 * Authlete's {@code /auth/token/issue} endpoint returns JSON which can
 * be mapped to this class. The service implementation should retrieve
 * the value of {@code "action"} from the response and take the following
 * steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the service implementation was wrong or
 * that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from the viewpoint of the client application, it is an
 * error on the server side. Therefore, the service implementation should
 * generate a response to the client application with the HTTP status of
 * {@code "500 Internal Server Error"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that
 * Authlete's {@code /auth/token/issue} API successfully generated
 * an access token.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the client application
 * must be {@code "200 OK"} and the content type must be
 * {@code "application/json"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which contains
 * an access token, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 * </dl>
 *
 * @author Takahiko Kawasaki
 */
public class TokenIssueResponse extends ApiResponse
{
    private static final long serialVersionUID = 8L;


    /**
     * The next action that the service implementation should take.
     */
    public enum Action
    {
        /**
         * The request from the service implementation was wrong or
         * an error occurred in Authlete. The service implementation
         * should return {@code "500 Internal Server Error"} to the
         * client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The token request from the client was valid. The service
         * implementation should return {@code "200 OK"} to the client
         * application with an access token.
         */
        OK
    }


    private static final String SUMMARY_FORMAT
        = "action=%s, responseContent=%s, "
        + "accessToken=%s, accessTokenExpiresAt=%d, accessTokenDuration=%d, "
        + "refreshToken=%s, refreshTokenExpiresAt=%d, refreshTokenDuration=%d, "
        + "clientId=%d, clientIdAlias=%s, clientIdAliasUsed=%s, subject=%s, "
        + "scopes=%s, properties=%s, jwtAccessToken=%s";


    /**
     * The next action that the service implementation should take.
     */
    private Action action;


    private String responseContent;
    private String accessToken;
    private long accessTokenExpiresAt;
    private long accessTokenDuration;
    private String refreshToken;
    private long refreshTokenExpiresAt;
    private long refreshTokenDuration;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String subject;
    private String[] scopes;
    private Property[] properties;
    private String jwtAccessToken;
    private URI[] accessTokenResources;
    private AuthzDetails authorizationDetails;
    private Pair[] serviceAttributes;
    private Pair[] clientAttributes;


    /**
     * Get the next action that the service implementation should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the service implementation should take.
     */
    public void setAction(Action action)
    {
        this.action = action;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response returned to the client application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as the entity body
     * of the response returned to the client application.
     */
    public void setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT, action, responseContent,
                accessToken, accessTokenExpiresAt, accessTokenDuration,
                refreshToken, refreshTokenExpiresAt, refreshTokenDuration,
                clientId, clientIdAlias, clientIdAliasUsed, subject,
                Utils.join(scopes, " "), Utils.stringifyProperties(properties),
                jwtAccessToken);
    }


    /**
     * Get the newly issued access token. This method returns a non-null
     * value only when {@link #getAction()} returns {@link Action#OK}.
     *
     * <p>
     * If the service is configured to issue JWT-based access tokens,
     * a JWT-based access token is issued additionally. In the case,
     * {@link #getJwtAccessToken()} returns the JWT-based access token.
     * </p>
     *
     * @return
     *         The newly issued access token.
     *
     * @see #getJwtAccessToken()
     *
     * @since 1.34
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the newly issued access token.
     *
     * @param accessToken
     *         The newly issued access token.
     *
     * @since 1.34
     */
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }


    /**
     * Get the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the access token will expire.
     *
     * @return
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the access token will expire.
     *
     * @since 1.34
     */
    public long getAccessTokenExpiresAt()
    {
        return accessTokenExpiresAt;
    }


    /**
     * Set the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the access token will expire.
     *
     * @param expiresAt
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the access token will expire.
     *
     * @since 1.34
     */
    public void setAccessTokenExpiresAt(long expiresAt)
    {
        this.accessTokenExpiresAt = expiresAt;
    }


    /**
     * Get the duration of the access token in seconds.
     *
     * @return
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public long getAccessTokenDuration()
    {
        return accessTokenDuration;
    }


    /**
     * Set the duration of the access token in seconds.
     *
     * @param duration
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public void setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;
    }


    /**
     * Get the refresh token. This method returns a non-null value only when
     * {@link #getAction()} returns {@link Action#OK} and the service supports
     * the <a href="https://tools.ietf.org/html/rfc6749#section-6">refresh token
     * flow</a>.
     *
     * <p>
     * If "Refresh Token Continuous Use" configuration parameter is NO
     * (= `refreshTokenKept=false`), a new refresh token is issued and the
     * old refresh token used in the refresh token flow is invalidated.
     * On the contrary, if the configuration parameter is YES, the refresh
     * token itself is not refreshed.
     * </p>
     *
     * @return
     *         The refresh token.
     *
     * @since 1.34
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the refresh token.
     *
     * @param refreshToken
     *         The refresh token.
     *
     * @since 1.34
     */
    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }


    /**
     * Get the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the refresh token will expire.
     *
     * @return
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the refresh token will expire.
     *         If the refresh token is null, this method returns 0.
     *
     * @since 1.34
     */
    public long getRefreshTokenExpiresAt()
    {
        return refreshTokenExpiresAt;
    }


    /**
     * Set the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the refresh token will expire.
     *
     * @param expiresAt
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the refresh token will expire.
     *         If the refresh token is null, this method returns 0.
     *
     * @since 1.34
     */
    public void setRefreshTokenExpiresAt(long expiresAt)
    {
        this.refreshTokenExpiresAt = expiresAt;
    }


    /**
     * Get the duration of the refresh token in seconds.
     *
     * @return
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public long getRefreshTokenDuration()
    {
        return refreshTokenDuration;
    }


    /**
     * Set the duration of the refresh token in seconds.
     *
     * @param duration
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public void setRefreshTokenDuration(long duration)
    {
        this.refreshTokenDuration = duration;
    }


    /**
     * Get the client ID.
     *
     * @since 2.8
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID.
     *
     * @since 2.8
     */
    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }


    /**
     * Get the client ID alias.
     *
     * <p>
     * If the client did not have an alias, this method returns
     * {@code null}.
     * </p>
     *
     * @return
     *         The client ID alias.
     *
     * @since 2.8
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the client ID alias.
     *
     * @param alias
     *         The client ID alias.
     *
     * @since 2.8
     */
    public void setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;
    }


    /**
     * Get the flag which indicates whether the client ID alias was used
     * when the token request was made.
     *
     * @return
     *         {@code true} if the client ID alias was used when the token
     *         request was made.
     *
     * @since 2.8
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether the client ID alias was used
     * when the token request was made.
     *
     * @param used
     *         {@code true} if the client ID alias was used when the token
     *         request was made.
     *
     * @since 2.8
     */
    public void setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;
    }


    /**
     * Get the subject (= resource owner's ID) of the access token.
     *
     * @since 2.8
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= resource owner's ID).
     *
     * @since 2.8
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    /**
     * Get the scopes covered by the access token.
     *
     * @since 2.8
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes covered by the access token.
     *
     * @since 2.8
     */
    public void setScopes(String[] scopes)
    {
        this.scopes = scopes;
    }


    /**
     * Get the extra properties associated with the access token.
     * This method returns {@code null} when no extra property is
     * associated with the issued access token.
     *
     * @return
     *         Extra properties associated with the issued access token.
     *
     * @since 2.8
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set the extra properties associated with the access token.
     *
     * @param properties
     *         Extra properties.
     *
     * @since 2.8
     */
    public void setProperties(Property[] properties)
    {
        this.properties = properties;
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
     * @since 2.37
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
     * @since 2.37
     */
    public void setJwtAccessToken(String jwtAccessToken)
    {
        this.jwtAccessToken = jwtAccessToken;
    }


    /**
     * Get the target resources of the access token.
     *
     * <p>
     * See "Resource Indicators for OAuth 2.0" for details.
     * </p>
     *
     * @return
     *         The target resources of the access token.
     *
     * @since 2.62
     */
    public URI[] getAccessTokenResources()
    {
        return accessTokenResources;
    }


    /**
     * Set the target resources of the access token.
     *
     * <p>
     * See "Resource Indicators for OAuth 2.0" for details.
     * </p>
     *
     * @param resources
     *         The target resources of the access token.
     *
     * @since 2.62
     */
    public void setAccessTokenResources(URI[] resources)
    {
        this.accessTokenResources = resources;
    }


    /**
     * Get the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @return
     *         Authorization details.
     *
     * @since 2.56
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
     * @param details
     *         Authorization details.
     *
     * @since 2.56
     */
    public void setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;
    }


    /**
     * Get the attributes of the service that the client application belongs to.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @return
     *         The attributes of the service.
     *
     * @since 2.88
     */
    public Pair[] getServiceAttributes()
    {
        return serviceAttributes;
    }


    /**
     * Set the attributes of the service that the client application belongs to.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         The attributes of the service.
     *
     * @since 2.88
     */
    public void setServiceAttributes(Pair[] attributes)
    {
        this.serviceAttributes = attributes;
    }


    /**
     * Get the attributes of the client.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @return
     *         The attributes of the client.
     *
     * @since 2.88
     */
    public Pair[] getClientAttributes()
    {
        return clientAttributes;
    }


    /**
     * Set the attributes of the client.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         The attributes of the client.
     *
     * @since 2.88
     */
    public void setClientAttributes(Pair[] attributes)
    {
        this.clientAttributes = attributes;
    }
}
