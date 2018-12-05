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
 * Response from Authlete's {@code /api/backchannel/authentication/issue} API.
 *
 * @since 2.32
 */
public class BackchannelAuthenticationIssueResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;



    /**
     * The next action that the OpenID provider implementation should take.
     */
    public enum Action
    {
        /**
         * The implementation of the backchannel authentication endpoint should
         * return a {@code 200 OK} response to the client application.
         */
        OK,


        /**
         * The implementation of the backchannel authentication endpoint should
         * return a {@code 500 Internal Server Error} response to the client
         * application. However, in most cases, commercial implementations
         * prefer to use other HTTP status code than 5xx.
         */
        INTERNAL_SERVER_ERROR,


        /**
         * The ticket included in the API call is wrong. It does not exist or
         * has expired.
         */
        BAD_TICKET,
    }


    private Action action;
    private String responseContent;
    private String authReqId;
    private int expiresIn;
    private int interval;


    /**
     * Get the next action that the OpenID provider implementation should take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the OpenID provider implementation should take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationIssueResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content of the response body of the response to the client
     * application. Its format is JSON.
     *
     * <p>
     * In successful cases, the content contains {@code auth_req_id}. In error
     * cases, the content contains {@code error}.
     * </p>
     *
     * @return
     *         The content of the response body of the response to the client
     *         application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content of the response body of the response to the client
     * application.
     *
     * @param responseContent
     *         The content of the response body of the response to the client
     *         application.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationIssueResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the issued authentication request ID. This corresponds to the
     * {@code auth_req_id} property in the response to the client.
     *
     * @return
     *         The issued authentication request ID ({@code auth_req_id}).
     */
    public String getAuthReqId()
    {
        return authReqId;
    }


    /**
     * Set the issued authentication request ID. This corresponds to the
     * {@code auth_req_id} property in the response to the client.
     *
     * @param authReqId
     *         The issued authentication request ID ({@code auth_req_id}).
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationIssueResponse setAuthReqId(String authReqId)
    {
        this.authReqId = authReqId;

        return this;
    }


    /**
     * Get the duration of the issued authentication request ID in seconds.
     * This corresponds to the {@code expires_in} property in the response
     * to the client.
     *
     * @return
     *         The duration of the issued authentication request ID in seconds
     *         ({@code expires_in}).
     */
    public int getExpiresIn()
    {
        return expiresIn;
    }


    /**
     * Set the duration of the issued authentication request ID in seconds.
     * This corresponds to the {@code expires_in} property in the response
     * to the client.
     *
     * @param expiresIn
     *         The duration of the issued authentication request ID in seconds
     *         ({@code expires_in}).
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationIssueResponse setExpiresIn(int expiresIn)
    {
        this.expiresIn = expiresIn;

        return this;
    }


    /**
     * Get the minimum amount of time in seconds that the client must wait for
     * between polling requests to the token endpoint. This corresponds to the
     * {@code interval} property in the response to the client.
     *
     * <p>
     * The value returned from this method has no meaning when the backchannel
     * token delivery mode is {@code "push"}.
     * </p>
     *
     * @return
     *         The minimum amount of time in seconds between polling requests.
     */
    public int getInterval()
    {
        return interval;
    }


    /**
     * Set the minimum amount of time in seconds that the client must wait for
     * between polling requests to the token endpoint. This corresponds to the
     * {@code interval} property in the response to the client.
     *
     * @param interval
     *         The minimum amount of time in seconds between polling requests.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationIssueResponse setInterval(int interval)
    {
        this.interval = interval;

        return this;
    }
}
