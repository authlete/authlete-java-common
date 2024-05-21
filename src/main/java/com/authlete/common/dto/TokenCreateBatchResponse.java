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
 * Response from Authlete's {@code /auth/token/create/batch} API.
 *
 * @author Hideki Ikeda
 *
 * @since 3.96
 */
public class TokenCreateBatchResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    private String requestId;


    /**
     * Get the request ID.
     *
     * @return
     *         The request ID.
     */
    public String getRequestId()
    {
        return requestId;
    }


    /**
     * @param requestId
     *         The request ID.
     *
     * @return
     *         {@this} object.
     */
    public TokenCreateBatchResponse setRequestId(String requestId)
    {
        this.requestId = requestId;

        return this;
    }
}
