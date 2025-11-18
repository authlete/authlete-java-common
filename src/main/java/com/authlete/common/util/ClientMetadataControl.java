/*
 * Copyright (C) 2022-2025 Authlete, Inc.
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
package com.authlete.common.util;


/**
 * Flags to control contents of a map that represents client metadata.
 *
 * @since 3.45
 */
public class ClientMetadataControl extends MapControl
{
    private boolean secretIncluded;
    private boolean customIncluded;
    private boolean aliasPreferred;
    private boolean entityIdPreferred;
    private boolean metadataDocumentLocationPreferred;


    @Override
    public ClientMetadataControl setNullIncluded(boolean included)
    {
        return (ClientMetadataControl)super.setNullIncluded(included);
    }


    @Override
    public ClientMetadataControl setZeroIncluded(boolean included)
    {
        return (ClientMetadataControl)super.setZeroIncluded(included);
    }


    @Override
    public ClientMetadataControl setFalseIncluded(boolean included)
    {
        return (ClientMetadataControl)super.setFalseIncluded(included);
    }


    /**
     * Get the flag indicating whether to include the {@code client_secret}
     * property.
     *
     * <p>
     * Note that the {@code client_secret} property is not included when
     * the client type is not "confidential"
     * (cf. {@code Client.}{@link com.authlete.common.dto.Client#getClientType() getClientType()}).
     * </p>
     *
     * @return
     *         {@code true} if the {@code client_secret} property is included.
     *         {@code false} if the {@code client_secret} property is not
     *         included.
     */
    public boolean isSecretIncluded()
    {
        return secretIncluded;
    }


    /**
     * Set the flag indicating whether to include the {@code client_secret}
     * property.
     *
     * <p>
     * Note that the {@code client_secret} property is not included when
     * the client type is not "confidential"
     * (cf. {@code Client.}{@link com.authlete.common.dto.Client#getClientType() getClientType()}).
     * </p>
     *
     * @param included
     *         {@code true} to include the {@code client_secret} property.
     *         {@code false} not to include the {@code client_secret} property.
     *
     * @return
     *         {@code this} object.
     */
    public ClientMetadataControl setSecretIncluded(boolean included)
    {
        this.secretIncluded = included;

        return this;
    }


    /**
     * Get the flag indicating whether to include custom metadata.
     *
     * @return
     *         {@code true} if custom metadata are included.
     *         {@code false} if custom metadata are not included.
     */
    public boolean isCustomIncluded()
    {
        return customIncluded;
    }


    /**
     * Set the flag indicating whether to include custom metadata.
     *
     * @param included
     *         {@code true} to include custom metadata.
     *         {@code false} not to include custom metadata.
     *
     * @return
     *         {@code this} object.
     */
    public ClientMetadataControl setCustomIncluded(boolean included)
    {
        this.customIncluded = included;

        return this;
    }


    /**
     * Get the flag whether to use the client ID alias as the value of the
     * {@code client_id} property when available.
     *
     * <p>
     * Note that the alias is not used if the alias feature is not enabled
     * (cf. {@code Client.}{@link com.authlete.common.dto.Client#isClientIdAliasEnabled()
     * isClientIdAliasEnabled()}).
     * </p>
     *
     * @return
     *         {@code true} if the client ID alias is used as the value of the
     *         {@code client_id} property when available.
     */
    public boolean isAliasPreferred()
    {
        return aliasPreferred;
    }


    /**
     * Set the flag whether to use the client ID alias as the value of the
     * {@code client_id} property when available.
     *
     * <p>
     * Note that the alias is not used if the alias feature is not enabled
     * (cf. {@code Client.}{@link com.authlete.common.dto.Client#isClientIdAliasEnabled()
     * isClientIdAliasEnabled()}).
     * </p>
     *
     * @param preferred
     *         {@code true} to use the client ID alias as the value of the
     *         {@code client_id} property when available.
     *
     * @return
     *         {@code this} object.
     */
    public ClientMetadataControl setAliasPreferred(boolean preferred)
    {
        this.aliasPreferred = preferred;

        return this;
    }


    /**
     * Get the flag whether to use the entity ID as the value of the
     * {@code client_id} property when available.
     *
     * @return
     *         {@code true} if the entity ID is used as the value of the
     *         {@code client_id} property when available.
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html">
     *      OpenID Federation 1.0</a>
     */
    public boolean isEntityIdPreferred()
    {
        return entityIdPreferred;
    }


    /**
     * Set the flag whether to use the entity ID as the value of the
     * {@code client_id} property when available.
     *
     * @param preferred
     *         {@code true} to use the entity ID as the value of the
     *         {@code client_id} property when available.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html">
     *      OpenID Federation 1.0</a>
     */
    public ClientMetadataControl setEntityIdPreferred(boolean preferred)
    {
        this.entityIdPreferred = preferred;

        return this;
    }


    /**
     * Get the flag whether to use the location of the client's metadata
     * document as the value of the {@code client_id} property when
     * available.
     *
     * @return
     *         {@code true} if the location of the client's metadata
     *         document is used as the value of the {@code client_id}
     *         property when available.
     *
     * @since 4.29
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public boolean isMetadataDocumentLocationPreferred()
    {
        return metadataDocumentLocationPreferred;
    }


    /**
     * Set the flag whether to use the location of the client's metadata
     * document as the value of the {@code client_id} property when
     * available.
     *
     * @param preferred
     *         {@code true} to use the location of the client's metadata
     *         document as the value of the {@code client_id} property
     *         when available.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.29
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public ClientMetadataControl setMetadataDocumentLocationPreferred(boolean preferred)
    {
        this.metadataDocumentLocationPreferred = preferred;

        return this;
    }
}
