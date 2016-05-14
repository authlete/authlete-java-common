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
 * <p>
 * This class is used mainly to represent an extra property
 * that is associated with an access token. Some Authlete APIs
 * (such as {@code /api/auth/token} API) accept an array of
 * properties via {@code properties} request parameter and
 * associate the properties with an access token.
 * </p>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.32
 */
public class Property implements Serializable
{
    private static final long serialVersionUID = 2L;


    private String key;
    private String value;
    private boolean hidden;


    /**
     * Constructor with a {@code null} key and a {@code null} value.
     */
    public Property()
    {
    }


    /**
     * Constructor with a pair of key and value. This is an alias of
     * {@link #Property(String, String, boolean) this}{@code
     * (key, value, false)}.
     *
     * @param key
     * @param value
     */
    public Property(String key, String value)
    {
        this(key, value, false);
    }


    /**
     * Constructor with a pair of key and value, and a flag to mark
     * this property as hidden or not.
     *
     * @param key
     * @param value
     * @param hidden
     *         {@code true} to mark this property as hidden. See the
     *         JavaDoc of {@link #isHidden()} for details.
     *
     * @since 1.33
     */
    public Property(String key, String value, boolean hidden)
    {
        this.key    = key;
        this.value  = value;
        this.hidden = hidden;
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


    /**
     * Check if this property is hidden from client applications.
     *
     * <p>
     * If a property is not hidden, the property will come along with an access token.
     * For example, if you set the {@code properties} request parameter as follows when
     * you call Authlete's {@code /api/auth/token} API,
     * </p>
     *
     * <blockquote>
     * <pre style="border: 1px solid gray; padding: 0.5em;"> "properties": [
     *     {
     *         "key":"example_parameter",
     *         "value":"example_value",
     *         "hidden":false
     *     }
     * ]</pre>
     * </blockquote>
     *
     * <p>
     * The value of {@code responseContent} in the response from the API will contain
     * the pair of {@code example_parameter} and {@code example_value} like below,
     * </p>
     *
     * <blockquote>
     * <pre style="border: 1px solid gray; padding: 0.5em;"
     * > "responseContent": "{\"access_token\":\"(abbrev)\",\"example_parameter\":\"example_value\",...}"</pre>
     * </blockquote>
     *
     * <p>
     * and this will result in that the client application will receive a JSON which contains
     * the pair like the following.
     * </p>
     *
     * <blockquote>
     * <pre style="border: 1px solid gray; padding: 0.5em;"> {
     *     "access_token":"(abbrev)",
     *     "example_parameter":"example_value",
     *     ...
     * }</pre>
     * </blockquote>
     *
     * <p>
     * On the other hand, if you mark a property as <i>hidden</i> like below,
     * </p>
     *
     * <blockquote>
     * <pre style="border: 1px solid gray; padding: 0.5em;"> "properties": [
     *     {
     *         "key":"hidden_parameter",
     *         "value":"hidden_value",
     *         "hidden":true
     *     }
     * ]</pre>
     * </blockquote>
     *
     * <p>
     * the client application will never see the property in any response from
     * your authorization server. However, of course, the property is still
     * associated with the access token and it can be confirmed by calling
     * Authlete's {@code /api/auth/introspection} API (which is an API to get
     * information about an access token). A response from the API contains all
     * properties associated with the given access token regardless of whether
     * they are hidden or visible. The following is an example from Authlete's
     * introspection API.
     * </p>
     *
     * <blockquote>
     * <pre style="border: 1px solid gray; padding: 0.5em;"> {
     *   "type":"introspectionResponse",
     *   "resultCode":"A056001",
     *   "resultMessage":"[A056001] The access token is valid.",
     *   "action":"OK",
     *   "clientId":5008706718,
     *   "existent":true,
     *   "expiresAt":1463310477000,
     *   "properties":[
     *     {
     *       "hidden":false,
     *       "key":"example_parameter",
     *       "value":"example_value"
     *     },
     *     {
     *       "hidden":true,
     *       "key":"hidden_parameter",
     *       "value":"hidden_value"
     *     }
     *   ],
     *   "refreshable":true,
     *   "responseContent":"Bearer error=\"invalid_request\"",
     *   "subject":"user123",
     *   "sufficient":true,
     *   "usable":true
     * }</pre>
     * </blockquote>
     *
     * @return
     *         {@code true} if this property is hidden from client applications.
     *         {@code false} if this property is visible to client applications.
     *
     * @since 1.33
     */
    public boolean isHidden()
    {
        return hidden;
    }


    /**
     * Set this property hidden from or visible to client applications.
     * See the JavaDoc of {@link #isHidden()} method for the difference between
     * hidden and visible.
     *
     * @param hidden
     *         {@code true} to hide this property from client applications.
     *         {@code false} to make this property visible to client applications.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.33
     */
    public Property setHidden(boolean hidden)
    {
        this.hidden = hidden;

        return this;
    }


    /**
     * Get the string representation of this property in the format of
     * <code>"<i>{key}</i>=<i>{value}</i>"</code>. If this property is hidden,
     * <code>" (hidden)"</code> is appended and so the resultant string
     * will become <code>"<i>{key}</i>=<i>{value}</i> (hidden)"</code>.
     */
    @Override
    public String toString()
    {
        return String.format("%s=%s%s", key, value, (hidden ? " (hidden)" : ""));
    }
}
