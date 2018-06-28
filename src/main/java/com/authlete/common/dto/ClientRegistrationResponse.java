/*
 * Copyright (C) 2018 Authlete, Inc.
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


/**
 * Response from Authlete's {@code /api/client/register} API.
 *
 * <p>
 * Authlete's {@code /api/client/register} API returns JSON which can be
 * mapped to this class. The implementation of client registration endpoint
 * should retrieve the value of {@code "action"} from the response and take
 * the following steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the endpoint implementation ({@link
 * ClientRegistrationRequest}) was wrong or that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from a viewpoint of the client application, it is an
 * error on the server side. Therefore, the endpoint implementation should
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
 * The following illustrates the response which the endpoint implementation
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
 * The following illustrates the response which the endpoint implementation
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
 * <dt><b>{@link Action#CREATED CREATED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "CREATED"}, it means that the
 * client registration request was valid and a client application has been
 * registered successfully.
 * </p>
 *
 * <p>
 * In this case, the HTTP status of the response returned to the client
 * application must be {@code "201 Created"} as described in "<a href=
 * "https://tools.ietf.org/html/rfc7591#section-3.2.1">3.2.1. Client
 * Information Response</a>" of <a href="https://tools.ietf.org/html/rfc7591"
 * >RFC 7591</a>.
 * </p>
 *
 * <p>
 * The following illustrates the response which the endpoint implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 201 Created
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 * </dl>
 *
 * @since 2.22
 */
public class ClientRegistrationResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the implementation of client registration
     * endpoint should take.
     */
    public enum Action
    {
        /**
         * The request from the endpoint implementation was wrong or an error
         * occurred in Authlete. The endpoint implementation should return
         * {@code "500 Internal Server Error"} to the client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The request from the client was wrong. The endpoint implementation
         * should return {@code "400 Bad Request"} to the client application.
         */
        BAD_REQUEST,

        /**
         * The request was valid and a client application has been registered
         * successfully. The endpoint implementation should return {@code
         * "201 Created"} to the client application.
         */
        CREATED,
    }


    private Action action;
    private String responseContent;
    private long clientId;
    private String clientSecret;
    private long clientIdIssuedAt;


    /**
     * Get the next action that the implementation of client registration
     * endpoint should take.
     *
     * @return
     *         The next action that should be taken.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of client registration
     * endpoint should take.
     *
     * @param action
     *         The next action that should be taken.
     */
    public void setAction(Action action)
    {
        this.action = action;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response returned to the client application.
     *
     * @return
     *         The content of the response returned to the client application.
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
     *         The content of the response returned to the client application.
     */
    public void setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;
    }


    /**
     * Get the client ID of the newly registered client application.
     *
     * <p>
     * This corresponds to {@code "client_id"} defined in "<a href=
     * "https://tools.ietf.org/html/rfc7591#section-3.2.1">3.2.1. Client
     * Information Response</a>" of <a href=
     * "https://tools.ietf.org/html/rfc7591">RFC 7591</a>.
     * </p>
     *
     * @return
     *         The client ID.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID of the newly registered client application.
     *
     * @param clientId
     *         The client ID.
     */
    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }


    /**
     * Get the client secret of the newly registered client application.
     *
     * <p>
     * This corresponds to {@code "client_secret"} defined in "<a href=
     * "https://tools.ietf.org/html/rfc7591#section-3.2.1">3.2.1. Client
     * Information Response</a>" of <a href=
     * "https://tools.ietf.org/html/rfc7591">RFC 7591</a>.
     * </p>
     *
     * <p>
     * Note that Authlete generates a client secret regardless of whether
     * the client type is confidential or public.
     * </p>
     *
     * @return
     *         The client secret.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret of the newly registered client application.
     *
     * @param clientSecret
     *         The client secret.
     */
    public void setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;
    }


    /**
     * Get the time at which the client identifier was issued. The time is
     * represented as the number of seconds since the Unix epoch (1970-Jan-1).
     *
     * <p>
     * This corresponds to {@code "client_id_issued_at"} defined in "<a href=
     * "https://tools.ietf.org/html/rfc7591#section-3.2.1">3.2.1. Client
     * Information Response</a>" of <a href=
     * "https://tools.ietf.org/html/rfc7591">RFC 7591</a>.
     * </p>
     *
     * @return
     *         The time at which the client identifier was issued.
     */
    public long getClientIdIssuedAt()
    {
        return clientIdIssuedAt;
    }


    /**
     * Set the time at which the client identifier was issued. The time is
     * represented as the number of seconds since the Unix epoch (1970-Jan-1).
     *
     * @param issuedAt
     *         The time at which the client identifier was issued.
     */
    public void setClientIdIssuedAt(long issuedAt)
    {
        this.clientIdIssuedAt = issuedAt;
    }
}
