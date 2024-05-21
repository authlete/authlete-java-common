/*
 * Copyright (C) 2014-2019 Authlete, Inc.
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
 * Request for Authlete's {@code /auth/token/fail} API.
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
 *
 * <dt><b><code>reason</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The reason of the failure of the token request. See the
 * description of {@link TokenResponse} as to which reason should
 * be chosen.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see TokenResponse
 *
 * @author Takahiko Kawasaki
 */
public class TokenFailRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * Failure reasons of token requests.
     */
    public enum Reason
    {
        /**
         * Unknown reason.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"server_error"}.
         * </p>
         */
        UNKNOWN,

        /**
         * The resource owner's credentials ({@code username} and
         * {@code password}) contained in the token request whose
         * flow is <a href="http://tools.ietf.org/html/rfc6749#section-4.3">
         * "Resource Owner Password Credentials"</a>) are invalid.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"invalid_request"}.
         * </p>
         */
        INVALID_RESOURCE_OWNER_CREDENTIALS,

        /**
         * The requested resource is invalid, missing, unknown, or malformed.
         * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"invalid_target"}.
         * </p>
         *
         * @since 2.62
         */
        INVALID_TARGET,
        ;
    }


    /**
     * The ticket issued by Authlete's /auth/token API.
     */
    private String ticket;


    /**
     * The reason of the failure.
     */
    private Reason reason;


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
    public TokenFailRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }


    /**
     * Get the value of {@code "reason"} which is the reason
     * of the failure of the token request.
     *
     * @return
     *         The reason of the failure.
     */
    public Reason getReason()
    {
        return reason;
    }


    /**
     * Set the value of {@code "reason"} which is the reason
     * of the failure of the token request.
     *
     * @param reason
     *         The reason of the failure.
     *
     * @return
     *         {@code this} object.
     */
    public TokenFailRequest setReason(Reason reason)
    {
        this.reason = reason;

        return this;
    }
}
