/*
 * Copyright (C) 2024 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.api;


import java.util.Collections;
import java.util.Map;


/**
 * Request options.
 *
 * @author hidebike712
 *
 * @since 4.15
 */
public class Options
{
    Map<String, String> headers;


    /**
     * Get the custom request headers for a request. Note that the returned map
     * is immutable.
     *
     * @return
     *         The custom request headers. Note that the returned map is immutable.
     */
    public Map<String, String> getHeaders()
    {
        return headers == null ? null : Collections.unmodifiableMap(headers);
    }


    /**
     * Set the custom request headers for a request.
     *
     * @param headers
     *         The custom request headers.
     *
     * @return
     *         {@code this} object.
     */
    public Options setHeaders(Map<String, String> headers)
    {
        this.headers = headers;

        return this;
    }
}
