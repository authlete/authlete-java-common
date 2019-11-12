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
import com.authlete.common.util.BaseJsonDeserializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


/**
 * JSON deserializer for {@link AuthzDetails}.
 *
 * @since 2.57
 */
public class AuthzDetailsDeserializer extends BaseJsonDeserializer
implements JsonDeserializer<AuthzDetails>
{
    @Override
    public AuthzDetails deserialize(
            JsonElement jelement, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        if (jelement == null || jelement.isJsonNull())
        {
            return null;
        }

        JsonArray jarray = jelement.getAsJsonArray();
        int size = jarray.size();

        AuthzDetails details = new AuthzDetails();

        AuthzDetailsElement[] elements = new AuthzDetailsElement[size];
        details.setElements(elements);

        AuthzDetailsElementDeserializer elementDeserializer =
            new AuthzDetailsElementDeserializer();

        for (int i = 0; i < size; ++i)
        {
            elements[i] = elementDeserializer.deserialize(jarray.get(i), null, null);
        }

        return details;
    }
}
