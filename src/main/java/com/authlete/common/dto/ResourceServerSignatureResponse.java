package com.authlete.common.dto;

public class ResourceServerSignatureResponse extends ApiResponse
{

    private static final long serialVersionUID = 1L;


    /**
     * The next action the service implementation should take.
     */
    public enum Action
    {
        /**
         * The request from the service implementation was wrong or
         * an error occurred in Authlete. The service implementation
         * should return {@code "500 Internal Server Error"} to the
         * client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The request does not contain the required parameters. The service
         * implementation should return {@code "400 Bad Request"} to
         * the client application.
         */
        BAD_REQUEST,

        /**
         * The signature was successfully applied to the request.
         */
        OK
    }


    private static final String SUMMARY_FORMAT = "action=%s, signatureInput=%s";

    /**
     * The signature header of the response message.
     */
    private String signature;

    /**
     * The signature-input header of the response message
     */
    private String signatureInput;

    /**
     * The content-digest header of the response message
     */
    private String contentDigest;

    /**
     * The action to take
     */
    private Action action;


    public String getSignature()
    {
        return signature;
    }


    public void setSignature(String signature)
    {
        this.signature = signature;
    }


    public String getSignatureInput()
    {
        return signatureInput;
    }


    public void setSignatureInput(String signatureInput)
    {
        this.signatureInput = signatureInput;
    }


    public String getContentDigest()
    {
        return contentDigest;
    }


    public void setContentDigest(String mContentDigest)
    {
        this.contentDigest = mContentDigest;
    }


    public Action getAction()
    {
        return action;
    }


    public void setAction(Action action)
    {
        this.action = action;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT, action, signatureInput);
    }
}
