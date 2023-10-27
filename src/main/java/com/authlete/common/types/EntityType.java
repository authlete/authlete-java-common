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
package com.authlete.common.types;


import java.util.EnumSet;


/**
 * Entity type identifiers in the context of the OpenID Federation
 * 1&#x2E;0.
 *
 * @see <a href="https://openid.net/specs/openid-federation-1_0.html#name-entity-type-identifiers"
 *      >OpenID Federation 1.0, 4.2. Entity Type Identifiers</a>
 *
 * @since 3.81
 */
public enum EntityType
{
    /**
     * {@code "openid_relying_party"} (1).
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html#name-openid-connect-relying-part"
     *      >OpenID Federation 1.0, 4.2.1. OpenID Connect Relying Party</a>
     */
    OPENID_RELYING_PARTY((short)1, "openid_relying_party"),


    /**
     * {@code "openid_provider"} (2).
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html#name-openid-provider"
     *      >OpenID Federation 1.0, 4.2.2. OpenID Provider</a>
     */
    OPENID_PROVIDER((short)2, "openid_provider"),


    /**
     * {@code "oauth_authorization_server"} (3).
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html#name-oauth-authorization-server"
     *      >OpenID Federation 1.0, 4.2.3. OAuth Authorization Server</a>
     */
    OAUTH_AUTHORIZATION_SERVER((short)3, "oauth_authorization_server"),


    /**
     * {@code "oauth_client"} (4).
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html#name-oauth-client"
     *      >OpenID Federation 1.0, 4.2.4. OAuth Client</a>
     */
    OAUTH_CLIENT((short)4, "oauth_client"),


    /**
     * {@code "oauth_resource"} (5).
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html#name-oauth-protected-resource"
     *      >OpenID Federation 1.0, 4.2.5. OAuth Protected Resource</a>
     */
    OAUTH_RESOURCE((short)5, "oauth_resource"),


    /**
     * {@code "federation_entity"} (6).
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html#name-federation-entity"
     *      >OpenID Federation 1.0, 4.7. Federation Entity</a>
     */
    FEDERATION_ENTITY((short)6, "federation_entity"),


    /**
     * {@code "openid_credential_issuer"} (7).
     *
     * <p>
     * This entity type identifier represents a credential issuer that conforms
     * to the "OpenID for Verifiable Credential Issuance" specification. The
     * specification is supported since Authlete 3.0.
     * </p>
     */
    OPENID_CREDENTIAL_ISSUER((short)7, "openid_credential_issuer"),
    ;


    private static final EntityType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private EntityType(short value, String string)
    {
        mValue  = value;
        mString = string;
    }


    /**
     * Get the integer representation of this enum instance.
     */
    public short getValue()
    {
        return mValue;
    }


    @Override
    public String toString()
    {
        return mString;
    }


    /**
     * Find an instance of this enum by a value.
     *
     * @param value
     *         The integer representation of the instance to find.
     *
     * @return
     *         An instance of this enum, or {@code null} if not found.
     */
    public static EntityType getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code MetadataType}.
     *
     * @param metadataType
     *         Metadata type. For example, {@code "openid_provider"}.
     *
     * @return
     *         {@code MetadataType} instance, or {@code null}.
     */
    public static EntityType parse(String metadataType)
    {
        if (metadataType == null)
        {
            return null;
        }

        for (EntityType entry : sValues)
        {
            if (entry.mString.equals(metadataType))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<EntityType> set)
    {
        return sHelper.toBits(set);
    }


    public static EntityType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<EntityType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<EntityType> toSet(EntityType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<EntityType>
    {
        public Helper(EntityType[] values)
        {
            super(EntityType.class, values);
        }


        @Override
        protected short getValue(EntityType entry)
        {
            return entry.getValue();
        }


        @Override
        protected EntityType[] newArray(int size)
        {
            return new EntityType[size];
        }
    }
}
