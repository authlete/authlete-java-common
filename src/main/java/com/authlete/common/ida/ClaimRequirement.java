/*
 * Copyright (C) 2022 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.ida;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Requirement for a claim by the {@code "value"}, {@code "values"} and
 * {@code "max_age"} constraints.
 *
 * @since 3.17
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 */
class ClaimRequirement
{
    private static final String KEY_ESSENTIAL      = "essential";  // OIDC Core 1.0
    private static final String KEY_VALUE          = "value";      // OIDC Core 1.0
    private static final String KEY_VALUES         = "values";     // OIDC Core 1.0
    private static final String KEY_MAX_AGE        = "max_age";    // OIDC Identity Assurance 1.0
    private static final String KEY_PURPOSE        = "purpose";    // OIDC Identity Assurance 1.0
    private static final Set<String> RESERVED_KEYS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    KEY_ESSENTIAL,
                    KEY_VALUE,
                    KEY_VALUES,
                    KEY_MAX_AGE,
                    KEY_PURPOSE
            )));


    private String mValue;
    private List<String> mValues;
    private Long mMaxAge;
    private Set<String> mUnreservedKeys;


    /**
     * Get the value of the {@code "value"} constraint.
     *
     * @return
     *         The value of the {@code "value"} constraint.
     *         {@code null} if the constraint does not exist.
     */
    public String getValue()
    {
        return mValue;
    }


    /**
     * Set the value of the {@code "value"} constraint.
     *
     * @param value
     *         The value of the {@code "value"} constraint.
     *         {@code null} means that the constraint does not exist.
     *
     * @return
     *         {@code this} object.
     */
    public ClaimRequirement setValue(String value)
    {
        mValue = value;

        return this;
    }


    /**
     * Get the value of the {@code "values"} constraint.
     *
     * @return
     *         The value of the {@code "values"} constraint.
     *         {@code null} if the constraint does not exist.
     */
    public List<String> getValues()
    {
        return mValues;
    }


    /**
     * Set the value of the {@code "values"} constraint.
     *
     * @param value
     *         The value of the {@code "values"} constraint.
     *         {@code null} means that the constraint does not exist.
     *
     * @return
     *         {@code this} object.
     */
    public ClaimRequirement setValues(List<String> values)
    {
        mValues = values;

        return this;
    }


    /**
     * Get the value of the {@code "max_age"} constraint.
     *
     * @return
     *         The value of the {@code "max_mage"} constraint.
     *         {@code null} if the constraint does not exist.
     */
    public Long getMaxAge()
    {
        return mMaxAge;
    }


    /**
     * Set the value of the {@code "max_age"} constraint.
     *
     * @param value
     *         The value of the {@code "max_age"} constraint.
     *         {@code null} means that the constraint does not exist.
     *
     * @return
     *         {@code this} object.
     */
    public ClaimRequirement setMaxAge(Long maxAge)
    {
        mMaxAge = maxAge;

        return this;
    }


    /**
     * Get unreserved keys that were found in the map given to the
     * {@link #parse(Map)} method.
     *
     * <p>
     * "Unreserved" here means "Not contained in the set of reserved keys
     * returned from the {@link #getReservedKeys()}."
     *
     * @return
     *         Unreserved keys found in the map given to the
     *         {@link #parse(Map)} method.
     */
    public Set<String> getUnreservedKeys()
    {
        return mUnreservedKeys;
    }


    /**
     * Set unreserved keys that were found in the map given to the
     * {@link #parse(Map)} method.
     *
     * @param keys
     *         Unreserved keys found in the map given to the
     *         {@link #parse(Map)} method.
     *
     * @return
     *         {@code this} object.
     */
    public ClaimRequirement setUnreservedKeys(Set<String> keys)
    {
        mUnreservedKeys = keys;

        return this;
    }


    /**
     * Check whether unreserved keys were contained in the map given to the
     * {@link #parse(Map)} method.
     *
     * @return
     *         {@code true} if unreserved keys were contained in the map.
     */
    public boolean hasUnreservedKeys()
    {
        return (mUnreservedKeys != null && 0 < mUnreservedKeys.size());
    }


    /**
     * Get the reserved keys that have special meanings and should not be
     * treated as normal claim names.
     *
     * <p>
     * The current implementation treats the following keys as reserved ones.
     * That is, the set returned from this method contains the following keys.
     * </p>
     *
     * <ul>
     * <li>{@code "essential"}
     * <li>{@code "value"}
     * <li>{@code "values"}
     * <li>{@code "max_age"}
     * <li>{@code "purpose"}
     * </ul>
     *
     * @return
     *         A set of reserved keys.
     */
    public static Set<String> getReservedKeys()
    {
        return RESERVED_KEYS;
    }


    /**
     * Convert the given map into a {@code ClaimRequirement} instance.
     *
     * <p>
     * When the given map contains any of the following constraints, this
     * method returns a {@code ClaimRequirement} instance. Otherwise, it
     * returns {@code null}.
     * </p>
     *
     * <ul>
     * <li>{@code "value"}
     * <li>{@code "values"}
     * <li>{@code "max_age"}
     * </ul>
     *
     * <p>
     * It is assumed that the value of "value" is a string, the elements of
     * {@code "values"} are all strings, and the value of {@code "max_age"}
     * is a number. When constraints hold values of other types, the
     * constraints are silently ignored.
     * </p>
     *
     * <p>
     * It seems that standard specifications assume that multiple constraints
     * are not specified at the same time, but this implementation does not
     * exclude the possibility. No problem occurs even in the case.
     * </p>
     *
     * @param map
     *         A {@code Map} object that may contain any of the {@code "value"},
     *         {@code "values"} and {@code "max_age"} constraints.
     *
     * @return
     *         A {@code ClaimRequirement} instance that represents constraints
     *         contained in the given map. If the given map does not contain
     *         constraints, {@code null} is returned.
     */
    public static ClaimRequirement parse(Map<String, Object> map)
    {
        if (map == null)
        {
            return null;
        }

        // Constraints in the given map.
        String       value  = extractAsString(    map, KEY_VALUE);
        List<String> values = extractAsStringList(map, KEY_VALUES);
        Long         maxAge = extractAsLong(      map, KEY_MAX_AGE);

        // Unreserved keys in the given map.
        Set<String> unreservedKeys = map.keySet().stream()
                .filter(key -> !RESERVED_KEYS.contains(key))
                .collect(Collectors.toSet())
                ;

        // If the given map contains no unreserved keys.
        if (unreservedKeys.size() == 0)
        {
            unreservedKeys = null;
        }

        // If the given map contains no constraints.
        if (value == null && values == null && maxAge == null)
        {
            return null;
        }

        return new ClaimRequirement()
                .setValue(value)
                .setValues(values)
                .setMaxAge(maxAge)
                .setUnreservedKeys(unreservedKeys)
                ;
    }


    /**
     * Get the value of the key as a string. If the value is not a string,
     * {@code null} is returned.
     */
    private static String extractAsString(Map<String, Object> map, String key)
    {
        Object value = map.get(key);

        // If the value is a string.
        if (value instanceof String)
        {
            return (String)value;
        }

        return null;
    }


    /**
     * Get the value of the key as a string list. If the value is not a list or
     * any of the elements in the list is not a string, {@code null} is returned.
     */
    private static List<String> extractAsStringList(Map<String, Object> map, String key)
    {
        Object value = map.get(key);

        // If the value is not a list.
        if (!(value instanceof List))
        {
            return null;
        }

        // Check all the elements in the array.
        for (Object element : (List<?>)value)
        {
            // If the element is not a string.
            if (!(element instanceof String))
            {
                return null;
            }
        }

        // Convert the '?' array into a string array.
        return ((List<?>)value).stream()
                .map(element -> (String)element).collect(Collectors.toList());
    }


    /**
     * Get the value of the key as a {@code Long} instance. If the value is not
     * a number, {@code null} is returned.
     */
    private static Long extractAsLong(Map<String, Object> map, String key)
    {
        Object value = map.get(key);

        // If the value is a number.
        if (value instanceof Number)
        {
            // Interpret the value as Long.
            return Long.valueOf(((Number)value).longValue());
        }

        return null;
    }
}
