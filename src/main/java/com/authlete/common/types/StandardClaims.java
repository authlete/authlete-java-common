/*
 * Copyright (C) 2014-2015 Authlete, Inc.
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


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Standard claims.
 *
 * <blockquote>
 * <ol>
 *   <li>{@link #SUB sub}
 *   <li>{@link #NAME name}
 *   <li>{@link #GIVEN_NAME given_name}
 *   <li>{@link #FAMILY_NAME family_name}
 *   <li>{@link #MIDDLE_NAME middle_name}
 *   <li>{@link #NICKNAME nickname}
 *   <li>{@link #PREFERRED_USERNAME preferred_username}
 *   <li>{@link #PROFILE profile}
 *   <li>{@link #PICTURE picture}
 *   <li>{@link #WEBSITE website}
 *   <li>{@link #EMAIL email}
 *   <li>{@link #EMAIL_VERIFIED email_verified}
 *   <li>{@link #GENDER gender}
 *   <li>{@link #BIRTHDATE birthdate}
 *   <li>{@link #ZONEINFO zoneinfo}
 *   <li>{@link #LOCALE locale}
 *   <li>{@link #PHONE_NUMBER phone_number}
 *   <li>{@link #PHONE_NUMBER_VERIFIED phone_number_verified}
 *   <li>{@link #ADDRESS address}
 *   <li>{@link #UPDATED_AT updated_at}
 * </ol>
 * </blockquote>
 *
 * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
 *      >OpenID Connect Core 1.0, 5.1. Standard Claims</a>
 *
 * @author Takahiko Kawasaki
 */
public final class StandardClaims
{
    /**
     * Subject - Identifier for the End-User at the Issuer.
     *
     * <p>
     * {@code "sub", string}.
     * </p>
     */
    public static final String SUB = "sub";


    /**
     * End-User's full name in displayable form including all name parts,
     * possibly including titles and suffixes, ordered according to the
     * End-User's locale and preferences.
     *
     * <p>
     * {@code "name", string}.
     * </p>
     */
    public static final String NAME = "name";


    /**
     * Given name(s) or first name(s) of the End-User. Note that in some
     * cultures, people can have multiple given names; all can be present,
     * with the names being separated by space characters.
     *
     * <p>
     * {@code "given_name", string}.
     * </p>
     */
    public static final String GIVEN_NAME = "given_name";


    /**
     * Surname(s) or last name(s) of the End-User. Note that in some
     * cultures, people can have multiple family names or no family
     * name; all can be present, with the names being separated by
     * space characters.
     *
     * <p>
     * {@code "family_name", string}.
     * </p>
     */
    public static final String FAMILY_NAME = "family_name";


    /**
     * Middle name(s) of the End-User. Note that in some cultures, people
     * can have multiple middle names; all can be present, with the names
     * being separated by space characters. Also note that in some cultures,
     * middle names are not used.
     *
     * <p>
     * {@code "middle_name", string}.
     * </p>
     */
    public static final String MIDDLE_NAME = "middle_name";


    /**
     * Casual name of the End-User that may or may not be the same as
     * the given_name. For instance, a nickname value of Mike might be
     * returned alongside a given_name value of Michael.
     *
     * <p>
     * {@code "nickname", string}.
     * </p>
     */
    public static final String NICKNAME = "nickname";


    /**
     * Shorthand name by which the End-User wishes to be referred to at
     * the RP, such as janedoe or j.doe. This value MAY be any valid JSON
     * string including special characters such as @, /, or whitespace.
     * The RP MUST NOT rely upon this value being unique, as discussed
     * in Section 5.7.
     *
     * <p>
     * {@code "preferred_username", string}.
     * </p>
     */
    public static final String PREFERRED_USERNAME = "preferred_username";


    /**
     * URL of the End-User's profile page. The contents of this Web page
     * SHOULD be about the End-User.
     *
     * <p>
     * {@code "profile", string}.
     * </p>
     */
    public static final String PROFILE = "profile";


    /**
     * URL of the End-User's profile picture. This URL MUST refer to an
     * image file (for example, a PNG, JPEG, or GIF image file), rather
     * than to a Web page containing an image. Note that this URL SHOULD
     * specifically reference a profile photo of the End-User suitable
     * for displaying when describing the End-User, rather than an
     * arbitrary photo taken by the End-User.
     *
     * <p>
     * {@code "picture", string}.
     * </p>
     */
    public static final String PICTURE = "picture";


