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
 * A response from Authlete's {@code /nativesso} API.
 *
 * <p>
 * A response from the {@code /nativesso} API can be mapped to this
 * class. The API caller should extract the value of the "{@code action}"
 * parameter from the API response and take the next action based on the
 * value of the parameter.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#OK OK}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#OK OK} means that a <a href=
 * "https://openid.net/specs/openid-connect-native-sso-1_0.html">Native
 * SSO</a>-compliant ID token and a token response have been prepared
 * successfully. In this case, the implementation of the token endpoint
 * should return a successful response to the client application. The
 * HTTP status code and the content type of the response should be 200
 * and {@code application/json}, respectively. The value of the
 * {@code responseContent} parameter can be used as the message body of
 * the response.
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <p>
 * The resulting message body will look like this:
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * {
 *   "access_token":      "(Access Token)",
 *   "token_type":        "(Token Type)",
 *   "expires_in":         (Lifetime in Seconds),
 *   "scope":             "(Space-separated Scopes)",
 *   "refresh_token":     "(Refresh Token)",
 *   "id_token":          "(ID Token)",
 *   "device_secret":     "(Device Secret)",
 *   "issued_token_type": "urn:ietf:params:oauth:token-type:access_token"
 * }</pre>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}
 * means that something wrong happened on Authlete side. In this case, the
 * implementation of the token endpoint should return an error response to
 * the client application. The HTTP status code and the content type of the
 * error response should be 500 and {@code application/json}, respectively.
 * The value of the {@code responseContent} parameter can be used as the
 * message body of the error response.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <p>
 * Note that, however, in real production deployments, it may be better to
 * return a vaguer error response instead of a bare one like above.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#CALLER_ERROR CALLER_ERROR}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#CALLER_ERROR CALLER_ERROR} means
 * that the API call is wrong. For example, the "{@code accessToken}" request
 * parameter is missing.
 * </p>
 *
 * <p>
 * Caller errors should be solved before the service is deployed in a
 * production environment.
 * </p>
 *
 * @since 4.18
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
 *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
 *
 * @see NativeSsoRequest
 */
public class NativeSsoResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the implementation of the token endpoint should take.
     */
    public enum Action
    {
        OK,
        INTERNAL_SERVER_ERROR,
        CALLER_ERROR,
    }


    private Action action;
    private String responseContent;
    private String idToken;


    /**
     * Get the next action that the implementation of the token endpoint
     * should take.
     *
     * @return
     *         The next action that the implementation of the token endpoint
     *         should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the token endpoint
     * should take.
     *
     * @param action
     *         The next action that the implementation of the token endpoint
     *         should take.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the response content that can be used as the message body of the
     * token response that should be returned from the token endpoint.
     *
     * @return
     *         The response content.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content that can be used as the message body of the
     * token response that should be returned from the token endpoint.
     *
     * @param responseContent
     *         The response content.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the issued ID token.
     *
     * @return
     *         The issued ID token in the JWS compact serialization format.
     */
    public String getIdToken()
    {
        return idToken;
    }


    /**
     * Set the issued ID token.
     *
     * @param idToken
     *         The issued ID token in the JWS compact serialization format.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoResponse setIdToken(String idToken)
    {
        this.idToken = idToken;

        return this;
    }
}
