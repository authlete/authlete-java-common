/*
 * Copyright (C) 2014-2021 Authlete, Inc.
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.authlete.common.types.ClaimType;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.DeliveryMode;
import com.authlete.common.types.Display;
import com.authlete.common.types.GrantType;
import com.authlete.common.types.JWSAlg;
import com.authlete.common.types.ResponseType;
import com.authlete.common.types.ServiceProfile;
import com.authlete.common.types.Sns;
import com.authlete.common.types.UserCodeCharset;


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
 * <h3>JWT-based access token</h3>
 *
 * <p>
 * When {@link #getAccessTokenSignAlg()} returns a non-null value, access
 * tokens issued by this service become JWTs. The value returned by the
 * method is used as the signature algorithm of the JWTs. When the method
 * returns null, access tokens issued by this service are random strings as
 * before.
 * </p>
 *
 * <p>
 * A JWT-based access token has the following claims.
 * </p>
 *
 * <blockquote>
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *   <tr bgcolor="orange">
 *     <th>claim name</th>
 *     <th>type</th>
 *     <th>description</th>
 *   </tr>
 *   <tr>
 *     <td>{@code scope}</td>
 *     <td>string</td>
 *     <td>Space-delimited scope names.</td>
 *   </tr>
 *   <tr>
 *     <td>{@code client_id}</td>
 *     <td>string</td>
 *     <td>Client ID.</td>
 *   </tr>
 *   <tr>
 *     <td>{@code exp}</td>
 *     <td>integer</td>
 *     <td>
 *       Time at which this access token will expire. Seconds since the
 *       Unix epoch.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code iat}</td>
 *     <td>integer</td>
 *     <td>
 *       Time at which this access token was issued. Seconds since the Unix
 *       epoch.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code sub}</td>
 *     <td>string</td>
 *     <td>
 *       The subject (unique identifier) of the resource owner who approved
 *       issue of this access token. This claim does not exist or its value
 *       is null if this access token was issued by resource owner password
 *       credentials flow.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code iss}</td>
 *     <td>string</td>
 *     <td>The issuer identifier of this service.</td>
 *   </tr>
 *   <tr>
 *     <td>{@code jti}</td>
 *     <td>string</td>
 *     <td>
 *       The unique identifier of this JWT. The value of this claim itself
 *       is the random-string version of this access token.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code cnf}</td>
 *     <td>object</td>
 *     <td>
 *       <p>
 *       If this access token is bound to a client certificate, this claim
 *       is included. The type of its value is object and the sub object
 *       contains a {@code "x5t#S256"} claim. The value of the {@code
 *       "x5t#S256"} claim is the X.509 Certificate SHA-256 thumbprint of
 *       the client certificate. See
 *       <i>"<a href="https://www.rfc-editor.org/rfc/rfc8705.html#name-jwt-certificate-thumbprint-"
 *       >3.1. JWT Certificate Thumbprint Confirmation Method</a>"</i> of
 *       <a href="https://www.rfc-editor.org/rfc/rfc8705.html">RFC 8705</a>
 *       (OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound
 *       Access Tokens) for details.
 *       </p>
 *       <p>
 *       If this access token is bound to a public key of DPoP, this claim
 *       is included. The type of its value is object and the sub object
 *       contains a {@code "jkt"} claim. The value of the {@code "jkt"}
 *       claim is the thumbprint of the public key. See <i>"OAuth 2.0
 *       Demonstration of Proof-of-Possession at the Application Layer"</i>
 *       for details.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code aud}</td>
 *     <td>array</td>
 *     <td>
 *       If this access token has been generated with target resources, this
 *       claim is included. See <i>"Resource Indicators for OAuth 2.0"</i>
 *       for details.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code authorization_details}</td>
 *     <td>array</td>
 *     <td>
 *       If this access token has been generated with {@code authorization_details},
 *       this claim is included. See <i>"OAuth 2.0 Rich Authorization Requests"</i>
 *       for details.
 *     </td>
 *   </tr>
 * </table>
 *
 * <p>
 * Visible (= not-hidden) extra properties of the access token are embedded
 * in the JWT as custom claims. Regarding extra properties, see the Authlete
 * API document.
 * </p>
 *
 * <p>
 * The feature of JWT-based access token is available since Authlete 2.1.
 * Access tokens issued by older Authlete versions are always random strings.
 * </p>
 *
 * <p>
 * <i>"Resource Indicators for OAuth 2.0"</i> is supported since Authlete 2.2.
 * The {@code resource} request parameter given to older Authlete versions is
 * just ignored, so JWT-based access tokens won't include the {@code aud} claim.
 * </p>
 *
 * <p>
 * <i>"OAuth 2.0 Rich Authorization Requests"</i> is supported since Authlete 2.2.
 * The {@code authorization_details} request parameter given to older Authlete
 * versions is just ignored, so JWT-based access tokens won't include the
 * {@code authorization_details} claim.
 * </p>
 * </blockquote>
 *
 * @see <a href="http://openid.net/specs/openid-connect-discovery-1_0.html"
 *      >OpenID Connect Discovery 1.0</a>
 */
public class Service implements Serializable
{
    private static final long serialVersionUID = 41L;


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
    private URI registrationManagementEndpoint;
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
    private boolean pkceS256Required;
    private boolean refreshTokenKept;
    private boolean refreshTokenDurationKept;
    private boolean errorDescriptionOmitted;
    private boolean errorUriOmitted;
    private boolean clientIdAliasEnabled;
    private ServiceProfile[] supportedServiceProfiles;
    private boolean tlsClientCertificateBoundAccessTokens;
    private URI introspectionEndpoint;
    private ClientAuthMethod[] supportedIntrospectionAuthMethods;
    private boolean mutualTlsValidatePkiCertChain;
    private String[] trustedRootCertificates;
    private boolean dynamicRegistrationSupported;
    private URI endSessionEndpoint;


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
     * Signature algorithm of JWT-based access tokens. When this property
     * is not null, access tokens issued by this service are JWTs. Otherwise,
     * access tokens are random strings as before.
     *
     * <p>
     * Symmetric algorithms are not supported.
     * </p>
     *
     * @since 2.37
     */
    private JWSAlg accessTokenSignAlg;


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
     * Duration of pushed authorization requests.
     *
     * @since 2.51
     */
    private long pushedAuthReqDuration;


    /**
     * Metadata.
     *
     * @since 1.39
     */
    private Pair[] metadata;


    /**
     * Key ID to identify a JWK used for access token signature using an
     * asymmetric key.
     *
     * @since 2.37
     */
    private String accessTokenSignatureKeyId;


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
     * {@code backchannel_user_code_parameter_supported} metadata.
     *
     * @since 2.32
     */
    private boolean backchannelUserCodeParameterSupported;


    /**
     * Duration of backchannel authentication request IDs issued from the
     * backchannel authentication endpoint in seconds. This is used as the
     * value of the {@code expires_in} property in responses from the
     * backchannel authentication endpoint.
     *
     * @since 2.32
     */
    private int backchannelAuthReqIdDuration;


