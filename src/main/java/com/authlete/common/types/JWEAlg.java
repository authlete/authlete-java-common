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
 * {@code "alg"} (Algorithm) Header Parameter Values for JWE.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7518#section-4.1"
 *      >RFC 7518, 4.1. "alg" (Algorithm) Header Parameter Values for JWE</a>
 *
 * @author Takahiko Kawasaki
 */
public enum JWEAlg
{
    /**
     * {@code "RSA1_5"} (1); RSAES-PKCS1-V1_5.
     */
    RSA1_5((short)1, "RSA1_5"),


    /**
     * {@code "RSA-OAEP"} (2); RSAES OAEP using default parameters.
     */
    RSA_OAEP((short)2, "RSA-OAEP"),


    /**
     * {@code "RSA-OAEP-256"} (3); RSAES OAEP using SHA-256 and MGF1 with SHA-256.
     */
    RSA_OAEP_256((short)3, "RSA-OEAP-256"),


    /**
     * {@code "A128KW"} (4); AES Key Wrap with default initial value using 128 bit key.
     */
    A128KW((short)4, "A128KW"),


    /**
     * {@code "A192KW"} (5); AES Key Wrap with default initial value using 192 bit key.
     */
    A192KW((short)5, "A192KW"),


    /**
     * {@code "A256KW"} (6); AES Key Wrap with default initial value using 256 bit key.
     */
    A256KW((short)6, "A256KW"),


    /**
     * {@code "dir"} (7); Direct use of a shared symmetric key as the CEK.
     */
    DIR((short)7, "dir"),


    /**
     * {@code "ECDH-ES"} (8); Elliptic Curve Diffie-Hellman Ephemeral Static key agreement
     * using Concat KDF.
     */
    ECDH_ES((short)8, "ECDH-ES"),


    /**
     * {@code "ECDH-ES+A128KW"} (9); ECDH-ES using Concat KDF and CEK wrapped with "A128KW".
     */
    ECDH_ES_A128KW((short)9, "ECDH-ES+A128KW"),


    /**
     * {@code "ECDH-ES+A192KW"} (10); ECDH-ES using Concat KDF and CEK wrapped with "A192KW".
     */
    ECDH_ES_A192KW((short)10, "ECDH-ES+A192KW"),


    /**
     * {@code "ECDH-ES+A256KW"} (11); ECDH-ES using Concat KDF and CEK wrapped with "A256KW".
     */
    ECDH_ES_A256KW((short)11, "ECDH-ES+A256KW"),


    /**
     * {@code "A128GCMKW"} (12); Key wrapping with AES GCM using 128 bit key.
     */
    A128GCMKW((short)12, "A128GCMKW"),


    /**
     * {@code "A192GCMKW"} (13); Key wrapping with AES GCM using 192 bit key.
     */
    A192GCMKW((short)13, "A192GCMKW"),


    /**
     * {@code "A256GCMKW"} (14); Key wrapping with AES GCM using 256 bit key.
     */
    A256GCMKW((short)14, "A256GCMKW"),


    /**
     * {@code "PBES2-HS256+A128KW"} (15); PBES2 with HMAC SHA-256 and "A128KW".
     */
    PBES2_HS256_A128KW((short)15, "PBES2-HS256+A128KW"),


    /**
     * {@code "PBES2-HS384+A192KW"} (16); PBES2 with HMAC SHA-384 and "A192KW".
     */
    PBES2_HS384_A192KW((short)16, "PBES2-HS384+A192KW"),


    /**
     * {@code "PBES2-HS512+A256KW"} (17); PBES2 with HMAC SHA-512 and "A256KW".
     */
    PBES2_HS512_A256KW((short)17, "PBES2-HS512+A256KW")
    ;


