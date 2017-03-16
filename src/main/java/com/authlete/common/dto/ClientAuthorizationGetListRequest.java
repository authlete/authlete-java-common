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
 * Request to Authlete's <code>/api/client/authorization/get/list</code> API.
 *
 * <p>
 * The API returns a list of client applications to which an end-user
 * has given authorization.
 * </p>
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
 *
 * <dt><b><code>start</code></b></dt>
 * <dd>
 * <p>
 * Start index of search results (inclusive). The default value is
 * {@link #DEFAULT_START} (= 0).
 * </p>
 * </dd>
 *
 * <dt><b><code>end</code></b></dt>
 * <dd>
 * <p>
 * End index of search results (exclusive). The default value is
 * {@link #DEFAULT_END} (= 5).
 * </p>
 * </dd>
 *
 * <dt><b><code>developer</code></b></dt>
 * <dd>
 * <p>
 * Unique developer ID. The default value is
 * {@link #DEFAULT_DEVELOPER} (= {@code null}).
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.1
 */
public class ClientAuthorizationGetListRequest implements Serializable
{
    /**
     * The default value of {@code developer} (= {@code null}).
     */
    public static final String DEFAULT_DEVELOPER = null;


    /**
     * The default value of {@code start} (= {@code 0}).
     */
    public static final int DEFAULT_START = 0;


    /**
     * The default value of {@code end} (= {@code 5}).
     */
    public static final int DEFAULT_END = 5;


    private static final long serialVersionUID = 1L;


    private String subject;
    private String developer;
    private int start;
    private int end;


    /**
     * The default constructor. This constructor is an alias of
     * {@link #ClientAuthorizationGetListRequest(String, String, int, int)
     * this}<code>(null, {@link #DEFAULT_DEVELOPER}, {@link #DEFAULT_START},
     * {@link #DEFAULT_END})</code>.
     *
     * Because the {@code subject} parameter is mandatory for
     * <code>/api/client/authorization/get/list</code> API, a non-null
     * value should be set using {@link #setSubject(String)} method later.
     */
    public ClientAuthorizationGetListRequest()
    {
        this(null, DEFAULT_DEVELOPER, DEFAULT_START, DEFAULT_END);
    }


    /**
     * A constructor with a subject value. This constructor is an alias of
     * {@link #ClientAuthorizationGetListRequest(String, String, int, int)
     * this}<code>(subject, {@link #DEFAULT_DEVELOPER}, {@link #DEFAULT_START},
     * {@link #DEFAULT_END})</code>.
     *
     * @param subject
     *         Unique identifier of an end-user.
     */
    public ClientAuthorizationGetListRequest(String subject)
    {
        this(subject, DEFAULT_DEVELOPER, DEFAULT_START, DEFAULT_END);
    }


    /**
     * A constructor with a subject value and a developer value. This constructor
     * is an alias of {@link #ClientAuthorizationGetListRequest(String, String, int, int)
     * this}<code>(subject, developer, {@link #DEFAULT_START}, {@link #DEFAULT_END})</code>.
     *
     * @param subject
     *         Unique identifier of an end-user.
     *
     * @param developer
     *         Unique identifier of a developer. If a non-null value is given,
     *         client applications which do not belong to the developer won't be
     *         included in a response from the API.
     */
    public ClientAuthorizationGetListRequest(String subject, String developer)
    {
        this(subject, developer, DEFAULT_START, DEFAULT_END);
    }


    /**
     * A constructor with a subject value, a start index and an end index of
     * search results. This constructor is an alias of {@link
     * #ClientAuthorizationGetListRequest(String, String, int, int)
     * this}<code>(subject, {@link #DEFAULT_DEVELOPER}, start, end)</code>.
     *
     * @param subject
     *         Unique identifier of an end-user.
     *
     * @param start
     *         Start index of search results (inclusive).
     *
     * @param end
     *         End index of search results (exclusive).
     */
    public ClientAuthorizationGetListRequest(String subject, int start, int end)
    {
        this(subject, DEFAULT_DEVELOPER, start, end);
    }


    /**
     * A constructor with all request parameters.
     *
     * @param subject
     *         Unique identifier of an end-user.
     *
     * @param developer
     *         Unique identifier of a developer. If a non-null value is given,
     *         client applications which do not belong to the developer won't be
     *         included in a response from the API.
     *
     * @param start
     *         Start index of search results (inclusive).
     *
     * @param end
     *         End index of search results (exclusive).
     */
    public ClientAuthorizationGetListRequest(String subject, String developer, int start, int end)
    {
        this.subject   = subject;
        this.developer = developer;
        this.start     = start;
        this.end       = end;
    }


    /**
     * Get the subject (= unique identifier) of the end-user.
     *
     * @return
     *         The subject (= unique identifier) of the end-user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the end-user.
     *
     * @param subject
     *         The subject of the end-user.
     *
     * @return
     *         {@code this} object.
     */
    public ClientAuthorizationGetListRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the developer of client applications.
     *
     * @return
     *         The developer of client applications. This may be {@code null}.
     */
    public String getDeveloper()
    {
        return developer;
    }


    /**
     * Set the developer of client applications.
     *
     * @param developer
     *         The developer of client applications. If a non-null value is given,
     *         client applications which do not belong to the developer won't be
     *         included in a response from the API.
     *
     * @return
     *         {@code this} object.
     */
    public ClientAuthorizationGetListRequest setDeveloper(String developer)
    {
        this.developer = developer;

        return this;
    }


    /**
     * Get the start index of search results (inclusive).
     *
     * @return
     *         The start index of search results (inclusive).
     */
    public int getStart()
    {
        return start;
    }


    /**
     * Set the start index of search results (inclusive).
     *
     * @param start
     *         The start index of search results (inclusive).
     *
     * @return
     *         {@code this} object.
     */
    public ClientAuthorizationGetListRequest setStart(int start)
    {
        this.start = start;

        return this;
    }


    /**
     * Get the end index of search results (exclusive).
     *
     * @return
     *         The end index of search results (exclusive).
     */
    public int getEnd()
    {
        return end;
    }


    /**
     * Set the end index of search results (exclusive).
     *
     * @param end
     *         The end index of search results (exclusive).
     *
     * @return
     *         {@code this} object.
     */
    public ClientAuthorizationGetListRequest setEnd(int end)
    {
        this.end = end;

        return this;
    }
}
