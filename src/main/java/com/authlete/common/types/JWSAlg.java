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


import static com.authlete.common.types.HashAlg.SHA_256;
import static com.authlete.common.types.HashAlg.SHA_384;
import static com.authlete.common.types.HashAlg.SHA_512;
import java.util.EnumSet;


/**
 * {@code "alg"} (Algorithm) Header Parameter Values for JWS.
 *
 * @see <a href="http://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-26#section-3.1"
 *      >JSON Web Algorithms (JWA), 3.1. "alg" (Algorithm) Header Parameter Values for JWS</a>
 *
 * @author Takahiko Kawasaki
 */
public enum JWSAlg
{
    /**
     * {@code "none"} (0); No digital signature or MAC performed.
     */
    NONE((short)0, "none", null),


    /**
     * {@code "HS256"} (1); HMAC using SHA-256.
     */
    HS256((short)1, "HS256", SHA_256),


    /**
     * {@code "HS384"} (2); HMAC using SHA-384.
     */
    HS384((short)2, "HS384", SHA_384),


    /**
     * {@code "HS512"} (3); HMAC using SHA-512.
     */
    HS512((short)3, "HS512", SHA_512),


    /**
     * {@code "RS256"} (4); RSASSA-PKCS-v1_5 using SHA-256.
     */
    RS256((short)4, "RS256", SHA_256),


    /**
     * {@code "RS384"} (5); RSASSA-PKCS-v1_5 using SHA-384.
     */
    RS384((short)5, "RS384", SHA_384),


    /**
     * {@code "RS512"} (6); RSASSA-PKCS-v1_5 using SHA-512.
     */
    RS512((short)6, "RS512", SHA_512),


    /**
     * {@code "ES256"} (7); ECDSA using P-256 and SHA-256.
     */
    ES256((short)7, "ES256", SHA_256),


    /**
     * {@code "ES384"} (8); ECDSA using P-384 and SHA-384.
     */
    ES384((short)8, "ES384", SHA_384),


    /**
     * {@code "ES512"} (9); ECDSA using P-521 and SHA-512.
     */
    ES512((short)9, "ES512", SHA_512),


    /**
     * {@code "PS256"} (10); RSASSA-PSS using SHA-256 and MGF1 with SHA-256.
     */
    PS256((short)10, "PS256", SHA_256),


    /**
     * {@code "PS384"} (11); RSASSA-PSS using SHA-384 and MGF1 with SHA-384.
     */
    PS384((short)11, "PS384", SHA_384),


    /**
     * {@code "PS512"} (12); RSASSA-PSS using SHA-512 and MGF1 with SHA-512.
     */
    PS512((short)12, "PS512", SHA_512)
    ;


    private static final JWSAlg[] mValues = values();
    private static final Helper mHelper = new Helper(mValues);
    private final short mValue;
    private final String mString;
    private final HashAlg mHashAlg;


    private JWSAlg(short value, String string, HashAlg hashAlg)
    {
        mValue   = value;
        mString  = string;
        mHashAlg = hashAlg;
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
     * Get the hash algorithm used by this signature algorithm.
     *
     * @return
     *         The hash algorithm. {@link #NONE} returns {@code null}.
     */
    public HashAlg getHashAlg()
    {
        return mHashAlg;
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
    public static JWSAlg getByValue(short value)
    {
        if (value < 0 || mValues.length <= value)
        {
            // Not found.
            return null;
        }

        return mValues[value];
    }


    /**
     * Convert {@code String} to {@code JWSAlg}.
     *
     * @param alg
     *         Algorithm name. For example, {@code "HS256"}.
     *
     * @return
     *         {@code JWSAlg} instance, or {@code null}.
     */
    public static JWSAlg parse(String alg)
    {
        if (alg == null)
        {
            return null;
        }

        for (JWSAlg entry : values())
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


    public static int toBits(EnumSet<JWSAlg> set)
    {
        return mHelper.toBits(set);
    }


    public static JWSAlg[] toArray(int bits)
    {
        return mHelper.toArray(bits);
    }


    public static EnumSet<JWSAlg> toSet(int bits)
    {
        return mHelper.toSet(bits);
    }


    public static EnumSet<JWSAlg> toSet(JWSAlg[] array)
    {
        return mHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<JWSAlg>
    {
        public Helper(JWSAlg[] values)
        {
            super(JWSAlg.class, values);
        }


        @Override
        protected short getValue(JWSAlg entry)
        {
            return entry.getValue();
        }


        @Override
        protected JWSAlg[] newArray(int size)
        {
            return new JWSAlg[size];
        }
    }
}
