package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's /auth/requestobject API.
 *
 * @since 2.XX
 */
public class RequestObjectRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * FAPI Request object endpoint request body (in JWS format).
     */
    private String body;


    /**
     * Get the value of the HTTP message body sent to the
     * request object endpoint. This consists entirely of a
     * JSON Web Token (JWT) signed with Json Web Signatures
     * (JWS) representing the client's request object.
     *
     * @return
     *         The body string.
     */
    public String getBody()
    {
        return body;
    }


    /**
     * Get the value of the HTTP message body sent to the
     * request object endpoint. This consists entirely of a
     * JSON Web Token (JWT) signed with Json Web Signatures
     * (JWS) representing the client's request object.
     * 
     * @param body
     *            The body string.
     *
     * @return
     *         {@code this} object.
     */
    public RequestObjectRequest setBody(String body)
    {
        this.body = body;

        return this;
    }
}
