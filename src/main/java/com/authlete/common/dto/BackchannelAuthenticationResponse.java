/*
 * Copyright (C) 2018 Authlete, Inc.
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


import com.authlete.common.types.DeliveryMode;
import com.authlete.common.types.UserIdentificationHintType;


/**
 * Response from Authlete's {@code /api/backchannel/authentication} API.
 *
 * @since 2.32
 */
public class BackchannelAuthenticationResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the OpenID provider implementation should take.
     */
    public enum Action
    {
        BAD_REQUEST,
        UNAUTHORIZED,
        INTERNAL_SERVER_ERROR,
        USER_IDENTIFICATION,
    }


    private Action action;
    private String responseContent;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String clientName;
    private DeliveryMode deliveryMode;
    private Scope[] scopes;
    private String[] claimNames;
    private String clientNotificationToken;
    private String[] acrs;
    private UserIdentificationHintType hintType;
    private String hint;
    private String sub;
    private String bindingMessage;
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
     * authentication request.
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
     * authentication request.
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
     * backchannel authentication endpoint to call either
     * {@code /api/backchannel/authentication/issue} API or
     * {@code /api/backchannel/authentication/fail} API later.
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
     * backchannel authentication endpoint to call either
     * {@code /api/backchannel/authentication/issue} API or
     * {@code /api/backchannel/authentication/fail} API later.
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
