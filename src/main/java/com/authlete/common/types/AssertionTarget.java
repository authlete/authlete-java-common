package com.authlete.common.types;

public enum AssertionTarget
{

    CLIENT_REGISTRATION_SOFTWARE_STATEMENT((short) 1, "software_statement");


    private static final AssertionTarget[] sValues = values();
    private final short mValue;
    private final String mString;


    private AssertionTarget(short value, String string)
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


    public static AssertionTarget getByValue(short value)
    {
        if (value < 1 || sValues.length < value)
        {
            // not found
            return null;
        }

        return sValues[value - 1];
    }

}
