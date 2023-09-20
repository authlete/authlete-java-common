/*
 * Copyright (C) 2019-2023 Authlete, Inc.
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


/**
 * Response from Authlete's {@code /api/pushed_auth_req} API.
 *
 * <p>
 * Authlete's {@code /api/pushed_auth_req} API returns JSON which can be mapped
 * to this class. The authorization server implementation should retrieve the
 * value of {@code action} from the response and take the following steps
 * according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#CREATED CREATED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code CREATED}, it means that the
 * authorization request has been registered successfully.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 201 Created} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which can
 * be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 201 Created
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code BAD_REQUEST}, it means that the
 * request was wrong.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 400 Bad Request} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#UNAUTHORIZED UNAUTHORIZED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code UNAUTHORIZED}, it means that
 * client authentication of the request failed.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 401 Unauthorized} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(challenge)</i>
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#FORBIDDEN FORBIDDEN}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code FORBIDDEN}, it means that the
 * client application is not allowed to use the pushed authorization request
 * endpoint.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 403 Forbidden} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 403 Forbidden
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#PAYLOAD_TOO_LARGE PAYLOAD_TOO_LARGE}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code PAYLOAD_TOO_LARGE}, it means that
 * the size of the pushed authorization request is too large.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 413 Payload Too Large} and
 * {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 413 Payload Too Large
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code INTERNAL_SERVER_ERROR}, it means
 * that the API call from the authorization server implementation was wrong or
 * that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from a viewpoint of the client application, it is an error
 * on the server side. Therefore, the authorization server implementation
 * should generate a response to the client application with
 * {@code 500 Internal Server Error} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 *
 * <p>
 * However, it is up to the authorization server's policy whether to return
 * {@code 500} actually.
 * </p>
 * <br/>
 * </dd>
 *
 * </dl>
 *
 * <h3>DPoP Nonce (Authlete 3.0 onwards)</h3>
 *
 * <p>
 * Since version 3.0, Authlete recognizes the {@code nonce} claim in DPoP
 * proof JWTs. If the {@code nonce} claim is required (= if the service's
 * {@code dpopNonceRequired} property is {@code true}, or the value of the
 * {@code dpopNonceRequired} request parameter passed to the Authlete API
 * is {@code true}), the Authlete API checks whether the {@code nonce}
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
 * @since 2.51
 */
public class PushedAuthReqResponse extends ApiResponse
{
    private static final long serialVersionUID = 2L;


    /**
     * The next action the authorization server should take.
     */
    public static enum Action
    {
        /**
         * The pushed authorization request has been registered successfully.
         * The endpoint should return {@code 201 Created} to the client
         * application.
         */
        CREATED,


        /**
         * The request is invalid. The pushed authorization request endpoint
         * should return {@code 400 Bad Request} to the client application.
         */
        BAD_REQUEST,


        /**
         * The client authentication at the pushed authorization request
         * endpoint failed. The endpoint should return {@code 401 Unauthorized}
         * to the client application.
         */
        UNAUTHORIZED,


        /**
         * The client application is not allowed to use the pushed
         * authorization request endpoint. The endpoint should return
         * {@code 403 Forbidden} to the client application.
         */
        FORBIDDEN,


        /**
         * The size of the pushed authorization request is too large. The
         * endpoint should return {@code 413 Payload Too Large} to the client
         * application.
         */
        PAYLOAD_TOO_LARGE,


        /**
         * The API call was wrong or an error occurred on Authlete side. The
         * pushed authorization request endpoint should return
         * {@code 500 Internal Server Error} to the client application.
         * However, it is up to the authorization server's policy whether to
         * return {@code 500} actually.
         */
        INTERNAL_SERVER_ERROR,
    }


    private static final String SUMMARY_FORMAT = "action=%s, responseContent=%s, "
            + "clientAuthMethod=%s, requestUri=%s";


    /**
     * @since Authlete 2.2.0
     */
    private Action action;
    /**
     * @since Authlete 2.2.0
     */
    private String responseContent;
    /**
     * @since Authlete 2.2.0
     */
    private ClientAuthMethod clientAuthMethod;
    /**
     * @since Authlete 2.2.0
     */
    private URI requestUri;
    private String dpopNonce;


    /**
     * Get the next action that the authorization server should take.
     *
     * @return
     *         The action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the authorization server should take.
     *
     * @param action
     *            The action.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response returned to the client application.
     *
     * @return
     *         The response content string.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as the entity body
     * of the response returned to the client application.
     *
     * @param responseContent
     *            The response content string.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the client authentication method that should be performed at the
     * pushed authorization request endpoint.
     *
     * <p>
     * If the client could not be identified by the information in the request,
     * this method returns {@code null}.
     * </p>
     *
     * @return
     *         The client authentication method that should be performed at
     *         the pushed authorization request endpoint.
     */
    public ClientAuthMethod getClientAuthMethod()
    {
        return clientAuthMethod;
    }


    /**
     * Set the client authentication method that should be performed at the
     * pushed authorization request endpoint.
     *
     * @param method
     *         The client authentication method that should be performed at
     *         the pushed authorization request endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqResponse setClientAuthMethod(ClientAuthMethod method)
    {
        this.clientAuthMethod = method;

        return this;
    }


    /**
     * Get the request URI created to represent the pushed authorization
     * request. This can be sent by the client as the 'request_uri' parameter
     * in an authorization request.
     *
     * @return
     *         The registered request URI.
     */
    public URI getRequestUri()
    {
        return requestUri;
    }


    /**
     * Set the request URI created to represent the pushed authorization
     * request.
     *
     * @param uri
     *            The registered request URI.
     *
     * @return
     *         {@code this} object.
     */
    public PushedAuthReqResponse setRequestUri(URI uri)
    {
        this.requestUri = uri;

        return this;
    }


    /**
     * Get the expected nonce value for DPoP proof JWT, which should be used
     * as the value of the {@code DPoP-Nonce} HTTP header.
     *
     * <p>
     * When this response parameter is not null, the implementation of the
     * PAR endpoint should add the {@code DPoP-Nonce} HTTP header in the
     * response from the endpoint to the client application, using the value
     * of this response parameter as the value of the HTTP header.
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
     * PAR endpoint should add the {@code DPoP-Nonce} HTTP header in the
     * response from the endpoint to the client application, using the value
     * of this response parameter as the value of the HTTP header.
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
    public PushedAuthReqResponse setDpopNonce(String dpopNonce)
    {
        this.dpopNonce = dpopNonce;

        return this;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
                action, responseContent, clientAuthMethod, requestUri);
    }
}
