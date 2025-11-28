package com.authlete.common.dto;

import java.io.Serializable;

public class TslGetRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int serviceNumber;

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }
}

