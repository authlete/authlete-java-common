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
 * Values for {@code prompt}.
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
 *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
 *
 * @author Takahiko Kawasaki
 */
public enum Prompt
{
    /**
     * {@code "none"} (0).
     *
     * <p>
     * The Authorization Server MUST NOT display any authentication or
     * consent user interface pages. An error is returned if an End-User
     * is not already authenticated or the Client does not have
     * pre-configured consent for the requested Claims or does not
     * fulfill other conditions for processing the request. The error
     * code will typically be {@code login_required}, {@code
     * interaction_required}, or another code defined in
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthError"
     * >Section 3.1.2.6</a>. This can be used as a method to check for
     * existing authentication and/or consent.
     * </p>
     */
    NONE((short)0, "none"),


    /**
     * {@code "login"} (1).
     *
     * <p>
     * The Authorization Server SHOULD prompt the End-User for reauthentication.
     * If it cannot reauthenticate the End-User, it MUST return an error,
     * typically {@code login_required}.
     * </p>
     */
    LOGIN((short)1, "login"),


    /**
     * {@code "consent"} (2).
     *
     * <p>
     * The Authorization Server SHOULD prompt the End-User for consent
     * before returning information to the Client. If it cannot obtain
     * consent, it MUST return an error, typically {@code consent_required}.
     * </p>
     */
    CONSENT((short)2, "consent"),


    /**
     * {@code "select_account"} (3).
     *
     * <p>
     * The Authorization Server SHOULD prompt the End-User to select a
     * user account. This enables an End-User who has multiple accounts
     * at the Authorization Server to select amongst the multiple accounts
     * that they might have current sessions for. If it cannot obtain
     * an account selection choice made by the End-User, it MUST return
     * an error, typically {@code account_selection_required}.
     * </p>
     */
    SELECT_ACCOUNT((short)3, "select_account")
    ;


    private static final Prompt[] mValues = values();
    private static final Helper mHelper = new Helper(mValues);
    private final short mValue;
    private final String mString;


    private Prompt(short value, String string)
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
    public static Prompt getByValue(short value)
    {
        if (value < 0 || mValues.length <= value)
        {
            // Not found.
            return null;
        }

        return mValues[value];
    }


    /**
     * Convert {@code String} to {@code Prompt}.
     *
     * @param prompt
     *         A value of {@code prompt} parameter.
     *         For example, {@code "login"}.
     *
     * @return
     *         {@code Prompt} instance, or {@code null}.
     */
    public static Prompt parse(String prompt)
    {
        if (prompt == null)
        {
            return null;
        }

        for (Prompt value : values())
        {
            if (value.mString.equals(prompt))
            {
                // Found.
                return value;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<Prompt> set)
    {
        return mHelper.toBits(set);
    }


    public static Prompt[] toArray(int bits)
    {
        return mHelper.toArray(bits);
    }


    public static EnumSet<Prompt> toSet(int bits)
    {
        return mHelper.toSet(bits);
    }


    public static EnumSet<Prompt> toSet(Prompt[] array)
    {
        return mHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<Prompt>
    {
        public Helper(Prompt[] values)
        {
            super(Prompt.class, values);
        }


        @Override
        protected short getValue(Prompt entry)
        {
            return entry.getValue();
        }


        @Override
        protected Prompt[] newArray(int size)
        {
            return new Prompt[size];
        }
    }
}
