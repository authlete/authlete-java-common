/*
 * Copyright (C) 2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's {@code /vci/offer/info} API.
 *
 * <p>
 * The API is used to get information about a <b>credential offer</b>.
 * </p>
 *
 * @since 3.59
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialOfferInfoRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The identifier of the credential offer.
     */
    private String identifier;


    /**
     * Get the identifier of the credential offer.
     *
     * <p>
     * The identifier is one assigned by Authlete's {@code /vci/offer/create}
     * API. The value is a base64url string with 256-bit entropy consisting of
     * 43 characters.
     * </p>
     *
     * <p>
     * This property is mandatory.
     * </p>
     *
     * @return
     *         The identifier of the credential offer.
     */
    public String getIdentifier()
    {
        return identifier;
    }


    /**
     * Set the identifier of the credential offer.
     *
     * <p>
     * The identifier is one assigned by Authlete's {@code /vci/offer/create}
     * API. The value is a base64url string with 256-bit entropy consisting of
     * 43 characters.
     * </p>
     *
     * <p>
     * This property is mandatory.
     * </p>
     *
     * @param identifier
     *         The identifier of the credential offer.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfoRequest setIdentifier(String identifier)
    {
        this.identifier = identifier;

        return this;
    }
}
