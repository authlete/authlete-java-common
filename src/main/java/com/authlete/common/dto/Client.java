/*
 * Copyright (C) 2014-2019 Authlete, Inc.
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
import com.authlete.common.types.ApplicationType;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.ClientType;
import com.authlete.common.types.DeliveryMode;
import com.authlete.common.types.GrantType;
import com.authlete.common.types.JWEAlg;
import com.authlete.common.types.JWEEnc;
import com.authlete.common.types.JWSAlg;
import com.authlete.common.types.ResponseType;
import com.authlete.common.types.SubjectType;


/**
 * Information about a client application.
 *
 * <p>
 * Some properties correspond to the ones listed in <a href=
 * "https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
 * >Client Metadata</a> in <a href=
 * "https://openid.net/specs/openid-connect-registration-1_0.html"
 * >OpenID Connect Dynamic Client Registration 1.0</a>.
 * </p>
 *
 * @see <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
 *      >OpenID Connect Dynamic Client Registration 1.0</a>
 *
 * @see <a href="https://openid.net/specs/openid-financial-api-jarm.html"
 *      >Financial-grade API: JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
 *
 * @author Takahiko Kawasaki
 */
public class Client implements Serializable
{
    private static final long serialVersionUID = 14L;


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    /**
     * Client number.
     */
    private int number;


    /**
     * Service number.
     */
    private int serviceNumber;


    /**
     * Developer unique ID.
     */
    private String developer;


    /**
     * Client ID.
     */
    private long clientId;


    /**
     * Alias of Client ID.
     */
    private String clientIdAlias;


    /**
     * True when the client ID alias is enabled.
     */
    private boolean clientIdAliasEnabled;


    /**
     * Client secret.
     */
    private String clientSecret;


    /**
     * Client type.
     */
    private ClientType clientType;


    /**
     * Redirect URIs.
     */
    private String[] redirectUris;


    /**
     * Response types.
     */
    private ResponseType[] responseTypes;


    /**
     * Grant types.
     */
    private GrantType[] grantTypes;


    /**
     * Application type.
     */
    private ApplicationType applicationType;


    /**
     * Email addresses of contacts.
     */
    private String[] contacts;


    /**
     * Client name.
     */
    private String clientName;


    /**
     * Client names.
     */
    private TaggedValue[] clientNames;


    /**
     * Logo URI.
     */
    private URI logoUri;


    /**
     * Logo URIs.
     */
    private TaggedValue[] logoUris;


    /**
     * Client URI.
     */
    private URI clientUri;


    /**
     * Client URIs.
     */
    private TaggedValue[] clientUris;


    /**
     * Policy URI.
     */
    private URI policyUri;


    /**
     * Policy URIs.
     */
    private TaggedValue[] policyUris;


    /**
     * Terms of Service URI.
     */
    private URI tosUri;


    /**
     * Terms of Service URIs.
     */
    private TaggedValue[] tosUris;


    /**
     * JSON Web Key Set URI.
     */
    private URI jwksUri;


    /**
     * JSON Web Key Set.
     */
    private String jwks;


    /**
     * Sector identifier.
     */
    private URI sectorIdentifier;


    private SubjectType subjectType;
    private JWSAlg idTokenSignAlg;
    private JWEAlg idTokenEncryptionAlg;
    private JWEEnc idTokenEncryptionEnc;
    private JWSAlg userInfoSignAlg;
    private JWEAlg userInfoEncryptionAlg;
    private JWEEnc userInfoEncryptionEnc;
    private JWSAlg requestSignAlg;
    private JWEAlg requestEncryptionAlg;
    private JWEEnc requestEncryptionEnc;
    private ClientAuthMethod tokenAuthMethod;
    private JWSAlg tokenAuthSignAlg;
    private int defaultMaxAge;
    private String[] defaultAcrs;
    private boolean authTimeRequired;
    private URI loginUri;
    private String[] requestUris;
    private String description;
    private TaggedValue[] descriptions;
    private long createdAt;
    private long modifiedAt;
    private ClientExtension extension;
    private String tlsClientAuthSubjectDn;
    private String tlsClientAuthSanDns;
    private URI tlsClientAuthSanUri;
    private String tlsClientAuthSanIp;
    private String tlsClientAuthSanEmail;
    private boolean tlsClientCertificateBoundAccessTokens;
    private String selfSignedCertificateKeyId;
    private String softwareId;
    private String softwareVersion;
    private JWSAlg authorizationSignAlg;
    private JWEAlg authorizationEncryptionAlg;
    private JWEEnc authorizationEncryptionEnc;
    private DeliveryMode bcDeliveryMode;
    private URI bcNotificationEndpoint;
    private JWSAlg bcRequestSignAlg;
    private boolean bcUserCodeRequired;
    private boolean dynamicallyRegistered;


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
     * @return
     *         The sector identifier.
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#SectorIdentifierValidation"
     *      >5. "sector_identifier_uri" Validation</a>
     */
    public URI getSectorIdentifier()
    {
        return sectorIdentifier;
    }


    /**
     * Set the sector identifier.
     *
     * @param sectorIdentifier
     *         The sector identifier.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#SectorIdentifierValidation"
     *      >5. "sector_identifier_uri" Validation</a>
     */
    public Client setSectorIdentifier(URI sectorIdentifier)
    {
        this.sectorIdentifier = sectorIdentifier;

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
     * Set the subject type that thsi client application requests.
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
     * Get the flag which indicates whether a client has been registered
     * dynamically.
     *
     * @param dynamicallyRegistered
     *         {@code true} if the client has been registered dynamically.
     * 
     * @return
     *         {@code this} object.
     *         
     * @since 2.XX
     */
    public boolean isDynamicallyRegistered()
    {
        return dynamicallyRegistered;
    }


    /**
     * Set the flag which indicates whether a client has been registered
     * dynamically.
     *
     * @param dynamicallyRegistered
     *         {@code true} if the client has been registered dynamically.
     * 
     * @return
     *         {@code this} object.
     *         
     * @since 2.XX
     */
    public Client setDynamicallyRegistered(boolean dynamicallyRegistered)
    {
        this.dynamicallyRegistered = dynamicallyRegistered;

        return this;
    }
}
