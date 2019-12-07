/*
 * Copyright (C) 2014-2019 Authlete, Inc.
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
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.GrantType;
import com.authlete.common.util.Utils;


/**
 * Response from Authlete's {@code /auth/token} API.
 *
 * <p>
 * Authlete's {@code /auth/token} API returns JSON which can
 * be mapped to this class. The service implementation should retrieve the
 * value of {@code "action"} from the response and take the following steps
 * according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INVALID_CLIENT INVALID_CLIENT}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INVALID_CLIENT"}, it means
 * that authentication of the client failed. In this case, the HTTP status
 * of the response to the client application is either {@code "400 Bad
 * Request"} or {@code "401 Unauthorized"}. This requirement comes from
 * <a href="http://tools.ietf.org/html/rfc6749#section-5.2">RFC 6749, 5.2.
 * Error Response</a>. The description about {@code "invalid_client"} shown
 * below is an excerpt from RFC 6749.
 * </p>
 * <blockquote>
 *   <dl>
 *     <dt><code>invalid_client</code></dt>
 *     <dd>
 *       <p>
 *         Client authentication failed (e.g., unknown client, no client
 *         authentication included, or unsupported authentication method).
 *         The authorization server MAY return an HTTP 401 (Unauthorized)
 *         status code to indicate which HTTP authentication schemes are
 *         supported. If the client attempted to authenticate via the
 *         "Authorization" request header field, the authorization server
 *         MUST respond with an HTTP 401 (Unauthorized) status code and
 *         include the <a href="http://tools.ietf.org/html/rfc2616#section-14.47"
 *         >"WWW-Authenticate"</a> response header field matching the
 *         authentication scheme used by the client.
 *       </p>
 *     </dd>
 *   </dl>
 * </blockquote>
 * <p>
 * In either case, the JSON string returned by {@link #getResponseContent()}
 * can be used as the entity body of the response to the client application.
 * </p>
 *
 * <p>
 * The following illustrate the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(challenge)</i>
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the service implementation
 * ({@link AuthorizationIssueRequest}) was wrong or that an error occurred
 * in Authlete.
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
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "BAD_REQUEST"}, it means
 * that the request from the client application is invalid.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the client application
 * must be {@code "400 Bad Request"} and the content type must be
 * {@code "application/json"}.
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
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#PASSWORD PASSWORD}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "PASSWORD"}, it means that
 * the request from the client application is valid and {@code grant_type}
 * is {@code "password"}. That is, the flow is
 * <a href="http://tools.ietf.org/html/rfc6749#section-4.3">"Resource Owner
 * Password Credentials"</a>.
 * </p>
 *
 * <p>
 * In this case, {@link #getUsername()} returns the value of {@code "username"}
 * request parameter and {@link #getPassword()} returns the value of {@code
 * "password"} request parameter which were contained in the token request
 * from the client application. The service implementation must validate the
 * credentials of the resource owner (= end-user) and take either of the
 * actions below according to the validation result.
 * </p>
 *
 * <ol style="list-style-type: lower-alpha">
 * <li>
 *   <p>
 *     <b>When the credentials are valid</b>, call Authlete's {@code
 *     /auth/token/issue} API to generate an access token for the client
 *     application. The API requires {@code "ticket"} request parameter and
 *     {@code "subject"} request parameter.
 *     Use the value returned from {@link #getTicket()} method as the value
 *     for {@code "ticket"} parameter.
 *   </p>
 *   <br/>
 *   <p>
 *     The response from {@code /auth/token/issue} API ({@link
 *     TokenIssueResponse}) contains data (an access token and others)
 *     which should be returned to the client application. Use the data
 *     to generate a response to the client application.
 *   </p>
 *   <br/>
 * <li>
 *   <p>
 *     <b>When the credentials are invalid</b>, call Authlete's {@code
 *     /auth/token/fail} API with {@code reason=}{@link
 *     TokenFailRequest.Reason#INVALID_RESOURCE_OWNER_CREDENTIALS
 *     INVALID_RESOURCE_OWNER_CREDENTIALS} to generate an error response
 *     for the client application. The API requires {@code "ticket"}
 *     request parameter. Use the value returned from {@link #getTicket()}
 *     method as the value for {@code "ticket"} parameter.
 *   </p>
 *   <br/>
 *   <p>
 *     The response from {@code /auth/token/fail} API ({@link
 *     TokenFailResponse}) contains error information which should be
 *     returned to the client application. Use it to generate a response
 *     to the client application.
 *   </p>
 * </ol>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that
 * the request from the client application is valid and an access token,
 * and optionally an ID token, is ready to be issued.
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
 * an access token (and optionally an ID token), so it can be used as
 * the entity body of the response.
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
 * @see <a href="http://tools.ietf.org/html/rfc6749">RFC 6749, OAuth 2.0</a>
 *
 * @author Takahiko Kawasaki
 */
