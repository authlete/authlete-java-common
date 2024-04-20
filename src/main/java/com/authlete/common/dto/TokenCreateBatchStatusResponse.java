/*
 * Copyright (C) 2024 Authlete, Inc.
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
 * Response from Authlete's {@code /auth/token/create/batch/status} API.
 *
 * @author Hideki Ikeda
 *
 * @since 3.96
 */
public class TokenCreateBatchStatusResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    private TokenBatchStatus status;


    /**
     * Get the batch status.
     *
     * @return
     *         The batch status.
     */
    public TokenBatchStatus getStatus()
    {
        return status;
    }


    /**
     * Set the batch status.
     *
     * @param status
     *         The batch stasus.
     *
     * @return
     *         {@code this} object.
     */
    public TokenCreateBatchStatusResponse setStatus(TokenBatchStatus status)
    {
        this.status = status;

        return this;
    }
}
