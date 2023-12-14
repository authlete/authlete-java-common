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
 * Request to Authlete's {@code /auth/authorization/ticket/info} API.
 *
 * <p>
 * The API is used to get information about a ticket that has been issued from
 * the {@code /auth/authorization} API.
 * </p>
 *
 * @since 3.88
 * @since Authlete 3.0
 */
public class AuthorizationTicketInfoRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The ticket that has been issued from the {@code /auth/authorization} API.
     */
    private String ticket;


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
    public AuthorizationTicketInfoRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }
}
