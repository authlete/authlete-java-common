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
package com.authlete.common.types;


import java.util.EnumSet;


/**
 * Claim types.
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimTypes"
 *      >OpenID Connect Core 1.0, 5.6. Claim Types</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ClaimType
{
    /**
     * {@code "normal"} (1).
     *
     * <p>
     * Claims that are directly asserted by the OpenID Provider.
     * </p>
     */
    NORMAL((short)1, "normal"),


    /**
     * {@code "aggregated"} (2).
     *
     * <p>
     * Claims that are asserted by a Claims Provider other than
     * the OpenID Provider but are returned by OpenID Provider.
     * </p>
     */
    AGGREGATED((short)2, "aggregated"),


    /**
     * {@code "distributed"} (3).
     *
     * <p>
     * Claims that are asserted by a Claims Provider other than
     * the OpenID Provider but are returned as references by the
     * OpenID Provider.
     * </p>
     */
    DISTRIBUTED((short)3, "distributed")
    ;


    private static final ClaimType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private ClaimType(short value, String string)
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
    public static ClaimType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code ClaimType}.
     *
     * @param claimType
     *         A claim type. For example, {@code "normal"}.
     *
     * @return
     *         {@code ClaimType} instance, or {@code null}.
     */
    public static ClaimType parse(String claimType)
    {
        if (claimType == null)
        {
            return null;
        }

        for (ClaimType value : sValues)
        {
            if (value.mString.equals(claimType))
            {
                // Found.
                return value;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<ClaimType> set)
    {
        return sHelper.toBits(set);
    }


    public static ClaimType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ClaimType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ClaimType> toSet(ClaimType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ClaimType>
    {
        public Helper(ClaimType[] values)
        {
            super(ClaimType.class, values);
        }


        @Override
        protected short getValue(ClaimType entry)
        {
            return entry.getValue();
        }


        @Override
        protected ClaimType[] newArray(int size)
        {
            return new ClaimType[size];
        }
    }
}
