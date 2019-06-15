/*
 * Copyright (C) 2019 Authlete, Inc.
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
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code INTERNAL_SERVER_ERROR}, it means
 * that an error occurred on Authlete side. The authorization server
 * implementation should tell the end-user that something wrong happened and
 * urge her to re-initiate a device flow.
 * </p>
 * </dd>
 * </dl>
 *
 * @since 2.42
 */
public class DeviceVerificationResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


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
        INTERNAL_SERVER_ERROR,
    }


    private Action action;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String clientName;
    private Scope[] scopes;


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
}
