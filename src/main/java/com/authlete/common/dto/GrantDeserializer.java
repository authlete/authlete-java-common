/*
 * Copyright (C) 2021 Authlete, Inc.
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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


/**
 * JSON deserializer for {@link Grant}.
 *
 * @since 3.1
 */
public class GrantDeserializer extends BaseJsonDeserializer
implements JsonDeserializer<Grant>
{
    @Override
    public Grant deserialize(
            JsonElement jelement, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        if (jelement == null || jelement.isJsonNull())
        {
            return null;
        }

        JsonObject jGrant = jelement.getAsJsonObject();

        // Object to set up.
        Grant grant = new Grant();

        // scopes
        addScopes(jGrant, grant);

        // claims
        addClaims(jGrant, grant);

        // authorizationDetails
        addAuthorizationDetails(jGrant, grant);

        return grant;
    }


    private void addScopes(JsonObject jGrant, Grant grant)
    {
        JsonArray jScopes = getAsArrayFromObject(jGrant, "scopes");

        if (jScopes == null || jScopes.isJsonNull())
        {
            return;
        }

        int size = jScopes.size();
        GrantScope[] scopes = new GrantScope[size];

        Gson gson = new Gson();

        for (int i = 0; i < size; ++i)
        {
            scopes[i] = gson.fromJson(jScopes.get(i), GrantScope.class);
        }

        grant.setScopes(scopes);
    }


    private void addClaims(JsonObject jGrant, Grant grant)
    {
        String[] claims = getAsStringArrayFromObject(jGrant, "claims");

        grant.setClaims(claims);
    }


    private void addAuthorizationDetails(JsonObject jGrant, Grant grant)
    {
        AuthzDetails details = new AuthzDetailsDeserializer().deserialize(
                getAsArrayFromObject(jGrant, "authorization_details"), null, null);

        grant.setAuthorizationDetails(details);
    }
}
