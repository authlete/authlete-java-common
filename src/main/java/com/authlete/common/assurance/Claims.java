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


import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The class that represents {@code verified_claims/claims}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class Claims extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;


    /**
     * Put a claim to this object.
     *
     * <p>
     * This method internally calls {@code put(String, Object)} method to
     * register the given pair of claim name and claim value and then
     * returns {@code this} object.
     * </p>
     *
     * @param claimName
     *         The claim name.
     *
     * @param claimValue
     *         The claim value.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.65
     */
    public Claims putClaim(String claimName, Object claimValue)
    {
        put(claimName, claimValue);

        return this;
    }


    /**
     * Create a {@code Claims} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "claims"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "claims"}.
     *
     * @return
     *         A {@code Claims} instance that represents {@code "claims"}.
     *         If the map does not contain the given key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static Claims extract(Map<?, ?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        Claims instance = new Claims();

        fill(instance, object, key);

        return instance;
    }


    private static void fill(Claims instance, Object object, String key)
    {
        Map<?, ?> map = Helper.ensureMap(object, key);

        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            if (!(entry.getKey() instanceof String))
            {
                throw Helper.exception("A key in '%s' is not a string.", key);
            }

            instance.put((String)entry.getKey(), entry.getValue());
        }

        // "minProperties": 1
        if (instance.size() < 1)
        {
            throw Helper.exception("'%s' is empty.", key);
        }
    }
}
