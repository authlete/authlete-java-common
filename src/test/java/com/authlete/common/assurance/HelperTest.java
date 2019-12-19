/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.assurance;


import static org.junit.Assert.*;
import org.junit.Test;


public class HelperTest
{
    private static void validDate(String string)
    {
        assertTrue(Helper.isValidDate(string));
    }


    private static void invalidDate(String string)
    {
        assertFalse(Helper.isValidDate(string));
    }


    private static void validTime(String string)
    {
        assertTrue(Helper.isValidTime(string));
    }


    private static void invalidTime(String string)
    {
        assertFalse(Helper.isValidTime(string));
    }


    @Test
    public void testDate01()
    {
        validDate("2019-12-31");
    }


    @Test
    public void testDate02()
    {
        validDate("20191231");
    }


    @Test
    public void testDate03()
    {
        invalidDate("2019-12-3");
    }


    @Test
    public void testDate04()
    {
        invalidDate("2019-1-31");
    }


    @Test
    public void testDate05()
    {
        invalidDate("201-12-31");
    }


    @Test
    public void testDate06()
    {
        invalidDate("2019--12--31");
    }


    @Test
    public void testDate07()
    {
        invalidDate("201A-12-31");
    }


    @Test
    public void testDate08()
    {
        invalidDate("2019-1A-31");
    }


    @Test
    public void testDate09()
    {
        invalidDate("2019-12-3A");
    }


    @Test
    public void testTime01()
    {
        validTime("2019-12-31T01:23:45");
    }


    @Test
    public void testTime02()
    {
        validTime("2019-12-31T01:23:45Z");
    }


    @Test
    public void testTime03()
    {
        validTime("2019-12-31T01:23:45+01");
    }


    @Test
    public void testTime04()
    {
        validTime("2019-12-31T01:23:45-01");
    }


    @Test
    public void testTime05()
    {
        validTime("2019-12-31T01:23:45+0100");
    }


    @Test
    public void testTime06()
    {
        validTime("2019-12-31T01:23:45+01:00");
    }


    @Test
    public void testTime07()
    {
        invalidTime("2019-12-31T01:23:4");
    }


    @Test
    public void testTime08()
    {
        invalidTime("2019-12-31T01:2:46");
    }


    @Test
    public void testTime09()
    {
        invalidTime("2019-12-31T1:23:45");
    }


    @Test
    public void testTime10()
    {
        invalidTime("2019-12-31t01:23:45");
    }


    @Test
    public void testTime11()
    {
        invalidTime("2019-12-31T01234");
    }


    @Test
    public void testTime12()
    {
        invalidTime("2019-12-31T01:23:45z");
    }


    @Test
    public void testTime13()
    {
        invalidTime("2019-12-31T01:23:45Z012");
    }


    @Test
    public void testTime14()
    {
        invalidTime("2019-12-31T01:23:45Z01234");
    }


    @Test
    public void testTime15()
    {
        invalidTime("2019-12-31T01:23:45Z01:2");
    }


    @Test
    public void testTime16()
    {
        invalidTime("2019-12-31T01:23:45Z1:23");
    }


    @Test
    public void testTime17()
    {
        invalidTime("2019-12-31T01:23:45Z01:234");
    }


    @Test
    public void testTime18()
    {
        invalidTime("2019-12-31T01:23:45Z01-23");
    }
}
