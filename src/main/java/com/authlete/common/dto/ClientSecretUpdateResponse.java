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


/**
 * Response from Authlete's {@code /api/client/secret/update} API.
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.11
 */
public class ClientSecretUpdateResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    private String newClientSecret;
    private String oldClientSecret;


    /**
     * Get the new client secret.
     *
     * @return
     *         The new client secret.
     */
    public String getNewClientSecret()
    {
        return newClientSecret;
    }


    /**
     * Set the new client secret.
     *
     * @param secret
     *         The new client secret.
     */
    public void setNewClientSecret(String secret)
    {
        this.newClientSecret = secret;
    }


    /**
     * Get the old client secret.
     *
     * @return
     *         The old client secret.
     */
    public String getOldClientSecret()
    {
        return oldClientSecret;
    }


    /**
     * Set the old client secret.
     *
     * @param secret
     *         The old client secret.
     */
    public void setOldClientSecret(String secret)
    {
        this.oldClientSecret = secret;
    }
}
