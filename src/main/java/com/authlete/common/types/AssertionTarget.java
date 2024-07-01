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
 * The part of the service that an assertion processor will be applied to.
 *
 * @since 2.39
 */
public enum AssertionTarget
{
    /**
     * The assertion processor is used for the OAuth Dynamic Client Registration
     * protocol's "software_statement" field, which contains a signed assertion
     * of client attributes.
     */
    CLIENT_REGISTRATION_SOFTWARE_STATEMENT((short)1, "software_statement"),
    ;


    private static final AssertionTarget[] sValues = values();
    private final short mValue;
    private final String mString;


    private AssertionTarget(short value, String string)
    {
        mValue = value;
        mString = string;
    }


    /**
     * Get the numerical value for this target.
     *
     * @return
     *         The numerical value for this target.
     *
     * @since 2.39
     */
    public short getValue()
    {
        return mValue;
    }


    @Override
    public String toString()
    {
        return mString;
    }


    /**
     * Get the enum object with the given numerical value, or {@code null} if not
     * found.
     *
     * @param value
     *         The numerical value to search for.
     *
     * @return
     *         The enum object, or {@code null} if not found.
     *
     * @since 2.39
     */
    public static AssertionTarget getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // not found
            return null;
        }

        return sValues[value - 1];
    }
}
