/*
 * Copyright (C) 2022-2023 Authlete, Inc.
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
 * Values for the {@code client_registration_types} RP metadata and the
 * {@code client_registration_types_supported} OP metadata that are defined in
 * <a href="https://openid.net/specs/openid-federation-1_0.html"
 * >OpenID Federation 1&#x002E;0</a>.
 *
 * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
 *      >OpenID Federation 1.0</a>
 *
 * @since 3.22
 */
public enum ClientRegistrationType
{
    /**
     * {@code "automatic"} (1).
     */
    AUTOMATIC((short)1, "automatic"),


    /**
     * {@code "explicit"} (2).
     */
    EXPLICIT((short)2, "explicit")
    ;


    private static final ClientRegistrationType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private ClientRegistrationType(short value, String string)
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
    public static ClientRegistrationType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code ClientRegistrationType}.
     *
     * @param clientRegistrationType
     *         A client registration type. For example, {@code "automatic"}.
     *
     * @return
     *         A {@code ClientRegistrationType} instance, or {@code null}.
     */
    public static ClientRegistrationType parse(String clientRegistrationType)
    {
        if (clientRegistrationType == null)
        {
            return null;
        }

        for (ClientRegistrationType entry : sValues)
        {
            if (entry.mString.equals(clientRegistrationType))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<ClientRegistrationType> set)
    {
        return sHelper.toBits(set);
    }


    public static ClientRegistrationType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ClientRegistrationType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ClientRegistrationType> toSet(ClientRegistrationType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ClientRegistrationType>
    {
        public Helper(ClientRegistrationType[] values)
        {
            super(ClientRegistrationType.class, values);
        }


        @Override
        protected short getValue(ClientRegistrationType entry)
        {
            return entry.getValue();
        }


        @Override
        protected ClientRegistrationType[] newArray(int size)
        {
            return new ClientRegistrationType[size];
        }
    }
}
