/*
 * Copyright (C) 2019 Authlete, Inc.
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
 * Request to Authlete's {@code /api/requestobject} API.
 *
 * <p>
 * The authorization server can implement a request object endpoint which
 * is defined in "Pushed Request Object" by using the Authlete API.
 * </p>
 *
 * <p>
 * Request parameters to the API are as follows.
 * </p>
 *
 * <blockquote>
 * <dl>
 *
 * <dt><b><code>body</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The HTTP message body of the request sent to the request object endpoint.
 * The format is either plain JSON or JWT.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientId</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client ID extracted from the {@code Authorization} header of the request
 * to the request object endpoint.
 * </p>
 * <p>
 * If the request object endpoint of the authorization server implementation
 * supports Basic Authentication as a means of client authentication, and the
 * request from the client application contained its client ID in the
 * {@code Authorization} header, the value should be extracted and set to this
 * parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientSecret</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client secret extracted from the {@code Authorization} header of the
 * request to the request object endpoint.
 * </p>
 * <p>
 * If the request object endpoint of the authorization server implementation
 * supports Basic Authentication as a means of client authentication, and the
 * request from the client application contained its client secret in the
 * {@code Authorization} header, the value should be extracted and set to this
 * parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate used in the TLS connection between the client
 * application and the request object endpoint of the authorization server.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificatePath</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate path presented by the client during client
 * authentication. Each element is a string in PEM format.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @since 2.50
 */
public class RequestObjectRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The HTTP message body sent to the request object endpoint.
     * Its format is either plain JSON or JWT.
     */
    private String body;


    /**
     * Client ID extracted from the Authorization header.
     */
    private String clientId;


    /**
     * Client secret extracted from the Authorization header.
     */
    private String clientSecret;


    /**
     * Client certificate.
     */
    private String clientCertificate;


    /**
     * Client certificate path.
     */
    private String[] clientCertificatePath;


    /**
     * Get the value of the HTTP message body sent to the request object
     * endpoint. Its format is either plain JSON or JWT.
     *
     * @return
     *         The HTTP message body sent to the request object endpoint.
     */
    public String getBody()
    {
        return body;
    }


    /**
     * Set the value of the HTTP message body sent to the request object
     * endpoint. Its format is either plain JSON or JWT.
     *
     * @param body
     *         The HTTP message body sent to the request object endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectRequest setBody(String body)
    {
        this.body = body;

        return this;
    }


    /**
     * Get the client ID extracted from the {@code Authorization} header of the
     * request to the request object endpoint.
     *
     * @return
     *         The client ID.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID extracted from the {@code Authorization} header of the
     * request to the request object endpoint.
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client secret extracted from the {@code Authorization} header of
     * the request to the request object endpoint.
     *
     * @return
     *         The client secret.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret extracted from the {@code Authorization} header of
     * the request to the request object endpoint.
     *
     * @param clientSecret
     *         The client secret.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectRequest setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }


    /**
     * Get the client certificate used in the TLS connection between the client
     * application and the request object endpoint.
     *
     * @return
     *         The client certificate.
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the TLS connection between the client
     * application and the request object endpoint.
     *
     * @param certificate
     *         The client certificate.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectRequest setClientCertificate(String certificate)
    {
        this.clientCertificate = certificate;

        return this;
    }


    /**
     * Get the client certificate path presented by the client during client
     * authentication.
     *
     * @return
     *          The client certificate path. Each element is a string in PEM
     *          format.
     */
    public String[] getClientCertificatePath()
    {
        return clientCertificatePath;
    }


    /**
     * Set the client certificate path presented by the client during client
     * authentication.
     *
     * @param path
     *         The client certificate path.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectRequest setClientCertificatePath(String[] path)
    {
        this.clientCertificatePath = path;

        return this;
    }
}
