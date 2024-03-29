/*
 * Copyright (C) 2019-2021 Authlete, Inc.
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


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;


/**
 * The base class for implementations of
 * {@link com.google.gson.JsonSerializer JsonSerializer}.
 *
 * @since 2.57
 */
public class BaseJsonSerializer
{
    public JsonElement fromStringArray(String[] array)
    {
        if (array == null)
        {
            return JsonNull.INSTANCE;
        }

        JsonArray jarray = new JsonArray();

        for (String string : array)
        {
            jarray.add(string);
        }

        return jarray;
    }


    public void addUnlessNull(JsonObject target, String name, JsonElement element)
    {
        if (element != null && element.isJsonNull() == false)
        {
            target.add(name, element);
        }
    }
}
