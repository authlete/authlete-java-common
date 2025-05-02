/*
 * Copyright (C) 2025 Authlete, Inc.
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
 * A request to Authlete's {@code /nativesso/logout} API.
 *
 * <p>
 * The {@code /nativesso/logout} API is provided to support the concept of
 * <i>"logout from all applications"</i> in the context of <a href=
 * "https://openid.net/specs/openid-connect-native-sso-1_0.html">Native SSO</a>.
 * This is accomplished by deleting access/refresh token records associated
 * with the specified session ID.
 * </p>
 *
 * <p>
 * In Authlete's implementation, access/refresh token records can be associated
 * with a session ID only through the mechanism introduced by the "<a href=
 * "https://openid.net/specs/openid-connect-native-sso-1_0.html">OpenID Connect
 * Native SSO for Mobile Apps 1.0</a>" specification ("Native SSO").
 * </p>
 *
 * @since 4.20
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
 *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
 */
public class NativeSsoLogoutRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The session ID of a user's authentication session.
     */
    private String sessionId;


    /**
     * Get the session ID of a user's authentication session.
     *
     * @return
     *         The session ID.
     */
    public String getSessionId()
    {
        return sessionId;
    }


    /**
     * Set the session ID of a user's authentication session.
     *
     * @param sessionId
     *         The session ID.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoLogoutRequest setSessionId(String sessionId)
    {
        this.sessionId = sessionId;

        return this;
    }
}
