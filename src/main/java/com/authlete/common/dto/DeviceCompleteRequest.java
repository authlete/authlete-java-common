/*
 * Copyright (C) 2019 Authlete, Inc.
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


/**
 * Request to Authlete's {@code /api/device/complete} API.
 *
 * <p>
 * In the <a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-device-flow/?include_text=1"
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
    private static final long serialVersionUID = 1L;


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
     * associated with the access token which will be issued.
     * </p>
     *
     * @return
     *         The subject (= unique identifier) of the end-user.
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
     * associated with the access token which will be issued.
     * </p>
     *
     * @param subject
     *         The subject (= unique identifier) of the end-user.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceCompleteRequest setSubject(String subject)
    {
        this.subject = subject;

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
}
