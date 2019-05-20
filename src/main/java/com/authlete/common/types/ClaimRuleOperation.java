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
package com.authlete.common.types;


/**
 * The operation that a claim rule will apply to any claims
 * it processes.
 * 
 * @since 2.XX
 */
public enum ClaimRuleOperation
{
    /**
     * The rule will fail if the claim is present and has a value.
     */
    PROHIBITED((short) 1, "prohibited"), 

    /**
     * The rule will pass if the claim is present and has a value.
     */
    PRESENT((short)2, "present"), 

    /**
     * The rule will pass if the claim is present and its value
     * equals the claim rule's comparison value.
     */
    EQUALS((short) 3, "equals")

    ;

    private static final ClaimRuleOperation[] sValues = values();
    private final short mValue;
    private final String mString;


    private ClaimRuleOperation(short value, String string)
    {
        mValue = value;
        mString = string;
    }


    /**
     * Get the numerical value for this operation.
     * 
     * @return
     *         The numerical value for this operation.
     * 
     * @since 2.XX
     */
    public short getValue()
    {
        return mValue;
    }


    /**
     * Get the string value for this operation.
     * 
     * @return
     *         The string value for this operation.
     * 
     * @since 2.XX
     */
    public String toString()
    {
        return mString;
    }


    /**
     * Get the enum object with the given numerical value,
     * or {@code null} if not found.
     * 
     * @param value
     *            The numerical value to search for.
     * 
     * @return
     *         The enum object, or {@code null} if not found.
     * 
     * @since 2.XX
     */
    public static ClaimRuleOperation getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // not found
            return null;
        }

        return sValues[value - 1];
    }
}