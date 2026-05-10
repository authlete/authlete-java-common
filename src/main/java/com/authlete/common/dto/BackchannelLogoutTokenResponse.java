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


import java.net.URI;


/**
 * Response from Authlete's
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
 * <p>
 * If the value of the {@code action} parameter in the response from the
 * Authlete API is {@link Action#OK OK}, it indicates that a Logout Token has
 * been generated successfully. In this case, your server is expected to send
 * a <a href=
 * "https://openid.net/specs/openid-connect-backchannel-1_0.html#BCRequest"
 * >Back-Channel Logout Request</a> containing the generated Logout Token to
 * the client's Backchannel Logout URI.
 * <p>
 *
 * <p>
 * The {@code logoutToken} parameter in the API response contains the generated
 * Logout Token, and the <code>backchannel<wbr>Logout<wbr>Uri</code> parameter
 * contains the client's Back-Channel Logout URI, which corresponds to the
 * <code>backchannel_<wbr>logout_<wbr>uri</code> client metadata parameter.
 * Using these values, the Back-Channel Logout Request can be constructed as
 * follows.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * POST {@link #getBackchannelLogoutUri()} HTTP/1.1
 * Content-Type: application/x-www-form-urlencoded
 * Content-Length: ...
 *
 * <i>logout_token={@link #getLogoutToken()}</i></pre>
 * <br/>
 *
 * <p>
 * Note that the Authlete API does not verify whether the client's
 * <code>backchannel_<wbr>logout_<wbr>uri</code> metadata parameter is
 * configured properly. Therefore, the value of the
 * <code>backchannel<wbr>Logout<wbr>Uri</code> response parameter may not be
 * a valid HTTP-accessible URL. It may even be {@code null}.
 * </p>
 *
 * <p>
 * Also note that the API call does not revoke any refresh tokens. If necessary,
 * the client itself should trigger refresh token revocation (e.g., by calling
 * the revocation endpoint).
 * </p>
 *
 * @see <a href="https://openid.net/specs/openid-connect-backchannel-1_0.html">
 *      OpenID Connect Back-Channel Logout 1.0</a>
 *
 * @since 4.43
 * @since Authlete 3.0.32
 */
public class BackchannelLogoutTokenResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the API caller should take.
     */
    public enum Action
    {
        /**
         * A Logout Token has been generated successfully. The API caller is
         * expected to send a Back-Channel Logout Request to the client.
         */
        OK,


        /**
         * There is an error on Authlete's side, such as a database access error.
         */
        SERVER_ERROR,


        /**
         * The API call is invalid. For example, the mandatory request parameter
         * {@code clientIdentifier} is missing.
         */
        CALLER_ERROR,
    }


    /**
     * The next action that the API caller should take.
     */
    private Action action;


    /**
     * The generated Logout Token.
     */
    private String logoutToken;


    /**
     * The client's Back-Channel Logout URI.
     */
    private URI backchannelLogoutUri;


    /**
     * Get the next action that the API caller should take. In practice, this
     * value represents the result of the API call.
     *
     * @return
     *         The next action that the API caller should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that API caller should take. In practice, this
     * value represents the result of the API call.
     *
     * @param action
     *         The next action that the API caller should take.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelLogoutTokenResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the issued <a href=
     * "https://openid.net/specs/openid-connect-backchannel-1_0.html#LogoutToken"
     * >Logout Token</a>.
     *
     * @return
     *         The issued Logout Token.
     */
    public String getLogoutToken()
    {
        return logoutToken;
    }


    /**
     * Set the issued <a href=
     * "https://openid.net/specs/openid-connect-backchannel-1_0.html#LogoutToken"
     * >Logout Token</a>.
     *
     * @param logoutToken
     *         The issued Logout Token.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelLogoutTokenResponse setLogoutToken(String logoutToken)
    {
        this.logoutToken = logoutToken;

        return this;
    }


    /**
     * Get the client's Back-Channel Logout URI, which corresponds to the
     * <code>backchannel_<wbr>logout_<wbr>uri</code> client metadata
     * parameter defined in the <a href=
     * "https://openid.net/specs/openid-connect-backchannel-1_0.html">OpenID
     * Connect Back-Channel Logout 1&#x2E;0</a> specification.
     *
     * @return
     *         The client's Back-Channel Logout URI.
     */
    public URI getBackchannelLogoutUri()
    {
        return backchannelLogoutUri;
    }


    /**
     * Set the client's Back-Channel Logout URI, which corresponds to the
     * <code>backchannel_<wbr>logout_<wbr>uri</code> client metadata
     * parameter defined in the <a href=
     * "https://openid.net/specs/openid-connect-backchannel-1_0.html">OpenID
     * Connect Back-Channel Logout 1&#x2E;0</a> specification.
     *
     * @param uri
     *         The client's Back-Channel Logout URI.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelLogoutTokenResponse setBackchannelLogoutUri(URI uri)
    {
        this.backchannelLogoutUri = uri;

        return this;
    }
}
