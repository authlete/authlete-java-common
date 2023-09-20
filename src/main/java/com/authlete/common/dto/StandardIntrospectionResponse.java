/*
 * Copyright (C) 2017-2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.dto;


import com.authlete.common.api.AuthleteApi;


/**
 * Response from Authlete's {@code /api/auth/introspection/standard} API.
 * Note that the API and {@code /api/auth/introspection} API are different.
 * {@code /api/auth/introspection/standard} API exists to help your
 * authorization server provide its own introspection API which complies
 * with <a href="https://tools.ietf.org/html/rfc7662">RFC 7662</a> (OAuth
 * 2.0 Token Introspection).
 *
 * <p>
 * Authlete's {@code /api/auth/introspection/standard} API returns JSON
 * which can be mapped to this class. The implementation of the
 * introspection endpoint of your authorization server should retrieve the
 * value of {@code "action"} from the response and take the following steps
 * according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from your system to Authlete
 * ({@link StandardIntrospectionRequest}) was wrong or that an error
 * occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from the viewpoint of the resource server, it is an
 * error on the server side. Therefore, the introspection endpoint of
 * your authorization server should generate a response to the resource
 * server with the HTTP status of {@code "500 Internal Server Error"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response if
 * you want. Note that, however, <a href=
 * "https://tools.ietf.org/html/rfc7662">RFC 7662</a> does not mention
 * anything about the response body of error responses.
 * </p>
 *
 * <p>
 * The following illustrates an example response which the introspection
 * endpoint of your authorization server generates and returns to the
 * resource server.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * Content-Type: application/json
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "BAD_REQUEST"}, it means
 * that the request from the resource server is invalid. This happens
 * when the request from the resource server did not include the {@code
 * token} request parameter. See "<a href=
 * "https://tools.ietf.org/html/rfc7662#section-2.1">2.1. Introspection
 * Request</a>" in RFC 7662 for details about requirements for
 * introspection requests.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the resource server
 * should be {@code "400 Bad Request"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response if
 * you want. Note that, however, <a href=
 * "https://tools.ietf.org/html/rfc7662">RFC 7662</a> does not mention
 * anything about the response body of error responses.
 * </p>
 *
 * <p>
 * The following illustrates an example response which the introspection
 * endpoint of your authorization server generates and returns to the
 * resource server.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that
 * the request from the resource server is valid.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the resource server
 * must be {@code "200 OK"} and its content type must be {@code
 * "application/json"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which complies
 * with the introspection response defined in "<a href=
 * "https://tools.ietf.org/html/rfc7662#section-2.2">2.2. Introspection
 * Response</a>" in RFC7662.
 * </p>
 *
 * <p>
 * The following illustrates the response which the introspection endpoint
 * of your authorization server should generate and return to the resource
 * server.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#JWT JWT}</b></dt>
 * <dd>
 * <p>
*  When the value of {@code "action"} is {@code "JWT"}, it means that
 * the request from the resource server is valid and a JWT is returned
 * to the resource server as the introspection response.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the resource server
 * must be {@code "200 OK"} and its content type must be {@code
 * "application/token-introspection+jwt"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JWT which complies with the
 * introspection response defined in "<a href="
 * https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
 * >JWT Response for OAuth Token Introspection</a>".
 * </p>
 *
 * <p>
 * The following illustrates the response which the introspection endpoint
 * of your authorization server should generate and return to the resource
 * server.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/token-introspection+jwt
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 * </dl>
 *
 * <br/>
 * <p>
 * Note that RFC 7662 says <i>"To prevent token scanning attacks,
 * <b>the endpoint MUST also require some form of authorization</b>
 * to access this endpoint"</i>. This means that you have to protect
 * your introspection endpoint in some way or other. Authlete does
 * not care about how your introspection endpoint is protected. In
 * most cases, as mentioned in RFC 7662, {@code "401 Unauthorized"}
 * is a proper response when an introspection request does not
 * satisfy authorization requirements imposed by your introspection
 * endpoint.
 * </p>
 *
 * @see <a href="http://tools.ietf.org/html/rfc7662">RFC 7662, OAuth 2.0 Token Introspection</a>
 * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
 *      >JWT Response for OAuth Token Introspection</a>
 * @see StandardIntrospectionRequest
 * @see AuthleteApi#standardIntrospection(StandardIntrospectionRequest)
 *
 * @author Takahiko Kawasaki
 * @author Hideki Ikeda
 *
 * @since 2.7
 */
public class StandardIntrospectionResponse extends ApiResponse
{
    private static final long serialVersionUID = 2L;


    /**
     * The next action that the implementation of the introspection
     * endpoint of the authorization server should take.
     */
    public enum Action
    {
        /**
         * The request from your system to Authlete ({@link
         * StandardIntrospectionRequest}) was wrong or an error occurred
         * in Authlete. The introspection endpoint of your authorization
         * server should return {@code "500 Internal Server Error"} to
         * the resource server.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The request from the resource server was wrong. The introspection
         * endpoint of your authorization server should return {@code
         * "400 Bad Request"} to the resource server.
         */
        BAD_REQUEST,

        /**
         * The request from the resource server was valid. The introspection
         * endpoint of your authorization server should return {@code
         * "200 OK"} to the resource server.
         */
        OK,

        /**
         * The request from the resource server was valid and a JWT is
         * returned to the resource server as the introspection response.
         * The introspection endpoint of your authorization server should
         * return {@code "200 OK"} to the resource server.
         *
         * @since 3.76
         * @since Authlete 3.0
         */
        JWT,
    }


    private static final String SUMMARY_FORMAT = "action=%s, responseContent=%s";


    /**
     * @since Authlete 1.1.7
     */
    private Action action;
    /**
     * @since Authlete 1.1.7
     */
    private String responseContent;


    /**
     * Get the next action that the introspection endpoint of your
     * authorization server should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the introspection endpoint of the
     * authorization server should take.
     */
    public StandardIntrospectionResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response returned to the resource server.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as the entity body
     * of the response returned to the resource server.
     */
    public StandardIntrospectionResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT, action, responseContent);
    }
}
