/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.assurance.constraint;


/**
 * The basic interface that classes representing constraints in
 * {@code verified_claims} implement.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public interface Constraint
{
    /**
     * Check if the key that represents the constraint exists. It does not
     * matter whether the value of the key is null or not.
     *
     * <p>
     * For example, {@code exists()} method of an instance that represents
     * {@code given_name} in the JSON below will return {@code true}.
     * </p>
     *
     * <pre>
     * {
     *   "verified_claims": {
     *     "claims": {
     *       "given_name": null
     *     }
     *   }
     * }
     * </pre>
     *
     * @return
     *         {@code true} if the key that represents the constraint exists.
     */
    boolean exists();


    /**
     * Check if the value of the constraint is null.
     *
     * <p>
     * For example, {@code isNull()} method of an instance that represents
     * {@code given_name} in the JSON below will return {@code true}.
     * </p>
     *
     * <pre>
     * {
     *   "verified_claims": {
     *     "claims": {
     *       "given_name": null
     *     }
     *   }
     * }
     * </pre>
     *
     * But, the method returns {@code false} in the following case.
     *
     * <pre>
     * {
     *   "verified_claims": {
     *     "claims": {
     *       "given_name": {
     *       }
     *     }
     *   }
     * }
     * </pre>
     *
     * @return
     *         {@code true} if the value of the constraint is null.
     */
    boolean isNull();
}
