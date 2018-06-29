/*
 * Copyright (C) 2018 Authlete, Inc.
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
 * Response from Authlete's {@code /auth/token/get/list} API.
 *
 * @since 2.22
 */
public class TokenListResponse implements Serializable
{
    private static final long serialVersionUID = 1L;


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    /**
     * The start index (inclusive) for the result set of the query.
     */
    private int start;


    /**
     * The end index (exclusive) for the result set of the query.
     */
    private int end;


    /**
     * The client associated with the access tokens.
     */
    private Client client;


    /**
     * The identifier of the user associated with the access tokens.
     */
    private String subject;


    /**
     * The total count of access tokens.
     */
    private int totalCount;


    /**
     * The access token list extracted from the database.
     */
    private AccessToken[] accessTokens;


    /**
     * Get the start index (inclusive) for the result set of the query.
     * It is the value contained in the original request (= the value
     * of {@code 'start'} parameter), or the default value (0) if the
     * original request did not contain the parameter.
     *
     * @return
     *         The start index for the result set of the query.
     */
    public int getStart()
    {
        return start;
    }


    /**
     * Set the start index (inclusive) for the result set of the query.
     *
     * @param start
     *         The start index for the result set of the query.
     *
     * @return
     *         {@code this} object.
     */
    public TokenListResponse setStart(int start)
    {
        this.start = start;

        return this;
    }


    /**
     * Get the end index (exclusive) for the result set of the query.
     * It is the value contained in the original request (= the value
     * of {@code 'end'} parameter), or the default value defined in
     * Authlete server if the original request did not contain the
     * parameter.
     *
     * @return
     *         The end index for the result set of the query.
     */
    public int getEnd()
    {
        return end;
    }


    /**
     * Set the end index (exclusive) for the result set of the query.
     *
     * @param end
     *         The end index for the result set of the query.
     *
     * @return
     *         {@code this} object.
     */
    public TokenListResponse setEnd(int end)
    {
        this.end = end;

        return this;
    }


    /**
     * Get the client information associated with the value of {@code 'clientIdentifier'}
     * parameter in the original request. If the original request did not contain
     * the parameter, this method returns {@code null}.
     *
     * @return
     *         The client information associated with the value of {@code 'clientIdentifier'}
     *         parameter in the original request. {@code null} is returned if the
     *         original request did not contain the parameter.
     */
    public Client getClient()
    {
        return client;
    }


    /**
     * Set the client information associated with the value of {@code 'clientIdentifier'}
     * parameter in the original request.
     *
     * @param client
     *         The client information associated with the value of {@code 'clientIdentifier'}
     *         parameter in the original request.
     *
     * @return
     *         {@code this} object.
     */
    public TokenListResponse setClient(Client client)
    {
        this.client = client;

        return this;
    }


    /**
     * Get the value of {@code 'subject'} parameter in the original request. If
     * the original request did not contain the parameter, this method returns
     * {@code null}.
     *
     * @return
     *         The value of {@code 'subject'} parameter in the original request.
     *         {@code null} is returned if the original request did not contain
     *         the parameter.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the value of {@code 'subject'} parameter in the original request.
     *
     * @param subject
     *         The value of {@code 'subject'} parameter in the original request.
     *
     * @return
     *         {@code this} object.
     */
    public TokenListResponse setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the total count of access tokens. The meaning of the total count changes
     * depending on the situation as follows.
     *
     * <ol>
     *   <li>
     *     When the value of {@code 'clientIdentifier'} parameter is {@code null}
     *     and the value of {@code 'subject'} parameter is {@code null} in the original
     *     request, it means the total count of the access tokens associated with
     *     the service.
     *   </li>
     *
     *   <li>
     *     When the value of {@code 'clientIdentifier'} parameter is {@code null}
     *     and the value of {@code 'subject'} parameter is not {@code null} in the
     *     original request, it means the total count of the access tokens associated
     *     with the subject.
     *   </li>
     *
     *   <li>
     *     When the value of {@code 'clientIdentifier'} parameter is not {@code null}
     *     and the value of {@code 'subject'} parameter is {@code null} in the original
     *     request, it means the total count of the access tokens associated with
     *     the client application.
     *   </li>
     *
     *   <li>
     *     When the value of {@code 'clientIdentifier'} parameter is not {@code null}
     *     and the value of {@code 'subject'} parameter is not {@code null} in the
     *     original request, it means the total count of the access tokens associated
     *     with the client application and the subject.
     *   </li>
     * </ol>
     *
     * @return
     *         The total count of access tokens.
     */
    public int getTotalCount()
    {
        return totalCount;
    }


    /**
     * Set the total count of access tokens. The meaning of the total count changes
     * depending on the situation. See {@link #getTotalCount()} for more details.
     *
     * @return
     *         {@code this} object.
     */
    public TokenListResponse setTotalCount(int count)
    {
        this.totalCount = count;

        return this;
    }


    /**
     * Get the list of access tokens that match the query conditions.
     *
     * @return
     *         List of clients, or {@code null} when no access tokens matched the
     *         query conditions.
     */
    public AccessToken[] getAccessTokens()
    {
        return accessTokens;
    }


    /**
     * Set the list of access tokens that match the query conditions.
     *
     * @param clients
     *         List of clients, or {@code null} when no access tokens matched the
     *         query conditions.
     *
     * @return
     *         {@code this} object.
     */
    public TokenListResponse setAccessTokens(AccessToken[] accessTokens)
    {
        this.accessTokens = accessTokens;

        return this;
    }
}
