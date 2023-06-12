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
 * Request to the {@code /vci/single/parse} API.
 *
 * <p>
 * The Authlete API is supposed to be used to parse a credential request that
 * the credential endpoint received.
 * </p>
 *
 * <p>
 * Note that the implementation of the credential endpoint should call the
 * {@code /auth/introspection} API to check whether the access token is valid
 * BEFORE calling the {@code /vci/single/parse} API. The validation on the
 * access token by the {@code /vci/single/parse} API is limited and not
 * exhaustive. For example, the {@code /vci/single/parse} API does not check
 * certificate binding (<a href="https://www.rfc-editor.org/rfc/rfc8705.html"
 * >RFC 8705</a>).
 * </p>
 *
 * @since 3.66
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialSingleParseRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The access token that came along with the credential request.
     */
    private String accessToken;


    /**
     * The message body of the credential request.
     */
    private String requestContent;


    /**
     * Get the access token that came along with the credential request.
     *
     * @return
     *         The access token that the credential endpoint received.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token that came along with the credential request.
     *
     * @param accessToken
     *         The access token that the credential endpoint received.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleParseRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the message body of the credential request. The expected format is
     * JSON Object that contains at least the {@code "format"} parameter.
     *
     * @return
     *         The message body of the credential request.
     */
    public String getRequestContent()
    {
        return requestContent;
    }


    /**
     * Set the message body of the credential request. The expected format is
     * JSON Object that contains at least the {@code "format"} parameter.
     *
     * @param requestContent
     *         The message body of the credential request.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleParseRequest setRequestContent(String requestContent)
    {
        this.requestContent = requestContent;

        return this;
    }
}
