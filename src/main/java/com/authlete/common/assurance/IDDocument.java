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
 * The class that represents {@code id_document}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class IDDocument extends Evidence
{
    private static final long serialVersionUID = 1L;


    private static final String ID_DOCUMENT = "id_document";
    private static final String METHOD      = "method";
    private static final String VERIFIER    = "verifier";
    private static final String TIME        = "time";
    private static final String DOCUMENT    = "document";


    /**
     * The constructor that construct evidence whose type is
     * {@code "id_document"}.
     */
    public IDDocument()
    {
        super(ID_DOCUMENT);
    }


    /**
     * Get the method used to verify this ID document.
     *
     * @return
     *         The method used to verify this ID document.
     */
    public String getMethod()
    {
        return (String)get(METHOD);
    }


    /**
     * Set the method used to verify this ID document.
     *
     * @param method
     *         The method used to verify this ID document.
     *
     * @return
     *         {@code this} object.
     */
    public IDDocument setMethod(String method)
    {
        put(METHOD, method);

        return this;
    }


    /**
     * Check if this object contains {@code "method"}.
     *
     * @return
     *         {@code true} if this object contains {@code "method"}.
     */
    public boolean containsMethod()
    {
        return containsKey(METHOD);
    }


    /**
     * Remove {@code "method"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public Provider removeMethod()
    {
        return (Provider)remove(METHOD);
    }


    /**
     * Get the legal entity that performed the identity verification.
     *
     * @return
     *         The legal entity that performed the identity verification.
     */
    public Verifier getVerifier()
    {
        return (Verifier)get(VERIFIER);
    }


    /**
     * Set the legal entity that performed the identity verification.
     *
     * @param verifier
     *         The legal entity that performed the identity verification.
     *
     * @return
     *         {@code this} object;
     */
    public IDDocument setVerifier(Verifier verifier)
    {
        put(VERIFIER, verifier);

        return this;
    }


    /**
     * Check if this object contains {@code "verifier"}.
     *
     * @return
     *         {@code true} if this object contains {@code "verifier"}.
     */
    public boolean containsVerifier()
    {
        return containsKey(VERIFIER);
    }


    /**
     * Remove {@code "verifier"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public Verifier removeVerifier()
    {
        return (Verifier)remove(VERIFIER);
    }


    /**
     * Get the date when this ID document was verified.
     *
     * @return
     *         The date when this ID document was verified.
     */
    public String getTime()
    {
        return (String)get(TIME);
    }


    /**
     * Set the date when this ID document was verified.
     *
     * @param time
     *         The date when this ID document was verified.
     *
     * @return
     *         {@code this} object.
     */
    public IDDocument setTime(String time)
    {
        put(TIME, time);

        return this;
    }


    /**
     * Check if this object contains {@code "time"}.
     *
     * @return
     *         {@code true} if this object contains {@code "time"}.
     */
    public boolean containsTime()
    {
        return containsKey(TIME);
    }


    /**
     * Remove {@code "time"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeTime()
    {
        return (String)remove(TIME);
    }


    /**
     * Get the ID document used to perform the ID verification.
     *
     * @return
     *         The ID document used to perform the ID verification.
     */
    public Document getDocument()
    {
        return (Document)get(DOCUMENT);
    }


    /**
     * Set the ID document used to perform the ID verification.
     *
     * @param document
     *         The ID document used to perform the ID verification.
     *
     * @return
     *         {@code this} object.
     */
    public IDDocument setDocument(Document document)
    {
        put(DOCUMENT, document);

        return this;
    }


    /**
     * Check if this object contains {@code "document"}.
     *
     * @return
     *         {@code true} if this object contains {@code "document"}.
     */
    public boolean containsDocument()
    {
        return containsKey(DOCUMENT);
    }


    /**
     * Remove {@code "document"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public Document removeDocument()
    {
        return (Document)remove(DOCUMENT);
    }


    /**
     * Create an {@code IDDocument} instance from the given object.
     *
     * @param map
     *         A map that represents {@code "id_document"}.
     *
     * @return
     *         An {@code IDDocument} instance that represents {@code "id_document"}.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static IDDocument extract(Map<?, ?> map) throws IdentityAssuranceException
    {
        IDDocument instance = new IDDocument();

        fill(instance, map, ID_DOCUMENT);

        return instance;
    }


    private static void fill(IDDocument instance, Map<?, ?> map, String key)
    {
        // "method": required
        fillMethod(instance, map, key);

        // "verifier": optional
        fillVerifier(instance, map, key);

        // "time": optional
        fillTime(instance, map, key);

        // "document": required
        fillDocument(instance, map, key);
    }


    private static void fillMethod(IDDocument instance, Map<?, ?> map, String key)
    {
        // The value of "method" in the map.
        String value = Helper.extractString(map, METHOD, key, true);

        instance.setMethod(value);
    }


    private static void fillVerifier(IDDocument instance, Map<?, ?> map, String key)
    {
        // The value of "verifier" in the map.
        Verifier value = Verifier.extract(map, VERIFIER);

        instance.setVerifier(value);
    }


    private static void fillTime(IDDocument instance, Map<?, ?> map, String key)
    {
        // The value of "time" in the map.
        String value = Helper.extractDateTime(map, TIME, key, false);

        instance.setTime(value);
    }


    private static void fillDocument(IDDocument instance, Map<?, ?> map, String key)
    {
        // Ensure "document" is contained.
        Helper.ensureKey(map, DOCUMENT, key);

        Document document = Document.extract(map, DOCUMENT);

        // Ensure "document" is not null.
        Helper.ensureNotNull(document, DOCUMENT);

        instance.setDocument(document);
    }
}
