/*
 * Copyright (C) 2019 Authlete, Inc.
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


import java.security.SecureRandom;
import java.util.Random;
import com.authlete.common.types.UserCodeCharset;


/**
 * Generator for end-user verification codes ({@code user_code}) in Device Flow.
 * See "6.1. User Code Recommendations" of <a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-device-flow/?include_text=1"
 * >OAuth 2.0 Device Authorization Grant</a> for recommendations for user code values.
 *
 * <pre>
 * // The simplest example
 * String userCode = new UserCodeGenerator().{@link #generate()};
 *
 * // The longest example
 * String userCode =
 *     new UserCodeGenerator()
 *         .{@link #setCharacters(UserCodeCharset)
 *                  setCharacters}({@link UserCodeCharset}.{@link UserCodeCharset#BASE20 BASE20})
 *         .{@link #setLength(int) setLength}(8)
 *         .{@link #setRandom(Random) setRandom}(new SecureRandom())
 *         .{@link #generate()}
 *         ;
 * </pre>
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-device-flow/?include_text=1"
 *      >OAuth 2.0 Device Authorization Grant</a>
 *
 * @since 2.43
 */
public class UserCodeGenerator
{
    private String mCharacters;
    private int mLength;
    private Random mRandom;


    /**
     * Constructor.
     */
    public UserCodeGenerator()
    {
    }


    /**
     * Constructor.
     *
     * @param characters
     *         Characters that may appear in generated user codes.
     */
    public UserCodeGenerator(String characters)
    {
        this(characters, 0, null);
    }


    /**
     * Constructor.
     *
     * @param length
     *         Length of generated user codes.
     */
    public UserCodeGenerator(int length)
    {
        this((String)null, length, null);
    }


    /**
     * Constructor.
     *
     * @param characters
     *         Characters that may appear in generated user codes.
     *
     * @param length
     *         Length of generated user codes.
     */
    public UserCodeGenerator(String characters, int length)
    {
        this(characters, length, null);
    }


    /**
     * Constructor.
     *
     * @param characters
     *         Characters that may appear in generated user codes.
     *
     * @param length
     *         Length of generated user codes.
     *
     * @param random
     *         A {@link Random} instance used for user code generation.
     */
    public UserCodeGenerator(String characters, int length, Random random)
    {
        mCharacters = characters;
        mLength     = length;
        mRandom     = random;
    }


    /**
     * Constructor.
     *
     * @param charset
     *         A character set for user codes. The value returned from
     *         {@code charset.}{@link UserCodeCharset#getCharacters()
     *         getCharacters()} is used unless {@code charset} is
     *         {@code null}.
     */
    public UserCodeGenerator(UserCodeCharset charset)
    {
        this(charset, 0, null);
    }


    /**
     * Constructor.
     *
     * @param charset
     *         A character set for user codes. The value returned from
     *         {@code charset.}{@link UserCodeCharset#getCharacters()
     *         getCharacters()} is used unless {@code charset} is
     *         {@code null}.
     *
     * @param length
     *         Length of generated user codes.
     */
    public UserCodeGenerator(UserCodeCharset charset, int length)
    {
        this(charset, length, null);
    }


    /**
     * Constructor.
     *
     * @param charset
     *         A character set for user codes. The value returned from
     *         {@code charset.}{@link UserCodeCharset#getCharacters()
     *         getCharacters()} is used unless {@code charset} is
     *         {@code null}.
     *
     * @param length
     *         Length of generated user codes.
     *
     * @param random
     *         A {@link Random} instance used for user code generation.
     */
    public UserCodeGenerator(UserCodeCharset charset, int length, Random random)
    {
        this(charset != null ? charset.getCharacters() : null, length, random);
    }


    /**
     * Get the characters that may appear in generated user codes.
     *
     * @return
     *         The characters that may appear in generated user codes.
     */
    public String getCharacters()
    {
        return mCharacters;
    }


    /**
     * Set characters that may appear in generated user codes.
     *
     * @param characters
     *         Characters that may appear in generated user codes.
     *
     * @return
     *         {@code this} object.
     */
    public UserCodeGenerator setCharacters(String characters)
    {
        mCharacters = characters;

        return this;
    }


