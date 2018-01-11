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
 * Response from Authlete's {@code /client/get/list} API.
 *
 * @author Takahiko Kawasaki
 */
public class ClientListResponse implements Serializable
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
     * The developer of the targeted client applications.
     */
    private String developer;


    /**
     * The total count of client applications.
     */
    private int totalCount;


    /**
     * The client list extracted from the database.
     */
    private Client[] clients;


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
    public ClientListResponse setStart(int start)
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
    public ClientListResponse setEnd(int end)
    {
        this.end = end;

        return this;
    }


    /**
     * Get the developer specified in the query. It is the value
     * contained in the original request (= the value of {@code
     * 'developer'} parameter) or {@code null}. When {@code null},
     * it means that all the clients that belong to the service
     * are targeted.
     *
     * @return
     *         The developer unique ID assigned by the service.
     *         May be {@code null} if the original request was
     *         not bound to any developer.
     */
    public String getDeveloper()
    {
        return developer;
    }


    /**
     * Set the developer.
     *
     * @param developer
     *         The developer unique ID assigned by the service.
     *
     * @return
     *         {@code this} object.
     */
    public ClientListResponse setDeveloper(String developer)
    {
        this.developer = developer;

        return this;
    }


    /**
     * Get the total count of client applications either of the
     * service (when {@code developer} is {@code null}) or of
     * the developer (when {@code developer} is not {@code null}).
     *
     * <p>
     * The value returned by this method is not the size of the
     * array returned by {@link #getClients()}. Instead, it is
     * the total count of the client applications (either of
     * the service or of the developer) which exist in Authlete's
     * database.
     * </p>
     *
     * @return
     *         The total count of client applications.
     */
    public int getTotalCount()
    {
        return totalCount;
    }


    /**
     * Set the total count of client applications either of the
     * service (when {@code developer} is {@code null}) or of
     * the developer (when {@code developer} is not {@code null}).
     *
     * @param count
     *         The total count of client applications.
     *
     * @return
     *         {@code this} object.
     */
    public ClientListResponse setTotalCount(int count)
    {
        this.totalCount = count;

        return this;
    }


    /**
     * Get the list of clients that match the query conditions.
     *
     * @return
     *         List of clients, or {@code null} when no client
     *         matched the query conditions.
     */
    public Client[] getClients()
    {
        return clients;
    }


    /**
     * Set the list of clients that match the query conditions.
     *
     * @param clients
     *         List of clients, or {@code null} when no client
     *         matched the query conditions.
     *
     * @return
     *         {@code this} object.
     */
    public ClientListResponse setClients(Client[] clients)
    {
        this.clients = clients;

        return this;
    }
}
