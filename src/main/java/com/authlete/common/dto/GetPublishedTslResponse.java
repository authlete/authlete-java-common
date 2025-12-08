package com.authlete.common.dto;

import java.io.Serializable;

/**
 * Response from Authlete's {@code /service/tsl} API.
 *
 * <p>
 * This class represents the response returned when retrieving a Token Status
 * List (TSL) from Authlete API. The response includes the TSL format (e.g., JWT)
 * and the TSL content itself.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class GetPublishedTslResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The format of the TSL, such as {@code "jwt"}.
     */
    private String format;

    /**
     * The Token Status List (TSL).
     */
    private String tsl;

    /**
     * Set the format of the Token Status List (TSL).
     *
     * @param format
     *         The TSL format (e.g., {@code "jwt"}).
     *
     * @return
     *         {@code this} object.
     */
    public GetPublishedTslResponse setFormat(String format)
    {
        this.format = format;

        return this;
    }

    /**
     * Get the format of the Token Status List (TSL).
     *
     * @return
     *          The TSL format.
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
     *
     * @return
     *         {@code this} object.
     */
    public GetPublishedTslResponse setTsl(String tsl)
    {
        this.tsl = tsl;

        return this;
    }

    /**
     * Get the Token Status List (TSL).
     *
     * @return
     *          The TSL content.
     */
    public String getTsl()
    {
        return tsl;
    }
}
