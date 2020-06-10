/*
 * Copyright (C) 2014-2018 Authlete, Inc.
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
package com.authlete.common.conf;


/**
 * POJO implementation of {@link AuthleteConfiguration}.
 */
public class AuthleteSimpleConfiguration implements AuthleteConfiguration
{
    private String baseUrl;
    private String serviceOwnerApiKey;
    private String serviceOwnerApiSecret;
    private String serviceOwnerAccessToken;
    private String serviceApiKey;
    private String serviceApiSecret;
    private String serviceAccessToken;
    private String dpopKey;
    private String clientCertificate;


    @Override
    public String getBaseUrl()
    {
        return baseUrl;
    }


    /**
     * Set the base URL.
     *
     * @param baseUrl
     *         The base URL.
     *
     * @return
     *         {@code this} object.
     */
    public AuthleteSimpleConfiguration setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;

        return this;
    }


    @Override
    public String getServiceOwnerApiKey()
    {
        return serviceOwnerApiKey;
    }


    /**
     * Set the API key of the service owner.
     *
     * @param apiKey
     *         The API key of the service owner.
     *
     * @return
     *         {@code this} object.
     */
    public AuthleteSimpleConfiguration setServiceOwnerApiKey(String apiKey)
    {
        this.serviceOwnerApiKey = apiKey;

        return this;
    }


    @Override
    public String getServiceOwnerApiSecret()
    {
        return serviceOwnerApiSecret;
    }


    /**
     * Set the API secret of the service owner.
     *
     * @param apiSecret
     *         The API secret of the service owner.
     *
     * @return
     *         {@code this} object.
     */
    public AuthleteSimpleConfiguration setServiceOwnerApiSecret(String apiSecret)
    {
        this.serviceOwnerApiSecret = apiSecret;

        return this;
    }


    @Override
    public String getServiceOwnerAccessToken()
    {
        return serviceOwnerAccessToken;
    }


    /**
     * Set the access token of the service owner.
     *
     * @param accessToken
     *         The access token of the service owner.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.30
     */
    public AuthleteSimpleConfiguration setServiceOwnerAccessToken(String accessToken)
    {
        this.serviceOwnerAccessToken = accessToken;

        return this;
    }


    @Override
    public String getServiceApiKey()
    {
        return serviceApiKey;
    }


    /**
     * Set the API key of the service.
     *
     * @param apiKey
     *         The API key of the service.
     *
     * @return
     *         {@code this} object.
     */
    public AuthleteSimpleConfiguration setServiceApiKey(String apiKey)
    {
        this.serviceApiKey = apiKey;

        return this;
    }


    @Override
    public String getServiceApiSecret()
    {
        return serviceApiSecret;
    }


    /**
     * Set the API secret of the service.
     *
     * @param apiSecret
     *         The API secret of the service.
     *
     * @return
     *         {@code this} object.
     */
    public AuthleteSimpleConfiguration setServiceApiSecret(String apiSecret)
    {
        this.serviceApiSecret = apiSecret;

        return this;
    }


    @Override
    public String getServiceAccessToken()
    {
        return serviceAccessToken;
    }


    /**
     * Set the access token of the service.
     *
     * @param accessToken
     *         The access token of the service.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.30
     */
    public AuthleteSimpleConfiguration setServiceAccessToken(String accessToken)
    {
        this.serviceAccessToken = accessToken;

        return this;
    }


    @Override
    public String getDpopKey()
    {
        return dpopKey;
    }


    /**
     * Set the DPoP access public and private key pair
     * in serialized JWK format.
     */
    public AuthleteSimpleConfiguration setDpopKey(String dpopKey)
    {
        this.dpopKey = dpopKey;

        return this;
    }


    @Override
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client's MTLS certificate in PEM format.
     */
    public AuthleteSimpleConfiguration setClientCertificate(String clientCertificate)
    {
        this.clientCertificate = clientCertificate;

        return this;
    }
}
