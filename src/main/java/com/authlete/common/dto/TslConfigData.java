package com.authlete.common.dto;

import com.authlete.common.types.TslPublishFormat;

import java.io.Serializable;
import java.net.URI;

/**
 * A class that represents TSL configurations data
 * The set consists of the following.
 *
 * <ul>
 * <li>{@code format}
 * <li>{@code validity}
 * <li>{@code publishFrequency}
 * <li>{@code timeToLive}
 * <li>{@code publishEndpoint}
 * </ul>
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/13/"
 *      >Token Status List (TSL)</a>
 */
public class TslConfigData implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * The format of the TSL. Possible values are JWT and CWT. Currently only JWT is supported
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private TslPublishFormat format;

    /**
     * The validity of the TSL in hours
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private long validity;

    /**
     * Publish TSL after every X hours
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private long publishFrequency;

    /**
     * Time to live in hours which provides indication to verifiers to cache this TSL
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private long timeToLive;

    /**
     * Endpoint where to publish this TSL
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private URI publishEndpoint;

    /**
     * The default constructor.
     */
    public TslConfigData() {
    }

    /**
     * Copy constructor.
     */
    public TslConfigData(TslConfigData tslConfigData)
    {
        if (tslConfigData == null)
        {
            return;
        }
        format = tslConfigData.getFormat();
        validity = tslConfigData.getValidity();
        publishFrequency = tslConfigData.getPublishFrequency();
        timeToLive = tslConfigData.getTimeToLive();
        publishEndpoint = tslConfigData.getPublishEndpoint();
    }

    public TslConfigData setFormat(TslPublishFormat format)
    {
        this.format = format;

        return this;
    }

    public TslPublishFormat getFormat()
    {
        return format;
    }

    public TslConfigData setValidity(long validity) {
        this.validity = validity;

        return this;
    }

    public long getValidity()
    {
        return validity;
    }

    public TslConfigData setPublishFrequency(long publishFrequency)
    {
        this.publishFrequency = publishFrequency;

        return this;
    }

    public long getPublishFrequency()
    {
        return publishFrequency;
    }

    public TslConfigData setTimeToLive(long timeToLive)
    {
        this.timeToLive = timeToLive;

        return this;
    }

    public long getTimeToLive()
    {
        return timeToLive;
    }

    public TslConfigData setPublishEndpoint(URI publishEndpoint)
    {
        this.publishEndpoint = publishEndpoint;

        return this;
    }

    public URI getPublishEndpoint()
    {
        return publishEndpoint;
    }
}