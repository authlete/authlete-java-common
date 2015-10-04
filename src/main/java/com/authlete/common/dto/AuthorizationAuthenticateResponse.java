/*
 * Copyright (C) 2014 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


/**
 * Response from Authlete's {@code /auth/authorization/authenticate} API.
 *
 * <p>
 * Authlete's {@code /auth/authorization/authenticate} API
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
 * ({@link AuthorizationAuthenticateRequest}) was wrong or that an error
 * occurred in Authlete.
 * </p>
 *
 * <p>
 * The service implementation should generate a response with the HTTP
 * status of {@code "500 Internal Server Error"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate.
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
 * that the ticket is no longer valid (deleted or expired).
 * </p>
 *
 * <p>
 * The HTTP status of the response returned should be {@code "400 Bad Request"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate.
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
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that
 * the response should be {@code "200 OK"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which contains
 * the result of authentication. When the authentication succeeded,
 * the subject of the authenticated end-user and optionally his/her
 * claim values are contained in the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dl>
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationAuthenticateResponse extends ApiResponse
{
    private static final long serialVersionUID = 2L;


    /**
     * The next action that the service implementation should take.
     */
    public enum Action
    {
        /**
         * The request from the service implementation was wrong or
         * an error occurred in Authlete, so the service implementation
         * should return {@code "500 Internal Server Error"}.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The ticket was no longer valid. The service implementation
         * should return {@code "400 Bad Request}".
         */
        BAD_REQUEST,

        /**
         * The result of authentication was obtained. The service
         * implementation should return {@code "200 OK"}.
         */
        OK
    }


    private static final String SUMMARY_FORMAT = "action=%s, authenticated=%s, responseContent=%s";


    private Action action;
    private boolean authenticated;
    private String responseContent;


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
     * Get the result of the authentication.
     *
     * @return
     *         {@code true} if authenticated.
     *
     * @since 1.15
     */
    public boolean isAuthenticated()
    {
        return authenticated;
    }


    /**
     * Set the result of the authentication.
     *
     * @param authenticated
     *         {@code true} if authenticated.
     *
     * @since 1.15
     */
    public void setAuthenticated(boolean authenticated)
    {
        this.authenticated = authenticated;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as the entity body
     * of the response.
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
        return String.format(SUMMARY_FORMAT, action, authenticated, responseContent);
    }
}
