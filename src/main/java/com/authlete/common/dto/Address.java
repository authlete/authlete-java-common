/*
 * Copyright (C) 2016 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Address claim that represents a physical mailing address.
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AddressClaim"
 *      >OpenID Connect Core 1.0, 5.1.1. Address Claim</a>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.24
 */
public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;


    // Some fields are intentionally in snake case.
    private String formatted;
    private String street_address;
    private String locality;
    private String region;
    private String postal_code;
    private String country;


    /**
     * Get the full mailing address, formatted for display or use on a mailing label.
     *
     * @return
     *         The full mailing address.
     */
    public String getFormatted()
    {
        return formatted;
    }


    /**
     * Set the full mailing address, formatted for display or use on a mailing label.
     *
     * @param formatted
     *         The full mailing address.
     *
     * @return
     *         {@code this} object.
     */
    public Address setFormatted(String formatted)
    {
        this.formatted = formatted;

        return this;
    }


    /**
     * Get the full street address component, which MAY include house number,
     * street name, Post Office Box, and multi-line extended street address
     * information.
     *
     * @return
     *         The full street address.
     */
    public String getStreetAddress()
    {
        return street_address;
    }


    /**
     * Set the full street address component, which MAY include house number,
     * street name, Post Office Box, and multi-line extended street address
     * information.
     *
     * @param streetAddress
     *          The full street address.
     *
     * @return
     *         {@code this} object.
     */
    public Address setStreetAddress(String streetAddress)
    {
        this.street_address = streetAddress;

        return this;
    }


    /**
     * Get the city or locality component.
     *
     * @return
     *         The city or locality.
     */
    public String getLocality()
    {
        return locality;
    }


    /**
     * Set the city or locality component.
     *
     * @param locality
     *         The city or locality.
     *
     * @return
     *         {@code this} object.
     */
    public Address setLocality(String locality)
    {
        this.locality = locality;

        return this;
    }


    /**
     * Get the state, province, prefecture, or region component.
     *
     * @return
     *         The state, province, prefecture, or region.
     */
    public String getRegion()
    {
        return region;
    }


    /**
     * Set the state, province, prefecture, or region component.
     *
     * @param region
     *         The state, province, prefecture, or region.
     *
     * @return
     *         {@code this} object.
     */
    public Address setRegion(String region)
    {
        this.region = region;

        return this;
    }


    /**
     * Get the zip code or postal code component.
     *
     * @return
     *         The zip code or postal code.
     */
    public String getPostalCode()
    {
        return postal_code;
    }


    /**
     * Set the zip code or postal code component.
     *
     * @param postalCode
     *         The zip code or postal code.
     *
     * @return
     *         {@code this} object.
     */
    public Address setPostaCode(String postalCode)
    {
        this.postal_code = postalCode;

        return this;
    }


    /**
     * Get the country name component.
     *
     * @return
     *         The country name.
     */
    public String getCountry()
    {
        return country;
    }


    /**
     * Set the country name component.
     *
     * @param country
     *         The country name.
     *
     * @return
     *         {@code this} object.
     */
    public Address setCountry(String country)
    {
        this.country = country;

        return this;
    }
}
