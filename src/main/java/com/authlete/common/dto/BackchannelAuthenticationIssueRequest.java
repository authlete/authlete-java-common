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


import java.io.Serializable;


/**
 * Request to Authlete's {@code /api/backchannel/authentication/issue} API.
 *
 * <p>
 * The API prepares JSON that contains an {@code auth_req_id}. The JSON should
 * be used as the response body of the response which is returned to the client
 * from the backchannel authentication endpoint.
 * </p>
 *
 * @since 2.32
 */
public class BackchannelAuthenticationIssueRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The ticket issued by Authlete's /api/backchannel/authentication API.
     */
    private String ticket;


    /**
     * Get the ticket which is necessary to call Authlete's
     * {@code /api/backchannel/authentication/issue} API.
     *
     * @return
     *         A ticket.
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the ticket which is necessary to call Authlete's
     * {@code /api/backchannel/authentication/issue} API.
     *
     * @param ticket
     *         A ticket previously issued by Authlete's
     *         {@code /api/backchannel/authentication} API.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationIssueRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }
}
