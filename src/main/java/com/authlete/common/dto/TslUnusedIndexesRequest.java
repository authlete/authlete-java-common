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

import java.io.Serializable;

/**
 * Request to Authlete's {@code /tsl/unused/indexes} API.
 *
 * <p>
 * This class represents a request to pre-populate unused token indexes for a
 * particular service. These unused indexes are later consumed when issuing
 * Verifiable Credentials (VCs) or access tokens, allowing efficient allocation
 * of token indexes within a Token Status List (TSL) environment.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslUnusedIndexesRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * If less than this number of unused VC/token indexes left then populate new unused indexes
     * specified in {@code unusedTokenIndexesAdd}.
     */
    private long unusedTokenIndexesLeft;

    /**
     * Add this number of new unused VC/token indexes.
     */
    private long unusedTokenIndexesAdd;

    /**
     * Set the unused token indexes left value.
     *
     * @param unusedTokenIndexesLeft
     *         The unused token indexes left value.
     *
     * @return
     *         {@code this} object.
     */
    public TslUnusedIndexesRequest setUnusedTokenIndexLeft(long unusedTokenIndexesLeft)
    {
        this.unusedTokenIndexesLeft = unusedTokenIndexesLeft;

        return this;
    }

    /**
     * Get the unused token indexes left value.
     *
     * @return
     *         The unused token indexes left value.
     */
    public long getUnusedTokenIndexLeft()
    {
        return unusedTokenIndexesLeft;
    }

    /**
     * Set the unused token indexes add value.
     *
     * @param unusedTokenIndexesAdd
     *         The unused token indexes add value.
     *
     * @return
     *         {@code this} object.
     */
    public TslUnusedIndexesRequest setUnusedTokenIndexAdd(long unusedTokenIndexesAdd)
    {
        this.unusedTokenIndexesAdd = unusedTokenIndexesAdd;

        return this;
    }

    /**
     * Get the unused token indexes add value.
     *
     * @return
     *         The unused token indexes add value.
     */
    public long getUnusedTokenIndexAdd()
    {
        return unusedTokenIndexesAdd;
    }

}
