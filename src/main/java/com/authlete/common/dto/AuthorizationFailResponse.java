/*
 * Copyright (C) 2014 Authlete, Inc.
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
 * Response from Authlete's {@code /auth/authorization/fail} API.
 *
 * <p>
 * Authlete's {@code /auth/authorization/fail} API
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
 * ({@link AuthorizationFailRequest}) was wrong or that an error occurred
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
 * {@link #getResponseContent()} returns a redirect URI to which the error
 * should be reported, so it can be used as the value of {@code "Location"}
 * header.
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
public class AuthorizationFailResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


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


    private static final String SUMMARY_FORMAT = "action=%s, responseContent=%s";


    /**
     * @since Authlete 0.1
     */
    private Action action;
    /**
     * @since Authlete 0.1
     */
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
     * Get the response content which can be used to generate a response
     * to the client application. The format of the value varies depending
     * on the value of {@code "action"}.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used to generate a response
     * to the client application.
     */
    public void setResponseContent(String content)
    {
        this.responseContent = content;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT, action, responseContent);
    }
}
