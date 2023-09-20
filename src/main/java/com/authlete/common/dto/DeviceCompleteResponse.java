/*
 * Copyright (C) 2019 Authlete, Inc.
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
 * Response from Authlete's {@code /api/device/complete} API.
 *
 * <p>
 * Authlete's {@code /api/device/complete} API returns JSON which can be mapped
 * to this class. The authorization server implementation should retrieve the
 * value of {@code action} from the response and take the following steps
 * according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#SUCCESS SUCCESS}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code SUCCESS}, it means that the API
 * call has been processed successfully. The authorization server should return
 * a successful response to the web browser the end-user is using.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#INVALID_REQUEST INVALID_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code INVALID_REQUEST}, it means that
 * the API call is invalid. Probably, the authorization server implementation
 * has some bugs.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#USER_CODE_EXPIRED USER_CODE_EXPIRED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code USER_CODE_EXPIRED}, it means that
 * the user code included in the API call has expired. The authorization server
 * implementation should tell the end-user that the user code has expired and
 * urge her to re-initiate a device flow.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#USER_CODE_NOT_EXIST USER_CODE_NOT_EXIST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code USER_CODE_NOT_EXIST}, it means
 * that the user code included in the API call does not exist. The authorization
 * server implementation should tell the end-user that the user code has been
 * invalidated and urge her to re-initiate a device flow.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#SERVER_ERROR SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code SERVER_ERROR}, it means that an
 * error occurred on Authlete side. The authorization server implementation
 * should tell the end-user that something wrong happened and urge her to
 * re-initiate a device flow.
 * </p>
 * </dd>
 * </dl>
 *
 * @since 2.42
 */
public class DeviceCompleteResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the authorization server implementation should take.
     */
    public enum Action
    {
        /**
         * The API call has been processed successfully. The authorization
         * server should return a successful response to the web browser the
         * end-user is using.
         */
        SUCCESS,


        /**
         * The API call is invalid. Probably, the authorization server
         * implementation has some bugs.
         */
        INVALID_REQUEST,


        /**
         * The user code has expired. The authorization server implementation
         * should tell the end-user that the user code has expired and urge her
         * to re-initiate a device flow.
         */
        USER_CODE_EXPIRED,


        /**
         * The user code does not exist. The authorization server implementation
         * should tell the end-user that the user code has been invalidated and
         * urge her to re-initiate a device flow.
         */
        USER_CODE_NOT_EXIST,


        /**
         * An error occurred on Authlete side. The authorization server
         * implementation should tell the end-user that something wrong
         * happened and urge her to re-initiate a device flow.
         */
        SERVER_ERROR,
    }


    /**
     * @since Authlete 2.0.0
     */
    private Action action;


    /**
     * Get the next action that the authorization server implementation should
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
     * Set the next action that the authorization server implementation should
     * take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceCompleteResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }
}
