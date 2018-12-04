/*
 * Copyright (C) 2014-2018 Authlete, Inc.
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
import java.util.Set;
import java.util.TreeSet;
import com.authlete.common.types.ClaimType;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.DeliveryMode;
import com.authlete.common.types.Display;
import com.authlete.common.types.GrantType;
import com.authlete.common.types.ResponseType;
import com.authlete.common.types.ServiceProfile;
import com.authlete.common.types.Sns;


/**
 * Information about a service.
 *
 * <p>
 * Some properties correspond to the ones listed in <a href=
 * "http://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata"
 * >OpenID Provider Metadata</a> in <a href=
 * "http://openid.net/specs/openid-connect-discovery-1_0.html"
 * >OpenID Connect Discovery 1.0</a>
 * </p>
 *
 * @see <a href="http://openid.net/specs/openid-connect-discovery-1_0.html"
 *      >OpenID Connect Discovery 1.0</a>
 */
public class Service implements Serializable
{
    private static final long serialVersionUID = 24L;


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    /**
     * Service number.
     */
    private int number;


    /**
     * Service owner number.
     */
    private int serviceOwnerNumber;


    /**
     * Service name.
     */
    private String serviceName;


    /**
     * API key.
     */
    private long apiKey;


    /**
     * API secret.
     */
    private String apiSecret;


    private URI issuer;
    private URI authorizationEndpoint;
    private URI tokenEndpoint;
    private URI revocationEndpoint;
    private ClientAuthMethod[] supportedRevocationAuthMethods;
    private URI userInfoEndpoint;
    private URI jwksUri;
    private String jwks;
    private URI registrationEndpoint;
    private Scope[] supportedScopes;
    private ResponseType[] supportedResponseTypes;
    private GrantType[] supportedGrantTypes;
    private String[] supportedAcrs;
    private ClientAuthMethod[] supportedTokenAuthMethods;
    private Display[] supportedDisplays;
    private ClaimType[] supportedClaimTypes;
    private String[] supportedClaims;
    private URI serviceDocumentation;
    private String[] supportedClaimLocales;
    private String[] supportedUiLocales;
    private URI policyUri;
    private URI tosUri;
    private URI authenticationCallbackEndpoint;
    private String authenticationCallbackApiKey;
    private String authenticationCallbackApiSecret;
    private Sns[] supportedSnses;
    private SnsCredentials[] snsCredentials;
    private long createdAt;
    private long modifiedAt;
    private URI developerAuthenticationCallbackEndpoint;
    private String developerAuthenticationCallbackApiKey;
    private String developerAuthenticationCallbackApiSecret;
    private Sns[] supportedDeveloperSnses;
    private SnsCredentials[] developerSnsCredentials;
    private int clientsPerDeveloper;
    private boolean directAuthorizationEndpointEnabled;
    private boolean directTokenEndpointEnabled;
    private boolean directRevocationEndpointEnabled;
    private boolean directUserInfoEndpointEnabled;
    private boolean directJwksEndpointEnabled;
    private boolean directIntrospectionEndpointEnabled;
    private boolean singleAccessTokenPerSubject;
    private boolean pkceRequired;
    private boolean refreshTokenKept;
    private boolean errorDescriptionOmitted;
    private boolean errorUriOmitted;
    private boolean clientIdAliasEnabled;
    private ServiceProfile[] supportedServiceProfiles;
    private boolean tlsClientCertificateBoundAccessTokens;
    private URI introspectionEndpoint;
    private ClientAuthMethod[] supportedIntrospectionAuthMethods;
    private boolean mutualTlsValidatePkiCertChain;
    private String[] trustedRootCertificates;


    /**
     * Description of this service.
     */
    private String description;


    /**
     * Access token type.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     */
    private String accessTokenType;


    /**
     * Duration of access tokens in seconds.
     */
    private long accessTokenDuration;


    /**
     * Duration of refresh tokens in seconds.
     */
    private long refreshTokenDuration;


    /**
     * Duration of ID tokens in seconds.
     */
    private long idTokenDuration;


    /**
     * Duration of authorization response JWTs.
     *
     * @since 2.28
     */
    private long authorizationResponseDuration;


    /**
     * Metadata.
     *
     * @since 1.39
     */
    private Pair[] metadata;


    /**
     * Key ID to identify a JWK used for signing authorization responses using an
     * asymmetric key. Regarding "signing the authorization response", see <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html">Financial-grade API:
     * JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
     *
     * @since 2.28
     */
    private String authorizationSignatureKeyId;


    /**
     * Key ID to identify a JWK used for ID token signature using an asymmetric key.
     *
     * @since 2.1
     */
    private String idTokenSignatureKeyId;


    /**
     * Key ID to identify a JWK used for User Info signature using an asymmetric key.
     *
     * @since 2.1
     */
    private String userInfoSignatureKeyId;


    /**
     * Supported backchannel token delivery modes. This property corresponds
     * to the {@code backchannel_token_delivery_modes_supported} metadata.
     *
     * @since 2.32
     */
    private DeliveryMode[] supportedBackchannelTokenDeliveryModes;


    /**
     * The backchannel authentication endpoint. This property corresponds to
     * the {@code backchannel_authentication_endpoint} metadata.
     *
     * @since 2.32
     */
    private URI backchannelAuthenticationEndpoint;


    /**
     * Boolean flag which indicates whether "user code" is supported at the
     * backchannel authentication endpoint. This property corresponds to the
     * {@code backchannel_user_code_parameter_supported} metadada.
     */
    private boolean backchannelUserCodeParameterSupported;


    /**
     * The allowable clock skew between the server and clients.
     *
     * @since 2.32
     */
    private int allowableClockSkew;


    /**
     * Get the service number.
     *
     * @return
     *         The service number.
     */
    public int getNumber()
    {
        return number;
    }


    /**
     * Set the service number.
     *
     * @param number
     *         The service number.
     *
     * @return
     *         {@code this} object.
     */
    public Service setNumber(int number)
    {
        this.number = number;

        return this;
    }


    /**
     * Get the service owner number.
     *
     * @return
     *         The service owner number.
     */
    public int getServiceOwnerNumber()
    {
        return serviceOwnerNumber;
    }


    /**
     * Set the service owner number
     *
     * @param serviceOwnerNumber
     *         The service owner number.
     *
     * @return
     *         {@code this} object.
     */
    public Service setServiceOwnerNumber(int serviceOwnerNumber)
    {
        this.serviceOwnerNumber = serviceOwnerNumber;

        return this;
    }


    /**
     * Get the service name.
     *
     * @return
     *         The service name.
     */
    public String getServiceName()
    {
        return serviceName;
    }


    /**
     * Set the service name.
     *
     * @param serviceName
     *         The service name.
     *
     * @return
     *         {@code this} object.
     */
    public Service setServiceName(String serviceName)
    {
        this.serviceName = serviceName;

        return this;
    }


    /**
     * Get the API key.
     *
     * @return
     *         The API key.
     */
    public long getApiKey()
    {
        return apiKey;
    }


