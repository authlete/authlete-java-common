/*
 * Copyright (C) 2014-2015 Authlete, Inc.
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
import com.authlete.common.dto.IntrospectionRequest;
import com.authlete.common.dto.IntrospectionResponse;
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
}
