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


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Mutable JSON Pointer.
 *
 * <pre>
 * <span style="color:green">// From a string.</span>
 * MutableJsonPointer pointer = new MutableJsonPointer("/a/b");
 *
 * <span style="color:green">// Append one by one.</span>
 * MutableJsonPointer pointer = new MutableJsonPointer().append("a").append("b");
 *
 * <span style="color:green">// Modify.</span>
 * MutableJsonPointer pointer = new MutableJsonPointer("/a/c").remove().append("b");
 *
 * <span style="color:green">// Get reference tokens.</span>
 * List&lt;String&gt; tokens = new MutableJsonPointer("/a/b").getReferenceTokens();
 *
 * <span style="color:green">// Create a JsonPointer instance from a MutableJsonPointer instance.</span>
 * <a href="https://jakarta.ee/specifications/jsonp/2.0/apidocs/jakarta.json/jakarta/json/jsonpointer"
 * >JsonPointer</a> pointer = <a href="https://jakarta.ee/specifications/jsonp/2.0/apidocs/jakarta.json/jakarta/json/json"
 * >Json</a>.<a href="https://jakarta.ee/specifications/jsonp/2.0/apidocs/jakarta.json/jakarta/json/json#createPointer(java.lang.String)"
 * >createPointer</a>(new MutableJsonPointer("/a/b").toString());
 * </pre>
 *
 * @since 3.17
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6901.html"
 *      >RFC 6901: JavaScript Object Notation (JSON) Pointer</a>
 *
 * @see <a href="https://jakarta.ee/specifications/jsonp/"
 *      >Jakarta JSON Processing</a>
 */
public class MutableJsonPointer
{
    private final LinkedList<String> mReferenceTokens = new LinkedList<>();


    /**
     * The default constructor.
     */
    public MutableJsonPointer()
    {
        this((String)null);
    }


    /**
     * A copy constructor.
     *
     * @param pointer
     *         A {@code MutableJsonPointer} instance from which reference
     *         tokens are copied.
     */
    public MutableJsonPointer(MutableJsonPointer pointer)
    {
        if (pointer != null)
        {
            mReferenceTokens.addAll(pointer.mReferenceTokens);
        }
    }


    /**
     * A constructor that receives a JSON pointer.
     *
     * @param pointer
     *         A JSON pointer. Both {@code null} and an empty string are
     *         accepted. They represent the whole JSON document. In other
     *         cases, {@code pointer} must start with {@code "/"}.
     *
     * @throws IllegalArgumentException
     *         {@code pointer} does not start with {@code "/"} although
     *         it is neither {@code null} nor an empty string.
     */
    public MutableJsonPointer(String pointer)
    {
        if (pointer == null)
        {
            return;
        }

        // If the given string is an empty string.
        if (pointer.isEmpty())
        {
            // An empty string represents the whole JSON document.
            return;
        }

        // If the given string does not start with "/".
        if (!pointer.startsWith("/"))
        {
            throw new IllegalArgumentException(
                    "A pointer must start with '/' unless it is an empty string.");
        }

        // Extract reference tokens from the pointer and add them to
        // the internal list.
        Arrays.stream(pointer.split("/")).skip(1)
            .forEach(referenceToken -> mReferenceTokens.add(referenceToken));
    }


    /**
     * Append a reference token.
     *
     * @param referenceToken
     *         A reference token to append.
     *
     * @param doEscape
     *         {@code true} to make this method escape special characters in
     *         {@code referenceToken} before the reference token is appended.
     *         The {@link #escape(String)} method is used for the escape
     *         processing.
     *
     * @return
     *         {@code this} object.
     */
    public MutableJsonPointer append(String referenceToken, boolean doEscape)
    {
        if (doEscape)
        {
            referenceToken = escape(referenceToken);
        }

        mReferenceTokens.add(referenceToken);

        return this;
    }


    /**
     * Append a reference token.
     *
     * <p>
     * This method is an alias of {@link #append(String, boolean)
     * append}<code>(referenceToken, true)</code>. Special characters in
     * {@code referenceToken} are escaped.
     * </p>
     *
     * @param referenceToken
     *         A reference token to append.
     *
     * @return
     *         {@code this} object.
     */
    public MutableJsonPointer append(String referenceToken)
    {
        return append(referenceToken, true);
    }


