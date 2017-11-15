/*
 * Copyright (C) 2014-2017 Authlete, Inc.
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
 * Client authentication methods.
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClientAuthentication"
 *      >OpenID Connect Core 1.0, 9. Client Authentication</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ClientAuthMethod
{
    /**
     * {@code "none"} (0).
     *
     * <p>
     * The Client does not authenticate itself at the Token Endpoint,
     * either because it uses only the Implicit Flow (and so does not
     * use the Token Endpoint) or because it is a Public Client with
     * no Client Secret or other authentication mechanism.
     * </p>
     */
    NONE((short)0, "none"),


    /**
     * {@code "client_secret_basic"} (1).
     *
     * <p>
     * Clients that have received a {@code client_secret} value from
     * the Authorization Server authenticate with the Authorization
     * Server in accordance with
     * <a href="http://tools.ietf.org/html/rfc6749#section-3.2.1"
     * >Section 3.2.1</a> of OAuth 2.0
     * [<a href="http://tools.ietf.org/html/rfc6749">RFC6749</a>]
     * using the HTTP Basic authentication scheme.
     * </p>
     */
    CLIENT_SECRET_BASIC((short)1, "client_secret_basic"),


    /**
     * {@code "client_secret_post"} (2).
     *
     * <p>
     * Clients that have received a {@code client_secret} value from
     * the Authorization Server, authenticate with the Authorization
     * Server in accordance with
     * <a href="http://tools.ietf.org/html/rfc6749#section-3.2.1"
     * >Section 3.2.1</a> of OAuth 2.0
     * [<a href="http://tools.ietf.org/html/rfc6749">RFC6749</a>]
     * by including the Client Credentials in the request body.
     * </p>
     */
    CLIENT_SECRET_POST((short)2, "client_secret_post"),


    /**
     * {@code "client_secret_jwt"} (3).
     *
     * <p>
     * Clients that have received a {@code client_secret} value from
     * the Authorization Server create a JWT using an HMAC SHA
     * algorithm, such as HMAC SHA-256. The HMAC (Hash-based Message
     * Authentication Code) is calculated using the octets of the
     * UTF-8 representation of the {@code client_secret} as the
     * shared key.
     * </p>
     * <p>
     * The Client authenticates in accordance with
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#OAuth.JWT"
     * >JSON Web Token (JWT) Profile for OAuth 2.0 Client
     * Authentication and Authorization Grants</a> [OAuth.JWT] and
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#OAuth.Assertions"
     * >Assertion Framework for OAuth 2.0 Client Authentication and
     * Authorization Grants</a> [OAuth.Assertions].
     * </p>
     */
    CLIENT_SECRET_JWT((short)3, "client_secret_jwt"),


    /**
     * {@code "private_key_jwt"} (4).
     *
     * <p>
     * Clients that have registered a public key sign a JWT using
     * that key. The Client authenticates in accordance with
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#OAuth.JWT"
     * >JSON Web Token (JWT) Profile for OAuth 2.0 Client
     * Authentication and Authorization Grants</a> [OAuth.JWT] and
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#OAuth.Assertions"
     * >Assertion Framework for OAuth 2.0 Client Authentication and
     * Authorization Grants</a> [OAuth.Assertions].
     * </p>
     */
    PRIVATE_KEY_JWT((short)4, "private_key_jwt"),


    /**
     * {@code "tls_client_auth"} (5).
     *
     * <p>
     * Clients authenticate with the Authorization Server using
     * X.509 certificates as defined in <i>"Mutual TLS Profiles
     * for OAuth Clients"</i>.
     * </p>
     *
     * @since 2.7
     */
    TLS_CLIENT_AUTH((short)5, "tls_client_auth"),


    /**
     * {@code "self_signed_tls_client_auth"} (6).
     *
     * <p>
     * Clients authenticate with the Authorization Server using
     * self-signed certificates as defined in <i>"Mutual TLS Profiles
     * for OAuth Clients"</i>.
     * </p>
     *
     * @since 2.11
     */
    SELF_SIGNED_TLS_CLIENT_AUTH((short)6, "self_signed_tls_client_auth"),
    ;


    private static final ClientAuthMethod[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private ClientAuthMethod(short value, String string)
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
    public static ClientAuthMethod getByValue(short value)
    {
        if (value < 0 || sValues.length <= value)
        {
            // Not found.
            return null;
        }

        return sValues[value];
    }


    /**
     * Convert {@code String} to {@code ClientAuthMethod}.
     *
     * @param method
     *         Client authentication method. For example,
     *         {@code "client_secret_basic"}.
     *
     * @return
     *         {@code ClientAuthMethod} instance, or {@code null}.
     */
    public static ClientAuthMethod parse(String method)
    {
        if (method == null)
        {
            return null;
        }

        for (ClientAuthMethod entry : values())
        {
            if (entry.mString.equals(method))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<ClientAuthMethod> set)
    {
        return sHelper.toBits(set);
    }


    public static ClientAuthMethod[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ClientAuthMethod> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ClientAuthMethod> toSet(ClientAuthMethod[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ClientAuthMethod>
    {
        public Helper(ClientAuthMethod[] values)
        {
            super(ClientAuthMethod.class, values);
        }


        @Override
        protected short getValue(ClientAuthMethod entry)
        {
            return entry.getValue();
        }


        @Override
        protected ClientAuthMethod[] newArray(int size)
        {
            return new ClientAuthMethod[size];
        }
    }
}
