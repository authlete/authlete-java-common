/*
 * Copyright (C) 2025 Authlete, Inc.
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

/**
 * Response from Authlete's {@code /tsl} API.
 *
 * <p>
 * This class represents the response returned when retrieving a Token Status
 * List (TSL) from Authlete API.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;

    /**
     * The next action that the implementation of the TSL endpoint
     * should take after getting a response from Authlete's
     * {@code /tsl} API.
     */
    public enum Action
    {
        /**
         * A get TSL response has been prepared successfully.
         */
        OK,

        /**
         * The feature of TSL is not enabled in the service
         * configuration.
         */
        FORBIDDEN,

        /**
         * Invalid TSL format
         */
        INVALID_TSL_FORMAT,

        /**
         * TSL not found
         */
        NO_TSL_FOUND
    }

    private Action action;

    private String responseContent;

    /**
     * Get the next action that the implementation of the TSL endpoint should
     * take after getting a response from Authlete's {@code /tsl} API.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }

    /**
     * Set the next action that the implementation of the TSL endpoint should
     * take after getting a response from Authlete's {@code /tsl} API.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public TslResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content that the implementation of the TSL endpoint should use
     * when it constructs a response.
     *
     * @return
     *         The response content in the JSON format.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content that the implementation of the TSL endpoint should use
     * when it constructs a response.
     *
     * @param content
     *         The response content in the JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public TslResponse setResponseContent(String content)
    {
        this.responseContent = content;

        return this;
    }

}
