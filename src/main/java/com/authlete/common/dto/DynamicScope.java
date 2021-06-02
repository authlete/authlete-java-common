/*
 * Copyright (C) 2021 Authlete, Inc.
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
import java.util.Objects;


/**
 * Dynamic Scope.
 *
 * <h3>Concept of Parameterized Scopes</h3>
 *
 * <p>
 * "Scope" is a technical term in the context of OAuth 2.0. It represents
 * a permission which is necessary to access protected resources.
 * </p>
 *
 * <p>
 * When a client application asks an authorization server to issue an access
 * token, the client application includes a list of scopes in the request.
 * If the specified scopes are supported by the server, the server issues an
 * access token that has the scopes.
 * </p>
 *
 * <p>
 * If unsupported scopes are included in a request, the server ignores such
 * scopes. As a result, the access token which will be issued based on the
 * request will include supported scopes only. For example, if a request
 * including "{@code scope1}" and "{@code scope2}" is sent to a server that
 * supports "{@code scope1}" only, the issued access token will include
 * "{@code scope1}" only.
 * </p>
 *
 * <p>
 * If a server supports <a href=
 * "https://openid.net/specs/openid-connect-discovery-1_0.html">OpenID Connect
 * Discovery 1.0</a> or <a href="https://www.rfc-editor.org/rfc/rfc8414.html"
 * >[RFC 8414] OAuth 2.0 Authorization Server Metadata</a>, it is highly likely
 * that the discovery document advertised by the server includes the
 * "{@code scopes_supported}" server metadata which is a list of scopes
 * supported by the server.
 * </p>
 *
 * <p>
 * <a href="https://www.rfc-editor.org/rfc/rfc6749.html">[RFC 6749] The OAuth
 * 2.0 Authorization Framework</a>, the core specification of OAuth 2.0,
 * restricts the range of characters which are allowed to be used in scope
 * strings. To be concrete, the range is printable ASCII letters except SPACE
 * (U+0020), QUOTATION MARK (U+0022), REVERSE SOLIDUS (U+005C) and DELETE
 * (U+007F).
 * </p>
 *
 * <p>
 * On the other hand, the specification does not define scope strings themselves
 * and leaves them to authorization server implementations. Some implementations
 * use simple English words such as "{@code publish_video}" and
 * "{@code read_insights}" (from Facebook <a href=
 * "https://developers.facebook.com/docs/permissions/reference/">Permissions
 * Reference</a>), others may use URIs such as
 * "{@code https://www.googleapis.com/auth/youtube}" (from Google <a href=
 * "https://developers.google.com/identity/protocols/oauth2/scopes">OAuth 2.0
 * Scopes for Google APIs</a>). Of course, other styles also may exist.
 * </p>
 *
 * <p>
 * And, in the history, some deployments invented their own local rules to
 * embed variable information into scope strings. For instance, like
 * "{@code payment:36fc67776}" where the part "{@code payment:}" is a fixed
 * text but the part "{@code 36fc67776}" is variable. This approach is often
 * called <i>"parameterized scopes"</i>.
 * </p>
 *
 * <p>
 * As you can easily imagine, rules for parameterized scopes can be invented
 * in various ways. A colon ({@code :}) is used as a delimiter in the example
 * above, but it does not necessarily have to be so. Another local rule may
 * introduce an asterisk ({@code *}) to make it represent all options in the
 * field like "{@code *:view}". Yet another rule may become more complex like
 * "{@code printer:print,manage:lp7200}".
 * </p>
 *
 * <p>
 * After years of experience and discussion, experts in the OAuth community
 * reached a consensus that "parameterized scopes" is not a good approach.
 * And the community has developed a new specification titled "<a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-rar/">OAuth 2.0 Rich
 * Authorization Requests</a>" (RAR). The specification introduces a new
 * parameter "{@code authorization_details}" by which client applications
 * can express variable information in a flexible way. Therefore, new
 * deployments should use RAR instead of inventing yet another rule for
 * parameterized scopes. Authlete 2.2 and onwards support RAR.
 * </p>
 *
 * <p>
 * However, it is not always possible to use RAR, and people have to use
 * parameterized scopes. For example, because <a href=
 * "https://openbanking-brasil.github.io/specs-seguranca/open-banking-brasil-financial-api-1_ID1.html"
 * >Open Banking Brasil Financial-grade API Security Profile 1.0
 * Implementers Draft 1</a> has introduced "Dynamic Consent Scope", financial
 * institutions in Brazil must support the dynamic scope, a kind of
 * parameterized scopes.
 * </p>
 *
 * <p>
 * A big problem here is that most vendor solutions for generic purposes do
 * not support local rules of parameterized scopes off the shelf. One of the
 * authors of the RAR specification mentioned the problem in his <a href=
 * "https://medium.com/oauth-2/transaction-authorization-or-why-we-need-to-re-think-oauth-scopes-2326e2038948"
 * >blog post</a> as follows.
 * </p>
 *
 * <blockquote>
 * </p><i>
 * Open Banking implementation experience has shown that this kind of
 * dynamically parameterized authorization process requires changes to most
 * existing OAuth implementations.
 * </i></p>
 * </blockquote>
 *
 * <p>
 * Vendors that want to enter local markets may fork their products to support
 * local rules of parameterized scopes, but forking is a burden on vendors.
 * </p>
 *
 * <h3>Authlete's Approach</h3>
 *
 * <p>
 * Authlete has a mechanism by which each scope can have scope attributes,
 * which are arbitrary key-value pairs. Developers can utilize scope attributes
 * for their own purposes but must keep in mind that some keys such as
 * "{@code fapi}" and "{@code access_token.duration}" have special meanings
 * in Authlete.
 * </p>
 *
 * <p>
 * To cover most possible patterns of local rules of parameterized scopes,
 * Authlete has defined a new key, "{@code regex}". The value of a "{@code
 * regex}" attribute should be a regular expression which matches a scope
 * string that may include dynamic values. Authlete uses regular expressions
 * defined by "{@code regex}" attributes to check whether requested scopes
 * are supported or not.
 * </p>
 *
 * <p>
 * For example, suppose that the server supports "{@code consent}" scope and
 * the scope has an "{@code regex}" attribute whose value is
 * "{@code ^consent:.+$}". In this case, the server accepts
 * "{@code consent:urn:bancoex:C1DD33123}" as a valid scope.
 * </p>
 *
 * <p>
 * Responses from some Authlete APIs (e.g. <a href=
 * "https://docs.authlete.com/#auth-authorization-api">/auth/authorization</a>
 * API) include information about scopes as an array of {@link Scope}.
 * However, dynamic scopes are not included in the array. Instead, they are
 * listed in a separate array named "{@code dynamicScopes}" whose elements
 * can be mapped to this class, {@link DynamicScope}.
 * </p>
 *
 * <p>
 * For example, if the value of the "{@code scope}" request parameter of an
 * authorization request is "{@code email consent:urn:bancoex:C1DD33123}",
 * the JSON response from the {@code /auth/authorization} API will include
 * the "{@code dynamicScopes}" array and the "{@code scopes}" array like below.
 * </p>
 *
 * <pre style="border: 1px solid black; padding: 1em; margin: 1em;">
 * "dynamicScopes": [
 *   {
 *     "name": "consent",
 *     "value": "consent:urn:bancoex:C1DD33123"
 *   }
 * ],
 * "scopes": [
 *   {
 *     "defaultEntry": false,
 *     "description": "(abbrev)",
 *     "descriptions": [
 *       {
 *         "tag": "en",
 *         "value": "(abbrev)"
 *       },
 *       {
 *         "tag": "ja",
 *         "value": "(abbrev)"
 *       }
 *     ],
 *     "name": "email"
 *   }
 * ],
 * </pre>
 *
 * <p>
 * Note that in the array of the "{@code dynamicScopes}" array, scope strings
 * specified in the "{@code scope}" request parameter are set in the
 * "{@code value}" field. On the other hand, the "{@code name}" field of
 * {@link DynamicScope} holds the name of the scope. The scope name will
 * appear in the discovery document like below.
 * </p>
 *
 * <pre style="border: 1px solid black; padding: 1em; margin: 1em;">
 * "scopes_supported": [
 *   "address",
 *   "email",
 *   "openid",
 *   "offline_access",
 *   "phone",
 *   "profile",
 *   "consent"
 * ],
 * </pre>
 *
 * <p>
 * This "<b>Dynamic Scope</b>" feature is available since Authlete 2.2.9.
 * </p>
 *
 * @since 2.92
 */
