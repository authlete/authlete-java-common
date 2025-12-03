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
     * The format of the TSL. Possible values are jwt and cwt. Currently only jwt is supported
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private TslPublishFormat format;

    /**
     * The validity of the TSL in hours.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private long validity;

    /**
     * Publish TSL after every X hours.
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
    public TslConfigData()
    {
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

    /**
     * Sets the publish format for the TSL configuration.
     *
     * @param format the {@link TslPublishFormat} value to set
     * @return this {@code TslConfigData} instance for method chaining
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslConfigData setFormat(TslPublishFormat format)
    {
        this.format = format;

        return this;
    }

    /**
     * Returns the publish format of the TSL configuration.
     *
     * @return the {@link TslPublishFormat} value currently configured
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslPublishFormat getFormat()
    {
        return format;
    }

    /**
     * Sets the TSL validity in hours.
     *
     * @param TSL validity in hours
     *
     * @return this {@code TslConfigData} instance for method chaining
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslConfigData setValidity(long validity)
    {
        this.validity = validity;

        return this;
    }

    /**
     * Returns the TSL validity in hours.
     *
     * @return TSL validity
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public long getValidity()
    {
        return validity;
    }

    /**
     * Sets the TSL publishing frequency in every X hours.
     *
     * @param TSL publish frequency
     *
     * @return this {@code TslConfigData} instance for method chaining
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslConfigData setPublishFrequency(long publishFrequency)
    {
        this.publishFrequency = publishFrequency;

        return this;
    }

    /**
     * Returns the TSL publishing frequency.
     *
     * @return TSL publishing frequency
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public long getPublishFrequency()
    {
        return publishFrequency;
    }

    /**
     * Sets the ttl value of TSL in hours.
     *
     * @param ttl value
     *
     * @return this {@code TslConfigData} instance for method chaining
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslConfigData setTimeToLive(long timeToLive)
    {
        this.timeToLive = timeToLive;

        return this;
    }

    /**
     * Returns the ttl value.
     *
     * @return ttl value
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public long getTimeToLive()
    {
        return timeToLive;
    }

    /**
     * Sets the TSL publishing endpoint.
     *
     * @param publish endpoint
     *
     * @return this {@code TslConfigData} instance for method chaining
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslConfigData setPublishEndpoint(URI publishEndpoint)
    {
        this.publishEndpoint = publishEndpoint;

        return this;
    }

    /**
     * Returns the TSL publishing endpoint.
     *
     * @return publishing endpoint
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public URI getPublishEndpoint()
    {
        return publishEndpoint;
    }
}