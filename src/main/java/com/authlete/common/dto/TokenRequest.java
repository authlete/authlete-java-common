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


import java.io.Serializable;
import java.util.Map;
import com.authlete.common.web.URLCoder;


/**
 * Request to Authlete's {@code /auth/token} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * OAuth 2.0 token request parameters which are the request parameters
 * that the OAuth 2.0 token endpoint of the service implementation
 * received from the client application.
 * </p>
 * <p>
 * The value of {@code "parameters"} is the entire entity body (which
 * is formatted in {@code application/x-www-form-urlencoded}) of the
 * request from the client application.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientId</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client ID extracted from {@code Authorization} header of
 * the token request from the client application.
 * </p>
 * <p>
 * If the token endpoint of the service implementation supports
 * Basic Authentication as a means of
 * <a href="http://tools.ietf.org/html/rfc6749#section-2.3">client
 * authentication</a>, and the request from the client application
 * contained its client ID in {@code Authorization} header, the
 * value should be extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientSecret</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client secret extracted from {@code Authorization} header
 * of the token request from the client application.
 * </p>
 * <p>
 * If the token endpoint of the service implementation supports
 * Basic Authentication as a means of
 * <a href="http://tools.ietf.org/html/rfc6749#section-2.3">client
 * authentication</a>, and the request from the client application
 * contained its client secret in {@code Authorization} header, the
 * value should be extracted and set to this parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate from the MTLS of the token request from
 * the client application.
 * See <a href="https://tools.ietf.org/html/rfc8705">RFC 8705</a>
 * (OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access
 * Tokens) for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificatePath</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The certificate path presented by the client during client authentication.
 * The certificates are strings in PEM format.
 * </p>
 * </dd>
 *
 * <dt><b><code>properties</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Extra properties to associate with an access token. Note that
 * {@code properties} parameter is accepted only when Content-Type
 * of the request is application/json, so don't use
 * application/x-www-form-urlencoded if you want to specify
 * {@code properties}.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpop</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code DPoP} HTTP header.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htm</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The HTTP method of the token request. In normal cases, the value should be
 * {@code "POST"}. If omitted, {@code "POST"} is used as the default value.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the token endpoint. If omitted, the {@code tokenEndpoint} property
 * of {@link Service} is used as the default value.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>jwtAtClaims</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * Additional claims in JSON object format that are added to the payload part
 * of the JWT access token. See the description of {@link #getJwtAtClaims()}
 * for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>accessToken</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The representation of an access token that may be issued as a result of the
 * Authlete API call. See {@link #getAccessToken()} for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>accessTokenDuration</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The duration of the access token that may be issued as a result of the
 * Authlete API call. See {@link #getAccessTokenDuration()} for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpopNonceRequired</code></b> (OPTIONAL; Authlete 3.0 onwards)</dt>
 * <dd>
 * <p>
 * The flag indicating whether to require the DPoP proof JWT to include
 * the {@code nonce} claim. Even if the service's {@code dpopNonceRequired}
 * property is false, calling the {@code /auth/token} API with this
 * {@code dpopNonceRequired} parameter true will force the Authlete API to
 * check whether the DPoP proof JWT includes the expected nonce value.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * <p>
 * An entity body of a token request may contain the client ID
 * ({@code client_id}) and the client secret ({@code client_secret})
 * along with other request parameters as described in
 * <a href="http://tools.ietf.org/html/rfc6749#section-2.3.1"
 * >RFC 6749, 2.3.1. Client Password</a>. If the client credentials
 * are contained in both {@code Authorization} header and the entity
 * body, they must be identical. Otherwise, Authlete's {@code
 * /auth/token} API generates an error (it's not a service error but
 * a client error).
 * </p>
 */
public class TokenRequest implements Serializable
{
    private static final long serialVersionUID = 10L;


    /**
     * OAuth 2.0 token request parameters.
     */
    private String parameters;


    /**
     * Client ID.
     */
    private String clientId;


    /**
     * Client secret.
     */
    private String clientSecret;


    /**
     * Client certificate (used in MTLS auth and bound access tokens).
     */
    private String clientCertificate;


