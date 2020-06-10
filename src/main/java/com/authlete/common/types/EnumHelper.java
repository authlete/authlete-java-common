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


import java.util.EnumSet;


abstract class EnumHelper<TEnum extends Enum<TEnum>>
{
    private final Class<TEnum> mClass;
    private final TEnum[] mValues;


    public EnumHelper(Class<TEnum> enumClass, TEnum[] enumValues)
    {
        mClass  = enumClass;
        mValues = enumValues;
    }


    public int toBits(TEnum[] array)
    {
        if (array == null)
        {
            return 0;
        }

        int bits = 0;

        for (TEnum entry : array)
        {
            bits |= (1 << getValue(entry));
        }

        return bits;
    }


    public int toBits(EnumSet<TEnum> set)
    {
        if (set == null)
        {
            return 0;
        }

        int bits = 0;

        for (TEnum entry : set)
        {
            bits |= (1 << getValue(entry));
        }

        return bits;
    }


    public TEnum[] toArray(int bits)
    {
        return toArray(toSet(bits));
    }


    public TEnum[] toArray(EnumSet<TEnum> set)
    {
        if (set == null)
        {
            return null;
        }

        return set.toArray(newArray(set.size()));
    }


    public EnumSet<TEnum> toSet(int bits)
    {
        EnumSet<TEnum> set = EnumSet.noneOf(mClass);

        for (TEnum entry : mValues)
        {
            if ((bits & (1 << getValue(entry))) != 0)
            {
                set.add(entry);
            }
        }

        return set;
    }


    public EnumSet<TEnum> toSet(TEnum[] array)
    {
        if (array == null)
        {
            return null;
        }

        EnumSet<TEnum> set = EnumSet.noneOf(mClass);

        for (TEnum entry : array)
        {
            set.add(entry);
        }

        return set;
    }


    protected abstract short getValue(TEnum entry);
    protected abstract TEnum[] newArray(int size);
}
