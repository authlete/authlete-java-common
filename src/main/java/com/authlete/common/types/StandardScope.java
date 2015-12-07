/*
 * Copyright (C) 2015 Authlete, Inc.
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
 * Scopes defined by related specifications.
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html"
 *      >OpenID Connect Core 1.0</a>
 *
 * @since 1.5
 *
 * @author Takahiko Kawasaki
 */
public enum StandardScope
{
    /**
     * {@code "address"}, which requests {@code "address"} claim.
     *
     * @see ClaimsScope#ADDRESS
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     */
    ADDRESS((short)1, "address"),


    /**
     * {@code "email"}, which requests {@code "email"} and {@code
     * "email_verified"} claims.
     *
     * @see ClaimsScope#EMAIL
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     */
    EMAIL((short)2, "email"),


    /**
     * {@code "openid"}, which must be contained in every OpenID Connect
     * Authentication Request.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     */
    OPENID((short)3, "openid"),


    /**
     * {@code "offline_access"}, which requests that <i>an OAuth 2&#x002E;0 Refresh Token
     * be issued that can be used to obtain an Access Token that grants access to
     * the End-User's UserInfo Endpoint even when the End-User is not present
     * (not logged in).</i>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#OfflineAccess"
     *      >OpenID Connect Core 1.0, 11. Offline Access</a>
     */
    OFFLINE_ACCESS((short)4, "offline_access"),


    /**
     * {@code "phone"}, which requests {@code "phone_number"} and {@code
     * "phone_number_verified"} claims.
     *
     * @see ClaimsScope#PHONE
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     */
    PHONE((short)5, "phone"),


    /**
     * {@code "profile"}, which requests the following claims:
     * {@code "name"}, {@code "family_name"}, {@code "given_name"},
     * {@code "middle_name"}, {@code "nickname"}, {@code "preferred_username"},
     * {@code "profile"}, {@code "picture"}, {@code "website"}, {@code "gender"},
     * {@code "birthdate"}, {@code "zoneinfo"}, {@code "locale"} and
     * {@code "updated_at"}.
     *
     * @see ClaimsScope#PROFILE
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     */
    PROFILE((short)6, "profile")
    ;


    private static final StandardScope[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private StandardScope(short value, String string)
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
    public static StandardScope getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code StandardScope}.
     *
     * @param scope
     *         A standard scope name. For example, {@code "openid"}.
     *
     * @return
     *         {@code StandardScope} instance, or {@code null}.
     */
    public static StandardScope parse(String scope)
    {
        if (scope == null)
        {
            return null;
        }

        for (StandardScope entry : sValues)
        {
            if (entry.mString.equals(scope))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<StandardScope> set)
    {
        return sHelper.toBits(set);
    }


    public static StandardScope[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<StandardScope> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<StandardScope> toSet(StandardScope[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<StandardScope>
    {
        public Helper(StandardScope[] values)
        {
            super(StandardScope.class, values);
        }


        @Override
        protected short getValue(StandardScope entry)
        {
            return entry.getValue();
        }


        @Override
        protected StandardScope[] newArray(int size)
        {
            return new StandardScope[size];
        }
    }
}
