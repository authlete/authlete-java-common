/*
 * Copyright (C) 2018 Authlete, Inc.
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


/**
 * Response from Authlete's {@code /api/backchannel/authentication/complete}
 * API.
 *
 * @since 2.32
 */
public class BackchannelAuthenticationCompleteResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the OpenID provider implementation should take.
     */
    public enum Action
    {
        /**
         * The OpenID provider implementation must send a notification to the
         * client's notification endpoint. This action code is returned when
         * the backchannel token delivery mode is {@code "ping"} or
         * {@code "push"}.
         */
        NOTIFICATION,


        /**
         * The OpenID provider implementation does not have to take any
         * immediate action for this API response. The remaining task is just
         * to handle polling requests from the client to the token endpoint.
         * This action code is returned when the backchannel token delivery
         * mode is {@code "poll"}.
         */
        NO_ACTION,
    }


    private Action action;
    private String responseContent;
    private URI clientNotificationEndpoint;
    private String clientNotificationToken;


    /**
     * Get the next action that the OpenID provider should take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the OpenID provider should take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content of the notification.
     *
     * <p>
     * When {@link #getAction()} returns {@link Action#NOTIFICATION
     * NOTIFICATION}, this method returns JSON which should be used as the
     * request body of the notification.
     * </p>
     *
     * <p>
     * In successful cases, when the backchannel token delivery mode is
     * {@code "ping"}, the JSON contains {@code "auth_req_id"}. On the other
     * hand, when the backchannel token delivery mode is {@code "push"}, the
     * JSON contains an access token, an ID token, and optionally a refresh
     * token (and some other properties).
     * </p>
     *
     * @return
     *         The content of the notification.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content of the notification.
     *
     * @param responseContent
     *         The content of the notification.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the client notification endpoint to which a notification needs to be
     * sent.
     *
     * <p>
     * This corresponds to the {@code "client_notification_endpoint"} metadata
     * of the client application.
     * </p>
     *
     * @return
     *         The client notification endpoint.
     */
    public URI getClientNotificationEndpoint()
    {
        return clientNotificationEndpoint;
    }


    /**
     * Set the client notification endpoint to which a notification needs to be
     * sent.
     *
     * <p>
     * This corresponds to the {@code "client_notification_endpoint"} metadata
     * of the client application.
     * </p>
     *
     * @param endpoint
     *         The client notification endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setClientNotificationEndpoint(URI endpoint)
    {
        this.clientNotificationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the client notification token which needs to be embedded as a
     * {@code Bearer} token in the {@code Authorization} header in the
     * notification.
     *
     * <p>
     * This is the value of the {@code "client_notification_token"} request
     * parameter included in the backchannel authentication request.
     * </p>
     *
     * @return
     *         The client notification token.
     */
    public String getClientNotificationToken()
    {
        return clientNotificationToken;
    }


    /**
     * Set the client notification token which needs to be embedded as a
     * {@code Bearer} token in the {@code Authorization} header in the
     * notification.
     *
     * @param token
     *         The client notification token.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setClientNotificationToken(String token)
    {
        this.clientNotificationToken = token;

        return this;
    }
}
