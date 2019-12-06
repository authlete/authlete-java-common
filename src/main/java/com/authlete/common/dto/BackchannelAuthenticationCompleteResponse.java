/*
 * Copyright (C) 2018-2019 Authlete, Inc.
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
import com.authlete.common.types.DeliveryMode;


/**
 * Response from Authlete's {@code /api/backchannel/authentication/complete}
 * API.
 *
 * <p>
 * Authlete's {@code /api/backchannel/authentication/complete} API returns JSON
 * which can be mapped to this class. The authorization server implementation
 * should retrieve the value of {@code action} from the response and take the
 * following steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#NOTIFICATION NOTIFICATION}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code NOTIFICATION}, it means that the
 * authorization server must send a notification to the client notification
 * endpoint.
 * </p>
 *
 * <p>
 * According to the CIBA Core specification, the notification is an HTTP POST
 * request whose request body is JSON and whose {@code Authorization} header
 * contains the client notification token, which was included in the
 * backchannel authentication request as the value of the
 * {@code client_notification_token} request parameter, as a bearer token.
 * </p>
 *
 * <p>
 * When the backchannel token delivery mode is "ping", the request body of the
 * notification is JSON which contains the {@code auth_req_id} property only.
 * When the backchannel token delivery mode is "push", the request body will
 * additionally contain an access token, an ID token and other properties.
 * Note that when the backchannel token delivery mode is "poll", a notification
 * does not have to be sent to the client notification endpoint.
 * </p>
 *
 * <p>
 * In error cases, in the "ping" mode, however, the content of a notification
 * is not different from the content in successful cases. That is, the
 * notification contains the {@code auth_req_id} property only. The client
 * will know the error when it accesses the token endpoint. On the other hand,
 * in the "push" mode, in error cases, the content of a notification will
 * include the {@code error} property instead of an access token and an ID
 * token. The client will know the error by detecting that {@code error} is
 * included in the notification.
 * </p>
 *
 * <p>
 * In any case, the {@link #getResponseContent()} method returns JSON which
 * can be used as the request body of the notification.
 * </p>
 *
 * <p>
 * The client notification endpoint that the notification should be sent to can
 * be obtained by calling the {@link #getClientNotificationEndpoint()} method.
 * Likewise, the client notification token that the notification should include
 * as a bearer token can be obtained by calling the
 * {@link #getClientNotificationToken()} method. With these methods, the
 * notification can be built like the following.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * POST <i>(The path of {@link #getClientNotificationEndpoint()})</i> HTTP/1.1
 * HOST: <i>(The host of {@link #getClientNotificationEndpoint()})</i>
 * Authorization: Bearer <i>(The value returned from {@link #getClientNotificationToken()})</i>
 * Content-Type: application/json
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#NO_ACTION NO_ACTION}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code NO_ACTION}, it means that the
 * authorization server does not have to take any immediate action.
 * </p>
 *
 * <p>
 * {@code NO_ACTION} is returned when the backchannel token delivery mode is
 * "poll". In this case, the client will receive the final result at the token
 * endpoint.
 * </p>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#SERVER_ERROR SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code SERVER_ERROR}, it means either
 * (1) that the request from the authorization server to Authlete was wrong,
 * or (2) that an error occurred on Authlete side.
 * </p>
 *
 * <p>
 * When the backchannel token delivery mode is "ping" or "push",
 * {@code SERVER_ERROR} is used only when an error is detected before the
 * record of the ticket (which is included in the API call to
 * {@code /api/backchannel/authentication/complete}) is retrieved from the
 * database successfully. If an error is detected <em>after</em> the record of
 * the ticket is retrieved from the database, {@code NOTIFICATION} is used
 * instead of {@code SERVER_ERROR}.
 * </p>
 *
 * <p>
 * When the backchannel token delivery mode is "poll", {@code SERVER_ERROR} is
 * used regardless of whether it is before or after the record of the ticket is
 * retrieved from the database.
 * </p>
 * </br>
 * </dd>
 *
 * </dl>
 *
 * @since 2.32
 */
