/*
 * Copyright (C) 2014-2015 Authlete, Inc.
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
 * <dt><b><code>subject</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The subject (= unique identifier) of the authenticated user.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * <p>
 * {@code subject} request parameter was added as a required parameter
 * on version 1.13.
 * </p>
 *
 * @see TokenResponse
 *
 * @author Takahiko Kawasaki
 */
public class TokenIssueRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The ticket issued by Authlete's endpoint.
     */
    private String ticket;


    /**
     * The subject (unique identifier) of the authenticated user.
     */
    private String subject;


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


    /**
     * Get the value of {@code "subject"} which is the unique
     * identifier of the authenticated user.
     *
     * @return
     *         The subject of the authenticated user.
     *
     * @since 1.13
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the value of {@code "subject"} which is the unique
     * identifier of the authenticated user.
     *
     * @param subject
     *         The subject of the authenticated user.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.13
     */
    public TokenIssueRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }
}
