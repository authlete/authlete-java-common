/*
 * Copyright (C) 2014-2023 Authlete, Inc.
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


import java.net.URI;
import com.authlete.common.types.GrantType;
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
 * <h3>JWT-based access token</h3>
 *
 * <p>
 * Since version 2.1, Authlete provides a feature to issue access tokens in
 * JWT format. This feature can be enabled by setting a non-null value to the
 * {@code accessTokenSignAlg} property of the service (see the description of
 * the {@link Service} class for details). {@code /api/auth/introspection} API
 * can accept access tokens in JWT format. However, note that the API does not
 * return information contained in a given JWT-based access token but returns
 * information stored in the database record which corresponds to the given
 * JWT-based access token. Because attributes of the database record can be
 * modified after the access token is issued (for example, by using {@code
 * /api/auth/token/update} API), information returned by {@code
 * /api/auth/introspection} API and information the given JWT-based access
 * token holds may be different.
 * </p>
 *
 * @author Takahiko Kawasaki
 */
public class IntrospectionResponse extends ApiResponse
{
    private static final long serialVersionUID = 19L;


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
     * Scope details.
     *
     * @since 3.16
     */
    private Scope[] scopeDetails;


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
     * The entity ID of the client.
     *
     * @since 3.37
     * @since Authlete 2.3
     */
    private URI clientEntityId;


    /**
     * Flag which indicates whether the entity ID of the client was used when
     * the request for the access token was made.
     *
     * @since 3.37
     * @since Authlete 2.3
     */
    private boolean clientEntityIdUsed;


    /**
     * Confirmation hash for MTLS-bound access tokens. Currently only the S256
     * type is supported and is assumed.
     */
    private String certificateThumbprint;


    /**
     * The target resources specified by the initial request.
     */
    private URI[] resources;


    /**
     * The target resources of the access token.
     */
    private URI[] accessTokenResources;


    /**
     * The content of the {@code "authorization_details"} request parameter which was
     * included in the request that obtained the token.
     */
    private AuthzDetails authorizationDetails;


    /**
     * Grant ID that this access token is tied to.
     */
    private String grantId;


    /**
     * Grant that this access token has inherited.
     */
    private Grant grant;


    /**
     * Claims that the user has consented for the client application to know.
     */
    private String[] consentedClaims;


    /**
     * The attributes of the service that the client belongs to.
     */
    private Pair[] serviceAttributes;


    /**
     * The attributes of the client that the access token has been issued to.
     */
    private Pair[] clientAttributes;


    /**
     * Flag that indicates whether the token is for an external attachment.
     *
     * @since 3.16
     */
    private boolean forExternalAttachment;


    /**
     * The Authentication Context Class Reference of the user authentication
     * that the authorization server performed during the course of issuing
     * the access token.
     *
     * @since 3.40
     * @since Authlete 2.3
     */
    private String acr;


    /**
     * The time when the user authentication was performed during the course
     * of issuing the access token.
     *
     * @since 3.40
     * @since Authlete 2.3
     */
    private long authTime;


    /**
     * The grant type that was used for the issuance of the access token.
     *
     * @since 3.41
     * @since Authlete 2.1.24
     * @since Authlete 2.2.36
     * @since Authlete 2.3
     */
    private GrantType grantType;


    /**
     * The flag indicating whether the token is for credential issuance.
     *
     * @since 3.62
     * @since Authlete 3.0
     */
    private boolean forCredentialIssuance;


    /**
     * The credentials offered by the token.
     *
     * @since 3.62
     * @since Authlete 3.0
     */
    private String credentials;


    /**
     * The {@code c_nonce}.
     *
     * @since 3.63
     * @since Authlete 3.0
     */
    private String cNonce;


    /**
     * The time at which the {@code c_nonce} expires.
     *
     * @since 3.63
     * @since Authlete 3.0
     */
    private long cNonceExpiresAt;


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
     * Get the details of the scopes.
     *
     * <p>
     * The {@code scopes} property of this class is a list of scope names.
     * The property does not hold information about scope attributes.
     * This {@code scopeDetails} property was newly created to convey
     * information about scope attributes.
     * </p>
     *
     * @return
     *         The details of the scopes.
     *
     * @since 3.16
     */
    public Scope[] getScopeDetails()
    {
        return scopeDetails;
    }


