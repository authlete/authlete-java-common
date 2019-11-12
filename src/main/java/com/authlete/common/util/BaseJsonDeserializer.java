/*
 * Copyright (C) 2019 Authlete, Inc.
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
import com.google.gson.JsonObject;


/**
 * The base class for implementations of
 * {@link com.google.gson.JsonDeserializer JsonDeserializer}.
 *
 * @since 2.57
 */
public class BaseJsonDeserializer
{
    public JsonElement getFromObject(JsonObject jobject, String name)
    {
        JsonElement jelement = jobject.get(name);

        if (jelement == null || jelement.isJsonNull())
        {
            return null;
        }

        return jelement;
    }


    public String getAsStringFromObject(JsonObject jobject, String name)
    {
        JsonElement jelement = getFromObject(jobject, name);

        if (jelement == null)
        {
            return null;
        }

        return jelement.getAsString();
    }


    public String getAsStringFromArray(JsonArray jarray, int index)
    {
        JsonElement jelement = jarray.get(index);

        if (jelement == null || jelement.isJsonNull())
        {
            return null;
        }

        return jelement.getAsString();
    }


    public JsonArray getAsArrayFromObject(JsonObject jobject, String name)
    {
        JsonElement jelement = getFromObject(jobject, name);

        if (jelement == null)
        {
            return null;
        }

        return jelement.getAsJsonArray();
    }


    public String[] getAsStringArrayFromObject(JsonObject jobject, String name)
    {
        JsonArray jarray = getAsArrayFromObject(jobject, name);

        if (jarray == null)
        {
            return null;
        }

        int size = jarray.size();
        String[] array = new String[size];

        for (int i = 0; i < size; ++i)
        {
            array[i] = getAsStringFromArray(jarray, i);
        }

        return array;
    }
}
