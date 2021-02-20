/*
 * Copyright (C) 2018-2021 Authlete, Inc.
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


import java.net.URI;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.DeliveryMode;
import com.authlete.common.types.UserIdentificationHintType;


/**
 * Response from Authlete's {@code /api/backchannel/authentication} API.
 *
 * <p>
 * Authlete's {@code /api/backchannel/authentication} API returns JSON which
 * can be mapped to this class. The authorization server implementation should
 * retrieve the value of {@code action} from the response and take the
 * following steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code BAD_REQUEST}, it means that the
 * backchannel authentication request from the client application was wrong.
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
 * client authentication of the backchannel authentication request failed.
 * Note that client authentication is always required at the backchannel
 * authentication endpoint. This implies that public clients are not allowed
 * to use the backchannel authentication endpoint.
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
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#USER_IDENTIFICATION USER_IDENTIFICATION}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code USER_IDENTIFICATION}, it means
 * that the backchannel authentication request from the client application is
 * valid. The authorization server implementation has to follow the steps below.
 * </p>
 *
 * <ol>
 * <li>
 * <p><b>[END-USER IDENTIFICATION]</b></p>
 * <br/>
 *
 * <p>
 * The first step is to determine the subject (= unique identifier) of the
 * end-user from whom the client application wants to get authorization.
 * </p>
 * <br/>
 *
 * <p>
 * According to the CIBA specification, a backchannel authentication request
 * contains one (and only one) of the {@code login_hint_token},
 * {@code id_token_hint} and {@code login_hint} request parameters as a hint
 * by which the authorization server identifies the subject of an end-user.
 * </p>
 * <br/>
 *
 * <p>
 * The authorization server implementation can know which hint is included in
 * the backchannel authentication request by calling the {@link #getHintType()}
 * method. The method returns a {@link UserIdentificationHintType} instance
 * that indicates which hint is included. For example, when the method returns
 * {@link UserIdentificationHintType#LOGIN_HINT LOGIN_HINT}, it means that the
 * backchannel authentication request contains the {@code login_hint} request
 * parameter as a hint.
 * </p>
 * <br/>
 *
 * <p>
 * The {@link #getHint()} method returns the value of the hint. For example,
 * when the {@code getHintType()} method returns {@code LOGIN_HINT}, the
 * {@code getHint()} method returns the value of the {@code login_hint} request
 * parameter.
 * </p>
 * <br/>
 *
 * <p>
 * It is up to the authorization server implementation how to determine the
 * subject of the end-user from the hint. There are few things Authlete can
 * help. Only one thing Authlete can do is to let the {@link #getSub()} method
 * return the value of the {@code sub} claim in the {@code id_token_hint}
 * request parameter when the request parameter is used.
 * </p>
 * <br/><br/>
 * </li>
 *
 * <li>
 * <p><b>[END-USER IDENTIFICATION ERROR]</b></p>
 * <br/>
 *
 * <p>
 * There are some cases where the authorization server implementation
 * encounters an error during the user identification process. In any error
 * case, the authorization server implementation has to return an HTTP response
 * with the {@code error} response parameter to the client application. The
 * following is an example of such error responses.
 * </p>
 * <br/>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * {"error":"unknown_user_id"}</pre>
 * <br/>
 *
 * <p>
 * Authlete provides {@code /api/backchannel/authentication/fail} API that
 * builds the response body (JSON) of an error response. However, because
 * it is easy to build an error response manually, you may choose not to
 * call the API. One good thing in using the API is that the API call can
 * trigger deletion of the ticket which has been issued from Authlete's
 * {@code /api/backchannel/authentication} API. If you don't call
 * {@code /api/backchannel/authentication/fail} API, the ticket will continue
 * to exist in the database until it is cleaned up by the batch program after
 * the ticket expires.
 * </p>
 * <br/>
 *
 * <p>
 * Possible error cases that the authorization server implementation itself
 * has to handle are as follows. Other error cases have already been covered
 * by {@code /api/backchannel/authentication} API.
 * </p>
 * <br/>
 *
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 * <thead>
 *   <tr>
 *     <th>{@code error}</th>
 *     <th>description</th>
 *   </tr>
 * </thead>
 * <tbody>
 *   <tr>
 *     <td>{@code expired_login_hint_token}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation detected that the hint
 *         presented by the {@code login_hint_token} request parameter has
 *         expired.
 *       </p>
 *       <br/>
 *       <p>
 *         Note that the format of {@code login_hint_token} is not described in
 *         the CIBA Core spec at all and so there is no consensus on how to
 *         detect expiration of {@code login_hint_token}. Interpretation of
 *         {@code login_hint_token} is left to each authorization server
 *         implementation.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code unknown_user_id}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation could not determine the
 *         subject of the end-user by the presented hint.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code unauthorized_client}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation has custom rules to reject
 *         backchannel authentication requests from some particular clients and
 *         found that the client which has made the backchannel authentication
 *         request is one of the particular clients.
 *       <p>
 *       <br/>
 *       <p>
 *         Note that {@code /api/backchannel/authentication} API does not
 *         return {@code action=USER_IDENTIFICATION} in cases where the client
 *         does not exist or client authentication has failed. Therefore, the
 *         authorization server implementation will never have to use the error
 *         code {@code unauthorized_client} unless the server has intentionally
 *         implemented custom rules to reject backchannel authentication
 *         requests based on clients.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code missing_user_code}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation has custom rules to require
 *         that a backchannel authentication request include a user code for
 *         some particular users and found that the user identified by the hint
 *         is one of the particular users.
 *       </p>
 *       <br/>
 *       <p>
 *         Note that {@code /api/backchannel/authentication} API does not
 *         return {@code action=USER_IDENTIFICATION} when both the
 *         {@code backchannel_user_code_parameter_supported} metadata of the
 *         server and the {@code backchannel_user_code_parameter} metadata of
 *         the client are {@code true} and the backchannel authentication
 *         request does not include the {@code user_code} request parameter.
 *         In this case, {@code /api/backchannel/authentication} API returns
 *         {@code action=BAD_REQUEST} with JSON containing
 *         {@code "error":"missing_user_code"}. Therefore, the authorization
 *         server implementation will never have to use the error code
 *         {@code missing_user_code} unless the server has intentionally
 *         implemented custom rules to require a user code based on users
 *         even in the case where the {@code backchannel_user_code_parameter}
 *         metadata of the client which has made the backchannel authentication
 *         request is {@code false}.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code invalid_user_code}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation detected that the presented
 *         user code is invalid.
 *       </p>
 *       <br/>
 *       <p>
 *         Note that the format of {@code user_code} is not described in the
 *         CIBA Core spec at all and so there is no consensus on how to judge
 *         whether a user code is valid or not. It is up to each authorization
 *         server implementation how to handle user codes.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code invalid_binding_message}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation detected that the presented
 *         binding message is invalid.
 *       </p>
 *       <br/>
 *       <p>
 *         Note that the format of {@code binding_message} is not described in
 *         the CIBA Core spec at all and so there is no consensus on how to
 *         judge whether a binding message is valid or not. It is up to each
 *         authorization server implementation how to handle binding messages.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code invalid_target}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation rejects the requested target
 *         resources.
 *       </p>
 *       <br/>
 *       <p>
 *         The error code {@code "invalid_target"} is from <i>"Resource Indicators
 *         for OAuth 2.0"</i>. The specification defines the {@code "resource"}
 *         request parameter. By using the parameter, client applications can
 *         request target resources that should be bound to the access token being
 *         issued. If the authorization server wants to reject the request, call
 *         {@code /api/backchannel/authentication/fail} API with
 *         {@code INVALID_TARGET}.
 *       </p>
 *       <br/>
 *       <p>
 *         Note that <i>"Resource Indicators for OAuth 2.0"</i> is supported since
 *         Authlete 2.2. Older versions don't recognize the {@code resource} request
 *         parameter, so {@link #getResources()} always returns null if the Authlete
 *         server you are using is older than 2.2.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code access_denined}</td>
 *     <td>
 *       <p>
 *         The authorization server implementation has custom rules to reject
 *         backchannel authentication requests without asking the end-user and
 *         respond to the client as if the end-user had rejected the request in
 *         some particular cases and found that the backchannel authentication
 *         request is one of the particular cases.
 *       </p>
 *       <br/>
 *       <p>
 *         The authorization server implementation will never have to use the
 *         error code {@code access_denied} at this timing unless the server
 *         has intentionally implemented custom rules to reject backchannel
 *         authentication requests without asking the end-user and respond to
 *         the client as if the end-user had rejected the request.
 *       </p>
 *     </td>
 *   </tr>
 * </tbody>
 * </table>
 * <br/><br/>
 * </li>
 *
 * <li>
 * <p><b>[AUTH_REQ_ID ISSUE]</b></p>
 * <br/>
 *
 * <p>
 * If the authorization server implementation has successfully determined the
 * subject of the end-user, the next action is to return an HTTP response to
 * the client application which contains {@code auth_req_id}.
 * </p>
 * <br/>
 *
 * <p>
 * Authlete provides {@code /api/backchannel/authentication/issue} API which
 * generates a JSON containing {@code auth_req_id}, so, your next action is
 * (1) call the API, (2) receive the response from the API, (3) build a
 * response to the client application using the content of the API response,
 * and (4) return the response to the client application. See the description
 * of {@code /api/backchannel/authentication/issue} API for details.
 * </p>
 * <br/><br/>
 * </li>
 *
 * <li>
 * <p><b>[END-USER AUTHENTICATION AND AUTHORIZATION]</b></p>
 * <br/>
 *
 * <p>
 * After sending a JSON containing {@code auth_req_id} back to the client
 * application, the authorization server implementation starts to communicate
 * with an <b>authentication device</b> of the end-user. It is assumed that
 * end-user authentication is performed on the authentication device and the
 * end-user confirms the content of the backchannel authentication request
 * and grants authorization to the client application if everything is okay.
 * The authorization server implementation must be able to receive the result
 * of the end-user authentication and authorization from the authentication
 * device.
 * </p>
 * <br/>
 *
 * <p>
 * How to communicate with an authentication device and achieve end-user
 * authentication and authorization is up to each authorization server
 * implementation, but the following request parameters of the backchannel
 * authentication request should be taken into consideration in any
 * implementation.
 * </p>
 * <br/>
 *
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 * <thead>
 *   <tr>
 *     <th>parameter</th>
 *     <th>method</th>
 *     <th>description</th>
 *   </tr>
 * </thead>
 * <tbody>
 *   <tr>
 *     <td>{@code acr_values}</td>
 *     <td>{@link #getAcrs()}</td>
 *     <td>
 *       <p>
 *         A backchannel authentication request may contain an array of ACRs
 *         (Authentication Context Class References) in preference order. If
 *         multiple authentication devices are registered for the end-user,
 *         the authorization server implementation should take the ACRs into
 *         consideration when selecting the best authentication device.
 *       </p>
 *     </td>
 *   <tr/>
 *   <tr>
 *     <td>{@code scope}</td>
 *     <td>{@link #getScopes()}</td>
 *     <td>
 *       <p>
 *         A backchannel authentication request always contains a list of
 *         scopes. At least, {@code openid} is included in the list (otherwise
 *         {@code /api/backchannel/authentication} API returns
 *         {@code action=BAD_REQUEST}). It would be better to show the
 *         requested scopes to the end-user on the authentication device or
 *         somewhere appropriate.
 *       </p>
 *       <br/>
 *       <p>
 *         If the {@code scope} request parameter contains {@code address},
 *         {@code email}, {@code phone} and/or {@code profile}, they are
 *         interpreted as defined in <a href=
 *         "https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
 *         >5.4. Requesting Claims using Scope Values</a> of OpenID Connect
 *         Core 1.0. That is, they are expanded into a list of claim names.
 *         The {@link #getClaimNames()} method returns the expanded result.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code binding_message}</td>
 *     <td>{@link #getBindingMessage()}</td>
 *     <td>
 *       <p>
 *         A backchannel authentication request may contain a binding message.
 *         It is a human readable identifier or message intended to be
 *         displayed on both the consumption device (client application) and
 *         the authentication device.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code user_code}</td>
 *     <td>{@link #getUserCode()}</td>
 *     <td>
 *       <p>
 *         A backchannel authentication request may contain a user code.
 *         It is a secret code, such as password or pin, known only to the
 *         end-user but verifiable by the authorization server. The user code
 *         should be used to authorize sending a request to the authentication
 *         device.
 *       </p>
 *     </td>
 *   </tr>
 * </tbody>
 * </table>
 * <br/><br/>
 * </li>
 *
 * <li>
 * <p><b>[END-USER AUTHENTICATION AND AUTHORIZATION COMPLETION]</b></p>
 * <br/>
 *
 * <p>
 * After receiving the result of end-user authentication and authorization, the
 * authorization server implementation must call Authlete's
 * {@code /api/backchannel/authentication/complete} to tell Authlete the result
 * and pass necessary data so that Authlete can generate an ID token, an access
 * token and optionally a refresh token. See the description of the API for
 * details.
 * </p>
 * <br/><br/>
 * </li>
 *
 * <li>
 * <p><b>[CLIENT NOTIFICATION]</b></p>
 * <br/>
 *
 * <p>
 * When the backchannel token delivery mode is either
 * {@link DeliveryMode#PING ping} or {@link DeliveryMode#PUSH push}, the
 * authorization server implementation must send a notification to the
 * pre-registered notification endpoint of the client after the end-user
 * authentication and authorization. In this case,
 * {@link BackchannelAuthenticationCompleteResponse#getAction() getAction()}
 * method of {@link BackchannelAuthenticationCompleteResponse} (a response
 * from {@code /api/backchannel/authentication/complete} API) returns
 * {@link BackchannelAuthenticationCompleteResponse.Action#NOTIFICATION
 * NOTIFICATION}. See the description of
 * {@code /api/backchannel/authentication/complete} API for details.
 * </p>
 * <br/><br/>
 * </li>
 *
 * <li>
 * <p><b>[TOKEN REQUEST]</b></p>
 * <br/>
 *
 * <p>
 * When the backchannel token delivery mode is either
 * {@link DeliveryMode#PING ping} or {@link DeliveryMode#POLL poll}, the
 * client application will make a token request to the token endpoint to
 * get an ID token, an access token and optionally a refresh token.
 * </p>
 * <br/>
 *
 * <p>
 * A token request that corresponds to a backchannel authentication request
 * uses {@code urn:openid:params:grant-type:ciba} as
 * the value of the {@code grant_type} request parameter. Authlete's
 * {@code /api/auth/token} API recognizes the grant type automatically and
 * behaves properly, so the existing token endpoint implementation does not
 * have to be changed to support CIBA.
 * </p>
 * </li>
 *
 * </ol>
 * </dd>
 * </dl>
 *
 * @since 2.32
 */
