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


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Scopes which are used to request claims.
 *
 * <blockquote>
 * <ol>
 *   <li>{@link #PROFILE profile}
 *   <li>{@link #EMAIL email}
 *   <li>{@link #ADDRESS address}
 *   <li>{@link #PHONE phone}
 * </ol>
 * </blockquote>
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
 *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ClaimsScope
{
    /**
     * This scope ({@code "profile"}) requests the following claims.
     *
     * <blockquote>
     * <ol>
     *   <li>{@link StandardClaims#NAME name}
     *   <li>{@link StandardClaims#FAMILY_NAME family_name}
     *   <li>{@link StandardClaims#GIVEN_NAME given_name}
     *   <li>{@link StandardClaims#MIDDLE_NAME middle_name}
     *   <li>{@link StandardClaims#NICKNAME nickname}
     *   <li>{@link StandardClaims#PREFERRED_USERNAME preferred_username}
     *   <li>{@link StandardClaims#PROFILE profile}
     *   <li>{@link StandardClaims#PICTURE picture}
     *   <li>{@link StandardClaims#WEBSITE website}
     *   <li>{@link StandardClaims#GENDER gender}
     *   <li>{@link StandardClaims#BIRTHDATE birthdate}
     *   <li>{@link StandardClaims#ZONEINFO zoneinfo}
     *   <li>{@link StandardClaims#LOCALE locale}
     *   <li>{@link StandardClaims#UPDATED_AT updated_at}
     * </ol>
     * </blockquote>
     */
    PROFILE("profile",
            StandardClaims.NAME,
            StandardClaims.FAMILY_NAME,
            StandardClaims.GIVEN_NAME,
            StandardClaims.MIDDLE_NAME,
            StandardClaims.NICKNAME,
            StandardClaims.PREFERRED_USERNAME,
            StandardClaims.PROFILE,
            StandardClaims.PICTURE,
            StandardClaims.WEBSITE,
            StandardClaims.GENDER,
            StandardClaims.BIRTHDATE,
            StandardClaims.ZONEINFO,
            StandardClaims.LOCALE,
            StandardClaims.UPDATED_AT
    ),


    /**
     * This scope ({@code "email"}) requests the following claims.
     *
     * <blockquote>
     * <ol>
     *   <li>{@link StandardClaims#EMAIL email}
     *   <li>{@link StandardClaims#EMAIL_VERIFIED email_verified}
     * </ol>
     * </blockquote>
     */
    EMAIL("email",
            StandardClaims.EMAIL,
            StandardClaims.EMAIL_VERIFIED
    ),


    /**
     * This scope ({@code "address"}) requests the following claims.
     *
     * <blockquote>
     * <ol>
     *   <li>{@link StandardClaims#ADDRESS address}
     * </ol>
     * </blockquote>
     */
    ADDRESS("address",
            StandardClaims.ADDRESS
    ),


    /**
     * This scope ({@code "phone"}) requests the following claims.
     *
     * <blockquote>
     * <ol>
     *   <li>{@link StandardClaims#PHONE_NUMBER phone_number}
     *   <li>{@link StandardClaims#PHONE_NUMBER_VERIFIED phone_number_verified}
     * </ol>
     * </blockquote>
     */
    PHONE("phone",
            StandardClaims.PHONE_NUMBER,
            StandardClaims.PHONE_NUMBER_VERIFIED
    )
    ;


    private static final Map<String, ClaimsScope> sNameMap;
    private final String mName;
    private final SortedSet<String> mClaims;


    static
    {
        sNameMap = new HashMap<String, ClaimsScope>();

        for (ClaimsScope value : values())
        {
            sNameMap.put(value.mName, value);
        }
    }


    private ClaimsScope(String name, String... claims)
    {
        mName = name;

        SortedSet<String> set = new TreeSet<String>();

        for (String claim : claims)
        {
            set.add(claim);
        }

        mClaims = Collections.unmodifiableSortedSet(set);
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
     * Get the list of claim names which are requested by this scope.
     *
     * @return
     *         The list of claim names.
     */
    public SortedSet<String> getClaims()
    {
        return mClaims;
    }


    /**
     * Get a {@link ClaimsScope} instance by a name.
     *
     * @param name
     *         A scope name such as {@code "profile"}.
     *
     * @return
     *         A {@link ClaimsScope} instance, or {@code null} when
     *         no instance has the specified name.
     */
    public static ClaimsScope getByName(String name)
    {
        return sNameMap.get(name);
    }
}
