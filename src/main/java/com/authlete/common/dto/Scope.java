/*
 * Copyright (C) 2014-2015 Authlete, Inc.
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
 * Information about a scope of a service.
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
 *      > RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a>
 *
 * @author Takahiko Kawasaki
 */
public class Scope implements Serializable
{
    private static final long serialVersionUID = 2L;


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    /**
     * Scope name.
     */
    private String name;


    /**
     * Flag that indicates whether this scope is included in
     * the default scope list.
     */
    private boolean defaultEntry;


    /**
     * Description of this scope.
     */
    private String description;


    /**
     * Descriptions for various languages.
     */
    private TaggedValue[] descriptions;


    /**
     * Get the scope name.
     *
     * @return
     *         The scope name.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     *      > RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a>
     */
    public String getName()
    {
        return name;
    }


    /**
     * Set the scope name.
     *
     * @param name
     *         The scope name.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     *      > RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a>
     */
    public Scope setName(String name)
    {
        this.name = name;

        return this;
    }


    /**
     * Get the flag that indicates whether this scope is included in
     * the default scope list.
     *
     * @return
     *         {@code true} if this scope is included in the default
     *         scope list. Otherwise, {@code false}.
     */
    public boolean isDefaultEntry()
    {
        return defaultEntry;
    }


    /**
     * Set the flag that indicates whether this scope is included in
     * the default scope list.
     *
     * @param defaultEntry
     *         {@code true} to include this scope in the default scope list.
     *         Otherwise, {@code false}.
     *
     * @return
     *         {@code this} object.
     */
    public Scope setDefaultEntry(boolean defaultEntry)
    {
        this.defaultEntry = defaultEntry;

        return this;
    }


    /**
     * Get the description.
     *
     * @return
     *         The description.
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Set the description.
     *
     * @param description
     *         The description.
     *
     * @return
     *         {@code this} object.
     */
    public Scope setDescription(String description)
    {
        this.description = description;

        return this;
    }


    /**
     * Get descriptions for various languages.
     *
     * @return
     *         Descriptions.
     */
    public TaggedValue[] getDescriptions()
    {
        return descriptions;
    }


    /**
     * Set descriptions for various languages.
     *
     * @param descriptions
     *         Descriptions.
     *
     * @return
     *         {@code this} object.
     */
    public Scope setDescriptions(TaggedValue[] descriptions)
    {
        this.descriptions = descriptions;

        return this;
    }


    /**
     * Extract scope names.
     *
     * @param scopes
     *         Scopes.
     *
     * @return
     *         Scope names. If {@code scopes} is {@code null},
     *         {@code null} is returned.
     */
    public static String[] extractNames(Scope[] scopes)
    {
        if (scopes == null)
        {
            return null;
        }

        // Create an array for scope names.
        String[] names = new String[scopes.length];

        // For each scope.
        for (int i = 0; i < scopes.length; ++i)
        {
            // Scope at the index.
            Scope scope = scopes[i];

            // Extract the scope name.
            names[i] = (scope == null) ? null : scope.getName();
        }

        // Extracted scope names.
        return names;
    }
}
