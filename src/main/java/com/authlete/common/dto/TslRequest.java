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

import com.authlete.common.types.TslFormat;

import java.io.Serializable;

/**
 * Request to Authlete's {@code /tsl} API.
 *
 * <p>
 * This class represents a request to retrieve a published Token Status List (TSL)
 * for a specific service. The request requires the caller to specify the
 * {@code tslFormat} for the requested TSL.
 * </p>
 *
 * <p>
 * For more details about Token Status Lists (TSL), see:
 * </p>
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/">
 *      Token Status List (TSL) Specification</a>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The requested TSL format. Currently only JWT format is supported.
     */
    private TslFormat format;

    /**
     * Set the requested TSL format
     *
     * @param format
     *         The TSL format.
     *
     * @return
     *         {@code this} object.
     */
    public TslRequest setTslFormat(TslFormat format)
    {
        this.format = format;

        return this;
    }

    /**
     * Get the requested TSL format.
     *
     * @return
     *         The TSL format.
     */
    public TslFormat getTslFormat()
    {
        return format;
    }
}
