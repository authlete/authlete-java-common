/*
 * Copyright (C) 2019-2023 Authlete, Inc.
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
 * Request to Authlete's {@code /api/device/complete} API.
 *
 * <p>
 * In the <a href="https://www.rfc-editor.org/rfc/rfc8628.html"
 * >device flow</a>, an end-user accesses the verification endpoint of the
 * authorization server where she interacts with the verification endpoint
 * and inputs a user code. The verification endpoint checks if the user code
 * is valid and then asks the end-user whether she approves or rejects the
 * authorization request which the user code represents.
 * </p>
 *
 * <p>
 * After the authorization server receives the decision of the end-user, it
 * should call Authlete's {@code /api/device/complete} API to tell Authlete
 * the decision.
 * </p>
 *
 * <p>
 * When the end-user was authenticated and authorization was granted to the
 * client by the end-user, the authorization server should call the API with
 * {@code result=}{@link Result#AUTHORIZED AUTHORIZED}. In this successful
 * case, the {@code subject} request parameter is mandatory. The API will
 * update the database record so that {@code /api/auth/token} API can generate
 * an access token later.
 * </p>
 *
 * <p>
 * If the {@code scope} parameter of the device authorization request included
 * the {@code openid} scope, an ID token is generated. In this case, {@code sub},
 * {@code authTime}, {@code acr} and {@code claims} request parameters in the
 * API call to {@code /api/device/complete} affect the ID token.
 * </p>
 *
 * <p>
 * When the authorization server receives the decision of the end-user and it
 * indicates that she has rejected to give authorization to the client, the
 * authorization server should call the API with
 * {@code result=}{@link Result#ACCESS_DENIED ACCESS_DENIED}. In this case,
 * the API will update the database record so that the {@code /api/auth/token}
 * API can generate an error response later. If {@code errorDescription} and
 * {@code errorUri} request parameters are given to the
 * {@code /api/device/complete} API, they will be used as the values of
 * {@code error_description} and {@code error_uri} response parameters in the
 * error response from the token endpoint.
 * </p>
 *
 * <p>
 * When the authorization server could not get decision from the end-user for
 * some reasons, the authorization server should call the API with
 * {@code result=}{@link Result#TRANSACTION_FAILED TRANSACTION_FAILED}. In
 * this error case, the API will behave in the same way as in the case of
 * {@code ACCESS_DENIED}. The only difference is that {@code expired_token}
 * is used as the value of the {@code error} response parameter instead of
 * {@code access_denied}.
 * </p>
 *
 * @since 2.42
 */
public class DeviceCompleteRequest implements Serializable
{
    private static final long serialVersionUID = 6L;


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
         * The end-user denied the device authorization request.
         */
        ACCESS_DENIED((short)2),


        /**
         * The authorization server could not get decision from the end-user
         * for some reasons.
         *
         * <p>
         * This result can be used as a generic error.
         * </p>
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
     * The user code input by the end-user.
     */
    private String userCode;


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
     *
     * @since 2.44
     */
    private String sub;


    /**
     * The time at which the end-user was authenticated.
     *
     * @since 2.44
     */
    private long authTime;


    /**
     * The authentication context class reference.
     *
     * @since 2.44
     */
    private String acr;


    /**
     * Additional claims in JSON format.
     *
     * @since 2.44
     */
    private String claims;


    /**
     * Extra properties associated with the access token.
     */
    private Property[] properties;


    /**
     * Scopes associated with the access token. If this field is {@code null},
     * the scopes specified in the original device authorization request are
     * used. In other cases, the scopes here will replace the original scopes
     * contained in the original request.
     */
    private String[] scopes;


    /**
     * JSON that represents additional JWS header parameters for the ID token
     * which may be issued from the token endpoint.
     *
     * @since 2.79
     */
    private String idtHeaderParams;


    /**
     * Claims that the user has consented for the client application to know.
     *
     * @since 3.7
     */
    private String[] consentedClaims;


    /**
     * Additional claims that are added to the payload part of the JWT
     * access token.
     *
     * @since 3.23
     */
    private String jwtAtClaims;


