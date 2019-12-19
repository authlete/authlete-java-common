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
 * The class that represents {@code qes}.
 *
 * <p>
 * QES is verification based on an eIDAS Qualified Electronic Signature.
 * </p>
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class QES extends Evidence
{
    private static final long serialVersionUID = 1L;


    private static final String QES           = "qes";
    private static final String ISSUER        = "issuer";
    private static final String SERIAL_NUMBER = "serial_number";
    private static final String CREATED_AT    = "created_at";


    /**
     * The constructor that construct evidence whose type is
     * {@code "qes"}.
     */
    public QES()
    {
        super(QES);
    }


    /**
     * Get the certification authority that issued the signer's certificate.
     *
     * @return
     *         The certification authority.
     */
    public String getIssuer()
    {
        return (String)get(ISSUER);
    }


    /**
     * Set the certification authority that issued the signer's certificate.
     *
     * @param issuer
     *         The certification authority.
     *
     * @return
     *         {@code this} object.
     */
    public QES setIssuer(String issuer)
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
    public String removeIssuer()
    {
        return (String)remove(ISSUER);
    }


    /**
     * Get the serial number of the certificate.
     *
     * @return
     *         The serial number of the certificate.
     */
    public String getSerialNumber()
    {
        return (String)get(SERIAL_NUMBER);
    }


    /**
     * Set the serial number of the certificate.
     *
     * @param serialNumber
     *         The serial number of the certificate.
     *
     * @return
     *         {@code this} object.
     */
    public QES setSerialNumber(String serialNumber)
    {
        put(SERIAL_NUMBER, serialNumber);

        return this;
    }


    /**
     * Check if this object contains {@code "serial_number"}.
     *
     * @return
     *         {@code true} if this object contains {@code "serial_number"}.
     */
    public boolean containsSerialNumber()
    {
        return containsKey(SERIAL_NUMBER);
    }


    /**
     * Remove {@code "serial_number"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeSerialNumber()
    {
        return (String)remove(SERIAL_NUMBER);
    }


    /**
     * Get the time when the signature was created.
     *
     * @return
     *         The time when the signature was created.
     */
    public String getCreatedAt()
    {
        return (String)get(CREATED_AT);
    }


    /**
     * Set the time when the signature was created.
     *
     * @param createdAt
     *         The time when the signature was created.
     *
     * @return
     *         {@code this} object.
     */
    public QES setCreatedAt(String createdAt)
    {
        put(CREATED_AT, createdAt);

        return this;
    }


    /**
     * Check if this object contains {@code "created_at"}.
     *
     * @return
     *         {@code true} if this object contains {@code "created_at"}.
     */
    public boolean containsCreatedAt()
    {
        return containsKey(CREATED_AT);
    }


    /**
     * Remove {@code "created_at"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeCreatedAt()
    {
        return (String)remove(CREATED_AT);
    }


    /**
     * Create a {@code QES} instance from the given object.
     *
     * @param map
     *         A map that represents {@code "qes"}.
     *
     * @return
     *         A {@code QES} instance that represents {@code "qes"}.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static QES extract(Map<?,?> map) throws IdentityAssuranceException
    {
        QES instance = new QES();

        fill(instance, map, "qes");

        return instance;
    }


    private static void fill(QES instance, Map<?,?> map, String key)
    {
        // "issuer": required
        fillIssuer(instance, map, key);

        // "serial_number": required
        fillSerialNumber(instance, map, key);

        // "created_at": required
        fillCreatedAt(instance, map, key);
    }


    private static void fillIssuer(QES instance, Map<?,?> map, String key)
    {
        // The value of "issuer" in the map.
        String value = Helper.extractString(map, ISSUER, key, true);

        instance.setIssuer(value);
    }


    private static void fillSerialNumber(QES instance, Map<?,?> map, String key)
    {
        // The value of "serial_number" in the map.
        String value = Helper.extractString(map, SERIAL_NUMBER, key, true);

        instance.setSerialNumber(value);
    }


    private static void fillCreatedAt(QES instance, Map<?,?> map, String key)
    {
        // The value of "created_at" in the map.
        String value = Helper.extractTime(map, CREATED_AT, key, true);

        instance.setCreatedAt(value);
    }
}
