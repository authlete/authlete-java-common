package com.authlete.common.dto;

/**
 * Represents the Token Status List (TSL) publish configuration for a service.
 *
 * <p>
 * This class contains per-service settings related to TSL publication, including
 * the service identifier and the Unix timestamp (in seconds) indicating when the
 * next TSL will be published.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslPublishConfig
{

    private static final long serialVersionUID = 1L;

    /**
     * The service number for which the TSL publication schedule applies.
     *
     * <p>
     * This uniquely identifies the service whose TSL will be published at
     * the configured next publish time.
     * </p>
     */
    private int serviceNumber;

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
     * Set the service number associated with this TSL publish configuration.
     *
     * @param serviceNumber
     *         The service number.
     *
     * @return
     *         {@code this} object.
     */
    public TslPublishConfig setServiceNumber(int serviceNumber)
    {
        this.serviceNumber = serviceNumber;

        return this;
    }

    /**
     * Get the service number associated with this TSL publish configuration.
     *
     * @return
     *          The service number.
     */
    public int getServiceNumber()
    {
        return serviceNumber;
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
    public TslPublishConfig setNextTslPublishTime(long nextTslPublishTime)
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
}
