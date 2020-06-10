/*
 * Copyright (C) 2014-2015 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Authentication response from a service implementation to Authlete.
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.1
 */
public class AuthenticationCallbackResponse implements Serializable
{
    private static final long serialVersionUID = 3L;


    private boolean authenticated;
    private String subject;
    private String claims;


    /**
     * Get the authentication result.
     *
     * @return
     *         {@code true} if the credentials of the end-user were valid.
     *         Otherwise, {@code false}.
     */
    public boolean isAuthenticated()
    {
        return authenticated;
    }


    /**
     * Set the authentication result.
     *
     * <p>
     * When the credentials ({@code id} and {@code password}) in the
     * authentication callback request ({@link AuthenticationCallbackRequest})
     * are valid, {@code true} should be set to this property.
     * </p>
     *
     * @param authenticated
     *         {@code true} if the credentials of the end-user were valid.
     *         Otherwise, {@code false}.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackResponse setAuthenticated(boolean authenticated)
    {
        this.authenticated = authenticated;

        return this;
    }


    /**
     * Get the subject (= unique identifier) of the authenticated user.
     *
     * @return
     *         The subject (= unique identifier) of the authenticated user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the authenticated user.
     *
     * <p>
     * When the credentials ({@code id} and {@code password}) in the
     * authentication callback request ({@link AuthenticationCallbackRequest})
     * are valid, the subject (= unique identifier) of the end-user should
     * be set to this property.
     * </p>
     *
     * <p>
     * The value of {@code subject} does not always have to be equal to
     * the value of {@code id} in the authentication callback request.
     * For example, {@code id} may be an email address but a service
     * implementation may have generated and assigned a unique identifier
     * such as {@code 60504791} to the end-user who is represented by
     * the email address. In such a case, {@code 60504791} should be set
     * as {@code subject}.
     * </p>
     *
     * @param subject
     *         The subject (= unique identifier) of the authenticated user.
     *         When the authentication failed, this property should be
     *         {@code null}.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackResponse setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the claims of the authenticated user in JSON format.
     *
     * @return
     *         The claims of the authenticated user in JSON format.
     */
    public String getClaims()
    {
        return claims;
    }


    /**
     * Set the claims of the authenticated user in JSON format.
     *
     * <p>
     * For example, to embed "<code>given_name</code>" claim,
     * "<code>family_name</code>" claim and "<code>email</code>"
     * claim, the string should be formatted like the following.
     * </p>
     *
     * <pre>
     * {
     *   "given_name": "Takahiko",
     *   "family_name": "Kawasaki",
     *   "email": "takahiko.kawasaki@example.com"
     * }
     * </pre>
     *
     * <p>
     * See "<a href=
     * "http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >5.1. Standard Claims</a>" in <a href=
     * "http://openid.net/specs/openid-connect-core-1_0.html"
     * >OpenID Connect Core 1.0</a> for further details about
     * the format.
     * </p>
     *
     * <p>
     * This property does not have to be set (1) when the credentials
     * ({@code id} and {@code password}) in the authentication callback
     * request ({@link AuthenticationCallbackRequest}) were invalid,
     * (2) when the authentication callback request did not contain
     * any claims (= {@code claims} request parameter was {@code null}
     * or empty), or (3) when the service implementation could not
     * provide data for any of the requested claims.
     * </p>
     *
     * @param claims
     *         The claims of the authenticated user in JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackResponse setClaims(String claims)
    {
        this.claims = claims;

        return this;
    }
}
