/*
 * Copyright (C) 2019-2020 Authlete, Inc.
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
package com.authlete.common.assurance.constraint;


import java.util.Map;


/**
 * The class that represents the constraint for {@code verified_claims}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class VerifiedClaimsConstraint extends BaseConstraint
{
    private VerificationConstraint verification;
    private ClaimsConstraint claims;


    /**
     * Get the constraint for {@code verification}.
     *
     * @return
     *         The constraint for {@code verification}.
     */
    public VerificationConstraint getVerification()
    {
        return verification;
    }


    /**
     * Set the constraint for {@code verification}.
     *
     * @param constraint
     *         The constraint for {@code verification}.
     */
    public void setVerification(VerificationConstraint constraint)
    {
        this.verification = constraint;
    }


    /**
     * Get the constraint for {@code claims}.
     *
     * @return
     *         The constraint for {@code claims}.
     */
    public ClaimsConstraint getClaims()
    {
        return claims;
    }


    /**
     * Set the constraint for {@code claims}.
     *
     * @param constraint
     *         The constraint for {@code claims}.
     */
    public void setClaims(ClaimsConstraint constraint)
    {
        this.claims = constraint;
    }


    /**
     * Get the flag that indicates whether {@code verified_claims} requires
     * all possible claims.
     *
     * <p>
     * When the value of the {@code "claims"} is null like below, it is
     * <i>"interpreted as a request for all possible Claims."</i>
     * </p>
     *
     * <pre>
     * {
     *   "verified_claims": {
     *     "claims": null
     *   }
     * }
     * </pre>
     *
     * <p>
     * This method returns {@code true} when {@code "claims"} does not exist
     * or the value of {@code "claims"} is null.
     * </p>
     *
     * <p>
     * <b>NOTE</b>: In the Implementer's Draft 1
     * (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0-ID1.html"
     * >ID1</a>) of OpenID Connect for Identity Assurance 1.0,
     * {@code "claims":null} has a special meaning. It is interpreted as
     * <i>"all possible claims"</i>. However, the special rule was removed
     * from the
     * <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0-ID2.html"
     * >ID2</a>. Therefore, this method has been marked as deprecated.
     * </p>
     *
     * @return
     *         {@code true} if the constraint indicates that all possible
     *         claims are required.
     */
    @Deprecated
    public boolean isAllClaimsRequested()
    {
        // OpenID Connect for Identity Assurance 1.0, Implementer's Draft 1
        // 5.1. Requesting End-User Claims
        //
        //   Note: A claims sub-element with value null is interpreted
        //   as a request for all possible Claims.
        //
        //   Note: The claims sub-element can be omitted, which is
        //   equivalent to a claims element whose value is null.

        return (claims == null || !claims.exists() || claims.isNull());
    }


    /**
     * Create a {@code VerifiedClaimsConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "verified_claims"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "verified_claims"}.
     *
     * @return
     *         A {@code VerifiedClaimsConstraint} instance that represents
     *         {@code "verified_claims"}. Even if the map does not contain the
     *         given key, an instance of {@code VerifiedClaimsConstraint} is
     *         returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static VerifiedClaimsConstraint extract(Map<?, ?> map, String key) throws ConstraintException
    {
        VerifiedClaimsConstraint instance = new VerifiedClaimsConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(VerifiedClaimsConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?, ?> map = Helper.ensureMap(object, key);

        instance.verification = VerificationConstraint.extract(map, "verification");
        instance.claims       = ClaimsConstraint.extract(map, "claims");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "verification", verification);

        if (claims != null && claims.exists())
        {
            map.put("claims", claims.toMap());
        }

        return map;
    }
}
