/*
 * Copyright (C) 2015-2021 Authlete, Inc.
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
import java.util.List;
import java.util.Map;
import com.authlete.common.util.Utils;


/**
 * Request to Authlete's {@code /auth/userinfo/issue} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>token</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The access token that has been passed to the service's <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo"
 * >userinfo endpoint</a> by the client application. In other words,
 * the access token which was contained in the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoRequest"
 * >userinfo request</a>.
 * </p>
 * </dd>
 *
 * <dt><b><code>claims</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Claims in JSON format. As for the format, see {@link #setClaims(String)}
 * and <i>"<a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
 * >OpenID Connect Core 1.0, 5.1. Standard Claims</a>"</i>.
 * </p>
 * </dd>
 *
 * <dt><b><code>sub</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code sub} claim. If the value of this request parameter
 * is not empty, it is used as the value of the 'sub' claim. Otherwise, the
 * value of the subject associated with the access token is used.
 * </p>
 * </dd>
 *
 * <dt><b><code>claimsForTx</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * Claim data that are referenced when Authlete computes values of
 * <i>transformed claims</i>. See the description of
 * {@link #setClaimsForTx(String)} for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>verifiedClaimsForTx</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * Verified claim data that are referenced when Authlete computes values of
 * <i>transformed claims</i>. See the description of
 * {@link #setVerifiedClaimsForTx(String[])} for details.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 */
public class UserInfoIssueRequest implements Serializable
{
    private static final long serialVersionUID = 6L;


    /**
     * The access token.
     */
    private String token;


    /**
     * Claims in JSON format.
     */
    private String claims;


    /**
     * The value of the 'sub' claim. If this field is empty, the value of
     * the subject that is associated with the access token is used as the
     * value of the 'sub' claim.
     */
    private String sub;


    /**
     * Claim key-value pairs that are used to compute transformed claims.
     */
    private String claimsForTx;


    /**
     * Verified claim key-value pairs that are used to compute transformed
     * claims.
     */
    private String[] verifiedClaimsForTx;


    /**
     * Get the access token which has come along with the userinfo
     * request from the client application.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token which has been issued by Authlete.
     * The access token is the one that has come along with the
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfoRequest"
     * >userinfo request</a> from the client application.
     */
    public UserInfoIssueRequest setToken(String token)
    {
        this.token = token;

        return this;
    }


    /**
     * Get the claims of the subject in JSON format.
     *
     * @return
     *         The claims of the subject in JSON format. See the description
     *         of {@link #setClaims(String)} for details about the format.
     *
     * @see #setClaims(String)
     */
    public String getClaims()
    {
        return claims;
    }


    /**
     * Set the claims of the subject in JSON format.
     *
     * <p>
     * The service implementation is required to retrieve claims of the subject
     * (= information about the end-user) from its database and format them in
     * JSON format.
     * </p>
     *
     * <p>
     * For example, if <code>"given_name"</code> claim, <code>"family_name"</code>
     * claim and <code>"email"</code> claim are requested, the service implementation
     * should generate a JSON object like the following:
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
     * and set its String representation by this method.
     * </p>
     *
     * <p>
     * See <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >OpenID Connect Core 1.0, 5.1. Standard Claims</a> for further details
     * about the format.
     * </p>
     *
     * @param claims
     *         The claims of the subject in JSON format.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     *      >OpenID Connect Core 1.0, 5.1. Standard Claims</a>
     */
    public UserInfoIssueRequest setClaims(String claims)
    {
        this.claims = claims;

        return this;
    }


    /**
     * Set the value of {@code "claims"} which is the claims of the subject.
     * The argument is converted into a JSON string and passed to {@link
     * #setClaims(String)} method.
     *
     * @param claims
     *         The claims of the subject. Keys are claim names.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.24
     */
    public UserInfoIssueRequest setClaims(Map<String, Object> claims)
    {
        if (claims == null || claims.size() == 0)
        {
            this.claims = null;
            return this;
        }

        String json = Utils.toJson(claims);

        return setClaims(json);
    }


    /**
     * Get the value of the {@code sub} claim. If this method returns a non-empty value,
     * it is used as the value of the 'sub' claim. Otherwise, the value of the subject
     * associated with the access token is used.
     *
     * @return
     *         The value of the {@code sub} claim.
     *
     * @since 1.35
     */
    public String getSub()
    {
        return sub;
    }


    /**
     * Set the value of the {@code sub} claim. If a non-empty value is given, it is
     * used as the value of the 'sub' claim. Otherwise, the value of the subject
     * associated with the access token is used.
     *
     * @param sub
     *         The value of the {@code sub} claim.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.35
     */
    public UserInfoIssueRequest setSub(String sub)
    {
        this.sub = sub;

        return this;
    }


    /**
     * Get values of claims requested indirectly by <i>"transformed claims"</i>.
     *
     * <p>
     * See the description of {@link #setClaimsForTx(String)} for details.
     * </p>
     *
     * @return
     *         Values of claims requested indirectly by <i>"transformed
     *         claims"</i>. The format is JSON.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see #setClaimsForTx(String)
     *
     * @since 3.8
     */
    public String getClaimsForTx()
    {
        return claimsForTx;
    }


