/*
 * Copyright (C) 2018-2019 Authlete, Inc.
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
 * <p>
 * The API prepares JSON that contains an {@code error}. The JSON should be
 * used as the response body of the response which is returned to the client
 * from the backchannel authentication endpoint.
 * </p>
 *
 * @since 2.32
 */
public class BackchannelAuthenticationFailRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


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
         * The client is not authorized to use the CIBA flow.
         *
         * <p>
         * Note that {@code /api/backchannel/authentication} API does not
         * return {@code action=USER_IDENTIFICATION} in cases where the client
         * does not exist or client authentication has failed. Therefore, the
         * authorization server implementation will never have to call
         * {@code /api/backchannel/authentication/fail} API with
         * {@code reason=UNAUTHORIZED} unless the server has intentionally
         * implemented special rules to reject backchannel authentication
         * requests based on clients.
         * </p>
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"unauthorized_client"}.
         * </p>
         */
        UNAUTHORIZED_CLIENT,


        /**
         * A user code is required but the backchannel authentication request
         * does not contain it.
         *
         * <p>
         * Note that {@code /api/backchannel/authentication} API does not
         * return {@code action=USER_IDENTIFICATION} when both the
         * {@code backchannel_user_code_parameter_supported} metadata of the
         * server and the {@code backchannel_user_code_parameter} metadata of
         * the client are {@code true} and the backchannel authentication
         * request does not include the {@code user_code} request parameter.
         * In this case, {@code /api/backchannel/authentication} API returns
         * {@code action=BAD_REQUEST} with JSON containing
         * {@code "error":"missing_user_code"}.
         * </p>
         *
         * <p>
         * Therefore, the authorization server implementation will never have
         * to call {@code /api/backchannel/authentication/fail} API with
         * {@code reason=MISSING_USER_CODE} unless the server has intentionally
         * implemented special rules to require a user code even in the case
         * where the {@code backchannel_user_code_parameter} metadata of the
         * client which has made the backchannel authentication request is
         * {@code false}.
         * </p>
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"missing_user_code"}.
         * </p>
         */
        MISSING_USER_CODE,


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
         * The binding message is invalid or unacceptable for use in the
         * context of the given request.
         *
         * <p>
         * Using this reason will result in
         * {@code "error":"invalid_binding_message"}.
         * </p>
         *
         * @since 2.35
         */
        INVALID_BINDING_MESSAGE,


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
    private String errorDescription;
    private URI errorUri;


    /**
     * Get the ticket which should be deleted on a call of Authlete's
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
     * Set the ticket which should be deleted on a call of Authlete's
     * {@code /api/backchannel/authentication/fail} API.
     *
     * <p>
     * This request parameter is not mandatory but optional. If this request
     * parameter is given and the ticket belongs to the service, the specified
     * ticket is deleted from the database. Giving this parameter is
     * recommended to clean up the storage area for the service.
     * </p>
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
     *
     * <p>
     * This request parameter is not mandatory but optional. However, giving
     * this parameter is recommended. If omitted, {@link Reason#SERVER_ERROR
     * SERVER_ERROR} is used as a reason.
     * </p>
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
    public String getErrorDescription()
    {
        return errorDescription;
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
    public BackchannelAuthenticationFailRequest setErrorDescription(String description)
    {
        this.errorDescription = description;

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
    public URI getErrorUri()
    {
        return errorUri;
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
    public BackchannelAuthenticationFailRequest setErrorUri(URI uri)
    {
        this.errorUri = uri;

        return this;
    }
}
