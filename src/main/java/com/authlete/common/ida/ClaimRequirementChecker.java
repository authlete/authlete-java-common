/*
 * Copyright (C) 2022-2024 Authlete, Inc.
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
package com.authlete.common.ida;


import static java.time.ZoneOffset.UTC;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


/**
 * A utility to check whether a value satisfies a requirement.
 *
 * @since 3.17
 */
class ClaimRequirementChecker
{
    /**
     * Additional datetime formats to support which are different from
     * the default ones supported by LocalDateTime and OffsetDateTime.
     */
    private static final List<DateTimeFormatter> DATETIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX")
    );


    private final ClaimRequirement mRequirement;
    private final OffsetDateTime mCurrentTime;


    /**
     * A constructor with a requirement for a claim.
     *
     * <p>
     * This constructor is an alias of <code>{@link
     * #ClaimRequirementChecker(ClaimRequirement, OffsetDateTime)
     * ClaimRequirement}(requirement, null)</code>.
     * </p>
     *
     * @param requirement
     *         A requirement for a claim.
     */
    public ClaimRequirementChecker(ClaimRequirement requirement)
    {
        this(requirement, null);
    }


    /**
     * A constructor with a requirement and a time which is used to process
     * the {@code "max_age"} constraint.
     *
     * @param requirement
     *         A requirement. Must not be {@code null}.
     *
     * @param currentTime
     *         A time which is used to process the {@code "max_age"} constraint.
     *         If {@code null} is given, <code>{@link OffsetDateTime}.{@link
     *         OffsetDateTime#now(java.time.ZoneId) now}({@link
     *         java.time.ZoneOffset#UTC UTC}) is used.
     *
     * @throws IllegalArgumentException
     *         {@code requirement} is {@code null}.
     */
    public ClaimRequirementChecker(
            ClaimRequirement requirement, OffsetDateTime currentTime)
    {
        if (requirement == null)
        {
            throw new IllegalArgumentException("'requirement' must not be null.");
        }

        mRequirement = requirement;
        mCurrentTime = (currentTime != null)
                     ? currentTime : OffsetDateTime.now(UTC);
    }


    /**
     * Get the requirement given to the constructor.
     *
     * @return
     *         The requirement given to the constructor.
     */
    private ClaimRequirement getRequirement()
    {
        return mRequirement;
    }


    /**
     * Get the time which is used to process the {@code "max_age"} constraint.
     * The value is the one given to or initialized in the constructor.
     *
     * @return
     *         The time which is used to process the {@code "max_age"}
     *         constraint.
     */
    private OffsetDateTime getCurrentTime()
    {
        return mCurrentTime;
    }


    /**
     * Check the given value satisfies constraints of the requirement.
     *
     * @param value
     *         The target value to check.
     *
     * @return
     *         {@code true} if the given value satisfies all the constraints
     *         of the requirement.
     */
    public boolean check(Object value)
    {
        ClaimRequirement requirement = getRequirement();

        boolean satisfied = true;

        // NOTE for the "essential" constraint:
        //
        // From a filtering point of view, the "essential" constraint is
        // meaningless because even if "essential":true is specified and the
        // said claim is unavailable, the authorization server must not treat
        // the case as an error.
        //
        //   OpenID Connect Core 1.0 / 5.5.1. Individual Claims Requests
        //
        //     essential
        //       OPTIONAL. Indicates whether the Claim being requested is an
        //       Essential Claim. If the value is true, this indicates that the
        //       Claim is an Essential Claim. For instance, the Claim request:
        //
        //         "auth_time": {"essential": true}
        //
        //       can be used to specify that it is Essential to return an
        //       auth_time Claim Value.
        //
        //       If the value is false, it indicates that it is a Voluntary
        //       Claim. The default is false.
        //
        //       By requesting Claims as Essential Claims, the RP indicates to
        //       the End-User that releasing these Claims will ensure a smooth
        //       authorization for the specific task requested by the End-User.
        //       Note that even if the Claims are not available because the
        //       End-User did not authorize their release or they are not
        //       present, the Authorization Server MUST NOT generate an error
        //       when Claims are not returned, whether they are Essential or
        //       Voluntary, unless otherwise specified in the description of
        //       the specific claim.
        //
        // Therefore, we do nothing here for requirement.isEssential().

        // If the requirement contains "value".
        if (requirement.getValue() != null)
        {
            // True if the value satisfies the "value" constraint.
            satisfied &= checkWithValue(value, requirement.getValue());
        }

        // If the requirement contains "values".
        if (satisfied && requirement.getValues() != null)
        {
            // True if the value satisfies the "values" constraint.
            satisfied &= checkWithValues(value, requirement.getValues());
        }

        // If the requirement contains "max_age".
        if (satisfied && requirement.getMaxAge() != null)
        {
            // True if the value satisfies the "max_age" constraint.
            satisfied &= checkWithMaxAge(
                    value, requirement.getMaxAge(), getCurrentTime());
        }

        // NOTE for the "purpose" property:
        //
        // The "purpose" property is not a constraint. It does not affect
        // the result of this requirement check.
        //
        // Therefore, We do nothing here for requirement.getPurpose().

        return satisfied;
    }


    /**
     * When the given object is a {@code String} instance, the object is
     * returned after being cast by {@code (String)}. In other cases,
     * {@code null} is returned.
     */
    private static String interpretAsString(Object value)
    {
        // If the given object is a String instance.
        if (value instanceof String)
        {
            return (String)value;
        }

        // Failed to interpret the given value as a string.
        return null;
    }


    /**
     * Check if the given value satisfies the {@code "value"} constraint.
     */
    private static boolean checkWithValue(Object value, String expected)
    {
        // Interpret the value as a string.
        String actual = interpretAsString(value);

        // If the value could not be interpreted as a string.
        if (actual == null)
        {
            // Not match.
            return false;
        }

        // True if the actual value matches the 'expected' value.
        return actual.equals(expected);
    }


    /**
     * Check if the given value satisfies the {@code "values"} constraint.
     */
    private static boolean checkWithValues(Object value, List<String> expected)
    {
        // Interpret the value as a JSON string.
        String actual = interpretAsString(value);

        // If the value could not be interpreted as a string.
        if (actual == null)
        {
            // Not match.
            return false;
        }

        // True if any of the elements in the 'expected' array matches
        // the actual value.
        return expected.stream().anyMatch(element -> actual.equals(element));
    }


    /**
     * Check if the given value satisfies the {@code "max_age"} constraint.
     */
    private static boolean checkWithMaxAge(
            Object value, Long maxAge, OffsetDateTime currentTime)
    {
        // Parse the value as a date time.
        OffsetDateTime origin = parseAsDateTime(value);

        // If the value could not be interpreted as a date time.
        if (origin == null)
        {
            // Not match.
            return false;
        }

        // Compute the expiration date time.
        OffsetDateTime expiration = origin.plusSeconds(maxAge);

        // True if the current time has not reached the expiration date time.
        return currentTime.isBefore(expiration);
    }


    /**
     * Parse the given value as a date time.
     */
    private static OffsetDateTime parseAsDateTime(Object value)
    {
        // Interpret the value as a string.
        String string = interpretAsString(value);

        // If the value could not be interpreted as a string.
        if (string == null)
        {
            return null;
        }

        // If the length of the string is equal to or less than
        // the length of "YYYY-MM-DD".
        if (string.length() <= 10)
        {
            try
            {
                // Parse as date without offset.
                return LocalDate.parse(string).atTime(0, 0).atOffset(UTC);
            }
            catch (Exception cause)
            {
                // Won't try to parse the string as date+time because
                // it will fail anyway.
                return null;
            }
        }

        try
        {
            // Parse as date+time without offset.
            return LocalDateTime.parse(string).atOffset(UTC);
        }
        catch (Exception cause)
        {
        }

        try
        {
            // Parse as date+time with offset.
            return OffsetDateTime.parse(string);
        }
        catch (Exception cause)
        {
        }

        // Try additional datetime formats to support which are different from
        // the default ones supported by LocalDateTime and OffsetDateTime.
        for (DateTimeFormatter formatter : DATETIME_FORMATTERS)
        {
            try
            {
                // Parse the string with the formatter.
                return OffsetDateTime.parse(string, formatter);
            }
            catch (Exception cause)
            {
            }
        }

        // The value could not be interpreted as a date time.
        return null;
    }
}
