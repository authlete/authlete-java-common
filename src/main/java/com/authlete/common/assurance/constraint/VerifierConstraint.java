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
 * The class that represents the constraint for {@code id_document/verifier}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class VerifierConstraint extends BaseConstraint
{
    private LeafConstraint organization;
    private LeafConstraint txn;


    /**
     * Get the constraint for {@code organization}.
     *
     * @return
     *         The constraint for {@code organization}.
     */
    public LeafConstraint getOrganization()
    {
        return organization;
    }


    /**
     * Set the constraint for {@code organization}.
     *
     * @param constraint
     *         The constraint for {@code organization}.
     */
    public void setOrganization(LeafConstraint constraint)
    {
        this.organization = constraint;
    }


    /**
     * Get the constraint for {@code txn}.
     *
     * @return
     *         The constraint for {@code txn}.
     */
    public LeafConstraint getTxn()
    {
        return txn;
    }


    /**
     * Set the constraint for {@code txn}.
     *
     * @param constraint
     *         The constraint for {@code txn}.
     */
    public void setTxn(LeafConstraint constraint)
    {
        this.txn = constraint;
    }


    /**
     * Create a {@code VerifierConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "verifier"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "verifier"}.
     *
     * @return
     *         A {@code VerifierConstraint} instance that represents
     *         {@code "verifier"}. Even if the map does not contain the given
     *         key, an instance of {@code VerifierConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static VerifierConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        VerifierConstraint instance = new VerifierConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(VerifierConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?,?> map = Helper.ensureMap(object, key);

        instance.organization = LeafConstraint.extract(map, "organization");
        instance.txn          = LeafConstraint.extract(map, "txn");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "organization", organization);
        addIfAvailable(map, "txn",          txn);

        return map;
    }
}