public class DynamicScope implements Serializable, Comparable<DynamicScope>
{
    private static final long serialVersionUID = 1L;


    /**
     * Scope name.
     */
    private String name;


    /**
     * Scope value.
     */
    private String value;


    /**
     * The default constructor.
     */
    public DynamicScope()
    {
    }


    /**
     * A constructor with a scope name and a scope value.
     *
     * @param name
     *         The scope name which is registered as one of supported scopes.
     *
     * @param value
     *         The scope value which was specified in the "{@code scope}"
     *         request parameter.
     */
    public DynamicScope(String name, String value)
    {
        this.name  = name;
        this.value = value;
    }


    /**
     * Get the scope name.
     *
     * @return
     *         The scope name which is registered as one of supported scopes.
     */
    public String getName()
    {
        return name;
    }


    /**
     * Set the scope name.
     *
     * @param name
     *         The scope name which is registered as one of supported scopes.
     *
     * @return
     *         {@code this} object.
     */
    public DynamicScope setName(String name)
    {
        this.name = name;

        return this;
    }


    /**
     * Get the scope value.
     *
     * @return
     *         The scope value which was specified in the "{@code scope}"
     *         request parameter.
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Set the scope value.
     *
     * @param value
     *         The scope value which was specified in the "{@code scope}"
     *         request parameter.
     *
     * @return
     *         {@code this} object.
     */
    public DynamicScope setValue(String value)
    {
        this.value = value;

        return this;
    }


    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof DynamicScope))
        {
            return false;
        }

        return compareTo((DynamicScope)other) == 0;
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(name, value);
    }


    @Override
    public int compareTo(DynamicScope other)
    {
        if (this == other)
        {
            return 0;
        }

        int result = compare(name, other.name);

        if (result != 0)
        {
            result = compare(value, other.value);
        }

        return result;
    }


    private static int compare(String a, String b)
    {
        if (a == null)
        {
            return (b == null) ? 0 : -1;
        }
        else
        {
            return (b == null) ? 1 : a.compareTo(b);
        }
    }
}
