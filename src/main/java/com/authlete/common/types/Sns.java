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
package com.authlete.common.types;


import java.util.EnumSet;


public enum Sns
{
    /**
     * {@code "facebook"} (1).
     */
    FACEBOOK((short)1, "facebook"),
    ;


    private static final Sns[] mValues = values();
    private static final Helper mHelper = new Helper(mValues);
    private final short mValue;
    private final String mString;


    private Sns(short value, String string)
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
    public static Sns getByValue(short value)
    {
        if (value < 1 || mValues.length < value)
        {
            // Not found.
            return null;
        }

        return mValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code Sns}.
     *
     * @param sns
     *         An SNS. For example, {@code "facebook"}.
     *
     * @return
     *         {@code Sns} instance, or {@code null}.
     */
    public static Sns parse(String sns)
    {
        if (sns == null)
        {
            return null;
        }

        for (Sns entry : values())
        {
            if (entry.mString.equals(sns))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<Sns> set)
    {
        return mHelper.toBits(set);
    }


    public static Sns[] toArray(int bits)
    {
        return mHelper.toArray(bits);
    }


    public static EnumSet<Sns> toSet(int bits)
    {
        return mHelper.toSet(bits);
    }


    public static EnumSet<Sns> toSet(Sns[] array)
    {
        return mHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<Sns>
    {
        public Helper(Sns[] values)
        {
            super(Sns.class, values);
        }


        @Override
        protected short getValue(Sns entry)
        {
            return entry.getValue();
        }


        @Override
        protected Sns[] newArray(int size)
        {
            return new Sns[size];
        }
    }
}
