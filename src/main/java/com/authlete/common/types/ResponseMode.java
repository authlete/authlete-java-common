/*
 * Copyright (C) 2014 Authlete, Inc.
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
 * From <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html"
 * >OAuth 2.0 Multiple Response Type Encoding Practices</a>,
 * <a href="http://openid.net/specs/oauth-v2-multiple-response-types-1_0.html#ResponseModes"
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
 * From <a href="http://openid.net/specs/oauth-v2-form-post-response-mode-1_0.html"
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
 * @see <a href="http://openid.net/specs/oauth-v2-form-post-response-mode-1_0.html"
 *      >OAuth 2.0 Form Post Response Mode</a>
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
    FORM_POST((short)3, "form_post")
    ;


    private static final ResponseMode[] mValues = values();
    private static final Helper mHelper = new Helper(mValues);
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
        if (value < 1 || mValues.length < value)
        {
            // Not found.
            return null;
        }

        return mValues[value - 1];
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

        for (ResponseMode entry : values())
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
        return mHelper.toBits(set);
    }


    public static ResponseMode[] toArray(int bits)
    {
        return mHelper.toArray(bits);
    }


    public static EnumSet<ResponseMode> toSet(int bits)
    {
        return mHelper.toSet(bits);
    }


    public static EnumSet<ResponseMode> toSet(ResponseMode[] array)
    {
        return mHelper.toSet(array);
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
