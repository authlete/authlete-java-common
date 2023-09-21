/*
 * Copyright (C) 2019-2023 Authlete, Inc.
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
import com.authlete.common.types.ClientAuthMethod;
import com.authlete.common.types.GMAction;


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
    private static final long serialVersionUID = 9L;


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


    /**
     * @since Authlete 2.0.0
     */
    private Action action;

    /**
     * @since Authlete 2.0.0
     */
    private String responseContent;

    /**
     * @since Authlete 2.0.0
     */
    private long clientId;

    /**
     * @since Authlete 2.0.0
     */
    private String clientIdAlias;

    /**
     * @since Authlete 2.0.0
     */
    private boolean clientIdAliasUsed;

    /**
     * @since Authlete 3.0.0
     */
    private URI clientEntityId;

    /**
     * @since Authlete 3.0.0
     */
    private boolean clientEntityIdUsed;

    /**
     * @since Authlete 2.0.0
     */
    private String clientName;

    /**
     * @since Authlete 2.0.0
     */
    private ClientAuthMethod clientAuthMethod;

    /**
     * @since Authlete 2.0.0
     */
    private Scope[] scopes;

    /**
     * @since Authlete 2.2.8
     */
    private DynamicScope[] dynamicScopes;

    /**
     * @since Authlete 2.0.0
     */
    private String[] claimNames;

    /**
     * @since Authlete 2.0.0
     */
    private String[] acrs;

    /**
     * @since Authlete 2.0.0
     */
    private String deviceCode;

    /**
     * @since Authlete 2.0.0
     */
    private String userCode;

    /**
     * @since Authlete 2.0.0
     */
    private URI verificationUri;

    /**
     * @since Authlete 2.0.0
     */
    private URI verificationUriComplete;

    /**
     * @since Authlete 2.0.0
     */
    private int expiresIn;

    /**
     * @since Authlete 2.0.0
     */
    private int interval;

    /**
     * @since Authlete 2.2.1
     */
    private URI[] resources;

    /**
     * @since Authlete 2.2.0
     */
    private AuthzDetails authorizationDetails;

    /**
     * @since Authlete 3.0.0
     */
    private GMAction gmAction;

    /**
     * @since Authlete 3.0.0
     */
    private String grantId;

    /**
     * @since Authlete 3.0.0
     */
    private String grantSubject;

    /**
     * @since Authlete 3.0.0
     */
    private Grant grant;

    /**
     * @since Authlete 2.2.3
     */
    private Pair[] serviceAttributes;

    /**
     * @since Authlete 2.2.3
     */
    private Pair[] clientAttributes;

    /**
     * @since Authlete 2.0.0
     */
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
     * Get the entity ID of the client.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @return
     *         The entity ID of the client.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public URI getClientEntityId()
    {
        return clientEntityId;
    }


    /**
     * Set the entity ID of the client.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @param entityId
     *         The entity ID of the client.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public DeviceAuthorizationResponse setClientEntityId(URI entityId)
    {
        this.clientEntityId = entityId;

        return this;
    }


    /**
     * Get the flag which indicates whether the entity ID of the client was
     * used in the device authorization request as a client ID.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @return
     *         {@code true} if the entity ID of the client was used in the
     *         request as a client ID.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public boolean isClientEntityIdUsed()
    {
        return clientEntityIdUsed;
    }


    /**
     * Set the flag which indicates whether the entity ID of the client was
     * used in the device authorization request as a client ID.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @param used
     *         {@code true} to indicate that the entity ID of the client was
     *         used in the request as a client ID.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public DeviceAuthorizationResponse setClientEntityIdUsed(boolean used)
    {
        this.clientEntityIdUsed = used;

        return this;
    }


    /**
     * Get the client identifier used in the device authorization request.
     *
     * <p>
     * When {@link #isClientIdAliasUsed()} returns {@code true}, this method
     * returns the same value as {@link #getClientIdAlias()} does. Otherwise,
     * if {@link #isClientEntityIdUsed()} returns {@code true}, this method
     * returns the same value as {@link #getClientEntityId()}{@code .toString()}
     * does. In other cases, this method returns the string representation of
     * the value returned from {@link #getClientId()}.
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
        else if (clientEntityIdUsed)
        {
            return clientEntityId.toString();
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
     * Get the client authentication method that should be performed at the
     * device authorization endpoint.
     *
     * <p>
     * If the client could not be identified by the information in the request,
     * this method returns {@code null}.
     * </p>
     *
     * @return
     *         The client authentication method that should be performed at
     *         the device authorization endpoint.
     *
     * @since 2.50
     */
    public ClientAuthMethod getClientAuthMethod()
    {
        return clientAuthMethod;
    }


    /**
     * Set the client authentication method that should be performed at the
     * device authorization endpoint.
     *
     * @param method
     *         The client authentication method that should be performed at
     *         the device authorization endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.50
     */
    public DeviceAuthorizationResponse setClientAuthMethod(ClientAuthMethod method)
    {
        this.clientAuthMethod = method;

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
     * @return
     *         {@code this} object.
     *
     * @since 2.92
     *
     * @see DynamicScope
     */
    public DeviceAuthorizationResponse setDynamicScopes(DynamicScope[] dynamicScopes)
    {
        this.dynamicScopes = dynamicScopes;

        return this;
    }


    /**
     * Get the names of the claims which were requested indirectly via some
     * special scopes. See <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     * >5.4. Requesting Claims using Scope Values</a> in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect
     * Core 1.0</a> for details.
     *
     * <p>
     * This method always returns {@code null} if the {@code scope} request
     * parameter of the device authorization request does not include the
     * {@code openid} scope even if special scopes (such as {@code profile})
     * are included in the request (unless the {@code openid} scope is included
     * in the default set of scopes which is used when the {@code scope}
     * request parameter is omitted).
     * </p>
     *
     * @return
     *         The names of the requested claims.
     *
     * @since 2.44
     */
    public String[] getClaimNames()
    {
        return claimNames;
    }


    /**
     * Set the names of the claims which were requested indirectly via some
     * special scopes.
     *
     * @param names
     *         The names of the requested claims.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.44
     */
    public DeviceAuthorizationResponse setClaimNames(String[] names)
    {
        this.claimNames = names;

        return this;
    }


    /**
     * Get the list of ACR values requested by the device authorization
     * request.
     *
     * <p>
     * Basically, this method returns the value of the {@code "acr_values"}
     * request parameter in the device authorization request. However,
     * because unsupported ACR values are dropped on Authlete side, if the
     * {@code "acr_values"} request parameter contains unrecognized ACR
     * values, the list returned by this method becomes different from the
     * value of the {@code "acr_values"} request parameter.
     * </p>
     *
     * <p>
     * If the request does not include the {@code acr_values} request
     * parameter, the value of the {@code default_acr_values} client metadata
     * is used.
     * </p>
     *
     * @return
     *         The list of requested ACR values.
     *
     * @since 2.44
     */
    public String[] getAcrs()
    {
        return acrs;
    }


    /**
     * Set the list of ACR values requested by the device authorization
     * request.
     *
     * @param acrs
     *         The list of requested ACR values.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.44
     */
    public DeviceAuthorizationResponse setAcrs(String[] acrs)
    {
        this.acrs = acrs;

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


    /**
     * Get the resources specified by the {@code resource} request parameters.
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
     * Set the resources specified by the {@code resource} request parameters.
     * See <i>"Resource Indicators for OAuth 2.0"</i> for details.
     *
     * @param resources
     *         Target resources.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.62
     */
    public DeviceAuthorizationResponse setResources(URI[] resources)
    {
        this.resources = resources;

        return this;
    }


    /**
     * Get the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @return
     *         Authorization details.
     *
     * @since 2.58
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
     * @return
     *         {@code this} object.
     *
     * @since 2.58
     */
    public DeviceAuthorizationResponse setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;

        return this;
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
     *         and {@link GMAction#MERGE MERGE}.
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
     *         and {@link GMAction#MERGE MERGE}.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public DeviceAuthorizationResponse setGmAction(GMAction action)
    {
        this.gmAction = action;

        return this;
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
     * @return
     *         {@code this} object.
     *
     * @since 3.1
     */
    public DeviceAuthorizationResponse setGrantId(String grantId)
    {
        this.grantId = grantId;

        return this;
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
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public DeviceAuthorizationResponse setGrantSubject(String subject)
    {
        this.grantSubject = subject;

        return this;
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
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public DeviceAuthorizationResponse setGrant(Grant grant)
    {
        this.grant = grant;

        return this;
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
     * @return
     *         {@code this} object.
     *
     * @since 2.88
     */
    public DeviceAuthorizationResponse setServiceAttributes(Pair[] attributes)
    {
        this.serviceAttributes = attributes;

        return this;
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
     * @return
     *         {@code this} object.
     *
     * @since 2.88
     */
    public DeviceAuthorizationResponse setClientAttributes(Pair[] attributes)
    {
        this.clientAttributes = attributes;

        return this;
    }
}
