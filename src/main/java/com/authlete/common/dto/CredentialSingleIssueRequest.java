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
 * A request to Authlete's {@code /vci/single/issue} API.
 *
 * <p>
 * The Authlete API is supposed to be called by the implementation of the
 * <b>credential endpoint</b>. The endpoint is defined in the "<a href=
 * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 * >OpenID for Verifiable Credential Issuance</a>" (OID4VCI) specification.
 * </p>
 *
 * <p>
 * The implementation of the credential endpoint is expected to call the
 * following Authlete APIs in the order.
 * </p>
 *
 * <ol>
 * <li>{@code /auth/introspection}
 * <li>{@code /vci/single/parse}
 * <li>{@code /vci/single/issue}
 * </ol>
 *
 * <p>
 * The role of the {@code /vci/single/issue} API is to issue a credential or
 * a transaction ID and to prepare a response that should be returned from
 * the credential endpoint.
 * </p>
 *
 * @since 3.67
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialSingleIssueRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The access token that was presented at the credential endpoint.
     */
    private String accessToken;


    /**
     * The instruction for credential issuance.
     */
    private CredentialIssuanceOrder order;


    /**
     * Get the access token that was presented at the credential endpoint.
     *
     * @return
     *         The access token that was presented at the credential endpoint.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token that was presented at the credential endpoint.
     *
     * @param accessToken
     *         The access token that was presented at the credential endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleIssueRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the credential order that provides an instruction for issuing a
     * credential.
     *
     * @return
     *         The instruction for credential issuance.
     */
    public CredentialIssuanceOrder getOrder()
    {
        return order;
    }


    /**
     * Set the credential order that provides an instruction for issuing a
     * credential.
     *
     * @param order
     *         The instruction for credential issuance.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleIssueRequest setOrder(CredentialIssuanceOrder order)
    {
        this.order = order;

        return this;
    }
}
