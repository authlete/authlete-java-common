/*
 * Copyright (C) 2014-2019 Authlete, Inc.
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
 * Values for {@code subject_type}.
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#SubjectIDTypes"
 *      >OpenID Connect Core 1.0, 8. Subject Identifier Types</a>
 *
 * @author Takahiko Kawasaki
 */
public enum SubjectType
{
    /**
     * {@code "public"} (1).
     *
     * <p>
     * This provides the same {@code sub} (subject) value to all Clients.
     * It is the default if the provider has no {@code subject_types_supported}
     * element in its discovery document.
     * </p>
     */
    PUBLIC((short)1, "public"),


    /**
     * {@code "pairwise"} (2).
     *
     * <p>
     * This provides a different {@code sub} value to each sector identifier, so
     * as not to enable Clients to correlate the End-User's activities without
     * permission. See <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#PairwiseAlg">8.1.
     * Pairwise Identifier Algorithm</a> for details.
     * </p>
     */
    PAIRWISE((short)2, "pairwise"),
    ;


    private static final SubjectType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private SubjectType(short value, String string)
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
    public static SubjectType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code SubjectType}.
     *
     * @param subjectType
     *         A subject type. For example, {@code "pairwise"}.
     *
     * @return
     *         {@code SubjectType} instance, or {@code null}.
     */
    public static SubjectType parse(String subjectType)
    {
        if (subjectType == null)
        {
            return null;
        }

        for (SubjectType value : sValues)
        {
            if (value.mString.equals(subjectType))
            {
                // Found.
                return value;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<SubjectType> set)
    {
        return sHelper.toBits(set);
    }


    public static SubjectType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<SubjectType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<SubjectType> toSet(SubjectType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<SubjectType>
    {
        public Helper(SubjectType[] values)
        {
            super(SubjectType.class, values);
        }


        @Override
        protected short getValue(SubjectType entry)
        {
            return entry.getValue();
        }


        @Override
        protected SubjectType[] newArray(int size)
        {
            return new SubjectType[size];
        }
    }
}
