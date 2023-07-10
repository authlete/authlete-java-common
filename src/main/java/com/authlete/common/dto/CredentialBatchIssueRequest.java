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
 * A request to Authlete's {@code /vci/batch/issue} API.
 *
 * <p>
 * The Authlete API is supposed to be called by the implementation of the
 * <b>batch credential endpoint</b>. The endpoint is defined in the "<a href=
 * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 * >OpenID for Verifiable Credential Issuance</a>" (OID4VCI) specification.
 * </p>
 *
 * <p>
 * The implementation of the batch credential endpoint is expected to call
 * the following Authlete APIs in the order.
 * </p>
 *
 * <ol>
 * <li>{@code /auth/introspection}
 * <li>{@code /vci/batch/parse}
 * <li>{@code /vci/batch/issue}
 * </ol>
 *
 * <p>
 * The role of the {@code /vci/batch/issue} API is to issue credentials and/or
 * transaction IDs and to prepare a response that should be returned from th
 * batch credential endpoint.
 * </p>
 *
 * @since 3.71
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialBatchIssueRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The access token that was presented at the batch credential endpoint.
     */
    private String accessToken;


    /**
     * The instructions for issuance of credentials and/or transaction IDs.
     */
    private CredentialIssuanceOrder[] orders;


    /**
     * Get the access token that was presented at the batch credential endpoint.
     *
     * @return
     *         The access token that was presented at the batch credential endpoint.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token that was presented at the batch credential endpoint.
     *
     * @param accessToken
     *         The access token that was presented at the batch credential endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialBatchIssueRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the credential orders that provide instructions for issuance of
     * credentials and/or transaction IDs.
     *
     * @return
     *         The instructions for issuance of credentials and/or transaction IDs.
     */
    public CredentialIssuanceOrder[] getOrders()
    {
        return orders;
    }


    /**
     * Set the credential orders that provide instructions for issuance of
     * credentials and/or transaction IDs.
     *
     * @param order
     *         The instructions for issuance of credentials and/or transaction IDs.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialBatchIssueRequest setOrders(CredentialIssuanceOrder[] orders)
    {
        this.orders = orders;

        return this;
    }
}
