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
package com.authlete.common.dto;


import java.lang.reflect.Type;
import com.authlete.common.util.BaseJsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


/**
 * JSON deserializer for {@link AuthzDetailsElement}.
 *
 * <p>
 * Other fields than the independent fields such as {@code type} and
 * {@code locations} are packed into one {@code otherFields} string.
 * </p>
 *
 * @since 2.57
 */
public class AuthzDetailsElementDeserializer extends BaseJsonDeserializer
implements JsonDeserializer<AuthzDetailsElement>
{
    private static String[] INDEPENDENT_FIELDS = new String[] {
        "type", "locations", "actions", "datatypes", "identifier", "privileges"
    };


    @Override
    public AuthzDetailsElement deserialize(
            JsonElement jelement, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        if (jelement == null || jelement.isJsonNull())
        {
            return null;
        }

        JsonObject jobject = jelement.getAsJsonObject();

        return new AuthzDetailsElement()
                .setType(getAsStringFromObject(jobject, "type"))
                .setLocations(getAsStringArrayFromObject(jobject, "locations"))
                .setActions(getAsStringArrayFromObject(jobject, "actions"))
                .setDataTypes(getAsStringArrayFromObject(jobject, "datatypes"))
                .setIdentifier(getAsStringFromObject(jobject, "identifier"))
                .setPrivileges(getAsStringArrayFromObject(jobject, "privileges"))
                .setOtherFields(getOtherFieldsFromObject(jobject))
                ;
    }


    private static String getOtherFieldsFromObject(JsonObject jobject)
    {
        JsonObject copy = jobject.deepCopy();

        for (String name : INDEPENDENT_FIELDS)
        {
            copy.remove(name);
        }

        if (copy.size() == 0)
        {
            return null;
        }

        return copy.toString();
    }
}
