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
package com.authlete.common.dto;

import com.authlete.common.util.Utils;

/**
 * Response from Authlete's {@code /auth/introspection} API.
 *
 * <p>
 * Authlete's {@code /auth/introspection} API returns JSON which can
 * be mapped to this class. The service implementation should retrieve
 * the value of {@code "action"} from the response and take the following
 * steps according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the service implementation was wrong or
 * that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from the viewpoint of the client application, it is an
 * error on the server side. Therefore, the service implementation should
 * generate a response to the client application with the HTTP status of
 * {@code "500 Internal Server Error"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so if the protected resource of the service
 * implementation wants to return an error response to the client application
 * in the way that complies with RFC 6750, the string returned from {@link
 * #getResponseContent()} can be used as the value of {@code WWW-Authenticate}.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "BAD_REQUEST"}, it means
 * that the request from the client application does not contain an access
 * token (= the request from the service implementation to Authlete does
 * not contain {@code "token"} parameter).
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so if the protected resource of the service
 * implementation wants to return an error response to the client application
 * in the way that complies with RFC 6750, the string returned from {@link
 * #getResponseContent()} can be used as the value of {@code WWW-Authenticate}.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#UNAUTHORIZED UNAUTHORIZED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "UNAUTHORIZED"}, it means
 * that the access token does not exist or has expired. Or the client
 * application associated with the access token does not exist any longer.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so if the protected resource of the service
 * implementation wants to return an error response to the client application
 * in the way that complies with RFC 6750, the string returned from {@link
 * #getResponseContent()} can be used as the value of {@code WWW-Authenticate}.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#FORBIDDEN FORBIDDEN}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "FORBIDDEN"}, it means
 * that the access token does not cover the required scopes or that the
 * subject associated with the access token is different from the subject
 * contained in the request.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so if the protected resource of the service
 * implementation wants to return an error response to the client application
 * in the way that complies with RFC 6750, the string returned from {@link
 * #getResponseContent()} can be used as the value of {@code WWW-Authenticate}.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 403 Forbidden
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that the
 * access token which the client application presented is valid (= exists
 * and has not expired).
 * </p>
 *
 * <p>
 * The service implementation is supposed to return the protected resource
 * to the client application.
 * </p>
 *
 * <p>
 * When {@code "action"} is {@code "OK"}, {@link #getResponseContent()}
 * returns {@code "Bearer error=\"invalid_request\""}. This is the
 * simplest string which can be used as the value of {@code WWW-Authenticate}
 * header to indicate {@code "400 Bad Request"}. The service implementation
 * may use this string to tell the client application that the request was
 * bad. But in such a case, the service should generate a more informative
 * error message to help developers of client applications.
 * </p>
 *
 * <p>
 * The following is an example error response which complies with RFC 6750.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 * </dl>
 *
 * <p>
 * Basically, {@link #getResponseContent()} returns a string which describes
 * the error in the format of <a href="http://tools.ietf.org/html/rfc6750"
 * >RFC 6750</a> (OAuth 2.0 Bearer Token Usage). So, if the service has
 * selected {@code "Bearer"} as the token type, the string returned from
 * {@link #getResponseContent()} can be used directly as the value for
 * {@code WWW-Authenticate}. However, if the service has selected another
 * different token type, the service has to generate error messages for
 * itself.
 * </p>
 *
 * @author Takahiko Kawasaki
 */
public class IntrospectionResponse extends ApiResponse
{
    private static final long serialVersionUID = 7L;


    /**
     * The next action the service implementation should take.
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
         * The request does not contain an access token. The service
         * implementation should return {@code "400 Bad Request"} to
         * the client application.
         */
        BAD_REQUEST,

        /**
         * The access token does not exist or has expired. The service
         * implementation should return {@code "401 Unauthorized"} to
         * the client application.
         */
        UNAUTHORIZED,

        /**
         * The access token does not cover the required scopes. The
         * service implementation should return {@code "403 Forbidden"}
         * to the client application.
         */
        FORBIDDEN,

