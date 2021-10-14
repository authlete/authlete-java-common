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


import java.io.Serializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Grant.
 *
 * <p>
 * This class holds the same information as the response from the Grant
 * Management Endpoint on the grant management action 'query' does.
 * </p>
 *
 * @see <a href="https://openid.net/specs/fapi-grant-management.html"
 *      >Grant Management for OAuth 2.0</a>
 *
 * @since 3.1
 */
public class Grant implements Serializable
{
    private static final long serialVersionUID = 1L;


    private GrantScope[] scopes;
    private String[] claims;
    private AuthzDetails authorizationDetails;


    /**
     * Get the grant scopes.
     *
     * @return
     *         The grant scopes.
     */
    public GrantScope[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the grant scopes.
     *
     * @param scopes
     *         The grant scopes.
     *
     * @return
     *         {@code this} object.
     */
    public Grant setScopes(GrantScope[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the claims.
     *
     * @return
     *         The claims
     */
    public String[] getClaims()
    {
        return claims;
    }


    /**
     * Set the claims
     *
     * @param claims
     *         The claims
     *
     * @return
     *         {@code this} object.
     */
    public Grant setClaims(String[] claims)
    {
        this.claims = claims;

        return this;
    }


    /**
     * Get the authorization details.
     *
     * @return
     *         The authorization details.
     */
    public AuthzDetails getAuthorizationDetails()
    {
        return authorizationDetails;
    }


    /**
     * Set the authorization details.
     *
     * @param details
     *         The authorization details.
     *
     * @return
     *         {@code this} object.
     */
    public Grant setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;

        return this;
    }


    /**
     * Convert this instance into a JSON string.
     *
     * <p>
     * The returned string contains a JSON object which has {@code "scopes"},
     * {@code "claims"} and {@code "authorization_details"} as top-level
     * properties.
     * </p>
     *
     * @return
     *         A JSON string.
     */
    public String toJson()
    {
        return createSerializer().toJson(this);
    }


    /**
     * Build a {@code Grant} instance from a JSON string.
     *
     * <p>
     * The following is an example of input JSON.
     * </p>
     *
     * <pre>
     * {
     *    "scopes":[
     *       {
     *          "scope":"contacts read write",
     *          "resource":[
     *             "https://rs.example.com/api"
     *          ]
     *       },
     *       {
     *          "scope":"openid"
     *       }
     *    ],
     *    "claims":[
     *       "given_name",
     *       "nickname",
     *       "email",
     *       "email_verified"
     *    ],
     *    "authorization_details":[
     *       {
     *          "type":"account_information",
     *          "actions":[
     *             "list_accounts",
     *             "read_balances",
     *             "read_transactions"
     *          ],
     *          "locations":[
     *             "https://example.com/accounts"
     *          ]
     *       }
     *    ]
     * }
     * </pre>
     *
     * @param json
     *         A JSON string.
     *
     * @return
     *         A {@code Grant} instance built from the input JSON.
     */
    public static Grant fromJson(String json)
    {
        if (json == null)
        {
            return null;
        }

        return createDeserializer().fromJson(json, Grant.class);
    }


    /**
     * Create a serializer for {@link Grant}.
     */
    private static Gson createSerializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                Grant.class, new GrantSerializer())
            .create();
    }


    /**
     * Create a deserializer for {@link Grant}.
     */
    private static Gson createDeserializer()
    {
        return new GsonBuilder()
            .registerTypeAdapter(
                Grant.class, new GrantDeserializer())
            .create();
    }
}
