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
 * The class that represents the basic set of constraints + {@code max_age}.
 *
 * @since 2.63
 */
public class TimeConstraint extends LeafConstraint
{
    private long maxAge;
    private boolean maxAgeExists;


    /**
     * Get the value of {@code "max_age"}.
     *
     * @return
     *         The value of {@code "max_age"}.
     */
    public long getMaxAge()
    {
        return maxAge;
    }


    /**
     * Set the value of {@code "max_age"}.
     *
     * @param essential
     *         The value of {@code "max_age"}.
     */
    public void setMaxAge(long maxAge)
    {
        this.maxAge = maxAge;
    }


    /**
     * Create a {@code TimeConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain a constraint.
     *
     * @param key
     *         The key that identifies the object in the map.
     *
     * @return
     *         A {@code TimeConstraint} instance that represents a constraint.
     *         Even if the map does not contain the given key, an instance of
     *         {@code TimeConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static TimeConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        TimeConstraint instance = new TimeConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(TimeConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?,?> map = Helper.ensureMap(object, key);

        LeafConstraint.fill(instance, map, key);

        fillMaxAge(instance, map);
    }


    private static void fillMaxAge(TimeConstraint instance, Map<?,?> map)
    {
        instance.maxAgeExists = map.containsKey("max_age");

        if (instance.maxAgeExists)
        {
            instance.maxAge = extractLong(map, "max_age");
        }
    }


    private static long extractLong(Map<?,?> map, String key)
    {
        Object value = map.get(key);

        return Helper.ensureLong(value, key);
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        if (maxAgeExists)
        {
            map.put("max_age", Long.valueOf(maxAge));
        }

        return map;
    }
}
