/*
 * Copyright (C) 2022 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's {@code /api/rs/sign} API.
 *
 * <p>
 * The resource server can use this utility to sign responses using
 * HTTP Message Signatures defined in <a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-httpbis-message-signatures/"
 * >draft-ietf-httpbis-message-signatures</a> as profiled by <a href=
 * "https://bitbucket.org/openid/fapi/src/master/Financial_API_HTTP_Signing.md"
 * >Financial-grade API: HTTP Signing Requirements</a>.
 * </p>
 *
 * <p>
 * To use this feature, a service is configured with the {@code signRsResponse} flag
 * set to true and the {@code resourceSignatureKeyId} set to the key ID of one of the keys
 * in the service's registered JWKS. This key will be used to create an HTTP Message Signature
 * on the input parameters.
 * </p>
 *
 * <blockquote>
 * <dl>
 *
 * <dt><b><code>requestSignature</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The {@code Signature} header value from the request to the RS. All
 * signatures in this header will be included in the output signature.
 * </p>
 * </dd>
 *
 * <dt><b><code>status</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The HTTP status code of the response.
 * </p>
 * </dd>
 *
 * <dt><b><code>headers</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The HTTP response headers, all will be included in the output signature.
 * </p>
 * </dd>
 *
 * <dt><b><code>message</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The HTTP message response body. If included, the response will include
 * the {@code Content-Digest} of the message and the digest will be covered in
 * the signature.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @since 3.38
 * @since Authlete 2.3
 */
public class ResourceServerSignatureRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The {@code Signature} header value from the request. All
     * signatures in this header will be included in the output signature.
     */
    private String requestSignature;


    /**
     * The HTTP response headers, all will be included in the signature.
     */
    private Pair[] headers;


    /**
     * The HTTP message response body. If included, the response will include
     * the Content-Digest of the message and the digest will be covered in
     * the signature.
     */
    private String message;


    /**
     * The HTTP status code of the response.
     */
    private int status;


    /**
     * Get the {@code Signature} header value from the request. All
     * signatures in this header will be included in the output signature.
     *
     * @return
     *         The formatted Signature header value.
     */
    public String getRequestSignature()
    {
        return requestSignature;
    }


    /**
     * Set the {@code Signature} header value from the request. All
     * signatures in this header will be included in the output signature.
     *
     * @parameter requestSignature
     *         The formatted Signature header value.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureRequest setRequestSignature(String requestSignature)
    {
        this.requestSignature = requestSignature;
        return this;
    }


    /**
     * Get the HTTP response headers, all will be included in the signature.
     * The name of the header is the {@code key} of the entry and the value
     * of the header is the {@code value} of the entry.
     *
     * @return
     *         The response headers.
     */
    public Pair[] getHeaders()
    {
        return headers;
    }


    /**
     * Set the HTTP response headers, all will be included in the signature.
     * The name of the header is the {@code key} of the entry and the value
     * of the header is the {@code value} of the entry.
     *
     * @parameter headers
     *         The response headers.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureRequest setHeaders(Pair[] headers)
    {
        this.headers = headers;
        return this;
    }


    /**
     * Get the HTTP message response body. If included, the response will include
     * the {@code Content-Digest} of the message and the digest will be covered in
     * the signature.
     *
     * @return
     *         HTTP message response body.
     */
    public String getMessage()
    {
        return message;
    }


    /**
     * Set the HTTP message response body. If included, the response will include
     * the {@code Content-Digest} of the message and the digest will be covered in
     * the signature.
     *
     * @parameter message
     *         HTTP message response body.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureRequest setMessage(String message)
    {
        this.message = message;
        return this;
    }


    /**
     * Get the HTTP status code of the response.
     *
     * @return
     *         The numeric HTTP status code.
     */
    public int getStatus()
    {
        return status;
    }


    /**
     * Set the HTTP status code of the response.
     *
     * @parameter status
     *         The numeric HTTP status code.
     *
     * @return
     *         {@code this} object.
     */
    public ResourceServerSignatureRequest setStatus(int status)
    {
        this.status = status;
        return this;
    }
}
