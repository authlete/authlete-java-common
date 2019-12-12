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
 * The class that represents the constraint for {@code qes}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class QESConstraint extends EvidenceConstraint
{
    private LeafConstraint type;
    private LeafConstraint issuer;
    private LeafConstraint serialNumber;
    private LeafConstraint createdAt;


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
     * Get the constraint for {@code issuer}.
     *
     * @return
     *         The constraint for {@code issuer}.
     */
    public LeafConstraint getIssuer()
    {
        return issuer;
    }


    /**
     * Set the constraint for {@code issuer}.
     *
     * @param constraint
     *         The constraint for {@code issuer}.
     */
    public void setIssuer(LeafConstraint constraint)
    {
        this.issuer = constraint;
    }


    /**
     * Get the constraint for {@code serial_number}.
     *
     * @return
     *         The constraint for {@code serial_number}.
     */
    public LeafConstraint getSerialNumber()
    {
        return serialNumber;
    }


    /**
     * Set the constraint for {@code serial_number}.
     *
     * @param constraint
     *         The constraint for {@code serial_number}.
     */
    public void setSerialNumber(LeafConstraint constraint)
    {
        this.serialNumber = constraint;
    }


    /**
     * Get the constraint for {@code created_at}.
     *
     * @return
     *         The constraint for {@code created_at}.
     */
    public LeafConstraint getCreatedAt()
    {
        return createdAt;
    }


    /**
     * Set the constraint for {@code created_at}.
     *
     * @param constraint
     *         The constraint for {@code created_at}.
     */
    public void setCreatedAt(LeafConstraint constraint)
    {
        this.createdAt = constraint;
    }


    /**
     * Create a {@code QESConstraint} instance from the given object.
     *
     * @param map
     *         A map that represents {@code "qes"}.
     *
     * @return
     *         A {@code QESConstraint} that represents {@code "qes"}.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static QESConstraint extract(Map<?,?> map) throws ConstraintException
    {
        QESConstraint instance = new QESConstraint();
        instance.setExists(true);

        fill(instance, map);

        return instance;
    }


    private static void fill(QESConstraint instance, Map<?,?> map)
    {
        instance.type         = LeafConstraint.extract(map, "type");
        instance.issuer       = LeafConstraint.extract(map, "issuer");
        instance.serialNumber = LeafConstraint.extract(map, "serial_number");
        instance.createdAt    = LeafConstraint.extract(map, "created_at");
    }
}
