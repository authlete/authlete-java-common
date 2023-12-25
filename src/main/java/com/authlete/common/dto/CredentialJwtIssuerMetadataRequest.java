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
 * Request to Authlete's {@code /vci/jwtissuer} API.
 *
 * <p>
 * The Authlete API is supposed to be called from within the implementation of
 * the JWT VC issuer metadata endpoint ({@code /.well-known/jwt-vc-issuer}) of
 * the credential issuer.
 * </p>
 *
 * <p>
 * The API will generate JSON like below.
 * </p>
 *
 * <blockquote>
 * <pre>
 * {
 *   "issuer": "{@link Service}.{@link Service#getCredentialIssuerMetadata()
 *              getCredentialIssuerMetadata()}.{@link CredentialIssuerMetadata#getCredentialIssuer()
 *              getCredentialIssuer()}",
 *   "jwks_uri": "{@link Service}.{@link Service#getCredentialJwksUri()
 *              getCredentialJwksUri()}"
 * }
 * </pre>
 * </blockquote>
 *
 * <p>
 * Note that the JWT VC issuer metadata endpoint ({@code /.well-known/jwt-vc-issuer})
 * is different from the credential issuer metadata endpoint
 * ({@code /.well-known/openid-credential-issuer}).
 * </p>
 *
 * <p>
 * NOTE: The well-known path has been changed from {@code /.well-known/jwt-issuer}
 * to {@code /.well-known/jwt-vc-issuer} by a breaking change of the SD-JWT VC
 * specification.
 * </p>
 *
 * @since 3.79
 * @since Authlete 3.0
 *
 * @see CredentialJwtIssuerMetadataResponse
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-sd-jwt-vc/"
 *      >SD-JWT-based Verifiable Credentials (SD-JWT VC)</a>
 */
public class CredentialJwtIssuerMetadataRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private boolean pretty;


    /**
     * Get the flag indicating whether the metadata is written in the pretty
     * format or not.
     *
     * @return
     *         {@code true} if the metadata is written in the pretty format.
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
     */
    public CredentialJwtIssuerMetadataRequest setPretty(boolean pretty)
    {
        this.pretty = pretty;

        return this;
    }
}
