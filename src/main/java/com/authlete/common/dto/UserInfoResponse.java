/*
 * Copyright (C) 2015-2023 Authlete, Inc.
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
import com.authlete.common.util.Utils;



/**
 * Response from Authlete's {@code /auth/userinfo} API.
 *
 * <p>
 * Authlete's {@code /auth/userinfo} API returns JSON which can be mapped
 * to this class. The service implementation should retrieve the value of
 * {@code "action"} from the response and take the following steps
 * according to the value.
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
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
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
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
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
 * that the access token does not exist, has expired, or is not associated
 * with any subject (= any user account).
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
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
 * that the access token does not include the {@code "openid"} scope.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Error Response</a> for details.
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
 * access token which the client application presented is valid. To be
 * concrete, it means that the access token exists, has not expired,
 * includes the {@code "openid"} scope, and is associated with a subject
 * (= a user account).
 * </p>
 *
 * <p>
 * What the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> of your service should do next is to collect information
 * about the subject (user) from your database. The value of the subject
 * can be obtained from {@link #getSubject()}, and the names of data, i.e.,
 * the claims names can be obtained from {@link #getClaims()}.
 * For example, if {@code getSubject()} returns {@code "joe123"} and {@code
 * getClaims()} returns {@code ["given_name", "email"]}, you need to extract
 * information about {@code joe123}'s given name and email from your
 * database.
 * </p>
 *
 * <p>
 * Then, call Authlete's {@code /auth/userinfo/issue} API with the collected
 * information and the access token in order to make Authlete generate a userinfo
 * response. See the descriptions of {@link com.authlete.common.dto.UserInfoIssueRequest
 * UserInfoIssueRequest} and {@link com.authlete.common.dto.UserInfoIssueResponse
 * UserInfoIssueResponse} for details about {@code /auth/userinfo/issue} API.
 * </p>
 *
 * <p>
 * If an error occurred during the above steps, generate an error response
 * to the client. The response should comply with RFC 6750. For example,
 * if the subject associated with the access token does not exist in your
 * database any longer, you may feel like generating a response like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * WWW-Authenticate: Bearer error="{@link com.authlete.common.types.ErrorCode#invalid_token
 * invalid_token}",
 *  error_description="The subject associated with the access token does not exist."
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 *
 * <p>
 * Also, an error might occur on database access. If you treat the error
 * as an internal server error, then the response would be like the following.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * WWW-Authenticate: Bearer error="{@link com.authlete.common.types.ErrorCode#server_error
 * server_error}",
 *  error_description="Failed to extract information about the subject from the database."
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 *
 * </dd>
 * </dl>
 *
 * <p>
 * Authlete 2.3 and newer version support <i>"Transformed Claims"</i>. An
 * authorization request may request <i>"transformed claims"</i>. A transformed
 * claim uses an existing claim as input. For example, an authorization server
 * may predefine a transformed claim named {@code 18_or_over} which uses the
 * {@code birthdate} claim as input. If a client application requests the
 * {@code 18_or_over} transformed claim, the authorization server needs to
 * prepare the value of the {@code birthdate} claim and passes it to Authlete's
 * {@code /api/auth/userinfo/issue} API so that Authlete can compute the value
 * of the {@code 18_or_over} transformed claim. See the descriptions of
 * {@link #getRequestedClaimsForTx()} and {@link #getRequestedVerifiedClaimsForTx()}
 * for details.
 * </p>
 *
 * @author Takahiko Kawasaki
 */
