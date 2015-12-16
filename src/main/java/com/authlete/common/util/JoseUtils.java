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
     * is one of the following. (All the algorithms listed in {@code
     * JWEAlg} are supported as of Dec. 15, 2015.)
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
     * <li>{@link JWEAlg#DIR dir}
     * <li>{@link JWEAlg#ECDH_ES ECDH-ES}
     * <li>{@link JWEAlg#ECDH_ES_A128KW ECDH-ES+A128KW}
     * <li>{@link JWEAlg#ECDH_ES_A192KW ECDH-ES+A192KW}
     * <li>{@link JWEAlg#ECDH_ES_A256KW ECDH-ES+A256KW}
     * <li>{@link JWEAlg#A128GCMKW A128GCMKW}
     * <li>{@link JWEAlg#A192GCMKW A192GCMKW}
     * <li>{@link JWEAlg#A256GCMKW A256GCMKW}
     * <li>{@link JWEAlg#PBES2_HS256_A128KW PBSE2-HS256-A128KW}
     * <li>{@link JWEAlg#PBES2_HS384_A192KW PBSE2-HS384-A192KW}
     * <li>{@link JWEAlg#PBES2_HS512_A256KW PBSE2-HS512-A256KW}
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
        // All the algorithms listed in JWEAlg are supported.
        return (alg != null);
    }
}