public class TokenResponse extends ApiResponse
{
    private static final long serialVersionUID = 9L;


    /**
     * The next action that the service implementation should take.
     */
    public enum Action
    {
        /**
         * Authentication of the client application failed. The service
         * implementation should return either {@code "400 Bad Request"}
         * or {@code "401 Unauthorized"} to the client application.
         */
        INVALID_CLIENT,

        /**
         * The request from the service was wrong or an error occurred
         * in Authlete. The service implementation should return {@code
         * "500 Internal Server Error"} to the client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The token request from the client was wrong. The service
         * implementation should return {@code "400 Bad Request"} to the
         * client application.
         */
        BAD_REQUEST,

        /**
         * The token request from the client application was valid and
         * the grant type is {@code "password"}. The service implementation
         * should validate the credentials of the resource owner and call
         * Authlete's {@code /auth/token/issue} API or {@code /auth/token/fail}
         * API according to the result of the validation.
         */
        PASSWORD,

        /**
         * The token request from the client was valid. The service
         * implementation should return {@code "200 OK"} to the client
         * application with an access token.
         */
        OK
    }


    private static final String SUMMARY_FORMAT
        = "action=%s, username=%s, password=%s, ticket=%s, responseContent=%s, "
        + "accessToken=%s, accessTokenExpiresAt=%d, accessTokenDuration=%d, "
        + "refreshToken=%s, refreshTokenExpiresAt=%d, refreshTokenDuration=%d, "
        + "idToken=%s, grantType=%s, "
        + "clientId=%d, clientIdAlias=%s, clientIdAliasUsed=%s, "
        + "subject=%s, scopes=%s, properties=%s, jwtAccessToken=%s, "
        + "clientAuthMethod=%s";


    private Action action;
    private String responseContent;
    private String username;
    private String password;
    private String ticket;
    private String accessToken;
    private long accessTokenExpiresAt;
    private long accessTokenDuration;
    private String refreshToken;
    private long refreshTokenExpiresAt;
    private long refreshTokenDuration;
    private String idToken;
    private GrantType grantType;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String subject;
    private String[] scopes;
    private Property[] properties;
    private String jwtAccessToken;
    private ClientAuthMethod clientAuthMethod;
    private URI[] resources;
    private URI[] accessTokenResources;
    private AuthzDetails authorizationDetails;


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
     * Get the value of {@code "username"} request parameter.
     *
     * <p>
     * This method returns a non-null value only when the value of
     * {@code "grant_type"} request parameter in the token request
     * is {@code "password"}.
     * </p>
     *
     * <p>
     * {@code getSubject()} method was renamed to {@code getUsername()}
     * on version 1.13.
     * </p>
     *
     * @since 1.13
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.3.2"
     *      >RFC 6749, 4.3.2. Access Token Request</a>
     */
    public String getUsername()
    {
        return username;
    }


    /**
     * Set the value of {@code "username"} request parameter.
     *
     * <p>
     * {@code setSubject(String}} was renamed to {@code setUsername(String)}
     * on version 1.13.
     * </p>
     *
     * @since 1.13
     */
    public void setUsername(String username)
    {
        this.username = username;
    }


