package com.authlete.common.dto;


import java.io.Serializable;


public class ClientCursorListResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer totalCount = null;

    private int limit;

    private Client[] entities;

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


    public Client[] getEntities()
    {
        return entities;
    }


    public int getLimit()
    {
        return limit;
    }


    public Integer getTotalCount()
    {
        return totalCount;
    }


    public String getNextCursor()
    {
        return nextCursor;
    }
}