public class BackchannelAuthenticationResponse extends ApiResponse
{
    private static final long serialVersionUID = 8L;


    /**
     * The next action that the OpenID provider implementation should take.
     */
    public enum Action
    {
        /**
         * The backchannel authentication request is invalid. The authorization
         * server implementation should return an error response with
         * {@code 400 Bad Request} and {@code application/json} to the client
         * application.
         */
        BAD_REQUEST,


        /**
         * Client authentication of the backchannel authentication request
         * failed. The authorization server implementation should return an
         * error response with {@code 401 Unauthorized} and
         * {@code application/json} to the client application.
         */
        UNAUTHORIZED,


        /**
         * The API call from the authorization server implementation was wrong
         * or an error occurred on Authlete side. The authorization server
         * implementation should return an error response with
         * {@code 500 Internal Server Error} and {@code application/json} to
         * the client application.
         */
        INTERNAL_SERVER_ERROR,


        /**
         * The backchannel authentication request was valid. The authorization
         * server implementation is required to (1) identify the subject of the
         * end-user from the given hint, (2) issue {@code auth_req_id} to the
         * client application, (3) communicate with an authentication device of
         * the end-user to perform end-user authentication and authorization, etc.
         * See the API document of {@link BackchannelAuthenticationResponse}
         * for details.
         */
        USER_IDENTIFICATION,
    }


