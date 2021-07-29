/*
 * Copyright (C) 2014-2017 Authlete, Inc.
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
package com.authlete.common.api;


import java.lang.reflect.Constructor;
import java.util.logging.Logger;

import com.authlete.common.conf.AuthleteConfiguration;
import com.authlete.common.conf.AuthleteEnvConfiguration;
import com.authlete.common.conf.AuthletePropertiesConfiguration;


/**
 * Factory to create an {@link AuthleteApi} instance.
 *
 * @author Takahiko Kawasaki
 */
public class AuthleteApiFactory
{
	
	/**
     * The system property key to specify the source for the
     * configuration ({@code authlete.default.configuration.source}) of
     * default api.
     * When this system property has a value as "ENV", Authlete default
     * API will be configured from system environments variables.
     * 
     * @since 2.99
     */
    public static final String SYSTEM_PROPERTY_AUTHLETE_DEFAULT_CONFIGURATION_SOURCE =
        "AUTHLETE_CONFIG_SOURCE";
    
    /**
     * An implementation of {@link AuthleteApi} using JAX-RS.
     * This implementation exists in authlete/authlete-java-jaxrs.
     */
    private static final String IMPL_JAX_RS = "com.authlete.jaxrs.api.AuthleteApiImpl";


    /**
     * An implementation of {@link AuthleteApi} using {@link java.net.HttpURLConnection HttpURLConnection}.
     * This implementation exists in authlete/authlete-java-common.
     */
    private static final String IMPL_HTTP_URL_CONNECTION = "com.authlete.common.api.AuthleteApiImpl";


    /**
     * Known implementations of AuthleteApi.
     */
    private static final String[] sKnownImpls = {
        IMPL_JAX_RS,
        IMPL_HTTP_URL_CONNECTION
    };


    /**
     * The default {@link AuthleteApi} instance.
     */
    private static volatile AuthleteApi sDefaultApi;


    private AuthleteApiFactory()
    {
    }


    /**
     * Create an instance of {@link AuthleteApi}.
     *
     * <p>
     * This method repeats to call {@link #create(AuthleteConfiguration, String)}
     * until one of the known classes is successfully instantiated.
     * </p>
     *
     * <p>
     * The classes listed below are the ones that the current implementation knows.
     * </p>
     *
     * <ol>
     * <li><code>com.authlete.jaxrs.api.AuthleteApiImpl</code><br/>
     *     (using JAX-RS 2.0 API, contained in <code>com.authlete:authlete-java-jaxrs</code>)
     * <li><code>com.authlete.common.api.AuthleteApiImpl</code><br/>
     *     (using {@link java.net.HttpURLConnection HttpURLConnection}, contained in <code>com.authlete:authlete-java-common</code> since version 2.0)
     * </ol>
     *
     * @param configuration
     *         Authlete configuration.
     *
     * @return
     *         An instance of {@link AuthleteApi}. If none of the known classes
     *         that implement {@code AuthleteApi} interface was successfully
     *         instantiated, {@code null} is returned.
     */
    public static AuthleteApi create(AuthleteConfiguration configuration)
    {
        for (String className : sKnownImpls)
        {
            try
            {
                return create(configuration, className);
            }
            catch (Exception e)
            {
                // Ignore.
            }
        }

        // No implementation was found.
        return null;
    }


    /**
     * Create an instance of {@link AuthleteApi} from the specified class.
     *
     * @param configuration
     *        Authlete configuration.
     *
     * @param className
     *         The name of a class that implements {@link AuthleteApi}
     *         interface. The class must have a constructor which takes
     *         one argument of type {@link AuthleteConfiguration}.
     *
     * @return
     *         An instance of the specified class.
     *
     * @throws IllegalArgumentException
     *         <ul>
     *         <li>{@code configuration} is {@code null}.
     *         <li>{@code className} is {@code null}.
     *         <li>The specified class is not found.
     *         <li>The specified class does not implement {@link AuthleteApi} interface.
     *         <li>The specified class does not have a constructor which takes one
     *             argument of type {@link AuthleteConfiguration}.
     *         </ul>
     *
     * @throws IllegalStateException
     *         The constructor of the specified class threw an exception.
     */
    public static AuthleteApi create(AuthleteConfiguration configuration, String className)
    {
        if (configuration == null)
        {
            throw new IllegalArgumentException("configuration is null.");
        }

        if (className == null)
        {
            throw new IllegalArgumentException("className is null.");
        }

        Class<?> clazz;

        try
        {
            clazz = Class.forName(className);
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalArgumentException(className + " is not found.", e);
        }

        if (AuthleteApi.class.isAssignableFrom(clazz) == false)
        {
            throw new IllegalArgumentException(className + " does not implement AuthleteApi interface.");
        }

        Constructor<?> constructor;

        try
        {
            constructor = clazz.getConstructor(AuthleteConfiguration.class);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(className + " does not have a constructor which takes one AuthleteConfiguration parameter.", e);
        }

        AuthleteApi api;

        try
        {
            api = (AuthleteApi)constructor.newInstance(configuration);
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Failed to create an instance of " + className + ".", e);
        }

        return api;
    }


    /**
     * Get the default instance of {@link AuthleteApi}.
     *
     * <p>
     * This method loads a configuration file (using {@link
     * AuthletePropertiesConfiguration}) on the first call, creates
     * an instance of {@link AuthleteApi} and caches the instance.
     * The second and subsequent calls return the cached instance.
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
     *
     * @since 1.29
     */
    public static AuthleteApi getDefaultApi()
    {
        if (sDefaultApi != null)
        {
            return sDefaultApi;
        }

        synchronized (AuthleteApiFactory.class)
        {
            if (sDefaultApi != null)
            {
                return sDefaultApi;
            }

            // Load an Authlete configuration file.
            AuthleteConfiguration ac ;
            
            if(System.getenv().getOrDefault(SYSTEM_PROPERTY_AUTHLETE_DEFAULT_CONFIGURATION_SOURCE, "FILE").equalsIgnoreCase("ENV")) {
            	ac = new AuthleteEnvConfiguration();
            } else {
            	ac = new AuthletePropertiesConfiguration();
            }
            

            // Create an AuthleteApi instance using the configuration.
            sDefaultApi = create(ac);

            return sDefaultApi;
        }
    }
}
