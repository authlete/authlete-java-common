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
package com.authlete.common.assurance;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * The class that represents elements in {@code verified_claims/verification/evidence}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public abstract class Evidence extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;


    private static final String TYPE = "type";


    /**
     * The constructor.
     *
     * @param type
     *         The type of the evidence.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.4.1.1"
     *      >OpenID Connect for Identity Assurance 1.0, 4.1.1. Evidence</a>
     */
    public Evidence(String type)
    {
        put(TYPE, type);
    }


    /**
     * Get the type of this evidence. The value given to the constructor of
     * {@link Evidence}.
     *
     * @return
     *         The type of this evidence.
     */
    public String getType()
    {
        return (String)get(TYPE);
    }


    /**
     * Create an instance of a subclass of {@code Evidence} from an object in
     * the given list.
     *
     * @param list
     *         A list that represents an {@code "evidence"} array.
     *
     * @param index
     *         The position in the list.
     *
     * @param key
     *         The key that identifies the array. In normal cases, the key is
     *         {@code "evidence"}.
     *
     * @return
     *         An instance of a subclass of {@code Evidence} that represents
     *         the element in the {@code "evidence"} array.
     *
     * @throws IdentityAssuranceException
     *         The structure of the object does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static Evidence extract(List<?> list, int index, String key) throws IdentityAssuranceException
    {
        // Extract the element at the index as a Map.
        Object object = Helper.extractObject(list, index, key, true);
        Map<?,?> map  = Helper.ensureMap(object, index, key);

        String parent = String.format("%s[%d]", key, index);

        // "type": required
        String type = Helper.extractString(map, TYPE, parent, true);

        if ("id_document".equals(type))
        {
            return IDDocument.extract(map);
        }

        if ("qes".equals(type))
        {
            return QES.extract(map);
        }

        if ("utility_bill".equals(type))
        {
            return UtilityBill.extract(map);
        }

        throw Helper.exception("'type' of '%s[%d]' is unknown.", key, index);
    }
}
