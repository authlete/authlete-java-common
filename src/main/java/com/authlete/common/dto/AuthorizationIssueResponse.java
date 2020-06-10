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


/**
 * Response from Authlete's {@code /auth/authorization/issue} API.
 *
 * <p>
 * Authlete's {@code /auth/authorization/issue} API
 * returns JSON which can be mapped to this class. The service implementation
 * should retrieve the value of {@code "action"} from the response and
 * take the following steps according to the value.
 * </p>
 *
 * <dl>
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
 * that the ticket is no longer valid (deleted or expired) and that the
 * reason of the invalidity was probably due to the end-user's too-delayed
 * response to the authorization UI.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the client application should
 * be {@code "400 Bad Request"} and the content type should be {@code
 * "application/json"} although OAuth 2.0 specification does not mention the
 * format of the error response.
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
 * <dt><b>{@link Action#LOCATION LOCATION}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "LOCATION"}, it means that
 * the response to the client application should be {@code "302 Found"}
 * with {@code "Location"} header.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a redirect URI which contains
 * (1) an authorization code, an ID token and/or an access token (on
 * success) or (2) an error code (on failure), so it can be used as the
 * value of {@code "Location"} header.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 302 Found
 * Location: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#FORM FORM}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "FORM"}, it means that
 * the response to the client application should be {@code "200 OK"}
 * with an HTML which triggers redirection by JavaScript. This happens
 * when the authorization request from the client contains
 * {@code response_mode=form_post} request parameter.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns an HTML which satisfies the
 * requirements of {@code response_mode=form_post}, so it can be used
 * as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: text/html;charset=UTF-8
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dl>
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationIssueResponse extends ApiResponse
{
    private static final long serialVersionUID = 3L;


    /**
     * The next action that the service implementation should take.
     */
    public enum Action
    {
        /**
         * The request from the service implementation was wrong or
         * an error occurred in Authlete, so the service implementation
         * should return {@code "500 Internal Server Error"} to the
         * client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The ticket was no longer valid. The service implementation
         * should return {@code "400 Bad Request}" to the client application.
         */
        BAD_REQUEST,

        /**
         * The service implementation should return {@code "302 Found"}
         * to the client application with {@code "Location"} header.
         */
        LOCATION,

        /**
         * The service implementation should return {@code "200 OK"}
         * to the client application with an HTML which triggers
         * redirection.
         */
        FORM
    }


    private static final String SUMMARY_FORMAT
        = "action=%s, responseContent=%s, accessToken=%s, accessTokenExpiresAt=%d, "
        + "accessTokenDuration=%d, idToken=%s, authorizationCode=%s, jwtAccessToken=%s";


    private Action action;
    private String responseContent;
    private String accessToken;
    private long accessTokenExpiresAt;
    private long accessTokenDuration;
    private String idToken;
    private String authorizationCode;
    private String jwtAccessToken;


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
    public void setResponseContent(String content)
    {
        this.responseContent = content;
    }


    /**
     * Get the access token. An access token is issued when the
     * {@code response_type} request parameter of the authorization
     * request includes {@code token}.
     *
     * <p>
     * If the service is configured to issue JWT-based access tokens,
     * a JWT-based access token is issued additionally. In the case,
     * {@link #getJwtAccessToken()} returns the JWT-based access token.
     * </p>
     *
     * @return
     *         The newly issued access token. If an access token is
     *         not issued, this method returns {@code null}.
     *
     * @since 1.34
     *
     * @see #getJwtAccessToken()
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token.
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
     * Get the date in milliseconds since the Unix epoch at which
     * the access token will expire.
     *
     * @return
     *         The date at which the access token will expire.
     *         If an access token is not issued, this method
     *         returns 0.
     *
     * @since 1.34
     */
    public long getAccessTokenExpiresAt()
    {
        return accessTokenExpiresAt;
    }


    /**
     * Set the date in milliseconds since the Unix epoch at which
     * the access token will expire.
     *
     * @param expiresAt
     *         The date at which the access token will expire.
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
     *         The duration of the access token in seconds.
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
     *         The duration of the access token in seconds.
     *
     * @since 1.34
     */
    public void setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;
    }


    /**
     * Get the newly issued ID token. An ID token is issued when the
     * {@code response_type} request parameter of the authorization
     * request includes {@code id_token}.
     *
     * @return
     *         The newly issued ID token. If an ID token is not issued,
     *         this method returns {@code null}.
     *
     * @since 1.34
     */
    public String getIdToken()
    {
        return idToken;
    }


    /**
     * Set the newly issued ID token.
     *
     * @param idToken
     *         The newly issued ID token.
     *
     * @since 1.34
     */
    public void setIdToken(String idToken)
    {
        this.idToken = idToken;
    }


    /**
     * Get the newly issued authorization code. An authorization code is
     * issued when the {@code response_type} request parameter of the
     * authorization request includes {@code code}.
     *
     * @return
     *         The newly issued authorization code. If an authorization
     *         code is not issued, this method returns {@code null}.
     *
     * @since 1.34
     */
    public String getAuthorizationCode()
    {
        return authorizationCode;
    }


    /**
     * Set the newly issued authorization code.
     *
     * @param code
     *         The newly issued authorization code.
     *
     * @since 1.34
     */
    public void setAuthorizationCode(String code)
    {
        this.authorizationCode = code;
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
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
                action, responseContent, accessToken, accessTokenExpiresAt,
                accessTokenDuration, idToken, authorizationCode, jwtAccessToken);
    }
}
