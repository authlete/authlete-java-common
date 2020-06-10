/*
 * Copyright (C) 2015 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;
import com.authlete.common.types.Sns;


/**
 * Developer authentication request from Authlete to a service implementation.
 *
 * <p>
 * When a client application developer needs to be authenticated at
 * the Client Application Developer Console (which Authlete provides
 * for client application developers on behalf of the service),
 * Authlete makes a <strong>developer authentication callback request
 * </strong> to the developer authentication callback endpoint of the
 * service. This class represents the format of the request.
 * </p>
 *
 * <p>
 * When the developer tried end-user authentication by inputting his/her
 * credentials to the input fields of the form, {@code id} and {@code
 * password} in this authentication callback request are the values
 * that the developer has input.
 * </p>
 *
 * <p>
 * On the other hand, when the developer tried developer authentication by
 * signing in an SNS such as Facebook, {@code id} represents the subject
 * (unique identifier) of the developer in the SNS and {@code password}
 * has no meaning. In this case, {@code sns} and {@code accessToken} are
 * not {@code null}. {@code accessToken} in this request is the value of
 * the access token issued by the SNS which your implementation of the
 * developer authentication callback endpoint may use as necessary.
 * </p>
 *
 * <p>
 * Some notes specific to respective SNSes.
 * </p>
 *
 * <blockquote>
 * <dl>
 *   <dt><strong>Facebook</strong></dt>
 *   <dd>
 *     <p>
 *       The value of <strong>{@code id}</strong> is unique to each
 *       Facebook application and cannot be used across different
 *       applications. If you need the third party ID, make an API
 *       call to <code>/me</code> endpoint with
 *       <code>fields=third_party_id</code> and
 *       <code>access_token=<i>{accessToken}</i></code>. See the API
 *       document of Facebook for details.
 *     </p>
 *     <p>
 *       The value of <strong>{@code rawTokenResponse}</strong> is
 *       in the form of <code>application/x-www-form-urlencoded</code>
 *       (not <code>application/json</code>). This is a violation
 *       against RFC 6749 (OAuth 2.0).
 *     </p>
 *     <p>
 *       The value of <strong>{@code refreshToken}</strong> is empty.
 *     </p>
 *     <p>
 *       The value of <strong>expiresIn</strong> is the value of
 *       <code>expires</code> in the response from the token endpoint
 *       of Facebook.
 *     </p>
 *   </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.9
 */
public class DeveloperAuthenticationCallbackRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private long serviceApiKey;
    private String id;
    private String password;

    private Sns sns;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private String rawTokenResponse;


    /**
     * Get the API key of the target service.
     *
     * <p>
     * This property is always set when Authlete makes a request.
     * </p>
     *
     * @return
     *         The API key of the target service.
     */
    public long getServiceApiKey()
    {
        return serviceApiKey;
    }


    /**
     * Set the API key of the target service.
     *
     * @param apiKey
     *         The API key of the target service.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setServiceApiKey(long apiKey)
    {
        this.serviceApiKey = apiKey;

        return this;
    }


    /**
     * Get the ID of the developer to authenticate.
     *
     * <p>
     * When the value of {@code sns} property is {@code null}, this property
     * holds the value of the login ID that the developer has entered to the
     * login ID field in the login form of the Client Application Developer
     * Console (which Authlete provides for client application developers on
     * behalf of the service).
     * </p>
     *
     * <p>
     * On the other hand, if {@code sns} property is not {@code null},
     * this property holds the subject (= unique identifier) of the
     * developer in the SNS.
     * </p>
     *
     * <p>
     * This property is always set when Authlete makes a request.
     * </p>
     *
     * @return
     *         The ID of the developer to authenticate.
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the ID of the developer to authenticate.
     *
     * @param id
     *         The ID of the developer to authenticate.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setId(String id)
    {
        this.id = id;

        return this;
    }


    /**
     * Get the password of the developer to authenticate.
     *
     * <p>
     * This property holds the value of the password that the developer
     * has entered to the password field in the login form of the Client
     * Application Developer Console (which Authlete provides for client
     * application developers on behalf of the service).
     * </p>
     *
     * <p>
     * If {@code sns} property is {@code null}, it is ensured that this
     * property is not {@code null}. In such a case, authentication
     * should be performed on the pair of {@link #id} property and this
     * {@code password} property. On the other hand, if {@code sns}
     * property is not {@code null}, this property has no meaning,
     * because authentication has been performed by the SNS.
     * </p>
     *
     * @return
     *         The password of the developer to authenticate.
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * Set the password of the developer to authenticate.
     *
     * @param password
     *         The password of the developer to authenticate.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setPassword(String password)
    {
        this.password = password;

        return this;
    }


    /**
     * Get the SNS that the developer used for social login.
     *
     * @return
     *         The SNS that the developer used for social login.
     *         {@code null} is returned if the developer did not
     *         use social login.
     */
    public Sns getSns()
    {
        return sns;
    }


    /**
     * Set the SNS that the developer used for social login.
     *
     * @param sns
     *         The SNS that the developer used for social login.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setSns(Sns sns)
    {
        this.sns = sns;

        return this;
    }


    /**
     * Get the access token returned by the SNS which the developer
     * used for social login.
     *
     * @return
     *         The access token returned by the SNS. {@code null}
     *         is returned when social login was not used.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token returned by the SNS which the developer
     * used for social login.
     *
     * @param accessToken
     *         The access token returned by the SNS.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the refresh token returned by the SNS which the developer
     * used for social login.
     *
     * @return
     *         The refresh token returned by the SNS. {@code null}
     *         is returned when social login was not used or if
     *         the SNS did not return a refresh token.
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the refresh token returned by the SNS which the developer
     * used for social login.
     *
     * @param refreshToken
     *         The refresh token returned by the SNS.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;

        return this;
    }


    /**
     * Get the lifetime of the access token in seconds.
     *
     * @return
     *         The lifetime of the access token in seconds. 0 is returned
     *         when social login was not used of if the SNS did not return
     *         information about lifetime of the access token.
     */
    public long getExpiresIn()
    {
        return expiresIn;
    }


    /**
     * Set the lifetime of the access token in seconds.
     *
     * @param expiresIn
     *         The lifetime of the access token in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setExpiresIn(long expiresIn)
    {
        this.expiresIn = expiresIn;

        return this;
    }


    /**
     * Get the raw response from the token endpoint of the SNS.
     *
     * <p>
     * If the SNS complies with RFC 6749, the format is JSON.
     * Note that Facebook returns application/x-www-form-urlencoded.
     * </p>
     *
     * @return
     *         The raw response from the token endpoint of the SNS.
     */
    public String getRawTokenResponse()
    {
        return rawTokenResponse;
    }


    /**
     * Set the raw response from the token endpoint of the SNS.
     *
     * @param response
     *         The raw response from the token endpoint of the SNS.
     *
     * @return
     *         {@code this} object.
     */
    public DeveloperAuthenticationCallbackRequest setRawTokenResponse(String response)
    {
        this.rawTokenResponse = response;

        return this;
    }
}
