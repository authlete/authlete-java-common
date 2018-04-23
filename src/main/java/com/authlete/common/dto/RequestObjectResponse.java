package com.authlete.common.dto;


public class RequestObjectResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action the service implementation should take.
     */
    public static enum Action
    {
        /**
         * The client has made a poorly formatted request.
         */
        BAD_REQUEST,

        /**
         * The request object has been accepted and stored as a request URI
         * attached to this client.
         */
        OK,

        /**
         * There was an internal server error.
         */
        INTERNAL_SERVER_ERROR,

    }


    private static final String SUMMARY_FORMAT = "action=%s, responseContent=%s, "
            + "expiration=%d, requestUri=%s";


    private Action action;
    private String responseContent;
    private long expiration;
    private String requestUri;


    /**
     * Get the next action that the service implementation should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the service implementation should take.
     */
    public void setAction(Action action)
    {
        this.action = action;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response returned to the client application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as the entity body
     * of the response returned to the client application.
     */
    public void setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;
    }


    /**
     * @return the expiration
     */
    public long getExpiration()
    {
        return expiration;
    }


    /**
     * @param expiration
     *            the expiration to set
     */
    public void setExpiration(long expiration)
    {
        this.expiration = expiration;
    }


    /**
     * @return the requestUri
     */
    public String getRequestUri()
    {
        return requestUri;
    }


    /**
     * @param requestUri
     *            the requestUri to set
     */
    public void setRequestUri(String requestUri)
    {
        this.requestUri = requestUri;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
                action, responseContent,
                expiration, requestUri);
    }
}
