/*
 * Copyright (C) 2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.types;


import java.util.EnumSet;


/**
 * Client Assertion Type.
 *
 * @see <a href="https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml#uri"
 *      >IANA: OAuth Parameters / OAuth URI</a>
 *
 * @since 3.74
 * @since Authlete 3.0
 */
public enum ClientAssertionType
{
    /**
     * {@code "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"}.
     *
     * <p>
     * The client assertion type used by the {@code client_secret_jwt} client
     * authentication method and the {@code private_key_jwt} client
     * authentication method.
     * </p>
     */
    JWT_BEARER((short)1, "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"),


    /**
     * {@code "urn:ietf:params:oauth:client-assertion-type:jwt-client-attestation"}.
     *
     * <p>
     * The client assertion type used by the {@code attest_jwt_client_auth}
     * client authentication method.
     * </p>
     */
    JWT_CLIENT_ATTESTATION((short)2, "urn:ietf:params:oauth:client-assertion-type:jwt-client-attestation"),
    ;


    private static final ClientAssertionType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);

    private final short mValue;
    private final String mString;


    private ClientAssertionType(short value, String string)
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
    public static ClientAssertionType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code ClientAssertionType}.
     *
     * @param type
     *         A client assertion type. For example,
     *         {@code "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"}.
     *
     * @return
     *         {@code ClientAssertionType} instance, or {@code null}.
     */
    public static ClientAssertionType parse(String type)
    {
        if (type == null)
        {
            return null;
        }

        for (ClientAssertionType entry : sValues)
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


    public static int toBits(EnumSet<ClientAssertionType> set)
    {
        return sHelper.toBits(set);
    }


    public static ClientAssertionType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ClientAssertionType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ClientAssertionType> toSet(ClientAssertionType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ClientAssertionType>
    {
        public Helper(ClientAssertionType[] values)
        {
            super(ClientAssertionType.class, values);
        }


        @Override
        protected short getValue(ClientAssertionType entry)
        {
            return entry.getValue();
        }


        @Override
        protected ClientAssertionType[] newArray(int size)
        {
            return new ClientAssertionType[size];
        }
    }
}
