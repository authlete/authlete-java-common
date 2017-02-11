/*
 * Copyright (C) 2014-2017 Authlete, Inc.
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
package com.authlete.common.api;


import com.authlete.common.dto.AuthorizationFailRequest;
import com.authlete.common.dto.AuthorizationFailResponse;
import com.authlete.common.dto.AuthorizationIssueRequest;
import com.authlete.common.dto.AuthorizationIssueResponse;
import com.authlete.common.dto.AuthorizationRequest;
import com.authlete.common.dto.AuthorizationResponse;
import com.authlete.common.dto.Client;
import com.authlete.common.dto.ClientListResponse;
import com.authlete.common.dto.GrantedScopesGetResponse;
import com.authlete.common.dto.IntrospectionRequest;
import com.authlete.common.dto.IntrospectionResponse;
import com.authlete.common.dto.RevocationRequest;
import com.authlete.common.dto.RevocationResponse;
import com.authlete.common.dto.Service;
import com.authlete.common.dto.ServiceListResponse;
import com.authlete.common.dto.TokenCreateRequest;
import com.authlete.common.dto.TokenCreateResponse;
import com.authlete.common.dto.TokenFailRequest;
import com.authlete.common.dto.TokenFailResponse;
import com.authlete.common.dto.TokenIssueRequest;
import com.authlete.common.dto.TokenIssueResponse;
import com.authlete.common.dto.TokenRequest;
import com.authlete.common.dto.TokenResponse;
import com.authlete.common.dto.TokenUpdateRequest;
import com.authlete.common.dto.TokenUpdateResponse;
import com.authlete.common.dto.UserInfoIssueRequest;
import com.authlete.common.dto.UserInfoIssueResponse;
import com.authlete.common.dto.UserInfoRequest;
import com.authlete.common.dto.UserInfoResponse;


/**
 * Authlete API.
 *
 * @author Takahiko Kawasaki
 */