    /**
     * Set characters that may appear in generated user codes. The value
     * returned from {@code charset.}{@link UserCodeCharset#getCharacters()
     * getCharacters()} is used as characters for generated user codes
     * unless {@code charset} is {@code null}.
     *
     * @param charset
     *         A character set for user codes.
     *
     * @return
     *         {@code this} object.
     */
    public UserCodeGenerator setCharacters(UserCodeCharset charset)
    {
        return setCharacters(charset != null ? charset.getCharacters() : null);
    }


    /**
     * Get the length of generated user codes.
     *
     * @return
     *         The length of generated user codes.
     */
    public int getLength()
    {
        return mLength;
    }


    /**
     * Set length of generated user codes.
     *
     * @param length
     *         Length of generated user codes.
     *
     * @return
     *         {@code this} object.
     */
    public UserCodeGenerator setLength(int length)
    {
        mLength = length;

        return this;
    }


    /**
     * Get the {@link Random} instance used for user code generation.
     *
     * @return
     *         The {@link Random} instance user for user code generation.
     */
    public Random getRandom()
    {
        return mRandom;
    }


    /**
     * Set a {@link Random} instance used for user code generation.
     *
     * @param random
     *         A {@link Random} instance used for user code generation.
     *
     * @return
     *         {@code this} object.
     */
    public UserCodeGenerator setRandom(Random random)
    {
        mRandom = random;

        return this;
    }


    /**
     * Generate a user code.
     *
     * <p>
     * If characters for user codes are not set, the value returned from
     * {@link UserCodeCharset}{@code .}{@link UserCodeCharset#BASE20
     * BASE20}{@code .}{@link UserCodeCharset#getCharacters() getCharacters()}
     * (namely, {@code "BCDFGHJKLMNPQRSTVWXZ"}) is used.
     * </p>
     *
     * <p>
     * If length is not set or its value is 0 or less, proper length is
     * computed based on the characters and used.
     * </p>
     *
     * <p>
     * If a {@code Random} instance is not set, a new instance of
     * {@link SecureRandom} is created and used.
     * </p>
     *
     * @return
     *         A newly-generated user code.
     */
    public String generate()
    {
        // Characters
        String characters = getCharacters();
        if (characters == null || characters.length() == 0)
        {
            characters = getDefaultCharacters();
        }

        // Length of user code
        int length = getLength();
        if (length <= 0)
        {
            length = getDefaultLength(characters);
        }

        // Random number generator
        Random random = getRandom();
        if (random == null)
        {
            random = getDefaultRandom();
        }

        return generate(characters, length, random);
    }


    private String generate(String characters, int length, Random random)
    {
        StringBuilder userCodeBuilder = new StringBuilder(length);

        for (int i = 0; i < length; ++i)
        {
            int index = random.nextInt(characters.length());
            char ch   = characters.charAt(index);

            userCodeBuilder.append(ch);
        }

        return userCodeBuilder.toString();
    }


    private String getDefaultCharacters()
    {
        return UserCodeCharset.BASE20.getCharacters();
    }


    private int getDefaultLength(String characters)
    {
        int n = characters.length();

        if (55 <= n)
        {
            // 55 ^ 6 = 27,680,640,625
            return 6;
        }
        else if (31 <= n)
        {
            // 31 ^ 7 = 27,512,614,111
            return 7;
        }
        else if (14 <= n)
        {
            // "WDJB-MJHT" is an example in the Device Flow spec.
            // 14 ^ 8 = 1,475,789,056
            return 8;
        }
        else if (10 <= n)
        {
            // "019-450-730" is an example in the Device Flow spec.
            // Its entropy is used as the minimum in this if-else chain.
            // 10 ^ 9 = 1,000,000,000
            return 9;
        }
        else if (8 <= n)
        {
            // 8 ^ 10 = 1,073,741,824
            return 10;
        }
        else
        {
            // Not enough entropy.
            return 11;
        }
    }


    private Random getDefaultRandom()
    {
        return new SecureRandom();
    }
}
