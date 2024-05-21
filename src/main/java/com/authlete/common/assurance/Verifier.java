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
import java.util.Map;


/**
 * The class that represents {@code id_document/verifier}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class Verifier extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;


    private static final String ORGANIZATION = "organization";
    private static final String TXN          = "txn";


    /**
     * Get the organization which performed the verification on behalf of the OP.
     *
     * @return
     *         The organization which performed the verification on behalf of the OP.
     */
    public String getOrganization()
    {
        return (String)get(ORGANIZATION);
    }


    /**
     * Set the organization which performed the verification on behalf of the OP.
     *
     * @param organization
     *         The organization which performed the verification on behalf of the OP.
     *
     * @return
     *         {@code this} object.
     */
    public Verifier setOrganization(String organization)
    {
        put(ORGANIZATION, organization);

        return this;
    }


    /**
     * Check if this object contains {@code "organization"}.
     *
     * @return
     *         {@code true} if this object contains {@code "organization"}.
     */
    public boolean containsOrganization()
    {
        return containsKey(ORGANIZATION);
    }


    /**
     * Remove {@code "organization"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeOrganization()
    {
        return (String)remove(ORGANIZATION);
    }


    /**
     * Get the identifier referring to the identity verification transaction.
     *
     * @return
     *         The identifier referring to the identity verification transaction.
     */
    public String getTxn()
    {
        return (String)get(TXN);
    }


    /**
     * Set the identifier referring to the identity verification transaction.
     *
     * @param txn
     *         The identifier referring to the identity verification transaction.
     *
     * @return
     *         {@code this} object.
     */
    public Verifier setTxn(String txn)
    {
        put(TXN, txn);

        return this;
    }


    /**
     * Check if this object contains {@code "txn"}.
     *
     * @return
     *         {@code true} if this object contains {@code "txn"}.
     */
    public boolean containsTxn()
    {
        return containsKey(TXN);
    }


    /**
     * Remove {@code "txn"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeTxn()
    {
        return (String)remove(TXN);
    }


    /**
     * Create a {@code Verifier} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "verifier"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "verifier"}.
     *
     * @return
     *         A {@code Verifier} instance that represents {@code "verifier"}.
     *         If the map does not contain the given key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static Verifier extract(Map<?, ?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        Verifier instance = new Verifier();

        fill(instance, object, key);

        return instance;
    }


    private static void fill(Verifier instance, Object object, String key)
    {
        Map<?, ?> map = Helper.ensureMap(object, key);

        // "organization": optional
        fillOrganization(instance, map, key);

        // "txn": optional
        fillTxn(instance, map, key);
    }


    private static void fillOrganization(Verifier instance, Map<?, ?> map, String key)
    {
        // The value of "organization" in the map.
        String value = Helper.extractString(map, ORGANIZATION, key, false);

        instance.setOrganization(value);
    }


    private static void fillTxn(Verifier instance, Map<?, ?> map, String key)
    {
        // The value of "txn" in the map.
        String value = Helper.extractString(map, TXN, key, false);

        instance.setTxn(value);
    }
}
