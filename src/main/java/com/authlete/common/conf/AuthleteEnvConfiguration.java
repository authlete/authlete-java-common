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
 * Implementation of {@link AuthleteConfiguration} based on
 * environment variables.
 *
 * <p>
 * This class refers to the following environment variables.
 * </p>
 *
 * <blockquote>
 * <dl>
 *   <dt><b>AUTHLETE_BASE_URL</b></dt>
 *   <dd><p>The base URL.</p></dd>
 *
 *   <dt><b>AUTHLETE_SERVICEOWNER_APIKEY</b></dt>
 *   <dd><p>The API key of the service owner.</p></dd>
 *
 *   <dt><b>AUTHLETE_SERVICEOWNER_APISECRET</b></dt>
 *   <dd><p>The API secret of the service owner.</p></dd>
 *
 *   <dt><b>AUTHLETE_SERVICE_APIKEY</b></dt>
 *   <dd><p>The API key of the service.</p></dd>
 *
 *   <dt><b>AUTHLETE_SERVICE_APISECRET</b></dt>
 *   <dd><p>The API secret of the service.</p></dd>
 * </dl>
 * </blockquote>
 */
public class AuthleteEnvConfiguration implements AuthleteConfiguration
{
    private static final String ENV_KEY_BASE_URL                   = "AUTHLETE_BASE_URL";
    private static final String ENV_KEY_SERVICE_OWNER_API_KEY      = "AUTHLETE_SERVICEOWNER_APIKEY";
    private static final String ENV_KEY_SERVICE_OWNER_API_SECRET   = "AUTHLETE_SERVICEOWNER_APISECRET";
    private static final String ENV_KEY_SERVICE_API_KEY            = "AUTHLETE_SERVICE_APIKEY";
    private static final String ENV_KEY_SERVICE_API_SECRET         = "AUTHLETE_SERVICE_APISECRET";
    private static final String ENV_KEY_SERVICE_OWNER_ACCESS_TOKEN = "AUTHLETE_SERVICEOWNER_ACCESSTOKEN";
    private static final String ENV_KEY_SERVICE_ACCESS_TOKEN       = "AUTHLETE_SERVICE_ACCESSTOKEN";
    private static final String ENV_KEY_DPOP_KEY                   = "AUTHLETE_DPOP_KEY";
    private static final String ENV_KEY_CLIENT_CERTIFICATE         = "AUTHLETE_CLIENT_CERTIFICATE";


    @Override
    public String getBaseUrl()
    {
        return get(ENV_KEY_BASE_URL);
    }


    @Override
    public String getServiceOwnerApiKey()
    {
        return get(ENV_KEY_SERVICE_OWNER_API_KEY);
    }


    @Override
    public String getServiceOwnerApiSecret()
    {
        return get(ENV_KEY_SERVICE_OWNER_API_SECRET);
    }


    @Override
    public String getServiceOwnerAccessToken()
    {
        return get(ENV_KEY_SERVICE_OWNER_ACCESS_TOKEN);
    }


    @Override
    public String getServiceApiKey()
    {
        return get(ENV_KEY_SERVICE_API_KEY);
    }


    @Override
    public String getServiceApiSecret()
    {
        return get(ENV_KEY_SERVICE_API_SECRET);
    }


    @Override
    public String getServiceAccessToken()
    {
        return get(ENV_KEY_SERVICE_ACCESS_TOKEN);
    }


    @Override
    public String getDpopKey()
    {
        return get(ENV_KEY_DPOP_KEY);
    }


    @Override
    public String getClientCertificate()
    {
        return get(ENV_KEY_CLIENT_CERTIFICATE);
    }


    private String get(String key)
    {
        return System.getenv(key);
    }
}
