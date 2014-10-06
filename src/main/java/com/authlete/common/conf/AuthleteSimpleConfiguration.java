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
package com.authlete.common.conf;


/**
 * POJO implementation of {@link AuthleteConfiguration}.
 *
 * @author Takahiko Kawasaki
 */
public class AuthleteSimpleConfiguration implements AuthleteConfiguration
{
    private String baseUrl;
    private String serviceOwnerApiKey;
    private String serviceOwnerApiSecret;
    private String serviceApiKey;
    private String serviceApiSecret;


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
}
