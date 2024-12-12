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
package com.authlete.common.types;


/**
 * Values for {@code code_challenge_method}.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7636">RFC 7636 (Proof Key for Code Exchange by OAuth Public Clients)</a>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.21
 */
public enum CodeChallengeMethod
{
    /**
     * {@code plain}, meaning {@code code_challenge = code_verifier}.
     * See <a href="https://tools.ietf.org/html/rfc7636">RFC 7636</a> for details.
     */
    PLAIN((short)1, "plain"),


    /**
     * {@code S256}, meaning
     * {@code code_challenge = BASE64URL-ENCODE(SHA256(ASCII(code_verifier)))}.
     * See <a href="https://tools.ietf.org/html/rfc7636">RFC 7636</a> for details.
     */
    S256((short)2, "S256"),
    ;


    private static final CodeChallengeMethod[] sValues = values();
    private final short mValue;
    private final String mString;


    private CodeChallengeMethod(short value, String string)
    {
        mValue = value;
        mString = string;
    }


    /**
     * Get the integer representation of this enum instance.
     */
    public short getValue()
    {
        return mValue;
    }


    @Override
    public String toString()
    {
        return mString;
    }


    /**
     * Find an instance of this enum by a value.
     *
     * @param value
     *         The integer representation of the instance to find.
     *
     * @return
     *         An instance of this enum, or {@code null} if not found.
     */
    public static CodeChallengeMethod getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code CodeChallengeMethod}.
     *
     * @param method
     *         A value of {@code code_challenge_method} parameter.
     *         For example, {@code "plain"}.
     *
     * @return
     *         {@code CodeChallengeMethod} instance, or {@code null}.
     */
    public static CodeChallengeMethod parse(String method)
    {
        if (method == null)
        {
            return null;
        }

        for (CodeChallengeMethod value : sValues)
        {
            if (value.mString.equals(method))
            {
                // Found.
                return value;
            }
        }

        // Not found.
        return null;
    }
}
