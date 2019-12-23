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


import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The class that represents the constraint for {@code verified_claims/claims}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class ClaimsConstraint
extends LinkedHashMap<String, VerifiedClaimConstraint>
implements Constraint
{
    private static final long serialVersionUID = 2L;


    private boolean exists;
    private boolean isNull;


    @Override
    public boolean exists()
    {
        return exists;
    }


    /**
     * Set the existence of the constraint.
     *
     * @param exists
     *         {@code true} to indicate that the constraint exists.
     */
    public void setExists(boolean exists)
    {
        this.exists = exists;
    }


    @Override
    public boolean isNull()
    {
        return isNull;
    }


    /**
     * Set the boolean flag that indicates that the value of the constraint is null.
     *
     * @param isNull
     *         {@code true} to indicate that the value of the constraint is null.
     */
    public void setNull(boolean isNull)
    {
        this.isNull = isNull;
    }


    /**
     * Put a claim constraint to this object.
     *
     * <p>
     * This method internally calls {@code put(String, VerifiedClaimConstraint)}
     * method to register the given pair of claim name and constraint and then
     * returns {@code this} object.
     * </p>
     *
     * @param claimName
     *         The claim name.
     *
     * @param constraint
     *         The constraint for the claim.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.65
     */
    public ClaimsConstraint putClaim(String claimName, VerifiedClaimConstraint constraint)
    {
        put(claimName, constraint);

        return this;
    }


    /**
     * Create a {@code ClaimsConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "claims"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "claims"}.
     *
     * @return
     *         A {@code ClaimsConstraint} instance that represents {@code "claims"}.
     *         Even if the map does not contain the given key, an instance of
     *         {@code ClaimsConstraint} is returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static ClaimsConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        ClaimsConstraint instance = new ClaimsConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(ClaimsConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?,?> map = Helper.ensureMap(object, key);

        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            if (!(entry.getKey() instanceof String))
            {
                throw Helper.exception("A key in '%s' is not a string.", key);
            }

            String name = (String)entry.getKey();

            VerifiedClaimConstraint constraint =
                    VerifiedClaimConstraint.extract(entry.getValue(), name);

            instance.put(name, constraint);
        }
    }


    /**
     * Create a {@code Map} instance that represents this object in the way
     * conforming to the structure defined in <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.5"
     * >5. Requesting Verified Claims</a> of OpenID Connect for Identity Assurance 1.0.
     *
     * @return
     *         A {@code Map} instance that represents this object.
     *         If {@link #exists()} returns {@code false} or {@link #isNull()}
     *         returns {@code true}, this method returns null.
     */
    public Map<String, Object> toMap()
    {
        if (!exists || isNull)
        {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        for (Map.Entry<String, VerifiedClaimConstraint> entry : entrySet())
        {
            map.put(entry.getKey(), entry.getValue().toMap());
        }

        return map;
    }


    /**
     * Convert this object into JSON in the way conforming to the structure
     * defined in <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.5"
     * >5. Requesting Verified Claims</a> of OpenID Connect for Identity Assurance 1.0.
     *
     * <p>
     * This method is an alias of {@link #toJson(boolean) toJson}{@code (false)}.
     * </p>
     *
     * @return
     *         JSON that represents this object. If {@link #toMap()} returns
     *         null, this method returns {@code "null"} (a {@code String}
     *         instance which consists of {@code 'n'}, {@code 'u'}, {@code 'l'}
     *         and {@code 'l'}).
     */
    public String toJson()
    {
        return Helper.toJson(toMap());
    }


    /**
     * Convert this object into JSON in the way conforming to the structure
     * defined in <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.5"
     * >5. Requesting Verified Claims</a> of OpenID Connect for Identity Assurance 1.0.
     *
     * @param pretty
     *         {@code true} to make the output more human-readable.
     *
     * @return
     *         JSON that represents this object. If {@link #toMap()} returns
     *         null, this method returns {@code "null"} (a {@code String}
     *         instance which consists of {@code 'n'}, {@code 'u'}, {@code 'l'}
     *         and {@code 'l'}).
     */
    public String toJson(boolean pretty)
    {
        return Helper.toJson(toMap(), pretty);
    }
}
