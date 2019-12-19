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


import java.util.Map;


/**
 * The class that represents {@code utility_bill}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class UtilityBill extends Evidence
{
    private static final long serialVersionUID = 1L;


    private static final String UTILITY_BILL = "utility_bill";
    private static final String PROVIDER     = "provider";
    private static final String DATE         = "date";


    /**
     * The constructor that construct evidence whose type is
     * {@code "utility_bill"}.
     */
    public UtilityBill()
    {
        super(UTILITY_BILL);
    }


    /**
     * Get the provider that issued the utility bill.
     *
     * @return
     *         The provider that issued the utility bill.
     */
    public Provider getProvider()
    {
        return (Provider)get(PROVIDER);
    }


    /**
     * Set the provider that issued the utility bill.
     *
     * @param provider
     *         The provider that issued the utility bill.
     *
     * @return
     *         {@code this} object.
     */
    public UtilityBill setProvider(Provider provider)
    {
        put(PROVIDER, provider);

        return this;
    }


    /**
     * Check if this object contains {@code "provider"}.
     *
     * @return
     *         {@code true} if this object contains {@code "provider"}.
     */
    public boolean containsProvider()
    {
        return containsKey(PROVIDER);
    }


    /**
     * Remove {@code "provider"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public Provider removeProvider()
    {
        return (Provider)remove(PROVIDER);
    }


    /**
     * Get the date when the utility bill was issued.
     *
     * @return
     *         The date when the utility bill was issued.
     */
    public String getDate()
    {
        return (String)get(DATE);
    }


    /**
     * Set the date when the utility bill was issued.
     *
     * @param date
     *         The date when the utility bill was issued.
     *
     * @return
     *         {@code this} object.
     */
    public UtilityBill setDate(String date)
    {
        put(DATE, date);

        return this;
    }


    /**
     * Check if this object contains {@code "date"}.
     *
     * @return
     *         {@code true} if this object contains {@code "date"}.
     */
    public boolean containsDate()
    {
        return containsKey(DATE);
    }


    /**
     * Remove {@code "date"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeDate()
    {
        return (String)remove(DATE);
    }


    /**
     * Create a {@code UtilityBill} instance from the given object.
     *
     * @param map
     *         A map that represents {@code "utility_bill"}.
     *
     * @return
     *         A {@code UtilityBill} instance that represents {@code "utility_bill"}.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static UtilityBill extract(Map<?,?> map) throws IdentityAssuranceException
    {
        UtilityBill instance = new UtilityBill();

        fill(instance, map, UTILITY_BILL);

        return instance;
    }


    private static void fill(UtilityBill instance, Map<?,?> map, String key)
    {
        // "provider": required
        fillProvider(instance, map, key);

        // "date": required
        fillDate(instance, map, key);
    }


    private static void fillProvider(UtilityBill instance, Map<?,?> map, String key)
    {
        // Ensure "provider" is contained.
        Helper.ensureKey(map, PROVIDER, key);

        Provider provider = Provider.extract(map, PROVIDER);

        // Ensure "provider" is not null.
        Helper.ensureNotNull(provider, PROVIDER);

        instance.setProvider(provider);
    }


    private static void fillDate(UtilityBill instance, Map<?,?> map, String key)
    {
        // The value of "date" in the map.
        String value = Helper.extractDate(map, DATE, key, true);

        instance.setDate(value);
    }
}