    /**
     * Get the value of {@code "password"} request parameter.
     *
     * <p>
     * This method returns a non-null value only when the value of
     * {@code "grant_type"} request parameter in the token request
     * is {@code "password"}.
     * </p>
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.3.2"
     *      >RFC 6749, 4.3.2. Access Token Request</a>
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * Set the value of {@code "password"} request parameter.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }


    /**
     * Get the ticket issued from Authlete's {@code /auth/token} endpoint.
     * The value is to be used as the value of {@code "ticket"} request
     * parameter for {@code /auth/token/issue} API or {@code /auth/token/fail}
     * API.
     *
     * <p>
     * This method returns a non-null value only when {@code "action"} is
     * {@link Action#PASSWORD PASSWORD}.
     * </p>
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the ticket used for {@code /auth/token/issue} API or {@code
     * /auth/token/fail} API.
     */
    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
                action, username, password, ticket, responseContent,
                accessToken, accessTokenExpiresAt, accessTokenDuration,
                refreshToken, refreshTokenExpiresAt, refreshTokenDuration,
                idToken, grantType, clientId, clientIdAlias, clientIdAliasUsed,
                subject, Utils.join(scopes, " "),
                Utils.stringifyProperties(properties), jwtAccessToken,
                clientAuthMethod);
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
     * Get the newly issued refresh token. This method returns a non-null
     * value only when {@link #getAction()} returns {@link Action#OK} and
     * the service supports the <a href=
     * "https://tools.ietf.org/html/rfc6749#section-6">refresh token flow</a>.
     *
     * @return
     *         The newly issued refresh token.
     *
     * @since 1.34
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the newly issued refresh token.
     *
     * @param refreshToken
     *         The newly issued refresh token.
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
     * Get the ID token.
     *
     * <p>
     * An <a href="http://openid.net/specs/openid-connect-core-1_0.html#IDToken"
     * >ID token</a> is issued from a token endpoint when the <a href=
     * "https://tools.ietf.org/html/rfc6749#section-4.1">authorization code
     * flow</a> is used and <code>"openid"</code> is included in the scope list.
     * </p>
     *
     * @return
     *         ID token.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#CodeFlowAuth"
     *      >Authentication using the Authorization Code Flow</a>
     *
     * @since 1.34
     */
    public String getIdToken()
    {
        return idToken;
    }


    /**
     * Set the ID token.
     *
     * @param idToken
     *         ID token.
     *
     * @since 1.34
     */
    public void setIdToken(String idToken)
    {
        this.idToken = idToken;
    }


    /**
     * Get the grant type of the token request.
     *
     * @since 2.8
     */
    public GrantType getGrantType()
    {
        return grantType;
    }


    /**
     * Set the grant type of the token request.
     *
     * @param grantType
     *         Grant type of the token request.
     *
     * @since 2.8
     */
    public void setGrantType(GrantType grantType)
    {
        this.grantType = grantType;
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
     * Get the client ID alias when the token request was made.
     *
     * <p>
     * If the client did not have an alias, this method returns
     * {@code null}. Also, if the token request was invalid and
     * it failed to identify a client, this method returns
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
     * Set the client ID alias when the token request was made.
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
     * <p>
     * Even if an access token has been issued by the call of
     * {@code /api/auth/token} API, this method returns {@code null}
     * if the flow of the token request was <a href=
     * "http://tools.ietf.org/html/rfc6749#section-4.4">Client
     * Credentials Flow</a> ({@code grant_type=client_credentials})
     * because it means the access token is not associated with any
     * specific end-user.
     * </p>
     *
     * @since 2.8
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= resource owner's ID) of the access token.
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
     * Get the client authentication method that should be performed at the
     * token endpoint.
     *
     * <p>
     * If the client could not be identified by the information in the request,
     * this method returns {@code null}.
     * </p>
     *
     * @return
     *         The client authentication method that should be performed at
     *         the token endpoint.
     *
     * @since 2.50
     */
    public ClientAuthMethod getClientAuthMethod()
    {
        return clientAuthMethod;
    }


    /**
     * Set the client authentication method that should be performed at the
     * token endpoint.
     *
     * @param method
     *         The client authentication method that should be performed at
     *         the token endpoint.
     *
     * @since 2.50
     */
    public void setClientAuthMethod(ClientAuthMethod method)
    {
        this.clientAuthMethod = method;
    }


    /**
     * Get the resources specified by the {@code resource} request parameters
     * in the token request.
     *
     * <p>
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     * </p>
     *
     * @return
     *         Resources specified by the {@code resource} request parameters
     *         in the token request.
     *
     * @since 2.62
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Set the resources specified by the {@code resource} request parameters
     * in the token request.
     *
     * <p>
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     * </p>
     *
     * @param resources
     *         Resources specified by the {@code resource} request parameters
     *         in the token request.
     *
     * @since 2.62
     */
    public void setResources(URI[] resources)
    {
        this.resources = resources;
    }


    /**
     * Get the target resources of the access token being issued.
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
     * Set the target resources of the access token being issued.
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
     * <p>
     * When the {@code action} (= the value returned from {@link #getAction()}
     * is {@link Action#PASSWORD PASSWORD}, this method returns an array that
     * represents the {@code authorization_details} request parameter included
     * in the token request. In other successful cases, this method returns the
     * authorization details associated with the issued access token.
     * </p>
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
}
