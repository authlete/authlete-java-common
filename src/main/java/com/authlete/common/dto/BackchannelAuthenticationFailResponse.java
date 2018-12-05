/*
 * Copyright (C) 2018 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


/**
 * Response from Authlete's {@code /api/backchannel/authentication/fail} API.
 *
 * @since 2.32
 */
public class BackchannelAuthenticationFailResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the OpenID provider implementation should take.
     */
    public enum Action
    {
        /**
         * The implementation of the backchannel authentication endpoint should
         * return a {@code 400 Bad Request} response to the client application.
         */
        BAD_REQUEST,


        /**
         * The implementation of the backchannel authentication endpoint should
         * return a {@code 403 Forbidden} response to the client application.
         *
         * <p>
         * {@link #getAction()} returns this value only when the {@code reason}
         * request parameter of the API call was
         * {@link BackchannelAuthenticationFailRequest.Reason#ACCESS_DENIED
         * ACCESS_DENIED}.
         * </p>
         */
        FORBIDDEN,


        /**
         * The implementation of the backchannel authentication endpoint should
         * return a {@code 500 Internal Server Error} response to the client
         * application. However, in most cases, commercial implementations
         * prefer to use other HTTP status code than 5xx.
         *
         * <p>
         * {@link #getAction()} returns this value when (1) the {@code reason}
         * request parameter of the API call was
         * {@link BackchannelAuthenticationFailRequest.Reason#SERVER_ERROR
         * SERVER_ERROR}, (2) an error occurred on Authlete side, or (3) the
         * request parameters of the API call were wrong.
         * </p>
         */
        INTERNAL_SERVER_ERROR,


        /**
         * The ticket included in the API call is invalid. It does not exist or
         * has expired.
         */
        INVALID_TICKET,
    }


    private Action action;
    private String responseContent;


    /**
     * Get the next action that the backchannel authentication endpoint should
     * take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the backchannel authentication endpoint should
     * take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationFailResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content of the response body of the response to the client.
     * Its format is always JSON.
     *
     * @return
     *         The content of the response body of the response to the client.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content of the response body of the response to the client.
     *
     * @param responseContent
     *         The content of the response body of the response to the client.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationFailResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }
}
