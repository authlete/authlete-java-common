/*
 * Copyright (C) 2019-2021 Authlete, Inc.
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
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * A class that represents an element in {@code authorization_details}
 * which is defined in <i>"OAuth 2&#x002E;0 Rich Authorization Requests"</i>.
 *
 * @since 2.56
 */
public class AuthzDetailsElement implements Serializable
{
    private static final long serialVersionUID = 4L;


    private String type;
    private String[] locations;
    private String[] actions;
    private String[] dataTypes;
    private String identifier;
    private String[] privileges;
    private String otherFields;


    /**
     * Get the type of this element.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"The type of authorization data as a string. This field MAY define which
     * other elements are allowed in the request. This element is REQUIRED."</i>
     * </p>
     * </blockquote>
     *
     * @return
     *         The type of this element.
     */
    public String getType()
    {
        return type;
    }


    /**
     * Set the type of this element.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"The type of authorization data as a string. This field MAY define which
     * other elements are allowed in the request. This element is REQUIRED."</i>
     * </p>
     * </blockquote>
     *
     * @param type
     *         The type of this element.
     *
     * @return
     *         {@code this} object.
     */
    public AuthzDetailsElement setType(String type)
    {
        this.type = type;

        return this;
    }


    /**
     * Get the resources and/or resource servers.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the location of the resource or
     * resource server. These strings are typically URIs identifying the
     * location of the RS."</i>
     * </p>
     * </blockquote>
     *
     * @return
     *         The resources and/or resource servers.
     */
    public String[] getLocations()
    {
        return locations;
    }


    /**
     * Set the resources and/or resource servers.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the location of the resource or
     * resource server. These strings are typically URIs identifying the
     * location of the RS."</i>
     * </p>
     * </blockquote>
     *
     * @param locations
     *         The resources and/or resource servers.
     *
     * @return
     *         {@code this} object.
     */
    public AuthzDetailsElement setLocations(String[] locations)
    {
        this.locations = locations;

        return this;
    }


    /**
     * Get the actions.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the kinds of actions to be taken
     * at the resource."</i>
     * </p>
     * </blockquote>
     *
     * @return
     *         The actions.
     */
    public String[] getActions()
    {
        return actions;
    }


    /**
     * Set the actions.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the kinds of actions to be taken
     * at the resource."</i>
     * </p>
     * </blockquote>
     *
     * @param actions
     *         The actions.
     *
     * @return
     *         {@code this} object.
     */
    public AuthzDetailsElement setActions(String[] actions)
    {
        this.actions = actions;

        return this;
    }


    /**
     * Get the data types.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the kinds of data being requested
     * from the resource."</i>
     * </p>
     * </blockquote>
     *
     * @return
     *         The data types.
     *
     * @since 2.85
     */
    public String[] getDataTypes()
    {
        return dataTypes;
    }


    /**
     * Set the data types.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the kinds of data being requested
     * from the resource."</i>
     * </p>
     * </blockquote>
     *
     * @param dataTypes
     *         The data types.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.85
     */
    public AuthzDetailsElement setDataTypes(String[] dataTypes)
    {
        this.dataTypes = dataTypes;

        return this;
    }


    /**
     * Get the identifier of a specific resource.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"A string identifier indicating a specific resource available at the API."</i>
     * </p>
     * </blockquote>
     *
     * @return
     *         The identifier of a specific resource.
     */
    public String getIdentifier()
    {
        return identifier;
    }


    /**
     * Set the identifier of a specific resource.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"A string identifier indicating a specific resource available at the API."</i>
     * </p>
     * </blockquote>
     *
     * @param identifier
     *         The identifier of a specific resource.
     *
     * @return
     *         {@code this} object.
     */
    public AuthzDetailsElement setIdentifier(String identifier)
    {
        this.identifier = identifier;

        return this;
    }


    /**
     * Get the types or levels of privilege.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the types or levels of privilege
     * being requested at the resource."</i>
     * </p>
     * </blockquote>
     *
     * @return
     *         Types or levels of privilege.
     *
     * @since 2.91
     */
    public String[] getPrivileges()
    {
        return privileges;
    }


