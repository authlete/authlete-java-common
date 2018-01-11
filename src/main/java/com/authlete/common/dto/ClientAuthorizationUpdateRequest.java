/*
 * Copyright (C) 2016-2017 Authlete, Inc.
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
 * Request to Authlete's <code>/client/authorization/update/<i>{clientId}</i></code> API.
 *
 * <p>
 * The API is used to update existing access tokens issued to a client
 * application by a user.
 * </p>
 *
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>subject</code></b></dt>
 * <dd>
 * <p>
 * The subject (= identifier) of the end-user who has granted authorization
 * to the client application.
 * </p>
 * </dd>
 *
 * <dt><b><code>scopes</code></b></dt>
 * <dd>
 * <p>
 * A new set of scopes assigned to existing access tokens. Scopes that are
 * not supported by the service and those that the client application
 * is not allowed to request are ignored on the server side. If the
 * {@code scopes} request parameter is not included in a request or its
 * value is {@code null}, the scopes of the access token are not changed.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.37
 */
public class ClientAuthorizationUpdateRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String subject;
    private String[] scopes;


    /**
     * The default constructor.
     *
     * <p>
     * Because the {@code subject} parameter is mandatory for
     * <code>/api/client/authorization/update/<i>{clientId}</i></code> API,
     * a non-null value should be set using {@link #setSubject(String)}
     * method later.
     * </p>
     */
    public ClientAuthorizationUpdateRequest()
    {
    }


    /**
     * A constructor with a subject and scopes.
     *
     * @param subject
     *         The subject (= unique identifier) of the end-user.
     *
     * @param scopes
     *         The new value of scopes that is set to existing access tokens.
     *         {@code null} means that scopes are not changed.
     *
     * @since 2.1
     */
    public ClientAuthorizationUpdateRequest(String subject, String[] scopes)
    {
        this.subject = subject;
        this.scopes  = scopes;
    }


    /**
     * Get the subject (= unique identifier) of the end-user who has granted
     * authorization to the client application.
     *
     * @return
     *         The subject (= unique identifier) of the end-user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the end-user who has granted
     * authorization to the client application.
     *
     * @param subject
     *         The subject (= unique identifier) of the end-user.
     *
     * @return
     *         {@code this} object.
     */
    public ClientAuthorizationUpdateRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the new set of scopes assigned to existing access tokens.
     *
     * @return
     *         The new set of scopes.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set a new set of scopes assigned to existing access tokens.
     *
     * <p>
     * If {@code null} is given, the scope set associated with existing access tokens
     * is not changed.
     * </p>
     *
     * @param scopes
     *         A new set of scopes. {@code null} means that scopes are not changed.
     *
     * @return
     *         {@code this} object.
     */
    public ClientAuthorizationUpdateRequest setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }
}
