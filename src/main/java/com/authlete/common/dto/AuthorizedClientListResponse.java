/*
 * Copyright (C) 2016 Authlete, Inc.
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


/**
 * Response from Authlete's {@code /client/authorization/get/list} API.
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.36
 */
public class AuthorizedClientListResponse extends ClientListResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The identifier of the user who has granted authorization
     * to the client applications.
     */
    private String subject;


    /**
     * Get the identifier of the user who has granted authorization
     * to the client applications.
     *
     * @return
     *         The identifier of the user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the identifier of the user who has granted authorization
     * to the client applications.
     *
     * @param subject
     *         The identifier of the user.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizedClientListResponse setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }
}
