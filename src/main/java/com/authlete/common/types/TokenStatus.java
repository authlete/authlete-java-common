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
