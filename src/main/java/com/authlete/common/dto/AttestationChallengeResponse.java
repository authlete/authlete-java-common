/*
 * Copyright (C) 2026 Authlete, Inc.
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
 * Response from Authlete's {@code /attestation/challenge} API.
 *
 * <p>
 * The Authlete API is supposed to be used from within the implementation
 * the challenge endpoint of the authorization server.
 * </p>
 *
 * <p>
 * Authlete's {@code /attestation/challenge} API returns JSON which can be
 * mapped to this class. The authorization server implementation should
 * retrieve the value of the <code>{@link #getAction() action}</code>
 * response parameter from the API response and take the following steps
 * according to the value.
 * </p>
 *
 * <h3><code>OK</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#OK OK}</code>, it means that Authlete
 * could prepare a challenge response successfully.
 * <p>
 *
 * </p>
 * In this case, the implementation of the challenge endpoint of the
 * authorization server should return an HTTP response with the HTTP status
 * code "{@code 200 OK}" and the content type "{@code application/json}".
 * The message body of the response has been prepared by Authlete's
 * {@code /attestation/challenge} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response
 * parameter.
 * </p>
 *
 * <p>
 * The implementation of the challenge endpoint can construct an HTTP response
 * by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
 *
 * <p>
 * Note that the <a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/"
 * >OAuth 2.0 Attestation-Based Client Authentication</a> specification
 * requires the response to include the {@code Cache-Control} header including
 * the value {@code no-store}.
 * </p>
 *
 * <h3><code>INTERNAL_SERVER_ERROR</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</code>,
 * it means that an unexpected error has occurred on Authlete side or the
 * service has not been set up properly yet.
 * </p>
 *
 * </p>
 * In this case, a simple implementation of the challenge endpoint would return
 * an HTTP response with the HTTP status code "{@code 500 Internal Server Error}"
 * and the content type "{@code application/json}". The message body (= error
 * information in the JSON format) of the response has been prepared by Authlete's
 * {@code /attestation/challenge} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * Such simple implementation of the challenge endpoint can construct an HTTP
 * response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 500 Internal Server Error
 * Content-Type: application/json
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
 *
 * <p>
 * However, in real commercial deployments, it is rare for an authorization
 * server to return "{@code 500 Internal Server Error}" when it encounters an
 * unexpected internal error. It's up to implementations of authorization
 * servers what they actually return in the case of internal server error.
 * </p>
 *
 * @since 4.39
 * @since Authlete 3.0.28
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/">
 *      OAuth 2.0 Attestation-Based Client Authentication</a>
 */
public class AttestationChallengeResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the implementation of the challenge endpoint
     * should take after getting a response from Authlete's
     * {@code /attestation/challenge} API.
     *
     * @since 4.39
     * @since Authlete 3.0.28
     */
    public enum Action
    {
        /**
         * A challenge response has been prepared successfully. The
         * implementation of the challenge endpoint should return an HTTP
         * response with the HTTP status code "{@code 200 OK}" and the content
         * type "{@code application/json}".
         */
        OK,

        /**
         * An unexpected error occurred on Authlete side or the service has
         * not been set up properly yet. A simple implementation of the
         * challenge endpoint would return an HTTP response with the HTTP
         * status code "{@code 500 Internal Server Error}" and the content
         * type "{@code application/json}".
         */
        INTERNAL_SERVER_ERROR,
    }


    private Action action;
    private String responseContent;
    private String attestationChallenge;


    /**
     * Get the next action that the implementation of the challenge endpoint
     * should take after getting a response from Authlete's
     * {@code /attestation/challenge} API.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the challenge endpoint
     * should take after getting a response from Authlete's
     * {@code /attestation/challenge} API.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public AttestationChallengeResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content that the implementation of the challenge endpoint should
     * use when it constructs a response.
     *
     * @return
     *         The response content in the JSON format.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content that the implementation of the challenge endpoint should
     * use when it constructs a response.
     *
     * @param content
     *         The response content in the JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public AttestationChallengeResponse setResponseContent(String content)
    {
        this.responseContent = content;

        return this;
    }


    /**
     * Get the {@code attestation_challenge} value generated by the
     * {@code /attestation/challenge} API.
     *
     * @return
     *         The generated {@code attestation_challenge} value.
     */
    public String getAttestationChallenge()
    {
        return attestationChallenge;
    }


    /**
     * Set the {@code attestation_challenge} value generated by the
     * {@code /attestation/challenge} API.
     *
     * @param challenge
     *         The generated {@code attestation_challenge} value.
     *
     * @return
     *         {@code this} object.
     */
    public AttestationChallengeResponse setAttestationChallenge(String challenge)
    {
        this.attestationChallenge = challenge;

        return this;
    }
}