    private static final JWEAlg[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private JWEAlg(short value, String string)
    {
        mValue  = value;
        mString = string;
    }


    /**
     * Get the name of this algorithm.
     *
     * One of the values listed in the table in
     * <a href="https://tools.ietf.org/html/rfc7518#section-4.1"
     * >4.1. "alg" (Algorithm) Header Parameter Values for JWE</a> of
     * <a href="https://tools.ietf.org/html/rfc7518">RFC 7518</a>
     * is returned.
     *
     * @return
     *         The name of this algorithm.
     *
     * @since 2.17
     */
    public String getName()
    {
        return mString;
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
    public static JWEAlg getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code JWEAlg}.
     *
     * @param alg
     *         Algorithm name. For example, {@code "RSA1_5"}.
     *
     * @return
     *         {@code JWEAlg} instance, or {@code null}.
     */
    public static JWEAlg parse(String alg)
    {
        if (alg == null)
        {
            return null;
        }

        for (JWEAlg entry : sValues)
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


    public static int toBits(EnumSet<JWEAlg> set)
    {
        return sHelper.toBits(set);
    }


    public static JWEAlg[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<JWEAlg> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<JWEAlg> toSet(JWEAlg[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<JWEAlg>
    {
        public Helper(JWEAlg[] values)
        {
            super(JWEAlg.class, values);
        }


        @Override
        protected short getValue(JWEAlg entry)
        {
            return entry.getValue();
        }


        @Override
        protected JWEAlg[] newArray(int size)
        {
            return new JWEAlg[size];
        }
    }


    /**
     * Check if the given JWE algorithm is a symmetric one.
     *
     * @param alg
     *         JWE algorithm.
     *
     * @return
     *         {@code true} if the given JWE algorithm is symmetric.
     *         To be concrete, {@code true} is returned when {@code alg} is
     *         {@link #A128KW}, {@link #A192KW}, {@link #A256KW},
     *         {@link #DIR}, {@link #A128GCMKW}, {@link #A192GCMKW},
     *         {@link #A256GCMKW}, {@link #PBES2_HS256_A128KW},
     *         {@link #PBES2_HS384_A192KW}, or {@link #PBES2_HS512_A256KW}.
     *
     * @since 2.28
     */
    public static boolean isSymmetric(JWEAlg alg)
    {
        if (alg == null)
        {
            return false;
        }

        switch (alg)
        {
            case A128KW:
            case A192KW:
            case A256KW:
            case DIR:
            case A128GCMKW:
            case A192GCMKW:
            case A256GCMKW:
            case PBES2_HS256_A128KW:
            case PBES2_HS384_A192KW:
            case PBES2_HS512_A256KW:
                return true;

            default:
                return false;
        }
    }


    /**
     * Check if the given JWE algorithm is an asymmetric one.
     *
     * @param alg
     *         JWE algorithm.
     *
     * @return
     *         {@code true} if the given JWE algorithm is asymmetric.
     *         To be concrete, {@code true} is returned when {@code alg} is
     *         {@link #RSA1_5}, {@link #RSA_OAEP}, {@link #RSA_OAEP_256},
     *         {@link #ECDH_ES}, {@link #ECDH_ES_A128KW},
     *         {@link #ECDH_ES_A192KW}, or {@link #ECDH_ES_A256KW}.
     *
     * @since 2.28
     */
    public static boolean isAsymmetric(JWEAlg alg)
    {
        if (alg == null)
        {
            return false;
        }

        switch (alg)
        {
            case RSA1_5:
            case RSA_OAEP:
            case RSA_OAEP_256:
            case ECDH_ES:
            case ECDH_ES_A128KW:
            case ECDH_ES_A192KW:
            case ECDH_ES_A256KW:
                return true;

            default:
                return false;
        }
    }


    /**
     * Check if this algorithm is a symmetric one.
     *
     * @return
     *         {@code true} if this algorithm is symmetric.
     *         To be concrete, {@code true} is returned when this is
     *         {@link #A128KW}, {@link #A192KW}, {@link #A256KW},
     *         {@link #DIR}, {@link #A128GCMKW}, {@link #A192GCMKW},
     *         {@link #A256GCMKW}, {@link #PBES2_HS256_A128KW},
     *         {@link #PBES2_HS384_A192KW}, or {@link #PBES2_HS512_A256KW}.
     *
     * @since 2.28
     */
    public boolean isSymmetric()
    {
        return isSymmetric(this);
    }


    /**
     * Check if this algorithm is an asymmetric one.
     *
     * @return
     *         {@code true} if this algorithm is asymmetric.
     *         To be concrete, {@code true} is returned when this is
     *         {@link #RSA1_5}, {@link #RSA_OAEP}, {@link #RSA_OAEP_256},
     *         {@link #ECDH_ES}, {@link #ECDH_ES_A128KW},
     *         {@link #ECDH_ES_A192KW}, or {@link #ECDH_ES_A256KW}.
     *
     * @since 2.28
     */
    public boolean isAsymmetric()
    {
        return isAsymmetric(this);
    }
}
