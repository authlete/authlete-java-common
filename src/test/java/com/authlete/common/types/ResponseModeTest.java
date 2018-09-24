/*
 * Copyright (C) 2018 Authlete, Inc.
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


import org.junit.Test;
import static org.junit.Assert.*;
import static com.authlete.common.types.ResponseMode.*;


public class ResponseModeTest
{
    @Test
    public void testJwtRequired()
    {
        assertFalse(QUERY        .isJwtRequired());
        assertFalse(FRAGMENT     .isJwtRequired());
        assertFalse(FORM_POST    .isJwtRequired());
        assertTrue( JWT          .isJwtRequired());
        assertTrue( QUERY_JWT    .isJwtRequired());
        assertTrue( FRAGMENT_JWT .isJwtRequired());
        assertTrue( FORM_POST_JWT.isJwtRequired());
    }


    @Test
    public void testQueryRequired()
    {
        assertTrue( QUERY        .isQueryRequired());
        assertFalse(FRAGMENT     .isQueryRequired());
        assertFalse(FORM_POST    .isQueryRequired());
        assertFalse(JWT          .isQueryRequired());
        assertTrue( QUERY_JWT    .isQueryRequired());
        assertFalse(FRAGMENT_JWT .isQueryRequired());
        assertFalse(FORM_POST_JWT.isQueryRequired());
    }


    @Test
    public void testFragmentRequired()
    {
        assertFalse(QUERY        .isFragmentRequired());
        assertTrue( FRAGMENT     .isFragmentRequired());
        assertFalse(FORM_POST    .isFragmentRequired());
        assertFalse(JWT          .isFragmentRequired());
        assertFalse(QUERY_JWT    .isFragmentRequired());
        assertTrue( FRAGMENT_JWT .isFragmentRequired());
        assertFalse(FORM_POST_JWT.isFragmentRequired());
    }


    @Test
    public void testFormPostRequired()
    {
        assertFalse(QUERY        .isFormPostRequired());
        assertFalse(FRAGMENT     .isFormPostRequired());
        assertTrue( FORM_POST    .isFormPostRequired());
        assertFalse(JWT          .isFormPostRequired());
        assertFalse(QUERY_JWT    .isFormPostRequired());
        assertFalse(FRAGMENT_JWT .isFormPostRequired());
        assertTrue( FORM_POST_JWT.isFormPostRequired());
    }


    @Test
    public void testWithJwt()
    {
        assertEquals(QUERY_JWT,     QUERY        .withJwt());
        assertEquals(FRAGMENT_JWT,  FRAGMENT     .withJwt());
        assertEquals(FORM_POST_JWT, FORM_POST    .withJwt());
        assertEquals(JWT,           JWT          .withJwt());
        assertEquals(QUERY_JWT,     QUERY_JWT    .withJwt());
        assertEquals(FRAGMENT_JWT,  FRAGMENT_JWT .withJwt());
        assertEquals(FORM_POST_JWT, FORM_POST_JWT.withJwt());
    }


    @Test
    public void testWithoutJwt()
    {
        assertEquals(QUERY,     QUERY        .withoutJwt());
        assertEquals(FRAGMENT,  FRAGMENT     .withoutJwt());
        assertEquals(FORM_POST, FORM_POST    .withoutJwt());
        assertEquals(null,      JWT          .withoutJwt());
        assertEquals(QUERY,     QUERY_JWT    .withoutJwt());
        assertEquals(FRAGMENT,  FRAGMENT_JWT .withoutJwt());
        assertEquals(FORM_POST, FORM_POST_JWT.withoutJwt());
    }
}
