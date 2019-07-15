package com.authlete.common.dto;


/**
 * Response from Authlete's /auth/requestobject API.
 *
 * @since 2.XX
 */
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
     *
     * @return
     *         The action.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action that the service implementation should take.
     *
     * @param action
     *            The action.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectResponse setAction(Action action)
    {
        this.action = action;

        return this;
    }


    /**
     * Get the response content which can be used as the entity body
     * of the response returned to the client application.
     *
     * @return
     *         The response content string.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as the entity body
     * of the response returned to the client application.
     *
     * @param responseContent
     *            The response content string.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectResponse setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;

        return this;
    }


    /**
     * Get the expiration date of the stored request object in
     * seconds since epoch.
     *
     * @return
     *         The expiration date.
     */
    public long getExpiration()
    {
        return expiration;
    }


    /**
     * Set the expiration date of the stored request object in
     * seconds since epoch.
     *
     * @param expiration
     *            The expiration date.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectResponse setExpiration(long expiration)
    {
        this.expiration = expiration;

        return this;
    }


    /**
     * Get the request URI created to represent the stored
     * request object. This can be sent by the client
     * as the 'request_uri' parameter in an authorization
     * request.
     *
     * @return
     *         The registered request URI.
     */
    public String getRequestUri()
    {
        return requestUri;
    }


    /**
     * Set the request URI created to represent the stored
     * request object. This can be sent by the client
     * as the 'request_uri' parameter in an authorization
     * request.
     *
     * @param requestUri
     *            The registered request URI.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectResponse setRequestUri(String requestUri)
    {
        this.requestUri = requestUri;

        return this;
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