    /**
     * URL of the End-User's Web page or blog. This Web page SHOULD
     * contain information published by the End-User or an organization
     * that the End-User is affiliated with.
     *
     * <p>
     * {@code "website", string}.
     * </p>
     */
    public static final String WEBSITE = "website";


    /**
     * End-User's preferred e-mail address. Its value MUST conform to
     * the RFC 5322 [RFC5322] addr-spec syntax. The RP MUST NOT rely
     * upon this value being unique, as discussed in Section 5.7 Claim
     * Stability and Uniqueness.
     *
     * <p>
     * {@code "email", string}.
     * </p>
     */
    public static final String EMAIL = "email";


    /**
     * True if the End-User's e-mail address has been verified; otherwise
     * false. When this Claim Value is true, this means that the OP took
     * affirmative steps to ensure that this e-mail address was controlled
     * by the End-User at the time the verification was performed. The
     * means by which an e-mail address is verified is context-specific,
     * and dependent upon the trust framework or contractual agreements
     * within which the parties are operating.
     *
     * <p>
     * {@code "email_verified", boolean}.
     * </p>
     */
    public static final String EMAIL_VERIFIED = "email_verified";


    /**
     * End-User's gender. Values defined by this specification are female
     * and male. Other values MAY be used when neither of the defined
     * values are applicable.
     *
     * <p>
     * {@code "gender", string}.
     * </p>
     */
    public static final String GENDER = "gender";


    /**
     * End-User's birthday, represented as an ISO 8601:2004 [ISO8601-2004]
     * YYYY-MM-DD format. The year MAY be 0000, indicating that it is
     * omitted. To represent only the year, YYYY format is allowed. Note
     * that depending on the underlying platform's date related function,
     * providing just year can result in varying month and day, so the
     * implementers need to take this factor into account to correctly
     * process the dates.
     *
     * <p>
     * {@code "birthdate", string}.
     * </p>
     */
    public static final String BIRTHDATE = "birthdate";


    /**
     * String from zoneinfo [zoneinfo] time zone database representing
     * the End-User's time zone. For example, Europe/Paris or
     * America/Los_Angeles.
     *
     * <p>
     * {@code "zoneinfo", string}.
     * </p>
     */
    public static final String ZONEINFO = "zoneinfo";


    /**
     * End-User's locale, represented as a BCP47 [RFC5646] language tag.
     * This is typically an ISO 639-1 Alpha-2 [ISO639-1] language code
     * in lowercase and an ISO 3166-1 Alpha-2 [ISO3166-1] country code
     * in uppercase, separated by a dash. For example, en-US or fr-CA.
     * As a compatibility note, some implementations have used an
     * underscore as the separator rather than a dash, for example,
     * en_US; Relying Parties MAY choose to accept this locale syntax
     * as well.
     *
     * <p>
     * {@code "locale", string}.
     * </p>
     */
    public static final String LOCALE = "locale";


    /**
     * End-User's preferred telephone number. E.164 [E.164] is
     * RECOMMENDED as the format of this Claim, for example, +1 (425)
     * 555-1212 or +56 (2) 687 2400. If the phone number contains an
     * extension, it is RECOMMENDED that the extension be represented
     * using the RFC 3966 [RFC3966] extension syntax, for example,
     * +1 (604) 555-1234;ext=5678.
     *
     * <p>
     * {@code "phone_number", string}.
     * </p>
     */
    public static final String PHONE_NUMBER = "phone_number";


    /**
     * True if the End-User's phone number has been verified; otherwise
     * false. When this Claim Value is true, this means that the OP took
     * affirmative steps to ensure that this phone number was controlled
     * by the End-User at the time the verification was performed. The
     * means by which a phone number is verified is context-specific,
     * and dependent upon the trust framework or contractual agreements
     * within which the parties are operating. When true, the phone_number
     * Claim MUST be in E.164 format and any extensions MUST be represented
     * in RFC 3966 format.
     *
     * <p>
     * {@code "phone_number_verified", boolean}.
     * </p>
     */
    public static final String PHONE_NUMBER_VERIFIED = "phone_number_verified";


    /**
     * End-User's preferred postal address. The value of the address member
     * is a JSON [RFC4627] structure containing some or all of the members
     * defined in Section 5.1.1.
     *
     * <p>
     * {@code "address", JSON object}.
     * </p>
     */
    public static final String ADDRESS = "address";


