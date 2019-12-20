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
package com.authlete.common.assurance.constraint;


import java.util.Map;


/**
 * The class that represents the constraint for claims in {@code verified_claims/claims}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class VerifiedClaimConstraint extends LeafConstraint
{
    private String purpose;
    private boolean purposeExists;


    /**
     * Get the value of {@code "purpose"}.
     *
     * @return
     *         The value of {@code "purpose"}.
     */
    public String getPurpose()
    {
        return purpose;
    }


    /**
     * Set the value of {@code "purpose"}.
     *
     * @param value
     *         The value of {@code "purpose"}.
     */
    public void setPurpose(String purpose)
    {
        this.purpose = purpose;
    }


    /**
     * Create a {@code VerifiedClaimConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain a constraint.
     *
     * @param key
     *         The key that identifies the object in the map.
     *
     * @return
     *         A {@code VerifiedClaimConstraint} instance that represents a
     *         constraint. Even if the map does not contain the given key, an
     *         instance of {@code VerifiedClaimConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static VerifiedClaimConstraint extract(Object object, String key) throws ConstraintException
    {
        VerifiedClaimConstraint instance = new VerifiedClaimConstraint();
        instance.setExists(true);

        fill(instance, object, key);

        return instance;
    }


    private static void fill(VerifiedClaimConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?,?> map = Helper.ensureMap(object, key);

        LeafConstraint.fill(instance, map, key);

        fillPurpose(instance, map);
    }


    private static void fillPurpose(VerifiedClaimConstraint instance, Map<?,?> map)
    {
        instance.purposeExists = map.containsKey("purpose");

        if (instance.purposeExists)
        {
            instance.purpose = extractString(map, "purpose");
        }
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        if (purposeExists)
        {
            map.put("purpose", purpose);
        }

        return map;
    }
}
