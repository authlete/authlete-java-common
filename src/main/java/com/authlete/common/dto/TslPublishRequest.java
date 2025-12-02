package com.authlete.common.dto;

import java.io.Serializable;

/**
 * Request to Authlete's {@code /tsl/publish} API.
 *
 * This class is used to publish the TSL.
 * The set consists of the following.
 *
 * <ul>
 * <li>{@code serviceNumber}
 * </ul>
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/13/"
 *      >Token Status List (TSL)</a>
 */
public class TslPublishRequest implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * Service number whose TSL is to be published
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int serviceNumber;

    public void setServiceNumber(int serviceNumber)
    {
        this.serviceNumber = serviceNumber;
    }

    public int getServiceNumber()
    {
        return serviceNumber;
    }
}

