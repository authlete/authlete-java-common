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
 * Request to Authlete's {@code /api/client/registration/} API.
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
    private String json;


    /**
     * The client registration access token. Used only for GET, UPDATE, and
     * DELETE requests.
     */
    private String token;


    /**
     * The client's identifier. Used for GET, UPDATE, and DELETE requests.
     */
    private String clientId;


    /**
     * Get client metadata in JSON format that complies with <a href=
     * "https://tools.ietf.org/html/rfc7591">RFC 7591</a> (OAuth 2&#46;0
     * Dynamic Client Registration Protocol).
     *
     * @return
     *         Client metadata in JSON format.
     */
    public String getJson()
    {
        return json;
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
    public ClientRegistrationRequest setJson(String metadata)
    {
        this.json = metadata;

        return this;
    }


    /**
     * Get the client registration access token which was passed with
     * this update request.
     * 
     * @return Client registration access token.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the client registration access token which was passed with
     * this update request.
     * 
     * @param token
     *            Client registration access token.
     * 
     * @return {@code this} object.
     */
    public ClientRegistrationRequest setToken(String token)
    {
        this.token = token;

        return this;
    }

    /**
     * Get the client's ID. This is usually parsed from the URL of the
     * management endpoint at the endpoint implementation.
     * 
     * @return
     *         Client ID.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client's ID. This is usually parsed from the URL of the
     * management endpoint at the endpoint implementation.
     * 
     * @param clientId
     *            Client ID.
     * 
     * @return {@code this} object.
     */
    public ClientRegistrationRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }
}
