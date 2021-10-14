/*
 * Copyright (C) 2014-2021 Authlete, Inc.
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
import com.authlete.common.types.Display;
import com.authlete.common.types.GMAction;
import com.authlete.common.types.Prompt;
import com.authlete.common.util.Utils;


/**
 * Response from Authlete's {@code /auth/authorization} API.
 *
 * <p>
 * Note: In the description below, <i>"authorization server"</i>
 * is always used even where <i>"OpenID provider"</i> should be
 * used.
 * </p>
 *
 * <p>
 * Authlete's {@code /auth/authorization} API returns
 * JSON which can be mapped to this class. The authorization server
 * implementation should retrieve the value of {@code "action"} from
 * the response and take the following steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the authorization server implementation
 * was wrong or that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from the viewpoint of the client application, it
 * is an error on the server side. Therefore, the authorization server
 * implementation should generate a response to the client application
 * with the HTTP status of {@code "500 Internal Server Error"}. Authlete
 * recommends {@code "application/json"} as the content type although
 * OAuth 2.0 specification does not mention the format of the error
 * response when the redirect URI is not usable.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes
 * the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
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
 * The HTTP status of the response returned to the client application should
 * be {@code "400 Bad Request"} and Authlete recommends {@code
 * "application/json"} as the content type although OAuth 2.0 specification
 * does not mention the format of the error response when the redirect URI
 * is not usable.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a JSON string which describes the error,
 * so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
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
 *
 * <dt><b>{@link Action#LOCATION LOCATION}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "LOCATION"}, it means
 * that the request from the client application is invalid but the
 * redirect URI to which the error should be reported has been determined.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the client application should
 * be {@code "302 Found"} and {@code "Location"} header must have a redirect
 * URI with the {@code "error"} parameter.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns the redirect URI which has the {@code
 * "error"} parameter, so it can be used as the value of {@code "Location"}
 * header.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 302 Found
 * Location: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 *
 * <dt><b>{@link Action#FORM FORM}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "FORM"}, it means
 * that the request from the client application is invalid but the
 * redirect URI to which the error should be reported has been determined,
 * and that the request contains {@code response_mode=form_post} as is
 * defined in <i>"<a href=
 * 'https://openid.net/specs/oauth-v2-form-post-response-mode-1_0.html'
 * >OAuth 2.0 Form Post Response Mode</a>"</i>.
 * </p>
 *
 * <p>
 * The HTTP status of the response returned to the client application should
 * be {@code "200 OK"} and the content type should be {@code
 * "text/html;charset=UTF-8"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns an HTML which satisfies the requirements
 * of {@code response_mode=form_post}, so it can be used as the entity body
 * of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: text/html;charset=UTF-8
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * </dd>
 *
 *
 * <dt><b>{@link Action#NO_INTERACTION NO_INTERACTION}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "NO_INTERACTION"}, it means
 * that the request from the client application has no problem and requires
 * the authorization server to process the request without displaying any
 * user interface for authentication and/or consent. This happens when the
 * request contains {@code prompt=none}.
 * </p>
 *
 * <p>
 * The authorization server implementation must follow the following steps.
 * </p>
 *
 * <blockquote>
 * <ol>
 *   <li>
 *     <p><b>[END-USER AUTHENTICATION]</b>
 *     Check whether an end-user has already logged in. If an end-user has
 *     logged in, go to the next step ([MAX_AGE]). Otherwise, call Authlete's
 *     {@code /auth/authorization/fail} API with {@code reason=}{@link
 *     AuthorizationFailRequest.Reason#NOT_LOGGED_IN NOT_LOGGED_IN} and use
 *     the response from the API to generate a response to the client
 *     application.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[MAX_AGE]</b>
 *     Get the value of the max age by {@link #getMaxAge()} method. The value
 *     represents the maximum authentication age which has come from {@code
 *     "max_age"} request parameter or {@code "default_max_age"} configuration
 *     parameter of the client application. If the value is 0, go to the next
 *     step ([SUBJECT]). Otherwise, follow the sub steps described below.
 *     </p>
 *     <br/>
 *     <ol style="list-style-type: lower-roman;">
 *       <li>
 *         <p>
 *         Get the time at which the end-user was authenticated. Note that
 *         this value is not managed by Authlete, meaning that it is expected
 *         that the authorization server implementation manages the value.
 *         If the authorization server implementation does not manage
 *         authentication time of end-users, call Authlete's {@code
 *         /auth/authorization/fail} API with {@code reason=}{@link
 *         AuthorizationFailRequest.Reason#MAX_AGE_NOT_SUPPORTED
 *         MAX_AGE_NOT_SUPPORTED} and use the response from the API to
 *         generate a response to the client application.
 *         </p>
 *         <br/>
 *       <li>
 *         <p>
 *         Add the value of the maximum authentication age (which is represented
 *         in seconds) to the authentication time.
 *         </p>
 *         <br/>
 *       <li>
 *         <p>
 *         Check whether the calculated value is equal to or greater than the
 *         current time. If this condition is satisfied, go to the next step
 *         ([SUBJECT]).
 *         Otherwise, call Authlete's {@code /auth/authorization/fail} API with
 *         {@code reason=}{@link AuthorizationFailRequest.Reason#EXCEEDS_MAX_AGE
 *         EXCEEDS_MAX_AGE} and use the response from the API to generate a
 *         response to the client application.
 *         </p>
 *     </ol>
 *     <br/>
 *
 *   <li>
 *     <p><b>[SUBJECT]</b>
 *     Get the value of the requested subject by {@link #getSubject()} method.
 *     The value represents an end-user who the client application expects to
 *     grant authorization. If the value is {@code null}, go to the next step
 *     ([ACRs]). Otherwise, follow the sub steps described below.
 *     </p>
 *     <br/>
 *     <ol style="list-style-type: lower-roman;">
 *       <li>
 *         <p>
 *         Compare the value of the requested subject to the subject (= unique
 *         user ID) of the current end-user.
 *         </p>
 *         <br/>
 *       <li>
 *         <p>
 *         If they are equal, go to the next step ([ACRs]).
 *         </p>
 *         <br/>
 *       <li>
 *         <p>
 *         If they are not equal, call Authlete's {@code /auth/authorization/fail}
 *         API with {@code reason=}{@link AuthorizationFailRequest.Reason#DIFFERENT_SUBJECT
 *         DIFFERENT_SUBJECT} and use the response from the API to generate
 *         a response to the client application.
 *         </p>
 *     </ol>
 *     <br/>
 *
 *   <li>
 *     <p><b>[ACRs]</b>
 *     Get the value of ACRs (Authentication Context Class References) by {@link
 *     #getAcrs()} method. The value has come from (1) {@code "acr"} claim in
 *     {@code "claims"} request parameter, (2) {@code "acr_values"} request
 *     parameter, or (3) {@code "default_acr_values"} configuration parameter
 *     of the client application.
 *     </p>
 *     <br/>
 *     <p>
 *     It is ensured that all the ACRs returned by {@link #getAcrs()} method
 *     are supported by the authorization server implementation. In other words,
 *     it is ensured that all the ACRs are listed in the {@code
 *     "acr_values_supported"} configuration parameter of the authorization server.
 *     </p>
 *     <br/>
 *     <p>
 *     If the value of ACRs is {@code null}, go to the next step ([SCOPES]).
 *     Otherwise, follow the sub steps described below.
 *     </p>
 *     <br/>
 *     <ol style="list-style-type: lower-roman;">
 *       <li>
 *         <p>
 *         Get the ACR performed for the authentication of the current end-user.
 *         Note that this value is managed not by Authlete but by the authorization
 *         server implementation. (If the authorization server implementation cannot
 *         handle ACRs, it should not have listed ACRs as {@code "acr_values_supported"}.)
 *         </p>
 *         <br/>
 *       <li>
 *         <p>
 *         Compare the ACR value obtained in the above step to each element in
 *         the ACR array obtained by {@link #getAcrs()} method in the listed order.
 *         If the ACR value was found in the array, go to the next step ([SCOPES]).
 *         </p>
 *         <br/>
 *       <li>
 *         <p>
 *         If the ACR value was not found in the ACR array (= the ACR performed
 *         for the authentication of the current end-user did not match any one
 *         of the ACRs requested by the client application), check whether one
 *         of the requested ACRs must be satisfied or not by calling {@link
 *         #isAcrEssential()} method. If {@link #isAcrEssential()} returns
 *         {@code true}, call Authlete's {@code /auth/authorization/fail} API
 *         with {@code reason=}{@link AuthorizationFailRequest.Reason#ACR_NOT_SATISFIED
 *         ACR_NOT_SATISFIED} and use the response from the API to generate a
 *         response to the client application. Otherwise, go to the next step
 *         ([SCOPES]).
 *         </p>
 *     </ol>
 *     <br/>
 *   <li>
 *     <p><b>[SCOPES]</b>
 *     Get the scopes by {@link #getScopes()}. If the array contains a scope
 *     which has not been granted to the client application by the end-user
 *     in the past, call Authlete's {@code /auth/authorization/fail} API with
 *     {@code reason=}{@link AuthorizationFailRequest.Reason#CONSENT_REQUIRED
 *     CONSENT_REQUIRED} and use the response from the API to generate a
 *     response to the client application. Otherwise, go to the next step
 *     ([DYNAMIC SCOPES]).
 *     </p>
 *     <br/>
 *     <p>
 *     Note that Authlete provides APIs to manage records of granted scopes
 *     ({@code /api/client/granted_scopes/*} APIs), but the APIs work only
 *     in the case the Authlete server you use is a dedicated Authlete server
 *     (contact <a href="mailto:sales@authlete.com">sales@authlete.com</a>
 *     for details). In other words, the APIs of the shared Authlete server
 *     are disabled intentionally (in order to prevent garbage data from
 *     being accumulated) and they return {@code 403 Forbidden}.
 *     </p>
 *     <br/>
 *   <li>
 *     <p><b>[DYNAMIC SCOPES]</b>
 *     Get the dynamic scopes by {@link #getDynamicScopes()}. If the array
 *     contains a scope which has not been granted to the client application
 *     by the end-user in the past, call Authlete's
 *     {@code /auth/authorization/fail} API with
 *     {@code reason=}{@link AuthorizationFailRequest.Reason#CONSENT_REQUIRED
 *     CONSENT_REQUIRED} and use the response from the API to generate a
 *     response to the client application. Otherwise, go to the next step
 *     ([RESOURCES]).
 *     </p>
 *     <br/>
 *     <p>
 *     Note that Authlete provides APIs to manage records of granted scopes
 *     ({@code /api/client/granted_scopes/*} APIs) but dynamic scopes are
 *     not remembered as granted scopes.
 *     </p>
 *     <br/>
 *     <p>
 *     See the description of the {@link DynamicScope} class for details about
 *     dynamic scopes.
 *     </p>
 *     <br/>
 *   <li>
 *     <p><b>[RESOURCES]</b>
 *     Get the requested target resources by {@link #getResources()}. The array
 *     represents the values of the {@code resource} request parameters. If you
 *     want to reject the request, call Authlete's {@code /auth/authorization/fail}
 *     API with {@code reason=}{@link AuthorizationFailRequest.Reason#INVALID_TARGET
 *     INVALID_TARGET} and use the response from the API to generate a response
 *     to the client application. Otherwise, go to the next step ([ISSUE]).
 *     </p>
 *     <br/>
 *     <p>
 *     See "Resource Indicators for OAuth 2.0" for details. Note that the
 *     specification is supported since Authlete 2.2. If the Authlete server you
 *     are using is older than 2.2, {@code getResources()} always returns null.
 *     </p>
 *     <br/>
 *   <li>
 *     <p><b>[ISSUE]</b>
 *     If all the above steps succeeded, the last step is to issue an authorization
 *     code, an ID token and/or an access token. (There is a special case. When
 *     {@code response_type=none}, nothing is issued.) The last step can be
 *     performed by calling Authlete's {@code /auth/authorization/issue} API.
 *     The API requires the following parameters, which are represented as
 *     properties of {@link AuthorizationIssueRequest} class. Prepare these
 *     parameters and call the {@code /auth/authorization/issue} API.
 *     </p>
 *     <br/>
 *     <ul>
 *       <li>
 *         <p><b>[ticket]</b> (required)
 *           This parameter represents a ticket which is exchanged with tokens
 *           at the {@code /auth/authorization/issue} endpoint.
 *           Use the value returned by {@link #getTicket()} as it is.
 *         </p>
 *         <br/>
 *       <li>
 *         <p><b>[subject]</b> (required)
 *           This parameter represents the unique identifier of the current end-user.
 *           It is often called "user ID" and it may or may not be visible to the user.
 *           In any case, it is a number or a string assigned to an end-user by your
 *           service. Authlete does not care about the format of the value of {@code
 *           subject}, but it must consist of only ASCII letters and its length must
 *           be equal to or less than 100.
 *         </p>
 *         <br/>
 *         <p>
 *           When {@link #getSubject()} method returns a non-null value, the
 *           value of {@code subject} parameter is necessarily identical to the
 *           value returned from the method.
 *         </p>
 *         <br/>
 *         <p>
 *           The value of this parameter will be embedded in an ID token as the
 *           value of {@code "sub"} claim. When the value of {@code "subject_type"}
 *           configuration parameter of the client is {@link
 *           com.authlete.common.types.SubjectType#PAIRWISE PAIRWISE}, the value
 *           of {@code "sub"} claim is different from the value specified here.
 *           Note that the behavior for {@code PAIRWISE} is not supported by
 *           Authlete 2.1 and older versions. See <a href=
 *           "http://openid.net/specs/openid-connect-core-1_0.html#SubjectIDTypes"
 *           >8. Subject Identifier Types</a> of OpenID Connect Core 1.0 for
 *           details about subject types.
 *         </p>
 *         <br/>
 *         <p>
 *           You can use the <code>sub</code> request parameter to adjust the value
 *           of the <code>sub</code> claim in an ID token. See the description of the
 *           <code>sub</code> request parameter for details.
 *         </p>
 *         <br/>
 *       <li>
 *         <p><b>[authTime]</b> (optional)
 *           This parameter represents the time when the end-user authentication
 *           occurred. Its value is the number of seconds from 1970-01-01. The
 *           value of this parameter will be embedded in an ID token as the value
 *           of {@code "auth_time"} claim.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p><b>[acr]</b> (optional)
 *           This parameter represents the ACR (Authentication Context Class
 *           Reference) which the authentication of the end-user satisfies.
 *           When {@link #getAcrs()} method returns a non-empty array and
 *           {@link #isAcrEssential()} method returns
 *           {@code true}, the value of this parameter must be one of the array
 *           elements. Otherwise, even {@code null} is allowed. The value of
 *           this parameter will be embedded in an ID token as the value of
 *           {@code "acr"} claim.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p><b>[claims]</b> (optional)
 *           This parameter represents claims of the end-user. "Claims" here
 *           are pieces of information about the end-user such as {@code "name"},
 *           {@code "email"} and {@code "birthdate"}. The authorization server
 *           implementation is required to gather claims of the end-user, format
 *           the claim values into a JSON and set the JSON string as the value
 *           of this parameter.
 *         </p>
 *         <br/>
 *         <p>
 *           The claims which the authorization server implementation is required
 *           to gather can be obtained by {@link #getClaims()} method.
 *         </p>
 *         <br/>
 *         <p>
 *           For example, if {@link #getClaims()} method returns an array which
 *           contains {@code "name"}, {@code "email"} and {@code "birthdate"},
 *           the value of this parameter should look like the following.
 *         </p>
 *         <blockquote>
 *           <pre style="padding: 0.7em; margin: 1em;"> {
 *   "name": "John Smith",
 *   "email": "john@example.com",
 *   "birthdate": "1974-05-06"
 * }</pre>
 *         </blockquote>
 *         <p>
 *           {@link #getClaimsLocales()} lists the end-user's preferred languages
 *           and scripts for claim values, ordered by preference. When {@link
 *           #getClaimsLocales()} returns a non-empty array, its elements should
 *           be taken into account when the authorization server implementation
 *           gathers claim values. Especially, note the excerpt below from
 *           <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsLanguagesAndScripts"
 *           >5.2. Claims Languages and Scripts</a> of OpenID Connect Core 1.0.
 *         </p>
 *         <blockquote>
 *           <p>
 *             <i>"When the OP determines, either through the {@code claims_locales}
 *             parameter, or by other means, that the End-User and Client are
 *             requesting Claims in only one set of languages and scripts, it
 *             is RECOMMENDED that OPs return Claims without language tags
 *             when they employ this language and script. It is also RECOMMENDED
 *             that Clients be written in a manner that they can handle and
 *             utilize Claims using language tags."</i>
 *           </p>
 *         </blockquote>
 *         <p>
 *           If {@link #getClaims()} method returns {@code null} or an empty array,
 *           the value of this parameter should be {@code null}.
 *         </p>
 *         <br/>
 *         <p>
 *           See <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
 *           >5.1. Standard Claims</a> of OpenID Connect Core 1.0 for claim names
 *           and their value formats. Note (1) that the authorization server
 *           implementation may support its special claims
 *           (<a href="http://openid.net/specs/openid-connect-core-1_0.html#AdditionalClaims"
 *           >5.1.2. Additional Claims</a>) and (2) that claim names may be followed
 *           by a language tag
 *           (<a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsLanguagesAndScripts"
 *           >5.2. Claims Languages and Scripts</a>). Read the specification of
 *           <a href="http://openid.net/specs/openid-connect-core-1_0.html"
 *           >OpenID Connect Core 1.0</a> for details.
 *         </p>
 *         <br/>
 *         <p>
 *           The claim values in this parameter will be embedded in an ID token.
 *         </p>
 *         <br/>
 *         <p>
 *           {@link #getIdTokenClaims()} is available since version 2.25. The method
 *           returns the value of the {@code "id_token"} property in the {@code "claims"}
 *           request parameter or in the {@code "claims"} property in a request object.
 *           The value returned from the method should be considered when you prepare
 *           claim values. See the description of the method for details. Note that,
 *           however, old Authlete servers don't support this response parameter.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p><b>[properties]</b> (optional)
 *           Extra properties to associate with an access token and/or an authorization
 *           code that may be issued by this request. Note that <code>properties</code>
 *           parameter is accepted only when Content-Type of the request to Authlete's
 *           {@code /auth/authorization/issue} is <code>application/json</code>, so
 *           don't use <code>application/x-www-form-urlencoded</code> if you want to
 *           use this request parameter. See <a href=
 *           "https://www.authlete.com/documents/definitive_guide/extra_properties"
 *           >Extra Properties</a> for details.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p><b>[scopes]</b> (optional)
 *           Scopes to associate with an access token and/or an authorization code.
 *           If this parameter is null, the scopes specified in the original authorization
 *           request from the client application are used. In other cases, including the
 *           case of an empty array, the specified scopes will replace the scopes
 *           contained in the original authorization request.
 *         </p>
 *         <br/>
 *         <p>
 *           Even scopes that are not included in the original authorization request
 *           can be specified. However, as an exception, {@code "openid"} scope is
 *           ignored on Authlete server side if it is not included in the original
 *           request. It is because the existence of {@code "openid"} scope considerably
 *           changes the validation steps and because adding {@code "openid"} triggers
 *           generation of an ID token (although the client application has not requested
 *           it) and the behavior is a major violation against the specification.
 *         </p>
 *         <br/>
 *         <p>
 *           If you add <code>"offline_access"</code> scope although it is not included
 *           in the original request, keep in mind that the specification requires explicit
 *           consent from the user for the scope (<a href=
 *           "http://openid.net/specs/openid-connect-core-1_0.html#OfflineAccess">OpenID
 *           Connect Core 1.0, 11. Offline Access</a>). When <code>"offline_access"</code>
 *           is included in the original request, the current implementation of Authlete's
 *           {@code /auth/authorization} API checks whether the request has come along with
 *           <code>prompt</code> request parameter and the value includes
 *           <code>"consent"</code>. However, note that the implementation of Authlete's
 *           {@code /auth/authorization/issue} API does not perform such checking if
 *           <code>"offline_access"</code> scope is added via this scopes parameter.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p><b>[sub]</b> (optional)
 *           The value of the {@code sub} claim in an ID token which may be issued.
 *           If the value of this request parameter is not empty, it is used as the
 *           value of the {@code sub} claim. Otherwise, the value of the {@code subject}
 *           request parameter is used as the value of the {@code sub} claim. The main
 *           purpose of this parameter is to hide the actual value of the subject from
 *           client applications.
 *         </p>
 *       </li>
 *     </ul>
 *     <br/>
 *     <p>
 *     {@code /auth/authorization/issue} API returns a response in JSON format
 *     which can be mapped to {@link AuthorizationIssueResponse}. Use the response
 *     from the API to generate a response to the client application. See the
 *     description of {@link AuthorizationIssueResponse} for details.
 *     </p>
 * </ol>
 * </blockquote>
 * </dd>
 *
 *
 * <dt><b>{@link Action#INTERACTION INTERACTION}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERACTION"}, it means
 * that the request from the client application has no problem and requires
 * the authorization server to process the request with user interaction by
 * an HTML form.
 * </p>
 * <p>
 * The purpose of the UI displayed to the end-user is to ask the end-user
 * to grant authorization to a client application. The items described
 * below are some points which the authorization server implementation
 * should take into account when it builds the UI.
 * </p>
 *
 * <blockquote>
 * <ol>
 *   <li>
 *     <p><b>[DISPLAY MODE]</b>
 *       {@code AuthorizationResponse} contains {@code "display"} parameter.
 *       The value can be obtained by {@link #getDisplay()} method and is
 *       one of {@link Display#PAGE PAGE} (default), {@link Display#POPUP
 *       POPUP}, {@link Display#TOUCH TOUCH} and {@link Display#WAP WAP}.
 *       The meanings of the values are described in <a href=
 *       "http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
 *       >3.1.2.1. Authentication Request</a> of OpenID Connect Core 1.0.
 *       Basically, the authorization server implementation should display
 *       the UI which is suitable for the display mode, but it is okay for
 *       the authorization server implementation to <i>"attempt to detect
 *       the capabilities of the User Agent and present an appropriate
 *       display."</i>
 *     </p>
 *     <br/>
 *     <p>
 *       It is ensured that the value returned by {@link #getDisplay()} is
 *       one of the supported display values which are specified by {@code
 *       "display_values_supported"} configuration parameter of the service.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[UI LOCALE]</b>
 *       {@code AuthorizationResponse} contains {@code "ui_locales"} parameter.
 *       The value can be obtained by {@link #getUiLocales()} and it is an
 *       array of language tag values (such as {@code "fr-CA"} and {@code
 *       "en"}) ordered by preference. The authorization server implementation
 *       should display the UI in one of the language listed in the {@code
 *       "ui_locales"} parameter when possible.
 *     </p>
 *     <br/>
 *     <p>
 *       It is ensured that language tags returned by {@link #getUiLocales()}
 *       are contained in the list of supported UI locales which are specified
 *       by {@code "ui_locales_supported"} configuration parameter of the
 *       service.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[CLIENT INFORMATION]</b>
 *       The authorization server implementation should show the end-user
 *       information about the client application. The information can be
 *       obtained by {@link #getClient()} method.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[SCOPES]</b>
 *       A client application requires authorization for specific permissions.
 *       In OAuth 2.0 specification, "scope" is a technical term which represents
 *       a permission. {@link #getScopes()} method returns scopes requested by
 *       the client application. The authorization server implementation should
 *       show the end-user the scopes.
 *     </p>
 *     <br/>
 *     <p>
 *       The authorization server implementation may choose not to show scopes
 *       to which the end-user has given consent in the past. To put it the
 *       other way around, the authorization server implementation may show
 *       only the scopes to which the end-user has not given consent yet.
 *       However, if the value returned from {@link #getPrompts()} contains
 *       {@link Prompt#CONSENT CONSENT}, the authorization server implementation
 *       has to obtain explicit consent from the end-user even if the end-user
 *       has given consent to all the requested scopes in the past.
 *     </p>
 *     <br/>
 *     <p>
 *       Note that Authlete provides APIs to manage records of granted scopes
 *       ({@code /api/client/granted_scopes/*} APIs), but the APIs work only
 *       in the case the Authlete server you use is a dedicated Authlete server
 *       (contact <a href="mailto:sales@authlete.com">sales@authlete.com</a>
 *       for details). In other words, the APIs of the shared Authlete server
 *       are disabled intentionally (in order to prevent garbage data from
 *       being accumulated) and they return {@code 403 Forbidden}.
 *     </p>
 *     <br/>
 *     <p>
 *       It is ensured that scopes returned by {@link #getScopes()} are
 *       contained in the list of supported scopes which are specified by
 *       {@code "scopes_supported"} configuration parameter of the service.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[DYNAMIC SCOPES]</b>
 *       The authorization request may include dynamic scopes. The list of
 *       recognized dynamic scopes are accessible by {@link #getDynamicScopes()}
 *       method. See the description of the {@link DynamicScope} class for
 *       details about dynamic scopes.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[AUTHORIZATION DETAILS]</b>
 *       The authorization server implementation should show the end-user
 *       "authorization details" if the request includes it.
 *       {@link #getAuthorizationDetails()} returns the content of the
 *       {@code authorization_details} request parameter.
 *     </p>
 *     <br/>
 *     <p>
 *       See "OAuth 2.0 Rich Authorization Requests" for details. Note that the
 *       specification is supported since Authlete 2.2. If the Authlete server
 *       you are using is older than 2.2, {@code getAuthorizationDetails()}
 *       always returns null.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[PURPOSE]</b>
 *       The authorization server implementation must show the value of the
 *       {@code purpose} request parameter if it supports <a href=
 *       "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *       >OpenID Connect for Identity Assurance 1.0</a>. See <a href=
 *       "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.8"
 *       >8. Transaction-specific Purpose</a> in the specification for details.
 *     </p>
 *     <br/>
 *     <p>
 *       {@link #getPurpose()} returns the value of the {@code purpose} request
 *       parameter. However, if the Authlete server you are using does not support
 *       OpenID Connect for Identity Assurance 1.0 (in other words, if the Authlete
 *       server is older than 2.2), {@code getPurpose()} always returns null.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[END-USER AUTHENTICATION]</b>
 *       Necessarily, the end-user must be authenticated (= must login your
 *       service) before granting authorization to the client application.
 *       Simply put, a login form is expected to be displayed for end-user
 *       authentication. The authorization server implementation must follow
 *       the steps described below to comply with OpenID Connect. (Or just
 *       always show a login form if it's too much of a bother to follow
 *       the steps below.)
 *     </p>
 *     <br/>
 *     <ol style="list-style-type: lower-roman;">
 *       <li>
 *         <p>
 *           Get the value of {@link #getPrompts()}. It corresponds to the
 *           value of the {@code prompt} request parameter. Details of the
 *           request parameter are described in
 *           <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
 *           >3.1.2.1. Authentication Request</a> of OpenID Connect Core 1.0.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p>
 *           If the value returned from {@link #getPrompts()} contains
 *           {@link Prompt#SELECT_ACCOUNT SELECT_ACCOUNT}, display a form
 *           to urge the end-user to select one of his/her accounts for login.
 *           If {@link #getSubject()} returns a non-null value, it is the
 *           end-user ID that the client application expects, so the value
 *           should be used to determine the value of the login ID. Note
 *           that a subject and a login ID are not necessarily equal. If
 *           {@link #getSubject()} returns null, the value returned by
 *           {@link #getLoginHint()} should be referred to as a hint to
 *           determine the value of the login ID. {@link #getLoginHint()}
 *           method simply returns the value of the {@code login_hint}
 *           request parameter.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p>
 *           If the value returned from {@link #getPrompts()} contains
 *           {@link Prompt#LOGIN LOGIN}, display a form to urge the end-user
 *           to login even if the end-user has already logged in. If {@link
 *           #getSubject()} returns a non-null value, it is the end-user
 *           ID that the client application expects, so the value should
 *           be used to determine the value of the login ID. Note that a
 *           subject and a login ID are not necessarily equal. If {@link
 *           #getSubject()} returns null, the value returned by {@link
 *           #getLoginHint()} should be referred to as a hint to determine
 *           the value of the login ID. {@link #getLoginHint()} method
 *           simply returns the value of the {@code login_hint} request
 *           parameter.
 *         </p>
 *         <br/>
 *       </li>
 *       <li>
 *         <p>
 *           If the value returned from {@link #getPrompts()} does not
 *           contain {@link Prompt#LOGIN LOGIN}, the authorization server
 *           implementation does not have to authenticate the end-user
 *           if all the conditions described below are satisfied. If
 *           any one of the conditions is not satisfied, show a login
 *           form to authenticate the end-user.
 *         </p>
 *         <br/>
 *         <ul>
 *           <li>
 *             <p>
 *               An end-user has already logged in your service.
 *             </p>
 *             <br/>
 *           <li>
 *             <p>
 *               The login ID of the current end-user matches the value
 *               returned by {@link #getSubject()}. This check is required
 *               only when {@link #getSubject()} returns a non-null value.
 *             </p>
 *             <br/>
 *           <li>
 *             <p>
 *               The max age, which is the number of seconds obtained by
 *               {@link #getMaxAge()} method, has not passed since the
 *               current end-user logged in your service. This check is
 *               required only when {@link #getMaxAge()} returns a
 *               non-zero value.
 *             </p>
 *             <br/>
 *             <p>
 *               If the authorization server implementation does not manage
 *               authentication time of end-users (= if the authorization
 *               server implementation cannot know when end-users logged in)
 *               and if {@link #getMaxAge()} returns a non-zero value, a
 *               login form should be displayed.
 *             </p>
 *             <br/>
 *           </li>
 *           <li>
 *             <p>
 *               The ACR (Authentication Context Class Reference) of the
 *               authentication performed for the current end-user satisfies
 *               one of the ACRs listed by {@link #getAcrs()}. This check is
 *               required only when {@link #getAcrs()} returns a non-empty
 *               array.
 *             </p>
 *           </li>
 *         </ul>
 *       </li>
 *     </ol>
 *     <br/>
 *     <p>
 *       In every case, the end-user authentication must satisfy one of the ACRs
 *       listed by {@link #getAcrs()} when {@link #getAcrs()} returns a non-empty
 *       array and {@link #isAcrEssential()} returns {@code true}.
 *     </p>
 *     <br/>
 *
 *   <li>
 *     <p><b>[GRANT/DENY BUTTONS]</b>
 *       The end-user is supposed to choose either (1) to grant authorization
 *       to the client application or (2) to deny the authorization request.
 *       The UI must have UI components to accept the decision by the user.
 *       Usually, a button to grant authorization and a button to deny the
 *       request are provided.
 *     </p>
 *     <br/>
 * </ol>
 * </blockquote>
 *
 * <p>
 * When the subject returned by {@link #getSubject()} method is not {@code null},
 * the end-user authentication must be performed for the subject, meaning that
 * the authorization server implementation should repeatedly show a login form
 * until the subject is successfully authenticated.
 * </p>
 *
 * <p>
 * The end-user will choose either (1) to grant authorization to the client
 * application or (2) to deny the authorization request. When the end-user
 * chose to deny the authorization request, call Authlete's {@code
 * /auth/authorization/fail} API with {@code reason=}{@link
 * AuthorizationFailRequest.Reason#DENIED DENIED} and use the response from
 * the API to generate a response to the client application.
 * </p>
 *
 * <p>
 * When the end-user chose to grant authorization to the client application,
 * the authorization server implementation has to issue an authorization code,
 * an ID token, and/or an access token to the client application. (There is a
 * special case. When {@code response_type=none}, nothing is issued.) Issuing
 * the tokens can be performed by calling Authlete's {@code /auth/authorization/issue}
 * API. Read [ISSUE] written above in the description for the case of {@code
 * action=NO_INTERACTION}.
 * </p>
 *
 * <p>
 * Authlete 2.3 and newer versions support <a href=
 * "https://openid.net/specs/fapi-grant-management.html">Grant Management for
 * OAuth 2.0</a>. An authorization request may contain a {@code grant_id}
 * request parameter which is defined in the specification. If the value of
 * the request parameter is valid, {@link #getGrantSubject()} will return the
 * subject of the user who has given the grant to the client application.
 * Authorization server implementations may use the value returned from
 * {@link #getGrantSubject()} in order to determine the user to authenticate.
 * </p>
 * </dd>
 * </dl>
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749">RFC 6749, OAuth 2.0</a>
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html"
 *      >OpenID Connect Core 1.0</a>
 *
 * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html"
 *      >OpenID Connect Dynamic Client Registration 1.0</a>
 *
 * @see <a href="http://openid.net/specs/openid-connect-discovery-1_0.html"
 *      >OpenID Connect Discovery 1.0</a>
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationResponse extends ApiResponse
{
    private static final long serialVersionUID = 16L;


    /**
     * The next action that the service implementation should take.
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
         * The authorization request was wrong and the service implementation
         * should notify the client application of the error by
         * {@code "400 Bad Request"}.
         */
        BAD_REQUEST,

        /**
         * The authorization request was wrong and the service implementation
         * should notify the client application of the error by
         * {@code "302 Found"}.
         */
        LOCATION,

        /**
         * The authorization request was wrong and the service implementation
         * should notify the client application of the error by
         * {@code "200 OK"} with an HTML which triggers redirection by
         * JavaScript.
         *
         * @see <a href="http://openid.net/specs/oauth-v2-form-post-response-mode-1_0.html"
         *      >OAuth 2.0 Form Post Response Mode</a>
         */
        FORM,

        /**
         * The authorization request was valid and the service implementation
         * should issue an authorization code, an ID token and/or an access
         * token without interaction with the end-user.
         */
        NO_INTERACTION,

        /**
         * The authorization request was valid and the service implementation
         * should display UI to ask for authorization from the end-user.
         */
        INTERACTION
    }


    private static final String SUMMARY_FORMAT
        = "ticket=%s, action=%s, serviceNumber=%d, clientNumber=%d, clientId=%d, "
        + "clientSecret=%s, clientType=%s, developer=%s, display=%s, maxAge=%d, "
        + "scopes=%s, uiLocales=%s, claimsLocales=%s, claims=%s, acrEssential=%s, "
        + "clientIdAliasUsed=%s, "
        + "acrs=%s, subject=%s, loginHint=%s, lowestPrompt=%s, prompts=%s";


    /*
     * Do not change variable names. They must match the variable names
     * in JSONs which are exchanged between clients and Authlete server.
     */


    private Action action;
    private Service service;
    private Client client;
    private Display display;
    private int maxAge;
    private Scope[] scopes;
    private DynamicScope[] dynamicScopes;
    private String[] uiLocales;
    private String[] claimsLocales;
    private String[] claims;
    private boolean acrEssential;
    private boolean clientIdAliasUsed;
    private String[] acrs;
    private String subject;
    private String loginHint;
    private Prompt lowestPrompt;
    private Prompt[] prompts;
    private String requestObjectPayload;
    private String idTokenClaims;
    private String userInfoClaims;
    private URI[] resources;
    private AuthzDetails authorizationDetails;
    private String purpose;
    private GMAction gmAction;
    private String grantId;
    private String grantSubject;
    private Grant grant;
    private String responseContent;
    private String ticket;


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
     * Get the information about the service.
     *
     * @return
     *         Information about the service.
     *
     * @since 1.23
     */
    public Service getService()
    {
        return service;
    }


    /**
     * Set the information about the service.
     *
     * @param service
     *         Information about the service.
     *
     * @since 1.23
     */
    public void setService(Service service)
    {
        this.service = service;
    }


    /**
     * Get the information about the client application which has made
     * the authorization request.
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html"
     *      >OpenID Connect Dynamic Client Registration 1.0</a>
     */
    public Client getClient()
    {
        return client;
    }


    /**
     * Set the information about the client application which has made
     * the authorization request.
     */
    public void setClient(Client client)
    {
        this.client = client;
    }


    /**
     * Get the display mode which the client application requests
     * by {@code "display"} request parameter. When the authorization
     * request does not contain {@code "display"} request parameter,
     * this method returns {@link Display#PAGE} as the default value.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     */
    public Display getDisplay()
    {
        return display;
    }


    /**
     * Set the display mode which the client application requires
     * by {@code "display"} request parameter.
     */
    public void setDisplay(Display display)
    {
        this.display = display;
    }


    /**
     * Get the maximum authentication age which is the allowable
     * elapsed time in seconds since the last time the end-user
     * was actively authenticated by the service implementation.
     * The value comes from {@code "max_age"} request parameter
     * or {@code "default_max_age"} configuration parameter of
     * the client application. 0 may be returned which means
     * that the max age constraint does not have to be imposed.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
     */
    public int getMaxAge()
    {
        return maxAge;
    }


    /**
     * Set the maximum authentication age.
     */
    public void setMaxAge(int maxAge)
    {
        this.maxAge = maxAge;
    }


    /**
     * Get the scopes which the client application requests by {@code
     * "scope"} request parameter. When the authorization request does
     * not contain {@code "scope"} request parameter, this method
     * returns a list of scopes which are marked as default by the
     * service implementation. {@code null} may be returned if the
     * authorization request does not contain valid scopes and none
     * of registered scopes is marked as default.
     *
     * <p>
     * You may want to enable end-users to select/deselect scopes in
     * the authorization page. In other words, you may want to use
     * a different set of scopes than the set specified by the original
     * authorization request. You can replace scopes when you call
     * Authlete's /auth/authorization/issue API. See the description
     * of {@link AuthorizationIssueRequest#setScopes(String[])} for
     * details.
     * </p>
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     *      >OAuth 2.0, 3.3. Access Token Scope</a>
     */
    public Scope[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes which the client application requests or the
     * default scopes when the authorization request does not contain
     * {@code "scope"} request parameter.
     *
     * <p>
     * You may want to enable end-users to select/deselect scopes in
     * the authorization page. In other words, you may want to use
     * a different set of scopes than the set specified by the original
     * authorization request. You can replace scopes when you call
     * Authlete's /auth/authorization/issue API. See the description
     * of {@link AuthorizationIssueRequest#setScopes(String[])} for
     * details.
     * </p>
     */
    public void setScopes(Scope[] scopes)
    {
        this.scopes = scopes;
    }


    /**
     * Get the dynamic scopes which the client application requested
     * by the {@code scope} request parameter. See the description of
     * {@link DynamicScope} for details.
     *
     * @return
     *         The list of dynamic scopes.
     *
     * @since 2.92
     *
     * @see DynamicScope
     */
    public DynamicScope[] getDynamicScopes()
    {
        return dynamicScopes;
    }


    /**
     * Set the dynamic scopes which the client application requested
     * by the {@code scope} request parameter. See the description of
     * {@link DynamicScope} for details.
     *
     * @param dynamicScopes
     *         The list of dynamic scopes.
     *
     * @since 2.92
     *
     * @see DynamicScope
     */
    public void setDynamicScopes(DynamicScope[] dynamicScopes)
    {
        this.dynamicScopes = dynamicScopes;
    }


    /**
     * Get the list of preferred languages and scripts for the user
     * interface. The value comes from {@code "ui_locales"} request
     * parameter.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     */
    public String[] getUiLocales()
    {
        return uiLocales;
    }


    /**
     * Set the list of preferred languages and scripts for the user
     * interface.
     */
    public void setUiLocales(String[] uiLocales)
    {
        this.uiLocales = uiLocales;
    }


    /**
     * Get the list of preferred languages and scripts for claim
     * values contained in the ID token. The value comes from
     * {@code "claims_locales"} request parameter.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsLanguagesAndScripts"
     *      >OpenID Connect Core 1.0, 5.2. Claims Languages and Scripts</a>
     */
    public String[] getClaimsLocales()
    {
        return claimsLocales;
    }


    /**
     * Set the list of preferred languages and scripts for claim
     * values contained in the ID token.
     */
    public void setClaimsLocales(String[] claimsLocales)
    {
        this.claimsLocales = claimsLocales;
    }


    /**
     * Get the list of claims that the client application requests
     * to be embedded in the ID token. The value comes from
     * {@code "scope"} and {@code "claims"} request parameters of
     * the original authorization request.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     *      >OpenID Connect Core 1.0, 5.5. Requesting Claims using
     *      the "claims" Request Parameter</a>
     */
    public String[] getClaims()
    {
        return claims;
    }


    /**
     * Set the list of claims that the client application requests
     * to be embedded in the ID token.
     */
    public void setClaims(String[] claims)
    {
        this.claims = claims;
    }


    /**
     * Get the flag which indicates whether the end-user authentication
     * must satisfy one of the requested ACRs.
     *
     * <p>
     * This method returns {@code true} only when the authorization
     * request from the client contains {@code "claim"} request parameter
     * and it contains an entry for {@code "acr"} claim with
     * {@code "essential":true}.
     * </p>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#IndividualClaimsRequests"
     *      >OpenID Connect Core 1.0, 5.5.1. Individual Claims Requests</a>
     */
    public boolean isAcrEssential()
    {
        return acrEssential;
    }


    /**
     * Set the flag which indicates whether the end-user authentication
     * must satisfy one of the requested ACRs.
     */
    public void setAcrEssential(boolean essential)
    {
        this.acrEssential = essential;
    }


    /**
     * Get the flag which indicates whether the value of the {@code client_id}
     * request parameter included in the authorization request is the client
     * ID alias or the original numeric client ID.
     *
     * @since 2.3
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether the value of the {@code client_id}
     * request parameter included in the authorization request is the client
     * ID alias or the original numeric client ID.
     *
     * @since 2.3
     */
    public void setClientIdAliasUsed(boolean used)
    {
        clientIdAliasUsed = used;
    }


    /**
     * Get the list of ACRs (Authentication Context Class References)
     * requested by the client application. The value come from (1)
     * {@code "acr"} claim in {@code "claims"} request parameter, (2)
     * {@code "acr_values"} request parameter, or (3) {@code
     * "default_acr_values"} configuration parameter of the client
     * application.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     *      >OpenID Connect Core 1.0, 5.5. Requesting Claims using
     *      the "claims" Request Parameter</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     *      >OpenID Connect Dynamic Client Registration 1.0, 2. Client Metadata</a>
     */
    public String[] getAcrs()
    {
        return acrs;
    }


    /**
     * Set the list of ACRs (Authentication Context Class References)
     * requested by the client application.
     */
    public void setAcrs(String[] acrs)
    {
        this.acrs = acrs;
    }


    /**
     * Get the subject (= end-user's unique ID) that the client
     * application requests. The value comes from {@code "sub"}
     * claim in {@code "claims"} request parameter. This method
     * may return {@code null} (probably in most cases).
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     *      >OpenID Connect Core 1.0, 5.5. Requesting Claims using
     *      the "claims" Request Parameter</a>
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= end-user's login ID) that the client
     * application requests.
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    /**
     * Get the value of login hint, which is specified by the client
     * application using {@code "login_hint"} request parameter.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     */
    public String getLoginHint()
    {
        return loginHint;
    }


    /**
     * Set the value of login hint, which is specified by the client
     * application using {@code "login_hint"} request parameter.
     */
    public void setLoginHint(String loginHint)
    {
        this.loginHint = loginHint;
    }


    /**
     * Get the prompt that the UI displayed to the end-user must satisfy
     * at least. The value comes from {@code "prompt"} request parameter.
     * When the authorization request does not contain {@code "prompt"}
     * parameter, this method returns {@link Prompt#CONSENT CONSENT} as
     * the default value.
     *
     * <p>
     * This method is deprecated. Use {@link #getPrompts()} instead.
     * </p>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     *
     * @deprecated
     */
    @Deprecated
    public Prompt getLowestPrompt()
    {
        return lowestPrompt;
    }


    /**
     * Set the prompt that the UI displayed to the end-user must satisfy
     * at least.
     */
    public void setLowestPrompt(Prompt prompt)
    {
        this.lowestPrompt = prompt;
    }


    /**
     * Get the list of prompts contained in the authorization request
     * (= the value of {@code prompt} request parameter).
     *
     * @return
     *         The list of prompts contained in the authorization request.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
     *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
     *
     * @since 1.34
     */
    public Prompt[] getPrompts()
    {
        return prompts;
    }


    /**
     * Set the list of prompts contained in the authorization request
     * (= the value of {@code prompt} request parameter).
     *
     * @param prompts
     *         The list of prompts contained in the authorization request.
     *
     * @since 1.34
     */
    public void setPrompts(Prompt[] prompts)
    {
        this.prompts = prompts;
    }


    /**
     * Get the payload part of the request object.
     *
     * <p>
     * This method returns {@code null} if the authorization request does not
     * include a request object.
     * </p>
     *
     * @return
     *         The payload part of the request object in JSON format.
     *
     * @since 2.22
     */
    public String getRequestObjectPayload()
    {
        return requestObjectPayload;
    }


    /**
     * Set the payload part of the request object.
     *
     * @param payload
     *         The payload part of the request object in JSON format.
     *
     * @since 2.22
     */
    public void setRequestObjectPayload(String payload)
    {
        this.requestObjectPayload = payload;
    }


    /**
     * Get the value of the {@code "id_token"} property in the {@code "claims"}
     * request parameter or in the {@code "claims"} property in a request object.
     *
     * <p>
     * A client application may request certain claims be embedded in an ID
     * token or in a response from the UserInfo endpoint. There are several
     * ways. Including the {@code claims} request parameter and including the
     * {@code claims} property in a request object are such examples. In both
     * the cases, the value of the {@code claims} parameter/property is JSON.
     * Its format is described in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     * >5.5. Requesting Claims using the "claims" Request Parameter</a> of
     * <a href="https://openid.net/specs/openid-connect-core-1_0.html">OpenID
     * Connect Core 1.0</a>.
     * </p>
     *
     * <p>
     * The following is an excerpt from the specification. You can find
     * {@code "userinfo"} and {@code "id_token"} are top-level properties.
     * </p>
     *
     * <pre>
     * {
     *  "userinfo":
     *   {
     *    "given_name": {"essential": true},
     *    "nickname": null,
     *    "email": {"essential": true},
     *    "email_verified": {"essential": true},
     *    "picture": null,
     *    "http://example.info/claims/groups": null
     *  },
     * "id_token":
     *  {
     *   "auth_time": {"essential": true},
     *   "acr": {"values": ["urn:mace:incommon:iap:silver"] }
     *  }
     * }
     * </pre>
     *
     * <p>
     * This method ({@code getIdTokenClaims()}) returns the value of the
     * {@code "id_token"} property in JSON format. For example, if the
     * JSON above is included in an authorization request, this method
     * returns JSON equivalent to the following.
     * </p>
     *
     * <pre>
     *  {
     *   "auth_time": {"essential": true},
     *   "acr": {"values": ["urn:mace:incommon:iap:silver"] }
     *  }
     * </pre>
     *
     * <p>
     * Note that if a request object is given and it contains the
     * {@code claims} property and if the {@code claims} request
     * parameter is also given, this method returns the value in
     * the former.
     * </p>
     *
     * @return
     *         The value of the {@code "id_token"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @since 2.25
     */
    public String getIdTokenClaims()
    {
        return idTokenClaims;
    }


    /**
     * Set the value of the {@code "id_token"} property in the {@code "claims"}
     * request parameter or in the {@code "claims"} property in a request object.
     *
     * @param idTokenClaims
     *         The value of the {@code "id_token"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @since 2.25
     */
    public void setIdTokenClaims(String idTokenClaims)
    {
        this.idTokenClaims = idTokenClaims;
    }


    /**
     * Get the value of the {@code "userinfo"} property in the {@code "claims"}
     * request parameter or in the {@code "claims"} property in a request object.
     *
     * <p>
     * A client application may request certain claims be embedded in an ID
     * token or in a response from the UserInfo endpoint. There are several
     * ways. Including the {@code claims} request parameter and including the
     * {@code claims} property in a request object are such examples. In both
     * the cases, the value of the {@code claims} parameter/property is JSON.
     * Its format is described in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     * >5.5. Requesting Claims using the "claims" Request Parameter</a> of
     * <a href="https://openid.net/specs/openid-connect-core-1_0.html">OpenID
     * Connect Core 1.0</a>.
     * </p>
     *
     * <p>
     * The following is an excerpt from the specification. You can find
     * {@code "userinfo"} and {@code "id_token"} are top-level properties.
     * </p>
     *
     * <pre>
     * {
     *  "userinfo":
     *   {
     *    "given_name": {"essential": true},
     *    "nickname": null,
     *    "email": {"essential": true},
     *    "email_verified": {"essential": true},
     *    "picture": null,
     *    "http://example.info/claims/groups": null
     *  },
     * "id_token":
     *  {
     *   "auth_time": {"essential": true},
     *   "acr": {"values": ["urn:mace:incommon:iap:silver"] }
     *  }
     * }
     * </pre>
     *
     * <p>
     * This method ({@code getUserInfoClaims()}) returns the value of the
     * {@code "userinfo"} property in JSON format. For example, if the
     * JSON above is included in an authorization request, this method
     * returns JSON equivalent to the following.
     * </p>
     *
     * <pre>
     *   {
     *    "given_name": {"essential": true},
     *    "nickname": null,
     *    "email": {"essential": true},
     *    "email_verified": {"essential": true},
     *    "picture": null,
     *    "http://example.info/claims/groups": null
     *  }
     * </pre>
     *
     * <p>
     * Note that if a request object is given and it contains the
     * {@code claims} property and if the {@code claims} request
     * parameter is also given, this method returns the value in
     * the former.
     * </p>
     *
     * @return
     *         The value of the {@code "userinfo"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @since 2.25
     */
    public String getUserInfoClaims()
    {
        return userInfoClaims;
    }


    /**
     * Set the value of the {@code "userinfo"} property in the {@code "claims"}
     * request parameter or in the {@code "claims"} property in a request object.
     *
     * @param userInfoClaims
     *         The value of the {@code "userinfo"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @since 2.25
     */
    public void setUserInfoClaims(String userInfoClaims)
    {
        this.userInfoClaims = userInfoClaims;
    }


    /**
     * Get the resources specified by the {@code resource} request parameters
     * or by the {@code resource} property in the request object. If both are
     * given, the values in the request object take precedence.
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     *
     * @return
     *         Target resources.
     *
     * @since 2.62
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Set the resources specified by the {@code resource} request parameters
     * or by the {@code resource} property in the request object. If both are
     * given, the values in the request object should be set.
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     *
     * @param resources
     *         Target resources.
     *
     * @since 2.62
     */
    public void setResources(URI[] resources)
    {
        this.resources = resources;
    }


    /**
     * Get the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
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
     * Get the value of the {@code purpose} request parameter.
     *
     * <p>
     * The {@code purpose} request parameter is defined in <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.8"
     * >8. Transaction-specific Purpose</a> of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * {@code purpose} OPTIONAL. String describing the purpose for obtaining
     * certain user data from the OP. The purpose MUST NOT be shorter than 3
     * characters and MUST NOT be longer than 300 characters. If these rules
     * are violated, the authentication request MUST fail and the OP returns
     * an error {@code invalid_request} to the RP.
     * </p>
     * </blockquote>
     *
     * <p>
     * NOTE: This method works only when Authlete server you are using supports
     * OpenID Connect for Identity Assurance 1.0.
     * </p>
     *
     * @return
     *         The value of the {@code purpose} request parameter.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.8"
     *      >OpenID Connect for Identity Assurance 1.0, 8. Transaction-specific Purpose</a>
     *
     * @since 2.63
     */
    public String getPurpose()
    {
        return purpose;
    }


    /**
     * Set the value of the {@code purpose} request parameter.
     *
     * <p>
     * The {@code purpose} request parameter is defined in <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.8"
     * >8. Transaction-specific Purpose</a> of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a> as follows:
     * </p>
     *
     * <blockquote>
     * <p>
     * {@code purpose} OPTIONAL. String describing the purpose for obtaining
     * certain user data from the OP. The purpose MUST NOT be shorter than 3
     * characters and MUST NOT be longer than 300 characters. If these rules
     * are violated, the authentication request MUST fail and the OP returns
     * an error {@code invalid_request} to the RP.
     * </p>
     * </blockquote>
     *
     * @param purpose
     *         The value of the {@code purpose} request parameter.
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#rfc.section.8"
     *      >OpenID Connect for Identity Assurance 1.0, 8. Transaction-specific Purpose</a>
     *
     * @since 2.63
     */
    public void setPurpose(String purpose)
    {
        this.purpose = purpose;
    }


    /**
     * Get the value of the {@code grant_management_action} request parameter.
     *
     * <p>
     * The {@code grant_management_action} request parameter is defined in
     * <a href="https://openid.net/specs/fapi-grant-management.html">Grant
     * Management for OAuth 2.0</a>, which is supported by Authlete 2.3 and
     * newer versions.
     * </p>
     *
     * @return
     *         A grant management action. {@code null} or one of
     *         {@link GMAction#CREATE CREATE}, {@link GMAction#REPLACE REPLACE}
     *         and {@link GMAction#UPDATE UPDATE}.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public GMAction getGmAction()
    {
        return gmAction;
    }


    /**
     * Set the value of the {@code grant_management_action} request parameter.
     *
     * <p>
     * The {@code grant_management_action} request parameter is defined in
     * <a href="https://openid.net/specs/fapi-grant-management.html">Grant
     * Management for OAuth 2.0</a>, which is supported by Authlete 2.3 and
     * newer versions.
     * </p>
     *
     * @param action
     *         A grant management action. {@code null} or one of
     *         {@link GMAction#CREATE CREATE}, {@link GMAction#REPLACE REPLACE}
     *         and {@link GMAction#UPDATE UPDATE}.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public void setGmAction(GMAction action)
    {
        this.gmAction = action;
    }


    /**
     * Get the value of the {@code grant_id} request parameter.
     *
     * <p>
     * The {@code grant_id} request parameter is defined in
     * <a href="https://openid.net/specs/fapi-grant-management.html">Grant
     * Management for OAuth 2.0</a>, which is supported by Authlete 2.3 and
     * newer versions.
     * </p>
     *
     * @return
     *         A grant ID.
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
     * Set the value of the {@code grant_id} request parameter.
     *
     * <p>
     * The {@code grant_id} request parameter is defined in
     * <a href="https://openid.net/specs/fapi-grant-management.html">Grant
     * Management for OAuth 2.0</a>, which is supported by Authlete 2.3 and
     * newer versions.
     * </p>
     *
     * @param grantId
     *         A grant ID.
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
     * Get the subject of the user who has given the grant which is identified
     * by the {@code grant_id} request parameter.
     *
     * <p>
     * Authlete 2.3 and newer versions support <a href=
     * "https://openid.net/specs/fapi-grant-management.html">Grant Management
     * for OAuth 2.0</a>. An authorization request may contain a {@code grant_id}
     * request parameter which is defined in the specification. If the value of
     * the request parameter is valid, {@link #getGrantSubject()} will return
     * the subject of the user who has given the grant to the client application.
     * Authorization server implementations may use the value returned from
     * {@link #getGrantSubject()} in order to determine the user to authenticate.
     * </p>
     *
     * <p>
     * The user your system will authenticate during the authorization process
     * (or has already authenticated) may be different from the user of the
     * grant. The first implementer's draft of "Grant Management for OAuth 2.0"
     * does not mention anything about the case, so the behavior in the case is
     * left to implementations. Authlete will not perform the grant management
     * action when the {@code subject} passed to Authlete does not match the
     * user of the grant.
     * </p>
     *
     * @return
     *         The subject of the user who has given the grant.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public String getGrantSubject()
    {
        return grantSubject;
    }


    /**
     * Set the subject of the user who has given the grant which is identified
     * by the {@code grant_id} request parameter.
     *
     * <p>
     * Authlete 2.3 and newer versions support <a href=
     * "https://openid.net/specs/fapi-grant-management.html">Grant Management
     * for OAuth 2.0</a>. An authorization request may contain a {@code grant_id}
     * request parameter which is defined in the specification. If the value of
     * the request parameter is valid, {@link #getGrantSubject()} will return
     * the subject of the user who has given the grant to the client application.
     * Authorization server implementations may use the value returned from
     * {@link #getGrantSubject()} in order to determine the user to authenticate.
     * </p>
     *
     * <p>
     * The user your system will authenticate during the authorization process
     * (or has already authenticated) may be different from the user of the
     * grant. The first implementer's draft of "Grant Management for OAuth 2.0"
     * does not mention anything about the case, so the behavior in the case is
     * left to implementations. Authlete will not perform the grant management
     * action when the {@code subject} passed to Authlete does not match the
     * user of the grant.
     * </p>
     *
     * @param subject
     *         The subject of the user who has given the grant.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public void setGrantSubject(String subject)
    {
        this.grantSubject = subject;
    }


    /**
     * Get the content of the grant which is identified by the {@code grant_id}
     * request parameter.
     *
     * <p>
     * The user your system will authenticate during the authorization process
     * (or has already authenticated) may be different from the user of the grant.
     * Be careful when your system displays the content of the grant.
     * </p>
     *
     * @return
     *         The content of the grant.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public Grant getGrant()
    {
        return grant;
    }


    /**
     * Set the content of the grant which is identified by the {@code grant_id}
     * request parameter.
     *
     * <p>
     * The user your system will authenticate during the authorization process
     * (or has already authenticated) may be different from the user of the grant.
     * Be careful when your system displays the content of the grant.
     * </p>
     *
     * @param grant
     *         The content of the grant.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public void setGrant(Grant grant)
    {
        this.grant = grant;
    }


    /**
     * Get the response content which can be used to generate a response
     * to the client application. The format of the value varies depending
     * on the value of {@code "action"}.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used to generate a response
     * to the client application.
     */
    public void setResponseContent(String content)
    {
        this.responseContent = content;
    }


    /**
     * Get the ticket which has been issued to the service implementation
     * from Authlete's {@code /auth/authorization} API. This ticket is
     * needed for {@code /auth/authorization/issue} API and
     * {@code /auth/authorization/fail} API.
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the ticket for the service implementation to call
     * {@code /auth/authorization/issue} API and
     * {@code /auth/authorization/fail} API.
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
                ticket,
                action,
                (client != null ? client.getServiceNumber() : 0),
                (client != null ? client.getNumber() : 0),
                (client != null ? client.getClientId() : 0),
                (client != null ? client.getClientSecret() : null),
                (client != null ? client.getClientType() : null),
                (client != null ? client.getDeveloper() : null),
                display,
                maxAge,
                Utils.stringifyScopeNames(scopes),
                Utils.join(uiLocales, " "),
                Utils.join(claimsLocales, " "),
                Utils.join(claims, " "),
                acrEssential,
                clientIdAliasUsed,
                Utils.join(acrs, " "),
                subject,
                loginHint,
                lowestPrompt,
                Utils.stringifyPrompts(prompts)
                );
    }
}
