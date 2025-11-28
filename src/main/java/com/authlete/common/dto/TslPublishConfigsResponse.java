package com.authlete.common.dto;

/**
 * Response from Authlete's {@code service/tsl/publish/configs} API.
 *
 * @since TODO
 */
public class TslPublishConfigsResponse extends ApiResponse {

    private static final long serialVersionUID = 1L;

    private TslPublishConfig[] tslPublishConfigs;

    public void setTslPublishConfigs(TslPublishConfig[] tslPublishConfigs) {
        this.tslPublishConfigs = tslPublishConfigs;
    }

    public TslPublishConfig[] getTslPublishConfigs() {
        return tslPublishConfigs;
    }
}
