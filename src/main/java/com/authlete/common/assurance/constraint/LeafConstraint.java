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


import java.util.List;
import java.util.Map;


/**
 * The class that represents the basic set of constraints defined in "<a href=
 * "https://openid.net/specs/openid-connect-core-1_0.html#IndividualClaimsRequests"
 * >5.5.1. Individual Claims Requests</a>".
 *
 * <pre>
 * {
 *   "essential": boolean,
 *   "value": string,
 *   "values": string array
 * }
 * </pre>
 *
 * @since 2.63
 */
public class LeafConstraint extends BaseConstraint
{
    private boolean essential;
    private String value;
    private String[] values;


    /**
     * Get the value of {@code "essential"}
     *
     * @return
     *         The value of {@code "essential"}.
     */
    public boolean isEssential()
    {
        return essential;
    }


    /**
     * Set the value of {@code "essential"}
     *
     * @param essential
     *         The value of {@code "essential"}.
     */
    public void setEssential(boolean essential)
    {
        this.essential = essential;
    }


    /**
     * Get the value of {@code "value"}.
     *
     * @return
     *         The value of {@code "value"}.
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Set the value of {@code "value"}.
     *
     * @param value
     *         The value of {@code "value"}.
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Get the value of {@code "values"}.
     *
     * @return
     *         The value of {@code "values"}.
     */
    public String[] getValues()
    {
        return values;
    }


    /**
     * Set the value of {@code "values"}.
     *
     * @param value
     *         The value of {@code "values"}.
     */
    public void setValues(String[] values)
    {
        this.values = values;
    }


    /**
     * Create a {@code LeafConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain a constraint.
     *
     * @param key
     *         The key that identifies the object in the map.
     *
     * @return
     *         A {@code LeafConstraint} that represents a constraint.
     *         Even if the map does not contain the given key, an instance of
     *         {@code LeafConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static LeafConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        LeafConstraint instance = new LeafConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    static void fill(LeafConstraint instance, Object object, String key)
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

        instance.essential = extractBoolean(    map, "essential");
        instance.value     = extractString(     map, "value");
        instance.values    = extractStringArray(map, "values");
    }


    private static boolean extractBoolean(Map<?,?> map, String key)
    {
        if (map.containsKey(key) == false)
        {
            return false;
        }

        Object value = map.get(key);

        if (value == null)
        {
            return false;
        }

        if (!(value instanceof Boolean))
        {
            throw new ConstraintException("'" + key + "' is not a boolean value.");
        }

        return ((Boolean)value).booleanValue();
    }


    static String extractString(Map<?,?> map, String key)
    {
        if (map.containsKey(key) == false)
        {
            return null;
        }

        Object value = map.get(key);

        if (value == null)
        {
            return null;
        }

        if (!(value instanceof String))
        {
            throw new ConstraintException("'" + key + "' is not a string.");
        }

        return (String)value;
    }


    private static String[] extractStringArray(Map<?,?> map, String key)
    {
        if (map.containsKey(key) == false)
        {
            return null;
        }

        Object value = map.get(key);

        if (value == null)
        {
            return null;
        }

        if (!(value instanceof List))
        {
            throw new ConstraintException("'" + key + "' is not an array.");
        }

        List<?> list = (List<?>)value;
        int size = list.size();

        String[] array = new String[size];

        for (int i = 0; i < size; ++i)
        {
            Object element = list.get(i);

            if (element != null && !(element instanceof String))
            {
                throw new ConstraintException(
                        String.format("'%s[%d]' is not a string.", key, i));
            }

            array[i] = (String)element;
        }

        return array;
    }
}
