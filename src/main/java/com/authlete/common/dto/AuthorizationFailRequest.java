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
 * Request to Authlete's {@code /auth/authorization/fail} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>ticket</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The ticket issued by Authlete's {@code /auth/authorization} API
 * to the service implementation. It is the value of {@code "ticket"}
 * contained in the response from Authlete's {@code
 * /auth/authorization} API ({@link AuthorizationResponse}).
 * </p>
 * </dd>
 *
 * <dt><b><code>reason</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The reason of the failure of the authorization request.
 * See the description of {@link AuthorizationResponse} as to which
 * reason should be chosen.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see AuthorizationResponse
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationFailRequest implements Serializable
{
    private static final long serialVersionUID = 3L;


    /**
     * Failure reasons of authorization requests.
     */
    public enum Reason
    {
        /**
         * Unknown reason.
         *
         * <p>
         * Using this reason will result in {@code error=server_error}.
         * </p>
         */
        UNKNOWN,

        /**
         * The authorization request from the client application contained
         * {@code prompt=none}, but any end-user has not logged in.
         *
         * <p>
         * See <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
         * >"OpenID Connect Core 1.0, 3.1.2.1. Authentication Request"</a>
         * for {@code prompt} request parameter.
         * </p>
         *
         * <p>
         * Using this reason will result in {@code error=login_required}.
         * </p>
         *
         * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
         *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
         */
        NOT_LOGGED_IN,

        /**
         * The authorization request from the client application contained
         * {@code max_age} parameter with a non-zero value or the client's
         * configuration has a non-zero value for {@code default_max_age}
         * configuration parameter, but the service implementation cannot
         * behave properly based on the max age value mainly because the
         * service implementation does not manage authentication time of
         * end-users.
         *
         * <p>
         * See <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
         * >"OpenID Connect Core 1.0, 3.1.2.1. Authentication Request"</a>
         * for {@code max_age} request parameter.
         * </p>
         *
         * <p>
         * See <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
         * >"OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata"</a>
         * for {@code default_max_age} configuration parameter.
         * </p>
         *
         * <p>
         * Using this reason will result in {@code error=login_required}.
         * </p>
         *
         * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
         *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
         *
         * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
         *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
         */
        MAX_AGE_NOT_SUPPORTED,

        /**
         * The authorization request from the client application contained
         * {@code prompt=none}, but the time specified by {@code max_age}
         * request parameter or by {@code default_max_age} configuration
         * parameter has passed since the time at which the end-user logged
         * in.
         *
         * <p>
         * See <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
         * >"OpenID Connect Core 1.0, 3.1.2.1. Authentication Request"</a>
         * for {@code prompt} and {@code max_age} request parameters.
         * </p>
         *
         * <p>
         * See <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
         * >"OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata"</a>
         * for {@code default_max_age} configuration parameter.
         * </p>
         *
         * <p>
         * Using this reason will result in {@code error=login_required}.
         * </p>
         *
         * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
         *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
         *
         * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
         *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
         */
        EXCEEDS_MAX_AGE,

        /**
         * The authorization request from the client application requested
         * a specific value for {@code sub} claim, but the current end-user
         * (in the case of {@code prompt=none}) or the end-user after the
         * authentication is different from the specified value.
         *
         * <p>
         * Using this reason will result in {@code error=login_required}.
         * </p>
         */
        DIFFERENT_SUBJECT,

        /**
         * The authorization request from the client application contained
         * {@code "acr"} claim in {@code "claims"} request parameter and
         * the claim was marked as essential, but the ACR performed for the
         * end-user does not match any one of the requested ACRs.
         *
         * <p>
         * Using this reason will result in {@code error=login_required}.
         * </p>
         */
        ACR_NOT_SATISFIED,

        /**
         * The end-user denied the authorization request from the client
         * application.
         *
         * <p>
         * Using this reason will result in {@code error=access_denied}.
         * </p>
         */
        DENIED,

        /**
         * Server error.
         *
         * <p>
         * Using this reason will result in {@code error=server_error}.
         * </p>
         *
         * @since 1.3
         */
        SERVER_ERROR,

        /**
         * The end-user was not authenticated.
         *
         * <p>
         * Using this reason will result in {@code error=login_required}.
         * </p>
         *
         * @since 1.3
         */
        NOT_AUTHENTICATED,

        /**
         * The authorization server cannot obtain an account selection choice
         * made by the end-user.
         *
         * <p>
         * Using this reason will result in {@code error=account_selection_required}.
         * </p>
         *
         * @since 2.11
         */
        ACCOUNT_SELECTION_REQUIRED,

        /**
         * The authorization server cannot obtain consent from the end-user.
         *
         * <p>
         * Using this reason will result in {@code error=consent_required}.
         * </p>
         *
         * @since 2.11
         */
        CONSENT_REQUIRED,

        /**
         * The authorization server needs interaction with the end-user.
         *
         * <p>
         * Using this reason will result in {@code error=interaction_required}.
         * </p>
         *
         * @since 2.11
         */
        INTERACTION_REQUIRED,

        /**
         * The requested resource is invalid, missing, unknown, or malformed.
         * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
         *
         * <p>
         * Using this reason will result in {@code error=invalid_target}.
         * </p>
         *
         * @since 2.62
         */
        INVALID_TARGET,
    }


    /**
     * The ticket issued by Authlete's /auth/authorization API.
     */
    private String ticket;


    /**
     * The reason of the failure.
     */
    private Reason reason;


    /**
     * The custom description about the failure.
     */
    private String description;


    /**
     * Get the value of {@code "ticket"} which is the ticket
     * issued by Authlete's {@code /auth/authorization} API
     * to the service implementation.
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
     * issued by Authlete's {@code /auth/authorization} API
     * to the service implementation.
     *
     * @param ticket
     *         The ticket.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationFailRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }


    /**
     * Get the value of {@code "reason"} which is the reason
     * of the failure of the authorization request.
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
     * of the failure of the authorization request.
     *
     * @param reason
     *         The reason of the failure.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationFailRequest setReason(Reason reason)
    {
        this.reason = reason;

        return this;
    }


    /**
     * Get the custom description about the authorization failure.
     *
     * @return
     *         The custom description.
     *
     * @since 1.3
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Set the custom description about the authorization failure.
     *
     * @param description
     *         The custom description.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.3
     */
    public AuthorizationFailRequest setDescription(String description)
    {
        this.description = description;

        return this;
    }
}
