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
 * Client types of OAuth 2.0 client applications.
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-2.1"
 *      >RFC 6749 (OAuth 2.0), 2.1. Client Types</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ClientType
{
    /**
     * {@code "public"} (1).
     *
     * <p>
     * Clients incapable of maintaining the confidentiality of their
     * credentials (e.g., clients executing on the device used by the
     * resource owner, such as an installed native application or a web
     * browser-based application), and incapable of secure client
     * authentication via any other means.
     * </p>
     */
    PUBLIC((short)1, "public"),


    /**
     * {@code "confidential"} (2).
     *
     * <p>
     * Clients capable of maintaining the confidentiality of their
     * credentials (e.g., client implemented on a secure server with
     * restricted access to the client credentials), or capable of secure
     * client authentication using other means.
     * </p>
     */
    CONFIDENTIAL((short)2, "confidential"),
    ;


    private static final ClientType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private ClientType(short value, String string)
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
    public static ClientType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code ClientType}.
     *
     * @param clientType
     *         A client type. For example, {@code "public"}.
     *
     * @return
     *         {@code ClientType} instance, or {@code null}.
     */
    public static ClientType parse(String clientType)
    {
        if (clientType == null)
        {
            return null;
        }

        for (ClientType entry : sValues)
        {
            if (entry.mString.equals(clientType))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<ClientType> set)
    {
        return sHelper.toBits(set);
    }


    public static ClientType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ClientType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ClientType> toSet(ClientType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ClientType>
    {
        public Helper(ClientType[] values)
        {
            super(ClientType.class, values);
        }


        @Override
        protected short getValue(ClientType entry)
        {
            return entry.getValue();
        }


        @Override
        protected ClientType[] newArray(int size)
        {
            return new ClientType[size];
        }
    }
}
