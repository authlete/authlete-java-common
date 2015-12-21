/*
 * Copyright 2015 Authlete, Inc.
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
package com.authlete.common.api;


import com.authlete.common.conf.AuthleteConfiguration;
import com.authlete.common.conf.AuthletePropertiesConfiguration;


/**
 * API factory for the default {@link AuthleteApi} instance.
 *
 * @since 1.24
 */
public class DefaultApiFactory
{
    /**
     * The system property key to specify the name of an Authlete
     * configuration file. When this system property has a value,
     * it is used as the name of the configuration file. Otherwise,
     * the default file (<code>authlete.properties</code>) is used.
     */
    public static final String SYSTEM_PROPERTY_AUTHLETE_CONFIGURATION_FILE =
        "authlete.configuration.file";


    /**
     * {@link AuthleteApi} instance created using an Authlete configuration.
     */
    private static AuthleteApi sAuthleteApi;


    /**
     * Get an instance of {@link AuthleteApi}.
     *
     * <p>
     * This method loads a configuration file (using {@link
     * AuthletePropertiesConfiguration}) on the first call and creates
     * an instance of {@link AuthleteApi}. The second and subsequent
     * calls return a cached instance.
     * </p>
     *
     * <p>
     * The default name of the configuration file is {@code
     * authlete.properties}, but it can be changed by a system property
     * {@code authlete.configuration.file}. The current directory and
     * the classpath are searched for the configuration file in this order.
     * </p>
     *
     * @return
     *         An instance of {@code AuthleteApi}.
     */
    public static AuthleteApi getInstance()
    {
        if (sAuthleteApi != null)
        {
            return sAuthleteApi;
        }

        synchronized (DefaultApiFactory.class)
        {
            if (sAuthleteApi != null)
            {
                return sAuthleteApi;
            }

            // Load an Authlete configuration file.
            AuthleteConfiguration ac = loadAuthleteConfiguration();

            // Create an AuthleteApi instance using the configuration.
            sAuthleteApi = AuthleteApiFactory.getInstance(ac);

            return sAuthleteApi;
        }
    }


    /**
     * Load an Authlete configuration file using {@link
     * AuthletePropertiesConfiguration}.
     */
    private static AuthleteConfiguration loadAuthleteConfiguration()
    {
        // The name of the authlete configuration file specified
        // via the system property.
        String file = System.getProperty(SYSTEM_PROPERTY_AUTHLETE_CONFIGURATION_FILE);

        if (file != null)
        {
            // The system property has a value.
            // Use is as a configuration file name.
            return new AuthletePropertiesConfiguration(file);
        }
        else
        {
            // Use the default configuration file ("authlete.properties").
            return new AuthletePropertiesConfiguration();
        }
    }
}