    /**
     * Set the types or levels of privilege.
     *
     * <p>
     * From <i>"OAuth 2.0 Rich Authorization Requests"</i>:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>"An array of strings representing the types or levels of privilege
     * being requested at the resource."</i>
     * </p>
     * </blockquote>
     *
     * @param privileges
     *         Types or levels of privilege.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.91
     */
    public AuthzDetailsElement setPrivileges(String[] privileges)
    {
        this.privileges = privileges;

        return this;
    }


    /**
     * Get the other fields (than the pre-defined ones such as {@code type}
     * and {@code locations}) as a string in the JSON format.
     *
     * <p>
     * The content varies depending on the {@code type} field.
     * </p>
     *
     * @return
     *         Other fields in the JSON format. This may be {@code null}.
     *
     * @see #getOtherFieldsAsMap()
     */
    public String getOtherFields()
    {
        return otherFields;
    }


    /**
     * Set the other fields (than the pre-defined ones such as {@code type}
     * and {@code locations}) as a string in the JSON format.
     *
     * <p>
     * The content varies depending on the {@code type} field.
     * </p>
     *
     * @param otherFields
     *         Other fields in the JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public AuthzDetailsElement setOtherFields(String otherFields)
    {
        this.otherFields = otherFields;

        return this;
    }


    /**
     * Get the other fields (than {@code type}, {@code locations},
     * {@code actions} and {@code identifier}) as a {@link Map} object.
     *
     * <p>
     * The content varies depending on the {@code type} field.
     * </p>
     *
     * @return
     *         Other fields as a {@link Map} object. This may be {@code null}.
     *
     * @see #getOtherFields()
     */
    public Map<?, ?> getOtherFieldsAsMap()
    {
        if (otherFields == null)
        {
            return null;
        }

        return new Gson().fromJson(otherFields, Map.class);
    }


    /**
     * Set the other fields (than {@code type}, {@code locations},
     * {@code actions} and {@code identifier}) using a {@link Map}
     * object. The internal value is stored as a string serialization
     * of the {@link Map} object.
     *
     * <p>
     * The content varies depending on the {@code type} field.
     * </p>
     *
     * @see #getOtherFields()
     */
    public AuthzDetailsElement setOtherFieldsFromMap(Map<?, ?> otherFields)
    {
        if (otherFields == null)
        {
            this.otherFields = null;
        }
        else
        {
            this.otherFields = new Gson().toJson(otherFields, Map.class);
        }

        return this;
    }


    /**
     * Convert this instance into a JSON string.
     *
     * <p>
     * "Other fields" (the string returned from {@link #getOtherFields()} are
     * expanded into the output JSON.
     * </p>
     *
     * @return
     *         A JSON string that represents this instance.
     *
     * @since 2.57
     */
    public String toJson()
    {
        return createSerializer().toJson(this);
    }


    /**
     * Build an {@code AuthzDetailsElement} instance from a JSON string.
     *
     * <p>
     * The following is an example of input JSON.
     * </p>
     *
     * <pre>
     * {
     *   "type"      : "my_type",
     *   "locations" : ["loc0", "loc1"],
     *   "actions"   : ["act0", "act1"],
     *   "identifier": "my_id",
     *   "prop0"     : "a",
     *   "prop1"     : ["b", "c"],
     *   "prop2"     : {
     *     "sub0": "d",
     *     "sub1": ["e", "f"]
     *   }
     * }
     * </pre>
     *
     * @param json
     *         A JSON string.
     *
     * @return
     *         An {@code AuthzDetailsElement} instance built from the input JSON.
     *
     * @since 2.57
     */
    public static AuthzDetailsElement fromJson(String json)
    {
        if (json == null)
        {
            return null;
        }

        return createDeserializer().fromJson(json, AuthzDetailsElement.class);
    }


    /**
     * Create a serializer for {@link AuthzDetailsElement}.
     */
    private static Gson createSerializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                AuthzDetailsElement.class, new AuthzDetailsElementSerializer())
            .create();
    }


    /**
     * Create a deserializer for {@link AuthzDetailsElement}.
     */
    private static Gson createDeserializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                AuthzDetailsElement.class, new AuthzDetailsElementDeserializer())
            .create();
    }
}
