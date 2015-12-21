/*
 * Copyright (C) 2015 Authlete, Inc.
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


import com.google.gson.Gson;


public class Utils
{
    private static final Gson GSON = new Gson();


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
        if (object == null)
        {
            return null;
        }

        return GSON.toJson(object);
    }
}
