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
 * A response from Authlete's {@code /vci/single/issue} API.
 *
 * <p>
 * A response from the {@code /vci/single/issue} API can be mapped to this
 * class. The API caller should extract the value of the {@code action}
 * parameter from the API response and take the next action based on the
 * value of the parameter.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#OK OK}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#OK OK} means that a credential
 * has been issued successfully. In this case, the implementation of the
 * credential endpoint should return a successful response to the request
 * sender. The HTTP status code and the content type of the response
 * should be 200 and {@code application/json}, respectively. The value of
 * the {@code responseContent} parameter can be used as the message body
 * of the response. It contains the "{@code credential}" parameter that
 * conforms to the specification of "Credential Response".
 * </p>
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
 * <h3>{@code action} = {@link Action#OK_JWT OK_JWT}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#OK_JWT OK_JWT} means that a
 * credential has been issued successfully and the credential response
 * should be encrypted. In this case, the implementation of the credential
 * endpoint should return a successful response to the request sender.
 * The HTTP status code and the content type of the response should be
 * 200 and {@code application/jwt}, respectively. The value of the
 * {@code responseContent} parameter is an encrypted JWT and can be used
 * as the message body of the response.
 * </p>
 *
 * <p>
 * The {@code OK_JWT} action is returned when the successful credential
 * response is encrypted.
 * </p>
 *
 * <pre>
 * HTTP/1.1 200 OK
 * Content-Type: application/jwt
 * Cache-Control: no-store
 *
 * (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#ACCEPTED ACCEPTED}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#ACCEPTED ACCEPTED} means that
 * a transaction ID has been issued successfully. In this case, the
 * implementation of the credential endpoint should return a successful
 * response to the request sender. The HTTP status code and the content
 * type of the response should be 202 and {@code application/json},
 * respectively. The value of the {@code responseContent} parameter can
 * be used as the message body of the response. It contains the
 * "{@code transaction_id}" parameter that conforms to the specification
 * of "Credential Response".
 * </p>
 *
 * <pre>
 * HTTP/1.1 202 Accepted
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#ACCEPTED_JWT ACCEPTED_JWT}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#ACCEPTED_JWT ACCEPTED_JWT} means
 * that a transaction ID has been issued successfully and the credential
 * response should be encrypted. In this case, the implementation of the
 * credential endpoint should return a successful response to the request
 * sender. The HTTP status code and the content type of the response should
 * be 202 and {@code application/jwt}, respectively. The value of the
 * {@code responseContent} parameter is an encrypted JWT and can be used
 * as the message body of the response.
 * </p>
 *
 * <pre>
 * HTTP/1.1 202 Accepted
 * Content-Type: application/jwt
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
 * credential endpoint should return an error response to the request sender.
 * The HTTP status code of the error response should be 401. The value of the
 * {@code responseContent} parameter can be used as the value of the
 * {@code WWW-Authenticate} HTTP header of the error response.
 * </p>
 *
 * <pre>
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: (Put the value of the "responseContent" parameter here.)
 * </pre>
 *
 * <p>
 * It is unlikely that this action is returned if the access token is the same
 * one as was passed to the {@code /vci/single/parse} API and the API had
 * returned a successful response.
 * </p>
 *
 * <hr>
 * <h3>{@code action} = {@link Action#BAD_REQUEST BAD_REQUEST}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#BAD_REQUEST BAD_REQUEST} means that
 * the original credential request is wrong. In this case, the implementation
 * of the credential endpoint should return an error response to the request
 * sender. The HTTP status code and the content type of the error response
 * should be 400 and {@code application/json}, respectively. The value of the
 * {@code responseContent} parameter can be used as the message body of the
 * error response as it conforms to the specification of "Credential Error
 * Response".
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
 * <h3>{@code action} = {@link Action#FORBIDDEN FORBIDDEN}</h3>
 *
 * <p>
 * The {@code action} value {@link Action#FORBIDDEN FORBIDDEN} means that
 * the use of the Authlete API is forbidden. In this case, the implementation
 * of the credential endpoint should return an error response to the request
 * sender. The HTTP status code and the content type of the error response
 * should be 403 and {@code application/json}, respectively. The value of the
 * {@code responseContent} parameter can be used as the message body of the
 * error response as it conforms to the specification of "Credential Error
 * Response".
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
 * to the request sender. The HTTP status code and the content type of the
 * error response should be 500 and {@code application/json}, respectively.
 * The value of the {@code responseContent} parameter can be used as the
 * message body of the error response as it conforms to the specification of
 * "Credential Error Response".
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
 * that the API call is wrong. For example, the "{@code order}" request
 * parameter is missing.
 * </p>
 *
 * <p>
 * Caller errors should be solved before the service is deployed in a
 * production environment.
 * </p>
 *
 * @since 3.67
 * @since Authlete 3.0
 */
public class CredentialSingleIssueResponse extends ApiResponse
{
    private static final long serialVersionUID = 2L;


    /**
     * The next action that the implementation of the credential endpoint
     * should take.
     */
    public enum Action
    {
        /**
         * A credential was issued successfully. The implementation of the
         * credential endpoint should return a successful response with the
         * HTTP status code "200 OK" and the content type
         * {@code application/json}.
         */
        OK,

        /**
         * A credential was issued successfully and the credential response
         * should be encrypted. The implementation of the credential endpoint
         * should return a successful response with the HTTP status code
         * "200 OK" and the content type {@code application/jwt}.
         *
         * @since 3.86
         */
        OK_JWT,

        /**
         * A transaction ID was issued successfully. The implementation of
         * the credential endpoint should return a successful response with
         * the HTTP status code "202 Accepted" and the content type
         * {@code application/json}.
         */
        ACCEPTED,

        /**
         * A transaction ID was issued successfully and the credential response
         * should be encrypted. The implementation of the credential endpoint
         * should return a successful response with the HTTP status code
         * "202 Accepted" and the content type {@code application/jwt}.
         *
         * @since 3.86
         */
        ACCEPTED_JWT,

        /**
         * The original credential request is wrong. This can happen, for
         * example, when the process for encrypting the credential response
         * with the encryption parameters specified in the credential request
         * failed.
         *
         * @since 3.86
         */
        BAD_REQUEST,

        /**
         * The API call does not contain an access token or the access token
         * is invalid.
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

        /**
         * The API call is invalid.
         */
        CALLER_ERROR,
    }


    /**
     * The next action that the implementation of the credential endpoint
     * should take.
     */
    private Action action;


    /**
     * The content of the response that the implementation of the credential
     * endpoint should return.
     */
    private String responseContent;


    /**
     * The issued transaction ID.
     */
    private String transactionId;


    /**
     * Get the next action that the implementation of the credential endpoint
     * should take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the credential endpoint
     * should take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleIssueResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content of the response that the implementation of the credential
     * endpoint should return.
     *
     * @return
     *         The content of the response returned from the credential endpoint.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content of the response that the implementation of the credential
     * endpoint should return.
     *
     * @param responseContent
     *         The content of the response returned from the credential endpoint.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleIssueResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the issued transaction ID.
     *
     * <p>
     * A transaction ID is issued when the {@code issuanceDeferred} boolean
     * flag of the credential order ({@link CredentialIssuanceOrder}) is true.
     * </p>
     *
     * <p>
     * The transaction ID is expected to be presented at the deferred credential
     * endpoint.
     * </p>
     *
     * @return
     *         The issued transaction ID.
     */
    public String getTransactionId()
    {
        return transactionId;
    }


    /**
     * Set the issued transaction ID.
     *
     * <p>
     * A transaction ID is issued when the {@code issuanceDeferred} boolean
     * flag of the credential order ({@link CredentialIssuanceOrder}) is true.
     * </p>
     *
     * <p>
     * The transaction ID is expected to be presented at the deferred credential
     * endpoint.
     * </p>
     *
     * @param transactionId
     *         The issued transaction ID.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialSingleIssueResponse setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;

        return this;
    }
}
