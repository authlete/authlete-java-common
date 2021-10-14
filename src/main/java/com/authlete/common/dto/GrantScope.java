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
 * Scope representation in a grant.
 *
 * <p>
 * This class holds the same information as each entry in the {@code "scopes"}
 * array in the response from the Grant Management Endpoint on the grant
 * management action 'query' does.
 * </p>
 *
 * @see <a href="https://openid.net/specs/fapi-grant-management.html"
 *      >Grant Management for OAuth 2.0</a>
 *
 * @since 3.1
 */
public class GrantScope implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * Space-delimited scopes.
     */
    private String scope;


    /**
     * List of resource indicators.
     */
    private String[] resource;


    /**
     * The default constructor with no argument.
     */
    public GrantScope()
    {
    }


    /**
     * A constructor with initial property values.
     *
     * @param scope
     *         A space-delimited scopes.
     *
     * @param resource
     *         A list of resource indicators.
     */
    public GrantScope(String scope, String[] resource)
    {
        this.scope    = scope;
        this.resource = resource;
    }


    /**
     * Get the space-delimited scopes.
     *
     * @return
     *         The space-delimited scopes.
     */
    public String getScope()
    {
        return scope;
    }


    /**
     * Set the space-delimited scopes.
     *
     * @param scope
     *         The space-delimited scopes.
     *
     * @return
     *         {@code this} object.
     */
    public GrantScope setScope(String scope)
    {
        this.scope = scope;

        return this;
    }


    /**
     * Get the resource.
     *
     * @return
     *         A list of resource indicators.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
     *      >Resource Indicators for OAuth 2.0</a>
     */
    public String[] getResource()
    {
        return resource;
    }


    /**
     * Set the resource.
     *
     * @param resource
     *         A list of resource indicators.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
     *      >Resource Indicators for OAuth 2.0</a>
     */
    public GrantScope setResource(String[] resource)
    {
        this.resource = resource;

        return this;
    }
}
