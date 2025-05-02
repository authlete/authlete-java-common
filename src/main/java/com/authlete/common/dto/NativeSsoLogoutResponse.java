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


/**
 * A response from Authlete's {@code /nativesso/logout} API.
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
public class NativeSsoLogoutResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the API caller should take.
     */
    public enum Action
    {
        /**
         * The {@code /nativesso/logout} API call completed successfully.
         * Zero or more access/refresh token have been deleted.
         */
        OK,


        /**
         * An error occurred on the Authlete side. For example, an error might
         * have occurred while executing a delete operation on the database.
         */
        SERVER_ERROR,


        /**
         * The {@code /nativesso/logout} API call contained a problem.
         * For example, the call may have been missing the required request
         * parameter {@code sessionId}.
         */
        CALLER_ERROR,
    }


    /**
     * The next action that the API caller should take.
     */
    private Action action;


    /**
     * The number of deleted access/refresh token records.
     */
    private int count;


    /**
     * Get the next action that the API caller should take.
     *
     * @return
     *         The next action to take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the API caller should take.
     *
     * @param action
     *         The next action to take.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoLogoutResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the number of deleted access/refresh token records.
     *
     * @return
     *         The number of deleted access/refresh token records.
     */
    public int getCount()
    {
        return count;
    }


    /**
     * Set the number of deleted access/refresh token records.
     *
     * @param count
     *         The number of deleted access/refresh token records.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoLogoutResponse setCount(int count)
    {
        this.count = count;

        return this;
    }
}
