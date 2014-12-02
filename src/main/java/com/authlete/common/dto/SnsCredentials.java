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
import com.authlete.common.types.Sns;


/**
 * SNS credentials (API key and API secret).
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.3
 */
public class SnsCredentials implements Serializable
{
    private static final long serialVersionUID = 1L;


    private Sns sns;
    private String apiKey;
    private String apiSecret;


    /**
     * Get the SNS.
     *
     * @return
     *         The SNS.
     */
    public Sns getSns()
    {
        return sns;
    }


    /**
     * Set the SNS.
     *
     * @param sns
     *         The SNS.
     *
     * @return
     *         {@code this} object.
     */
    public SnsCredentials setSns(Sns sns)
    {
        this.sns = sns;

        return this;
    }


    /**
     * Get the API key.
     *
     * @return
     *         The API key.
     */
    public String getApiKey()
    {
        return apiKey;
    }


    /**
     * Set the API key.
     *
     * @param apiKey
     *         The API key.
     *
     * @return
     *         {@code this} object.
     */
    public SnsCredentials setApiKey(String apiKey)
    {
        this.apiKey = apiKey;

        return this;
    }


    /**
     * Get the API secret.
     *
     * @return
     *         The API secret.
     */
    public String getApiSecret()
    {
        return apiSecret;
    }


    /**
     * Set the API secret.
     *
     * @param apiSecret
     *         The API secret.
     *
     * @return
     *         {@code this} object.
     */
    public SnsCredentials setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;

        return this;
    }
}
