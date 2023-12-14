/*
 * Copyright (C) 2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Information about a ticket that has been issued from the
 * {@code /auth/authorization} API.
 *
 * @since 3.88
 * @since Authlete 3.0
 */
public class AuthorizationTicketInfo implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The arbitrary text attached to the ticket.
     */
    private String context;


    /**
     * Get the arbitrary text attached to the ticket.
     *
     * @return
     *         The arbitrary text.
     */
    public String getContext()
    {
        return context;
    }


    /**
     * Set the arbitrary text attached to the ticket.
     *
     * @param context
     *         The arbitrary text.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationTicketInfo setContext(String context)
    {
        this.context = context;

        return this;
    }
}
