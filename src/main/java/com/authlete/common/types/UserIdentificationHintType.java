/*
 * Copyright (C) 2018 Authlete, Inc.
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
 * Types of hints for end-user identification.
 *
 * @since 2.32
 */
public enum UserIdentificationHintType
{
    /**
     * {@code "id_token_hint"};
     * An ID token previously issued to the client.
     */
    ID_TOKEN_HINT((short)1, "id_token_hint"),


    /**
     * {@code "login_hint"};
     * An arbitrary string whose interpretation varies depending on contexts.
     */
    LOGIN_HINT((short)2, "login_hint"),


    /**
     * {@code "login_hint_token"};
     * A token whose format is deployment or profile specific.
     */
    LOGIN_HINT_TOKEN((short)3, "login_hint_token"),
    ;


    private static final UserIdentificationHintType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private UserIdentificationHintType(short value, String string)
    {
        mValue  = value;
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
    public static UserIdentificationHintType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code HintType}.
     *
     * @param hintType
     *         A hint type. For example, {@code "id_token_hint"}.
     *
     * @return
     *         {@code HintType} instance, or {@code null}.
     */
    public static UserIdentificationHintType parse(String hintType)
    {
        if (hintType == null)
        {
            return null;
        }

        for (UserIdentificationHintType entry : sValues)
        {
            if (entry.mString.equals(hintType))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<UserIdentificationHintType> set)
    {
        return sHelper.toBits(set);
    }


    public static UserIdentificationHintType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<UserIdentificationHintType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<UserIdentificationHintType> toSet(UserIdentificationHintType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<UserIdentificationHintType>
    {
        public Helper(UserIdentificationHintType[] values)
        {
            super(UserIdentificationHintType.class, values);
        }


        @Override
        protected short getValue(UserIdentificationHintType entry)
        {
            return entry.getValue();
        }


        @Override
        protected UserIdentificationHintType[] newArray(int size)
        {
            return new UserIdentificationHintType[size];
        }
    }
}
