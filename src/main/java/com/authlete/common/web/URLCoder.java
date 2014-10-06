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
package com.authlete.common.web;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


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
}
