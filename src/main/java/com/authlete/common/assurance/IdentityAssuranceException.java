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
package com.authlete.common.assurance;


/**
 * An exception that indicates the structure does not conform to the
 * specification (<a href=
 * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 * >OpenID Connect for Identity Assurance 1.0</a>).
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class IdentityAssuranceException extends RuntimeException
{
    private static final long serialVersionUID = 1L;


    /**
     * The default constructor.
     */
    public IdentityAssuranceException()
    {
    }


    /**
     * The constructor with a message.
     *
     * @param message
     *         A message that explains this exception.
     */
    public IdentityAssuranceException(String message)
    {
        super(message);
    }


    /**
     * The constructor with a cause.
     *
     * @param cause
     *         The cause of this exception.
     */
    public IdentityAssuranceException(Throwable cause)
    {
        super(cause);
    }


    /**
     * The constructor with a message and a cause.
     *
     * @param message
     *         A message that explains this exception.
     *
     * @param cause
     *         The cause of this exception.
     */
    public IdentityAssuranceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
