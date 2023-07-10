/*
 * Copyright (C) 2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's {@code /vci/metadata} API.
 *
 * <p>
 * The Authlete API is supposed to be used from within the implementation of
 * the credential issuer metadata endpoint
 * ({@code /.well-known/openid-credential-issuer}).
 * </p>
 *
 * @since 3.55
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialIssuerMetadataRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    private boolean pretty;


    /**
     * Get the flag indicating whether the metadata is written in the pretty
     * format or not.
     *
     * @return
     *         {@code true} if the metadata is written in the pretty format.
     *
     * @since 3.56
     */
    public boolean isPretty()
    {
        return pretty;
    }


    /**
     * Set the flag indicating whether the metadata is written in the pretty
     * format or not.
     *
     * @param pretty
     *         {@code true} to write the metadata in the pretty format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.56
     */
    public CredentialIssuerMetadataRequest setPretty(boolean pretty)
    {
        this.pretty = pretty;

        return this;
    }
}
