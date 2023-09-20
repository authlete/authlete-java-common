/*
 * Copyright (C) 2021 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


/**
 * Response from Authlete's {@code /api/hsk/get/list} API.
 *
 * @since 2.97
 */
public class HskListResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The result of the API call.
     */
    public enum Action
    {
        /**
         * The API call succeeded.
         */
        SUCCESS,

        /**
         * The API call was wrong.
         */
        INVALID_REQUEST,

        /**
         * An error occurred on Authlete side.
         */
        SERVER_ERROR,
    }


    /**
     * @since Authlete 2.2.12
     */
    private Action action;
    /**
     * @since Authlete 2.2.12
     */
    private Hsk[] hsks;


    /**
     * Get the result of the API call.
     *
     * @return
     *         The result of the API call.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the result of the API call.
     *
     * @param action
     *         The result of the API call.
     *
     * @return
     *         {@code this} object.
     */
    public HskListResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the information about the keys on the HSM.
     *
     * @return
     *         Information about the keys.
     */
    public Hsk[] getHsks()
    {
        return hsks;
    }


    /**
     * Set the information about the keys on the HSM.
     *
     * @param hsks
     *         Information about the keys.
     *
     * @return
     *         {@code this} object.
     */
    public HskListResponse setHsks(Hsk[] hsks)
    {
        this.hsks = hsks;

        return this;
    }
}
