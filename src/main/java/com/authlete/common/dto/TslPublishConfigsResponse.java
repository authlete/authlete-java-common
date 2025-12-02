package com.authlete.common.dto;

/**
 * Response from Authlete's {@code /tsl/publish/configs} API.
 *
 * This class contains TSL publish configuration response which decided when next TSL
 * is going to be published.
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 */
public class TslPublishConfigsResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;

    /**
     * TSL publish configurations reponse.
     * It contains an array of the following information.
     *
     * <ul>
     * <li>{@code serviceNumber}
     * <li>{@code nextTslPublishTime}
     * </ul>
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private TslPublishConfig[] tslPublishConfigs;

    public void setTslPublishConfigs(TslPublishConfig[] tslPublishConfigs)
    {
        this.tslPublishConfigs = tslPublishConfigs;
    }

    public TslPublishConfig[] getTslPublishConfigs()
    {
        return tslPublishConfigs;
    }
}