    /**
     * Time the End-User's information was last updated. Its value is a
     * JSON number representing the number of seconds from 1970-01-01T0:0:0Z
     * as measured in UTC until the date/time.
     *
     * <p>
     * {@code "updated_at", number}.
     * </p>
     */
    public static final String UPDATED_AT = "updated_at";


    /**
     * Set of claim names.
     */
    private static final SortedSet<String> sStandardClaims
        = createStandardClaims();


    private static final Map<String, Integer> sClaimToNumberMap
        = createClaimToNumberMap();


    private static final String[] sValues = createValues();


    private static SortedSet<String> createStandardClaims()
    {
        SortedSet<String> set = new TreeSet<String>();

        set.add(SUB);
        set.add(NAME);
        set.add(GIVEN_NAME);
        set.add(FAMILY_NAME);
        set.add(MIDDLE_NAME);
        set.add(NICKNAME);
        set.add(PREFERRED_USERNAME);
        set.add(PROFILE);
        set.add(PICTURE);
        set.add(WEBSITE);
        set.add(EMAIL);
        set.add(EMAIL_VERIFIED);
        set.add(GENDER);
        set.add(BIRTHDATE);
        set.add(ZONEINFO);
        set.add(LOCALE);
        set.add(PHONE_NUMBER);
        set.add(PHONE_NUMBER_VERIFIED);
        set.add(ADDRESS);
        set.add(UPDATED_AT);

        return Collections.unmodifiableSortedSet(set);
    }


    private static Map<String, Integer> createClaimToNumberMap()
    {
        Map<String, Integer> map = new HashMap<String, Integer>();

        put(map, SUB, 1);
        put(map, NAME, 2);
        put(map, GIVEN_NAME, 3);
        put(map, FAMILY_NAME, 4);
        put(map, MIDDLE_NAME, 5);
        put(map, NICKNAME, 6);
        put(map, PREFERRED_USERNAME, 7);
        put(map, PROFILE, 8);
        put(map, PICTURE, 9);
        put(map, WEBSITE, 10);
        put(map, EMAIL, 11);
        put(map, EMAIL_VERIFIED, 12);
        put(map, GENDER, 13);
        put(map, BIRTHDATE, 14);
        put(map, ZONEINFO, 15);
        put(map, LOCALE, 16);
        put(map, PHONE_NUMBER, 17);
        put(map, PHONE_NUMBER_VERIFIED, 18);
        put(map, ADDRESS, 19);
        put(map, UPDATED_AT, 20);

        return Collections.unmodifiableMap(map);
    }


    private static void put(Map<String, Integer> map, String claim, int number)
    {
        map.put(claim, Integer.valueOf(number));
    }


    private static String[] createValues()
    {
        return new String[] {
            SUB, NAME, GIVEN_NAME, FAMILY_NAME, MIDDLE_NAME, NICKNAME,
            PREFERRED_USERNAME, PROFILE, PICTURE, WEBSITE, EMAIL,
            EMAIL_VERIFIED, GENDER, BIRTHDATE, ZONEINFO, LOCALE,
            PHONE_NUMBER, PHONE_NUMBER_VERIFIED, ADDRESS, UPDATED_AT
        };
    }


    private StandardClaims()
    {
    }


    /**
     * Check if the given claim name is in the list described in
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >"OpenID Connect Core 1&#46;0, 5&#46;1&#46; Standard Claims"</a>.
     */
    public static boolean isStandardClaim(String claimName)
    {
        if (claimName == null)
        {
            return false;
        }

        return sStandardClaims.contains(claimName);
    }


    /**
     * Get the claim list described in
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >"OpenID Connect Core 1&#46;0, 5&#46;1&#46; Standard Claims"</a>.
     */
    public static SortedSet<String> getStandardClaims()
    {
        return sStandardClaims;
    }


    public static int toBits(Set<String> set)
    {
        if (set == null)
        {
            return 0;
        }

        int bits = 0;

        for (String claim : set)
        {
            Integer number = sClaimToNumberMap.get(claim);

            if (number != null)
            {
                bits |= (1 << number);
            }
        }

        return bits;
    }


    public static Set<String> toSet(int bits)
    {
        Set<String> set = new HashSet<String>();

        for (int i = 0; i < sValues.length; ++i)
        {
            if ((bits & (1 << (i + 1))) != 0)
            {
                set.add(sValues[i]);
            }
        }

        return set;
    }
}
