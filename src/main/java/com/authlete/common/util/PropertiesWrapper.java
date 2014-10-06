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
package com.authlete.common.util;


import java.util.Properties;


/**
 * Properties wrapper.
 *
 * @author Takahiko Kawasaki
 */
public class PropertiesWrapper extends StringBasedTypedProperties
{
    private final Properties properties;


    /**
     * Constructor with a {@code Properties} instance to be wrapped.
     *
     * @param properties
     *         {@code Properties} instance to be wrapped.
     *
     * @throws IllegalArgumentException
     *         {@code properties} is {@code null}.
     */
    public PropertiesWrapper(Properties properties)
    {
        if (properties == null)
        {
            throw new IllegalArgumentException("properties is null.");
        }

        this.properties = properties;
    }


    @Override
    public boolean contains(String key)
    {
        return properties.containsKey(key);
    }


    @Override
    public String getString(String key, String defaultValue)
    {
        return properties.getProperty(key, defaultValue);
    }


    @Override
    public void setString(String key, String value)
    {
        properties.setProperty(key, value);
    }


    @Override
    public void remove(String key)
    {
        properties.remove(key);

    }


    @Override
    public void clear()
    {
        properties.clear();
    }
}
