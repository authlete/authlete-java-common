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
 * Information about a credential request.
 *
 * <p>
 * This class represents information about a credential request that is sent
 * to the credential endpoint or information about a credential request in the
 * {@code credential_requests} array in a batch credential request that is
 * sent to the batch credential endpoint.
 * </p>
 *
 * @since 3.66
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html">
 *      OpenID for Verifiable Credential Issuance 1.0</a>
 */
public class CredentialRequestInfo implements Serializable
{
    private static final long serialVersionUID = 3L;


    /**
     * The identifier of the credential request.
     */
    private String identifier;


    /**
     * The value of the format parameter in the credential request.
     */
    private String format;


    /**
     * The binding key specified by the proof in the credential request.
     */
    private String bindingKey;


    /**
     * The binding keys specified by the proofs in the credential request.
     *
     * @since 4.2
     */
    private String[] bindingKeys;


    /**
     * The details about the credential request.
     */
    private String details;


    /**
     * The {@code credential_configuration_id} parameter in the credential
     * request.
     *
     * @since 4.34
     * @since Authlete 3.0.25
     */
    private String credentialConfigurationId;


    /**
     * The {@code credential_identifier} parameter in the credential request.
     *
     * @since 4.34;
     * @since Authlete 3.0.25
     */
    private String credentialIdentifier;


    /**
     * Get the identifier of the credential request.
     *
     * <p>
     * The identifier is assigned by Authlete when the {@code /vci/single/parse}
     * API or the {@code /vci/batch/parse} API is used. The identifier is used
     * as a transaction ID, too. The format is a base64url string that consists
     * of 43 characters with 256-bit entropy.
     * </p>
     *
     * @return
     *         The identifier of the credential request.
     */
    public String getIdentifier()
    {
        return identifier;
    }


    /**
     * Set the identifier of the credential request.
     *
     * <p>
     * The identifier is assigned by Authlete when the {@code /vci/single/parse}
     * API or the {@code /vci/batch/parse} API is used. The identifier is used
     * as a transaction ID, too. The format is a base64url string that consists
     * of 43 characters with 256-bit entropy.
     * </p>
     *
     * @param identifier
     *         The identifier of the credential request.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialRequestInfo setIdentifier(String identifier)
    {
        this.identifier = identifier;

        return this;
    }


    /**
     * Get the value of the {@code format} parameter in the credential request.
     *
     * <p>
     * This {@code format} property is available only when the supported OID4VCI
     * specification version is {@code "1.0-ID1"} (i.e., when the Service's
     * {@code oid4vciVersion} is unset or set to {@code "1.0-ID1"}) and the
     * credential request contains the {@code format} parameter. Note that in
     * the final version of the specification, the {@code format} parameter is
     * deprecated.
     * </p>
     *
     * @return
     *         The value of the {@code format} parameter in the credential request.
     *         Such as {@code "dc+sd-jwt"}.
     */
    public String getFormat()
    {
        return format;
    }


    /**
     * Set the value of the {@code format} parameter in the credential request.
     *
     * <p>
     * This {@code format} property is available only when the supported OID4VCI
     * specification version is {@code "1.0-ID1"} (i.e., when the Service's
     * {@code oid4vciVersion} is unset or set to {@code "1.0-ID1"}) and the
     * credential request contains the {@code format} parameter. Note that in
     * the final version of the specification, the {@code format} parameter is
     * deprecated.
     * </p>
     *
     * @param format
     *         The value of the {@code format} parameter in the credential request.
     *         Such as {@code "jwt_vc_json"} and {@code "dc+sd-jwt"}.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialRequestInfo setFormat(String format)
    {
        this.format = format;

        return this;
    }


    /**
     * Get the binding key specified by the proof in the credential request.
     * The format is JWK (<a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     * >RFC 7517 JSON Web Key</a>).
     *
     * @return
     *         The binding key. If the credential request does not contain a
     *         proof, a binding key is not available. In the case, {@code null}
     *         is returned.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     *      >RFC 7517 JSON Web Key</a>
     */
    public String getBindingKey()
    {
        return bindingKey;
    }


    /**
     * Set the binding key specified by the proof in the credential request.
     * The format is JWK (<a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     * >RFC 7517 JSON Web Key</a>).
     *
     * @param bindingKey
     *         The binding key.
     *
     * @return
     *         {@code this} object}.
     */
    public CredentialRequestInfo setBindingKey(String bindingKey)
    {
        this.bindingKey = bindingKey;

        return this;
    }


    /**
     * Get the binding keys specified by the proofs in the credential request.
     * The format of each entry in the returned array is a string representation
     * of JWK (<a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     * >RFC 7517 JSON Web Key</a>).
     *
     * @return
     *         The binding keys. If the credential request does not contain
     *         proofs, binding keys are not available. In the case, {@code null}
     *         is returned.
     *
     * @since 4.2
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     *      >RFC 7517 JSON Web Key</a>
     */
    public String[] getBindingKeys()
    {
        return bindingKeys;
    }


