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
package com.authlete.common.types;


import java.util.EnumSet;


/**
 * Values for {@code prompt}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
 *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
 *
 * @see <a href="https://openid.net/specs/openid-connect-prompt-create-1_0.html"
 *      >Initiating User Registration via OpenID Connect 1.0</a>
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
    SELECT_ACCOUNT((short)3, "select_account"),


    /**
     * {@code "create"} (4).
     *
     * <p>
     * (Excerpted from the specification)<br>
     * A value of {@code create} indicates to the OpenID Provider that the
     * client desires that the user be shown the account creation UX rather
     * than the login flow. Care must be taken if combining this value with
     * other {@code prompt} values. Mutually exclusive conditions can arise
     * so it is RECOMMENDED that {@code create} not be combined with any
     * other values.
     * </p>
     *
     * @see <a href="https://openid.net/specs/openid-connect-prompt-create-1_0.html"
     *      >Initiating User Registration via OpenID Connect 1.0</a>
     *
     * @since 3.56
     */
    CREATE((short)4, "create"),
    ;


    private static final Prompt[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
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
        if (value < 0 || sValues.length <= value)
        {
            // Not found.
            return null;
        }

        return sValues[value];
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

        for (Prompt value : sValues)
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
        return sHelper.toBits(set);
    }


    public static Prompt[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<Prompt> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<Prompt> toSet(Prompt[] array)
    {
        return sHelper.toSet(array);
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
