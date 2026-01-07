package com.authlete.common.dto;

import com.authlete.common.types.TslFormat;

/**
 * Represents the Token Status List (TSL) publish configurations for a service.
 *
 * <p>
 * This class contains per-service settings related to TSL publication, including
 * the service ID, format and the Unix timestamp (in seconds) indicating when the
 * next TSL will be published.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslPublishConfigInfo
{

    private static final long serialVersionUID = 1L;

    /**
     * The service ID for which the TSL publication schedule applies.
     *
     * <p>
     * This uniquely identifies the service whose TSL will be published at
     * the configured next publish time.
     * </p>
     */
    private long serviceId;

    /**
     * The Unix timestamp (in seconds) indicating when the next TSL
     * will be published for this service.
     *
     * <p>
     * This value allows services to schedule periodic TSL publication.
     * </p>
     */
    private long nextTslPublishTime;

    /**
     * The TSL format of the TSL for this service.
     */
    private TslFormat format;

    /**
     * Set the service ID associated with this TSL publish configurations.
     *
     * @param serviceId
     *         The service ID.
     *
     * @return
     *         {@code this} object.
     */
    public TslPublishConfigInfo setServiceId(long serviceId)
    {
        this.serviceId = serviceId;

        return this;
    }

    /**
     * Get the service ID associated with this TSL publish configurations.
     *
     * @return
     *          The service ID.
     */
    public long getServiceId()
    {
        return serviceId;
    }

    /**
     * Set the Unix timestamp (in seconds) for the next TSL publication time.
     *
     * @param nextTslPublishTime
     *         The next publish time, in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public TslPublishConfigInfo setNextTslPublishTime(long nextTslPublishTime)
    {
        this.nextTslPublishTime = nextTslPublishTime;

        return this;
    }

    /**
     * Get the Unix timestamp (in seconds) for the next TSL publication time.
     *
     * @return The next publish time, in seconds.
     */
    public long getNextTslPublishTime()
    {
        return nextTslPublishTime;
    }

    /**
     * Sets the format of the TSL
     *
     * @param format
     *         The TSL format.
     *
     * @return
     *         {@code this} object.
     */
    public TslPublishConfigInfo setTslFormat(TslFormat format)
    {
        this.format = format;

        return this;
    }

    /**
     * Gets the format of the TSL.
     *
     * @return
     *         The TSL format.
     */
    public TslFormat getTslFormat()
    {
        return format;
    }

}