    /**
     * Client certificate path (used in PKI-based MTLS auth when
     * certificates are validated by the Authlete service).
     */
    private String[] clientCertificatePath;


    /**
     * Extra properties to associate with an access token.
     */
    private Property[] properties;


    /**
     * DPoP Header
     */
    private String dpop;


    /**
     * HTTP Method (for DPoP validation).
     */
    private String htm;


    /**
     * HTTP URL base (for DPoP validation).
     */
    private String htu;


    /**
     * Additional claims that are added to the payload part of the JWT
     * access token.
     *
     * @since 3.35
     * @since Authlete 2.3
     */
    private String jwtAtClaims;


    /**
     * The representation of an access token that may be issued as a
     * result of the Authlete API call.
     *
     * @since 3.24
     * @since Authlete 2.2.27
     */
    private String accessToken;


    /**
     * The duration of the access token that may be issued as a result
     * of the Authlete API call.
     *
     * @since 3.64
     * @since Authlete 2.2.41
     * @since Authlete 2.3.5
     * @since Authlete 3.0
     */
    private long accessTokenDuration;


    /**
     * Whether to check if the DPoP proof JWT includes the expected nonce value.
     *
     * @since 3.82
     * @since Authlete 3.0
     */
    private boolean dpopNonceRequired;


    /**
     * Get the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 token endpoint of the service
     * implementation received from the client application.
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 token endpoint of the service
     * implementation received from the client application.
     */
    public TokenRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Set the value of {@code parameters} which are the request
     * parameters that the OAuth 2.0 token endpoint of the service
     * implementation received from the client application.
     *
     * <p>
     * This method converts the given map into a string in {@code
     * x-www-form-urlencoded} and passes it to {@link
     * #setParameters(String)} method.
     * </p>
     *
     * @param parameters
     *         Request parameters.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.24
     */
    public TokenRequest setParameters(Map<String, String[]> parameters)
    {
        return setParameters(URLCoder.formUrlEncode(parameters));
    }


    /**
     * Get the client ID extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public String getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public TokenRequest setClientId(String clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client secret extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public String getClientSecret()
    {
        return clientSecret;
    }


    /**
     * Set the client secret extracted from {@code Authorization}
     * header of the token request from the client application.
     */
    public TokenRequest setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;

        return this;
    }


    /**
     * Get the client certificate from the MTLS of the token
     * request from the client application.
     *
     * @since 2.12
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate from the MTLS of the token
     * request from the client application.
     *
     * @since 2.12
     */
    public TokenRequest setClientCertificate(String clientCertificate)
    {
        this.clientCertificate = clientCertificate;

        return this;
    }


