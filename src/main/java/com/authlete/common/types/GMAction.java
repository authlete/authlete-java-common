/*
 * Copyright (C) 2021 Authlete, Inc.
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
 * Grant Management Action.
 *
 * @see <a href="https://openid.net/specs/fapi-grant-management.html"
 *      >Grant Management for OAuth 2.0</a>
 *
 * @since 3.1
 */
public enum GMAction
{
    /**
     * {@code "create"} (1).
     */
    CREATE((short)1, "create"),


    /**
     * {@code "query"} (2).
     */
    QUERY((short)2, "query"),


    /**
     * {@code "replace"} (3).
     */
    REPLACE((short)3, "replace"),


    /**
     * {@code "revoke"} (4).
     */
    REVOKE((short)4, "revoke"),


    /**
     * {@code "update"} (5).
     */
    UPDATE((short)5, "update"),
    ;


    private static final GMAction[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private GMAction(short value, String string)
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
    public static GMAction getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code GMAction}.
     *
     * @param action
     *         A grant management action. For example, {@code "query"}.
     *
     * @return
     *         {@code GMAction} instance, or {@code null}.
     */
    public static GMAction parse(String action)
    {
        if (action == null)
        {
            return null;
        }

        for (GMAction entry : sValues)
        {
            if (entry.mString.equals(action))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<GMAction> set)
    {
        return sHelper.toBits(set);
    }


    public static GMAction[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<GMAction> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<GMAction> toSet(GMAction[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<GMAction>
    {
        public Helper(GMAction[] values)
        {
            super(GMAction.class, values);
        }


        @Override
        protected short getValue(GMAction entry)
        {
            return entry.getValue();
        }


        @Override
        protected GMAction[] newArray(int size)
        {
            return new GMAction[size];
        }
    }
}
