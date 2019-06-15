/*
 * Copyright (C) 2019 Authlete, Inc.
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


import java.net.URI;


/**
 * Response from Authlete's {@code /api/device/authorization} API.
 *
 * <p>
 * Authlete's {@code /api/device/authorization} API returns JSON which can
 * be mapped to this class. The authorization server implementation should
 * retrieve the value of {@code action} from the response and take the
 * following steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code OK}, it means that the device
 * authorization request from the client application is valid.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 200 OK} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which can
 * be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code BAD_REQUEST}, it means that the
 * device authorization request from the client application was wrong.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 400 Bad Request} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
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
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#UNAUTHORIZED UNAUTHORIZED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code UNAUTHORIZED}, it means that
 * client authentication of the device authorization request failed.
 * </p>
 *
 * <p>
 * The authorization server implementation should generate a response to the
 * client application with {@code 401 Unauthorized} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
 * </p>
 *
 * <p>
 * The following illustrates the response which the authorization server
 * implementation should generate and return to the client application.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(challenge)</i>
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * <i>(The value returned from {@link #getResponseContent()})</i></pre>
 * <br/>
 * </dd>
 *
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code action} is {@code INTERNAL_SERVER_ERROR}, it means
 * that the API call from the authorization server implementation was wrong or
 * that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from a viewpoint of the client application, it is an error
 * on the server side. Therefore, the authorization server implementation
 * should generate a response to the client application with
 * {@code 500 Internal Server Error} and {@code application/json}.
 * </p>
 *
 * <p>
 * The {@link #getResponseContent()} method returns a JSON string which
 * describes the error, so it can be used as the entity body of the response.
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
 * <br/>
 * </dd>
 *
 * </dl>
 *
 * @since 2.42
 */
