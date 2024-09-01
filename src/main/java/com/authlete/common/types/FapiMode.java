/*
 * Copyright (C) 2023-2024 Authlete, Inc.
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
 * FAPI mode.
 *
 * @since 3.80
 */
public enum FapiMode
{
    /**
     * {@code "fapi1_baseline"} (1).
     *
     * <p>
     * The FAPI mode for "<a href="https://openid.net/specs/openid-financial-api-part-1-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>".
     * </p>
     */
    FAPI1_BASELINE((short)1, "fapi1_baseline"),


    /**
     * {@code "fapi1_advanced"} (2).
     *
     * <p>
     * The FAPI mode for "<a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>".
     * </p>
     */
    FAPI1_ADVANCED((short)2, "fapi1_advanced"),


    /**
     * {@code "fapi2_security"} (3).
     *
     * <p>
     * The FAPI mode for "<a href="https://openid.net/specs/fapi-2_0-security-02.html">
     * FAPI 2.0 Security Profile</a>".
     * </p>
     */
    FAPI2_SECURITY((short)3, "fapi2_security"),


    /**
     * {@code "fapi2_message_signing_auth_req"} (4).
     *
     * <p>
     * The FAPI mode for "<a href="https://openid.net/specs/fapi-2_0-message-signing.html#section-5.3">
     * 5.3. Signing Authorization Requests</a>" of "<a href="
     * https://openid.net/specs/fapi-2_0-message-signing.html">FAPI 2.0
     * Message Signing Profile</a>".
     * </p>
     */
    FAPI2_MESSAGE_SIGNING_AUTH_REQ((short)4, "fapi2_message_signing_auth_req"),


    /**
     * {@code "fapi2_message_signing_auth_res"} (5).
     *
     * <p>
     * The FAPI mode for "<a href="https://openid.net/specs/fapi-2_0-message-signing.html#section-5.4">
     * 5.4. Signing Authorization Responses</a>" of "<a href="
     * https://openid.net/specs/fapi-2_0-message-signing.html">FAPI 2.0
     * Message Signing Profile</a>".
     * </p>
     */
    FAPI2_MESSAGE_SIGNING_AUTH_RES((short)5, "fapi2_message_signing_auth_res"),


    /**
     * {@code "fapi2_message_signing_introspection_res"} (6).
     *
     * <p>
     * The FAPI mode for "<a href="https://openid.net/specs/fapi-2_0-message-signing.html#section-5.5">
     * 5.5. Signing Introspection Responses</a>" of "<a href="
     * https://openid.net/specs/fapi-2_0-message-signing.html">FAPI 2.0
     * Message Signing Profile</a>".
     * </p>
     */
    FAPI2_MESSAGE_SIGNING_INTROSPECTION_RES((short)6, "fapi2_message_signing_introspection_res"),


    /**
     * {@code "fapi2_message_signing_resource_req"} (7).
     *
     * <p>
     * The FAPI mode for "<a href=
     * "https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html#section-5.6.1"
     * >5.6.1. Requirements for signing and verifying resource requests</a>" of
     * "<a href="https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html"
     * >FAPI 2.0 Message Signing</a>".
     * </p>
     *
     * @since 4.10
     */
    FAPI2_MESSAGE_SIGNING_RESOURCE_REQ((short)7, "fapi2_message_signing_resource_req"),


    /**
     * {@code "fapi2_message_signing_resource_res"} (8).
     *
     * <p>
     * The FAPI mode for "<a href=
     * "https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html#section-5.6.2"
     * >5.6.2. Requirements for signing and verifying resource responses</a>" of
     * "<a href="https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html"
     * >FAPI 2.0 Message Signing</a>".
     * </p>
     *
     * @since 4.10
     */
    FAPI2_MESSAGE_SIGNING_RESOURCE_RES((short)8, "fapi2_message_signing_resource_res"),
    ;


    private static final FapiMode[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private FapiMode(short value, String string)
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
     * Convert the value to bits.
     */
    public int toBits()
    {
        return (1 << mValue);
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
    public static FapiMode getByValue(short value)
    {
        if (value < 0 || sValues.length <= value)
        {
            // Not found.
            return null;
        }

        return sValues[value];
    }


    /**
     * Convert {@code String} to {@code FapiMode}.
     *
     * @param fapiMode
     *         A FAPI mode. For example, {@code "fapi1_advanced"}.
     *
     * @return
     *         {@code FapiMode} instance, or {@code null}.
     */
    public static FapiMode parse(String fapiMode)
    {
        if (fapiMode == null)
        {
            return null;
        }

        for (FapiMode value : sValues)
        {
            if (value.mString.equals(fapiMode))
            {
                // Found.
                return value;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<FapiMode> set)
    {
        return sHelper.toBits(set);
    }


    public static FapiMode[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<FapiMode> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<FapiMode> toSet(FapiMode[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<FapiMode>
    {
        public Helper(FapiMode[] values)
        {
            super(FapiMode.class, values);
        }


        @Override
        protected short getValue(FapiMode entry)
        {
            return entry.getValue();
        }


        @Override
        protected FapiMode[] newArray(int size)
        {
            return new FapiMode[size];
        }
    }
}
