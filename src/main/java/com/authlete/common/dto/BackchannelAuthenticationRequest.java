/*
 * Copyright (C) 2018 Authlete, Inc.
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
 * Request to Authlete's {@code /api/backchannel/authentication} API.
 *
 * <p>
 * When the implementation of the backchannel authentication endpoint of the
 * authorization server receives a backchannel authentication request from a
 * client application, the first step is to call Authlete's {@code
 * /api/backchannel/authentication} API. The API will parse the backchannel
 * authentication request on behalf of the implementation of the backchannel
 * authentication endpoint.
 * </p>
 *
 * <blockquote>
 * <dl>
 *
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * Parameters of a backchannel authentication request which are the
 * request parameters that the backchannel authentication endpoint of
 * the OpenID provider implementation received from the client application.
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
 * The client ID extracted from {@code Authorization} header of the
 * backchannel authentication request from the client application.
 * </p>
 * <p>
 * If the backchannel authentication endpoint of the OpenID provider
 * implementation supports Basic Authentication as a means of client
 * authentication, and the request from the client application contained
 * its client ID in {@code Authorization} header, the value should be
 * extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientSecret</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client secret extracted from {@code Authorization} header of the
 * backchannel authentication request from the client application.
 * </p>
 * <p>
 * If the backchannel authentication endpoint of the OpenID provider
 * implementation supports Basic Authentication as a means of client
 * authentication, and the request from the client application contained
 * its client secret in {@code Authorization} header, the value should be
 * extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certification used in the TLS connection between the client
 * application and the backchannel authentication endpoint of the OpenID
 * provider.
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
 * @since 2.32
 */
public class BackchannelAuthenticationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * Request parameters of a backchannel authentication request.
     */
    private String parameters;


    /**
     * Client ID extracted from Authorization header.
     */
    private String clientId;


    /**
     * Client secret extracted from Authorization header.
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
     * Get the value of {@code parameters} which are the request parameters
     * that the backchannel authentication endpoint of the OpenID provider
     * implementation received from the client application.
     *
     * @return
     *         Request parameters in {@code application/x-www-form-urlencoded}
     *         format.
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code parameters} which are the request parameters
     * that the backchannel authentication endpoint of the OpenID provider
     * implementation received from the client application.
     *
     * @param parameters
     *         Request parameters in {@code application/x-www-form-urlencoded}
     *         format.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Set the value of {@code parameters} which are the request parameters
     * that the backchannel authentication endpoint of the OpenID provider
     * implementation received from the client application.
     *
     * <p>
     * This method converts the given map into a string in
     * {@code application/x-www-form-urlencoded} and passes it to
     * {@link #setParameters(String)} method.
     * </p>
     *
     * @param parameters
     *         Request parameters.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationRequest setParameters(Map<String, String[]> parameters)
    {
        return setParameters(URLCoder.formUrlEncode(parameters));
    }


    /**
     * Get the client ID extracted from {@code Authorization} header of the
     * backchannel authentication request from the client application.
     *
     * @return
     *         The client ID.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID extracted from {@code Authorization} header of the
     * backchannel authentication request from the client application.
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client secret extracted from {@code Authorization} header of
     * the backchannel authentication request from the client application.
     *
     * @return
     *         The client secret.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret extracted from {@code Authorization} header of
     * the backchannel authentication request from the client application.
     *
     * @param clientSecret
     *         The client secret.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationRequest setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }


    /**
     * Get the client certificate used in the TLS connection between the
     * client application and the backchannel authentication endpoint of the
     * OpenID provider.
     *
     * @return
     *         The client certificate.
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the TLS connection between the
     * client application and the backchannel authentication endpoint of the
     * OpenID provider.
     *
     * @param certificate
     *         The client certificate
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationRequest setClientCertificate(String certificate)
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
    public BackchannelAuthenticationRequest setClientCertificatePath(String[] path)
    {
        this.clientCertificatePath = path;

        return this;
    }
}
