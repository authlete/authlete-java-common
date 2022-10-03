package com.authlete.common.dto;


import java.io.Serializable;


public class ResourceServerSignatureRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The {@code Signature} header value from the request. All
     * signatures in this header will be included in the output signature.
     * 
     * @since 3.xx
     */
    private String requestSignature;


    /**
     * The HTTP response headers, all will be included in the signature.
     * 
     * @since 3.xx
     */
    private Pair[] headers;


    /**
     * The HTTP message response body. If included, the response will include
     * the Content-Digest of the message and the digest will be covered in
     * the signature.
     * 
     * @since 3.xx
     */
    private String message;


    /**
     * The HTTP status code of the response.
     */
    private int status;


    public String getRequestSignature()
    {
        return requestSignature;
    }


    public ResourceServerSignatureRequest setRequestSignature(String requestSignature)
    {
        this.requestSignature = requestSignature;
        return this;
    }


    public Pair[] getHeaders()
    {
        return headers;
    }


    public ResourceServerSignatureRequest setHeaders(Pair[] headers)
    {
        this.headers = headers;
        return this;
    }


    public String getMessage()
    {
        return message;
    }


    public ResourceServerSignatureRequest setMessage(String message)
    {
        this.message = message;
        return this;
    }


    public int getStatus()
    {
        return status;
    }


    public ResourceServerSignatureRequest setStatus(int status)
    {
        this.status = status;
        return this;
    }
}
