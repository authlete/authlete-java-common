package com.authlete.common.dto;

import java.io.Serializable;

/**
 * This class is used to pre-populate the unused indexes which later will be used in the
 * issued VCs/tokens.
 *
 * A request to Authlete's {@code /service/populate/unused/indexes} API.
 *
 * @since 4.31
 * @since Authlete 3.0.22

 */
public class TslPopulateUnusedIndexesRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Service number against which unused indexes is populated.
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
