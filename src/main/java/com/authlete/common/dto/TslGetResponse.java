package com.authlete.common.dto;

import java.io.Serializable;

/**
 * Response from Authlete's {@code /service/tsl} API.
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 */
public class TslGetResponse implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * TSL format e.g. jwt.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private String format;

    /**
     * TSL
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private String tsl;

    public void setFormat(String format)
    {
        this.format = format;
    }

    public String getFormat()
    {
        return format;
    }

    public void setTsl(String tsl)
    {
        this.tsl = tsl;
    }

    public String getTsl()
    {
        return tsl;
    }
 }
