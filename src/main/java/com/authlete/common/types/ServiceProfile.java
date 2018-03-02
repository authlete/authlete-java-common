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
 * Service profile.
 *
 * @since 2.12
 */
public enum ServiceProfile
{
    /**
     * <a href="https://openid.net/wg/fapi/">Financial API</a>.
     *
     * {@code "fapi"} (1)
     */
    FAPI((short)1, "fapi"),
    ;


    private static final ServiceProfile[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private ServiceProfile(short value, String string)
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
    public static ServiceProfile getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code ServiceProfile}.
     *
     * @param serviceProfile
     *         Service profile. For example, {@code "fapi"}.
     *
     * @return
     *         {@code ServiceProfile} instance, or {@code null}.
     */
    public static ServiceProfile parse(String serviceProfile)
    {
        if (serviceProfile == null)
        {
            return null;
        }

        for (ServiceProfile entry : sValues)
        {
            if (entry.mString.equals(serviceProfile))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<ServiceProfile> set)
    {
        return sHelper.toBits(set);
    }


    public static ServiceProfile[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ServiceProfile> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ServiceProfile> toSet(ServiceProfile[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ServiceProfile>
    {
        public Helper(ServiceProfile[] values)
        {
            super(ServiceProfile.class, values);
        }


        @Override
        protected short getValue(ServiceProfile entry)
        {
            return entry.getValue();
        }


        @Override
        protected ServiceProfile[] newArray(int size)
        {
            return new ServiceProfile[size];
        }
    }
}
