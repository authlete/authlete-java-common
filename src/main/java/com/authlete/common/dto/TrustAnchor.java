/*
 * Copyright (C) 2022 Authlete, Inc.
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
import java.net.URI;


/**
 * Trust anchor.
 *
 * @since 3.22
 *
 * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
 *      >OpenID Connect Federation 1.0</a>
 */
public class TrustAnchor implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The entity ID of the trust anchor.
     */
    private URI entityId;


    /**
     * The JWK Set document containing public keys of the trust anchor.
     */
    private String jwks;


    /**
     * Get the entity ID of the trust anchor.
     *
     * @return
     *         The entity ID.
     */
    public URI getEntityId()
    {
        return entityId;
    }


    /**
     * Set the entity ID of the trust anchor.
     *
     * @param entityId
     *         The entity ID.
     *
     * @return
     *         {@code this} object.
     */
    public TrustAnchor setEntityId(URI entityId)
    {
        this.entityId = entityId;

        return this;
    }


    /**
     * Get the JWK Set document containing public keys of the trust anchor.
     *
     * <p>
     * The keys are used to verify signatures of entity statements issued
     * by the trust anchor.
     * </p>
     *
     * @return
     *         The JWK Set document containing public keys of the trust anchor.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     *      >RFC 7517 JSON Web Key (JWK)</a>
     */
    public String getJwks()
    {
        return jwks;
    }


    /**
     * Set the JWK Set document containing public keys of the trust anchor.
     *
     * <p>
     * The keys are used to verify signatures of entity statements issued
     * by the trust anchor.
     * </p>
     *
     * @param jwks
     *         The JWK Set document containing public keys of the trust anchor.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     *      >RFC 7517 JSON Web Key (JWK)</a>
     */
    public TrustAnchor setJwks(String jwks)
    {
        this.jwks = jwks;

        return this;
    }
}
