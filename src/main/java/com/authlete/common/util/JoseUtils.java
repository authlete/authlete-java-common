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
package com.authlete.common.util;


import com.authlete.common.types.JWEAlg;


/**
 * Utilities for JOSE (JavaScript Object Signing and Encryption).
 *
 * @author Takahiko Kawasaki
 */
public class JoseUtils
{
    private JoseUtils()
    {
    }


    /**
     * Check whether the given JWE algorithm is supported by Authlete.
     *
     * <p>
     * This method returns {@code true} when the given JWE algorithm
     * is one of the following.
     * </p>
     *
     * <blockquote>
     * <ol>
     * <li>{@link JWEAlg#RSA1_5 RSA1_5}
     * <li>{@link JWEAlg#RSA_OAEP RSA-OAEP}
     * <li>{@link JWEAlg#RSA_OAEP_256 RSA-OAEP-256}
     * <li>{@link JWEAlg#A128KW A128KW}
     * <li>{@link JWEAlg#A192KW A192KW}
     * <li>{@link JWEAlg#A256KW A256KW}
     * <li>{@link JWEAlg#A128GCMKW A128GCMKW}
     * <li>{@link JWEAlg#A192GCMKW A192GCMKW}
     * <li>{@link JWEAlg#A256GCMKW A256GCMKW}
     * <li>{@link JWEAlg#DIR dir}
     * </ol>
     * </blockquote>
     *
     * @param alg
     *         A JWE algorithm.
     *
     * @return
     *         {@code true} if the given JWE algorithm is supported
     *         by Authlete. {@code false} if the given JWE algorithm
     *         is not supported by Authlete. When {@code null} is
     *         given, this method returns {@code false}.
     */
    public static boolean isSupported(JWEAlg alg)
    {
        if (alg == null)
        {
            return false;
        }

        switch (alg)
        {
            case RSA1_5:
            case RSA_OAEP:
            case RSA_OAEP_256:
            case A128KW:
            case A192KW:
            case A256KW:
            case A128GCMKW:
            case A192GCMKW:
            case A256GCMKW:
            case DIR:
                // Supported
                return true;

            default:
                // Not supported.
                return false;
        }
    }
}