    /**
     * Get the extra properties to associate with an access token which
     * may be issued by this request.
     *
     * @return
     *         Extra properties.
     *
     * @since 1.30
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set extra properties to associate with an access token which may
     * be issued by this request.
     *
     * <p>
     * If the value of {@code grant_type} parameter contained in the token
     * request from the client application is {@code authorization_code},
     * properties set by this method will be added as the extra properties
     * of a newly created access token. The extra properties specified when
     * the authorization code was issued (using {@link
     * AuthorizationIssueRequest#setProperties(Property[])}) will also be
     * used, but their values will be overwritten if the extra properties
     * set by this method have the same keys. In other words, extra
     * properties contained in this request will be merged into existing
     * extra properties which are associated with the authorization code.
     * </p>
     *
     * <p>
     * Otherwise, if the value of {@code grant_type} parameter contained
     * in the token request from the client application is {@code
     * refresh_token}, properties set by this method will be added to the
     * existing extra properties of the corresponding access token. Extra
     * properties having the same keys will be overwritten in the same
     * manner as the case of {@code grant_type=authorization_code}.
     * </p>
     *
     * <p>
     * Otherwise, if the value of {@code grant_type} parameter contained
     * in the token request from the client application is {@code
     * client_credentials}, properties set by this method will be used
     * simply as extra properties of a newly created access token.
     * Because <a href="https://tools.ietf.org/html/rfc6749#section-4.4"
     * >Client Credentials flow</a> does not have a preceding authorization
     * request, merging extra properties will not be performed. This is
     * different from the cases of {@code grant_type=authorization_code}
     * and {@code grant_type=refresh_token}.
     * </p>
     *
     * <p>
     * In other cases ({@code grant_type=password}), properties set by
     * this method will not be used. When you want to associate extra
     * properties with an access token which is issued using <a href=
     * "https://tools.ietf.org/html/rfc6749#section-4.3">Resource Owner
     * Password Credentials flow</a>, use {@link
     * TokenIssueRequest#setProperties(Property[])} method instead.
     * </p>
     *
     * <p>
     * The argument {@code properties} is an array of properties. Each
     * property must be a pair of a string key and a string value.
     * That is, each property must be a string array of size 2. The key
     * must not be {@code null} or an empty string, but the value may be.
     * </p>
     *
     * <p>
     * Keys of extra properties will be used as labels of top-level
     * entries in a JSON response containing an access token which is
     * returned from an authorization server. An example is
     * {@code example_parameter}, which you can find in <a href=
     * "https://tools.ietf.org/html/rfc6749#section-5.1">5.1. Successful
     * Response</a> in RFC 6749. The following code snippet is an example
     * to set one extra property having {@code example_parameter} as its
     * key and {@code example_value} as its value.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {@link Property}[] properties = { new {@link Property#Property(String, String)
     * Property}("example_parameter", "example_value") };
     * request.{@link #setProperties(Property[]) setProperties}(properties);
     * </pre>
     * </blockquote>
     *
     * <p>
     * Keys listed below should not be used and they would be ignored on
     * the server side even if they were used. It's because they are reserved
     * in <a href="https://tools.ietf.org/html/rfc6749">RFC 6749</a> and
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html"
     * >OpenID Connect Core 1.0</a>.
     * </p>
     *
     * <ul>
     *   <li>{@code access_token}
     *   <li>{@code token_type}
     *   <li>{@code expires_in}
     *   <li>{@code refresh_token}
     *   <li>{@code scope}
     *   <li>{@code error}
     *   <li>{@code error_description}
     *   <li>{@code error_uri}
     *   <li>{@code id_token}
     * </ul>
     *
     * <p>
     * Note that <b>there is an upper limit on the total size of extra properties</b>.
     * On the server side, the properties will be (1) converted to a multidimensional
     * string array, (2) converted to JSON, (3) encrypted by AES/CBC/PKCS5Padding, (4)
     * encoded by base64url, and then stored into the database. The length of the
     * resultant string must not exceed 65,535 in bytes. This is the upper limit, but
     * we think it is big enough.
     * </p>
     *
     * @param properties
     *         Extra properties.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.30
     */
    public TokenRequest setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the certificate path presented by the client during
     * client authentication. These certificates are strings in
     * PEM format.
     *
     * @return
     *         The client certificate path as an array of strings
     *         in PEM format.
     *
     * @since 2.15
     */
    public String[] getClientCertificatePath()
    {
        return clientCertificatePath;
    }


    /**
     * Set the certificate path presented by the client during
     * client authentication. These certificates are strings in
     * PEM format.
     *
     * @param clientCertificatePath
     *            The client certificate path as an array of strings
     *            in PEM format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.15
     */
    public TokenRequest setClientCertificatePath(String[] clientCertificatePath)
    {
        this.clientCertificatePath = clientCertificatePath;

        return this;
    }


