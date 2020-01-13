/*
 * Copyright (C) 2019-2020 Authlete, Inc.
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
 * The class that represents {@code utility_bill/provider}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class Provider extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 2L;


    private static final String NAME           = "name";
    private static final String FORMATTED      = "formatted";
    private static final String STREET_ADDRESS = "street_address";
    private static final String LOCALITY       = "locality";
    private static final String REGION         = "region";
    private static final String POSTAL_CODE    = "postal_code";
    private static final String COUNTRY        = "country";


    /**
     * Get the name of the provider.
     *
     * @return
     *         The name of the provider.
     */
    public String getName()
    {
        return (String)get(NAME);
    }


    /**
     * Set the name of the provider.
     *
     * @param name
     *         The name of the provider.
     *
     * @return
     *         {@code this} object.
     */
    public Provider setName(String name)
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
     * Get the formatted address of the provider.
     *
     * @return
     *         The formatted address of the provider.
     *
     * @since 2.67
     */
    public String getFormatted()
    {
        return (String)get(FORMATTED);
    }


    /**
     * Set the formatted address of the provider.
     *
     * @param formatted
     *         The formatted address of the provider.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.67
     */
    public Provider setFormatted(String formatted)
    {
        put(FORMATTED, formatted);

        return this;
    }


    /**
     * Check if this object contains {@code "formatted"}.
     *
     * @return
     *         {@code true} if this object contains {@code "formatted"}.
     *
     * @since 2.67
     */
    public boolean containsFormatted()
    {
        return containsKey(FORMATTED);
    }


    /**
     * Remove {@code "formatted"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     *
     * @since 2.67
     */
    public String removeFormatted()
    {
        return (String)remove(FORMATTED);
    }


    /**
     * Get the street address of the provider's address.
     *
     * @return
     *         The street address of the provider's address.
     */
    public String getStreetAddress()
    {
        return (String)get(STREET_ADDRESS);
    }


    /**
     * Set the street address of the provider's address.
     *
     * @param streetAddress
     *         The street address of the provider's address.
     *
     * @return
     *         {@code this} object.
     */
    public Provider setStreetAddress(String streetAddress)
    {
        put(STREET_ADDRESS, streetAddress);

        return this;
    }


    /**
     * Check if this object contains {@code "street_address"}.
     *
     * @return
     *         {@code true} if this object contains {@code "street_address"}.
     */
    public boolean containsStreetAddress()
    {
        return containsKey(STREET_ADDRESS);
    }


    /**
     * Remove {@code "street_address"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeStreetAddress()
    {
        return (String)remove(STREET_ADDRESS);
    }


    /**
     * Get the locality of the provider's address.
     *
     * @return
     *         The locality of the provider's address.
     *
     * @since 2.67
     */
    public String getLocality()
    {
        return (String)get(LOCALITY);
    }


    /**
     * Set the locality of the provider's address.
     *
     * @param locality
     *         The locality of the provider's address.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.67
     */
    public Provider setLocality(String locality)
    {
        put(LOCALITY, locality);

        return this;
    }


    /**
     * Check if this object contains {@code "locality"}.
     *
     * @return
     *         {@code true} if this object contains {@code "locality"}.
     *
     * @since 2.67
     */
    public boolean containsLocality()
    {
        return containsKey(LOCALITY);
    }


    /**
     * Remove {@code "locality"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     *
     * @since 2.67
     */
    public String removeLocality()
    {
        return (String)remove(LOCALITY);
    }


    /**
     * Get the region of the provider's address.
     *
     * @return
     *         The region of the provider's address.
     */
    public String getRegion()
    {
        return (String)get(REGION);
    }


    /**
     * Set the region of the provider's address.
     *
     * @param region
     *         The region of the provider's address.
     *
     * @return
     *         {@code this} object.
     */
    public Provider setRegion(String region)
    {
        put(REGION, region);

        return this;
    }


    /**
     * Check if this object contains {@code "region"}.
     *
     * @return
     *         {@code true} if this object contains {@code "region"}.
     */
    public boolean containsRegion()
    {
        return containsKey(REGION);
    }


    /**
     * Remove {@code "region"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeRegion()
    {
        return (String)remove(REGION);
    }


    /**
     * Get the postal code of the provider's address.
     *
     * @return
     *         The postal code of the provider's address.
     *
     * @since 2.67
     */
    public String getPostalCode()
    {
        return (String)get(POSTAL_CODE);
    }


    /**
     * Set the postal code of the provider's address.
     *
     * @param postalCode
     *         The postal code of the provider's address.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.67
     */
    public Provider setPostalCode(String postalCode)
    {
        put(POSTAL_CODE, postalCode);

        return this;
    }


    /**
     * Check if this object contains {@code "postal_code"}.
     *
     * @return
     *         {@code true} if this object contains {@code "postal_code"}.
     *
     * @since 2.67
     */
    public boolean containsPostalCode()
    {
        return containsKey(POSTAL_CODE);
    }


    /**
     * Remove {@code "postal_code"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     *
     * @since 2.67
     */
    public String removePostalCode()
    {
        return (String)remove(POSTAL_CODE);
    }


    /**
     * Get the country of the provider's address
     *
     * @return
     *         The country of the provider's address.
     */
    public String getCountry()
    {
        return (String)get(COUNTRY);
    }


    /**
     * Set the country of the provider's address
     *
     * @param country
     *         The country of the provider's address.
     *
     * @return
     *         {@code this} object.
     */
    public Provider setCountry(String country)
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
     * Create a {@code Provider} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "provider"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "provider"}.
     *
     * @return
     *         A {@code Provider} instance that represents {@code "provider"}.
     *         If the map does not contain the give key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static Provider extract(Map<?,?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        Provider instance = new Provider();

        fill(instance, object, key);

        return instance;
    }


    private static void fill(Provider instance, Object object, String key)
    {
        Map<?,?> map = Helper.ensureMap(object, key);

        // "name": optional
        fillName(instance, map, key);

        // "formatted": optional
        fillFormatted(instance, map, key);

        // "street_address": optional
        fillStreetAddress(instance, map, key);

        // "locality": optional
        fillLocality(instance, map, key);

        // "region": optional
        fillRegion(instance, map, key);

        // "postal_code": optional
        fillPostalCode(instance, map, key);

        // "country": optional
        fillCountry(instance, map, key);
    }


    private static void fillName(Provider instance, Map<?,?> map, String key)
    {
        // The value of "name" in the map.
        String value = Helper.extractString(map, NAME, key, false);

        instance.setName(value);
    }


    private static void fillFormatted(Provider instance, Map<?,?> map, String key)
    {
        // The value of "formatted" in the map.
        String value = Helper.extractString(map, FORMATTED, key, false);

        instance.setFormatted(value);
    }


    private static void fillStreetAddress(Provider instance, Map<?,?> map, String key)
    {
        // The value of "street_address" in the map.
        String value = Helper.extractString(map, STREET_ADDRESS, key, false);

        instance.setStreetAddress(value);
    }


    private static void fillLocality(Provider instance, Map<?,?> map, String key)
    {
        // The value of "locality" in the map.
        String value = Helper.extractString(map, LOCALITY, key, false);

        instance.setLocality(value);
    }


    private static void fillRegion(Provider instance, Map<?,?> map, String key)
    {
        // The value of "region" in the map.
        String value = Helper.extractString(map, REGION, key, false);

        instance.setRegion(value);
    }


    private static void fillPostalCode(Provider instance, Map<?,?> map, String key)
    {
        // The value of "postal_code" in the map.
        String value = Helper.extractString(map, POSTAL_CODE, key, false);

        instance.setPostalCode(value);
    }


    private static void fillCountry(Provider instance, Map<?,?> map, String key)
    {
        // The value of "country" in the map.
        String value = Helper.extractString(map, COUNTRY, key, false);

        instance.setCountry(value);
    }
}
