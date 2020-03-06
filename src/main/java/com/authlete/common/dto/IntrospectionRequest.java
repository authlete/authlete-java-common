/*
 * Copyright (C) 2014-2020 Authlete, Inc.
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
 * The certificate presented by the client, used to validate TLS
 * client certificate bound access tokens.
 * See <a href="https://tools.ietf.org/html/rfc8705">RFC 8705</a>
 * (OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access
 * Tokens) for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpop</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code DPoP} HTTP header.
 * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the Application
 * Layer (DPoP)"</i> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htm</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The HTTP method of the request to the protected resource endpoint.
 * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the Application
 * Layer (DPoP)"</i> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the protected resource endpoint.
 * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the Application
 * Layer (DPoP)"</i> for details.
 * </p>
 * </dd>
 * </dl>
 *
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 */
public class IntrospectionRequest implements Serializable
{
    private static final long serialVersionUID = 3L;


    private String token;
    private String[] scopes;
    private String subject;
    private String clientCertificate;

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
     * Get the access token.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token which has been issued by Authlete.
     */
    public IntrospectionRequest setToken(String token)
    {
        this.token = token;

        return this;
    }


    /**
     * Get the scopes which are required to access the target
     * protected resource.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes which are required to access the target
     * protected resource. If the array contains a scope which
     * is not covered by the access token, Authlete's
     * {@code /auth/introspection} API returns {@code FORBIDDEN}
     * as the action and {@code insufficent_scope} as the error
     * code.
     *
     * @param scopes
     *         Scopes required to access the target protected
     *         resource. If {@code null} is given, Authlete's
     *         {@code /auth/introspection} endpoint does not
     *         perform scope checking.
     */
    public IntrospectionRequest setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the subject (= end-user ID managed by the service
     * implementation) which is required to access the target
     * protected resource.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= end-user ID managed by the service
     * implementation) which is required to access the target
     * protected resource. If the specified subject is different
     * from the one associated with the access token, Authlete's
     * {@code /auth/introspection} API returns {@code FORBIDDEN}
     * as the action and {@code invalid_request} as the error
     * code.
     *
     * @param subject
     *         Subject (= end-user ID managed by the service
     *         implementation) which is required to access the
     *         protected resource. If {@code null} is given,
     *         Authlete's {@code /auth/introspection} endpoint
     *         does not perform subject checking.
     */
    public IntrospectionRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the client certificate, used to validate binding against
     * access tokens using the TLS client certificate confirmation method.
     *
     * @return
     *         The certificate in PEM format.
     *
     * @since 2.14
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate, used to validate binding against
     * access tokens using the TLS client certificate confirmation method.
     *
     * @param clientCertificate
     *         The certificate in PEM format.
     *
     * @since 2.14
     */
    public IntrospectionRequest setClientCertificate(String clientCertificate)
    {
        this.clientCertificate = clientCertificate;

        return this;
    }


    /**
     * Get the {@code DPoP} header presented by the client during the request
     * to the resource server. This header contains a signed JWT which
     * includes the public key used to sign it.
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @return
     *         The {@code DPoP} header string.
     *
     * @since 2.70
     */
    public String getDpop()
    {
        return dpop;
    }


    /**
     * Set the {@code DPoP} header presented by the client during the request
     * to the resource server. This header contains a signed JWT which
     * includes the public key used to sign it.
     *
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @param dpop
     *         The {@code DPoP} header string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
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
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @return
     *         The HTTP method as a string. For example, {@code "GET"}.
     *
     * @since 2.70
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
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @param htm
     *         The HTTP method as a string. For example, {@code "GET"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
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
     * <p>
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @return
     *         The URL of the protected resource endpoint.
     *
     * @since 2.70
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
     * See <i>"OAuth 2.0 Demonstration of Proof-of-Possession at the
     * Application Layer (DPoP)"</i> for details.
     * </p>
     *
     * @param htu
     *         The URL of the protected resource endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     */
    public IntrospectionRequest setHtu(String htu)
    {
        this.htu = htu;

        return this;
    }
}
