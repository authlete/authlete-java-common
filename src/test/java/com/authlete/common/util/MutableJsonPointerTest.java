/*
 * Copyright (C) 2022 Authlete, Inc.
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
package com.authlete.common.util;


import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonPointer;
import org.junit.Test;


public class MutableJsonPointerTest
{
    private static JsonPointer createJsonPointer(String pointer)
    {
        return Json.createPointer(pointer);
    }


    private static JsonPointer createJsonPointer(MutableJsonPointer pointer)
    {
        return createJsonPointer(pointer.toString());
    }


    @Test
    public void testConstructorDefault()
    {
        JsonPointer pointer  = createJsonPointer(new MutableJsonPointer());
        String      expected = "";

        assertEquals(expected, pointer.toString());
    }


    @Test
    public void testConstructorString()
    {
        String pointerString = "/a/b";

        JsonPointer pointer = createJsonPointer(new MutableJsonPointer(pointerString));

        assertEquals(pointerString, pointer.toString());
    }


    @Test
    public void testConstructorPointer()
    {
        String pointerString = "/a/b";

        JsonPointer pointer  = createJsonPointer(pointerString);
        JsonPointer pointer2 = createJsonPointer(new MutableJsonPointer(pointer.toString()));

        assertEquals(pointer, pointer2);
    }


    @Test
    public void testConstructorEmpty()
    {
        JsonPointer pointer  = createJsonPointer(new MutableJsonPointer(""));
        String      expected = "";

        assertEquals(expected, pointer.toString());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalid()
    {
        String pointerString = "a/b";

        new MutableJsonPointer(pointerString);
    }


    @Test
    public void testEscape()
    {
        String input    = "a/b~";
        String output   = MutableJsonPointer.escape(input);
        String expected = "a~1b~0";

        assertEquals(expected, output);
    }


    @Test
    public void testUnescape()
    {
        String input    = "a~1b~0";
        String output   = MutableJsonPointer.unescape(input);
        String expected = "a/b~";

        assertEquals(expected, output);
    }


    @Test
    public void testUnescape2()
    {
        String input    = "~01";
        String output   = MutableJsonPointer.unescape(input);
        String expected = "~1";

        assertEquals(expected, output);
    }


    @Test
    public void testAppend()
    {
        MutableJsonPointer pointer   = new MutableJsonPointer().append("a").append("b");
        List<String> referenceTokens = pointer.getReferenceTokens();
        List<String> expectedTokens  = Arrays.asList("a", "b");
        String       expectedString  = "/a/b";

        assertEquals(expectedTokens, referenceTokens);
        assertEquals(expectedString, pointer.toString());
    }


    @Test
    public void testAppendEmpty()
    {
        MutableJsonPointer pointer  = new MutableJsonPointer().append("");
        String             expected = "/";

        assertEquals(expected, pointer.toString());
    }


    @Test
    public void testAppendEscape()
    {
        MutableJsonPointer pointer  = new MutableJsonPointer().append("a").append("b~/c/~");
        String             expected = "/a/b~0~1c~1~0";

        assertEquals(expected, pointer.toString());
    }


    @Test
    public void testAppendUnescape()
    {
        MutableJsonPointer pointer  = new MutableJsonPointer().append("a~0", false).append("b~1", false);
        String             expected = "/a~0/b~1";

        assertEquals(expected, pointer.toString());

    }


    @Test
    public void testAppendIndex()
    {
        MutableJsonPointer pointer  = new MutableJsonPointer("/a").append(0).append("b");
        String             expected = "/a/0/b";

        assertEquals(expected, pointer.toString());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAppendNegativeIndex()
    {
        new MutableJsonPointer("/a").append(-1);
    }


    @Test
    public void testRemove()
    {
        MutableJsonPointer pointer  = new MutableJsonPointer("/a/b").remove().append("c");
        String             expected = "/a/c";

        assertEquals(expected, pointer.toString());
    }


    @Test
    public void testRemoveManyTimes()
    {
        MutableJsonPointer pointer  = new MutableJsonPointer().remove().remove().remove().append("a");
        String             expected = "/a";

        assertEquals(expected, pointer.toString());
    }
}
