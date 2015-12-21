/*
 * Copyright (C) 2015 Authlete, Inc.
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


/**
 * Request to Authlete's {@code /auth/revocation} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * OAuth 2.0 token revocation request parameters which are the request
 * parameters that the OAuth 2.0 token revocation endpoint (<a href=
 * "https://tools.ietf.org/html/rfc7009">RFC 7009</a>) of the service
 * implementation received from the client application.
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
 * If the token revocation endpoint of the service implementation
 * supports Basic Authentication as a means of
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
 * If the token revocation endpoint of the service implementation
 * supports Basic Authentication as a means of
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
 * An entity body of a token revocation request may contain the client
 * ID ({@code client_id}) and the client secret ({@code client_secret})
 * along with other request parameters as described in
 * <a href="http://tools.ietf.org/html/rfc6749#section-2.3.1"
 * >RFC 6749, 2.3.1. Client Password</a>. If the client credentials
 * are contained in both {@code Authorization} header and the entity
 * body, they must be identical. Otherwise, Authlete's {@code
 * /auth/revocation} API generates an error (it's not a service error
 * but a client error).
 * </p>
 *
 * <p>
 * When the presented token is an access token, the implementation
 * revokes the access token and its associated refresh token, too.
 * Likewise, the presented token is a refresh token, the implementation
 * revokes the refresh token and its associated access token. Note that,
 * however, other access tokens and refresh tokens are not revoked
 * even though their associated client application, subject and grant
 * type are equal to those of the token to be revoked.
 * </p>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.16
 */
public class RevocationRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * OAuth 2.0 token revocation request parameters.
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
     * Get the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 token revocation endpoint of
     * the service implementation received from the client application.
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 token revocation endpoint of
     * the service implementation received from the client application.
     */
    public RevocationRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Set the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 token revocation endpoint of
     * the service implementation received from the client application.
     *
     * <p>
     * This method converts the given map into a string in {@code
     * x-www-form-urlencoded} and passes it to {@link
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
    public RevocationRequest setParameters(Map<String, String[]> parameters)
    {
        return setParameters(URLCoder.formUrlEncode(parameters));
    }


    /**
     * Get the client ID extracted from {@code Authorization} header
     * of the token revocation request from the client application.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID extracted from {@code Authorization} header
     * of the token revocation request from the client application.
     */
    public RevocationRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client secret extracted from {@code Authorization} header
     * of the token revocation request from the client application.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret extracted from {@code Authorization} header
     * of the token revocation request from the client application.
     */
    public RevocationRequest setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }
}