    /**
     * Set the API key.
     *
     * @param apiKey
     *         The API key.
     *
     * @return
     *         {@code this} object.
     */
    public Service setApiKey(long apiKey)
    {
        this.apiKey = apiKey;

        return this;
    }


    /**
     * Get the API secret.
     *
     * @return
     *         The API secret.
     */
    public String getApiSecret()
    {
        return apiSecret;
    }


    /**
     * Set the API secret.
     *
     * @param apiSecret
     *         The API secret.
     *
     * @return
     *         {@code this} object.
     */
    public Service setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;

        return this;
    }


    /**
     * Get the issuer identifier of this OpenID provider.
     *
     * @return
     *         The issuer identifier.
     */
    public URI getIssuer()
    {
        return issuer;
    }


    /**
     * Set the issuer identifier of this OpenID provider.
     *
     * @param issuer
     *         The issuer identifier.
     *
     * @return
     *         {@code this} object.
     */
    public Service setIssuer(URI issuer)
    {
        this.issuer = issuer;

        return this;
    }


    /**
     * Get the URI of the authorization endpoint.
     *
     * @return
     *         The URI of the authorization endpoint.
     */
    public URI getAuthorizationEndpoint()
    {
        return authorizationEndpoint;
    }


    /**
     * Set the URI of the authorization endpoint.
     *
     * @param endpoint
     *         The URI of the authorization endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public Service setAuthorizationEndpoint(URI endpoint)
    {
        this.authorizationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the URI of the token endpoint.
     *
     * @return
     *         The URI of the token endpoint.
     */
    public URI getTokenEndpoint()
    {
        return tokenEndpoint;
    }


    /**
     * Set the URI of the token endpoint.
     *
     * @param endpoint
     *         The URI of the token endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public Service setTokenEndpoint(URI endpoint)
    {
        this.tokenEndpoint = endpoint;

        return this;
    }


    /**
     * Get the URI of the token revocation endpoint.
     *
     * @return
     *         The URI of the token revocation endpoint.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7009">RFC 7009: OAuth 2.0 Token Revocation</a>
     *
     * @since 1.16
     */
    public URI getRevocationEndpoint()
    {
        return revocationEndpoint;
    }


    /**
     * Get client authentication methods supported at the revocation endpoint.
     *
     * @return
     *         Client authentication methods supported at the revocation endpoint.
     *
     * @since 2.13
     */
    public ClientAuthMethod[] getSupportedRevocationAuthMethods()
    {
        return supportedRevocationAuthMethods;
    }


    /**
     * Set client authentication methods supported at the revocation endpoint.
     *
     * @param methods
     *         Client authentication methods.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.13
     */
    public Service setSupportedRevocationAuthMethods(ClientAuthMethod[] methods)
    {
        this.supportedRevocationAuthMethods = methods;

        return this;
    }


    /**
     * Set the URI of the token revocation endpoint.
     *
     * @param endpoint
     *         The URI of the token revocation endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7009">RFC 7009: OAuth 2.0 Token Revocation</a>
     *
     * @since 1.16
     */
    public Service setRevocationEndpoint(URI endpoint)
    {
        this.revocationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the URI of the user info endpoint.
     *
     * @return
     *         The URI of the user info endpoint.
     */
    public URI getUserInfoEndpoint()
    {
        return userInfoEndpoint;
    }


    /**
     * Set the URI of the user info endpoint.
     *
     * @param endpoint
     *         The URI of the user info endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public Service setUserInfoEndpoint(URI endpoint)
    {
        this.userInfoEndpoint = endpoint;

        return this;
    }


    /**
     * Get the URI of the service's JSON Web Key Set.
     *
     * @return
     *         The URI of the service's JSON Web Key Set.
     */
    public URI getJwksUri()
    {
        return jwksUri;
    }


    /**
     * Set the URI of the service's JSON Web Key Set.
     *
     * @param uri
     *         The URI of the service's JSON Web Key Set.
     *
     * @return
     *         {@code this} object.
     */
    public Service setJwksUri(URI uri)
    {
        this.jwksUri = uri;

        return this;
    }


    /**
     * Get the JSON Web Key Set of the service.
     *
     * @return
     *         The JSON Web Key Set of the service.
     */
    public String getJwks()
    {
        return jwks;
    }


    /**
     * Set the JSON Web Key Set of the service.
     *
     * @param jwks
     *         The JSON Web Key Set of the service.
     *
     * @return
     *         {@code this} object.
     */
    public Service setJwks(String jwks)
    {
        this.jwks = jwks;

        return this;
    }


    /**
     * Get the URI of the registration endpoint.
     *
     * @return
     *         The URI of the registration endpoint.
     */
    public URI getRegistrationEndpoint()
    {
        return registrationEndpoint;
    }


    /**
     * Set the URI of the registration endpoint.
     *
     * @param endpoint
     *         The URI of the registration endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public Service setRegistrationEndpoint(URI endpoint)
    {
        this.registrationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the supported scopes.
     *
     * @return
     *         The supported scopes.
     */
    public Scope[] getSupportedScopes()
    {
        return supportedScopes;
    }


    /**
     * Set the supported scopes.
     *
     * @param supportedScopes
     *         The supported scopes.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedScopes(Scope[] supportedScopes)
    {
        this.supportedScopes = supportedScopes;

        return this;
    }


    /**
     * Get the supported response types.
     *
     * @return
     *         The supported response types.
     */
    public ResponseType[] getSupportedResponseTypes()
    {
        return supportedResponseTypes;
    }


    /**
     * Set the supported response types.
     *
     * @param responseTypes
     *         The supported response types.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedResponseTypes(ResponseType[] responseTypes)
    {
        this.supportedResponseTypes = responseTypes;

        return this;
    }


    /**
     * Get the supported grant types.
     *
     * @return
     *         The supported grant types.
     */
    public GrantType[] getSupportedGrantTypes()
    {
        return supportedGrantTypes;
    }


    /**
     * Set the supported grant types.
     *
     * @param grantTypes
     *         The supported grant types.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedGrantTypes(GrantType[] grantTypes)
    {
        this.supportedGrantTypes = grantTypes;

        return this;
    }


    /**
     * Get the supported ACRs (authentication context class references).
     *
     * @return
     *         The supported ACRs.
     */
    public String[] getSupportedAcrs()
    {
        return supportedAcrs;
    }


    /**
     * Set the supported ACRs (authentication context class references).
     *
     * @param acrs
     *         The supported ACRs.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedAcrs(String[] acrs)
    {
        this.supportedAcrs = acrs;

        return this;
    }


    /**
     * Get the supported client authentication methods at the token endpoint.
     *
     * @return
     *         The supported client authentication methods.
     */
    public ClientAuthMethod[] getSupportedTokenAuthMethods()
    {
        return supportedTokenAuthMethods;
    }


    /**
     * Set the number of client authentication methods at the token endpoint.
     *
     * @param methods
     *         The supported client authentication methods.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedTokenAuthMethods(ClientAuthMethod[] methods)
    {
        this.supportedTokenAuthMethods = methods;

        return this;
    }


    /**
     * Get the supported values of {@code display} parameter passed to
     * the authorization endpoint.
     *
     * @return
     *         The supported values of {@code display} parameter.
     */
    public Display[] getSupportedDisplays()
    {
        return supportedDisplays;
    }


    /**
     * Set the supported values of {@code display} parameter passed to
     * the authorization endpoint.
     *
     * @param displays
     *         The supported values of {@code display} parameter.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedDisplays(Display[] displays)
    {
        this.supportedDisplays = displays;

        return this;
    }


    /**
     * Get the supported claim types.
     *
     * @return
     *         The supported claim types.
     */
    public ClaimType[] getSupportedClaimTypes()
    {
        return supportedClaimTypes;
    }


    /**
     * Set the supported claim types.
     *
     * @param claimTypes
     *         The supported claim types.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedClaimTypes(ClaimType[] claimTypes)
    {
        this.supportedClaimTypes = claimTypes;

        return this;
    }


    /**
     * Get the supported claims.
     *
     * @return
     *         The supported claims.
     */
    public String[] getSupportedClaims()
    {
        return supportedClaims;
    }


    /**
     * Set the supported claims.
     *
     * @param supportedClaims
     *         The supported claims.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedClaims(String[] supportedClaims)
    {
        this.supportedClaims = supportedClaims;

        return this;
    }


    /**
     * Get the URI of a page containing human-readable information
     * that developers might want or need to know when using this
     * OpenID Provider.
     *
     * @return
     *         The URI of the service documentation.
     */
    public URI getServiceDocumentation()
    {
        return serviceDocumentation;
    }


    /**
     * Set the URI of a page containing human-readable information
     * that developers might want or need to know when using this
     * OpenID Provider.
     *
     * @param uri
     *         The URI of the service documentation.
     *
     * @return
     *         {@code this} object.
     */
    public Service setServiceDocumentation(URI uri)
    {
        this.serviceDocumentation = uri;

        return this;
    }


    /**
     * Get the supported claim locales.
     *
     * @return
     *         The supported claim locales.
     */
    public String[] getSupportedClaimLocales()
    {
        return supportedClaimLocales;
    }


    /**
     * Set the supported claim locales.
     *
     * @param supportedClaimLocales
     *         The supported claim locales.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedClaimLocales(String[] supportedClaimLocales)
    {
        this.supportedClaimLocales = supportedClaimLocales;

        return this;
    }


    /**
     * Get the supported UI locales.
     *
     * @return
     *         The supported UI locales.
     */
    public String[] getSupportedUiLocales()
    {
        return supportedUiLocales;
    }


    /**
     * Set the supported UI locales.
     *
     * @param supportedUiLocales
     *         The supported UI locales.
     *
     * @return
     *         {@code this} object.
     */
    public Service setSupportedUiLocales(String[] supportedUiLocales)
    {
        this.supportedUiLocales = supportedUiLocales;

        return this;
    }


    /**
     * Get the URI that this OpenID Provider provides to the person
     * registering the client to read about the OP's requirements on
     * how the Relying Party can use the data provided by the OP.
     *
     * @return
     *         The URI of the policy page.
     */
    public URI getPolicyUri()
    {
        return policyUri;
    }


    /**
     * Set the URI that this OpenID Provider provides to the person
     * registering the client to read about the OP's requirements on
     * how the Relying Party can use the data provided by the OP.
     *
     * @param uri
     *         The URI of the policy page.
     *
     * @return
     *         {@code this} object.
     */
    public Service setPolicyUri(URI uri)
    {
        this.policyUri = uri;

        return this;
    }


    /**
     * Get the URI that the OpenID Provider provides to the person
     * registering the client to read about the OP's terms of service.
     *
     * @return
     *         The URI of the Terms-of-Service page.
     */
    public URI getTosUri()
    {
        return tosUri;
    }


    /**
     * Set the URI that the OpenID Provider provides to the person
     * registering the client to read about the OP's terms of service.
     *
     * @param uri
     *         The URI of the Terms-of-Service page.
     *
     * @return
     *         {@code this} object.
     */
    public Service setTosUri(URI uri)
    {
        this.tosUri = uri;

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
    public Service setDescription(String description)
    {
        this.description = description;

        return this;
    }


    /**
     * Get the access token type; the value of {@code token_type} in
     * access token responses.
     *
     * @return
     *         The access token type.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     *
     * @see <a href="https://tools.ietf.org/html/rfc6750"
     *      >RFC 6750 (OAuth 2.0 Bearer Token Usage)</a>
     */
    public String getAccessTokenType()
    {
        return accessTokenType;
    }


    /**
     * Set the access token type; the value of {@code token_type} in
     * access token responses.
     *
     * @param type
     *         The access token type.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     *
     * @see <a href="https://tools.ietf.org/html/rfc6750"
     *      >RFC 6750 (OAuth 2.0 Bearer Token Usage)</a>
     */
    public Service setAccessTokenType(String type)
    {
        this.accessTokenType = type;

        return this;
    }


    /**
     * Get the duration of access tokens in seconds; the value of
     * {@code expires_in} in access token responses.
     *
     * @return
     *         The duration of access tokens in seconds.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     */
    public long getAccessTokenDuration()
    {
        return accessTokenDuration;
    }


    /**
     * Set the duration of access tokens in seconds; the value of
     * {@code expires_in} in access token responses.
     *
     * @param duration
     *         The duration of access tokens in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     */
    public Service setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;

        return this;
    }


    /**
     * Get the duration of refresh tokens in seconds.
     *
     * @return
     *         The duration of refresh tokens in seconds.
     */
    public long getRefreshTokenDuration()
    {
        return refreshTokenDuration;
    }


    /**
     * Set the duration of refresh tokens in seconds.
     *
     * @param duration
     *         The duration of refresh tokens in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public Service setRefreshTokenDuration(long duration)
    {
        this.refreshTokenDuration = duration;

        return this;
    }


    /**
     * Get the duration of ID tokens in seconds.
     *
     * @return
     *         The duration of ID tokens in seconds.
     */
    public long getIdTokenDuration()
    {
        return idTokenDuration;
    }


    /**
     * Set the duration of ID tokens in seconds.
     *
     * @param duration
     *         The duration of ID tokens in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public Service setIdTokenDuration(long duration)
    {
        this.idTokenDuration = duration;

        return this;
    }


    /**
     * Get the duration of authorization response JWTs.
     *
     * <p>
     * <a href="https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a> defines new values for the {@code response_mode}
     * request parameter. They are {@code query.jwt}, {@code fragment.jwt},
     * {@code form_post.jwt} and {@code jwt}. If one of them is specified
     * as the response mode, response parameters from the authorization
     * endpoint will be packed into a JWT. This property is used to compute
     * the value of the {@code exp} claim of the JWT.
     * </p>
     *
     * @return
     *         The duration of authorization response JWTs in seconds.
     *
     * @since 2.28
     */
    public long getAuthorizationResponseDuration()
    {
        return authorizationResponseDuration;
    }


    /**
     * Set the duration of authorization response JWTs.
     *
     * <p>
     * <a href="https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for
     * OAuth 2.0 (JARM)</a> defines new values for the {@code response_mode}
     * request parameter. They are {@code query.jwt}, {@code fragment.jwt},
     * {@code form_post.jwt} and {@code jwt}. If one of them is specified
     * as the response mode, response parameters from the authorization
     * endpoint will be packed into a JWT. This property is used to compute
     * the value of the {@code exp} claim of the JWT.
     * </p>
     *
     * @param duration
     *         The duration of authorization response JWTs in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.28
     */
    public Service setAuthorizationResponseDuration(long duration)
    {
        this.authorizationResponseDuration = duration;

        return this;
    }


    /**
     * Get the URI of the authentication callback endpoint.
     *
     * @return
     *         The URI of the authentication callback endpoint.
     *
     * @since 1.1
     */
    public URI getAuthenticationCallbackEndpoint()
    {
        return authenticationCallbackEndpoint;
    }


    /**
     * Set the URI of the authentication callback endpoint.
     *
     * @param endpoint
     *         The URI of the authentication callback endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.1
     */
    public Service setAuthenticationCallbackEndpoint(URI endpoint)
    {
        this.authenticationCallbackEndpoint = endpoint;

        return this;
    }


    /**
     * Get the API key to access the authentication callback endpoint.
     *
     * @return
     *         The API key to access the authentication callback endpoint.
     *
     * @since 1.1
     */
    public String getAuthenticationCallbackApiKey()
    {
        return authenticationCallbackApiKey;
    }


    /**
     * Set the API key to access the authentication callback endpoint.
     *
     * @param apiKey
     *         The API key to access the authentication callback endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.1
     */
    public Service setAuthenticationCallbackApiKey(String apiKey)
    {
        this.authenticationCallbackApiKey = apiKey;

        return this;
    }


    /**
     * Get the API secret to access the authentication callback endpoint.
     *
     * @return
     *         The API secret to access the authentication callback endpoint.
     *
     * @since 1.1
     */
    public String getAuthenticationCallbackApiSecret()
    {
        return authenticationCallbackApiSecret;
    }


    /**
     * Set the API secret to access the authentication callback endpoint.
     *
     * @param apiSecret
     *         The API secret to access the authentication callback endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.1
     */
    public Service setAuthenticationCallbackApiSecret(String apiSecret)
    {
        this.authenticationCallbackApiSecret = apiSecret;

        return this;
    }


    /**
     * Get the list of supported SNSes for social login at the authorization
     * endpoint.
     *
     * @return
     *         The list of SNSes.
     *
     * @since 1.3
     */
    public Sns[] getSupportedSnses()
    {
        return supportedSnses;
    }


    /**
     * Set the list of supported SNSes for social login at the authorization
     * endpoint.
     *
     * @param supportedSnses
     *         The list of SNSes.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.3
     */
    public Service setSupportedSnses(Sns[] supportedSnses)
    {
        this.supportedSnses = supportedSnses;

        return this;
    }


    /**
     * Get the list of SNS credentials that Authlete uses to support social login.
     *
     * @return
     *         The list of SNS credentials.
     *
     * @since 1.3
     */
    public SnsCredentials[] getSnsCredentials()
    {
        return snsCredentials;
    }


    /**
     * Set the list of SNS credentials that Authlete uses to support social login.
     *
     * @param snsCredentials
     *         The list of SNS credentials.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.3
     */
    public Service setSnsCredentials(SnsCredentials[] snsCredentials)
    {
        this.snsCredentials = snsCredentials;

        return this;
    }


    /**
     * Get the time at which this service was created.
     *
     * @return
     *         The time at which this service was created.
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
     * Set the time at which this service was created.
     *
     * @param createdAt
     *         The time at which this service was created.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.6
     */
    public Service setCreatedAt(long createdAt)
    {
        this.createdAt = createdAt;

        return this;
    }


    /**
     * Get the time at which this service was last modified.
     *
     * @return
     *         The time at which this service was last modified.
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
     * Set the time at which this service was last modified.
     *
     * @param modifiedAt
     *         The time at which this service was modified.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.6
     */
    public Service setModifiedAt(long modifiedAt)
    {
        this.modifiedAt = modifiedAt;

        return this;
    }


    /**
     * Get metadata.
     *
     * <p>
     * The content of the returned array depends on contexts.
     * </p>
     *
     * <br/>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <caption>Predefined Service Metadata</caption>
     *   <tr>
     *     <th bgcolor="orange">Key</th>
     *     <th bgcolor="orange">Description</th>
     *   </tr>
     *   <tr>
     *     <td>{@code "clientCount"}</td>
     *     <td>The number of client applications which belong to this service.</td>
     *   </tr>
     * </table>
     *
     * @return
     *         Metadata. The type is an array of {@link Pair}.
     *
     * @since 1.39
     */
    public Pair[] getMetadata()
    {
        return metadata;
    }


    /**
     * Set metadata.
     *
     * @param metadata
     *         Metadata. The type is an array of {@link Pair}.
     *         {@code null} may be returned.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.39
     */
    public Service setMetadata(Pair[] metadata)
    {
         this.metadata = metadata;

         return this;
    }


    /**
     * Get the URI of the developer authentication callback endpoint.
     *
     * @return
     *         The URI of the developer authentication callback endpoint.
     *
     * @since 1.9
     */
    public URI getDeveloperAuthenticationCallbackEndpoint()
    {
        return developerAuthenticationCallbackEndpoint;
    }


    /**
     * Set the URI of the developer authentication callback endpoint.
     *
     * @param endpoint
     *         The URI of the developer authentication callback endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.9
     */
    public Service setDeveloperAuthenticationCallbackEndpoint(URI endpoint)
    {
        this.developerAuthenticationCallbackEndpoint = endpoint;

        return this;
    }


    /**
     * Get the API key to access the developer authentication callback endpoint.
     *
     * @return
     *         The API key to access the developer authentication callback endpoint.
     *
     * @since 1.9
     */
    public String getDeveloperAuthenticationCallbackApiKey()
    {
        return developerAuthenticationCallbackApiKey;
    }


    /**
     * Set the API key to access the developer authentication callback endpoint.
     *
     * @param apiKey
     *         The API key to access the developer authentication callback endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.9
     */
    public Service setDeveloperAuthenticationCallbackApiKey(String apiKey)
    {
        this.developerAuthenticationCallbackApiKey = apiKey;

        return this;
    }


    /**
     * Get the API secret to access the developer authentication callback endpoint.
     *
     * @return
     *         The API secret to access the developer authentication callback endpoint.
     *
     * @since 1.9
     */
    public String getDeveloperAuthenticationCallbackApiSecret()
    {
        return developerAuthenticationCallbackApiSecret;
    }


    /**
     * Set the API secret to access the developer authentication callback endpoint.
     *
     * @param apiSecret
     *         The API secret to access the developer authentication callback endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.9
     */
    public Service setDeveloperAuthenticationCallbackApiSecret(String apiSecret)
    {
        this.developerAuthenticationCallbackApiSecret = apiSecret;

        return this;
    }


    /**
     * Get the list of supported SNSes for social login at the developer console.
     *
     * @return
     *         The list of SNSes.
     *
     * @since 1.10
     */
    public Sns[] getSupportedDeveloperSnses()
    {
        return supportedDeveloperSnses;
    }


    /**
     * Set the list of supported SNSes for social login at the developer console.
     *
     * @param supportedSnses
     *         The list of SNSes.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.10
     */
    public Service setSupportedDeveloperSnses(Sns[] supportedSnses)
    {
        this.supportedDeveloperSnses = supportedSnses;

        return this;
    }


    /**
     * Get the list of SNS credentials that Authlete uses to support social login
     * at the developer console.
     *
     * @return
     *         The list of SNS credentials.
     *
     * @since 1.10
     */
    public SnsCredentials[] getDeveloperSnsCredentials()
    {
        return developerSnsCredentials;
    }


    /**
     * Set the list of SNS credentials that Authlete uses to support social login
     * at the developer console.
     *
     * @param snsCredentials
     *         The list of SNS credentials.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.10
     */
    public Service setDeveloperSnsCredentials(SnsCredentials[] snsCredentials)
    {
        this.developerSnsCredentials = snsCredentials;

        return this;
    }


    /**
     * Get the number of client applications that one developer can create.
     * 0 means that developers can create as many client applications as
     * they want.
     *
     * @return
     *         The number of client applications that one developer can create.
     *         0 means no limit.
     *
     * @since 1.16
     */
    public int getClientsPerDeveloper()
    {
        return clientsPerDeveloper;
    }


    /**
     * Set the number of client applications that one developer can create.
     * 0 means that developers can create as many client applications as
     * they want.
     *
     * @param count
     *         The number of client applications that one developer can create.
     *         0 means no limit.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.16
     */
    public Service setClientsPerDeveloper(int count)
    {
        this.clientsPerDeveloper = count;

        return this;
    }


    /**
     * Get the flag which indicates whether the direct authorization endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/authorization/direct/{serviceApiKey}</code>
     *
     * @return
     *         {@code true} if enabled.
     *
     * @since 1.16
     */
    public boolean isDirectAuthorizationEndpointEnabled()
    {
        return directAuthorizationEndpointEnabled;
    }


    /**
     * Set the flag which indicates whether the direct authorization endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/authorization/direct/{serviceApiKey}</code>
     *
     * @param enabled
     *         {@code true} to enable the direct endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.16
     */
    public Service setDirectAuthorizationEndpointEnabled(boolean enabled)
    {
        this.directAuthorizationEndpointEnabled = enabled;

        return this;
    }


    /**
     * Get the flag which indicates whether the direct token endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/token/direct/{serviceApiKey}</code>
     *
     * @return
     *         {@code true} if enabled.
     *
     * @since 1.16
     */
    public boolean isDirectTokenEndpointEnabled()
    {
        return directTokenEndpointEnabled;
    }


    /**
     * Set the flag which indicates whether the direct token endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/token/direct/{serviceApiKey}</code>
     *
     * @param enabled
     *         {@code true} to enable the direct endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.16
     */
    public Service setDirectTokenEndpointEnabled(boolean enabled)
    {
        this.directTokenEndpointEnabled = enabled;

        return this;
    }


    /**
     * Get the flag which indicates whether the direct revocation endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/revocation/direct/{serviceApiKey}</code>
     *
     * @return
     *         {@code true} if enabled.
     *
     * @since 1.16
     */
    public boolean isDirectRevocationEndpointEnabled()
    {
        return directRevocationEndpointEnabled;
    }


    /**
     * Set the flag which indicates whether the direct revocation endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/revocation/direct/{serviceApiKey}</code>
     *
     * @param enabled
     *         {@code true} to enable the direct endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.16
     */
    public Service setDirectRevocationEndpointEnabled(boolean enabled)
    {
        this.directRevocationEndpointEnabled = enabled;

        return this;
    }


    /**
     * Get the flag which indicates whether the direct userinfo endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/userinfo/direct/{serviceApiKey}</code>
     *
     * @return
     *         {@code true} if enabled.
     *
     * @since 1.16
     */
    public boolean isDirectUserInfoEndpointEnabled()
    {
        return directUserInfoEndpointEnabled;
    }


    /**
     * Set the flag which indicates whether the direct userinfo endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/userinfo/direct/{serviceApiKey}</code>
     *
     * @param enabled
     *         {@code true} to enable the direct endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.16
     */
    public Service setDirectUserInfoEndpointEnabled(boolean enabled)
    {
        this.directUserInfoEndpointEnabled = enabled;

        return this;
    }


    /**
     * Get the flag which indicates whether the direct jwks endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/service/jwks/get/direct/{serviceApiKey}</code>
     *
     * @return
     *         {@code true} if enabled.
     *
     * @since 1.16
     */
    public boolean isDirectJwksEndpointEnabled()
    {
        return directJwksEndpointEnabled;
    }


    /**
     * Set the flag which indicates whether the direct jwks endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/service/jwks/get/direct/{serviceApiKey}</code>
     *
     * @param enabled
     *         {@code true} to enable the direct endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.16
     */
    public Service setDirectJwksEndpointEnabled(boolean enabled)
    {
        this.directJwksEndpointEnabled = enabled;

        return this;
    }


    /**
     * Get the flag which indicates whether the direct introspection endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/introspection/direct/{serviceApiKey}</code>
     *
     * @return
     *         {@code true} if enabled.
     *
     * @since 1.39
     */
    public boolean isDirectIntrospectionEndpointEnabled()
    {
        return directIntrospectionEndpointEnabled;
    }


    /**
     * Set the flag which indicates whether the direct introspection endpoint
     * is enabled or not. The path of the endpoint is
     * <code>/api/auth/introspection/direct/{serviceApiKey}</code>
     *
     * @param enabled
     *         {@code true} to enable the direct endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.39
     */
    public Service setDirectIntrospectionEndpointEnabled(boolean enabled)
    {
        this.directIntrospectionEndpointEnabled = enabled;

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
     * @since 1.20
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
     * @since 1.20
     */
    public Service setSingleAccessTokenPerSubject(boolean single)
    {
        this.singleAccessTokenPerSubject = single;

        return this;
    }


    /**
     * Get the flag which indicates whether the use of Proof Key for Code
     * Exchange (PKCE) is always required for authorization requests
     * by <a href="https://tools.ietf.org/html/rfc6749#section-4.1"
     * >Authorization Code Flow</a>. See <a href=
     * "https://tools.ietf.org/html/rfc7636">RFC 7636</a> (Proof Key
     * for Code Exchange by OAuth Public Clients) for details.
     *
     * @return
     *         {@code true} if PKCE is always required for authorization
     *         requests by Authorization Code Flow.
     *
     * @since 1.21
     *
     * @see <a href="https://tools.ietf.org/html/rfc7636">RFC 7636</a>
     */
    public boolean isPkceRequired()
    {
        return pkceRequired;
    }


    /**
     * Set the flag which indicates whether the use of Proof Key for Code
     * Exchange (PKCE) is always required for authorization requests
     * by <a href="https://tools.ietf.org/html/rfc6749#section-4.1"
     * >Authorization Code Flow</a>. See <a href=
     * "https://tools.ietf.org/html/rfc7636">RFC 7636</a> (Proof Key
     * for Code Exchange by OAuth Public Clients</a> for details.
     *
     * @param required
     *         {@code true} to always require PKCE for authorization
     *         requests by Authorization Code Flow.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.21
     */
    public Service setPkceRequired(boolean required)
    {
        this.pkceRequired = required;

        return this;
    }


    /**
     * Get the flag which indicates whether a refresh token remains valid
     * or gets renewed after its use.
     *
     * @return
     *         {@code true} if a refresh token remains valid after its use.
     *         {@code false} if a new refresh token is issued after its use.
     *
     * @since 1.33
     */
    public boolean isRefreshTokenKept()
    {
        return refreshTokenKept;
    }


    /**
     * Set the flag which indicates whether a refresh token remains valid
     * or gets renewed after its use.
     *
     * @param kept
     *         {@code true} to keep a refresh token valid after its use.
     *         {@code false} to renew a refresh token after its use.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.33
     */
    public Service setRefreshTokenKept(boolean kept)
    {
        this.refreshTokenKept = kept;

        return this;
    }


    /**
     * Get the flag which indicates whether the {@code error_description}
     * response parameter is omitted.
     *
     * <p>
     * According to RFC 6749, authorization servers may include the {@code
     * error_description} response parameter in error responses. When this
     * {@code errorDescriptionOmitted} property is {@code true}, Authlete does
     * not embed the {@code error_description} response parameter in error
     * responses.
     * </p>
     *
     * @return
     *         {@code true} if the {@code error_description} response parameter
     *         is omitted. {@code false} if the {@code error_description}
     *         response parameter is included in error responses from the
     *         authorization server.
     *
     * @since 1.39
     */
    public boolean isErrorDescriptionOmitted()
    {
        return errorDescriptionOmitted;
    }


    /**
     * Omit or embed the {@code error_description} response parameter in
     * error responses.
     *
     * @param omitted
     *         {@code true} to omit the {@code error_description} response
     *         parameter. {@code false} to embed the parameter.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.39
     */
    public Service setErrorDescriptionOmitted(boolean omitted)
    {
        this.errorDescriptionOmitted = omitted;

        return this;
    }


    /**
     * Get the flag which indicates whether the {@code error_uri} response
     * parameter is omitted.
     *
     * <p>
     * According to RFC 6749, authorization servers may include the
     * {@code error_uri} response parameter in error responses. When
     * this {@code errorUriOmitted} property is {@code true}, Authlete
     * does not embed the {@code error_uri} response parameter in error
     * responses.
     * </p>
     *
     * @return
     *         {@code true} if the {@code error_uri} response parameter
     *         is omitted. {@code false} if the {@code error_uri}
     *         response parameter is included in error responses from
     *         the authorization server.
     *
     * @since 1.39
     */
    public boolean isErrorUriOmitted()
    {
        return errorUriOmitted;
    }


    /**
     * Omit or embed the {@code error_uri} response parameter in error
     * responses.
     *
     * @param omitted
     *         {@code true} to omit the {@code error_uri} response parameter.
     *         {@code false} to embed the parameter.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.39
     */
    public Service setErrorUriOmitted(boolean omitted)
    {
        this.errorUriOmitted = omitted;

        return this;
    }


    /**
     * Get the flag which indicates whether the 'Client ID Alias' feature
     * is enabled or not.
     *
     * @return
     *         {@code true} if the 'Client ID Alias' feature is enabled.
     *         {@code false} if the feature is disabled.
     *
     * @since 2.2
     */
    public boolean isClientIdAliasEnabled()
    {
        return clientIdAliasEnabled;
    }


    /**
     * Enable/disable the 'Client ID Alias' feature.
     *
     * <p>
     * When a new client is created, Authlete generates a numeric value
     * and assigns it as a client ID to the newly created client. In
     * addition to the client ID, each client can have a client ID alias.
     * The client ID alias is, however, recognized only when this property
     * ({@code clientIdAliasEnabled}) is {@code true}.
     * </p>
     *
     * @param enabled
     *         {@code true} to enable the 'Client ID Alias' feature.
     *         {@code false} to disable it.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.2
     */
    public Service setClientIdAliasEnabled(boolean enabled)
    {
        this.clientIdAliasEnabled = enabled;

        return this;
    }


    /**
     * Get the key ID to identify a JWK used for signing authorization
     * responses using an asymmetric key.
     *
     * <p>
     * <a href="https://openid.net/specs/openid-financial-api-jarm.html"
     * >Financial-grade API: JWT Secured Authorization Response Mode for OAuth
     * 2.0 (JARM)</a> has added new values for the {@code response_mode}
     * request parameter. They are {@code query.jwt}, {@code fragment.jwt},
     * {@code form_post.jwt} and {@code jwt}. If one of them is used, response
     * parameters returned from the authorization endpoint will be packed into
     * a JWT. The JWT is always signed. For the signature of the JWT, Authlete
     * Server has to pick up one JWK from the service's JWK Set.
     * </p>
     *
     * <p>
     * Authlete Server searches the JWK Set for a JWK which satisfies
     * conditions for authorization response signature. If the number of JWK
     * candidates which satisfy the conditions is 1, there is no problem. On
     * the other hand, if there exist multiple candidates, a <a href=
     * "https://tools.ietf.org/html/rfc7517#section-4.5">Key ID</a> is needed
     * to be specified so that Authlete Server can pick up one JWK from among
     * the JWK candidates. This property exists to specify the key ID.
     * </p>
     *
     * @return
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @since 2.28
     */
    public String getAuthorizationSignatureKeyId()
    {
        return authorizationSignatureKeyId;
    }


    /**
     * Set the key ID to identify a JWK used for signing authorization responses
     * using an asymmetric key.
     *
     * <p>
     * See the description of {@link #getAuthorizationSignatureKeyId()} for details.
     * </p>
     *
     * @param keyId
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.28
     */
    public Service setAuthorizationSignatureKeyId(String keyId)
    {
        this.authorizationSignatureKeyId = keyId;

        return this;
    }


    /**
     * Get the key ID to identify a JWK used for ID token signature using
     * an asymmetric key.
     *
     * <p>
     * A JWK Set can be registered as a property of a Service. A JWK Set can
     * contain 0 or more JWKs (See <a href="https://tools.ietf.org/html/rfc7517"
     * >RFC 7517</a> for details about JWK). Authlete Server has to pick up
     * one JWK for signature from the JWK Set when it generates an ID token
     * and signature using an asymmetric key. Authlete Server
     * searches the registered JWK Set for a JWK which satisfies conditions
     * for ID token signature. If the number of JWK candidates which satisfy
     * the conditions is 1, there is no problem. On the other hand, if there
     * exist multiple candidates, a <a href=
     * "https://tools.ietf.org/html/rfc7517#section-4.5">Key ID</a> is needed
     * to be specified so that Authlete Server can pick up one JWK from among
     * the JWK candidates.
     * </p>
     *
     * <p>
     * This {@code idTokenSignatureKeyId} property exists for the purpose
     * described above. For key rotation (OpenID Connect Core 1.0, <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#RotateSigKeys"
     * >10.1.1. Rotation of Asymmetric Signing Keys</a>), this mechanism is
     * needed.
     * </p>
     *
     * @return
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @since 2.1
     */
    public String getIdTokenSignatureKeyId()
    {
        return idTokenSignatureKeyId;
    }


    /**
     * Set the key ID to identify a JWK used for ID token signature using
     * an asymmetric key.
     *
     * <p>
     * See the description of {@link #getIdTokenSignatureKeyId()} for details.
     * </p>
     *
     * @param keyId
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.1
     */
    public Service setIdTokenSignatureKeyId(String keyId)
    {
        this.idTokenSignatureKeyId = keyId;

        return this;
    }


    /**
     * Get the key ID to identify a JWK used for user info signature using
     * an asymmetric key.
     *
     * <p>
     * A JWK Set can be registered as a property of a Service. A JWK Set can
     * contain 0 or more JWKs (See <a href="https://tools.ietf.org/html/rfc7517"
     * >RFC 7517</a> for details about JWK). Authlete Server has to pick up
     * one JWK for signature from the JWK Set when it is required to sign
     * user info (which is returned from <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#UserInfo"
     * >UserInfo Endpoint</a>) using an asymmetric key. Authlete Server
     * searches the registered JWK Set for a JWK which satisfies conditions
     * for user info signature. If the number of JWK candidates which satisfy
     * the conditions is 1, there is no problem. On the other hand, if there
     * exist multiple candidates, a <a href=
     * "https://tools.ietf.org/html/rfc7517#section-4.5">Key ID</a> is needed
     * to be specified so that Authlete Server can pick up one JWK from among
     * the JWK candidates.
     * </p>
     *
     * <p>
     * This {@code userInfoSignatureKeyId} property exists for the purpose
     * described above. For key rotation (OpenID Connect Core 1.0, <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#RotateSigKeys"
     * >10.1.1. Rotation of Asymmetric Signing Keys</a>), this mechanism is
     * needed.
     * </p>
     *
     * @return
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @since 2.1
     */
    public String getUserInfoSignatureKeyId()
    {
        return userInfoSignatureKeyId;
    }


    /**
     * Set the key ID to identify a JWK used for user info signature using
     * an asymmetric key.
     *
     * <p>
     * See the description of {@link #getUserInfoSignatureKeyId()} for details.
     * </p>
     *
     * @param keyId
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.1
     */
    public Service setUserInfoSignatureKeyId(String keyId)
    {
        this.userInfoSignatureKeyId = keyId;

        return this;
    }


    /**
     * Get the supported service profiles.
     *
     * @return
     *         Supported service profiles.
     *
     * @since 2.12
     */
    public ServiceProfile[] getSupportedServiceProfiles()
    {
        return supportedServiceProfiles;
    }


    /**
     * Set the supported service profiles.
     *
     * @param profiles
     *         Supported service profiles.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.12
     */
    public Service setSupportedServiceProfiles(ServiceProfile[] profiles)
    {
        this.supportedServiceProfiles = profiles;

        return this;
    }


    /**
     * Set the supported service profiles.
     *
     * @param profiles
     *         Supported service profiles.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.12
     */
    public Service setSupportedServiceProfiles(Iterable<ServiceProfile> profiles)
    {
        if (profiles == null)
        {
            this.supportedServiceProfiles = null;

            return this;
        }

        Set<ServiceProfile> set = new TreeSet<ServiceProfile>();

        for (ServiceProfile profile : profiles)
        {
            if (profile != null)
            {
                set.add(profile);
            }
        }

        int size = set.size();

        if (size == 0)
        {
            this.supportedServiceProfiles = null;

            return this;
        }

        ServiceProfile[] array = new ServiceProfile[size];
        this.supportedServiceProfiles = set.toArray(array);

        return this;
    }


    /**
     * Check if this service supports the specified profile.
     *
     * If {@code null} is given, {@code false} is returned.
     * If the supported service profiles are not set to this service,
     * {@code false} is returned.
     *
     * @param profile
     *         A service profile.
     *
     * @return
     *         {@code true} if this service supports the service profile.
     *
     * @since 2.12
     */
    public boolean supports(ServiceProfile profile)
    {
        if (profile == null)
        {
            return false;
        }

        if (supportedServiceProfiles == null)
        {
            return false;
        }

        for (ServiceProfile supportedProfile : supportedServiceProfiles)
        {
            if (supportedProfile == profile)
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Check if this service supports all the specified service profiles.
     *
     * If {@code null} is given, {@code true} is returned.
     * If an empty array is given, {@code true} is returned.
     *
     * @param profiles
     *         Service profiles.
     *
     * @return
     *         {@code true} if this service supports all the specified
     *         service profiles.
     *
     * @since 2.12
     */
    public boolean supportsAll(ServiceProfile... profiles)
    {
        if (profiles == null)
        {
            return true;
        }

        for (ServiceProfile profile : profiles)
        {
            if (supports(profile) == false)
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Check if this service supports all the specified service profiles.
     *
     * If {@code null} is given, {@code true} is returned.
     * If an empty collection is given, {@code true} is returned.
     *
     * @param profiles
     *         Service profiles.
     *
     * @return
     *         {@code true} if this service supports all the specified
     *         service profiles.
     *
     * @since 2.12
     */
    public boolean supportsAll(Iterable<ServiceProfile> profiles)
    {
        if (profiles == null)
        {
            return true;
        }

        for (ServiceProfile profile : profiles)
        {
            if (supports(profile) == false)
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Check if this service any of the specified service profiles.
     *
     * If {@code null} is given, {@code false} is returned.
     * If an empty array is given, {@code false} is returned.
     *
     * @param profiles
     *         Service profiles.
     *
     * @return
     *         {@code true} if this service supports any of the specified
     *         service profiles.
     *
     * @since 2.12
     */
    public boolean supportsAny(ServiceProfile... profiles)
    {
        if (profiles == null)
        {
            return false;
        }

        for (ServiceProfile profile : profiles)
        {
            if (supports(profile))
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Check if this service any of the specified service profiles.
     *
     * If {@code null} is given, {@code false} is returned.
     * If an empty collection is given, {@code false} is returned.
     *
     * @param profiles
     *         Service profiles.
     *
     * @return
     *         {@code true} if this service supports any of the specified
     *         service profiles.
     *
     * @since 2.12
     */
    public boolean supportsAny(Iterable<ServiceProfile> profiles)
    {
        if (profiles == null)
        {
            return false;
        }

        for (ServiceProfile profile : profiles)
        {
            if (supports(profile))
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Does this service support issuing TLS client certificate bound access tokens?
     *
     * @return
     *         {@code true} if this service supports issuing TLS client
     *         certificate bound access tokens.
     *
     * @since 2.19
     */
    public boolean isTlsClientCertificateBoundAccessTokens()
    {
        return tlsClientCertificateBoundAccessTokens;
    }


    /**
     * Enable or disable support for TLS client certificate bound access tokens.
     *
     * @param enabled
     *         {@code true} to enable TLS client certificate bound access tokens.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.19
     */
    public Service setTlsClientCertificateBoundAccessTokens(boolean enabled)
    {
        this.tlsClientCertificateBoundAccessTokens = enabled;

        return this;
    }


    /**
     * Get the URI of the introspection endpoint.
     *
     * @return
     *         The URI of the introspection endpoint.
     *
     * @since 2.13
     *
     * @see <a href="https://tools.ietf.org/html/rfc7662">RFC 7662: OAuth 2.0 Token Introspection</a>
     */
    public URI getIntrospectionEndpoint()
    {
        return introspectionEndpoint;
    }


    /**
     * Set the URI of the introspection endpoint.
     *
     * @param endpoint
     *         The URI of the introspection endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.13
     *
     * @see <a href="https://tools.ietf.org/html/rfc7662">RFC 7662: OAuth 2.0 Token Introspection</a>
     */
    public Service setIntrospectionEndpoint(URI endpoint)
    {
        this.introspectionEndpoint = endpoint;

        return this;
    }


    /**
     * Get client authentication methods supported at the introspection endpoint.
     *
     * @return
     *         Client authentication methods supported at the introspection endpoint.
     *
     * @since 2.13
     */
    public ClientAuthMethod[] getSupportedIntrospectionAuthMethods()
    {
        return supportedIntrospectionAuthMethods;
    }


    /**
     * Set client authentication methods supported at the introspection endpoint.
     *
     * @param methods
     *         Client authentication methods.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.13
     */
    public Service setSupportedIntrospectionAuthMethods(ClientAuthMethod[] methods)
    {
        this.supportedIntrospectionAuthMethods = methods;

        return this;
    }


    /**
     * Determine whether this service validates certificate chains during PKI-based
     * client mutual TLS authentication.
     *
     * @return
     *          {@code true} if this service requires clients using PKI MTLS
     *          to present their certificate chain to the API during authentication,
     *          {@code false} otherwise.
     *
     * @since 2.15
     */
    public boolean isMutualTlsValidatePkiCertChain()
    {
        return mutualTlsValidatePkiCertChain;
    }


    /**
     * Set whether this service validates certificate chains during PKI-based
     * client mutual TLS authentication.
     *
     * @param mutualTlsValidatePkiCertChain
     *          {@code true} if this service requires clients using PKI MTLS
     *          to present their certificate chain to the API during authentication,
     *          {@code false} otherwise.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.15
     */
    public Service setMutualTlsValidatePkiCertChain(boolean mutualTlsValidatePkiCertChain)
    {
        this.mutualTlsValidatePkiCertChain = mutualTlsValidatePkiCertChain;

        return this;
    }


    /**
     * Get the list of root certificates trusted by this service for PKI-based
     * client mutual TLS authentication.
     *
     * @return
     *          The list of root certificates trusted by this service in PEM format.
     *
     * @since 2.15
     */
    public String[] getTrustedRootCertificates()
    {
        return trustedRootCertificates;
    }


    /**
     * Get the list of root certificates trusted by this service for PKI-based
     * client mutual TLS authentication.
     *
     * @param trustedRootCertificates
     *          The list of root certificates trusted by this service in PEM format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.15
     */
    public Service setTrustedRootCertificates(String[] trustedRootCertificates)
    {
        this.trustedRootCertificates = trustedRootCertificates;

        return this;
    }


    /**
     * Get the supported backchannel token delivery modes. This property
     * corresponds to the {@code backchannel_token_delivery_modes_supported}
     * metadata.
     *
     * <p>
     * Backchannel token delivery modes are defined in the specification of
     * CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @return
     *         Supported backchannel token delivery modes.
     *
     * @since 2.32
     */
    public DeliveryMode[] getSupportedBackchannelTokenDeliveryModes()
    {
        return supportedBackchannelTokenDeliveryModes;
    }


    /**
     * Get the supported backchannel token delivery modes. This property
     * corresponds to the {@code backchannel_token_delivery_modes_supported}
     * metadata.
     *
     * <p>
     * Backchannel token delivery modes are defined in the specification of
     * CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @param modes
     *         Supported backchannel token delivery modes.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Service setSupportedBackchannelTokenDeliveryModes(DeliveryMode[] modes)
    {
        this.supportedBackchannelTokenDeliveryModes = modes;

        return this;
    }


    /**
     * Get the URI of the backchannel authentication endpoint.
     *
     * <p>
     * Backchannel authentication endpoint is defined in the specification of
     * CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @return
     *         The URI of the backchannel authentication endpoint.
     *
     * @since 2.32
     */
    public URI getBackchannelAuthenticationEndpoint()
    {
        return backchannelAuthenticationEndpoint;
    }


    /**
     * Set the URI of the backchannel authentication endpoint.
     *
     * <p>
     * Backchannel authentication endpoint is defined in the specification of
     * CIBA (Client Initiated Backchannel Authentication).
     * </p>
     *
     * @param endpoint
     *         The URI of the backchannel authentication endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Service setBackchannelAuthenticationEndpoint(URI endpoint)
    {
        this.backchannelAuthenticationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the boolean flag which indicates whether the {@code "user_code"}
     * request parameter is supported at the backchannel authentication
     * endpoint. This property corresponds to the
     * {@code backchannel_user_code_parameter_supported} metadata.
     *
     * @return
     *         {@code true} if the {@code "user_code"} request parameter is
     *         supported at the backchannel authentication endpoint.
     *
     * @since 2.32
     */
    public boolean isBackchannelUserCodeParameterSupported()
    {
        return backchannelUserCodeParameterSupported;
    }


    /**
     * Set the boolean flag which indicates whether the {@code "user_code"}
     * request parameter is supported at the backchannel authentication
     * endpoint. This property corresponds to the
     * {@code backchannel_user_code_parameter_supported} metadata.
     *
     * @param supported
     *         {@code true} to indicate that the {@code "user_code"} request
     *         parameter is supported.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Service setBackchannelUserCodeParameterSupported(boolean supported)
    {
        this.backchannelUserCodeParameterSupported = supported;

        return this;
    }


    /**
     * Get the allowable clock skew between the server and clients in seconds.
     *
     * <p>
     * The clock skew is taken into consideration when time-related claims in
     * a JWT (e.g. {@code "exp"}, {@code "iat"}, {@code "nbf"}) are verified.
     * </p>
     *
     * @return
     *         Allowable clock skew in seconds.
     *
     * @since 2.32
     */
    public int getAllowableClockSkew()
    {
        return allowableClockSkew;
    }


    /**
     * Set the allowable clock skew between the server and clients in seconds.
     *
     * <p>
     * The clock skew is taken into consideration when time-related claims in
     * a JWT (e.g. {@code "exp"}, {@code "iat"}, {@code "nbf"}) are verified.
     * </p>
     *
     * @param seconds
     *         Allowable clock skew in seconds. Must be in between 0 and
     *         65,535.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Service setAllowableClockSkew(int seconds)
    {
        this.allowableClockSkew = seconds;

        return this;
    }
}