    /**
     * Set the details of the scopes.
     *
     * <p>
     * The {@code scopes} property of this class is a list of scope names.
     * The property does not hold information about scope attributes.
     * This {@code scopeDetails} property was newly created to convey
     * information about scope attributes.
     * </p>
     *
     * @param details
     *         The details of the scopes.
     *
     * @since 3.16
     */
    public void setScopeDetails(Scope[] details)
    {
        this.scopeDetails = details;
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
                certificateThumbprint);
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
     * Get the entity ID of the client.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-connect-federation-1_0.html">OpenID
     * Connect Federation 1.0</a>.
     * </p>
     *
     * @return
     *         The entity ID of the client.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
     *      >OpenID Connect Federation 1.0</a>
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
     * "https://openid.net/specs/openid-connect-federation-1_0.html">OpenID
     * Connect Federation 1.0</a>.
     * </p>
     *
     * @param entityId
     *         The entity ID of the client.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
     *      >OpenID Connect Federation 1.0</a>
     */
    public void setClientEntityId(URI entityId)
    {
        this.clientEntityId = entityId;
    }


    /**
     * Get the flag which indicates whether the entity ID of the client was
     * used when the request for the access token was made.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-connect-federation-1_0.html">OpenID
     * Connect Federation 1.0</a>.
     * </p>
     *
     * @return
     *         {@code true} if the entity ID of the client was used when the
     *         request for the access token was made.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
     *      >OpenID Connect Federation 1.0</a>
     */
    public boolean isClientEntityIdUsed()
    {
        return clientEntityIdUsed;
    }


    /**
     * Set the flag which indicates whether the entity ID of the client was
     * used when the request for the access token was made.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-connect-federation-1_0.html">OpenID
     * Connect Federation 1.0</a>.
     * </p>
     *
     * @param used
     *         {@code true} to indicate that the entity ID of the client was
     *         used when the request for the access token was made.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-connect-federation-1_0.html"
     *      >OpenID Connect Federation 1.0</a>
     */
    public void setClientEntityIdUsed(boolean used)
    {
        this.clientEntityIdUsed = used;
    }


    /**
     * Get the client certificate thumbprint used to validate the access token.
     *
     * @return
     *         The certificate thumbprint, calculated as the SHA256 hash
     *         of the DER-encoded certificate value.
     *
     * @since 2.14
     */
    public String getCertificateThumbprint()
    {
        return certificateThumbprint;
    }


    /**
     * Set the client certificate thumbprint used to validate the access token.
     *
     * @param thumbprint
     *         The certificate thumbprint, calculated as the SHA256 hash
     *         of the DER-encoded certificate value.
     *
     * @since 2.14
     */
    public void setCertificateThumbprint(String thumbprint)
    {
        this.certificateThumbprint = thumbprint;
    }


    /**
     * Get the target resources. This represents the resources specified by
     * the {@code resource} request parameters or by the {@code resource}
     * property in the request object.
     *
     * <p>
     * See "Resource Indicators for OAuth 2.0" for details.
     * </p>
     *
     * @return
     *         Target resources.
     *
     * @see #getAccessTokenResources()
     *
     * @since 2.62
     */
    public URI[] getResources()
    {
        return resources;
    }


    /**
     * Set the target resources. This represents the resources specified by
     * the {@code resource} request parameters or by the {@code resource}
     * property in the request object.
     *
     * @param resources
     *         Target resources.
     *
     * @see #setAccessTokenResources(URI[])
     *
     * @since 2.62
     */
    public void setResources(URI[] resources)
    {
        this.resources = resources;
    }


