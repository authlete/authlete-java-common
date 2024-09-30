/*
 * Copyright (C) 2014-2023 Authlete, Inc.
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


import static com.authlete.common.util.MapUtils.put;
import static com.authlete.common.util.MapUtils.putJsonObject;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.authlete.common.types.ApplicationType;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.ClientRegistrationType;
import com.authlete.common.types.ClientType;
import com.authlete.common.types.DeliveryMode;
import com.authlete.common.types.FapiMode;
import com.authlete.common.types.GrantType;
import com.authlete.common.types.JWEAlg;
import com.authlete.common.types.JWEEnc;
import com.authlete.common.types.JWSAlg;
import com.authlete.common.types.ResponseMode;
import com.authlete.common.types.ResponseType;
import com.authlete.common.types.ServiceProfile;
import com.authlete.common.types.SubjectType;
import com.authlete.common.util.ClientMetadataControl;
import com.authlete.common.util.Utils;


/**
 * Information about a client application.
 *
 * <p>
 * Some properties correspond to client metadata defined in related standard
 * specifications. See the implementation of
 * {@link #toStandardMetadata(ClientMetadataControl)} for exact mappings.
 * </p>
 *
 * @see <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
 *      >OpenID Connect Dynamic Client Registration 1.0</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html"
 *      >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8705.html"
 *      >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
 *
 * @see <a href="https://openid.net/specs/oauth-v2-jarm.html"
 *      >JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
 *
 * @see <a href="https://openid.net/specs/openid-client-initiated-backchannel-authentication-core-1_0.html"
 *      >OpenID Connect Client-Initiated Backchannel Authentication Flow - Core 1.0</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc9396.html"
 *      >RFC 9396 OAuth 2.0 Rich Authorization Requests</a>
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
 *      >OpenID Federation 1.0</a>
 *
 * @see <a href="https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml#client-metadata"
 *      >IANA OAuth Parameters / OAuth Dynamic Client Registration Metadata</a>
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class Client implements Serializable
{
    private static final long serialVersionUID = 38L;


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    /**
     * Client number.
     * @since Authlete 1.1
     */
    private int number;


    /**
     * Service number.
     * @since Authlete 1.1
     */
    private int serviceNumber;


    /**
     * Developer unique ID.
     * @since Authlete 1.1
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private String developer;


    /**
     * Client ID.
     * @since Authlete 1.1
     */
    private long clientId;


    /**
     * Alias of Client ID.
     * @since Authlete 1.1
     */
    private String clientIdAlias;


    /**
     * True when the client ID alias is enabled.
     * @since Authlete 1.1
     */
    private boolean clientIdAliasEnabled;


    /**
     * Client secret.
     * @since Authlete 1.1
     */
    private String clientSecret;


    /**
     * Client type.
     * @since Authlete 1.1
     */
    private ClientType clientType;


    /**
     * Redirect URIs.
     * @since Authlete 1.1
     */
    private String[] redirectUris;


    /**
     * Response types.
     * @since Authlete 1.1
     */
    private ResponseType[] responseTypes;


    /**
     * Grant types.
     * @since Authlete 1.1
     */
    private GrantType[] grantTypes;


    /**
     * Application type.
     * @since Authlete 1.1
     */
    private ApplicationType applicationType;


    /**
     * Email addresses of contacts.
     * @since Authlete 1.1
     */
    private String[] contacts;


    /**
     * Client name.
     * @since Authlete 1.1
     */
    private String clientName;


    /**
     * Client names.
     * @since Authlete 1.1
     */
    private TaggedValue[] clientNames;


    /**
     * Logo URI.
     * @since Authlete 1.1
     */
    private URI logoUri;


    /**
     * Logo URIs.
     * @since Authlete 1.1
     */
    private TaggedValue[] logoUris;


    /**
     * Client URI.
     * @since Authlete 1.1
     */
    private URI clientUri;


    /**
     * Client URIs.
     * @since Authlete 1.1
     */
    private TaggedValue[] clientUris;


    /**
     * Policy URI.
     * @since Authlete 1.1
     */
    private URI policyUri;


    /**
     * Policy URIs.
     * @since Authlete 1.1
     */
    private TaggedValue[] policyUris;


    /**
     * Terms of Service URI.
     * @since Authlete 1.1
     */
    private URI tosUri;


    /**
     * Terms of Service URIs.
     * @since Authlete 1.1
     */
    private TaggedValue[] tosUris;


    /**
     * JSON Web Key Set URI.
     * @since Authlete 1.1
     */
    private URI jwksUri;


    /**
     * JSON Web Key Set.
     * @since Authlete 1.1
     */
    private String jwks;


    /**
     * Calculated sector identifier host component
     *
     * @since 2.61
     * @since Authlete 2.2.1
     */
    private String derivedSectorIdentifier;


    /**
     * Sector identifier URI.
     *
     * @since 2.50
     * @since Authlete 2.2.1
     */
    private URI sectorIdentifierUri;

    /**
     * @since Authlete 1.1
     */
    private SubjectType subjectType;

    /**
     * @since Authlete 1.1
     */
    private JWSAlg idTokenSignAlg;

    /**
     * @since Authlete 1.1
     */
    private JWEAlg idTokenEncryptionAlg;

    /**
     * @since Authlete 1.1
     */
    private JWEEnc idTokenEncryptionEnc;

    /**
     * @since Authlete 1.1
     */
    private JWSAlg userInfoSignAlg;

    /**
     * @since Authlete 1.1
     */
    private JWEAlg userInfoEncryptionAlg;

    /**
     * @since Authlete 1.1
     */
    private JWEEnc userInfoEncryptionEnc;

    /**
     * @since Authlete 1.1
     */
    private JWSAlg requestSignAlg;

    /**
     * @since Authlete 1.1
     */
    private JWEAlg requestEncryptionAlg;

    /**
     * @since Authlete 1.1
     */
    private JWEEnc requestEncryptionEnc;

    /**
     * @since Authlete 1.1
     */
    private ClientAuthMethod tokenAuthMethod;

    /**
     * @since Authlete 1.1
     */
    private JWSAlg tokenAuthSignAlg;

    /**
     * @since Authlete 1.1
     */
    private int defaultMaxAge;

    /**
     * @since Authlete 1.1
     */
    private String[] defaultAcrs;

    /**
     * @since Authlete 1.1
     */
    private boolean authTimeRequired;

    /**
     * @since Authlete 1.1
     */
    private URI loginUri;

    /**
     * @since Authlete 1.1
     */
    private String[] requestUris;

    /**
     * @since Authlete 1.1
     */
    private String description;

    /**
     * @since Authlete 1.1
     */
    private TaggedValue[] descriptions;

    /**
     * @since Authlete 1.1
     */
    private long createdAt;

    /**
     * @since Authlete 1.1
     */
    private long modifiedAt;

    /**
     * @since Authlete 1.1
     */
    private ClientExtension extension;

    /**
     * @since Authlete 1.1.17
     */
    private String tlsClientAuthSubjectDn;

    /**
     * @since Authlete 2.0.0
     */
    private String tlsClientAuthSanDns;

    /**
     * @since Authlete 2.0.0
     */
    private URI tlsClientAuthSanUri;

    /**
     * @since Authlete 2.0.0
     */
    private String tlsClientAuthSanIp;

    /**
     * @since Authlete 2.0.0
     */
    private String tlsClientAuthSanEmail;

    /**
     * @since Authlete 1.1.19
     */
    private boolean tlsClientCertificateBoundAccessTokens;

    /**
     * @since Authlete 1.1.19
     */
    private String selfSignedCertificateKeyId;

    /**
     * @since Authlete 2.3.0
     */
    private String softwareId;

    /**
     * @since Authlete 2.3.0
     */
    private String softwareVersion;

    /**
     * @since Authlete 2.0.0
     */
    private JWSAlg authorizationSignAlg;

    /**
     * @since Authlete 2.0.0
     */
    private JWEAlg authorizationEncryptionAlg;

    /**
     * @since Authlete 2.0.0
     */
    private JWEEnc authorizationEncryptionEnc;

    /**
     * @since Authlete 2.0.0
     */
    private DeliveryMode bcDeliveryMode;

    /**
     * @since Authlete 2.0.0
     */
    private URI bcNotificationEndpoint;

    /**
     * @since Authlete 2.0.0
     */
    private JWSAlg bcRequestSignAlg;

    /**
     * @since Authlete 2.0.0
     */
    private boolean bcUserCodeRequired;

    /**
     * @since Authlete 2.0.0
     */
    private boolean dynamicallyRegistered;

    /**
     * @since Authlete 2.0.0
     */
    private String registrationAccessTokenHash;

    /**
     * @since Authlete 2.2.7
     */
    private String[] authorizationDetailsTypes;

    /**
     * @since Authlete 2.2.1
     */
    private boolean parRequired;

    /**
     * @since Authlete 2.2.1
     */
    private boolean requestObjectRequired;

    /**
     * @since Authlete 2.2.3
     */
    private Pair[] attributes;

    /**
     * @since Authlete 2.2.10
     */
    private String customMetadata;

    /**
     * @since Authlete 2.2.10
     */
    private boolean frontChannelRequestObjectEncryptionRequired;

    /**
     * @since Authlete 2.2.10
     */
    private boolean requestObjectEncryptionAlgMatchRequired;

    /**
     * @since Authlete 2.2.10
     */
    private boolean requestObjectEncryptionEncMatchRequired;

    /**
     * @since Authlete 2.3.0
     */
    private String digestAlgorithm;

    /**
     * @since Authlete 2.3.0
     */
    private boolean singleAccessTokenPerSubject;

    /**
     * @since Authlete 2.3.0
     */
    private boolean pkceRequired;

    /**
     * @since Authlete 2.3.0
     */
    private boolean pkceS256Required;

    /**
     * @since Authlete 2.3.0
     */
    private String rsSignedRequestKeyId;

    /**
     * @since Authlete 2.3.0
     */
    private boolean rsRequestSigned;

    /**
     * @since Authlete 2.3.0
     */
    private boolean dpopRequired;

    /**
     * @since Authlete 2.3.7
     */
    private boolean locked;

    /**
     * @since Authlete 3.0.0
     */
    private FapiMode[] fapiModes;

    /**
     * @since Authlete 3.0.0
     */
    private ResponseMode[] responseModes;

    /**
     * @since Authlete 3.0.0
     */
    private boolean mtlsEndpointAliasesUsed;


    /*
     * For OpenID Federation 1.0.
     */

    /**
     * @since Authlete 2.3.0
     */
    private URI entityId;

    /**
     * @since Authlete 2.3.0
     */
    private URI trustAnchorId;

    /**
     * @since Authlete 2.3.0
     */
    private String[] trustChain;

    /**
     * @since Authlete 2.3.0
     */
    private long trustChainExpiresAt;

    /**
     * @since Authlete 2.3.0
     */
    private long trustChainUpdatedAt;

    /**
     * @since Authlete 2.3.0
     */
    private String organizationName;

    /**
     * @since Authlete 2.3.0
     */
    private URI signedJwksUri;

    /**
     * @since Authlete 2.3.0
     */
    private ClientRegistrationType[] clientRegistrationTypes;

    /**
     * @since Authlete 2.3.0
     */
    private boolean automaticallyRegistered;

    /**
     * @since Authlete 2.3.0
     */
    private boolean explicitlyRegistered;

    /*
     * For Verifiable Credentials
     */

    /**
     * @since Authlete 3.0
     */
    private URI credentialOfferEndpoint;

    /**
     * @since Authlete 3.0
     */
    private boolean credentialResponseEncryptionRequired;


    /**
     * Get the client number.
     *
     * @return
     *         The client number.
     */
    public int getNumber()
    {
        return number;
    }


    /**
     * Set the client number.
     *
     * @param number
     *         The client number.
     *
     * @return
     *         {@code this} object.
     */
    public Client setNumber(int number)
    {
        this.number = number;

        return this;
    }


    /**
     * Get the number of the service which this client belongs to.
     *
     * @return
     *         The service number
     */
    public int getServiceNumber()
    {
        return serviceNumber;
    }


    /**
     * Set the number of the service which this client belongs to.
     *
     * @param number
     *         The service number.
     *
     * @return
     *         {@code this} object.
     */
    public Client setServiceNumber(int number)
    {
        this.serviceNumber = number;

        return this;
    }


    /**
     * Get the unique ID of the developer of this client application.
     *
     * @return
     *         The developer unique ID.
     */
    public String getDeveloper()
    {
        return developer;
    }


    /**
     * Set the unique ID of the developer of this client application.
     *
     * @param developer
     *         The developer unique ID.
     *
     * @return
     *         {@code this} object.
     */
    public Client setDeveloper(String developer)
    {
        this.developer = developer;

        return this;
    }


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
     *         {@code this} object.
     */
    public Client setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the alias of the client ID.
     *
     * <p>
     * Note that the client ID alias is recognized only when this
     * client's {@code clientIdAliasEnabled} property is {@code true}
     * AND the {@link Service service}'s {@code clientIdAliasEnabled}
     * property is also {@code true}.
     * </p>
     *
     * @return
     *         The alias of the client ID. This may be {@code null}.
     *
     * @since 2.1
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the alias of the client ID.
     *
     * <p>
     * Note that the client ID alias is recognized only when this
     * client's {@code clientIdAliasEnabled} property is {@code true}
     * AND the {@link Service service}'s {@code clientIdAliasEnabled}
     * property is also {@code true}.
     * </p>
     *
     * @param alias
     *         The alias of the client ID.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.1
     */
    public Client setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;

        return this;
    }


    /**
     * Get the flag which indicates whether the client ID alias
     * is enabled or not.
     *
     * <p>
     * Note that {@link Service} class also has
     * {@code clientIdAliasEnabled} property. If the service's
     * {@code clientIdAliasEnabled} property is {@code false},
     * the client ID alias of this client is not recognized even
     * if this client's {@code clientIdAliasEnabled} property is
     * {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if the client ID alias is enabled.
     *
     * @since 2.2
     */
    public boolean isClientIdAliasEnabled()
    {
        return clientIdAliasEnabled;
    }


    /**
     * Enable/disable the client ID alias.
     *
     * <p>
     * Note that {@link Service} class also has
     * {@code clientIdAliasEnabled} property. If the service's
     * {@code clientIdAliasEnabled} property is {@code false},
     * the client ID alias of this client is not recognized even
     * if this client's {@code clientIdAliasEnabled} property is
     * {@code true}.
     * </p>
     *
     * @param enabled
     *         {@code true} to enable the client ID alias.
     *         {@code false} to disable it.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.2
     */
    public Client setClientIdAliasEnabled(boolean enabled)
    {
        this.clientIdAliasEnabled = enabled;

        return this;
    }


    /**
     * Get the client secret.
     *
     * @return
     *         The client secret.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret.
     *
     * @param clientSecret
     *         The client secret.
     *
     * @return
     *         {@code this} object.
     */
    public Client setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }


    /**
     * Get the client type.
     *
     * @return
     *         The client type.
     */
    public ClientType getClientType()
    {
        return clientType;
    }


    /**
     * Set the client type.
     *
     * @param clientType
     *         The client type.
     *
     * @return
     *         {@code this} object.
     */
    public Client setClientType(ClientType clientType)
    {
        this.clientType = clientType;

        return this;
    }


    /**
     * Get the redirect URIs.
     *
     * @return
     *         The redirect URIs.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.2"
     *      >RFC 6749 (OAuth 2.0), 3.1.2. Redirection Endpoint</a>
     */
    public String[] getRedirectUris()
    {
        return redirectUris;
    }


    /**
     * Set the redirect URIs.
     *
     * @param uris
     *         The redirect URIs.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.2"
     *      >RFC 6749 (OAuth 2.0), 3.1.2. Redirection Endpoint</a>
     */
    public Client setRedirectUris(String[] uris)
    {
        this.redirectUris = uris;

        return this;
    }


    /**
     * Get {@code response_type} values that the client is declaring
     * that it will restrict itself to using.
     *
     * @return
     *         The response types.
     */
    public ResponseType[] getResponseTypes()
    {
        return responseTypes;
    }


    /**
     * Set {@code response_type} values that the client is declaring
     * that it will restrict itself to using.
     *
     * @param responseTypes
     *         The response types.
     *
     * @return
     *         {@code this} object.
     */
    public Client setResponseTypes(ResponseType[] responseTypes)
    {
        this.responseTypes = responseTypes;

        return this;
    }


    /**
     * Get {@code grant_type} values that the client is declaring
     * that it will restrict itself to using.
     *
     * @return
     *         The grant types.
     */
    public GrantType[] getGrantTypes()
    {
        return grantTypes;
    }


    /**
     * Set {@code grant_type} values that the client is declaring
     * that it will restrict itself to using.
     *
     * @param grantTypes
     *         The grant types.
     *
     * @return
     *         {@code this} object.
     */
    public Client setGrantTypes(GrantType[] grantTypes)
    {
        this.grantTypes = grantTypes;

        return this;
    }


    /**
     * Get the application type.
     *
     * @return
     *         The application type.
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
     */
    public ApplicationType getApplicationType()
    {
        return applicationType;
    }


    /**
     * Set the application type.
     *
     * @param applicationType
     *         The application type.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
     */
    public Client setApplicationType(ApplicationType applicationType)
    {
        this.applicationType = applicationType;

        return this;
    }


    /**
     * Get the email addresses of contacts.
     *
     * @return
     *         Email addresses of contacts.
     */
    public String[] getContacts()
    {
        return contacts;
    }


    /**
     * Set the email addresses of contacts.
     *
     * @param contacts
     *         Email addresses of contacts.
     *
     * @return
     *         {@code this} object.
     */
    public Client setContacts(String[] contacts)
    {
        this.contacts = contacts;

        return this;
    }


    /**
     * Get the client name.
     *
     * @return
     *         The client name.
     */
    public String getClientName()
    {
        return clientName;
    }


    /**
     * Set the client name.
     *
     * @param clientName
     *         The client name.
     *
     * @return
     *         {@code this} object.
     */
    public Client setClientName(String clientName)
    {
        this.clientName = clientName;

        return this;
    }


    /**
     * Get the client names each of which has a language tag.
     *
     * @return
     *         The client names each of which has a language tag.
     */
    public TaggedValue[] getClientNames()
    {
        return clientNames;
    }


    /**
     * Set the client names each of which has a language tag.
     *
     * @param clientNames
     *         The client names.
     *
     * @return
     *         {@code this} object.
     */
    public Client setClientNames(TaggedValue[] clientNames)
    {
        this.clientNames = clientNames;

        return this;
    }


    /**
     * Get the URI of the logo image.
     *
     * @return
     *         The URI of the logo image.
     */
    public URI getLogoUri()
    {
        return logoUri;
    }


    /**
     * Set the URI of the logo image.
     *
     * @param uri
     *         The URI of the logo image.
     *
     * @return
     *         {@code this} object.
     */
    public Client setLogoUri(URI uri)
    {
        this.logoUri = uri;

        return this;
    }


    /**
     * Get the logo URIs each of which has a language tag.
     *
     * @return
     *         The logo URIs.
     */
    public TaggedValue[] getLogoUris()
    {
        return logoUris;
    }


    /**
     * Set the logo URIs each of which has a language tag.
     *
     * @param uris
     *         The logo URIs.
     *
     * @return
     *         {@code this} object.
     */
    public Client setLogoUris(TaggedValue[] uris)
    {
        this.logoUris = uris;

        return this;
    }


    /**
     * Get the URI of the home page.
     *
     * @return
     *         The URI of the home page.
     */
    public URI getClientUri()
    {
        return clientUri;
    }


    /**
     * Set the URI of the home page.
     *
     * @param uri
     *         The URI of the home page.
     *
     * @return
     *         {@code this} object.
     */
    public Client setClientUri(URI uri)
    {
        this.clientUri = uri;

        return this;
    }


    /**
     * Get the URIs of the home pages for specific languages.
     *
     * @return
     *         The URIs of the home page for specific languages.
     */
    public TaggedValue[] getClientUris()
    {
        return clientUris;
    }


    /**
     * Set the URIs of the home pages for specific languages.
     *
     * @param uris
     *         The URIs of the home page for specific languages.
     *
     * @return
     *         {@code this} object.
     */
    public Client setClientUris(TaggedValue[] uris)
    {
        this.clientUris = uris;

        return this;
    }


    /**
     * Get the URI of the policy page which describes how
     * the client application uses the profile data of the
     * end-user.
     *
     * @return
     *         The URI of the policy page.
     */
    public URI getPolicyUri()
    {
        return policyUri;
    }


    /**
     * Set the URI of the policy page which describes how
     * the client application uses the profile data of the
     * end-user.
     *
     * @param uri
     *         The URI of the policy page.
     *
     * @return
     *         {@code this} object.
     */
    public Client setPolicyUri(URI uri)
    {
        this.policyUri = uri;

        return this;
    }


    /**
     * Get the URIs of the policy pages for specific languages.
     *
     * @return
     *         The URIs of the policy pages for specific languages.
     */
    public TaggedValue[] getPolicyUris()
    {
        return policyUris;
    }


    /**
     * Set the URIs of the policy pages for specific languages.
     *
     * @param uris
     *         The URIs of the policy pages for specific languages.
     *
     * @return
     *         {@code this} object.
     */
    public Client setPolicyUris(TaggedValue[] uris)
    {
        this.policyUris = uris;

        return this;
    }


    /**
     * Get the URI of the "Terms Of Service" page.
     *
     * @return
     *         The URI of the "Terms Of Service" page.
     */
    public URI getTosUri()
    {
        return tosUri;
    }


    /**
     * Set the URI of the "Terms Of Service" page.
     *
     * @param uri
     *         The URI of the "Terms Of Service" page.
     *
     * @return
     *         {@code this} object.
     */
    public Client setTosUri(URI uri)
    {
        this.tosUri = uri;

        return this;
    }


    /**
     * Get the URIs of the "Terms Of Service" pages for specific languages.
     *
     * @return
     *         The URIs of the "Terms Of Service" pages for specific languages.
     */
    public TaggedValue[] getTosUris()
    {
        return tosUris;
    }


    /**
     * Set the URIs of the "Terms Of Service" pages for specific languages.
     *
     * @param uris
     *         The URIs of the "Terms Of Service" pages for specific languages.
     *
     * @return
     *         {@code this} object.
     */
    public Client setTosUris(TaggedValue[] uris)
    {
        this.tosUris = uris;

        return this;
    }


    /**
     * Get the URI of the JSON Web Key Set of the client application.
     *
     * @return
     *         The URI of the JSON Web Key Set of the client application.
     */
    public URI getJwksUri()
    {
        return jwksUri;
    }


    /**
     * Set the URI of the JSON Web Key Set of the client application.
     *
     * @param uri
     *         The URI of the JSON Web Key Set of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public Client setJwksUri(URI uri)
    {
        this.jwksUri = uri;

        return this;
    }


    /**
     * Get the JSON Web Key Set.
     *
     * @return
     *         The JSON Web Key Set.
     */
    public String getJwks()
    {
        return jwks;
    }


    /**
     * Set the JSON Web Key Set.
     *
     * @param jwks
     *         The JSON Web Key Set.
     *
     * @return
     *         {@code this} object.
     */
    public Client setJwks(String jwks)
    {
        this.jwks = jwks;

        return this;
    }


    /**
     * Get the sector identifier.
     *
     * @deprecated Since Authlete 2.2. Use {@link Client#getSectorIdentifierUri()} instead.
     *
     * @return
     *         The sector identifier.
     */
    @Deprecated
    public URI getSectorIdentifier()
    {
        return this.getSectorIdentifierUri();
    }


    /**
     * Set the sector identifier.
     *
     * @deprecated Since Authlete 2.2. Use {@link Client#setSectorIdentifierUri(URI)} instead.
     *
     * @param sectorIdentifier
     *         The sector identifier.
     *
     * @return
     *         {@code this} object.
     */
    @Deprecated
    public Client setSectorIdentifier(URI sectorIdentifier)
    {
        return this.setSectorIdentifierUri(sectorIdentifier);
    }


    /**
     * Get the value of the sector identifier URI.
     *
     * <p>
     * This represents the {@code sector_identifier_uri} client metadata which
     * is defined in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >2. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html">OpenID Connect
     * Dynamic Client Registration 1.0</a>.
     * </p>
     *
     * @return
     *         The sector identifier URI.
     *
     * @since 2.50
     */
    public URI getSectorIdentifierUri()
    {
        return sectorIdentifierUri;
    }


    /**
     * Set the value of the sector identifier URI.
     *
     * <p>
     * This represents the {@code sector_identifier_uri} client metadata which
     * is defined in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >2. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html">OpenID Connect
     * Dynamic Client Registration 1.0</a>.
     * </p>
     *
     * @param uri
     *         The sector identifier URI.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.50
     */
    public Client setSectorIdentifierUri(URI uri)
    {
        this.sectorIdentifierUri = uri;

        return this;
    }


    /**
     * Get the sector identifier host component as derived from either the
     * {@code sector_identifier_uri} or the registered {@code redirect_uri}.
     * If no {@code sector_identifier_uri} is registered and multiple
     * {@code redirect_uri}s are also registered, this value is undefined
     * and the field returns {@code null}.
     *
     * @return
     *         The derived sector identifier, if available, or {@code null} otherwise.
     *
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#PairwiseAlg"
     *      >OIDC Core, 8.1. Pairwise Identifier Algorithm</a>
     *
     * @since 2.61
     */
    public String getDerivedSectorIdentifier()
    {
        return derivedSectorIdentifier;
    }


    /**
     * Set the sector identifier host component as derived from either the
     * {@code sector_identifier_uri} or the registered {@code redirect_uri}.
     * If no {@code sector_identifier_uri} is registered and multiple
     * {@code redirect_uri}s are also registered, this value is undefined
     * and the field is {@code null}.
     *
     * @param derivedSectorIdentifier
     *         The derived sector identifier, if available, or {@code null} otherwise.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#PairwiseAlg"
     *      >OIDC Core, 8.1. Pairwise Identifier Algorithm</a>
     *
     * @since 2.61
     */
    public Client setDerivedSectorIdentifier(String derivedSectorIdentifier)
    {
        this.derivedSectorIdentifier = derivedSectorIdentifier;

        return this;
    }


    /**
     * Get the subject type that this client application requests.
     *
     * @return
     *         The subject type.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#SubjectIDTypes"
     *      >Subject Identifier Types</a>
     */
    public SubjectType getSubjectType()
    {
        return subjectType;
    }


    /**
     * Set the subject type that this client application requests.
     *
     * @param subjectType
     *          The subject type.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#SubjectIDTypes"
     *      >Subject Identifier Types</a>
     */
    public Client setSubjectType(SubjectType subjectType)
    {
        this.subjectType = subjectType;

        return this;
    }


    /**
     * Get the JWS <code>alg</code> algorithm for signing the ID token
     * issued to this client. This property corresponds to
     * <code>id_token_signed_response_alg</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWS <code>alg</code> algorithm for signing the ID
     *         token issued to this client.
     */
    public JWSAlg getIdTokenSignAlg()
    {
        return idTokenSignAlg;
    }


    /**
     * Set the JWS <code>alg</code> algorithm for signing the ID token
     * issued to this client. This property corresponds to
     * <code>id_token_signed_response_alg</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param alg
     *         The JWS <code>alg</code> algorithm for signing the
     *         ID token issued to this client.
     *
     * @return
     *         {@code this} object.
     */
    public Client setIdTokenSignAlg(JWSAlg alg)
    {
        this.idTokenSignAlg = alg;

        return this;
    }


    /**
     * Get the JWE <code>alg</code> algorithm for encrypting the ID token
     * issued to this client. This property corresponds to
     * <code>id_token_encrypted_response_alg</code>in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWE <code>alg</code> algorithm for encrypting the
     *         ID token issued to this client.
     */
    public JWEAlg getIdTokenEncryptionAlg()
    {
        return idTokenEncryptionAlg;
    }


    /**
     * Set the JWE <code>alg</code> algorithm for encrypting the ID token
     * issued to this client. This property corresponds to
     * <code>id_token_encrypted_response_alg</code>in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param alg
     *         The JWE <code>alg</code> algorithm for encrypting the
     *         ID token issued to this client.
     *
     * @return
     *         {@code this} object.
     */
    public Client setIdTokenEncryptionAlg(JWEAlg alg)
    {
        this.idTokenEncryptionAlg = alg;

        return this;
    }


    /**
     * Get the JWE <code>enc</code> algorithm for encrypting the ID token
     * issued to this client. This property corresponds to
     * <code>id_token_encrypted_response_enc</code>in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWE <code>enc</code> algorithm for encrypting the
     *         ID token issued to this client.
     */
    public JWEEnc getIdTokenEncryptionEnc()
    {
        return idTokenEncryptionEnc;
    }


    /**
     * Set the JWE <code>enc</code> algorithm for encrypting the ID token
     * issued to this client. This property corresponds to
     * <code>id_token_encrypted_response_enc</code>in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param enc
     *         The JWE <code>enc</code> algorithm for encrypting the
     *         ID token issued to this client.
     *
     * @return
     *         {@code this} object.
     */
    public Client setIdTokenEncryptionEnc(JWEEnc enc)
    {
        this.idTokenEncryptionEnc = enc;

        return this;
    }


    /**
     * Get the JWS <code>alg</code> algorithm for signing UserInfo responses.
     * This property corresponds to <code>userinfo_signed_response_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWS <code>alg</code> algorithm for signing UserInfo responses.
     */
    public JWSAlg getUserInfoSignAlg()
    {
        return userInfoSignAlg;
    }


    /**
     * Set the JWS <code>alg</code> algorithm for signing UserInfo responses.
     * This property corresponds to <code>userinfo_signed_response_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param alg
     *         The JWS <code>alg</code> algorithm for signing UserInfo responses.
     *
     * @return
     *         {@code this} object.
     */
    public Client setUserInfoSignAlg(JWSAlg alg)
    {
        this.userInfoSignAlg = alg;

        return this;
    }


    /**
     * Get the JWE <code>alg</code> algorithm for encrypting UserInfo responses.
     * This property corresponds to <code>userinfo_encrypted_response_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWE <code>alg</code> algorithm for encrypting UserInfo responses.
     */
    public JWEAlg getUserInfoEncryptionAlg()
    {
        return userInfoEncryptionAlg;
    }


    /**
     * Set the JWE <code>alg</code> algorithm for encrypting UserInfo responses.
     * This property corresponds to <code>userinfo_encrypted_response_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param alg
     *         The JWE <code>alg</code> algorithm for encrypting UserInfo responses.
     *
     * @return
     *         {@code this} object.
     */
    public Client setUserInfoEncryptionAlg(JWEAlg alg)
    {
        this.userInfoEncryptionAlg = alg;

        return this;
    }


    /**
     * Get the JWE <code>enc</code> algorithm for encrypting UserInfo responses.
     * This property corresponds to <code>userinfo_encrypted_response_enc</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWE <code>enc</code> algorithm for encrypting UserInfo responses.
     */
    public JWEEnc getUserInfoEncryptionEnc()
    {
        return userInfoEncryptionEnc;
    }


    /**
     * Set the JWE <code>enc</code> algorithm for encrypting UserInfo responses.
     * This property corresponds to <code>userinfo_encrypted_response_enc</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param enc
     *         The JWE <code>enc</code> algorithm for encrypting UserInfo responses.
     *
     * @return
     *         {@code this} object.
     */
    public Client setUserInfoEncryptionEnc(JWEEnc enc)
    {
        this.userInfoEncryptionEnc = enc;

        return this;
    }


    /**
     * Get the JWS <code>alg</code> algorithm for signing request objects.
     * This property corresponds to <code>request_object_signing_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWS <code>alg</code> algorithm for signing request objects.
     */
    public JWSAlg getRequestSignAlg()
    {
        return requestSignAlg;
    }


    /**
     * Set the JWS <code>alg</code> algorithm for signing request objects.
     * This property corresponds to <code>request_object_signing_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param alg
     *         The JWS <code>alg</code> algorithm for signing request objects.
     *
     * @return
     *         {@code this} object.
     */
    public Client setRequestSignAlg(JWSAlg alg)
    {
        this.requestSignAlg = alg;

        return this;
    }


    /**
     * Get the JWE <code>alg</code> algorithm for encrypting request objects.
     * This property corresponds to <code>request_object_encryption_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWE <code>alg</code> algorithm for encrypting request objects.
     */
    public JWEAlg getRequestEncryptionAlg()
    {
        return requestEncryptionAlg;
    }


    /**
     * Set the JWE <code>alg</code> algorithm for encrypting request objects.
     * This property corresponds to <code>request_object_encryption_alg</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param alg
     *         The JWE <code>alg</code> algorithm for encrypting request objects.
     *
     * @return
     *         {@code this} object.
     */
    public Client setRequestEncryptionAlg(JWEAlg alg)
    {
        this.requestEncryptionAlg = alg;

        return this;
    }


    /**
     * Get the JWE <code>enc</code> algorithm for encrypting request objects.
     * This property corresponds to <code>request_object_encryption_enc</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWE <code>enc</code> algorithm for encrypting request objects.
     */
    public JWEEnc getRequestEncryptionEnc()
    {
        return requestEncryptionEnc;
    }


    /**
     * Set the JWE <code>enc</code> algorithm for encrypting request objects.
     * This property corresponds to <code>request_object_encryption_enc</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param enc
     *         The JWE <code>enc</code> algorithm for encrypting request objects.
     *
     * @return
     *         {@code this} object.
     */
    public Client setRequestEncryptionEnc(JWEEnc enc)
    {
        this.requestEncryptionEnc = enc;

        return this;
    }


    /**
     * Get the client authentication method for the token endpoint.
     * This property corresponds to <code>token_endpoint_auth_method</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The client authentication method for the token endpoint.
     */
    public ClientAuthMethod getTokenAuthMethod()
    {
        return tokenAuthMethod;
    }


    /**
     * Set the client authentication method for the token endpoint.
     * This property corresponds to <code>token_endpoint_auth_method</code>
     * in <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param method
     *         The client authentication method for the token endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public Client setTokenAuthMethod(ClientAuthMethod method)
    {
        this.tokenAuthMethod = method;

        return this;
    }


    /**
     * Get the JWS <code>alg</code> algorithm for signing the JWT used to
     * authenticate the client at the token endpoint. This property corresponds
     * to <code>token_endpoint_auth_signing_alg</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The JWS <code>alg</code> algorithm for signing the JWT used to
     *         authenticate the client at the token endpoint.
     */
    public JWSAlg getTokenAuthSignAlg()
    {
        return tokenAuthSignAlg;
    }


    /**
     * Set the JWS <code>alg</code> algorithm for signing the JWT used to
     * authenticate the client at the token endpoint. This property corresponds
     * to <code>token_endpoint_auth_signing_alg</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param alg
     *         The JWS <code>alg</code> algorithm for signing the JWT used to
     *         authenticate the client at the token endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public Client setTokenAuthSignAlg(JWSAlg alg)
    {
        this.tokenAuthSignAlg = alg;

        return this;
    }


    /**
     * Get the default value of the maximum authentication age in seconds.
     * This property corresponds to <code>default_max_age</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The default value of the maximum authentication age in seconds.
     */
    public int getDefaultMaxAge()
    {
        return defaultMaxAge;
    }


    /**
     * Set the default value of the maximum authentication age in seconds.
     * This property corresponds to <code>default_max_age</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * <p>
     * This value is used when the request from the client application does
     * not contain the <code>max_age</code> request parameter.
     * </p>
     *
     * @param defaultMaxAge
     *         The default value of the maximum authentication age in seconds.
     *         0 means that no default value is set.
     *
     * @return
     *         {@code this} object.
     */
    public Client setDefaultMaxAge(int defaultMaxAge)
    {
        this.defaultMaxAge = defaultMaxAge;

        return this;
    }


    /**
     * Get the flag which indicates whether this client requires <code>auth_time</code>
     * claim to be embedded in the ID token. This property corresponds to
     * <code>require_auth_time</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The flag which indicates whether this client requires <code>auth_time</code>
     *         claim to be embedded in the ID token.
     */
    public boolean isAuthTimeRequired()
    {
        return authTimeRequired;
    }


    /**
     * Set the flag which indicates whether this client requires <code>auth_time</code>
     * claim to be embedded in the ID token. This property corresponds to
     * <code>require_auth_time</code> in <a href=
     * "http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param required
     *         The flag which indicates whether this client requires <code>auth_time</code>
     *         claim to be embedded in the ID token.
     *
     * @return
     *         {@code this} object.
     */
    public Client setAuthTimeRequired(boolean required)
    {
        this.authTimeRequired = required;

        return this;
    }


    /**
     * Get the default list of authentication context class references.
     * This property corresponds to <code>default_acr_values</code> in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The default list of authentication context class references.
     */
    public String[] getDefaultAcrs()
    {
        return defaultAcrs;
    }


    /**
     * Set the default list of authentication context class references.
     * This property corresponds to <code>default_max_age</code> in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * <p>
     * This value is used when the request from the client application does
     * not contain the <code>acr_values</code> request parameter.
     * </p>
     *
     * @param defaultAcrs
     *         The default list of authentication context class references.
     *
     * @return
     *         {@code this} object.
     */
    public Client setDefaultAcrs(String[] defaultAcrs)
    {
        this.defaultAcrs = defaultAcrs;

        return this;
    }


    /**
     * Get the URL that can initiate a login for this client application.
     * This property corresponds to <code>initiate_login_uri</code> in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The URL that can initiate a login for this client application.
     */
    public URI getLoginUri()
    {
        return loginUri;
    }


    /**
     * Set the URL that can initiate a login for this client application.
     * This property corresponds to <code>initiate_login_uri</code> in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param uri
     *         The URL that can initiate a login for this client application.
     *
     * @return
     *         {@code this} object.
     */
    public Client setLoginUri(URI uri)
    {
        this.loginUri = uri;

        return this;
    }


    /**
     * Get the request URIs that this client declares it may use. This property
     * corresponds to <code>request_uris</code> in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @return
     *         The request URIs that this client declares it may use.
     */
    public String[] getRequestUris()
    {
        return requestUris;
    }


    /**
     * Set the request URIs that this client declares it may use. This property
     * corresponds to <code>request_uris</code> in <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     * >Client Metadata</a>.
     *
     * @param uris
     *         The request URIs that this client declares it may use.
     *
     * @return
     *         {@code this} object.
     */
    public Client setRequestUris(String[] uris)
    {
        this.requestUris = uris;

        return this;
    }


    /**
     * Get the description.
     *
     * @return
     *         The description.
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Set the description.
     *
     * @param description
     *         The description.
     *
     * @return
     *         {@code this} object.
     */
    public Client setDescription(String description)
    {
        this.description = description;

        return this;
    }


    /**
     * Get the descriptions for specific languages.
     *
     * @return
     *         The descriptions for specific languages.
     */
    public TaggedValue[] getDescriptions()
    {
        return descriptions;
    }


    /**
     * Set the descriptions for specific languages.
     *
     * @param descriptions
     *         The descriptions for specific languages.
     *
     * @return
     *         {@code this} object.
     */
    public Client setDescriptions(TaggedValue[] descriptions)
    {
        this.descriptions = descriptions;

        return this;
    }


    /**
     * Get the time at which this client was created.
     *
     * @return
     *         The time at which this client was created.
     *         The value is represented as milliseconds since
     *         the UNIX epoch (1970-01-01).
     *
     * @since 1.6
     */
    public long getCreatedAt()
    {
        return createdAt;
    }


    /**
     * Set the time at which this client was created.
     *
     * @param createdAt
     *         The time at which this client was created.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.6
     */
    public Client setCreatedAt(long createdAt)
    {
        this.createdAt = createdAt;

        return this;
    }


    /**
     * Get the time at which this client was last modified.
     *
     * @return
     *         The time at which this client was last modified.
     *         The value is represented as milliseconds since
     *         the UNIX epoch (1970-01-01).
     *
     * @since 1.6
     */
    public long getModifiedAt()
    {
        return modifiedAt;
    }


    /**
     * Set the time at which this client was last modified.
     *
     * @param modifiedAt
     *         The time at which this client was modified.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.6
     */
    public Client setModifiedAt(long modifiedAt)
    {
        this.modifiedAt = modifiedAt;

        return this;
    }


    /**
     * Get the extended information about this client.
     *
     * @return
     *         The extended information about this client.
     *
     * @since 1.39
     */
    public ClientExtension getExtension()
    {
        return extension;
    }


    /**
     * Set the extended information about this client.
     *
     * @param extension
     *         The extended information about this client.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.39
     */
    public Client setExtension(ClientExtension extension)
    {
        this.extension = extension;

        return this;
    }


    /**
     * Get the string representation of the expected subject
     * distinguished name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_subject_dn} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @return
     *         The expected subject distinguished name of the
     *         client certificate.
     *
     * @since 2.7
     */
    public String getTlsClientAuthSubjectDn()
    {
        return tlsClientAuthSubjectDn;
    }


    /**
     * Set the string representation of the expected subject
     * distinguished name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_subject_dn} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @param name
     *         The expected subject distinguished name of the
     *         client certificate.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.7
     */
    public Client setTlsClientAuthSubjectDn(String name)
    {
        this.tlsClientAuthSubjectDn = name;

        return this;
    }


    /**
     * Get the string representation of the expected DNS subject
     * alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_dns} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @return
     *         The expected DNS subject alternative name of the
     *         client certificate.
     *
     * @since 2.38
     */
    public String getTlsClientAuthSanDns()
    {
        return tlsClientAuthSanDns;
    }


    /**
     * Set the string representation of the expected DNS subject
     * alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_dns} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @param name
     *         The expected DNS subject alternative name of the
     *         client certificate.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.38
     */
    public Client setTlsClientAuthSanDns(String tlsClientAuthSanDns)
    {
        this.tlsClientAuthSanDns = tlsClientAuthSanDns;

        return this;
    }


    /**
     * Get the string representation of the expected URI subject
     * alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_uri} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @return
     *         The expected URI subject alternative name of the
     *         client certificate.
     *
     * @since 2.38
     */
    public URI getTlsClientAuthSanUri()
    {
        return tlsClientAuthSanUri;
    }


    /**
     * Set the string representation of the expected URI subject
     * alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_uri} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @param name
     *         The expected URI subject alternative name of the
     *         client certificate.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.38
     */
    public Client setTlsClientAuthSanUri(URI tlsClientAuthSanUri)
    {
        this.tlsClientAuthSanUri = tlsClientAuthSanUri;

        return this;
    }


    /**
     * Get the string representation of the expected IP address
     * subject alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_ip} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @return
     *         The expected IP address subject alternative name of the
     *         client certificate.
     *
     * @since 2.38
     */
    public String getTlsClientAuthSanIp()
    {
        return tlsClientAuthSanIp;
    }


    /**
     * Set the string representation of the expected IP address
     * subject alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_ip} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @param name
     *         The expected IP address subject alternative name of the
     *         client certificate.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.38
     */
    public Client setTlsClientAuthSanIp(String tlsClientAuthSanIp)
    {
        this.tlsClientAuthSanIp = tlsClientAuthSanIp;

        return this;
    }


    /**
     * Get the string representation of the expected email address
     * subject alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_email} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @return
     *         The expected email address subject alternative name of the
     *         client certificate.
     *
     * @since 2.38
     */
    public String getTlsClientAuthSanEmail()
    {
        return tlsClientAuthSanEmail;
    }


    /**
     * Set the string representation of the expected email address
     * subject alternative name of the certificate this client will
     * use in mutual TLS authentication.
     *
     * <p>
     * See {@code tls_client_auth_san_email} in <i>"2.3. Dynamic
     * Client Registration"</i> in <i>"Mutual TLS Profiles for
     * OAuth Clients"</i> for details.
     * </p>
     *
     * @param name
     *         The expected email address subject alternative name of the
     *         client certificate.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.38
     */
    public Client setTlsClientAuthSanEmail(String tlsClientAuthSanEmail)
    {
        this.tlsClientAuthSanEmail = tlsClientAuthSanEmail;

        return this;
    }


    /**
     * Does this client use TLS client certificate bound access tokens?
     *
     * @return
     *         {@code true} if this client uses TLS client certificate bound
     *         access tokens.
     *
     * @since 2.19
     */
    public boolean isTlsClientCertificateBoundAccessTokens()
    {
        return tlsClientCertificateBoundAccessTokens;
    }


    /**
     * Set whether this client uses TLS client certificate bound access tokens
     * or not.
     *
     * @param use
     *         {@code true} to indicate that this client uses TLS client
     *         certificate bound access tokens.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.19
     */
    public Client setTlsClientCertificateBoundAccessTokens(boolean use)
    {
        this.tlsClientCertificateBoundAccessTokens = use;

        return this;
    }


    /**
     * Get the key ID of a JWK containing a self-signed certificate of this client.
     *
     * <p>
     * See <i>"2.2. Self-Signed Certificate Mutual TLS OAuth Client Authentication
     * Method"</i> in <i>"OAuth 2.0 Mutual TLS Client Authentication and Certificate
     * Bound Access Tokens"</i> for details.
     * </p>
     *
     * @return
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @since 2.20
     */
    public String getSelfSignedCertificateKeyId()
    {
        return selfSignedCertificateKeyId;
    }


    /**
     * Set the key ID of a JWK containing a self-signed certificate of this client.
     * Unless this value is set to {@code null}, Authlete uses this value to look
     * up the corresponding JWK for client authentication using mutual TLS utilizing
     * self-signed certificates.
     *
     * <p>
     * See <i>"2.2. Self-Signed Certificate Mutual TLS OAuth Client Authentication
     * Method"</i> in <i>"OAuth 2.0 Mutual TLS Client Authentication and Certificate
     * Bound Access Tokens"</i> for details.
     * </p>
     *
     * @param keyId
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.20
     */
    public Client setSelfSignedCertificateKeyId(String keyId)
    {
        this.selfSignedCertificateKeyId = keyId;

        return this;
    }


    /**
     * Get the unique identifier string assigned by the client developer or
     * software publisher used by registration endpoints to identify the client
     * software to be dynamically registered.
     *
     * <p>
     * This property corresponds to the {@code software_id} metadata defined in
     * <a href="https://tools.ietf.org/html/rfc7591#section-2">2. Client
     * Metadata</a> of <a href="https://tools.ietf.org/html/rfc7591">RFC 7591</a>
     * (OAuth 2.0 Dynamic Client Registration Protocol).
     * </p>
     *
     * @return
     *         The unique identifier of the client software.
     *
     * @since 2.24
     */
    public String getSoftwareId()
    {
        return softwareId;
    }


    /**
     * Set a unique identifier string assigned by the client developer or
     * software publisher used by registration endpoints to identify the client
     * software to be dynamically registered.
     *
     * <p>
     * This property corresponds to the {@code software_id} metadata defined in
     * <a href="https://tools.ietf.org/html/rfc7591#section-2">2. Client
     * Metadata</a> of <a href="https://tools.ietf.org/html/rfc7591">RFC 7591</a>
     * (OAuth 2.0 Dynamic Client Registration Protocol).
     * </p>
     *
     * @param softwareId
     *         A unique identifier of the client software.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.24
     */
    public Client setSoftwareId(String softwareId)
    {
        this.softwareId = softwareId;

        return this;
    }


    /**
     * Get the version identifier string for the client software identified by
     * the software ID.
     *
     * <p>
     * This property corresponds to the {@code software_version} metadata
     * defined in <a href="https://tools.ietf.org/html/rfc7591#section-2">2.
     * Client Metadata</a> of <a href="https://tools.ietf.org/html/rfc7591"
     * >RFC 7591</a> (OAuth 2.0 Dynamic Client Registration Protocol).
     * </p>
     *
     * @return
     *         The version of the client software.
     *
     * @since 2.24
     */
    public String getSoftwareVersion()
    {
        return softwareVersion;
    }


    /**
     * Set a version identifier string for the client software identified by
     * the software ID.
     *
     * <p>
     * This property corresponds to the {@code software_version} metadata
     * defined in <a href="https://tools.ietf.org/html/rfc7591#section-2">2.
     * Client Metadata</a> of <a href="https://tools.ietf.org/html/rfc7591"
     * >RFC 7591</a> (OAuth 2.0 Dynamic Client Registration Protocol).
     * </p>
     *
     * @param softwareVersion
     *         A version of the client software.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.24
     */
    public Client setSoftwareVersion(String softwareVersion)
    {
        this.softwareVersion = softwareVersion;

        return this;
    }


    /**
     * Get the JWS {@code alg} algorithm for signing authorization responses.
     * This property corresponds to {@code authorization_signed_response_alg} in
     * <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html#client-metadata"
     * >5. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a>.
     *
     * @return
     *         The JWS {@code alg} algorithm for signing authorization responses.
     *
     * @since 2.27
     */
    public JWSAlg getAuthorizationSignAlg()
    {
        return authorizationSignAlg;
    }


    /**
     * Set the JWS {@code alg} algorithm for signing authorization responses.
     * This property corresponds to {@code authorization_signed_response_alg} in
     * <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html#client-metadata"
     * >5. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a>.
     *
     * @param alg
     *         The JWS {@code alg} algorithm for signing authorization responses.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.27
     */
    public Client setAuthorizationSignAlg(JWSAlg alg)
    {
        this.authorizationSignAlg = alg;

        return this;
    }


    /**
     * Get the JWE {@code alg} algorithm for encrypting authorization responses.
     * This property corresponds to {@code authorization_encrypted_response_alg} in
     * <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html#client-metadata"
     * >5. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a>.
     *
     * @return
     *         The JWE {@code alg} algorithm for encrypting authorization responses.
     *
     * @since 2.27
     */
    public JWEAlg getAuthorizationEncryptionAlg()
    {
        return authorizationEncryptionAlg;
    }


    /**
     * Set the JWE {@code alg} algorithm for encrypting authorization responses.
     * This property corresponds to {@code authorization_encrypted_response_alg} in
     * <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html#client-metadata"
     * >5. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a>.
     *
     * @param alg
     *         The JWE {@code alg} algorithm for encrypting authorization responses.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.27
     */
    public Client setAuthorizationEncryptionAlg(JWEAlg alg)
    {
        this.authorizationEncryptionAlg = alg;

        return this;
    }


    /**
     * Get the JWE {@code enc} algorithm for encrypting authorization responses.
     * This property corresponds to {@code authorization_encrypted_response_enc} in
     * <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html#client-metadata"
     * >5. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a>.
     *
     * @return
     *         The JWE {@code enc} algorithm for encrypting authorization responses.
     *
     * @since 2.27
     */
    public JWEEnc getAuthorizationEncryptionEnc()
    {
        return authorizationEncryptionEnc;
    }


    /**
     * Set the JWE {@code enc} algorithm for encrypting authorization responses.
     * This property corresponds to {@code authorization_encrypted_response_enc} in
     * <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html#client-metadata"
     * >5. Client Metadata</a> of <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a>.
     *
     * @param enc
     *         The JWE {@code enc} algorithm for encrypting authorization responses.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.27
     */
    public Client setAuthorizationEncryptionEnc(JWEEnc enc)
    {
        this.authorizationEncryptionEnc = enc;

        return this;
    }


    /**
     * Get the backchannel token delivery mode. This property corresponds
     * to the {@code backchannel_token_delivery_mode} metadata.
     *
     * <p>
     * The backchannel token delivery mode is defined in the specification
     * of the CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @return
     *         The backchannel token delivery mode.
     *
     *　@since 2.32
     */
    public DeliveryMode getBcDeliveryMode()
    {
        return bcDeliveryMode;
    }


    /**
     * Set the backchannel token delivery mode. This property corresponds
     * to the {@code backchannel_token_delivery_mode} metadata.
     *
     * <p>
     * The backchannel token delivery mode is defined in the specification
     * of CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @param mode
     *         The backchannel token delivery mode.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Client setBcDeliveryMode(DeliveryMode mode)
    {
        this.bcDeliveryMode = mode;

        return this;
    }


    /**
     * Get the backchannel client notification endpoint. This property
     * corresponds to the {@code backchannel_client_notification_endpoint}
     * metadata.
     *
     * <p>
     * The backchannel client notification endpoint is defined in the
     * specification of CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @return
     *         The backchannel client notification endpoint.
     *
     *　@since 2.32
     */
    public URI getBcNotificationEndpoint()
    {
        return bcNotificationEndpoint;
    }


    /**
     * Set the backchannel client notification endpoint. This property
     * corresponds to the {@code backchannel_client_notification_endpoint}
     * metadata.
     *
     * <p>
     * The backchannel client notification endpoint is defined in the
     * specification of CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @param endpoint
     *         The backchannel client notification endpoint.
     *
     * @return
     *         {@code this} object.
     *
     *　@since 2.32
     */
    public Client setBcNotificationEndpoint(URI endpoint)
    {
        this.bcNotificationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the signature algorithm of the request to the backchannel
     * authentication endpoint. This property corresponds to the
     * {@code backchannel_authentication_request_signing_alg} metadata.
     *
     * @return
     *         The signature algorithm of the request to the backchannel
     *         authentication endpoint.
     *
     * @since 2.32
     */
    public JWSAlg getBcRequestSignAlg()
    {
        return bcRequestSignAlg;
    }


    /**
     * Set the signature algorithm of the request to the backchannel
     * authentication endpoint. This property corresponds to the
     * {@code backchannel_authentication_request_signing_alg} metadata.
     *
     * <p>
     * The specification of CIBA (Client Initiated Backchannel Authentication)
     * allows asymmetric algorithms only.
     * </p>
     *
     * @param alg
     *         The signature algorithm of the request to the backchannel
     *         authentication endpoint.
     *
     * @return
     *         {@code this} object.
     *
     *　@since 2.32
     */
    public Client setBcRequestSignAlg(JWSAlg alg)
    {
        this.bcRequestSignAlg = alg;

        return this;
    }


    /**
     * Get the boolean flag which indicates whether a user code is required
     * when this client makes a backchannel authentication request. This
     * property corresponds to the {@code backchannel_user_code_parameter}
     * metadata.
     *
     * @return
     *         {@code true} if a user code is required when this client
     *         makes a backchannel authentication request.
     *
     * @since 2.32
     */
    public boolean isBcUserCodeRequired()
    {
        return bcUserCodeRequired;
    }


    /**
     * Set the boolean flag which indicates whether a user code is required
     * when this client makes a backchannel authentication request. This
     * property corresponds to the {@code backchannel_user_code_parameter}
     * metadata.
     *
     * @param required
     *         {@code true} to indicate that a user code is required when
     *         this client makes a backchannel authentication request.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Client setBcUserCodeRequired(boolean required)
    {
        this.bcUserCodeRequired = required;

        return this;
    }


    /**
     * Get the flag which indicates whether this client has been registered dynamically.
     *
     * @param dynamicallyRegistered
     *         {@code true} if the client has been registered dynamically.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.39
     */
    public boolean isDynamicallyRegistered()
    {
        return dynamicallyRegistered;
    }


    /**
     * Set the flag which indicates whether this client has been registered dynamically.
     *
     * @param dynamicallyRegistered
     *         {@code true} if the client has been registered dynamically.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.39
     */
    public Client setDynamicallyRegistered(boolean dynamicallyRegistered)
    {
        this.dynamicallyRegistered = dynamicallyRegistered;

        return this;
    }


    /**
     * Get the hash of the registration access token for this client.
     *
     * @return
     *         The hash of the registration access token for this client.
     *
     * @since 2.39
     */
    public String getRegistrationAccessTokenHash()
    {
        return registrationAccessTokenHash;
    }


    /**
     * Set the hash of the registration access token for this client.
     *
     * @param registrationAccessToken
     *         The hash of the registration access token for this client.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.39
     */
    public Client setRegistrationAccessTokenHash(String registrationAccessToken)
    {
        this.registrationAccessTokenHash = registrationAccessToken;

        return this;
    }


    /**
     * Get the authorization details types that this client may use as values
     * of the {@code "type"} field in {@code "authorization_details"}.
     *
     * <p>
     * This property corresponds to the {@code "authorization_details_types"}
     * metadata. See "OAuth 2.0 Rich Authorization Requests" (RAR) for details.
     * </p>
     *
     * <p>
     * Note that the property name was renamed from {@code authorizationDataTypes}
     * to {@code authorizationDetailsTypes} to align with the change made by
     * the 5th draft of the RAR specification.
     * </p>
     *
     * @return
     *         Authorization details types used in {@code "authorization_details"}.
     *
     * @since 2.91
     */
    public String[] getAuthorizationDetailsTypes()
    {
        return authorizationDetailsTypes;
    }


    /**
     * Set the authorization details types that this client may use as values
     * of the {@code "type"} field in {@code "authorization_details"}.
     *
     * <p>
     * This property corresponds to the {@code "authorization_details_types"}
     * metadata. See "OAuth 2.0 Rich Authorization Requests" (RAR) for details.
     * </p>
     *
     * <p>
     * Note that the property name was renamed from {@code authorizationDataTypes}
     * to {@code authorizationDetailsTypes} to align with the change made by
     * the 5th draft of the RAR specification.
     * </p>
     *
     * @param types
     *         Authorization details types used in {@code "authorization_details"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.91
     */
    public Client setAuthorizationDetailsTypes(String[] types)
    {
        this.authorizationDetailsTypes = types;

        return this;
    }


    /**
     * Get the flag indicating whether this client is required to use the
     * pushed authorization request endpoint.
     *
     * <p>
     * This property corresponds to the
     * {@code require_pushed_authorization_requests} client metadata defined
     * in "OAuth 2.0 Pushed Authorization Requests".
     * </p>
     *
     * @return
     *         {@code true} if this client is required to use the pushed
     *         authorization request endpoint.
     *
     * @since 2.77
     */
    public boolean isParRequired()
    {
        return parRequired;
    }


    /**
     * Set the flag indicating whether this client is required to use the
     * pushed authorization request endpoint.
     *
     * <p>
     * This property corresponds to the
     * {@code require_pushed_authorization_requests} client metadata defined
     * in "OAuth 2.0 Pushed Authorization Requests".
     * </p>
     *
     * @param required
     *         {@code true} to indicate that this client is required to use
     *         the pushed authorization request endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.77
     */
    public Client setParRequired(boolean required)
    {
        this.parRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether authorization requests from this client
     * are always required to utilize a request object by using either
     * {@code request} or {@code request_uri} request parameter.
     *
     * <p>
     * If this flag is {@code true} and the service's {@link
     * Service#isTraditionalRequestObjectProcessingApplied()
     * isTraditionalRequestObjectProcessingApplied()} returns {@code false},
     * authorization requests from this client are processed as if
     * {@code require_signed_request_object} client metadata of this client is
     * {@code true}. The metadata is defined in JAR (JWT Secured Authorization
     * Request).
     * </p>
     *
     * @return
     *         {@code true} if authorization requests from this client are
     *         always required to utilize a request object.
     *
     * @since 2.80
     */
    public boolean isRequestObjectRequired()
    {
        return requestObjectRequired;
    }


    /**
     * Set the flag indicating whether authorization requests from this client
     * are always required to utilize a request object by using either
     * {@code request} or {@code request_uri} request parameter.
     *
     * <p>
     * See the description of {@link #isRequestObjectRequired()} for details.
     * </p>
     *
     * @param required
     *         {@code true} to require that authorization requests from this
     *         client always utilize a request object.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.80
     */
    public Client setRequestObjectRequired(boolean required)
    {
        this.requestObjectRequired = required;

        return this;
    }


    /**
     * Get attributes.
     *
     * <p>
     * The feature of "client attributes" is available since Authlete 2.2.
     * </p>
     *
     * @return
     *         Attributes.
     *
     * @since 2.87
     */
    public Pair[] getAttributes()
    {
        return attributes;
    }


    /**
     * Set attributes.
     *
     * <p>
     * The feature of "client attributes" is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         Attributes.
     *
     * @return
     *     {@code this} object.
     *
     * @since 2.87
     */
    public Client setAttributes(Pair[] attributes)
    {
        this.attributes = attributes;

        return this;
    }


    /**
     * Load attributes from an iterable.
     *
     * <p>
     * The feature of "client attributes" is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         Attributes.
     *
     * @return
     *     {@code this} object.
     *
     * @since 2.89
     */
    public Client loadAttributes(Iterable<Pair> attributes)
    {
        if (attributes == null)
        {
            this.attributes = null;

            return this;
        }

        List<Pair> list = new ArrayList<Pair>();

        for (Pair attribute : attributes)
        {
            if (attribute == null || attribute.getKey() == null)
            {
                continue;
            }

            list.add(attribute);
        }

        int size = list.size();

        if (size == 0)
        {
            this.attributes = null;

            return this;
        }

        Pair[] array = new Pair[size];
        this.attributes = list.toArray(array);

        return this;
    }


    /**
     * Get the custom client metadata in JSON format.
     *
     * <p>
     * Standard specifications define client metadata as necessary.
     * The following are such examples.
     * </p>
     *
     * <ol>
     * <li><a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     *     >OpenID Connect Dynamic Client Registration 1.0</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc7591.html"
     *     >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc8705.html"
     *     >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     * <li><a href="https://openid.net/specs/openid-client-initiated-backchannel-authentication-core-1_0.html"
     *     >OpenID Connect Client-Initiated Backchannel Authentication Flow - Core 1.0</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc9101.html"
     *     >RFC 9101 The OAuth 2.0 Authorization Framework: JWT-Secured Authorization Request (JAR)</a>
     * <li><a href="https://openid.net/specs/openid-financial-api-jarm.html"
     *     >Financial-grade API: JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc9126.html"
     *     >RFC 9126 OAuth 2.0 Pushed Authorization Requests</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc9396.html"
     *      >RFC 9396 OAuth 2.0 Rich Authorization Requests</a>
     * </ol>
     *
     * <p>
     * Standard client metadata included in Client Registration Request and
     * Client Update Request (cf. <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html">OIDC
     * DynReg</a>, <a href="https://www.rfc-editor.org/rfc/rfc7591.html">RFC
     * 7591</a> and <a href=""https://www.rfc-editor.org/rfc/rfc7592.html"
     * >RFC 7592</a>) are, if supported by Authlete, set to corresponding
     * properties of the client application. For example, the value of
     * the {@code client_name} client metadata in Client Registration/Update
     * Request is set to the {@code clientName} property. On the other hand,
     * unrecognized client metadata are discarded.
     * </p>
     *
     * <p>
     * By listing up custom client metadata in advance by using the
     * {@code supportedCustomClientMetadata} property of {@link Service},
     * Authlete can recognize them and stores their values into the database.
     * The stored custom client metadata values can be referenced by this
     * method.
     * </p>
     *
     * @return
     *         Custom client metadata in JSON format.
     *
     * @see Service#getSupportedCustomClientMetadata()
     *
     * @since 2.93
     */
    public String getCustomMetadata()
    {
        return customMetadata;
    }


    /**
     * Set the custom client metadata in JSON format.
     *
     * <p>
     * Standard specifications define client metadata as necessary.
     * The following are such examples.
     * </p>
     *
     * <ol>
     * <li><a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     *     >OpenID Connect Dynamic Client Registration 1.0</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc7591.html"
     *     >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc8705.html"
     *     >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     * <li><a href="https://openid.net/specs/openid-client-initiated-backchannel-authentication-core-1_0.html"
     *     >OpenID Connect Client-Initiated Backchannel Authentication Flow - Core 1.0</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc9101.html"
     *     >RFC 9101 The OAuth 2.0 Authorization Framework: JWT-Secured Authorization Request (JAR)</a>
     * <li><a href="https://openid.net/specs/openid-financial-api-jarm.html"
     *     >Financial-grade API: JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc9126.html"
     *     >RFC 9126 OAuth 2.0 Pushed Authorization Requests</a>
     * <li><a href="https://www.rfc-editor.org/rfc/rfc9396.html"
     *      >RFC 9396 OAuth 2.0 Rich Authorization Requests</a>
     * </ol>
     *
     * <p>
     * Standard client metadata included in Client Registration Request and
     * Client Update Request (cf. <a href=
     * "https://openid.net/specs/openid-connect-registration-1_0.html">OIDC
     * DynReg</a>, <a href="https://www.rfc-editor.org/rfc/rfc7591.html">RFC
     * 7591</a> and <a href=""https://www.rfc-editor.org/rfc/rfc7592.html"
     * >RFC 7592</a>) are, if supported by Authlete, set to corresponding
     * properties of the client application. For example, the value of
     * the {@code client_name} client metadata in Client Registration/Update
     * Request is set to the {@code clientName} property. On the other hand,
     * unrecognized client metadata are discarded.
     * </p>
     *
     * <p>
     * By listing up custom client metadata in advance by using the
     * {@code supportedCustomClientMetadata} property of {@link Service},
     * Authlete can recognize them and stores their values into the database.
     * The stored custom client metadata values can be referenced by
     * {@link #getCustomMetadata()}.
     * </p>
     *
     * @param metadata
     *         Custom client metadata in JSON format.
     *
     * @return
     *         {@code this} object.
     *
     * @see Service#getSupportedCustomClientMetadata()
     *
     * @since 2.93
     */
    public Client setCustomMetadata(String metadata)
    {
        this.customMetadata = metadata;

        return this;
    }


    /**
     * Get the flag indicating whether encryption of request object is required
     * when the request object is passed through the front channel.
     *
     * <p>
     * This flag does not affect the processing of request objects at the
     * Pushed Authorization Request Endpoint, which is defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc9126.html">RFC 9126 OAuth 2.0
     * Pushed Authorization Requests</a>. Unecrypted request objects are
     * accepted at the endpoint even if this flag is {@code true}.
     * </p>
     *
     * <p>
     * This flag does not indicate whether a request object is always required.
     * There is a different flag, {@code requestObjectRequired}, for the purpose.
     * See the description of {@link #isRequestObjectRequired()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, encryption of request object is
     * required if the {@code Service.frontChannelRequestObjectEncryptionRequired}
     * flag is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if encryption of request object is required when
     *         the request object is passed through the front channel.
     *
     * @see #isRequestObjectRequired()
     * @see Service#isFrontChannelRequestObjectEncryptionRequired()
     *
     * @since 2.96
     */
    public boolean isFrontChannelRequestObjectEncryptionRequired()
    {
        return frontChannelRequestObjectEncryptionRequired;
    }


    /**
     * Set the flag indicating whether encryption of request object is required
     * when the request object is passed through the front channel.
     *
     * <p>
     * This flag does not affect the processing of request objects at the
     * Pushed Authorization Request Endpoint, which is defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc9126.html">RFC 9126 OAuth 2.0
     * Pushed Authorization Requests</a>. Unecrypted request objects are
     * accepted at the endpoint even if this flag is {@code true}.
     * </p>
     *
     * <p>
     * This flag does not indicate whether a request object is always required.
     * There is a different flag, {@code requestObjectRequired}, for the purpose.
     * See the description of {@link #isRequestObjectRequired()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, encryption of request object is
     * required if the {@code Service.frontChannelRequestObjectEncryptionRequired}
     * flag is {@code true}.
     * </p>
     *
     * @param required
     *         {@code true} to require that request objects passed through the
     *         front channel be encrypted.
     *
     * @return
     *         {@code this} object.
     *
     * @see #isRequestObjectRequired()
     * @see Service#isFrontChannelRequestObjectEncryptionRequired()
     *
     * @since 2.96
     */
    public Client setFrontChannelRequestObjectEncryptionRequired(boolean required)
    {
        this.frontChannelRequestObjectEncryptionRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether the JWE {@code alg} of encrypted request
     * object must match the {@code request_object_encryption_alg} client metadata.
     *
     * <p>
     * The {@code request_object_encryption_alg} client metadata itself is defined
     * in <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     * >OpenID Connect Dynamic Client Registration 1.0</a> as follows.
     * </p>
     *
     * <blockquote>
     * <dl>
     * <dt>{@code request_object_encryption_alg}</dt>
     * <dd>
     * <p>
     * OPTIONAL. JWE [JWE] {@code alg} algorithm [JWA] the RP is declaring that
     * it may use for encrypting Request Objects sent to the OP. This parameter
     * SHOULD be included when symmetric encryption will be used, since this
     * signals to the OP that a {@code client_secret} value needs to be returned
     * from which the symmetric key will be derived, that might not otherwise be
     * returned. The RP MAY still use other supported encryption algorithms or
     * send unencrypted Request Objects, even when this parameter is present.
     * If both signing and encryption are requested, the Request Object will be
     * signed then encrypted, with the result being a Nested JWT, as defined in
     * [JWT]. The default, if omitted, is that the RP is not declaring whether
     * it might encrypt any Request Objects.
     * </p>
     * </dd>
     * </dl>
     * </blockquote>
     *
     * <p>
     * The point here is <i>"The RP MAY still use other supported encryption
     * algorithms or send unencrypted Request Objects, even when this parameter
     * is present."</i>
     * </p>
     *
     * <p>
     * The property that represents the client metadata is
     * {@code requestEncryptionAlg}. See the description of
     * {@link #getRequestEncryptionAlg()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Service.requestObjectEncryptionAlgMatchRequired} flag is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if the JWE {@code alg} of encrypted request object
     *         must match the {@code request_object_encryption_alg} client metadata.
     *
     * @see #getRequestEncryptionAlg()
     * @see Service#isRequestObjectEncryptionAlgMatchRequired()
     *
     * @since 2.96
     */
    public boolean isRequestObjectEncryptionAlgMatchRequired()
    {
        return requestObjectEncryptionAlgMatchRequired;
    }


    /**
     * Set the flag indicating whether the JWE {@code alg} of encrypted request
     * object must match the {@code request_object_encryption_alg} client metadata.
     *
     * <p>
     * The {@code request_object_encryption_alg} client metadata itself is defined
     * in <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     * >OpenID Connect Dynamic Client Registration 1.0</a> as follows.
     * </p>
     *
     * <blockquote>
     * <dl>
     * <dt>{@code request_object_encryption_alg}</dt>
     * <dd>
     * <p>
     * OPTIONAL. JWE [JWE] {@code alg} algorithm [JWA] the RP is declaring that
     * it may use for encrypting Request Objects sent to the OP. This parameter
     * SHOULD be included when symmetric encryption will be used, since this
     * signals to the OP that a {@code client_secret} value needs to be returned
     * from which the symmetric key will be derived, that might not otherwise be
     * returned. The RP MAY still use other supported encryption algorithms or
     * send unencrypted Request Objects, even when this parameter is present.
     * If both signing and encryption are requested, the Request Object will be
     * signed then encrypted, with the result being a Nested JWT, as defined in
     * [JWT]. The default, if omitted, is that the RP is not declaring whether
     * it might encrypt any Request Objects.
     * </p>
     * </dd>
     * </dl>
     * </blockquote>
     *
     * <p>
     * The point here is <i>"The RP MAY still use other supported encryption
     * algorithms or send unencrypted Request Objects, even when this parameter
     * is present."</i>
     * </p>
     *
     * <p>
     * The property that represents the client metadata is
     * {@code requestEncryptionAlg}. See the description of
     * {@link #getRequestEncryptionAlg()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Service.requestObjectEncryptionAlgMatchRequired} flag is {@code true}.
     * </p>
     *
     * @param required
     *         {@code true} to require that the JWE {@code alg} of encrypted
     *         request object match the {@code request_object_encryption_alg}
     *         client metadata.
     *
     * @return
     *         {@code this} object.
     *
     * @see #getRequestEncryptionAlg()
     * @see Service#isRequestObjectEncryptionAlgMatchRequired()
     *
     * @since 2.96
     */
    public Client setRequestObjectEncryptionAlgMatchRequired(boolean required)
    {
        this.requestObjectEncryptionAlgMatchRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether the JWE {@code enc} of encrypted request
     * object must match the {@code request_object_encryption_enc} client metadata.
     *
     * <p>
     * The {@code request_object_encryption_enc} client metadata itself is defined
     * in <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     * >OpenID Connect Dynamic Client Registration 1.0</a> as follows.
     * </p>
     *
     * <blockquote>
     * <dl>
     * <dt>{@code request_object_encryption_enc}</dt>
     * <dd>
     * <p>
     * OPTIONAL. JWE {@code enc} algorithm [JWA] the RP is declaring that it may
     * use for encrypting Request Objects sent to the OP. If
     * {@code request_object_encryption_alg} is specified, the default for this
     * value is {@code A128CBC-HS256}. When {@code request_object_encryption_enc}
     * is included, {@code request_object_encryption_alg} MUST also be provided.
     * </p>
     * </dd>
     * </dl>
     * </blockquote>
     *
     * <p>
     * The property that represents the client metadata is
     * {@code requestEncryptionEnc}. See the description of
     * {@link #getRequestEncryptionEnc()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Service.requestObjectEncryptionEncMatchRequired} flag is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if the JWE {@code enc} of encrypted request object
     *         must match the {@code request_object_encryption_enc} client metadata.
     *
     * @see #getRequestEncryptionEnc()
     * @see Service#isRequestObjectEncryptionEncMatchRequired()
     *
     * @since 2.96
     */
    public boolean isRequestObjectEncryptionEncMatchRequired()
    {
        return requestObjectEncryptionEncMatchRequired;
    }


    /**
     * Set the flag indicating whether the JWE {@code enc} of encrypted request
     * object must match the {@code request_object_encryption_enc} client metadata.
     *
     * <p>
     * The {@code request_object_encryption_enc} client metadata itself is defined
     * in <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     * >OpenID Connect Dynamic Client Registration 1.0</a> as follows.
     * </p>
     *
     * <blockquote>
     * <dl>
     * <dt>{@code request_object_encryption_enc}</dt>
     * <dd>
     * <p>
     * OPTIONAL. JWE {@code enc} algorithm [JWA] the RP is declaring that it may
     * use for encrypting Request Objects sent to the OP. If
     * {@code request_object_encryption_alg} is specified, the default for this
     * value is {@code A128CBC-HS256}. When {@code request_object_encryption_enc}
     * is included, {@code request_object_encryption_alg} MUST also be provided.
     * </p>
     * </dd>
     * </dl>
     * </blockquote>
     *
     * <p>
     * The property that represents the client metadata is
     * {@code requestEncryptionEnc}. See the description of
     * {@link #getRequestEncryptionEnc()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Service.requestObjectEncryptionEncMatchRequired} flag is {@code true}.
     * </p>
     *
     * @param required
     *         {@code true} to require that the JWE {@code enc} of encrypted
     *         request object match the {@code request_object_encryption_enc}
     *         client metadata.
     *
     * @return
     *         {@code this} object.
     *
     * @see #getRequestEncryptionEnc()
     * @see Service#isRequestObjectEncryptionEncMatchRequired()
     *
     * @since 2.96
     */
    public Client setRequestObjectEncryptionEncMatchRequired(boolean required)
    {
        this.requestObjectEncryptionEncMatchRequired = required;

        return this;
    }


    /**
     * Get the digest algorithm that this client requests the server to use
     * when it computes digest values of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#name-external-attachments"
     * >external attachments</a>, which may be referenced from within ID tokens
     * or userinfo responses (or any place that can have the {@code
     * verified_claims} claim).
     *
     * <p>
     * Possible values are listed in the <a href=
     * "https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg"
     * >Hash Algorithm Registry</a> of IANA (Internet Assigned Numbers Authority),
     * but the server does not necessarily support all the values there. When
     * this property is omitted, {@code "sha-256"} is used as the default
     * algorithm.
     * </p>
     *
     * <p>
     * This property corresponds to the {@code digest_algorithm} client metadata
     * which was defined by the third implementer's draft of &quot;<a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>&quot;.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @return
     *         The digest algorithm that this client requests the server to use
     *         when it computes digest values of external attachments.
     *
     * @since 3.13
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see <a href="https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg"
     *      >Hash Algorithm Registry</a>
     */
    public String getDigestAlgorithm()
    {
        return digestAlgorithm;
    }


    /**
     * Set the digest algorithm that this client requests the server to use
     * when it computes digest values of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#name-external-attachments"
     * >external attachments</a>, which may be referenced from within ID tokens
     * or userinfo responses (or any place that can have the {@code
     * verified_claims} claim).
     *
     * <p>
     * Possible values are listed in the <a href=
     * "https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg"
     * >Hash Algorithm Registry</a> of IANA (Internet Assigned Numbers Authority),
     * but the server does not necessarily support all the values there. When
     * this property is omitted, {@code "sha-256"} is used as the default
     * algorithm.
     * </p>
     *
     * <p>
     * This property corresponds to the {@code digest_algorithm} client metadata
     * which was defined by the third implementer's draft of &quot;<a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>&quot;.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @param algorithm
     *         The digest algorithm that this client requests the server to use
     *         when it computes digest values of external attachments.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.13
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see <a href="https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg"
     *      >Hash Algorithm Registry</a>
     */
    public Client setDigestAlgorithm(String algorithm)
    {
        this.digestAlgorithm = algorithm;

        return this;
    }


    /**
     * Get the flag which indicates whether the number of access tokens
     * per subject (and per client) is at most one or can be more.
     *
     * <p>
     * If this flag is {@code true}, an attempt to issue a new access
     * token invalidates existing access tokens associated with the
     * same subject and the same client.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, invalidation of existing access
     * tokens is executed if the {@code singleAccessTokenPerSubject}
     * property of the {@link Service} this client application belongs to
     * is {@code true}. (cf. {@link Service#isSingleAccessTokenPerSubject()})
     * </p>
     *
     * <p>
     * Note that, however, attempts by Client Credentials Flow do not
     * invalidate existing access tokens because access tokens issued
     * by Client Credentials Flow are not associated with any end-user's
     * subject. Also note that an attempt by Refresh Token Flow
     * invalidates the coupled access token only and this invalidation
     * is always performed regardless of whether this flag is {@code
     * true} or {@code false}.
     * </p>
     *
     * @return
     *         {@code true} if the number of access tokens per subject
     *         (and per client) is at most one.
     *
     * @since 3.25
     * @since Authlete 2.3
     *
     * @see Service#isSingleAccessTokenPerSubject()
     */
    public boolean isSingleAccessTokenPerSubject()
    {
        return singleAccessTokenPerSubject;
    }


    /**
     * Set the flag which indicates whether the number of access tokens
     * per subject (and per client) is at most one or can be more.
     *
     * <p>
     * If {@code true} is set, an attempt to issue a new access token
     * invalidates existing access tokens associated with the same
     * subject and the same client.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, invalidation of existing access
     * tokens is executed if the {@code singleAccessTokenPerSubject}
     * property of the {@link Service} this client application belongs to
     * is {@code true}. (cf. {@link Service#setSingleAccessTokenPerSubject(boolean)})
     * </p>
     *
     * <p>
     * Note that, however, attempts by Client Credentials Flow do not
     * invalidate existing access tokens because access tokens issued
     * by Client Credentials Flow are not associated with any end-user's
     * subject. Also note that an attempt by Refresh Token Flow
     * invalidates the coupled access token only and this invalidation
     * is always performed regardless of whether this flag is {@code
     * true} or {@code false}.
     * </p>
     *
     * @param single
     *         {@code true} to set the maximum number of access tokens
     *         per subject (and per client) to 1.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.25
     * @since Authlete 2.3
     *
     * @see Service#setSingleAccessTokenPerSubject(boolean)
     */
    public Client setSingleAccessTokenPerSubject(boolean single)
    {
        this.singleAccessTokenPerSubject = single;

        return this;
    }


    /**
     * Get the flag indicating whether PKCE (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7636.html">RFC 7636</a>) is required
     * whenever this client makes an authorization request by the authorization
     * code flow.
     *
     * <p>
     * Note that even if this flag is {@code false}, PKCE is required if
     * {@link Service#isPkceRequired() Service.pkceRequired} is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if PKCE is required whenever this client makes
     *         an authorization request by the authorization code flow.
     *
     * @since 3.29
     * @since Authlete 2.3
     *
     * @see Service#isPkceRequired()
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7636.html"
     *      >RFC 7636 Proof Key for Code Exchange by OAuth Public Clients</a>
     */
    public boolean isPkceRequired()
    {
        return pkceRequired;
    }


    /**
     * Set the flag indicating whether PKCE (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7636.html">RFC 7636</a>) is required
     * whenever this client makes an authorization request by the authorization
     * code flow.
     *
     * <p>
     * Note that even if this flag is {@code false}, PKCE is required if
     * {@link Service#isPkceRequired() Service.pkceRequired} is {@code true}.
     * </p>
     *
     * @param required
     *         {@code true} to require PKCE whenever this client makes an
     *         authorization request by the authorization code flow.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.29
     * @since Authlete 2.3
     *
     * @see Service#setPkceRequired(boolean)
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7636.html"
     *      >RFC 7636 Proof Key for Code Exchange by OAuth Public Clients</a>
     */
    public Client setPkceRequired(boolean required)
    {
        this.pkceRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether {@code S256} must be used as the code
     * challenge method whenever this client uses PKCE (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7636.html">RFC 7636</a>).
     *
     * <p>
     * Note that even if this flag is {@code false}, {@code S256} is required
     * if {@link Service#isPkceS256Required() Service.pkceS256Required} is
     * {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if {@code S256} must be used as the code challenge
     *         method whenever this client uses PKCE.
     *
     * @since 3.29
     * @since Authlete 2.3
     *
     * @see Service#setPkceS256Required(boolean)
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7636.html"
     *      >RFC 7636 Proof Key for Code Exchange by OAuth Public Clients</a>
     */
    public boolean isPkceS256Required()
    {
        return pkceS256Required;
    }


    /**
     * Set the flag indicating whether {@code S256} must be used as the code
     * challenge method whenever this client uses PKCE (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7636.html">RFC 7636</a>).
     *
     * @param required
     *         {@code true} to require {@code S256} as the code challenge
     *         method whenever this client uses PKCE.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.29
     * @since Authlete 2.3
     *
     * @see Service#setPkceS256Required(boolean)
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7636.html"
     *      >RFC 7636 Proof Key for Code Exchange by OAuth Public Clients</a>
     */
    public Client setPkceS256Required(boolean required)
    {
        this.pkceS256Required = required;

        return this;
    }


    /**
     * Get the entity ID of this client.
     *
     * <p>
     * This property holds a non-null value only when this client has been
     * registered by the mechanism defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @return
     *         The entity ID.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public URI getEntityId()
    {
        return entityId;
    }


    /**
     * Set the entity ID of this client.
     *
     * <p>
     * This property holds a non-null value only when this client has been
     * registered by the mechanism defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @param entityId
     *         The entity ID.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setEntityId(URI entityId)
    {
        this.entityId = entityId;

        return this;
    }


    /**
     * Get the entity ID of the trust anchor of the trust chain that was used
     * when this client was registered or updated by the mechanism defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>.
     *
     * @return
     *         The entity ID of the trust anchor.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public URI getTrustAnchorId()
    {
        return trustAnchorId;
    }


    /**
     * Set the entity ID of the trust anchor of the trust chain that was used
     * when this client was registered or updated by the mechanism defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>.
     *
     * @param trustAnchorId
     *         The entity ID of the trust anchor.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setTrustAnchorId(URI trustAnchorId)
    {
        this.trustAnchorId = trustAnchorId;

        return this;
    }


    /**
     * Get the trust chain that was used when this client was registered or
     * updated by the mechanism defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>.
     *
     * <p>
     * The elements in the array are entity statements that are ordered from
     * the entity configuration of this client to the entity statement of the
     * trust anchor. There may be one or more entity statements of intermediate
     * entities in between. The format of the elements is a signed JWT (JWS).
     * </p>
     *
     * @return
     *         The trust chain.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public String[] getTrustChain()
    {
        return trustChain;
    }


    /**
     * Set the trust chain that was used when this client was registered or
     * updated by the mechanism defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>.
     *
     * <p>
     * The elements in the array are entity statements that are ordered from
     * the entity configuration of this client to the entity statement of the
     * trust anchor. There may be one or more entity statements of intermediate
     * entities in between. The format of the elements is a signed JWT (JWS).
     * </p>
     *
     * @param trustChain
     *         The trust chain.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setTrustChain(String[] trustChain)
    {
        this.trustChain = trustChain;

        return this;
    }


    /**
     * Get the expiration time of the trust chain that was used when this client
     * was registered or updated by the mechanism defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>. The value is represented as
     * milliseconds elapsed since the Unix epoch (1970-01-01).
     *
     * @return
     *         The expiration time of the trust chain.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public long getTrustChainExpiresAt()
    {
        return trustChainExpiresAt;
    }


    /**
     * Set the expiration time of the trust chain that was used when this client
     * was registered or updated by the mechanism defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>. The value is represented as
     * milliseconds elapsed since the Unix epoch (1970-01-01).
     *
     * @param expiresAt
     *         The expiration time of the trust chain.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setTrustChainExpiresAt(long expiresAt)
    {
        this.trustChainExpiresAt = expiresAt;

        return this;
    }


    /**
     * Get the time at which the trust chain was updated by the mechanism defined
     * in <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>.
     *
     * @return
     *         The time at which the trust chain was updated.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public long getTrustChainUpdatedAt()
    {
        return trustChainUpdatedAt;
    }


    /**
     * Set the time at which the trust chain was updated by the mechanism defined
     * in <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x002E;0</a>.
     *
     * @param updatedAt
     *         The time at which the trust chain was updated.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.33
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setTrustChainUpdatedAt(long updatedAt)
    {
        this.trustChainUpdatedAt = updatedAt;

        return this;
    }


    /**
     * Get the human-readable name representing the organization that manages
     * this client. This property corresponds to the {@code organization_name}
     * client metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * @return
     *         The name of the organization that manages this client.
     *
     * @since 3.34
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public String getOrganizationName()
    {
        return organizationName;
    }


    /**
     * Set the human-readable name representing the organization that manages
     * this client. This property corresponds to the {@code organization_name}
     * client metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * @param name
     *         The name of the organization that manages this client.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.34
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setOrganizationName(String name)
    {
        this.organizationName = name;

        return this;
    }


    /**
     * Get the URI of the endpoint that returns this client's JWK Set document in
     * the JWT format. This property corresponds to the {@code signed_jwks_uri}
     * client metadata defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * @return
     *         The URI of the endpoint that returns this client's JWK Set
     *         document in the JWT format.
     *
     * @since 3.34
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public URI getSignedJwksUri()
    {
        return signedJwksUri;
    }


    /**
     * Set the URI of the endpoint that returns this client's JWK Set document in
     * the JWT format. This property corresponds to the {@code signed_jwks_uri}
     * client metadata defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * @param uri
     *         The URI of the endpoint that returns this client's JWK Set
     *         document in the JWT format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.34
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setSignedJwksUri(URI uri)
    {
        this.signedJwksUri = uri;

        return this;
    }


    /**
     * Get the client registration types that the client has declared it may use.
     *
     * <p>
     * This property corresponds to the {@code client_registration_types} client
     * metadata defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @return
     *         Client registration types.
     *
     * @since 3.36
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public ClientRegistrationType[] getClientRegistrationTypes()
    {
        return clientRegistrationTypes;
    }


    /**
     * Set the client registration types that the client has declared it may use.
     *
     * <p>
     * This property corresponds to the {@code client_registration_types} client
     * metadata defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @param types
     *         Client registration types.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setClientRegistrationTypes(ClientRegistrationType[] types)
    {
        this.clientRegistrationTypes = types;

        return this;
    }


    /**
     * Get the key ID of a JWK containing the public key used by this client to
     * sign requests to the resource server.
     *
     * @return
     *         The key ID.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    public String getRsSignedRequestKeyId()
    {
        return rsSignedRequestKeyId;
    }


    /**
     * Set the key ID of a JWK containing the public key used by this client to
     * sign requests to the resource server.
     *
     * @param keyId
     *         The key ID.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    public Client setRsSignedRequestKeyId(String keyId)
    {
        this.rsSignedRequestKeyId = keyId;

        return this;
    }


    /**
     * Get whether the client is expected to sign requests to the resource server.
     * If {@code true}, introspection requests and userinfo requests will be checked
     * for a signature and the signature will be validated against the key identified
     * by {@link #getRsSignedRequestKeyId()}.
     *
     * @return
     *         {@code true} if the client signs requests to the resource server,
     *         {@code false} otherwise.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    public boolean isRsRequestSigned()
    {
        return rsRequestSigned;
    }


    /**
     * Set whether the client is expected to sign requests to the resource server.
     * If {@code true}, introspection requests and userinfo requests will be checked
     * for a signature and the will be signature validated against the key identified
     * by {@link #getRsSignedRequestKeyId()}.
     *
     * @param signed
     *         {@code true} if the client signs requests to the resource server,
     *         {@code false} otherwise.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    public Client setRsRequestSigned(boolean signed)
    {
        this.rsRequestSigned = signed;

        return this;
    }


    /**
     * Get the flag indicating whether this client was registered by the
     * "automatic" client registration of OpenID Federation.
     *
     * @return
     *         {@code true} if this client was registered by the automatic
     *         client registration.
     *
     * @since 3.46
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public boolean isAutomaticallyRegistered()
    {
        return automaticallyRegistered;
    }


    /**
     * Set the flag indicating whether this client was registered by the
     * "automatic" client registration of OpenID Federation.
     *
     * @param auto
     *         {@code true} to indicate that this client was registered by
     *         the automatic client registration.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.46
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setAutomaticallyRegistered(boolean auto)
    {
        this.automaticallyRegistered = auto;

        return this;
    }


    /**
     * Get the flag indicating whether this client was registered by the
     * "explicit" client registration of OpenID Federation.
     *
     * @return
     *         {@code true} if this client was registered by the explicit
     *         client registration.
     *
     * @since 3.46
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public boolean isExplicitlyRegistered()
    {
        return explicitlyRegistered;
    }


    /**
     * Set the flag indicating whether this client was registered by the
     * "explicit" client registration of OpenID Federation.
     *
     * @param explicit
     *         {@code true} to indicate that this client was registered by
     *         the explicit client registration.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.46
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Client setExplicitlyRegistered(boolean explicit)
    {
        this.explicitlyRegistered = explicit;

        return this;
    }


    /**
     * Set the flag indicating whether this client requires DPoP access tokens.
     *
     * @return
     *         {@code true} to indicate that this client requires DPoP access tokens.
     *
     * @since 3.49
     * @since Authlete 2.3
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public boolean isDpopRequired()
    {
        return dpopRequired;
    }


    /**
     * Get the flag indicating whether this client requires DPoP access tokens.
     *
     * @param required
     *            {@code true} to indicate that this client requires DPoP access tokens.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.49
     * @since Authlete 2.3
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public Client setDpopRequired(boolean dpopRequired)
    {
        this.dpopRequired = dpopRequired;

        return this;
    }


    /**
     * Get the URL of the credential offer endpoint at which this client
     * (wallet) receives a credential offer from the credential issuer.
     *
     * <p>
     * This property corresponds to the {@code credential_offer_endpoint}
     * client metadata that is defined in <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credential Issuance</a>.
     * </p>
     *
     * @return
     *         The URL of the credential offer endpoint.
     *
     * @since 3.59
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     */
    public URI getCredentialOfferEndpoint()
    {
        return credentialOfferEndpoint;
    }


    /**
     * Set the URL of the credential offer endpoint at which this client
     * (wallet) receives a credential offer from the credential issuer.
     *
     * <p>
     * This property corresponds to the {@code credential_offer_endpoint}
     * client metadata that is defined in <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credential Issuance</a>.
     * </p>
     *
     * @param endpoint
     *         The URL of the credential offer endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.59
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     */
    public Client setCredentialOfferEndpoint(URI endpoint)
    {
        this.credentialOfferEndpoint = endpoint;

        return this;
    }


    /**
     * Get the flag which indicates whether this client is locked.
     *
     * @return
     *         {@code true} if this client is locked.
     *
     * @since 3.75
     */
    public boolean isLocked()
    {
        return locked;
    }


    /**
     * Set the flag which indicates whether this client is locked.
     *
     * @param locked
     *         {@code true} to indicate that this client is locked.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.75
     */
    public Client setLocked(boolean locked)
    {
        this.locked = locked;

        return this;
    }


    /**
     * Get the FAPI modes for this client.
     *
     * <p>
     * When the value of this property is not {@code null}, Authlete always processes
     * requests from this client based on the specified FAPI modes if the FAPI feature
     * is enabled in Authlete, the {@link ServiceProfile#FAPI FAPI} profile is supported
     * by the service, and the FAPI modes for the service are set to {@code null}.
     * </p>
     * <p>
     * For instance, when this property is set to an array containing {@link
     * FapiMode#FAPI1_ADVANCED FAPI1_ADVANCED} only, Authlete always processes
     * requests from this client based on "<a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>" if the FAPI feature
     * is enabled in Authlete, the {@link ServiceProfile#FAPI FAPI} profile is supported
     * by the service, and the FAPI modes for the service are set to {@code null}.
     * </p>
     *
     * @return
     *         The FAPI modes for this client.
     *
     * @since 3.80
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html"
     *      >Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>
     */
    public FapiMode[] getFapiModes()
    {
        return fapiModes;
    }


    /**
     * Set the FAPI modes for this client.
     *
     * <p>
     * When the value of this property is not {@code null}, Authlete always processes
     * requests from this client based on the specified FAPI modes if the FAPI feature
     * is enabled in Authlete, the {@link ServiceProfile#FAPI FAPI} profile is supported
     * by the service, and the FAPI modes for the service are set to {@code null}.
     * </p>
     * <p>
     * For instance, when this property is set to an array containing {@link
     * FapiMode#FAPI1_ADVANCED FAPI1_ADVANCED} only, Authlete always processes
     * requests from this client based on "<a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>" if the FAPI feature
     * is enabled in Authlete, the {@link ServiceProfile#FAPI FAPI} profile is supported
     * by the service, and the FAPI modes for the service are set to {@code null}.
     * </p>
     *
     * @param modes
     *         The FAPI modes for this client.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.80
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html"
     *      >Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>
     */
    public Client setFapiModes(FapiMode[] modes)
    {
        this.fapiModes = modes;

        return this;
    }


    /**
     * Get the flag indicating whether credential responses to this client
     * must be always encrypted or not.
     *
     * <p>
     * When this flag is {@code true}, credential requests from this client
     * must always include encryption-related parameters such as
     * {@code credential_response_encryption_alg}.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, encryption-related parameters
     * are always required when the service's
     * {@code credentialIssuerMetadata.requireCredentialResponseEncryption}
     * property is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if credential responses to this client must be
     *         always encrypted.
     *
     * @since 3.86
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     */
    public boolean isCredentialResponseEncryptionRequired()
    {
        return credentialResponseEncryptionRequired;
    }


    /**
     * Set the flag indicating whether credential responses to this client
     * must be always encrypted or not.
     *
     * <p>
     * When this flag is {@code true}, credential requests from this client
     * must always include encryption-related parameters such as
     * {@code credential_response_encryption_alg}.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, encryption-related parameters
     * are always required when the service's
     * {@code credentialIssuerMetadata.requireCredentialResponseEncryption}
     * property is {@code true}.
     * </p>
     *
     * @param required
     *         {@code true} to require credential requests from this client
     *         to always include encryption-related parameters such as
     *         {@code credential_response_encryption_alg}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.86
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     */
    public Client setCredentialResponseEncryptionRequired(boolean required)
    {
        this.credentialResponseEncryptionRequired = required;

        return this;
    }


    /**
     * Get a {@code Map} instance that represents a set of standard client
     * metadata.
     *
     * <p>
     * This method is an alias of {@link #toStandardMetadata(ClientMetadataControl)
     * toStandardMetadata}{@code (null)}.
     * </p>
     *
     * @return
     *         A {@code Map} instance that represents a set of standard client
     *         metadata.
     */
    public Map<String, Object> toStandardMetadata()
    {
        return toStandardMetadata(null);
    }


    /**
     * Get the response modes that this client may use.
     *
     * <p>
     * This property corresponds to the {@code response_modes} client metadata
     * that is defined in <a href="https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html#section-5.3.3"
     * >FAPI 2.0 Message Signing, 5.3.3. Client Metadata</a>.
     * </p>
     *
     * @return
     *         The response modes that this client may use.
     *
     * @since 3.92
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html#section-5.3.3"
     *      >FAPI 2.0 Message Signing, 5.3.3. Client Metadata</a>
     */
    public ResponseMode[] getResponseModes()
    {
        return responseModes;
    }


    /**
     * Set the response modes that this client may use.
     *
     * <p>
     * This property corresponds to the {@code response_modes} client metadata
     * that is defined in <a href="https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html#section-5.3.3"
     * >FAPI 2.0 Message Signing, 5.3.3. Client Metadata</a>.
     * </p>
     *
     * @param modes
     *         The response modes that this client may use.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.92
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.bitbucket.io/fapi/fapi-2_0-message-signing.html#section-5.3.3"
     *      >FAPI 2.0 Message Signing, 5.3.3. Client Metadata</a>
     */
    public Client setResponseModes(ResponseMode[] modes)
    {
        this.responseModes = modes;

        return this;
    }


    /**
     * Get the flag indicating whether the client intends to prefer mutual TLS
     * endpoints over non-MTLS endpoints.
     *
     * <p>
     * This property corresponds to the {@code use_mtls_endpoint_aliases} client
     * metadata that is defined in <a href="https://openid.bitbucket.io/fapi/fapi-2_0-security-profile.html#section-8.1.1"
     * >FAPI 2.0 Security Profile, 8.1.1. use_mtls_endpoint_aliases</a>.
     * </p>
     *
     * @return
     *         The flag indicating whether the client intends to prefer mutual TLS
     *         endpoints over non-MTLS endpoints.
     *
     * @since 4.10
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.bitbucket.io/fapi/fapi-2_0-security-profile.html#section-8.1.1"
     *      >FAPI 2.0 Security Profile, 8.1.1. use_mtls_endpoint_aliases</a>
     */
    public boolean isMtlsEndpointAliasesUsed()
    {
        return mtlsEndpointAliasesUsed;
    }


    /**
     * Set the flag indicating whether the client intends to prefer mutual TLS
     * endpoints over non-MTLS endpoints.
     *
     * <p>
     * This property corresponds to the {@code use_mtls_endpoint_aliases} client
     * metadata that is defined in <a href="https://openid.bitbucket.io/fapi/fapi-2_0-security-profile.html#section-8.1.1"
     * >FAPI 2.0 Security Profile, 8.1.1. use_mtls_endpoint_aliases</a>.
     * </p>
     *
     * @return
     *         The flag indicating whether the client intends to prefer mutual TLS
     *         endpoints over non-MTLS endpoints.
     *
     * @since 4.10
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.bitbucket.io/fapi/fapi-2_0-security-profile.html#section-8.1.1"
     *      >FAPI 2.0 Security Profile, 8.1.1. use_mtls_endpoint_aliases</a>
     */
    public Client setMtlsEndpointAliasUsed(boolean mtlsEndpointAliasesUsed)
    {
        this.mtlsEndpointAliasesUsed = mtlsEndpointAliasesUsed;

        return this;
    }


    /**
     * Get a {@code Map} instance that represents a set of standard client
     * metadata.
     *
     * <p>
     * This method creates a new {@code Map} instance per call. Modifying the
     * Map instance does not affect this {@link Client} instance.
     * </p>
     *
     * @param control
     *         Flags to control output of this method. If {@code null} is given,
     *         a new {@link ClientMetadataControl} instance is created and used.
     *
     * @return
     *         A {@code Map} instance that represents a set of standard client
     *         metadata.
     *
     * @since 3.45
     *
     * @see <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     *      >OpenID Connect Dynamic Client Registration 1.0</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html"
     *      >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8705.html"
     *      >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     *
     * @see <a href="https://openid.net/specs/oauth-v2-jarm.html"
     *      >JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
     *
     * @see <a href="https://openid.net/specs/openid-client-initiated-backchannel-authentication-core-1_0.html"
     *      >OpenID Connect Client-Initiated Backchannel Authentication Flow - Core 1.0</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9396.html"
     *      >RFC 9396 OAuth 2.0 Rich Authorization Requests</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     *
     * @see <a href="https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml#client-metadata"
     *      >IANA OAuth Parameters / OAuth Dynamic Client Registration Metadata</a>
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     */
    public Map<String, Object> toStandardMetadata(ClientMetadataControl control)
    {
        if (control == null)
        {
            control = new ClientMetadataControl();
        }

        boolean nullIncluded  = control.isNullIncluded();
        boolean zeroIncluded  = control.isZeroIncluded();
        boolean falseIncluded = control.isFalseIncluded();

        Map<String, Object> metadata = new LinkedHashMap<String, Object>();

        // client_id
        putClientId(metadata, control);

        //----------------------------------------------------------------------
        // OpenID Connect Dynamic Client Registration 1.0
        //----------------------------------------------------------------------

        // redirect_uris
        put(metadata, "redirect_uris", getRedirectUris(), nullIncluded);

        // response_types
        put(metadata, "response_types", getResponseTypes(), nullIncluded);

        // grant_types
        put(metadata, "grant_types", getGrantTypes(), nullIncluded);

        // application_type
        put(metadata, "application_type", getApplicationType(), nullIncluded);

        // contacts
        put(metadata, "contacts", getContacts(), nullIncluded);

        // client_name
        put(metadata, "client_name", getClientName(), nullIncluded);

        // client_name#{language-tag}
        put(metadata, "client_name", getClientNames(), nullIncluded);

        // logo_uri
        put(metadata, "logo_uri", getLogoUri(), nullIncluded);

        // logo_uri#{language-tag}
        put(metadata, "logo_uri", getLogoUris(), nullIncluded);

        // client_uri
        put(metadata, "client_uri", getClientUri(), nullIncluded);

        // client_uri#{language-tag}
        put(metadata, "client_uri", getClientUris(), nullIncluded);

        // policy_uri
        put(metadata, "policy_uri", getPolicyUri(), nullIncluded);

        // policy_uri#{language-tag}
        put(metadata, "policy_uri", getPolicyUris(), nullIncluded);

        // tos_uri
        put(metadata, "tos_uri", getTosUri(), nullIncluded);

        // tos_uri#{language-tag}
        put(metadata, "tos_uri", getTosUris(), nullIncluded);

        // jwks_uri
        put(metadata, "jwks_uri", getJwksUri(), nullIncluded);

        // jwks
        putJsonObject(metadata, "jwks", getJwks(), nullIncluded);

        // sector_identifier_uri
        put(metadata, "sector_identifier_uri", getSectorIdentifierUri(), nullIncluded);

        // subject_type
        put(metadata, "subject_type", getSubjectType(), nullIncluded);

        // id_token_signed_response_alg
        put(metadata, "id_token_signed_response_alg", getIdTokenSignAlg(), nullIncluded);

        // id_token_encrypted_response_alg
        put(metadata, "id_token_encrypted_response_alg", getIdTokenEncryptionAlg(), nullIncluded);

        // id_token_encrypted_response_enc
        put(metadata, "id_token_encrypted_response_enc", getIdTokenEncryptionEnc(), nullIncluded);

        // userinfo_signed_response_alg
        put(metadata, "userinfo_signed_response_alg", getUserInfoSignAlg(), nullIncluded);

        // userinfo_encrypted_response_alg
        put(metadata, "userinfo_encrypted_response_alg", getUserInfoEncryptionAlg(), nullIncluded);

        // userinfo_encrypted_response_enc
        put(metadata, "userinfo_encrypted_response_enc", getUserInfoEncryptionEnc(), nullIncluded);

        // request_object_signing_alg
        put(metadata, "request_object_signing_alg", getRequestSignAlg(), nullIncluded);

        // request_object_encryption_alg
        put(metadata, "request_object_encryption_alg", getRequestEncryptionAlg(), nullIncluded);

        // request_object_encryption_enc
        put(metadata, "request_object_encryption_enc", getRequestEncryptionEnc(), nullIncluded);

        // token_endpoint_auth_method
        put(metadata, "token_endpoint_auth_method", getTokenAuthMethod(), nullIncluded);

        // token_endpoint_auth_signing_alg
        put(metadata, "token_endpoint_auth_signing_alg", getTokenAuthSignAlg(), nullIncluded);

        // default_max_age
        put(metadata, "default_max_age", getDefaultMaxAge(), zeroIncluded);

        // require_auth_time
        put(metadata, "require_auth_time", isAuthTimeRequired(), falseIncluded);

        // default_acr_values
        put(metadata, "default_acr_values", getDefaultAcrs(), nullIncluded);

        // initiate_login_uri
        put(metadata, "initiate_login_uri", getLoginUri(), nullIncluded);

        // request_uris
        put(metadata, "request_uris", getRequestUris(), nullIncluded);

        //----------------------------------------------------------------------
        // RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol
        //----------------------------------------------------------------------

        // scope
        putScope(metadata, control);

        // software_id
        put(metadata, "software_id", getSoftwareId(), nullIncluded);

        // software_version
        put(metadata, "software_version", getSoftwareVersion(), nullIncluded);

        // client_secret
        putClientSecret(metadata, control);

        // client_id_issued_at
        putClientIdIssuedAt(metadata, control);

        //----------------------------------------------------------------------
        // RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens
        //----------------------------------------------------------------------

        // tls_client_auth_subject_dn
        put(metadata, "tls_client_auth_subject_dn", getTlsClientAuthSubjectDn(), nullIncluded);

        // tls_client_auth_san_dns
        put(metadata, "tls_client_auth_san_dns", getTlsClientAuthSanDns(), nullIncluded);

        // tls_client_auth_san_uri
        put(metadata, "tls_client_auth_san_uri", getTlsClientAuthSanUri(), nullIncluded);

        // tls_client_auth_san_ip
        put(metadata, "tls_client_auth_san_ip", getTlsClientAuthSanIp(), nullIncluded);

        // tls_client_auth_san_email
        put(metadata, "tls_client_auth_san_email", getTlsClientAuthSanEmail(), nullIncluded);

        // tls_client_certificate_bound_access_tokens
        put(metadata, "tls_client_certificate_bound_access_tokens",
                isTlsClientCertificateBoundAccessTokens(), falseIncluded);

        //----------------------------------------------------------------------
        // JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)
        //----------------------------------------------------------------------

        // authorization_signed_response_alg
        put(metadata, "authorization_signed_response_alg", getAuthorizationSignAlg(), nullIncluded);

        // authorization_encrypted_response_alg
        put(metadata, "authorization_encrypted_response_alg", getAuthorizationEncryptionAlg(), nullIncluded);

        // authorization_encrypted_response_enc
        put(metadata, "authorization_encrypted_response_enc", getAuthorizationEncryptionEnc(), nullIncluded);

        //----------------------------------------------------------------------
        // OpenID Connect Client-Initiated Backchannel Authentication Flow - Core 1.0
        //----------------------------------------------------------------------

        // backchannel_token_delivery_mode
        put(metadata, "backchannel_token_delivery_mode", getBcDeliveryMode(), nullIncluded);

        // backchannel_client_notification_endpoint
        put(metadata, "backchannel_client_notification_endpoint", getBcNotificationEndpoint(), nullIncluded);

        // backchannel_authentication_request_signing_alg
        put(metadata, "backchannel_authentication_request_signing_alg", getBcRequestSignAlg(), nullIncluded);

        // backchannel_user_code_parameter
        put(metadata, "backchannel_user_code_parameter", isBcUserCodeRequired(), falseIncluded);

        //----------------------------------------------------------------------
        // OAuth 2.0 Rich Authorization Requests
        //----------------------------------------------------------------------

        // authorization_details_types
        put(metadata, "authorization_details_types", getAuthorizationDetailsTypes(), nullIncluded);

        //----------------------------------------------------------------------
        // OpenID Connect for Identity Assurance 1.0
        //----------------------------------------------------------------------

        // digest_algorithm
        put(metadata, "digest_algorithm", getDigestAlgorithm(), nullIncluded);

        //----------------------------------------------------------------------
        // OpenID Federation 1.0
        //----------------------------------------------------------------------

        // organization_name
        put(metadata, "organization_name", getOrganizationName(), nullIncluded);

        // signed_jwks_uri
        put(metadata, "signed_jwks_uri", getSignedJwksUri(), nullIncluded);

        // client_registration_types
        put(metadata, "client_registration_types", getClientRegistrationTypes(), nullIncluded);

        //----------------------------------------------------------------------
        // OpenID for Verifiable Credential Issuance
        //----------------------------------------------------------------------

        // credential_offer_endpoint
        put(metadata, "credential_offer_endpoint", getCredentialOfferEndpoint(), nullIncluded);

        //----------------------------------------------------------------------
        // FAPI 2.0 Security Profile, 8.1.1. use_mtls_endpoint_aliases
        //----------------------------------------------------------------------

        // use_mtls_endpoint_aliases
        put(metadata, "use_mtls_endpoint_aliases", isMtlsEndpointAliasesUsed(), falseIncluded);

        //----------------------------------------------------------------------
        // FAPI 2.0 Message Signing, 5.3.3. Client Metadata
        //----------------------------------------------------------------------

        // response_modes
        put(metadata, "response_modes", getResponseModes(), nullIncluded);

        //----------------------------------------------------------------------
        // Custom Metadata
        //----------------------------------------------------------------------

        putCustomMetadata(metadata, control);

        return metadata;
    }


    private void putClientId(Map<String, Object> metadata, ClientMetadataControl control)
    {
        String value;

        // If "alias" is preferred, enabled and available.
        if (control.isAliasPreferred() && isClientIdAliasEnabled() && getClientIdAlias() != null)
        {
            // Use the client ID alias as the value of "client_id".
            value = getClientIdAlias();
        }
        // If "entity ID" is preferred and available.
        else if (control.isEntityIdPreferred() && getEntityId() != null)
        {
            // Use the entity ID as the value of "client_id".
            value = getEntityId().toString();
        }
        else
        {
            // Use the original numeric client ID as the value of "client_id".
            value = String.valueOf(getClientId());
        }

        metadata.put("client_id", value);
    }


    private void putClientSecret(Map<String, Object> metadata, ClientMetadataControl control)
    {
        if (!control.isSecretIncluded())
        {
            return;
        }

        // The client secret is available only when the client type is "confidential".
        String value = (getClientType() == ClientType.CONFIDENTIAL)
                     ? getClientSecret() : null;

        put(metadata, "client_secret", value, control.isNullIncluded());
    }


    private void putClientIdIssuedAt(Map<String, Object> metadata, ClientMetadataControl control)
    {
        // Convert milliseconds to seconds.
        long value = getCreatedAt() / 1000L;

        put(metadata, "client_id_issued_at", value, control.isZeroIncluded());
    }


    private void putScope(Map<String, Object> metadata, ClientMetadataControl control)
    {
        ClientExtension extension = getExtension();

        String value = null;

        // If the "requestable scopes" feature is enabled.
        if (extension != null && extension.isRequestableScopesEnabled())
        {
            // Concatenate the requestable scopes.
            value = Utils.join(extension.getRequestableScopes(), " ");
        }

        put(metadata, "scope", value, control.isNullIncluded());
    }


    @SuppressWarnings("unchecked")
    private void putCustomMetadata(Map<String, Object> metadata, ClientMetadataControl control)
    {
        if (!control.isCustomIncluded())
        {
            return;
        }

        String json = getCustomMetadata();

        // If custom metadata are not set.
        if (json == null)
        {
            return;
        }

        Map<String, Object> custom;

        try
        {
            // Parse the custom metadata as JSON.
            custom = Utils.fromJson(json, Map.class);
        }
        catch (Exception cause)
        {
            // Failed to parse the custom metadata as JSON.
            return;
        }

        // If the result of parsing the custom metadata is unavailable.
        if (custom == null)
        {
            return;
        }

        // Merge the custom metadata into the metadata.
        put(metadata, custom, control);
    }
}
