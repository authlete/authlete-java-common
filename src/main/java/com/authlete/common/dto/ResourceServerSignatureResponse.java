/*
 * Copyright (C) 2022 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.authlete.common.dto;


/**
 * Response from Authlete's {@code /api/rs/sign} API.
 *
 * <p>
 * Authlete's {@code /api/rs/sign} API returns JSON which can be mapped to
 * this class. The resource server implementation should retrieve the value
 * of {@code action} from the response and take the following steps according
 * to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code OK}, it means that the
 * response message has been successfully signed.
 * </p>
 *
 * <p>
 * The resource server implementation should generate a response to the
 * client application with its intended response code and, if applicable,
 * message payload.
 * </p>
 *
 * <p>
 * The resource server implementation should add the headers in this response
 * object to the HTTP response message before returning it to the client.
 * </p>
 *
 * <blockquote>
 * <dl>
 * <dt><b>{@link #getSignature()}</b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The serialized value for the {@code Signature} header applied to the
 * response.
 * </p>
 * </dd>
 *
 * <dt><b>{@link #getSignatureInput()}</b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The serialized value for the {@code Signature-Input} header applied to the
 * response.
 * </p>
 * </dd>
 *
 * <dt><b>{@link #getContentDigest()}</b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The serialized value for the {@code Content-Digest} header applied to the
 * response. This value is only returned if a {@code message} was passed
 * to the request, otherwise it is {@code null}.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * <p>
 * The following illustrates the response which the resource server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 * Signature: <i>(The value returned from {@link #getSignature()})</i>
 * Signature-Input: <i>(The value returned from {@link #getSignatureInput()})</i>
 * </pre>
 *
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code BAD_REQUEST}, it means that the
 * request was wrong.
 * </p>
 *
 * <p>
 * The resource server implementation should generate a response to the
 * client application with {@code 400 Bad Request}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response
 * if the resource server wants to and it makes sense for the API at the
 * resource server.
 * </p>
 * </dd>
 *
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code INTERNAL_SERVER_ERROR}, it means
 * that the API call from the resource server implementation was wrong or
 * that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from a viewpoint of the client application, it is an error
 * on the server side. Therefore, the resource server implementation
 * should generate a response to the client application with
 * {@code 500 Internal Server Error}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response
 * if the resource server wants to and it makes sense for the API at the
 * resource server.
 * </p>
 * <br/>
 * </dd>
 * </dl>
 *
 * @since 3.38
 * @since Authlete 2.3
 */
public class ResourceServerSignatureResponse extends ApiResponse
{

    private static final long serialVersionUID = 1L;


    /**
     * The next action the resource server implementation should take.
     */
    public enum Action
    {
        /**
         * The request from the service implementation was wrong or
         * an error occurred in Authlete. The RS implementation
         * should return {@code "500 Internal Server Error"} to the
         * client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The request does not contain the required parameters. The RS
         * implementation should return {@code "400 Bad Request"} to
         * the client application.
         */
        BAD_REQUEST,

        /**
         * The signature was successfully applied to the request. The RS
         * implementation should add the response headers into the response
         * to the client application.
         */
        OK
    }


    private static final String SUMMARY_FORMAT = "action=%s, signatureInput=%s";

    /**
     * The action to take.
     */
    private Action action;

    /**
     * The {@code Signature} header value to add to the response message.
     */
    private String signature;

    /**
     * The {@code Signature-Input} header value to add to the response message.
     */
    private String signatureInput;

    /**
     * The {@code Content-Digest} header value to add to the response message.
     */
    private String contentDigest;


    /**
     * Get the {@code Signature} header value to add to the response message.
     *
     * @return
     *         The serialized header value.
     */
    public String getSignature()
    {
        return signature;
    }


    /**
     * Set the {@code Signature} header value to add to the response message.
     *
     * @param signature
     *         The serialized header value.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureResponse setSignature(String signature)
    {
        this.signature = signature;
        return this;
    }


    /**
     * Get the {@code Signature-Input} header value to add to the response message.
     *
     * @return
     *         The serialized header value.
     */
    public String getSignatureInput()
    {
        return signatureInput;
    }


    /**
     * Set the {@code Signature-Input} header value to add to the response message.
     *
     * @param signatureInput
     *         The serialized header value.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureResponse setSignatureInput(String signatureInput)
    {
        this.signatureInput = signatureInput;
        return this;
    }


    /**
     * Get the {@code Content-Digest} header value to add to the response message.
     *
     * @return
     *         The serialized header value.
     */
    public String getContentDigest()
    {
        return contentDigest;
    }


    /**
     * Set the {@code Content-Digest} header value to add to the response message.
     *
     * @param contentDigest
     *         The serialized header value.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureResponse setContentDigest(String contentDigest)
    {
        this.contentDigest = contentDigest;
        return this;
    }


    /**
     * Get the next action that the resource server should take.
     *
     * @return
     *         The action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the resource server should take.
     *
     * @param action
     *         The action.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureResponse setAction(Action action)
    {
        this.action = action;
        return this;
    }


    /**
     * Get the summary of this instance.
     *
     * @return
     *         The summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT, action, signatureInput);
    }
}
