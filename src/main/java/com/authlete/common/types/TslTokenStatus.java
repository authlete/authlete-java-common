package com.authlete.common.types;

import java.util.EnumSet;

public enum TslTokenStatus
{

    VALID((short)0, "valid"),
    INVALID((short)1, "invalid"),
    SUSPENDED((short)2, "suspended");


    private static final TslTokenStatus[] sValues = values();
    private static final TslTokenStatus.Helper sHelper = new TslTokenStatus.Helper(sValues);
    private final short mValue;
    private final String mString;


    private TslTokenStatus(short value, String string)
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


    /**
     * Find an instance of this enum by a value.
     *
     * @param value
     *         The integer representation of the instance to find.
     *
     * @return
     *         An instance of this enum, or {@code null} if not found.
     */
    public static TslTokenStatus getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Get the string value of TSL token status.
     *
     * @return
     *         A string that contains TSL token status.
     */
    public String getString()
    {
        return mString;
    }


    public static int toBits(EnumSet<TslTokenStatus> set)
    {
        return sHelper.toBits(set);
    }


    public static TslTokenStatus[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<TslTokenStatus> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<TslTokenStatus> toSet(TslTokenStatus[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<TslTokenStatus>
    {
        public Helper(TslTokenStatus[] values)
        {
            super(TslTokenStatus.class, values);
        }


        @Override
        protected short getValue(TslTokenStatus entry)
        {
            return entry.getValue();
        }


        @Override
        protected TslTokenStatus[] newArray(int size)
        {
            return new TslTokenStatus[size];
        }
    }
}
