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
 * Authlete configuration.
 */
public interface AuthleteConfiguration
{
    public enum ApiVersion
    {
        V2, V3
    }


    /**
     * Get the supported API version as an enum, defaults to V2.
     */
    default ApiVersion getApiVersion()
    {
        String apiVersionString = getApiVersionString();
        if (apiVersionString == null)
        {
            return ApiVersion.V2;
        }
        else
        {
            return ApiVersion.valueOf(apiVersionString);
        }
    }


    /**
     * Get the supported API version as a string.
     */
    String getApiVersionString();

    /**
     * Get the base URL.
     */
    String getBaseUrl();


    /**
     * Get the service owner API key.
     */
    String getServiceOwnerApiKey();


    /**
     * Get the service owner API secret.
     */
    String getServiceOwnerApiSecret();


    /**
     * Get the service owner API access token
     */
    String getServiceOwnerAccessToken();


    /**
     * Get the service API key.
     */
    String getServiceApiKey();


    /**
     * Get the service API secret.
     */
    String getServiceApiSecret();


    /**
     * Get the service API access token
     */
    String getServiceAccessToken();


    /**
     * Get the public/private keypair used for DPoP
     * signatures in JWK format.
     */
    String getDpopKey();


    /**
     * Get the certificate used for MTLS bound
     * access tokens in PEM format.
     */
    String getClientCertificate();
}
