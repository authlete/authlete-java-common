/*
 * Copyright (C) 2022 Authlete, Inc.
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
 * Values for {@code attachments_supported}.
 *
 * @since 3.13
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 */
public enum AttachmentType
{
    /**
     * {@code "embedded"} (1).
     */
    EMBEDDED((short)1, "embedded"),


    /**
     * {@code "external"} (2).
     */
    EXTERNAL((short)2, "external")
    ;


    private static final AttachmentType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private AttachmentType(short value, String string)
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
    public static AttachmentType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code AttachmentType}.
     *
     * @param type
     *         An attachment type. For example, {@code "embedded"}.
     *
     * @return
     *         {@code AttachmentType} instance, or {@code null}.
     */
    public static AttachmentType parse(String type)
    {
        if (type == null)
        {
            return null;
        }

        for (AttachmentType entry : sValues)
        {
            if (entry.mString.equals(type))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<AttachmentType> set)
    {
        return sHelper.toBits(set);
    }


    public static AttachmentType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<AttachmentType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<AttachmentType> toSet(AttachmentType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<AttachmentType>
    {
        public Helper(AttachmentType[] values)
        {
            super(AttachmentType.class, values);
        }


        @Override
        protected short getValue(AttachmentType entry)
        {
            return entry.getValue();
        }


        @Override
        protected AttachmentType[] newArray(int size)
        {
            return new AttachmentType[size];
        }
    }
}
