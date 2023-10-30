/*
 * Copyright (C) 2022-2023 Authlete, Inc.
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
 * Request to Authlete's {@code /federation/registration} API.
 *
 * <p>
 * The Authlete API is for implementations of the <b>federation registration
 * endpoint</b> that accepts "explicit client registration". Its details are
 * defined in <a href="https://openid.net/specs/openid-federation-1_0.html"
 * >OpenID Federation 1.0</a>.
 * </p>
 *
 * <p>
 * The endpoint accepts {@code POST} requests whose {@code Content-Type}
 * is either of the following.
 * </p>
 *
 * <ol>
 *   <li>{@code application/entity-statement+jwt}
 *   <li>{@code application/trust-chain+json}
 * </ol>
 *
 * <p>
 * When the {@code Content-Type} of a request is
 * {@code application/entity-statement+jwt}, the content of the request is
 * the entity configuration of a relying party that is to be registered.
 * In this case, the implementation of the federation registration endpoint
 * should call Authlete's {@code /federation/registration} API with the
 * entity configuration set to the {@code entityConfiguration} request
 * parameter.
 * </p>
 *
 * <p>
 * On the other hand, when the {@code Content-Type} of a request is
 * {@code application/trust-chain+json}, the content of the request is a
 * JSON array that contains entity statements in JWT format. The sequence
 * of the entity statements composes the trust chain of a relying party
 * that is to be registered. In this case, the implementation of the
 * federation registration endpoint should call Authlete's
 * {@code /federation/registration} API with the trust chain set to the
 * {@code trustChain} request parameter.
 * </p>
 *
 * @since 3.45
 * @since Authlete 2.3
 *
 * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
 *      >OpenID Federation 1.0</a>
 */
public class FederationRegistrationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The entity configuration of a relying party.
     */
    private String entityConfiguration;


    /**
     * The trust chain of a relying party.
     */
    private String trustChain;


    /**
     * Get the entity configuration of a relying party.
     *
     * @return
     *         An entity configuration in JWT format.
     */
    public String getEntityConfiguration()
    {
        return entityConfiguration;
    }


    /**
     * Set the entity configuration of a relying party.
     *
     * @param jwt
     *         An entity configuration in JWT format.
     *
     * @return
     *         {@code this} object.
     */
    public FederationRegistrationRequest setEntityConfiguration(String jwt)
    {
        this.entityConfiguration = jwt;

        return this;
    }


    /**
     * Get the trust chain of a relying party.
     *
     * @return
     *         A trust chain in JSON format.
     */
    public String getTrustChain()
    {
        return trustChain;
    }


    /**
     * Set the trust chain of a relying party.
     *
     * @param json
     *         A trust chain in JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public FederationRegistrationRequest setTrustChain(String json)
    {
        this.trustChain = json;

        return this;
    }
}
