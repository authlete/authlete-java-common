/*
 * Copyright (C) 2015-2018 Authlete, Inc.
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
 * Response from Authlete's {@code /auth/userinfo/issue} API.
 *
 * <p>
 * Authlete's {@code /auth/userinfo/issue} API returns JSON which can be
 * mapped to this class. The service implementation should retrieve the
 * value of {@code "action"} from the response and take the following
 * steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the service implementation was wrong or
 * that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from the viewpoint of the client application, it is an
 * error on the server side. Therefore, the service implementation should
 * generate a response to the client application with the HTTP status of
 * {@code "500 Internal Server Error"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "BAD_REQUEST"}, it means
 * that the request from the client application does not contain an access
 * token (= the request from the service implementation to Authlete does
 * not contain {@code "token"} parameter).
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#UNAUTHORIZED UNAUTHORIZED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "UNAUTHORIZED"}, it means
 * that the access token does not exist, has expired, or is not associated
 * with any subject (= any user account).
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#FORBIDDEN FORBIDDEN}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "FORBIDDEN"}, it means
 * that the access token does not include the {@code "openid"} scope.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 403 Forbidden
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#JSON JSON}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "JSON"}, it means that the
 * access token which the client application presented is valid and a userinfo
 * response was successfully generated in the format of JSON.
 * </p>
 *
 * <p>
 * The <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfo"
 * >UserInfo Endpoint</a> of your service is expected to generate a response
 * to the client application. The content type of the response must be {@code
 * "application/json"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a userinfo response in JSON format when
 * {@code "action"} is {@code "JSON"}, so a response to the client can be
 * built like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Cache-Control: no-store
 * Pragma: no-cache
 * Content-Type: application/json;charset=UTF-8
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#JWT JWT}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "JWT"}, it means that the
 * access token which the client application presented is valid and a
 * userinfo response was successfully generated in the format of JWT
 * (JSON Web Token)
 * (<a href="https://tools.ietf.org/html/rfc7519">RFC 7519</a>).
 * </p>
 *
 * <p>
 * The <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfo"
 * >UserInfo Endpoint</a> of your service is expected to generate a response
 * to the client application. The content type of the response must be {@code
 * "application/jwt"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a userinfo response in JWT format when
 * {@code "action"} is {@code "JWT"}, so a response to the client can be
 * built like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Cache-Control: no-store
 * Pragma: no-cache
 * Content-Type: application/jwt
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 * </dl>
 *
 * @author Takahiko Kawasaki
 */
public class UserInfoIssueResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action the service implementation should take.
     */
    public enum Action
    {
        /**
         * The request from the service implementation was wrong or
         * an error occurred in Authlete. The service implementation
         * should return {@code "500 Internal Server Error"} to the
         * client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The request does not contain an access token. The service
         * implementation should return {@code "400 Bad Request"} to
         * the client application.
         */
        BAD_REQUEST,

        /**
         * The access token does not exist or has expired. The service
         * implementation should return {@code "401 Unauthorized"} to
         * the client application.
         */
        UNAUTHORIZED,

        /**
         * The access token does not cover the required scopes. To be
         * concrete, the access token does not include the {@code
         * "openid"} scope. The service implementation should return
         * {@code "403 Forbidden"} to the client application.
         */
        FORBIDDEN,

        /**
         * The access token was valid and a userinfo response was
         * generated successfully in JSON format. The service implementation
         * should return {@code "200 OK"} to the client application
         * with the content type {@code "application/json;charset=UTF-8"}.
         */
        JSON,

        /**
         * The access token was valid and a userinfo response token was
         * generated successfully in JWT format. The service implementation
         * should return {@code "200 OK"} to the client application
         * with the content type {@code "application/jwt"}.
         */
        JWT
    }


    private static final String SUMMARY_FORMAT = "action=%s, responseContent=%s";


    private Action action;
    private String responseContent;

    /**
     * The signature header of the response message.
     */
    private String signature;

    /**
     * The signature-input header of the response message
     */
    private String signatureInput;

    /**
     * The content-digest header of the response message
     */
    private String contentDigest;


    /**
     * Get the next action that the service implementation should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the service implementation should take.
     */
    public void setAction(Action action)
    {
        this.action = action;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response returned to the client application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as the entity body
     * of the response returned to the client application.
     */
    public void setResponseContent(String content)
    {
        this.responseContent = content;
    }


    public String getSignature()
    {
        return signature;
    }


    public void setSignature(String signature)
    {
        this.signature = signature;
    }


    public String getSignatureInput()
    {
        return signatureInput;
    }


    public void setSignatureInput(String signatureInput)
    {
        this.signatureInput = signatureInput;
    }


    public String getContentDigest()
    {
        return contentDigest;
    }


    public void setContentDigest(String mContentDigest)
    {
        this.contentDigest = mContentDigest;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT, action, responseContent);
    }
}
