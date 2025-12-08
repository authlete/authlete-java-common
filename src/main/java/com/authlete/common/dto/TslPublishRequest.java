package com.authlete.common.dto;

import java.io.Serializable;

/**
 * Request to Authlete's {@code /service/tsl/publish} API.
 *
 * <p>
 * This class represents a request to publish a Token Status List (TSL) for
 * a specific service. The request contains the {@code serviceNumber}, which
 * uniquely identifies the service whose TSL is to be published.
 * </p>
 *
 * <p>
 * For more details about Token Status Lists (TSL), see:
 * </p>
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/">
 *      Token Status List (TSL) Specification</a>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslPublishRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The service number whose TSL is to be published.
     *
     * <p>
     * This uniquely identifies the service within Authlete's environment. When
     * this request is processed, the TSL for this service will be published.
     * </p>
     */
    private int serviceNumber;

    /**
     * Set the service number whose TSL is to be published.
     *
     * @param serviceNumber
     *         The service number to publish the TSL for.
     *
     * @return
     *         {@code this} object.
     */
    public TslPublishRequest setServiceNumber(int serviceNumber)
    {
        this.serviceNumber = serviceNumber;

        return this;
    }

    /**
     * Get the service number whose TSL is to be published.
     *
     * @return
     *          The service number.
     */
    public int getServiceNumber()
    {
        return serviceNumber;
    }
}
