/*
 * Copyright (C) 2022-2023 Authlete, Inc.
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
 * Response from Authlete's {@code /federation/configuration} API.
 *
 * <p>
 * Authlete's {@code /federation/configuration} API returns JSON which can
 * be mapped to this class. The authorization server implementation should
 * retrieve the value of the <code>{@link #getAction() action}</code>
 * response parameter from the API response and take the following steps
 * according to the value.
 * </p>
 *
 * <h3><code>OK</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#OK OK}</code>, it means that Authlete
 * could prepare an entity configuration successfully.
 * <p>
 *
 * </p>
 * In this case, the implementation of the entity configuration endpoint of the
 * authorization server should return an HTTP response to the client application
 * with the HTTP status code "{@code 200 OK}" and the content type
 * "{@code application/entity-statement+jwt}". The message body (= an entity
 * configuration in the JWT format) of the response has been prepared by
 * Authlete's {@code /federation/configuration} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * The implementation of the entity configuration endpoint can construct an
 * HTTP response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 200 OK
 * Content-Type: application/entity-statement+jwt
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
 *
 * <h3><code>NOT_FOUND</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#NOT_FOUND NOT_FOUND}</code>, it means that
 * the service configuration has not enabled the feature of <a href=
 * "https://openid.net/specs/openid-federation-1_0.html">OpenID Federation 1.0</a>
 * and so the client application should have not access the entity configuration
 * endpoint.
 * </p>
 *
 * </p>
 * In this case, the implementation of the entity configuration endpoint of the
 * authorization server should return an HTTP response to the client application
 * with the HTTP status code "{@code 404 Not Found}" and the content type
 * "{@code application/json}". The message body (= error information in the JSON
 * format) of the response has been prepared by Authlete's
 * {@code /federation/configuration} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * The implementation of the entity configuration endpoint can construct an
 * HTTP response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 404 Not Found
 * Content-Type: application/json
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
 *
 * <h3><code>INTERNAL_SERVER_ERROR</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</code>,
 * it means that an unexpected error has occurred on Authlete side or the
 * service has not been set up properly yet. For example, when a JWK Set
 * for federation ({@code Service.}<code>{@link Service#getFederationJwks()
 * federationJwks}</code>) has not been setup, when authority hints
 * ({@code Service.}<code>{@link Service#getAuthorityHints()
 * authorityHints}</code>) have not been setup, etc.
 * </p>
 *
 * </p>
 * In this case, a simple implementation of the entity configuration endpoint
 * would return an HTTP response to the client application with the HTTP status
 * code "{@code 500 Internal Server Error}" and the content type
 * "{@code application/json}". The message body (= error information in the JSON
 * format) of the response has been prepared by Authlete's
 * {@code /federation/configuration} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * Such simple implementation of the entity configuration endpoint can construct
 * an HTTP response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 500 Internal Server Error
 * Content-Type: application/json
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
 *
 * <p>
 * However, in real commercial deployments, it is rare for an authorization
 * server to return "{@code 500 Internal Server Error}" when it encounters
 * an unexpected internal error. It's up to implementations of authorization
 * servers what they actually return to client applications in the case of
 * internal server error.
 * </p>
 *
 * @since 3.31
 * @since Authlete 2.3
 *
 * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
 *      >OpenID Federation 1.0</a>
 */
public class FederationConfigurationResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the implementation of the entity configuration
     * endpoint should take after getting a response from Authlete's
     * {@code /federation/configuration} API.
     *
     * @since 3.31
     * @since Authlete 2.3
     */
    public enum Action
    {
        /**
         * An entity configuration has been prepared successfully. The
         * implementation of the entity configuration endpoint should return
         * an HTTP response to the client application with the HTTP status
         * code "{@code 200 OK}" and the content type
         * "{@code application/entity-statement+jwt}".
         */
        OK,

        /**
         * The feature of <a href="https://openid.net/specs/openid-federation-1_0.html"
         * >OpenID Federation 1&#x002E;0</a> is not enabled in this
         * service. The implementation of the entity configuration endpoint
         * should return an HTTP response to the client application with the
         * HTTP status code "{@code 404 Not Found}" and the content type
         * "{@code application/json}" to indicate that the client application
         * should have not accessed the endpoint.
         */
        NOT_FOUND,

        /**
         * An unexpected error occurred on Authlete side or the service has
         * not been set up properly yet. A simple implementation of the entity
         * configuration endpoint would return an HTTP response to the client
         * application with the HTTP status code "{@code 500 Internal Server Error}"
         * and the content type "{@code application/json}".
         */
        INTERNAL_SERVER_ERROR,
    }

    /**
     * @since Authlete 2.3
     */
    private Action action;
    /**
     * @since Authlete 2.3
     */
    private String responseContent;


    /**
     * Get the next action that the implementation of the entity configuration
     * endpoint should take after getting a response from Authlete's
     * {@code /federation/configuration} API.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the entity configuration
     * endpoint should take after getting a response from Authlete's
     * {@code /federation/configuration} API.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public FederationConfigurationResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content that the implementation of the entity configuration
     * endpoint should use when it constructs a response to the client
     * application.
     *
     * <p>
     * The format of the content varies depending on the value of the
     * {@code action} response parameter.
     * </p>
     *
     * @return
     *         The content that should be returned to the client application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content that the implementation of the entity configuration
     * endpoint should use when it constructs a response to the client
     * application.
     *
     * <p>
     * The format of the content varies depending on the value of the
     * {@code action} response parameter.
     * </p>
     *
     * @param content
     *         The content that should be returned to the client application.
     *
     * @return
     *         {@code this} object.
     */
    public FederationConfigurationResponse setResponseContent(String content)
    {
        this.responseContent = content;

        return this;
    }
}
