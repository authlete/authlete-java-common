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
 * Response from Authlete's {@code /service/get/list} API.
 *
 * @author Takahiko Kawasaki
 */
public class ServiceListResponse implements Serializable
{
    private static final long serialVersionUID = 1L;


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    /**
     * The start index (inclusive) for the result set of the query.
     * @since Authlete 0.1
     */
    private int start;


    /**
     * The end index (exclusive) for the result set of the query.
     * @since Authlete 0.1
     */
    private int end;


    /**
     * The total count of services.
     * @since Authlete 0.1
     */
    private int totalCount;


    /**
     * The service list extracted from the database.
     * @since Authlete 0.1
     */
    private Service[] services;


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
    public ServiceListResponse setStart(int start)
    {
        this.start = start;

        return this;
    }


    /**
     * Get the end index (exclusive) for the result set of the query.
     * It is the value contained in the original request (= the value
     * of {@code 'end'} parameter), or the default value used by Authlete
     * server if the original request did not contain the parameter.
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
    public ServiceListResponse setEnd(int end)
    {
        this.end = end;

        return this;
    }


    /**
     * Get the total count of services.
     *
     * @return
     *         The total count of services.
     */
    public int getTotalCount()
    {
        return totalCount;
    }


    /**
     * Set the total count of services.
     *
     * @param count
     *         The total count of services.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceListResponse setTotalCount(int count)
    {
        this.totalCount = count;

        return this;
    }


    /**
     * Get the list of services.
     *
     * @return
     *         List of services, or {@code null} when no service
     *         matched the query conditions.
     */
    public Service[] getServices()
    {
        return services;
    }


    /**
     * Set the list of services that match the query conditions.
     *
     * @param services
     *         List of services, or {@code null} when no service
     *         matched the query conditions.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceListResponse setServices(Service[] services)
    {
        this.services = services;

        return this;
    }
}
