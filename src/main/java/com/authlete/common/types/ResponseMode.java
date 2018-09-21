/*
 * Copyright (C) 2014-2018 Authlete, Inc.
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
 * Values for {@code response_mode}.
 *
 * <p>
 * From <a href="https://openid.net/specs/oauth-v2-multiple-response-types-1_0.html"
 * >OAuth 2.0 Multiple Response Type Encoding Practices</a>,
 * <a href="https://openid.net/specs/oauth-v2-multiple-response-types-1_0.html#ResponseModes"
 * >2.1. Response Modes</a>
 * </p>
 * <blockquote>
 * <dl>
 * <dt>{@code query}</dt>
 * <dd>
 * In this mode, Authorization Response parameters are encoded in
 * the query string added to the {@code redirect_uri} when redirecting
 * back to the Client.
 * <br/><br/>
 * </dd>
 * <dt>{@code fragment}</dt>
 * <dd>
 * In this mode, Authorization Response parameters are encoded in
 * the fragment added to the {@code redirect_uri} when redirecting
 * back to the Client.
 * <br/><br/>
 * </dd>
 * </dl>
 * </blockquote>
 * <br/>
 *
 * <p>
 * From <a href="https://openid.net/specs/oauth-v2-form-post-response-mode-1_0.html"
 * >OAuth 2.0 Form Post Response Mode</a>
 * </p>
 * <blockquote>
 * <dl>
 * <dt>{@code form_post}</dt>
 * <dd>
 * In this mode, Authorization Response parameters are encoded as HTML
 * form values that are auto-submitted in the User Agent, and thus are
 * transmitted via the HTTP {@code POST} method to the Client, with the
 * result parameters being encoded in the body using the {@code
 * application/x-www-form-urlencoded} format. The action attribute of
 * the form MUST be the Client's Redirection URI. The method of the
 * form attribute MUST be {@code POST}.
 * <br/><br/>
 * Any technique supported by the User Agent MAY be used to cause the
 * submission of the form, and any form content necessary to support
 * this MAY be included, such as submit controls and client-side
 * scripting commands. However, the Client MUST be able to process the
 * message without regard for the mechanism by which the form submission
 * was initiated.
 * </dd>
 * </dl>
 * </blockquote>
 * <br/>
 *
 * <p>
 * From <a href="https://openid.net/specs/openid-financial-api-jarm.html"
 * >Financial-grade API: JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
 * </p>
 * <blockquote>
 * <dl>
 * <dt>{@code jwt}</dt>
 * <dd>
 * In this mode, Authorization Response parameters are packed into a JWT
 * and the resultant JWT is embedded in the default place (the query part
 * of the redirect URI, the fragment part of the redirect URI, or the HTML
 * form in the response body).
 * </dd>
 * <dt>{@code query.jwt}</dt>
 * <dd>
 * In this mode, Authorization Response parameters are packed into a JWT
 * and the resultant JWT is embedded in the query part of the redirect URI.
 * </dd>
 * <dt>{@code fragment.jwt}</dt>
 * <dd>
 * In this mode, Authorization Response parameters are packed into a JWT
 * and the resultant JWT is embedded in the fragment part of the redirect URI.
 * </dd>
 * <dt>{@code form_post.jwt}</dt>
 * <dd>
 * In this mode, Authorization Response parameters are packed into a JWT
 * and the resultant JWT is embedded in the HTML form in the response body.
 * </dd>
 * </dl>
 * </blockquote>
 * <br/>
 *
 * @see <a href="https://openid.net/specs/oauth-v2-form-post-response-mode-1_0.html"
 *      >OAuth 2.0 Form Post Response Mode</a>
 *
 * @see <a href="https://openid.net/specs/openid-financial-api-jarm.html"
 *      >Financial-grade API: JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)</a>
 *
 * @author Takahiko Kawasaki
 */
public enum ResponseMode
{
    /**
     * {@code "query"} (1); {@code response_mode} to request authorization
     * response parameters be encoded in the query string.
     */
    QUERY((short)1, "query"),


    /**
     * {@code "fragment"} (2); {@code response_mode} to request authorization
     * response parameters be encoded in the fragment.
     */
    FRAGMENT((short)2, "fragment"),


