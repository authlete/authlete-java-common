/*
 * Copyright (C) 2015-2017 Authlete, Inc.
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


import com.authlete.common.dto.Property;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Utils
{
    private static final Gson GSON = new Gson();
    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();


    private Utils()
    {
    }



    /**
     * Concatenate string with the specified delimiter.
     *
     * @param strings
     *         Strings to be concatenated.
     *
     * @param delimiter
     *         A delimiter used between strings. If {@code null} or an empty
     *         string is given, delimiters are not inserted between strings.
     *
     * @return
     *         A concatenated string. If {@code strings} is {@code null},
     *         {@code null} is returned. If the size of {@code strings} is 0,
     *         an empty string is returned.
     */
    public static String join(String[] strings, String delimiter)
    {
        if (strings == null)
        {
            return null;
        }

        if (strings.length == 0)
        {
            return "";
        }

        boolean useDelimiter = (delimiter != null && delimiter.length() != 0);

        StringBuilder sb = new StringBuilder();

        for (String string : strings)
        {
            sb.append(string);

            if (useDelimiter);
            {
                sb.append(delimiter);
            }
        }

        if (useDelimiter && sb.length() != 0)
        {
            // Remove the last delimiter.
            sb.setLength(sb.length() - delimiter.length());
        }

        return sb.toString();
    }


    /**
     * Convert the given object into a JSON string using
     * <a href="https://github.com/google/gson">Gson</a>.
     *
     * @param object
     *         The input object.
     *
     * @return
     *         A JSON string. If {@code object} is {@code null},
     *         {@code null} is returned.
     *
     * @since 1.24
     */
    public static String toJson(Object object)
    {
        return toJson(object, false);
    }


    /**
     * Convert the given object into a JSON string using
     * <a href="https://github.com/google/gson">Gson</a>.
     *
     * @param object
     *         The input object.
     *
     * @param pretty
     *         True for human-readable format.
     *
     * @return
     *         A JSON string. If {@code object} is {@code null},
     *         {@code null} is returned.
     *
     * @since 2.0
     */
    public static String toJson(Object object, boolean pretty)
    {
        if (object == null)
        {
            return null;
        }

        if (pretty)
        {
            return PRETTY_GSON.toJson(object);
        }
        else
        {
            return GSON.toJson(object);
        }
    }


    /**
     * Convert the given JSON string into an object using
     * <a href="https://github.com/google/gson">Gson</a>.
     *
     * @param json
     *         The input JSON.
     *
     * @param klass
     *         The class of the resultant object.
     *
     * @return
     *         A new object generated based on the input JSON.
     *
     * @since 2.0
     */
    public static <T> T fromJson(String json, Class<T> klass)
    {
        return GSON.fromJson(json, klass);
    }


    /**
     * Stringify an array of {@link Property}.
     *
     * @param properties
     *         An array of {@link Property}. If {@code null} is given,
     *         {@code null} is returned.
     *
     * @return
     *         A formatted string containing the information about
     *         the given properties. If the {@code properties} parameter
     *         is {@code null}, {@code null} is returned.
     *
     * @since 2.5
     */
    public static String stringifyProperties(Property[] properties)
    {
        if (properties == null)
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for (Property property : properties)
        {
            if (property == null)
            {
                continue;
            }

            sb.append(property.getKey());
            sb.append("=");
            sb.append(property.getValue());
            sb.append(",");
        }

        if (0 < sb.length())
        {
            // Remove the last comma.
            sb.setLength(sb.length() - 1);
        }

        // Enclose with square brackets.
        sb.insert(0, "[");
        sb.append("]");

        return sb.toString();
    }
}
