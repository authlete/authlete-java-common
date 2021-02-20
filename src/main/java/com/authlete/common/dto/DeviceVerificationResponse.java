/*
 * Copyright (C) 2019-2021 Authlete, Inc.
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

/**
 * Response from Authlete's {@code /api/device/verification} API.
 *
 * <p>
 * Authlete's {@code /api/device/verification} API returns JSON which can
 * be mapped to this class. The authorization server implementation should
 * retrieve the value of {@code action} from the response and take the
 * following steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#VALID VALID}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code VALID}, it means that the user
 * code exists, has not expired, and belongs to the service. The authorization
 * server implementation should interact with the end-user to ask whether she
 * approves or rejects the authorization request from the device.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#EXPIRED EXPIRED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code EXPIRED}, it means that the user
 * code has expired. The authorization server implementation should tell the
 * end-user that the user code has expired and urge her to re-initiate a device
 * flow.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#NOT_EXIST NOT_EXIST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code NOT_EXIST}, it means that the
 * user code does not exist. The authorization server implementation should
 * tell the end-user that the user code is invalid and urge her to retry to
 * input a valid user code.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#SERVER_ERROR SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code SERVER_ERROR}, it means that an
 * error occurred on Authlete side. The authorization server implementation
 * should tell the end-user that something wrong happened and urge her to
 * re-initiate a device flow.
 * </p>
 * </dd>
 * </dl>
 *
 * @since 2.42
 */
public class DeviceVerificationResponse extends ApiResponse
{
    private static final long serialVersionUID = 6L;


    /**
     * The next action that the authorization server implementation should take.
     */
    public enum Action
    {
        /**
         * The user code is valid. This means that the user code exists, has
         * not expired, and belongs to the service. The authorization server
         * implementation should interact with the end-user to ask whether she
         * approves or rejects the authorization request from the device.
         */
        VALID,


        /**
         * The user code has expired. The authorization server implementation
         * should tell the end-user that the user code has expired and urge her
         * to re-initiate a device flow.
         */
        EXPIRED,


        /**
         * The user code does not exist. The authorization server implementation
         * should tell the end-user that the user code is invalid and urge her
         * to retry to input a valid user code.
         */
        NOT_EXIST,


        /**
         * An error occurred on Authlete side. The authorization server
         * implementation should tell the end-user that something wrong happened
         * and urge her to re-initiate a device flow.
         */
        SERVER_ERROR,
    }


    private Action action;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String clientName;
    private Scope[] scopes;
    private String[] claimNames;
    private String[] acrs;
    private long expiresAt;
    private URI[] resources;
    private AuthzDetails authorizationDetails;
    private Pair[] serviceAttributes;
    private Pair[] clientAttributes;


    /**
     * Get the next action that the authorization server should take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the authorization server should take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceVerificationResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the client ID of the client application to which the user code has
     * been issued.
     *
     * @return
     *         The client ID of the client application.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID of the client application to which the user code has
     * been issued.
     *
     * @param clientId
     *         The client ID of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceVerificationResponse setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client ID alias of the client application to which the user code
     * has been issued.
     *
     * @return
     *         The client ID alias of the client application.
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the client ID alias of the client application to which the user code
     * has been issued.
     *
     * @param alias
     *         The client ID alias of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceVerificationResponse setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;

        return this;
    }


    /**
     * Get the flag which indicates whether the client ID alias was used in
     * the device authorization request for the user code.
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
     * the device authorization request for the user code.
     *
     * @param used
     *         {@code true} to indicate that the client ID alias was used in
     *         the request.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceVerificationResponse setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;

        return this;
    }


    /**
     * Get the client identifier used in the device authorization request for
     * the user code.
     *
     * <p>
     * When {@link #isClientIdAliasUsed()} returns {@code true}, this method
     * returns the same value as {@link #getClientIdAlias()} does. Otherwise,
     * this method returns the string representation of the value returned
     * from {@link #getClientId()}.
     * </p>
     *
     * @return
     *         The client identifier used in the device authorization request
     *         for the user code.
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
     * Get the name of the client application to which the user code has been
     * issued.
     *
     * @return
     *         The name of the client application.
     */
    public String getClientName()
    {
        return clientName;
    }


    /**
     * Set the name of the client application to which the user code has been
     * issued.
     *
     * @param name
     *         The name of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceVerificationResponse setClientName(String name)
    {
        this.clientName = name;

        return this;
    }


    /**
     * Get the scopes requested by the device authorization request for the
     * user code.
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
     * Set the scopes requested by the device authorization request for the
     * user code.
     *
     * @param scopes
     *         The requested scopes.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceVerificationResponse setScopes(Scope[] scopes)
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
     * <p>
     * This method always returns {@code null} if the {@code scope} request
     * parameter of the device authorization request does not include the
     * {@code openid} scope even if special scopes (such as {@code profile})
     * are included in the request (unless the {@code openid} scope is included
     * in the default set of scopes which is used when the {@code scope}
     * request parameter is omitted).
     * </p>
     *
     * @return
     *         The names of the requested claims.
     *
     * @since 2.44
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
     *
     * @since 2.44
     */
    public DeviceVerificationResponse setClaimNames(String[] names)
    {
        this.claimNames = names;

        return this;
    }


    /**
     * Get the list of ACR values requested by the device authorization
     * request.
     *
     * @return
     *         The list of requested ACR values.
     *
     * @since 2.44
     */
    public String[] getAcrs()
    {
        return acrs;
    }


    /**
     * Set the list of ACR values requested by the device authorization
     * request.
     *
     * @param acrs
     *         The list of requested ACR values.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.44
     */
    public DeviceVerificationResponse setAcrs(String[] acrs)
    {
        this.acrs = acrs;

        return this;
    }


    /**
     * Get the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the user code will expire.
     *
     * @return
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the user code will expire.
     *
     * @since 2.44
     */
    public long getExpiresAt()
    {
        return expiresAt;
    }


    /**
     * Set the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the user code will expire.
     *
     * @param expiresAt
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the user code will expire.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.44
     */
    public DeviceVerificationResponse setExpiresAt(long expiresAt)
    {
        this.expiresAt = expiresAt;

        return this;
    }


    /**
     * Get the resources specified by the {@code resource} request parameters
     * in the preceding device authorization request.
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
     * in the preceding device authorization request.
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
    public DeviceVerificationResponse setResources(URI[] resources)
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
    public DeviceVerificationResponse setAuthorizationDetails(AuthzDetails details)
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
    public DeviceVerificationResponse setServiceAttributes(Pair[] attributes)
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
    public DeviceVerificationResponse setClientAttributes(Pair[] attributes)
    {
        this.clientAttributes = attributes;

        return this;
    }
}
