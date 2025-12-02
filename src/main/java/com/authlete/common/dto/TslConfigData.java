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

    /**
     * Sets the TSL publish format.
     *
     * @param format the {@link TslPublishFormat} to use for publishing.
     *
     * @return this {@link TslConfigData} instance for method chaining
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
     * Returns the configured TSL publish format.
     *
     * @return the {@link TslPublishFormat} in use.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslPublishFormat getFormat()
    {
        return format;
    }

    /**
     * Sets the validity period for the TSL in hours.
     *
     * @param validity the validity duration
     * @return this {@link TslConfigData} instance for method chaining
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public TslConfigData setValidity(long validity) {
        this.validity = validity;

        return this;
    }

    /**
     * Returns the validity period of the TSL.
     *
     * @return the validity duration
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public long getValidity()
    {
        return validity;
    }

    /**
     * Sets the frequency in hours at which the TSL should be published.
     *
     * @param publishFrequency the publishing frequency
     * @return this {@link TslConfigData} instance for method chaining
     *
     * @since 4.31
     * @since Authlete 3.0.22
     *
     */
    public TslConfigData setPublishFrequency(long publishFrequency)
    {
        this.publishFrequency = publishFrequency;

        return this;
    }

    /**
     * Returns the publish frequency of the TSL.
     *
     * @return the publishing frequency
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public long getPublishFrequency()
    {
        return publishFrequency;
    }

    /**
     * Sets the time to live in hours which provides indication to verifiers to cache this TSL
     *
     * @param timeToLive the TTL value
     * @return this {@link TslConfigData} instance for method chaining
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
     * Returns the time-to-live (TTL) in hours value for the TSL.
     *
     * @return the TTL value
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public long getTimeToLive()
    {
        return timeToLive;
    }

    /**
     * Sets the endpoint URI where the TSL will be published.
     *
     * @param publishEndpoint the publish endpoint URI.
     *
     * @return this {@link TslConfigData} instance for method chaining
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
     * Returns the endpoint URI where the TSL is published.
     *
     * @return the publish endpoint URI.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    public URI getPublishEndpoint()
    {
        return publishEndpoint;
    }
}