public class UserInfoResponse extends ApiResponse
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
         * The access token does not cover the required scopes. To be
         * concrete, the access token does not include the {@code
         * "openid"} scope. The service implementation should return
         * {@code "403 Forbidden"} to the client application.
         */
        FORBIDDEN,

        /**
         * The access token is valid. The service implementation should
         * collect information about requested claims and pass the
         * information to Authlete's {@code /auth/userinfo/issue}
         * endpoint in order to make Authlete generate an ID token.
         */
        OK
    }


    private static final String SUMMARY_FORMAT
        = "action=%s, clientId=%d, subject=%s, scopes=%s, claims=%s, "
        + "accessToken=%s, properties=%s, clientIdAlias=%s, clientIdAliasUsed=%s"
        ;


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
     * Claims that are requested by the client application.
     */
    private String[] claims;


    /**
     * The access token that came along with the userinfo request.
     */
    private String token;


    /**
     * Entity body of the response to the client.
     */
    private String responseContent;


    /**
     * Extra properties associated with the access token.
     */
    private Property[] properties;


    /**
     * The client ID alias when the authorization request for
     * the access token was made.
     */
    private String clientIdAlias;


    /**
     * Flag which indicates whether the client ID alias was used
     * when the authorization request for the access token was made.
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
     * Flag which indicates whether the entity ID of the client was used
     * when the request for the access token was made.
     *
     * @since 3.37
     * @since Authlete 2.3
     */
    private boolean clientEntityIdUsed;


    /**
     * "userinfo" in "claims" of an authorization request.
     */
    private String userInfoClaims;


    /**
     * "transformed_claims" in "claims" of an authorization request.
     */
    private String transformedClaims;


    /**
     * Claims that the user has consented for the client application to know.
     */
    private String[] consentedClaims;


    /**
     * Names of claims that will be referenced when transformed claims are
     * computed.
     */
    private String[] requestedClaimsForTx;


    /**
     * Names of verified claims that will be referenced when transformed claims
     * are computed.
     */
    private StringArray[] requestedVerifiedClaimsForTx;


    /**
     * The attributes of the service that the client belongs to.
     */
    private Pair[] serviceAttributes;


    /**
     * The attributes of the client that the access token has been issued to.
     */
    private Pair[] clientAttributes;


    /**
     * The expected nonce value for DPoP proof JWT, which should be used
     * as the value of the {@code DPoP-Nonce} HTTP header.
     *
     * @since 3.82
     * @since Authlete 3.0
     */
    private String dpopNonce;


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
     * Get the list of claims that the client application requests
     * to be embedded in the userinfo response. The value comes from
     * {@code "scope"} and {@code "claims"} request parameters of
     * the original authorization request.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     *      >OpenID Connect Core 1.0, 5.5. Requesting Claims using
     *      the "claims" Request Parameter</a>
     */
    public String[] getClaims()
    {
        return claims;
    }


    /**
     * Set the list of claims that the client application requests
     * to be embedded in the ID token.
     */
    public void setClaims(String[] claims)
    {
        this.claims = claims;
    }


    /**
     * Get the access token that came along with the userinfo request.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token that came along with the userinfo request.
     */
    public void setToken(String accessToken)
    {
        this.token = accessToken;
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
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
            action, clientId, subject,
            Utils.join(scopes, " "),
            Utils.join(claims, " "),
            token,
            Utils.stringifyProperties(properties),
            clientIdAlias,
            clientIdAliasUsed);
    }


    /**
     * Get the extra properties associated with the access token.
     *
     * @return
     *         Extra properties. This method returns {@code null} when
     *         no extra property is associated with the access token.
     *
     * @since 2.5
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
     * @since 2.5
     */
    public void setProperties(Property[] properties)
    {
        this.properties = properties;
    }


    /**
     * Get the client ID alias when the authorization request for the access
     * token was made. Note that this value may be different from the current
     * client ID alias.
     *
     * @return
     *         The client ID alias when the authorization request for the
     *         access token was made.
     *
     * @since 2.5
     */
    public String getClientIdAlias()
    {
        return clientIdAlias;
    }


    /**
     * Set the client ID alias when the authorization request for the access
     * token was made.
     *
     * @param alias
     *         The client ID alias.
     *
     * @since 2.5
     */
    public void setClientIdAlias(String alias)
    {
        this.clientIdAlias = alias;
    }


    /**
     * Get the flag which indicates whether the client ID alias was used
     * when the authorization request for the access token was made.
     *
     * @return
     *         {@code true} if the client ID alias was used when the
     *         authorization request for the access token was made.
     *
     * @since 2.5
     */
    public boolean isClientIdAliasUsed()
    {
        return clientIdAliasUsed;
    }


    /**
     * Set the flag which indicates whether the client ID alias was used
     * when the authorization request for the access token was made.
     *
     * @param used
     *         {@code true} if the client ID alias was used when the
     *         authorization request for the access token was made.
     *
     * @since 2.5
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
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
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
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @return
     *         {@code true} if the entity ID of the client was used when the
     *         request for the access token was made.
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
     * used when the request for the access token was made.
     *
     * <p>
     * "Entity ID" is a technical term defined in <a href=
     * "https://openid.net/specs/openid-federation-1_0.html">OpenID
     * Federation 1.0</a>.
     * </p>
     *
     * @param used
     *         {@code true} to indicate that the entity ID of the client was
     *         used when the request for the access token was made.
     *
     * @since 3.37
     * @since Authlete 2.3
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html"
     *      >OpenID Federation 1.0</a>
     */
    public void setClientEntityIdUsed(boolean used)
    {
        this.clientEntityIdUsed = used;
    }


    /**
     * Get the value of the {@code "userinfo"} property in the {@code "claims"}
     * request parameter or in the {@code "claims"} property in an authorization
     * request object.
     *
     * <p>
     * A client application may request certain claims be embedded in an ID
     * token or in a response from the UserInfo endpoint. There are several
     * ways. Including the {@code claims} request parameter and including the
     * {@code claims} property in a request object are such examples. In both
     * the cases, the value of the {@code claims} parameter/property is JSON.
     * Its format is described in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     * >5.5. Requesting Claims using the "claims" Request Parameter</a> of
     * <a href="https://openid.net/specs/openid-connect-core-1_0.html">OpenID
     * Connect Core 1.0</a>.
     * </p>
     *
     * <p>
     * The following is an excerpt from the specification. You can find
     * {@code "userinfo"} and {@code "id_token"} are top-level properties.
     * </p>
     *
     * <pre>
     * {
     *  "userinfo":
     *   {
     *    "given_name": {"essential": true},
     *    "nickname": null,
     *    "email": {"essential": true},
     *    "email_verified": {"essential": true},
     *    "picture": null,
     *    "http://example.info/claims/groups": null
     *  },
     * "id_token":
     *  {
     *   "auth_time": {"essential": true},
     *   "acr": {"values": ["urn:mace:incommon:iap:silver"] }
     *  }
     * }
     * </pre>
     *
     * <p>
     * This method ({@code getUserInfoClaims()}) returns the value of the
     * {@code "userinfo"} property in JSON format. For example, if the
     * JSON above is included in an authorization request, this method
     * returns JSON equivalent to the following.
     * </p>
     *
     * <pre>
     *   {
     *    "given_name": {"essential": true},
     *    "nickname": null,
     *    "email": {"essential": true},
     *    "email_verified": {"essential": true},
     *    "picture": null,
     *    "http://example.info/claims/groups": null
     *  }
     * </pre>
     *
     * <p>
     * Note that if a request object is given and it contains the
     * {@code claims} property and if the {@code claims} request
     * parameter is also given, this method returns the value in
     * the former.
     * </p>
     *
     * @return
     *         The value of the {@code "userinfo"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @since 2.64
     */
    public String getUserInfoClaims()
    {
        return userInfoClaims;
    }


    /**
     * Set the value of the {@code "userinfo"} property in the {@code "claims"}
     * request parameter or in the {@code "claims"} property in an authorization
     * request object.
     *
     * @param userInfoClaims
     *         The value of the {@code "userinfo"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @since 2.64
     */
    public void setUserInfoClaims(String userInfoClaims)
    {
        this.userInfoClaims = userInfoClaims;
    }


    /**
     * Get the value of the {@code "transformed_claims"} property in the
     * {@code "claims"} request parameter of an authorization request or
     * in the {@code "claims"} property in a request object.
     *
     * @return
     *         The value of the {@code "transformed_claims"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @since 3.8
     */
    public String getTransformedClaims()
    {
        return transformedClaims;
    }


    /**
     * Set the value of the {@code "transformed_claims"} property in the
     * {@code "claims"} request parameter of an authorization request or
     * in the {@code "claims"} property in a request object.
     *
     * @param transformedClaims
     *         The value of the {@code "transformed_claims"} property in the
     *         {@code "claims"} in JSON format.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @since 3.8
     */
    public void setTransformedClaims(String transformedClaims)
    {
        this.transformedClaims = transformedClaims;
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
     * Get names of claims that are requested indirectly by <i>"transformed
     * claims"</i>.
     *
     * <p>
     * A client application can request <i>"transformed claims"</i> by adding
     * names of transformed claims in the {@code claims} request parameter.
     * The following is an example of the {@code claims} request parameter
     * that requests a predefined transformed claim named {@code 18_or_over}
     * and a transformed claim named {@code nationality_usa} to be embedded
     * in the response from the userinfo endpoint.
     * </p>
     *
     * <pre>
     * {
     *   "transformed_claims": {
     *     "nationality_usa": {
     *       "claim": "nationalities",
     *       "fn": [
     *         [ "eq", "USA" ],
     *         "any"
     *       ]
     *     }
     *   },
     *   "userinfo": {
     *     "::18_or_over": null,
     *     ":nationality_usa": null
     *   }
     * }
     * </pre>
     *
     * <p>
     * The example above assumes that a transformed claim named {@code 18_or_over}
     * is predefined by the authorization server like below.
     * </p>
     *
     * <pre>
     * {
     *   "18_or_over": {
     *     "claim": "birthdate",
     *     "fn": [
     *       "years_ago",
     *       [ "gte", 18 ]
     *     ]
     *   }
     * }
     * </pre>
     *
     * <p>
     * In the example, the {@code nationalities} claim is requested indirectly
     * by the {@code nationality_usa} transformed claim. Likewise, the
     * {@code birthdate} claim is requested indirectly by the {@code 18_or_over}
     * transformed claim.
     * </p>
     *
     * <p>
     * When the {@code claims} request parameter of an authorization request is
     * like the example above, this {@code requestedClaimsForTx} property will
     * hold the following value.
     * </p>
     *
     * <pre>
     * [ "birthdate", "nationalities" ]
     * </pre>
     *
     * <p>
     * It is expected that the authorization server implementation prepares values
     * of the listed claims and passes them as the value of the {@code claimsForTx}
     * request parameter when it calls the {@code /api/auth/userinfo/issue} API
     * (cf. {@link UserInfoIssueRequest#setClaimsForTx(String)}). The following
     * is an example of the value of the {@code claimsForTx} request parameter.
     * </p>
     *
     * <pre>
     * {
     *   "birthdate": "1970-01-23",
     *   "nationalities": [ "DEU", "USA" ]
     * }
     * </pre>
     *
     * <p>
     * This {@code requestedClaimsForTx} property is available from Authlete 2.3
     * onwards.
     * </p>
     *
     * @return
     *         Names of claims that are requested indirectly by
     *         <i>"transformed claims"</i>
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @since 3.8
     */
    public String[] getRequestedClaimsForTx()
    {
        return requestedClaimsForTx;
    }


    /**
     * Set names of claims that are requested indirectly by <i>"transformed
     * claims"</i>.
     *
     * <p>
     * See the description of {@link #getRequestedClaimsForTx()} for details.
     * </p>
     *
     * @param claims
     *         Names of claims that are requested indirectly by
     *         <i>"transformed claims"</i>
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see #getRequestedClaimsForTx()
     *
     * @since 3.8
     */
    public void setRequestedClaimsForTx(String[] claims)
    {
        this.requestedClaimsForTx = claims;
    }


    /**
     * Get names of verified claims that are requested indirectly by
     * <i>"transformed claims"</i>.
     *
     * <p>
     * A client application can request <i>"transformed claims"</i> by adding
     * names of transformed claims in the {@code claims} request parameter.
     * The following is an example of the {@code claims} request parameter
     * that requests a predefined transformed claim named {@code 18_or_over}
     * and a transformed claim named {@code nationality_usa} to be embedded
     * in the response from the userinfo endpoint.
     * </p>
     *
     * <pre>
     * {
     *   "transformed_claims": {
     *     "nationality_usa": {
     *       "claim": "nationalities",
     *       "fn": [
     *         [ "eq", "USA" ],
     *         "any"
     *       ]
     *     }
     *   },
     *   "userinfo": {
     *     "verified_claims": {
     *       "verification": {
     *         "trust_framework": null
     *       },
     *       "claims": {
     *         "::18_or_over": null,
     *         ":nationality_usa": null
     *       }
     *     }
     *   }
     * }
     * </pre>
     *
     * <p>
     * The example above assumes that a transformed claim named {@code 18_or_over}
     * is predefined by the authorization server like below.
     * </p>
     *
     * <pre>
     * {
     *   "18_or_over": {
     *     "claim": "birthdate",
     *     "fn": [
     *       "years_ago",
     *       [ "gte", 18 ]
     *     ]
     *   }
     * }
     * </pre>
     *
     * <p>
     * In the example, the {@code nationalities} claim is requested indirectly
     * by the {@code nationality_usa} transformed claim. Likewise, the
     * {@code birthdate} claim is requested indirectly by the {@code 18_or_over}
     * transformed claim.
     * </p>
     *
     * <p>
     * When the {@code claims} request parameter of an authorization request is
     * like the example above, this {@code requestedVerifiedClaimsForTx} property
     * will hold the following value.
     * </p>
     *
     * <pre>
     * [
     *   { "array": [ "birthdate", "nationalities" ] }
     * ]
     * </pre>
     *
     * <p>
     * It is expected that the authorization server implementation prepares
     * values of the listed verified claims and passes them as the value of
     * the {@code verifiedClaimsForTx} request parameter when it calls the
     * {@code /api/auth/userinfo/issue} API (cf.
     * {@link UserInfoIssueRequest#setVerifiedClaimsForTx(String[])}).
     * The following is an example of the value of the
     * {@code verifiedClaimsForTx} request parameter.
     * </p>
     *
     * <pre>
     * [
     *   "{\"birthdate\":\"1970-01-23\",\"nationalities\":[\"DEU\",\"USA\"]}"
     * ]
     * </pre>
     *
     * <p>
     * The reason that this {@code requestedVerifiedClaimsForTx} property and
     * the {@code verifiedClaimsForTx} request parameter are arrays is that
     * the {@code "verified_claims"} property in the {@code claims} request
     * parameter can be an array like below.
     * </p>
     *
     * <pre>
     * {
     *   "transformed_claims": {
     *     "nationality_usa": {
     *       "claim": "nationalities",
     *       "fn": [
     *         [ "eq", "USA" ],
     *         "any"
     *       ]
     *     }
     *   },
     *   "userinfo": {
     *     "verified_claims": [
     *       {
     *         "verification": { "trust_framework": { "value": "gold" } },
     *         "claims": { "::18_or_above": null }
     *       },
     *       {
     *         "verification": { "trust_framework": { "value": "silver" } },
     *         "claims": { ":nationality_usa": null }
     *       }
     *     ]
     *   }
     * }
     * </pre>
     *
     * <p>
     * The order of elements in {@code requestedVerifiedClaimsForTx} matches
     * the order of elements in the {@code "verified_claims"} array.
     * </p>
     *
     * <p>
     * This {@code requestedVerifiedClaimsForTx} property is available from
     * Authlete 2.3 onwards.
     * </p>
     *
     * @return
     *         Names of verified claims that are requested indirectly by
     *         <i>"transformed claims"</i>
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @since 3.8
     */
    public StringArray[] getRequestedVerifiedClaimsForTx()
    {
        return requestedVerifiedClaimsForTx;
    }


    /**
     * Set names of verified claims that are requested indirectly by
     * <i>"transformed claims"</i>.
     *
     * <p>
     * See the description of {@link #getRequestedVerifiedClaimsForTx()} for
     * details.
     * </p>
     *
     * <p>
     * This {@code requestedVerifiedClaimsForTx} property is available from
     * Authlete 2.3 onwards.
     * </p>
     *
     * @param claimsArray
     *         Names of verified claims that are requested indirectly by
     *         <i>"transformed claims"</i>
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see #getRequestedVerifiedClaimsForTx()
     *
     * @since 3.8
     */
    public void setRequestedVerifiedClaimsForTx(StringArray[] claimsArray)
    {
        this.requestedVerifiedClaimsForTx = claimsArray;
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
     * Get the expected nonce value for DPoP proof JWT, which should be used
     * as the value of the {@code DPoP-Nonce} HTTP header.
     *
     * <p>
     * When this response parameter is not null, the implementation of the
     * userinfo endpoint should add the {@code DPoP-Nonce} HTTP header in the
     * response from the endpoint to the client application, using the value
     * of this response parameter as the value of the HTTP header.
     * </p>
     *
     * <pre>
     * DPoP-Nonce: (<i>The value of this {@code dpopNonce} response parameter</i>)
     * </pre>
     *
     * @return
     *         The expected nonce value for DPoP proof JWT.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getDpopNonce()
    {
        return dpopNonce;
    }


    /**
     * Set the expected nonce value for DPoP proof JWT, which should be used
     * as the value of the {@code DPoP-Nonce} HTTP header.
     *
     * <p>
     * When this response parameter is not null, the implementation of the
     * userinfo endpoint should add the {@code DPoP-Nonce} HTTP header in the
     * response from the endpoint to the client application, using the value
     * of this response parameter as the value of the HTTP header.
     * </p>
     *
     * <pre>
     * DPoP-Nonce: (<i>The value of this {@code dpopNonce} response parameter</i>)
     * </pre>
     *
     * @param dpopNonce
     *         The expected nonce value for DPoP proof JWT.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public void setDpopNonce(String dpopNonce)
    {
        this.dpopNonce = dpopNonce;
    }
}
