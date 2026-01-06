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
 * Response to Authlete's {@code /tsl/unused/indexes} API.
 *
 * <p>
 * This class represents a response to pre-populate unused token indexes for a
 * particular service. These unused indexes are later consumed when issuing
 * Verifiable Credentials (VCs) or access tokens, allowing efficient allocation
 * of token indices within a Token Status List (TSL) environment.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */

public class TslUnusedIndexesResponse extends ApiResponse {

    private static final long serialVersionUID = 1L;

    /**
     * The next action that the implementation of the TSL unused indexes endpoint
     * should take after getting a response from Authlete's
     * {@code /tsl/unused/indexes} API.
     *
     * @since 4.33
     * @since Authlete 3.0.22
     */
    public enum Action
    {
        /**
         * A TSL publish response has been prepared successfully.
         */
        OK,

        /**
         * The feature of TSL publish not enabled in the service
         * configuration.
         */
        FORBIDDEN,
    }

    private TslUnusedIndexesResponse.Action action;

    /**
     * Get the next action that the implementation of the TSL unused indexes endpoint should
     * take after getting a response from Authlete's {@code /tsl/unused/indexes} API.
     *
     * @return
     *         The next action.
     */
    public TslUnusedIndexesResponse.Action getAction()
    {
        return action;
    }

    /**
     * Set the next action that the implementation of the TSL unused indexes endpoint should
     * take after getting a response from Authlete's {@code /tsl/unused/indexes} API.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public TslUnusedIndexesResponse setAction(TslUnusedIndexesResponse.Action action)
    {
        this.action = action;

        return this;
    }
}
