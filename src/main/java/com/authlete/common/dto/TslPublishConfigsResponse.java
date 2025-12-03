package com.authlete.common.dto;

/**
 * Response from Authlete's {@code /tsl/publish/configs} API.
 *
 * <p>
 * This class represents the response containing Token Status List (TSL)
 * publication configuration information for one or more services. Each entry
 * indicates when the next TSL will be published for the corresponding service.
 * </p>
 *
 * <p>
 * The response includes an array of {@link TslPublishConfig} objects, each
 * describing:
 * </p>
 *
 * <ul>
 *   <li>{@code serviceNumber} – The service identifier.</li>
 *   <li>{@code nextTslPublishTime} – The scheduled Unix timestamp (seconds) for the next TSL publication.</li>
 * </ul>
 *
 * @since 4.31
 * @since Authlete 3.0.22
 */
public class TslPublishConfigsResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;

    /**
     * The list of TSL publish configurations.
     *
     * <p>
     * Each element in the array contains the publish schedule for a service,
     * including the service number and the next scheduled publish time.
     * </p>
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private TslPublishConfig[] tslPublishConfigs;

    /**
     * Set the list of TSL publish configurations.
     *
     * @param tslPublishConfigs
     *         An array of {@link TslPublishConfig} objects.
     */
    public void setTslPublishConfigs(TslPublishConfig[] tslPublishConfigs)
    {
        this.tslPublishConfigs = tslPublishConfigs;
    }

    /**
     * Get the list of TSL publish configurations.
     *
     * @return An array of {@link TslPublishConfig} objects.
     */
    public TslPublishConfig[] getTslPublishConfigs()
    {
        return tslPublishConfigs;
    }
}
