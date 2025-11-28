package com.authlete.common.dto;

import java.io.Serializable;

/**
 * A request to Authlete's {@code /service/populate/unused/indexes} API.
 *
 * @since TODO
 */
public class TslPopulateUnusedIndexesRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int serviceNumber;

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }
}
