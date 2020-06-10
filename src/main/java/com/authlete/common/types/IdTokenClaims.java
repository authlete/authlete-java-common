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


import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Claims used within the ID Token for all OAuth 2.0 flows
 * used by OpenID Connect.
 *
 * <blockquote>
 * <ol>
 *   <li>{@link #ISS iss}
 *   <li>{@link #SUB sub}
 *   <li>{@link #AUD aud}
 *   <li>{@link #EXP exp}
 *   <li>{@link #IAT iat}
 *   <li>{@link #AUTH_TIME auth_time}
 *   <li>{@link #NONCE nonce}
 *   <li>{@link #ACR acr}
 *   <li>{@link #AMR amr}
 *   <li>{@link #AZP azp}
 * </ol>
 * </blockquote>
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#IDToken"
 *      >OpenID Connect Core 1.0, 2. ID Token</a>
 *
 * @author Takahiko Kawasaki
 */
public class IdTokenClaims
{
    /**
     * Issuer Identifier for the Issuer of the response.
     */
    public static final String ISS = "iss";


    /**
     * Subject Identifier.
     */
    public static final String SUB = "sub";


    /**
     * Audience(s) that the ID Token is intended for.
     */
    public static final String AUD = "aud";


    /**
     * Expiration time on or after which the ID Token MUST NOT be
     * accepted for processing.
     */
    public static final String EXP = "exp";


    /**
     * Time at which the ID Token was issued.
     */
    public static final String IAT = "iat";


    /**
     * Time when the End-User authentication occurred.
     */
    public static final String AUTH_TIME = "auth_time";


    /**
     * String value used to associate a Client session with an ID Token,
     * and to mitigate replay attacks.
     */
    public static final String NONCE = "nonce";


    /**
     * Authentication Context Class Reference.
     */
    public static final String ACR = "acr";


    /**
     * Authentication Methods References.
     */
    public static final String AMR = "amr";


    /**
     * Authorized party - the party to which the ID Token was issued.
     */
    public static final String AZP = "azp";


    /**
     * Set of claim names.
     */
    private static final SortedSet<String> sIdTokenClaims;


    static
    {
        SortedSet<String> set = new TreeSet<String>();

        set.add(ISS);
        set.add(SUB);
        set.add(AUD);
        set.add(EXP);
        set.add(IAT);
        set.add(AUTH_TIME);
        set.add(NONCE);
        set.add(ACR);
        set.add(AMR);
        set.add(AZP);

        sIdTokenClaims = Collections.unmodifiableSortedSet(set);
    }


    private IdTokenClaims()
    {
    }


    /**
     * Check if the given claim name is in the list described in
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#IDToken"
     * >"OpenID Connect Core 1&#46;0, 2&#46; ID Token</a>".
     */
    public static boolean isIdTokenClaim(String claimName)
    {
        if (claimName == null)
        {
            return false;
        }

        return sIdTokenClaims.contains(claimName);
    }


    /**
     * Get the claim list described in
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#IDToken"
     * >"OpenID Connect Core 1&#46;0, 2&#46; ID Token"</a>.
     */
    public static SortedSet<String> getIdTokenClaims()
    {
        return sIdTokenClaims;
    }
}
