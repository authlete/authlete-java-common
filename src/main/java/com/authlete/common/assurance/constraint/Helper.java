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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


class Helper
{
    public static ConstraintException exception(String format, Object... args)
    {
        return new ConstraintException(String.format(format, args));
    }


    public static void ensureNotNull(Object object, String key)
    {
        if (object == null)
        {
            throw exception("'%s' is null.", key);
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


    public static List<?> ensureList(Object object, String key)
    {
        if (!(object instanceof List))
        {
            throw exception("'%s' is not an array.", key);
        }

        return (List<?>)object;
    }


    public static boolean ensureBoolean(Object object, String key)
    {
        if (!(object instanceof Boolean))
        {
            throw exception("'%s' is not a boolean value.");
        }

        return ((Boolean)object).booleanValue();
    }


    public static long ensureLong(Object object, String key)
    {
        if (!(object instanceof Number))
        {
            throw exception("'%s' is not a number.");
        }

        return ((Number)object).longValue();
    }


    public static String ensureString(Object object, String key)
    {
        if (!(object instanceof String))
        {
            throw exception("'%s' is not a string.");
        }

        return (String)object;
    }


    public static String toJson(Object object)
    {
        return toJson(object, false);
    }


    public static String toJson(Object object, boolean pretty)
    {
        if (object == null)
        {
            return "null";
        }

        return createGson(pretty).toJson(object);
    }


    private static Gson createGson(boolean pretty)
    {
        GsonBuilder builder = new GsonBuilder().serializeNulls();

        if (pretty)
        {
            builder.setPrettyPrinting();
        }

        return builder.create();
    }
}
