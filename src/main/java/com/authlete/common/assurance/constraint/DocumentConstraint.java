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
 * The class that represents the constraint for {@code id_document/document}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class DocumentConstraint extends BaseConstraint
{
    private LeafConstraint type;
    private LeafConstraint number;
    private IssuerConstraint issuer;
    private LeafConstraint dateOfIssuance;
    private LeafConstraint dateOfExpiry;


    /**
     * Get the constraint for {@code type}.
     *
     * @return
     *         The constraint for {@code type}.
     */
    public LeafConstraint getType()
    {
        return type;
    }


    /**
     * Set the constraint for {@code type}.
     *
     * @param constraint
     *         The constraint for {@code type}.
     */
    public void setType(LeafConstraint constraint)
    {
        this.type = constraint;
    }


    /**
     * Get the constraint for {@code number}.
     *
     * @return
     *         The constraint for {@code number}.
     */
    public LeafConstraint getNumber()
    {
        return number;
    }


    /**
     * Set the constraint for {@code number}.
     *
     * @param constraint
     *         The constraint for {@code number}.
     */
    public void setNumber(LeafConstraint constraint)
    {
        this.number = constraint;
    }


    /**
     * Get the constraint for {@code issuer}.
     *
     * @return
     *         The constraint for {@code issuer}.
     */
    public IssuerConstraint getIssuer()
    {
        return issuer;
    }


    /**
     * Set the constraint for {@code issuer}.
     *
     * @param constraint
     *         The constraint for {@code issuer}.
     */
    public void setIssuer(IssuerConstraint constraint)
    {
        this.issuer = constraint;
    }


    /**
     * Get the constraint for {@code date_of_issuance}.
     *
     * @return
     *         The constraint for {@code date_of_issuance}.
     */
    public LeafConstraint getDateOfIssuance()
    {
        return dateOfIssuance;
    }


    /**
     * Set the constraint for {@code date_of_issuance}.
     *
     * @param constraint
     *         The constraint for {@code date_of_issuance}.
     */
    public void setDateOfIssuance(LeafConstraint constraint)
    {
        this.dateOfIssuance = constraint;
    }


    /**
     * Get the constraint for {@code date_of_expiry}.
     *
     * @return
     *         The constraint for {@code date_of_expiry}.
     */
    public LeafConstraint getDateOfExpiry()
    {
        return dateOfExpiry;
    }


    /**
     * Set the constraint for {@code date_of_expiry}.
     *
     * @param constraint
     *         The constraint for {@code date_of_expiry}.
     */
    public void setDateOfExpiry(LeafConstraint constraint)
    {
        this.dateOfExpiry = constraint;
    }


    /**
     * Create a {@code DocumentConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "document"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "document"}.
     *
     * @return
     *         A {@code DocumentConstraint} instance that represents {@code "document"}.
     *         Even if the map does not contain the given key, an instance of
     *         {@code DocumentConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static DocumentConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        DocumentConstraint instance = new DocumentConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(DocumentConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?,?> map = Helper.ensureMap(object, key);

        instance.type           = LeafConstraint.extract(  map, "type");
        instance.number         = LeafConstraint.extract(  map, "number");
        instance.issuer         = IssuerConstraint.extract(map, "issuer");
        instance.dateOfIssuance = LeafConstraint.extract(  map, "date_of_issuance");
        instance.dateOfExpiry   = LeafConstraint.extract(  map, "date_of_expiry");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "type",             type);
        addIfAvailable(map, "number",           number);
        addIfAvailable(map, "issuer",           issuer);
        addIfAvailable(map, "date_of_issuance", dateOfIssuance);
        addIfAvailable(map, "date_of_expiry",   dateOfExpiry);

        return map;
    }
}
