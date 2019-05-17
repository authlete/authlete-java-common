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


import com.authlete.common.types.ClaimRuleOperation;


public class ClaimRule
{
    private ClaimRuleOperation operation;
    private String claimName;
    private String comparisonValue;


    /**
     * Get the operation that this rule will apply to any claims
     * it processes.
     * 
     * @return
     *         The operation.
     * 
     * @since 2.XX
     */
    public ClaimRuleOperation getOperation()
    {
        return operation;
    }


    /**
     * Set the operation that this rule will apply to any claims
     * it processes.
     * 
     * @param operation
     *            The operation.
     * 
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public ClaimRule setOperation(ClaimRuleOperation operation)
    {
        this.operation = operation;
        return this;
    }


    /**
     * Get the name of the claim that this rule applies to.
     * 
     * @return
     *         The claim name.
     * 
     * @since 2.XX
     */
    public String getClaimName()
    {
        return claimName;
    }


    /**
     * Set the name of the claim that this rule applies to.
     * 
     * @param claimName
     *            The claim name.
     * 
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public ClaimRule setClaimName(String claimName)
    {
        this.claimName = claimName;
        return this;
    }


    /**
     * Get the value to compare the claim value to, if the
     * operation is EQUALS. Values are compared based on
     * their serialization as strings.
     * 
     * @return
     *         The comparison value, as a string.
     * 
     * @since 2.XX
     */
    public String getComparisonValue()
    {
        return comparisonValue;
    }


    /**
     * Get the value to compare the claim value to, if the
     * operation is EQUALS. Values are compared based on
     * their serialization as strings.
     * 
     * @param comparisonValue
     *            The comparison value, as a string.
     * 
     * @return
     *         {@code this} object.
     * 
     * @since 2.XX
     */
    public ClaimRule setComparisonValue(String comparisonValue)
    {
        this.comparisonValue = comparisonValue;
        return this;
    }
}