    /**
     * Append a reference token.
     *
     * @param arrayIndex
     *         A reference token.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code arrayIndex} is less than 0.
     */
    public MutableJsonPointer append(int arrayIndex)
    {
        if (arrayIndex < 0)
        {
            throw new IllegalArgumentException(
                    "The argument to append(int) must not be negative.");
        }

        return append(String.valueOf(arrayIndex), false);
    }


    /**
     * Remove the last reference token from the end.
     *
     * <p>
     * Even if this method is called more times than the number of reference
     * tokens held by this instance, no exception is thrown.
     * </p>
     *
     * @return
     *         {@code this} object.
     */
    public MutableJsonPointer remove()
    {
        mReferenceTokens.pollLast();

        return this;
    }


    @Override
    public String toString()
    {
        if (mReferenceTokens.size() == 0)
        {
            // An empty string which represents the whole JSON document.
            return "";
        }

        StringBuilder builder = new StringBuilder();

        mReferenceTokens.stream().forEach(
                referenceToken -> builder.append("/").append(referenceToken));

        return builder.toString();
    }


    /**
     * Get the list of reference tokens.
     *
     * @return
     *         The list of reference tokens.
     */
    public List<String> getReferenceTokens()
    {
        return Collections.unmodifiableList(mReferenceTokens);
    }


    /**
     * Escape special characters in the given string according to the rules
     * defined in the <a href=
     * "https://www.rfc-editor.org/rfc/rfc6901.html#section-3">Section 3.
     * Syntax</a> of <a href="https://www.rfc-editor.org/rfc/rfc6901.html"
     * >RFC 6901</a> JavaScript Object Notation (JSON) Pointer.
     *
     * <p>
     * To be concrete, all {@code "~"} are converted to {@code "~0"} and
     * then all {@code "/"} are converted to {@code "~1"}.
     * </p>
     *
     * @param input
     *         A string that may contain special characters
     *         ({@code "~"} and {@code "/"}).
     *
     * @return
     *         A new string after conversion. If the given string is
     *         {@code null}, {@code null} is returned.
     */
    public static String escape(String input)
    {
        if (input == null)
        {
            return null;
        }

        // RFC 6901 JavaScript Object notation (JSON) Pointer
        // 3. Syntax
        //
        //   json-pointer    = *( "/" reference-token )
        //   reference-token = *( unescaped / escaped )
        //   unescaped       = %x00-2E / %x30-7D / %x7F-10FFFF
        //      ; %x2F ('/') and %x7E ('~') are excluded from 'unescaped'
        //   escaped         = "~" ( "0" / "1" )
        //      ; representing '~' and '/', respectively
        //

        return input.replaceAll("~", "~0").replaceAll("/", "~1");
    }


    /**
     * Unescape special sequences in the given string according to the rules
     * defined in the <a href=
     * "https://www.rfc-editor.org/rfc/rfc6901.html#section-4">Section 4.
     * Evaluation</a> of <a href="https://www.rfc-editor.org/rfc/rfc6901.html"
     * >RFC 6901</a> JavaScript Object Notation (JSON) Pointer.
     *
     * <p>
     * To be concrete, all {@code "~1"} are converted to {@code "/"} and
     * then all {@code "~0"} are converted to {@code "~"}.
     * </p>
     *
     * @param input
     *         A string that may contain special sequences ({@code "~0"} and
     *         {@code "~1"}).
     *
     * @return
     *         A new string after conversion. If the given string is
     *         {@code null}, {@code null} is returned.
     */
    public static String unescape(String input)
    {
        if (input == null)
        {
            return null;
        }

        // RFC 6901 JavaScript Object notation (JSON) Pointer
        // 4. Evaluation
        //
        //   Evaluation of each reference token begins by decoding any escaped
        //   character sequence.  This is performed by first transforming any
        //   occurrence of the sequence '~1' to '/', and then transforming any
        //   occurrence of the sequence '~0' to '~'.  By performing the
        //   substitutions in this order, an implementation avoids the error of
        //   turning '~01' first into '~1' and then into '/', which would be
        //   incorrect (the string '~01' correctly becomes '~1' after
        //   transformation).

        return input.replaceAll("~1", "/").replaceAll("~0", "~");
    }
}
