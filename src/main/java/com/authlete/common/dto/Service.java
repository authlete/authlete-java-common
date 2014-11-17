/*
 * Copyright (C) 2014 Authlete, Inc.
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
import com.authlete.common.types.ClaimType;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.Display;
import com.authlete.common.types.GrantType;
import com.authlete.common.types.ResponseType;


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
 *
 * @author Takahiko Kawasaki
 */
public class Service implements Serializable
{
    private static final long serialVersionUID = 2L;


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


    /**
     * Description of this service.
     */
    private String description;


    /**
     * Access token type.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     */
    private String accessTokenType;


    /**
     * Duration of access tokens in seconds.
     */
    private int accessTokenDuration;


    /**
     * Duration of refresh tokens in seconds.
     */
    private int refreshTokenDuration;


    /**
     * Duration of ID tokens in seconds.
     */
    private int idTokenDuration;


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
     * Get the URI of that this OpenID Provider provides to the person
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
     * Set the URI of that this OpenID Provider provides to the person
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
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     *
     * @see <a href="http://tools.ietf.org/html/rfc6750"
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
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     *
     * @see <a href="http://tools.ietf.org/html/rfc6750"
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
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     */
    public int getAccessTokenDuration()
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
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.1"
     *      >RFC 6749 (OAuth 2.0), 5.1. Successful Response</a>
     */
    public Service setAccessTokenDuration(int duration)
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
    public int getRefreshTokenDuration()
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
    public Service setRefreshTokenDuration(int duration)
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
    public int getIdTokenDuration()
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
    public Service setIdTokenDuration(int duration)
    {
        this.idTokenDuration = duration;

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
}
