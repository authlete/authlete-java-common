/*
 * Copyright (C) 2016 Authlete, Inc.
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
 * Property that consists of a string key and a string value.
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.32
 */
public class Property implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String key;
    private String value;


    /**
     * Constructor with a {@code null} key and a {@code null} value.
     */
    public Property()
    {
    }


    /**
     * Constructor with a pair of key and value.
     *
     * @param key
     * @param value
     */
    public Property(String key, String value)
    {
        this.key   = key;
        this.value = value;
    }


    /**
     * Get the key.
     *
     * @return
     *         Key
     */
    public String getKey()
    {
        return key;
    }


    /**
     * Set the key.
     *
     * @param key
     *         Key
     *
     * @return
     *         {@code this} object.
     */
    public Property setKey(String key)
    {
        this.key = key;

        return this;
    }


    /**
     * Get the value.
     *
     * @return
     *         Value
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Set the value.
     *
     * @param value
     *         Value
     *
     * @return
     *         {@code this} object.
     */
    public Property setValue(String value)
    {
        this.value = value;

        return this;
    }
}
