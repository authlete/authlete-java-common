/*
 * Copyright (C) 2014-2025 Authlete, Inc.
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
import com.authlete.common.types.AttachmentType;
import com.authlete.common.types.ClaimType;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.ClientRegistrationType;
import com.authlete.common.types.DeliveryMode;
import com.authlete.common.types.Display;
import com.authlete.common.types.FapiMode;
import com.authlete.common.types.GrantType;
import com.authlete.common.types.JWSAlg;
import com.authlete.common.types.Prompt;
import com.authlete.common.types.ResponseType;
import com.authlete.common.types.ServiceProfile;
import com.authlete.common.types.Sns;
import com.authlete.common.types.UserCodeCharset;


/**
 * Information about a service.
 *
 * <p>
 * Some properties correspond to the ones listed in <a href=
 * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata"
 * >OpenID Provider Metadata</a> in <a href=
 * "https://openid.net/specs/openid-connect-discovery-1_0.html"
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
 *       claim is the thumbprint of the public key.
 *       See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449
 *       OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code aud}</td>
 *     <td>array</td>
 *     <td>
 *       If this access token has been generated with target resources, this
 *       claim is included. See <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
 *       >RFC 8707</a> (Resource Indicators for OAuth 2.0) for details.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code authorization_details}</td>
 *     <td>array</td>
 *     <td>
 *       If this access token has been generated with {@code authorization_details},
 *       this claim is included.
 *       See <a href="https://www.rfc-editor.org/rfc/rfc9396.html">RFC 9396</a>
 *       (OAuth 2.0 Rich Authorization Requests) for details.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code grant_id}</td>
 *     <td>string</td>
 *     <td>
 *       The grant ID tied to this access token. This field may be set if
 *       the authorization request which created this access token included
 *       {@code grant_id} or {@code grant_management_action=create}.
 *       See <i>"Grant Management for OAuth 2.0"</i> for details about the
 *       request parameters.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code grant}</td>
 *     <td>object</td>
 *     <td>
 *       <p>
 *       The grant this access token has inherited. This field may be set if
 *       the authorization request which created this access token included
 *       {@code grant_management_action=update}. See <i>"Grant Management for
 *       OAuth 2.0"</i> for details about the request parameter.
 *       </p>
 *       <p>
 *       The format of this JSON object is the same as the response from the
 *       grant management endpoint which is defined in <i>"Grant Management for
 *       OAuth 2.0"</i>.
 *       </p>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code acr}</td>
 *     <td>string</td>
 *     <td>
 *       The authentication context class of the user authentication that the
 *       authorization server performed during the course of issuing the access
 *       token.
 *       See <a href="https://www.rfc-editor.org/rfc/rfc9470.html">RFC 9470</a>
 *       (OAuth 2.0 Step Up Authentication Challenge Protocol) for details.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code auth_time}</td>
 *     <td>integer</td>
 *     <td>
 *       The time when the user authentication was performed for the access
 *       token. The value represents the number of seconds elapsed since the
 *       Unix epoch.
 *       See <a href="https://www.rfc-editor.org/rfc/rfc9470.html">RFC 9470</a>
 *       (OAuth 2.0 Step Up Authentication Challenge Protocol) for details.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@code grant_type}</td>
 *     <td>string</td>
 *     <td>
 *       <p>
 *       The grant type that was used for the issuance of the access token.
 *       Possible values are as follows.
 *       </p>
 *       <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *         <tr bgcolor="orange">
 *           <th>Value</th>
 *           <th>Since Authlete Version</th>
 *         </tr>
 *         <tr>
 *           <td>{@code "authorization_code"}</td>
 *           <td>2.1.24, 2.2.36, 2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "implicit"}</td>
 *           <td>2.1.24, 2.2.36, 2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "password"}</td>
 *           <td>2.1.24, 2.2.36, 2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "client_credentials"}</td>
 *           <td>2.1.24, 2.2.36, 2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "urn:openid:params:grant-type:ciba"}</td>
 *           <td>2.1.24, 2.2.36, 2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "urn:ietf:params:oauth:grant-type:device_code"}</td>
 *           <td>2.1.24, 2.2.36, 2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "urn:ietf:params:oauth:grant-type:token-exchange"}</td>
 *           <td>2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}</td>
 *           <td>2.3</td>
 *         </tr>
 *         <tr>
 *           <td>{@code "urn:ietf:params:oauth:grant-type:pre-authorized_code"}</td>
 *           <td>3.0</td>
 *         </tr>
 *       </table>
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
 * <a href="https://www.rfc-editor.org/rfc/rfc8707.html">RFC 8707</a>
 * (Resource Indicators for OAuth 2.0) is supported since Authlete 2.2.
 * The {@code resource} request parameter given to older Authlete versions is
 * just ignored, so JWT-based access tokens won't include the {@code aud} claim.
 * </p>
 *
 * <p>
 * <a href="https://www.rfc-editor.org/rfc/rfc9396.html">RFC 9396</a>
 * (OAuth 2.0 Rich Authorization Requests) is supported since Authlete 2.2.
 * The {@code authorization_details} request parameter given to older Authlete
 * versions is just ignored, so JWT-based access tokens won't include the
 * {@code authorization_details} claim.
 * </p>
 *
 * <p>
 * <i>"Grant Management for OAuth 2.0"</i> is supported since Authlete 2.3.
 * The {@code grant_id} and {@code grant_management_action} request parameters
 * given to older Authlete versions are just ignored, so JWT-based access token
 * won't include the {@code grant_id} and {@code grant} claims.
 * </p>
 *
 * <p>
 * <a href="https://www.rfc-editor.org/rfc/rfc9470.html">RFC 9470</a>
 * (OAuth 2.0 Step Up Authentication Challenge Protocol) is supported
 * since Authlete 2.3. JWT access tokens issued by older Authlete versions
 * won't include the {@code acr} and {@code auth_time} claims.
 * </p>
 *
 * <p>
 * Some Authlete APIs (e.g. {@code /api/auth/authorization/issue}) recognize
 * the {@code jwtAtClaims} request parameter since Authlete 2.3. Its format is
 * JSON object. The content of the JSON object will be merged into the payload
 * part of the JWT access token.
 * </p>
 * </blockquote>
 *
 * @see <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
 *      >OpenID Connect Discovery 1.0</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
 *      >RFC 8707 Resource Indicators for OAuth 2.0</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc9396.html"
 *      >RFC 9396 OAuth 2.0 Rich Authorization Requests</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc9470.html"
 *      >RFC 9470 OAuth 2.0 Step Up Authentication Challenge Protocol</a>
 */
public class Service implements Serializable
{
    private static final long serialVersionUID = 88L;


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    /**
     * Service number.
     * @since Authlete 1.1
     */
    private int number;


    /**
     * Service owner number.
     * @since Authlete 1.1
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private int serviceOwnerNumber;


    /**
     * Service name.
     * @since Authlete 1.1
     */
    private String serviceName;


    /**
     * API key.
     * @since Authlete 1.1
     */
    private long apiKey;


    /**
     * API secret.
     * @since Authlete 1.1
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private String apiSecret;


    /**
     * @since Authlete 1.1
     */
    private URI issuer;

    /**
     * @since Authlete 1.1
     */
    private URI authorizationEndpoint;

    /**
     * @since Authlete 1.1
     */
    private URI tokenEndpoint;

    /**
     * @since Authlete 1.1
     */
    private URI revocationEndpoint;

    /**
     * @since Authlete 1.1.19
     */
    private ClientAuthMethod[] supportedRevocationAuthMethods;

    /**
     * @since Authlete 1.1
     */
    private URI userInfoEndpoint;

    /**
     * @since Authlete 1.1
     */
    private URI jwksUri;

    /**
     * @since Authlete 1.1
     */
    private String jwks;

    /**
     * @since Authlete 1.1
     */
    private URI registrationEndpoint;

    /**
     * @since Authlete 2.0.0
     */
    private URI registrationManagementEndpoint;

    /**
     * @since Authlete 1.1
     */
    private Scope[] supportedScopes;

    /**
     * @since Authlete 1.1
     */
    private ResponseType[] supportedResponseTypes;

    /**
     * @since Authlete 1.1
     */
    private GrantType[] supportedGrantTypes;

    /**
     * @since Authlete 1.1
     */
    private String[] supportedAcrs;

    /**
     * @since Authlete 1.1
     */
    private ClientAuthMethod[] supportedTokenAuthMethods;

    /**
     * @since Authlete 1.1
     */
    private Display[] supportedDisplays;

    /**
     * @since Authlete 1.1
     */
    private ClaimType[] supportedClaimTypes;

    /**
     * @since Authlete 1.1
     */
    private String[] supportedClaims;

    /**
     * @since Authlete 1.1
     */
    private URI serviceDocumentation;

    /**
     * @since Authlete 1.1
     */
    private String[] supportedClaimLocales;

    /**
     * @since Authlete 1.1
     */
    private String[] supportedUiLocales;

    /**
     * @since Authlete 1.1
     */
    private URI policyUri;

    /**
     * @since Authlete 1.1
     */
    private URI tosUri;

    /**
     * @since Authlete 1.1
     */
    private URI authenticationCallbackEndpoint;

    /**
     * @since Authlete 1.1
     */
    private String authenticationCallbackApiKey;

    /**
     * @since Authlete 1.1
     */
    private String authenticationCallbackApiSecret;

    /**
     * @since Authlete 1.1
     */
    private Sns[] supportedSnses;

    /**
     * @since Authlete 1.1
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private SnsCredentials[] snsCredentials;

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
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private URI developerAuthenticationCallbackEndpoint;

    /**
     * @since Authlete 1.1
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private String developerAuthenticationCallbackApiKey;

    /**
     * @since Authlete 1.1
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private String developerAuthenticationCallbackApiSecret;

    /**
     * @since Authlete 1.1
     */
    private Sns[] supportedDeveloperSnses;

    /**
     * @since Authlete 1.1
     * @deprecated Authlete 3.0
     */
    @Deprecated
    private SnsCredentials[] developerSnsCredentials;

    /**
     * @since Authlete 1.1
     */
    private int clientsPerDeveloper;

    /**
     * @since Authlete 1.1
     */
    private boolean directAuthorizationEndpointEnabled;

    /**
     * @since Authlete 1.1
     */
    private boolean directTokenEndpointEnabled;

    /**
     * @since Authlete 1.1
     */
    private boolean directRevocationEndpointEnabled;

    /**
     * @since Authlete 1.1
     */
    private boolean directUserInfoEndpointEnabled;

    /**
     * @since Authlete 1.1
     */
    private boolean directJwksEndpointEnabled;

    /**
     * @since Authlete 1.1
     */
    private boolean directIntrospectionEndpointEnabled;

    /**
     * @since Authlete 1.1
     */
    private boolean singleAccessTokenPerSubject;

    /**
     * @since Authlete 1.1
     */
    private boolean pkceRequired;

    /**
     * @since Authlete 1.1
     */
    private boolean pkceS256Required;

    /**
     * @since Authlete 1.1
     */
    private boolean refreshTokenKept;

    /**
     * @since Authlete 2.2.1
     */
    private boolean refreshTokenDurationKept;

    /**
     * @since Authlete 2.2.13
     */
    private boolean refreshTokenDurationReset;

    /**
     * @since Authlete 1.1
     */
    private boolean errorDescriptionOmitted;

    /**
     * @since Authlete 1.1
     */
    private boolean errorUriOmitted;

    /**
     * @since Authlete 1.1
     */
    private boolean clientIdAliasEnabled;

    /**
     * @since Authlete 1.1.19
     */
    private ServiceProfile[] supportedServiceProfiles;

    /**
     * @since Authlete 1.1.19
     */
    private boolean tlsClientCertificateBoundAccessTokens;

    /**
     * @since Authlete 1.1.19
     */
    private URI introspectionEndpoint;

    /**
     * @since Authlete 1.1.19
     */
    private ClientAuthMethod[] supportedIntrospectionAuthMethods;

    /**
     * @since Authlete 1.1.19
     */
    private boolean mutualTlsValidatePkiCertChain;

    /**
     * @since Authlete 1.1.19
     */
    private String[] trustedRootCertificates;

    /**
     * @since Authlete 2.0.0
     */
    private boolean dynamicRegistrationSupported;

    /**
     * @since Authlete 2.2.1
     */
    private URI endSessionEndpoint;


    /**
     * Description of this service.
     * @since Authlete 1.1
     */
    private String description;


    /**
     * Access token type.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     *
     * @since Authlete 1.1
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
     * @since Authlete 2.0.0
     */
    private JWSAlg accessTokenSignAlg;


    /**
     * Duration of access tokens in seconds.
     * @since Authlete 1.1
     */
    private long accessTokenDuration;


    /**
     * Duration of refresh tokens in seconds.
     * @since Authlete 1.1
     */
    private long refreshTokenDuration;


    /**
     * Duration of ID tokens in seconds.
     * @since Authlete 1.1
     */
    private long idTokenDuration;


    /**
     * Duration of authorization response JWTs.
     *
     * @since 2.28
     * @since Authlete 2.0.0
     */
    private long authorizationResponseDuration;


    /**
     * Duration of pushed authorization requests.
     *
     * @since 2.51
     * @since Authlete 2.2.0
     */
    private long pushedAuthReqDuration;


    /**
     * Metadata.
     *
     * @since 1.39
     * @since Authlete 1.1
     */
    private Pair[] metadata;


    /**
     * Key ID to identify a JWK used for access token signature using an
     * asymmetric key.
     *
     * @since 2.37
     * @since Authlete 2.0.0
     */
    private String accessTokenSignatureKeyId;


    /**
     * Key ID to identify a JWK used for signing authorization responses using an
     * asymmetric key. Regarding "signing the authorization response", see <a href=
     * "https://openid.net/specs/openid-financial-api-jarm.html">Financial-grade API:
     * JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
     *
     * @since 2.28
     * @since Authlete 2.0.0
     */
    private String authorizationSignatureKeyId;


    /**
     * Key ID to identify a JWK used for ID token signature using an asymmetric key.
     *
     * @since 2.1
     * @since Authlete 1.1
     */
    private String idTokenSignatureKeyId;


    /**
     * Key ID to identify a JWK used for User Info signature using an asymmetric key.
     *
     * @since 2.1
     * @since Authlete 1.1
     */
    private String userInfoSignatureKeyId;


    /**
     * Supported backchannel token delivery modes. This property corresponds
     * to the {@code backchannel_token_delivery_modes_supported} metadata.
     *
     * @since 2.32
     * @since Authlete 2.0.0
     */
    private DeliveryMode[] supportedBackchannelTokenDeliveryModes;


    /**
     * The backchannel authentication endpoint. This property corresponds to
     * the {@code backchannel_authentication_endpoint} metadata.
     *
     * @since 2.32
     * @since Authlete 2.0.0
     */
    private URI backchannelAuthenticationEndpoint;


    /**
     * Boolean flag which indicates whether "user code" is supported at the
     * backchannel authentication endpoint. This property corresponds to the
     * {@code backchannel_user_code_parameter_supported} metadata.
     *
     * @since 2.32
     * @since Authlete 2.0.0
     */
    private boolean backchannelUserCodeParameterSupported;


    /**
     * Duration of backchannel authentication request IDs issued from the
     * backchannel authentication endpoint in seconds. This is used as the
     * value of the {@code expires_in} property in responses from the
     * backchannel authentication endpoint.
     *
     * @since 2.32
     * @since Authlete 2.0.0
     */
    private int backchannelAuthReqIdDuration;


    /**
     * The minimum interval between polling requests to the token endpoint
     * from client applications in seconds. This is used as the value of
     * the {@code interval} property in responses from the backchannel
     * authentication endpoint.
     *
     * @since 2.32
     * @since Authlete 2.0.0
     */
    private int backchannelPollingInterval;


    /**
     * Boolean flag which indicates whether the {@code binding_message}
     * request parameter is always required whenever a backchannel
     * authentication request is judged as a request for Financial-grade
     * API.
     *
     * @since 2.48
     * @since Authlete 2.0.0
     */
    private boolean backchannelBindingMessageRequiredInFapi;


    /**
     * The allowable clock skew between the server and clients.
     *
     * @since 2.32
     * @since Authlete 2.0.0
     */
    private int allowableClockSkew;


    /**
     * The device authorization endpoint. This property corresponds to
     * the {@code device_authorization_endpoint} metadata.
     *
     * @since 2.42
     * @since Authlete 2.0.0
     */
    private URI deviceAuthorizationEndpoint;


    /**
     * The verification URI for the device flow.
     *
     * @since 2.42
     * @since Authlete 2.0.0
     */
    private URI deviceVerificationUri;


    /**
     * The verification URI for the device flow with a placeholder for a
     * user code.
     *
     * @since 2.42
     * @since Authlete 2.0.0
     */
    private URI deviceVerificationUriComplete;


    /**
     * Duration of device verification codes and end-user verification codes
     * issued from the device authorization endpoint in seconds. This is used
     * as the value of the {@code expires_in} property in responses from the
     * device authorization endpoint.
     *
     * @since 2.42
     * @since Authlete 2.0.0
     */
    private int deviceFlowCodeDuration;


    /**
     * The minimum interval between polling requests to the token endpoint from
     * client applications in seconds in device flow. This is used as the value
     * of the {@code interval} property in responses from the device
     * authorization endpoint.
     *
     * @since 2.42
     * @since Authlete 2.0.0
     */
    private int deviceFlowPollingInterval;


    /**
     * Character set for end-user verification codes (user_code) for Device Flow.
     *
     * @since 2.43
     * @since Authlete 2.0.0
     */
    private UserCodeCharset userCodeCharset;


    /**
     * Length of end-user verification codes (user_code) for Device Flow.
     *
     * @since 2.43
     * @since Authlete 2.0.0
     */
    private int userCodeLength;


    /**
     * The URI of the pushed authorization request endpoint.
     *
     * @since 2.52
     * @since Authlete 2.2.0
     */
    private URI pushedAuthReqEndpoint;


    /**
     * MTLS endpoint aliases.
     *
     * @since 2.49
     * @since Authlete 2.0.0
     */
    private NamedUri[] mtlsEndpointAliases;


    /**
     * Supported authorization details types for {@code "authorization_details"}.
     *
     * <p>
     * This property was renamed from {@code supportedAuthorizationDataTypes} to
     * align with the change made by the 5th draft of the RAR specification.
     * </p>
     *
     * @since 2.91
     * @since Authlete 2.2.7
     */
    private String[] supportedAuthorizationDetailsTypes;


    /**
     * Supported trust frameworks. This property corresponds to the
     * {@code trust_frameworks_supported} server metadata which is defined in
     * OpenID Connect for Identity Assurance 1.0.
     *
     * @since 2.63
     * @since Authlete 2.2.1
     */
    private String[] supportedTrustFrameworks;


    /**
     * Supported evidence. This property corresponds to the
     * {@code evidence_supported} server metadata which is defined in
     * OpenID Connect for Identity Assurance 1.0.
     *
     * @since 2.63
     * @since Authlete 2.2.1
     */
    private String[] supportedEvidence;


    /**
     * Supported ID documents. This property corresponds to the
     * {@code id_documents_supported} server metadata which was defined in
     * old drafts of OpenID Connect for Identity Assurance 1.0.
     *
     * <p>
     * The third implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 renamed the {@code id_documents_supported} server metadata to
     * {@code documents_supported}.
     * </p>
     *
     * @since 2.63
     * @since Authlete 2.2.1
     *
     * @see supportedDocuments
     *
     * @deprecated
     */
    @Deprecated
    private String[] supportedIdentityDocuments;


    /**
     * Supported documents. This property corresponds to the {@code documents_supported}
     * server metadata which was renamed to from {@code id_documents_supported}
     * by the third implementer's draft of OpenID Connect for Identity Assurance
     * 1.0.
     *
     * @since 3.13
     * @since Authlete 2.3.0
     */
    private String[] supportedDocuments;


    /**
     * Supported verification methods. This property corresponds to the
     * {@code id_documents_verification_methods_supported} server metadata
     * which was defined in old drafts of OpenID Connect for Identity Assurance
     * 1.0.
     *
     * <p>
     * The third implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 renamed the {@code id_documents_verification_methods_supported}
     * server metadata to {@code documents_methods_supported}.
     * </p>
     *
     * @since 2.63
     * @since Authlete 2.2.1
     *
     * @see supportedDocumentsMethods
     *
     * @deprecated
     */
    @Deprecated
    private String[] supportedVerificationMethods;


    /**
     * Supported validation and verification processes. This property corresponds
     * to the {@code documents_methods_supported} server metadata which was
     * renamed to from {@code id_documents_verification_methods_supported}
     * by the third implementer's draft of OpenID Connect for Identity Assurance
     * 1.0.
     *
     * @since 3.13
     * @since Authlete 2.3.0
     */
    private String[] supportedDocumentsMethods;


    /**
     * Supported document validation methods. This property corresponds to the
     * {@code documents_validation_methods_supported} server metadata which
     * was added by the third implementer's draft of OpenID Connect for
     * Identity Assurance 1.0.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @since 3.13
     * @since Authlete 2.3.0
     *
     * @deprecated
     */
    @Deprecated
    private String[] supportedDocumentsValidationMethods;


    /**
     * Supported document verification methods. This property corresponds to
     * the {@code documents_verification_methods_supported} server metadata
     * which was added by the third implementer's draft of OpenID Connect for
     * Identity Assurance 1.0.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @since 3.13
     * @since Authlete 2.3.0
     *
     * @deprecated
     */
    @Deprecated
    private String[] supportedDocumentsVerificationMethods;


    /**
     * Supported document check methods. This property corresponds to the
     * {@code documents_check_methods_supported} server metadata which was
     * added by the fourth implementer's draft of OpenID Connect for Identity
     * Assurance 1.0.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @since 3.48
     * @since Authlete 2.3.0
     */
    private String[] supportedDocumentsCheckMethods;


    /**
     * Supported electronic record types. This property corresponds to the
     * {@code electronic_records_supported} server metadata which was added by
     * the third implementer's draft of OpenID Connect for Identity Assurance 1.0.
     *
     * @since 3.13
     * @since Authlete 2.3.0
     */
    private String[] supportedElectronicRecords;


    /**
     * Supported verified claims. This property corresponds to the
     * {@code claims_in_verified_claims_supported} server metadata which is
     * defined in OpenID Connect for Identity Assurance 1.0.
     *
     * @since 2.63
     * @since Authlete 2.2.1
     */
    private String[] supportedVerifiedClaims;


