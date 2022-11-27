/*
 * Copyright (C) 2022 Authlete, Inc.
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
 * Response from Authlete's {@code /federation/registration} API.
 *
 *
 * <p>
 * Authlete's {@code /federation/registration} API returns JSON which can
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
 * parameter is <code>{@link Action#OK OK}</code>, it means that the client
 * registration request was processed successfully and a client was registered.
 * <p>
 *
 * </p>
 * In this case, the implementation of the federation registration endpoint of
 * the authorization server should return an HTTP response to the API caller
 * with the HTTP status code "{@code 200 OK}" and the content type
 * "{@code application/jose}". The message body (= an entity statement in the
 * the JWT format) of the response has been prepared by Authlete's
 * {@code /federation/registration} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * The implementation of the federation registration endpoint can construct an
 * HTTP response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 200 OK
 * Content-Type: application/jose
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
 *
 * <h3><code>BAD_REQUEST</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#BAD_REQUEST BAD_REQUEST}</code>, it means
 * that the client registration request is wrong or that a client having the
 * same entity ID has already been registered.
 * </p>
 *
 * </p>
 * In this case, the implementation of the federation registration endpoint of
 * the authorization server should return an HTTP response to the API caller
 * with the HTTP status code "{@code 400 Bad Request}" and the content type
 * "{@code application/json}". The message body (= error information in the
 * JSON format) of the response has been prepared by Authlete's
 * {@code /federation/registration} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * The implementation of the federation registration endpoint can construct an
 * HTTP response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 400 Bad Request
 * Content-Type: application/json
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
 * "https://openid.net/specs/openid-connect-federation-1_0.html">OpenID Connect
 * Federation 1.0</a> and so the federation registration endpoint should not be
 * accessed.
 * </p>
 *
 * </p>
 * In this case, the implementation of the federation registration endpoint of
 * the authorization server should return an HTTP response to the API caller
 * with the HTTP status code "{@code 404 Not Found}" and the content type
 * "{@code application/json}". The message body (= error information in the
 * JSON format) of the response has been prepared by Authlete's
 * {@code /federation/registration} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * The implementation of the federation registration endpoint can construct an
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
 * federationJwks}</code>) has not been set up, when authority hints
 * ({@code Service.}<code>{@link Service#getAuthorityHints()
 * authorityHints}</code>) have not been set up, etc.
 * </p>
 *
 * </p>
 * In this case, a simple implementation of the federation registration endpoint
 * would return an HTTP response to the client application with the HTTP status
 * code "{@code 500 Internal Server Error}" and the content type
 * "{@code application/json}". The message body (= error information in the JSON
 * format) of the response has been prepared by Authlete's
 * {@code /federation/registration} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * Such a simple implementation of the entity configuration endpoint can
 * construct an HTTP response by doing like below.
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
 * servers what they actually return to API callers in the case of internal
 * server error.
 * </p>
 *
 * @since 3.45
 * @since Authlete 2.3
 *
 * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
 *      >OpenID Connect Federation 1.0</a>
 */
public class FederationRegistrationResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the implementation of the federation
     * registration endpoint should take.
     *
     * @since 3.45
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
     *      >OpenID Connect Federation 1.0</a>
     */
    public enum Action
    {
        /**
         * The client registration request was processed successfully. The
         * implementation of the federation registration endpoint should
         * return the status code {@code 200 OK} with the content type
         * {@code application/jose}.
         */
        OK,


        /**
         * The client registration request was invalid. The implementation
         * of the federation registration endpoint should return the status
         * code {@code 400 Bad Request} with the content type
         * {@code application/json}.
         */
        BAD_REQUEST,


        /**
         * The feature of "OpenID Connect Federation 1&#x002E;0" is not
         * enabled on the Authlete server or by the service, so the
         * federation registration endpoint does not work. The endpoint
         * should return the status code {@code 404 Bad Request} with the
         * content type {@code application/json}.
         */
        NOT_FOUND,


        /**
         * Something wrong happened. The implementation of the federation
         * registration endpoint should return an error response. For
         * example, an error response with the status code
         * {@code 500 Internal Server Error} and the content type
         * {@code application/json}.
         */
        INTERNAL_SERVER_ERROR,
    }


    private Action action;
    private String responseContent;
    private Client client;


    /**
     * Get the next action that the implementation of the federation
     * registration endpoint should take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the federation
     * registration endpoint should take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public FederationRegistrationResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content that the implementation of the federation registration
     * endpoint should use when it constructs a response to the API caller.
     *
     * <p>
     * The format of the content varies depending on the value of the
     * {@code action} response parameter.
     * </p>
     *
     * @return
     *         The content that should be returned from the federation
     *         registration endpoint.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content that the implementation of the federation registration
     * endpoint should use when it constructs a response to the API caller.
     *
     * <p>
     * The format of the content varies depending on the value of the
     * {@code action} response parameter.
     * </p>
     *
     * @param responseContent
     *         The content that should be returned from the federation
     *         registration endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public FederationRegistrationResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the information about the client that has been registered by the
     * client registration request.
     *
     * @return
     *         The information about the registered client.
     */
    public Client getClient()
    {
        return client;
    }


    /**
     * Set the information about the client that has been registered by the
     * client registration request.
     *
     * @param client
     *         The information about the registered client.
     *
     * @return
     *         {@code this} object.
     */
    public FederationRegistrationResponse setClient(Client client)
    {
        this.client = client;

        return this;
    }
}
