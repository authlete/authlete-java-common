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
 * Response to Authlete's {@code /tsl/token/status} API.
 *
 * <p>
 * This class represents a response to update the status of an issued
 * Verifiable Credential (VC) or token. The updated status will later
 * be included in the issued Token Status List (TSL).
 *
 * @since 4.33
 * @since Authlete 3.0.22
 *
 */
public class TslTokenStatusUpdateResponse extends ApiResponse {

    private static final long serialVersionUID = 1L;

    /**
     * The next action that the implementation of the token status update endpoint
     * should take after getting a response from Authlete's
     * {@code /tsl/token/status} API.
     *
     * @since 4.33
     * @since Authlete 3.0.22
     */
    public enum Action
    {
        /**
         * A token status update performed successfully.
         */
        OK,

        /**
         * The feature of TSL publish not enabled in the service
         * configuration.
         */
        FORBIDDEN,
    }

    private TslTokenStatusUpdateResponse.Action action;

    /**
     * Get the next action that the implementation of the token status update endpoint should
     * take after getting a response from Authlete's {@code /tsl/token/status} API.
     *
     * @return
     *         The next action.
     */
    public TslTokenStatusUpdateResponse.Action getAction()
    {
        return action;
    }

    /**
     * Set the next action that the implementation of the token status update endpoint should
     * take after getting a response from Authlete's {@code /tsl/token/status} API.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public TslTokenStatusUpdateResponse setAction(TslTokenStatusUpdateResponse.Action action)
    {
        this.action = action;

        return this;
    }
}
