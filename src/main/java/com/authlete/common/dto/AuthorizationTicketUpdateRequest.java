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


import java.io.Serializable;


/**
 * Request to Authlete's {@code /auth/authorization/ticket/update} API.
 *
 * <p>
 * The API is used to update information about a ticket that has been issued
 * from the {@code /auth/authorization} API.
 * </p>
 *
 * @since 3.88
 * @since Authlete 3.0
 */
public class AuthorizationTicketUpdateRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The ticket.
     */
    private String ticket;


    /**
     * The information about the ticket.
     */
    private AuthorizationTicketInfo info;


    /**
     * Get the ticket that has been issued from the {@code /auth/authorization} API.
     *
     * @return
     *         The ticket.
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the ticket that has been issued from the {@code /auth/authorization} API.
     *
     * @param ticket
     *         The ticket.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationTicketUpdateRequest setTicket(String ticket)
    {
        this.ticket = ticket;

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
    public AuthorizationTicketUpdateRequest setInfo(AuthorizationTicketInfo info)
    {
        this.info = info;

        return this;
    }
}
