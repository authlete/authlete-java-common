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
 * Values for {@code display}.
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
 *      >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a>
 *
 * @author Takahiko Kawasaki
 */
public enum Display
{
    /**
     * {@code "page"} (1).
     *
     * <p>
     * The Authorization Server SHOULD display the authentication and
     * consent UI consistent with a full User Agent page view. If the
     * {@code display} parameter is not specified, this is the default
     * display mode.
     * </p>
     */
    PAGE((short)1, "page"),


    /**
     * {@code "popup"} (2).
     *
     * <p>
     * The Authorization Server SHOULD display the authentication and
     * consent UI consistent with a popup User Agent window. The popup
     * User Agent window should be of an appropriate size for a
     * login-focused dialog and should not obscure the entire window
     * that it is popping up over.
     * </p>
     */
    POPUP((short)2, "popup"),


    /**
     * {@code "touch"} (3).
     *
     * <p>
     * The Authorization Server SHOULD display the authentication and
     * consent UI consistent with a device that leverages a touch
     * interface.
     * </p>
     */
    TOUCH((short)3, "touch"),


    /**
     * {@code "wap"} (4).
     *
     * <p>
     * The Authorization Server SHOULD display the authentication and
     * consent UI consistent with a "feature phone" type display.
     * </p>
     */
    WAP((short)4, "wap"),
    ;


    private static final Display[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private Display(short value, String string)
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
    public static Display getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code Display}.
     *
     * @param display
     *         A value of {@code display} parameter.
     *         For example, {@code "page"}.
     *
     * @return
     *         {@code Display} instance, or {@code null}.
     */
    public static Display parse(String display)
    {
        if (display == null)
        {
            return null;
        }

        for (Display value : sValues)
        {
            if (value.mString.equals(display))
            {
                // Found.
                return value;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<Display> set)
    {
        return sHelper.toBits(set);
    }


    public static Display[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<Display> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<Display> toSet(Display[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<Display>
    {
        public Helper(Display[] values)
        {
            super(Display.class, values);
        }


        @Override
        protected short getValue(Display entry)
        {
            return entry.getValue();
        }


        @Override
        protected Display[] newArray(int size)
        {
            return new Display[size];
        }
    }
}
