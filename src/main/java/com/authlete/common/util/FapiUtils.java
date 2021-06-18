/*
 * Copyright (C) 2021 Authlete, Inc.
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


import java.util.UUID;


/**
 * Utilities for Financial-grade API (FAPI).
 *
 * @since 2.94
 *
 * @see <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html"
 *      >Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>
 *
 * @see <a href="https://openid.net/specs/openid-financial-api-part-2-1_0-final.html"
 *      >Financial-grade API Security Profile 1.0 - Part 2: Advanced</a>
 */
public class FapiUtils
{
    /**
     * {@code x-fapi-auth-date}
     *
     * <p>
     * From <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#client-provisions"
     * >6.2.2. Client provisions</a> of <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html"
     * >Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>:
     * </p>
     * <blockquote>
     * <ol start="3">
     * <li>
     * may send the last time the customer logged into the client in the
     * {@code x-fapi-auth-date} header where the value is supplied as a
     * HTTP-date as in Section 7.1.1.1 of RFC7231, e.g.,
     * {@code x-fapi-auth-date: Tue, 11 Sep 2012 19:43:31 GMT};
     * </ol>
     * </blockquote>
     *
     * @see <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#client-provisions"
     *      >Financial-grade API Security Profile 1.0 - Part 1: Baseline, 6.2.2. Client provisions</a>
     */
    public static final String X_FAPI_AUTH_DATE = "x-fapi-auth-date";


    /**
     * {@code x-fapi-customer-ip-address}
     *
     * <p>
     * From <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#protected-resources-provisions"
     * >6.2.1. Protected resources provisions</a> of <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html"
     * >Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>:
     * </p>
     * <blockquote>
     * <ol start="13">
     * <li>
     * shall not reject requests with a {@code x-fapi-customer-ip-address}
     * header containing a valid IPv4 or IPv6 address.
     * </ol>
     * </blockquote>
     *
     * <p>
     * From <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#client-provisions"
     * >6.2.2. Client provisions</a> of <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html"
     * >Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>:
     * </p>
     * <blockquote>
     * <ol start="4">
     * <li>
     * may send the customer’s IP address if this data is available in the
     * {@code x-fapi-customer-ip-address} header, e.g.,
     * {@code x-fapi-customer-ip-address: 2001:DB8::1893:25c8:1946} or
     * {@code x-fapi-customer-ip-address: 198.51.100.119}; and
     * </ol>
     * </blockquote>
     *
     * @see <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#protected-resources-provisions"
     *      >Financial-grade API Security Profile 1.0 - Part 1: Baseline, 6.2.1. Protected resources provisions</a>
     *
     * @see <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#client-provisions"
     *      >Financial-grade API Security Profile 1.0 - Part 1: Baseline, 6.2.2. Client provisions</a>
     */
    public static final String X_FAPI_CUSTOMER_IP_ADDRESS = "x-fapi-customer-ip-address";


    /**
     * {@code x-fapi-interaction-id}
     *
     * <p>
     * From <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#protected-resources-provisions"
     * >6.2.1. Protected resources provisions</a> of <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html"
     * >Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>:
     * </p>
     * <blockquote>
     * <ol start="11">
     * <li>
     * shall set the response header {@code x-fapi-interaction-id} to the value
     * received from the corresponding FAPI client request header or to a RFC4122
     * UUID value if the request header was not provided to track the interaction, e.g.,
     * {@code x-fapi-interaction-id: c770aef3-6784-41f7-8e0e-ff5f97bddb3a};
     * <li>
     * shall log the value of {@code x-fapi-interaction-id} in the log entry; and
     * </ol>
     * </blockquote>
     *
     * <p>
     * From <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#client-provisions"
     * >6.2.2. Client provisions</a> of <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html"
     * >Financial-grade API Security Profile 1.0 - Part 1: Baseline</a>:
     * </p>
     * <blockquote>
     * <ol start="5">
     * <li>
     * may send the {@code x-fapi-interaction-id} request header, in which case
     * the value shall be a RFC4122 UUID to the server to help correlate log
     * entries between client and server, e.g.,
     * {@code x-fapi-interaction-id: c770aef3-6784-41f7-8e0e-ff5f97bddb3a}.
     * </ol>
     * </blockquote>
     *
     * @see <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#protected-resources-provisions"
     *      >Financial-grade API Security Profile 1.0 - Part 1: Baseline, 6.2.1. Protected resources provisions</a>
     *
     * @see <a href="https://openid.net/specs/openid-financial-api-part-1-1_0-final.html#client-provisions"
     *      >Financial-grade API Security Profile 1.0 - Part 1: Baseline, 6.2.2. Client provisions</a>
     */
    public static final String X_FAPI_INTERACTION_ID = "x-fapi-interaction-id";


    /**
     * Compute the value suitable for the {@code x-fapi-interaction-id} HTTP
     * response header.
     *
     * @param incomingInteractionId
     *         The value of the {@code x-fapi-interaction-id} HTTP request header.
     *         This may be null.
     *
     * @return
     *         If {@code incomingInteractionId} is given, the same value is
     *         returned. Otherwise, a new value generated by
     *         {@link #generateInteractionId()} is returned.
     *
     * @throws IllegalArgumentException
     *         {@code incomingInteractionId} is not a valid UUID.
     */
    public static String computeOutgoingInteractionId(String incomingInteractionId) throws IllegalArgumentException
    {
        // If there is no 'x-fapi-interaction-id' request header or its value is empty.
        if (incomingInteractionId == null || incomingInteractionId.isEmpty())
        {
            // Generate a new interaction ID.
            return generateInteractionId();
        }

        // Make sure that the incoming interaction ID is a valid UUID.
        // If the format of the incoming interaction ID is wrong, an
        // IllegalArgumentExcepion is thrown.
        UUID.fromString(incomingInteractionId);

        // Use the same value.
        return incomingInteractionId;
    }


    /**
     * Generate a value suitable for the {@code x-fapi-interaction-id} HTTP header.
     *
     * @return
     *         A value suitable for the {@code x-fapi-interaction-id} HTTP header.
     */
    public static String generateInteractionId()
    {
        return UUID.randomUUID().toString();
    }
}
