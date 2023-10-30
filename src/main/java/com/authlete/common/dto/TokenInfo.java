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
import java.net.URI;


/**
 * Information about a token of the type
 * {@code "urn:ietf:params:oauth:token-type:access_token"} or the type
 * {@code "urn:ietf:params:oauth:token-type:refresh_token"}.
 *
 * <p>
 * This class is used to hold detailed information about a subject token
 * or an actor token. See the descriptions of
 * {@link TokenResponse#getSubjectTokenInfo()} and
 * {@link TokenResponse#getActorTokenInfo()} for details.
 * </p>
 *
 * @see TokenResponse#getSubjectTokenInfo()
 * @see TokenResponse#getActorTokenInfo()
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
 *      >RFC 8693 OAuth 2.0 Token Exchange</a>
 *
 * @since 3.26
 * @since Authlete 2.3
 */
public class TokenInfo implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The client ID.
     */
    private long clientId;


    /**
     * Resource owner's unique identifier.
     */
    private String subject;


    /**
     * Scopes.
     */
    private Scope[] scopes;


    /**
     * The time at which the token expires.
     */
    private long expiresAt;


    /**
     * Extra properties associated with the token.
     */
    private Property[] properties;


    /**
     * The client ID alias when the authorization request or the token request
     * for the token was made.
     */
    private String clientIdAlias;


    /**
     * Flag which indicates whether the client ID alias was used when the
     * authorization request or the token request for the token was made.
     */
    private boolean clientIdAliasUsed;


    /**
     * The entity ID of the client.
     *
     * @since 3.37
     */
    private URI clientEntityId;


    /**
     * Flag which indicates whether the entity ID of the client was used
     * when the request for the token was made.
     *
     * @since 3.37
     */
    private boolean clientEntityIdUsed;


    /**
     * The target resources of the token.
     */
    private URI[] resources;


    /**
     * The content of the {@code "authorization_details"} request parameter
     * which was included in the request that obtained the token.
     */
    private AuthzDetails authorizationDetails;


    /**
     * Get the client ID.
     *
     * @return
     *         The client ID.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID.
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         {@code this} instance.
     */
    public TokenInfo setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the subject (= resource owner's unique identifier).
     *
     * @return
     *         The subject.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= resource owner's unique identifier).
     *
     * @param subject
     *         The subject.
     *
     * @return
     *         {@code this} instance.
     */
    public TokenInfo setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the scopes.
     *
     * @return
     *         The scopes.
     */
    public Scope[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes.
     *
     * @param scopes
     *         The scopes.
     *
     * @return
     *         {@code this} instance.
     */
    public TokenInfo setScopes(Scope[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the expiration date/time in seconds since the Unix epoch.
     *
     * @return
     *         The expiration date/time.
     */
    public long getExpiresAt()
    {
        return expiresAt;
    }


    /**
     * Set the expiration date/time in seconds since the Unix epoch.
     *
     * @param expiresAt
     *         The expiration date/time.
     *
     * @return
     *         {@code this} instance.
     */
    public TokenInfo setExpiresAt(long expiresAt)
    {
        this.expiresAt = expiresAt;

        return this;
    }


    /**
     * Get the extra properties associated with the token.
     *
     * @return
     *         The extra properties.
     *
     * @see <a href="https://www.authlete.com/developers/definitive_guide/extra_properties/"
     *      >Extra Properties</a>
     *
     * @see <a href="https://kb.authlete.com/en/s/oauth-and-openid-connect/a/extra-properties"
     *      >How to add extra properties to an access token - Authlete Knowledge Base</a>
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set the extra properties associated with the token.
     *
     * @param properties
     *         The extra properties.
     *
     * @return
     *         {@code this} instance.
     *
     * @see <a href="https://www.authlete.com/developers/definitive_guide/extra_properties/"
     *      >Extra Properties</a>
     *
     * @see <a href="https://kb.authlete.com/en/s/oauth-and-openid-connect/a/extra-properties"
     *      >How to add extra properties to an access token - Authlete Knowledge Base</a>
     */
    public TokenInfo setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the alias of the client ID.
     *
     * @return
     *         The alias of the client ID.
     *
     * @see <a href="https://kb.authlete.com/en/s/oauth-and-openid-connect/a/client-id-alias"
     *      >Using "Client ID Alias" - Authlete Knowledge Base</a>
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the alias of the client ID.
     *
     * @param alias
     *         The alias of the client ID.
     *
     * @return
     *         {@code this} instance.
     *
     * @see <a href="https://kb.authlete.com/en/s/oauth-and-openid-connect/a/client-id-alias"
     *      >Using "Client ID Alias" - Authlete Knowledge Base</a>
     */
    public TokenInfo setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;

        return this;
    }


    /**
     * Get the flag indicating whether the alias of the client ID was used
     * when the token was created.
     *
     * @return
     *         {@code true} if the alias of the client ID was used.
     *
     * @see <a href="https://kb.authlete.com/en/s/oauth-and-openid-connect/a/client-id-alias"
     *      >Using "Client ID Alias" - Authlete Knowledge Base</a>
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag indicating whether the alias of the client ID was used
     * when the token was created.
     *
     * @param used
     *         {@code true} to indicate that the alias of the client ID was
     *         used.
     *
     * @return
     *         {@code this} instance.
     *
     * @see <a href="https://kb.authlete.com/en/s/oauth-and-openid-connect/a/client-id-alias"
     *      >Using "Client ID Alias" - Authlete Knowledge Base</a>
     */
    public TokenInfo setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;

        return this;
    }


    /**
     * Get the entity ID of the client.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @return
     *         The entity ID of the client.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public URI getClientEntityId()
    {
        return clientEntityId;
    }


    /**
     * Set the entity ID of the client.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @param entityId
     *         The entity ID of the client.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public TokenInfo setClientEntityId(URI entityId)
    {
        this.clientEntityId = entityId;

        return this;
    }


    /**
     * Get the flag which indicates whether the entity ID of the client was
     * used when the request for the token was made.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @return
     *         {@code true} if the entity ID of the client was used when the
     *         request for the token was made.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public boolean isClientEntityIdUsed()
    {
        return clientEntityIdUsed;
    }


    /**
     * Set the flag which indicates whether the entity ID of the client was
     * used when the request for the token was made.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @param used
     *         {@code true} to indicate that the entity ID of the client was
     *         used when the request for the token was made.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public TokenInfo setClientEntityIdUsed(boolean used)
    {
        this.clientEntityIdUsed = used;

        return this;
    }


    /**
     * Get the resources associated with the token.
     *
     * <p>
     * The values are ones specified by the {@code resource} request parameters.
     * </p>
     *
     * @return
     *         The resources.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
     *      >RFC 8707 Resource Indicators for OAuth 2.0</a>
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Set the resources associated with the token.
     *
     * <p>
     * The values are ones specified by the {@code resource} request parameters.
     * </p>
     *
     * @param resources
     *         The resources.
     *
     * @return
     *         {@code this} instance.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
     *      >RFC 8707 Resource Indicators for OAuth 2.0</a>
     */
    public TokenInfo setResources(URI[] resources)
    {
        this.resources = resources;

        return this;
    }


    /**
     * Get the authorization details associated with the token.
     *
     * <p>
     * The value is one specified by the {@code authorization_details} request
     * parameter.
     * </p>
     *
     * @return
     *         The authorization details.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9396.html"
     *      >RFC 9396 OAuth 2.0 Rich Authorization Requests</a>
     */
    public AuthzDetails getAuthorizationDetails()
    {
        return authorizationDetails;
    }


    /**
     * Set the authorization details associated with the token.
     *
     * <p>
     * The value is one specified by the {@code authorization_details} request
     * parameter.
     * </p>
     *
     * @param details
     *         The authorization details.
     *
     * @return
     *         {@code this} instance.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9396.html"
     *      >RFC 9396 OAuth 2.0 Rich Authorization Requests</a>
     */
    public TokenInfo setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;

        return this;
    }
}
