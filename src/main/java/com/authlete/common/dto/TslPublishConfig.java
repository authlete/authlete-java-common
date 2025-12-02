package com.authlete.common.dto;

/**
 * This class contains per service TSL publish configurations.
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 */
public class TslPublishConfig {

    private static final long serialVersionUID = 1L;

    /**
     * Service number whose TSL will be published at next publish time.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int serviceNumber;

    /**
     * It specifies the time when next TSL will be published. It is in seconds
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private long nextTslPublishTime;

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setNextTslPublishTime(long nextTslPublishTime) {
        this.nextTslPublishTime = nextTslPublishTime;
    }

    public long getNextTslPublishTime() {
        return nextTslPublishTime;
    }

}
