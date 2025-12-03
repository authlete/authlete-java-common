package com.authlete.common.dto;

import java.io.Serializable;

/**
 * Request to Authlete's {@code /tsl/publish} API.
 *
 * <p>
 * This class represents a request to retrieve a published Token Status List (TSL)
 * for a specific service. The request requires the caller to specify the
 * {@code serviceNumber}, which uniquely identifies the service whose TSL is
 * being requested.
 * </p>
 *
 * <p>
 * For more details about Token Status Lists (TSL), see:
 * </p>
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/13/">
 *      Token Status List (TSL) Specification</a>
 *
 * @since 4.31
 * @since Authlete 3.0.22
 */
public class TslGetRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The service number for which the TSL is requested.
     *
     * <p>
     * This value uniquely identifies a service within Authlete's environment.
     * The TSL associated with this service number will be retrieved when the
     * request is processed.
     * </p>
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int serviceNumber;

    /**
     * Set the service number whose Token Status List (TSL) should be retrieved.
     *
     * @param serviceNumber
     *         The service number to query.
     */
    public void setServiceNumber(int serviceNumber)
    {
        this.serviceNumber = serviceNumber;
    }

    /**
     * Get the service number whose Token Status List (TSL) is being requested.
     *
     * @return The service number.
     */
    public int getServiceNumber()
    {
        return serviceNumber;
    }
}
