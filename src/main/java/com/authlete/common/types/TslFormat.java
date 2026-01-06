package com.authlete.common.types;

import java.util.EnumSet;

public enum TslFormat
{

    /**
     * Currently jwt is supported. TSL publishing cwt format will be supported in the next iteration
     */
    JWT((short)1, "jwt");


    private static final TslFormat[] sValues = values();
    private static final TslFormat.Helper sHelper = new TslFormat.Helper(sValues);
    private final short mValue;
    private final String mString;


    private TslFormat(short value, String string)
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
    public static TslFormat getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // Not found.
            return null;
        }

        return sValues[value - 1];
    }


    /**
     * Get the string value of TSL publish format.
     *
     * @return
     *         A string that contains TSL publish format.
     */
    public String getString()
    {
        return mString;
    }


    public static int toBits(EnumSet<TslFormat> set)
    {
        return sHelper.toBits(set);
    }


    public static TslFormat[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<TslFormat> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<TslFormat> toSet(TslFormat[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<TslFormat>
    {
        public Helper(TslFormat[] values)
        {
            super(TslFormat.class, values);
        }


        @Override
        protected short getValue(TslFormat entry)
        {
            return entry.getValue();
        }


        @Override
        protected TslFormat[] newArray(int size)
        {
            return new TslFormat[size];
        }
    }
}