    /**
     * The minimum interval between polling requests to the token endpoint
     * from client applications in seconds. This is used as the value of
     * the {@code interval} property in responses from the backchannel
     * authentication endpoint.
     *
     * @since 2.32
     */
    private int backchannelPollingInterval;


    /**
     * Boolean flag which indicates whether the {@code binding_message}
     * request parameter is always required whenever a backchannel
     * authentication request is judged as a request for Financial-grade
     * API.
     *
     * @since 2.48
     */
    private boolean backchannelBindingMessageRequiredInFapi;


    /**
     * The allowable clock skew between the server and clients.
     *
     * @since 2.32
     */
    private int allowableClockSkew;


    /**
     * The device authorization endpoint. This property corresponds to
     * the {@code device_authorization_endpoint} metadata.
     *
     * @since 2.42
     */
    private URI deviceAuthorizationEndpoint;


    /**
     * The verification URI for the device flow.
     *
     * @since 2.42
     */
    private URI deviceVerificationUri;


    /**
     * The verification URI for the device flow with a placeholder for a
     * user code.
     *
     * @since 2.42
     */
    private URI deviceVerificationUriComplete;


    /**
     * Duration of device verification codes and end-user verification codes
     * issued from the device authorization endpoint in seconds. This is used
     * as the value of the {@code expires_in} property in responses from the
     * device authorization endpoint.
     *
     * @since 2.42
     */
    private int deviceFlowCodeDuration;


    /**
     * The minimum interval between polling requests to the token endpoint from
     * client applications in seconds in device flow. This is used as the value
     * of the {@code interval} property in responses from the device
     * authorization endpoint.
     *
     * @since 2.42
     */
    private int deviceFlowPollingInterval;


    /**
     * Character set for end-user verification codes (user_code) for Device Flow.
     *
     * @since 2.43
     */
    private UserCodeCharset userCodeCharset;


    /**
     * Length of end-user verification codes (user_code) for Device Flow.
     *
     * @since 2.43
     */
    private int userCodeLength;


    /**
     * The URI of the pushed authorization request endpoint.
     *
     * @since 2.52
     */
    private URI pushedAuthReqEndpoint;


    /**
     * MTLS endpoint aliases.
     *
     * @since 2.49
     */
    private NamedUri[] mtlsEndpointAliases;


    /**
     * Supported data types for {@code "authorization_details"}.
     */
    private String[] supportedAuthorizationDataTypes;


    /**
     * Supported trust frameworks. This corresponds to
     * {@code trust_frameworks_supported}.
     *
     * @since 2.63
     */
    private String[] supportedTrustFrameworks;


    /**
     * Supported evidence. This corresponds to
     * {@code evidence_supported}.
     *
     * @since 2.63
     */
    private String[] supportedEvidence;


    /**
     * Supported ID documents. This corresponds to
     * {@code id_documents_supported}.
     *
     * @since 2.63
     */
    private String[] supportedIdentityDocuments;


    /**
     * Supported verification methods. This corresponds to
     * {@code id_documents_verification_methods_supported}.
     *
     * @since 2.63
     */
    private String[] supportedVerificationMethods;


    /**
     * Supported verified claims. This corresponds to
     * {@code claims_in_verified_claims_supported}.
     *
     * @since 2.63
     */
    private String[] supportedVerifiedClaims;


    /**
     * The flag indicating whether token requests from public clients without
     * the {@code client_id} request parameter are allowed when the client can
     * be guessed from {@code authorization_code} or {@code refresh_token}.
     *
     * <p>
     * Don't set this flag unless you have special reasons.
     * </p>
     *
     * @since 2.68
     */
    private boolean missingClientIdAllowed;


    /**
     * The flag indicating whether this service requires that clients use the
     * pushed authorization request endpoint.
     *
     * @since 2.77
     */
    private boolean parRequired;


    /**
     * The flag indicating whether authorization requests must utilize a
     * request object.
     *
     * @since 2.80
     */
    private boolean requestObjectRequired;


    /**
     * The flag indicating whether traditional request object processing
     * (rules defined in OIDC Core 1.0) is applied.
     *
     * @since 2.80
     */
    private boolean traditionalRequestObjectProcessingApplied;


    /**
     * The flag indicating whether claims specified by shortcut scopes
     * (e.g. profile) are included in the issued ID token only when no
     * access token is issued.
     *
     * @since 2.81
     */
    private boolean claimShortcutRestrictive;


    /**
     * The flag indicating whether requests that request no scope are
     * rejected or not.
     *
     * @since 2.81
     */
    private boolean scopeRequired;


    /**
     * The flag indicating whether the {@code nbf} claim in the request
     * object is optional even when the authorization request is regarded
     * as a FAPI-Part2 request.
     *
     * @since 2.86
     */
    private boolean nbfOptional;


    /**
     * The flag indicating whether generation of the {@code iss} response
     * parameter is suppressed.
     *
     * @since 2.86
     */
    private boolean issSuppressed;


    /**
     * Arbitrary attributes associated with this service.
     *
     * @since 2.87
     */
    private Pair[] attributes;


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
     * Get the URI of the registration management endpoint. If dynamic client
     * registration is supported, and this is set, this URI will be used as the
     * basis of the client's management endpoint by appending {@code /clientid/}
     * to it as a path element. If this is unset, the value of {@code registrationEndpoint}
     * will be used as the URI base instead.
     *
     * @return
     *         The base URI of the registration management endpoint.
     *
     * @since
     *        2.39
     */
    public URI getRegistrationManagementEndpoint()
    {
        return registrationManagementEndpoint;
    }


