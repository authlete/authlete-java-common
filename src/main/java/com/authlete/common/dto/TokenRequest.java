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
 * Request to Authlete's {@code /auth/token} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * OAuth 2.0 token request parameters which are the request parameters
 * that the OAuth 2.0 token endpoint of the service implementation
 * received from the client application.
 * </p>
 * <p>
 * The value of {@code "parameters"} is the entire entity body (which
 * is formatted in {@code application/x-www-form-urlencoded}) of the
 * request from the client application.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientId</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client ID extracted from {@code Authorization} header of
 * the token request from the client application.
 * </p>
 * <p>
 * If the token endpoint of the service implementation supports
 * Basic Authentication as a means of
 * <a href="http://tools.ietf.org/html/rfc6749#section-2.3">client
 * authentication</a>, and the request from the client application
 * contained its client ID in {@code Authorization} header, the
 * value should be extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientSecret</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client secret extracted from {@code Authorization} header
 * of the token request from the client application.
 * </p>
 * <p>
 * If the token endpoint of the service implementation supports
 * Basic Authentication as a means of
 * <a href="http://tools.ietf.org/html/rfc6749#section-2.3">client
 * authentication</a>, and the request from the client application
 * contained its client secret in {@code Authorization} header, the
 * value should be extracted and set to this parameter.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * <p>
 * An entity body of a token request may contain the client ID
 * ({@code client_id}) and the client secret ({@code client_secret})
 * along with other request parameters as described in
 * <a href="http://tools.ietf.org/html/rfc6749#section-2.3.1"
 * >RFC 6749, 2.3.1. Client Password</a>. If the client credentials
 * are contained in both {@code Authorization} header and the entity
 * body, they must be identical. Otherwise, Authlete's {@code
 * /auth/token} API generates an error (it's not a service error but
 * a client error).
 * </p>
 *
 * @author Takahiko Kawasaki
 */
public class TokenRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * OAuth 2.0 token request parameters.
     */
    private String parameters;


    /**
     * Client ID.
     */
    private String clientId;


    /**
     * Client secret.
     */
    private String clientSecret;


    /**
     * Get the value of {@code "parameters"} which are the request
     * parameters that the OAuth 2.0 token endpoint of the service
     * implementation received from the client application.
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code "parameters"} which are the request
     * parameters that the OAuth 2.0 token endpoint of the service
     * implementation received from the client application.
     */
    public TokenRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Get the client ID extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public TokenRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client secret extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public TokenRequest setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }
}
