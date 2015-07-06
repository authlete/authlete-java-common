/*
 * Copyright (C) 2015 Authlete, Inc.
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
 * Developer authentication response from a service implementation to Authlete.
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.9
 */
public class DeveloperAuthenticationCallbackResponse implements Serializable
{
    private static final long serialVersionUID = 1L;


    private boolean authenticated;
    private String subject;
    private String displayName;


    /**
     * Get the authentication result.
     *
     * @return
     *         {@code true} if the credentials of the developer were valid.
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
     * developer authentication callback request ({@link
     * DeveloperAuthenticationCallbackRequest}) are valid, {@code true}
     * should be set to this property.
     * </p>
     *
     * @param authenticated
     *         {@code true} if the credentials of the developer were valid.
     *         Otherwise, {@code false}.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackResponse setAuthenticated(boolean authenticated)
    {
        this.authenticated = authenticated;

        return this;
    }


    /**
     * Get the subject (= unique identifier) of the authenticated developer.
     *
     * @return
     *         The subject (= unique identifier) of the authenticated developer.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the authenticated developer.
     *
     * <p>
     * When the credentials ({@code id} and {@code password}) in the
     * developer authentication callback request ({@link
     * DeveloperAuthenticationCallbackRequest}) are valid, the subject
     * (= unique identifier) of the developer should be set to this property.
     * </p>
     *
     * <p>
     * The value of {@code subject} does not always have to be equal to
     * the value of {@code id} in the developer authentication callback
     * request. For example, {@code id} may be an email address but a
     * service implementation may have generated and assigned a unique
     * identifier such as {@code 60504791} to the developer who is
     * represented by the email address. In such a case, {@code 60504791}
     * should be set as {@code subject}.
     * </p>
     *
     * @param subject
     *         The subject (= unique identifier) of the authenticated
     *         developer. When the authentication failed, this property
     *         should be {@code null}.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackResponse setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the display name of the authenticated developer.
     *
     * @return
     *         The display name of the authenticated developer.
     */
    public String getDisplayName()
    {
        return displayName;
    }


    /**
     * Set the display name of the authenticated developer.
     *
     * @param displayName
     *         The display name of the authenticated developer.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackResponse setDisplayName(String displayName)
    {
        this.displayName = displayName;

        return this;
    }
}