    /**
     * Set values of claims requested indirectly by <i>"transformed claims"</i>.
     *
     * <p>
     * A client application may request <i>"transformed claims"</i>. Each of
     * transformed claims uses an existing claim as input. As a result, to
     * compute the value of a transformed claim, the value of the referenced
     * existing claim is needed. This {@code claimsForTx} request parameter
     * has to be used to provide values of existing claims for computation of
     * transformed claims.
     * </p>
     *
     * <p>
     * A response from the {@code /api/auth/userinfo} API may include the
     * {@code requestedClaimsForTx} response parameter which is a list of
     * claims that are referenced indirectly by transformed claims (cf.
     * {@link UserInfoResponse#getRequestedClaimsForTx()}). The authorization
     * server implementation should prepare values of the claims listed in
     * {@code requestedClaimsForTx} and pass them as the value of this {@code
     * claimsForTx} request parameter.
     * </p>
     *
     * <p>
     * The following is an example of the value of this request parameter.
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
     * This request parameter ({@code claimsForTx}) is recognized by Authlete
     * 2.3 onwards.
     * </p>
     *
     * @param claims
     *         Values of claims requested indirectly by <i>"transformed claims"</i>.
     *         The format is JSON.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see UserInfoResponse#getRequestedClaimsForTx()
     *
     * @since 3.8
     */
    public UserInfoIssueRequest setClaimsForTx(String claims)
    {
        this.claimsForTx = claims;

        return this;
    }


    /**
     * Set the value of {@code "claimsForTx"} which is the claims of the
     * subject. The argument is converted into a JSON string and passed
     * to {@link #setClaimsForTx(String)} method.
     *
     * @param claims
     *         The claims of the subject. Keys are claim names.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.9
     */
    public UserInfoIssueRequest setClaimsForTx(Map<String, Object> claims)
    {
        if (claims == null || claims.size() == 0)
        {
            return setClaimsForTx((String)null);
        }

        String json = Utils.toJson(claims);

        return setClaimsForTx(json);
    }


    /**
     * Get values of verified claims requested indirectly by
     * <i>"transformed claims"</i>.
     *
     * <p>
     * See the description of {@link #setVerifiedClaimsForTx(String[])}
     * for details.
     * </p>
     *
     * @return
     *         Values of verified claims requested indirectly by <i>"transformed
     *         claims"</i>. The format of elements in the array is JSON.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see #setVerifiedClaimsForTx(String[])
     *
     * @since 3.8
     */
    public String[] getVerifiedClaimsForTx()
    {
        return verifiedClaimsForTx;
    }


    /**
     * Set values of verified claims requested indirectly by
     * <i>"transformed claims"</i>.
     *
     * <p>
     * A client application may request <i>"transformed claims"</i>. Each of
     * transformed claims uses an existing claim as input. As a result, to
     * compute the value of a transformed claim, the value of the referenced
     * existing claim is needed. This {@code verifiedClaimsForTx} request
     * parameter has to be used to provide values of existing claims for
     * computation of transformed claims.
     * </p>
     *
     * <p>
     * A response from the {@code /api/auth/userinfo} API may include the
     * {@code requestedVerifiedClaimsForTx} response parameter which is a list
     * of verified claims that are referenced indirectly by transformed claims
     * (cf. {@link UserInfoResponse#getRequestedVerifiedClaimsForTx()}).
     * The authorization server implementation should prepare values of the
     * verified claims listed in {@code requestedVerifiedClaimsForTx} and pass
     * them as the value of this {@code verifiedClaimsForTx} request parameter.
     * </p>
     *
     * <p>
     * The following is an example of the value of this request parameter.
     * </p>
     *
     * <pre>
     * [
     *   "{\"birthdate\":\"1970-01-23\",\"nationalities\":[\"DEU\",\"USA\"]}"
     * ]
     * </pre>
     *
     * <p>
     * The reason that this {@code verifiedClaimsForTx} property is an array
     * is that the {@code "verified_claims"} property in the {@code claims}
     * request parameter of an authorization request can be an array like below.
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
     * For the example above, the value of this {@code verifiedClaimsForTx}
     * property should be an array of size 2 and look like below. The first
     * element is JSON including claims which have been verified under the
     * trust framework "gold", and the second element is JSON including
     * claims which have been verified under the trust framework "silver".
     * </p>
     *
     * <pre>
     * [
     *   "{\"birthdate\":\"1970-01-23\"}",
     *   "{\"nationalities\":[\"DEU\",\"USA\"]}"
     * ]
     * </pre>
     *
     * <p>
     * This request parameter ({@code verifiedClaimsForTx}) is recognized by
     * Authlete 2.3 onwards.
     * </p>
     *
     * @param claims
     *         Values of verified claims requested indirectly by
     *         <i>"transformed claims"</i>. The format of elements in the
     *         array is JSON.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *      >OpenID Connect for Identity Assurance 1.0</a>
     *
     * @see UserInfoResponse#getRequestedVerifiedClaimsForTx()
     *
     * @since 3.8
     */
    public UserInfoIssueRequest setVerifiedClaimsForTx(String[] claims)
    {
        this.verifiedClaimsForTx = claims;

        return this;
    }


    /**
     * Set the value of {@code "verifiedClaimsForTx"} which is the verified
     * claims of the subject. Each element in the given list is converted to
     * a JSON string and a newly created string array containing the converted
     * elements is passed to {@link #setVerifiedClaimsForTx(String[])}.
     *
     * @param list
     *         List of clusters of verified claims.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.9
     */
    public UserInfoIssueRequest setVerifiedClaimsForTx(List<Map<String, Object>> list)
    {
        if (list == null || list.size() == 0)
        {
            return setVerifiedClaimsForTx((String[])null);
        }

        int size = list.size();

        String[] array = new String[size];

        for (int i = 0; i < size; ++i)
        {
            array[i] = Utils.toJson(list.get(i));
        }

        return setVerifiedClaimsForTx(array);
    }
}
