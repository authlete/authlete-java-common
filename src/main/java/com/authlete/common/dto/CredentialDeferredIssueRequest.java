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
 * A request to Authlete's {@code /vci/deferred/issue} API.
 *
 * <p>
 * The Authlete API is supposed to be called by the implementation of the
 * <b>deferred credential endpoint</b>. The endpoint is defined in the "<a href=
 * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 * >OpenID for Verifiable Credential Issuance 1.0</a>" (OID4VCI) specification.
 * </p>
 *
 * <p>
 * The implementation of the deferred credential endpoint is expected to call
 * the following Authlete APIs in the order.
 * </p>
 *
 * <ol>
 * <li>{@code /auth/introspection}
 * <li>{@code /vci/deferred/parse}
 * <li>{@code /vci/deferred/issue}
 * </ol>
 *
 * <p>
 * The {@code /vci/deferred/issue} API is used for one of the following purposes:
 * </p>
 *
 * <ol>
 * <li>To issue a credential
 * <li>To deny the deferred credential request
 * <li>To notify that the requested credential is not ready yet
 * </ol>
 *
 * <h3>Issuing a credential</h3>
 *
 * <p>
 * To issue a credential, the {@code order} request parameter must be set up
 * properly, and both the {@code denied} and {@code order.issuanceDeferred}
 * request parameters must be set to {@code false}.
 * </p>
 *
 * <h3>Denying a deferred credential request</h3>
 *
 * <p>
 * To deny the deferred credential request, the {@code denied} request parameter
 * in the API call must be set to {@code true}. In this case, Authlete prepares
 * a response containing {@code "error":"credential_request_denied"} and returns
 * <code>"action":"{@link CredentialDeferredIssueResponse.Action#BAD_REQUEST BAD_REQUEST}"</code>.
 * </p>
 *
 * <p>
 * Note that the {@code credential_request_denied} error code does not exist in
 * OID4VCI 1.0 ID1. Therefore, you should not call the API with {@code denied}
 * set to {@code true} when the target specification version is "1.0-ID1".
 * </p>
 *
 * <h3>Notifying that the credential is not ready yet</h3>
 *
 * <p>
 * To notify that the requested credential is not ready yet, the
 * {@code order.issuanceDeferred} request parameter in the API call must be
 * set to {@code true}. In this case, Authlete will do one of the following:
 * </p>
 *
 * <ol>
 * <li>
 * Prepare an error response with the {@code issuance_pending} error code for
 * OID4VCI 1.0 ID1.
 * <li>
 * Prepare a successful response containing the {@code transaction_id} and
 * {@code interval} parameters for OID4VCI 1.0 Final or later.
 * </ol>
 *
 * <p>
 * Note that if the Authlete Server version is older than 3.0.25 and the target
 * specification version is "1.0-ID1", you need to manually construct an error
 * response as shown below, without using the {@code /vci/deferred/issue} API.
 * </p>
 *
 * <pre>
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * {
 *   "error": "issuance_pending"
 * }
 * </pre>
 *
 * <h3>Interval handling</h3>
 *
 * <p>
 * If the {@code interval} request parameter in the API call is present and its
 * value is positive, it is used as the value of the {@code interval} response
 * parameter in the deferred credential response. If the {@code interval}
 * request parameter is missing, zero, or negative, Authlete uses the default
 * value.
 * </p>
 *
 * <p>
 * Note that the {@code interval} response parameter does not exist in OID4VCI
 * 1.0 ID1. Therefore, you don't have to care about the {@code interval} request
 * parameter if the target specification version is "1.0-ID1".
 * </p>
 *
 * <h3>Important note</h3>
 *
 * <p>
 * In all cases, the {@code order.requestIdentifier} request parameter must be
 * set properly.
 * </p>
 *
 * @since 3.70
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html">
 *      OpenID for Verifiable Credential Issuance 1.0</a>
 */
public class CredentialDeferredIssueRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The instruction for credential issuance.
     */
    private CredentialIssuanceOrder order;


    /**
     * The flag that indicates whether to deny the deferred credential request.
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
    public CredentialDeferredIssueRequest setOrder(CredentialIssuanceOrder order)
    {
        this.order = order;

        return this;
    }

    /**
     * Get the flag that indicates whether to deny the deferred credential
     * request.
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
     * Set the flag that indicates whether to deny the deferred credential
     * request.
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
    public CredentialDeferredIssueRequest setDenied(boolean denied)
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
     * parameter in the deferred credential response. However, the
     * {@code interval} parameter is included in the deferred credential
     * response only when the following conditions are met:
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
     * parameter in the deferred credential response. However, the
     * {@code interval} parameter is included in the deferred credential
     * response only when the following conditions are met:
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
    public CredentialDeferredIssueRequest setInterval(int interval)
    {
        this.interval = interval;

        return this;
    }
}
