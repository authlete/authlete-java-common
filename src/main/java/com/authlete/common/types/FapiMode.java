package com.authlete.common.types;


import java.util.EnumSet;


/**
 * FAPI mode.
 *
 * @author Hideki Ikeda
 */
public enum FapiMode
{
    /**
     * {@code "fapi1_baseline"} (1).
     *
     * <p>
     * The request type for "<a href="https://openid.net/specs/openid-financial-api-part-1-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>".
     * </p>
     */
    FAPI1_BASELINE((short)1, "fapi1_baseline"),


    /**
     * {@code "fapi1_advanced"} (2).
     *
     * <p>
     * The request type for "<a href="https://openid.net/specs/openid-financial-api-part-2-1_0.html">
     * Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>".
     * </p>
     */
    FAPI1_ADVANCED((short)2, "fapi1_advanced"),


    /**
     * {@code "fapi2_security_profile"} (3).
     *
     * <p>
     * The request type for "<a href="https://openid.net/specs/fapi-2_0-security-02.html">
     * FAPI 2.0 Security Profile</a>".
     * </p>
     */
    FAPI2_SECURITY_PROFILE((short)3, "fapi2_security_profile"),


    /**
     * {@code "fapi2_message_signing_auth_req"} (4).
     *
     * <p>
     * The request type for "<a href="https://openid.net/specs/fapi-2_0-message-signing.html#section-5.3">
     * 5.3. Signing Authorization Requests</a>" of "<a href="
     * https://openid.net/specs/fapi-2_0-message-signing.html">FAPI 2.0
     * Message Signing</a>".
     * </p>
     */
    FAPI2_MS_AUTH_REQ((short)4, "fapi2_ms_auth_req"),


    /**
     * {@code "fapi2_message_signing_auth_res"} (5).
     *
     * <p>
     * The request type for "<a href="https://openid.net/specs/fapi-2_0-message-signing.html#section-5.4">
     * 5.4. Signing Authorization Responses</a>" of "<a href="
     * https://openid.net/specs/fapi-2_0-message-signing.html">FAPI 2.0
     * Message Signing</a>".
     * </p>
     */
    FAPI2_MS_AUTH_RES((short)5, "fapi2_ms_auth_res"),


    /**
     * {@code "fapi2_message_signing_introspection_res"} (6).
     *
     * <p>
     * The request type for "<a href="https://openid.net/specs/fapi-2_0-message-signing.html#section-5.5">
     * 5.5. Signing Introspection Responses</a>" of "<a href="
     * https://openid.net/specs/fapi-2_0-message-signing.html">FAPI 2.0
     * Message Signing</a>".
     * </p>
     */
    FAPI2_MS_INTROSPECTION_RES((short)6, "fapi2_ms_introspection_res"),
    ;


    private static final FapiMode[] sValues = values();
    private static final Helper sHelper = new Helper(sValues);
    private final short mValue;
    private final String mString;


    private FapiMode(short value, String string)
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
     * Convert the value to bits.
     */
    public int toBits()
    {
        return (1 << mValue);
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
    public static FapiMode getByValue(short value)
    {
        if (value < 0 || sValues.length <= value)
        {
            // Not found.
            return null;
        }

        return sValues[value];
    }


    /**
     * Convert {@code String} to {@code FapiMode}.
     *
     * @param fapiMode
     *         A FAPI mode. For example, {@code "fapi2_security_profile"}.
     *
     * @return
     *         {@code FapiMode} instance, or {@code null}.
     */
    public static FapiMode parse(String fapiMode)
    {
        if (fapiMode == null)
        {
            return null;
        }

        for (FapiMode value : sValues)
        {
            if (value.mString.equals(fapiMode))
            {
                // Found.
                return value;
            }
        }

        // Not found.
        return null;
    }


    public static int toBits(EnumSet<FapiMode> set)
    {
        return sHelper.toBits(set);
    }


    public static FapiMode[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<FapiMode> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<FapiMode> toSet(FapiMode[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<FapiMode>
    {
        public Helper(FapiMode[] values)
        {
            super(FapiMode.class, values);
        }


        @Override
        protected short getValue(FapiMode entry)
        {
            return entry.getValue();
        }


        @Override
        protected FapiMode[] newArray(int size)
        {
            return new FapiMode[size];
        }
    }
}