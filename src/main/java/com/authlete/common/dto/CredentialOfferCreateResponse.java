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


/**
 * Response from Authlete's {@code /vci/offer/create} API.
 *
 * <p>
 * The API is used to create a <b>credential offer</b>.
 * </p>
 *
 * @since 3.59
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialOfferCreateResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The result of the {@code /vci/offer/create} API call.
     */
    public enum Action
    {
        /**
         * The {@code /vci/offer/create} API has created a credential offer
         * successfully.
         */
        CREATED,


        /**
         * The feature of Verifiable Credentials is not enabled in the service
         * configuration.
         */
        FORBIDDEN,


        /**
         * The API call was wrong. For example, the {@code subject} request
         * parameter was missing.
         */
        CALLER_ERROR,


        /**
         * An error occurred on Authlete side.
         */
        AUTHLETE_ERROR,
    }


    /**
     * The result of the {@code /vci/offer/create} API call.
     * @since Authlete 3.0
     */
    private Action action;


    /**
     * Information about the credential offer.
     * @since Authlete 3.0
     */
    private CredentialOfferInfo info;


    /**
     * Get the result of the {@code /vci/offer/create} API call.
     *
     * @return
     *         The result of the API call.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the result of the {@code /vci/offer/create} API call.
     *
     * @param action
     *         The result of the API call.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get information about the credential offer.
     *
     * @return
     *         Information about the credential offer.
     */
    public CredentialOfferInfo getInfo()
    {
        return info;
    }


    /**
     * Set information about the credential offer.
     *
     * @param info
     *         Information about the credential offer.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateResponse setInfo(CredentialOfferInfo info)
    {
        this.info = info;

        return this;
    }
}
