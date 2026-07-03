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
 * Response from Authlete's {@code /clients} API.
 *
 * @author Kyle Gonzalez
 * @since Authlete 3.0.34
 */
public class ClientCursorListResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The total number of clients that are available for specific service. Will be set on the initial list call and {@code null} on consecutive list calls.
     */
    private Integer totalCount = null;

    /**
     * The requested limit of clients to return.
     */
    private int limit;

    /**
     * The array of clients that have been returned.
     */
    private Client[] entities;

    /**
     * The opaque cursor token which is required for the next list request. If there are no further clients to list this property will be {@code null}.
     */
    private String nextCursor;


    public ClientCursorListResponse()
    {

    }


    public ClientCursorListResponse(Client[] entities, int limit, String nextCursor)
    {
        this.entities = entities;
        this.limit = limit;
        this.nextCursor = nextCursor;
    }


    public ClientCursorListResponse(Client[] entities, int limit, String nextCursor, Integer totalCount)
    {
        this(entities, limit, nextCursor);
        this.totalCount = totalCount;
    }


    /**
     * Get the list of clients that match the list conditions.
     *
     * @return Array of clients that match, otherwise an empty array
     */
    public Client[] getEntities()
    {
        return entities;
    }


    /**
     * Get the interpreted limit of clients that will be return from the single call.
     *
     * @return the maximum amount of clients that could be returned from this single call
     */
    public int getLimit()
    {
        return limit;
    }


    /**
     * Get the total count of clients available from this list endpoint given the current list conditions.
     *
     * @return the maximum available amount of clients that can be iterated through
     */
    public Integer getTotalCount()
    {
        return totalCount;
    }


    /**
     * The next {@code cursor} that should be provided to the list endpoint to retrieve the next portion of clients.
     * If {@code null} it means all clients have been iterated over already and there are no further clients to retrieve.
     *
     * @return the next cursor value to be used if more clients can be retrieved, otherwise {@code null}
     */
    public String getNextCursor()
    {
        return nextCursor;
    }
}
