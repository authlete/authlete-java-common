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
package com.authlete.common.dto;


import java.net.URI;
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.GrantType;
import com.authlete.common.types.TokenType;
import com.authlete.common.util.Utils;


/**
 * Response from Authlete's {@code /auth/token} API.
 *
 * <p>
 * Authlete's {@code /auth/token} API returns JSON which can
 * be mapped to this class. The service implementation should retrieve the
 * value of {@code "action"} from the response and take the following steps
 * according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INVALID_CLIENT INVALID_CLIENT}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INVALID_CLIENT"}, it means
 * that authentication of the client failed. In this case, the HTTP status
 * of the response to the client application is either {@code "400 Bad
 * Request"} or {@code "401 Unauthorized"}. This requirement comes from
 * <a href="https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749, 5.2.
 * Error Response</a>. The description about {@code "invalid_client"} shown
 * below is an excerpt from RFC 6749.
 * </p>
 * <blockquote>
 *   <dl>
 *     <dt><code>invalid_client</code></dt>
 *     <dd>
 *       <p>
 *         Client authentication failed (e.g., unknown client, no client
 *         authentication included, or unsupported authentication method).
 *         The authorization server MAY return an HTTP 401 (Unauthorized)
 *         status code to indicate which HTTP authentication schemes are
 *         supported. If the client attempted to authenticate via the
 *         "Authorization" request header field, the authorization server
 *         MUST respond with an HTTP 401 (Unauthorized) status code and
 *         include the <a href="https://www.rfc-editor.org/rfc/rfc2616.html#section-14.47"
 *         >"WWW-Authenticate"</a> response header field matching the
 *         authentication scheme used by the client.
 *       </p>
 *     </dd>
 *   </dl>
 * </blockquote>
 * <p>
 * In either case, the JSON string returned by {@link #getResponseContent()}
 * can be used as the entity body of the response to the client application.
 * </p>
 *
 * <p>
 * The following illustrate the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(challenge)</i>
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the service implementation
 * ({@link AuthorizationIssueRequest}) was wrong or that an error occurred
 * in Authlete.
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
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "BAD_REQUEST"}, it means
 * that the request from the client application is invalid.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the client application
 * must be {@code "400 Bad Request"} and the content type must be
 * {@code "application/json"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#PASSWORD PASSWORD}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "PASSWORD"}, it means that
 * the request from the client application is valid and {@code grant_type}
 * is {@code "password"}. That is, the flow is
 * <a href="https://www.rfc-editor.org/rfc/rfc6749.html#section-4.3">"Resource Owner
 * Password Credentials"</a>.
 * </p>
 *
 * <p>
 * In this case, {@link #getUsername()} returns the value of {@code "username"}
 * request parameter and {@link #getPassword()} returns the value of {@code
 * "password"} request parameter which were contained in the token request
 * from the client application. The service implementation must validate the
 * credentials of the resource owner (= end-user) and take either of the
 * actions below according to the validation result.
 * </p>
 *
 * <ol style="list-style-type: lower-alpha">
 * <li>
 *   <p>
 *     <b>When the credentials are valid</b>, call Authlete's {@code
 *     /auth/token/issue} API to generate an access token for the client
 *     application. The API requires {@code "ticket"} request parameter and
 *     {@code "subject"} request parameter.
 *     Use the value returned from {@link #getTicket()} method as the value
 *     for {@code "ticket"} parameter.
 *   </p>
 *   <p>
 *     The response from {@code /auth/token/issue} API ({@link
 *     TokenIssueResponse}) contains data (an access token and others)
 *     which should be returned to the client application. Use the data
 *     to generate a response to the client application.
 *   </p>
 * <li>
 *   <p>
 *     <b>When the credentials are invalid</b>, call Authlete's {@code
 *     /auth/token/fail} API with {@code reason=}{@link
 *     TokenFailRequest.Reason#INVALID_RESOURCE_OWNER_CREDENTIALS
 *     INVALID_RESOURCE_OWNER_CREDENTIALS} to generate an error response
 *     for the client application. The API requires {@code "ticket"}
 *     request parameter. Use the value returned from {@link #getTicket()}
 *     method as the value for {@code "ticket"} parameter.
 *   </p>
 *   <p>
 *     The response from {@code /auth/token/fail} API ({@link
 *     TokenFailResponse}) contains error information which should be
 *     returned to the client application. Use it to generate a response
 *     to the client application.
 *   </p>
 * </ol>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that
 * the request from the client application is valid and an access token,
 * and optionally an ID token, is ready to be issued.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the client application
 * must be {@code "200 OK"} and the content type must be
 * {@code "application/json"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which contains
 * an access token (and optionally an ID token), so it can be used as
 * the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the service implementation
 * should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 * <dt><b>{@link Action#TOKEN_EXCHANGE TOKEN_EXCHANGE}</b> (Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "TOKEN_EXCHANGE"}, it means
 * that the request from the client application is a valid token exchange
 * request (cf. <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC
 * 8693 OAuth 2.0 Token Exchange</a>) and that the request has already passed
 * the following validation steps.
 * </p>
 *
 * <ol>
 * <li>
 * <p>
 * Confirm that the value of the {@code requested_token_type} request parameter
 * is one of the registered token type identifiers if the request parameter is
 * given and its value is not empty.
 * </p>
 *
 * <li>
 * <p>
 * Confirm that the {@code subject_token} request parameter is given and its
 * value is not empty.
 * </p>
 *
 * <li>
 * <p>
 * Confirm that the {@code subject_token_type} request parameter is given and
 * its value is one of the registered token type identifiers.
 * </p>
 *
 * <li>
 * <p>
 * Confirm that the {@code actor_token_type} request parameter is given and
 * its value is one of the registered token type identifiers if the
 * {@code actor_token} request parameter is given and its value is not empty.
 * </p>
 *
 * <li>
 * <p>
 * Confirm that the {@code actor_token_type} request parameter is not given
 * or its value is empty when the {@code actor_token} request parameter is
 * not given or its value is empty.
 * </p>
 * </ol>
 *
 * <p>
 * Furthermore, Authlete performs additional validation on the tokens specified
 * by the {@code subject_token} request parameter and the {@code actor_token}
 * request parameter according to their respective token types as shown below.
 * </p>
 *
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *   <caption><b>Token Validation Steps</b></caption>
 *   <tr>
 *     <td bgcolor="gold" align="center">Token Type</td>
 *     <td bgcolor="lightyellow"><code>urn:ietf:params:oauth:token-type:jwt</code></td>
 *   <tr/>
 *   <tr>
 *     <td colspan="2">
 *       <ol>
 *       <li>
 *         <p>
 *         Confirm that the format conforms to the JWT specification (<a href=
 *         "https://www.rfc-editor.org/rfc/rfc7519.html">RFC 7519</a>).
 *         </p>
 *       <li>
 *         <p>
 *         Check if the JWT is encrypted and if it is encrypted, then (a) reject
 *         the token exchange request when the {@link
 *         Service#isTokenExchangeEncryptedJwtRejected()
 *         tokenExchangeEncryptedJwtRejected} flag of the service is {@code true}
 *         or (b) skip remaining validation steps when the flag is {@code false}.
 *         Note that Authlete does not verify an encrypted JWT because there is
 *         no standard way to obtain the key to decrypt the JWT with. This means
 *         that you must verify an encrypted JWT by yourself when one is used as
 *         an input token with the token type
 *         {@code "urn:ietf:params:oauth:token-type:jwt"}.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the current time has not reached the time indicated by
 *         the {@code exp} claim if the JWT contains the claim.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the current time is equal to or after the time indicated
 *         by the {@code iat} claim if the JWT contains the claim.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the current time is equal to or after the time indicated
 *         by the {@code nbf} claim if the JWT contains the claim.
 *         </p>
 *       <li>
 *         <p>
 *         Check if the JWT is signed and if it is not signed, then (a) reject
 *         the token exchange request when the {@link
 *         Service#isTokenExchangeUnsignedJwtRejected()
 *         tokenExchangeUnsignedJwtRejected} flag of the service is {@code true}
 *         or (b) finish validation on the input token. Note that Authlete does
 *         not verify the signature of the JWT because there is no standard way
 *         to obtain the key to verify the signature of a JWT with. This means
 *         that you must verify the signature by yourself when a signed JWT is
 *         used as an input token with the token type
 *         {@code "urn:ietf:params:oauth:token-type:jwt"}.
 *         </p>
 *       </ol>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td bgcolor="gold" align="center">Token Type</td>
 *     <td bgcolor="lightyellow"><code>urn:ietf:params:oauth:token-type:access_token</code></td>
 *   <tr/>
 *   <tr>
 *     <td colspan="2">
 *       <ol>
 *       <li>
 *         <p>
 *         Confirm that the token is an access token that has been issued by
 *         the Authlete server of your service. This implies that access
 *         tokens issued by other systems cannot be used as a subject token
 *         or an actor token with the token type
 *         <code>urn:ietf:params:oauth:token-type:access_token</code>.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the access token has not expired.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the access token belongs to the service.
 *         </p>
 *       </ol>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td bgcolor="gold" align="center">Token Type</td>
 *     <td bgcolor="lightyellow"><code>urn:ietf:params:oauth:token-type:refresh_token</code></td>
 *   <tr/>
 *   <tr>
 *     <td colspan="2">
 *       <ol>
 *       <li>
 *         <p>
 *         Confirm that the token is a refresh token that has been issued by
 *         the Authlete server of your service. This implies that refresh
 *         tokens issued by other systems cannot be used as a subject token
 *         or an actor token with the token type
 *         <code>urn:ietf:params:oauth:token-type:refresh_token</code>.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the refresh token has not expired.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the refresh token belongs to the service.
 *         </p>
 *       </ol>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td bgcolor="gold" align="center">Token Type</td>
 *     <td bgcolor="lightyellow"><code>urn:ietf:params:oauth:token-type:id_token</code></td>
 *   <tr/>
 *   <tr>
 *     <td colspan="2">
 *       <ol>
 *       <li>
 *         <p>
 *         Confirm that the format conforms to the JWT specification (<a href=
 *         "https://www.rfc-editor.org/rfc/rfc7519.html">RFC 7519</a>).
 *         </p>
 *       <li>
 *         <p>
 *         Check if the ID Token is encrypted and if it is encrypted, then (a)
 *         reject the token exchange request when the {@link
 *         Service#isTokenExchangeEncryptedJwtRejected()
 *         tokenExchangeEncryptedJwtRejected} flag of the service is {@code true}
 *         or (b) skip remaining validation steps when the flag is {@code false}.
 *         Note that Authlete does not verify an encrypted ID Token because
 *         there is no standard way to obtain the key to decrypt the ID Token
 *         with in the context of token exchange where the client ID for the
 *         encrypted ID Token cannot be determined. This means that you must
 *         verify an encrypted ID Token by yourself when one is used as an
 *         input token with the token type
 *         {@code "urn:ietf:params:oauth:token-type:id_token"}.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the ID Token contains the {@code exp} claim and the
 *         current time has not reached the time indicated by the claim.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the ID Token contains the {@code iat} claim and the
 *         current time is equal to or after the time indicated by the claim.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the current time is equal to or after the time indicated
 *         by the {@code nbf} claim if the ID Token contains the claim.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the ID Token contains the {@code iss} claim and the
 *         value is a valid URI. In addition, confirm that the URI has the
 *         {@code https} scheme, no query component and no fragment component.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the ID Token contains the {@code aud} claim and its
 *         value is a JSON string or an array of JSON strings.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the value of the {@code nonce} claim is a JSON string
 *         if the ID Token contains the claim.
 *         </p>
 *       <li>
 *         <p>
 *         Check if the ID Token is signed and if it is not signed, then (a)
 *         reject the token exchange request when the {@link
 *         Service#isTokenExchangeUnsignedJwtRejected()
 *         tokenExchangeUnsignedJwtRejected} flag of the service is {@code true}
 *         or (b) finish validation on the input token.
 *         </p>
 *       <li>
 *         <p>
 *         Confirm that the signature algorithm is asymmetric. This implies that
 *         ID Tokens whose signature algorithm is symmetric ({@code HS256},
 *         {@code HS384} or {@code HS512}) cannot be used as a subject token or
 *         an actor token with the token type
 *         {@code urn:ietf:params:oauth:token-type:id_token}.
 *         </p>
 *       <li>
 *         <p>
 *         Verify the signature of the ID Token. Signature verification is
 *         performed even in the case where the issuer of the ID Token is not
 *         your service. But in that case, the issuer must support the discovery
 *         endpoint defined in <a href=
 *         "https://openid.net/specs/openid-connect-discovery-1_0.html">OpenID
 *         Connect Discovery 1.0</a>. Otherwise, signature verification fails.
 *         </p>
 *       </ol>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td bgcolor="gold" align="center">Token Type</td>
 *     <td bgcolor="lightyellow"><code>urn:ietf:params:oauth:token-type:saml1</code></td>
 *   <tr/>
 *   <tr>
 *     <td colspan="2">
 *       <ol>
 *       <li>
 *         <p>
 *         (Authlete does not perform any validation for this token type.)
 *         </p>
 *       </ol>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td bgcolor="gold" align="center">Token Type</td>
 *     <td bgcolor="lightyellow"><code>urn:ietf:params:oauth:token-type:saml2</code></td>
 *   <tr/>
 *   <tr>
 *     <td colspan="2">
 *       <ol>
 *       <li>
 *         <p>
 *         (Authlete does not perform any validation for this token type.)
 *         </p>
 *       </ol>
 *     </td>
 *   </tr>
 * </table>
 *
 * <p>
 * The specification of Token Exchange (<a href=
 * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>) is very
 * flexible. In other words, the specification has abandoned the task of
 * determining details. Therefore, for secure token exchange, you have
 * to complement the specification with your own rules. For that purpose,
 * Authlete provides some configuration options as listed below.
 * Authorization server implementers may utilize them and/or implement
 * their own rules.
 * </p>
 *
 * <ul>
 * <li>
 * <p>
 * <code>{@link Service#isTokenExchangeByIdentifiableClientsOnly()
 * Service.tokenExchangeByIdentifiableClientsOnly} - </code>
 * whether to reject token exchange requests that contain no client
 * identifier.
 * </p>
 *
 * <li>
 * <p>
 * <code>{@link Service#isTokenExchangeByConfidentialClientsOnly()
 * Service.tokenExchangeByConfidentialClientsOnly} - </code>
 * whether to reject token exchange requests by public clients.
 * </p>
 *
 * <li>
 * <p>
 * <code>{@link Service#isTokenExchangeByPermittedClientsOnly()
 * Service.tokenExchangeByPermittedClientsOnly} - </code>
 * whether to reject token exchange requests by clients that have no
 * explicit permission.
 * </p>
 *
 * <li>
 * <p>
 * <code>{@link Service#isTokenExchangeEncryptedJwtRejected()
 * Service.tokenExchangeEncryptedJwtRejected} - </code>
 * whether to reject token exchange requests which use encrypted JWTs
 * as input tokens.
 * </p>
 *
 * <li>
 * <p>
 * <code>{@link Service#isTokenExchangeUnsignedJwtRejected()
 * Service.tokenExchangeUnsignedJwtRejected} - </code>
 * whether to reject token exchange requests which use unsigned JWTs
 * as input tokens.
 * </p>
 * </ul>
 *
 * <p>
 * In the case of {@link Action#TOKEN_EXCHANGE TOKEN_EXCHANGE}, the {@link
 * #getResponseContent()} method returns {@code null}. You have to construct
 * the token response by yourself.
 * </p>
 *
 * <p>
 * For example, you may generate an access token by calling Authlete's
 * {@code /api/auth/token/create} API and construct a token response like
 * below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-cache, no-store
 *
 * {
 *   "access_token":      "{@link TokenCreateResponse#getAccessToken()}",
 *   "issued_token_type": "urn:ietf:params:oauth:token-type:access_token",
 *   "token_type":        "Bearer",
 *   "expires_in":        {@link TokenCreateResponse#getExpiresIn()},
 *   "scope":             "String.join(" ", {@link TokenCreateResponse#getScopes()})"
 * }</pre>
 *
 * </dd>
 *
 * <dt><b>{@link Action#JWT_BEARER JWT_BEARER}</b> (Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "JWT_BEARER"}, it means that
 * the request from the client application is a valid token request with the
 * grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"} (<a href=
 * "https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523 JSON Web Token (JWT)
 * Profile for OAuth 2.0 Client Authentication and Authorization Grants</a>)
 * and that the request has already passed the following validation steps.
 * </p>
 *
 * <ol>
 * <li>
 *   <p>
 *   Confirm that the {@code assertion} request parameter is given and its value
 *   is not empty.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the format of the assertion conforms to the JWT specification
 *   (<a href="https://www.rfc-editor.org/rfc/rfc7519.html">RFC 7519</a>).
 *   </p>
 *
 * <li>
 *   <p>
 *   Check if the JWT is encrypted and if it is encrypted, then (a) reject the
 *   token request when the {@link Service#isJwtGrantEncryptedJwtRejected()
 *   jwtGrantEncryptedJwtRejected} flag of the service is {@code true} or (b)
 *   skip remaining validation steps when the flag is {@code false}. Note that
 *   Authlete does not verify an encrypted JWT because there is no standard way
 *   to obtain the key to decrypt the JWT with. This means that you must verify
 *   an encrypted JWT by yourself.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the JWT contains the {@code iss} claim and its value is a
 *   JSON string.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the JWT contains the {@code sub} claim and its value is a
 *   JSON string.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the JWT contains the {@code aud} claim and its value is
 *   either a JSON string or an array of JSON strings.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the issuer identifier of the service (cf. {@link Service#getIssuer()})
 *   or the URL of the token endpoint (cf. {@link Service#getTokenEndpoint()})
 *   is listed as audience in the {@code aud} claim.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the JWT contains the {@code exp} claim and the current time
 *   has not reached the time indicated by the claim.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the current time is equal to or after the time indicated by
 *   by the {@code iat} claim if the JWT contains the claim.
 *   </p>
 *
 * <li>
 *   <p>
 *   Confirm that the current time is equal to or after the time indicated by
 *   by the {@code nbf} claim if the JWT contains the claim.
 *   </p>
 *
 * <li>
 *   <p>
 *   Check if the JWT is signed and if it is not signed, then (a) reject the
 *   token request when the {@link Service#isJwtGrantUnsignedJwtRejected()
 *   jwtGrantUnsignedJwtRejected} flag of the service is {@code true} or (b)
 *   finish validation on the JWT. Note that Authlete does not verify the
 *   signature of the JWT because there is no standard way to obtain the key
 *   to verify the signature of a JWT with. This means that you must verify
 *   the signature by yourself.
 *   </p>
 * </ol>
 *
 * <p>
 * Authlete provides some configuration options for the grant type as listed
 * below. Authorization server implementers may utilize them and/or implement
 * their own rules.
 * </p>
 *
 * <ul>
 * <li>
 *   <p>
 *   <code>{@link Service#isJwtGrantByIdentifiableClientsOnly()
 *   Service.jwtGrantByIdentifiableClientsOnly} - </code>
 *   whether to reject token requests that use the grant type
 *   {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"} but contain no client
 *   identifier.
 *   </p>
 *
 * <li>
 *   <p>
 *   <code>{@link Service#isJwtGrantEncryptedJwtRejected()
 *   Service.jwtGrantEncryptedJwtRejected} - </code>
 *   whether to reject token requests that use an encrypted JWT as an
 *   authorization grant with the grant type
 *   {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
 *   </p>
 *
 * <li>
 *   <p>
 *   <code>{@link Service#isJwtGrantUnsignedJwtRejected()
 *   Service.jwtGrantUnsignedJwtRejected} - </code>
 *   whether to reject token requests that use an unsigned JWT as an
 *   authorization grant with the grant type
 *   {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
 *   </p>
 * </ul>
 *
 * <p>
 * In the case of {@link Action#JWT_BEARER JWT_BEARER}, the {@link
 * #getResponseContent()} method returns {@code null}. You have to construct
 * the token response by yourself.
 * </p>
 *
 * <p>
 * For example, you may generate an access token by calling Authlete's
 * {@code /api/auth/token/create} API and construct a token response like
 * below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-cache, no-store
 *
 * {
 *   "access_token": "{@link TokenCreateResponse#getAccessToken()}",
 *   "token_type":   "Bearer",
 *   "expires_in":   {@link TokenCreateResponse#getExpiresIn()},
 *   "scope":        "String.join(" ", {@link TokenCreateResponse#getScopes()})"
 * }</pre>
 *
 * <p>
 * Finally, note again that Authlete does not verify the signature of the JWT
 * specified by the {@code assertion} request parameter. You must verify the
 * signature by yourself.
 * </p>
 *
 * </dd>
 * </dl>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6749.html"
 *      >RFC 6749 The OAuth 2.0 Authorization Framework</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
 *      Assertion Framework for OAuth 2.0 Client Authentication and
 *      Authorization Grants</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
 *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
 *      and Authorization Grants</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
 *      >RFC 8693 OAuth 2.0 Token Exchange</a>
 */
