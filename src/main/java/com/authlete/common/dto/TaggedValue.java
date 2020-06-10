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
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * A string value with a <a href="http://en.wikipedia.org/wiki/IETF_language_tag"
 * >language tag</a>
 *
 * @author Takahiko Kawasaki
 *
 * @see <a href="http://en.wikipedia.org/wiki/IETF_language_tag">Language tag</a>
 */
public class TaggedValue implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String tag;
    private String value;


    /**
     * The default constructor.
     */
    public TaggedValue()
    {
    }


    /**
     * Constructor with a tag and a value.
     *
     * @param tag
     * @param value
     */
    public TaggedValue(String tag, String value)
    {
        this.tag   = tag;
        this.value = value;
    }


    /**
     * Get the tag.
     *
     * @return
     *         The tag.
     */
    public String getTag()
    {
        return tag;
    }


    /**
     * Set the tag.
     *
     * @param tag
     *
     * @return
     *         {@code this} object.
     */
    public TaggedValue setTag(String tag)
    {
        this.tag = tag;

        return this;
    }


    /**
     * Get the value.
     *
     * @return
     *         The value.
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Set the value.
     *
     * @param value
     *
     * @return
     *         {@code this} object.
     */
    public TaggedValue setValue(String value)
    {
        this.value = value;

        return this;
    }
}