    /**
     * {@code "form_post"} (3); {@code response_mode} to request authorization
     * response parameters be encoded as HTML form values.
     */
    FORM_POST((short)3, "form_post"),

    /**
     * {@code "jwt"} (4); {@code response_mode} to request authorization response
     * parameters be encoded as JWT and embedded in the default part. This is a
     * shortcut of <code>"<i>{default}</i>.jwt"</code>.
     *
     * @since 2.27
     */
    JWT((short)4, "jwt"),

    /**
     * {@code "query.jwt"} (5); {@code response_mode} to request authorization
     * response parameters be encoded as JWT and embedded in the query part of
     * the redirect URI.
     *
     * @since 2.27
     */
    QUERY_JWT((short)5, "query.jwt"),

    /**
     * {@code "fragment.jwt"} (6); {@code response_mode} to request authorization
     * response parameters be encoded as JWT and embedded in the fragment part of
     * the redirect URI.
     *
     * @since 2.27
     */
    FRAGMENT_JWT((short)6, "fragment.jwt"),

    /**
     * {@code "form_post.jwt"} (7); {@code response_mode} to request authorization
     * response parameters be encoded as JWT and embedded in the HTML form in the
     * response body.
     *
     * @since 2.27
     */
    FORM_POST_JWT((short)7, "form_post.jwt"),
    ;


    private static final ResponseMode[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private ResponseMode(short value, String string)
    {
        mValue  = value;
        mString = string;
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
     * Find an instance of this enum by a value.
     *
     * @param value
     *         The integer representation of the instance to find.
     *
     * @return
     *         An instance of this enum, or {@code null} if not found.
     */
    public static ResponseMode getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Check whether this response mode requires response parameters be
     * packed into a JWT.
     *
     * @return
     *         {@code true} if this response mode is one of {@link #JWT},
     *         {@link #QUERY_JWT}, {@link #FRAGMENT_JWT} and
     *         {@link #FORM_POST_JWT}.
     *
     * @since 2.27
     */
    public boolean isJwtRequired()
    {
        return (mValue & JWT.mValue) != 0;
    }


    /**
     * Check whether this response mode requires response parameters be
     * embedded in the query part of the redirect URI.
     *
     * @return
     *         {@code true} if this response mode is either {@link #QUERY} or
     *         {@link #QUERY_JWT}. Otherwise, {@code false}.
     *
     * @since 2.27
     */
    public boolean isQueryRequired()
    {
        return (mValue & 0x3) == QUERY.mValue;
    }


    /**
     * Check whether this response mode requires response parameters be
     * embedded in the fragment part of the redirect URI.
     *
     * @return
     *         {@code true} if this response mode is either {@link #FRAGMENT} or
     *         {@link #FRAGMENT_JWT}. Otherwise, {@code false}.
     *
     * @since 2.27
     */
    public boolean isFragmentRequired()
    {
        return (mValue & 0x3) == FRAGMENT.mValue;
    }


    /**
     * Check whether this response mode requires response parameters be
     * embedded in the HTML form in the response body.
     *
     * @return
     *         {@code true} if this response mode is either {@link #FORM_POST}
     *         or {@link #FORM_POST_JWT}.
     *
     * @since 2.27
     */
    public boolean isFormPostRequired()
    {
        return (mValue & 0x3) == FORM_POST.mValue;
    }


    /**
     * Convert {@code String} to {@code ResponseMode}.
     *
     * @param responseMode
     *         A response mode. For example, {@code "query"}.
     *
     * @return
     *         {@code ResponseMode} instance, or {@code null}.
     */
    public static ResponseMode parse(String responseMode)
    {
        if (responseMode == null)
        {
            return null;
        }

        for (ResponseMode entry : sValues)
        {
            if (entry.mString.equals(responseMode))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<ResponseMode> set)
    {
        return sHelper.toBits(set);
    }


    public static ResponseMode[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<ResponseMode> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<ResponseMode> toSet(ResponseMode[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<ResponseMode>
    {
        public Helper(ResponseMode[] values)
        {
            super(ResponseMode.class, values);
        }


        @Override
        protected short getValue(ResponseMode entry)
        {
            return entry.getValue();
        }


        @Override
        protected ResponseMode[] newArray(int size)
        {
            return new ResponseMode[size];
        }
    }
}