    /**
     * Supported attachment types. This property corresponds to the {@code
     * attachments_supported} server metadata which was added by the third
     * implementer's draft of OpenID Connect for Identity Assurance 1.0.
     *
     * @since 3.13
     * @since Authlete 2.3.0
     */
    private AttachmentType[] supportedAttachments;


    /**
     * Supported algorithms used to compute digest values of external
     * attachments. This property corresponds to the
     * {@code digest_algorithms_supported} server metadata which was added
     * by the third implementer's draft of OpenID Connect for Identity
     * Assurance 1.0.
     *
     * @since 3.13
     * @since Authlete 2.3.0
     */
    private String[] supportedDigestAlgorithms;


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
     * @since Authlete 2.2.1
     */
    private boolean missingClientIdAllowed;


    /**
     * The flag indicating whether this service requires that clients use the
     * pushed authorization request endpoint.
     *
     * @since 2.77
     * @since Authlete 2.2.1
     */
    private boolean parRequired;


    /**
     * The flag indicating whether authorization requests must utilize a
     * request object.
     *
     * @since 2.80
     * @since Authlete 2.2.1
     */
    private boolean requestObjectRequired;


    /**
     * The flag indicating whether traditional request object processing
     * (rules defined in OIDC Core 1.0) is applied.
     *
     * @since 2.80
     * @since Authlete 2.2.1
     */
    private boolean traditionalRequestObjectProcessingApplied;


    /**
     * The flag indicating whether claims specified by shortcut scopes
     * (e.g. profile) are included in the issued ID token only when no
     * access token is issued.
     *
     * @since 2.81
     * @since Authlete 2.2.1
     */
    private boolean claimShortcutRestrictive;


    /**
     * The flag indicating whether requests that request no scope are
     * rejected or not.
     *
     * @since 2.81
     * @since Authlete 2.2.1
     */
    private boolean scopeRequired;


    /**
     * The flag indicating whether the {@code nbf} claim in the request
     * object is optional even when the authorization request is regarded
     * as a FAPI-Part2 request.
     *
     * @since 2.86
     * @since Authlete 2.2.1
     */
    private boolean nbfOptional;


    /**
     * The flag indicating whether generation of the {@code iss} response
     * parameter is suppressed.
     *
     * @since 2.86
     * @since Authlete 2.2.1
     */
    private boolean issSuppressed;


    /**
     * Arbitrary attributes associated with this service.
     *
     * @since 2.87
     * @since Authlete 2.2.3
     */
    private Pair[] attributes;


    /**
     * Custom client metadata supported by this service.
     *
     * @since 2.93
     * @since Authlete 2.2.10
     */
    private String[] supportedCustomClientMetadata;


    /**
     * The flag indicating whether the expiration date of an access token
     * never exceeds that of the corresponding refresh token.
     *
     * @since 2.95
     * @since Authlete 2.2.12
     */
    private boolean tokenExpirationLinked;


    /**
     * The flag indicating whether encryption of request object is required
     * when the request object is passed through the front channel.
     *
     * @since 2.96
     * @since Authlete 2.2.10
     */
    private boolean frontChannelRequestObjectEncryptionRequired;


    /**
     * The flag indicating whether the JWE {@code alg} of encrypted request
     * object must match the value of the {@code request_object_encryption_alg}
     * client metadata.
     *
     * @since 2.96
     * @since Authlete 2.2.10
     */
    private boolean requestObjectEncryptionAlgMatchRequired;


    /**
     * The flag indicating whether the JWE {@code enc} of encrypted request
     * object must match the value of the {@code request_object_encryption_enc}
     * client metadata.
     *
     * @since 2.96
     * @since Authlete 2.2.10
     */
    private boolean requestObjectEncryptionEncMatchRequired;


    /**
     * The flag indicating whether HSM (Hardware Security Module) support is
     * enabled for this service.
     *
     * @since 2.97
     * @since Authlete 2.2.13
     */
    private boolean hsmEnabled;


    /**
     * Hardware-secured keys. Output only.
     *
     * @since 2.97
     * @since Authlete 2.2.13
     */
    private Hsk[] hsks;


    /**
     * The URL of the grant management endpoint.
     *
     * @since 3.1
     * @since Authlete 2.3.0
     */
    private URI grantManagementEndpoint;


    /**
     * The flag indicating whether every authorization request must include the
     * {@code grant_management_action} request parameter.
     *
     * @since 3.1
     * @since Authlete 2.3.0
     */
    private boolean grantManagementActionRequired;


    /**
     * The flag indicating whether to let /api/client/registration API use
     * ClientRegistrationResponse.Action.UNAUTHORIZED whenever appropriate.
     *
     * @since 3.4
     * @since Authlete 2.3.0
     */
    private boolean unauthorizedOnClientConfigSupported;


    /**
     * The flag indicating whether the {@code scope} request parameter in
     * dynamic client registration/update requests is used as requestable
     * scopes.
     *
     * @since 3.5
     * @since Authlete 2.3.0
     */
    private boolean dcrScopeUsedAsRequestable;


    /**
     * Predefined transformed claims in JSON format. Available from
     * Authlete 2.3 onwards.
     *
     * @since 3.8
     * @since Authlete 2.3.0
     */
    private String predefinedTransformedClaims;


    /**
     * The flag indicating whether the port number component of redirection
     * URIs can be variable when the host component indicates loopback.
     *
     * @since 3.12
     * @since Authlete 2.3.0
     */
    private boolean loopbackRedirectionUriVariable;


    /**
     * The flag indicating whether Authlete checks whether the {@code aud} claim
     * of request objects matches the issuer identifier of this service.
     *
     * @since 3.14
     * @since Authlete 2.3.0
     */
    private boolean requestObjectAudienceChecked;


    /**
     * The flag indicating whether Authlete generates access tokens for
     * external attachments and embeds them in ID tokens and userinfo
     * responses.
     *
     * @since 3.16
     * @since Authlete 2.3
     */
    private boolean accessTokenForExternalAttachmentEmbedded;


    /**
     * The flag indicating whether refresh token requests with the same
     * refresh token can be made multiple times in quick succession and
     * they can obtain the same renewed refresh token within the short
     * period.
     *
     * @since 3.21
     * @since Authlete 2.3
     */
    private boolean refreshTokenIdempotent;


    /**
     * The flag indicating whether this service supports OpenID
     * Federation 1&#002E0.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private boolean federationEnabled;


    /**
     * The human-readable name representing the organization that operates
     * this service. This property corresponds to the {@code organization_name}
     * server metadata that is defined in OpenID Federation 1.0.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private String organizationName;


    /**
     * Identifiers of entities that can issue entity statements for this
     * service. This property corresponds to the {@code authority_hints}
     * property that appears in a self-signed entity statement that is
     * defined in OpenID Federation 1.0.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private URI[] authorityHints;


    /**
     * Trust anchors that are referenced when this service resolves trust
     * chains of relying parties.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private TrustAnchor[] trustAnchors;


    /**
     * JWK Set document containing keys that are used to sign (1) self-signed
     * entity statement of this service and (2) the response from
     * {@code signed_jwks_uri}.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private String federationJwks;


    /**
     * A key ID to identify a JWK used to sign the entity configuration and
     * the signed JWK Set.
     *
     * @since 3.31
     * @since Authlete 2.3
     */
    private String federationSignatureKeyId;


    /**
     * The duration of the entity configuration in seconds.
     *
     * @since 3.31
     * @since Authlete 2.3
     */
    private long federationConfigurationDuration;


    /**
     * The URI of the endpoint that returns this service's JWK Set document in
     * the JWT format. This property corresponds to the {@code signed_jwks_uri}
     * server metadata defined in OpenID Federation 1.0.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private URI signedJwksUri;


    /**
     * The URI of the federation registration endpoint. This property corresponds
     * to the {@code federation_registration_endpoint} server metadata that is
     * defined in OpenID Federation 1.0.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private URI federationRegistrationEndpoint;


    /**
     * Supported client registration types. This property corresponds to the
     * {@code client_registration_types_supported} server metadata that is
     * defined in OpenID Federation 1.0.
     *
     * @since 3.22
     * @since Authlete 2.3
     */
    private ClientRegistrationType[] supportedClientRegistrationTypes;


    /**
     * The flag indicating whether to prohibit unidentifiable clients from
     * making token exchange requests.
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    private boolean tokenExchangeByIdentifiableClientsOnly;


    /**
     * The flag indicating whether to prohibit public clients from making
     * token exchange requests.
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    private boolean tokenExchangeByConfidentialClientsOnly;


    /**
     * The flag indicating whether to prohibit clients that have no explicit
     * permission from making token exchange requests.
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    private boolean tokenExchangeByPermittedClientsOnly;


    /**
     * The flag indicating whether to reject token exchange requests which
     * use encrypted JWTs as input tokens.
     *
     * @since 3.27
     * @since Authlete 2.3
     */
    private boolean tokenExchangeEncryptedJwtRejected;


    /**
     * The flag indicating whether to reject token exchange requests which
     * use unsigned JWTs as input tokens.
     *
     * @since 3.27
     * @since Authlete 2.3
     */
    private boolean tokenExchangeUnsignedJwtRejected;


    /**
     * The flag indicating whether to prohibit unidentifiable clients from
     * using the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    private boolean jwtGrantByIdentifiableClientsOnly;


    /**
     * The flag indicating whether to reject token requests that use an
     * encrypted JWT as an authorization grant with the grant type
     * {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    private boolean jwtGrantEncryptedJwtRejected;


    /**
     * The flag indicating whether to reject token requests that use an
     * unsigned JWT as an authorization grant with the grant type
     * {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    private boolean jwtGrantUnsignedJwtRejected;


    /**
     * The flag indicating whether to block DCR (Dynamic Client Registration)
     * requests whose {@code software_id} has already been used previously.
     *
     * @since 3.32
     * @since Authlete 2.2.30
     */
    private boolean dcrDuplicateSoftwareIdBlocked;


    /**
     * The key ID of a JWK containing the private key used by this service to
     * sign responses from the resource server.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    private String resourceSignatureKeyId;


    /**
     * The flag indicating whether this service signs responses from the resource
     * server.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    private boolean rsResponseSigned;


    /**
     * The flag indicating whether to remove the {@code openid} scope from a
     * new access token issued by the refresh token flow if the presented
     * refresh token does not contain the {@code offline_access} scope.
     *
     * @since 3.42
     * @since Authlete 2.2.36
     */
    private boolean openidDroppedOnRefreshWithoutOfflineAccess;


    /**
     * The flag indicating whether the feature of Verifiable Credentials for
     * this service is enabled or not.
     *
     * @since 3.55
     * @since Authlete 3.0
     */
    private boolean verifiableCredentialsEnabled;


    /**
     * Credential issuer metadata.
     *
     * @since 3.55
     * @since Authlete 3.0
     */
    private CredentialIssuerMetadata credentialIssuerMetadata;


    /**
     * The default duration of credential offers in seconds.
     *
     * @since 3.59
     * @since Authlete 3.0
     */
    private long credentialOfferDuration;


    /**
     * The default length of user PINs.
     *
     * @since 3.59
     * @since Authlete 3.0
     * @deprecated
     */
    @Deprecated
    private int userPinLength;


    /**
     * The type of the {@code aud} claim in ID tokens.
     *
     * @since 3.57
     * @since Authlete 2.3.3
     */
    private String idTokenAudType;


    /**
     * Supported {@code prompt} values.
     *
     * @since 3.58
     * @since Authlete 3.0
     */
    private Prompt[] supportedPromptValues;


    /**
     * The name of the validation schema set that is used to validate the
     * content of {@code "verified_claims"}.
     *
     * @since 3.61
     * @since Authlete 2.3.0
     */
    private String verifiedClaimsValidationSchemaSet;


    /**
     * The flag indicating whether token requests using the pre-authorized
     * code grant flow by unidentifiable clients are allowed.
     *
     * @since 3.62
     * @since Authlete 3.0
     */
    private boolean preAuthorizedGrantAnonymousAccessSupported;


    /**
     * The duration of {@code c_nonce}.
     *
     * <p>
     * The {@code cNonceDuration} field added by the version 3.63 has been
     * renamed to {@code cnonceDuration} by the version 3.90.
     * </p>
     *
     * @since 3.90
     * @since Authlete 3.0
     */
    private long cnonceDuration;


    /**
     * The duration of credential transaction in seconds.
     *
     * @since 3.66
     * @since Authlete 3.0
     */
    private long credentialTransactionDuration;


    /**
     * The default duration of credentials in seconds.
     *
     * @since 3.67
     * @since Authlete 3.0
     */
    private long credentialDuration;


    /**
     * JWK Set document containing private keys that are used to sign
     * verifiable credentials.
     *
     * @since 3.67
     * @since Authlete 3.0
     */
    private String credentialJwks;


    /**
     * The URL at which the JWK Set document of the credential issuer is exposed.
     *
     * @since 3.79
     * @since Authlete 3.0
     */
    private URI credentialJwksUri;


    /**
     * The flag indicating whether to enable the feature of ID token reissuance
     * in the refresh token flow.
     *
     * @since 3.68
     * @since Authlete 2.3.8
     * @since Authlete 3.0
     */
    private boolean idTokenReissuable;


    /**
     * The key ID of the key for signing introspection responses.
     *
     * @since 3.77
     * @since Authlete 3.0
     */
    private String introspectionSignatureKeyId;


    /**
     * The FAPI modes for this service.
     *
     * @since 3.80
     * @since Authlete 3.0
     */
    private FapiMode[] fapiModes;


    /**
     * Whether to require DPoP proof JWTs to include the {@code nonce} claim
     * whenever they are presented.
     *
     * @since 3.82
     * @since Authlete 3.0
     */
    private boolean dpopNonceRequired;


    /**
     * The duration of nonce values for DPoP proof JWTs in seconds.
     *
     * @since 3.82
     * @since Authlete 3.0
     */
    private long dpopNonceDuration;


    /**
     * The URI of the endpoint that receives token batch results.
     *
     * @since 3.96
     * @since Authlete 3.0
     */
    private URI tokenBatchNotificationEndpoint;


    /**
     * Whether to restrict the {@code aud} claim value in client assertions to the
     * issuer of this service as a string.
     *
     * @since 4.14
     * @since Authlete 3.0
     */
    private boolean clientAssertionAudRestrictedToIssuer;


    /**
     * Whether to support the Native SSO specification.
     *
     * @since 4.18
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
     *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
     */
    private boolean nativeSsoSupported;


    /**
     * The version of the OpenID for Verifiable Credential Issuance specification
     * to support.
     *
     * @since 4.25
     * @since Authlete 3.0.22
     */
    private String oid4vciVersion;


    /**
     * Whether to support <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >OAuth Client ID Metadata Document</a>.
     *
     * @since 4.29
     * @since Authlete 3.0.22
     */
    private boolean clientIdMetadataDocumentSupported;


    /**
     * Whether to enable the allowlist for client IDs in the CIMD context.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private boolean cimdAllowlistEnabled;


    /**
     * The allowlist for client IDs in the CIMD context.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private String[] cimdAllowlist;


    /**
     * Whether to always retrieve client metadata in the CIMD context
     * regardless of the cache's validity.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     */
    private boolean cimdAlwaysRetrieved;


    /**
     * Whether to allow the {@code http} scheme in client IDs in the CIMD
     * context.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     */
    private boolean cimdHttpPermitted;


    /**
     * Whether to allow a query component in client IDs in the CIMD context.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     */
    private boolean cimdQueryPermitted;


