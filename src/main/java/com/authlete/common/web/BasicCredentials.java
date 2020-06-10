/*
 * Copyright (C) 2014-2017 Authlete, Inc.
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;


/**
 * Credentials in Basic authentication.
 *
 * @see <a href="http://tools.ietf.org/html/rfc2617#section-2"
 *      >RFC 2617 (HTTP Authentication), 2. Basic Authentication Scheme</a>
 *
 * @author Takahiko Kawasaki
 */
public class BasicCredentials
{
    /**
     * Regular expression to parse {@code Authorization} header.
     */
    private static final Pattern CHALLENGE_PATTERN
        = Pattern.compile("^Basic *([^ ]+) *$", Pattern.CASE_INSENSITIVE);


    /**
     * User ID.
     */
    private final String mUserId;


    /**
     * Password.
     */
    private final String mPassword;


    /**
     * "Basic {base64-encoded ID:Password}"
     */
    private transient String mFormatted;


    /**
     * Constructor with credentials.
     *
     * @param userId
     *         The user ID.
     *
     * @param password
     *         The password.
     */
    public BasicCredentials(String userId, String password)
    {
        this.mUserId   = userId;
        this.mPassword = password;
    }


    /**
     * Get the user ID.
     *
     * @return
     *         The user ID.
     */
    public String getUserId()
    {
        return mUserId;
    }


    /**
     * Get the password.
     *
     * @return
     *         The password.
     */
    public String getPassword()
    {
        return mPassword;
    }


    /**
     * Parse {@code Authorization} header for Basic authentication.
     *
     * @param input
     *         The value of {@code Authorization} header. Expected inputs
     *         are either <code>"Basic <i>{Base64-Encoded-UserID-and-Password}</i>"</code>,
     *         or <code>"<i>{Base64-Encoded-UserID-and-Password}</i>"</code>.
     *
     * @return
     *         Parsed credentials. If {@code input} is {@code null} is returned.
     */
    public static BasicCredentials parse(String input)
    {
        if (input == null)
        {
            return null;
        }

        Matcher matcher = CHALLENGE_PATTERN.matcher(input);

        if (matcher.matches() == false)
        {
            return new BasicCredentials(null, null);
        }

        // "userid:password" encoded in Base64.
        String encoded = matcher.group(1);

        // Decode the base64 string.
        byte[] decoded = Base64.decodeBase64(encoded);

        // Convert the byte array to String.
        String value = createString(decoded);

        // Split "userid:password" into "userid" and "password".
        String[] credentials = value.split(":", 2);

        // User ID and Password.
        String userId   = null;
        String password = null;

        switch (credentials.length)
        {
            case 2:
                // Password
                password = credentials[1];
                // FALLTHROUGH

            case 1:
                // User ID
                userId = credentials[0];
        }

        return new BasicCredentials(userId, password);
    }


    private static String createString(byte[] bytes)
    {
        try
        {
            return new String(bytes, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
    }


    /**
     * Create a value suitable as the value of {@code Authorization} header.
     *
     * @return
     *         {@code Authorization} header value for Basic authentication.
     */
    public String format()
    {
        if (mFormatted != null)
        {
            return mFormatted;
        }

        // userid:password
        String credentials = String.format("%s:%s",
                mUserId   == null ? "" : mUserId,
                mPassword == null ? "" : mPassword);

        // Convert the credentials into a byte array.
        byte[] credentialsBytes = getBytes(credentials);

        // Encode the byte array by Base64.
        String encoded = Base64.encodeBase64String(credentialsBytes);

        // Build the value of Authorization header.
        mFormatted = "Basic " + encoded;

        return mFormatted;
    }


    private static byte[] getBytes(String string)
    {
        try
        {
            return string.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // This never happens.
            return string.getBytes();
        }
    }
}
