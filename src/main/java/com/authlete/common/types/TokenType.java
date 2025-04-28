/*
 * Copyright (C) 2022-2025 Authlete, Inc.
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
 * Token types registered at <a href=
 * "https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml#uri"
 * >OAuth URI</a> of <a href=
 * "https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml"
 * >OAuth Parameters</a> of IANA (Internet Assigned Numbers Authority).
 *
 * @since 3.26
 * @since Authlete 2.3
 *
 * @see <a href="https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml#uri"
 *      >IANA / OAuth Parameters / OAuth URI</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7519.html#section-9"
 *      >RFC 7519 JSON Web Token (JWT), Section 9. URI for Declaring that Content is a JWT</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html#section-3"
 *      >RFC 8693 OAuth 2.0 Token Exchange, Section 3. Token Type Identifiers</a>
 *
 * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
 *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
 */
public enum TokenType
{
    /**
     * JSON Web Token (JWT) Token Type;
     * {@code "urn:ietf:params:oauth:token-type:jwt"}.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7519.html#section-9"
     *      >RFC 7519 JSON Web Token (JWT), Section 9. URI for Declaring that Content is a JWT</a>
     */
    JWT((short)1, "urn:ietf:params:oauth:token-type:jwt"),


    /**
     * Token type URI for an OAuth 2&#x002E;0 access token;
     * {@code "urn:ietf:params:oauth:token-type:access_token"}.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html#section-3"
     *      >RFC 8693 OAuth 2.0 Token Exchange, Section 3. Token Type Identifiers</a>
     */
    ACCESS_TOKEN((short)2, "urn:ietf:params:oauth:token-type:access_token"),


    /**
     * Token type URI for an OAuth 2&#x002E;0 refresh token;
     * {@code "urn:ietf:params:oauth:token-type:refresh_token"}.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html#section-3"
     *      >RFC 8693 OAuth 2.0 Token Exchange, Section 3. Token Type Identifiers</a>
     */
    REFRESH_TOKEN((short)3, "urn:ietf:params:oauth:token-type:refresh_token"),


    /**
     * Token type URI for an ID Token;
     * {@code "urn:ietf:params:oauth:token-type:id_token"}.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html#section-3"
     *      >RFC 8693 OAuth 2.0 Token Exchange, Section 3. Token Type Identifiers</a>
     */
    ID_TOKEN((short)4, "urn:ietf:params:oauth:token-type:id_token"),


    /**
     * Token type URI for a base64url-encoded SAML 1&#x002E;1 assertion;
     * {@code "urn:ietf:params:oauth:token-type:saml1"}.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html#section-3"
     *      >RFC 8693 OAuth 2.0 Token Exchange, Section 3. Token Type Identifiers</a>
     */
    SAML1((short)5, "urn:ietf:params:oauth:token-type:saml1"),


    /**
     * Token type URI for a base64url-encoded SAML 2&#x002E;0 assertion;
     * {@code "urn:ietf:params:oauth:token-type:saml2"}.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html#section-3"
     *      >RFC 8693 OAuth 2.0 Token Exchange, Section 3. Token Type Identifiers</a>
     */
    SAML2((short)6, "urn:ietf:params:oauth:token-type:saml2"),


    /**
     * Token type URI for a device secret;
     * {@code "urn:openid:params:token-type:device-secret"}.
     *
     * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
     *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
     *
     * @since 4.18
     * @since Authlete 3.0
     */
    DEVICE_SECRET((short)7, "urn:openid:params:token-type:device-secret"),
    ;


    private static final TokenType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private TokenType(short value, String string)
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
    public static TokenType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code TokenType}.
     *
     * @param tokenType
     *         A token type. For example,
     *         {@code "urn:ietf:params:oauth:token-type:access_token"}.
     *
     * @return
     *         {@code TokenType} instance, or {@code null}.
     */
    public static TokenType parse(String tokenType)
    {
        if (tokenType == null)
        {
            return null;
        }

        for (TokenType entry : sValues)
        {
            if (entry.mString.equals(tokenType))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<TokenType> set)
    {
        return sHelper.toBits(set);
    }


    public static TokenType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<TokenType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<TokenType> toSet(TokenType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<TokenType>
    {
        public Helper(TokenType[] values)
        {
            super(TokenType.class, values);
        }


        @Override
        protected short getValue(TokenType entry)
        {
            return entry.getValue();
        }


        @Override
        protected TokenType[] newArray(int size)
        {
            return new TokenType[size];
        }
    }
}
