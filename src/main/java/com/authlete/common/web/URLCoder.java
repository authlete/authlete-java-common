/*
 * Copyright (C) 2014-2015 Authlete, Inc.
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
package com.authlete.common.web;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;


/**
 * URL encoder/decoder.
 *
 * @author Takahiko Kawasaki
 */
public class URLCoder
{
    /**
     * URL-encode the input with UTF-8.
     *
     * @param input
     *         A string to be encoded.
     *
     * @return
     *         Encoded string.
     */
    public static String encode(String input)
    {
        try
        {
            return URLEncoder.encode(input, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // This never happens.
            return input;
        }
    }


    /**
     * URL-decode the input with UTF-8.
     *
     * @param input
     *         An encoded string.
     *
     * @return
     *         Decoded string.
     */
    public static String decode(String input)
    {
        try
        {
            return URLDecoder.decode(input, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // This never happens.
            return input;
        }
    }


    /**
     * Convert the given map to a string in {@code x-www-form-urlencoded} format.
     *
     * @param parameters
     *         Pairs of key and values. The type of values must be either
     *         {@code String[]} or {@code List<String>}.
     *
     * @return
     *         A string in {@code x-www-form-urlencoded} format.
     *         {@code null} is returned if {@code parameters} is {@code null}.
     *
     * @since 1.24
     */
    public static String formUrlEncode(Map<String, ?> parameters)
    {
        if (parameters == null)
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        // For each key-values pair.
        for (Map.Entry<String, ?> entry : parameters.entrySet())
        {
            String key    = entry.getKey();
            Object values = entry.getValue();

            appendParameters(sb, key, (String[])values);
        }

        if (sb.length() != 0)
        {
            // Drop the last &.
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }


    private static void appendParameters(StringBuilder sb, String key, String[] values)
    {
        // If the key is invalid.
        if (key == null || key.length() == 0)
        {
            return;
        }

        // Encode the key.
        key = encode(key);

        if (values == null || values.length == 0)
        {
            // Just append "{key}&".
            sb.append(key).append("&");
            return;
        }
        sb.append(key);
        // For each value of the key.
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] != null)
            {
                if (values[i] != null && i == 0)
                {
                    sb.append("=");
                }
                sb.append(encode(values[i]));
                if (values.length > 1 && i!= values.length-1)
                {
                    sb.append(" ");
                }
            }
        }
        sb.append("&");
    }
}
