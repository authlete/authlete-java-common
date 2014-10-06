/*
 * Copyright (C) 2014 Authlete, Inc.
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
 * Request to Authlete's {@code /auth/token/issue} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>ticket</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The ticket issued by Authlete's {@code /auth/token} API to the
 * service implementation. It is the value of {@code "ticket"}
 * contained in the response from Authlete's {@code /auth/token}
 * API ({@link TokenResponse}).
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see TokenResponse
 *
 * @author Takahiko Kawasaki
 */
public class TokenIssueRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The ticket issued by Authlete's endpoint.
     */
    private String ticket;


    /**
     * Get the value of {@code "ticket"} which is the ticket
     * issued by Authlete's {@code /auth/token} API to the
     * service implementation.
     *
     * @return
     *         The ticket.
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the value of {@code "ticket"} which is the ticket
     * issued by Authlete's {@code /auth/token} API to the
     * service implementation.
     *
     * @param ticket
     *         The ticket.
     *
     * @return
     *         {@code this} object.
     */
    public TokenIssueRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }
}
