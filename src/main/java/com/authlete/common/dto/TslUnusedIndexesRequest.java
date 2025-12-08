package com.authlete.common.dto;

import java.io.Serializable;

/**
 * Request to Authlete's {@code /service/tsl/unused/indexes} API.
 *
 * <p>
 * This class represents a request to pre-populate unused token indexes for a
 * particular service. These unused indexes are later consumed when issuing
 * Verifiable Credentials (VCs) or access tokens, allowing efficient allocation
 * of token indices within a Token Status List (TSL) environment.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslUnusedIndexesRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The service number for which unused token indexes should be populated.
     *
     * <p>
     * This value uniquely identifies the service under which the unused
     * indexes will be created.
     * </p>
     */
    private int serviceNumber;

    /**
     * Set the service number for which unused token indexes should be populated.
     *
     * @param serviceNumber
     *         The service number to populate indexes for.
     *
     * @return
     *         {@code this} object.
     */
    public TslUnusedIndexesRequest setServiceNumber(int serviceNumber)
    {
        this.serviceNumber = serviceNumber;

        return this;
    }

    /**
     * Get the service number for which unused token indexes should be populated.
     *
     * @return
     *          The service number.
     */
    public int getServiceNumber()
    {
        return serviceNumber;
    }
}
