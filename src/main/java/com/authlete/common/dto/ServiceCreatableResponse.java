/*
 * Copyright (C) 2015 Authlete, Inc.
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
import com.authlete.common.types.Plan;


/**
 * Response from Authlete's {@code /api/service/creatable} API.
 *
 * @author Takahiko Kawasaki
 */
public class ServiceCreatableResponse implements Serializable
{
    private static final long serialVersionUID = 1L;


    private boolean creatable;
    private int count;
    private int limit;
    private Plan plan;


    /**
     * Check whether the service owner can create a new service.
     *
     * @return
     *         {@code true} if the service owner can create a new service.
     *         {@code false} if the current number of services that the
     *         service owner has reached or exceeded the maximum number
     *         of services allowed in the plan.
     */
    public boolean isCreatable()
    {
        return creatable;
    }


    /**
     * Set the flag to indicate whether the service owner can create
     * a new service.
     *
     * @param creatable
     *         {@code true} if the service owner can create a new service.
     *         Otherwise, {@code false}.
     */
    public void setCreatable(boolean creatable)
    {
        this.creatable = creatable;
    }


    /**
     * Get the current number of services that the service owner has.
     *
     * @return
     *         The number of services.
     */
    public int getCount()
    {
        return count;
    }


    /**
     * Set the current number of services that the service owner has.
     *
     * @param count
     *         The number of services.
     */
    public void setCount(int count)
    {
        this.count = count;
    }


    /**
     * Get the maximum number of services that can be created in the plan.
     *
     * @return
     *         The maximum number of services that can be created.
     */
    public int getLimit()
    {
        return limit;
    }


    /**
     * Set the maximum number of services that can be created in the plan.
     *
     * @param limit
     *         The maximum number of services that can be created.
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }


    /**
     * Get the plan of the service owner.
     *
     * @return
     *         The plan.
     */
    public Plan getPlan()
    {
        return plan;
    }


    /**
     * Set the plan of the service owner.
     *
     * @param plan
     *         The plan.
     */
    public void setPlan(Plan plan)
    {
        this.plan = plan;
    }
}
