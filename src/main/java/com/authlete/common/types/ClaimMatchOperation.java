package com.authlete.common.types;

public enum ClaimMatchOperation
{
    PROHIBITED((short)1, "probited"), 
    PRESENT((short)2, "present"), 
    EQUALS((short)3, "equals");

    private static final ClaimMatchOperation[] sValues = values();
    private final short mValue;
    private final String mString;


    private ClaimMatchOperation(short value, String string)
    {
        mValue = value;
        mString = string;
    }


    public short getValue()
    {
        return mValue;
    }


    public String toString()
    {
        return mString;
    }


    public static ClaimMatchOperation getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // not found
            return null;
        }

        return sValues[value - 1];
    }


}