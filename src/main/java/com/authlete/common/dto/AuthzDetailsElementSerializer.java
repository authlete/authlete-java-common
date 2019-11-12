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
package com.authlete.common.dto;


import java.lang.reflect.Type;
import com.authlete.common.util.BaseJsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/**
 * JSON serializer for {@link AuthzDetailsElement}.
 *
 * <p>
 * "Other fields" (the string returned from {@link AuthzDetailsElement#getOtherFields()})
 * are expanded and merged with the independent fields such as {@code type} and
 * {@code locations}.
 * </p>
 *
 * @since 2.57
 */
public class AuthzDetailsElementSerializer extends BaseJsonSerializer
implements JsonSerializer<AuthzDetailsElement>
{
    @Override
    public JsonElement serialize(
            AuthzDetailsElement element, Type type, JsonSerializationContext context)
    {
        if (element == null)
        {
            return JsonNull.INSTANCE;
        }

        JsonObject jobject;

        String otherFields = element.getOtherFields();

        if (otherFields != null)
        {
            jobject = (JsonObject)new JsonParser().parse(otherFields);
        }
        else
        {
            jobject = new JsonObject();
        }

        jobject.addProperty("type",       element.getType());
        jobject.add(        "locations",  fromStringArray(element.getLocations()));
        jobject.add(        "actions",    fromStringArray(element.getActions()));
        jobject.addProperty("identifier", element.getIdentifier());

        return jobject;
    }
}
