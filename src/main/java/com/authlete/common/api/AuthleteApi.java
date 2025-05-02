/*
 * Copyright (C) 2014-2025 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.api;


import java.util.Map;
import com.authlete.common.dto.*;
import com.authlete.common.types.TokenStatus;


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
    default AuthorizationResponse authorization(AuthorizationRequest request) throws AuthleteApiException
    {
        return authorization(request, null);
    }


    /**
     * Call Authlete's {@code /auth/authorization} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    AuthorizationResponse authorization(AuthorizationRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/authorization/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default AuthorizationFailResponse authorizationFail(AuthorizationFailRequest request) throws AuthleteApiException
    {
        return authorizationFail(request, null);
    }


    /**
     * Call Authlete's {@code /auth/authorization/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    AuthorizationFailResponse authorizationFail(AuthorizationFailRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/authorization/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default AuthorizationIssueResponse authorizationIssue(AuthorizationIssueRequest request) throws AuthleteApiException
    {
        return authorizationIssue(request, null);
    }


    /**
     * Call Authlete's {@code /auth/authorization/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    AuthorizationIssueResponse authorizationIssue(AuthorizationIssueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default TokenResponse token(TokenRequest request) throws AuthleteApiException
    {
        return token(request, null);
    }


    /**
     * Call Authlete's {@code /auth/token} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    TokenResponse token(TokenRequest request, Options options) throws AuthleteApiException;


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
    default TokenCreateResponse tokenCreate(TokenCreateRequest request) throws AuthleteApiException
    {
        return tokenCreate(request, null);
    }


    /**
     * Call Authlete's {@code /auth/token/create} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    TokenCreateResponse tokenCreate(TokenCreateRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/delete} API.
     *
     * @param token
     *         An access token or its hash value.
     *
     * @since 2.81
     */
    default void tokenDelete(String token) throws AuthleteApiException
    {
        tokenDelete(token, null);
    }


    /**
     * Call Authlete's {@code /auth/token/delete} API.
     *
     * @param token
     *         An access token or its hash value.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    void tokenDelete(String token, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default TokenFailResponse tokenFail(TokenFailRequest request) throws AuthleteApiException
    {
        return tokenFail(request, null);
    }


    /**
     * Call Authlete's {@code /auth/token/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    TokenFailResponse tokenFail(TokenFailRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default TokenIssueResponse tokenIssue(TokenIssueRequest request) throws AuthleteApiException
    {
        return tokenIssue(request, null);
    }


    /**
     * Call Authlete's {@code /auth/token/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    TokenIssueResponse tokenIssue(TokenIssueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/revoke} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.26
     * @since Authlete 2.2.29
     */
    default TokenRevokeResponse tokenRevoke(TokenRevokeRequest request) throws AuthleteApiException
    {
        return tokenRevoke(request, null);
    }


    /**
     * Call Authlete's {@code /auth/token/revoke} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    TokenRevokeResponse tokenRevoke(TokenRevokeRequest request, Options options) throws AuthleteApiException;


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
    default TokenUpdateResponse tokenUpdate(TokenUpdateRequest request) throws AuthleteApiException
    {
        return tokenUpdate(request, null);
    }


    /**
     * Call Authlete's {@code /auth/token/update} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    TokenUpdateResponse tokenUpdate(TokenUpdateRequest request, Options options) throws AuthleteApiException;


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API).
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @return
     *         The list of access tokens.
     *
     * @since 2.29
     */
    default TokenListResponse getTokenList() throws AuthleteApiException
    {
        return getTokenList((Options)null);
    }


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API).
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(Options options) throws AuthleteApiException;


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API).
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 3.97
     */
    default TokenListResponse getTokenList(TokenStatus tokenStatus) throws AuthleteApiException
    {
        return getTokenList(tokenStatus, null);
    }


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API).
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(TokenStatus tokenStatus, Options options) throws AuthleteApiException;


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with
     * {@code clientIdentifier} and {@code subject}).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(String, String, int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 2.29
     */
    default TokenListResponse getTokenList(
            String clientIdentifier, String subject) throws AuthleteApiException
    {
        return getTokenList(clientIdentifier, subject, (Options)null);
    }


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with
     * {@code clientIdentifier} and {@code subject}).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(String, String, int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(
            String clientIdentifier, String subject, Options options) throws AuthleteApiException;


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with
     * {@code clientIdentifier} and {@code subject}).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(String, String, int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
     *
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 3.97
     */
    default TokenListResponse getTokenList(
            String clientIdentifier, String subject, TokenStatus tokenStatus)
                    throws AuthleteApiException
    {
        return getTokenList(clientIdentifier, subject, tokenStatus, null);
    }


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with
     * {@code clientIdentifier} and {@code subject}).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * This method uses the default range to limit the result set
     * of the query. Use {@link #getTokenList(String, String, int, int, TokenStatus)}
     * to specify the range explicitly.
     * </p>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
     *
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(
            String clientIdentifier, String subject, TokenStatus tokenStatus, Options options)
                    throws AuthleteApiException;


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API
     * with {@code start} and {@code end} parameters).
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(int, int)
     * getTokenList(0, 0)}.{@link TokenListResponse#getTotalCount()
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
     *         The list of access tokens.
     *
     * @since 2.29
     */
    default TokenListResponse getTokenList(int start, int end) throws AuthleteApiException
    {
        return getTokenList(start, end, (Options)null);
    }


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API
     * with {@code start} and {@code end} parameters).
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(int, int)
     * getTokenList(0, 0)}.{@link TokenListResponse#getTotalCount()
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
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(int start, int end, Options options) throws AuthleteApiException;


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API
     * with {@code start} and {@code end} parameters).
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(int, int, TokenStatus)
     * getTokenList(0, 0, TokenStatus.ALL)}.{@link TokenListResponse#getTotalCount()
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
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 3.97
     */
    default TokenListResponse getTokenList(
            int start, int end, TokenStatus tokenStatus) throws AuthleteApiException
    {
        return getTokenList(start, end, tokenStatus, null);
    }


    /**
     * Get the list of access tokens that are associated with the
     * service
     * (= call Authlete's {@code /auth/token/get/list} API
     * with {@code start} and {@code end} parameters).
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(int, int, TokenStatus)
     * getTokenList(0, 0, TokenStatus.ALL)}.{@link TokenListResponse#getTotalCount()
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
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(
            int start, int end, TokenStatus tokenStatus, Options options) throws AuthleteApiException;


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with {@code clientIdentifier},
     * {@code subject}, {@code start} and {@code end} parameters).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(String, String, int, int)
     * getTokenList(clientIdentifier, subject, 0, 0)}.{@link TokenListResponse#getTotalCount()
     * getTotalCount()};
     * </pre>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
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
     *         The list of access tokens.
     *
     * @since 2.29
     */
    default TokenListResponse getTokenList(String clientIdentifier, String subject,
            int start, int end) throws AuthleteApiException
    {
        return getTokenList(clientIdentifier, subject, start, end, (Options)null);
    }


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with {@code clientIdentifier},
     * {@code subject}, {@code start} and {@code end} parameters).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(String, String, int, int)
     * getTokenList(clientIdentifier, subject, 0, 0)}.{@link TokenListResponse#getTotalCount()
     * getTotalCount()};
     * </pre>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
     *
     * @param start
     *         The start index (inclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param end
     *         The end index (exclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(String clientIdentifier, String subject,
            int start, int end, Options options) throws AuthleteApiException;


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with {@code clientIdentifier},
     * {@code subject}, {@code start} and {@code end} parameters).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(String, String, int, int, TokenStatus)
     * getTokenList(clientIdentifier, subject, 0, 0, TokenStatus.ALL)}.{@link
     * TokenListResponse#getTotalCount() getTotalCount()};
     * </pre>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
     *
     * @param start
     *         The start index (inclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param end
     *         The end index (exclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 3.97
     */
    default TokenListResponse getTokenList(
            String clientIdentifier, String subject, int start, int end, TokenStatus tokenStatus)
                    throws AuthleteApiException
    {
        return getTokenList(clientIdentifier, subject, start, end, tokenStatus, null);
    }


    /**
     * Get the list of access tokens
     * (= call Authlete's {@code /auth/token/get/list} API with {@code clientIdentifier},
     * {@code subject}, {@code start} and {@code end} parameters).
     *
     * <p>
     * When both {@code clientIdentifier} and {@code subject} are
     * {@code null}, the list of access tokens that are associated
     * with the service is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is {@code null} but the {@code subject}
     * is not {@code null}, the list of access tokens that are associated
     * with the subject is returned.
     * </p>
     *
     * <p>
     * When {@code clientIdentifier} is not {@code null} but the
     * {@code subject} is {@code null}, the list of access tokens
     * that are associated with the client application is returned.
     * </p>
     *
     * <p>
     * When neither {@code clientIdentifier} nor {@code subject} is
     * {@code null}, the list of access tokens that are associated
     * with the client application and the subject is returned.
     * </p>
     *
     * <p>
     * The pair of {@code start} and {@code end} parameters denotes
     * the range of the result set of the query. For example, if
     * {@code start} is 5 and {@code end} is 7, the pair makes a
     * range from 5 (inclusive) to 7 (exclusive) and the response
     * will contain (at most) 2 pieces of access token information,
     * i.e., information about the 6th and the 7th access tokens (the
     * index starts from 0).
     * </p>
     *
     * <p>
     * If {@code end - start} is equal to or less than 0, {@link
     * TokenListResponse#getAccessTokens() getAccessTokens()} method
     * of the response returns {@code null}. But even in such a case,
     * {@link TokenListResponse#getTotalCount() getTotalCount()}
     * method returns the total count. In other words, if you want
     * to get just the total count, you can write the code as
     * shown below.
     * </p>
     *
     * <pre>
     * int totalCount = api.{@link #getTokenList(String, String, int, int, TokenStatus)
     * getTokenList(clientIdentifier, subject, 0, 0, TokenStatus.ALL)}.{@link
     * TokenListResponse#getTotalCount() getTotalCount()};
     * </pre>
     *
     * @param clientIdentifier
     *         The identifier of the client (client ID or client ID alias)
     *         associated with the targeted access tokens, or {@code null}.
     *
     * @param subject
     *         The subject of the targeted access tokens, or {@code null}.
     *
     * @param start
     *         The start index (inclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param end
     *         The end index (exclusive) of the result set of the query.
     *         Must not be negative.
     *
     * @param tokenStatus
     *         The status of the targeted access tokens, or {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The list of access tokens.
     *
     * @since 4.15
     */
    TokenListResponse getTokenList(
            String clientIdentifier, String subject, int start, int end, TokenStatus tokenStatus, Options options)
                    throws AuthleteApiException;


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
    default RevocationResponse revocation(RevocationRequest request) throws AuthleteApiException
    {
        return revocation(request, null);
    }


    /**
     * Call Authlete's {@code /auth/revocation} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    RevocationResponse revocation(RevocationRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/userinfo} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default UserInfoResponse userinfo(UserInfoRequest request) throws AuthleteApiException
    {
        return userinfo(request, null);
    }


    /**
     * Call Authlete's {@code /auth/userinfo} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    UserInfoResponse userinfo(UserInfoRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/userinfo/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default UserInfoIssueResponse userinfoIssue(UserInfoIssueRequest request) throws AuthleteApiException
    {
        return userinfoIssue(request, null);
    }


    /**
     * Call Authlete's {@code /auth/userinfo/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    UserInfoIssueResponse userinfoIssue(UserInfoIssueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/introspection} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     */
    default IntrospectionResponse introspection(IntrospectionRequest request) throws AuthleteApiException
    {
        return introspection(request, null);
    }


    /**
     * Call Authlete's {@code /auth/introspection} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    IntrospectionResponse introspection(IntrospectionRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/introspection/standard} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.7
     */
    default StandardIntrospectionResponse standardIntrospection(
            StandardIntrospectionRequest request) throws AuthleteApiException
    {
        return standardIntrospection(request, null);
    }


    /**
     * Call Authlete's {@code /auth/introspection/standard} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    StandardIntrospectionResponse standardIntrospection(
            StandardIntrospectionRequest request, Options options) throws AuthleteApiException;


    /**
     * Create a service (= call Authlete's {@code /service/create} API).
     *
     * @param service
     *         Information about a service to create.
     *
     * @return
     *         Information about a newly created service.
     */
    default Service createService(Service service) throws AuthleteApiException
    {
        return createService(service, null);
    }


    /**
     * Create a service (= call Authlete's {@code /service/create} API).
     *
     * @param service
     *         Information about a service to create.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    Service createService(Service service, Options options) throws AuthleteApiException;


    /**
     * @deprecated Use correctly spelled {@link #createService(Service)}.
     */
    @Deprecated
    Service createServie(Service service) throws AuthleteApiException;


    /**
     * Delete a service (= call Authlete's <code>/service/delete/{apiKey}</code> API).
     *
     * @param apiKey
     *         The API key of the service.
     */
    default void deleteService(long apiKey) throws AuthleteApiException
    {
        deleteService(apiKey, null);
    }


    /**
     * Delete a service (= call Authlete's <code>/service/delete/{apiKey}</code> API).
     *
     * @param apiKey
     *         The API key of the service.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    void deleteService(long apiKey, Options options) throws AuthleteApiException;


    /**
     * Get a service (= call Authlete's <code>/service/get/{apiKey}</code> API).
     *
     * @param apiKey
     *         The API key of the service.
     *
     * @return
     *         Information about the service.
     */
    default Service getService(long apiKey) throws AuthleteApiException
    {
        return getService(apiKey, null);
    }


    /**
     * Get a service (= call Authlete's <code>/service/get/{apiKey}</code> API).
     *
     * @param apiKey
     *         The API key of the service.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Information about the service.
     *
     * @since 4.15
     */
    Service getService(long apiKey, Options options) throws AuthleteApiException;


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
    default ServiceListResponse getServiceList() throws AuthleteApiException
    {
        return getServiceList(null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         The list of services.
     *
     * @since 4.15
     */
    ServiceListResponse getServiceList(Options options) throws AuthleteApiException;


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
    default ServiceListResponse getServiceList(int start, int end) throws AuthleteApiException
    {
        return getServiceList(start, end, null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         The list of services.
     *
     * @since 4.15
     */
    ServiceListResponse getServiceList(int start, int end, Options options) throws AuthleteApiException;


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
     *
     * @since 4.15
     */
    default Service updateService(Service service) throws AuthleteApiException
    {
        return updateService(service, null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         Information about the updated service.
     *
     * @since 4.15
     */
    Service updateService(Service service, Options options) throws AuthleteApiException;


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
    default String getServiceJwks() throws AuthleteApiException
    {
        return getServiceJwks(null);
    }


    /**
     * Get the JWK Set of a service.
     *
     * <p>
     * This method is an alias of {@link #getServiceJwks(boolean, boolean)
     * getServiceJwks}{@code (true, false)}.
     * </p>
     *
     * @param options
     *         Request options.
     *
     * @return
     *         JSON representation of the JWK Set of the service.
     *         {@code null} is returned when the service has registered
     *         neither content or URI of its JWK Set.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7517">RFC 7517: JSON Web Key (JWK)</a>
     *
     * @since 4.15
     */
    String getServiceJwks(Options options) throws AuthleteApiException;


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
    default String getServiceJwks(boolean pretty, boolean includePrivateKeys) throws AuthleteApiException
    {
        return getServiceJwks(pretty, includePrivateKeys, null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         JSON representation of the JWK Set of the service.
     *         {@code null} is returned when the service has registered
     *         neither content or URI of its JWK Set.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7517">RFC 7517: JSON Web Key (JWK)</a>
     *
     * @since 4.15
     */
    String getServiceJwks(boolean pretty, boolean includePrivateKeys, Options options) throws AuthleteApiException;


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * This method is an alias of {@link #getServiceConfiguration(boolean)
     * getServiceConfiguration}{@code (true)}.
     * </p>
     *
     * @return
     *         The configuration of the service in JSON format.
     *
     * @see <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     *      >OpenID Connect Discovery 1.0</a>
     *
     * @since 1.27
     */
    default String getServiceConfiguration() throws AuthleteApiException
    {
        return getServiceConfiguration((Options)null);
    }


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * This method is an alias of {@link #getServiceConfiguration(boolean)
     * getServiceConfiguration}{@code (true)}.
     * </p>
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The configuration of the service in JSON format.
     *
     * @see <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     *      >OpenID Connect Discovery 1.0</a>
     *
     * @since 4.15
     */
    String getServiceConfiguration(Options options) throws AuthleteApiException;


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * The value returned from this method can be used as the response body
     * from {@code /.well-known/openid-configuration}. See "<a href=
     * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfig"
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
     * @see <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     *      >OpenID Connect Discovery 1.0</a>
     *
     * @since 1.28
     */
    default String getServiceConfiguration(boolean pretty) throws AuthleteApiException
    {
        return getServiceConfiguration(pretty, null);
    }


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * The value returned from this method can be used as the response body
     * from {@code /.well-known/openid-configuration}. See "<a href=
     * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfig"
     * >4. Obtaining OpenID Provider Configuration Information</a>" in OpenID
     * Connect Discovery 1.0 for details.
     * </p>
     *
     * @param pretty
     *         {@code true} to get the JSON in pretty format.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The configuration of the service in JSON format.
     *
     * @see <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     *      >OpenID Connect Discovery 1.0</a>
     *
     * @since 4.15
     */
    String getServiceConfiguration(boolean pretty, Options options) throws AuthleteApiException;


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * The value returned from this method can be used as the response body
     * from {@code /.well-known/openid-configuration}. See "<a href=
     * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfig"
     * >4. Obtaining OpenID Provider Configuration Information</a>" in OpenID
     * Connect Discovery 1.0 for details.
     * </p>
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         The configuration of the service in JSON format.
     *
     * @since 3.44
     */
    default String getServiceConfiguration(ServiceConfigurationRequest request) throws AuthleteApiException
    {
        return getServiceConfiguration(request, null);
    }


    /**
     * Get the configuration of the service in JSON format that complies with
     * <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
     * >OpenID Connect Discovery 1.0</a>.
     *
     * <p>
     * The value returned from this method can be used as the response body
     * from {@code /.well-known/openid-configuration}. See "<a href=
     * "https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfig"
     * >4. Obtaining OpenID Provider Configuration Information</a>" in OpenID
     * Connect Discovery 1.0 for details.
     * </p>
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         The configuration of the service in JSON format.
     *
     * @since 4.15
     */
    String getServiceConfiguration(ServiceConfigurationRequest request, Options options) throws AuthleteApiException;


    /**
     * Create a client (= call Authlete's {@code /client/create} API).
     *
     * @param client
     *         Information about a client to create.
     *
     * @return
     *         Information about a newly created client.
     */
    default Client createClient(Client client) throws AuthleteApiException
    {
        return createClient(client, null);
    }


    /**
     * Create a client (= call Authlete's {@code /client/create} API).
     *
     * @param client
     *         Information about a client to create.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Information about a newly created client.
     *
     * @since 4.15
     */
    Client createClient(Client client, Options options) throws AuthleteApiException;


    /**
     * Register a client (= call Authlete's {@code /client/registration} API).
     *
     * This method can be used to implement a client registration endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7591">RFC 7591</a>
     * (OAuth 2.0 Dynamic Client Registration Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.41
     */
    default ClientRegistrationResponse dynamicClientRegister(ClientRegistrationRequest request) throws AuthleteApiException
    {
        return dynamicClientRegister(request, null);
    }


    /**
     * Register a client (= call Authlete's {@code /client/registration} API).
     *
     * This method can be used to implement a client registration endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7591">RFC 7591</a>
     * (OAuth 2.0 Dynamic Client Registration Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    ClientRegistrationResponse dynamicClientRegister(ClientRegistrationRequest request, Options options) throws AuthleteApiException;


    /**
     * Get a dynamically registered client (= call Authlete's
     * {@code /client/registration/get} API).
     *
     * This method can be used to implement a client registration management endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7592">RFC 7592</a>
     * (OAuth 2.0 Dynamic Client Registration Management Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.41
     */
    default ClientRegistrationResponse dynamicClientGet(ClientRegistrationRequest request) throws AuthleteApiException
    {
        return dynamicClientGet(request, null);
    }


    /**
     * Get a dynamically registered client (= call Authlete's
     * {@code /client/registration/get} API).
     *
     * This method can be used to implement a client registration management endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7592">RFC 7592</a>
     * (OAuth 2.0 Dynamic Client Registration Management Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    ClientRegistrationResponse dynamicClientGet(ClientRegistrationRequest request, Options options) throws AuthleteApiException;


    /**
     * Update a dynamically registered client (= call Authlete's
     * {@code /client/registration/update} API).
     *
     * This method can be used to implement a client registration management endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7592">RFC 7592</a>
     * (OAuth 2.0 Dynamic Client Registration Management Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.41
     */
    default ClientRegistrationResponse dynamicClientUpdate(ClientRegistrationRequest request) throws AuthleteApiException
    {
        return dynamicClientUpdate(request, null);
    }


    /**
     * Update a dynamically registered client (= call Authlete's
     * {@code /client/registration/update} API).
     *
     * This method can be used to implement a client registration management endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7592">RFC 7592</a>
     * (OAuth 2.0 Dynamic Client Registration Management Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    ClientRegistrationResponse dynamicClientUpdate(ClientRegistrationRequest request, Options options) throws AuthleteApiException;


    /**
     * Delete a dynamically registered client (= call Authlete's
     * {@code /client/registration/delete} API).
     *
     * This method can be used to implement a client registration management endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7592">RFC 7592</a>
     * (OAuth 2.0 Dynamic Client Registration Management Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.41
     */
    default ClientRegistrationResponse dynamicClientDelete(ClientRegistrationRequest request) throws AuthleteApiException
    {
        return dynamicClientDelete(request, null);
    }


    /**
     * Delete a dynamically registered client (= call Authlete's
     * {@code /client/registration/delete} API).
     *
     * This method can be used to implement a client registration management endpoint
     * that complies with <a href="https://tools.ietf.org/html/rfc7592">RFC 7592</a>
     * (OAuth 2.0 Dynamic Client Registration Management Protocol).
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    ClientRegistrationResponse dynamicClientDelete(ClientRegistrationRequest request, Options options) throws AuthleteApiException;


    /**
     * Delete a client (= call Authlete's <code>/client/delete/{clientId}</code> API).
     *
     * @param clientId
     *         Client ID.
     */
    default void deleteClient(long clientId) throws AuthleteApiException
    {
        deleteClient(clientId, null);
    }


    /**
     * Delete a client (= call Authlete's <code>/client/delete/{clientId}</code> API).
     *
     * @param clientId
     *         Client ID.
     *
     * @param options
     *         Request options.
     *
     * @since 4.15
     */
    void deleteClient(long clientId, Options options) throws AuthleteApiException;


    /**
     * Delete a client (= call Authlete's <code>/client/delete/{clientId}</code> API).
     *
     * @param clientId
     *         Client ID.
     *
     * @since 2.51
     */
    default void deleteClient(String clientId) throws AuthleteApiException
    {
        deleteClient(clientId, null);
    }


    /**
     * Delete a client (= call Authlete's <code>/client/delete/{clientId}</code> API).
     *
     * @param clientId
     *         Client ID.
     *
     * @param options
     *         Request options.
     *
     * @since 4.15
     */
    void deleteClient(String clientId, Options options) throws AuthleteApiException;


    /**
     * Get a client (= call Authlete's <code>/client/get/{clientId}</code> API).
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         Information about the client.
     */
    default Client getClient(long clientId) throws AuthleteApiException
    {
        return getClient(clientId, null);
    }


    /**
     * Get a client (= call Authlete's <code>/client/get/{clientId}</code> API).
     *
     * @param clientId
     *         The client ID.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Information about the client.
     *
     * @since 4.15
     */
    Client getClient(long clientId, Options options) throws AuthleteApiException;


    /**
     * Get a client (= call Authlete's <code>/client/get/{clientId}</code> API).
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         Information about the client.
     *
     * @since 2.51
     */
    default Client getClient(String clientId) throws AuthleteApiException
    {
        return getClient(clientId, null);
    }


    /**
     * Get a client (= call Authlete's <code>/client/get/{clientId}</code> API).
     *
     * @param clientId
     *         The client ID.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Information about the client.
     *
     * @since 4.15
     */
    Client getClient(String clientId, Options options) throws AuthleteApiException;


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
    default ClientListResponse getClientList() throws AuthleteApiException
    {
        return getClientList((Options)null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         The list of clients.
     *
     * @since 4.15
     */
    ClientListResponse getClientList(Options options) throws AuthleteApiException;


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
    default ClientListResponse getClientList(String developer) throws AuthleteApiException
    {
        return getClientList(developer, null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         The list of clients.
     *
     * @since 4.15
     */
    ClientListResponse getClientList(String developer, Options options) throws AuthleteApiException;


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
    default ClientListResponse getClientList(int start, int end) throws AuthleteApiException
    {
        return getClientList(start, end, null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         The list of clients.
     *
     * @since 4.15
     */
    ClientListResponse getClientList(int start, int end, Options options) throws AuthleteApiException;


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
    default ClientListResponse getClientList(String developer, int start, int end) throws AuthleteApiException
    {
        return getClientList(developer, start, end, null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         The list of clients.
     *
     * @since 4.15
     */
    ClientListResponse getClientList(String developer, int start, int end, Options options) throws AuthleteApiException;


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
    default Client updateClient(Client client) throws AuthleteApiException
    {
        return updateClient(client, null);
    }


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
     * @param options
     *         Request options.
     *
     * @return
     *         Information about the updated client.
     *
     * @since 4.15
     */
    Client updateClient(Client client, Options options) throws AuthleteApiException;


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
    default String[] getRequestableScopes(long clientId) throws AuthleteApiException
    {
        return getRequestableScopes(clientId, null);
    }


    /**
     * Get the requestable scopes assigned to a client (= call Authlete's
     * <code>/client/extension/requestable_scopes/get/{clientId}</code> API).
     *
     * @param clientId
     *         A client ID.
     *
     * @param options
     *         Request options.
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
     * @since 4.15
     */
    String[] getRequestableScopes(long clientId, Options options) throws AuthleteApiException;


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
    default String[] setRequestableScopes(long clientId, String[] scopes) throws AuthleteApiException
    {
        return setRequestableScopes(clientId, scopes, null);
    }


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
     * @param options
     *         Request options.
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
     * @since 4.15
     */
    String[] setRequestableScopes(long clientId, String[] scopes, Options options) throws AuthleteApiException;


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
    default void deleteRequestableScopes(long clientId) throws AuthleteApiException
    {
        deleteRequestableScopes(clientId, null);
    }


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
     * @param options
     *         Request options.
     *
     * @since 4.15
     */
    void deleteRequestableScopes(long clientId, Options options) throws AuthleteApiException;


    /**
     * Get the set of scopes that a user has granted to a client application
     * (call Authlete's <code>/client/granted_scopes/get/{clientId}</code> API).
     *
     * <p>
     * A dedicated Authlete server provides a functionality to remember the set
     * of scopes that a user has granted to a client application. A remembered
     * set is NOT removed from the database even after all existing access tokens
     * associated with the combination of the client application and the subject
     * have expired. Note that this functionality is not provided by the shared
     * Authlete server.
     * </p>
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
    default GrantedScopesGetResponse getGrantedScopes(long clientId, String subject)
    {
        return getGrantedScopes(clientId, subject, null);
    }


    /**
     * Get the set of scopes that a user has granted to a client application
     * (call Authlete's <code>/client/granted_scopes/get/{clientId}</code> API).
     *
     * <p>
     * A dedicated Authlete server provides a functionality to remember the set
     * of scopes that a user has granted to a client application. A remembered
     * set is NOT removed from the database even after all existing access tokens
     * associated with the combination of the client application and the subject
     * have expired. Note that this functionality is not provided by the shared
     * Authlete server.
     * </p>
     *
     * @param clientId
     *         A client ID.
     *
     * @param subject
     *         A unique user identifier.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Information about scopes granted to a client application by a user.
     *
     * @since 4.15
     */
    GrantedScopesGetResponse getGrantedScopes(long clientId, String subject, Options options);


    /**
     * Delete DB records about the set of scopes that a user has granted to a
     * client application (call Authlete's
     * <code>/client/granted_scopes/delete/{clientId}</code> API).
     *
     * <p>
     * Even if you delete records about granted scopes by calling this API,
     * existing access tokens are not deleted and scopes of existing access
     * tokens are not changed.
     * </p>
     *
     * <p>
     * Please call this method if the user identified by the subject is deleted
     * from your system. Otherwise, garbage data continue to exist in the
     * database.
     * </p>
     *
     * @param clientId
     *         A client ID.
     *
     * @param subject
     *         A unique user identifier.
     *
     * @since 1.40
     */
    default void deleteGrantedScopes(long clientId, String subject)
    {
        deleteGrantedScopes(clientId, subject, null);
    }


    /**
     * Delete DB records about the set of scopes that a user has granted to a
     * client application (call Authlete's
     * <code>/client/granted_scopes/delete/{clientId}</code> API).
     *
     * <p>
     * Even if you delete records about granted scopes by calling this API,
     * existing access tokens are not deleted and scopes of existing access
     * tokens are not changed.
     * </p>
     *
     * <p>
     * Please call this method if the user identified by the subject is deleted
     * from your system. Otherwise, garbage data continue to exist in the
     * database.
     * </p>
     *
     * @param clientId
     *         A client ID.
     *
     * @param subject
     *         A unique user identifier.
     *
     * @param options
     *         Request options.
     *
     * @since 4.15
     */
    void deleteGrantedScopes(long clientId, String subject, Options options);


    /**
     * Delete all existing access tokens issued to the client application
     * by the end-user.
     *
     * @param clientId
     *         The ID of the target client application.
     *
     * @param subject
     *         The subject (= unique identifier) of the end-user.
     *         Must not be {@code null}.
     *
     * @since 2.1
     */
    default void deleteClientAuthorization(long clientId, String subject) throws AuthleteApiException
    {
        deleteClientAuthorization(clientId, subject, null);
    }


    /**
     * Delete all existing access tokens issued to the client application
     * by the end-user.
     *
     * @param clientId
     *         The ID of the target client application.
     *
     * @param subject
     *         The subject (= unique identifier) of the end-user.
     *         Must not be {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @since 4.15
     */
    void deleteClientAuthorization(long clientId, String subject, Options options) throws AuthleteApiException;


    /**
     * Get a list of client applications authorized by the end-user.
     *
     * @param request
     *         Conditions to query the list. The {@code subject} property
     *         (= the unique identifier of the end-user) in the request
     *         must not be {@code null}.
     *
     * @return
     *         A list of client applications that have been authorized
     *         by the end-user.
     *
     * @since 2.1
     */
    default AuthorizedClientListResponse getClientAuthorizationList(ClientAuthorizationGetListRequest request) throws AuthleteApiException
    {
        return getClientAuthorizationList(request, null);
    }


    /**
     * Get a list of client applications authorized by the end-user.
     *
     * @param request
     *         Conditions to query the list. The {@code subject} property
     *         (= the unique identifier of the end-user) in the request
     *         must not be {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         A list of client applications that have been authorized
     *         by the end-user.
     *
     * @since 4.15
     */
    AuthorizedClientListResponse getClientAuthorizationList(ClientAuthorizationGetListRequest request, Options options) throws AuthleteApiException;


    /**
     * Update attributes of all existing access tokens issued to the
     * client application by the end-user.
     *
     * @param clientId
     *         The ID of the target client application.
     *
     * @param request
     *         The subject (= unique identifier) of the end-user and new attribute
     *         values of access tokens. The {@code subject} property in the request
     *         must not be {@code null}.
     *
     * @since 2.1
     */
    default void updateClientAuthorization(long clientId, ClientAuthorizationUpdateRequest request) throws AuthleteApiException
    {
        updateClientAuthorization(clientId, request, null);
    }


    /**
     * Update attributes of all existing access tokens issued to the
     * client application by the end-user.
     *
     * @param clientId
     *         The ID of the target client application.
     *
     * @param request
     *         The subject (= unique identifier) of the end-user and new attribute
     *         values of access tokens. The {@code subject} property in the request
     *         must not be {@code null}.
     *
     * @param options
     *         Request options.
     *
     * @since 4.15
     */
    void updateClientAuthorization(long clientId, ClientAuthorizationUpdateRequest request, Options options) throws AuthleteApiException;


    /**
     * Refresh the client secret of a client. A new value of the
     * client secret will be generated by the Authlete server.
     * If you want to specify a new value, use {@link
     * #updateClientSecret(long, String) updateClientSecret} method.
     *
     * @param clientId
     *         The client ID of a client.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/refresh} API.
     *
     * @since 2.11
     */
    default ClientSecretRefreshResponse refreshClientSecret(long clientId) throws AuthleteApiException
    {
        return refreshClientSecret(clientId, null);
    }


    /**
     * Refresh the client secret of a client. A new value of the
     * client secret will be generated by the Authlete server.
     * If you want to specify a new value, use {@link
     * #updateClientSecret(long, String) updateClientSecret} method.
     *
     * @param clientId
     *         The client ID of a client.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/refresh} API.
     *
     * @since 4.15
     */
    ClientSecretRefreshResponse refreshClientSecret(long clientId, Options options) throws AuthleteApiException;


    /**
     * Refresh the client secret of a client. A new value of the
     * client secret will be generated by the Authlete server.
     * If you want to specify a new value, use {@link
     * #updateClientSecret(String, String) updateClientSecret} method.
     *
     * @param clientIdentifier
     *         The client ID or the client ID alias of a client.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/refresh} API.
     *
     * @since 2.11
     */
    default ClientSecretRefreshResponse refreshClientSecret(String clientIdentifier) throws AuthleteApiException
    {
        return refreshClientSecret(clientIdentifier, null);
    }


    /**
     * Refresh the client secret of a client. A new value of the
     * client secret will be generated by the Authlete server.
     * If you want to specify a new value, use {@link
     * #updateClientSecret(String, String) updateClientSecret} method.
     *
     * @param clientIdentifier
     *         The client ID or the client ID alias of a client.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/refresh} API.
     *
     * @since 4.15
     */
    ClientSecretRefreshResponse refreshClientSecret(String clientIdentifier, Options options) throws AuthleteApiException;


    /**
     * Update the client secret of a client. If you want to
     * have the Authlete server generate a new value of the
     * client secret, use {@link #refreshClientSecret(long)
     * refreshClientSecret} method.
     *
     * <p>
     * Valid characters for a client secret are {@code A-Z},
     * {@code a-z}, {@code 0-9}, {@code -}, and {@code _}.
     * The maximum length of a client secret is 86.
     * </p>
     *
     * @param clientId
     *         The client ID of a client.
     *
     * @param clientSecret
     *         The new value of the client secret.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/update} API.
     *
     * @since 2.11
     */
    default ClientSecretUpdateResponse updateClientSecret(
            long clientId, String clientSecret) throws AuthleteApiException
    {
        return updateClientSecret(clientId, clientSecret, null);
    }


    /**
     * Update the client secret of a client. If you want to
     * have the Authlete server generate a new value of the
     * client secret, use {@link #refreshClientSecret(long)
     * refreshClientSecret} method.
     *
     * <p>
     * Valid characters for a client secret are {@code A-Z},
     * {@code a-z}, {@code 0-9}, {@code -}, and {@code _}.
     * The maximum length of a client secret is 86.
     * </p>
     *
     * @param clientId
     *         The client ID of a client.
     *
     * @param clientSecret
     *         The new value of the client secret.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/update} API.
     *
     * @since 4.15
     */
    ClientSecretUpdateResponse updateClientSecret(
            long clientId, String clientSecret, Options options) throws AuthleteApiException;


    /**
     * Update the client secret of a client. If you want to
     * have the Authlete server generate a new value of the
     * client secret, use {@link #refreshClientSecret(String)
     * refreshClientSecret} method.
     *
     * <p>
     * Valid characters for a client secret are {@code A-Z},
     * {@code a-z}, {@code 0-9}, {@code -}, and {@code _}.
     * The maximum length of a client secret is 86.
     * </p>
     *
     * @param clientIdentifier
     *         The client ID or the client ID alias of a client.
     *
     * @param clientSecret
     *         The new value of the client secret.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/update} API.
     *
     * @since 2.11
     */
    default ClientSecretUpdateResponse updateClientSecret(
            String clientIdentifier, String clientSecret) throws AuthleteApiException
    {
        return updateClientSecret(clientIdentifier, clientSecret, null);
    }


    /**
     * Update the client secret of a client. If you want to
     * have the Authlete server generate a new value of the
     * client secret, use {@link #refreshClientSecret(String)
     * refreshClientSecret} method.
     *
     * <p>
     * Valid characters for a client secret are {@code A-Z},
     * {@code a-z}, {@code 0-9}, {@code -}, and {@code _}.
     * The maximum length of a client secret is 86.
     * </p>
     *
     * @param clientIdentifier
     *         The client ID or the client ID alias of a client.
     *
     * @param clientSecret
     *         The new value of the client secret.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         A response from Authlete's {@code /api/client/secret/update} API.
     *
     * @since 4.15
     */
    ClientSecretUpdateResponse updateClientSecret(
            String clientIdentifier, String clientSecret, Options options) throws AuthleteApiException;


    /**
     * Get the reference to the settings of this {@code AuthleteApi}
     * implementation.
     *
     * @return
     *         The reference to the settings of this {@code AuthleteApi}
     *         implementation.
     *
     * @since 2.9
     */
    Settings getSettings();


    /**
     * Verify a JOSE object.
     *
     * @param request
     *         A request to Authlete's {@code /api/jose/verify} API.
     *
     * @return
     *         A response from Authlete's {@code /api/jose/verify} API.
     *
     * @since 2.23
     */
    default JoseVerifyResponse verifyJose(JoseVerifyRequest request) throws AuthleteApiException
    {
        return verifyJose(request, null);
    }


    /**
     * Verify a JOSE object.
     *
     * @param request
     *         A request to Authlete's {@code /api/jose/verify} API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         A response from Authlete's {@code /api/jose/verify} API.
     *
     * @since 4.15
     */
    JoseVerifyResponse verifyJose(JoseVerifyRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/backchannel/authentication} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.32
     */
    default BackchannelAuthenticationResponse backchannelAuthentication(
            BackchannelAuthenticationRequest request) throws AuthleteApiException
    {
        return backchannelAuthentication(request, null);
    }


    /**
     * Call Authlete's {@code /api/backchannel/authentication} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    BackchannelAuthenticationResponse backchannelAuthentication(
            BackchannelAuthenticationRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/backchannel/authentication/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.32
     */
    default BackchannelAuthenticationIssueResponse backchannelAuthenticationIssue(
            BackchannelAuthenticationIssueRequest request) throws AuthleteApiException
    {
        return backchannelAuthenticationIssue(request, null);
    }


    /**
     * Call Authlete's {@code /api/backchannel/authentication/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    BackchannelAuthenticationIssueResponse backchannelAuthenticationIssue(
            BackchannelAuthenticationIssueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/backchannel/authentication/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.32
     */
    default BackchannelAuthenticationFailResponse backchannelAuthenticationFail(
            BackchannelAuthenticationFailRequest request) throws AuthleteApiException
    {
        return backchannelAuthenticationFail(request, null);
    }


    /**
     * Call Authlete's {@code /api/backchannel/authentication/fail} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    BackchannelAuthenticationFailResponse backchannelAuthenticationFail(
            BackchannelAuthenticationFailRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/backchannel/authentication/complete} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.32
     */
    default BackchannelAuthenticationCompleteResponse backchannelAuthenticationComplete(
            BackchannelAuthenticationCompleteRequest request) throws AuthleteApiException
    {
        return backchannelAuthenticationComplete(request, null);
    }


    /**
     * Call Authlete's {@code /api/backchannel/authentication/complete} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    BackchannelAuthenticationCompleteResponse backchannelAuthenticationComplete(
            BackchannelAuthenticationCompleteRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/device/authorization} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.42
     */
    default DeviceAuthorizationResponse deviceAuthorization(
            DeviceAuthorizationRequest request) throws AuthleteApiException
    {
        return deviceAuthorization(request, null);
    }


    /**
     * Call Authlete's {@code /api/device/authorization} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    DeviceAuthorizationResponse deviceAuthorization(
            DeviceAuthorizationRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/device/complete} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.42
     */
    default DeviceCompleteResponse deviceComplete(
            DeviceCompleteRequest request) throws AuthleteApiException
    {
        return deviceComplete(request, null);
    }


    /**
     * Call Authlete's {@code /api/device/complete} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    DeviceCompleteResponse deviceComplete(
            DeviceCompleteRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/device/verification} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.42
     */
    default DeviceVerificationResponse deviceVerification(
            DeviceVerificationRequest request) throws AuthleteApiException
    {
        return deviceVerification(request, null);
    }


    /**
     * Call Authlete's {@code /api/device/verification} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    DeviceVerificationResponse deviceVerification(
            DeviceVerificationRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/pushed_auth_req} API.
     *
     * @param request
     *            Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.51
     */
    default PushedAuthReqResponse pushAuthorizationRequest(
            PushedAuthReqRequest request) throws AuthleteApiException
    {
        return pushAuthorizationRequest(request, null);
    }


    /**
     * Call Authlete's {@code /api/pushed_auth_req} API.
     *
     * @param request
     *            Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    PushedAuthReqResponse pushAuthorizationRequest(
            PushedAuthReqRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/hsk/create} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.97
     */
    default HskResponse hskCreate(HskCreateRequest request) throws AuthleteApiException
    {
        return hskCreate(request, null);
    }


    /**
     * Call Authlete's {@code /api/hsk/create} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    HskResponse hskCreate(HskCreateRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's <code>/api/hsk/delete/{handle}</code> API.
     *
     * @param handle
     *         The handle for the target record.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.97
     */
    default HskResponse hskDelete(String handle) throws AuthleteApiException
    {
        return hskDelete(handle, null);
    }


    /**
     * Call Authlete's <code>/api/hsk/delete/{handle}</code> API.
     *
     * @param handle
     *         The handle for the target record.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    HskResponse hskDelete(String handle, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's <code>/api/hsk/get/{handle}</code> API.
     *
     * @param handle
     *         The handle for the target record.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.97
     */
    default HskResponse hskGet(String handle) throws AuthleteApiException
    {
        return hskGet(handle, null);
    }


    /**
     * Call Authlete's <code>/api/hsk/get/{handle}</code> API.
     *
     * @param handle
     *         The handle for the target record.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    HskResponse hskGet(String handle, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/hsk/get/list} API.
     *
     * @return
     *         Response from the API.
     *
     * @since 2.97
     */
    default HskListResponse hskGetList() throws AuthleteApiException
    {
        return hskGetList(null);
    }


    /**
     * Call Authlete's {@code /api/hsk/get/list} API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    HskListResponse hskGetList(Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/misc/echo} API.
     *
     * <p>
     * The API returns a JSON string which contains given request parameters.
     * </p>
     *
     * @param parameters
     *         Arbitrary parameters.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.0
     */
    default Map<String, String> echo(Map<String, String> parameters) throws AuthleteApiException
    {
        return echo(parameters, null);
    }


    /**
     * Call Authlete's {@code /api/misc/echo} API.
     *
     * <p>
     * The API returns a JSON string which contains given request parameters.
     * </p>
     *
     * @param parameters
     *         Arbitrary parameters.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     */
    Map<String, String> echo(Map<String, String> parameters, Options option) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /api/gm} API.
     *
     * <p>
     * The API is for the implementation of the grant management endpoint which is
     * defined in "<a href="https://openid.net/specs/fapi-grant-management.html"
     * >Grant Management for OAuth 2.0</a>".
     * </p>
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    default GMResponse gm(GMRequest request) throws AuthleteApiException
    {
        return gm(request, null);
    }


    /**
     * Call Authlete's {@code /api/gm} API.
     *
     * <p>
     * The API is for the implementation of the grant management endpoint which is
     * defined in "<a href="https://openid.net/specs/fapi-grant-management.html"
     * >Grant Management for OAuth 2.0</a>".
     * </p>
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 4.15
     */
    GMResponse gm(GMRequest request, Options options) throws AuthleteApiException;


    /**
     * Update the lock flag of a client application.
     *
     * @param clientIdentifier
     *         The client ID or the client ID alias of a client application.
     *
     * @param clientLocked
     *         The value to which this request updates the lock flag of a client
     *         application.
     *
     * @since 3.10
     */
    default void updateClientLockFlag(
            String clientIdentifier, boolean clientLocked) throws AuthleteApiException
    {
        updateClientLockFlag(clientIdentifier, clientLocked, null);
    }


    /**
     * Update the lock flag of a client application.
     *
     * @param clientIdentifier
     *         The client ID or the client ID alias of a client application.
     *
     * @param clientLocked
     *         The value to which this request updates the lock flag of a client
     *         application.
     *
     * @param options
     *         Request options.
     *
     * @since 4.15
     */
    void updateClientLockFlag(
            String clientIdentifier, boolean clientLocked, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /federation/configuration} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.31
     * @since Authlete 2.3
     */
    default FederationConfigurationResponse federationConfiguration(
            FederationConfigurationRequest request) throws AuthleteApiException
    {
        return federationConfiguration(request, null);
    }


    /**
     * Call Authlete's {@code /federation/configuration} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 2.3
     */
    FederationConfigurationResponse federationConfiguration(
            FederationConfigurationRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /federation/registration} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.45
     * @since Authlete 2.3
     */
    default FederationRegistrationResponse federationRegistration(
            FederationRegistrationRequest request) throws AuthleteApiException
    {
        return federationRegistration(request, null);
    }


    /**
     * Call Authlete's {@code /federation/registration} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 2.3
     */
    FederationRegistrationResponse federationRegistration(
            FederationRegistrationRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/metadata} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.55
     * @since Authlete 3.0
     */
    default CredentialIssuerMetadataResponse credentialIssuerMetadata(
            CredentialIssuerMetadataRequest request) throws AuthleteApiException
    {
        return credentialIssuerMetadata(request, null);
    }


    /**
     * Call Authlete's {@code /vci/metadata} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialIssuerMetadataResponse credentialIssuerMetadata(
            CredentialIssuerMetadataRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/jwtissuer} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.79
     * @since Authlete 3.0
     */
    default CredentialJwtIssuerMetadataResponse credentialJwtIssuerMetadata(
            CredentialJwtIssuerMetadataRequest request) throws AuthleteApiException
    {
        return credentialJwtIssuerMetadata(request, null);
    }


    /**
     * Call Authlete's {@code /vci/jwtissuer} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialJwtIssuerMetadataResponse credentialJwtIssuerMetadata(
            CredentialJwtIssuerMetadataRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/jwks} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.72
     * @since Authlete 3.0
     */
    default CredentialIssuerJwksResponse credentialIssuerJwks(
            CredentialIssuerJwksRequest request) throws AuthleteApiException
    {
        return credentialIssuerJwks(request, null);
    }


    /**
     * Call Authlete's {@code /vci/jwks} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialIssuerJwksResponse credentialIssuerJwks(
            CredentialIssuerJwksRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/offer/create} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.59
     * @since Authlete 3.0
     */
    default CredentialOfferCreateResponse credentialOfferCreate(
            CredentialOfferCreateRequest request) throws AuthleteApiException
    {
        return credentialOfferCreate(request, null);
    }


    /**
     * Call Authlete's {@code /vci/offer/create} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialOfferCreateResponse credentialOfferCreate(
            CredentialOfferCreateRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/offer/info} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.59
     * @since Authlete 3.0
     */
    default CredentialOfferInfoResponse credentialOfferInfo(
            CredentialOfferInfoRequest request) throws AuthleteApiException
    {
        return credentialOfferInfo(request, null);
    }


    /**
     * Call Authlete's {@code /vci/offer/info} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialOfferInfoResponse credentialOfferInfo(
            CredentialOfferInfoRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/single/parse} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.66
     * @since Authlete 3.0
     */
    default CredentialSingleParseResponse credentialSingleParse(
            CredentialSingleParseRequest request) throws AuthleteApiException
    {
        return credentialSingleParse(request, null);
    }


    /**
     * Call Authlete's {@code /vci/single/parse} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialSingleParseResponse credentialSingleParse(
            CredentialSingleParseRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/single/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.67
     * @since Authlete 3.0
     */
    default CredentialSingleIssueResponse credentialSingleIssue(
            CredentialSingleIssueRequest request) throws AuthleteApiException
    {
        return credentialSingleIssue(request, null);
    }


    /**
     * Call Authlete's {@code /vci/single/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialSingleIssueResponse credentialSingleIssue(
            CredentialSingleIssueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/batch/parse} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.71
     * @since Authlete 3.0
     */
    default CredentialBatchParseResponse credentialBatchParse(
            CredentialBatchParseRequest request) throws AuthleteApiException
    {
        return credentialBatchParse(request, null);
    }


    /**
     * Call Authlete's {@code /vci/batch/parse} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialBatchParseResponse credentialBatchParse(
            CredentialBatchParseRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/batch/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.71
     * @since Authlete 3.0
     */
    default CredentialBatchIssueResponse credentialBatchIssue(
            CredentialBatchIssueRequest request) throws AuthleteApiException
    {
        return credentialBatchIssue(request, null);
    }


    /**
     * Call Authlete's {@code /vci/batch/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialBatchIssueResponse credentialBatchIssue(
            CredentialBatchIssueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/deferred/parse} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.69
     * @since Authlete 3.0
     */
    default CredentialDeferredParseResponse credentialDeferredParse(
            CredentialDeferredParseRequest request) throws AuthleteApiException
    {
        return credentialDeferredParse(request, null);
    }


    /**
     * Call Authlete's {@code /vci/deferred/parse} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialDeferredParseResponse credentialDeferredParse(
            CredentialDeferredParseRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /vci/deferred/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.70
     * @since Authlete 3.0
     */
    default CredentialDeferredIssueResponse credentialDeferredIssue(
            CredentialDeferredIssueRequest request) throws AuthleteApiException
    {
        return credentialDeferredIssue(request, null);
    }


    /**
     * Call Authlete's {@code /vci/deferred/issue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    CredentialDeferredIssueResponse credentialDeferredIssue(
            CredentialDeferredIssueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /idtoken/reissue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.68
     * @since Authlete 2.3.8
     * @since Authlete 3.0
     */
    default IDTokenReissueResponse idTokenReissue(
            IDTokenReissueRequest request) throws AuthleteApiException
    {
        return idTokenReissue(request, null);
    }


    /**
     * Call Authlete's {@code /idtoken/reissue} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 2.3.8
     * @since Authlete 3.0
     */
    IDTokenReissueResponse idTokenReissue(
            IDTokenReissueRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/authorization/ticket/info} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.88
     * @since Authlete 3.0
     */
    default AuthorizationTicketInfoResponse authorizationTicketInfo(
            AuthorizationTicketInfoRequest request) throws AuthleteApiException
    {
        return authorizationTicketInfo(request, null);
    }


    /**
     * Call Authlete's {@code /auth/authorization/ticket/info} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    AuthorizationTicketInfoResponse authorizationTicketInfo(
            AuthorizationTicketInfoRequest request, Options opitons) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/authorization/ticket/update} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.88
     * @since Authlete 3.0
     */
    default AuthorizationTicketUpdateResponse authorizationTicketUpdate(
            AuthorizationTicketUpdateRequest request) throws AuthleteApiException
    {
        return authorizationTicketUpdate(request, null);
    }


    /**
     * Call Authlete's {@code /auth/authorization/ticket/update} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    AuthorizationTicketUpdateResponse authorizationTicketUpdate(
            AuthorizationTicketUpdateRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/create/batch} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param dryRun
     *         Whether or not to dry-run this API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.96
     * @since Authlete 3.0
     */
    default TokenCreateBatchResponse tokenCreateBatch(
            TokenCreateRequest[] request, boolean dryRun) throws AuthleteApiException
    {
        return tokenCreateBatch(request, dryRun, null);
    }


    /**
     * Call Authlete's {@code /auth/token/create/batch} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param dryRun
     *         Whether or not to dry-run this API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    TokenCreateBatchResponse tokenCreateBatch(
            TokenCreateRequest[] request, boolean dryRun, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /auth/token/create/batch/status} API.
     *
     * @param requestId
     *         A request Id issued from {@code /auth/token/create/batch} API.
     *
     * @return
     *         Response from the API.
     *
     * @since 3.96
     * @since Authlete 3.0
     */
    default TokenCreateBatchStatusResponse getTokenCreateBatchStatus(
            String requestId) throws AuthleteApiException
    {
        return getTokenCreateBatchStatus(requestId, null);
    }


    /**
     * Call Authlete's {@code /auth/token/create/batch/status} API.
     *
     * @param requestId
     *         A request Id issued from {@code /auth/token/create/batch} API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.15
     * @since Authlete 3.0
     */
    TokenCreateBatchStatusResponse getTokenCreateBatchStatus(
            String requestId, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /nativesso} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.18
     * @since Authlete 3.0
     */
    NativeSsoResponse nativeSso(
            NativeSsoRequest request, Options options) throws AuthleteApiException;


    /**
     * Call Authlete's {@code /nativesso/logout} API.
     *
     * @param request
     *         Request parameters passed to the API.
     *
     * @param options
     *         Request options.
     *
     * @return
     *         Response from the API.
     *
     * @since 4.20
     * @since Authlete 3.0
     */
    NativeSsoLogoutResponse nativeSsoLogout(
            NativeSsoLogoutRequest request, Options options) throws AuthleteApiException;
}
