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
 * The class that represents {@code id_document/document}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class Document extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;


    private static final String TYPE             = "type";
    private static final String NUMBER           = "number";
    private static final String ISSUER           = "issuer";
    private static final String DATE_OF_ISSUANCE = "date_of_issuance";
    private static final String DATE_OF_EXPIRY   = "date_of_expiry";


    /**
     * Get the type of the document.
     *
     * @return
     *         The type of the document.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.2"
     *      >OpenID Connect for Identity Assurance 1.0, 11.2. Identity Documents</a>
     */
    public String getType()
    {
        return (String)get(TYPE);
    }


    /**
     * Set the type of the document.
     *
     * @param type
     *         The type of the document.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.2"
     *      >OpenID Connect for Identity Assurance 1.0, 11.2. Identity Documents</a>
     */
    public Document setType(String type)
    {
        put(TYPE, type);

        return this;
    }


    /**
     * Check if this object contains {@code "type"}.
     *
     * @return
     *         {@code true} if this object contains {@code "type"}.
     */
    public boolean containsType()
    {
        return containsKey(TYPE);
    }


    /**
     * Remove {@code "type"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeType()
    {
        return (String)remove(TYPE);
    }


    /**
     * Get the number of the document.
     *
     * @return
     *         The number of the document.
     */
    public String getNumber()
    {
        return (String)get(NUMBER);
    }


    /**
     * Set the number of the document.
     *
     * @param number
     *         The number of the document.
     *
     * @return
     *         {@code this} object.
     */
    public Document setNumber(String number)
    {
        put(NUMBER, number);

        return this;
    }


    /**
     * Check if this object contains {@code "number"}.
     *
     * @return
     *         {@code true} if this object contains {@code "number"}.
     */
    public boolean containsNumber()
    {
        return containsKey(NUMBER);
    }


    /**
     * Remove {@code "number"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeNumber()
    {
        return (String)remove(NUMBER);
    }


    /**
     * Get the issuer of the document.
     *
     * @return
     *         The issuer of the document.
     */
    public Issuer getIssuer()
    {
        return (Issuer)get(ISSUER);
    }


    /**
     * Set the issuer of the document.
     *
     * @param issuer
     *         The issuer of the document.
     *
     * @return
     *         {@code this} object.
     */
    public Document setIssuer(Issuer issuer)
    {
        put(ISSUER, issuer);

        return this;
    }


    /**
     * Check if this object contains {@code "issuer"}.
     *
     * @return
     *         {@code true} if this object contains {@code "issuer"}.
     */
    public boolean containsIssuer()
    {
        return containsKey(ISSUER);
    }


    /**
     * Remove {@code "issuer"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public Issuer removeIssuer()
    {
        return (Issuer)remove(ISSUER);
    }


    /**
     * Get the date of issuance of the document.
     *
     * @return
     *         The date of issuance of the document.
     */
    public String getDateOfIssuance()
    {
        return (String)get(DATE_OF_ISSUANCE);
    }


    /**
     * Set the date of issuance of the document.
     *
     * @param date
     *         The date of issuance of the document.
     *
     * @return
     *         {@code this} object.
     */
    public Document setDateOfIssuance(String date)
    {
        put(DATE_OF_ISSUANCE, date);

        return this;
    }


    /**
     * Check if this object contains {@code "date_of_issuance"}.
     *
     * @return
     *         {@code true} if this object contains {@code "date_of_issuance"}.
     */
    public boolean containsDateOfIssuance()
    {
        return containsKey(DATE_OF_ISSUANCE);
    }


    /**
     * Remove {@code "date_of_issuance"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeDateOfIssuance()
    {
        return (String)remove(DATE_OF_ISSUANCE);
    }


    /**
     * Get the date of expiry of the document.
     *
     * @return
     *         The date of expiry of the document.
     */
    public String getDateOfExpiry()
    {
        return (String)get(DATE_OF_EXPIRY);
    }


    /**
     * Set the date of expiry of the document.
     *
     * @param date
     *         The date of expiry of the document.
     *
     * @return
     *         {@code this} object.
     */
    public Document setDateOfExpiry(String date)
    {
        put(DATE_OF_EXPIRY, date);

        return this;
    }


    /**
     * Check if this object contains {@code "date_of_expiry"}.
     *
     * @return
     *         {@code true} if this object contains {@code "date_of_expiry"}.
     */
    public boolean containsDateOfExpiry()
    {
        return containsKey(DATE_OF_EXPIRY);
    }


    /**
     * Remove {@code "date_of_expiry"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeDateOfExpir()
    {
        return (String)remove(DATE_OF_EXPIRY);
    }


    /**
     * Create a {@code Document} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "document"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "document"}.
     *
     * @return
     *         A {@code Document} instance that represents {@code "document"}.
     *         If the map does not contain the given key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static Document extract(Map<?,?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        Document instance = new Document();

        fill(instance, object, key);

        return instance;
    }


    private static void fill(Document instance, Object object, String key)
    {
        Map<?,?> map = Helper.ensureMap(object, key);

        // "type": optional
        fillType(instance, map, key);

        // "number": optional
        fillNumber(instance, map, key);

        // "issuer": optional
        fillIssuer(instance, map, key);

        // "date_of_issuance": optional
        fillDateOfIssuance(instance, map, key);

        // "date_of_expiry": optional
        fillDateOfExpiry(instance, map, key);
    }


    private static void fillType(Document instance, Map<?,?> map, String key)
    {
        // The value of "type" in the map.
        String value = Helper.extractString(map, TYPE, key, false);

        instance.setType(value);
    }


    private static void fillNumber(Document instance, Map<?,?> map, String key)
    {
        // The value of "number" in the map.
        String value = Helper.extractString(map, NUMBER, key, false);

        instance.setNumber(value);
    }


    private static void fillIssuer(Document instance, Map<?,?> map, String key)
    {
        // The value of "issuer" in the map.
        Issuer value = Issuer.extract(map, ISSUER);

        instance.setIssuer(value);
    }


    private static void fillDateOfIssuance(Document instance, Map<?,?> map, String key)
    {
        // The value of "date_of_issuance" in the map.
        String value = Helper.extractDate(map, DATE_OF_ISSUANCE, key, false);

        instance.setDateOfIssuance(value);
    }


    private static void fillDateOfExpiry(Document instance, Map<?,?> map, String key)
    {
        // The value of "date_of_expiry" in the map.
        String value = Helper.extractDate(map, DATE_OF_EXPIRY, key, false);

        instance.setDateOfExpiry(value);
    }
}
