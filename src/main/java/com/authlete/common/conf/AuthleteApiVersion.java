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
package com.authlete.common.conf;


/**
 * Authlete API version.
 *
 * @since 3.23
 */
public enum AuthleteApiVersion
{
    V2,
    V3,
    ;


    /**
     * Parse the given string as {@link AuthleteApiVersion}.
     *
     * <p>
     * When the given string is {@code null} or does not match any known version,
     * this method returns {@code null} without throwing any exception.
     * </p>
     *
     * @param version
     *         A string representing a version. For example, {@code "V2"}.
     *
     * @return
     *         An instance of {@link AuthleteApiVersion}, or {@code null}
     *         if the given string does not match any known version.
     */
    public static AuthleteApiVersion parse(String version)
    {
        if (version == null)
        {
            return null;
        }

        try
        {
            // Parse the given string as AuthleteApiVersion. If the string
            // does not match any known version, valueOf() will throw an
            // IllegalArgumentException instance.
            return AuthleteApiVersion.valueOf(version);
        }
        catch (Exception e)
        {
            // The given string did not match any known version.
            return null;
        }
    }
}
