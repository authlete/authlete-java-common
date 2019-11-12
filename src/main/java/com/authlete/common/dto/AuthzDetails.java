/*
 * Copyright (C) 2019 Authlete, Inc.
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * A class that represents {@code authorization_details} which is defined in
 * <i>"OAuth 2&#x002E;0 Rich Authorization Requests"</i>.
 *
 * @since 2.57
 */
public class AuthzDetails implements Serializable
{
    private static final long serialVersionUID = 1L;


    private AuthzDetailsElement[] elements;


    /**
     * Get the elements of this authorization details.
     *
     * @return
     *         Elements of this authorization details.
     */
    public AuthzDetailsElement[] getElements()
    {
        return elements;
    }


    /**
     * Set the elements of this authorization details.
     *
     * @param elements
     *         Elements of this authorization details.
     *
     * @return
     *         {@code this} object.
     */
    public AuthzDetails setElements(AuthzDetailsElement[] elements)
    {
        this.elements = elements;

        return this;
    }


    /**
     * Convert this instance into a JSON string.
     *
     * <p>
     * The returned string contains a JSON array that represents
     * the elements returned from {@link #getElements()}.
     * </p>
     *
     * <p>
     * When the value of the elements is {@code null}, this method
     * returns {@code "null"}.
     * </p>
     *
     * @return
     *         A JSON string.
     */
    public String toJson()
    {
        return createSerializer().toJson(this);
    }


    /**
     * Build an {@code AuthzDetails} instance from a JSON string.
     *
     * <p>
     * The following is an example of input JSON.
     * </p>
     *
     * <pre>
     * [
     *   {
     *     "type"      : "typ0",
     *     "locations" : ["loc0", "loc1"],
     *     "actions"   : ["act0", "act1"],
     *     "identifier": "my_id",
     *     "prop0"     : "a",
     *     "prop1"     : ["b", "c"],
     *     "prop2"     : {
     *       "sub0": "d",
     *       "sub1": ["e", "f"]
     *     }
     *   },
     *   {
     *     "type"      : "typ1"
     *   }
     * ]
     * </pre>
     *
     * @param json
     *         A JSON string.
     *
     * @return
     *         An {@code AuthzDetails} instance built from the input JSON.
     */
    public static AuthzDetails fromJson(String json)
    {
        if (json == null)
        {
            return null;
        }

        return createDeserializer().fromJson(json, AuthzDetails.class);
    }


    /**
     * Create a serializer for {@link AuthzDetails}.
     */
    private static Gson createSerializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                AuthzDetails.class, new AuthzDetailsSerializer())
            .registerTypeAdapter(
                AuthzDetailsElement.class, new AuthzDetailsElementSerializer())
            .create();
    }


    /**
     * Create a deserializer for {@link AuthzDetails}.
     */
    private static Gson createDeserializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                AuthzDetails.class, new AuthzDetailsDeserializer())
            .registerTypeAdapter(
                AuthzDetailsElement.class, new AuthzDetailsElementDeserializer())
            .create();
    }
}