    /**
     * The type of the {@code aud} claim in the ID token being issued.
     *
     * @since 3.57
     * @since Authlete 2.3.3
     */
    private String idTokenAudType;


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
     * Get the user code input by the end-user.
     *
     * @return
     *         The user code.
     */
    public String getUserCode()
    {
        return userCode;
    }


    /**
     * Set the user code input by the end-user.
     * This request parameter is mandatory.
     *
     * @param userCode
     *         The user code.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceCompleteRequest setUserCode(String userCode)
    {
        this.userCode = userCode;

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
    public DeviceCompleteRequest setResult(Result result)
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
    public DeviceCompleteRequest setSubject(String subject)
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
     *
     * @since 2.44
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
     *
     * @since 2.44
     */
    public DeviceCompleteRequest setSub(String sub)
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
     *
     * @since 2.44
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
     *
     * @since 2.44
     */
    public DeviceCompleteRequest setAuthTime(long authTime)
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
     *
     * @since 2.44
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
     *
     * @since 2.44
     */
    public DeviceCompleteRequest setAcr(String acr)
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
     *
     * @since 2.44
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
     *
     * @since 2.44
     */
    public DeviceCompleteRequest setClaims(String claims)
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
     *
     * @since 2.44
     */
    public DeviceCompleteRequest setClaims(Map<String, Object> claims)
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
     * Get the extra properties associated with the access token that will be
     * issued.
     *
     * @return
     *         Extra properties.
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set extra properties associated with the access token that will be
     * issued.
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
    public DeviceCompleteRequest setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get scopes associated with the access token. If this method returns a
     * non-null value, the set of scopes will be used instead of the scopes
     * specified in the original device authorization request.
     *
     * @return
     *         Scopes to replace the scopes specified in the original device
     *         authorization request with. When {@code null} is returned from
     *         this method, replacement is not performed.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set scopes associated with the access token. If {@code null} (the
     * default value) is set, the scopes specified in the original device
     * authorization request are used. In other cases, the scopes given to
     * this method will replace the original scopes contained in the original
     * request.
     * </p>
     *
     * <p>
     * Even scopes that are not included in the original request can be
     * included.
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
    public DeviceCompleteRequest setScopes(String[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get JSON that represents additional JWS header parameters for the ID
     * token that may be issued from the token endpoint.
     *
     * @return
     *         JSON that represents additional JWS header parameters for
     *         the ID token.
     *
     * @since 2.79
     */
    public String getIdtHeaderParams()
    {
        return idtHeaderParams;
    }


    /**
     * Set JSON that represents additional JWS header parameters for the ID
     * token that may be issued from the token endpoint.
     *
     * @param params
     *         JSON that represents additional JWS header parameters for
     *         the ID token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.79
     */
    public DeviceCompleteRequest setIdtHeaderParams(String params)
    {
        this.idtHeaderParams = params;

        return this;
    }


    /**
     * Get the claims that the user has consented for the client application
     * to know.
     *
     * <p>
     * See the description of {@link #setConsentedClaims(String[])} for
     * details.
     * </p>
     *
     * @return
     *         Consented claims.
     *
     * @since 3.7
     */
    public String[] getConsentedClaims()
    {
        return consentedClaims;
    }


    /**
     * Set the claims that the user has consented for the client application
     * to know.
     *
     * <p>
     * If the {@code claims} request parameter holds JSON, Authlete extracts
     * claims from the JSON and embeds them in an ID token (cf. {@link
     * #setClaims(String)}). However, the claims are not necessarily identical
     * to the set of claims that the user has actually consented for the client
     * application to know.
     * </p>
     *
     * <p>
     * For example, if the user has allowed the {@code profile} scope to be
     * tied to an access token being issued, it technically means that the
     * user has consented for the client application to know the following
     * claims based on the mapping defined in OpenID Connect Core 1.0 <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     * >Section 5.4. Requesting Claims using Scope Values</a>: {@code name},
     * {@code family_name}, {@code given_name}, {@code middle_name},
     * {@code nickname}, {@code preferred_username}, {@code profile},
     * {@code picture}, {@code website}, {@code gender}, {@code birthdate},
     * {@code zoneinfo}, {@code locale} and {@code updated_at}. However,
     * JSON of the {@code claims} request parameter does not necessarily
     * include all the claims. It may be simply because the authorization
     * server does not support other claims or because the authorization
     * server intends to return requested claims from the <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#UserInfo"
     * >UserInfo Endpoint</a> instead of embedding them in an ID token, or
     * for some other reasons. Therefore, Authlete does not assume that the
     * claims in the JSON of the {@code claims} request parameter represent
     * the complete set of consented claims.
     * </p>
     *
     * <p>
     * This {@code consentedClaims} request parameter (supported from Authlete
     * 2.3) can be used to convey the exact set of consented claims to Authlete.
     * Authlete saves the information into its database and makes them
     * referrable in responses from the {@code /api/auth/introspection} API
     * and the {@code /api/auth/userinfo} API.
     * </p>
     *
     * <p>
     * In addition, the information conveyed via this {@code consentedClaims}
     * request parameter is used to compute the exact value of the {@code
     * claims} parameter in responses from the Grant Management Endpoint, which
     * is defined in <a href="https://openid.net/specs/fapi-grant-management.html"
     * >Grant Management for OAuth 2.0</a>.
     * </p>
     *
     * </p>
     * When this request parameter is missing or its value is empty, Authlete
     * computes the set of consented claims from the consented scopes (e.g.
     * {@code profile}) and the claims in the JSON of the {@code claims}
     * request parameter although Authlete knows the possibility that the
     * computed set may be different from the actual set of consented claims.
     * Especially, the computed set may not include claims that the
     * authorization server returns from the UserInfo Endpoint. Therefore,
     * if you want to control the exact set of consented claims, utilize this
     * request parameter.
     * </p>
     *
     * @param claims
     *         Consented claims.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.7
     */
    public DeviceCompleteRequest setConsentedClaims(String[] claims)
    {
        this.consentedClaims = claims;

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
    public DeviceCompleteRequest setErrorDescription(String description)
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
    public DeviceCompleteRequest setErrorUri(URI uri)
    {
        this.errorUri = uri;

        return this;
    }


    /**
     * Get the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This request parameter has a meaning only when the format of access
     * tokens issued by this service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * @return
     *         Additional claims that are added to the payload part of the JWT
     *         access token.
     *
     * @since 3.23
     */
    public String getJwtAtClaims()
    {
        return jwtAtClaims;
    }


    /**
     * Set the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This request parameter has a meaning only when the format of access
     * tokens issued by this service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * @param claims
     *         Additional claims that are added to the payload part of the JWT
     *         access token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.23
     */
    public DeviceCompleteRequest setJwtAtClaims(String claims)
    {
        this.jwtAtClaims = claims;

        return this;
    }


    /**
     * Get the type of the {@code aud} claim of the ID token being issued.
     * Valid values are as follows.
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>{@code "array"}</td>
     *     <td>The type of the {@code aud} claim is always an array of strings.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "string"}</td>
     *     <td>The type of the {@code aud} claim is always a single string.</td>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td>The type of the {@code aud} claim remains the same as before.</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * <p>
     * This request parameter takes precedence over the {@code idTokenAudType}
     * property of {@link Service} (cf. {@link Service#getIdTokenAudType()}).
     * </p>
     *
     * @return
     *         The type of the {@code aud} claim in ID tokens.
     *
     * @since 3.57
     * @since Authlete 2.3.3
     */
    public String getIdTokenAudType()
    {
        return idTokenAudType;
    }


    /**
     * Set the type of the {@code aud} claim of the ID token being issued.
     * Valid values are as follows.
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>{@code "array"}</td>
     *     <td>The type of the {@code aud} claim is always an array of strings.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "string"}</td>
     *     <td>The type of the {@code aud} claim is always a single string.</td>
     *   </tr>
     *   <tr>
     *     <td>null</td>
     *     <td>The type of the {@code aud} claim remains the same as before.</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * <p>
     * This request parameter takes precedence over the {@code idTokenAudType}
     * property of {@link Service} (cf. {@link Service#getIdTokenAudType()}).
     * </p>
     *
     * @param type
     *         The type of the {@code aud} claim in ID tokens.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.57
     * @since Authlete 2.3.3
     */
    public DeviceCompleteRequest setIdTokenAudType(String type)
    {
        this.idTokenAudType = type;

        return this;
    }
}
