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


import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


/**
 * The class that represents the constraint which includes {@code verified_claims}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class VerifiedClaimsContainerConstraint extends BaseConstraint
{
    private VerifiedClaimsConstraint verifiedClaims;


    /**
     * Get the constraint for {@code verified_claims}.
     *
     * @return
     *         The constraint for {@code verified_claims}.
     */
    public VerifiedClaimsConstraint getVerifiedClaims()
    {
        return verifiedClaims;
    }


    /**
     * Set the constraint for {@code verified_claims}.
     *
     * @param constraint
     *         The constraint for {@code verified_claims}.
     */
    public void setVerifiedClaims(VerifiedClaimsConstraint constraint)
    {
        this.verifiedClaims = constraint;
    }


    /**
     * Create a {@code VerifiedClaimsContainerConstraint} instance from the given object.
     *
     * @param map
     *         A map that represents a constraint which includes {@code "verified_claims"}.
     *
     * @return
     *         A {@code VerifiedClaimsContainerConstraint} instance that
     *         represents a constraint which includes {@code "verified_claims"}.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static VerifiedClaimsContainerConstraint extract(Map<?, ?> map) throws ConstraintException
    {
        VerifiedClaimsContainerConstraint instance = new VerifiedClaimsContainerConstraint();
        instance.setExists(true);

        fill(instance, map);

        return instance;
    }


    private static void fill(VerifiedClaimsContainerConstraint instance, Map<?, ?> map)
    {
        instance.verifiedClaims = VerifiedClaimsConstraint.extract(map, "verified_claims");
    }


    /**
     * Create a {@code VerifiedClaimsContainerConstraint} instance from the given JSON.
     *
     * <p>
     * Values returned from {@link
     * com.authlete.common.dto.AuthorizationResponse#getIdTokenClaims()
     * getIdTokenClaims()} and {@link
     * com.authlete.common.dto.AuthorizationResponse#getUserInfoClaims()
     * getUserInfoClaims()} of {@link com.authlete.common.dto.AuthorizationResponse
     * AuthorizationResponse} are good candidates to be given to this method.
     * </p>
     *
     * @param json
     *         JSON that may include {@code "verified_claims"}.
     *
     * @return
     *         A {@code VerifiedClaimsContainerConstraint} instance that represents
     *         the given JSON.
     *
     * @throws JsonSyntaxException
     *         The given string is not valid JSON.
     *
     * @throws ConstraintException
     *         The structure of the JSON does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static VerifiedClaimsContainerConstraint fromJson(String json)
            throws JsonSyntaxException, ConstraintException
    {
        if (json == null)
        {
            return null;
        }

        Map<?, ?> map = (Map<?, ?>)new Gson().fromJson(json, Map.class);

        return extract(map);
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "verified_claims", verifiedClaims);

        return map;
    }
}
