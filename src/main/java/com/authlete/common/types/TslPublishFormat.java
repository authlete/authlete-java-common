package com.authlete.common.types;

import java.util.EnumSet;

public enum TslPublishFormat
{

    /**
     * Currently jwt is supported. TSL publishing cwt format will be supported in the next iteration
     */
    JWT((short)1, "jwt");


    private static final TslPublishFormat[] sValues = values();
    private static final TslPublishFormat.Helper sHelper = new TslPublishFormat.Helper(sValues);
    private final short mValue;
    private final String mString;


    private TslPublishFormat(short value, String string)
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
    public static TslPublishFormat getByValue(short value)
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


    public static int toBits(EnumSet<TslPublishFormat> set)
    {
        return sHelper.toBits(set);
    }


    public static TslPublishFormat[] toArray(int bits)
    {
        return sHelper.toArray(bits);
    }


    public static EnumSet<TslPublishFormat> toSet(int bits)
    {
        return sHelper.toSet(bits);
    }


    public static EnumSet<TslPublishFormat> toSet(TslPublishFormat[] array)
    {
        return sHelper.toSet(array);
    }


    private static class Helper extends EnumHelper<TslPublishFormat>
    {
        public Helper(TslPublishFormat[] values)
        {
            super(TslPublishFormat.class, values);
        }


        @Override
        protected short getValue(TslPublishFormat entry)
        {
            return entry.getValue();
        }


        @Override
        protected TslPublishFormat[] newArray(int size)
        {
            return new TslPublishFormat[size];
        }
    }
}
