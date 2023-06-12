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
 * Response from the {@code /vci/single/parse} API.
 *
 * <p>
 * The response from the Authlete API is a JSON object and can be mapped to
 * this class. The API caller should retrieve the value of the {@code action}
 * parameter from the API response and take one of the following actions
 * accordingly.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#OK OK}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#OK OK} means that the credential
 * request is valid. In this case, the implementation of the credential
 * endpoint should call the {@code /vci/single/issue} API in order to issue
 * a verifiable credential or a transaction ID and generate a response to
 * the verifiable credential holder.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#BAD_REQUEST BAD_REQUEST}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#BAD_REQUEST BAD_REQUEST} means that
 * the credential request is invalid. In this case, the implementation of the
 * credential endpoint should return an error response to the verifiable
 * credential holder. The HTTP status code and the content type of the error
 * response should be 400 and {@code application/json}, respectively. The
 * value of the {@code responseContent} parameter can be used as the message
 * body of the error response as it conforms to the specification of
 * "Credential Error Response".
 * </p>
 *
 * <pre>
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#UNAUTHORIZED UNAUTHORIZED}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#UNAUTHORIZED UNAUTHORIZED} means
 * that the access token is invalid. In this case, the implementation of the
 * credential endpoint should return an error response to the verifiable
 * credential holder. The HTTP status code of the error response should be
 * 401. The value of the {@code responseContent} parameter can be used as
 * the value of the {@code WWW-Authenticate} HTTP header of the error response.
 * </p>
 *
 * <pre>
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <p>
 * Note that the implementation of the credential endpoint should call the
 * {@code /auth/introspection} API to check whether the access token is valid
 * BEFORE calling the {@code /vci/single/parse} API. The validation on the
 * access token by the {@code /vci/single/parse} API is limited and not
 * exhaustive. For example, the {@code /vci/single/parse} API does not check
 * certificate binding (<a href="https://www.rfc-editor.org/rfc/rfc8705.html"
 * >RFC 8705</a>).
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#FORBIDDEN FORBIDDEN}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#FORBIDDEN FORBIDDEN} means that
 * the use of the Authlete API is forbidden. In this case, the implementation
 * of the credential endpoint should return an error response to the verifiable
 * credential holder. The HTTP status code and the content type of the error
 * response should be 403 and {@code application/json}, respectively. The
 * value of the {@code responseContent} parameter can be used as the message
 * body of the error response as it conforms to the specification of
 * "Credential Error Response".
 * </p>
 *
 * <pre>
 * HTTP/1.1 403 Forbidden
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <p>
 * Note that this happens either when the {@link Service#isVerifiableCredentialsEnabled()
 * verifiableCredentialsEnabled} property of the {@link Service service} is
 * false or when the Authlete server does not support the feature of
 * "Verifiable Credentials". In either case, this "forbidden" issue should
 * be solved before the service is deployed in a production environment.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}
 * means that something wrong happened on Authlete side. In this case, the
 * implementation of the credential endpoint should return an error response
 * to the verifiable credential holder. The HTTP status code and the content
 * type of the error response should be 500 and {@code application/json},
 * respectively. The value of the {@code responseContent} parameter can be
 * used as the message body of the error response as it conforms to the
 * specification of "Credential Error Response".
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
 * @since 3.66
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialSingleParseResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    public enum Action
    {
        /**
         * The credential request is valid.
         */
        OK,

        /**
         * The credential request is invalid.
         */
        BAD_REQUEST,

        /**
         * The credential request does not contain the access token or
         * the access token is invalid.
         */
        UNAUTHORIZED,

        /**
         * The feature of Verifiable Credentials is not enabled in the service
         * configuration.
         */
        FORBIDDEN,

        /**
         * An error occurred on Authlete side.
         */
        INTERNAL_SERVER_ERROR,
    }


    /**
     * The next action that the credential endpoint should take.
     */
    private Action action;


    /**
     * The content of the response to the verifiable credential holder.
     */
    private String responseContent;


    /**
     * Information about the credential request.
     */
    private CredentialRequestInfo info;


    /**
     * Get the next action that the credential endpoint should take.
     *
     * @return
     *         The next action that the credential endpoint should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the credential endpoint should take.
     *
     * @param action
     *         The next action that the credential endpoint should take.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleParseResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content of the response that should be returned to the
     * verifiable credential holder. The format varies depending on the value
     * of the {@code action} parameter.
     *
     * <p>
     * When the value of the {@code action} parameter is {@link Action#OK OK},
     * the value of this parameter is null.
     * </p>
     *
     * <p>
     * When the value of the {@code action} parameter is {@link Action#UNAUTHORIZED
     * UNAUTHORIZED}, the value of this parameter is a string suitable as the
     * value of the {@code WWW-Authenticate} HTTP header.
     * </p>
     *
     * <p>
     * In other error cases, the value of this parameter is JSON that conforms
     * to the specification of "Credential Error Response".
     * </p>
     *
     * @return
     *         The content of the response that should be returned to the
     *         verifiable credential holder.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content of the response that should be returned to the
     * verifiable credential holder. The format varies depending on the value
     * of the {@code action} parameter.
     *
     * <p>
     * When the value of the {@code action} parameter is {@link Action#OK OK},
     * the value of this parameter should be null.
     * </p>
     *
     * <p>
     * When the value of the {@code action} parameter is {@link Action#UNAUTHORIZED
     * UNAUTHORIZED}, the value of this parameter should be a string suitable
     * as the value of the {@code WWW-Authenticate} HTTP header.
     * </p>
     *
     * <p>
     * In other error cases, the value of this parameter should be JSON that
     * conforms to the specification of "Credential Error Response".
     * </p>
     *
     * @param content
     *         The content of the response that should be returned to the
     *         verifiable credential holder.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleParseResponse setResponseContent(String content)
    {
        this.responseContent = content;

        return this;
    }


    /**
     * Get information about the credential request.
     *
     * @return
     *         Information about the credential request.
     */
    public CredentialRequestInfo getInfo()
    {
        return info;
    }


    /**
     * Set information about the credential request.
     *
     * @param info
     *         Information about the credential request.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleParseResponse setInfo(CredentialRequestInfo info)
    {
        this.info = info;

        return this;
    }
}
