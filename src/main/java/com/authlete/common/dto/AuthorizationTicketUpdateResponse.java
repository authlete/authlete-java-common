/*
 * Copyright (C) 2023 Authlete, Inc.
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
 * Response from Authlete's {@code /auth/authorization/ticket/update} API.
 *
 * <p>
 * The API is used to update information about a ticket that has been issued
 * from the {@code /auth/authorization} API.
 * </p>
 *
 * @since 3.88
 * @since Authlete 3.0
 */
public class AuthorizationTicketUpdateResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The result of the {@code /auth/authorization/ticket/update} API call.
     */
    public enum Action
    {
        /**
         * Information about the ticket has been updated successfully.
         */
        OK,


        /**
         * The ticket was not found.
         */
        NOT_FOUND,


        /**
         * The API call was wrong. For example, the {@code ticket} request
         * parameter was missing.
         */
        CALLER_ERROR,


        /**
         * An error occurred on Authlete side.
         */
        AUTHLETE_ERROR,
    }


    /**
     * The result of the {@code /auth/authorization/ticket/info} API call.
     */
    private Action action;


    /**
     * Information about the ticket.
     */
    private AuthorizationTicketInfo info;


    /**
     * Get the result of the {@code /auth/authorization/ticket/update} API call.
     *
     * @return
     *         The result of the API call.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the result of the {@code /auth/authorization/ticket/update} API call.
     *
     * @param action
     *         The result of the API call.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationTicketUpdateResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the information about the ticket.
     *
     * @return
     *         The information about the ticket.
     */
    public AuthorizationTicketInfo getInfo()
    {
        return info;
    }


    /**
     * Set the information about the ticket.
     *
     * @param info
     *         The information about the ticket.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationTicketUpdateResponse setInfo(AuthorizationTicketInfo info)
    {
        this.info = info;

        return this;
    }
}
