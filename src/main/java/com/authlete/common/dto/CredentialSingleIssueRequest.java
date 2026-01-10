/*
 * Copyright (C) 2023-2026 Authlete, Inc.
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
 * >OpenID for Verifiable Credential Issuance 1.0</a>" (OID4VCI) specification.
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
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html">
 *      OpenID for Verifiable Credential Issuance 1.0</a>
 */
public class CredentialSingleIssueRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The access token that was presented at the credential endpoint.
     */
    private String accessToken;


    /**
     * The instruction for credential issuance.
     */
    private CredentialIssuanceOrder order;


    /**
     * The flag that indicates whether to deny the credential request.
     *
     * @since 4.35
     * @since Authlete 3.0.25
     */
    private boolean denied;


    /**
     * The minimum amount of time in seconds that the Wallet SHOULD wait after
     * receiving the response before sending a new request to the Deferred
     * Credential Endpoint.
     *
     * @since 4.35
     * @since Authlete 3.0.25
     */
    private int interval;


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


    /**
     * Get the flag that indicates whether to deny the credential request.
     *
     * <p>
     * If this parameter is set to {@code true}, the response content prepared
     * by Authlete will contain {@code "error":"credential_request_denied"}
     * like below.
     * </p>
     *
     * <pre>
     * {
     *     "error": "credential_request_denied"
     * }
     * </pre>
     *
     * <p>
     * Note that the {@code credential_request_denied} error code does not
     * exist in OID4VCI 1.0 ID1.
     * </p>
     *
     * @return
     *         {@code true} for generating a response content containing
     *         {@code "error":"credential_request_denied"}.
     *
     * @since 4.35
     * @since Authlete 3.0.25
     */
    public boolean isDenied()
    {
        return denied;
    }


    /**
     * Set the flag that indicates whether to deny the credential request.
     *
     * <p>
     * If this parameter is set to {@code true}, the response content prepared
     * by Authlete will contain {@code "error":"credential_request_denied"}
     * like below.
     * </p>
     *
     * <pre>
     * {
     *     "error": "credential_request_denied"
     * }
     * </pre>
     *
     * <p>
     * Note that the {@code credential_request_denied} error code does not
     * exist in OID4VCI 1.0 ID1.
     * </p>
     *
     * @param denied
     *         {@code true} for generating a response content containing
     *         {@code "error":"credential_request_denied"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.35
     * @since Authlete 3.0.25
     */
    public CredentialSingleIssueRequest setDenied(boolean denied)
    {
        this.denied = denied;

        return this;
    }


    /**
     * Get the minimum amount of time in seconds that the Wallet SHOULD wait
     * after receiving the response before sending a new request to the
     * Deferred Credential Endpoint.
     *
     * <p>
     * The value of this parameter is used as the value of the {@code interval}
     * parameter in the credential response. However, the {@code interval}
     * parameter is included in the credential response only when the following
     * conditions are met:
     * </p>
     *
     * <ul>
     * <li>
     * The value of the {@code order.issuanceDeferred} request parameter is
     * {@code true}.
     * </li>
     *
     * <li>
     * The version of the OID4VCI specification is 1.0-Final or later (that is,
     * the {@code oid4vciVersion} property of {@link Service} is set and its
     * value is not {@code "1.0-ID1"}).
     * </li>
     * </ul>
     *
     * @return
     *         The minimum amount of time in seconds that the Wallet SHOULD
     *         wait after receiving the response before sending a new request
     *         to the Deferred Credential Endpoint.
     *
     * @since 4.35
     * @since Authlete 3.0.25
     */
    public int getInterval()
    {
        return interval;
    }


    /**
     * Set the minimum amount of time in seconds that the Wallet SHOULD wait
     * after receiving the response before sending a new request to the
     * Deferred Credential Endpoint.
     *
     * <p>
     * The value of this parameter is used as the value of the {@code interval}
     * parameter in the credential response. However, the {@code interval}
     * parameter is included in the credential response only when the following
     * conditions are met:
     * </p>
     *
     * <ul>
     * <li>
     * The value of the {@code order.issuanceDeferred} request parameter is
     * {@code true}.
     * </li>
     *
     * <li>
     * The version of the OID4VCI specification is 1.0-Final or later (that is,
     * the {@code oid4vciVersion} property of {@link Service} is set and its
     * value is not {@code "1.0-ID1"}).
     * </li>
     * </ul>
     *
     * @param interval
     *         The minimum amount of time in seconds that the Wallet SHOULD
     *         wait after receiving the response before sending a new request
     *         to the Deferred Credential Endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.35
     * @since Authlete 3.0.25
     */
    public CredentialSingleIssueRequest setInterval(int interval)
    {
        this.interval = interval;

        return this;
    }
}