    /**
     * Whether to prohibit client ID aliases that start with {@code https://}
     * or {@code http://}.
     *
     * @since 4.32
     * @since Authlete 3.0.22
     */
    private boolean httpAliasProhibited;


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
     * @since 2.17
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
     * @since 2.17
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
     * Even if this flag is {@code false}, invalidation of existing access
     * tokens is executed if the {@code singleAccessTokenPerSubject}
     * property of the target client is {@code true}. The property of
     * {@link Client} is recognized by Authlete 2.3 onwards. (cf.
     * {@link Client#isSingleAccessTokenPerSubject()})
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
     *
     * @see Client#isSingleAccessTokenPerSubject()
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
     * property of the target client is {@code true}. The property of
     * {@link Client} is recognized by Authlete 2.3 onwards. (cf.
     * {@link Client#setSingleAccessTokenPerSubject(boolean)})
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
     *
     * @see Client#setSingleAccessTokenPerSubject(boolean)
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
     * Get the flag which indicates whether duration of refresh tokens are
     * reset when they are used even if the {@code refreshTokenKept} property
     * of this service (cf&#x2E; {@link #isRefreshTokenKept()}) is true
     * (= even if "Refresh Token Continuous Use" is "Kept").
     *
     * <p>
     * This flag has no effect when the {@code refreshTokenKept} property is
     * {@code false}. In other words, if this service issues a new refresh
     * token on every refresh token request, the refresh token will have fresh
     * duration (unless {@code refreshTokenDurationKept} is true) and this
     * {@code refreshTokenDurationReset} property is not referenced.
     * </p>
     *
     * @return
     *         {@code true} if duration of refresh tokens are reset when they
     *         are used.
     *
     * @since 2.98
     */
    public boolean isRefreshTokenDurationReset()
    {
        return refreshTokenDurationReset;
    }


    /**
     * Set the flag which indicates whether duration of refresh tokens are
     * reset when they are used even if the {@code refreshTokenKept} property
     * of this service (cf&#x2E; {@link #isRefreshTokenKept()}) is true
     * (= even if "Refresh Token Continuous Use" is "Kept").
     *
     * <p>
     * This flag has no effect when the {@code refreshTokenKept} property is
     * {@code false}. In other words, if this service issues a new refresh
     * token on every refresh token request, the refresh token will have fresh
     * duration (unless {@code refreshTokenDurationKept} is true) and this
     * {@code refreshTokenDurationReset} property is not referenced.
     * </p>
     *
     * @param reset
     *         {@code true} to reset duration of refresh tokens when they are
     *         used.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.98
     */
    public Service setRefreshTokenDurationReset(boolean reset)
    {
        this.refreshTokenDurationReset = reset;

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
     *
     * @see #isHttpAliasProhibited()
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
     *
     * @see #isHttpAliasProhibited()
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
     * defined in "5. Metadata for Mutual-TLS Endpoint Aliases" of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8705.html">RFC 8705 OAuth 2.0
     * Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>.
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
     * defined in "5. Metadata for Mutual-TLS Endpoint Aliases" of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8705.html">RFC 8705 OAuth 2.0
     * Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>.
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
     * Get the supported authorization details types that can be used as values
     * of the {@code "type"} field in {@code "authorization_details"}.
     *
     * <p>
     * This property corresponds to the
     * {@code "authorization_details_types_supported"} server metadata.
     * See "OAuth 2.0 Rich Authorization Requests" (RAR) for details.
     * </p>
     *
     * <p>
     * Note that the property name was renamed from {@code supportedAuthorizationDataTypes}
     * to {@code supportedAuthorizationDetailsTypes} to align with the change
     * made by the 5th draft of the RAR specification.
     * </p>
     *
     * @return
     *         Supported authorization details types.
     *
     * @since 2.91
     */
    public String[] getSupportedAuthorizationDetailsTypes()
    {
        return supportedAuthorizationDetailsTypes;
    }


    /**
     * Set the supported authorization details types that can be used as values
     * of the {@code "type"} field in {@code "authorization_details"}.
     *
     * <p>
     * This property corresponds to the
     * {@code "authorization_details_types_supported"} server metadata.
     * See "OAuth 2.0 Rich Authorization Requests" (RAR) for details.
     * </p>
     *
     * <p>
     * Note that the property name was renamed from {@code supportedAuthorizationDataTypes}
     * to {@code supportedAuthorizationDetailsTypes} to align with the change
     * made by the 5th draft of the RAR specification.
     * </p>
     *
     * @param types
     *         Supported authorization details types.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.91
     */
    public Service setSupportedAuthorizationDetailsTypes(String[] types)
    {
        this.supportedAuthorizationDetailsTypes = types;

        return this;
    }


    /**
     * Get trust frameworks supported by this service.
     * This property corresponds to the {@code trust_frameworks_supported} server
     * metadata.
     *
     * @return
     *         Trust frameworks supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 2.63
     */
    public String[] getSupportedTrustFrameworks()
    {
        return supportedTrustFrameworks;
    }


    /**
     * Set trust frameworks supported by this service.
     * This property corresponds to the {@code trust_frameworks_supported} server
     * metadata.
     *
     * @param frameworks
     *         Trust frameworks supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
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
     * This property corresponds to the {@code evidence_supported} server metadata.
     *
     * @return
     *         Evidence supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 2.63
     */
    public String[] getSupportedEvidence()
    {
        return supportedEvidence;
    }


    /**
     * Set evidence supported by this service.
     * This property corresponds to the {@code evidence_supported} server metadata.
     *
     * @param evidence
     *         Evidence supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
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
     * This property corresponds to the {@code id_documents_supported} server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_supported} server metadata to
     * {@code documents_supported}.
     * </p>
     *
     * @return
     *         Identity documents supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see #getSupportedDocuments()
     *
     * @since 2.63
     *
     * @deprecated
     */
    @Deprecated
    public String[] getSupportedIdentityDocuments()
    {
        return supportedIdentityDocuments;
    }


    /**
     * Set identity documents supported by this service.
     * This property corresponds to the {@code id_documents_supported} server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_supported} server metadata to
     * {@code documents_supported}.
     * </p>
     *
     * @param documents
     *         Identity documents supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see #setSupportedDocuments(String[])
     *
     * @since 2.63
     *
     * @deprecated
     */
    @Deprecated
    public Service setSupportedIdentityDocuments(String[] documents)
    {
        this.supportedIdentityDocuments = documents;

        return this;
    }


    /**
     * Get document types supported by this service. This property corresponds
     * to the {@code documents_supported} server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_supported} server metadata to
     * {@code documents_supported}.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @return
     *         Document types supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public String[] getSupportedDocuments()
    {
        return supportedDocuments;
    }


    /**
     * Set document types supported by this service. This property corresponds
     * to the {@code documents_supported} server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_supported} server metadata to
     * {@code documents_supported}.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @param documents
     *         Document types supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public Service setSupportedDocuments(String[] documents)
    {
        this.supportedDocuments = documents;

        return this;
    }


    /**
     * Get verification methods supported by this service.
     * This property corresponds to the {@code id_documents_verification_methods_supported}
     * server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_verification_methods_supported} server metadata to
     * {@code documents_methods_supported}.
     * </p>
     *
     * @return
     *         Verification methods supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see #getSupportedDocumentsMethods()
     *
     * @since 2.63
     *
     * @deprecated
     */
    @Deprecated
    public String[] getSupportedVerificationMethods()
    {
        return supportedVerificationMethods;
    }


    /**
     * Set verification methods supported by this service.
     * This property corresponds to the {@code id_documents_verification_methods_supported}
     * server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_verification_methods_supported} server metadata to
     * {@code documents_methods_supported}.
     * </p>
     *
     * @param methods
     *         Verification methods supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see #setSupportedDocumentsMethods(String[])
     *
     * @since 2.63
     *
     * @deprecated
     */
    @Deprecated
    public Service setSupportedVerificationMethods(String[] methods)
    {
        this.supportedVerificationMethods = methods;

        return this;
    }


    /**
     * Get validation and verification processes supported by this service.
     * This property corresponds to the {@code documents_methods_supported}
     * server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_verification_methods_supported} server metadata to
     * {@code documents_methods_supported}.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @return
     *         Validation and verification processes supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public String[] getSupportedDocumentsMethods()
    {
        return supportedDocumentsMethods;
    }


    /**
     * Set validation and verification processes supported by this service.
     * This property corresponds to the {@code documents_methods_supported}
     * server metadata.
     *
     * <p>
     * The third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> renamed the
     * {@code id_documents_verification_methods_supported} server metadata to
     * {@code documents_methods_supported}.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @param methods
     *         Validation and verification processes supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public Service setSupportedDocumentsMethods(String[] methods)
    {
        this.supportedDocumentsMethods = methods;

        return this;
    }


    /**
     * Get document validation methods supported by this service. This property
     * corresponds to the {@code documents_validation_methods_supported} server
     * metadata which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @return
     *         Document validation methods supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     *
     * @deprecated
     */
    @Deprecated
    public String[] getSupportedDocumentsValidationMethods()
    {
        return supportedDocumentsValidationMethods;
    }


    /**
     * Set document validation methods supported by this service. This property
     * corresponds to the {@code documents_validation_methods_supported} server
     * metadata which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @param methods
     *         Document validation methods supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     *
     * @deprecated
     */
    @Deprecated
    public Service setSupportedDocumentsValidationMethods(String[] methods)
    {
        this.supportedDocumentsValidationMethods = methods;

        return this;
    }


    /**
     * Get document verification methods supported by this service. This property
     * corresponds to the {@code documents_verification_methods_supported} server
     * metadata which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @return
     *         Document verification methods supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     *
     * @deprecated
     */
    @Deprecated
    public String[] getSupportedDocumentsVerificationMethods()
    {
        return supportedDocumentsVerificationMethods;
    }


    /**
     * Set document verification methods supported by this service. This property
     * corresponds to the {@code documents_verification_methods_supported} server
     * metadata which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @param methods
     *         Document verification methods supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     *
     * @deprecated
     */
    @Deprecated
    public Service setSupportedDocumentsVerificationMethods(String[] methods)
    {
        this.supportedDocumentsVerificationMethods = methods;

        return this;
    }


    /**
     * Get document check methods supported by this service. This property
     * corresponds to the {@code documents_check_methods_supported} server
     * metadata which was added by the fourth implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @return
     *         Document check methods supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.48
     */
    public String[] getSupportedDocumentsCheckMethods()
    {
        return supportedDocumentsCheckMethods;
    }


    /**
     * Set document check methods supported by this service. This property
     * corresponds to the {@code documents_check_methods_supported} server
     * metadata which was added by the fourth implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * The fourth implementer's draft of OpenID Connect for Identity Assurance
     * 1.0 replaced the {@code documents_validation_methods_supported} server
     * metadata and the {@code documents_verification_methods_supported} server
     * metadata with the {@code documents_check_methods_supported} server
     * metadata.
     * </p>
     *
     * @param methods
     *         Document check methods supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.48
     */
    public Service setSupportedDocumentsCheckMethods(String[] methods)
    {
        this.supportedDocumentsCheckMethods = methods;

        return this;
    }


    /**
     * Get electronic record types supported by this service. This property
     * corresponds to the {@code electronic_records_supported} server metadata
     * which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * If {@code "electronic_record"} is included in the list of supported
     * evidence (cf. {@link #getSupportedEvidence()}, this property must have
     * at least one entry.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @return
     *         Electronic record types supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public String[] getSupportedElectronicRecords()
    {
        return supportedElectronicRecords;
    }


    /**
     * Set electronic record types supported by this service. This property
     * corresponds to the {@code electronic_records_supported} server metadata
     * which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * If {@code "electronic_record"} is included in the list of supported
     * evidence (cf. {@link #getSupportedEvidence()}, this property must have
     * at least one entry.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @param records
     *         Electronic record types supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public Service setSupportedElectronicRecords(String[] records)
    {
        this.supportedElectronicRecords = records;

        return this;
    }


    /**
     * Get verified claims supported by this service.
     * This property corresponds to the {@code claims_in_verified_claims_supported}
     * server metadata.
     *
     * @return
     *         Verified claims supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 2.63
     */
    public String[] getSupportedVerifiedClaims()
    {
        return supportedVerifiedClaims;
    }


    /**
     * Set verified claims supported by this service.
     * This property corresponds to the {@code claims_in_verified_claims_supported}
     * server metadata.
     *
     * @param claims
     *         Verified claims supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 2.63
     */
    public Service setSupportedVerifiedClaims(String[] claims)
    {
        this.supportedVerifiedClaims = claims;

        return this;
    }


    /**
     * Get attachment types supported by this service. This property
     * corresponds to the {@code attachments_supported} server metadata
     * which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @return
     *         Attachment types supported by this service.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public AttachmentType[] getSupportedAttachments()
    {
        return supportedAttachments;
    }


    /**
     * Set attachment types supported by this service. This property
     * corresponds to the {@code attachments_supported} server metadata
     * which was added by the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @param types
     *         Attachment types supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.13
     */
    public Service setSupportedAttachments(AttachmentType[] types)
    {
        this.supportedAttachments = types;

        return this;
    }


    /**
     * Get supported algorithms used to compute digest values of external
     * attachments. This property corresponds to the
     * {@code digest_algorithms_supported} server metadata which was added by
     * the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * Possible values are listed in the <a href=
     * "https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg"
     * >Hash Algorithm Registry</a> of IANA (Internet Assigned Numbers Authority).
     * If this service supports external attachments (cf. {@link
     * #getSupportedAttachments()}), this property must include at least
     * {@code "sha-256"}.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @return
     *         Supported digest algorithms for external attachments.
     *
     * @since 3.13
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see <a href="https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg"
     *      >Hash Algorithm Registry</a>
     */
    public String[] getSupportedDigestAlgorithms()
    {
        return supportedDigestAlgorithms;
    }


    /**
     * Set supported algorithms used to compute digest values of external
     * attachments. This property corresponds to the
     * {@code digest_algorithms_supported} server metadata which was added by
     * the third implementer's draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     *
     * <p>
     * Possible values are listed in the <a href=
     * "https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg"
     * >Hash Algorithm Registry</a> of IANA (Internet Assigned Numbers Authority).
     * If this service supports external attachments (cf. {@link
     * #getSupportedAttachments()}), this property must include at least
     * {@code "sha-256"}.
     * </p>
     *
     * <p>
     * This property is recognized by Authlete 2.3 and newer versions.
     * </p>
     *
     * @param algorithms
     *         Supported digest algorithms for external attachments.
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
    public Service setSupportedDigestAlgorithms(String[] algorithms)
    {
        this.supportedDigestAlgorithms = algorithms;

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
     * Load attributes from an iterable.
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
     * @since 2.89
     */
    public Service loadAttributes(Iterable<Pair> attributes)
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
     * Get custom client metadata supported by this service.
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
     *     >RFC 9101 The OAuth 2.0 Authorization Framework: JWT Secured Authorization Request (JAR)</a>
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
     * >RFC 7592</a>) are, if supported by Authlete, stored into Authlete
     * database. On the other hand, unrecognized client metadata are discarded.
     * </p>
     *
     * <p>
     * By listing up custom client metadata in advance by using this property
     * ({@code Service.supportedCustomClientMetadata}), Authlete can recognize
     * them and stores their values into the database. The stored custom client
     * metadata values can be referenced by {@link Client#getCustomMetadata()}.
     * </p>
     *
     * <p>
     * This property affects the behavior of {@code /api/client/registration}
     * API of Authlete 2.2 onwards.
     * </p>
     *
     * @return
     *         Custom client metadata supported by this service.
     *
     * @see Client#getCustomMetadata()
     *
     * @since 2.93
     */
    public String[] getSupportedCustomClientMetadata()
    {
        return supportedCustomClientMetadata;
    }


    /**
     * Set custom client metadata supported by this service.
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
     *     >RFC 9101 The OAuth 2.0 Authorization Framework: JWT Secured Authorization Request (JAR)</a>
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
     * >RFC 7592</a>) are, if supported by Authlete, stored into Authlete
     * database. On the other hand, unrecognized client metadata are discarded.
     * </p>
     *
     * <p>
     * By listing up custom client metadata in advance by using this property
     * ({@code Service.supportedCustomClientMetadata}), Authlete can recognize
     * them and stores their values into the database. The stored custom client
     * metadata values can be referenced by {@link Client#getCustomMetadata()}.
     * </p>
     *
     * <p>
     * This property affects the behavior of {@code /api/client/registration}
     * API of Authlete 2.2 onwards.
     * </p>
     *
     * @param metadata
     *         Custom client metadata supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @see Client#getCustomMetadata()
     *
     * @since 2.93
     */
    public Service setSupportedCustomClientMetadata(String[] metadata)
    {
        this.supportedCustomClientMetadata = metadata;

        return this;
    }


    /**
     * Get the flag indicating whether the expiration date of an access token
     * never exceeds that of the corresponding refresh token.
     *
     * <p>
     * When a new access token is issued by a refresh token request (= a token
     * request with {@code grant_type=refresh_token}), the expiration date of
     * the access token may exceed the expiration date of the corresponding
     * refresh token. This behavior itself is not wrong and may happen when
     * {@link #isRefreshTokenKept()} returns {@code true} and/or when
     * {@link #isRefreshTokenDurationKept()} returns {@code true}.
     * </p>
     *
     * <p>
     * When this flag is {@code true}, the expiration date of an access token
     * never exceeds that of the corresponding refresh token regardless of
     * the calculated duration based on other settings such as
     * {@link Service#getAccessTokenDuration()},
     * {@link ClientExtension#getAccessTokenDuration()} and the
     * {@code access_token.duration} attribute of scopes.
     * </p>
     *
     * <p>
     * It is technically possible to set a value which is bigger than the
     * duration of refresh tokens as the duration of access tokens although
     * it is strange. In the case, the duration of an access token becomes
     * longer than the duration of the refresh token which is issued together
     * with the access token. Even if the duration values are configured so,
     * if this flag is {@code true}, the expiration date of the access token
     * does not exceed that of the refresh token. That is, the duration of
     * the access token will be shortened, and as a result, the access token
     * and the refresh token will have the same expiration date.
     * </p>
     *
     * @return
     *         {@code true} if the service assures that the expiration date
     *         of an access token never exceeds that of the corresponding
     *         refresh token.
     *
     * @since 2.95
     */
    public boolean isTokenExpirationLinked()
    {
        return tokenExpirationLinked;
    }


    /**
     * Set the flag indicating whether the expiration date of an access token
     * never exceeds that of the corresponding refresh token.
     *
     * <p>
     * When a new access token is issued by a refresh token request (= a token
     * request with {@code grant_type=refresh_token}), the expiration date of
     * the access token may exceed the expiration date of the corresponding
     * refresh token. This behavior itself is not wrong and may happen when
     * {@link #isRefreshTokenKept()} returns {@code true} and/or when
     * {@link #isRefreshTokenDurationKept()} returns {@code true}.
     * </p>
     *
     * <p>
     * When this flag is {@code true}, the expiration date of an access token
     * never exceeds that of the corresponding refresh token regardless of
     * the calculated duration based on other settings such as
     * {@link Service#getAccessTokenDuration()},
     * {@link ClientExtension#getAccessTokenDuration()} and the
     * {@code access_token.duration} attribute of scopes.
     * </p>
     *
     * <p>
     * It is technically possible to set a value which is bigger than the
     * duration of refresh tokens as the duration of access tokens although
     * it is strange. In the case, the duration of an access token becomes
     * longer than the duration of the refresh token which is issued together
     * with the access token. Even if the duration values are configured so,
     * if this flag is {@code true}, the expiration date of the access token
     * does not exceed that of the refresh token. That is, the duration of
     * the access token will be shortened, and as a result, the access token
     * and the refresh token will have the same expiration date.
     * </p>
     *
     * @param linked
     *         {@code true} to assure that the expiration date of an access
     *         token never exceeds that of the corresponding refresh token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.95
     */
    public Service setTokenExpirationLinked(boolean linked)
    {
        this.tokenExpirationLinked = linked;

        return this;
    }


    /**
     * Get the flag indicating whether encryption of request object is required
     * when the request object is passed through the front channel.
     *
     * <p>
     * This flag does not affect the processing of request objects at the
     * Pushed Authorization Request Endpoint, which is defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc9126.html"> RFC 9126 OAuth 2.0
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
     * required if the {@code Client.frontChannelRequestObjectEncryptionRequired}
     * flag is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if encryption of request object is required when
     *         the request object is passed through the front channel.
     *
     * @see #isRequestObjectRequired()
     * @see Client#isFrontChannelRequestObjectEncryptionRequired()
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
     * required if the {@code Client.frontChannelRequestObjectEncryptionRequired}
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
     * @see Client#isFrontChannelRequestObjectEncryptionRequired()
     *
     * @since 2.96
     */
    public Service setFrontChannelRequestObjectEncryptionRequired(boolean required)
    {
        this.frontChannelRequestObjectEncryptionRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether the JWE {@code alg} of encrypted request
     * object must match the {@code request_object_encryption_alg} client metadata
     * of the client that has sent the request object.
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
     * The {@link Client}'s property that represents the client metadata is
     * {@code Client.requestEncryptionAlg}. See the description of
     * {@link Client#getRequestEncryptionAlg()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Client.requestObjectEncryptionAlgMatchRequired} flag is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if the JWE {@code alg} of encrypted request object
     *         must match the {@code request_object_encryption_alg} client metadata.
     *
     * @see Client#getRequestEncryptionAlg()
     * @see Client#isRequestObjectEncryptionAlgMatchRequired()
     *
     * @since 2.96
     */
    public boolean isRequestObjectEncryptionAlgMatchRequired()
    {
        return requestObjectEncryptionAlgMatchRequired;
    }


    /**
     * Set the flag indicating whether the JWE {@code alg} of encrypted request
     * object must match the {@code request_object_encryption_alg} client metadata
     * of the client that has sent the request object.
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
     * The {@link Client}'s property that represents the client metadata is
     * {@code Client.requestEncryptionAlg}. See the description of
     * {@link Client#getRequestEncryptionAlg()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Client.requestObjectEncryptionAlgMatchRequired} flag is {@code true}.
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
     * @see Client#getRequestEncryptionAlg()
     * @see Client#isRequestObjectEncryptionAlgMatchRequired()
     *
     * @since 2.96
     */
    public Service setRequestObjectEncryptionAlgMatchRequired(boolean required)
    {
        this.requestObjectEncryptionAlgMatchRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether the JWE {@code enc} of encrypted request
     * object must match the {@code request_object_encryption_enc} client metadata
     * of the client that has sent the request object.
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
     * The {@link Client}'s property that represents the client metadata is
     * {@code Client.requestEncryptionEnc}. See the description of
     * {@link Client#getRequestEncryptionEnc()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Client.requestObjectEncryptionEncMatchRequired} flag is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if the JWE {@code enc} of encrypted request object
     *         must match the {@code request_object_encryption_enc} client metadata.
     *
     * @see Client#getRequestEncryptionEnc()
     * @see Client#isRequestObjectEncryptionEncMatchRequired()
     *
     * @since 2.96
     */
    public boolean isRequestObjectEncryptionEncMatchRequired()
    {
        return requestObjectEncryptionEncMatchRequired;
    }


    /**
     * Set the flag indicating whether the JWE {@code enc} of encrypted request
     * object must match the {@code request_object_encryption_enc} client metadata
     * of the client that has sent the request object.
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
     * The {@link Client}'s property that represents the client metadata is
     * {@code Client.requestEncryptionEnc}. See the description of
     * {@link Client#getRequestEncryptionEnc()} for details.
     * </p>
     *
     * <p>
     * Even if this flag is {@code false}, the match is required if the
     * {@code Client.requestObjectEncryptionEncMatchRequired} flag is {@code true}.
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
     * @see Client#getRequestEncryptionEnc()
     * @see Client#isRequestObjectEncryptionEncMatchRequired()
     *
     * @since 2.96
     */
    public Service setRequestObjectEncryptionEncMatchRequired(boolean required)
    {
        this.requestObjectEncryptionEncMatchRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether HSM (Hardware Security Module) support
     * is enabled for this service.
     *
     * <p>
     * When this flag is false, keys managed in HSMs are not used even if they
     * exist. In addition, {@code /api/hsk/*} APIs reject all requests.
     * </p>
     *
     * <p>
     * Even if this flag is true, HSM-related features do not work if the
     * configuration of the Authlete server you are using does not support HSM.
     * </p>
     *
     * @return
     *         {@code true} if HSM support is enabled for this service.
     *
     * @since 2.97
     */
    public boolean isHsmEnabled()
    {
        return hsmEnabled;
    }


    /**
     * Set the flag indicating whether HSM (Hardware Security Module) support
     * is enabled for this service.
     *
     * <p>
     * When this flag is false, keys managed in HSMs are not used even if they
     * exist. In addition, {@code /api/hsk/*} APIs reject all requests.
     * </p>
     *
     * <p>
     * Even if this flag is true, HSM-related features do not work if the
     * configuration of the Authlete server you are using does not support HSM.
     * </p>
     *
     * @param enabled
     *         {@code true} to enable HSM support for this service.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.97
     */
    public Service setHsmEnabled(boolean enabled)
    {
        this.hsmEnabled = enabled;

        return this;
    }


    /**
     * Get information about keys managed on HSMs (Hardware Security Modules).
     *
     * <p>
     * This {@code hsks} property is output only, meaning that {@code hsks} in
     * requests to {@code /api/service/create} API and {@code /api/service/update}
     * API do not have any effect. The contents of this property is controlled
     * only by {@code /api/hsk/*} APIs.
     * </p>
     *
     * @return
     *         Information about keys managed on HSMs.
     *
     * @since 2.97
     */
    public Hsk[] getHsks()
    {
        return hsks;
    }


    /**
     * Set information about keys managed on HSMs (Hardware Security Modules).
     *
     * <p>
     * This {@code hsks} property is output only, meaning that {@code hsks} in
     * requests to {@code /api/service/create} API and {@code /api/service/update}
     * API do not have any effect. The contents of this property is controlled
     * only by {@code /api/hsk/*} APIs.
     * </p>
     *
     * @param hsks
     *         Information about keys managed on HSMs.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.97
     */
    public Service setHsks(Hsk[] hsks)
    {
        this.hsks = hsks;

        return this;
    }


    /**
     * Get the URL of the grant management endpoint.
     *
     * @return
     *         The URL of the grant management endpoint.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public URI getGrantManagementEndpoint()
    {
        return grantManagementEndpoint;
    }


    /**
     * Set the URL of the grant management endpoint.
     *
     * @param endpoint
     *         The URL of the grant management endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public Service setGrantManagementEndpoint(URI endpoint)
    {
        this.grantManagementEndpoint = endpoint;

        return this;
    }


    /**
     * Get the flag indicating whether every authorization request (and any
     * request serving as an authorization request such as CIBA backchannel
     * authentication request and device authorization request) must include
     * the {@code grant_management_action} request parameter.
     *
     * <p>
     * This property corresponds to the {@code grant_management_action_required}
     * server metadata defined in "<a href=
     * "https://openid.net/specs/fapi-grant-management.html">Grant Management
     * for OAuth 2.0</a>".
     * </p>
     *
     * <p>
     * Note that setting {@code true} to this property will result in blocking
     * all public clients because the specification requires that grant
     * management be usable only by confidential clients for security reasons.
     * </p>
     *
     * @return
     *         {@code true} if every authorization request must include the
     *         {@code grant_management_action} request parameter.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public boolean isGrantManagementActionRequired()
    {
        return grantManagementActionRequired;
    }


    /**
     * Set the flag indicating whether every authorization request (and any
     * request serving as an authorization request such as CIBA backchannel
     * authentication request and device authorization request) must include
     * the {@code grant_management_action} request parameter.
     *
     * <p>
     * This property corresponds to the {@code grant_management_action_required}
     * server metadata defined in "<a href=
     * "https://openid.net/specs/fapi-grant-management.html">Grant Management
     * for OAuth 2.0</a>".
     * </p>
     *
     * <p>
     * Note that setting {@code true} to this property will result in blocking
     * all public clients because the specification requires that grant
     * management be usable only by confidential clients for security reasons.
     * </p>
     *
     * @param required
     *         {@code true} to require every authorization request include the
     *         {@code grant_management_action} request parameter.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public Service setGrantManagementActionRequired(boolean required)
    {
        this.grantManagementActionRequired = required;

        return this;
    }


    /**
     * Get the flag indicating whether Authlete's {@code /api/client/registration}
     * API uses {@link ClientRegistrationResponse.Action#UNAUTHORIZED UNAUTHORIZED}
     * as a value of the {@code action} response parameter when appropriate.
     *
     * <p>
     * See the description of {@link #setUnauthorizedOnClientConfigSupported(boolean)}
     * for details.
     * </p>
     *
     * @return
     *         {@code true} if Authlete's {@code /api/client/registration} uses
     *         {@code UNAUTHORIZED} as a value of the {@code action} response
     *         parameter when appropriate. {@code false} if {@code UNAUTHORIZED}
     *         is not used in any case.
     *
     * @since 3.4
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7592.html"
     *      >RFC 7592 OAuth 2.0 Dynamic Client Registration Management Protocol</a>
     */
    public boolean isUnauthorizedOnClientConfigSupported()
    {
        return unauthorizedOnClientConfigSupported;
    }


    /**
     * Set the flag indicating whether Authlete's {@code /api/client/registration}
     * API uses {@link ClientRegistrationResponse.Action#UNAUTHORIZED UNAUTHORIZED}
     * as a value of the {@code action} response parameter when appropriate.
     *
     * <p>
     * The {@code UNAUTHORIZED} enum value did not exist in the initial
     * implementation of the {@link ClientRegistrationResponse.Action} enum.
     * This means that implementations of client configuration endpoint were
     * not able to conform to <a href=
     * "https://www.rfc-editor.org/rfc/rfc7592.html">RFC 7592</a> strictly.
     * </p>
     *
     * <p>
     * For backward compatibility (to avoid breaking running systems),
     * Authlete's {@code /api/client/registration} API does not return the
     * {@code UNAUTHORIZED} enum value if this flag is not turned on.
     * </p>
     *
     * <p>
     * The steps an existing implementation of client configuration endpoint
     * has to do in order to conform to the requirement related to
     * "{@code 401 Unauthorized}" are as follows.
     * </p>
     *
     * <ol>
     * <li>
     * Update the Authlete library (e.g. <a href=
     * "https://github.com/authlete/authlete-java-common"
     * >authlete-java-common</a>) your system is using.
     * <li>
     * Update your implementation of client configuration endpoint so that
     * it can handle the {@code UNAUTHORIZED} action.
     * <li>
     * Turn on this {@code unauthorizedOnClientConfigSupported} flag.
     * </ol>
     *
     * @param supported
     *         {@code true} to let Authlete's {@code /api/client/registration}
     *         API use the {@code UNAUTHORIZED} enum value when appropriate.
     *         {@code false} to prevent the {@code UNAUTHORIZED} enum value
     *         from being used.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.4
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7592.html"
     *      >RFC 7592 OAuth 2.0 Dynamic Client Registration Management Protocol</a>
     */
    public Service setUnauthorizedOnClientConfigSupported(boolean supported)
    {
        this.unauthorizedOnClientConfigSupported = supported;

        return this;
    }


    /**
     * Get the flag indicating whether the {@code scope} request parameter
     * in dynamic client registration and update requests (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591</a> and
     * <a href="https://www.rfc-editor.org/rfc/rfc7592.html">RFC 7592</a>)
     * is used as scopes that the client can request.
     *
     * <p>
     * Limiting the range of scopes that a client can request is achieved by
     * listing scopes in the {@code client.extension.requestableScopes}
     * property (cf. {@link ClientExtension#getRequestableScopes()}) and
     * setting {@code true} to the
     * {@code client.extension.requestableScopesEnabled} property (cf.
     * {@link ClientExtension#isRequestableScopesEnabled()}). This feature
     * is called "requestable scopes".
     * </p>
     *
     * <p>
     * This property affects behaviors of {@code /api/client/registration}
     * and other family APIs.
     * </p>
     *
     * @return
     *         {@code true} if the {@code scope} request parameter in dynamic
     *         client registration and update requests is used to limit the
     *         range of scopes that the client can request. {@code false} if
     *         Authlete does nothing special for the {@code scope} request
     *         parameter.
     *
     * @since 3.5
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html"
     *      >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7592.html"
     *      >RFC 7592 OAuth 2.0 Dynamic Client Registration Management Protocol</a>
     */
    public boolean isDcrScopeUsedAsRequestable()
    {
        return dcrScopeUsedAsRequestable;
    }


    /**
     * Set the flag indicating whether the {@code scope} request parameter
     * in dynamic client registration and update requests (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591</a> and
     * <a href="https://www.rfc-editor.org/rfc/rfc7592.html">RFC 7592</a>)
     * is used as scopes that the client can request.
     *
     * <p>
     * Limiting the range of scopes that a client can request is achieved by
     * listing scopes in the {@code client.extension.requestableScopes}
     * property (cf. {@link ClientExtension#getRequestableScopes()}) and
     * setting {@code true} to the
     * {@code client.extension.requestableScopesEnabled} property (cf.
     * {@link ClientExtension#isRequestableScopesEnabled()}). This feature
     * is called "requestable scopes".
     * </p>
     *
     * <p>
     * This property affects behaviors of {@code /api/client/registration}
     * and other family APIs.
     * </p>
     *
     * @param used
     *         {@code true} to make Authlete treat the {@code scope} request
     *         parameter in dynamic client registration and update requests
     *         as requestable scopes. {@code false} to leave it to Authlete
     *         (Authlete will do nothing special).
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.5
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html"
     *      >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7592.html"
     *      >RFC 7592 OAuth 2.0 Dynamic Client Registration Management Protocol</a>
     */
    public Service setDcrScopeUsedAsRequestable(boolean used)
    {
        this.dcrScopeUsedAsRequestable = used;

        return this;
    }


    /**
     * Get the transformed claims predefined by this service in JSON format.
     * This property corresponds to the {@code transformed_claims_predefined}
     * server metadata.
     *
     * <p>
     * See the description of {@link #setPredefinedTransformedClaims(String)}
     * for details.
     * </p>
     *
     * <p>
     * This {@code predefinedTransformedClaims} property is available from
     * Authlete 2.3 onwards.
     * </p>
     *
     * @return
     *         Predefined transformed claims in JSON format.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see #setPredefinedTransformedClaims(String)
     *
     * @since 3.8
     */
    public String getPredefinedTransformedClaims()
    {
        return predefinedTransformedClaims;
    }


    /**
     * Set the transformed claims predefined by this service in JSON format.
     * This property corresponds to the {@code transformed_claims_predefined}
     * server metadata.
     *
     * <p>
     * <i>"Transformed Claims"</i> is a specification that enables to define
     * a <i>"transformed claim"</i> which transforms the value of an existing
     * claim by applying <i>"transformation functions"</i>.
     * </p>
     *
     * <p>
     * The following example defines a transformed claim named {@code 18_or_over}
     * which uses the {@code birthdate} claim as input and applies two
     * transformation functions, {@code years_ago} and {@code gte}. As a result
     * of the transformation, the transformed claim will have a boolean value.
     * </p>
     *
     * <pre>
     * {
     *   "18_or_over": {
     *     "claim": "birthdate",
     *     "fn": [
     *       "years_ago",
     *       [ "gte", 18 ]
     *     ]
     *   }
     * }
     * </pre>
     *
     * <p>
     * A client application can request a predefined transformed claim by
     * prepending two colons ({@code ::}) to the name of a transformed claim.
     * The following is an example of the {@code claims} request parameter
     * (OpenID Connect Core 1.0, <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     * >5.5. Requesting Claims using the "claims" Request Parameter</a>) that
     * requests a predefined transformed claim named {@code 18_or_over} to be
     * embedded in an ID token.
     * </p>
     *
     * <pre>
     * {
     *   "id_token": {
     *     "::18_or_over": null
     *   }
     * }
     * </pre>
     *
     * <p>
     * If the age of the user is 18 or over, the ID token will contain the
     * transformed claim like below.
     * </p>
     *
     * <pre>
     * "::18_or_over": true
     * </pre>
     *
     * <p>
     * This {@code predefinedTransformedClaims} property is available from
     * Authlete 2.3 onwards.
     * </p>
     *
     * @param claims
     *         Predefined transformed claims in JSON format.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @since 3.8
     */
    public Service setPredefinedTransformedClaims(String claims)
    {
        this.predefinedTransformedClaims = claims;

        return this;
    }


    /**
     * Get the flag indicating whether the port number component of redirection
     * URIs can be variable when the host component indicates loopback.
     *
     * <p>
     * When this flag is true, if the host component of a redirection URI
     * specified in an authorization request indicates loopback (to be precise,
     * when the host component is {@code localhost}, {@code 127.0.0.1} or
     * {@code ::1}), the port number component is ignored when the specified
     * redirection URI is compared to pre-registered ones. This behavior is
     * described in <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html#section-7.3">7.3. Loopback
     * Interface Redirection</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html">RFC 8252 OAuth 2.0 for
     * Native Apps</a>.
     * </p>
     *
     * <p>
     * <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-3.1.2.3"
     * >3.1.2.3. Dynamic Configuration</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749</a> states
     * <i>"If the client registration included the full redirection URI, the
     * authorization server MUST compare the two URIs using <b>simple string
     * comparison</b> as defined in [RFC3986] Section 6.2.1."</i> Also, the
     * description of {@code redirect_uri} in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     * >3.1.2.1. Authentication Request</a> of <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect
     * Core 1.0</a> states <i>"This URI MUST exactly match one of the
     * Redirection URI values for the Client pre-registered at the OpenID
     * Provider, with the matching performed as described in Section 6.2.1 of
     * [RFC3986] (<b>Simple String Comparison</b>)."</i> These "Simple String
     * Comparison" requirements are preceded by this flag. That is, even when
     * the conditions described in RFC 6749 and OpenID Connect Core 1.0 are
     * satisfied, the port number component of loopback redirection URIs can
     * be variable when this flag is true.
     * </p>
     *
     * <p>
     * <a href="https://www.rfc-editor.org/rfc/rfc8252.html#section-8.3">8.3.
     * Loopback Redirect Considerations</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html">RFC 8252</a> states as
     * follows.
     * </p>
     *
     * <blockquote><p><i>
     * While redirect URIs using <code>localhost</code> (i.e.,
     * <code>"http://localhost:{port}/{path}"</code>) function similarly to
     * loopback IP redirects described in Section 7.3, the use of
     * <code>localhost</code> is NOT RECOMMENDED. Specifying a redirect URI
     * with the loopback IP literal rather than <code>localhost</code> avoids
     * inadvertently listening on network interfaces other than the loopback
     * interface. It is also less susceptible to client-side firewalls and
     * misconfigured host name resolution on the user's device.
     * </i></p></blockquote>
     *
     * <p>
     * However, Authlete allows the port number component to be variable in
     * the case of {@code localhost}, too. It is left to client applications
     * whether they use {@code localhost} or a literal loopback IP address
     * ({@code 127.0.0.1} for IPv4 or {@code ::1} for IPv6).
     * </p>
     *
     * <p>
     * Section 7.3 and Section 8.3 of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html">RFC 8252</a> state that
     * loopback redirection URIs use the {@code "http"} scheme, but Authlete
     * allows the port number component to be variable in other cases (e.g.
     * in the case of the {@code "https"} scheme), too.
     * </p>
     *
     * @return
     *         True if the port number component of loopback redirection URIs
     *         can be variable.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8252.html#section-7.3"
     *      >RFC 8252 OAuth 2.0 for Native Apps, 7.3. Loopback Interface Redirection</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8252.html#section-8.3"
     *      >RFC 8252 OAuth 2.0 for Native Apps, 8.3. Loopback Redirect Considerations</a>
     *
     * @since 3.12
     */
    public boolean isLoopbackRedirectionUriVariable()
    {
        return loopbackRedirectionUriVariable;
    }


    /**
     * Set the flag indicating whether the port number component of redirection
     * URIs can be variable when the host component indicates loopback.
     *
     * <p>
     * When this flag is true, if the host component of a redirection URI
     * specified in an authorization request indicates loopback (to be precise,
     * when the host component is {@code localhost}, {@code 127.0.0.1} or
     * {@code ::1}), the port number component is ignored when the specified
     * redirection URI is compared to pre-registered ones. This behavior is
     * described in <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html#section-7.3">7.3. Loopback
     * Interface Redirection</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html">RFC 8252 OAuth 2.0 for
     * Native Apps</a>.
     * </p>
     *
     * <p>
     * <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-3.1.2.3"
     * >3.1.2.3. Dynamic Configuration</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749</a> states
     * <i>"If the client registration included the full redirection URI, the
     * authorization server MUST compare the two URIs using <b>simple string
     * comparison</b> as defined in [RFC3986] Section 6.2.1."</i> Also, the
     * description of {@code redirect_uri} in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     * >3.1.2.1. Authentication Request</a> of <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect
     * Core 1.0</a> states <i>"This URI MUST exactly match one of the
     * Redirection URI values for the Client pre-registered at the OpenID
     * Provider, with the matching performed as described in Section 6.2.1 of
     * [RFC3986] (<b>Simple String Comparison</b>)."</i> These "Simple String
     * Comparison" requirements are preceded by this flag. That is, even when
     * the conditions described in RFC 6749 and OpenID Connect Core 1.0 are
     * satisfied, the port number component of loopback redirection URIs can
     * be variable when this flag is true.
     * </p>
     *
     * <p>
     * <a href="https://www.rfc-editor.org/rfc/rfc8252.html#section-8.3">8.3.
     * Loopback Redirect Considerations</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html">RFC 8252</a> states as
     * follows.
     * </p>
     *
     * <blockquote><p><i>
     * While redirect URIs using <code>localhost</code> (i.e.,
     * <code>"http://localhost:{port}/{path}"</code>) function similarly to
     * loopback IP redirects described in Section 7.3, the use of
     * <code>localhost</code> is NOT RECOMMENDED. Specifying a redirect URI
     * with the loopback IP literal rather than <code>localhost</code> avoids
     * inadvertently listening on network interfaces other than the loopback
     * interface. It is also less susceptible to client-side firewalls and
     * misconfigured host name resolution on the user's device.
     * </i></p></blockquote>
     *
     * <p>
     * However, Authlete allows the port number component to be variable in
     * the case of {@code localhost}, too. It is left to client applications
     * whether they use {@code localhost} or a literal loopback IP address
     * ({@code 127.0.0.1} for IPv4 or {@code ::1} for IPv6).
     * </p>
     *
     * <p>
     * Section 7.3 and Section 8.3 of <a href=
     * "https://www.rfc-editor.org/rfc/rfc8252.html">RFC 8252</a> state that
     * loopback redirection URIs use the {@code "http"} scheme, but Authlete
     * allows the port number component to be variable in other cases (e.g.
     * in the case of the {@code "https"} scheme), too.
     * </p>
     *
     * @param variable
     *         True to allow the port number component of loopback redirection
     *         URIs to be variable.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8252.html#section-7.3"
     *      >RFC 8252 OAuth 2.0 for Native Apps, 7.3. Loopback Interface Redirection</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8252.html#section-8.3"
     *      >RFC 8252 OAuth 2.0 for Native Apps, 8.3. Loopback Redirect Considerations</a>
     *
     * @since 3.12
     */
    public Service setLoopbackRedirectionUriVariable(boolean variable)
    {
        this.loopbackRedirectionUriVariable = variable;

        return this;
    }


    /**
     * Get the flag indicating whether Authlete checks whether the {@code aud}
     * claim of request objects matches the issuer identifier of this service.
     *
     * <p>
     * <a href="https://openid.net/specs/openid-connect-core-1_0.html#JWTRequests"
     * >Section 6.1. Passing a Request Object by Value</a> of <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect
     * Core 1.0</a> has the following statement.
     * </p>
     *
     * <blockquote>
     * <p>
     * The {@code aud} value SHOULD be or include the OP's Issuer Identifier URL.
     * </p>
     * </blockquote>
     *
     * <p>
     * Likewise, <a href="https://www.rfc-editor.org/rfc/rfc9101.html#section-4"
     * >Section 4. Request Object</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc9101.html">RFC 9101</a> (The OAuth 2.0
     * Authorization Framework: JWT-Secured Authorization Request (JAR)) has the
     * following statement.
     * </p>
     *
     * <blockquote>
     * <p>
     * The value of {@code aud} should be the value of the authorization server
     * (AS) {@code issuer}, as defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc8414.html">RFC 8414</a>.
     * </p>
     * </blockquote>
     *
     * <p>
     * As excerpted above, validation on the {@code aud} claim of request objects
     * is optional. However, if this flag is turned on, Authlete checks whether
     * the {@code aud} claim of request objects matches the issuer identifier of
     * this service and raises an error if they are different.
     * </p>
     *
     * @return
     *         {@code true} if Authlete checks whether the {@code aud} claim of
     *         request objects matches the issuer identifier of this service.
     *
     * @since 3.14
     */
    public boolean isRequestObjectAudienceChecked()
    {
        return requestObjectAudienceChecked;
    }


    /**
     * Set the flag indicating whether Authlete checks whether the {@code aud}
     * claim of request objects matches the issuer identifier of this service.
     *
     * <p>
     * <a href="https://openid.net/specs/openid-connect-core-1_0.html#JWTRequests"
     * >Section 6.1. Passing a Request Object by Value</a> of <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect
     * Core 1.0</a> has the following statement.
     * </p>
     *
     * <blockquote>
     * <p>
     * The {@code aud} value SHOULD be or include the OP's Issuer Identifier URL.
     * </p>
     * </blockquote>
     *
     * <p>
     * Likewise, <a href="https://www.rfc-editor.org/rfc/rfc9101.html#section-4"
     * >Section 4. Request Object</a> of <a href=
     * "https://www.rfc-editor.org/rfc/rfc9101.html">RFC 9101</a> (The OAuth 2.0
     * Authorization Framework: JWT-Secured Authorization Request (JAR)) has the
     * following statement.
     * </p>
     *
     * <blockquote>
     * <p>
     * The value of {@code aud} should be the value of the authorization server
     * (AS) {@code issuer}, as defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc8414.html">RFC 8414</a>.
     * </p>
     * </blockquote>
     *
     * <p>
     * As excerpted above, validation on the {@code aud} claim of request objects
     * is optional. However, if this flag is turned on, Authlete checks whether
     * the {@code aud} claim of request objects matches the issuer identifier of
     * this service and raises an error if they are different.
     * </p>
     *
     * @param checked
     *         {@code true} to make Authlete check whether the {@code aud} claim
     *         of request objects matches the issuer identifier of this service.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.14
     */
    public Service setRequestObjectAudienceChecked(boolean checked)
    {
        this.requestObjectAudienceChecked = checked;

        return this;
    }


    /**
     * Get the flag indicating whether Authlete generates access tokens for
     * external attachments and embeds them in ID tokens and userinfo
     * responses.
     *
     * <p>
     * The third draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> introduced a new feature
     * called "Attachments". The feature enables OpenID Providers to attach
     * additional contents as parts of "evidence".
     * </p>
     *
     * <p>
     * There are two types of attachments. One is "embedded attachment" where
     * contents of attachments are base64-encoded and embedded in ID tokens
     * and userinfo responses directly. The other is "external attachment"
     * where contents of attachments are hosted on resource servers and URLs
     * of them are embedded in ID tokens and userinfo responses.
     * </p>
     *
     * <p>
     * When an OpenID Provider embeds URLs of external attachments in ID tokens
     * and userinfo responses, it may optionally embed access tokens with which
     * the client application accesses the external attachments.
     * </p>
     *
     * <p>
     * The following is an example of {@code "verified_claims"} that shows how
     * an access token is embedded. (A simplified version of an example in the
     * specification.)
     * </p>
     *
     * <pre>
     * "verified_claims": {
     *   "verification": {
     *     "trust_framework":"eidas",
     *     "evidence": [
     *       {
     *         "type": "document",
     *         "attachments": [
     *           {
     *             "desc": "Front of id document",
     *             "digest": {
     *               "alg": "sha-256",
     *               "value": "qC1zE5AfxylOFLrCnOIURXJUvnZwSFe5uUj8t6hdQVM="
     *             },
     *             "url": "https://example.com/attachments/pGL9yz4hZQ",
     *             "access_token": "ksj3n283dke",
     *             "exp": 1676552089
     *           }
     *         ]
     *       }
     *     ]
     *   },
     *   "claims": {
     *     "given_name": "Max",
     *     "family_name": "Mustermann",
     *     "birthdate": "1956-01-28"
     *   }
     * }
     * </pre>
     *
     * <p>
     * Because it is developers (not Authlete) who prepare the content of
     * {@code "verified_claims"} (cf. the {@code "claims"} request parameter of
     * Authlete's {@code /api/auth/authorization/issue} API), developers can
     * embed arbitrary access tokens for external attachments. However, it is
     * a burdensome task to prepare access tokens appropriately. The task can
     * be delegated to Authlete by setting true to this
     * {@code accessTokenForExternalAttachmentEmbedded} property.
     * </p>
     *
     * <p>
     * When this property is set to true, Authlete behaves as described below
     * for each element in the {@code "attachments"} array.
     * </p>
     *
     * <ol>
     * <li>Ignores the element if it does not contain the {@code "url"} property
     *     because it means that the element is not an external attachment.
     * <li>Does nothing for the element if it already contains the
     *     {@code "access_token"} property.
     * <li>Computes the duration of the access token which is about to be
     *     generated. If the element already contains the {@code "exp"} property,
     *     its value is used to compute the duration. Otherwise, (1) the duration
     *     of the ID token is used as the duration of the access token for the
     *     external attachment in the case where the URL of the external attachment
     *     is going to be embedded in an ID token, or (2) the remaining duration
     *     of the access token which was presented at the userinfo endpoint is
     *     used as the duration of the access token for the external attachment
     *     in the case where the URL of the external attachment is going to be
     *     embedded in a userinfo response.
     * <li>Generates an access token which has the duration computed in the
     *     previous step. Also, the access token has the URL as an associated
     *     resource as if the {@code resource} request parameter defined in
     *     <a href="https://www.rfc-editor.org/rfc/rfc8707.html">RFC 8707</a>
     *     (Resource Indicators for OAuth 2.0) were used.
     * <li>Puts the {@code "access_token"} and {@code "exp"} properties
     *     in the element whose values are the generated access token and the
     *     computed duration.
     * </ol>
     *
     * <p>
     * Note that the {@code expires_in} property was replaced with {@code exp}
     * after the 4th draft of the OpenID Connect for Identity Assurance 1.0
     * was published.
     * </p>
     *
     * @return
     *         {@code true} if Authlete generates access tokens for external
     *         attachments and embeds them in ID tokens and userinfo responses.
     *
     * @since 3.16
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     */
    public boolean isAccessTokenForExternalAttachmentEmbedded()
    {
        return accessTokenForExternalAttachmentEmbedded;
    }


    /**
     * Set the flag indicating whether Authlete generates access tokens for
     * external attachments and embeds them in ID tokens and userinfo
     * responses.
     *
     * <p>
     * The third draft of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> introduced a new feature
     * called "Attachments". The feature enables OpenID Providers to attach
     * additional contents as parts of "evidence".
     * </p>
     *
     * <p>
     * There are two types of attachments. One is "embedded attachment" where
     * contents of attachments are base64-encoded and embedded in ID tokens
     * and userinfo responses directly. The other is "external attachment"
     * where contents of attachments are hosted on resource servers and URLs
     * of them are embedded in ID tokens and userinfo responses.
     * </p>
     *
     * <p>
     * When an OpenID Provider embeds URLs of external attachments in ID tokens
     * and userinfo responses, it may optionally embed access tokens with which
     * the client application accesses the external attachments.
     * </p>
     *
     * <p>
     * The following is an example of {@code "verified_claims"} that shows how
     * an access token is embedded. (A simplified version of an example in the
     * specification.)
     * </p>
     *
     * <pre>
     * "verified_claims": {
     *   "verification": {
     *     "trust_framework":"eidas",
     *     "evidence": [
     *       {
     *         "type": "document",
     *         "attachments": [
     *           {
     *             "desc": "Front of id document",
     *             "digest": {
     *               "alg": "sha-256",
     *               "value": "qC1zE5AfxylOFLrCnOIURXJUvnZwSFe5uUj8t6hdQVM="
     *             },
     *             "url": "https://example.com/attachments/pGL9yz4hZQ",
     *             "access_token": "ksj3n283dke",
     *             "exp": 1676552089
     *           }
     *         ]
     *       }
     *     ]
     *   },
     *   "claims": {
     *     "given_name": "Max",
     *     "family_name": "Mustermann",
     *     "birthdate": "1956-01-28"
     *   }
     * }
     * </pre>
     *
     * <p>
     * Because it is developers (not Authlete) who prepare the content of
     * {@code "verified_claims"} (cf. the {@code "claims"} request parameter of
     * Authlete's {@code /api/auth/authorization/issue} API), developers can
     * embed arbitrary access tokens for external attachments. However, it is
     * a burdensome task to prepare access tokens appropriately. The task can
     * be delegated to Authlete by setting true to this
     * {@code accessTokenForExternalAttachmentEmbedded} property.
     * </p>
     *
     * <p>
     * When this property is set to true, Authlete behaves as described below
     * for each element in the {@code "attachments"} array.
     * </p>
     *
     * <ol>
     * <li>Ignores the element if it does not contain the {@code "url"} property
     *     because it means that the element is not an external attachment.
     * <li>Does nothing for the element if it already contains the
     *     {@code "access_token"} property.
     * <li>Computes the duration of the access token which is about to be
     *     generated. If the element already contains the {@code "exp"} property,
     *     its value is used to compute the duration. Otherwise, (1) the duration
     *     of the ID token is used as the duration of the access token for the
     *     external attachment in the case where the URL of the external attachment
     *     is going to be embedded in an ID token, or (2) the remaining duration
     *     of the access token which was presented at the userinfo endpoint is
     *     used as the duration of the access token for the external attachment
     *     in the case where the URL of the external attachment is going to be
     *     embedded in a userinfo response.
     * <li>Generates an access token which has the duration computed in the
     *     previous step. Also, the access token has the URL as an associated
     *     resource as if the {@code resource} request parameter defined in
     *     <a href="https://www.rfc-editor.org/rfc/rfc8707.html">RFC 8707</a>
     *     (Resource Indicators for OAuth 2.0) were used.
     * <li>Puts the {@code "access_token"} and {@code "exp"} properties
     *     in the element whose values are the generated access token and the
     *     computed duration.
     * </ol>
     *
     * <p>
     * Note that the {@code expires_in} property was replaced with {@code exp}
     * after the 4th draft of the OpenID Connect for Identity Assurance 1.0
     * was published.
     * </p>
     *
     * @param embedded
     *         {@code true} to make Authlete generate access tokens for external
     *         attachments and embed them in ID tokens and userinfo responses.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.16
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     */
    public Service setAccessTokenForExternalAttachmentEmbedded(boolean embedded)
    {
        this.accessTokenForExternalAttachmentEmbedded = embedded;

        return this;
    }


    /**
     * Get the flag indicating whether refresh token requests with the same
     * refresh token can be made multiple times in quick succession and they
     * can obtain the same renewed refresh token within the short period.
     *
     * <p>
     * This feature is available in Authlete 2.3 onwards.
     * </p>
     *
     * @return
     *         {@code true} if multiple refresh token requests in a short
     *         period can obtain the same renewed refresh token.
     *
     * @since 3.21
     * @since Authlete 2.3
     */
    public boolean isRefreshTokenIdempotent()
    {
        return refreshTokenIdempotent;
    }


    /**
     * Set the flag indicating whether refresh token requests with the same
     * refresh token can be made multiple times in quick succession and they
     * can obtain the same renewed refresh token within the short period.
     *
     * <p>
     * This feature is available in Authlete 2.3 onwards.
     * </p>
     *
     * @param idempotent
     *         {@code true} to enable multiple refresh token requests in a
     *         short period to obtain the same renewed refresh token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.21
     * @since Authlete 2.3
     */
    public Service setRefreshTokenIdempotent(boolean idempotent)
    {
        this.refreshTokenIdempotent = idempotent;

        return this;
    }


    /**
     * Get the flag indicating whether this service supports <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1&#x002E;0</a>.
     *
     * <p>
     * If the feature of OpenID Federation 1.0 is not enabled in the
     * Authlete server on which this service is hosted, functions related to
     * OpenID Federation 1.0 are not usable regardless of the setting
     * of this property.
     * </p>
     *
     * <p>
     * OpenID Federation 1.0 is supported by Authlete 2.3 onwards.
     * </p>
     *
     * @return
     *         {@code true} if this service supports OpenID Federation 1.0.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public boolean isFederationEnabled()
    {
        return federationEnabled;
    }


    /**
     * Set the flag indicating whether this service supports <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1&#x002E;0</a>.
     *
     * <p>
     * If the feature of OpenID Federation 1.0 is not enabled in the
     * Authlete server on which this service is hosted, functions related to
     * OpenID Federation 1.0 are not usable regardless of the setting
     * of this property.
     * </p>
     *
     * <p>
     * OpenID Federation 1.0 is supported by Authlete 2.3 onwards.
     * </p>
     *
     * @param enabled
     *         {@code true} to enable the feature of OpenID
     *         Federation 1.0.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setFederationEnabled(boolean enabled)
    {
        this.federationEnabled = enabled;

        return this;
    }


    /**
     * Get the human-readable name representing the organization that operates
     * this service. This property corresponds to the {@code organization_name}
     * server metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * If this property is not empty, the {@code organization_name} property
     * appears in self-signed entity statements of this service.
     * </p>
     *
     * @return
     *         The name of the organization that operates this service.
     *
     * @since 3.22
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
     * Set the human-readable name representing the organization that operates
     * this service. This property corresponds to the {@code organization_name}
     * server metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * If this property is not empty, the {@code organization_name} property
     * appears in self-signed entity statements of this service.
     * </p>
     *
     * @param name
     *         The name of the organization that operates this service.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setOrganizationName(String name)
    {
        this.organizationName = name;

        return this;
    }


    /**
     * Get the identifiers of entities that can issue entity statements for
     * this service. This property corresponds to the {@code authority_hints}
     * property that appears in a self-signed entity statement that is defined
     * in <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1.0</a>.
     *
     * <p>
     * OpenID providers participating in one or more federations are supposed
     * to have authority hints. It is only trust anchors having no superiors
     * that do not have authority hints.
     * </p>
     *
     * <p>
     * Because the {@code authority_hints} property in self-signed entity
     * statements of OpenID providers is mandatory, if this property is empty,
     * the configuration endpoint ({@code /.well-known/openid-federation})
     * cannot generate a valid entity statement. It means that OpenID
     * Federation 1.0 does not work.
     * </p>
     *
     * @return
     *         Identifiers of entities that can issue entity statements
     *         for this service.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public URI[] getAuthorityHints()
    {
        return authorityHints;
    }


    /**
     * Set the identifiers of entities that can issue entity statements for
     * this service. This property corresponds to the {@code authority_hints}
     * property that appears in a self-signed entity statement that is defined
     * in <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1.0</a>.
     *
     * <p>
     * OpenID providers participating in one or more federations are supposed
     * to have authority hints. It is only trust anchors having no superiors
     * that do not have authority hints.
     * </p>
     *
     * <p>
     * Because the {@code authority_hints} property in self-signed entity
     * statements of OpenID providers is mandatory, if this property is empty,
     * the configuration endpoint ({@code /.well-known/openid-federation})
     * cannot generate a valid entity statement. It means that OpenID
     * Federation 1.0 does not work.
     * </p>
     *
     * @param authorityHints
     *         Identifiers of entities that can issue entity statements
     *         for this service.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setAuthorityHints(URI[] authorityHints)
    {
        this.authorityHints = authorityHints;

        return this;
    }


    /**
     * Get the trust anchors that are referenced when this service resolves
     * trust chains of relying parties.
     *
     * <p>
     * If this property is empty, client registration fails regardless of
     * whether its type is {@code automatic} or {@code explicit}. It means
     * that OpenID Federation 1.0 does not work.
     * </p>
     *
     * @return
     *         The trust anchors that are referenced when this service resolves
     *         trust chains of relying parties.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     *
     * @see TrustAnchor
     */
    public TrustAnchor[] getTrustAnchors()
    {
        return trustAnchors;
    }


    /**
     * Set the trust anchors that are referenced when this service resolves
     * trust chains of relying parties.
     *
     * <p>
     * If this property is empty, client registration fails regardless of
     * whether its type is {@code automatic} or {@code explicit}. It means
     * that OpenID Federation 1.0 does not work.
     * </p>
     *
     * @param trustAnchors
     *         The trust anchors that are referenced when this service resolves
     *         trust chains of relying parties.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     *
     * @see TrustAnchor
     */
    public Service setTrustAnchors(TrustAnchor[] trustAnchors)
    {
        this.trustAnchors = trustAnchors;

        return this;
    }


    /**
     * Get the JWK Set document containing keys that are used to sign (1)
     * self-signed entity statement of this service and (2) the response from
     * {@code signed_jwks_uri}.
     *
     * <p>
     * If this property is empty, this service cannot generate a valid
     * self-signed entity statement. It means that OpenID Federation
     * 1.0 does not work.
     * </p>
     *
     * @return
     *         The JWK Set document containing keys used to sign self-signed
     *         entity statement and the response from {@code signed_jwks_uri}.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public String getFederationJwks()
    {
        return federationJwks;
    }


    /**
     * Set the JWK Set document containing keys that are used to sign (1)
     * self-signed entity statement of this service and (2) the response from
     * {@code signed_jwks_uri}.
     *
     * <p>
     * If this property is empty, this service cannot generate a valid
     * self-signed entity statement. It means that OpenID Federation
     * 1.0 does not work.
     * </p>
     *
     * @param jwks
     *         The JWK Set document containing keys used to sign self-signed
     *         entity statement and the response from {@code signed_jwks_uri}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setFederationJwks(String jwks)
    {
        this.federationJwks = jwks;

        return this;
    }


    /**
     * Get the key ID to identify a JWK that should be used to sign the entity
     * configuration and the signed JWK Set.
     *
     * <p>
     * The entity configuration is a kind of JWT and published at
     * {@code /.well-known/openid-federation} or at a variant location such as
     * {@code /.well-known/openid-federation}<i>{path_part_of_issuer}</i>.
     * </p>
     *
     * <p>
     * The signed JWK Set is also a kind of JWT and published at the URL
     * designated by the {@code signed_jwks_uri} server metadata.
     * </p>
     *
     * <p>
     * When this property is specified, Authlete will use the JWK having the
     * specified key ID when signing the entity configuration and the signed
     * JWK Set. Otherwise, when this property is omitted, there is no guarantee
     * as to which JWK Authlete will choose.
     * </p>
     *
     * @return
     *         A key ID. May be {@code null}.
     *
     * @since 3.31
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public String getFederationSignatureKeyId()
    {
        return federationSignatureKeyId;
    }


    /**
     * Set the key ID to identify a JWK that should be used to sign the entity
     * configuration and the signed JWK Set.
     *
     * <p>
     * The entity configuration is a kind of JWT and published at
     * {@code /.well-known/openid-federation} or at a variant location such as
     * {@code /.well-known/openid-federation}<i>{path_part_of_issuer}</i>.
     * </p>
     *
     * <p>
     * The signed JWK Set is also a kind of JWT and published at the URL
     * designated by the {@code signed_jwks_uri} server metadata.
     * </p>
     *
     * <p>
     * When this property is specified, Authlete will use the JWK having the
     * specified key ID when signing the entity configuration and the signed
     * JWK Set. Otherwise, when this property is omitted, there is no guarantee
     * as to which JWK Authlete will choose.
     * </p>
     *
     * @param keyId
     *         A key ID. May be {@code null}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.31
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setFederationSignatureKeyId(String keyId)
    {
        this.federationSignatureKeyId = keyId;

        return this;
    }


    /**
     * Get the duration of the entity configuration in seconds.
     *
     * <p>
     * An OpenID provider that participates in an OpenID Connect federation
     * must publish its entity configuration at
     * {@code /.well-known/openid-federation} or at a variant location such as
     * <code>/.well-known/openid-federation<i>{path_part_of_issuer}</i></code>.
     * An entity configuration is a kind of JWT. This property specifies the
     * duration of the JWT in seconds.
     * </p>
     *
     * <p>
     * When the value of this property is 0, the default value determined by
     * your Authlete server is used as the duration of the entity configuration.
     * </p>
     *
     * @return
     *         The duration of the entity configuration in seconds.
     *
     * @since 3.31
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public long getFederationConfigurationDuration()
    {
        return federationConfigurationDuration;
    }


    /**
     * Set the duration of the entity configuration in seconds.
     *
     * <p>
     * An OpenID provider that participates in an OpenID Connect federation
     * must publish its entity configuration at
     * {@code /.well-known/openid-federation} or at a variant location such as
     * <code>/.well-known/openid-federation<i>{path_part_of_issuer}</i></code>.
     * An entity configuration is a kind of JWT. This property specifies the
     * duration of the JWT in seconds.
     * </p>
     *
     * <p>
     * When the value of this property is 0, the default value determined by
     * your Authlete server is used as the duration of the entity configuration.
     * </p>
     *
     * @param duration
     *         The duration of the entity configuration in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.31
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setFederationConfigurationDuration(long duration)
    {
        this.federationConfigurationDuration = duration;

        return this;
    }


    /**
     * Get the URI of the endpoint that returns this service's JWK Set document in
     * the JWT format. This property corresponds to the {@code signed_jwks_uri}
     * server metadata defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * The JWT returned from the endpoint is signed with a key in the JWK Set
     * document specified by the {@code federationJwks} property. Therefore, if
     * the {@code federationJwks} property is not set up properly, the endpoint
     * won't return a valid response.
     * </p>
     *
     * <p>
     * If this property is not empty, the {@code signed_jwks_uri} property
     * appears in the {@code openid_provider} property of this service's entity
     * configuration. And in that case, {@code jwks_uri} does not appear in
     * exchange.
     * </p>
     *
     * @return
     *         The URI of the endpoint that returns this service's JWK Set
     *         document in the JWT format.
     *
     * @since 3.22
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
     * Set the URI of the endpoint that returns this service's JWK Set document in
     * the JWT format. This property corresponds to the {@code signed_jwks_uri}
     * server metadata defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * The JWT returned from the endpoint is signed with a key in the JWK Set
     * document specified by the {@code federationJwks} property. Therefore, if
     * the {@code federationJwks} property is not set up properly, the endpoint
     * won't return a valid response.
     * </p>
     *
     * <p>
     * If this property is not empty, the {@code signed_jwks_uri} property
     * appears in the {@code openid_provider} property of this service's entity
     * configuration. And in that case, {@code jwks_uri} does not appear in
     * exchange.
     * </p>
     *
     * @param uri
     *         The URI of the endpoint that returns this service's JWK Set
     *         document in the JWT format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setSignedJwksUri(URI uri)
    {
        this.signedJwksUri = uri;

        return this;
    }


    /**
     * Get the URI of the federation registration endpoint. This property
     * corresponds to the {@code federation_registration_endpoint} server
     * metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * If this service declares it supports the "{@code explicit}" client
     * registration, this property must not be empty.
     * </p>
     *
     * @return
     *         The URI of the federation registration endpoint.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public URI getFederationRegistrationEndpoint()
    {
        return federationRegistrationEndpoint;
    }


    /**
     * Set the URI of the federation registration endpoint. This property
     * corresponds to the {@code federation_registration_endpoint} server
     * metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * If this service declares it supports the "{@code explicit}" client
     * registration, this property must not be empty.
     * </p>
     *
     * @param endpoint
     *         The URI of the federation registration endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public Service setFederationRegistrationEndpoint(URI endpoint)
    {
        this.federationRegistrationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the client registration types supported by this service. This
     * property corresponds to the {@code client_registration_types_supported}
     * server metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * If this property includes {@link ClientRegistrationType#EXPLICIT
     * EXPLICIT}, the {@code federationRegistrationEndpoint} property must be
     * set up properly.
     * </p>
     *
     * @return
     *         Client registration types supported by this service.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     *
     * @see ClientRegistrationType
     */
    public ClientRegistrationType[] getSupportedClientRegistrationTypes()
    {
        return supportedClientRegistrationTypes;
    }


    /**
     * Set the client registration types supported by this service. This
     * property corresponds to the {@code client_registration_types_supported}
     * server metadata that is defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     *
     * <p>
     * If this property includes {@link ClientRegistrationType#EXPLICIT
     * EXPLICIT}, the {@code federationRegistrationEndpoint} property must be
     * set up properly.
     * </p>
     *
     * @param types
     *         Client registration types supported by this service.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.22
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     *
     * @see ClientRegistrationType
     */
    public Service setSupportedClientRegistrationTypes(ClientRegistrationType[] types)
    {
        this.supportedClientRegistrationTypes = types;

        return this;
    }


    /**
     * Get the flag indicating whether to prohibit unidentifiable clients from
     * making token exchange requests (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * Section 2.1 of <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     * >RFC 8692 OAuth 2.0 Token Exchange</a> states as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>The supported methods of client authentication and whether or not
     * to allow unauthenticated or <b>unidentified</b> clients are deployment
     * decisions that are at the discretion of the authorization server.</i>
     * </p>
     * </blockquote>
     *
     * <p>
     * Technically speaking, "<b>unidentified</b>" in the excerpted sentence
     * means that a token exchange request contains no identifier of the client
     * that made the request.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, this service rejects token
     * exchange requests that contain no client identifier.
     * </p>
     *
     * @return
     *         {@code true} if this service rejects token exchange requests
     *         that contain no client identifier.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public boolean isTokenExchangeByIdentifiableClientsOnly()
    {
        return tokenExchangeByIdentifiableClientsOnly;
    }


    /**
     * Set the flag indicating whether to prohibit unidentifiable clients from
     * making token exchange requests (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * Section 2.1 of <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     * >RFC 8692 OAuth 2.0 Token Exchange</a> states as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>The supported methods of client authentication and whether or not
     * to allow unauthenticated or <b>unidentified</b> clients are deployment
     * decisions that are at the discretion of the authorization server.</i>
     * </p>
     * </blockquote>
     *
     * <p>
     * Technically speaking, "<b>unidentified</b>" in the excerpted sentence
     * means that a token exchange request contains no identifier of the client
     * that made the request.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, this service rejects token
     * exchange requests that contain no client identifier.
     * </p>
     *
     * @param only
     *         {@code true} to reject token exchange requests that contain
     *         no client identifier.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public Service setTokenExchangeByIdentifiableClientsOnly(boolean only)
    {
        this.tokenExchangeByIdentifiableClientsOnly = only;

        return this;
    }


    /**
     * Get the flag indicating whether to prohibit public clients from making
     * token exchange requests (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * Section 2.1 of <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     * >RFC 8692 OAuth 2.0 Token Exchange</a> states as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>The supported methods of client authentication and whether or not
     * to allow <b>unauthenticated</b> or unidentified clients are deployment
     * decisions that are at the discretion of the authorization server.</i>
     * </p>
     * </blockquote>
     *
     * <p>
     * Technically speaking, "<b>unauthenticated</b>" in the excerpted sentence
     * means that the client making a token exchange request is a public client
     * and so <a href=
     * "https://darutk.medium.com/oauth-2-0-client-authentication-4b5f929305d4"
     * >client authentication</a> for the client is not required at the token
     * endpoint.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, this service rejects token
     * exchange requests from public clients.
     * </p>
     *
     * @return
     *         {@code true} if this service rejects token exchange requests
     *         from public clients.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public boolean isTokenExchangeByConfidentialClientsOnly()
    {
        return tokenExchangeByConfidentialClientsOnly;
    }


    /**
     * Set the flag indicating whether to prohibit public clients from making
     * token exchange requests (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * Section 2.1 of <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     * >RFC 8692 OAuth 2.0 Token Exchange</a> states as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>The supported methods of client authentication and whether or not
     * to allow <b>unauthenticated</b> or unidentified clients are deployment
     * decisions that are at the discretion of the authorization server.</i>
     * </p>
     * </blockquote>
     *
     * <p>
     * Technically speaking, "<b>unauthenticated</b>" in the excerpted sentence
     * means that the client making a token exchange request is a public client
     * and so <a href=
     * "https://darutk.medium.com/oauth-2-0-client-authentication-4b5f929305d4"
     * >client authentication</a> for the client is not required at the token
     * endpoint.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, this service rejects token
     * exchange requests from public clients.
     * </p>
     *
     * @param only
     *         {@code true} to reject token exchange requests from public
     *         clients.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public Service setTokenExchangeByConfidentialClientsOnly(boolean only)
    {
        this.tokenExchangeByConfidentialClientsOnly = only;

        return this;
    }


    /**
     * Get the flag indicating whether to prohibit clients which have no
     * explicit permission from making token exchange requests (cf&#x002E;
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * An administrator can give a client an explicit permission to make
     * token exchange requests by setting {@code true} to the
     * {@code tokenExchangePermitted} flag of the client (cf. {@link
     * ClientExtension#setTokenExchangePermitted(boolean)}).
     * </p>
     *
     * <p>
     * When this flag ({@code tokenExchangeByPermittedClientsOnly}) is set
     * to {@code true}, this service rejects token exchange requests from
     * clients whose {@code tokenExchangePermitted} flag is {@code false}.
     * </p>
     *
     * @return
     *         {@code true} if this service rejects token exchange requests
     *         from clients whose {@code tokenExchangePermitted} flag is
     *         {@code false}.
     *
     * @see ClientExtension#isTokenExchangePermitted()
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public boolean isTokenExchangeByPermittedClientsOnly()
    {
        return tokenExchangeByPermittedClientsOnly;
    }


    /**
     * Set the flag indicating whether to prohibit clients which have no
     * explicit permission from making token exchange requests (cf&#x002E;
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * An administrator can give a client an explicit permission to make
     * token exchange requests by setting {@code true} to the
     * {@code tokenExchangePermitted} flag of the client (cf. {@link
     * ClientExtension#setTokenExchangePermitted(boolean)}).
     * </p>
     *
     * <p>
     * When this flag ({@code tokenExchangeByPermittedClientsOnly}) is set
     * to {@code true}, this service rejects token exchange requests from
     * clients whose {@code tokenExchangePermitted} flag is {@code false}.
     * </p>
     *
     * @param only
     *         {@code true} to reject token exchange requests from clients
     *         whose {@code tokenExchangePermitted} flag is {@code false}.
     *
     * @return
     *         {@code this} object.
     *
     * @see ClientExtension#setTokenExchangePermitted(boolean)
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public Service setTokenExchangeByPermittedClientsOnly(boolean only)
    {
        this.tokenExchangeByPermittedClientsOnly = only;

        return this;
    }


    /**
     * Get the flag indicating whether to reject token exchange requests which
     * use encrypted JWTs as input tokens.
     *
     * <p>
     * When this {@code tokenExchangeEncryptedJwtRejected} flag is {@code true},
     * token exchange requests which use encrypted JWTs as input tokens (subject
     * token and/or actor token) with the token type
     * {@code "urn:ietf:params:oauth:token-type:jwt"} or the token type
     * {@code "urn:ietf:params:oauth:token-type:id_token"} are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input token when Authlete detects that it is an encrypted
     * JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for token exchange requests.
     * </p>
     *
     * @return
     *         {@code true} if token exchange requests which use encrypted JWTs
     *         as input tokens are rejected.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.27
     * @since Authlete 2.3
     */
    public boolean isTokenExchangeEncryptedJwtRejected()
    {
        return tokenExchangeEncryptedJwtRejected;
    }


    /**
     * Set the flag indicating whether to reject token exchange requests which
     * use encrypted JWTs as input tokens.
     *
     * <p>
     * When this {@code tokenExchangeEncryptedJwtRejected} flag is {@code true},
     * token exchange requests which use encrypted JWTs as input tokens (subject
     * token and/or actor token) with the token type
     * {@code "urn:ietf:params:oauth:token-type:jwt"} or the token type
     * {@code "urn:ietf:params:oauth:token-type:id_token"} are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input token when Authlete detects that it is an encrypted
     * JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for token exchange requests.
     * </p>
     *
     * @param rejected
     *         {@code true} to reject token exchange requests which use
     *         encrypted JWTs as input tokens.
     *
     * @return
     *         {@code this} object.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.27
     * @since Authlete 2.3
     */
    public Service setTokenExchangeEncryptedJwtRejected(boolean rejected)
    {
        this.tokenExchangeEncryptedJwtRejected = rejected;

        return this;
    }


    /**
     * Get the flag indicating whether to reject token exchange requests which
     * use unsigned JWTs as input tokens.
     *
     * <p>
     * When this {@code tokenExchangeUnsignedJwtRejected} flag is {@code true},
     * token exchange requests which use unsigned JWTs as input tokens (subject
     * token and/or actor token) with the token type
     * {@code "urn:ietf:params:oauth:token-type:jwt"} or the token type
     * {@code "urn:ietf:params:oauth:token-type:id_token"} are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input token when Authlete detects that it is an unsigned
     * JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for token exchange requests.
     * </p>
     *
     * @return
     *         {@code true} if token exchange requests which use unsigned JWTs
     *         as input tokens are rejected.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.27
     * @since Authlete 2.3
     */
    public boolean isTokenExchangeUnsignedJwtRejected()
    {
        return tokenExchangeUnsignedJwtRejected;
    }


    /**
     * Set the flag indicating whether to reject token exchange requests which
     * use unsigned JWTs as input tokens.
     *
     * <p>
     * When this {@code tokenExchangeUnsignedJwtRejected} flag is {@code true},
     * token exchange requests which use unsigned JWTs as input tokens (subject
     * token and/or actor token) with the token type
     * {@code "urn:ietf:params:oauth:token-type:jwt"} or the token type
     * {@code "urn:ietf:params:oauth:token-type:id_token"} are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input token when Authlete detects that it is an unsigned
     * JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for token exchange requests.
     * </p>
     *
     * @param rejected
     *         {@code true} to reject token exchange requests which use
     *         unsigned JWTs as input tokens.
     *
     * @return
     *         {@code this} object.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.27
     * @since Authlete 2.3
     */
    public Service setTokenExchangeUnsignedJwtRejected(boolean rejected)
    {
        this.tokenExchangeUnsignedJwtRejected = rejected;

        return this;
    }


    /**
     * Get the flag indicating whether to prohibit unidentifiable clients from
     * using the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * (<a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523</a>).
     *
     * <p>
     * <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523 JSON Web
     * Token (JWT) Profile for OAuth 2.0 Client Authentication and Authorization
     * Grants</a> states as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>JWT authorization grants may be used with or without client
     * authentication or <b>identification</b>.</i>
     * </p>
     * </blockquote>
     *
     * <p>
     * Technically speaking, "<b>identification</b>" in the excerpted sentence
     * means that a token request contains an identifier of the client that
     * made the request.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, this service rejects token requests
     * that use the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * but contain no client identifier.
     * </p>
     *
     * @return
     *         {@code true} if this service rejects token requests that use the
     *         grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     *         but contain no client identifier.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public boolean isJwtGrantByIdentifiableClientsOnly()
    {
        return jwtGrantByIdentifiableClientsOnly;
    }


    /**
     * Set the flag indicating whether to prohibit unidentifiable clients from
     * using the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * (<a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523</a>).
     *
     * <p>
     * <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523 JSON Web
     * Token (JWT) Profile for OAuth 2.0 Client Authentication and Authorization
     * Grants</a> states as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * <i>JWT authorization grants may be used with or without client
     * authentication or <b>identification</b>.</i>
     * </p>
     * </blockquote>
     *
     * <p>
     * Technically speaking, "<b>identification</b>" in the excerpted sentence
     * means that a token request contains an identifier of the client that
     * made the request.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, this service rejects token requests
     * that use the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * but contain no client identifier.
     * </p>
     *
     * @param only
     *         {@code true} to reject token requests that use the grant type
     *         {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"} but
     *         contain no client identifier.
     *
     * @return
     *         {@code this} service.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public Service setJwtGrantByIdentifiableClientsOnly(boolean only)
    {
        this.jwtGrantByIdentifiableClientsOnly = only;

        return this;
    }


    /**
     * Get the flag indicating whether to reject token requests that use
     * an encrypted JWT as an authorization grant with the grant type
     * {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * (<a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523</a>).
     *
     * <p>
     * When this {@code jwtGrantEncryptedJwtRejected} flag is {@code true},
     * token requests that use an encrypted JWT as an authorization grant with
     * the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input assertion when Authlete detects that it is an
     * encrypted JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for the grant type.
     * </p>
     *
     * @return
     *         {@code true} if token requests that use an encrypted JWT as
     *         an authorization grant with the grant type
     *         {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"} are
     *         rejected.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public boolean isJwtGrantEncryptedJwtRejected()
    {
        return jwtGrantEncryptedJwtRejected;
    }


    /**
     * Set the flag indicating whether to reject token requests that use
     * an encrypted JWT as an authorization grant with the grant type
     * {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * (<a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523</a>).
     *
     * <p>
     * When this {@code jwtGrantEncryptedJwtRejected} flag is {@code true},
     * token requests that use an encrypted JWT as an authorization grant with
     * the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input assertion when Authlete detects that it is an
     * encrypted JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for the grant type.
     * </p>
     *
     * @param rejected
     *         {@code true} to reject token requests that use an encrypted JWT
     *         as an authorization grant with the grant type
     *         {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
     *
     * @return
     *         {@code this} object.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public Service setJwtGrantEncryptedJwtRejected(boolean rejected)
    {
        this.jwtGrantEncryptedJwtRejected = rejected;

        return this;
    }


    /**
     * Get the flag indicating whether to reject token requests that use
     * an unsigned JWT as an authorization grant with the grant type
     * {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * (<a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523</a>).
     *
     * <p>
     * When this {@code jwtGrantUnsignedJwtRejected} flag is {@code true},
     * token requests that use an unsigned JWT as an authorization grant with
     * the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input assertion when Authlete detects that it is an
     * unsigned JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for the grant type.
     * </p>
     *
     * @return
     *         {@code true} if token requests that use an unsigned JWT as
     *         an authorization grant with the grant type
     *         {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"} are
     *         rejected.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public boolean isJwtGrantUnsignedJwtRejected()
    {
        return jwtGrantUnsignedJwtRejected;
    }


    /**
     * Set the flag indicating whether to reject token requests that use
     * an unsigned JWT as an authorization grant with the grant type
     * {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * (<a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523</a>).
     *
     * <p>
     * When this {@code jwtGrantUnsignedJwtRejected} flag is {@code true},
     * token requests that use an unsigned JWT as an authorization grant with
     * the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}
     * are rejected.
     * </p>
     *
     * <p>
     * When this flag is {@code false}, Authlete skips remaining validation
     * steps on an input assertion when Authlete detects that it is an
     * unsigned JWT.
     * </p>
     *
     * <p>
     * See the description of {@link TokenResponse} for details about validation
     * Authlete performs for the grant type.
     * </p>
     *
     * @param rejected
     *         {@code true} to reject token requests that use an unsigned JWT
     *         as an authorization grant with the grant type
     *         {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
     *
     * @return
     *         {@code this} object.
     *
     * @see TokenResponse
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public Service setJwtGrantUnsignedJwtRejected(boolean rejected)
    {
        this.jwtGrantUnsignedJwtRejected = rejected;

        return this;
    }


    /**
     * Get the flag indicating whether to block DCR (Dynamic Client Registration)
     * requests whose {@code software_id} has already been used previously.
     *
     * <p>
     * A DCR request may contain the {@code software_id} client metadata (which
     * is defined in <a href="https://www.rfc-editor.org/rfc/rfc7591.html">RFC
     * 7591</a>). The client metadata is saved in Authlete's database together
     * with other client metadata.
     * </p>
     *
     * <p>
     * If this {@code dcrDuplicateSoftwareIdBlocked} flag is {@code true},
     * Authlete checks whether the value of the {@code software_id} client
     * metadata included in a DCR request already exists in the database,
     * and rejects the DCR request if one exists.
     * </p>
     *
     * @return
     *         {@code true} if DCR requests whose {@code software_id} has already
     *         been used previously are blocked.
     *
     * @since 3.32
     * @since Authlete 2.2.30
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html"
     *      >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
     */
    public boolean isDcrDuplicateSoftwareIdBlocked()
    {
        return dcrDuplicateSoftwareIdBlocked;
    }


    /**
     * Set the flag indicating whether to block DCR (Dynamic Client Registration)
     * requests whose {@code software_id} has already been used previously.
     *
     * <p>
     * A DCR request may contain the {@code software_id} client metadata (which
     * is defined in <a href="https://www.rfc-editor.org/rfc/rfc7591.html">RFC
     * 7591</a>). The client metadata is saved in Authlete's database together
     * with other client metadata.
     * </p>
     *
     * <p>
     * If this {@code dcrDuplicateSoftwareIdBlocked} flag is {@code true},
     * Authlete checks whether the value of the {@code software_id} client
     * metadata included in a DCR request already exists in the database,
     * and rejects the DCR request if one exists.
     * </p>
     *
     * @param blocked
     *         {@code true} to block DCR requests whose {@code software_id} has
     *         already been used previously.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.32
     * @since Authlete 2.2.30
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html"
     *      >RFC 7591 OAuth 2.0 Dynamic Client Registration Protocol</a>
     */
    public Service setDcrDuplicateSoftwareIdBlocked(boolean blocked)
    {
        this.dcrDuplicateSoftwareIdBlocked = blocked;

        return this;
    }


    /**
     * Get the key ID of a JWK containing the private key used by this service to
     * sign responses from the resource server, such as the userinfo endpoint and
     * responses sent to the RS signing endpoint.
     *
     * @return
     *         The key ID.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    public String getResourceSignatureKeyId()
    {
        return resourceSignatureKeyId;
    }


    /**
     * Set the key ID of a JWK containing the private key used by this service to
     * sign responses from the resource server, such as the userinfo endpoint and
     * responses sent to the RS signing endpoint.
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
    public Service setResourceSignatureKeyId(String keyId)
    {
        this.resourceSignatureKeyId = keyId;

        return this;
    }


    /**
     * Get whether the service signs responses from the resource server.
     * If {@code true}, userinfo issue responses and responses sent to the RS
     * signing endpoint that are in relation to a client's signed request will
     * be signed using the key identified by {{@link #getResourceSignatureKeyId()}.
     *
     * @return
     *         {@code true} if the services signs responses for the resource server,
     *         {@code false} otherwise.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    public boolean isRsResponseSigned()
    {
        return rsResponseSigned;
    }


    /**
     * Set whether the service signs responses from the resource server.
     * If {@code true}, userinfo issue responses and responses sent to the RS
     * signing endpoint that are in relation to a client's signed request will
     * be signed using the key identified by {{@link #getResourceSignatureKeyId()}.
     *
     * @param signed
     *         {@code true} if the services signs responses for the resource server,
     *         {@code false} otherwise.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.39
     * @since Authlete 2.3
     */
    public Service setRsResponseSigned(boolean signed)
    {
        this.rsResponseSigned = signed;

        return this;
    }


    /**
     * Get the flag indicating whether to remove the {@code openid} scope from
     * a new access token issued by the refresh token flow if the presented
     * refresh token does not contain the {@code offline_access} scope.
     *
     * @return
     *         {@code true} if the {@code openid} scope is dropped when
     *         the presented refresh token does not contain the
     *         {@code offline_access} scope.
     *
     * @since 3.42
     * @since Authlete 2.2.36
     */
    public boolean isOpenidDroppedOnRefreshWithoutOfflineAccess()
    {
        return openidDroppedOnRefreshWithoutOfflineAccess;
    }


    /**
     * Set the flag indicating whether to remove the {@code openid} scope from
     * a new access token issued by the refresh token flow if the presented
     * refresh token does not contain the {@code offline_access} scope.
     *
     * @param dropped
     *         {@code true} to drop the {@code openid} scope when
     *         the presented refresh token does not contain the
     *         {@code offline_access} scope.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.42
     * @since Authlete 2.2.36
     */
    public Service setOpenidDroppedOnRefreshWithoutOfflineAccess(boolean dropped)
    {
        this.openidDroppedOnRefreshWithoutOfflineAccess = dropped;

        return this;
    }


    /**
     * Get the flag indicating whether the feature of Verifiable Credentials
     * for this service is enabled or not.
     *
     * @return
     *         {@code true} if the feature of Verifiable Credentials is enabled.
     *
     * @since 3.55
     * @since Authlete 3.0
     */
    public boolean isVerifiableCredentialsEnabled()
    {
        return verifiableCredentialsEnabled;
    }


    /**
     * Set the flag indicating whether the feature of Verifiable Credentials
     * for this service is enabled or not.
     *
     * @param enabled
     *         {@code true} to indicate that the feature of Verifiable Credentials
     *         is enabled.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.55
     * @since Authlete 3.0
     */
    public Service setVerifiableCredentialsEnabled(boolean enabled)
    {
        this.verifiableCredentialsEnabled = enabled;

        return this;
    }


    /**
     * Get the credential issuer metadata.
     *
     * @return
     *         The credential issuer metadata.
     *
     * @since 3.55
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     */
    public CredentialIssuerMetadata getCredentialIssuerMetadata()
    {
        return credentialIssuerMetadata;
    }


    /**
     * Set the credential issuer metadata.
     *
     * @param metadata
     *         The credential issuer metadata.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.55
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     */
    public Service setCredentialIssuerMetadata(CredentialIssuerMetadata metadata)
    {
        this.credentialIssuerMetadata = metadata;

        return this;
    }


    /**
     * Get the default duration of credential offers in seconds.
     *
     * <p>
     * When an API call to the {@code /vci/offer/create} API does not contain
     * the {@code duration} request parameter or the value of the parameter is
     * 0 or negative, the value of this property is used as the default value.
     * </p>
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used as the default value.
     * </p>
     *
     * @return
     *         The default duration of credential offers in seconds.
     *
     * @since 3.59
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     *
     * @see CredentialOfferCreateRequest#getDuration()
     */
    public long getCredentialOfferDuration()
    {
        return credentialOfferDuration;
    }


    /**
     * Set the default duration of credential offers in seconds.
     *
     * <p>
     * When an API call to the {@code /vci/offer/create} API does not contain
     * the {@code duration} request parameter or the value of the parameter is
     * 0 or negative, the value of this property is used as the default value.
     * </p>
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used as the default value.
     * </p>
     *
     * @param duration
     *         The default duration of credential offers in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.59
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     *
     * @see CredentialOfferCreateRequest#setDuration(long)
     */
    public Service setCredentialOfferDuration(long duration)
    {
        this.credentialOfferDuration = duration;

        return this;
    }


    /**
     * Get the default length of user PINs.
     *
     * <p>
     * When an API call to the {@code /vci/offer/create} API does not contain
     * the {@code userPinLength} request parameter or the value of the parameter
     * is 0 or negative, the value of this property is used as the default value.
     * </p>
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used as the default value.
     * </p>
     *
     * <p>
     * NOTE: This property has been deprecated due to a breaking change of the
     * OID4VCI specification. The {@code /vci/offer/create} API no longer
     * recognizes the {@code userPinLength} request parameter.
     * </p>
     *
     * @return
     *         The default length of user PINs.
     *
     * @since 3.59
     * @since Authlete 3.0
     *
     * @see CredentialOfferCreateRequest#getUserPinLength()
     *
     * @deprecated
     */
    @Deprecated
    public int getUserPinLength()
    {
        return userPinLength;
    }


    /**
     * Set the default length of user PINs.
     *
     * <p>
     * When an API call to the {@code /vci/offer/create} API does not contain
     * the {@code userPinLength} request parameter or the value of the parameter
     * is 0 or negative, the value of this property is used as the default value.
     * </p>
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used as the default value.
     * </p>
     *
     * <p>
     * NOTE: This property has been deprecated due to a breaking change of the
     * OID4VCI specification. The {@code /vci/offer/create} API no longer
     * recognizes the {@code userPinLength} request parameter.
     * </p>
     *
     * @param length
     *         The default length of user PINs.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.59
     * @since Authlete 3.0
     *
     * @see CredentialOfferCreateRequest#setUserPinLength(int)
     *
     * @deprecated
     */
    @Deprecated
    public Service setUserPinLength(int length)
    {
        this.userPinLength = length;

        return this;
    }


    /**
     * Get the type of the {@code aud} claim in ID tokens.
     * Valid values are as follows.
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>{@code "array"}</td>
     *     <td>The type of the {@code aud} claim is always an array of strings.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "string"}</td>
     *     <td>The type of the {@code aud} claim is always a single string.</td>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td>The type of the {@code aud} claim remains the same as before.</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * <p>
     * Authlete APIs that may trigger ID token issuance may accept the
     * {@code idTokenAudType} request parameter (e.g.
     * {@link AuthorizationIssueRequest#getIdTokenAudType()}). Such request
     * parameters take precedence over this service property.
     * </p>
     *
     * @return
     *         The type of the {@code aud} claim in ID tokens.
     *
     * @since 3.57
     * @since Authlete 2.3.3
     */
    public String getIdTokenAudType()
    {
        return idTokenAudType;
    }


    /**
     * Set the type of the {@code aud} claim in ID tokens.
     * Valid values are as follows.
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>{@code "array"}</td>
     *     <td>The type of the {@code aud} claim is always an array of strings.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "string"}</td>
     *     <td>The type of the {@code aud} claim is always a single string.</td>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td>The type of the {@code aud} claim remains the same as before.</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * <p>
     * Authlete APIs that may trigger ID token issuance may accept the
     * {@code idTokenAudType} request parameter (e.g.
     * {@link AuthorizationIssueRequest#getIdTokenAudType()}). Such request
     * parameters take precedence over this service property.
     * </p>
     *
     * @param type
     *         The type of the {@code aud} claim in ID tokens.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.57
     * @since Authlete 2.3.3
     */
    public Service setIdTokenAudType(String type)
    {
        this.idTokenAudType = type;

        return this;
    }


    /**
     * Get the supported {@code prompt} values.
     *
     * @return
     *         The supported {@code prompt} values.
     *
     * @see <a href="https://openid.net/specs/openid-connect-prompt-create-1_0.html"
     *      >Initiating User Registration via OpenID Connect 1.0</a>
     *
     * @since 3.58
     * @since Authlete 3.0
     */
    public Prompt[] getSupportedPromptValues()
    {
        return supportedPromptValues;
    }


    /**
     * Set the supported {@code prompt} values.
     *
     * @param promptValues
     *         The supported {@code prompt} values.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-prompt-create-1_0.html"
     *      >Initiating User Registration via OpenID Connect 1.0</a>
     *
     * @since 3.58
     * @since Authlete 3.0
     */
    public Service setSupportedPromptValues(final Prompt[] promptValues)
    {
        this.supportedPromptValues = promptValues;

        return this;
    }


    /**
     * Get the name of the validation schema set that is used to validate the
     * content of {@code "verified_claims"}.
     *
     * <p>
     * Since version 2.3, Authlete validates the content of
     * {@code "verified_claims"} based on the JSON schema files that accompany
     * the specification (<a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>). They are found in the
     * <code><a href="https://bitbucket.org/openid/ekyc-ida/src/master/schema/"
     * >/schema/</a></code> folder of the Git repository of the specification.
     * </p>
     *
     * <p>
     * Usually, Authlete uses the legitimate JSON schema files that conform to
     * the specification. But, it is possible to make Authlete use a different
     * set of JSON schema files by specifying a name of validation schema set
     * through this property ({@code Service.verifiedClaimsValidationSchemaSet}).
     * </p>
     *
     * <p>
     * Authlete recognizes the following names of validation schema set at least.
     * </p>
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>name</th>
     *     <th>description</th>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td>Same as {@code "standard"}.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "standard"}</td>
     *     <td>The set of the legitimate JSON schema files.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "standard+id_document"}</td>
     *     <td>
     *       A set of customized JSON schema files that mostly conform to the
     *       standard but additionally accept {@code "id_document"} as a valid
     *       name of {@code evidence}. This is for backward compatibility. Note
     *       that {@code "id_document"} was deprecated by Implementer's Draft 4
     *       (cf. <a href="https://bitbucket.org/openid/ekyc-ida/pull-requests/152"
     *       >eKYC-IDA PR 152</a>).
     *     </td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * @return
     *         The name of the validation schema set.
     *
     * @since 3.61
     * @since Authlete 2.3.4
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/pull-requests/152"
     *       >eKYC-IDA PR 152: removal of deprecated items - issue 1334</a>
     */
    public String getVerifiedClaimsValidationSchemaSet()
    {
        return verifiedClaimsValidationSchemaSet;
    }


    /**
     * Set the name of the validation schema set that is used to validate the
     * content of {@code "verified_claims"}.
     *
     * <p>
     * Since version 2.3, Authlete validates the content of
     * {@code "verified_claims"} based on the JSON schema files that accompany
     * the specification (<a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>). They are found in the
     * <code><a href="https://bitbucket.org/openid/ekyc-ida/src/master/schema/"
     * >/schema/</a></code> folder of the Git repository of the specification.
     * </p>
     *
     * <p>
     * Usually, Authlete uses the legitimate JSON schema files that conform to
     * the specification. But, it is possible to make Authlete use a different
     * set of JSON schema files by specifying a name of validation schema set
     * through this property ({@code Service.verifiedClaimsValidationSchemaSet}).
     * </p>
     *
     * <p>
     * Authlete recognizes the following names of validation schema set at least.
     * </p>
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>name</th>
     *     <th>description</th>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td>Same as {@code "standard"}.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "standard"}</td>
     *     <td>The set of the legitimate JSON schema files.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "standard+id_document"}</td>
     *     <td>
     *       A set of customized JSON schema files that mostly conform to the
     *       standard but additionally accept {@code "id_document"} as a valid
     *       name of {@code evidence}. This is for backward compatibility. Note
     *       that {@code "id_document"} was deprecated by Implementer's Draft 4
     *       (cf. <a href="https://bitbucket.org/openid/ekyc-ida/pull-requests/152"
     *       >eKYC-IDA PR 152</a>).
     *     </td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * @param schemaSet
     *         The name of the validation schema set.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.61
     * @since Authlete 2.3.4
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/pull-requests/152"
     *       >eKYC-IDA PR 152: removal of deprecated items - issue 1334</a>
     */
    public Service setVerifiedClaimsValidationSchemaSet(String schemaSet)
    {
        this.verifiedClaimsValidationSchemaSet = schemaSet;

        return this;
    }


    /**
     * Get the flag indicating whether token requests using the pre-authorized
     * code grant flow by unidentifiable clients are allowed.
     *
     * <p>
     * This property corresponds to the
     * {@code pre-authorized_grant_anonymous_access_supported} server metadata
     * defined in "<a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credentials Issuance</a>".
     * </p>
     *
     * @return
     *         {@code true} if token requests using the pre-authorized code
     *         grant flow by unidentifiable clients are allows.
     *
     * @since 3.62
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public boolean isPreAuthorizedGrantAnonymousAccessSupported()
    {
        return preAuthorizedGrantAnonymousAccessSupported;
    }


    /**
     * Set the flag indicating whether token requests using the pre-authorized
     * code grant flow by unidentifiable clients are allowed.
     *
     * <p>
     * This property corresponds to the
     * {@code pre-authorized_grant_anonymous_access_supported} server metadata
     * defined in "<a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credentials Issuance</a>".
     * </p>
     *
     * @param supported
     *         {@code true} to allow unidentifiable clients to make token
     *         requests using the pre-authorized code grant flow.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.62
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public Service setPreAuthorizedGrantAnonymousAccessSupported(boolean supported)
    {
        this.preAuthorizedGrantAnonymousAccessSupported = supported;

        return this;
    }


    /**
     * Get the duration (in 1.0-ID1) or validity period (time window)
     * (in 1.0-Final) of {@code c_nonce} in seconds.
     *
     * <p>
     * The method of generating {@code c_nonce} differs between OID4VCI 1.0-ID1
     * and OID4VCI 1.0-Final.
     * </p>
     *
     * <p>
     * In 1.0-ID1, the {@code c_nonce} is issued from the token endpoint or the
     * credential endpoint. In Authlete's implementation, the {@code c_nonce}
     * for 1.0-ID1 is associated with an access token.
     * </p>
     *
     * <p>
     * For {@code c_nonce} in 1.0-ID1, this {@code cnonceDuration} property is
     * used as the lifetime of {@code c_nonce}.
     * </p>
     *
     * <p>
     * In contrast, in 1.0-Final, the {@code c_nonce} is issued from the nonce
     * endpoint. Requests to this endpoint do not include an access token, so
     * the server cannot determine who the requester is. As a result, the
     * {@code c_nonce} cannot be generated per access token or per client
     * application. Consequently, the same {@code c_nonce} value is returned to
     * anyone who accesses the endpoint within the same time window.
     * </p>
     *
     * <p>
     * For {@code c_nonce} in 1.0-Final, this {@code cnonceDuration} property
     * specifies the validity period (time window) in seconds.
     * </p>
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used as the default value.
     * </p>
     *
     * <p>
     * See <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credentials Issuance</a> for details about {@code
     * c_nonce}.
     * </p>
     *
     * <p>
     * NOTE:
     * The {@code getCNonceDuration()} method added by the version 3.63 has
     * been renamed to {@code getCnonceDuration()} by the version 3.90.
     * </p>
     *
     * @return
     *         The duration of {@code c_nonce} in seconds in OID4VCI 1.0-ID1, or
     *         the validity period (time window) in seconds in OID4VCI 1.0-Final.
     *
     * @since 3.90
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public long getCnonceDuration()
    {
        return cnonceDuration;
    }


    /**
     * Set the duration (in 1.0-ID1) or validity period (time window)
     * (in 1.0-Final) of {@code c_nonce} in seconds.
     *
     * <p>
     * The method of generating {@code c_nonce} differs between OID4VCI 1.0-ID1
     * and OID4VCI 1.0-Final.
     * </p>
     *
     * <p>
     * In 1.0-ID1, the {@code c_nonce} is issued from the token endpoint or the
     * credential endpoint. In Authlete's implementation, the {@code c_nonce}
     * for 1.0-ID1 is associated with an access token.
     * </p>
     *
     * <p>
     * For {@code c_nonce} in 1.0-ID1, this {@code cnonceDuration} property is
     * used as the lifetime of {@code c_nonce}.
     * </p>
     *
     * <p>
     * In contrast, in 1.0-Final, the {@code c_nonce} is issued from the nonce
     * endpoint. Requests to this endpoint do not include an access token, so
     * the server cannot determine who the requester is. As a result, the
     * {@code c_nonce} cannot be generated per access token or per client
     * application. Consequently, the same {@code c_nonce} value is returned to
     * anyone who accesses the endpoint within the same time window.
     * </p>
     *
     * <p>
     * For {@code c_nonce} in 1.0-Final, this {@code cnonceDuration} property
     * specifies the validity period (time window) in seconds.
     * </p>
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used as the default value.
     * </p>
     *
     * <p>
     * See <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credentials Issuance</a> for details about {@code
     * c_nonce}.
     * </p>
     *
     * <p>
     * NOTE:
     * The {@code setCNonceDuration(long)} method added by the version 3.63 has
     * been renamed to {@code setCnonceDuration(long)} by the version 3.90.
     * </p>
     *
     * @param duration
     *         The duration of {@code c_nonce} in seconds in OID4VCI 1.0-ID1, or
     *         the validity period (time window) in seconds in OID4VCI 1.0-Final.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.90
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public Service setCnonceDuration(long duration)
    {
        this.cnonceDuration = duration;

        return this;
    }


    /**
     * Get the duration of transaction ID in seconds that may be issued as a
     * result of a credential request or a batch credential request.
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used.
     * </p>
     *
     * @return
     *         The duration of transaction ID in seconds.
     *
     * @since 3.66
     * @since Authlete 3.0
     */
    public long getCredentialTransactionDuration()
    {
        return credentialTransactionDuration;
    }


    /**
     * Set the duration of transaction ID in seconds that may be issued as a
     * result of a credential request or a batch credential request.
     *
     * <p>
     * If the value of this property is 0 or negative, the default value per
     * Authlete server is used.
     * </p>
     *
     * @param duration
     *         The duration of transaction ID in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.66
     * @since Authlete 3.0
     */
    public Service setCredentialTransactionDuration(long duration)
    {
        this.credentialTransactionDuration = duration;

        return this;
    }


    /**
     * Get the default duration of verifiable credentials in seconds.
     *
     * <p>
     * Some Authlete APIs such as the {@code /vci/single/issue} API and the
     * {@code /vci/batch/issue} API may issue one or more verifiable
     * credentials. The value of this property specifies the default duration
     * of such verifiable credentials.
     * </p>
     *
     * <p>
     * The value 0 indicates that verifiable credentials will not expire.
     * In the case, verifiable credentials will not have a property that
     * indicates the expiration time. For example, JWT-based verifiable
     * credentials will not contain the "{@code exp}" claim (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7519.html">RFC 7519</a>, <a href=
     * "https://www.rfc-editor.org/rfc/rfc7519.html#section-4.1.4">Section
     * 4.1.4</a>).
     * </p>
     *
     * <p>
     * Authlete APIs that may issue verifiable credentials recognize a request
     * parameter that can override the duration. For example, a request to the
     * {@code /vci/single/issue} API ({@link CredentialSingleIssueRequest})
     * contains an "{@code order}" object ({@link CredentialIssuanceOrder})
     * that has a "{@code credentialDuration}" parameter
     * ({@link CredentialIssuanceOrder#getCredentialDuration() credentialDuration})
     * that can override the default duration.
     * </p>
     *
     * @return
     *         The default duration of verifiable credentials in seconds.
     *
     * @since 3.67
     * @since Authlete 3.0
     */
    public long getCredentialDuration()
    {
        return credentialDuration;
    }


    /**
     * Set the default duration of verifiable credentials in seconds.
     *
     * <p>
     * Some Authlete APIs such as the {@code /vci/single/issue} API and the
     * {@code /vci/batch/issue} API may issue one or more verifiable
     * credentials. The value of this property specifies the default duration
     * of such verifiable credentials.
     * </p>
     *
     * <p>
     * The value 0 indicates that verifiable credentials will not expire.
     * In the case, verifiable credentials will not have a property that
     * indicates the expiration time. For example, JWT-based verifiable
     * credentials will not contain the "{@code exp}" claim (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7519.html">RFC 7519</a>, <a href=
     * "https://www.rfc-editor.org/rfc/rfc7519.html#section-4.1.4">Section
     * 4.1.4</a>).
     * </p>
     *
     * <p>
     * Authlete APIs that may issue verifiable credentials recognize a request
     * parameter that can override the duration. For example, a request to the
     * {@code /vci/single/issue} API ({@link CredentialSingleIssueRequest})
     * contains an "{@code order}" object ({@link CredentialIssuanceOrder})
     * that has a "{@code credentialDuration}" parameter
     * ({@link CredentialIssuanceOrder#getCredentialDuration() credentialDuration})
     * that can override the default duration.
     * </p>
     *
     * @param duration
     *         The default duration of verifiable credentials in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.67
     * @since Authlete 3.0
     */
    public Service setCredentialDuration(long duration)
    {
        this.credentialDuration = duration;

        return this;
    }


    /**
     * Get the JWK Set document containing private keys that are used to sign
     * verifiable credentials.
     *
     * <p>
     * Some Authlete APIs such as the {@code /vci/single/issue} API and the
     * {@code /vci/batch/issue} API may issue one or more verifiable
     * credentials. The content of this property is referred to by such APIs.
     * </p>
     *
     * <p>
     * Authlete APIs that may issue verifiable credentials recognize a request
     * parameter that can specify the key ID of a private key that should be
     * used for signing. For example, a request to the {@code /vci/single/issue}
     * API ({@link CredentialSingleIssueRequest}) contains an "{@code order}"
     * object ({@link CredentialIssuanceOrder}) that has a "{@code signingKeyId}"
     * parameter ({@link CredentialIssuanceOrder#getSigningKeyId() signingKeyId})
     * that can specify the key ID of a private key to be used for signing.
     * When a key ID is not specified, Authlete will select a private key
     * automatically.
     * </p>
     *
     * <p>
     * If JWKs in the JWK Set do not contain the "{@code kid}" property (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7517.html">RFC 7517</a>, <a href=
     * "https://www.rfc-editor.org/rfc/rfc7517.html#section-4.5">Section 4.5</a>)
     * when this {@code credentialJwks} property is updated, Authlete will
     * automatically insert the "{@code kid}" property into such JWKs. The JWK
     * thumbprint (<a href="https://www.rfc-editor.org/rfc/rfc7638.html">RFC
     * 7638</a>) computed with the SHA-256 hash algorithm is used as the value
     * of the "{@code kid}" property.
     * </p>
     *
     * @return
     *         The JWK Set document containing private keys that are used to
     *         sign verifiable credentials.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     *      >RFC 7517 JSON Web Key (JWK)</a>
     *
     * @since 3.67
     * @since Authlete 3.0
     */
    public String getCredentialJwks()
    {
        return credentialJwks;
    }


    /**
     * Set the JWK Set document containing private keys that are used to sign
     * verifiable credentials.
     *
     * <p>
     * Some Authlete APIs such as the {@code /vci/single/issue} API and the
     * {@code /vci/batch/issue} API may issue one or more verifiable
     * credentials. The content of this property is referred to by such APIs.
     * </p>
     *
     * <p>
     * Authlete APIs that may issue verifiable credentials recognize a request
     * parameter that can specify the key ID of a private key that should be
     * used for signing. For example, a request to the {@code /vci/single/issue}
     * API ({@link CredentialSingleIssueRequest}) contains an "{@code order}"
     * object ({@link CredentialIssuanceOrder}) that has a "{@code signingKeyId}"
     * parameter ({@link CredentialIssuanceOrder#getSigningKeyId() signingKeyId})
     * that can specify the key ID of a private key to be used for signing.
     * When a key ID is not specified, Authlete will select a private key
     * automatically.
     * </p>
     *
     * <p>
     * If JWKs in the JWK Set do not contain the "{@code kid}" property (<a href=
     * "https://www.rfc-editor.org/rfc/rfc7517.html">RFC 7517</a>, <a href=
     * "https://www.rfc-editor.org/rfc/rfc7517.html#section-4.5">Section 4.5</a>)
     * when this {@code credentialJwks} property is updated, Authlete will
     * automatically insert the "{@code kid}" property into such JWKs. The JWK
     * thumbprint (<a href="https://www.rfc-editor.org/rfc/rfc7638.html">RFC
     * 7638</a>) computed with the SHA-256 hash algorithm is used as the value
     * of the "{@code kid}" property.
     * </p>
     *
     * @param jwks
     *         The JWK Set document containing private keys that are used to
     *         sign verifiable credentials.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html"
     *      >RFC 7517 JSON Web Key (JWK)</a>
     *
     * @since 3.67
     * @since Authlete 3.0
     */
    public Service setCredentialJwks(String jwks)
    {
        this.credentialJwks = jwks;

        return this;
    }


    /**
     * Get the URL at which the JWK Set document of the credential issuer is
     * exposed.
     *
     * <p>
     * The value of this property is referenced when Authlete's
     * {@code /vci/jwtissuer} API generates the JSON representing the JWT
     * issuer metadata. The JSON will be generated like below.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "issuer": "{@link Service#getCredentialIssuerMetadata()
     *              getCredentialIssuerMetadata()}.{@link CredentialIssuerMetadata#getCredentialIssuer()
     *              getCredentialIssuer()}",
     *   "jwks_uri": "{@link Service#getCredentialJwksUri() getCredentialJwksUri()}"
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         The URL at which the JWK Set document of the credential issuer
     *         is exposed.
     *
     * @since 3.79
     * @since Authlete 3.0
     *
     * @see com.authlete.common.api.AuthleteApi#credentialJwtIssuerMetadata(CredentialJwtIssuerMetadataRequest)
     */
    public URI getCredentialJwksUri()
    {
        return credentialJwksUri;
    }


    /**
     * Set the URL at which the JWK Set document of the credential issuer is
     * exposed.
     *
     * <p>
     * The value of this property is referenced when Authlete's
     * {@code /vci/jwtissuer} API generates the JSON representing the JWT
     * issuer metadata. The JSON will be generated like below.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "issuer": "{@link Service#getCredentialIssuerMetadata()
     *              getCredentialIssuerMetadata()}.{@link CredentialIssuerMetadata#getCredentialIssuer()
     *              getCredentialIssuer()}",
     *   "jwks_uri": "{@link Service#getCredentialJwksUri() getCredentialJwksUri()}"
     * }
     * </pre>
     * </blockquote>
     *
     * @param uri
     *         The URL at which the JWK Set document of the credential issuer
     *         is exposed.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.79
     * @since Authlete 3.0
     *
     * @see com.authlete.common.api.AuthleteApi#credentialJwtIssuerMetadata(CredentialJwtIssuerMetadataRequest)
     */
    public Service setCredentialJwksUri(URI uri)
    {
        this.credentialJwksUri = uri;

        return this;
    }


    /**
     * Get the flag indicating whether to enable the feature of ID token
     * reissuance in the refresh token flow.
     *
     * <p>
     * If this property is {@code true}, the {@code action} response parameter
     * in a response from the {@code /auth/token} API becomes
     * {@link TokenResponse.Action#ID_TOKEN_REISSUABLE ID_TOKEN_REISSUABLE}
     * when the following conditions are met.
     * </p>
     *
     * <ol>
     * <li>The flow of the token request is the refresh token flow.
     * <li>The scope set after processing the token request still contains the
     *     "{@code openid}" scope.
     * <li>The access token is associated with the subject of a user.
     * <li>The access token is associated with a client application.
     * </ol>
     *
     * <p>
     * See the description of the {@link TokenResponse} class for details.
     * </p>
     *
     * @return
     *         {@code true} if the feature of ID token reissuance in the
     *         refresh token flow is enabled. {@code false} if the feature
     *         is disabled.
     *
     * @since 3.68
     * @since Authlete 2.3.8
     * @since Authlete 3.0
     *
     * @see TokenResponse
     */
    public boolean isIdTokenReissuable()
    {
        return idTokenReissuable;
    }


    /**
     * Set the flag indicating whether to enable the feature of ID token
     * reissuance in the refresh token flow.
     *
     * <p>
     * If this property is {@code true}, the {@code action} response parameter
     * in a response from the {@code /auth/token} API becomes
     * {@link TokenResponse.Action#ID_TOKEN_REISSUABLE ID_TOKEN_REISSUABLE}
     * when the following conditions are met.
     * </p>
     *
     * <ol>
     * <li>The flow of the token request is the refresh token flow.
     * <li>The scope set after processing the token request still contains the
     *     "{@code openid}" scope.
     * <li>The access token is associated with the subject of a user.
     * <li>The access token is associated with a client application.
     * </ol>
     *
     * <p>
     * See the description of the {@link TokenResponse} class for details.
     * </p>
     *
     * @param reissuable
     *         {@code true} to enable the feature of ID token reissuance in
     *         the refresh token flow. {@code false} to disable the feature.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.68
     * @since Authlete 2.3.8
     * @since Authlete 3.0
     *
     * @see TokenResponse
     */
    public Service setIdTokenReissuable(boolean reissuable)
    {
        this.idTokenReissuable = reissuable;

        return this;
    }


    /**
     * Get the key ID of the key for signing introspection responses.
     *
     * @return
     *         The key ID of the key for signing introspection responses.
     *
     * @since 3.77
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public String getIntrospectionSignatureKeyId()
    {
        return introspectionSignatureKeyId;
    }


    /**
     * Set the key ID of the key for signing introspection responses.
     *
     * @param keyId
     *         The key ID of the key for signing introspection responses.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.77
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public Service setIntrospectionSignatureKeyId(String keyId)
    {
        this.introspectionSignatureKeyId = keyId;

        return this;
    }


    /**
     * Get the FAPI modes for this service.
     *
     * <p>
     * When the value of this property is not {@code null}, Authlete always processes
     * requests to this service based on the specified FAPI modes if the FAPI
     * feature is enabled in Authlete and the {@link ServiceProfile#FAPI FAPI}
     * profile is supported by this service.
     * </p>
     * <p>
     * For instance, when this property is set to an array containing {@link
     * FapiMode#FAPI1_ADVANCED FAPI1_ADVANCED} only, Authlete always processes
     * requests to this service based on "<a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>" if the
     * FAPI feature is enabled in Authlete and the {@link ServiceProfile#FAPI FAPI}
     * profile is supported by this service.
     * </p>
     *
     * @return
     *         The FAPI modes for this service.
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
     * Set the FAPI modes for this service.
     *
     * <p>
     * When the value of this property is not {@code null}, Authlete always processes
     * requests to this service based on the specified FAPI modes if the FAPI
     * feature is enabled in Authlete and the {@link ServiceProfile#FAPI FAPI}
     * profile is supported by this service.
     * </p>
     * <p>
     * For instance, when this property is set to an array containing {@link
     * FapiMode#FAPI1_ADVANCED FAPI1_ADVANCED} only, Authlete always processes
     * requests to this service based on "<a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>" if the
     * FAPI feature is enabled in Authlete and the {@link ServiceProfile#FAPI FAPI}
     * profile is supported by this service.
     * </p>
     *
     * @param modes
     *         The FAPI modes for this service.
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
    public Service setFapiModes(FapiMode[] modes)
    {
        this.fapiModes = modes;

        return this;
    }

    /**
     * Get the flag indicating whether to require DPoP proof JWTs to include
     * the {@code nonce} claim whenever they are presented.
     *
     * <p>
     * DPoP-aware Authlete APIs such as the {@code /auth/introspection} API and
     * the {@code /auth/token} API recognize the {@code dpopNonceRequired}
     * request parameter, which enables API callers to require DPoP proof JWTs
     * to include the {@code nonce} claim at runtime, even if this service
     * property is false.
     * </p>
     *
     * @return
     *         {@code true} if DPoP proof JWTs are required to include the
     *         {@code nonce} claim whenever they are presented.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public boolean isDpopNonceRequired()
    {
        return dpopNonceRequired;
    }


    /**
     * Set the flag indicating whether to require DPoP proof JWTs to include
     * the {@code nonce} claim whenever they are presented.
     *
     * <p>
     * DPoP-aware Authlete APIs such as the {@code /auth/introspection} API and
     * the {@code /auth/token} API recognize the {@code dpopNonceRequired}
     * request parameter, which enables API callers to require DPoP proof JWTs
     * to include the {@code nonce} claim at runtime, even if this service
     * property is false.
     * </p>
     *
     * @param required
     *         {@code true} to require DPoP proof JWTs to include the
     *         {@code nonce} claim whenever they are presented.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public Service setDpopNonceRequired(boolean required)
    {
        this.dpopNonceRequired = required;

        return this;
    }


    /**
     * Get the duration of nonce values for DPoP proof JWTs in seconds.
     *
     * @return
     *         The duration of nonce values for DPoP proof JWTs in seconds.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public long getDpopNonceDuration()
    {
        return dpopNonceDuration;
    }


    /**
     * Set the duration of nonce values for DPoP proof JWTs in seconds.
     *
     * @param duration
     *         The duration of nonce values for DPoP proof JWTs in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public Service setDpopNonceDuration(long duration)
    {
        this.dpopNonceDuration = duration;

        return this;
    }


    /**
     * Get the URI of the endpoint that receives token batch results.
     *
     * @return
     *         The URI of the endpoint that receives token batch results.
     *
     * @since 3.96
     * @since Authlete 3.0
     */
    public URI getTokenBatchNotificationEndpoint()
    {
        return tokenBatchNotificationEndpoint;
    }


    /**
     * Set the URI of the endpoint that receives token batch results.
     *
     * @param endpoint
     *         The URI of the endpoint that receives token batch results.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.96
     * @since Authlete 3.0
     */
    public Service setTokenBatchNotificationEndpoint(URI endpoint)
    {
        this.tokenBatchNotificationEndpoint = endpoint;

        return this;
    }


    /**
     * Get the flag to determine to restrict the {@code aud} claim value in client
     * assertions to the issuer of this service as a string.
     *
     * @return
     *         {@code true} if the {@code aud} claim value in client assertions
     *         is restricted to the issuer of this service as a string.
     *
     * @since 4.14
     * @since Authlete 3.0
     */
    public boolean isClientAssertionAudRestrictedToIssuer()
    {
        return clientAssertionAudRestrictedToIssuer;
    }


    /**
     * Set the flag to determine whether to restrict the {@code aud} claim value
     * in client assertions to the issuer of this service as a string.
     *
     * @param restricted
     *         {@code true} to restrict the {@code aud} claim value in client assertions
     *         to the issuer of this service as a string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.14
     * @since Authlete 3.0
     */
    public Service setClientAssertionAudRestrictedToIssuer(boolean restricted)
    {
        this.clientAssertionAudRestrictedToIssuer = restricted;

        return this;
    }


    /**
     * Get the flag to determine to support the "<a href=
     * "https://openid.net/specs/openid-connect-native-sso-1_0.html">OpenID
     * Connect Native SSO for Mobile Apps 1&#x2E;0</a>" specification (a.k.a.
     * "Native SSO").
     *
     * <p>
     * If this property is not set to {@code true}, Native SSO-specific
     * parameters will be ignored or treated as errors. For example, the
     * {@code device_sso} scope will have no special meaning (Authlete will
     * not embed the {@code sid} claim in the ID token), and the
     * <code>urn:<wbr>openid:<wbr>params:<wbr>token-type:<wbr>device-secret</code>
     * token type will be treated as unknown, resulting in an error.
     * </p>
     *
     * <p>
     * This property corresponds to the {@code native_sso_supported} server
     * metadata. If this property is set to {@code true},
     * {@code "native_sso_supported":true} will appear in the server metadata
     * document (see <a href=
     * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata"
     * >OpenID Connect Discovery 1.0, Section 3. OpenID Provider Metadata</a>;
     * <a href="https://www.rfc-editor.org/rfc/rfc8414.html#section-2">RFC 8414:
     * OAuth 2.0 Authorization Server Metadata, Section 2. Authorization Server
     * Metadata</a>).
     * </p>
     *
     * <p>
     * The Native SSO specification is supported in Authlete 3.0 and later.
     * </p>
     *
     * @return
     *         {@code true} if the Native SSO specification is supported.
     *
     * @since 4.18
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
     *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
     */
    public boolean isNativeSsoSupported()
    {
        return nativeSsoSupported;
    }


    /**
     * Set the flag to determine to support the "<a href=
     * "https://openid.net/specs/openid-connect-native-sso-1_0.html">OpenID
     * Connect Native SSO for Mobile Apps 1&#x2E;0</a>" specification (a.k.a.
     * "Native SSO").
     *
     * <p>
     * If this property is not set to {@code true}, Native SSO-specific
     * parameters will be ignored or treated as errors. For example, the
     * {@code device_sso} scope will have no special meaning (Authlete will
     * not embed the {@code sid} claim in the ID token), and the
     * <code>urn:<wbr>openid:<wbr>params:<wbr>token-type:<wbr>device-secret</code>
     * token type will be treated as unknown, resulting in an error.
     * </p>
     *
     * <p>
     * This property corresponds to the {@code native_sso_supported} server
     * metadata. If this property is set to {@code true},
     * {@code "native_sso_supported":true} will appear in the server metadata
     * document (see <a href=
     * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata"
     * >OpenID Connect Discovery 1.0, Section 3. OpenID Provider Metadata</a>;
     * <a href="https://www.rfc-editor.org/rfc/rfc8414.html#section-2">RFC 8414:
     * OAuth 2.0 Authorization Server Metadata, Section 2. Authorization Server
     * Metadata</a>).
     * </p>
     *
     * <p>
     * The Native SSO specification is supported in Authlete 3.0 and later.
     * </p>
     *
     * @param supported
     *         {@code true} to support the Native SSO specification..
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.18
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
     *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
     */
    public Service setNativeSsoSupported(boolean supported)
    {
        this.nativeSsoSupported = supported;

        return this;
    }


    /**
     * Get the version of the OpenID for Verifiable Credential Issuance
     * specification to support.
     *
     * <p>
     * Valid values are as follows:
     * </p>
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Specification</th>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td rowspan="2">
     *       <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0-ID1.html">
     *       OpenID for Verifiable Credential Issuance 1.0, Implementer's Draft 1</a>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>{@code "1.0-ID1"}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "1.0"}</td>
     *     <td rowspan="2">
     *       <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html">
     *       OpenID for Verifiable Credential Issuance 1.0, Final</a>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>{@code "1.0-Final"}</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * @return
     *         The version of the OpenID for Verifiable Credential Issuance
     *         specification to support.
     *
     * @since 4.25
     * @since Authlete 3.0.22
     *
     * @see <a href="https://www.authlete.com/developers/oid4vci/">
     *      OpenID for Verifiable Credential Issuance</a>
     */
    public String getOid4vciVersion()
    {
        return oid4vciVersion;
    }


    /**
     * Set the version of the OpenID for Verifiable Credential Issuance
     * specification to support.
     *
     * <p>
     * Valid values are as follows:
     * </p>
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Specification</th>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td rowspan="2">
     *       <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0-ID1.html">
     *       OpenID for Verifiable Credential Issuance 1.0, Implementer's Draft 1</a>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>{@code "1.0-ID1"}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "1.0"}</td>
     *     <td rowspan="2">
     *       <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html">
     *       OpenID for Verifiable Credential Issuance 1.0, Final</a>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>{@code "1.0-Final"}</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * @param version
     *         The version of the OpenID for Verifiable Credential Issuance
     *         specification to support.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.25
     * @since Authlete 3.0.22
     *
     * @see <a href="https://www.authlete.com/developers/oid4vci/">
     *      OpenID for Verifiable Credential Issuance</a>
     */
    public Service setOid4vciVersion(String version)
    {
        this.oid4vciVersion = version;

        return this;
    }


    /**
     * Get the flag to determine to support <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >OAuth Client ID Metadata Document</a>.
     *
     * <p>
     * This flag corresponds to the {@code client_id_metadata_document_supported}
     * metadata parameter.
     * </p>
     *
     * @return
     *         {@code true} if OAuth Client ID Metadata Document is supported.
     *
     * @since 4.29
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public boolean isClientIdMetadataDocumentSupported()
    {
        return clientIdMetadataDocumentSupported;
    }


    /**
     * Set the flag to determine to support <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >OAuth Client ID Metadata Document</a>.
     *
     * <p>
     * This flag corresponds to the {@code client_id_metadata_document_supported}
     * metadata parameter.
     * </p>
     *
     * @param supported
     *         {@code true} to support OAuth Client ID Metadata Document.
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
    public Service setClientIdMetadataDocumentSupported(boolean supported)
    {
        this.clientIdMetadataDocumentSupported = supported;

        return this;
    }


    /**
     * Get the flag that indicates whether the allowlist for client IDs
     * in the <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> context is enabled or not.
     *
     * <p>
     * If the allowlist is enabled, the client ID in a request must match at
     * least one entry in the allowlist to be considered a valid client ID
     * in the CIMD context.
     * </p>
     *
     * @return
     *         {@code true} if the allowlist for client IDs in the CIMD
     *         context is enabled.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public boolean isCimdAllowlistEnabled()
    {
        return cimdAllowlistEnabled;
    }


    /**
     * Set the flag that indicates whether the allowlist for client IDs
     * in the <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> context is enabled or not.
     *
     * <p>
     * If the allowlist is enabled, the client ID in a request must match at
     * least one entry in the allowlist to be considered a valid client ID
     * in the CIMD context.
     * </p>
     *
     * @param enabled
     *         {@code true} to enable the allowlist for client IDs in the
     *         CIMD context.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public Service setCimdAllowlistEnabled(boolean enabled)
    {
        this.cimdAllowlistEnabled = enabled;

        return this;
    }


    /**
     * Get the allowlist for client IDs in the <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> context.
     *
     * <p>
     * When the allowlist feature is enabled (see {@link #isCimdAllowlistEnabled()}),
     * the allowlist specified by this property is consulted to determine
     * whether the client ID in a request is valid in the CIMD context.
     * In this case, the client ID must match at least one entry in the
     * allowlist.
     * </p>
     *
     * <p>
     * Each entry in the allowlist must be a valid URI. The comparison between
     * an allowlist entry and a client ID is performed as follows:
     * </p>
     *
     * <ol>
     * <li>[scheme] Simple string comparison.
     * <li>[authority] Simple string comparison.
     * <li>[path] The client ID's path must contain all path segments of
     *     the allowlist entry in the same order.
     * <li>[query] Simple string comparison, but only if the allowlist
     *     entry has a query component.
     * </ol>
     *
     * <p>
     * For example, if the allowlist contains "{@code https://example.com/a/b}",
     * then "{@code https://example.com/a/b/c}" is considered valid, but
     * "{@code https://example.com/a}" is not.
     * </p>
     *
     * @return
     *         The allowlist for client IDs in the CIMD context.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public String[] getCimdAllowlist()
    {
        return cimdAllowlist;
    }


    /**
     * Set the allowlist for client IDs in the <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> context.
     *
     * <p>
     * When the allowlist feature is enabled (see {@link #isCimdAllowlistEnabled()}),
     * the allowlist specified by this property is consulted to determine
     * whether the client ID in a request is valid in the CIMD context.
     * In this case, the client ID must match at least one entry in the
     * allowlist.
     * </p>
     *
     * <p>
     * Each entry in the allowlist must be a valid URI. The comparison between
     * an allowlist entry and a client ID is performed as follows:
     * </p>
     *
     * <ol>
     * <li>[scheme] Simple string comparison.
     * <li>[authority] Simple string comparison.
     * <li>[path] The client ID's path must contain all path segments of
     *     the allowlist entry in the same order.
     * <li>[query] Simple string comparison, but only if the allowlist
     *     entry has a query component.
     * </ol>
     *
     * <p>
     * For example, if the allowlist contains "{@code https://example.com/a/b}",
     * then "{@code https://example.com/a/b/c}" is considered valid, but
     * "{@code https://example.com/a}" is not.
     * </p>
     *
     * @param allowlist
     *         The allowlist for client IDs in the CIMD context.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public Service setCimdAllowlist(String[] allowlist)
    {
        this.cimdAllowlist = allowlist;

        return this;
    }


    /**
     * Get the flag that indicates whether Authlete should always fetch the
     * client metadata from the location specified by the client ID (when
     * <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> applies), regardless of whether a cached copy already exists
     * and has not yet expired.
     *
     * <p>
     * Under normal circumstances, client metadata retrieved from the location
     * referenced by the client ID is stored in the database with an expiration
     * time calculated using HTTP caching mechanisms (see <a href=
     * "https://www.rfc-editor.org/rfc/rfc9111.html">RFC 9111 HTTP Caching</a>).
     * Until that expiration time is reached, Authlete does not attempt to
     * retrieve the client metadata again.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, Authlete retrieves the client
     * metadata regardless of the cache's validity.
     * </p>
     *
     * <p>
     * Some Authlete APIs accept a {@code cimdOptions.alwaysRetrieved} request
     * parameter (see {@link CimdOptions}). If the parameter is provided and
     * its value is {@code true}, it takes precedence over this service
     * configuration.
     * </p>
     *
     * <p>
     * This flag is effective only when the service supports CIMD (see {@link
     * Service#isClientIdMetadataDocumentSupported()}) and CIMD is actually
     * used to resolve client metadata. For example, if the client ID in a
     * request does not appear to be a valid URI, CIMD will not be used even
     * if the service is configured to support it. In such cases, this flag
     * has no effect.
     * </p>
     *
     * <p>
     * Client metadata retrieval is performed only in the initiating request
     * of an authorization flow, and not in any subsequent requests. For
     * example, in the authorization code flow, metadata may be retrieved
     * during the authorization request, but not during the subsequent token
     * request. In contrast, in the client credentials flow, metadata retrieval
     * may occur because the token request itself is the initiating request
     * in the flow.
     * </p>
     *
     * @return
     *         {@code true} if Authlete attempts to retrieve client metadata
     *         regardless of the cache's validity.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public boolean isCimdAlwaysRetrieved()
    {
        return cimdAlwaysRetrieved;
    }


    /**
     * Set the flag that indicates whether Authlete should always fetch the
     * client metadata from the location specified by the client ID (when
     * <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> applies), regardless of whether a cached copy already exists
     * and has not yet expired.
     *
     * <p>
     * Under normal circumstances, client metadata retrieved from the location
     * referenced by the client ID is stored in the database with an expiration
     * time calculated using HTTP caching mechanisms (see <a href=
     * "https://www.rfc-editor.org/rfc/rfc9111.html">RFC 9111 HTTP Caching</a>).
     * Until that expiration time is reached, Authlete does not attempt to
     * retrieve the client metadata again.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, Authlete retrieves the client
     * metadata regardless of the cache's validity.
     * </p>
     *
     * <p>
     * Some Authlete APIs accept a {@code cimdOptions.alwaysRetrieved} request
     * parameter (see {@link CimdOptions}). If the parameter is provided and
     * its value is {@code true}, it takes precedence over this service
     * configuration.
     * </p>
     *
     * <p>
     * This flag is effective only when the service supports CIMD (see {@link
     * Service#isClientIdMetadataDocumentSupported()}) and CIMD is actually
     * used to resolve client metadata. For example, if the client ID in a
     * request does not appear to be a valid URI, CIMD will not be used even
     * if the service is configured to support it. In such cases, this flag
     * has no effect.
     * </p>
     *
     * <p>
     * Client metadata retrieval is performed only in the initiating request
     * of an authorization flow, and not in any subsequent requests. For
     * example, in the authorization code flow, metadata may be retrieved
     * during the authorization request, but not during the subsequent token
     * request. In contrast, in the client credentials flow, metadata retrieval
     * may occur because the token request itself is the initiating request
     * in the flow.
     * </p>
     *
     * @param always
     *         {@code true} to instruct Authlete to retrieve client metadata
     *         regardless of the cache's validity.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public Service setCimdAlwaysRetrieved(boolean always)
    {
        this.cimdAlwaysRetrieved = always;

        return this;
    }


    /**
     * Get the flag that indicates whether the {@code http} scheme in the client
     * ID is permitted (when <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> applies).
     *
     * <p>
     * The specification requires the {@code https} scheme, but if this flag is
     * set to {@code true}, Authlete also allows the {@code http} scheme. The
     * main purpose of this option is to make development easier for developers
     * who run CIMD-enabled servers and a web server publishing client metadata
     * on their local machines without TLS.
     * </p>
     *
     * <p>
     * Given this purpose, it is not recommended to enable this option in
     * production environments unless an allowlist is used (see {@link
     * Service#isCimdAllowlistEnabled()}).
     * </p>
     *
     * <p>
     * Some Authlete APIs accept a {@code cimdOptions.httpPermitted} request
     * parameter (see {@link CimdOptions}). If the parameter is provided and
     * its value is {@code true}, it takes precedence over this service
     * configuration.
     * </p>
     *
     * @return
     *         {@code true} if the {@code http} scheme in the client ID is
     *         permitted.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public boolean isCimdHttpPermitted()
    {
        return cimdHttpPermitted;
    }


    /**
     * Set the flag that indicates whether the {@code http} scheme in the client
     * ID is permitted (when <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> applies).
     *
     * <p>
     * The specification requires the {@code https} scheme, but if this flag is
     * set to {@code true}, Authlete also allows the {@code http} scheme. The
     * main purpose of this option is to make development easier for developers
     * who run CIMD-enabled servers and a web server publishing client metadata
     * on their local machines without TLS.
     * </p>
     *
     * <p>
     * Given this purpose, it is not recommended to enable this option in
     * production environments unless an allowlist is used (see {@link
     * Service#isCimdAllowlistEnabled()}).
     * </p>
     *
     * <p>
     * Some Authlete APIs accept a {@code cimdOptions.httpPermitted} request
     * parameter (see {@link CimdOptions}). If the parameter is provided and
     * its value is {@code true}, it takes precedence over this service
     * configuration.
     * </p>
     *
     * @param permitted
     *         {@code true} to permit the {@code http} scheme in the client ID.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public Service setCimdHttpPermitted(boolean permitted)
    {
        this.cimdHttpPermitted = permitted;

        return this;
    }


    /**
     * Get the flag that indicates whether a query component in the client ID
     * is permitted (when <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> applies).
     *
     * <p>
     * Although the specification states that a client ID <i>"SHOULD NOT
     * include a query string component,"</i> it does technically allow it.
     * However, query components are prone to misuse. Therefore, Authlete does
     * not allow them by default. Setting this flag to {@code true} relaxes
     * that restriction.
     * </p>
     *
     * <p>
     * Some Authlete APIs accept a {@code cimdOptions.queryPermitted} request
     * parameter (see {@link CimdOptions}). If the parameter is provided and
     * its value is {@code true}, it takes precedence over this service
     * configuration.
     * </p>
     *
     * @return
     *         {@code true} if a query component in the client ID is permitted.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public boolean isCimdQueryPermitted()
    {
        return cimdQueryPermitted;
    }


    /**
     * Set the flag that indicates whether a query component in the client ID
     * is permitted (when <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a> applies).
     *
     * <p>
     * Although the specification states that a client ID <i>"SHOULD NOT
     * include a query string component,"</i> it does technically allow it.
     * However, query components are prone to misuse. Therefore, Authlete does
     * not allow them by default. Setting this flag to {@code true} relaxes
     * that restriction.
     * </p>
     *
     * <p>
     * Some Authlete APIs accept a {@code cimdOptions.queryPermitted} request
     * parameter (see {@link CimdOptions}). If the parameter is provided and
     * its value is {@code true}, it takes precedence over this service
     * configuration.
     * </p>
     *
     * @param permitted
     *         {@code true} to permit a query component in the client ID.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.30
     * @since Authlete 3.0.22
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public Service setCimdQueryPermitted(boolean permitted)
    {
        this.cimdQueryPermitted = permitted;

        return this;
    }


    /**
     * Get the flag that indicates whether to prohibit client ID aliases that
     * start with {@code https://} or {@code http://}.
     *
     * <p>
     * The primary purpose of this flag is to prevent the use of client ID
     * aliases that may conflict with entity IDs in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID Federation
     * 1.0</a> or metadata document locations in <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a>.
     * </p>
     *
     * <p>
     * For backward compatibility, the default value of this flag is set to
     * {@code false}, but it is recommended to set it to {@code true} whenever
     * possible.
     * </p>
     *
     * @return
     *         {@code true} if client ID aliases that start with {@code https://}
     *         or {@code http://} are prohibited.
     *
     * @since 4.32
     * @since Authlete 3.0.22
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html">
     *      OpenID Federation 1.0</a>
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public boolean isHttpAliasProhibited()
    {
        return httpAliasProhibited;
    }


    /**
     * Set the flag that indicates whether to prohibit client ID aliases that
     * start with {@code https://} or {@code http://}.
     *
     * <p>
     * The primary purpose of this flag is to prevent the use of client ID
     * aliases that may conflict with entity IDs in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID Federation
     * 1.0</a> or metadata document locations in <a href=
     * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >CIMD</a>.
     * </p>
     *
     * <p>
     * For backward compatibility, the default value of this flag is set to
     * {@code false}, but it is recommended to set it to {@code true} whenever
     * possible.
     * </p>
     *
     * @param prohibited
     *         {@code true} to prohibit client ID aliases that start with
     *         {@code https://} or {@code http://}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.32
     * @since Authlete 3.0.22
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html">
     *      OpenID Federation 1.0</a>
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    public Service setHttpAliasProhibited(boolean prohibited)
    {
        this.httpAliasProhibited = prohibited;

        return this;
    }
}
