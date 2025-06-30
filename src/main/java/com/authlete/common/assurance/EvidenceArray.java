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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The class that represents {@code verified_claims/verification/evidence}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class EvidenceArray extends ArrayList<Evidence>
{
    private static final long serialVersionUID = 1L;


    /**
     * Create an {@code EvidenceArray} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "evidence"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "evidence"}.
     *
     * @return
     *         An {@code EvidenceArray} instance that represents {@code "evidence"}.
     *         If the map does not contain the given key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static EvidenceArray extract(Map<?, ?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        EvidenceArray instance = new EvidenceArray();

        fill(instance, object, key);

        return instance;
    }


    private static void fill(EvidenceArray instance, Object object, String key)
    {
        List<?> list = Helper.ensureList(object, key);
        int size = list.size();

        // "minItems": 1
        if (size < 1)
        {
            throw Helper.exception("'%s' is empty.", key);
        }

        for (int i = 0; i < size; ++i)
        {
            instance.add(Evidence.extract(list, i, key));
        }
    }
}