    /**
     * Get the {@code DPoP} header presented by the client during the request
     * to the token endpoint. The header contains a signed JWT which includes
     * the public key that is paired with the private key used to sign the JWT.
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The {@code DPoP} header string.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getDpop()
    {
        return dpop;
    }


    /**
     * Set the {@code DPoP} header presented by the client during the request
     * to the token endpoint. The header contains a signed JWT which includes
     * the public key that is paired with the private key used to sign the JWT.
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param dpop
     *         The {@code DPoP} header string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public TokenRequest setDpop(String dpop)
    {
        this.dpop = dpop;

        return this;
    }


    /**
     * Get the HTTP method of the token request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is {@code "POST"}. When this parameter
     * is omitted, {@code "POST"} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The HTTP method as a string. For example, {@code "POST"}.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtm()
    {
        return htm;
    }


    /**
     * Set the HTTP method of the token request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is {@code "POST"}. When this parameter
     * is omitted, {@code "POST"} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param htm
     *         The HTTP method as a string. For example, {@code "POST"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public TokenRequest setHtm(String htm)
    {
        this.htm = htm;

        return this;
    }


    /**
     * Get the URL of the token endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code tokenEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The URL of the token endpoint.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtu()
    {
        return htu;
    }


    /**
     * Set the URL of the token endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code tokenEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param htu
     *         The URL of the token endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public TokenRequest setHtu(String htu)
    {
        this.htu = htu;

        return this;
    }


    /**
     * Get the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This request parameter has a meaning only when the format of access
     * tokens issued by this service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * <p>
     * Note that this {@code jwtAtClaims} request parameter to the {@code
     * /auth/token} API works only in the client credentials flow (<a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749</a> <a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html#section-4.4">Section
     * 4.4</a>). For other flows, additional claims need to be passed to
     * Authlete through other APIs. For example, to embed additional claims
     * in an access token that is issued by the authorization code flow
     * (<a href="https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749</a>
     * <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.1"
     * >Section 4.1</a>), the {@code jwtAtClaims} request parameter of the
     * {@code /auth/authorization/issue} API has to be used.
     * </p>
     *
     * @return
     *         Additional claims that are added to the payload part of the
     *         JWT access token.
     *
     * @since 3.35
     * @since Authlete 2.3
     */
    public String getJwtAtClaims()
    {
        return jwtAtClaims;
    }


    /**
     * Set the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This request parameter has a meaning only when the format of access
     * tokens issued by this service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * <p>
     * Note that this {@code jwtAtClaims} request parameter to the {@code
     * /auth/token} API works only in the client credentials flow (<a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749</a> <a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html#section-4.4">Section
     * 4.4</a>). For other flows, additional claims need to be passed to
     * Authlete through other APIs. For example, to embed additional claims
     * in an access token that is issued by the authorization code flow
     * (<a href="https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749</a>
     * <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.1"
     * >Section 4.1</a>), the {@code jwtAtClaims} request parameter of the
     * {@code /auth/authorization/issue} API has to be used.
     * </p>
     *
     * @param claims
     *         Additional claims that are added to the payload part of the
     *         JWT access token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.35
     * @since Authlete 2.3
     */
    public TokenRequest setJwtAtClaims(String claims)
    {
        this.jwtAtClaims = claims;

        return this;
    }