    /**
     * Get the target resources of the access token.
     *
     * <p>
     * The target resources returned by this method may be the same as or
     * different from the ones returned by {@link #getResources()}.
     * </p>
     *
     * <p>
     * In some flows, the initial request and the subsequent token request
     * are sent to different endpoints. Example flows are the Authorization
     * Code Flow, the Refresh Token Flow, the CIBA Ping Mode, the CIBA Poll
     * Mode and the Device Flow. In these flows, not only the initial request
     * but also the subsequent token request can include the {@code resource}
     * request parameters. The purpose of the {@code resource} request
     * parameters in the token request is to narrow the range of the target
     * resources from the original set of target resources requested by the
     * preceding initial request. If narrowing down is performed, the target
     * resources returned by {@link #getResources()} and the ones returned by
     * this method are different. This method returns the narrowed set of
     * target resources.
     * </p>
     *
     * <p>
     * See "Resource Indicators for OAuth 2.0" for details.
     * </p>
     *
     * @return
     *         The target resources of the access token.
     *
     * @see #getResources()
     *
     * @since 2.62
     */
    public URI[] getAccessTokenResources()
    {
        return accessTokenResources;
    }


    /**
     * Set the target resources of the access token.
     *
     * <p>
     * See the description of {@link #getAccessTokenResources()} for details
     * about the target resources of the access token.
     * </p>
     *
     * @param resources
     *         The target resources of the access token.
     *
     * @see #setResources(URI[])
     *
     * @since 2.62
     */
    public void setAccessTokenResources(URI[] resources)
    {
        this.accessTokenResources = resources;
    }


    /**
     * Get the authorization details. This represents the value of the
     * {@code "authorization_details"} request parameter which is defined in
     * <i>"OAuth 2.0 Rich Authorization Requests"</i>.
     *
     * @return
     *         Authorization details.
     *
     * @since 2.56
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
     * @since 2.56
     */
    public void setAuthorizationDetails(AuthzDetails details)
    {
        this.authorizationDetails = details;
    }


    /**
     * Get the grant ID which this access token is tied to.
     *
     * <p>
     * In Authlete, when an authorization request includes the
     * {@code grant_management_action} request parameter, a grant ID (which
     * may be a newly-generated one or an existing one specified by the
     * {@code grant_id} request parameter) is tied to the access token which
     * is created as a result of the authorization request.
     * </p>
     *
     * @return
     *         The grant ID tied to this access token.
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
     * Set the grant ID which this access token is tied to.
     *
     * <p>
     * In Authlete, when an authorization request includes the
     * {@code grant_management_action} request parameter, a grant ID (which
     * may be a newly-generated one or an existing one specified by the
     * {@code grant_id} request parameter) is tied to the access token which
     * is created as a result of the authorization request.
     * </p>
     *
     * @param grantId
     *         The grant ID tied to this access token.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public void setGrantId(String grantId)
    {
        this.grantId = grantId;
    }


    /**
     * Get the grant that this access token has inherited.
     *
     * <p>
     * When an authorization request includes {@code grant_id} and
     * {@code grant_management_action=update}, privileges identified by the
     * grant ID are additionally given to the access token which is created
     * as a result of the authorization request. This property represents
     * the grant.
     * </p>
     *
     * @return
     *         The grant that this access token has inherited.
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
     * Set the grant that this access token has inherited.
     *
     * <p>
     * When an authorization request includes {@code grant_id} and
     * {@code grant_management_action=update}, privileges identified by the
     * grant ID are additionally given to the access token which is created
     * as a result of the authorization request. This property represents
     * the grant.
     * </p>
     *
     * @param grant
     *         The grant that this access token has inherited.
     *
     * @see <a href="https://openid.net/specs/fapi-grant-management.html"
     *      >Grant Management for OAuth 2.0</a>
     *
     * @since 3.1
     */
    public void setGrant(Grant grant)
    {
        this.grant = grant;
    }


