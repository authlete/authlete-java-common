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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/**
 * JSON serializer for {@link AuthzDetails}.
 *
 * @since 2.57
 */
public class AuthzDetailsSerializer extends BaseJsonSerializer
implements JsonSerializer<AuthzDetails>
{
    @Override
    public JsonElement serialize(
            AuthzDetails details, Type type, JsonSerializationContext context)
    {
        if (details == null)
        {
            return JsonNull.INSTANCE;
        }

        AuthzDetailsElement[] elements = details.getElements();
        if (elements == null)
        {
            return JsonNull.INSTANCE;
        }

        JsonArray jarray = new JsonArray();

        AuthzDetailsElementSerializer elementSerializer = new AuthzDetailsElementSerializer();

        for (int i = 0; i < elements.length; ++i)
        {
            jarray.add(elementSerializer.serialize(elements[i], null, null));
        }

        return jarray;
    }
}