    private Action action;
    private String responseContent;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String clientName;
    private ClientAuthMethod clientAuthMethod;
    private DeliveryMode deliveryMode;
    private Scope[] scopes;
    private String[] claimNames;
    private String clientNotificationToken;
    private String[] acrs;
    private UserIdentificationHintType hintType;
    private String hint;
    private String sub;
    private String bindingMessage;
    private String userCode;
    private boolean userCodeRequired;
    private int requestedExpiry;
    private String requestContext;
    private URI[] resources;
    private AuthzDetails authorizationDetails;
    private Pair[] serviceAttributes;
    private Pair[] clientAttributes;
    private String[] warnings;
    private String ticket;


    /**
     * Get the next action that the implementation of the backchannel
     * authentication endpoint should take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the backchannel
     * authentication endpoint should take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content that can be used to generate a response to the client
     * application.
     *
     * <p>
     * When this method returns a non-null value, it is JSON containing error
     * information. When {@link #getAction()} returns
     * {@link Action#USER_IDENTIFICATION USER_IDENTIFICATION}, this method
     * returns {@code null}.
     * </p>
     *
     * @return
     *         The content of a response to the client.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content that can be used to generate a response to the client
     * application.
     *
     * @param responseContent
     *         The content of a response to the client.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setResponseContent(String responseContent)
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
    public BackchannelAuthenticationResponse setClientId(long clientId)
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
    public BackchannelAuthenticationResponse setClientIdAlias(String alias)
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
    public BackchannelAuthenticationResponse setClientIdAliasUsed(boolean used)
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
    public BackchannelAuthenticationResponse setClientName(String name)
    {
        this.clientName = name;

        return this;
    }


    /**
     * Get the client authentication method that should be performed at the
     * backchannel authentication endpoint.
     *
     * <p>
     * If the client could not be identified by the information in the request,
     * this method returns {@code null}.
     * </p>
     *
     * @return
     *         The client authentication method that should be performed at
     *         the backchannel authentication endpoint.
     *
     * @since 2.50
     */
    public ClientAuthMethod getClientAuthMethod()
    {
        return clientAuthMethod;
    }


