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
 * Backchannel token delivery mode defined in the specification of
 * CIBA (Client Initiated Backchannel Authentication).
 *
 * @since 2.32
 */
public enum DeliveryMode
{
    /**
     * Poll mode, a backchannel token delivery mode where a client polls the
     * token endpoint until it gets tokens.
     */
    POLL((short)1, "poll"),


    /**
     * Ping mode, a backchannel token delivery mode where a client is notified
     * via its client notification endpoint and then gets tokens from the token
     * endpoint.
     */
    PING((short)2, "ping"),


    /**
     * Push mode, a backchannel token delivery mode where a client receives
     * tokens at its client notification endpoint.
     */
    PUSH((short)3, "push"),
    ;


    private static final DeliveryMode[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private DeliveryMode(short value, String string)
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
    public static DeliveryMode getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code DeliveryMode}.
     *
     * @param grantType
     *         A backchannel token delivery mode. For example, {@code "poll"}.
     *
     * @return
     *         {@code DeliveryMode} instance, or {@code null}.
     */
    public static DeliveryMode parse(String grantType)
    {
        if (grantType == null)
        {
            return null;
        }

        for (DeliveryMode entry : sValues)
        {
            if (entry.mString.equals(grantType))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<DeliveryMode> set)
    {
        return sHelper.toBits(set);
    }


    public static DeliveryMode[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<DeliveryMode> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<DeliveryMode> toSet(DeliveryMode[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<DeliveryMode>
    {
        public Helper(DeliveryMode[] values)
        {
            super(DeliveryMode.class, values);
        }


        @Override
        protected short getValue(DeliveryMode entry)
        {
            return entry.getValue();
        }


        @Override
        protected DeliveryMode[] newArray(int size)
        {
            return new DeliveryMode[size];
        }
    }
}
