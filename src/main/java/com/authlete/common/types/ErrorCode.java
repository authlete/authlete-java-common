/*
 * Copyright (C) 2014-2022 Authlete, Inc.
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
package com.authlete.common.types;


/**
 * Values for {@code error} from OAuth 2.0 endpoints.
 *
 * <p>
 * Some error codes are not used by Authlete. For example, because
 * Authlete supports the {@code request} parameter and the {@code
 * request_uri} parameter, {@link #request_not_supported} and {@link
 * #request_uri_not_supported} are not used.
 * </p>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.1.2.1"
 *      >RFC 6749 (OAuth 2.0), 4.1.2.1. Error Response</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.2.2.1"
 *      >RFC 6749 (OAuth 2.0), 4.2.2.1. Error Response</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-5.2"
 *      >RFC 6749 (OAuth 2.0), 5.2. Error Response</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6750.html#section-3.1"
 *      >RFC 6750 (OAuth 2.0 Bearer Token Usage), 3.1. Error Codes</a>
 *
 * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#AuthError"
 *      >OpenID Connect Core 1.0, 3.1.2.6. Authorization Error Response</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html#section-3.2.2"
 *      >RFC 7591, 3.2.2. Client Registration Error Response</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ErrorCode
{
    /**
     * The resource owner or authorization server denied the
     * request.
     */
    access_denied,


    /**
     * The End-User is REQUIRED to select a session at the
     * Authorization Server. The End-User MAY be authenticated
     * at the Authorization Server with different associated
     * accounts, but the End-User did not select a session.
     * This error MAY be returned when the {@code prompt}
     * parameter value in the Authentication Request is
     * {@code none}, but the Authentication Request cannot be
     * completed without displaying a user interface to prompt
     * for a session to use.
     */
    account_selection_required,


    /**
     * The Authorization Server requires End-User consent.
     * This error MAY be returned when the {@code prompt}
     * parameter value in the Authentication Request is
     * {@code none}, but the Authentication Request cannot
     * be completed without displaying a user interface for
     * End-User consent.
     */
    consent_required,


    /**
     * The request requires higher privileges than provided by the
     * access token.
     */
    insufficient_scope,


    /**
     * The Authorization Server requires End-User interaction of
     * some form to proceed. This error MAY be returned when the
     * {@code prompt} parameter value in the Authentication Request
     * is {@code none}, but the Authentication Request cannot be
     * completed without displaying a user interface for End-User
     * interaction.
     */
    interaction_required,


    /**
     * Client authentication failed (e.g., unknown client, no
     * client authentication included, or unsupported
     * authentication method).  The authorization server MAY
     * return an HTTP 401 (Unauthorized) status code to indicate
     * which HTTP authentication schemes are supported.  If the
     * client attempted to authenticate via the "Authorization"
     * request header field, the authorization server MUST
     * respond with an HTTP 401 (Unauthorized) status code and
     * include the "WWW-Authenticate" response header field
     * matching the authentication scheme used by the client.
     */
    invalid_client,


    /**
     * The provided authorization grant (e.g., authorization
     * code, resource owner credentials) or refresh token is
     * invalid, expired, revoked, does not match the redirection
     * URI used in the authorization request, or was issued to
     * another client.
     */
    invalid_grant,


    /**
     * The request is missing a required parameter, includes an
     * invalid parameter value, includes a parameter more than
     * once, or is otherwise malformed.
     */
    invalid_request,


    /**
     * The {@code request_uri} in the Authorization Request
     * returns an error or contains invalid data.
     */
    invalid_request_uri,


    /**
     * The {@code request} parameter contains an invalid Request Object.
     */
    invalid_request_object,


    /**
     * The requested scope is invalid, unknown, or malformed.
     */
    invalid_scope,


    /**
     * The access token provided is expired, revoked, malformed, or
     * invalid for other reasons.
     */
    invalid_token,


    /**
     * The Authorization Server requires End-User authentication.
     * This error MAY be returned when the {@code prompt} parameter
     * value in the Authentication Request is {@code none}, but the
     * Authentication Request cannot be completed without displaying
     * a user interface for End-User authentication.
     */
    login_required,


    /**
     * The OP does not support use of the {@code registration}
     * parameter defined in <a href="https://openid.net/specs/openid-connect-core-1_0.html#RegistrationParameter"
     * >Section 7.2.1</a>.
     */
    registration_not_supported,


    /**
     * The OP does not support use of the {@code request} parameter
     * defined in <a href="https://openid.net/specs/openid-connect-core-1_0.html#JWTRequests"
     * >Section 6</a>.
     */
    request_not_supported,


    /**
     * The OP does not support use of the {@code request_uri} parameter
     * defined in <a href="https://openid.net/specs/openid-connect-core-1_0.html#JWTRequests"
     * >Section 6</a>.
     */
    request_uri_not_supported,


    /**
     * The authorization server encountered an unexpected
     * condition that prevented it from fulfilling the request.
     * (This error code is needed because a 500 Internal Server
     * Error HTTP status code cannot be returned to the client
     * via an HTTP redirect.)
     */
    server_error,


    /**
     * The authorization server is currently unable to handle
     * the request due to a temporary overloading or maintenance
     * of the server. (This error code is needed because a 503
     * Service Unavailable HTTP status code cannot be returned
     * to the client via an HTTP redirect.)
     */
    temporarily_unavailable,


    /**
     * The client is not authorized to request an authorization
     * code or an access token using this method.
     */
    unauthorized_client,


    /**
     * The authorization grant type is not supported by the
     * authorization server.
     */
    unsupported_grant_type,


    /**
     * The authorization server does not support obtaining an
     * authorization code or an access token using this method.
     */
    unsupported_response_type,


    /**
     * The value of one or more redirect URIs is invalid.
     *
     * <p>
     * See "<a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html#section-3.2.2">3.2.2.
     * Client Registration Error Response</a>" in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591</a> for details.
     * </p>
     *
     * @since 2.22
     */
    invalid_redirect_uri,


    /**
     * The value of one of the client metadata fields is invalid and the
     * server has rejected the client registration request.
     *
     * <p>
     * See "<a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html#section-3.2.2">3.2.2.
     * Client Registration Error Response</a>" in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591</a> for details.
     * </p>
     *
     * @since 2.22
     */
    invalid_client_metadata,


    /**
     * The software statement presented is invalid.
     *
     * <p>
     * See "<a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html#section-3.2.2">3.2.2.
     * Client Registration Error Response</a>" in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591</a> for details.
     * </p>
     *
     * @since 2.22
     */
    invalid_software_statement,


    /**
     * The software statement presented is not approved for use by this
     * authorization server.
     *
     * <p>
     * See "<a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html#section-3.2.2">3.2.2.
     * Client Registration Error Response</a>" in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591</a> for details.
     * </p>
     *
     * @since 2.22
     */
    unapproved_software_statement,


    /**
     * The provided {@code login_hint_token} has expired.
     *
     * <p>
     * This error code is defined in the CIBA Core specification.
     * </p>
     *
     * @since 2.32
     */
    expired_login_hint_token,


    /**
     * The OpenID provider is not able to identify which end-user the client
     * wished to be authenticated by means of the hint provided in the
     * request ({@code login_hint_token}, {@code id_token_hint} or
     * {@code login_hint}).
     *
     * <p>
     * This error code is defined in the CIBA Core specification.
     * </p>
     *
     * @since 2.32
     */
    unknown_user_id,


    /**
     * A user code is not included in the backchannel authentication request.
     *
     * <p>
     * This error code is defined in the CIBA Core specification.
     * </p>
     *
     * @since 2.32
     */
    missing_user_code,


    /**
     * The provided user code is invalid.
     *
     * <p>
     * This error code is defined in the CIBA Core specification.
     * </p>
     *
     * @since 2.32
     */
    invalid_user_code,


    /**
     * The binding message is invalid or unacceptable for use in the context
     * of the given request.
     *
     * <p>
     * This error code is defined in the CIBA Core specification.
     * </p>
     *
     * @since 2.40
     */
    invalid_binding_message,


    /**
     * The authorization request is still pending as the end-user hasn't yet
     * been authenticated.
     *
     * <p>
     * This error code is defined in the CIBA Core specification and the
     * Device Flow specification.
     * </p>
     *
     * @since 2.32
     */
    authorization_pending,


    /**
     * A variant of {@code authorization_pending}, the authorization request
     * is still pending and polling should continue, but the interval should
     * be increased.
     *
     * <p>
     * This error code is defined in the CIBA Core specification and the
     * Device Flow specification.
     * </p>
     *
     * @since 2.32
     */
    slow_down,


    /**
     * The token has expired.
     *
     * <p>
     * In the context of CIBA, the token means {@code auth_req_id}. In the
     * context of Device Flow, the token means {@code device_code}.
     * </p>
     *
     * @since 2.32
     */
    expired_token,


    /**
     * The OpenID Provider encountered an unexpected condition that prevented
     * it from successfully completing the transaction. This general case error
     * code can be used to inform the Client that the CIBA transaction was
     * unsuccessful for reasons other than those explicitly defined by
     * {@code access_denied} and {@code expired_token}.
     *
     * @since 2.36
     */
    transaction_failed,


    /**
     * The {@code type} field of one or more elements in the
     * {@code authorization_details} request parameter is not supported.
     * Details are defined in <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @since 2.56
     */
    invalid_authorization_details,


    /**
     * The requested resource is invalid, missing, unknown, or malformed.
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc8707.html">RFC 8707</a>
     * (Resource Indicators for OAuth 2.0) for details.
     * </p>
     *
     * @since 2.62
     */
    invalid_target,


    /**
     * The DPoP proof JWT included in the {@code DPoP} HTTP header is invalid.
     *
     * <p>
     * See "<a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-dpop/"
     * >OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer
     * (DPoP)"</a> for details.
     * </p>
     *
     * @since 2.74
     */
    invalid_dpop_proof,


    /**
     * The {@code grant_id} included in the request is unknown or invalid.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    invalid_grant_id,


    /**
     * No trusted Trust Anchor could be found.
     *
     * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
     *      >OpenID Connect Federation 1.0</a>
     *
     * @since 3.33
     * @since Authlete 2.3
     */
    missing_trust_anchor,


    /**
     * Trust chain validation failed.
     *
     * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
     *      >OpenID Connect Federation 1.0</a>
     *
     * @since 3.33
     * @since Authlete 2.3
     */
    validation_failed,
    ;
}
