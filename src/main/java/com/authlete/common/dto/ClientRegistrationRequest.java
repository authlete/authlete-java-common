/*
 * Copyright (C) 2018 Authlete, Inc.
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
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's {@code /api/client/register} API.
 *
 * <p>
 * The API is used to implement a client registration endpoint that complies
 * with <a href="https://tools.ietf.org/html/rfc7591">RFC 7591</a> (OAuth 2.0
 * Dynamic Client Registration Protocol).
 * </p>
 *
 * @since 2.22
 */
public class ClientRegistrationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * Metadata in JSON format that complies with RFC 7591.
     */
    private String metadata;


    /**
     * Get client metadata in JSON format that complies with <a href=
     * "https://tools.ietf.org/html/rfc7591">RFC 7591</a> (OAuth 2&#46;0
     * Dynamic Client Registration Protocol).
     *
     * @return
     *         Client metadata in JSON format.
     */
    public String getMetadata()
    {
        return metadata;
    }


    /**
     * Set client metadata in JSON format that complies with <a href=
     * "https://tools.ietf.org/html/rfc7591">RFC 7591</a> (OAuth 2&#46;0
     * Dynamic Client Registration Protocol).
     *
     * @param metadata
     *         Client metadata in JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public ClientRegistrationRequest setMetadata(String metadata)
    {
        this.metadata = metadata;

        return this;
    }
}