public interface AuthleteApi
{
    /**
     * Call Authlete's {@code /auth/authorization} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    AuthorizationResponse authorization(AuthorizationRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/authorization/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    AuthorizationFailResponse authorizationFail(AuthorizationFailRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/authorization/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    AuthorizationIssueResponse authorizationIssue(AuthorizationIssueRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    TokenResponse token(TokenRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/create} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 1.13
     */
    TokenCreateResponse tokenCreate(TokenCreateRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    TokenFailResponse tokenFail(TokenFailRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    TokenIssueResponse tokenIssue(TokenIssueRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/update} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 1.34
     */
    TokenUpdateResponse tokenUpdate(TokenUpdateRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/revocation} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 1.16
     */
    RevocationResponse revocation(RevocationRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/userinfo} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    UserInfoResponse userinfo(UserInfoRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/userinfo/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    UserInfoIssueResponse userinfoIssue(UserInfoIssueRequest request) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/introspection} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    IntrospectionResponse introspection(IntrospectionRequest request) throws AuthleteApiException;


    /**
     * Create a service (= call Authlete's {@code /service/create} API).
     *
     * @param service
     *         Information about a service to create.
     *
     * @return
     *         Information about a newly created service.
     */
    Service createServie(Service service) throws AuthleteApiException;


    /**
     * Delete a service (= call Authlete's <code>/service/delete/{apiKey}</code> API).
     *
     * @param apiKey
     *         The API key of the service.
     */
    void deleteService(long apiKey) throws AuthleteApiException;


    /**
     * Get a service (= call Authlete's <code>/service/get/{apiKey}</code> API).
     *
     * @param apiKey
     *         The API key of the service.
     *
     * @return
     *         Information about the service.
     */
    Service getService(long apiKey) throws AuthleteApiException;


    /**
     * Get the list of services that belong to the service owner
     * (= call Authlete's {@code /service/get/list} API).
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getServiceList(int, int)} to specify
     * the range explicitly.
     * </p>
     *
     * @return
     *         The list of services.
     */
    ServiceListResponse getServiceList() throws AuthleteApiException;


    /**
     * Get the list of services that belong to the service owner
     * (= call Authlete's {@code /service/get/list} API with {@code
     * start} and {@code end} parameters).
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of service information, i.e.,
     * information about the 6th and the 7th services (the index
     * starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * ServiceListResponse#getServices() getServices()} method of
     * the response returns {@code null}. But even in such a case,
     * {@link ServiceListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getServiceList(int, int)
     * getServiceList(0, 0)}.{@link ServiceListResponse#getTotalCount()
     * getTotalCount()};
     * </pre>
     *
     * @param start
     *         The start index (inclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param end
     *         The end index (exclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @return
     *         The list of services.
     */
    ServiceListResponse getServiceList(int start, int end) throws AuthleteApiException;


    /**
     * Update a service (= call Authlete's <code>/service/update/{apiKey}</code> API).
     *
     * <p>
     * {@code service.}{@link Service#getApiKey() getApiKey()} must
     * return the correct API key of the service.
     * </p>
     *
     * @param service
     *         Information about a service to update.
     *
     * @return
     *         Information about the updated service.
     */
    Service updateService(Service service) throws AuthleteApiException;


    /**
     * Get the JWK Set of a service.
     *
     * <p>
     * This method is an alias of {@link #getServiceJwks(boolean, boolean)
     * getServiceJwks}{@code (true, false)}.
     * </p>
     *
     * @return
     *         JSON representation of the JWK Set of the service.
     *         {@code null} is returned when the service has registered
     *         neither content or URI of its JWK Set.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7517">RFC 7517: JSON Web Key (JWK)</a>
     *
     * @since 1.13
     */
    String getServiceJwks() throws AuthleteApiException;


    /**
     * Get the JWK Set of a service.
     *
     * <p>
     * You can register either or both (1) the content of a JWK set and
     * (2) the URI of a JWK set. The table below describes how registration
     * combinations affect the response from this method. For example, the
     * table indicates that the content of the JWK Set is returned with a
     * status code 200 if both (content and URI) are registered.
     * </p>
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <thread>
     *     <tr>
     *       <th colspan="2">Service JWK Set</th>
     *       <th colspan="3">Response</th>
     *     </tr>
     *     <tr>
     *       <th>Content</th>
     *       <th>URI</th>
     *       <th>Status Code</th>
     *       <th>Return Value</th>
     *       <th>Exception</th>
     *     </tr>
     *   </thead>
     *   <tbody>
     *     <tr>
     *       <td>Registered</td>
     *       <td>Registered</td>
     *       <td>200 OK</td>
     *       <td>JWK Set</td>
     *       <td>Not Raised</td>
     *     </tr>
     *     <tr>
     *       <td>Registered</td>
     *       <td>Not Registered</td>
     *       <td>200 OK</td>
     *       <td>JWK Set</td>
     *       <td>Not Raised</td>
     *     </tr>
     *     <tr>
     *       <td rowspan="2">Not Registered</td>
     *       <td rowspan="2">Registered</td>
     *       <td>204 No Content</td>
     *       <td>{@code null}</td>
     *       <td>Not Raised</td>
     *     </tr>
     *     <tr>
     *       <td>302 Found <sup>*</sup></td>
     *       <td>&nbsp;</td>
     *       <td>Raised</td>
     *     </tr>
     *     <tr>
     *       <td>Not Registered</td>
     *       <td>Not Registered</td>
     *       <td>204 No Content</td>
     *       <td>{@code null}</td>
     *       <td>Not Raised</td>
     *     </tr>
     *   </tbody>
     * </table>
     * </blockquote>
     *
     * <p>
     * 302 Found is returned when the request URI and the registered JWK Set URI
     * are different. In this case, {@code Location} header contains the registered
     * JWK Set URI. If you need the value of the URI, catch {@link AuthleteApiException}
     * and call {@link AuthleteApiException#getResponseHeaders() getResponseHeaders()}.
     * </p>
     *
     * @param pretty
     *         {@code true} to get the JSON in pretty format.
     *
     * @param includePrivateKeys
     *         {@code true} to keep private keys in the JSON. {@code false} to
     *         remove private keys.
     *
     * @return
     *         JSON representation of the JWK Set of the service.
     *         {@code null} is returned when the service has registered
     *         neither content or URI of its JWK Set.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7517">RFC 7517: JSON Web Key (JWK)</a>
     *
     * @since 1.28
     */
    String getServiceJwks(boolean pretty, boolean includePrivateKeys) throws AuthleteApiException;


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="http://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * This method is an alias of {@link #getService(long)
     * getServiceConfiguration}{@code (true)}.
     * </p>
     *
     * @return
     *         The configuration of the service in JSON format.
     *
     * @see <a href="http://openid.net/specs/openid-connect-discovery-1_0.html"
     *      >OpenID Connect Discovery 1.0</a>
     *
     * @since 1.27
     */
    String getServiceConfiguration() throws AuthleteApiException;


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="http://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * The value returned from this method can be used as the response body
     * from {@code /.well-known/openid-configuration}. See "<a href=
     * "http://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfig"
     * >4. Obtaining OpenID Provider Configuration Information</a>" in OpenID
     * Connect Discovery 1.0 for details.
     * </p>
     *
     * @param pretty
     *         {@code true} to get the JSON in pretty format.
     *
     * @return
     *         The configuration of the service in JSON format.
     *
     * @see <a href="http://openid.net/specs/openid-connect-discovery-1_0.html"
     *      >OpenID Connect Discovery 1.0</a>
     *
     * @since 1.28
     */
    String getServiceConfiguration(boolean pretty) throws AuthleteApiException;


    /**
     * Create a client (= call Authlete's {@code /client/create} API).
     *
     * @param client
     *         Information about a client to create.
     *
     * @return
     *         Information about a newly created client.
     */
    Client createClient(Client client) throws AuthleteApiException;


    /**
     * Delete a client (= call Authlete's <code>/client/delete/{clientId}</code> API).
     *
     * @param clientId
     *         Client ID.
     */
    void deleteClient(long clientId) throws AuthleteApiException;


    /**
     * Get a client (= call Authlete's <code>/client/get/{clientId}</code> API).
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         Information about the client.
     */
    Client getClient(long clientId) throws AuthleteApiException;


    /**
     * Get the list of client applications that belong to the service
     * (= call Authlete's {@code /client/get/list} API).
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getClientList(int, int)} to specify
     * the range explicitly.
     * </p>
     *
     * @return
     *         The list of clients.
     */
    ClientListResponse getClientList() throws AuthleteApiException;


    /**
     * Get the list of client applications that belong to the developer
     * (= call Authlete's {@code /client/get/list} API with {@code
     * developer} parameter).
     *
     * <p>
     * When {@code developer} is {@code null}, the list of client
     * applications that belong to the service is returned.
     * </p>
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getClientList(String, int, int)}
     * to specify the range explicitly.
     * </p>
     *
     * @param developer
     *         The developer of the targeted client applications.
     *
     * @return
     *         The list of clients.
     */
    ClientListResponse getClientList(String developer) throws AuthleteApiException;


    /**
     * Get the list of client applications that belong to the service
     * (= call Authlete's {@code /client/get/list} API with {@code
     * start} and {@code end} parameters).
     *
     * @param start
     *         The start index (inclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param end
     *         The end index (exclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @return
     *         The list of clients.
     */
    ClientListResponse getClientList(int start, int end) throws AuthleteApiException;


    /**
     * Get the list of client applications
     * (= call Authlete's {@code /client/get/list} API with {@code
     * developer}, {@code start} and {@code end} parameters).
     *
     * <p>
     * When {@code developer} is {@code null}, the list of client
     * applications that belong to the service is returned.
     * Otherwise, when {@code developer} is not {@code null}, the
     * list of client applications that belong to the developer is
     * returned.
     * </p>
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of client information, i.e.,
     * information about the 6th and the 7th applications (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * ClientListResponse#getClients() getClients()} method of the
     * response returns {@code null}. But even in such a case,
     * {@link ClientListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getClientList(String, int, int)
     * getClientList(developer, 0, 0)}.{@link ClientListResponse#getTotalCount()
     * getTotalCount()};
     * </pre>
     *
     * @param developer
     *         The developer of the targeted client applications,
     *         or {@code null} to get the list of client applications
     *         of the entire service.
     *
     * @param start
     *         The start index (inclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param end
     *         The end index (exclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @return
     *         The list of client applications.
     */
    ClientListResponse getClientList(String developer, int start, int end) throws AuthleteApiException;


    /**
     * Update a client (= call Authlete's <code>/client/update/{clientId}</code> API).
     *
     * <p>
     * {@code client.}{@link Client#getClientId() getClientId()} must
     * return the correct client ID.
     * </p>
     *
     * @param client
     *         Information about a client to update.
     *
     * @return
     *         Information about the updated client.
     */
    Client updateClient(Client client) throws AuthleteApiException;


    /**
     * Get the requestable scopes assigned to a client (= call Authlete's
     * <code>/client/extension/requestable_scopes/get/{clientId}</code> API).
     *
     * @param clientId
     *         A client ID.
     *
     * @return
     *         <dl>
     *           <dt>null</dt>
     *           <dd>
     *             Requestable scopes are not assigned to the client, meaning
     *             that the client can request any scopes supported by the service.
     *           </dd>
     *           <dt>An empty array</dt>
     *           <dd>
     *             The client cannot request any scopes, meaning that values
     *             included in the {@code scope} request parameter in authorization
     *             requests and token requests are all ignored.
     *           </dd>
     *           <dt>An array of scope names</dt>
     *           <dd>
     *             The array represents the set of scopes that the client is allowed
     *             to request.
     *           </dd>
     *         </dl>
     *
     * @since 1.34
     */
    String[] getRequestableScopes(long clientId) throws AuthleteApiException;


    /**
     * Set the requestable scopes assigned to a client (= call Authlete's
     * <code>/client/extension/requestable_scopes/update/{clientId}</code> API).
     *
     * <p>
     * Calling this method with {@code scopes=null} has the same effect as calling
     * {@link #deleteRequestableScopes(long) deleteRequestableScopes(clientId)}.
     * </p>
     *
     * <p>
     * Since the version 1.39, the {@link Client} class has {@code extension}
     * property and information about <i>"Requestable Scopes per Client"</i>
     * is included in the property. So, calling <code>/client/update/{clientId}</code>
     * API is enough and recommended. In other words, calling
     * <code>/client/extension/requestable_scopes/update/{clientId}</code> API
     * is no longer recommended.
     * </p>
     *
     * <p>
     * Known issue: The JSON parser used by the implementation of
     * <code>/client/extension/requestable_scopes/update/{clientId}</code> API
     * treats an empty array as null and it does not provide any configuration
     * method to change the behavior. Until the JSON parser is replaced, passing
     * an empty array to the API leads to the same result as passing {@code null}
     * to the API.
     * </p>
     *
     * @param clientId
     *         A client ID.
     *
     * @param scopes
     *         <dl>
     *           <dt>null</dt>
     *           <dd>
     *             Requestable scopes assigned to the client are cleared. This
     *             results in that the client can request any scopes supported
     *             by the service.
     *           </dd>
     *           <dt>An empty array</dt>
     *           <dd>
     *             The client cannot request any scopes, meaning that values included
     *             in the {@code scope} request parameter in authorization requests
     *             and token requests are all ignored.
     *           </dd>
     *           <dt>An array of scope names</dt>
     *           <dd>
     *             The given array is used as the set of scopes that the client is
     *             allowed to request.
     *           </dd>
     *         </dl>
     *
     * @return
     *         The resultant set of requestable scopes. The value may be different
     *         from the one given to this method.
     *
     * @since 1.34
     */
    String[] setRequestableScopes(long clientId, String[] scopes) throws AuthleteApiException;


    /**
     * Clear the requestable scopes assigned to a client (= call Authlete's
     * <code>/client/extension/requestable_scopes/delete/{clientId}</code> API).
     *
     * <p>
     * Calling this method has the same effect as calling {@link #setRequestableScopes(long, String[])
     * setRequestableScopes(clientId, null)}.
     * </p>
     *
     * @param clientId
     *         A client ID.
     *
     * @since 1.34
     */
    void deleteRequestableScopes(long clientId) throws AuthleteApiException;


    /**
     * Get the set of scopes that a user has granted to a client application
     * (call Authlete's <code>/client/granted_scopes/get/{clientId}</code> API).
     *
     * @param clientId
     *         A client ID.
     *
     * @param subject
     *         A unique user identifier.
     *
     * @return
     *         Information about scopes granted to a client application by a user.
     *
     * @since 1.39
     */
    GrantedScopesGetResponse getGrantedScopes(long clientId, String subject);
}
