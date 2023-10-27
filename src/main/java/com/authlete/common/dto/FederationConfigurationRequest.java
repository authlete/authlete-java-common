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
import com.authlete.common.types.EntityType;


/**
 * Request to Authlete's {@code /federation/configuration} API.
 *
 * <p>
 * The Authlete API creates an <b>entity configuration</b>, which is defined in
 * the "<a href="https://openid.net/specs/openid-federation-1_0.html">OpenID
 * Federation 1.0</a>" specification.
 * </p>
 *
 * <p>
 * The optional "{@code entityTypes}" request parameter specifies the entity
 * types for which the entity configuration is created. For example, if the
 * request parameter holds an array containing "{@link EntityType#OPENID_PROVIDER
 * OPENID_PROVIDER}" and "{@link EntityType#OPENID_CREDENTIAL_ISSUER
 * OPENID_CREDENTIAL_ISSUER}", the entity configuration will contain metadata
 * for both "{@code openid_provider}" and "{@code openid_credential_issuer}".
 * To be concrete, the "{@code metadata}" property in the entity configuration
 * will look like the following.
 * </p>
 *
 * <blockquote>
 * <pre>
 * <span style="color: navy;">"metadata"</span>: {
 *   <span style="color: navy;">"openid_provider"</span>: {
 *     ...
 *   },
 *   <span style="color: navy;">"openid_credential_issuer"</span>: {
 *     ...
 *   }
 * }
 * </pre>
 * </blockquote>
 *
 * <p>
 * Unsupported entity types in the "{@code entityTypes}" request parameter,
 * to be specific, other entity types than "{@code OPENID_PROVIDER}" and
 * "{@code OPENID_CREDENTIAL_ISSUER}", are ignored.
 * </p>
 *
 * <p>
 * If the feature of "Verifiable Credentials" (which is supported from
 * Authlete 3.0) is not enabled, "{@code OPENID_CREDENTIAL_ISSUER}" in the
 * "{@code entityTypes}" request parameter is ignored even if it is included.
 * </p>
 *
 * <P>
 * When the "{@code entityTypes}" request parameter is omitted or empty, or
 * when the resultant set of entity types becomes empty after unsupported
 * entity types are dropped, the {@code /federation/configuration} API will
 * behave as if the "{@code entityTypes}" request parameter were specified
 * with "{@code OPENID_PROVIDER}" only. This behavior is for the backward
 * compatibility.
 * </p>
 *
 * @since 3.31
 * @since Authlete 2.3
 *
 * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
 *      >OpenID Federation 1.0</a>
 */
public class FederationConfigurationRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    private EntityType[] entityTypes;


    /**
     * Get the entity types for which the entity configuration is created.
     *
     * <p>
     * When this request parameter is omitted or empty, the Authlete API will
     * create an entity configuration for {@code openid_provider}.
     * </p>
     *
     * @return
     *         The entity types for which the entity configuration is created.
     *
     * @since 3.81
     * @since Authlete 3.0
     */
    public EntityType[] getEntityTypes()
    {
        return entityTypes;
    }


    /**
     * Set the entity types for which the entity configuration is created.
     *
     * <p>
     * When this request parameter is omitted or empty, the Authlete API will
     * create an entity configuration for {@code openid_provider}.
     * </p>
     *
     * @param entityTypes
     *         The entity types for which the entity configuration is created.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.81
     * @since Authlete 3.0
     */
    public FederationConfigurationRequest setEntityTypes(EntityType[] entityTypes)
    {
        this.entityTypes = entityTypes;

        return this;
    }
}
