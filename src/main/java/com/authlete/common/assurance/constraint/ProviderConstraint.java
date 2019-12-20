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
 * The class that represents the constraint for {@code utility_bill/provider}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class ProviderConstraint extends BaseConstraint
{
    private LeafConstraint name;
    private LeafConstraint country;
    private LeafConstraint region;
    private LeafConstraint streetAddress;


    /**
     * Get the constraint for {@code name}.
     *
     * @return
     *         The constraint for {@code name}.
     */
    public LeafConstraint getName()
    {
        return name;
    }


    /**
     * Set the constraint for {@code name}.
     *
     * @param constraint
     *         The constraint for {@code name}.
     */
    public void setName(LeafConstraint constraint)
    {
        this.name = constraint;
    }


    /**
     * Get the constraint for {@code country}.
     *
     * @return
     *         The constraint for {@code country}.
     */
    public LeafConstraint getCountry()
    {
        return country;
    }


    /**
     * Set the constraint for {@code country}.
     *
     * @param constraint
     *         The constraint for {@code country}.
     */
    public void setCountry(LeafConstraint constraint)
    {
        this.country = constraint;
    }


    /**
     * Get the constraint for {@code region}.
     *
     * @return
     *         The constraint for {@code region}.
     */
    public LeafConstraint getRegion()
    {
        return region;
    }


    /**
     * Set the constraint for {@code region}.
     *
     * @param constraint
     *         The constraint for {@code region}.
     */
    public void setRegion(LeafConstraint constraint)
    {
        this.region = constraint;
    }


    /**
     * Get the constraint for {@code street_address}.
     *
     * @return
     *         The constraint for {@code street_address}.
     */
    public LeafConstraint getStreetAddress()
    {
        return streetAddress;
    }


    /**
     * Set the constraint for {@code street_address}.
     *
     * @param constraint
     *         The constraint for {@code street_address}.
     */
    public void setStreetAddress(LeafConstraint constraint)
    {
        this.streetAddress = constraint;
    }


    /**
     * Create a {@code ProviderConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "provider"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "provider"}.
     *
     * @return
     *         A {@code ProviderConstraint} instance that represents
     *         {@code "provider"}. Even if the map does not contain the given
     *         key, an instance of {@code ProviderConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static ProviderConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        ProviderConstraint instance = new ProviderConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(ProviderConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?,?> map = Helper.ensureMap(object, key);

        instance.name          = LeafConstraint.extract(map, "name");
        instance.country       = LeafConstraint.extract(map, "country");
        instance.region        = LeafConstraint.extract(map, "region");
        instance.streetAddress = LeafConstraint.extract(map, "street_address");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "name",           name);
        addIfAvailable(map, "country",        country);
        addIfAvailable(map, "region",         region);
        addIfAvailable(map, "street_address", streetAddress);

        return map;
    }
}
