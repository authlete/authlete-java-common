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
 * Values for {@code application_type}.
 *
 * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
 *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ApplicationType
{
    /**
     * {@code "web"} (1).
     */
    WEB((short)1, "web"),


    /**
     * {@code "native"} (2).
     */
    NATIVE((short)2, "native"),
    ;


    private static final ApplicationType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private ApplicationType(short value, String string)
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
    public static ApplicationType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code ApplicationType}.
     *
     * @param applicationType
     *         An application type. For example, {@code "web"}.
     *
     * @return
     *         {@code ApplicationType} instance, or {@code null}.
     */
    public static ApplicationType parse(String applicationType)
    {
        if (applicationType == null)
        {
            return null;
        }

        for (ApplicationType entry : sValues)
        {
            if (entry.mString.equals(applicationType))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<ApplicationType> set)
    {
        return sHelper.toBits(set);
    }


    public static ApplicationType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ApplicationType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ApplicationType> toSet(ApplicationType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ApplicationType>
    {
        public Helper(ApplicationType[] values)
        {
            super(ApplicationType.class, values);
        }


        @Override
        protected short getValue(ApplicationType entry)
        {
            return entry.getValue();
        }


        @Override
        protected ApplicationType[] newArray(int size)
        {
            return new ApplicationType[size];
        }
    }
}
