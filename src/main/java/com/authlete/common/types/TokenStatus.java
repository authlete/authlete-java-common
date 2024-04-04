/*
 * Copyright (C) 2024 Authlete, Inc.
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


/**
 * TokenStatus filtering options for using in getTokenList API.
 * {@link com.authlete.common.api.AuthleteApi#getTokenList(TokenStatus)}
 * {@link com.authlete.common.api.AuthleteApi#getTokenList(int, int, TokenStatus)}
 * {@link com.authlete.common.api.AuthleteApi#getTokenList(String, String, TokenStatus)}
 * {@link com.authlete.common.api.AuthleteApi#getTokenList(String, String, int, int, TokenStatus)}
 *
 * @since 3.96
 */
public enum TokenStatus
{
    // All valid tokens
    VALID,
    // All invalid tokens
    INVALID,
    // All tokens
    ALL
}
