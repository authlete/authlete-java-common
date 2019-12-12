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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The class that represents the constraint for
 * {@code verified_claims/verification/evidence}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class EvidenceArrayConstraint
extends ArrayList<EvidenceConstraint>
implements Constraint
{
    private static final long serialVersionUID = 1L;


    private boolean exists;
    private boolean isNull;


    @Override
    public boolean exists()
    {
        return exists;
    }


    /**
     * Set the existence of the constraint.
     *
     * @param exists
     *         {@code true} to indicate that the constraint exists.
     */
    public void setExists(boolean exists)
    {
        this.exists = exists;
    }


    @Override
    public boolean isNull()
    {
        return isNull;
    }


    /**
     * Set the boolean flag that indicates that the value of the constraint is null.
     *
     * @param isNull
     *         {@code true} to indicate that the value of the constraint is null.
     */
    public void setNull(boolean isNull)
    {
        this.isNull = isNull;
    }


    /**
     * Create an {@code EvidenceArrayConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "evidence"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "evidence"}.
     *
     * @return
     *         An {@code EvidenceArrayConstraint} that represents {@code "evidence"}.
     *         Even if the map does not contain the given key, an instance of
     *         {@code EvidenceArrayConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static EvidenceArrayConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        EvidenceArrayConstraint instance = new EvidenceArrayConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(EvidenceArrayConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        if (!(object instanceof List))
        {
            throw new ConstraintException("'" + key + "' is not an array.");
        }

        List<?> list = (List<?>)object;
        int size = list.size();

        for (int i = 0; i < size; ++i)
        {
            instance.add(EvidenceConstraint.extract(list, i, key));
        }
    }
}