    /**
     * Set the binding keys specified by the proofs in the credential request.
     * The format of each entry in the returned array is a string representation
     * of JWK (<a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     * >RFC 7517 JSON Web Key</a>).
     *
     * @param bindingKeys
     *         The binding keys.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.2
     */
    public CredentialRequestInfo setBindingKeys(String[] bindingKeys)
    {
        this.bindingKeys = bindingKeys;

        return this;
    }


    /**
     * Get the details of the credential request. The format is JSON Object.
     *
     * <p>
     * The value is almost the same as the credential request except that it
     * does not contain the following parameters:
     * </p>
     *
     * <ul>
     * <li>{@code credential_configuration_id}
     * <li>{@code credential_identifier}
     * <li>{@code credential_response_encryption}
     * <li>{@code format}
     * <li>{@code proof}
     * <li>{@code proofs}
     * </ul>
     *
     * <p>
     * For example, when the original credential request holds the following:
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "format": "jwt_vc_json",
     *   "credential_definition": {
     *     "type": [
     *       "VerifiableCredential",
     *       "UniversityDegreeCredential"
     *     ]
     *   },
     *   "proof": {
     *     "proof_type": "jwt",
     *     "jwt": "eyJ...OzM"
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * this {@code details} parameter holds the following:
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_definition": {
     *     "type": [
     *       "VerifiableCredential",
     *       "UniversityDegreeCredential"
     *     ]
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         The details of the credential request.
     */
    public String getDetails()
    {
        return details;
    }


    /**
     * Set the details of the credential request. The format is JSON Object.
     *
     * @param details
     *         The details of the credential request.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialRequestInfo setDetails(String details)
    {
        this.details = details;

        return this;
    }


    /**
     * Get the value of the {@code credential_configuration_id} parameter in
     * the credential request.
     *
     * <p>
     * This property is not available when the supported OID4VCI specification
     * version is {@code "1.0-ID1"} (i.e., when the Service's
     * {@code oid4vciVersion} is unset or set to {@code "1.0-ID1"}), because
     * the {@code credential_configuration_id} parameter does not exist in
     * OID4VCI 1.0 ID1.
     * </p>
     *
     * @return
     *         The value of the {@code credential_configuration_id} parameter
     *         in the credential request.
     *
     * @since 4.34
     * @since Authlete 3.0.25
     */
    public String getCredentialConfigurationId()
    {
        return credentialConfigurationId;
    }


    /**
     * Set the value of the {@code credential_configuration_id} parameter in
     * the credential request.
     *
     * <p>
     * This property is not available when the supported OID4VCI specification
     * version is {@code "1.0-ID1"} (i.e., when the Service's
     * {@code oid4vciVersion} is unset or set to {@code "1.0-ID1"}), because
     * the {@code credential_configuration_id} parameter does not exist in
     * OID4VCI 1.0 ID1.
     * </p>
     *
     * @param id
     *         The value of the {@code credential_configuration_id} parameter
     *         in the credential request.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.34
     * @since Authlete 3.0.25
     */
    public CredentialRequestInfo setCredentialConfigurationId(String id)
    {
        this.credentialConfigurationId = id;

        return this;
    }


    /**
     * Get the value of the {@code credential_identifier} parameter in the
     * credential request.
     *
     * <p>
     * This property is not available when the supported OID4VCI specification
     * version is {@code "1.0-ID1"} (i.e., when the Service's
     * {@code oid4vciVersion} is unset or set to {@code "1.0-ID1"}), because
     * Authlete does not support the {@code credential_identifier} parameter
     * for OID4VCI 1.0 ID1.
     * </p>
     *
     * @return
     *         The value of the {@code credential_identifier} parameter in the
     *         credential request.
     *
     * @since 4.34
     * @since Authlete 3.0.25
     */
    public String getCredentialIdentifier()
    {
        return credentialIdentifier;
    }


    /**
     * Set the value of the {@code credential_identifier} parameter in the
     * credential request.
     *
     * <p>
     * This property is not available when the supported OID4VCI specification
     * version is {@code "1.0-ID1"} (i.e., when the Service's
     * {@code oid4vciVersion} is unset or set to {@code "1.0-ID1"}), because
     * Authlete does not support the {@code credential_identifier} parameter
     * for OID4VCI 1.0 ID1.
     * </p>
     *
     * @param identifier
     *         The value of the {@code credential_identifier} parameter in the
     *         credential request.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.34
     * @since Authlete 3.0.25
     */
    public CredentialRequestInfo setCredentialIdentifier(String identifier)
    {
        this.credentialIdentifier = identifier;

        return this;
    }
}
