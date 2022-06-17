/*
 * Copyright (C) 2014-2022 Authlete, Inc.
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
    /**
     * Get the Authlete API version.
     *
     * @since 3.23
     */
    String getApiVersion();


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
     * Get the public/private key pair used for DPoP
     * signatures in JWK format.
     *
     * @since 2.73
     */
    String getDpopKey();


    /**
     * Get the certificate used for MTLS bound
     * access tokens in PEM format.
     *
     * @since 2.73
     */
    String getClientCertificate();
}
