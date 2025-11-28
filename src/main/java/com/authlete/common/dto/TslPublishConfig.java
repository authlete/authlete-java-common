package com.authlete.common.dto;

/**
 * This DTO holds the information when next TSL will be published
 * per service.
 *
 * @since TODO
 */
public class TslPublishConfig {

    private static final long serialVersionUID = 1L;

    private int serviceNumber;

    /**
     * It specifies the time when next TSL will be published. It is in seconds
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
