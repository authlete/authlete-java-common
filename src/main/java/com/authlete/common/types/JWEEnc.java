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
 * {@code "enc"} (Encryption Algorithm) Header Parameter Values for JWE.
 *
 * @see <a href="http://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-26#section-5.1"
 *      >JSON Web Algorithms (JWA), 5.1. "enc" (Encryption Algorithm) Header Parameter Values for JWE</a>
 *
 * @author Takahiko Kawasaki
 */
public enum JWEEnc
{
    /**
     * {@code "A128CBC-HS256"} (1); AES_128_CBC_HMAC_SHA_256 authenticated
     * encryption algorithm as defined in
     * <a href="http://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-26#section-5.2.3"
     * >Section 5&#46;2&#46;3</a>.
     */
    A128CBC_HS256((short)1, "A128CBC-HS256"),


    /**
     * {@code "A192CBC-HS384"} (2); AES_192_CBC_HMAC_SHA_384 authenticated
     * encryption algorithm as defined in
     * <a href="http://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-26#section-5.2.4"
     * >Section 5&#46;2&#46;4</a>.
     */
    A192CBC_HS384((short)2, "A192CBC-HS384"),


    /**
     * {@code "A256CBC-HS512"} (3); AES_256_CBC_HMAC_SHA_512 authenticated
     * encryption algorithm as defined in
     * <a href="http://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-26#section-5.2.5"
     * >Section 5&#46;2&#46;5</a>.
     */
    A256CBC_HS512((short)3, "A256CBC-HS512"),


    /**
     * {@code "A128GCM"} (4); AES GCM using 128 bit key.
     */
    A128GCM((short)4, "A128GCM"),


    /**
     * {@code "A192GCM"} (5); AES GCM using 192 bit key.
     */
    A192GCM((short)5, "A192GCM"),


    /**
     * {@code "A256GCM"} (6); AES GCM using 256 bit key.
     */
    A256GCM((short)6, "A256GCM")
    ;


    private static final JWEEnc[] mValues = values();
    private static final Helper mHelper = new Helper(mValues);
    private final short mValue;
    private final String mString;


    private JWEEnc(short value, String string)
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
    public static JWEEnc getByValue(short value)
    {
        if (value < 1 || mValues.length < value)
        {
            // Not found.
            return null;
        }

        return mValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code JWEEnc}.
     *
     * @param alg
     *         Algorithm name. For example, {@code "A128CBC-HS256"}.
     *
     * @return
     *         {@code JWEEnc} instance, or {@code null}.
     */
    public static JWEEnc parse(String alg)
    {
        if (alg == null)
        {
            return null;
        }

        for (JWEEnc entry : values())
        {
            if (entry.mString.equals(alg))
            {
                // Found.
                return entry;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<JWEEnc> set)
    {
        return mHelper.toBits(set);
    }


    public static JWEEnc[] toArray(int bits)
    {
        return mHelper.toArray(bits);
    }


    public static EnumSet<JWEEnc> toSet(int bits)
    {
        return mHelper.toSet(bits);
    }


    public static EnumSet<JWEEnc> toSet(JWEEnc[] array)
    {
        return mHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<JWEEnc>
    {
        public Helper(JWEEnc[] values)
        {
            super(JWEEnc.class, values);
        }


        @Override
        protected short getValue(JWEEnc entry)
        {
            return entry.getValue();
        }


        @Override
        protected JWEEnc[] newArray(int size)
        {
            return new JWEEnc[size];
        }
    }
}
