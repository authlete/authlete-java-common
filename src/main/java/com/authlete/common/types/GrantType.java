/*
 * Copyright (C) 2014-2023 Authlete, Inc.
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
import com.authlete.common.util.Version;


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
     * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.1.3"
     *      >RFC 6749 (OAuth 2.0), 4.1.3. Access Token Request</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#TokenEndpoint"
     *      >OpenID Connect Core 1.0, 3.1.3. Token Endpoint</a>
     */
    AUTHORIZATION_CODE((short)1, "authorization_code", new Version(1, 1)),


    /**
     * {@code "implicit"} (2), representing Implicit Flow.
     *
     * <p>
     * This is not a value for {@code grant_type} but listed in this enum
     * because OpenID Connect Dynamic Client Registration 1.0 uses {@code
     * "implicit"} as a value for {@code grant_types} of client metadata.
     * </p>
     *
     * @see <a href="https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
     */
    IMPLICIT((short)2, "implicit", new Version(1, 1)),


    /**
     * {@code "password"} (3), a {@code grant_type} to request an access token
     * using a resource owner's username and password.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.3.2"
     *      >RFC 6749 (OAuth 2.0), 4.3.2. Access Token Request</a>
     */
    PASSWORD((short)3, "password", new Version(1, 1)),


    /**
     * {@code "client_credentials"} (4), a {@code grant_type} to request
     * an access token using a client's credentials.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.4.2"
     *      >RFC 6749 (OAuth 2.0), 4.4.2. Access Token Request</a>
     */
    CLIENT_CREDENTIALS((short)4, "client_credentials", new Version(1, 1)),


    /**
     * {@code "refresh_token"} (5), a {@code grant_type} to request an access
     * token, and optionally an ID token and/or a refresh token, using a
     * refresh token.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-6"
     *      >RFC 6749 (OAuth 2.0), 6. Refreshing an Access Token</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#RefreshTokens"
     *      >OpenID Connect Core 1.0, 12. Using Refresh Tokens</a>
     */
    REFRESH_TOKEN((short)5, "refresh_token", new Version(1, 1)),


    /**
     * {@code "urn:openid:params:grant-type:ciba"} (6), a {@code grant_type} to
     * request an ID token, an access token, and optionally a refresh token,
     * using a CIBA flow.
     *
     * <p>
     * CIBA is short for Client Initiated Backchannel Authentication.
     * </p>
     *
     * @since 2.34
     */
    CIBA((short)6, "urn:openid:params:grant-type:ciba", new Version(2, 1)),


    /**
     * {@code "urn:ietf:params:oauth:grant-type:device_code"} (7), a
     * {@code grant_type} to request an access token and optionally a
     * refresh token, using Device Flow.
     *
     * @since 2.42
     */
    DEVICE_CODE((short)7, "urn:ietf:params:oauth:grant-type:device_code", new Version(2, 1)),


    /**
     * {@code "urn:ietf:params:oauth:grant-type:token-exchange"} (8), a
     * {@code grant_type} for token exchange.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    TOKEN_EXCHANGE((short)8, "urn:ietf:params:oauth:grant-type:token-exchange", new Version(2, 3)),


    /**
     * {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"} (9), a
     * {@code grant_type} using a JWT as an authorization grant.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    JWT_BEARER((short)9, "urn:ietf:params:oauth:grant-type:jwt-bearer", new Version(2, 3)),


    /**
     * {@code "urn:ietf:params:oauth:grant-type:pre-authorized_code"} (10), a
     * {@code grant_type} to request an access token and optionally a
     * refresh token, using Pre-Authorized Code Flow.
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html#name-pre-authorized-code-flow"
     *      >OpenID for Verifiable Credential Issuance, 3.5. Pre-Authorized Code Flow</a>
     *
     * @since 3.53
     * @since Authlete 3.0
     */
    PRE_AUTHORIZED_CODE((short)10, "urn:ietf:params:oauth:grant-type:pre-authorized_code", new Version(3, 0)),
    ;


    private static final GrantType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;
    private final Version mVersion;


    private GrantType(short value, String string, Version version)
    {
        mValue   = value;
        mString  = string;
        mVersion = version;
    }


    /**
     * Get the integer representation of this enum instance.
     */
    public short getValue()
    {
        return mValue;
    }


    /**
     * Get the Authlete version since which this grant type has been supported.
     *
     * @return
     *         The Authlete version since which this grant type has been supported.
     *
     * @since 3.54
     */
    public Version getVersion()
    {
        return mVersion;
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
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
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

        for (GrantType entry : sValues)
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
        return sHelper.toBits(set);
    }


    public static GrantType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<GrantType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<GrantType> toSet(GrantType[] array)
    {
        return sHelper.toSet(array);
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
