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
 * Response from Authlete's {@code /tsl/publish/configs/list} API.
 *
 * <p>
 * This class represents the response containing Token Status List (TSL)
 * publish configurations for one or more services. Each entry
 * indicates when the next TSL will be published for the corresponding service.
 * </p>
 *
 * <p>
 * The response includes an array of {@link TslPublishConfigInfo} objects, each
 * describing:
 * </p>
 *
 * <ul>
 *   <li>{@code serviceID} – The service ID.</li>
 *   <li>{@code nextTslPublishTime} – The scheduled Unix timestamp (seconds) for the next TSL publication.</li>
 * </ul>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslPublishConfigsListResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;

    /**
     * The result of the {@code /tsl/publish/configs/list} API call.
     */
    public enum Action
    {
        /**
         * Information about the TSL publish configs has been obtained
         * successfully.
         */
        OK,
    }

    /**
     * The result of the {@code /tsl/publish/configs/list} API call.
     */
    private Action action;

    /**
     * The list of TSL publish configurations.
     *
     * <p>
     * Each element in the array contains the publishing schedule for a service,
     * including the service ID and the next scheduled publish time.
     * </p>
     */
    private TslPublishConfigInfo[] info;


    /**
     * Get the result of the {@code /tsl/publish/configs/list} API call.
     *
     * @return
     *         The result of the API call.
     */
    public Action getAction()
    {
        return action;
    }

    /**
     * Set the result of the {@code /tsl/publish/configs/list} API call.
     *
     * @param action
     *         The result of the API call.
     *
     * @return
     *         {@code this} object.
     */
    public TslPublishConfigsListResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }

    /**
     * Set the list of TSL publish configurations.
     *
     * @param info
     *         An array of {@link TslPublishConfigInfo} objects.
     *
     * @return
     *         {@code this} object.
     */
    public TslPublishConfigsListResponse setInfo(TslPublishConfigInfo[] info)
    {
        this.info = info;

        return this;
    }

    /**
     * Get the list of TSL publish configurations.
     *
     * @return
     *          An array of {@link TslPublishConfigInfo} objects.
     */
    public TslPublishConfigInfo[] getInfo()
    {
        return info;
    }
}
