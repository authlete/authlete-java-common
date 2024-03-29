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


import static com.authlete.common.types.HashAlg.SHA_256;
import static com.authlete.common.types.HashAlg.SHA_384;
import static com.authlete.common.types.HashAlg.SHA_512;
import java.util.EnumSet;


/**
 * {@code "alg"} (Algorithm) Header Parameter Values for JWS.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7518#section-3.1"
 *      >RFC 7518, 3.1. "alg" (Algorithm) Header Parameter Values for JWS</a>
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
     *
     * @since 2.75
     */
    PS512((short)12, "PS512", SHA_512),


    /**
     * {@code "ES256K"} (13); ECDSA using secp256k1 curve and SHA-256.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8812.html"
     *      >RFC 8812 CBOR Object Signing and Encryption (COSE) and JSON Object
     *       Signing and Encryption (JOSE) Registrations for Web Authentication
     *       (WebAuthn) Algorithms</a>
     *
     * @since 2.75
     */
    ES256K((short)13, "ES256K", SHA_256),


    /**
     * {@code "EdDSA"} (14); EdDSA signature algorithms.
     *
     * <p>
     * Note: SHA512 is used as has as per the Edwards algorithm in <a href=
     * "https://tools.ietf.org/html/rfc8032">RFC 8032 : Edwards-Curve Digital
     * Signature Algorithm (EdDSA)</a>.
     * </p>
     *
     * @see <a href="https://tools.ietf.org/html/rfc8037">RFC 8037 :
     *      CFRG Elliptic Curve Diffie-Hellman (ECDH) and Signatures
     *      in JSON Object Signing and Encryption (JOSE)</a>
     *
     * @since 2.75
     */
    EdDSA((short)14, "EdDSA", SHA_512),
    ;


    private static final JWSAlg[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
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
     * Get the name of this algorithm.
     *
     * One of the values listed in the table in
     * <a href="https://tools.ietf.org/html/rfc7518#section-3.1">3.1.
     * "alg" (Algorithm) Header Parameter Values for JWS</a> of
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
        if (value < 0 || sValues.length <= value)
        {
            // Not found.
            return null;
        }

        return sValues[value];
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

        for (JWSAlg entry : sValues)
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
        return sHelper.toBits(set);
    }


    public static JWSAlg[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<JWSAlg> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<JWSAlg> toSet(JWSAlg[] array)
    {
        return sHelper.toSet(array);
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


    /**
     * Check if the given JWS algorithm is a symmetric one.
     *
     * @param alg
     *         JWS algorithm.
     *
     * @return
     *         {@code true} if the given JWS algorithm is symmetric.
     *         To be concrete, {@code true} is returned when {@code alg}
     *         is {@link #HS256}, {@link #HS384} or {@link #HS512}.
     *
     * @since 2.12
     */
    public static boolean isSymmetric(JWSAlg alg)
    {
        if (alg == null)
        {
            return false;
        }

        switch (alg)
        {
            case HS256:
            case HS384:
            case HS512:
                return true;

            default:
                return false;
        }
    }


    /**
     * Check if the given JWS algorithm is an asymmetric one.
     *
     * @param alg
     *         JWS algorithm.
     *
     * @return
     *         {@code true} if the given JWS algorithm is asymmetric.
     *         To be concrete, {@code true} is returned when {@code alg} is
     *         {@link #RS256}, {@link #RS384}, {@link #RS512},
     *         {@link #PS256}, {@link #PS384}, {@link #PS512},
     *         {@link #ES256}, {@link #ES384} or {@link #ES512}.
     *
     * @since 2.12
     */
    public static boolean isAsymmetric(JWSAlg alg)
    {
        if (alg == null)
        {
            return false;
        }

        switch (alg)
        {
            case RS256:
            case RS384:
            case RS512:
            case PS256:
            case PS384:
            case PS512:
            case ES256:
            case ES384:
            case ES512:
            case ES256K:
            case EdDSA:
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
     *         is {@link #HS256}, {@link #HS384} or {@link #HS512}.
     *
     * @since 2.16
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
     *         {@link #RS256}, {@link #RS384}, {@link #RS512},
     *         {@link #PS256}, {@link #PS384}, {@link #PS512},
     *         {@link #ES256}, {@link #ES384} or {@link #ES512}.
     *
     * @since 2.16
     */
    public boolean isAsymmetric()
    {
        return isAsymmetric(this);
    }
}
