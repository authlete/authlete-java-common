package com.authlete.common.dto;

import com.authlete.common.types.TslPublishFormat;

import java.io.Serializable;
import java.net.URI;

/**
 * A class that represents trust status list configurations data
 * The set consists of the following.
 *
 * <ul>
 * <li>{@code format}
 * <li>{@code validity}
 * <li>{@code publishFrequency}
 * <li>{@code timeToLive}
 * <li>{@code publishEndpoint}
 * </ul>
 *
 * @since TODO
 * @since Authlete TODO
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/12/"
 *      >Token Status List (TSL)</a>
 */
public class TslConfigData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The format of the TSL. Possible values are JWT and CWT. Currently only JWT is supported
     *
     * @since TODO
     */
    private TslPublishFormat format;

    /**
     * The validity of the TSL in hours
     *
     * @since TODO
     */
    private int validity;

    /**
     * Publish TSL after every X hours
     *
     * @since TODO
     */
    private int publishFrequency;

    /**
     * Time to live in hours which provides indication to verifiers to cache this TSL
     *
     * @since TODO
     */
    private int timeToLive;

    /**
     * Endpoint where to publish this TSL
     *
     * @since TODO
     */
    private URI publishEndpoint;

    /**
     * The default constructor.
     */
    public TslConfigData() {
    }

    /**
     * Copy constructor.
     *
     * @param tslConfigData
     *         Source to copy data from. {@code null} won't raise any exception.
     */
    public TslConfigData(TslConfigData tslConfigData) {
        if (tslConfigData == null) {
            return;
        }
        format = tslConfigData.getFormat();
        validity = tslConfigData.getValidity();
        publishFrequency = tslConfigData.getPublishFrequency();
        timeToLive = tslConfigData.getTimeToLive();
        publishEndpoint = tslConfigData.getPublishEndpoint();
    }

    public TslConfigData setFormat(TslPublishFormat format) {
        this.format = format;

        return this;
    }
    public TslPublishFormat getFormat() {
        return format;
    }

    public TslConfigData setValidity(int validity) {
        this.validity = validity;

        return this;
    }
    public int getValidity() {
        return validity;
    }

    public TslConfigData setPublishFrequency(int publishFrequency) {
        this.publishFrequency = publishFrequency;

        return this;
    }
    public int getPublishFrequency() {
        return publishFrequency;
    }

    public TslConfigData setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;

        return this;
    }
    public int getTimeToLive() {
        return timeToLive;
    }

    public TslConfigData setPublishEndpoint(URI publishEndpoint) {
        this.publishEndpoint = publishEndpoint;

        return this;
    }
    public URI getPublishEndpoint() {
        return publishEndpoint;
    }
}

