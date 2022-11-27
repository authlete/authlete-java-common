/*
 * Copyright (C) 2022 Authlete, Inc.
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
package com.authlete.common.util;


/**
 * Flags to control contents of a map.
 *
 * @since 3.45
 */
public class MapControl
{
    private boolean nullIncluded;
    private boolean zeroIncluded;
    private boolean falseIncluded;


    /**
     * Get the flag indicating whether properties should be included
     * even when their values are null.
     *
     * @return
     *         {@code true} if properties are included even when their
     *         values are null. {@code false} if properties are not
     *         included when their values are null.
     */
    public boolean isNullIncluded()
    {
        return nullIncluded;
    }


    /**
     * Set the flag indicating whether properties should be included
     * even when their values are null.
     *
     * @param included
     *         {@code true} to include properties even when their values
     *         are null. {@code false} not to include properties when
     *         their values are null.
     *
     * @return
     *         {@code this} object.
     */
    public MapControl setNullIncluded(boolean included)
    {
        this.nullIncluded = included;

        return this;
    }


    /**
     * Get the flag indicating whether properties should be included
     * even when their values are zero.
     *
     * @return
     *         {@code true} if properties are included even when their
     *         values are zero. {@code false} if properties are not
     *         included when their values are zero.
     */
    public boolean isZeroIncluded()
    {
        return zeroIncluded;
    }


    /**
     * Set the flag indicating whether properties should be included
     * even when their values are zero.
     *
     * @param included
     *         {@code true} to include properties even when their values
     *         are zero. {@code false} not to include properties when
     *         their values are zero.
     *
     * @return
     *         {@code this} object.
     */
    public MapControl setZeroIncluded(boolean included)
    {
        this.zeroIncluded = included;

        return this;
    }


    /**
     * Get the flag indicating whether properties should be included
     * even when their values are false.
     *
     * @return
     *         {@code true} if properties are included even when their
     *         values are false. {@code false} if properties are not
     *         included when their values are false.
     */
    public boolean isFalseIncluded()
    {
        return falseIncluded;
    }


    /**
     * Set the flag indicating whether properties should be included
     * even when their values are false.
     *
     * @param included
     *         {@code true} to include properties even when their values
     *         are false. {@code false} not to include properties when
     *         their values are false.
     *
     * @return
     *         {@code this} object.
     */
    public MapControl setFalseIncluded(boolean included)
    {
        this.falseIncluded = included;

        return this;
    }
}