public class TokenResponse extends ApiResponse
{
    private static final long serialVersionUID = 13L;


    /**
     * The next action that the service implementation should take.
     */
    public enum Action
    {
        /**
         * Authentication of the client application failed. The service
         * implementation should return either {@code "400 Bad Request"}
         * or {@code "401 Unauthorized"} to the client application.
         */
        INVALID_CLIENT,

        /**
         * The request from the service was wrong or an error occurred
         * in Authlete. The service implementation should return {@code
         * "500 Internal Server Error"} to the client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The token request from the client was wrong. The service
         * implementation should return {@code "400 Bad Request"} to the
         * client application.
         */
        BAD_REQUEST,

        /**
         * The token request from the client application was valid and
         * the grant type is {@code "password"}. The service implementation
         * should validate the credentials of the resource owner and call
         * Authlete's {@code /auth/token/issue} API or {@code /auth/token/fail}
         * API according to the result of the validation.
         */
        PASSWORD,

        /**
         * The token request from the client was valid. The service
         * implementation should return {@code "200 OK"} to the client
         * application with an access token.
         */
        OK,

        /**
         * The token request from the client was a valid token exchange
         * request. The service implementation should take necessary
         * actions (e.g. create an access token), generate a response and
         * return it to the client application.
         *
         * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
         *      >RFC 8693 OAuth 2.0 Token Exchange</a>
         *
         * @since 3.26
         * @since Authlete 2.3
         */
        TOKEN_EXCHANGE,

