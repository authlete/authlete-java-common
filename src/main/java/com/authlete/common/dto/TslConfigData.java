package com.authlete.common.dto;

import com.authlete.common.types.TslFormat;

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
 * @since 4.33
 * @since Authlete 3.0.22
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/"
 *      >Token Status List (TSL)</a>
 */
public class TslConfigData implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * The format of the TSL. Possible values are jwt and cwt. Currently only jwt is supported
     */
    private TslFormat format;

    /**
     * The validity of the TSL in hours.
     */
    private long validity;

    /**
     * Publish TSL after every X hours.
     */
    private long publishFrequency;

    /**
     * Time to live in hours which provides indication to verifiers to cache this TSL
     */
    private long timeToLive;

    /**
     * Endpoint where to publish this TSL
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
     * Sets the publishing format for the TSL configuration.
     *
     * @param format
     *          the {@link TslFormat} value to set
     *
     * @return
     *          this {@code TslConfigData} instance for method chaining
     */
    public TslConfigData setFormat(TslFormat format)
    {
        this.format = format;

        return this;
    }

    /**
     * Returns the publishing format of the TSL configuration.
     *
     * @return
     *          the {@link TslFormat} value currently configured
     */
    public TslFormat getFormat()
    {
        return format;
    }

    /**
     * Sets the TSL validity in hours.
     *
     * @param validity
     *          validity in hours
     *
     * @return
     *          this {@code TslConfigData} instance for method chaining
     */
    public TslConfigData setValidity(long validity)
    {
        this.validity = validity;

        return this;
    }

    /**
     * Returns the TSL validity in hours.
     *
     * @return
     *          TSL validity
     */
    public long getValidity()
    {
        return validity;
    }

    /**
     * Sets the TSL publishing frequency in every X hours.
     *
     * @param publishFrequency
     *          TSL publish frequency
     *
     * @return
     *          this {@code TslConfigData} instance for method chaining
     */
    public TslConfigData setPublishFrequency(long publishFrequency)
    {
        this.publishFrequency = publishFrequency;

        return this;
    }

    /**
     * Returns the TSL publishing frequency.
     *
     * @return
     *          TSL publishing frequency
     */
    public long getPublishFrequency()
    {
        return publishFrequency;
    }

    /**
     * Sets the ttl value of TSL in hours.
     *
     * @param timeToLive
     *          the ttl value
     *
     * @return
     *          this {@code TslConfigData} instance for method chaining
     */
    public TslConfigData setTimeToLive(long timeToLive)
    {
        this.timeToLive = timeToLive;

        return this;
    }

    /**
     * Returns the ttl value.
     *
     * @return
     *          ttl value
     */
    public long getTimeToLive()
    {
        return timeToLive;
    }

    /**
     * Sets the TSL publishing endpoint.
     *
     * @param publishEndpoint
     *          TSL publish endpoint
     *
     * @return
     *          this {@code TslConfigData} instance for method chaining
     */
    public TslConfigData setPublishEndpoint(URI publishEndpoint)
    {
        this.publishEndpoint = publishEndpoint;

        return this;
    }

    /**
     * Returns the TSL publishing endpoint.
     *
     * @return
     *          TSL publishing endpoint
     */
    public URI getPublishEndpoint()
    {
        return publishEndpoint;
    }
}