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


    private static void validDateTime(String string)
    {
        assertTrue(Helper.isValidDateTime(string));
    }


    private static void invalidDateTime(String string)
    {
        assertFalse(Helper.isValidDateTime(string));
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
    public void testDateTime01()
    {
        validDateTime("2019-12-31T01:23:45");
    }


    @Test
    public void testDateTime02()
    {
        validDateTime("2019-12-31T01:23:45Z");
    }


    @Test
    public void testDateTime03()
    {
        validDateTime("2019-12-31T01:23:45+01");
    }


    @Test
    public void testDateTime04()
    {
        validDateTime("2019-12-31T01:23:45-01");
    }


    @Test
    public void testDateTime05()
    {
        validDateTime("2019-12-31T01:23:45+0100");
    }


    @Test
    public void testDateTime06()
    {
        validDateTime("2019-12-31T01:23:45+01:00");
    }


    @Test
    public void testDateTime07()
    {
        invalidDateTime("2019-12-31T01:23:4");
    }


    @Test
    public void testDateTime08()
    {
        invalidDateTime("2019-12-31T01:2:46");
    }


    @Test
    public void testDateTime09()
    {
        invalidDateTime("2019-12-31T1:23:45");
    }


    @Test
    public void testDateTime10()
    {
        invalidDateTime("2019-12-31t01:23:45");
    }


    @Test
    public void testDateTime11()
    {
        invalidDateTime("2019-12-31T01234");
    }


    @Test
    public void testDateTime12()
    {
        invalidDateTime("2019-12-31T01:23:45z");
    }


    @Test
    public void testDateTime13()
    {
        invalidDateTime("2019-12-31T01:23:45Z012");
    }


    @Test
    public void testDateTime14()
    {
        invalidDateTime("2019-12-31T01:23:45Z01234");
    }


    @Test
    public void testDateTime15()
    {
        invalidDateTime("2019-12-31T01:23:45Z01:2");
    }


    @Test
    public void testDateTime16()
    {
        invalidDateTime("2019-12-31T01:23:45Z1:23");
    }


    @Test
    public void testDateTime17()
    {
        invalidDateTime("2019-12-31T01:23:45Z01:234");
    }


    @Test
    public void testDateTime18()
    {
        invalidDateTime("2019-12-31T01:23:45Z01-23");
    }


    @Test
    public void testDateTime19()
    {
        validDateTime("2019-12-31T01:23:45.1");
    }


    @Test
    public void testDateTime20()
    {
        validDateTime("2019-12-31T01:23:45.12");
    }


    @Test
    public void testDateTime21()
    {
        validDateTime("2019-12-31T01:23:45.123");
    }


    @Test
    public void testDateTime22()
    {
        validDateTime("2019-12-31T01:23:45,1");
    }


    @Test
    public void testDateTime23()
    {
        validDateTime("2019-12-31T01:23:45,12");
    }


    @Test
    public void testDateTime24()
    {
        validDateTime("2019-12-31T01:23:45,123");
    }


    @Test
    public void testDateTime25()
    {
        invalidDateTime("2019-12-31T01:23:45.");
    }


    @Test
    public void testDateTime26()
    {
        invalidDateTime("2019-12-31T01:23:45,");
    }


    @Test
    public void testDateTime27()
    {
        invalidDateTime("2019-12-31T01:23:45x1");
    }


    @Test
    public void testDateTime28()
    {
        validDateTime("2019-12-31T01:23:45.1Z");
    }


    @Test
    public void testDateTime29()
    {
        validDateTime("2019-12-31T01:23:45.12+09:00");
    }


    @Test
    public void testDateTime30()
    {
        validDateTime("2019-12-31T01:23:45.123-09:00");
    }
}