    /**
     * Set the URI of the registration management endpoint. If dynamic client
     * registration is supported, and this is set, this URI will be used as the
     * basis of the client's management endpoint by appending {@code /clientid/}
     * to it as a path element. If this is unset, the value of {@code registrationEndpoint}
     * will be used as the URI base instead.
     *
     * @param endpoint
     *         The base URI of the registration management endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since
     *        2.39
     */
    public Service setRegistrationManagementEndpoint(URI endpoint)
    {
        this.registrationManagementEndpoint = endpoint;

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
     * Get the signature algorithm of access tokens.
     *
     * <p>
     * When this method returns null, access tokens issued by this service are
     * just random strings. On the other hand, when this method returns a
     * non-null value, access tokens issued by this service are JWTs and the
     * value returned from this method represents the signature algorithm of
     * the JWTs. Regarding the format, see the description of this
     * {@link Service} class.
     * </p>
     *
     * <p>
     * This feature is available since Authlete 2.1. Access tokens generated
     * by older Authlete versions are always random strings.
     * </p>
     *
     * @return
     *         The signature algorithm of JWT-based access tokens. When null
     *         is returned, access tokens are not JWTs but just random strings.
     *
     * @since 2.37
     */
    public JWSAlg getAccessTokenSignAlg()
    {
        return accessTokenSignAlg;
    }


    /**
     * Set the signature algorithm of access tokens.
     *
     * <p>
     * When null is set, access tokens issued by this service are just random
     * strings. On the other hand, when a non-null value is set, access tokens
     * issued by this service are JWTs and the value set by this method is used
     * as the signature algorithm of the JWTs. Regarding the format, see the
     * description of this {@link Service} class.
     * </p>
     *
     * <p>
     * This feature is available since Authlete 2.1. Access tokens generated
     * by older Authlete versions are always random strings.
     * </p>
     *
     * @param alg
     *         The signature algorithm of JWT-based access tokens. When null
     *         is given, access tokens are not JWTs but just random strings.
     *         Note that symmetric algorithms ({@link JWSAlg#HS256 HS256},
     *         {@link JWSAlg#HS384 HS384} and {@link JWSAlg#HS512 HS512})
     *         are not supported.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.37
     */
    public Service setAccessTokenSignAlg(JWSAlg alg)
    {
        this.accessTokenSignAlg = alg;

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
     * Get the duration of pushed authorization requests.
     *
     * <p>
     * "OAuth 2.0 Pushed Authorization Requests" defines an endpoint (called
     * "pushed authorization request endpoint") which client applications can
     * register authorization requests into and get corresponding URIs (called
     * "request URIs") from. The issued URIs represent the registered
     * authorization requests. The client applications can use the URIs as the
     * value of the {@code request_uri} request parameter in an authorization
     * request.
     * </p>
     *
     * <p>
     * The value returned from this method represents the duration of registered
     * authorization requests and is used as the value of the {@code expires_in}
     * parameter in responses from the pushed authorization request endpoint.
     * </p>
     *
     * @return
     *         The duration of pushed authorization requests in seconds.
     *
     * @since 2.51
     */
    public long getPushedAuthReqDuration()
    {
        return pushedAuthReqDuration;
    }


    /**
     * Set the duration of pushed authorization requests.
     *
     * <p>
     * "OAuth 2.0 Pushed Authorization Requests" defines an endpoint (called
     * "pushed authorization request endpoint") which client applications can
     * register authorization requests into and get corresponding URIs (called
     * "request URIs") from. The issued URIs represent the registered
     * authorization requests. The client applications can use the URIs as the
     * value of the {@code request_uri} request parameter in an authorization
     * request.
     * </p>
     *
     * <p>
     * The value given to this method represents the duration of registered
     * authorization requests and is used as the value of the {@code expires_in}
     * parameter in responses from the pushed authorization request endpoint.
     * </p>
     *
     * @param duration
     *         The duration of pushed authorization requests.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.51
     */
    public Service setPushedAuthReqDuration(long duration)
    {
        this.pushedAuthReqDuration = duration;

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
     * Get the flag which indicates whether {@code S256} is always required
     * as the code challenge method whenever PKCE (<a href=
     * "https://tools.ietf.org/html/rfc7636">RFC 7636</a>) is used.
     *
     * <p>
     * If this flag is {@code true}, {@code code_challenge_method=S256} must
     * be included in the authorization request whenever it includes the
     * {@code code_challenge} request parameter. Neither omission of the
     * {@code code_challenge_method} request parameter nor use of {@code plain}
     * ({@code code_challenge_method=plain}) is allowed.
     * </p>
     *
     * @return
     *         {@code true} if {@code S256} is always required as the code
     *         challenge method whenever PKCE is used.
     *
     * @since 2.46
     *
     * @see <a href="https://tools.ietf.org/html/rfc7636">RFC 7636</a>
     */
    public boolean isPkceS256Required()
    {
        return pkceS256Required;
    }


    /**
     * Set the flag which indicates whether {@code S256} is always required
     * as the code challenge method whenever PKCE (<a href=
     * "https://tools.ietf.org/html/rfc7636">RFC 7636</a>) is used.
     *
     * <p>
     * If {@code true} is set, {@code code_challenge_method=S256} must be
     * included in the authorization request whenever it includes the
     * {@code code_challenge} request parameter. Neither omission of the
     * {@code code_challenge_method} request parameter nor use of {@code plain}
     * ({@code code_challenge_method=plain}) is allowed.
     * </p>
     *
     * @param required
     *         {@code true} to require {@code S256} as the code challenge
     *         method whenever PKCE is used.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.46
     *
     * @see <a href="https://tools.ietf.org/html/rfc7636">RFC 7636</a>
     */
    public Service setPkceS256Required(boolean required)
    {
        this.pkceS256Required = required;

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
     * Get the flag which indicates whether the remaining duration of the used
     * refresh token is taken over to the newly issued refresh token.
     *
     * @return
     *         {@code true} if the remaining duration of the used refresh token
     *         is taken over to the newly issued refresh token.
     *
     * @since 2.78
     */
    public boolean isRefreshTokenDurationKept()
    {
        return refreshTokenDurationKept;
    }


    /**
     * Set the flag which indicates whether the remaining duration of the used
     * refresh token is taken over to the newly issued refresh token.
     *
     * @param kept
     *         {@code true} to indicate that the remaining duration of the used
     *         refresh token is taken over to the newly issued refresh token.
     *
     * @return
     *         {@code this} object.
     */
    public Service setRefreshTokenDurationKept(boolean kept)
    {
        this.refreshTokenDurationKept = kept;

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
     * Get the key ID to identify a JWK used for signing access tokens.
     *
     * <p>
     * A JWK Set can be registered as a property of a Service. A JWK Set can
     * contain 0 or more JWKs (See <a href="https://tools.ietf.org/html/rfc7517"
     * >RFC 7517</a> for details about JWK). Authlete Server has to pick up
     * one JWK for signing from the JWK Set when it generates a JWT-based
     * access token (see {@link #getAccessTokenSignAlg()} for details about
     * JWT-based access token). Authlete Server searches the registered JWK Set
     * for a JWK which satisfies conditions for access token signature. If the
     * number of JWK candidates which satisfy the conditions is 1, there is no
     * problem. On the other hand, if there exist multiple candidates, a
     * <a href="https://tools.ietf.org/html/rfc7517#section-4.5">Key ID</a> is
     * needed to be specified so that Authlete Server can pick up one JWK from
     * among the JWK candidates.
     * </p>
     *
     * <p>
     * This {@code accessTokenSignatureKeyId} property exists for the purpose
     * described above.
     * </p>
     *
     * @return
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @since 2.37
     */
    public String getAccessTokenSignatureKeyId()
    {
        return accessTokenSignatureKeyId;
    }


    /**
     * Set the key ID to identify a JWK used for signing access tokens.
     *
     * <p>
     * See the description of {@link #getAccessTokenSignatureKeyId()} for
     * details.
     * </p>
     *
     * @param keyId
     *         A key ID of a JWK. This may be {@code null}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.37
     */
    public Service setAccessTokenSignatureKeyId(String keyId)
    {
        this.accessTokenSignatureKeyId = keyId;

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
     * Get the duration of backchannel authentication request IDs issued from
     * the backchannel authentication endpoint in seconds. This is used as the
     * value of the {@code expires_in} property in responses from the
     * backchannel authentication endpoint.
     *
     * @return
     *         The duration of backchannel authentication request IDs in
     *         seconds.
     *
     * @since 2.32
     */
    public int getBackchannelAuthReqIdDuration()
    {
        return backchannelAuthReqIdDuration;
    }


    /**
     * Set the duration of backchannel authentication request IDs issued from
     * the backchannel authentication endpoint in seconds. This is used as the
     * value of the {@code expires_in} property in responses from the
     * backchannel authentication endpoint.
     *
     * @param duration
     *         The duration of backchannel authentication request IDs in
     *         seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Service setBackchannelAuthReqIdDuration(int duration)
    {
        this.backchannelAuthReqIdDuration = duration;

        return this;
    }


    /**
     * Get the minimum interval between polling requests to the token endpoint
     * from client applications in seconds. This is used as the value of the
     * {@code interval} property in responses from the backchannel
     * authentication endpoint.
     *
     * @return
     *         The minimum interval between polling requests in seconds.
     *
     * @since 2.32
     */
    public int getBackchannelPollingInterval()
    {
        return backchannelPollingInterval;
    }


    /**
     * Set the minimum interval between polling requests to the token endpoint
     * from client applications in seconds. This is used as the value of the
     * {@code interval} property in responses from the backchannel
     * authentication endpoint.
     *
     * @param interval
     *         The minimum interval between polling requests in seconds.
     *         Must be in between 0 and 65,535.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.32
     */
    public Service setBackchannelPollingInterval(int interval)
    {
        this.backchannelPollingInterval = interval;

        return this;
    }


    /**
     * Get the boolean flag which indicates whether the {@code binding_message}
     * request parameter is always required whenever a backchannel authentication
     * request is judged as a request for Financial-grade API.
     *
     * @return
     *         {@code true} if the {@code binding_message} request parameter
     *         is required whenever a backchannel authentication request is
     *         judged as a request for Financial-grade API.
     *
     * @since 2.48
     */
    public boolean isBackchannelBindingMessageRequiredInFapi()
    {
        return backchannelBindingMessageRequiredInFapi;
    }


    /**
     * Set the boolean flag which indicates whether the {@code binding_message}
     * request parameter is always required whenever a backchannel authentication
     * request is judged as a request for Financial-grade API.
     *
     * <p>
     * The FAPI-CIBA profile requires that the authorization server <i>"shall
     * ensure unique authorization context exists in the authorization request
     * or require a {@code binding_message} in the authorization request"</i>
     * (FAPI-CIBA, 5.2.2, 2). The simplest way to fulfill this requirement is
     * to set {@code true} to this property.
     * </p>
     *
     * <p>
     * If {@code false} is set to this property, the {@code binding_message}
     * request parameter remains optional even in FAPI context, but in exchange,
     * your authorization server must implement a custom mechanism that ensures
     * each backchannel authentication request has unique context.
     * </p>
     *
     * @param required
     *         {@code true} to require the {@code binding_message} request
     *         parameter whenever a backchannel authentication request is
     *         judged as a request for Financial-grade API.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.48
     */
    public Service setBackchannelBindingMessageRequiredInFapi(boolean required)
    {
        this.backchannelBindingMessageRequiredInFapi = required;

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


    /**
     * Get the flag which indicates whether the dynamic client registration is
     * supported.
     *
     * @return
     *         {@code true} if enabled.
     *
     * @since 2.39
     */
    public boolean isDynamicRegistrationSupported()
    {
        return dynamicRegistrationSupported;
    }


    /**
     * Set the flag which indicates whether dynamic client registration is supported.
     *
     * @param enabled
     *         {@code true} to enable dynamic client registration
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.39
     */
    public Service setDynamicRegistrationSupported(boolean enabled)
    {
        this.dynamicRegistrationSupported = enabled;

        return this;
    }


    /**
     * Get the URI of the device authorization endpoint.
     *
     * <p>
     * Device authorization endpoint is defined in the specification of
     * OAuth 2.0 Device Authorization Grant.
     * </p>
     *
     * @return
     *         The URI of the device authorization endpoint.
     *
     * @since 2.42
     */
    public URI getDeviceAuthorizationEndpoint()
    {
        return deviceAuthorizationEndpoint;
    }


    /**
     * Set the URI of the device authorization endpoint.
     *
     * <p>
     * Device authorization endpoint is defined in the specification of
     * OAuth 2.0 Device Authorization Grant.
     * </p>
     *
     * @param endpoint
     *         The URI of the device authorization endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.42
     */
    public Service setDeviceAuthorizationEndpoint(URI endpoint)
    {
        this.deviceAuthorizationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the verification URI for the device flow. This URI is used as the
     * value of the {@code verification_uri} parameter in responses from the
     * device authorization endpoint.
     *
     * @return
     *         The verification URI.
     *
     * @since 2.42
     */
    public URI getDeviceVerificationUri()
    {
        return deviceVerificationUri;
    }


    /**
     * Set the verification URI for the device flow. This URI is used as the
     * value of the {@code verification_uri} parameter in responses from the
     * device authorization endpoint.
     *
     * @param uri
     *         The verification URI.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.42
     */
    public Service setDeviceVerificationUri(URI uri)
    {
        this.deviceVerificationUri = uri;

        return this;
    }


    /**
     * Get the verification URI for the device flow with a placeholder for a
     * user code. This URI is used to build the value of the
     * {@code verification_uri_complete} parameter in responses from the device
     * authorization endpoint.
     *
     * @return
     *         The verification URI with a placeholder for a user code.
     *
     * @since 2.42
     */
    public URI getDeviceVerificationUriComplete()
    {
        return deviceVerificationUriComplete;
    }


    /**
     * Set the verification URI for the device flow with a placeholder for a
     * user code. This URI is used to build the value of the
     * {@code verification_uri_complete} parameter in responses from the device
     * authorization endpoint.
     *
     * <p>
     * It is expected that the URI contains a fixed string {@code USER_CODE}
     * somewhere as a placeholder for a user code. For example, like the
     * following.
     * </p>
     *
     * <pre>
     * https://example.com/device?user_code=USER_CODE
     * </pre>
     *
     * <p>
     * The fixed string is replaced with an actual user code when Authlete
     * builds a verification URI with a user code for the
     * {@code verification_uri_complete} parameter.
     * </p>
     *
     * <p>
     * If this URI is not set, the {@code verification_uri_complete} parameter
     * won't appear in device authorization responses.
     * </p>
     *
     * @param uri
     *         The verification URI with a placeholder for a user code.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.42
     */
    public Service setDeviceVerificationUriComplete(URI uri)
    {
        this.deviceVerificationUriComplete = uri;

        return this;
    }


    /**
     * Get the duration of device verification codes and end-user verification
     * codes issued from the device authorization endpoint in seconds. This is
     * used as the value of the {@code expires_in} property in responses from
     * the device authorization endpoint.
     *
     * @return
     *         The duration of device verification codes and end-user
     *         verification codes in seconds.
     *
     * @since 2.42
     */
    public int getDeviceFlowCodeDuration()
    {
        return deviceFlowCodeDuration;
    }


    /**
     * Set the duration of device verification codes and end-user verification
     * codes issued from the device authorization endpoint in seconds. This is
     * used as the value of the {@code expires_in} property in responses from
     * the device authorization endpoint.
     *
     * @param duration
     *         The duration of device verification codes and end-user
     *         verification codes in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.42
     */
    public Service setDeviceFlowCodeDuration(int duration)
    {
        this.deviceFlowCodeDuration = duration;

        return this;
    }


    /**
     * Get the minimum interval between polling requests to the token endpoint
     * from client applications in seconds in device flow. This is used as the
     * value of the {@code interval} property in responses from the device
     * authorization endpoint.
     *
     * @return
     *         The minimum interval between polling requests in seconds in
     *         device flow.
     *
     * @since 2.42
     */
    public int getDeviceFlowPollingInterval()
    {
        return deviceFlowPollingInterval;
    }


    /**
     * Set the minimum interval between polling requests to the token endpoint
     * from client applications in seconds in device flow. This is used as the
     * value of the {@code interval} property in responses from the device
     * authorization endpoint.
     *
     * @param interval
     *         The minimum interval between polling requests in seconds in
     *         device flow. Must be in between 0 and 65,535.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.42
     */
    public Service setDeviceFlowPollingInterval(int interval)
    {
        this.deviceFlowPollingInterval = interval;

        return this;
    }


    /**
     * Get the character set for end-user verification codes
     * ({@code user_code}) for Device Flow.
     *
     * @return
     *         The character set for end-user verification codes
     *         ({@code user_code}) for Device Flow.
     *
     * @since 2.43
     */
    public UserCodeCharset getUserCodeCharset()
    {
        return userCodeCharset;
    }


    /**
     * Set the character set for end-user verification codes
     * ({@code user_code}) for Device Flow.
     *
     * @param charset
     *         The character set for end-user verification codes
     *         ({@code user_code}) for Device Flow.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.43
     */
    public Service setUserCodeCharset(UserCodeCharset charset)
    {
        this.userCodeCharset = charset;

        return this;
    }


    /**
     * Get the length of end-user verification codes ({@code user_code}) for
     * Device Flow.
     *
     * @return
     *         The length of end-user verification codes ({@code user_code})
     *         for Device Flow.
     *
     * @since 2.43
     */
    public int getUserCodeLength()
    {
        return userCodeLength;
    }


    /**
     * Set the length of end-user verification codes ({@code user_code}) for
     * Device Flow.
     *
     * @param length
     *         The length of end-user verification codes ({@code user_code})
     *         for Device Flow. The value must not be negative and must not
     *         be larger than 255.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.43
     */
    public Service setUserCodeLength(int length)
    {
        this.userCodeLength = length;

        return this;
    }


    /**
     * Get the URI of the pushed authorization request endpoint.
     *
     * <p>
     * This property corresponds to the {@code pushed_authorization_request_endpoint}
     * metadata defined in "<a href=
     * "https://tools.ietf.org/html/draft-lodderstedt-oauth-par-00"
     * >5. Authorization Server Metadata</a>" of OAuth 2.0 Pushed Authorization Requests.
     * </p>
     *
     * @return
     *         The URI of the pushed authorization request endpoint.
     *
     * @since 2.52
     *
     * @see <a href="https://tools.ietf.org/html/draft-lodderstedt-oauth-par-00#section-5"
     *      >OAuth 2.0 Pushed Authorization Requests, 5. Authorization Server Metadata</a>
     */
    public URI getPushedAuthReqEndpoint()
    {
        return pushedAuthReqEndpoint;
    }


    /**
     * Set the URI of the pushed authorization request endpoint.
     *
     * <p>
     * This property corresponds to the {@code pushed_authorization_request_endpoint}
     * metadata defined in "<a href=
     * "https://tools.ietf.org/html/draft-lodderstedt-oauth-par-00"
     * >5. Authorization Server Metadata</a>" of OAuth 2.0 Pushed Authorization Requests.
     * </p>
     *
     * @param endpoint
     *            The URI of the pushed authorization request endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.52
     *
     * @see <a href="https://tools.ietf.org/html/draft-lodderstedt-oauth-par-00#section-5"
     *      >OAuth 2.0 Pushed Authorization Requests, 5. Authorization Server Metadata</a>
     */
    public Service setPushedAuthReqEndpoint(URI endpoint)
    {
        this.pushedAuthReqEndpoint = endpoint;

        return this;
    }


    /**
     * Get the MTLS endpoint aliases.
     *
     * <p>
     * This property corresponds to the {@code mtls_endpoint_aliases} metadata
     * defined in "5. Metadata for Mutual TLS Endpoint Aliases" of <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-mtls/?include_text=1"
     * >OAuth 2.0 Mutual TLS Client Authentication and Certificate-Bound Access
     * Tokens</a>.
     * </p>
     *
     * @return
     *         MTLS endpoint aliases.
     *
     * @since 2.49
     */
    public NamedUri[] getMtlsEndpointAliases()
    {
        return mtlsEndpointAliases;
    }


    /**
     * Set the MTLS endpoint aliases.
     *
     * <p>
     * This property corresponds to the {@code mtls_endpoint_aliases} metadata
     * defined in "5. Metadata for Mutual TLS Endpoint Aliases" of <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-mtls/?include_text=1"
     * >OAuth 2.0 Mutual TLS Client Authentication and Certificate-Bound Access
     * Tokens</a>.
     * </p>
     *
     * <p>
     * The aliases will be embedded in the response from the discovery endpoint
     * like the following.
     * </p>
     *
     * <pre>
     * {
     *     ......,
     *     "mtls_endpoint_aliases": {
     *         "token_endpoint":         "https://mtls.example.com/token",
     *         "revocation_endpoint":    "https://mtls.example.com/revo",
     *         "introspection_endpoint": "https://mtls.example.com/introspect"
     *     }
     * }
     * </pre>
     *
     * @param aliases
     *         MTLS endpoint aliases.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.49
     */
    public Service setMtlsEndpointAliases(NamedUri[] aliases)
    {
        this.mtlsEndpointAliases = aliases;

        return this;
    }


    /**
     * Get the supported data types that can be used as values of the
     * {@code "type"} field in {@code "authorization_details"}.
     *
     * <p>
     * This property corresponds to the
     * {@code "authorization_data_types_supported"} metadata.
     * See "OAuth 2.0 Rich Authorization Requests" (RAR) for details.
     * </p>
     *
     * @return
     *         Supported data types.
     *
     * @since 2.55
     */
    public String[] getSupportedAuthorizationDataTypes()
    {
        return supportedAuthorizationDataTypes;
    }


    /**
     * Set the supported data types that can be used as values of the
     * {@code "type"} field in {@code "authorization_details"}.
     *
     * <p>
     * This property corresponds to the
     * {@code "authorization_data_types_supported"} metadata.
     * See "OAuth 2.0 Rich Authorization Requests" (RAR) for details.
     * </p>
     *
     * @param types
     *         Supported data types.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.55
     */
    public Service setSupportedAuthorizationDataTypes(String[] types)
    {
        this.supportedAuthorizationDataTypes = types;

        return this;
    }


    /**
     * Get trust frameworks supported by this service.
     * This corresponds to the {@code trust_frameworks_supported} <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @return
     *         Trust frameworks supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.1"
     *      >OpenID Connect for Identity Assurance 1.0, 11.1. Trust Frameworks</a>
     *
     * @since 2.63
     */
    public String[] getSupportedTrustFrameworks()
    {
        return supportedTrustFrameworks;
    }


    /**
     * Set trust frameworks supported by this service.
     * This corresponds to the {@code trust_frameworks_supported} <a href=
     * https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @param frameworks
     *         Trust frameworks supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.1"
     *      >OpenID Connect for Identity Assurance 1.0, 11.1. Trust Frameworks</a>
     *
     * @since 2.63
     */
    public Service setSupportedTrustFrameworks(String[] frameworks)
    {
        this.supportedTrustFrameworks = frameworks;

        return this;
    }


    /**
     * Get evidence supported by this service.
     * This corresponds to the {@code evidence_supported} <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @return
     *         Evidence supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.4.1.1"
     *      >OpenID Connect for Identity Assurance 1.0, 4.1.1. Evidence</a>
     *
     * @since 2.63
     */
    public String[] getSupportedEvidence()
    {
        return supportedEvidence;
    }


    /**
     * Set evidence supported by this service.
     * This corresponds to the {@code evidence_supported} <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @param evidence
     *         Evidence supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.4.1.1"
     *      >OpenID Connect for Identity Assurance 1.0, 4.1.1. Evidence</a>
     *
     * @since 2.63
     */
    public Service setSupportedEvidence(String[] evidence)
    {
        this.supportedEvidence = evidence;

        return this;
    }


    /**
     * Get identity documents supported by this service.
     * This corresponds to the {@code id_documents_supported} <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @return
     *         Identity documents supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.2"
     *      >OpenID Connect for Identity Assurance 1.0, 11.2. Identity Documents</a>
     *
     * @since 2.63
     */
    public String[] getSupportedIdentityDocuments()
    {
        return supportedIdentityDocuments;
    }


    /**
     * Set identity documents supported by this service.
     * This corresponds to the {@code id_documents_supported} <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @param documents
     *         Identity documents supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.2"
     *      >OpenID Connect for Identity Assurance 1.0, 11.2. Identity Documents</a>
     *
     * @since 2.63
     */
    public Service setSupportedIdentityDocuments(String[] documents)
    {
        this.supportedIdentityDocuments = documents;

        return this;
    }


    /**
     * Get verification methods supported by this service.
     * This corresponds to the {@code id_documents_verification_methods_supported}
     * <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @return
     *         Verification methods supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.3"
     *      >OpenID Connect for Identity Assurance 1.0, 11.3. Verification Methods</a>
     *
     * @since 2.63
     */
    public String[] getSupportedVerificationMethods()
    {
        return supportedVerificationMethods;
    }


    /**
     * Set verification methods supported by this service.
     * This corresponds to the {@code id_documents_verification_methods_supported}
     * <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @param methods
     *         Verification methods supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.11.3"
     *      >OpenID Connect for Identity Assurance 1.0, 11.3. Verification Methods</a>
     *
     * @since 2.63
     */
    public Service setSupportedVerificationMethods(String[] methods)
    {
        this.supportedVerificationMethods = methods;

        return this;
    }


    /**
     * Get verified claims supported by this service.
     * This corresponds to the {@code claims_in_verified_claims_supported}
     * <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @return
     *         Verified claims supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.3.1"
     *      >OpenID Connect for Identity Assurance 1.0, 3.1. Additional Claims about End-Users</a>
     *
     * @since 2.63
     */
    public String[] getSupportedVerifiedClaims()
    {
        return supportedVerifiedClaims;
    }


    /**
     * Set verified claims supported by this service.
     * This corresponds to the {@code claims_in_verified_claims_supported}
     * <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.7"
     * >metadata</a>.
     *
     * @param claims
     *         Verified claims supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.3.1"
     *      >OpenID Connect for Identity Assurance 1.0, 3.1. Additional Claims about End-Users</a>
     *
     * @since 2.63
     */
    public Service setSupportedVerifiedClaims(String[] claims)
    {
        this.supportedVerifiedClaims = claims;

        return this;
    }


    /**
     * Get the flag indicating whether token requests from public clients without
     * the {@code client_id} request parameter are allowed when the client can be
     * guessed from {@code authorization_code} or {@code refresh_token}.
     *
     * <p>
     * This flag should not be set unless you have special reasons.
     * </p>
     *
     * @return
     *         {@code true} if token requests from public clients without the
     *         {@code client_id} request parameter are allowed in the authorization
     *         code flow and the refresh token flow.
     *
     * @since 2.68
     */
    public boolean isMissingClientIdAllowed()
    {
        return missingClientIdAllowed;
    }


    /**
     * Set the flag indicating whether token requests from public clients without
     * the {@code client_id} request parameter are allowed when the client can be
     * guessed from {@code authorization_code} or {@code refresh_token}.
     *
     * <p>
     * Don't set this flag unless you have special reasons.
     * </p>
     *
     * @param allowed
     *         {@code true} to allow token requests from public clients without
     *         the {@code client_id} request parameter in the authorization code
     *         flow and the refresh token flow.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.68
     */
    public Service setMissingClientIdAllowed(boolean allowed)
    {
        this.missingClientIdAllowed = allowed;

        return this;
    }


    /**
     * Get the end session endpoint for the service. This endpoint is used by clients
     * to signal to the IdP that the user's session should be terminated.
     *
     * @return
     *         The end session endpoint, or {@code null} if not set.
     *
     * @see <a href="https://openid.net/specs/openid-connect-session-1_0.html#RPLogout"
     *      >OpenID Connect Session Management 1.0, 5. RP-Initiated Logout</a>
     *
     * @since 2.69
     */
    public URI getEndSessionEndpoint()
    {
        return endSessionEndpoint;
    }


    /**
     * Set the end session endpoint for the service. This endpoint is used by clients
     * to signal to the IdP that the user's session should be terminated.
     *
     * @param endSessionEndpoint
     *            The end session endpoint, or {@code null} if not set.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-session-1_0.html#RPLogout"
     *      >OpenID Connect Session Management 1.0, 5. RP-Initiated Logout</a>
     *
     * @since 2.69
     */
    public Service setEndSessionEndpoint(URI endSessionEndpoint)
    {
        this.endSessionEndpoint = endSessionEndpoint;

        return this;
    }


    /**
     * Get the flag indicating whether this service requires that clients use
     * the pushed authorization request endpoint.
     *
     * <p>
     * This property corresponds to the
     * {@code require_pushed_authorization_requests} server metadata defined
     * in "OAuth 2.0 Pushed Authorization Requests".
     * </p>
     *
     * @return
     *         {@code true} if client of this service are required to use PAR.
     *
     * @since 2.77
     */
    public boolean isParRequired()
    {
        return parRequired;
    }


    /**
     * Set the flag indicating whether this service requires that clients use
     * the pushed authorization request endpoint.
     *
     * <p>
     * This property corresponds to the
     * {@code require_pushed_authorization_requests} server metadata defined
     * in "OAuth 2.0 Pushed Authorization Requests".
     * </p>
     *
     * @param required
     *         {@code true} to indicate that this service requires that clients
     *         use the pushed authorization request endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.77
     */
    public Service setParRequired(boolean required)
    {
        this.parRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether this service requires that authorization
     * requests always utilize a request object by using either {@code request}
     * or {@code request_uri} request parameter.
     *
     * <p>
     * If this flag is {@code true} and
     * {@link #isTraditionalRequestObjectProcessingApplied()} returns
     * {@code false}, the value of {@code require_signed_request_object} server
     * metadata of this service is reported as {@code true} in the discovery
     * document. The metadata is defined in JAR (JWT Secured Authorization
     * Request). That {@code require_signed_request_object} is {@code true}
     * means that authorization requests which don't conform to the JAR
     * specification are rejected.
     * </p>
     *
     * @return
     *         {@code true} if this service requires that authorization
     *         requests always utilize a request object.
     *
     * @since 2.80
     */
    public boolean isRequestObjectRequired()
    {
        return requestObjectRequired;
    }


    /**
     * Set the flag indicating whether this service requires that authorization
     * requests always utilize a request object by using either {@code request}
     * or {@code request_uri} request parameter.
     *
     * <p>
     * See the description of {@link #isRequestObjectRequired()} for details.
     * </p>
     *
     * @param required
     *         {@code true} to require that authorization requests always
     *         utilize a request object.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.80
     */
    public Service setRequestObjectRequired(boolean required)
    {
        this.requestObjectRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether a request object is processed based on
     * rules defined in OpenID Connect Core 1.0 or JAR (JWT Secured
     * Authorization Request).
     *
     * <p>
     * Differences between rules in OpenID Connect Core 1.0 and ones in JAR are
     * as follows.
     * </p>
     *
     * <ul>
     * <li>JAR requires that a request object be always signed.
     * <li>JAR does not allow request parameters outside a request object to
     *     be referred to.
     * <li>OIDC Core 1.0 requires that {@code response_type} request parameter
     *     exist outside a request object even if the request object includes
     *     the request parameter.
     * <li>OIDC Core 1.0 requires that {@code scope} request parameter exist
     *     outside a request object if the authorization request is an OIDC
     *     request even if the request object includes the request parameter.
     * </ul>
     *
     * <p>
     * If this flag is {@code false} and {@link #isRequestObjectRequired()}
     * returns {@code true}, the value of {@code require_signed_request_object}
     * server metadata of this service is reported as {@code true} in the
     * discovery document. The metadata is defined in JAR (JWT Secured
     * Authorization Request). That {@code require_signed_request_object} is
     * {@code true} means that authorization requests which don't conform to
     * the JAR specification are rejected.
     * </p>
     *
     * @return
     *         {@code true} if rules defined in OpenID Connect Core 1.0 are
     *         applied on processing a request object. {@code false} if rules
     *         defined in JAR (JWT Secured Authorization Request) are applied.
     *
     * @since 2.80
     */
    public boolean isTraditionalRequestObjectProcessingApplied()
    {
        return traditionalRequestObjectProcessingApplied;
    }


    /**
     * Set the flag indicating whether a request object is processed based on
     * rules defined in OpenID Connect Core 1.0 or JAR (JWT Secured
     * Authorization Request).
     *
     * <p>
     * See the description of {@link #isTraditionalRequestObjectProcessingApplied()}
     * for details.
     * </p>
     *
     * @param applied
     *         {@code true} to apply rules defined in OpenID Connect Core 1.0
     *         on processing a request object. {@code false} to apply rules
     *         defined in JAR instead.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.80
     */
    public Service setTraditionalRequestObjectProcessingApplied(boolean applied)
    {
        this.traditionalRequestObjectProcessingApplied = applied;

        return this;
    }


    /**
     * Get the flag indicating whether claims specified by shortcut scopes
     * (e.g. {@code profile}) are included in the issued ID token only when
     * no access token is issued.
     *
     * <p>
     * To strictly conform to the description below excerpted from OpenID
     * Connect Core 1.0 <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     * >Section 5.4</a>, this flag has to be true.
     * </p>
     *
     * <blockquote>
     * <p><i>
     * The Claims requested by the {@code profile}, {@code email},
     * {@code address}, and {@code phone} scope values are returned from the
     * UserInfo Endpoint, as described in Section 5.3.2, when a
     * {@code response_type} value is used that results in an Access Token
     * being issued. However, when no Access Token is issued (which is the
     * case for the {@code response_type} value {@code id_token}), the
     * resulting Claims are returned in the ID Token.
     * </i></p>
     * </blockquote>
     *
     * @return
     *         {@code true} if claims specified by shortcut scopes are included
     *         in the issued ID token only when no access token is issued.
     *         {@code false} if the claims are included in the issued ID token
     *         regardless of whether an access token is issued or not.
     *
     * @since 2.81
     */
    public boolean isClaimShortcutRestrictive()
    {
        return claimShortcutRestrictive;
    }


    /**
     * Set the flag indicating whether claims specified by shortcut scopes
     * (e.g. {@code profile}) are included in the issued ID token only when
     * no access token is issued.
     *
     * <p>
     * To strictly conform to the description below excerpted from OpenID
     * Connect Core 1.0 <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     * >Section 5.4</a>, this flag has to be true.
     * </p>
     *
     * <blockquote>
     * <p><i>
     * The Claims requested by the {@code profile}, {@code email},
     * {@code address}, and {@code phone} scope values are returned from the
     * UserInfo Endpoint, as described in Section 5.3.2, when a
     * {@code response_type} value is used that results in an Access Token
     * being issued. However, when no Access Token is issued (which is the
     * case for the {@code response_type} value {@code id_token}), the
     * resulting Claims are returned in the ID Token.
     * </i></p>
     * </blockquote>
     *
     * @param restrictive
     *         {@code true} to include claims specified by shortcut scopes
     *         in the issued ID token only when no access token is issued.
     *         {@code false} to include the claims in the issued ID token
     *         regardless of whether an access token is issued or not.
     *
     * @return
     *         {@code this} object.
     */
    public Service setClaimShortcutRestrictive(boolean restrictive)
    {
        this.claimShortcutRestrictive = restrictive;

        return this;
    }


    /**
     * Get the flag indicating whether requests that request no scope are
     * rejected or not.
     *
     * <p>
     * When a request has no explicit {@code scope} parameter and the service's
     * pre-defined default scope set is empty, the authorization server regards
     * the request requests no scope. When this flag is true, requests that
     * request no scope are rejected.
     * </p>
     *
     * <p>
     * The requirement below excerpted from RFC 6749 <a href=
     * "https://tools.ietf.org/html/rfc6749#section-3.3">Section 3.3</a> does
     * not explicitly mention the case where the default scope set is empty.
     * </p>
     *
     * <blockquote>
     * <p><i>
     * If the client omits the {@code scope} parameter when requesting
     * authorization, the authorization server MUST either process the request
     * using a pre-defined default value or fail the request indicating an
     * invalid scope.
     * </i></p>
     * </blockquote>
     *
     * <p>
     * However, if you interpret <i>"the default scope set exists but is empty"</i>
     * as <i>"the default scope set does not exist"</i> and want to strictly
     * conform to the requirement above, this flag has to be true.
     * </p>
     *
     * @return
     *         {@code true} if the authorization server rejects requests that
     *         request no scope. {@code false} if the authorization server
     *         admits requests that request no scope.
     *
     * @since 2.81
     */
    public boolean isScopeRequired()
    {
        return scopeRequired;
    }


    /**
     * Set the flag indicating whether requests that request no scope are
     * rejected or not.
     *
     * <p>
     * When a request has no explicit {@code scope} parameter and the service's
     * pre-defined default scope set is empty, the authorization server regards
     * the request requests no scope. When this flag is true, requests that
     * request no scope are rejected.
     * </p>
     *
     * <p>
     * The requirement below excerpted from RFC 6749 <a href=
     * "https://tools.ietf.org/html/rfc6749#section-3.3">Section 3.3</a> does
     * not explicitly mention the case where the default scope set is empty.
     * </p>
     *
     * <blockquote>
     * <p><i>
     * If the client omits the {@code scope} parameter when requesting
     * authorization, the authorization server MUST either process the request
     * using a pre-defined default value or fail the request indicating an
     * invalid scope.
     * </i></p>
     * </blockquote>
     *
     * <p>
     * However, if you interpret <i>"the default scope set exists but is empty"</i>
     * as <i>"the default scope set does not exist"</i> and want to strictly
     * conform to the requirement above, this flag has to be true.
     * </p>
     *
     * @param required
     *         {@code true} to reject requests that request no scope.
     *         {@code false} to admit requests that request no scope.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.82
     */
    public Service setScopeRequired(boolean required)
    {
        this.scopeRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether the {@code nbf} claim in the request
     * object is optional even when the authorization request is regarded as
     * a FAPI-Part2 request.
     *
     * <p>
     * The final version of Financial-grade API was approved in January, 2021.
     * The Part 2 of the final version has new requirements on lifetime of
     * request objects. They require that request objects contain an {@code nbf}
     * claim and the lifetime computed by {@code exp - nbf} be no longer than
     * 60 minutes.
     * </p>
     *
     * <p>
     * Therefore, when an authorization request is regarded as a FAPI-Part2
     * request, the request object used in the authorization request must
     * contain an {@code nbf} claim. Otherwise, the authorization server
     * rejects the authorization request.
     * </p>
     *
     * <p>
     * When this flag is {@code true}, the {@code nbf} claim is treated as an
     * optional claim even when the authorization request is regarded as a
     * FAPI-Part2 request. That is, the authorization server does not perform
     * the validation on lifetime of the request object.
     * </p>
     *
     * <p>
     * Skipping the validation is a violation of the FAPI specification. The
     * reason why this flag has been prepared nevertheless is that the new
     * requirements (which do not exist in the Implementer's Draft 2 released
     * in October, 2018) have big impacts on deployed implementations of client
     * applications and Authlete thinks there should be a mechanism whereby to
     * make the migration from ID2 to Final smooth without breaking live
     * systems.
     * </p>
     *
     * @return
     *         {@code true} if the {@code nbf} claim is treated as an optional
     *         claim even when the authorization request is regarded as a
     *         FAPI-Part2 request.
     *
     * @since 2.86
     */
    public boolean isNbfOptional()
    {
        return nbfOptional;
    }


    /**
     * Set the flag indicating whether the {@code nbf} claim in the request
     * object is optional even when the authorization request is regarded as
     * a FAPI-Part2 request.
     *
     * <p>
     * See the description of {@link #isNbfOptional()} for details about this
     * flag.
     * </p>
     *
     * @param optional
     *         {@code true} to treat the {@code nbf} claim as an optional claim.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.86
     */
    public Service setNbfOptional(boolean optional)
    {
        this.nbfOptional = optional;

        return this;
    }


    /**
     * Get the flag indicating whether generation of the {@code iss} response
     * parameter is suppressed.
     *
     * <p>
     * &quot;OAuth 2.0 Authorization Server Issuer Identifier in Authorization
     * Response&quot; has defined a new authorization response parameter,
     * {@code iss}, as a countermeasure for a certain type of mix-up attacks.
     * </p>
     *
     * <p>
     * The specification requires that the {@code iss} response parameter
     * always be included in authorization responses unless JARM (JWT Secured
     * Authorization Response Mode) is used.
     * </p>
     *
     * <p>
     * When this flag is {@code true}, the authorization server does not include
     * the {@code iss} response parameter in authorization responses. By turning
     * this flag on and off, developers of client applications can experiment
     * the mix-up attack and the effect of the {@code iss} response parameter.
     * </p>
     *
     * <p>
     * Note that this flag should not be {@code true} in production environment
     * unless there are special reasons for it.
     * </p>
     *
     * @return
     *         {@code true} if the authorization server does not include the
     *         {@code iss} response parameter in authorization responses.
     *
     * @since 2.86
     */
    public boolean isIssSuppressed()
    {
        return issSuppressed;
    }


    /**
     * Set the flag indicating whether generation of the {@code iss} response
     * parameter is suppressed.
     *
     * <p>
     * See the description of {@link #isIssSuppressed()} for details about
     * this flag.
     * </p>
     *
     * @param suppressed
     *         {@code true} to make the authorization server suppress the
     *         {@code iss} response parameter.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.86
     */
    public Service setIssSuppressed(boolean suppressed)
    {
        this.issSuppressed = suppressed;

        return this;
    }


    /**
     * Get attributes.
     *
     * <p>
     * The feature of "service attributes" is available since Authlete 2.2.
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
     * The feature of "service attributes" is available since Authlete 2.2.
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
    public Service setAttributes(Pair[] attributes)
    {
        this.attributes = attributes;

        return this;
    }


    /**
     * Set attributes.
     *
     * <p>
     * The feature of "service attributes" is available since Authlete 2.2.
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
    public Service setAttributes(Iterable<Pair> attributes)
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
}
