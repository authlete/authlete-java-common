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
 * </dl>
 * 
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The certificate presented by the client, used to validate MTLS 
 * sender constrained access tokens. 
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
    private static final long serialVersionUID = 2L;


    private String token;
    private String[] scopes;
    private String subject;
    private String clientCertificate;


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
     * access tokens using the MTLS sender confirmation method.
     * 
     * @return the certificate in PEM format
     * 
     * @since 2.14
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate, used to validate binding against
     * access tokens using the MTLS sender confirmation method.
     * 
     * @param clientCertificate the certificate in PEM format
     * 
     * @since 2.14
     */
    public IntrospectionRequest setClientCertificate(String clientCertificate)
    {
        this.clientCertificate = clientCertificate;
        
        return this;
    }
}
