/*
 * Copyright (C) 2017 Authlete, Inc.
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


/**
 * A pair of a string key and a string value.
 *
 * <p>
 * {@code java.util.AbstractMap.SimpleEntry} class provides the same
 * functionality in a more generic way, but the class is not available
 * in Java SE 1.5.
 * </p>
 *
 * @since 1.39
 *
 * @author Takahiko Kawasaki
 */
public class Pair implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String key;
    private String value;


    /**
     * Constructor with a {@code null} key and a {@code null} value.
     */
    public Pair()
    {
        this(null, null);
    }


    /**
     * Constructor with an initial key and an initial value.
     *
     * @param key
     *         The initial value of the key.
     *
     * @param value
     *         The initial value of the value.
     */
    public Pair(String key, String value)
    {
        this.key   = key;
        this.value = value;
    }


    /**
     * Get the key of this pair.
     *
     * @return
     *         The key.
     */
    public String getKey()
    {
        return key;
    }


    /**
     * Set the key of this pair.
     *
     * @param key
     *         The key.
     *
     * @return
     *         {@code this} object.
     */
    public Pair setKey(String key)
    {
        this.key = key;

        return this;
    }


    /**
     * Get the value of this pair.
     *
     * @return
     *         The value.
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Set the value of this pair.
     *
     * @param value
     *         The value.
     *
     * @return
     *         {@code this} object.
     */
    public Pair setValue(String value)
    {
        this.value = value;

        return this;
    }
}
