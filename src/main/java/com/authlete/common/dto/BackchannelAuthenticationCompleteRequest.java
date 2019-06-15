/*
 * Copyright (C) 2018-2019 Authlete, Inc.
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


import java.io.Serializable;
import java.net.URI;
import java.util.Map;
import com.authlete.common.util.Utils;


/**
 * Request to Authlete's {@code /api/backchannel/authentication/complete} API.
 *
 * <p>
 * After the implementation of the backchannel authentication endpoint returns
 * JSON containing an {@code auth_req_id} to the client, the authorization
 * server starts a background process that communicates with the authentication
 * device of the end-user. On the authentication device, end-user
 * authentication is performed and the end-user is asked whether they give
 * authorization to the client or not. The authorization server will receive
 * the result of end-user authentication and authorization from the
 * authentication device.
 * </p>
 *
 * <p>
 * After the authorization server receives the result from the authentication
 * device, or even in the case where the server gave up receiving a response
 * from the authentication device for some reasons, the server should call the
 * {@code /api/backchannel/authentication/complete} API to tell Authlete the
 * result.
 * </p>
 *
 * <p>
 * When the end-user was authenticated and authorization was granted to the
 * client by the end-user, the authorization server should call the API with
 * {@code result=}{@link Result#AUTHORIZED AUTHORIZED}. In this successful
 * case, the {@code subject} request parameter is mandatory. If the token
 * delivery mode is "push", the API will generate an access token, an ID token
 * and optionally a refresh token. On the other hand, if the token delivery
 * mode is "poll" or "ping", the API will just update the database record so
 * that {@code /api/auth/token} API can generate tokens later.
 * </p>
 *
 * <p>
 * When the authorization server received the decision of the end-user from
 * the authentication device and it indicates that the end-user has rejected
 * to give authorization to the client, the authorization server should call
 * the API with {@code result=}{@link Result#ACCESS_DENIED ACCESS_DENIED}.
 * In this case, if the token delivery mode is "push", the API will generate
 * an error response that contains the {@code error} response parameter and
 * optionally the {@code error_description} and {@code error_uri} response
 * parameters (if the {@code errorDescription} and {@code errorUri} request
 * parameters have been given). On the other hand, if the token delivery mode
 * is "poll" or "ping", the API will just update the database record so that
 * {@code /api/auth/token} API can generate an error response later. In any
 * token delivery mode, the value of the {@code error} parameter will become
 * {@code access_denied}.
 * </p>
 *
 * <p>
 * When the authorization server could not get the result of end-user
 * authentication and authorization from the authentication device for some
 * reasons, the authorization server should call the API with
 * {@code result=}{@link Result#TRANSACTION_FAILED TRANSACTION_FAILED}. In
 * this error case, the API will behave in the same way as in the case of
 * {@code ACCESS_DENIED}. The only difference is that {@code expired_token}
 * is used as the value of the {@code error} parameter.
 * </p>
 *
 * @since 2.32
 */
public class BackchannelAuthenticationCompleteRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * Types of results of end-user authentication and authorization.
     */
    public enum Result
    {
        /**
         * The end-user was authenticated and has granted authorization to
         * the client application.
         */
        AUTHORIZED((short)1),


        /**
         * The end-user denied the backchannel authentication request.
         */
        ACCESS_DENIED((short)2),


        /**
         * The authorization server could not get the result of end-user
         * authentication and authorization from the authentication device
         * for some reasons.
         *
         * <p>
         * For example, the authorization server failed to communicate with
         * the authentication device due to a network error, the device did
         * not return a response within a reasonable time, etc.
         * </p>
         *
         * <p>
         * This result can be used as a generic error.
         * </p>
         *
         * @since 2.36
         */
        TRANSACTION_FAILED((short)3),
        ;


        private static final Result[] sValues = values();
        private final short mValue;


        private Result(short value)
        {
            mValue = value;
        }


        /**
         * Get the integer representation of this enum instance.
         */
        public short getValue()
        {
            return mValue;
        }


        /**
         * Find an instance of this enum by a value.
         *
         * @param value
         *         The integer representation of the instance to find.
         *
         * @return
         *         An instance of this enum, or {@code null} if not found.
         */
        public static Result getByValue(short value)
        {
            if (value < 1 || sValues.length < value)
            {
                // Not found.
                return null;
            }

            return sValues[value - 1];
        }
    }


    /**
     * The ticket issued by Authlete's /api/backchannel/authentication API.
     */
    private String ticket;


    /**
     * The result of the end-user authentication and authorization.
     */
    private Result result;


    /**
     * The subject (= unique identifier) of the end-user.
     */
    private String subject;


    /**
     * The value of the {@code sub} claim for the ID token. When this field
     * is empty, {@code subject} is used as the value of the {@code sub}
     * claim.
     */
    private String sub;


    /**
     * The time at which the end-user was authenticated.
     */
    private long authTime;


    /**
     * The authentication context class reference.
     */
    private String acr;


    /**
     * Additional claims in JSON format.
     */
    private String claims;


    /**
     * Extra properties associated with the access token.
     */
    private Property[] properties;


    /**
     * Scopes associated with the access token. If this field is {@code null},
     * the scopes specified in the original backchannel authentication request
     * are used. In other cases, the scopes here will replace the original
     * scopes contained in the original request.
     */
    private String[] scopes;


    /**
     * The description of the error. This property is referred to when the
     * result is not AUTHORIZED.
     */
    private String errorDescription;


    /**
     * The URI of a document which describes the error in detail. This property
     * is referred to when the result is not AUTHORIZED.
     */
    private URI errorUri;


    /**
     * Get the ticket which is necessary to call Authlete's
     * {@code /api/backchannel/authentication/complete} API.
     *
     * @return
     *         The ticket.
     */
    public String getTicket()
    {
        return ticket;
    }


    /**
     * Set the ticket which is necessary to call Authlete's
     * {@code /api/backchannel/authentication/complete} API.
     * This request parameter is mandatory.
     *
     * @param ticket
     *         The ticket previously issued by Authlete's
     *         {@code /api/backchannel/authentication} API.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setTicket(String ticket)
    {
        this.ticket = ticket;

        return this;
    }


    /**
     * Get the result of end-user authentication and authorization.
     *
     * @return
     *         The result of end-user authentication and authorization.
     */
    public Result getResult()
    {
        return result;
    }


    /**
     * Set the result of end-user authentication and authorization.
     * This request parameter is mandatory.
     *
     * @param result
     *         The result of end-user authentication and authorization.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setResult(Result result)
    {
        this.result = result;

        return this;
    }


    /**
     * Get the subject (= unique identifier) of the end-user who has granted
     * authorization to the client application.
     *
     * <p>
     * This {@code subject} property is used as the value of the subject
     * associated with the access token and as the value of the {@code sub}
     * claim in the ID token.
     * </p>
     *
     * <p>
     * Note that, if {@link #getSub()} returns a non-empty value, it is used
     * as the value of the {@code sub} claim in the ID token. However, even
     * in the case, the value of the subject associated with the access token
     * is still the value of this {@code subject} property.
     * </p>
     *
     * @return
     *         The subject (= unique identifier) of the end-user.
     *
     * @see #getSub()
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the end-user who has granted
     * authorization to the client application. This request parameter is
     * mandatory when {@link #getResult()} returns {@link Result#AUTHORIZED
     * AUTHORIZED}.
     *
     * <p>
     * This {@code subject} property is used as the value of the subject
     * associated with the access token and as the value of the {@code sub}
     * claim in the ID token.
     * </p>
     *
     * <p>
     * Note that, if {@link #getSub()} returns a non-empty value, it is used
     * as the value of the {@code sub} claim in the ID token. However, even
     * in the case, the value of the subject associated with the access token
     * is still the value set by this method.
     * </p>
     *
     * @param subject
     *         The subject (= unique identifier) of the end-user.
     *
     * @return
     *         {@code this} object.
     *
     * @see #setSub(String)
     */
    public BackchannelAuthenticationCompleteRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the value of the {@code sub} claim that should be used in the ID
     * token. If this method returns {@code null} or its value is empty, the
     * value returned by {@link #getSubject()} is used as the value of the
     * {@code sub} claim. The main purpose of this {@code sub} property is
     * to hide the actual value of the subject from client applications.
     *
     * <p>
     * Note that the value of the {@code subject} request parameter is used
     * as the value of the subject associated with the access token regardless
     * of whether this {@code sub} property is a non-empty value or not. In
     * other words, this {@code sub} property affects only the {@code sub}
     * claim in the ID token.
     * </p>
     *
     * @return
     *         The value of the {@code sub} claim.
     *
     * @see #getSubject()
     */
    public String getSub()
    {
        return sub;
    }


    /**
     * Set the value of the {@code sub} claim that should be used in the ID
     * token. If this method returns {@code null} or its value is empty, the
     * value returned by {@link #getSubject()} is used as the value of the
     * {@code sub} claim. The main purpose of this {@code sub} property is
     * to hide the actual value of the subject from client applications.
     *
     * <p>
     * Note that the value of the {@code subject} request parameter is used
     * as the value of the subject associated with the access token regardless
     * of whether this {@code sub} property is a non-empty value or not. In
     * other words, this {@code sub} property affects only the {@code sub}
     * claim in the ID token.
     * </p>
     *
     * @param sub
     *         The value of the {@code sub} claim.
     *
     * @return
     *         {@code this} object.
     *
     * @see #setSubject(String)
     */
    public BackchannelAuthenticationCompleteRequest setSub(String sub)
    {
        this.sub = sub;

        return this;
    }


    /**
     * Get the time at which the end-user was authenticated.
     *
     * @return
     *         The time at which the end-user was authenticated.
     *         It is the number of seconds since 1970-01-01.
     */
    public long getAuthTime()
    {
        return authTime;
    }


    /**
     * Set the time at which the end-user was authenticated. When this request
     * parameter holds a positive number, the {@code auth_time} claim will be
     * embedded in the ID token.
     *
     * @param authTime
     *         The time at which the end-user was authenticated.
     *         It is the number of seconds since 1970-01-01.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setAuthTime(long authTime)
    {
        this.authTime = authTime;

        return this;
    }


    /**
     * Get the reference of the authentication context class which the
     * end-user authentication satisfied.
     *
     * @return
     *         The authentication context class reference.
     */
    public String getAcr()
    {
        return acr;
    }


    /**
     * Set the reference of the authentication context class which the
     * end-user authentication satisfied. When this request parameter
     * holds a non-null value, the {@code acr} claim will be embedded
     * in the ID token.
     *
     * @param acr
     *         The authentication context class reference.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setAcr(String acr)
    {
        this.acr = acr;

        return this;
    }


    /**
     * Get additional claims which will be embedded in the ID token.
     *
     * @return
     *         Additional claims in JSON format which will be embedded in the
     *         ID token. See the description of {@link #setClaims(String)} for
     *         details about the format.
     *
     * @see #setClaims(String)
     */
    public String getClaims()
    {
        return claims;
    }


    /**
     * Set additional claims which will be embedded in the ID token.
     *
     * <p>
     * The authorization server implementation is required to retrieve values
     * of requested claims of the end-user from its database and format them
     * in JSON format.
     * </p>
     *
     * <p>
     * For example, if <code>"given_name"</code> claim,
     * <code>"family_name"</code> claim and <code>"email"</code> claim are
     * requested, the authorization server implementation should generate
     * a JSON object like the following:
     * </p>
     *
     * <pre>
     * {
     *   "given_name": "Takahiko",
     *   "family_name": "Kawasaki",
     *   "email": "takahiko.kawasaki@example.com"
     * }
     * </pre>
     *
     * <p>
     * and set its string representation by this method.
     * </p>
     *
     * <p>
     * See OpenID Connect Core 1.0, <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >5.1. Standard Claims</a> for further details about the format.
     * </p>
     *
     * @param claims
     *         Additional claims in JSON format.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     *      >OpenID Connect Core 1.0, 5.1. Standard Claims</a>
     */
    public BackchannelAuthenticationCompleteRequest setClaims(String claims)
    {
        this.claims = claims;

        return this;
    }


    /**
     * Set additional claims which will be embedded in the ID token.
     *
     * <p>
     * The argument is converted into a JSON string and passed to
     * {@link #setClaims(String)} method.
     * </p>
     *
     * @param claims
     *         Additional claims. Keys are claim names.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setClaims(Map<String, Object> claims)
    {
        if (claims == null || claims.size() == 0)
        {
            this.claims = null;
        }
        else
        {
            setClaims(Utils.toJson(claims));
        }

        return this;
    }


    /**
     * Get the extra properties associated with the access token.
     *
     * @return
     *         Extra properties.
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set extra properties associated with the access token.
     *
     * <p>
     * Keys of extra properties will be used as labels of top-level entries
     * in a JSON response returned from the authorization server. An example
     * is {@code example_parameter}, which you can find in <a href=
     * "https://tools.ietf.org/html/rfc6749#section-5.1">5.1. Successful
     * Response</a> in RFC 6749. The following code snippet is an example
     * to set one extra property having {@code example_parameter} as its
     * key and {@code example_value} as its value.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {@link Property}[] properties = { new {@link Property#Property(String, String)
     * Property}("example_parameter", "example_value") };
     * request.{@link #setProperties(Property[]) setProperties}(properties);
     * </pre>
     * </blockquote>
     *
     * <p>
     * Note that <b>there is an upper limit on the total size of extra
     * properties</b>. On Authlete side, the properties will be (1) converted
     * to a multidimensional string array, (2) converted to JSON, (3) encrypted
     * by AES/CBC/PKCS5Padding, (4) encoded by base64url, and then stored into
     * the database. The length of the resultant string must not exceed 65,535
     * in bytes. This is the upper limit, but we think it is big enough.
     * </p>
     *
     * @param properties
     *         Extra properties.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get scopes associated with the access token. If this method returns a
     * non-null value, the set of scopes will be used instead of the scopes
     * specified in the original backchannel authentication request.
     *
     * @return
     *         Scopes to replace the scopes specified in the original
     *         backchannel authentication request with. When {@code null} is
     *         returned from this method, replacement is not performed.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set scopes associated with the access token. If {@code null} (the
     * default value) is set, the scopes specified in the original backchannel
     * authentication request are used. In other cases, the scopes given to
     * this method will replace the original scopes contained in the original
     * request.
     * </p>
     *
     * <p>
     * Even scopes that are not included in the original request can be
     * included.
     * </p>
     *
     * <p>
     * Note that because the CIBA specification requires {@code "openid"} as a
     * mandatory scope, {@code "openid"} should be always included.
     * </p>
     *
     * @param scopes
     *         Scopes associated with the access token. If a non-null value is
     *         set, the original scopes requested by the client application are
     *         replaced.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the description of the error. This corresponds to the
     * {@code error_description} property in the response to the client.
     *
     * @return
     *         The description of the error.
     */
    public String getErrorDescription()
    {
        return errorDescription;
    }


    /**
     * Set the description of the error. This corresponds to the
     * {@code error_description} property in the response to the client.
     *
     * <p>
     * If this optional request parameter is given, its value is used as the
     * value of the {@code error_description} property, but it is used only
     * when the result is not {@link Result#AUTHORIZED AUTHORIZED}.
     * </p>
     *
     * <p>
     * To comply with the specification strictly, the description must not
     * include characters outside the set %x20-21 / %x23-5B / %x5D-7E.
     * </p>
     *
     * @param description
     *         The description of the error.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setErrorDescription(String description)
    {
        this.errorDescription = description;

        return this;
    }


    /**
     * Get the URI of a document which describes the error in detail. This
     * corresponds to the {@code error_uri} property in the response to the
     * client.
     *
     * @return
     *         The URI of a document which describes the error in detail.
     */
    public URI getErrorUri()
    {
        return errorUri;
    }


    /**
     * Set the URI of a document which describes the error in detail. This
     * corresponds to the {@code error_uri} property in the response to the
     * client.
     *
     * <p>
     * If this optional request parameter is given, its value is used as the
     * value of the {@code error_uri} property, but it is used only when the
     * result is not {@link Result#AUTHORIZED AUTHORIZED}.
     * </p>
     *
     * @param uri
     *         The URI of a document which describes the error in detail.
     *
     * @return
     *         {@code this} object.
     */
    public BackchannelAuthenticationCompleteRequest setErrorUri(URI uri)
    {
        this.errorUri = uri;

        return this;
    }
}
