/*
 * Copyright (C) 2014-2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
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
 * Request to Authlete's {@code /auth/introspection} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>token</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * An access token to introspect.
 * </p>
 * </dd>
 *
 * <dt><b><code>scopes</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Scopes that should be covered by the access token.
 * </p>
 * </dd>
 *
 * <dt><b><code>subject</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The subject that should be associated with the access token.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate used in the mutual TLS connection established
 * between the client application and the protected resource endpoint.
 * See <a href="https://www.rfc-editor.org/rfc/rfc8705.html">RFC 8705
 * OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access
 * Tokens</a> for details.
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
 * The HTTP method of the request to the protected resource endpoint.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the protected resource endpoint.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>resources</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Resource indicators that should be covered by the access token.
 * See <a href="https://www.rfc-editor.org/rfc/rfc8707.html">RFC 8707
 * Resource Indicators for OAuth 2.0</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>uri</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The full URL of the resource server.
 * </p>
 * </dd>
 *
 * <dt><b><code>headers</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The HTTP headers to be included in processing the signature. If this is
 * a signed request, this must include the {@code Signature} and
 * {@code Signature-Input} headers, as well as any additional headers
 * covered by the signature.
 * </p>
 * </dd>
 *
 * <dt><b><code>message</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The HTTP message body of the request, if present. If supplied,
 * this is used to validate the value of the {@code Content-Digest} header,
 * which must in turn be covered in the HTTP Message Signature.
 * </p>
 * </dd>
 *
 * <dt><b><code>requiredComponents</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The list of component identifiers required to be covered by
 * the signature on this message. If this is omitted, the set defaults to
 * including the {@code @method} and {@code @target-uri} derived components
 * as well as all headers in the {@link #getHeaders()} array.
 * </p>
 * </dd>
 *
 * <dt><b><code>acrValues</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The list of Authentication Context Class Reference values one of which
 * the user authentication performed during the course of issuing the access
 * token must satisfy.
 * </p>
 * </dd>
 *
 * <dt><b><code>maxAge</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The maximum authentication age which is the maximum allowable elapsed time
 * since the user authentication was performed during the course of issuing the
 * access token.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpopNonceRequired</code></b> (OPTIONAL; Authlete 3.0 onwards)</dt>
 * <dd>
 * <p>
 * The flag indicating whether to require the DPoP proof JWT to include the
 * {@code nonce} claim. Even if the service's {@code dpopNonceRequired} property
 * is false, calling the {@code /auth/introspection} API with this
 * {@code dpopNonceRequired} parameter true will force the Authlete API to check
 * whether the DPoP proof JWT includes the expected nonce value.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6750.html"
 *      >RFC 6750 The OAuth 2.0 Authorization Framework: Bearer Token Usage</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8705.html"
 *      >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
 *      >RFC 8707 Resource Indicators for OAuth 2.0</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
 *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc9470.html"
 *      >RFC 9470 OAuth 2.0 Step Up Authentication Challenge Protocol</a>
 */
public class IntrospectionRequest implements Serializable
{
    private static final long serialVersionUID = 7L;


    /**
     * Access token to introspect.
     */
    private String token;


    /**
     * Required scopes for access to the protected resource endpoint.
     */
    private String[] scopes;


    /**
     * Expected identifier of resource owner.
     */
    private String subject;


    /**
     * Client certificate used in the mutual TLS connection between
     * the client application and the protected resource endpoint.
     */
    private String clientCertificate;


    /**
     * DPoP Header.
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
     * Resource indicators.
     */
    private URI[] resources;


