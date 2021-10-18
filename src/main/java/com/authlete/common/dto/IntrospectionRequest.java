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
 * See <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
 * >OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer (DPoP)</a>
 * for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htm</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The HTTP method of the request to the protected resource endpoint.
 * See <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
 * >OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer (DPoP)</a>
 * for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the protected resource endpoint.
 * See <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
 * >OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer (DPoP)</a>
 * for details.
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
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
 *      >OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer (DPoP)</a>
 */
public class IntrospectionRequest implements Serializable
{
    private static final long serialVersionUID = 4L;


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
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
     *      >OAuth 2.0 Demonstrating Proof-of-Possession at the Application Layer (DPoP)</a>
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
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
     *      >OAuth 2.0 Demonstrating Proof-of-Possession at the Application Layer (DPoP)</a>
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
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
     *      >OAuth 2.0 Demonstrating Proof-of-Possession at the Application Layer (DPoP)</a>
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
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
     *      >OAuth 2.0 Demonstrating Proof-of-Possession at the Application Layer (DPoP)</a>
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
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
     *      >OAuth 2.0 Demonstrating Proof-of-Possession at the Application Layer (DPoP)</a>
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
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
     *      >OAuth 2.0 Demonstrating Proof-of-Possession at the Application Layer (DPoP)</a>
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
}
