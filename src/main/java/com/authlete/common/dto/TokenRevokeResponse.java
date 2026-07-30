/*
 * Copyright (C) 2022 Authlete, Inc.
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
 * Response from Authlete's {@code /auth/token/revoke} API.
 *
 * @since 3.26
 * @since Authlete 2.2.29
 */
public class TokenRevokeResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;

    /**
     * The maximum amount of tokens that can be revoked per request is 20.
     * If 20 is returned then additional token revocation requests should be made until this value 
     * is 0 to ensure that all tokens are revoked.
     */
    private int count;


    /**
     * Get the number of revoked tokens.
     * The maximum amount of tokens that can be revoked per request is 20.
     * If 20 is returned then additional token revocation requests should be made until this value 
     * is 0 to ensure that all tokens are revoked.
     *
     * @return
     *         The number of revoked tokens.
     */
    public int getCount()
    {
        return count;
    }


    /**
     * Set the number of revoked tokens.
     * The maximum amount of tokens that can be revoked per request is 20.
     * If 20 is returned then additional token revocation requests should be made until this value 
     * is 0 to ensure that all tokens are revoked.
     *
     * @param count
     *         The number of revoked tokens.
     *
     * @return
     *         {@code this} object.
     */
    public TokenRevokeResponse setCount(int count)
    {
        this.count = count;

        return this;
    }
}
