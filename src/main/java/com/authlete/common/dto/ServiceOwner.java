/*
 * Copyright (C) 2014-2015 Authlete, Inc.
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
 * Information about a service owner.
 *
 * @author Takahiko Kawasaki
 */
public class ServiceOwner implements Serializable
{
    private static final long serialVersionUID = 2L;


    private int number;
    private String name;
    private String email;
    private String loginId;
    private long apiKey;
    private String apiSecret;
    private Plan plan;


    /**
     * Get the service owner number.
     *
     * @return
     *         The service owner number.
     */
    public int getNumber()
    {
        return number;
    }


    /**
     * Set the service owner number.
     *
     * @param number
     *         The service owner number.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceOwner setNumber(int number)
    {
        this.number = number;

        return this;
    }


    /**
     * Get the service owner name.
     *
     * @return
     *         The service owner name.
     */
    public String getName()
    {
        return name;
    }


    /**
     * Set the service owner name.
     *
     * @param name
     *         The service owner name.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceOwner setName(String name)
    {
        this.name = name;

        return this;
    }


    /**
     * Get the email address of the service owner.
     *
     * @return
     *         The email address of the service owner.
     */
    public String getEmail()
    {
        return email;
    }


    /**
     * Set the email address of the service owner.
     *
     * @param email
     *         The email address of the service owner.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceOwner setEmail(String email)
    {
        this.email = email;

        return this;
    }


    /**
     * Get the login ID of the service owner.
     *
     * @return
     *         The login ID of the service owner.
     */
    public String getLoginId()
    {
        return loginId;
    }


    /**
     * Set the login ID of the service owner.
     *
     * @param loginId
     *         The login ID of the service owner.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceOwner setLoginId(String loginId)
    {
        this.loginId = loginId;

        return this;
    }


    /**
     * Get the API key of the service owner.
     *
     * @return
     *         The API key of the service owner.
     */
    public long getApiKey()
    {
        return apiKey;
    }


    /**
     * Set the API key of the service owner.
     *
     * @param apiKey
     *         The API key of the service owner.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceOwner setApiKey(long apiKey)
    {
        this.apiKey = apiKey;

        return this;
    }


    /**
     * Get the API secret of the service owner.
     *
     * @return
     *         The API secret of the service owner.
     */
    public String getApiSecret()
    {
        return apiSecret;
    }


    /**
     * Set the API secret of the service owner.
     *
     * @param apiSecret
     *         The API secret of the service owner.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceOwner setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;

        return this;
    }



    /**
     * Get the plan.
     *
     * @return
     *         The plan.
     */
    public Plan getPlan()
    {
        return plan;
    }


    /**
     * Set the plan.
     *
     * @param plan
     *         The plan.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceOwner setPlan(Plan plan)
    {
        this.plan = plan;

        return this;
    }
}