    /**
     * Get the representation of an access token that may be issued as a
     * result of the Authlete API call.
     *
     * <p>
     * Basically, it is the Authlete server's role to generate an access token.
     * However, some systems may have inflexible restrictions on the format of
     * access tokens. Such systems may use this {@code accessToken} request
     * parameter to specify the representation of an access token by themselves
     * instead of leaving the access token generation task to the Authlete server.
     * </p>
     *
     * <p>
     * Usually, the Authlete server (1) generates a random 256-bit value, (2)
     * base64url-encodes the value into a 43-character string, and (3) uses the
     * resultant string as the representation of an access token. The Authlete
     * implementation is written on the assumption that the 256-bit entropy is
     * big enough. Therefore, <b>make sure that the entropy of the value of the
     * {@code accessToken} request parameter is big enough, too.</b>
     * </p>
     *
     * </p>
     * The entropy does not necessarily have to be equal to or greater than 256
     * bits. For example, 192-bit random values (which will become 32-character
     * strings when encoded by base64url) may be enough. However, note that if
     * the entropy is too low, access token string values will collide and
     * Authlete API calls will fail.
     * </p>
     *
     * <p>
     * When no access token is generated as a result of the Authlete API call,
     * this {@code accessToken} request parameter is not used.
     * </p>
     *
     * @return
     *         The representation of an access token that may be issued as a
     *         result of the Authlete API call.
     *
     * @since 3.24
     * @since Authlete 2.2.27
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the representation of an access token that may be issued as a
     * result of the Authlete API call.
     *
     * <p>
     * Basically, it is the Authlete server's role to generate an access token.
     * However, some systems may have inflexible restrictions on the format of
     * access tokens. Such systems may use this {@code accessToken} request
     * parameter to specify the representation of an access token by themselves
     * instead of leaving the access token generation task to the Authlete server.
     * </p>
     *
     * <p>
     * Usually, the Authlete server (1) generates a random 256-bit value, (2)
     * base64url-encodes the value into a 43-character string, and (3) uses the
     * resultant string as the representation of an access token. The Authlete
     * implementation is written on the assumption that the 256-bit entropy is
     * big enough. Therefore, <b>make sure that the entropy of the value of the
     * {@code accessToken} request parameter is big enough, too.</b>
     * </p>
     *
     * </p>
     * The entropy does not necessarily have to be equal to or greater than 256
     * bits. For example, 192-bit random values (which will become 32-character
     * strings when encoded by base64url) may be enough. However, note that if
     * the entropy is too low, access token string values will collide and
     * Authlete API calls will fail.
     * </p>
     *
     * <p>
     * When no access token is generated as a result of the Authlete API call,
     * this {@code accessToken} request parameter is not used.
     * </p>
     *
     * @param accessToken
     *         The representation of an access token that may be issued as a
     *         result of the Authlete API call.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.24
     * @since Authlete 2.2.27
     */
    public TokenRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the duration of the access token that may be issued as a result of
     * the Authlete API call.
     *
     * <p>
     * When this request parameter holds a positive integer, it is used as the
     * duration of the access token. In other cases, this request parameter is
     * ignored.
     * </p>
     *
     * @return
     *         The duration of the access token in seconds.
     *
     * @since 3.64
     * @since Authlete 2.2.41
     * @since Authlete 2.3.5
     * @since Authlete 3.0
     */
    public long getAccessTokenDuration()
    {
        return accessTokenDuration;
    }


    /**
     * Set the duration of the access token that may be issued as a result of
     * the Authlete API call.
     *
     * <p>
     * When this request parameter holds a positive integer, it is used as the
     * duration of the access token. In other cases, this request parameter is
     * ignored.
     * </p>
     *
     * @param duration
     *         The duration of the access token in seconds.
     *
     * @return
     *         {@code this} request parameter.
     *
     * @since 3.64
     * @since Authlete 2.2.41
     * @since Authlete 2.3.5
     * @since Authlete 3.0
     */
    public TokenRequest setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;

        return this;
    }


    /**
     * Get the flag indicating whether to check if the DPoP proof JWT includes
     * the expected {@code nonce} value.
     *
     * <p>
     * If this request parameter is {@code true} or if the service's
     * {@code dpopNonceRequired} property ({@link Service#isDpopNonceRequired()})
     * is {@code true}, the {@code /auth/token} API checks if the DPoP proof
     * JWT includes the expected {@code nonce} value. In this case, the response
     * from the {@code /auth/token} API will include the {@code dpopNonce}
     * response parameter, which should be used as the value of the
     * {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @return
     *         {@code true} if the {@code /auth/token} API checks whether the
     *         DPoP proof JWT includes the expected {@code nonce} value, even
     *         if the service's {@code dpopNonceRequired} property is false.
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
     * Set the flag indicating whether to check if the DPoP proof JWT includes
     * the expected {@code nonce} value.
     *
     * <p>
     * If this request parameter is {@code true} or if the service's
     * {@code dpopNonceRequired} property ({@link Service#isDpopNonceRequired()})
     * is {@code true}, the {@code /auth/token} API checks if the DPoP proof
     * JWT includes the expected {@code nonce} value. In this case, the response
     * from the {@code /auth/token} API will include the {@code dpopNonce}
     * response parameter, which should be used as the value of the
     * {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @param required
     *         {@code true} to have the {@code /auth/token} API check whether
     *         the DPoP proof JWT includes the expected {@code nonce} value,
     *         even if the service's {@code dpopNonceRequired} property is false.
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
    public TokenRequest setDpopNonceRequired(boolean required)
    {
        this.dpopNonceRequired = required;

        return this;
    }
}
