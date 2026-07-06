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
 * Response from Authlete's cursor based APIs.
 *
 * @author Kyle Gonzalez
 * @since Authlete 3.0.34
 */
public class CursorResponse<T extends Serializable> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The total number of entities, in situations where this is expensive to calculate it can be omitted and set to {@code null}.
     */
    private Integer totalCount = null;

    /**
     * The requested max limit of entities to return in this payload.
     */
    private int limit;

    /**
     * The array of entities that have been returned.
     */
    private T[] entities;

    /**
     * The opaque cursor token which is required for the next list request. If there are no further entities to retrieve this property will be {@code null}.
     */
    private String nextCursor;


    public CursorResponse()
    {

    }


    public CursorResponse(T[] entities, int limit, String nextCursor)
    {
        this.entities = entities;
        this.limit = limit;
        this.nextCursor = nextCursor;
    }


    public CursorResponse(T[] entities, int limit, String nextCursor, Integer totalCount)
    {
        this(entities, limit, nextCursor);
        this.totalCount = totalCount;
    }


    /**
     * Get the list of entities that match the list conditions.
     *
     * @return Array of entities that match, otherwise an empty array
     */
    public T[] getEntities()
    {
        return entities;
    }


    /**
     * Get the interpreted limit of entities that will be return from the single call.
     *
     * @return the maximum amount of entities that could be returned from this single call
     */
    public int getLimit()
    {
        return limit;
    }


    /**
     * Get the total count of entities available from this endpoint given the provided conditions.
     *
     * @return the maximum available amount of entities that can be retrieved at this point in time
     */
    public Integer getTotalCount()
    {
        return totalCount;
    }


    /**
     * The next {@code cursor} that should be provided to this endpoint to retrieve the next portion of entities.
     * If {@code null} it means all entities have been iterated over already and there are no further entities to retrieve.
     *
     * @return the next cursor value to be used if more entities can be retrieved, otherwise {@code null}
     */
    public String getNextCursor()
    {
        return nextCursor;
    }
}
