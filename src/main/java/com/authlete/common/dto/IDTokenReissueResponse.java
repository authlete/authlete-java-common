/*
 * Copyright (C) 2023 Authlete, Inc.
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
 * A response from Authlete's {@code /idtoken/reissue} API.
 *
 * <p>
 * A response from the {@code /idtoken/reissue} API can be mapped to this
 * class. The API caller should extract the value of the "{@code action}"
 * parameter from the API response and take the next action based on the
 * value of the parameter.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#OK OK}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#OK OK} means that an ID token has
 * been reissued successfully. In this case, the implementation of the token
 * endpoint should return a successful response to the client application.
 * The HTTP status code and the content type of the response should be 200
 * and {@code application/json}, respectively. The value of the
 * {@code responseContent} parameter can be used as the message body of the
 * response.
 *
 * <pre>
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * (Put the value of the "responseContent" parameter here.)
 * </pre>
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
 * <pre>
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
 * @since 3.68
 * @since Authlete 2.3.8
 *
 * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#RefreshTokens"
 *      >OpenID Connect Core 1.0, 12.2. Successful Refresh Response</a>
 *
 * @see IDTokenReissueRequest
 */
public class IDTokenReissueResponse extends ApiResponse
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
    public IDTokenReissueResponse setAction(Action action)
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
    public IDTokenReissueResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the reissued ID token.
     *
     * @return
     *         The reissued ID token in the JWS compact serialization format.
     */
    public String getIdToken()
    {
        return idToken;
    }


    /**
     * Set the reissued ID token.
     *
     * @param idToken
     *         The reissued ID token in the JWS compact serialization format.
     *
     * @return
     *         {@code this} object.
     */
    public IDTokenReissueResponse setIdToken(String idToken)
    {
        this.idToken = idToken;

        return this;
    }
}