        /**
         * The access token is valid. The service implementation should
         * return the protected resource to the client application.
         */
        OK
    }


    private static final String SUMMARY_FORMAT
        = "action=%s, clientId=%d, subject=%s, existent=%s, "
        + "usable=%s, sufficient=%s, refreshable=%s, expiresAt=%d, "
        + "scopes=%s, properties=%s, clientIdAlias=%s, clientIdAliasUsed=%s, "
        + "confirmation=%s";


    /**
     * The next action the service implementation should take.
     */
    private Action action;


    /**
     * The client ID.
     */
    private long clientId;


    /**
     * Resource owner's user account.
     */
    private String subject;


    /**
     * Scopes.
     */
    private String[] scopes;


    /**
     * Flag to indicate whether the access token exists.
     */
    private boolean existent;


    /**
     * Flag to indicate whether the access token is usable
     * (= exists and has not expired).
     */
    private boolean usable;


    /**
     * Flag to indicate whether the access token covers the required scopes.
     */
    private boolean sufficient;


    /**
     * Flag to indicate whether the access token is refreshable.
     */
    private boolean refreshable;


    /**
     * Entity body of the response to the client.
     */
    private String responseContent;


    /**
     * The time at which the access token expires.
     */
    private long expiresAt;


    /**
     * Extra properties associated with the access token.
     */
    private Property[] properties;


    /**
     * The client ID alias when the authorization request or the token request for
     * the access token was made.
     */
    private String clientIdAlias;


    /**
     * Flag which indicates whether the client ID alias was used when the authorization
     * request or the token request for the access token was made.
     */
    private boolean clientIdAliasUsed;

    
    /**
     * Confirmation hash for MTLS-bound access tokens. Currently only the s256 
     * type is supported and is assumed.
     */
    private String s256Confirmation;
    
    /**
     * Get the next action the service implementation should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action the service implementation should take.
     */
    public void setAction(Action action)
    {
        this.action = action;
    }


    /**
     * Get the client ID.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID.
     */
    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }


    /**
     * Get the subject (= resource owner's ID).
     *
     * <p>
     * This method returns {@code null} if the access token was generated
     * by <a href="http://tools.ietf.org/html/rfc6749#section-4.4"
     * >Client Credentials Grant</a>, which means that the access token
     * is not associated with any specific end-user.
     * </p>
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= resource owner's ID).
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    /**
     * Get the scopes covered by the access token.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes covered by the access token.
     */
    public void setScopes(String[] scopes)
    {
        this.scopes = scopes;
    }


    /**
     * Get the flag which indicates whether the access token exists.
     *
     * @return
     *         {@code true} if the access token exists.
     *         {@code false} if the access token does not exist.
     */
    public boolean isExistent()
    {
        return existent;
    }


    /**
     * Set the flag which indicates whether the access token exists.
     */
    public void setExistent(boolean existent)
    {
        this.existent = existent;
    }


    /**
     * Get the flag which indicates whether the access token is usable
     * (= exists and has not expired).
     *
     * @return
     *         {@code true} if the access token is usable. {@code false}
     *         if the access token does not exist or has expired.
     */
    public boolean isUsable()
    {
        return usable;
    }


    /**
     * Set the flag which indicates whether the access token is usable
     * (= exists and has not expired).
     */
    public void setUsable(boolean usable)
    {
        this.usable = usable;
    }


    /**
     * Get the flag which indicates whether the access token covers
     * the required scopes.
     *
     * @return
     *         {@code true} if the access token covers all the required
     *         scopes. {@code false} if any one of the required scopes
     *         is not covered by the access token.
     */
    public boolean isSufficient()
    {
        return sufficient;
    }


    /**
     * Set the flag which indicates whether the access token covers
     * the required scopes.
     */
    public void setSufficient(boolean sufficient)
    {
        this.sufficient = sufficient;
    }


    /**
     * Get the flag which indicates whether the access token can be
     * refreshed using the associated refresh token.
     *
     * @return
     *         {@code true} if the access token can be refreshed using
     *         the associated refresh token. {@code false} if the refresh
     *         token for the access token has expired or the access token
     *         has no associated refresh token.
     */
    public boolean isRefreshable()
    {
        return refreshable;
    }


    /**
     * Set the flag which indicates whether the access token can be
     * refreshed using the associated refresh token.
     */
    public void setRefreshable(boolean refreshable)
    {
        this.refreshable = refreshable;
    }


    /**
     * Get the response content which can be used as a part of the
     * response to the client application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as a part of the
     * response to the client application.
     */
    public void setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;
    }


    /**
     * Get the time at which the access token expires in milliseconds
     * since the Unix epoch (1970-01-01).
     *
     * @return
     *         The time at which the access token expires.
     */
    public long getExpiresAt()
    {
        return expiresAt;
    }


    /**
     * Set the time at which the access token expires in milliseconds
     * since the Unix epoch (1970-01-01).
     *
     * @param expiresAt
     *         The time at which the access token expires.
     */
    public void setExpiresAt(long expiresAt)
    {
        this.expiresAt = expiresAt;
    }


    /**
     * Get the extra properties associated with the access token.
     *
     * @return
     *         Extra properties. This method returns {@code null} when
     *         no extra property is associated with the access token.
     *
     * @since 1.30
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set the extra properties associated with the access token.
     *
     * @param properties
     *         Extra properties.
     *
     * @since 1.30
     */
    public void setProperties(Property[] properties)
    {
        this.properties = properties;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
                action, clientId, subject, existent, usable,
                sufficient, refreshable, expiresAt,
                Utils.join(scopes, " "),
                Utils.stringifyProperties(properties),
                clientIdAlias, clientIdAliasUsed,
                s256Confirmation);
    }


    /**
     * Get the client ID alias when the authorization request or the token
     * request for the access token was made. Note that this value may be
     * different from the current client ID alias.
     *
     * @return
     *         The client ID alias when the authorization request or the
     *         token request for the access token was made.
     *
     * @since 2.3
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the client ID alias when the authorization request or the token
     * request for the access token was made.
     *
     * @param alias
     *         The client ID alias.
     *
     * @since 2.3
     */
    public void setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;
    }


    /**
     * Get the flag which indicates whether the client ID alias was used
     * when the authorization request or the token request for the access
     * token was made.
     *
     * @return
     *         {@code true} if the client ID alias was used when the
     *         authorization request or the token request for the access
     *         token was made.
     *
     * @since 2.3
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether the client ID alias was used
     * when the authorization request or the token request for the access
     * token was made.
     *
     * @param used
     *         {@code true} if the client ID alias was used when the
     *         authorization request or the token request for the access
     *         token was made.
     *
     * @since 2.3
     */
    public void setClientIdAliasUsed(boolean used)
    {
        this.clientIdAliasUsed = used;
    }


    /**
     * Get the confirmation value, used in MTLS bound access tokens.
     * 
     * @return the cnf
     */
    public String getS256Confirmation()
    {
        return s256Confirmation;
    }


    /**
     * Set the confirmation value, used in MTLS bound access tokens.
     *
     * @param cnf the cnf to set
     */
    public void setS256Confirmation(String cnf)
    {
        this.s256Confirmation = cnf;
    }

}
