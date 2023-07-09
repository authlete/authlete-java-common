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
 * A request to Authlete's {@code /vci/deferred/issue} API.
 *
 * <p>
 * The Authlete API is supposed to be called by the implementation of the
 * <b>deferred credential endpoint</b>. The endpoint is defined in the
 * "<a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 * >OpenID for Verifiable Credential Issuance</a>" (OID4VCI) specification.
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
 * The role of the {@code /vci/deferred/issue} API is to issue a credential.
 * </p>
 *
 * @since 3.69
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialDeferredParseRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The access token that came along with the deferred credential request.
     */
    private String accessToken;


    /**
     * The message body of the deferred credential request.
     */
    private String requestContent;


    /**
     * Get the access token that came along with the deferred credential request.
     *
     * @return
     *         The access token that the deferred credential endpoint received.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token that came along with the deferred credential request.
     *
     * @param accessToken
     *         The access token that the deferred credential endpoint received.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialDeferredParseRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the message body of the deferred credential request. The expected
     * format is JSON Object that contains the "{@code transaction_id}"
     * parameter.
     *
     * @return
     *         The message body of the deferred credential request.
     */
    public String getRequestContent()
    {
        return requestContent;
    }


    /**
     * Set the message body of the deferred credential request. The expected
     * format is JSON Object that contains the "{@code transaction_id}"
     * parameter.
     *
     * @param requestContent
     *         The message body of the deferred credential request.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialDeferredParseRequest setRequestContent(String requestContent)
    {
        this.requestContent = requestContent;

        return this;
    }
}
