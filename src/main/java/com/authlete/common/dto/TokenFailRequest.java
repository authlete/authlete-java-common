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
         * Using this reason will result in {@code "error":"server_error"}.
         * </p>
         */
        UNKNOWN,

        /**
         * The resource owner's credentials ({@code username} and {@code password}) contained in the token request whose flow is <a href="http://tools.ietf.org/html/rfc6749#section-4.3"> "Resource
         * Owner Password Credentials"</a>) are invalid.
         *
         * <p>
         * Using this reason will result in {@code "error":"invalid_request"}.
         * </p>
         */
        INVALID_RESOURCE_OWNER_CREDENTIALS,

        /**
         * The requested resource is invalid, missing, unknown, or malformed. See <i>"Resource Indicators for OAuth 2.0"</i> for details.
         *
         * <p>
         * Using this reason will result in {@code "error":"invalid_target"}.
         * </p>
         *
         * @since 2.62
         */
        INVALID_TARGET,

        /**
         * Client authentication failed (e.g., unknown client, no client authentication included, or unsupported
         * authentication method).  The authorization server MAY return an HTTP 401 (Unauthorized) status code to indicate
         * which HTTP authentication schemes are supported. If the client attempted to authenticate via the "Authorization"
         * request header field, the authorization server MUST respond with an HTTP 401 (Unauthorized) status code and
         * include the "WWW-Authenticate" response header field matching the authentication scheme used by the client.
         *
         * <p>
         * Using this reason will result in {@code "error":"invalid_client"}.
         * </p>
         *
         * @since 4.46
         */
        INVALID_CLIENT,

        /**
         * The provided authorization grant (e.g., authorization code, resource owner credentials) or refresh token is
         * invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to
         * another client.
         *
         * <p>
         * Using this reason will result in {@code "error":"invalid_grant"}.
         * </p>
         *
         * @since 4.46
         */
        INVALID_GRANT,

        /**
         * The authenticated client is not authorized to use this authorization grant type.
         *
         * <p>
         * Using this reason will result in {@code "error":"unauthorized_client"}.
         * </p>
         *
         * @since 4.46
         */
        UNAUTHORIZED_CLIENT,

        /**
         * The authorization grant type is not supported by the authorization server.
         *
         * <p>
         * Using this reason will result in {@code "error":"unsupported_grant_type"}.
         * </p>
         *
         * @since 4.46
         */
        UNSUPPORTED_GRANT_TYPE,

        /**
         * The requested scope is invalid, unknown, malformed, or exceeds the scope granted by the resource owner.
         *
         * <p>
         * Using this reason will result in {@code "error":"invalid_scope"}.
         * </p>
         *
         * @since 4.46
         */
        INVALID_SCOPE,
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
