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


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


class Helper
{
    // YYYY-MM-DDThh:mm:ss
    private static Pattern DATETIME_PATTERN =
            Pattern.compile("^\\d{4}-?\\d{2}-?\\d{2}T\\d{2}:?\\d{2}:?\\d{2}([,.]\\d{1,})?(Z|[+-]\\d{2}(:?\\d{2})?)?$");


    // YYYY-MM-DD
    private static Pattern DATE_PATTERN =
            Pattern.compile("^\\d{4}-?\\d{2}-?\\d{2}$");


    public static IdentityAssuranceException exception(String format, Object... args)
    {
        return new IdentityAssuranceException(String.format(format, args));
    }


    public static void ensureNotNull(Object object, String key)
    {
        if (object == null)
        {
            throw exception("'%s' is null.", key);
        }
    }


    public static void ensureKey(Map<?,?> map, String key, String parent)
    {
        if (!map.containsKey(key))
        {
            throw exception("'%s' does not include '%s'.", parent, key);
        }
    }


    public static Map<?,?> ensureMap(Object object, String key)
    {
        if (!(object instanceof Map))
        {
            throw exception("'%s' is not an object.", key);
        }

        return (Map<?,?>)object;
    }


    public static Map<?,?> ensureMap(Object object, int index, String key)
    {
        if (!(object instanceof Map))
        {
            throw exception("'%s[%d]' is not an object.", key, index);
        }

        return (Map<?,?>)object;
    }


    public static List<?> ensureList(Object object, String key)
    {
        if (!(object instanceof List))
        {
            throw exception("'%s' is not an array.", key);
        }

        return (List<?>)object;
    }


    public static void ensureNoAdditionalProperties(Map<?,?> map, String parent, Set<String> validKeys)
    {
        for (Object key : map.keySet())
        {
            if (validKeys.contains(key))
            {
                continue;
            }

            throw exception("'%s' contains invalid kesy.", parent);
        }
    }


    public static Object extractObject(Map<?,?> map, String key, String parent, boolean required)
    {
        if (!map.containsKey(key))
        {
            if (required)
            {
                throw exception("'%s' does not include '%s'.", parent, key);
            }
            else
            {
                return null;
            }
        }

        Object value = map.get(key);

        if (value == null)
        {
            if (required)
            {
                throw exception("'%s' is null.", key);
            }
            else
            {
                return null;
            }
        }

        return value;
    }


    public static Object extractObject(List<?> list, int index, String key, boolean required)
    {
        Object value = list.get(index);

        if (value == null)
        {
            if (required)
            {
                throw exception("'%s[%d]' is null.", key, index);
            }
            else
            {
                return null;
            }
        }

        return value;
    }


    public static String extractString(Map<?,?> map, String key, String parent, boolean required)
    {
        Object value = extractObject(map, key, parent, required);

        if (value == null)
        {
            return null;
        }

        if (!(value instanceof String))
        {
            throw exception("'%s' is not a string.", key);
        }

        return (String)value;
    }


    public static String extractDateTime(Map<?,?> map, String key, String parent, boolean required)
    {
        String value = extractString(map, key, parent, required);

        if (value == null)
        {
            return null;
        }

        if (!isValidDateTime(value))
        {
            throw exception("The format of '%s' in '%s' is wrong.", key, parent);
        }

        return value;
    }


    public static boolean isValidDateTime(String string)
    {
        return DATETIME_PATTERN.matcher(string).matches();
    }


    public static String extractDate(Map<?,?> map, String key, String parent, boolean required)
    {
        String value = extractString(map, key, parent, required);

        if (value == null)
        {
            return null;
        }

        if (!isValidDate(value))
        {
            throw exception("The format of '%s' in '%s' is wrong.", key, parent);
        }

        return value;
    }


    public static boolean isValidDate(String string)
    {
        return DATE_PATTERN.matcher(string).matches();
    }
}
