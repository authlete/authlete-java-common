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
package com.authlete.common.types;


/**
 * Plan.
 *
 * @author Takahiko Kawasaki
 *
 * @see <a href="https://www.authlete.com/pricing">Pricing</a>
 *
 * @since 1.17
 */
public enum Plan
{
    /**
     * Free plan.
     */
    FREE((short)0),


    /**
     * Lite plan.
     */
    LITE((short)1),


    /**
     * Premium plan.
     */
    PREMIUM((short)2),


    /**
     * Enterprise plan.
     */
    ENTERPRISE((short)3)
    ;


    private static final Plan[] sValues = values();
    private final short mValue;


    private Plan(short value)
    {
        mValue = value;
    }


    /**
     * Get the integer representation of this enum instance.
     */
    public short getValue()
    {
        return mValue;
    }


    /**
     * Find an instance of this enum by a value.
     *
     * @param value
     *         The integer representation of the instance to find.
     *
     * @return
     *         An instance of this enum, or {@code null} if not found.
     */
    public static Plan getByValue(short value)
    {
        if (value < 0 || sValues.length <= value)
        {
            // Not found.
            return null;
        }

        return sValues[value];
    }
}