    /**
     * Set the client authentication method that should be performed at the
     * backchannel authentication endpoint.
     *
     * @param method
     *         The client authentication method that should be performed at
     *         the backchannel authentication endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.50
     */
    public BackchannelAuthenticationResponse setClientAuthMethod(ClientAuthMethod method)
    {
        this.clientAuthMethod = method;

        return this;
    }


    /**
     * Get the backchannel token delivery mode of the client application.
     *
     * @return
     *         The backchannel token delivery mode.
     */
    public DeliveryMode getDeliveryMode()
    {
        return deliveryMode;
    }


    /**
     * Set the backchannel token delivery mode of the client application.
     *
     * @param mode
     *         The backchannel token delivery mode.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setDeliveryMode(DeliveryMode mode)
    {
        this.deliveryMode = mode;

        return this;
    }


    /**
     * Get the scopes requested by the backchannel authentication request.
     *
     * <p>
     * Basically, this method returns the value of the {@code "scope"} request
     * parameter in the backchannel authentication request. However, because
     * unregistered scopes are dropped on Authlete side, if the {@code "scope"}
     * request parameter contains unknown scopes, the list returned by this
     * method becomes different from the value of the {@code "scope"} request
     * parameter.
     * </p>
     *
     * <p>
     * Note that {@link Scope#getDescription()} method and
     * {@link Scope#getDescriptions()} method of each element ({@link Scope}
     * instance) in the array returned from this method always return
     * {@code null} even if descriptions of the scopes are registered.
     * </p>
     *
     * @return
     *         The requested scopes.
     */
    public Scope[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes requested by the backchannel authentication request.
     *
     * @param scopes
     *         The requested scopes.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setScopes(Scope[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the names of the claims which were requested indirectly via some
     * special scopes. See <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     * >5.4. Requesting Claims using Scope Values</a> in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect
     * Core 1.0</a> for details.
     *
     * @return
     *         The names of the requested claims.
     */
    public String[] getClaimNames()
    {
        return claimNames;
    }


    /**
     * Set the names of the claims which were requested indirectly via some
     * special scopes.
     *
     * @param names
     *         The names of the requested claims.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setClaimNames(String[] names)
    {
        this.claimNames = names;

        return this;
    }


    /**
     * Get the client notification token included in the backchannel
     * authentication request. It is the value of the
     * {@code client_notification_token} request parameter.
     *
     * <p>
     * When the backchannel token delivery mode is {@code "ping"} or
     * {@code "push"}, the backchannel authentication request must include a
     * client notification token.
     * </p>
     *
     * @return
     *         The client notification token included in the backchannel
     *         authentication request.
     */
    public String getClientNotificationToken()
    {
        return clientNotificationToken;
    }


    /**
     * Set the client notification token included in the backchannel
     * authentication request. It is the value of the
     * {@code client_notification_token} request parameter.
     *
     * <p>
     * When the backchannel token delivery mode is {@code "ping"} or
     * {@code "push"}, the backchannel authentication request must include a
     * client notification token.
     * </p>
     *
     * @param token
     *         The client notification token included in the backchannel
     *         authentication request.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setClientNotificationToken(String token)
    {
        this.clientNotificationToken = token;

        return this;
    }


    /**
     * Get the list of ACR values requested by the backchannel authentication
     * request.
     *
     * <p>
     * Basically, this method returns the value of the {@code "acr_values"}
     * request parameter in the backchannel authentication request. However,
     * because unsupported ACR values are dropped on Authlete side, if the
     * {@code "acr_values"} request parameter contains unrecognized ACR
     * values, the list returned by this method becomes different from the
     * value of the {@code "acr_values"} request parameter.
     * </p>
     *
     * @return
     *         The list of requested ACR values.
     */
    public String[] getAcrs()
    {
        return acrs;
    }


    /**
     * Set the list of ACR values requested by the backchannel authentication
     * request.
     *
     * @param acrs
     *         The list of requested ACR values.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setAcrs(String[] acrs)
    {
        this.acrs = acrs;

        return this;
    }


    /**
     * Get the type of the hint for end-user identification which was
     * included in the backchannel authentication request.
     *
     * <p>
     * When the backchannel authentication request contains
     * {@code "id_token_hint"}, this method returns
     * {@link UserIdentificationHintType#ID_TOKEN_HINT ID_TOKEN_HINT}.
     * Likewise, this method returns
     * {@link UserIdentificationHintType#LOGIN_HINT LOGIN_HINT} when the
     * request contains {@code "login_hint"}, or returns
     * {@link UserIdentificationHintType#LOGIN_HINT_TOKEN LOGIN_HINT_TOKEN}
     * when the request contains {@code "login_hint_token"}.
     * </p>
     *
     * <p>
     * Note that a backchannel authentication request must include one and
     * only one hint among {@code "id_token_hint"}, {@code "login_hint"} and
     * {@code "login_hint_token"}.
     * </p>
     *
     * @return
     *         The type of the hint for end-user identification.
     */
    public UserIdentificationHintType getHintType()
    {
        return hintType;
    }


    /**
     * Set the type of the hint for end-user identification which was
     * included in the backchannel authentication request.
     *
     * @param hintType
     *         The type of the hint for end-user identification.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setHintType(UserIdentificationHintType hintType)
    {
        this.hintType = hintType;

        return this;
    }


    /**
     * Get the value of the hint for end-user identification.
     *
     * <p>
     * When {@link #getHintType()} returns
     * {@link UserIdentificationHintType#ID_TOKEN_HINT ID_TOKEN_HINT},
     * this method returns the value of the {@code "id_token_hint"} request
     * parameter. Likewise, this method returns the value of the
     * {@code "login_hint"} request parameter when {@link #getHintType()}
     * returns {@link UserIdentificationHintType#LOGIN_HINT LOGIN_HINT},
     * or returns the value of the {@code "login_hint_token"} request
     * parameter when {@link #getHintType()} returns
     * {@link UserIdentificationHintType#LOGIN_HINT_TOKEN LOGIN_HINT_TOKEN}.
     * </p>
     *
     * @return
     *         The value of the hint for end-user identification.
     */
    public String getHint()
    {
        return hint;
    }


    /**
     * Set the value of the hint for end-user identification.
     *
     * @param hint
     *         The value of the hint for end-user identification.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setHint(String hint)
    {
        this.hint = hint;

        return this;
    }


    /**
     * Get the value of the {@code "sub"} claim contained in the ID token hint
     * included in the backchannel authentication request.
     *
     * <p>
     * This method works only when the backchannel authentication request
     * contains the {@code "id_token_hint"} request parameter.
     * </p>
     *
     * @return
     *         The value of the {@code "sub"} claim contained in the ID token
     *         hint.
     */
    public String getSub()
    {
        return sub;
    }


    /**
     * Set the value of the {@code "sub"} claim contained in the ID token hint
     * included in the backchannel authentication request.
     *
     * @param sub
     *         The value of the {@code "sub"} claim contained in the ID token
     *         hint.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setSub(String sub)
    {
        this.sub = sub;

        return this;
    }


    /**
     * Get the user code included in the backchannel authentication request.
     * It is the value of the {@code "user_code"} request parameter.
     *
     * @return
     *         The user code.
     */
    public String getUserCode()
    {
        return userCode;
    }


    /**
     * Set the user code included in the backchannel authentication request.
     * It is the value of the {@code "user_code"} request parameter.
     *
     * @param userCode
     *         The user code.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setUserCode(String userCode)
    {
        this.userCode = userCode;

        return this;
    }


    /**
     * Get the requested expiry for the authentication request ID
     * ({@code auth_req_id}). It is the value of the {@code "requested_expiry"}
     * request parameter.
     *
     * @return
     *         The requested expiry in seconds.
     *
     * @since 2.35
     */
    public int getRequestedExpiry()
    {
        return requestedExpiry;
    }


    /**
     * Set the requested expiry for the authentication request ID
     * ({@code auth_req_id}). It is the value of the {@code "requested_expiry"}
     * request parameter.
     *
     * @param seconds
     *         The requested expiry in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.35
     */
    public BackchannelAuthenticationResponse setRequestedExpiry(int seconds)
    {
        this.requestedExpiry = seconds;

        return this;
    }


    /**
     * Get the flag which indicates whether a user code is required.
     *
     * <p>
     * This method returns {@code true} when both the
     * {@code backchannel_user_code_parameter} metadata of the client
     * (= {@link com.authlete.common.dto.Client Client}'s
     * {@code bcUserCodeRequired} property) and the
     * {@code backchannel_user_code_parameter_supported} metadata of the
     * service (= {@link com.authlete.common.dto.Service Service}'s
     * {@code backchannelUserCodeParameterSupported} property) are
     * {@code true}.
     * </p>
     *
     * @return
     *         {@code true} when a user code is required.
     *
     * @since 2.33
     */
    public boolean isUserCodeRequired()
    {
        return userCodeRequired;
    }


    /**
     * Set the flag which indicates whether a user code is required.
     *
     * @param required
     *         {@code true} to indicate that a user code is required.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.33
     */
    public BackchannelAuthenticationResponse setUserCodeRequired(boolean required)
    {
        this.userCodeRequired = required;

        return this;
    }


    /**
     * Get the binding message included in the backchannel authentication
     * request. It is the value of the {@code "binding_message"} request
     * parameter.
     *
     * @return
     *         The binding message.
     */
    public String getBindingMessage()
    {
        return bindingMessage;
    }


    /**
     * Set the binding message included in the backchannel authentication
     * request. It is the value of the {@code "binding_message"} request
     * parameter.
     *
     * @param message
     *         The binding message.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setBindingMessage(String message)
    {
        this.bindingMessage = message;

        return this;
    }


    /**
     * Get the request context of the backchannel authentication request. It
     * is the value of the {@code "request_context"} claim in the signed
     * authentication request and its format is JSON. {@code "request_context"}
     * is a new claim added by the FAPI-CIBA profile.
     *
     * <p>
     * This method returns {@code null} if the backchannel authentication
     * request does not include a {@code "request"} request parameter or
     * the JWT specified by the request parameter does not include a
     * {@code "request_context"} claim.
     * </p>
     *
     * @return
     *         The request context in JSON format.
     *
     * @since 2.45
     */
    public String getRequestContext()
    {
        return requestContext;
    }


    /**
     * Set the request context of the backchannel authentication request. It
     * is the value of the {@code "request_context"} claim in the signed
     * authentication request and its format is JSON. {@code "request_context"}
     * is a new claim added by the FAPI-CIBA profile.
     *
     * @param context
     *         The request context in JSON format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.45
     */
    public BackchannelAuthenticationResponse setRequestContext(String context)
    {
        this.requestContext = context;

        return this;
    }


    /**
     * Get the resources specified by the {@code resource} request parameters
     * or by the {@code resource} property in the request object. If both are
     * given, the values in the request object take precedence.
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
     * or by the {@code resource} property in the request object. If both are
     * given, the values in the request object should be set.
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
    public BackchannelAuthenticationResponse setResources(URI[] resources)
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
    public BackchannelAuthenticationResponse setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;

        return this;
    }


    /**
     * Get the attributes of the service that the client application belongs to.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @return
     *         The attributes of the service.
     *
     * @since 2.88
     */
    public Pair[] getServiceAttributes()
    {
        return serviceAttributes;
    }


    /**
     * Set the attributes of the service that the client application belongs to.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         The attributes of the service.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.88
     */
    public BackchannelAuthenticationResponse setServiceAttributes(Pair[] attributes)
    {
        this.serviceAttributes = attributes;

        return this;
    }


    /**
     * Get the attributes of the client.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @return
     *         The attributes of the client.
     *
     * @since 2.88
     */
    public Pair[] getClientAttributes()
    {
        return clientAttributes;
    }


    /**
     * Set the attributes of the client.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         The attributes of the client.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.88
     */
    public BackchannelAuthenticationResponse setClientAttributes(Pair[] attributes)
    {
        this.clientAttributes = attributes;

        return this;
    }


    /**
     * Get the warnings raised during processing the backchannel authentication
     * request.
     *
     * @return
     *         Warnings. This may be {@code null}.
     */
    public String[] getWarnings()
    {
        return warnings;
    }


    /**
     * Set the warnings raised during processing the backchannel authentication
     * request.
     *
     * @param warnings
     *         Warnings.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setWarnings(String[] warnings)
    {
        this.warnings = warnings;

        return this;
    }


    /**
     * Get the ticket that is necessary for the implementation of the
     * backchannel authentication endpoint to call
     * {@code /api/backchannel/authentication/*} API.
     *
     * @return
     *         The ticket issued from {@code /api/backchannel/authentication}
     *         API.
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the ticket that is necessary for the implementation of the
     * backchannel authentication endpoint to call
     * {@code /api/backchannel/authentication/*} API.
     *
     * @param ticket
     *         The ticket issued from {@code /api/backchannel/authentication}
     *         API.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationResponse setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }
}