        /**
         * The token request from the client was a valid token request with
         * the grant type {@code "urn:ietf:params:oauth:grant-type:jwt-bearer"}.
         * The service implementation must verify the signature of the assertion,
         * create an access token, generate a response and return it to the
         * client application.
         *
         * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
         *      Assertion Framework for OAuth 2.0 Client Authentication and
         *      Authorization Grants</a>
         *
         * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
         *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
         *      and Authorization Grants</a>
         *
         * @since 3.30
         * @since Authlete 2.3
         */
        JWT_BEARER,
    }


    private static final String SUMMARY_FORMAT
        = "action=%s, username=%s, password=%s, ticket=%s, responseContent=%s, "
        + "accessToken=%s, accessTokenExpiresAt=%d, accessTokenDuration=%d, "
        + "refreshToken=%s, refreshTokenExpiresAt=%d, refreshTokenDuration=%d, "
        + "idToken=%s, grantType=%s, "
        + "clientId=%d, clientIdAlias=%s, clientIdAliasUsed=%s, "
        + "subject=%s, scopes=%s, properties=%s, jwtAccessToken=%s, "
        + "clientAuthMethod=%s";


    private Action action;
    private String responseContent;
    private String username;
    private String password;
    private String ticket;
    private String accessToken;
    private long accessTokenExpiresAt;
    private long accessTokenDuration;
    private String refreshToken;
    private long refreshTokenExpiresAt;
    private long refreshTokenDuration;
    private String idToken;
    private GrantType grantType;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String subject;
    private String[] scopes;
    private Property[] properties;
    private String jwtAccessToken;
    private ClientAuthMethod clientAuthMethod;
    private URI[] resources;
    private URI[] accessTokenResources;
    private AuthzDetails authorizationDetails;
    private String grantId;
    private Pair[] serviceAttributes;
    private Pair[] clientAttributes;

