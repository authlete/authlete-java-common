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
import com.authlete.common.util.BaseJsonSerializer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/**
 * JSON serializer for {@link Grant}.
 *
 * @since 3.1
 */
public class GrantSerializer extends BaseJsonSerializer
implements JsonSerializer<Grant>
{
    @Override
    public JsonElement serialize(
            Grant grant, Type type, JsonSerializationContext context)
    {
        if (grant == null)
        {
            return JsonNull.INSTANCE;
        }

        // Object to set up.
        JsonObject jGrant = new JsonObject();

        // "scopes"
        addScopes(grant, jGrant);

        // "claims"
        addClaims(grant, jGrant);

        // "authorization_details"
        addAuthorizationDetails(grant, jGrant);

        return jGrant;
    }


    private void addScopes(Grant grant, JsonObject jGrant)
    {
        JsonElement element = new Gson().toJsonTree(grant.getScopes());

        addUnlessNull(jGrant, "scopes", element);
    }


    private void addClaims(Grant grant, JsonObject jGrant)
    {
        JsonElement element = fromStringArray(grant.getClaims());

        addUnlessNull(jGrant, "claims", element);
    }


    private void addAuthorizationDetails(Grant grant, JsonObject jGrant)
    {
        JsonElement element = new AuthzDetailsSerializer().serialize(
                grant.getAuthorizationDetails(), null, null);

        addUnlessNull(jGrant, "authorization_details", element);
    }
}
