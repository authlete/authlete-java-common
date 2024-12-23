/*
 * Copyright (C) 2014-2023 Authlete, Inc.
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
import java.util.Map;
import com.authlete.common.web.URLCoder;
import com.fasterxml.jackson.annotation.JsonSetter;


/**
 * Request to Authlete's {@code /auth/authorization} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * OAuth 2.0 authorization request parameters which are the
 * request parameters that the OAuth 2.0 authorization endpoint
 * of the service implementation received from the client
 * application.
 * </p>
 * <p>
 * The value of {@code "parameters"} is (1) the entire query
 * string when the HTTP method of the request from the client
 * application is {@code "GET"} or (2) the entire entity body
 * (which is formatted in {@code application/x-www-form-urlencoded})
 * when the HTTP method of the request from the client application
 * is {@code "POST"}.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationRequest implements Serializable
{
    private static final long serialVersionUID = 3L;


    /**
     * OAuth 2.0 authorization request parameters.
     */
    private String parameters;


    /**
     * An arbitrary text to be attached to the ticket that will be issued
     * from the {@code /auth/authorization} API.
     *
     * @since 3.88
     * @since Authlete 3.0
     */
    private String context;


    /**
     * Get the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 authorization endpoint of the
     * service implementation received from the client application.
     *
     * @return
     *         Request parameters in {@code x-www-form-urlencoded}
     *         format.
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 authorization endpoint of the
     * service implementation received from the client application.
     *
     * @param parameters
     *         Request parameters in {@code x-www-form-urlencoded}
     *         format.
     *
     * @return
     *         {@code this} object.
     */
    @JsonSetter
    public AuthorizationRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Set the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 authorization endpoint of the
     * service implementation received from the client application.
     *
     * <p>
     * This method converts the given map into a string in {@code
     * x-www-form-urlencoded} format and passes it to {@link
     * #setParameters(String)} method.
     * </p>
     *
     * @param parameters
     *         Request parameters.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.24
     */
    public AuthorizationRequest setParameters(Map<String, String[]> parameters)
    {
        return setParameters(URLCoder.formUrlEncode(parameters));
    }


    /**
     * Get the arbitrary text to be attached to the ticket that will be issued
     * from the {@code /auth/authorization} API.
     *
     * <p>
     * The text can be retrieved later by the {@code /auth/authorization/ticket/info}
     * API and can be updated by the {@code /auth/authorization/ticket/update} API.
     * </p>
     *
     * <p>
     * The text will be compressed and encrypted when it is saved in the Authlete
     * database.
     * </p>
     *
     * @return
     *         The arbitrary text to be attached to the ticket.
     *
     * @since 3.88
     * @since Authlete 3.0
     */
    public String getContext()
    {
        return context;
    }


    /**
     * Set the arbitrary text to be attached to the ticket that will be issued
     * from the {@code /auth/authorization} API.
     *
     * <p>
     * The text can be retrieved later by the {@code /auth/authorization/ticket/info}
     * API and can be updated by the {@code /auth/authorization/ticket/update} API.
     * </p>
     *
     * <p>
     * The text will be compressed and encrypted when it is saved in the Authlete
     * database.
     * </p>
     *
     * @param context
     *         The arbitrary text to be attached to the ticket.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.88
     * @since Authlete 3.0
     */
    public AuthorizationRequest setContext(String context)
    {
        this.context = context;

        return this;
    }
}