    /*
     * For RFC 8693 OAuth 2.0 Token Exchange
     */
    private String[] audiences;
    private TokenType requestedTokenType;
    private String subjectToken;
    private TokenType subjectTokenType;
    private TokenInfo subjectTokenInfo;
    private String actorToken;
    private TokenType actorTokenType;
    private TokenInfo actorTokenInfo;

    /*
     * For RFC 7523 JSON Web Token (JWT) Profile for OAuth 2.0
     * Client Authentication and Authorization Grants
     */
    private String assertion;


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
    public void setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;
    }


    /**
     * Get the value of {@code "username"} request parameter.
     *
     * <p>
     * This method returns a non-null value only when the value of
     * {@code "grant_type"} request parameter in the token request
     * is {@code "password"}.
     * </p>
     *
     * <p>
     * {@code getSubject()} method was renamed to {@code getUsername()}
     * on version 1.13.
     * </p>
     *
     * @since 1.13
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.3.2"
     *      >RFC 6749, 4.3.2. Access Token Request</a>
     */
    public String getUsername()
    {
        return username;
    }


    /**
     * Set the value of {@code "username"} request parameter.
     *
     * <p>
     * {@code setSubject(String}} was renamed to {@code setUsername(String)}
     * on version 1.13.
     * </p>
     *
     * @since 1.13
     */
    public void setUsername(String username)
    {
        this.username = username;
    }


    /**
     * Get the value of {@code "password"} request parameter.
     *
     * <p>
     * This method returns a non-null value only when the value of
     * {@code "grant_type"} request parameter in the token request
     * is {@code "password"}.
     * </p>
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.3.2"
     *      >RFC 6749, 4.3.2. Access Token Request</a>
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * Set the value of {@code "password"} request parameter.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }


    /**
     * Get the ticket issued from Authlete's {@code /auth/token} endpoint.
     * The value is to be used as the value of {@code "ticket"} request
     * parameter for {@code /auth/token/issue} API or {@code /auth/token/fail}
     * API.
     *
     * <p>
     * This method returns a non-null value only when {@code "action"} is
     * {@link Action#PASSWORD PASSWORD}.
     * </p>
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the ticket used for {@code /auth/token/issue} API or {@code
     * /auth/token/fail} API.
     */
    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
                action, username, password, ticket, responseContent,
                accessToken, accessTokenExpiresAt, accessTokenDuration,
                refreshToken, refreshTokenExpiresAt, refreshTokenDuration,
                idToken, grantType, clientId, clientIdAlias, clientIdAliasUsed,
                subject, Utils.join(scopes, " "),
                Utils.stringifyProperties(properties), jwtAccessToken,
                clientAuthMethod);
    }


    /**
     * Get the newly issued access token. This method returns a non-null
     * value only when {@link #getAction()} returns {@link Action#OK}.
     *
     * <p>
     * If the service is configured to issue JWT-based access tokens,
     * a JWT-based access token is issued additionally. In the case,
     * {@link #getJwtAccessToken()} returns the JWT-based access token.
     * </p>
     *
     * @return
     *         The newly issued access token.
     *
     * @see #getJwtAccessToken()
     *
     * @since 1.34
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the newly issued access token.
     *
     * @param accessToken
     *         The newly issued access token.
     *
     * @since 1.34
     */
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }


    /**
     * Get the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the access token will expire.
     *
     * @return
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the access token will expire.
     *
     * @since 1.34
     */
    public long getAccessTokenExpiresAt()
    {
        return accessTokenExpiresAt;
    }


    /**
     * Set the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the access token will expire.
     *
     * @param expiresAt
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the access token will expire.
     *
     * @since 1.34
     */
    public void setAccessTokenExpiresAt(long expiresAt)
    {
        this.accessTokenExpiresAt = expiresAt;
    }


    /**
     * Get the duration of the access token in seconds.
     *
     * @return
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public long getAccessTokenDuration()
    {
        return accessTokenDuration;
    }


    /**
     * Set the duration of the access token in seconds.
     *
     * @param duration
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public void setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;
    }


    /**
     * Get the newly issued refresh token. This method returns a non-null
     * value only when {@link #getAction()} returns {@link Action#OK} and
     * the service supports the <a href=
     * "https://tools.ietf.org/html/rfc6749#section-6">refresh token flow</a>.
     *
     * @return
     *         The newly issued refresh token.
     *
     * @since 1.34
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the newly issued refresh token.
     *
     * @param refreshToken
     *         The newly issued refresh token.
     *
     * @since 1.34
     */
    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }


    /**
     * Get the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the refresh token will expire.
     *
     * @return
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the refresh token will expire.
     *         If the refresh token is null, this method returns 0.
     *
     * @since 1.34
     */
    public long getRefreshTokenExpiresAt()
    {
        return refreshTokenExpiresAt;
    }


    /**
     * Set the date in milliseconds since the Unix epoch (1970-01-01)
     * at which the refresh token will expire.
     *
     * @param expiresAt
     *         The expiration date in milliseconds since the Unix epoch
     *         (1970-01-01) at which the refresh token will expire.
     *         If the refresh token is null, this method returns 0.
     *
     * @since 1.34
     */
    public void setRefreshTokenExpiresAt(long expiresAt)
    {
        this.refreshTokenExpiresAt = expiresAt;
    }


    /**
     * Get the duration of the refresh token in seconds.
     *
     * @return
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public long getRefreshTokenDuration()
    {
        return refreshTokenDuration;
    }


    /**
     * Set the duration of the refresh token in seconds.
     *
     * @param duration
     *         Duration in seconds.
     *
     * @since 1.34
     */
    public void setRefreshTokenDuration(long duration)
    {
        this.refreshTokenDuration = duration;
    }


    /**
     * Get the ID token.
     *
     * <p>
     * An <a href="http://openid.net/specs/openid-connect-core-1_0.html#IDToken"
     * >ID token</a> is issued from a token endpoint when the <a href=
     * "https://tools.ietf.org/html/rfc6749#section-4.1">authorization code
     * flow</a> is used and <code>"openid"</code> is included in the scope list.
     * </p>
     *
     * @return
     *         ID token.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#CodeFlowAuth"
     *      >Authentication using the Authorization Code Flow</a>
     *
     * @since 1.34
     */
    public String getIdToken()
    {
        return idToken;
    }


    /**
     * Set the ID token.
     *
     * @param idToken
     *         ID token.
     *
     * @since 1.34
     */
    public void setIdToken(String idToken)
    {
        this.idToken = idToken;
    }


    /**
     * Get the grant type of the token request.
     *
     * @since 2.8
     */
    public GrantType getGrantType()
    {
        return grantType;
    }


    /**
     * Set the grant type of the token request.
     *
     * @param grantType
     *         Grant type of the token request.
     *
     * @since 2.8
     */
    public void setGrantType(GrantType grantType)
    {
        this.grantType = grantType;
    }


    /**
     * Get the client ID.
     *
     * @since 2.8
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID.
     *
     * @since 2.8
     */
    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }


    /**
     * Get the client ID alias when the token request was made.
     *
     * <p>
     * If the client did not have an alias, this method returns
     * {@code null}. Also, if the token request was invalid and
     * it failed to identify a client, this method returns
     * {@code null}.
     * </p>
     *
     * @return
     *         The client ID alias.
     *
     * @since 2.8
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the client ID alias when the token request was made.
     *
     * @param alias
     *         The client ID alias.
     *
     * @since 2.8
     */
    public void setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;
    }


    /**
     * Get the flag which indicates whether the client ID alias was used
     * when the token request was made.
     *
     * @return
     *         {@code true} if the client ID alias was used when the token
     *         request was made.
     *
     * @since 2.8
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether the client ID alias was used
     * when the token request was made.
     *
     * @param used
     *         {@code true} if the client ID alias was used when the token
     *         request was made.
     *
     * @since 2.8
     */
    public void setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;
    }


    /**
     * Get the subject (= resource owner's ID) of the access token.
     *
     * <p>
     * Even if an access token has been issued by the call of
     * {@code /api/auth/token} API, this method returns {@code null}
     * if the flow of the token request was <a href=
     * "http://tools.ietf.org/html/rfc6749#section-4.4">Client
     * Credentials Flow</a> ({@code grant_type=client_credentials})
     * because it means the access token is not associated with any
     * specific end-user.
     * </p>
     *
     * @since 2.8
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= resource owner's ID) of the access token.
     *
     * @since 2.8
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    /**
     * Get the scopes covered by the access token.
     *
     * @since 2.8
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes covered by the access token.
     *
     * @since 2.8
     */
    public void setScopes(String[] scopes)
    {
        this.scopes = scopes;
    }


    /**
     * Get the extra properties associated with the access token.
     * This method returns {@code null} when no extra property is
     * associated with the issued access token.
     *
     * @return
     *         Extra properties associated with the issued access token.
     *
     * @since 2.8
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set the extra properties associated with the access token.
     *
     * @param properties
     *         Extra properties.
     *
     * @since 2.8
     */
    public void setProperties(Property[] properties)
    {
        this.properties = properties;
    }


    /**
     * Get the newly issued access token in JWT format.
     *
     * <p>
     * If the authorization server is configured to issue JWT-based access
     * tokens (= if {@link Service#getAccessTokenSignAlg()} returns a non-null
     * value), a JWT-based access token is issued along with the original
     * random-string one.
     * </p>
     *
     * <p>
     * Regarding the detailed format of the JWT-based access token, see the
     * description of the {@link Service} class.
     * </p>
     *
     * @return
     *         The newly issued access token in JWT format. If the service is
     *         not configured to issue JWT-based access tokens, this method
     *         always returns null.
     *
     * @see #getAccessToken()
     *
     * @since 2.37
     */
    public String getJwtAccessToken()
    {
        return jwtAccessToken;
    }


    /**
     * Set the newly issued access token in JWT format.
     *
     * @param jwtAccessToken
     *         The newly issued access token in JWT format.
     *
     * @since 2.37
     */
    public void setJwtAccessToken(String jwtAccessToken)
    {
        this.jwtAccessToken = jwtAccessToken;
    }


    /**
     * Get the client authentication method that should be performed at the
     * token endpoint.
     *
     * <p>
     * If the client could not be identified by the information in the request,
     * this method returns {@code null}.
     * </p>
     *
     * @return
     *         The client authentication method that should be performed at
     *         the token endpoint.
     *
     * @since 2.50
     */
    public ClientAuthMethod getClientAuthMethod()
    {
        return clientAuthMethod;
    }


    /**
     * Set the client authentication method that should be performed at the
     * token endpoint.
     *
     * @param method
     *         The client authentication method that should be performed at
     *         the token endpoint.
     *
     * @since 2.50
     */
    public void setClientAuthMethod(ClientAuthMethod method)
    {
        this.clientAuthMethod = method;
    }


    /**
     * Get the resources specified by the {@code resource} request parameters
     * in the token request.
     *
     * <p>
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     * </p>
     *
     * @return
     *         Resources specified by the {@code resource} request parameters
     *         in the token request.
     *
     * @since 2.62
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Set the resources specified by the {@code resource} request parameters
     * in the token request.
     *
     * <p>
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     * </p>
     *
     * @param resources
     *         Resources specified by the {@code resource} request parameters
     *         in the token request.
     *
     * @since 2.62
     */
    public void setResources(URI[] resources)
    {
        this.resources = resources;
    }


    /**
     * Get the target resources of the access token being issued.
     *
     * <p>
     * See "Resource Indicators for OAuth 2.0" for details.
     * </p>
     *
     * @return
     *         The target resources of the access token.
     *
     * @since 2.62
     */
    public URI[] getAccessTokenResources()
    {
        return accessTokenResources;
    }


    /**
     * Set the target resources of the access token being issued.
     *
     * <p>
     * See "Resource Indicators for OAuth 2.0" for details.
     * </p>
     *
     * @param resources
     *         The target resources of the access token.
     *
     * @since 2.62
     */
    public void setAccessTokenResources(URI[] resources)
    {
        this.accessTokenResources = resources;
    }


    /**
     * Get the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * <p>
     * When the {@code action} (= the value returned from {@link #getAction()}
     * is {@link Action#PASSWORD PASSWORD}, this method returns an array that
     * represents the {@code authorization_details} request parameter included
     * in the token request. In other successful cases, this method returns the
     * authorization details associated with the issued access token.
     * </p>
     *
     * @return
     *         Authorization details.
     *
     * @since 2.56
     */
    public AuthzDetails getAuthorizationDetails()
    {
        return authorizationDetails;
    }


    /**
     * Set the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @param details
     *         Authorization details.
     *
     * @since 2.56
     */
    public void setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;
    }


    /**
     * Get the value of the {@code grant_id} parameter in the token response.
     *
     * @return
     *         The value of the {@code grant_id} response parameter.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public String getGrantId()
    {
        return grantId;
    }


    /**
     * Set the value of the {@code grant_id} parameter in the token response.
     *
     * @param grantId
     *         The value of the {@code grant_id} response parameter.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public void setGrantId(String grantId)
    {
        this.grantId = grantId;
    }


    /**
     * Get the attributes of the service that the client application belongs to.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @return
     *         The attributes of the service.
     *
     * @since 2.88
     */
    public Pair[] getServiceAttributes()
    {
        return serviceAttributes;
    }


    /**
     * Set the attributes of the service that the client application belongs to.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         The attributes of the service.
     *
     * @since 2.88
     */
    public void setServiceAttributes(Pair[] attributes)
    {
        this.serviceAttributes = attributes;
    }


    /**
     * Get the attributes of the client.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @return
     *         The attributes of the client.
     *
     * @since 2.88
     */
    public Pair[] getClientAttributes()
    {
        return clientAttributes;
    }


    /**
     * Set the attributes of the client.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         The attributes of the client.
     *
     * @since 2.88
     */
    public void setClientAttributes(Pair[] attributes)
    {
        this.clientAttributes = attributes;
    }


    /**
     * Get the values of the {@code audience} request parameters that are
     * contained in the token exchange request (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * The {@code audience} request parameter is defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth 2.0 Token
     * Exchange</a>. Although <a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749 The OAuth 2.0
     * Authorization Framework</a> states <i>"Request and response parameters
     * MUST NOT be included more than once"</i>, RFC 8693 allows a token
     * exchange request to include the {@code audience} request parameter
     * multiple times.
     * </p>
     *
     * @return
     *         The values of the {@code audience} request parameters.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public String[] getAudiences()
    {
        return audiences;
    }


    /**
     * Set the values of the {@code audience} request parameters that are
     * contained in the token exchange request (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * The {@code audience} request parameter is defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth 2.0 Token
     * Exchange</a>. Although <a href=
     * "https://www.rfc-editor.org/rfc/rfc6749.html">RFC 6749 The OAuth 2.0
     * Authorization Framework</a> states <i>"Request and response parameters
     * MUST NOT be included more than once"</i>, RFC 8693 allows a token
     * exchange request to include the {@code audience} request parameter
     * multiple times.
     * </p>
     *
     * @param audiences
     *         The values of the {@code audience} request parameters.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setAudiences(String[] audiences)
    {
        this.audiences = audiences;
    }


    /**
     * Get the value of the {@code requested_token_type} request parameter.
     *
     * <p>
     * The {@code requested_token_type} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @return
     *         The value of the {@code requested_token_type} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public TokenType getRequestedTokenType()
    {
        return requestedTokenType;
    }


    /**
     * Set the value of the {@code requested_token_type} request parameter.
     *
     * <p>
     * The {@code requested_token_type} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @param tokenType
     *         The value of the {@code requested_token_type} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setRequestedTokenType(TokenType tokenType)
    {
        this.requestedTokenType = tokenType;
    }


    /**
     * Get the value of the {@code subject_token} request parameter.
     *
     * <p>
     * The {@code subject_token} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @return
     *         The value of the {@code subject_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public String getSubjectToken()
    {
        return subjectToken;
    }


    /**
     * Set the value of the {@code subject_token} request parameter.
     *
     * <p>
     * The {@code subject_token} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @param token
     *         The value of the {@code subject_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setSubjectToken(String token)
    {
        this.subjectToken = token;
    }


    /**
     * Get the value of the {@code subject_token_type} request parameter.
     *
     * <p>
     * The {@code subject_token_type} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @return
     *         The value of the {@code subject_token_type} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public TokenType getSubjectTokenType()
    {
        return subjectTokenType;
    }


    /**
     * Set the value of the {@code subject_token_type} request parameter.
     *
     * <p>
     * The {@code subject_token_type} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @param tokenType
     *         The value of the {@code subject_token_type} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setSubjectTokenType(TokenType tokenType)
    {
        this.subjectTokenType = tokenType;
    }


    /**
     * Get the information about the token specified by the
     * {@code subject_token} request parameter.
     *
     * <p>
     * This property holds a non-null value only when the value of the
     * {@code subject_token_type} request parameter is either
     * {@code "urn:ietf:params:oauth:token-type:access_token"} or
     * {@code "urn:ietf:params:oauth:token-type:refresh_token"} (= only
     * when the {@code subjectTokenType} property is either
     * <code>"{@link TokenType#ACCESS_TOKEN ACCESS_TOKEN}"</code> or
     * <code>"{@link TokenType#REFRESH_TOKEN REFRESH_TOKEN}"</code>).
     * </p>
     *
     * @return
     *         The information about the token specified by the
     *         {@code subject_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public TokenInfo getSubjectTokenInfo()
    {
        return subjectTokenInfo;
    }


    /**
     * Set the information about the token specified by the
     * {@code subject_token} request parameter.
     *
     * <p>
     * This property holds a non-null value only when the value of the
     * {@code subject_token_type} request parameter is either
     * {@code "urn:ietf:params:oauth:token-type:access_token"} or
     * {@code "urn:ietf:params:oauth:token-type:refresh_token"} (= only
     * when the {@code subjectTokenType} property is either
     * <code>"{@link TokenType#ACCESS_TOKEN ACCESS_TOKEN}"</code> or
     * <code>"{@link TokenType#REFRESH_TOKEN REFRESH_TOKEN}"</code>).
     * </p>
     *
     * @param tokenInfo
     *         The information about the token specified by the
     *         {@code subject_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setSubjectTokenInfo(TokenInfo tokenInfo)
    {
        this.subjectTokenInfo = tokenInfo;
    }


    /**
     * Get the value of the {@code actor_token} request parameter.
     *
     * <p>
     * The {@code actor_token} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @return
     *         The value of the {@code actor_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public String getActorToken()
    {
        return actorToken;
    }


    /**
     * Set the value of the {@code actor_token} request parameter.
     *
     * <p>
     * The {@code actor_token} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @param token
     *         The value of the {@code actor_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setActorToken(String token)
    {
        this.actorToken = token;
    }


    /**
     * Get the value of the {@code actor_token_type} request parameter.
     *
     * <p>
     * The {@code actor_token_type} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @return
     *         The value of the {@code actor_token_type} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public TokenType getActorTokenType()
    {
        return actorTokenType;
    }


    /**
     * Set the value of the {@code actor_token_type} request parameter.
     *
     * <p>
     * The {@code actor_token_type} request parameter is defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693 OAuth
     * 2.0 Token Exchange</a>.
     * </p>
     *
     * @param tokenType
     *         The value of the {@code actor_token_type} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setActorTokenType(TokenType tokenType)
    {
        this.actorTokenType = tokenType;
    }


    /**
     * Get the information about the token specified by the
     * {@code actor_token} request parameter.
     *
     * <p>
     * This property holds a non-null value only when the value of the
     * {@code actor_token_type} request parameter is either
     * {@code "urn:ietf:params:oauth:token-type:access_token"} or
     * {@code "urn:ietf:params:oauth:token-type:refresh_token"} (= only
     * when the {@code actorTokenType} property is either
     * <code>"{@link TokenType#ACCESS_TOKEN ACCESS_TOKEN}"</code> or
     * <code>"{@link TokenType#REFRESH_TOKEN REFRESH_TOKEN}"</code>).
     * </p>
     *
     * @return
     *         The information about the token specified by the
     *         {@code actor_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public TokenInfo getActorTokenInfo()
    {
        return actorTokenInfo;
    }


    /**
     * Set the information about the token specified by the
     * {@code actor_token} request parameter.
     *
     * <p>
     * This property holds a non-null value only when the value of the
     * {@code actor_token_type} request parameter is either
     * {@code "urn:ietf:params:oauth:token-type:access_token"} or
     * {@code "urn:ietf:params:oauth:token-type:refresh_token"} (= only
     * when the {@code actorTokenType} property is either
     * <code>"{@link TokenType#ACCESS_TOKEN ACCESS_TOKEN}"</code> or
     * <code>"{@link TokenType#REFRESH_TOKEN REFRESH_TOKEN}"</code>).
     * </p>
     *
     * @param tokenInfo
     *         The information about the token specified by the
     *         {@code actor_token} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public void setActorTokenInfo(TokenInfo tokenInfo)
    {
        this.actorTokenInfo = tokenInfo;
    }


    /**
     * Get the value of the {@code assertion} request parameter.
     *
     * <p>
     * The {@code assertion} request parameter is defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7521.html#section-4.1">Section
     * 4.1</a> of <a href="https://www.rfc-editor.org/rfc/rfc7521.html"
     * >RFC 7521 Assertion Framework for OAuth 2.0 Client
     * Authentication and Authorization Grants</a>.
     * </p>
     *
     * @return
     *         The value of the {@code assertion} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public String getAssertion()
    {
        return assertion;
    }


    /**
     * Set the value of the {@code assertion} request parameter.
     *
     * <p>
     * The {@code assertion} request parameter is defined in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7521.html#section-4.1">Section
     * 4.1</a> of <a href="https://www.rfc-editor.org/rfc/rfc7521.html"
     * >RFC 7521 Assertion Framework for OAuth 2.0 Client
     * Authentication and Authorization Grants</a>.
     * </p>
     *
     * @param assertion
     *         The value of the {@code assertion} request parameter.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7521.html">RFC 7521
     *      Assertion Framework for OAuth 2.0 Client Authentication and
     *      Authorization Grants</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7523.html">RFC 7523
     *      JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication
     *      and Authorization Grants</a>
     *
     * @since 3.30
     * @since Authlete 2.3
     */
    public void setAssertion(String assertion)
    {
        this.assertion = assertion;
    }
}
