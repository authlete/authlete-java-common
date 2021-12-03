/*
 * Copyright (C) 2021 Authlete, Inc.
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
 * Request to Authlete's <code>/api/client/lock_flag/update/<i>{clientIdentifier}</i></code> API.
 *
 * <p>
 * The API is used to update the lock flag of a client application.
 * </p>
 *
 * @since 3.10
 */
public class ClientLockFlagUpdateRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private boolean clientLocked;


    /**
     * Get the value to which this request updates the lock flag of a client
     * application.
     *
     * @return
     *         The value to which this request updates the lock flag of a client
     *         application.
     */
    public boolean isClientLocked()
    {
        return clientLocked;
    }


    /**
     * Set the value to which this request updates the lock flag of a client
     * application.
     *
     * @param clientLocked
     *         The value to which this request updates the lock flag of a client
     *         application.
     *
     * @return
     *         {@code this} object.
     */
    public ClientLockFlagUpdateRequest setClientLocked(boolean clientLocked)
    {
        this.clientLocked = clientLocked;

        return this;
    }
}