    /**
     * Get the claims that the user has consented for the client application
     * to know.
     *
     * <p>
     * The following Authlete APIs accept a {@code consentedClaims} request
     * parameter (which is supported from Authlete 2.3).
     * </p>
     *
     * <ul>
     * <li>{@code /api/auth/authorization/issue}
     * <li>{@code /api/backchannel/authentication/complete}
     * <li>{@code /api/device/complete}
     * </ul>
     *
     * <p>
     * The request parameter is used to convey consented claims to Authlete.
     * This property holds the consented claims. See the description of
     * {@link AuthorizationIssueRequest#setConsentedClaims(String[])} for
     * details.
     * </p>
     *
     * @return
     *         Consented claims.
     *
     * @see AuthorizationIssueRequest#setConsentedClaims(String[])
     * @see BackchannelAuthenticationCompleteRequest#setConsentedClaims(String[])
     * @see DeviceCompleteRequest#setConsentedClaims(String[])
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
     * The following Authlete APIs accept a {@code consentedClaims} request
     * parameter (which is supported from Authlete 2.3).
     * </p>
     *
     * <ul>
     * <li>{@code /api/auth/authorization/issue}
     * <li>{@code /api/backchannel/authentication/complete}
     * <li>{@code /api/device/complete}
     * </ul>
     *
     * <p>
     * The request parameter is used to convey consented claims to Authlete.
     * This property holds the consented claims. See the description of
     * {@link AuthorizationIssueRequest#setConsentedClaims(String[])} for
     * details.
     * </p>
     *
     * @param claims
     *         Consented claims.
     *
     * @see AuthorizationIssueRequest#setConsentedClaims(String[])
     * @see BackchannelAuthenticationCompleteRequest#setConsentedClaims(String[])
     * @see DeviceCompleteRequest#setConsentedClaims(String[])
     *
     * @since 3.7
     */
    public void setConsentedClaims(String[] claims)
    {
        this.consentedClaims = claims;
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
     * @since 2.88
     */
    public void setServiceAttributes(Pair[] attributes)
    {
        this.serviceAttributes = attributes;
    }


    /**
     * Get the attributes of the client that the access token has been issued to.
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
     * Set the attributes of the client that the access token has been issued to.
     *
     * <p>
     * This property is available since Authlete 2.2.
     * </p>
     *
     * @param attributes
     *         The attributes of the client.
     *
     * @since 2.88
     */
    public void setClientAttributes(Pair[] attributes)
    {
        this.clientAttributes = attributes;
    }


    /**
     * Get the flag which indicates whether the token is for an external attachment.
     *
     * <p>
     * OpenID Provider implementations can make Authlete generate access tokens
     * for external attachments and embed them in ID tokens and userinfo responses
     * by setting true to the {@code accessTokenForExternalAttachmentEmbedded}
     * property of the service. If the token presented at Authlete's
     * {@code /api/auth/introspection} API has been generated by the feature,
     * this {@code forExternalAttachment} property in the response from the
     * Authlete API becomes true. See the description of
     * {@link Service#isAccessTokenForExternalAttachmentEmbedded()} for details
     * about the feature.
     * </p>
     *
     * @return
     *         {@code true} if the token is for an external attachment.
     *
     * @since 3.16
     *
     * @see Service#isAccessTokenForExternalAttachmentEmbedded()
     */
    public boolean isForExternalAttachment()
    {
        return forExternalAttachment;
    }


    /**
     * Set the flag which indicates whether the token is for an external attachment.
     *
     * <p>
     * OpenID Provider implementations can make Authlete generate access tokens
     * for external attachments and embed them in ID tokens and userinfo responses
     * by setting true to the {@code accessTokenForExternalAttachmentEmbedded}
     * property of the service. If the token presented at Authlete's
     * {@code /api/auth/introspection} API has been generated by the feature,
     * this {@code forExternalAttachment} property in the response from the
     * Authlete API becomes true. See the description of
     * {@link Service#isAccessTokenForExternalAttachmentEmbedded()} for details
     * about the feature.
     * </p>
     *
     * @param forExternalAttachment
     *         {@code true} to indicate that the token is for an external attachment.
     *
     * @since 3.16
     *
     * @see Service#isAccessTokenForExternalAttachmentEmbedded()
     */
    public void setForExternalAttachment(boolean forExternalAttachment)
    {
        this.forExternalAttachment = forExternalAttachment;
    }


    /**
     * Get the Authentication Context Class Reference of the user authentication
     * that the authorization server performed during the course of issuing the
     * access token.
     *
     * @return
     *         The Authentication Context Class Reference.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-step-up-authn-challenge/"
     *      >OAuth 2.0 Step-up Authentication Challenge Protocol</a>
     */
    public String getAcr()
    {
        return acr;
    }


    /**
     * Set the Authentication Context Class Reference of the user authentication
     * that the authorization server performed during the course of issuing the
     * access token.
     *
     * @param acr
     *         The Authentication Context Class Reference.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-step-up-authn-challenge/"
     *      >OAuth 2.0 Step-up Authentication Challenge Protocol</a>
     */
    public void setAcr(String acr)
    {
        this.acr = acr;
    }


    /**
     * Get the time when the user authentication was performed during the course
     * of issuing the access token.
     *
     * @return
     *         The time of the user authentication in seconds since the Unix epoch.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-step-up-authn-challenge/"
     *      >OAuth 2.0 Step-up Authentication Challenge Protocol</a>
     */
    public long getAuthTime()
    {
        return authTime;
    }


    /**
     * Set the time when the user authentication was performed during the course
     * of issuing the access token.
     *
     * @param authTime
     *         The time of the user authentication in seconds since the Unix epoch.
     *
     * @since 3.40
     * @since Authlete 2.3
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-step-up-authn-challenge/"
     *      >OAuth 2.0 Step-up Authentication Challenge Protocol</a>
     */
    public void setAuthTime(long authTime)
    {
        this.authTime = authTime;
    }


    /**
     * Get the grant type that was used for issuance of the access token.
     *
     * @return
     *         The grant type.
     *
     * @since 3.41
     * @since Authlete 2.1.24
     * @since Authlete 2.2.36
     * @since Authlete 2.3
     */
    public GrantType getGrantType()
    {
        return grantType;
    }


    /**
     * Set the grant type that was used for issuance of the access token.
     *
     * @param grantType
     *         The grant type.
     *
     * @since 3.41
     * @since Authlete 2.1.24
     * @since Authlete 2.2.36
     * @since Authlete 2.3
     */
    public void setGrantType(GrantType grantType)
    {
        this.grantType = grantType;
    }


    /**
     * Get the flag indicating whether the token is for credential issuance.
     *
     * <p>
     * <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credential Issuance</a> defines flows to issue
     * Verifiable Credentials. In the flows, a wallet presents an access token
     * at the credential endpoint of a credential issuer and gets a Verifiable
     * Credential in return.
     * </p>
     *
     * <p>
     * To get an access token for the purpose, a client application uses either
     * the pre-authorized code flow or the authorization code flow with the
     * {@code authorization_details} request parameter whose {@code type} is
     * {@code openid_credential}.
     * </p>
     *
     * <p>
     * For access tokens obtained by the flows described above, this
     * {@code forCredentialIssuance} flag is set.
     * </p>
     *
     * @return
     *         {@code true} if the token is for credential issuance.
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     *
     * @since 3.62
     * @since Authlete 3.0
     */
    public boolean isForCredentialIssuance()
    {
        return forCredentialIssuance;
    }


    /**
     * Set the flag indicating whether the token is for credential issuance.
     *
     * <p>
     * <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credential Issuance</a> defines flows to issue
     * Verifiable Credentials. In the flows, a wallet presents an access token
     * at the credential endpoint of a credential issuer and gets a Verifiable
     * Credential in return.
     * </p>
     *
     * <p>
     * To get an access token for the purpose, a client application uses either
     * the pre-authorized code flow or the authorization code flow with the
     * {@code authorization_details} request parameter whose {@code type} is
     * {@code openid_credential}.
     * </p>
     *
     * <p>
     * For access tokens obtained by the flows described above, this
     * {@code forCredentialIssuance} flag should be set.
     * </p>
     *
     * @param forCredentialIssuance
     *         {@code true} to indicate that the token is for credential issuance.
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     *
     * @since 3.62
     * @since Authlete 3.0
     */
    public void setForCredentialIssuance(boolean forCredentialIssuance)
    {
        this.forCredentialIssuance = forCredentialIssuance;
    }


    /**
     * Get the credentials that the credential issuer can offer when the token
     * is presented at the credential endpoint.
     *
     * <p>
     * The value of this property corresponds to (1) the {@code credentials}
     * property in the credential offer based on which the token was issued or
     * (2) the content of the {@code authorization_details} element (whose type
     * is {@code openid_credential}) that was included in the authorization
     * request based on which the token was issued.
     * </p>
     *
     * @return
     *         The credentials that the credential issuer can offer when the
     *         token is presented at the credential endpoint. The format is
     *         a JSON array.
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     *
     * @since 3.62
     * @since Authlete 3.0
     */
    public String getCredentials()
    {
        return credentials;
    }


    /**
     * Set the credentials that the credential issuer can offer when the token
     * is presented at the credential endpoint.
     *
     * <p>
     * The value of this property corresponds to (1) the {@code credentials}
     * property in the credential offer based on which the token was issued or
     * (2) the content of the {@code authorization_details} element (whose type
     * is {@code openid_credential}) that was included in the authorization
     * request based on which the token was issued.
     * </p>
     *
     * @param credentials
     *         The credentials that the credential issuer can offer when the
     *         token is presented at the credential endpoint. The format is
     *         a JSON array.
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credential Issuance</a>
     *
     * @since 3.62
     * @since Authlete 3.0
     */
    public void setCredentials(String credentials)
    {
        this.credentials = credentials;
    }


    /**
     * Get the {@code c_nonce} associated with the access token.
     *
     * <p>
     * {@code c_nonce} is issued from the token endpoint of an authorization
     * server in the pre-authorized code flow, and from the credential endpoint
     * and the batch credential endpoint of a credential issuer.
     * </p>
     *
     * <p>
     * In Authlete's implementation, {@code c_nonce} is managed in an access
     * token record. Therefore, when the database record is deleted, the
     * {@code c_nonce} is deleted together.
     * </p>
     *
     * <p>
     * See <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credentials Issuance</a> for details about {@code
     * c_nonce}.
     * </p>
     *
     * @return
     *         The {@code c_nonce}.
     *
     * @since 3.63
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public String getCNonce()
    {
        return cNonce;
    }


    /**
     * Set the {@code c_nonce} associated with the access token.
     *
     * <p>
     * {@code c_nonce} is issued from the token endpoint of an authorization
     * server in the pre-authorized code flow, and from the credential endpoint
     * and the batch credential endpoint of a credential issuer.
     * </p>
     *
     * <p>
     * In Authlete's implementation, {@code c_nonce} is managed in an access
     * token record. Therefore, when the database record is deleted, the
     * {@code c_nonce} is deleted together.
     * </p>
     *
     * <p>
     * See <a href=
     * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     * >OpenID for Verifiable Credentials Issuance</a> for details about {@code
     * c_nonce}.
     * </p>
     *
     * @param nonce
     *         The {@code c_nonce}.
     *
     * @since 3.63
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public void setCNonce(String nonce)
    {
        this.cNonce = nonce;
    }


    /**
     * Get the time at which the {@code c_nonce} expires in milliseconds since
     * the Unix epoch (1970-01-01).
     *
     * @return
     *         The time at which the {@code c_nonce} expires in milliseconds
     *         since the Unix epoch (1970-01-01).
     *
     * @since 3.63
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public long getCNonceExpiresAt()
    {
        return cNonceExpiresAt;
    }


    /**
     * Set the time at which the {@code c_nonce} expires in milliseconds since
     * the Unix epoch (1970-01-01).
     *
     * @param expiresAt
     *         The time at which the {@code c_nonce} expires in milliseconds
     *         since the Unix epoch (1970-01-01).
     *
     * @since 3.63
     * @since Authlete 3.0
     *
     * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
     *      >OpenID for Verifiable Credentials Issuance</a>
     */
    public void setCNonceExpiresAt(long expiresAt)
    {
        this.cNonceExpiresAt = expiresAt;
    }
}