    /**
     * The full URL of the resource server.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    private String uri;


    /**
     * The HTTP message body of the request, if present.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    private String message;


    /**
     * HTTP headers to be included in processing the signature. If this is
     * a signed request, this must include the Signature and Signature-Input
     * headers, as well as any additional headers covered by the signature.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    private Pair[] headers;


    /**
     * HTTP Message Components required to be in the signature. If absent,
     * defaults to "@method", "@target-uri", and appropriate headers such as
     * "authorization" and "dpop".
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    private String[] requiredComponents;


    /**
     * Authentication Context Class Reference values one of which the
     * user authentication performed during the course of issuing the
     * access token must satisfy.
     *
     * @since 3.40
     * @since Authlete 2.3
     */
    private String[] acrValues;


    /**
     * The maximum authentication age which is the maximum allowable
     * elapsed time since the user authentication was performed during
     * the course of issuing the access token.
     *
     * @since 3.40
     * @since Authlete 2.3
     */
    private int maxAge;


    /**
     * Whether to check if the DPoP proof JWT includes the expected nonce value.
     *
     * @since 3.82
     * @since Authlete 3.0
     */
    private boolean dpopNonceRequired;


    /**
     * Get the access token to introspect.
     *
     * @return
     *         The access token.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token to introspect.
     *
     * @param token
     *         The access token.
     *
     * @return
     *         {@code this} object.
     */
    public IntrospectionRequest setToken(String token)
    {
        this.token = token;

        return this;
    }