public class BackchannelAuthenticationCompleteResponse extends ApiResponse
{
    private static final long serialVersionUID = 5L;


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


        /**
         * An error occurred either because the ticket included in the API call
         * was invalid or because an error occurred on Authlete side.
         *
         * <p>
         * If an error occurred after Authlete succeeded in retrieving data
         * associated with the ticket from the database and if the backchannel
         * token delivery mode is "ping" or "push", {@link #NOTIFICATION} is
         * used as the value of {@code action} instead of {@code SERVER_ERROR}.
         * In the case, {@code responseContent} contains
         * {@code "error":"server_error"}.
         * </p>
         */
        SERVER_ERROR,
    }


    private Action action;
    private String responseContent;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String clientName;
    private DeliveryMode deliveryMode;
    private URI clientNotificationEndpoint;
    private String clientNotificationToken;
    private String authReqId;
    private String accessToken;
    private String refreshToken;
    private String idToken;
    private long accessTokenDuration;
    private long refreshTokenDuration;
    private long idTokenDuration;
    private String jwtAccessToken;
    private URI[] resources;
    private AuthzDetails authorizationDetails;


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
     * Get the client ID of the client application that has made the
     * backchannel authentication request.
     *
     * @return
     *         The client ID of the client application.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID of the client application that has made the
     * backchannel authentication request.
     *
     * @param clientId
     *         The client ID of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client ID alias of the client application that has made the
     * backchannel authentication request.
     *
     * @return
     *         The client ID alias of the client application.
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the client ID alias of the client application that has made the
     * backchannel authentication request.
     *
     * @param alias
     *         The client ID alias of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;

        return this;
    }


    /**
     * Get the flag which indicates whether the client ID alias was used in
     * the backchannel authentication request.
     *
     * @return
     *         {@code true} if the client ID alias was used in the request.
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether the client ID alias was used in
     * the backchannel authentication request.
     *
     * @param used
     *         {@code true} to indicate that the client ID alias was used in
     *         the request.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;

        return this;
    }


    /**
     * Get the client identifier used in the backchannel authentication
     * request.
     *
     * <p>
     * When {@link #isClientIdAliasUsed()} returns {@code true}, this method
     * returns the same value as {@link #getClientIdAlias()} does. Otherwise,
     * this method returns the string representation of the value returned
     * from {@link #getClientId()}.
     * </p>
     *
     * @return
     *         The client identifier used in the backchannel authentication
     *         request.
     */
    public String getClientIdentifier()
    {
        if (clientIdAliasUsed)
        {
            return clientIdAlias;
        }
        else
        {
            return String.valueOf(clientId);
        }
    }


    /**
     * Get the name of the client application which has made the backchannel
     * authentication request.
     *
     * @return
     *         The name of the client application.
     */
    public String getClientName()
    {
        return clientName;
    }


    /**
     * Set the name of the client application which has made the backchannel
     * authentication request.
     *
     * @param name
     *         The name of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setClientName(String name)
    {
        this.clientName = name;

        return this;
    }


    /**
     * Get the backchannel token delivery mode.
     *
     * @return
     *         The backchannel token delivery mode.
     */
    public DeliveryMode getDeliveryMode()
    {
        return deliveryMode;
    }


    /**
     * Set the backchannel token delivery mode.
     *
     * @param deliveryMode
     *         The backchannel token delivery mode.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setDeliveryMode(DeliveryMode deliveryMode)
    {
        this.deliveryMode = deliveryMode;

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


    /**
     * Get the value of the {@code "auth_req_id"} which is associated with
     * the ticket.
     *
     * @return
     *         The value of the {@code "auth_req_id"}.
     */
    public String getAuthReqId()
    {
        return authReqId;
    }


    /**
     * Set the value of the {@code "auth_req_id"} which is associated with
     * the ticket.
     *
     * @param authReqId
     *         The value of the {@code "auth_req_id"}.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setAuthReqId(String authReqId)
    {
        this.authReqId = authReqId;

        return this;
    }


    /**
     * Get the issued access token. This method returns a non-null value only
     * when the backchannel token delivery mode is "push" and an access token
     * has been issued successfully.
     *
     * @return
     *         The issued access token.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the issued access token.
     *
     * @param accessToken
     *         The issued access token.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the issued refresh token. This method returns a non-null value only
     * when the backchannel token delivery mode is "push" and a refresh token
     * has been issued successfully.
     *
     * <p>
     * Note that refresh tokens are not issued if the service does not support
     * the refresh token flow.
     * </p>
     *
     * @return
     *         The issued refresh token.
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the issued refresh token.
     *
     * @param refreshToken
     *         The issued refresh token.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;

        return this;
    }


    /**
     * Get the issued ID token. This method returns a non-null value only
     * when the backchannel token delivery mode is "push" and an ID token
     * has been issued successfully.
     *
     * @return
     *         The issued ID token.
     */
    public String getIdToken()
    {
        return idToken;
    }


    /**
     * Set the issued ID token.
     *
     * @param idToken
     *         The issued ID token.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setIdToken(String idToken)
    {
        this.idToken = idToken;

        return this;
    }


    /**
     * Get the duration of the access token in seconds. If an access token has
     * not been issued, this method returns 0.
     *
     * @return
     *         The duration of the access token in seconds.
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
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;

        return this;
    }


    /**
     * Get the duration of the refresh token in seconds. If a refresh token has
     * not been issued, this method returns 0.
     *
     * @return
     *         The duration of the refresh token in seconds.
     */
    public long getRefreshTokenDuration()
    {
        return refreshTokenDuration;
    }


    /**
     * Set the duration of the refresh token in seconds.
     *
     * @param duration
     *         The duration of the refresh token in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setRefreshTokenDuration(long duration)
    {
        this.refreshTokenDuration = duration;

        return this;
    }


    /**
     * Get the duration of the ID token in seconds. If an ID token has not
     * been issued, this method returns 0.
     *
     * @return
     *         The duration of the ID token in seconds.
     */
    public long getIdTokenDuration()
    {
        return idTokenDuration;
    }


    /**
     * Set the duration of the ID token in seconds.
     *
     * @param duration
     *         The duration of the ID token in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteResponse setIdTokenDuration(long duration)
    {
        this.idTokenDuration = duration;

        return this;
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
     * @return
     *         {@code this} object.
     *
     * @since 2.37
     */
    public BackchannelAuthenticationCompleteResponse setJwtAccessToken(String jwtAccessToken)
    {
        this.jwtAccessToken = jwtAccessToken;

        return this;
    }


    /**
     * Get the resources specified by the {@code resource} request parameters
     * or by the {@code resource} property in the request object in the
     * preceding backchannel authentication request. If both are given, the
     * values in the request object take precedence.
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     *
     * @return
     *         Target resources.
     *
     * @since 2.62
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Set the resources specified by the {@code resource} request parameters
     * or by the {@code resource} property in the request object in the
     * preceding backchannel authentication request. If both are given, the
     * values in the request object should be set.
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     *
     * @param resources
     *         Target resources.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.62
     */
    public BackchannelAuthenticationCompleteResponse setResources(URI[] resources)
    {
        this.resources = resources;

        return this;
    }


    /**
     * Get the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @return
     *         Authorization details.
     *
     * @since 2.56
     */
    public AuthzDetails getAuthorizationDetails()
    {
        return authorizationDetails;
    }


    /**
     * Set the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @param details
     *         Authorization details.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.56
     */
    public BackchannelAuthenticationCompleteResponse setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;

        return this;
    }
}
