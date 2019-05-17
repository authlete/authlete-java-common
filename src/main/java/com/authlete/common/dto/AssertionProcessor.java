/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.dto;


import com.authlete.common.types.AssertionTarget;


/**
 * Information about an assertion processor, used by Authlete to allow a
 * service to programmatically check assertions used passed in by clients
 * and resource servers.
 *
 * @since 2.XX
 */
public class AssertionProcessor
{
    private int number;
    private String jwks;
    private AssertionTarget target;
    private ClaimRule[] claimRules;
    private int serviceNumber;


    /**
     * Get the JSON Web Key set used to check the assertion's
     * signatures, serialized as a JSON string.
     * 
     * @return
     *         The JSON Web Key Set
     * 
     * @since 2.XX
     */
    public String getJwks()
    {
        return jwks;
    }


    /**
     * Set the JSON Web Key set used to check the assertion's
     * signatures, serialized as a JSON string.
     * 
     * @param jwks
     *            The JSON Web Key Set
     *
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public AssertionProcessor setJwks(String jwks)
    {
        this.jwks = jwks;
        return this;
    }


    /**
     * Get the part of the service that this assertion processor will
     * be applied to.
     * 
     * @return
     *         The target assertion. Can not be {@code null} if this processor
     *         is active.
     * 
     * @since 2.XX
     */
    public AssertionTarget getTarget()
    {
        return target;
    }


    /**
     * Set the part of the service that this assertion processor will
     * be applied to.
     * 
     * @param target
     *            The target assertion. Can not be {@code null} if this processor
     *            is active.
     * 
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public AssertionProcessor setTarget(AssertionTarget target)
    {
        this.target = target;
        return this;
    }


    /**
     * Get the claim rules that will be applied to any assertions processed by
     * this processor.
     * 
     * @return
     *         The array of claim rules.
     * 
     * @since 2.XX
     */
    public ClaimRule[] getClaimRules()
    {
        return claimRules;
    }


    /**
     * Set the claim rules that will be applied to any assertions processed by
     * this processor.
     * 
     * @param claimRules
     *            The array of claim rules.
     * 
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public AssertionProcessor setClaimRules(ClaimRule[] claimRules)
    {
        this.claimRules = claimRules;
        return this;
    }


    /**
     * Get the number of the service that this assertion processor is attached to.
     * 
     * @return
     *         The service number.
     * 
     * @since 2.XX
     */
    public int getServiceNumber()
    {
        return this.serviceNumber;
    }


    /**
     * Set the number of the service that this assertion processor is attached to.
     * 
     * @serviceNumber
     *                The service number.
     * 
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public AssertionProcessor setServiceNumber(int serviceNumber)
    {
        this.serviceNumber = serviceNumber;
        return this;
    }


    /**
     * Get the object number for this assertion processor.
     * 
     * @return
     *         The number.
     * 
     * @since 2.XX
     */
    public int getNumber()
    {
        return number;
    }


    /**
     * Get the object number for this assertion processor.
     * 
     * @param number
     *            The number.
     * 
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public AssertionProcessor setNumber(int number)
    {
        this.number = number;
        return this;
    }
}
