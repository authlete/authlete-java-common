/*
 * Copyright (C) 2014 Authlete, Inc.
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
 * Values for {@code grant_type}.
 *
 * @author Takahiko Kawasaki
 */
public enum GrantType
{
    /**
     * {@code "authorization_code"} (1), a {@code grant_type} to request
     * an access token and/or an ID token, and optionally a refresh token,
     * using an authorization code.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.1.3"
     *      >RFC 6749 (OAuth 2.0), 4.1.3. Access Token Request</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#TokenEndpoint"
     *      >OpenID Connect Core 1.0, 3.1.3. Token Endpoint</a>
     */
    AUTHORIZATION_CODE((short)1, "authorization_code"),


    /**
     * {@code "implicit"} (2), representing Implicit Flow.
     *
     * <p>
     * This is not a value for {@code grant_type} but listed in this enum
     * because OpenID Connect Dynamic Client Registration 1.0 uses {@code
     * "implicit"} as a value for {@code grant_types} of client metadata.
     * </p>
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
     */
    IMPLICIT((short)2, "implicit"),


    /**
     * {@code "password"} (3), a {@code grant_type} to request an access token
     * using a resource owner's username and password.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.3.2"
     *      >RFC 6749 (OAuth 2.0), 4.3.2. Access Token Request</a>
     */
    PASSWORD((short)3, "password"),


    /**
     * {@code "client_credentials"} (4), a {@code grant_type} to request
     * an access token using a client's credentials.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.4.2"
     *      >RFC 6749 (OAuth 2.0), 4.4.2. Access Token Request</a>
     */
    CLIENT_CREDENTIALS((short)4, "client_credentials"),


    /**
     * {@code "refresh_token"} (5), a {@code grant_type} to request an access
     * token, and optionally an ID token and/or a refresh token, using a
     * refresh token.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-6"
     *      >RFC 6749 (OAuth 2.0), 6. Refreshing an Access Token</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#RefreshTokens"
     *      >OpenID Connect Core 1.0, 12. Using Refresh Tokens</a>
     */
    REFRESH_TOKEN((short)5, "refresh_token")
    ;


    private static final GrantType[] mValues = values();
    private static final Helper mHelper = new Helper(mValues);
    private final short mValue;
    private final String mString;


    private GrantType(short value, String string)
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
    public static GrantType getByValue(short value)
    {
        if (value < 1 || mValues.length < value)
        {
            // Not found.
            return null;
        }

        return mValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code GrantType}.
     *
     * @param grantType
     *         A grant type. For example, {@code "authorization_code"}.
     *
     * @return
     *         {@code GrantType} instance, or {@code null}.
     */
    public static GrantType parse(String grantType)
    {
        if (grantType == null)
        {
            return null;
        }

        for (GrantType entry : values())
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


    public static int toBits(EnumSet<GrantType> set)
    {
        return mHelper.toBits(set);
    }


    public static GrantType[] toArray(int bits)
    {
        return mHelper.toArray(bits);
    }


    public static EnumSet<GrantType> toSet(int bits)
    {
        return mHelper.toSet(bits);
    }


    public static EnumSet<GrantType> toSet(GrantType[] array)
    {
        return mHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<GrantType>
    {
        public Helper(GrantType[] values)
        {
            super(GrantType.class, values);
        }


        @Override
        protected short getValue(GrantType entry)
        {
            return entry.getValue();
        }


        @Override
        protected GrantType[] newArray(int size)
        {
            return new GrantType[size];
        }
    }
}
