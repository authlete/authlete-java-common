package com.authlete.common.dto;

import java.io.Serializable;

/**
 * Response from Authlete's {@code /service/tsl} API.
 *
 * <p>
 * This class represents the response returned when retrieving a Token Status
 * List (TSL) from Authlete. The response includes the TSL format (e.g., JWT)
 * and the TSL content itself.
 * </p>
 *
 * @since 4.31
 * @since Authlete 3.0.22
 */
public class TslGetResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The format of the TSL, such as {@code "jwt"}.
     *
     * <p>
     * This value indicates how the TSL is encoded or represented.
     * </p>
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private String format;

    /**
     * The Token Status List (TSL) in the format specified by {@link #format}.
     *
     * <p>
     * The TSL is typically represented as a JWT or another supported encoding.
     * </p>
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private String tsl;

    /**
     * Set the format of the Token Status List (TSL).
     *
     * @param format
     *         The TSL format (e.g., {@code "jwt"}).
     */
    public void setFormat(String format)
    {
        this.format = format;
    }

    /**
     * Get the format of the Token Status List (TSL).
     *
     * @return The TSL format.
     */
    public String getFormat()
    {
        return format;
    }

    /**
     * Set the Token Status List (TSL).
     *
     * @param tsl
     *         The TSL content in the format specified by {@link #format}.
     */
    public void setTsl(String tsl)
    {
        this.tsl = tsl;
    }

    /**
     * Get the Token Status List (TSL).
     *
     * @return The TSL content.
     */
    public String getTsl()
    {
        return tsl;
    }
}
