/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.assurance;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * The class that represents {@code verified_claims}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class VerifiedClaims extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;


    private static final String VERIFICATION = "verification";
    private static final String CLAIMS       = "claims";
    private static final Set<String> KEYS;


    static
    {
        KEYS = new HashSet<String>();
        KEYS.add(VERIFICATION);
        KEYS.add(CLAIMS);
    }


    /**
     * Get information about the verification.
     *
     * @return
     *         The information about the verification.
     */
    public Verification getVerification()
    {
        return (Verification)get(VERIFICATION);
    }


    /**
     * Set information about the verification.
     *
     * @param verification
     *         The information about the verification.
     *
     * @return
     *         {@code this} object.
     */
    public VerifiedClaims setVerification(Verification verification)
    {
        put(VERIFICATION, verification);

        return this;
    }


    /**
     * Check if this object contains {@code "verification"}.
     *
     * @return
     *         {@code true} if this object contains {@code "verification"}.
     */
    public boolean containsVerification()
    {
        return containsKey(VERIFICATION);
    }


    /**
     * Remove {@code "verification"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public Verification removeVerification()
    {
        return (Verification)remove(VERIFICATION);
    }


    /**
     * Get the verified claims.
     *
     * @return
     *         The verified claims.
     */
    public Claims getClaims()
    {
        return (Claims)get(CLAIMS);
    }


    /**
     * Set the verified claims.
     *
     * @param claims
     *         The verified claims.
     *
     * @return
     *         {@code this} object.
     */
    public VerifiedClaims setClaims(Claims claims)
    {
        put(CLAIMS, claims);

        return this;
    }


    /**
     * Check if this object contains {@code "claims"}.
     *
     * @return
     *         {@code true} if this object contains {@code "claims"}.
     */
    public boolean containsClaims()
    {
        return containsKey(CLAIMS);
    }


    /**
     * Remove {@code "claims"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public Claims removeClaims()
    {
        return (Claims)remove(CLAIMS);
    }


    /**
     * Add a verified claim.
     *
     * @param name
     *         The name of the claim.
     *
     * @param value
     *         The value of the claim.
     *
     * @return
     *         {@code this} object.
     */
    public VerifiedClaims addClaim(String name, Object value)
    {
        Map<String, Object> map = getClaims();

        if (map == null)
        {
            map = new HashMap<String, Object>();
            put(CLAIMS, map);
        }

        map.put(name, value);

        return this;
    }


    /**
     * Create a {@code VerifiedClaims} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "verified_claims"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "verified_claims"}.
     *
     * @return
     *         A {@code VerifiedClaims} instance that represents {@code "verified_claims"}.
     *         If the map does not contain the given key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static VerifiedClaims extract(Map<?, ?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        VerifiedClaims instance = new VerifiedClaims();

        fill(instance, object, key);

        return instance;
    }


    private static void fill(VerifiedClaims instance, Object object, String key)
    {
        Map<?,?> map = Helper.ensureMap(object, key);

        // "verification": required
        fillVerification(instance, map, key);

        // "claims": required
        fillClaims(instance, map, key);

        // "additionalProperties": false
        Helper.ensureNoAdditionalProperties(map, key, KEYS);
    }


    private static void fillVerification(VerifiedClaims instance, Map<?,?> map, String key)
    {
        // Ensure "verification" is contained.
        Helper.ensureKey(map, VERIFICATION, key);

        Verification verification = Verification.extract(map, VERIFICATION);

        // Ensure "verification" is not null.
        Helper.ensureNotNull(verification, VERIFICATION);

        instance.setVerification(verification);
    }


    private static void fillClaims(VerifiedClaims instance, Map<?,?> map, String key)
    {
        // Ensure "claims" is contained.
        Helper.ensureKey(map, CLAIMS, key);

        Claims claims = Claims.extract(map, CLAIMS);

        // Ensure "claims" is not null.
        Helper.ensureNotNull(claims, CLAIMS);

        instance.setClaims(claims);
    }
}