    /**
     * Get the scopes which are required to access the protected resource
     * endpoint.
     *
     * @return
     *         Required scopes.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes which are required to access the protected resource
     * endpoint.
     *
     * <p>
     * If the array contains a scope which is not covered by the access token,
     * Authlete's {@code /auth/introspection} API returns {@code FORBIDDEN} as
     * the action and {@code insufficent_scope} as the error code.
     * </p>
     *
     * @param scopes
     *         Scopes required to access the protected resource endpoint.
     *         If {@code null} is given, the {@code /auth/introspection}
     *         API does not perform scope checking.
     *
     * @return
     *         {@code this} object.
     */
    public IntrospectionRequest setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the subject (= end-user ID managed by the service implementation)
     * which is required to access the protected resource endpoint.
     *
     * @return
     *         Expected identifier of resource owner.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= end-user ID managed by the service implementation)
     * which is required to access the protected resource endpoint.
     *
     * <p>
     * If the specified subject is different from the one associated with the
     * access token, Authlete's {@code /auth/introspection} API returns
     * {@code FORBIDDEN} as the action and {@code invalid_request} as the error
     * code.
     * </p>
     *
     * @param subject
     *         Subject (= end-user ID managed by the service implementation)
     *         which is required to access the protected resource endpoint.
     *         If {@code null} is given, the {@code /auth/introspection} API
     *         does not perform subject checking.
     *
     * @return
     *         {@code this} object.
     */
    public IntrospectionRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the client certificate used in the mutual TLS connection established
     * between the client application and the protected resource endpoint.
     *
     * @return
     *         The client certificate in PEM format.
     *
     * @since 2.14
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8705.html"
     *      >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the mutual TLS connection established
     * between the client application and the protected resource endpoint.
     *
     * <p>
     * If the access token is bound to a client certificate, this parameter is
     * used for validation.
     * </p>
     *
     * @param clientCertificate
     *         The client certificate in PEM format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.14
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8705.html"
     *      >RFC 8705 OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     */
    public IntrospectionRequest setClientCertificate(String clientCertificate)
    {
        this.clientCertificate = clientCertificate;

        return this;
    }


    /**
     * Get the {@code DPoP} header presented by the client during the request
     * to the resource server. This header contains a signed JWT which
     * includes the public key that is paired with the private key used to
     * sign it.
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
     * to the resource server. This header contains a signed JWT which
     * includes the public key that is paired with the private key used to
     * sign it.
     *
     * <p>
     * If the access token is bound to a public key via DPoP, this parameter
     * is used for validation.
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
    public IntrospectionRequest setDpop(String dpop)
    {
        this.dpop = dpop;

        return this;
    }


    /**
     * Get the HTTP method of the request from the client to the protected
     * resource endpoint. This field is used to validate the {@code DPoP}
     * header.
     *
     * @return
     *         The HTTP method as a string. For example, {@code "GET"}.
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
     * Set the HTTP method of the request from the client to the protected
     * resource endpoint. This field is used to validate the {@code DPoP}
     * header.
     *
     * <p>
     * If the access token is bound to a public key via DPoP, this parameter
     * is used for validation.
     * </p>
     *
     * @param htm
     *         The HTTP method as a string. For example, {@code "GET"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public IntrospectionRequest setHtm(String htm)
    {
        this.htm = htm;

        return this;
    }


    /**
     * Get the URL of the protected resource endpoint. This field is used
     * to validate the {@code DPoP} header.
     *
     * @return
     *         The URL of the protected resource endpoint.
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
     * Set the URL of the protected resource endpoint. This field is used
     * to validate the {@code DPoP} header.
     *
     * <p>
     * If the access token is bound to a public key via DPoP, this parameter
     * is used for validation.
     * </p>
     *
     * @param htu
     *         The URL of the protected resource endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public IntrospectionRequest setHtu(String htu)
    {
        this.htu = htu;

        return this;
    }


    /**
     * Get the resource indicators that the access token should cover.
     *
     * @return
     *         The resource indicators.
     *
     * @since 3.1
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
     *      >RFC 8707 Resource Indicators for OAuth 2.0</a>
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Set the resource indicators that the access token should cover.
     *
     * @param resources
     *         The resource indicators that the access token should cover to
     *         access the protected resource endpoint. If {@code null} is
     *         given, the {@code /auth/introspection} API does not perform
     *         resource indicator checking.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.3
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8707.html"
     *      >RFC 8707 Resource Indicators for OAuth 2.0</a>
     */
    public IntrospectionRequest setResources(URI[] resources)
    {
        this.resources = resources;

        return this;
    }


    /**
     * Get the URL of the resource server. This field is used to validate
     * the HTTP Message Signature.
     *
     * @return
     *         The URL of the resource server.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public String getUri()
    {
        return uri;
    }


    /**
     * Set the URL of the resource server. This field is used to validate
     * the HTTP Message Signature.
     *
     * @param uri
     *         The URL of the resource server.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public IntrospectionRequest setUri(String uri)
    {
        this.uri = uri;

        return this;
    }


    /**
     * Get the HTTP message body, if present. If provided, this will be used to calculate
     * the expected value of the {@code Content-Digest} in the headers of the request
     * covered by the HTTP Message Signature.
     *
     * @return
     *         The HTTP message body.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public String getMessage()
    {
        return message;
    }


    /**
     * Set the HTTP message body, if present. If provided, this will be used to calculate
     * the expected value of the {@code Content-Digest} in the headers of the request
     * covered by the HTTP Message Signature.
     *
     * @param message
     *         The HTTP message body.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public IntrospectionRequest setMessage(String message)
    {
        this.message = message;

        return this;
    }


    /**
     * Get the HTTP headers to be included in processing the signature. If this is
     * a signed request, this must include the {@code Signature} and
     * {@code Signature-Input} headers, as well as any additional headers
     * covered by the signature.
     *
     * @return
     *         The HTTP headers.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public Pair[] getHeaders()
    {
        return headers;
    }


    /**
     * Set the HTTP headers to be included in processing the signature. If this is
     * a signed request, this must include the {@code Signature} and
     * {@code Signature-Input} headers, as well as any additional headers
     * covered by the signature.
     *
     * @param headers
     *         The HTTP headers.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public IntrospectionRequest setHeaders(Pair[] headers)
    {
        this.headers = headers;

        return this;
    }


    /**
     * Get the list of component identifiers required to be covered by
     * the signature on this message. If this is omitted, the set defaults to
     * including the {@code @method} and {@code @target-uri} derived components
     * as well the {@code Authorization} header and, if present,
     * the {@code DPoP} header.
     *
     * @return
     *         The component identifiers to cover in the signature.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public String[] getRequiredComponents()
    {
        return requiredComponents;
    }


    /**
     * Set the list of component identifiers required to be covered by
     * the signature on this message. If this is omitted, the set defaults to
     * including the {@code @method} and {@code @target-uri} derived components
     * as well the {@code Authorization} header and, if present,
     * the {@code DPoP} header.
     *
     * @param requiredComponents
     *         The component identifiers to cover in the signature.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public IntrospectionRequest setRequiredComponents(String[] requiredComponents)
    {
        this.requiredComponents = requiredComponents;

        return this;
    }


    /**
     * Get the list of Authentication Context Class Reference values one of
     * which the user authentication performed during the course of issuing
     * the access token must satisfy.
     *
     * @return
     *         The list of Authentication Context Class Reference values.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9470.html"
     *      >RFC 9470 OAuth 2.0 Step Up Authentication Challenge Protocol</a>
     */
    public String[] getAcrValues()
    {
        return acrValues;
    }


    /**
     * Set the list of Authentication Context Class Reference values one of
     * which the user authentication performed during the course of issuing
     * the access token must satisfy.
     *
     * @param acrValues
     *         The list of Authentication Context Class Reference values.
     *         If {@code null} is given, the {@code /auth/introspection} API
     *         does not perform ACR checking.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9470.html"
     *      >RFC 9470 OAuth 2.0 Step Up Authentication Challenge Protocol</a>
     */
    public IntrospectionRequest setAcrValues(String[] acrValues)
    {
        this.acrValues = acrValues;

        return this;
    }


    /**
     * Get the maximum authentication age which is the maximum allowable
     * elapsed time since the user authentication was performed during
     * the course of issuing the access token.
     *
     * @return
     *         The maximum authentication age in seconds.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9470.html"
     *      >RFC 9470 OAuth 2.0 Step Up Authentication Challenge Protocol</a>
     */
    public int getMaxAge()
    {
        return maxAge;
    }


    /**
     * Set the maximum authentication age which is the maximum allowable
     * elapsed time since the user authentication was performed during
     * the course of issuing the access token.
     *
     * @param maxAge
     *         The maximum authentication age in seconds. If 0 or a negative
     *         value is given, the {@code /auth/introspection} API does not
     *         perform max age checking.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9470.html"
     *      >RFC 9470 OAuth 2.0 Step Up Authentication Challenge Protocol</a>
     */
    public IntrospectionRequest setMaxAge(int maxAge)
    {
        this.maxAge = maxAge;

        return this;
    }


    /**
     * Get the flag indicating whether to check if the DPoP proof JWT includes
     * the expected {@code nonce} value.
     *
     * <p>
     * If this request parameter is {@code true} or if the service's
     * {@code dpopNonceRequired} property ({@link Service#isDpopNonceRequired()})
     * is {@code true}, the {@code /auth/introspection} API checks if the DPoP
     * proof JWT includes the expected {@code nonce} value. In this case, the
     * response from the {@code /auth/introspection} API will include the
     * {@code dpopNonce} response parameter, which should be used as the value
     * of the {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @return
     *         {@code true} if the {@code /auth/introspection} API checks
     *         whether the DPoP proof JWT includes the expected {@code nonce}
     *         value, even if the service's {@code dpopNonceRequired} property
     *         is false.
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
     * is {@code true}, the {@code /auth/introspection} API checks if the DPoP
     * proof JWT includes the expected {@code nonce} value. In this case, the
     * response from the {@code /auth/introspection} API will include the
     * {@code dpopNonce} response parameter, which should be used as the value
     * of the {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @param required
     *         {@code true} to have the {@code /auth/introspection} API check
     *         whether the DPoP proof JWT includes the expected {@code nonce}
     *         value, even if the service's {@code dpopNonceRequired} property
     *         is false.
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
    public IntrospectionRequest setDpopNonceRequired(boolean required)
    {
        this.dpopNonceRequired = required;

        return this;
    }
}
