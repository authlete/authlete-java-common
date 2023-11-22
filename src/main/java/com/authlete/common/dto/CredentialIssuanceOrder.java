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
 * Instruction for credential issuance.
 *
 * <p>
 * Authlete APIs that may issue one or more verifiable credentials expect that
 * an API call contains one or more instructions for the credential issuance.
 * This class represents such instructions.
 * </p>
 *
 * <h3>The {@code requestIdentifier} Parameter</h3>
 *
 * <blockquote>
 * <p>
 * This mandatory parameter must be the identifier that has been assigned
 * to the credential request by the preceding Authlete API.
 * </p>
 *
 * <p>
 * The {@code /vci/single/parse} API parses a credential request that the
 * implementation of the credential endpoint receives. The response from
 * the API ({@link CredentialSingleParseResponse}) includes an identifier
 * ({@code info.identifier}) assigned to the credential request. The
 * identifier has to be set to this {@code requestIdentifier} parameter.
 * </p>
 *
 * <p>
 * Likewise, the {@code /vci/batch/parse} API parses a batch credential
 * request that the implementation of the batch credential endpoint
 * receives. The response from the API includes identifiers assigned to
 * each credential request in the batch credential request. A request to
 * the {@code /vci/batch/issue} API is expected to contain multiple
 * credential orders. Each order is represented by this class and its
 * {@code requestIdentifier} parameter should hold an identifier that was
 * contained in the response from the {@code /vci/batch/parse} API.
 * </p>
 *
 * <p>
 * The {@code /vci/deferred/parse} API parses a deferred credential request
 * that the implementation of the deferred credential endpoint receives.
 * The API returns information about a credential request
 * ({@link CredentialRequestInfo}) that is tied to the transaction ID in
 * the deferred credential request. The identifier in the information should
 * be set to this {@code requestIdentifier} parameter when the
 * {@code /vci/deferred/issue} API is called. FYI: The Authlete
 * implementation uses the identifier as a transaction ID, so the value of
 * the transaction ID in the deferred credential request is equal to the
 * identifier in the information returned from the {@code /vci/deferred/parse}
 * API.
 * </p>
 * </blockquote>
 *
 * <h3>The {@code credentialPayload} Parameter</h3>
 *
 * <blockquote>
 * <p>
 * This parameter is an additional content that will be added to the payload
 * of a credential to be issued. The format of this parameter must be a JSON
 * object if present. The necessity and content of this parameter depend on
 * the credential format.
 * </p>
 *
 * <h4>The {@code vc+sd-jwt} Format</h4>
 *
 * <p>
 * When the credential format is "{@code vc+sd-jwt}", the {@code credentialPayload}
 * parameter is mandatory. It must contain at least the "{@code vct}" claim
 * whose value is a string, and typically it is expected to contain some claims
 * about the user such as the "{@code sub}" claim and the "{@code family_name}"
 * claim.
 * </p>
 *
 * <pre>
 * {
 *   "vct": "Diploma",
 *   "sub": "79301273",
 *   "family_name": "Kawasaki"
 * }
 * </pre>
 *
 * <p>
 * The following claims are added by Authlete even if they are not included in
 * the {@code credentialPayload} parameter.
 * </p>
 *
 * <ul>
 * <li>{@code iss}
 * <li>{@code iat}
 * <li>{@code exp} (only when the credential has an expiration time.)
 * <li>{@code cnf} (only when the credential request included a key proof.)
 * </ul>
 *
 * <p>
 * Claims in the payload will be made <b>selectively-disclosable</b> automatically
 * in the way that conforms to the "<a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-selective-disclosure-jwt/"
 * >Selective Disclosure for JWTs (SD-JWT)</a>" specification. Note that, however,
 * the following claims appear in the payload as they are without being made
 * selectively-disclosable.
 * </p>
 *
 * <ul>
 * <li>{@code iss}
 * <li>{@code iat}
 * <li>{@code nbf}
 * <li>{@code exp}
 * <li>{@code cnf}
 * <li>{@code type}
 * <li>{@code status}
 * </ul>
 *
 * <p>
 * The current Authlete implementation makes leaf elements selectively-disclosable.
 * On the other hand, names of claims that have a nested structure are not hidden.
 * For example, "{@code country}" and "{@code locality}" in the "{@code address}"
 * object become selectively-disclosable, but the name of the object, i.e.,
 * "{@code address}", will appear in the payload like below.
 * </p>
 *
 * <pre>
 * "address": {
 *   "_sd": [
 *     "G_FeM1D-U3tDJcHB7pwTNEElLal9FE9PUs0klHgeM1c",
 *     "KlG6HEM6XWbymEJDfyDY4klJkQQ9iTuNG0LQXnE9mQ0",
 *     "X96Emv4S9uzFUGkU8MmOlFzUwEtDNeT-ToXw3Fx9AfI",
 *     "ffPGyxFBnNA1r60g2f796Hqq3dBGtaOogpnIBgRGdyY"
 *   ]
 * }
 * </pre>
 *
 * <p>
 * Authlete may provide an advanced mechanism to control the behavior in the future.
 * </p>
 * </blockquote>
 *
 * <h3>The {@code issuanceDeferred} Parameter</h3>
 *
 * <blockquote>
 * <p>
 * This boolean parameter indicates whether to defer credential issuance.
 * </p>
 *
 * <p>
 * When the value of this parameter is {@code true}, a transaction ID is issued
 * instead of a credential. That is, the response from the credential issuer
 * will contain the "{@code transaction_id}" parameter instead of the
 * "{@code credential}" parameter.
 * </p>
 *
 * <p>
 * When {@code true}, other parameters than {@code requestIdentifier} will not
 * be referenced.
 * </p>
 * </blockquote>
 *
 * <h3>The {@code credentialDuration} Parameter</h3>
 *
 * <blockquote>
 * <p>
 * This optional parameter specifies the duration of the credential in seconds.
 * </p>
 *
 * <p>
 * The value of this parameter and the value of the "{@code credentialDuration}"
 * property ({@link Service#getCredentialDuration() credentialDuration}) of the
 * service are used as input to determine the final duration of the credential.
 * The table below indicates how the duration is determined.
 * </p>
 *
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *   <tr bgcolor="orange">
 *     <th>Request Parameter</th>
 *     <th>Service Property</th>
 *     <th>Result</th>
 *   </tr>
 *   <tr>
 *     <td>0 or omitted</td>
 *     <td>0</td>
 *     <td>The credential won't expire.</td>
 *   </tr>
 *   <tr>
 *     <td>0 or omitted</td>
 *     <td>positive number</td>
 *     <td>The value of the service property is used as the duration.</td>
 *   </tr>
 *   <tr>
 *     <td>positive number</td>
 *     <td>any</td>
 *     <td>The value of the request parameter is used as the duration.</td>
 *   </tr>
 *   <tr>
 *     <td>negative number</td>
 *     <td>any</td>
 *     <td>The credential won't expire.</td>
 *   </tr>
 * </table>
 * </blockquote>
 *
 * <h3>The {@code signingKeyId} Parameter</h3>
 *
 * <blockquote>
 * <p>
 * This optional parameter specifies the key ID of the private key that should
 * be used for signing the credential.
 * </p>
 *
 * <p>
 * The key ID should be found in the JWK Set of the credential issuer, which
 * is the content of the {@code credentialJwks} property
 * ({@link Service#getCredentialJwks() credentialJwks}) of the service.
 * </p>
 *
 * <p>
 * If this parameter is omitted, Authlete will automatically select one private
 * key from the JWK Set.
 * </p>
 * </blockquote>
 *
 * @since 3.67
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-selective-disclosure-jwt/"
 *      >Selective Disclosure for JWTs (SD-JWT)</a>
 */
public class CredentialIssuanceOrder implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The identifier of a credential request.
     */
    private String requestIdentifier;


    /**
     * The additional payload that will be added into a credential to be issued.
     */
    private String credentialPayload;


    /**
     * The flag indicating whether to defer credential issuance.
     */
    private boolean issuanceDeferred;


    /**
     * The duration of a credential to be issued.
     */
    private long credentialDuration;


    /**
     * The key ID of a private key that should be used for signing a credential
     * to be issued.
     */
    private String signingKeyId;


    /**
     * Get the identifier that has been assigned to the credential request by
     * the preceding Authlete API.
     *
     * @return
     *         The identifier of a credential request.
     */
    public String getRequestIdentifier()
    {
        return requestIdentifier;
    }


    /**
     * Set the identifier that has been assigned to the credential request by
     * the preceding Authlete API.
     *
     * @param identifier
     *         The identifier of a credential request.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuanceOrder setRequestIdentifier(String identifier)
    {
        this.requestIdentifier = identifier;

        return this;
    }


    /**
     * Get the additional payload that will be added into a credential to be
     * issued.
     *
     * @return
     *         The additional payload of a credential.
     */
    public String getCredentialPayload()
    {
        return credentialPayload;
    }


    /**
     * Set the additional payload that will be added into a credential to be
     * issued.
     *
     * @param payload
     *         The additional payload of a credential.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuanceOrder setCredentialPayload(String payload)
    {
        this.credentialPayload = payload;

        return this;
    }


    /**
     * Get the flag indicating whether to defer credential issuance.
     *
     * @return
     *         {@code true} if credential issuance should be deferred.
     */
    public boolean isIssuanceDeferred()
    {
        return issuanceDeferred;
    }


    /**
     * Set the flag indicating whether to defer credential issuance.
     *
     * @param deferred
     *         {@code true} to defer credential issuance.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuanceOrder setIssuanceDeferred(boolean deferred)
    {
        this.issuanceDeferred = deferred;

        return this;
    }


    /**
     * Get the duration of the credential in seconds.
     *
     * <p>
     * If the value of this parameter is a positive number, the value is used
     * as the duration. If the value is 0, the default duration of the service
     * ({@link Service#getCredentialDuration()}) is used. If the value is a
     * negative number, the credential will not have an expiration time.
     * </p>
     *
     * @return
     *         The duration of the credential in seconds.
     */
    public long getCredentialDuration()
    {
        return credentialDuration;
    }


    /**
     * Set the duration of the credential in seconds.
     *
     * <p>
     * If the value of this parameter is a positive number, the value is used
     * as the duration. If the value is 0, the default duration of the service
     * ({@link Service#getCredentialDuration()}) is used. If the value is a
     * negative number, the credential will not have an expiration time.
     * </p>
     *
     * @param duration
     *         The duration of the credential in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuanceOrder setCredentialDuration(long duration)
    {
        this.credentialDuration = duration;

        return this;
    }


    /**
     * Get the key ID of the private key that should be used for signing
     * the credential.
     *
     * @return
     *         The key ID of the private key that should be used for signing
     *         the credential. If omitted, Authlete will select one
     *         automatically.
     */
    public String getSigningKeyId()
    {
        return signingKeyId;
    }


    /**
     * Set the key ID of the private key that should be used for signing
     * the credential.
     *
     * @param keyId
     *         The key ID of the private key that should be used for signing
     *         the credential. If omitted, Authlete will select one
     *         automatically.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialIssuanceOrder setSigningKeyId(String keyId)
    {
        this.signingKeyId = keyId;

        return this;
    }
}
