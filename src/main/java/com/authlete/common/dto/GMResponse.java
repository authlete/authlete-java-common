/*
 * Copyright (C) 2021-2023 Authlete, Inc.
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
 * Response from Authlete's {@code /api/gm} API.
 *
 * <p>
 * Authlete's {@code /api/gm} API returns JSON which can be mapped to this
 * class. The implementation of the grant management endpoint should retrieve
 * the value of {@code "action"} from the response and take the following
 * steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that the access
 * token which the client application presented is valid and information about
 * the grant has been retrieved successfully. In this case, the implementation
 * of the endpoint should return an HTTP response with the status code
 * {@code 200 OK} and the content type {@code application/json} to the client
 * application.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a response in JSON format when
 * {@code "action"} is {@code "OK"}, so a response to the client application
 * can be built like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Cache-Control: no-store
 * Pragma: no-cache
 * Content-Type: application/json
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#NO_CONTENT NO_CONTENT}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "NO_CONTENT"}, it means that
 * the access token which the client application presented is valid and the
 * grant was revoked successfully. In this case, the implementation of the
 * endpoint should return an HTTP response with the status code
 * {@code 204 No Content} to the client application.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#UNAUTHORIZED UNAUTHORIZED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "UNAUTHORIZED"}, it means that
 * the grant management request includes no access token or the access token
 * is invalid. In this case, the implementation of the endpoint should return
 * an HTTP response with the status code {@code 401 Unauthorized}.
 * </p>
 *
 * <p>
 * The specification does not describe details about error response format, but
 * in error cases, {@link #getResponseContent()} returns information about the
 * error in the format suitable as a value for the {@code WWW-Authenticate} HTTP
 * header. The implementation of the endpoint may use the string when building
 * an error response. The following is an example.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#FORBIDDEN FORBIDDEN}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "FORBIDDEN"}, it means that the
 * access token cannot be used to manage the grant ID. In this case, the
 * implementation of the endpoint should return an HTTP response with the
 * status code {@code 403 Forbidden}. The error response can be built like
 * below in the same way for the case of {@code UNAUTHORIZED}.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 403 Forbidden
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#NOT_FOUND NOT_FOUND}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "NOT_FOUND"}, it means that the
 * grant ID was not found. In this case, the implementation of the endpoint
 * should return an HTTP response with the status code {@code 404 Not Found}.
 * The error response can be built like below in the same way for the case of
 * {@code UNAUTHORIZED}.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 404 Not Found
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#CALLER_ERROR CALL_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "CALLER_ERROR"}, it means that
 * the API call is wrong. For example, the {@code gmAction} request parameter,
 * which is mandatory, is missing. The implementation of the endpoint should be
 * fixed. In this case, it's up to the implementation how to respond to the
 * client application. A simple implementation would return an HTTP response
 * with the status code {@code 500 Internal Server Error} like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#AUTHLETE_ERROR AUTHLETE_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "AUTHLETE_ERROR"}, it means
 * that an error occurred on Authlete side. In this case, it's up to the
 * implementation how to respond to the client application. A simple
 * implementation would return an HTTP response with the status code
 * {@code 500 Internal Server Error} like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 * </dl>
 *
 * <h3>DPoP Nonce (Authlete 3.0 onwards)</h3>
 *
 * <p>
 * Since version 3.0, Authlete recognizes the {@code nonce} claim in DPoP
 * proof JWTs. If the presented access token is bound to a public key through
 * the DPoP mechanism, and if the {@code nonce} claim is required (= if the
 * service's {@code dpopNonceRequired} property is {@code true}, or the value
 * of the {@code dpopNonceRequired} request parameter passed to the Authlete
 * API is {@code true}), the Authlete API checks whether the {@code nonce}
 * claim in the presented DPoP proof JWT is identical to the expected value.
 * </p>
 *
 * <p>
 * If the {@code dpopNonce} response parameter from the API is not null, its
 * value is the expected nonce value for DPoP proof JWT. The expected value
 * needs to be conveyed to the client application as the value of the
 * {@code DPoP-Nonce} HTTP header.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;"
 * >DPoP-Nonce: (The value returned from {@link #getDpopNonce()})</pre>
 *
 * <p>
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 *
 * @see <a href="https://openid.net/specs/fapi-grant-management.html"
 *      >Grant Management for OAuth 2.0</a>
 *
 * @since 3.1
 */
