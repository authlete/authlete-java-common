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
 * The base class for classes that represent constraints in
 * {@code verified_claims}.
 *
 * <p>
 * Note that {@link EvidenceArrayConstraint} and {@link ClaimsConstraint}
 * don't inherit this class. They implement the {@link Constraint}
 * interface by themselves.
 * </p>
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class BaseConstraint implements Constraint
{
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

        return new LinkedHashMap<String, Object>();
    }


    static void addIfAvailable(Map<String, Object> map, String name, BaseConstraint constraint)
    {
        if (constraint != null && constraint.exists())
        {
            map.put(name, constraint.toMap());
        }
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
