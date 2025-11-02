/*
 * Copyright (C) 2025 Authlete, Inc.
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
 * {@code "zip"} (Compression Algorithm) Header Parameter Values for JWE.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7516.html#section-4.1.3">
 *      RFC 7516: JSON Web Encryption (JWE),
 *      Section 4.1.3. "zip" (Compression Algorithm) Header Parameter</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-7.3">
 *      RFC 7518: JSON Web Algorithms (JWA),
 *      Section 7.3. JSON Web Encryption Compression Algorithms Registry</a>
 *
 * @see <a href="https://www.iana.org/assignments/jose/jose.xhtml#web-encryption-compression-algorithms">
 *      IANA: JSON Object Signing and Encryption (JOSE),
 *      JSON Web Encryption Compression Algorithms</a>
 *
 * @since 4.26
 */
public enum JWEZip
{
    /**
     * {@code "DEF"} (1); DEFLATE.
     */
    DEF((short)1, "DEF"),
    ;


    private static final JWEZip[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private JWEZip(short value, String string)
    {
        mValue  = value;
        mString = string;
    }


    /**
     * Get the name of this algorithm.
     *
     * One of the values listed in the table in
     * <a href="https://www.iana.org/assignments/jose/jose.xhtml#web-encryption-compression-algorithms"
     * >JSON Web Encryption Compression Algorithms</a>.
     *
     * @return
     *         The name of this algorithm.
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
    public static JWEZip getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Convert {@code String} to {@code JWEZip}.
     *
     * @param alg
     *         Algorithm name. For example, {@code "DEF"}.
     *
     * @return
     *         {@code JWEZip} instance, or {@code null}.
     */
    public static JWEZip parse(String alg)
    {
        if (alg == null)
        {
            return null;
        }

        for (JWEZip entry : sValues)
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


    public static int toBits(EnumSet<JWEZip> set)
    {
        return sHelper.toBits(set);
    }


    public static JWEZip[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<JWEZip> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<JWEZip> toSet(JWEZip[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<JWEZip>
    {
        public Helper(JWEZip[] values)
        {
            super(JWEZip.class, values);
        }


        @Override
        protected short getValue(JWEZip entry)
        {
            return entry.getValue();
        }


        @Override
        protected JWEZip[] newArray(int size)
        {
            return new JWEZip[size];
        }
    }
}
