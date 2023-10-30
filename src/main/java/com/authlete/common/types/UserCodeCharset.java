/*
 * Copyright (C) 2019-2023 Authlete, Inc.
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


import java.util.EnumSet;


/**
 * Character set for end-user verification codes ({@code user_code})
 * in Device Flow.
 *
 * <p>
 * See "6.1. User Code Recommendations" in <a href=
 * "https://www.rfc-editor.org/rfc/rfc8628.html">RFC 8628 OAuth 2.0 Device
 * Authorization Grant</a> for recommendations for user code values.
 * </p>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8628.html"
 *      >RFC 8628 OAuth 2.0 Device Authorization Grant</a>
 *
 * @since 2.43
 */
public enum UserCodeCharset
{
    /**
     * "BCDFGHJKLMNPQRSTVWXZ", 20 upper-case non-vowel characters.
     */
    BASE20((short)1, "BCDFGHJKLMNPQRSTVWXZ"),


    /**
     * "0123456789", 10 digit characters from '0' to '9'.
     */
    NUMERIC((short)2, "0123456789"),
    ;


    private static final UserCodeCharset[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mCharacters;


    private UserCodeCharset(short value, String characters)
    {
        mValue      = value;
        mCharacters = characters;
    }


    /**
     * Get the integer representation of this enum instance.
     */
    public short getValue()
    {
        return mValue;
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
    public static UserCodeCharset getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Get the characters in this characters set.
     *
     * @return
     *         A string that contains characters in this character set.
     */
    public String getCharacters()
    {
        return mCharacters;
    }


    public static int toBits(EnumSet<UserCodeCharset> set)
    {
        return sHelper.toBits(set);
    }


    public static UserCodeCharset[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<UserCodeCharset> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<UserCodeCharset> toSet(UserCodeCharset[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<UserCodeCharset>
    {
        public Helper(UserCodeCharset[] values)
        {
            super(UserCodeCharset.class, values);
        }


        @Override
        protected short getValue(UserCodeCharset entry)
        {
            return entry.getValue();
        }


        @Override
        protected UserCodeCharset[] newArray(int size)
        {
            return new UserCodeCharset[size];
        }
    }
}
