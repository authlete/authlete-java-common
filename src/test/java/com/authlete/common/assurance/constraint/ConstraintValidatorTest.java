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
package com.authlete.common.assurance.constraint;


import org.junit.Test;


public class ConstraintValidatorTest
{
    private static VerifiedClaimsContainerConstraint toContainer(String json)
    {
        return VerifiedClaimsContainerConstraint.fromJson(json);
    }


    private static void validate(String json)
    {
        new ConstraintValidator().validate(toContainer(json));
    }


    private static void validatePurpose(String purpose)
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "      \"title\":{",
                "        \"purpose\":\"" + purpose + "\"",
                "      }",
                "    }",
                "  }",
                "}");

        validate(json);
    }


    private static String getPurposeWithMinimumLength()
    {
        return "abc";
    }


    private static String getPurposeWithMaximumLength()
    {
        return "01234567890123456789012345678901234567890123456789" + //  50
               "01234567890123456789012345678901234567890123456789" + // 100
               "01234567890123456789012345678901234567890123456789" + // 150
               "01234567890123456789012345678901234567890123456789" + // 200
               "01234567890123456789012345678901234567890123456789" + // 250
               "01234567890123456789012345678901234567890123456789"   // 300
               ;
    }


    @Test
    public void testClaimsNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":null",
                "  }",
                "}");

        validate(json);
    }


    @Test(expected = ConstraintException.class)
    public void testClaimsEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "    }",
                "  }",
                "}");

        validate(json);
    }


    @Test
    public void testClaimsNormal()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "      \"title\":null",
                "    }",
                "  }",
                "}");

        validate(json);
    }


    @Test(expected = ConstraintException.class)
    public void testPurposeTooShort()
    {
        validatePurpose("a");
    }


    @Test(expected = ConstraintException.class)
    public void testPurposeTooLong()
    {
        validatePurpose(getPurposeWithMaximumLength() + "0");
    }


    @Test
    public void testPurposeWithMinimumLength()
    {
        validatePurpose(getPurposeWithMinimumLength());
    }


    @Test
    public void testPurposeWithMaximumLength()
    {
        validatePurpose(getPurposeWithMaximumLength());
    }


    @Test
    public void testPurposeWithNormalLength()
    {
        validatePurpose("abcdefghijklmnopqrstuvwxyz");
    }
}
