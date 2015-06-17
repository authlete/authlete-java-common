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


import java.util.HashMap;
import java.util.Map;


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
    ADDRESS("address"),


    /**
     * {@code "email"}, which requests {@code "email"} and {@code
     * "email_verified"} claims.
     *
     * @see ClaimsScope#EMAIL
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     */
    EMAIL("email"),


    /**
     * {@code "openid"}, which must be contained in every OpenID Connect
     * Authentication Request.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     */
    OPENID("openid"),


    /**
     * {@code "offline_access"}, which requests that <i>an OAuth 2&#x002E;0 Refresh Token
     * be issued that can be used to obtain an Access Token that grants access to
     * the End-User's UserInfo Endpoint even when the End-User is not present
     * (not logged in).</i>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#OfflineAccess"
     *      >OpenID Connect Core 1.0, 11. Offline Access</a>
     */
    OFFLINE_ACCESS("offline_access"),


    /**
     * {@code "phone"}, which requests {@code "phone_number"} and {@code
     * "phone_number_verified"} claims.
     *
     * @see ClaimsScope#PHONE
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     */
    PHONE("phone"),


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
    PROFILE("profile")
    ;


    private static final Map<String, StandardScope> sNameMap;
    private final String mName;


    static
    {
        sNameMap = new HashMap<String, StandardScope>();

        for (StandardScope value : values())
        {
            sNameMap.put(value.mName, value);
        }
    }


    private StandardScope(String name)
    {
        mName = name;
    }


    /**
     * Get the scope name in lower-case letters.
     *
     * @return
     *         The scope name.
     */
    public String getName()
    {
        return mName;
    }


    /**
     * Get the {@code StandardScope} instance from a scope name.
     *
     * @param name
     *         Scope name in lower-case letters.
     *
     * @return
     *         A {@code StandardScope} instance, or {@code null} if not found.
     */
    public static StandardScope getByName(String name)
    {
        return sNameMap.get(name);
    }
}
