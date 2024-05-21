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
 * The class that represents {@code id_document/document/issuer}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class Issuer extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;


    private static final String NAME    = "name";
    private static final String COUNTRY = "country";


    /**
     * Get the name of the issuer.
     *
     * @return
     *         The name of the issuer.
     */
    public String getName()
    {
        return (String)get(NAME);
    }


    /**
     * Set the name of the issuer.
     *
     * @param name
     *         The name of the issuer.
     *
     * @return
     *         {@code this} object.
     */
    public Issuer setName(String name)
    {
        put(NAME, name);

        return this;
    }


    /**
     * Check if this object contains {@code "name"}.
     *
     * @return
     *         {@code true} if this object contains {@code "name"}.
     */
    public boolean containsName()
    {
        return containsKey(NAME);
    }


    /**
     * Remove {@code "name"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeName()
    {
        return (String)remove(NAME);
    }


    /**
     * Get the country or organization that issued the document.
     *
     * @return
     *         The country or organization that issued the document.
     */
    public String getCountry()
    {
        return (String)get(COUNTRY);
    }


    /**
     * Set the country or organization that issued the document.
     *
     * @param country
     *         The country or organization that issued the document.
     *
     * @return
     *         {@code this} object.
     */
    public Issuer setCountry(String country)
    {
        put(COUNTRY, country);

        return this;
    }


    /**
     * Check if this object contains {@code "country"}.
     *
     * @return
     *         {@code true} if this object contains {@code "country"}.
     */
    public boolean containsCountry()
    {
        return containsKey(COUNTRY);
    }


    /**
     * Remove {@code "country"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeCountry()
    {
        return (String)remove(COUNTRY);
    }


    /**
     * Create an {@code Issuer} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "issuer"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "issuer"}.
     *
     * @return
     *         An {@code Issuer} instance that represents {@code "issuer"}.
     *         If the map does not contain the given key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static Issuer extract(Map<?, ?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        Issuer instance = new Issuer();

        fill(instance, object, key);

        return instance;
    }


    private static void fill(Issuer instance, Object object, String key)
    {
        Map<?, ?> map = Helper.ensureMap(object, key);

        // "name": optional
        fillName(instance, map, key);

        // "country": optional
        fillCountry(instance, map, key);
    }


    private static void fillName(Issuer instance, Map<?, ?> map, String key)
    {
        // The value of "name" in the map.
        String value = Helper.extractString(map, NAME, key, false);

        instance.setName(value);
    }


    private static void fillCountry(Issuer instance, Map<?, ?> map, String key)
    {
        // The value of "country" in the map.
        String value = Helper.extractString(map, COUNTRY, key, false);

        instance.setCountry(value);
    }
}
