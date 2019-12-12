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
 * The class that represents the constraint for
 * {@code id_document/document/issuer}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class IssuerConstraint extends BaseConstraint
{
    private LeafConstraint name;
    private LeafConstraint country;


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
     * Create an {@code IssuerConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "issuer"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "issuer"}.
     *
     * @return
     *         An {@code IssuerConstraint} that represents {@code "issuer"}.
     *         Even if the map does not contain the given key, an instance of
     *         {@code IssuerConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static IssuerConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        IssuerConstraint instance = new IssuerConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(IssuerConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        if (!(object instanceof Map))
        {
            throw new ConstraintException("'" + key + "' is not an object.");
        }

        Map<?,?> map = (Map<?,?>)object;

        instance.name    = LeafConstraint.extract(map, "name");
        instance.country = LeafConstraint.extract(map, "country");
    }
}
