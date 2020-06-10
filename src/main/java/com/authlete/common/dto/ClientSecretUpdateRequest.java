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
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's {@code /api/client/secret/update} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>clientSecret</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * A new value of the client secret.
 * Valid characters for a client secret are {@code A-Z},
 * {@code a-z}, {@code 0-9}, {@code -}, and {@code _}.
 * The maximum length of a client secret is 86.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.11
 */
public class ClientSecretUpdateRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String clientSecret;


    /**
     * Get the client secret.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret.
     *
     * <p>
     * Valid characters for a client secret are {@code A-Z},
     * {@code a-z}, {@code 0-9}, {@code -}, and {@code _}.
     * The maximum length of a client secret is 86.
     * </p>
     *
     * @param clientSecret
     *         The new value of the client secret.
     *
     * @throws IllegalArgumentException
     *         <ol>
     *           <li>{@code clientSecret} is {@code null}.</li>
     *           <li>{@code clientSecret} is an empty string.</li>
     *           <li>The length of {@code clientSecret} exceeds 86.</li>
     *           <li>{@code clientSecret} contains an illegal character.</li>
     *         </ol>
     */
    public ClientSecretUpdateRequest setClientSecret(String clientSecret)
    {
        // Check if the given client secret complies with the format.
        checkClientSecret(clientSecret);

        this.clientSecret = clientSecret;

        return this;
    }


    private void checkClientSecret(String clientSecret)
    {
        if (clientSecret == null)
        {
            throw new IllegalArgumentException("clientSecret is null.");
        }

        int len = clientSecret.length();

        if (len == 0)
        {
            throw new IllegalArgumentException("clientSecret is empty.");
        }

        if (86 < len)
        {
            throw new IllegalArgumentException("clientSecret is too long.");
        }

        for (int i = 0; i < len; ++i)
        {
            char c = clientSecret.charAt(i);

            if (('A' <= c && c <= 'Z') ||
                ('a' <= c && c <= 'z') ||
                ('0' <= c && c <= '9') ||
                ('-' == c || c == '_'))
            {
                // Valid character.
                continue;
            }

            // Illegal character.
            throw new IllegalArgumentException("clientSecret contains an illegal character.");
        }

        // OK. The given client secret complies with the format.
    }
}