public class GMResponse extends ApiResponse
{
    private static final long serialVersionUID = 2L;


    /**
     * The next action the service implementation should take.
     */
    public enum Action
    {
        /**
         * The access token is valid and information about the grant has been
         * retrieved successfully. The implementation of the endpoint should
         * return {@code "200 OK"} to the client application with the content
         * type {@code "application/json"}.
         */
        OK,

        /**
         * The access token is valid and the grant has been revoked successfully.
         * The implementation of the endpoint should return {@code 204 No Content}
         * to the client application.
         */
        NO_CONTENT,

        /**
         * The grant management request includes no access token or the access
         * token is invalid. The implementation of the endpoint should return
         * {@code 401 Unauthorized} to the client application.
         */
        UNAUTHORIZED,

        /**
         * The access token cannot be used to manage the grant ID. The
         * implementation of the endpoint should return {@code 403 Forbidden}
         * to the client application.
         */
        FORBIDDEN,

        /**
         * The grant ID was not found. The implementation of the endpoint should
         * return {@code "404 Not Found"} to the client application.
         */
        NOT_FOUND,

        /**
         * The API call is wrong. For example, the {@code gmAction} request
         * parameter is not included. The implementation of the endpoint should
         * be fixed. It's up to the implementation how to respond to the client
         * application. A simple implementation would return
         * {@code 500 Internal Server Error}.
         */
        CALLER_ERROR,

        /**
         * An error occurred on Authlete side. It's up to the the implementation
         * of the endpoint how to respond to the client application. A simple
         * implementation would return {@code "500 Internal Server Error"}.
         */
        AUTHLETE_ERROR,
    }


    /**
     * @since Authlete 3.0.0
     */
    private Action action;
    /**
     * @since Authlete 3.0.0
     */
    private String responseContent;
    private String dpopNonce;


    /**
     * Get the next action that the authorization server should take.
     *
     * @return
     *         The next action that the authorization server should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the authorization server should take.
     *
     * @param action
     *         The next action that the authorization server should take.
     *
     * @return
     *         {@code this} object.
     */
    public GMResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the response content which can be used to build a response
     * to the client application.
     *
     * @return
     *         The content of the response to the client application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used to build a response
     * to the client application.
     *
     * @param content
     *         The content of the response to the client application.
     *
     * @return
     *         {@code this} object.
     */
    public GMResponse setResponseContent(String content)
    {
        this.responseContent = content;

        return this;
    }


    /**
     * Get the expected nonce value for DPoP proof JWT, which should be used
     * as the value of the {@code DPoP-Nonce} HTTP header.
     *
     * <p>
     * When this response parameter is not null, the implementation of the
     * grant management endpoint should add the {@code DPoP-Nonce} HTTP header
     * in the response from the endpoint to the client application, using the
     * value of this response parameter as the value of the HTTP header.
     * </p>
     *
     * <pre>
     * DPoP-Nonce: (<i>The value of this {@code dpopNonce} response parameter</i>)
     * </pre>
     *
     * @return
     *         The expected nonce value for DPoP proof JWT.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getDpopNonce()
    {
        return dpopNonce;
    }


    /**
     * Set the expected nonce value for DPoP proof JWT, which should be used
     * as the value of the {@code DPoP-Nonce} HTTP header.
     *
     * <p>
     * When this response parameter is not null, the implementation of the
     * grant management endpoint should add the {@code DPoP-Nonce} HTTP header
     * in the response from the endpoint to the client application, using the
     * value of this response parameter as the value of the HTTP header.
     * </p>
     *
     * <pre>
     * DPoP-Nonce: (<i>The value of this {@code dpopNonce} response parameter</i>)
     * </pre>
     *
     * @param dpopNonce
     *         The expected nonce value for DPoP proof JWT.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public GMResponse setDpopNonce(String dpopNonce)
    {
        this.dpopNonce = dpopNonce;

        return this;
    }
}
