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
import java.net.URI;


/**
 * Request to Authlete's {@code /api/backchannel/authentication/fail} API.
 *
 * @since 2.32
 */
public class BackchannelAuthenticationFailRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * Failure reasons of backchannel authentication requests.
     */
    public enum Reason
    {
        /**
         * The {@code login_hint_token} provided in the authentication request
         * is not valid because it has expired.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"expired_login_hint_token"}.
         * </p>
         */
        EXPIRED_LOGIN_HINT_TOKEN,


        /**
         * The OpenID provider is not able to identify which end-user the
         * client wishes to be authenticated by means of the hint provided in
         * the request ({@code login_hint_token}, {@code id_token_hint} or
         * {@code login_hint}).
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"unknown_user_id"}.
         * </p>
         */
        UNKNOWN_USER_ID,


        /**
         * The user code provided in the authentication request is invalid.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"invalid_user_code"}.
         * </p>
         */
        INVALID_USER_CODE,


        /**
         * The resource owner or OpenID provider denied the request. Note that
         * as the authentication error response is received prior to any user
         * interaction, such an error would only be received if a resource
         * owner or OpenID provider had made a decision to deny a certain type
         * of request or requests from a certain type of client.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"access_denied"}.
         * </p>
         */
        ACCESS_DENIED,


        /**
         * The backchannel authentication request cannot be processed
         * successfully due to a server-side error.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"server_error"}.
         * </p>
         */
        SERVER_ERROR,
    }


    private String ticket;
    private Reason reason;
    private String description;
    private URI uri;


    /**
     * Get the ticket which is necessary to call Authlete's
     * {@code /api/backchannel/authentication/fail} API.
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
     * {@code /api/backchannel/authentication/fail} API.
     * This request parameter is mandatory.
     *
     * @param ticket
     *         A ticket previously issued by Authlete's
     *         {@code /api/backchannel/authentication} API.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationFailRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }


    /**
     * Get the reason of the failure of the backchannel authentication request.
     *
     * @return
     *         The reason of the failure of the backchannel authentication
     *         request.
     */
    public Reason getReason()
    {
        return reason;
    }


    /**
     * Set the reason of the failure of the backchannel authentication request.
     * This request parameter is mandatory.
     *
     * @param reason
     *         The reason of the failure of the backchannel authentication
     *         request.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationFailRequest setReason(Reason reason)
    {
        this.reason = reason;

        return this;
    }


    /**
     * Get the description of the error. This corresponds to the
     * {@code error_description} property in the response to the client.
     *
     * @return
     *         The description of the error.
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Set the description of the error. This corresponds to the
     * {@code error_description} property in the response to the client.
     *
     * <p>
     * If this optional request parameter is given, its value is used as the
     * value of the {@code error_description} property.
     * </p>
     *
     * @param description
     *         The description of the error.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationFailRequest setDescription(String description)
    {
        this.description = description;

        return this;
    }


    /**
     * Get the URI of a document which describes the error in detail. This
     * corresponds to the {@code error_uri} property in the response to the
     * client.
     *
     * @return
     *         The URI of a document which describes the error in detail.
     */
    public URI getUri()
    {
        return uri;
    }


    /**
     * Set the URI of a document which describes the error in detail. This
     * corresponds to the {@code error_uri} property in the response to the
     * client.
     *
     * <p>
     * If this optional request parameter is given, its value is used as the
     * value of the {@code error_uri} property.
     * </p>
     *
     * @param uri
     *         The URI of a document which describes the error in detail.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationFailRequest setUri(URI uri)
    {
        this.uri = uri;

        return this;
    }
}
