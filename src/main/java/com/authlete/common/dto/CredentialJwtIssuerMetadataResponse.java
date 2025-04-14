/*
 * Copyright (C) 2023 Authlete, Inc.
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
 * Response from Authlete's {@code /vci/jwtissuer} API.
 *
 * <p>
 * The Authlete API is supposed to be called from within the implementation of
 * the JWT VC issuer metadata endpoint ({@code /.well-known/jwt-vc-issuer}) of
 * the credential issuer.
 * </p>
 *
 * <p>
 * Authlete's {@code /vci/jwtissuer} API returns JSON which can be mapped to
 * this class. The credential issuer implementation should retrieve the value
 * of the <code>{@link #getAction() action}</code> response parameter from
 * the API response and take the following steps according to the value.
 * </p>
 *
 * <h3><code>OK</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#OK OK}</code>, it means that Authlete
 * could prepare the metadata successfully.
 * <p>
 *
 * </p>
 * In this case, the implementation of the JWT issuer metadata endpoint
 * ({@code /.well-known/jwt-vc-issuer}) of the credential issuer should return
 * an HTTP response with the HTTP status code "{@code 200 OK}" and the content
 * type "{@code application/json}". The message body of the response has been
 * prepared by Authlete's {@code /vci/jwtissuer} API and it is available as
 * the <code>{@link #getResponseContent() responseContent}</code> response
 * parameter.
 * </p>
 *
 * <p>
 * The implementation of the JWT issuer metadata endpoint can construct an
 * HTTP response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 200 OK
 * Content-Type: application/json
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
 *
 * <h3><code>NOT_FOUND</code></h3>
 *
 * <p>
 * When the value of the <code>{@link #getAction() action}</code> response
 * parameter is <code>{@link Action#NOT_FOUND NOT_FOUND}</code>, it means
 * that the service configuration has not enabled the feature of Verifiable
 * Credentials and so the JWT issuer metadata endpoint should not be accessed.
 * </p>
 *
 * </p>
 * In this case, the implementation of the JWT issuer metadata endpoint of the
 * credential issuer should return an HTTP response with the HTTP status code
 * "{@code 404 Not Found}" and the content type "{@code application/json}".
 * The message body (= error information in the JSON format) of the response
 * has been prepared by Authlete's {@code /vci/jwtissuer} API and it is available
 * as the <code>{@link #getResponseContent() responseContent}</code> response
 * parameter.
 * </p>
 *
 * <p>
 * The implementation of the JWT issuer metadata endpoint can construct an HTTP
 * response by doing like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * 404 Not Found
 * Content-Type: application/json
 * (Other HTTP headers)
 *
 * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
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
 * In this case, a simple implementation of the JWT issuer metadata endpoint
 * would return an HTTP response with the HTTP status code
 * "{@code 500 Internal Server Error}" and the content type
 * "{@code application/json}". The message body (= error information in the
 * JSON format) of the response has been prepared by Authlete's
 * {@code /vci/jwtissuer} API and it is available as the
 * <code>{@link #getResponseContent() responseContent}</code> response parameter.
 * </p>
 *
 * <p>
 * Such simple implementation of the JWT issuer metadata endpoint can construct
 * an HTTP response by doing like below.
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
 * However, in real commercial deployments, it is rare for a credential issuer
 * to return "{@code 500 Internal Server Error}" when it encounters an
 * unexpected internal error. It's up to implementations of credential issuers
 * what they actually return in the case of internal server error.
 * </p>
 *
 * <p>
 * NOTE: The well-known path has been changed from {@code /.well-known/jwt-issuer}
 * to {@code /.well-known/jwt-vc-issuer} by a breaking change of the SD-JWT VC
 * specification.
 * </p>
 *
 * @since 3.79
 * @since Authlete 3.0
 *
 * @see CredentialJwtIssuerMetadataRequest
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-sd-jwt-vc/"
 *      >SD-JWT-based Verifiable Credentials (SD-JWT VC)</a>
 */
public class CredentialJwtIssuerMetadataResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the implementation of the JWT issuer metadata
     * endpoint ({@code /.well-known/jwt-vc-issuer}) should take after getting
     * a response from Authlete's {@code /vci/jwtissuer} API.
     *
     * @since 3.79
     * @since Authlete 3.0
     */
    public enum Action
    {
        /**
         * JWT issuer metadata has been prepared successfully. The
         * implementation of the JWT issuer metadata endpoint should return
         * an HTTP response with the HTTP status code "{@code 200 OK}" and
         * the content type "{@code application/json}".
         */
        OK,

        /**
         * The feature of Verifiable Credentials is not enabled. The
         * implementation of the JWT issuer metadata endpoint should return
         * an HTTP response with the HTTP status code "{@code 404 Not Found}"
         * and the content type "{@code application/json}" to indicate that
         * the endpoint should not be accessed.
         */
        NOT_FOUND,

        /**
         * An unexpected error occurred on Authlete side or the service has
         * not been set up properly yet. A simple implementation of the JWT
         * issuer metadata endpoint would return an HTTP response with the
         * HTTP status code "{@code 500 Internal Server Error}" and the
         * content type "{@code application/json}".
         */
        INTERNAL_SERVER_ERROR,
        ;
    }


    private Action action;
    private String responseContent;


    /**
     * Get the next action that the implementation of the JWT issuer metadata
     * endpoint should take after getting a response from Authlete's
     * {@code /vci/jwtissuer} API.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the JWT issuer metadata
     * endpoint should take after getting a response from Authlete's
     * {@code /vci/jwtissuer} API.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialJwtIssuerMetadataResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content that the implementation of the JWT issuer metadata
     * endpoint should use when it constructs a response.
     *
     * <p>
     * In the successful case, the content is JSON like below.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "issuer": "{@link Service}.{@link Service#getCredentialIssuerMetadata()
     *              getCredentialIssuerMetadata()}.{@link CredentialIssuerMetadata#getCredentialIssuer()
     *              getCredentialIssuer()}",
     *   "jwks_uri": "{@link Service}.{@link Service#getCredentialJwksUri()
     *              getCredentialJwksUri()}"
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         The response content in the JSON format.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content that the implementation of the JWT issuer metadata
     * endpoint should use when it constructs a response.
     *
     * <p>
     * In the successful case, the content should be JSON like below.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "issuer": "{@link Service}.{@link Service#getCredentialIssuerMetadata()
     *              getCredentialIssuerMetadata()}.{@link CredentialIssuerMetadata#getCredentialIssuer()
     *              getCredentialIssuer()}",
     *   "jwks_uri": "{@link Service}.{@link Service#getCredentialJwksUri()
     *              getCredentialJwksUri()}"
     * }
     * </pre>
     * </blockquote>
     *
     * @param content
     *         The response content in the JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialJwtIssuerMetadataResponse setResponseContent(String content)
    {
        this.responseContent = content;

        return this;
    }
}
