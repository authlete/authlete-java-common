/*
 * Copyright (C) 2017 Authlete, Inc.
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
 * Request to Authlete's <code>/api/client/authorization/delete/<i>{clientId}</i></code> API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>subject</code></b></dt>
 * <dd>
 * <p>
 * The subject (= unique identifier) of the end-user who has granted authorization
 * to the client application.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.1
 */
public class ClientAuthorizationDeleteRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String subject;


    /**
     * The default constructor.
     *
     * <p>
     * Because the {@code subject} parameter is mandatory for
     * <code>/api/client/authorization/delete/<i>{clientId}</i></code> API,
     * a non-null value should be set using {@link #setSubject(String)}
     * method later.
     * </p>
     */
    public ClientAuthorizationDeleteRequest()
    {
    }


    /**
     * A constructor with a subject value.
     *
     * @param subject
     *         The subject (= unique identifier) of the end-user.
     */
    public ClientAuthorizationDeleteRequest(String subject)
    {
        this.subject = subject;
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
    public ClientAuthorizationDeleteRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }
}
