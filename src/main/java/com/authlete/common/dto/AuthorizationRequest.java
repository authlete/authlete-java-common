/*
 * Copyright (C) 2014 Authlete, Inc.
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
    private static final long serialVersionUID = 1L;


    /**
     * OAuth 2.0 authorization request parameters.
     */
    private String parameters;


    /**
     * Get the value of {@code "parameters"} which are the request
     * parameters that the OAuth 2.0 authorization endpoint of the
     * service implementation received from the client application.
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code "parameters"} which are the request
     * parameters that the OAuth 2.0 authorization endpoint of the
     * service implementation received from the client application.
     */
    public AuthorizationRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }
}
