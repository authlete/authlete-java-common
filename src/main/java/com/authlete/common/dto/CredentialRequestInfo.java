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
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialRequestInfo implements Serializable
{
    private static final long serialVersionUID = 1L;


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
     * The details about the credential request.
     */
    private String details;


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
     * @return
     *         The value of the {@code format} parameter in the credential request.
     *         Such as {@code "vc+sd-jwt"}.
     */
    public String getFormat()
    {
        return format;
    }


    /**
     * Set the value of the {@code format} parameter in the credential request.
     *
     * @param format
     *         The value of the {@code format} parameter in the credential request.
     *         Such as {@code "jwt_vc_json"} and {@code "vc+sd_jwt"}.
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
     */
    public CredentialRequestInfo setBindingKey(String bindingKey)
    {
        this.bindingKey = bindingKey;

        return this;
    }


    /**
     * Get the details of the credential request. The format is JSON Object.
     *
     * <p>
     * The value is almost the same as the credential request except that it
     * does not contain the {@code "format"} parameter and the {@code "proof"}
     * parameter.
     * </p>
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
}
