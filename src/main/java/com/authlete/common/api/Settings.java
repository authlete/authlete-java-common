/*
 * Copyright (C) 2017 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.api;


/**
 * Settings of {@link AuthleteApi} implementation.
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.9
 */
public class Settings
{
    private int connectionTimeout;


    /**
     * Get the timeout value in milliseconds for socket connection.
     * The default value is 0 and it means an infinite timeout.
     *
     * @return
     *         The connection timeout value in milliseconds.
     */
    public int getConnectionTimeout()
    {
        return connectionTimeout;
    }


    /**
     * Set the timeout value in milliseconds for socket connection.
     * A timeout of zero is interpreted as an infinite timeout.
     *
     * @param timeout
     *         The connection timeout value in milliseconds.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The given timeout value is negative.
     */
    public Settings setConnectionTimeout(int timeout)
    {
        if (timeout < 0)
        {
            throw new IllegalArgumentException("timeout value cannot be negative.");
        }

        this.connectionTimeout = timeout;

        return this;
    }
}