public class DeviceAuthorizationResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action that the authorization server implementation should take.
     */
    public enum Action
    {
        /**
         * The device authorization request is valid. The authorization server
         * implementation should return a successful response with {@code 200 OK}
         * and {@code application/json} to the client application.
         */
        OK,


        /**
         * The device authorization request is invalid. The authorization server
         * implementation should return an error response with {@code 400 Bad
         * Request} and {@code application/json} to the client application.
         */
        BAD_REQUEST,


        /**
         * Client authentication of the device authorization request failed. The
         * authorization server implementation should return an error response
         * with {@code 401 Unauthorized} and {@code application/json} to the
         * client application.
         */
        UNAUTHORIZED,


        /**
         * The API call from the authorization server implementation was wrong
         * or an error occurred on Authlete side. The authorization server
         * implementation should return an error response with
         * {@code 500 Internal Server Error} and {@code application/json} to
         * the client application.
         */
        INTERNAL_SERVER_ERROR,
    }


    private Action action;
    private String responseContent;
    private long clientId;
    private String clientIdAlias;
    private boolean clientIdAliasUsed;
    private String clientName;
    private Scope[] scopes;
    private String deviceCode;
    private String userCode;
    private URI verificationUri;
    private URI verificationUriComplete;
    private int expiresIn;
    private int interval;
    private String[] warnings;


    /**
     * Get the next action that the implementation of the device authorization
     * endpoint should take.
     *
     * @return
     *         The next action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the implementation of the device authorization
     * endpoint should take.
     *
     * @param action
     *         The next action.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the content that can be used to generate a response to the client
     * application.
     *
     * @return
     *         The content of a response to the client.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the content that can be used to generate a response to the client
     * application.
     *
     * @param responseContent
     *         The content of a response to the client.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the client ID of the client application that has made the device
     * authorization request.
     *
     * @return
     *         The client ID of the client application.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID of the client application that has made the device
     * authorization request.
     *
     * @param clientId
     *         The client ID of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the client ID alias of the client application that has made the
     * device authorization request.
     *
     * @return
     *         The client ID alias of the client application.
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the client ID alias of the client application that has made the
     * device authorization request.
     *
     * @param alias
     *         The client ID alias of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;

        return this;
    }


    /**
     * Get the flag which indicates whether the client ID alias was used in
     * the device authorization request.
     *
     * @return
     *         {@code true} if the client ID alias was used in the request.
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether the client ID alias was used in
     * the device authorization request.
     *
     * @param used
     *         {@code true} to indicate that the client ID alias was used in
     *         the request.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;

        return this;
    }


    /**
     * Get the client identifier used in the device authorization request.
     *
     * <p>
     * When {@link #isClientIdAliasUsed()} returns {@code true}, this method
     * returns the same value as {@link #getClientIdAlias()} does. Otherwise,
     * this method returns the string representation of the value returned
     * from {@link #getClientId()}.
     * </p>
     *
     * @return
     *         The client identifier used in the device authorization request.
     */
    public String getClientIdentifier()
    {
        if (clientIdAliasUsed)
        {
            return clientIdAlias;
        }
        else
        {
            return String.valueOf(clientId);
        }
    }


    /**
     * Get the name of the client application which has made the device
     * authorization request.
     *
     * @return
     *         The name of the client application.
     */
    public String getClientName()
    {
        return clientName;
    }


    /**
     * Set the name of the client application which has made the device
     * authorization request.
     *
     * @param name
     *         The name of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setClientName(String name)
    {
        this.clientName = name;

        return this;
    }


    /**
     * Get the scopes requested by the device authorization request.
     *
     * <p>
     * Basically, this method returns the value of the {@code "scope"} request
     * parameter in the device authorization request. However, because
     * unregistered scopes are dropped on Authlete side, if the {@code "scope"}
     * request parameter contains unknown scopes, the list returned by this
     * method becomes different from the value of the {@code "scope"} request
     * parameter.
     * </p>
     *
     * <p>
     * Note that {@link Scope#getDescription()} method and
     * {@link Scope#getDescriptions()} method of each element ({@link Scope}
     * instance) in the array returned from this method always return
     * {@code null} even if descriptions of the scopes are registered.
     * </p>
     *
     * @return
     *         The requested scopes.
     */
    public Scope[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes requested by the device authorization request.
     *
     * @param scopes
     *         The requested scopes.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setScopes(Scope[] scopes)
    {
        this.scopes = scopes;

        return this;
    }


    /**
     * Get the device verification code. This corresponds to the
     * {@code device_code} property in the response to the client.
     *
     * @return
     *         The device verification code.
     */
    public String getDeviceCode()
    {
        return deviceCode;
    }


    /**
     * Set the device verification code. This corresponds to the
     * {@code device_code} property in the response to the client.
     *
     * @param code
     *         The device verification code.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setDeviceCode(String code)
    {
        this.deviceCode = code;

        return this;
    }


    /**
     * Get the end-user verification code. This corresponds to the
     * {@code user_code} property in the response to the client.
     *
     * @return
     *         The end-user verification code.
     */
    public String getUserCode()
    {
        return userCode;
    }


    /**
     * Set the end-user verification code. This corresponds to the
     * {@code user_code} property in the response to the client.
     *
     * @param code
     *         The end-user verification code.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setUserCode(String code)
    {
        this.userCode = code;

        return this;
    }


    /**
     * Get the end-user verification URI. This corresponds to the
     * {@code verification_uri} property in the response to the client.
     *
     * @return
     *         The end-user verification URI.
     */
    public URI getVerificationUri()
    {
        return verificationUri;
    }


    /**
     * Set the end-user verification URI. This corresponds to the
     * {@code verification_uri} property in the response to the client.
     *
     *
     * @param uri
     *         The end-user verification URI.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setVerificationUri(URI uri)
    {
        this.verificationUri = uri;

        return this;
    }


    /**
     * Get the end-user verification URI that includes the end-user verification
     * code. This corresponds to the {@code verification_uri_complete} property
     * in the response to the client.
     *
     * @return
     *         The end-user verification URI that includes the end-user
     *         verification code.
     */
    public URI getVerificationUriComplete()
    {
        return verificationUriComplete;
    }


    /**
     * Set the end-user verification URI that includes the end-user verification
     * code. This corresponds to the {@code verification_uri_complete} property
     * in the response to the client.
     *
     *
     * @param uri
     *         The end-user verification URI that includes the end-user
     *         verification code.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setVerificationUriComplete(URI uri)
    {
        this.verificationUriComplete = uri;

        return this;
    }


    /**
     * Get the duration of the issued device verification code and end-user
     * verification code in seconds. This corresponds to the {@code expires_in}
     * property in the response to the client.
     *
     * @return
     *         The duration of the issued device verification code and end-user
     *         verification code in seconds.
     */
    public int getExpiresIn()
    {
        return expiresIn;
    }


    /**
     * Set the duration of the issued device verification code and end-user
     * verification code in seconds. This corresponds to the {@code expires_in}
     * property in the response to the client.
     *
     * @param expiresIn
     *         The duration of the issued device verification code and end-user
     *         verification code in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setExpiresIn(int expiresIn)
    {
        this.expiresIn = expiresIn;

        return this;
    }


    /**
     * Get the minimum amount of time in seconds that the client must wait for
     * between polling requests to the token endpoint. This corresponds to the
     * {@code interval} property in the response to the client.
     *
     * @return
     *         The minimum amount of time in seconds between polling requests.
     */
    public int getInterval()
    {
        return interval;
    }


    /**
     * Set the minimum amount of time in seconds that the client must wait for
     * between polling requests to the token endpoint. This corresponds to the
     * {@code interval} property in the response to the client.
     *
     * @param interval
     *         The minimum amount of time in seconds between polling requests.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setInterval(int interval)
    {
        this.interval = interval;

        return this;
    }


    /**
     * Get the warnings raised during processing the device authorization
     * request.
     *
     * @return
     *         Warnings. This may be {@code null}.
     */
    public String[] getWarnings()
    {
        return warnings;
    }


    /**
     * Set the warnings raised during processing the device authorization
     * request.
     *
     * @param warnings
     *         Warnings.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceAuthorizationResponse setWarnings(String[] warnings)
    {
        this.warnings = warnings;

        return this;
    }
}
