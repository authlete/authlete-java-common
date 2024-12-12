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
 * The class that represents the constraint for {@code utility_bill}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class UtilityBillConstraint extends EvidenceConstraint
{
    private ProviderConstraint provider;
    private LeafConstraint date;


    /**
     * Get the constraint for {@code provider}.
     *
     * @return
     *         The constraint for {@code provider}.
     */
    public ProviderConstraint getProvider()
    {
        return provider;
    }


    /**
     * Set the constraint for {@code provider}.
     *
     * @param constraint
     *         The constraint for {@code provider}.
     */
    public void setProvider(ProviderConstraint provider)
    {
        this.provider = provider;
    }


    /**
     * Get the constraint for {@code date}.
     *
     * @return
     *         The constraint for {@code date}.
     */
    public LeafConstraint getDate()
    {
        return date;
    }


    /**
     * Set the constraint for {@code date}.
     *
     * @param constraint
     *         The constraint for {@code date}.
     */
    public void setDate(LeafConstraint date)
    {
        this.date = date;
    }


    /**
     * Create a {@code UtilityBillConstraint} instance from the given object.
     *
     * @param map
     *         A map that represents {@code "utility_bill"}.
     *
     * @return
     *         A {@code UtilityBillConstraint} instance that represents
     *         {@code "utility_bill"}.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static UtilityBillConstraint extract(Map<?, ?> map) throws ConstraintException
    {
        UtilityBillConstraint instance = new UtilityBillConstraint();
        instance.setExists(true);

        fill(instance, map);

        return instance;
    }


    private static void fill(UtilityBillConstraint instance, Map<?, ?> map)
    {
        EvidenceConstraint.fill(instance, map);

        instance.provider = ProviderConstraint.extract(map, "provider");
        instance.date     = LeafConstraint.extract(    map, "date");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "provider", provider);
        addIfAvailable(map, "date",     date);

        return map;
    }
}
