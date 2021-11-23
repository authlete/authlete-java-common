/*
 * Copyright (C) 2021 Authlete, Inc.
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
 * A class that holds a string array.
 *
 * <p>
 * MOXy, a JSON processor, had a critical bug. It could not process
 * multidimensional arrays. The bug was reported as &quot;<a href=
 * "https://bugs.eclipse.org/bugs/show_bug.cgi?id=389815">Bug 389815</a>
 * - Enhancement Request - JSON specific multidimensional array support&quot;
 * on September 18, 2012. The PR which fixed the bug was <a href=
 * "https://github.com/eclipse-ee4j/eclipselink/pull/417">PR 417</a>.
 * The PR was merged on June 14, 2019.
 * </p>
 *
 * <p>
 * Because MOXy was adopted as the default JSON processor for GrassFish 4
 * (<i>&quot;<a href="https://blogs.oracle.com/theaquarium/post/moxy-is-the-new-default-json-binding-provider-in-glassfish-4"
 * >MOXy is the New Default JSON-Binding Provider in GlassFish 4</a>&quot;</i>),
 * the range of influence of the bug expanded. Developers had to avoid using
 * multidimensional arrays when they used GlassFish.
 * </p>
 *
 * <p>
 * The PR for the bug was merged in June 2019 (about two years and five months
 * ago as of this writing), but it is not an easy task to clean up dependencies
 * on the old buggy MOXy implementation. We still have to avoid using
 * multidimensional arrays.
 * </p>
 *
 * @since 3.8
 */
public class StringArray implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String[] array;


    /**
     * The default constructor.
     */
    public StringArray()
    {
    }


    /**
     * A constructor with the initial value of string array this instance holds.
     *
     * @param array
     *         A string array.
     */
    public StringArray(String[] array)
    {
        this.array = array;
    }


    /**
     * Get the string array this instance holds.
     *
     * @return
     *         The string array.
     */
    public String[] getArray()
    {
        return array;
    }


    /**
     * Set a string array to let this instance hold.
     *
     * @param array
     *         A string array.
     *
     * @return
     *         {@code this} object.
     */
    public StringArray setArray(String[] array)
    {
        this.array = array;

        return this;
    }
}
