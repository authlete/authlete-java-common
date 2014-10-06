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


/**
 * This is an abstract class that provides getters and setters
 * for key-value pairs. Subclasses are required to implement
 * the following abstract methods.
 *
 * <ul>
 * <li>{@code void }{@link #clear()}
 * <li>{@code boolean }{@link #contains(String) contains(String key)}
 * <li>{@code String }{@link #getString(String, String) getString(String key, String defaultValue)}
 * <li>{@code void }{@link #remove(String) remove(String key)}
 * <li>{@code void }{@link #setString(String, String) setString(String key, String value)}
 * </ul>
 *
 * <p>
 * Methods below which this class implements are just wrappers over
 * {@link #getString(String, String) getString(String key, String defaultValue)} or
 * {@link #setString(String, String) setString(String key, String value)}.
 * </p>
 *
 * <ul>
 * <li>{@code boolean }{@link #getBoolean(String, boolean) getBoolean(String key, boolean defaultValue)}
 * <li>{@code float }{@link #getFloat(String, float) getFloat(String key, float defaultValue)}
 * <li>{@code int }{@link #getInt(String, int) getInt(String key, int defaultValue)}
 * <li>{@code long }{@link #getLong(String, long) getLong(String key, long defaultValue)}
 * <li>{@code void }{@link #setBoolean(String, boolean) setBoolean(String key, boolean value)}
 * <li>{@code void }{@link #setFloat(String, float) setFloat(String key, float value)}
 * <li>{@code void }{@link #setInt(String, int) setInt(String key, int value)}
 * <li>{@code void }{@link #setLong(String, long) setLong(String key, long value)}
 * </ul>
 *
 * @author Takahiko Kawasaki
 */
public abstract class StringBasedTypedProperties extends TypedProperties
{
    protected StringBasedTypedProperties()
    {
    }


    @Override
    public boolean getBoolean(String key, boolean defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        String value = getString(key, null);

        if (value == null)
        {
            return defaultValue;
        }

        return Boolean.parseBoolean(value);
    }


    @Override
    public float getFloat(String key, float defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        String value = getString(key, null);

        if (value == null)
        {
            return defaultValue;
        }

        try
        {
            return Float.parseFloat(value);
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }
    }


    @Override
    public int getInt(String key, int defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        String value = getString(key, null);

        if (value == null)
        {
            return defaultValue;
        }

        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }
    }


    @Override
    public long getLong(String key, long defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        String value = getString(key, null);

        if (value == null)
        {
            return defaultValue;
        }

        try
        {
            return Long.parseLong(value);
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }
    }


    @Override
    public void setBoolean(String key, boolean value)
    {
        if (key == null)
        {
            return;
        }

        setString(key, String.valueOf(value));
    }


    @Override
    public void setFloat(String key, float value)
    {
        if (key == null)
        {
            return;
        }

        setString(key, String.valueOf(value));
    }


    @Override
    public void setInt(String key, int value)
    {
        if (key == null)
        {
            return;
        }

        setString(key, String.valueOf(value));
    }


    @Override
    public void setLong(String key, long value)
    {
        if (key == null)
        {
            return;
        }

        setString(key, String.valueOf(value));
    }
}
