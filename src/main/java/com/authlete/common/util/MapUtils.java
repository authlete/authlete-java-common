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
package com.authlete.common.util;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.authlete.common.dto.TaggedValue;


/**
 * Utility for {@link Map}.
 *
 * @since 3.45
 */
public class MapUtils
{
    /**
     * Put the given key-value pair into the {@code target} map when the
     * {@code value} is {@code true} or {@code falseIncluded} is {@code true}.
     */
    public static void put(
            Map<String, Object> target, String key,
            boolean value, boolean falseIncluded)
    {
        if (value != false || falseIncluded)
        {
            target.put(key, value);
        }
    }


    /**
     * Put the given key-value pair into the {@code target} map when the
     * {@code value} is 0 or {@code zeroIncluded} is {@code true}.
     */
    public static void put(
            Map<String, Object> target, String key,
            int value, boolean zeroIncluded)
    {
        if (value != 0 || zeroIncluded)
        {
            target.put(key, value);
        }
    }


    /**
     * Put the given key-value pair into the {@code target} map when the
     * {@code value} is 0 or {@code zeroIncluded} is {@code true}.
     */
    public static void put(
            Map<String, Object> target, String key,
            long value, boolean zeroIncluded)
    {
        if (value != 0 || zeroIncluded)
        {
            target.put(key, value);
        }
    }


    /**
     * Put the given key-value pair into the {@code target} map when the
     * {@code value} is not {@code null} or {@code nullIncluded} is
     * {@code true}.
     *
     * <p>
     * Note that the value of {@code value.toString()} is put into the
     * {@code target} map, not the {@code value} itself.
     * </p>
     */
    public static <T> void put(
            Map<String, Object> target, String key,
            T value, boolean nullIncluded)
    {
        if (value != null)
        {
            target.put(key, value.toString());
        }
        else if (nullIncluded)
        {
            target.put(key, null);
        }
    }


    /**
     * Put the given key-array pair into the {@code target} map when the
     * {@code array} is not {@code null} or {@code nullIncluded} is
     * {@code true}.
     *
     * <p>
     * Note that elements in the array are converted to {@code String}
     * instances by the {@code toString()} method and added to a new
     * {@code List} instance, and the {@code List} instance is put into
     * the {@code target} map.
     * </p>
     */
    public static <T> void put(
            Map<String, Object> target, String key,
            T[] array, boolean nullIncluded)
    {
        List<String> list = asList(array);

        if (list != null || nullIncluded)
        {
            target.put(key, list);
        }
    }


    /**
     * Put <code>"{baseKey}#{tag}"</code> properties into the {@code target}
     * map.
     */
    public static void put(
            Map<String, Object> target, String baseKey,
            TaggedValue[] array, boolean nullIncluded)
    {
        if (array == null)
        {
            return;
        }

        for (TaggedValue taggedValue : array)
        {
            putTaggedValue(target, baseKey, taggedValue, nullIncluded);
        }
    }


    private static void putTaggedValue(
            Map<String, Object> target, String baseKey,
            TaggedValue taggedValue, boolean nullIncluded)
    {
        if (taggedValue == null)
        {
            return;
        }

        String tag = taggedValue.getTag();

        if (tag == null || tag.isEmpty())
        {
            return;
        }

        String value = taggedValue.getValue();

        if (value == null && !nullIncluded)
        {
            return;
        }

        target.put(String.format("%s#%s", baseKey, tag), value);
    }


    /**
     * Convert the given {@code json} into a {@code Map} instance and put
     * the instance into the {@code target} map with the {@code key}.
     *
     * <p>
     * If {@code nullIncluded} is {@code true}, the {@code key} is put into
     * the {@code target} map even when the given {@code json} is {@code null}.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public static void putJsonObject(
            Map<String, Object> target, String key,
            String json, boolean nullIncluded)
    {
        Map<String, Object> value = (json == null) ? null :
                (Map<String, Object>)Utils.fromJson(json, Map.class);

        if (value != null || nullIncluded)
        {
            target.put(key, value);
        }
    }


    private static <T> List<String> asList(T[] array)
    {
        if (array == null)
        {
            return null;
        }

        return Arrays.stream(array)
                .map(element -> element.toString())
                .collect(Collectors.toList());
    }


    /**
     * Put the entries in the {@code map} into the {@code target} map.
     * When {@code control} is {@code null}, an instance is internally
     * created by {@code new MapControl()} and used.
     */
    public static void put(
            Map<String, Object> target,
            Map<String, Object> map, MapControl control)
    {
        if (control == null)
        {
            control = new MapControl();
        }

        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            putEntry(target, entry, control);
        }
    }


    private static void putEntry(
            Map<String, Object> target,
            Map.Entry<String, Object> entry, MapControl control)
    {
        String key   = entry.getKey();
        Object value = entry.getValue();

        if (value instanceof Boolean)
        {
            putEntryBoolean(target, key, (Boolean)value, control);
        }
        else if (value instanceof Integer)
        {
            putEntryInteger(target, key, (Integer)value, control);
        }
        else if (value instanceof Number)
        {
            putEntryNumber(target, key, (Number)value, control);
        }
        else
        {
            putEntryDefault(target, key, value, control);
        }
    }


    private static void putEntryBoolean(
            Map<String, Object> target,
            String key, Boolean value, MapControl control)
    {
        boolean value_ = value.booleanValue();

        if (value_ || control.isFalseIncluded())
        {
            target.put(key, value_);
        }
    }


    private static void putEntryInteger(
            Map<String, Object> target,
            String key, Integer value, MapControl control)
    {
        int value_ = ((Integer)value).intValue();

        if (value_ != 0 || control.isZeroIncluded())
        {
            target.put(key, value_);
        }
    }


    private static void putEntryNumber(
            Map<String, Object> target,
            String key, Number value, MapControl control)
    {
        long value_ = ((Number)value).longValue();

        if (value_ != 0 || control.isZeroIncluded())
        {
            target.put(key, value_);
        }
    }


    private static void putEntryDefault(
            Map<String, Object> target,
            String key, Object value, MapControl control)
    {
        if (value != null || control.isNullIncluded())
        {
            target.put(key, value);
        }
    }
}
