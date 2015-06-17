/*
 * Copyright (C) 2014-2015 Authlete, Inc.
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
package com.authlete.common.types;


import java.util.EnumSet;


/**
 * Values for {@code response_type}.
 *
 * <p>
 * From <a href="http://tools.ietf.org/html/rfc6749">RFC 6749 (OAuth 2.0)</a>,
 * <a href="http://tools.ietf.org/html/rfc6749#section-3.1.1">3.1.1. Response Type</a>
 * </p>
 * <blockquote>
 * <dl>
 * <dt>{@code response_type}</dt>
 * <dd>
 * REQUIRED. The value MUST be one of <b>{@code "code"}</b> for requesting
 * an authorization code as described by
 * <a href="http://tools.ietf.org/html/rfc6749#section-4.1.1"
 * >Section 4.1.1</a>, <b>{@code "token"}</b> for requesting an access token
 * (implicit grant) as described by
 * <a href="http://tools.ietf.org/html/rfc6749#section-4.2.1"
 * >Section 4.2.1</a>, or a registered extension value as described by
 * <a href="http://tools.ietf.org/html/rfc6749#section-8.4"
 * >Section 8.4.</a>
 * </dd>
 * </dl>
 * </blockquote>
 * <br/>
 *
 * <p>
 * From <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html"
 * >OAuth 2.0 Multiple Response Type Encoding Practices</a>,
 * <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html#id_token"
 * >3. ID Token Response Type</a>
 * </p>
 * <blockquote>
 * <dl>
 * <dt>{@code id_token}</dt>
 * <dd>
 * When supplied as the {@code response_type} parameter in an OAuth 2.0
 * Authorization Request, a successful response MUST include the parameter
 * {@code id_token}. The Authorization Server SHOULD NOT return an OAuth
 * 2.0 Authorization Code, Access Token, or Access Token Type in a successful
 * response to the grant request. If a {@code redirect_uri} is supplied,
 * the User Agent SHOULD be redirected there after granting or denying
 * access. The request MAY include a {@code state} parameter, and if so,
 * the Authorization Server MUST echo its value as a response parameter
 * when issuing either a successful response or an error response. The
 * default Response Mode for this Response Type is the fragment encoding
 * and the query encoding MUST NOT be used. Both successful and error
 * responses SHOULD be returned using the supplied Response Mode, or if
 * none is supplied, using the default Response Mode.
 * </dd>
 * </dl>
 * </blockquote>
 * <br/>
 *
 * <p>
 * From <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html"
 * >OAuth 2.0 Multiple Response Type Encoding Practices</a>,
 * <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html#none"
 * >4. None Response Type</a>
 * </p>
 * <blockquote>
 * <dl>
 * <dt>{@code none}</dt>
 * <dd>
 * When supplied as the {@code response_type} parameter in an OAuth 2.0
 * Authorization Request, the Authorization Server SHOULD NOT return an
 * OAuth 2.0 Authorization Code, Access Token, Access Token Type, or ID
 * Token in a successful response to the grant request. If a {@code
 * redirect_uri} is supplied, the User Agent SHOULD be redirected there
 * after granting or denying access. The request MAY include a {@code
 * state} parameter, and if so, the Authorization Server MUST echo its
 * value as a response parameter when issuing either a successful
 * response or an error response. The default Response Mode for this
 * Response Type is the query encoding. Both successful and error
 * responses SHOULD be returned using the supplied Response Mode, or if
 * none is supplied, using the default Response Mode.
 * </dd>
 * </dl>
 * </blockquote>
 * <br/>
 *
 * <p>
 * From <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html"
 * >OAuth 2.0 Multiple Response Type Encoding Practices</a>,
 * <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html#Combinations"
 * >5. Definitions of Multiple-Valued Response Type Combinations</a>
 * </p>
 * <blockquote>
 * <dl>
 * <dt>{@code code token}</dt>
 * <dd>
 * When supplied as the value for the {@code response_type} parameter,
 * a successful response MUST include an Access Token, an Access Token
 * Type, and an Authorization Code. The default Response Mode for this
 * Response Type is the fragment encoding and the query encoding MUST
 * NOT be used. Both successful and error responses SHOULD be returned
 * using the supplied Response Mode, or if none is supplied, using the
 * default Response Mode.
 * <br/><br/>
 * </dd>
 * <dt>{@code code id_token}</dt>
 * <dd>
 * When supplied as the value for the {@code response_type} parameter,
 * a successful response MUST include both an Authorization Code and
 * an {@code id_token}. The default Response Mode for this Response
 * Type is the fragment encoding and the query encoding MUST NOT be
 * used. Both successful and error responses SHOULD be returned using
 * the supplied Response Mode, or if none is supplied, using the
 * default Response Mode.
 * <br/><br/>
 * </dd>
 * <dt>{@code id_token token}</dt>
 * <dd>
 * When supplied as the value for the {@code response_type} parameter,
 * a successful response MUST include an Access Token, an Access Token
 * Type, and an {@code id_token}. The default Response Mode for this
 * Response Type is the fragment encoding and the query encoding MUST
 * NOT be used. Both successful and error responses SHOULD be returned
 * using the supplied Response Mode, or if none is supplied, using the
 * default Response Mode.
 * <br/><br/>
 * </dd>
 * <dt>{@code code id_token token}</dt>
 * <dd>
 * When supplied as the value for the {@code response_type} parameter,
 * a successful response MUST include an Authorization Code, an {@code
 * id_token}, an Access Token, and an Access Token Type. The default
 * Response Mode for this Response Type is the fragment encoding and
 * the query encoding MUST NOT be used. Both successful and error
 * responses SHOULD be returned using the supplied Response Mode, or
 * if none is supplied, using the default Response Mode.
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.1"
 *      >RFC 6749 (OAuth 2.0), 3.1.1. Response Type</a>
 *
 * @see <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html"
 *      >OAuth 2.0 Multiple Response Type Encoding Practices</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ResponseType
{
    /**
     * {@code "none"} (0), a {@code response_type} to request no access credentials.
     */
    NONE((short)0, "none", 0x0),


    /**
     * {@code "code"} (1), a {@code response_type} to request an authorization code.
     */
    CODE((short)1, "code", 0x1),


    /**
     * {@code "token"} (2), a {@code response_type} to request an access token.
     */
    TOKEN((short)2, "token", 0x2),


    /**
     * {@code "id_token"} (3), a {@code response_type} to request an ID token.
     */
    ID_TOKEN((short)3, "id_token", 0x4),


    /**
     * {@code "code token"} (4), a {@code response_type} to request
     * an authorization code and an access token.
     */
    CODE_TOKEN((short)4, "code token", 0x3),


    /**
     * {@code "code id_token"} (5), a {@code response_type} to request
     * an authorization code and an ID token.
     */
    CODE_ID_TOKEN((short)5, "code id_token", 0x5),


    /**
     * {@code "id_token token"} (6), a {@code response_type} to request
     * an ID token and an access token.
     */
    ID_TOKEN_TOKEN((short)6, "id_token token", 0x6),


    /**
     * {@code "code id_token token"} (7), a {@code response_type} to request
     * an authorization code, an ID token and an access token.
     */
    CODE_ID_TOKEN_TOKEN((short)7, "code id_token token", 0x7)
    ;


    private static final int FLAG_CODE     = 0x1;
    private static final int FLAG_TOKEN    = 0x2;
    private static final int FLAG_ID_TOKEN = 0x4;


    private static final ResponseType[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;
    private final int mFlags;


    private ResponseType(short value, String string, int flags)
    {
        mValue  = value;
        mString = string;
        mFlags  = flags;
    }


    /**
     * Get the integer representation of this enum instance.
     */
    public short getValue()
    {
        return mValue;
    }


    @Override
    public String toString()
    {
        return mString;
    }


    /**
     * Check if this response type contains a request for
     * an authorization code.
     */
    public boolean containsCode()
    {
        return (mFlags & FLAG_CODE) != 0;
    }


    /**
     * Check if this response type contains a request for an ID token.
     */
    public boolean containsIdToken()
    {
        return (mFlags & FLAG_ID_TOKEN) != 0;
    }


    /**
     * Check if this response type contains a request for an access token.
     */
    public boolean containsToken()
    {
        return (mFlags & FLAG_TOKEN) != 0;
    }


    /**
     * Check if this response type requires the Implicit Flow. In other words,
     * check if this response type contains either {@code token} or {@code id_token}.
     *
     * @return
     *         {@code true} if this response type requires the Implicit Flow.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-1.3.2"
     *      >RFC 6749 (OAuth 2.0), 1.3.2. Implicit</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata"
     *      >OpenID Connect Dynamic Client Registration, 2. Client Metadata, grant_types</a>
     */
    public boolean requiresImplicitFlow()
    {
        // OpenID Connect Dynamic Client Registration, 2. Client Metadata
        //
        //   grant_types
        //     OPTIONAL. JSON array containing a list of the OAuth 2.0 Grant Types
        //     that the Client is declaring that it will restrict itself to using.
        //     The Grant Type values used by OpenID Connect are:
        //
        //       * authorization_code:
        //           The Authorization Code Grant Type described in OAuth 2.0 Section 4.1.
        //       * implicit:
        //           The Implicit Grant Type described in OAuth 2.0 Section 4.2.
        //       * refresh_token:
        //           The Refresh Token Grant Type described in OAuth 2.0 Section 6.
        //
        //     The following table lists the correspondence between response_type
        //     values that the Client will use and grant_type values that MUST be
        //     included in the registered grant_types list:
        //
        //       code: authorization_code
        //       id_token: implicit
        //       token id_token: implicit
        //       code id_token: authorization_code, implicit
        //       code token: authorization_code, implicit
        //       code token id_token: authorization_code, implicit
        //
        //     If omitted, the default is that the Client will use only the
        //     authorization_code Grant Type.

        // That is, if either 'token' or 'id_token' is contained, the response
        // type is 'implicit'.

        return (mFlags & (FLAG_TOKEN | FLAG_ID_TOKEN)) != 0;
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
    public static ResponseType getByValue(short value)
    {
        if (value < 0 || sValues.length <= value)
        {
            // Not found.
            return null;
        }

        return sValues[value];
    }


    /**
     * Parse a space-separated {@code response_type} values.
     *
     * @param responseType
     *         A response type. For example, {@code "code id_token"}.
     *
     * @return
     *         {@code ResponseType} instance, or {@code null}.
     */
    public static ResponseType parse(String responseType)
    {
        if (responseType == null)
        {
            return null;
        }

        // OAuth 2.0 Multiple Response Type Encoding Practices,
        // 4. None Response Type
        //
        //     The Response Type none SHOULD NOT be combined with other
        //     Response Types.
        //
        if (responseType.equals("none"))
        {
            // "none"
            return NONE;
        }

        // Split by white spaces to extract elements.
        String[] elements = responseType.split(" +");

        if (elements == null || elements.length == 0)
        {
            return null;
        }

        int flags = 0;

        // For each element. This implementation allows same values
        // to be included multiple times.
        for (String element : elements)
        {
            // If null or an empty string (this should not happen).
            if (element == null || element.length() == 0)
            {
                continue;
            }

            if (element.equals("code"))
            {
                flags |= FLAG_CODE;
            }
            else if (element.equals("token"))
            {
                flags |= FLAG_TOKEN;
            }

            else if (element.equals("id_token"))
            {
                flags |= FLAG_ID_TOKEN;
            }
            else
            {
                // Unknown name. Invalid.
                return null;
            }
        }

        switch (flags)
        {
            case FLAG_CODE:
                // "code"
                return CODE;

            case FLAG_TOKEN:
                // "token"
                return TOKEN;

            case FLAG_ID_TOKEN:
                // "id_token"
                return ID_TOKEN;

            case (FLAG_CODE | FLAG_TOKEN):
                // "code token"
                return CODE_TOKEN;

            case (FLAG_CODE | FLAG_ID_TOKEN):
                // "code id_token"
                return CODE_ID_TOKEN;

            case (FLAG_ID_TOKEN | FLAG_TOKEN):
                // "id_token token"
                return ID_TOKEN_TOKEN;

            case (FLAG_CODE | FLAG_ID_TOKEN | FLAG_TOKEN):
                // "code id_token token"
                return CODE_ID_TOKEN_TOKEN;

            default:
                return null;
        }
    }


    public static int toBits(EnumSet<ResponseType> set)
    {
        return sHelper.toBits(set);
    }


    public static ResponseType[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ResponseType> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ResponseType> toSet(ResponseType[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ResponseType>
    {
        public Helper(ResponseType[] values)
        {
            super(ResponseType.class, values);
        }


        @Override
        protected short getValue(ResponseType entry)
        {
            return entry.getValue();
        }


        @Override
        protected ResponseType[] newArray(int size)
        {
            return new ResponseType[size];
        }
    }
}
