/*
 * Copyright (C) 2026 Authlete, Inc.
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
 * Request to Authlete's
 * <code>/api<wbr>/<i>{serivce-id}</i><wbr>/backchannel<wbr>/logout<wbr>/token</code>
 * API.
 *
 * <p>
 * The Authlete API generates a <a href=
 * "https://openid.net/specs/openid-connect-backchannel-1_0.html#LogoutToken"
 * >Logout Token</a> that complies with the <a href=
 * https://openid.net/specs/openid-connect-backchannel-1_0.html">OpenID Connect
 * Back-Channel Logout 1.0</a> specification.
 * </p>
 *
 * @see <a href="https://openid.net/specs/openid-connect-backchannel-1_0.html">
 *      OpenID Connect Back-Channel Logout 1.0</a>
 *
 * @since 4.43
 * @since Authlete 3.0.32
 */
public class BackchannelLogoutTokenRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The client identifier.
     */
    private String clientIdentifier;


    /**
     * The subject of the end-user.
     */
    private String subject;


    /**
     * The session ID.
     */
    private String sessionId;


    /**
     * Get the client identifier.
     * This request parameter is mandatory.
     *
     * @return
     *         The client identifier.
     */
    public String getClientIdentifier()
    {
        return clientIdentifier;
    }


    /**
     * Set the client identifier.
     * This request parameter is mandatory.
     *
     * @param identifier
     *         The client identifier.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelLogoutTokenRequest setClientIdentifier(String identifier)
    {
        this.clientIdentifier = identifier;

        return this;
    }


    /**
     * Get the subject of the end-user. At least one of this {@code subject}
     * request parameter or the {@code sessionId} request parameter is
     * required.
     *
     * <p>
     * This value is used as the value of the {@code sub} claim in the
     * Logout Token.
     * </p>
     *
     * @return
     *         The subject of the end-user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject of the end-user. At least one of this {@code subject}
     * request parameter or the {@code sessionId} request parameter is
     * required.
     *
     * <p>
     * This value is used as the value of the {@code sub} claim in the
     * Logout Token.
     * </p>
     *
     * @param subject
     *         The subject of the end-user.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelLogoutTokenRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the session ID. At least one of this {@code sessionId} request
     * parameter or the {@code subject} request parameter is required.
     *
     * <p>
     * This value is used as the value of the {@code sid} claim in the
     * Logout Token.
     * </p>
     *
     * <p>
     * If the <code>backchannel_<wbr>logout_<wbr>session_<wbr>supported</code>
     * server metadata parameter of your server is {@code true} (= if the
     * <code>backchannel<wbr>Logout<wbr>Session<wbr>Supported</code> property
     * of your {@link Service} is {@code true}), this request parameter should
     * be included.
     * </p>
     *
     * @return
     *         The session ID.
     */
    public String getSessionId()
    {
        return sessionId;
    }


    /**
     * Set the session ID. At least one of this {@code sessionId} request
     * parameter or the {@code subject} request parameter is required.
     *
     * <p>
     * This value is used as the value of the {@code sid} claim in the
     * Logout Token.
     * </p>
     *
     * <p>
     * If the <code>backchannel_<wbr>logout_<wbr>session_<wbr>supported</code>
     * server metadata parameter of your server is {@code true} (= if the
     * <code>backchannel<wbr>Logout<wbr>Session<wbr>Supported</code> property
     * of your {@link Service} is {@code true}), this request parameter should
     * be included.
     * </p>
     *
     * @param sessionId
     *         The session ID.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelLogoutTokenRequest setSessionId(String sessionId)
    {
        this.sessionId = sessionId;

        return this;
    }
}
