/*
 * Copyright (C) 2014-2015 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.api;


import java.util.List;
import java.util.Map;


/**
 * Authlete API exception.
 *
 * @author Takahiko Kawasaki
 */
public class AuthleteApiException extends RuntimeException
{
    private static final long serialVersionUID = 1L;


    /**
     * HTTP status code. May be 0.
     */
    private int mStatusCode;


    /**
     * HTTP status message. May be {@code null}.
     */
    private String mStatusMessage;


    /**
     * HTTP response body. May be {@code null}.
     */
    private String mResponseBody;


    /**
     * HTTP response headers. May be {@code null}.
     */
    private Map<String, List<String>> mResponseHeaders;


    /**
     * Constructor.
     */
    public AuthleteApiException()
    {
    }


    /**
     * Constructor with HTTP response information.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     */
    public AuthleteApiException(int statusCode, String statusMessage, String responseBody)
    {
        setResponse(statusCode, statusMessage, responseBody, null);
    }


    /**
     * Constructor with an error message.
     *
     * @param message
     *         Error message.
     */
    public AuthleteApiException(String message)
    {
        super(message);
    }


    /**
     * Constructor with an error message and HTTP response information.
     *
     * @param message
     *         Error message.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     */
    public AuthleteApiException(String message, int statusCode, String statusMessage, String responseBody)
    {
        super(message);

        setResponse(statusCode, statusMessage, responseBody, null);
    }


    /**
     * Constructor with an error message and HTTP response information.
     *
     * @param message
     *         Error message.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     *
     * @param responseHeaders
     *         Http response headers.
     *
     * @since 1.13
     */
    public AuthleteApiException(String message, int statusCode, String statusMessage, String responseBody, Map<String, List<String>> responseHeaders)
    {
        super(message);

        setResponse(statusCode, statusMessage, responseBody, responseHeaders);
    }


    /**
     * Constructor with the cause.
     *
     * @param cause
     *         The cause of this exception.
     */
    public AuthleteApiException(Throwable cause)
    {
        super(cause);
    }


    /**
     * Constructor with the cause and HTTP response information.
     *
     * @param cause
     *         The cause of this exception.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     */
    public AuthleteApiException(Throwable cause, int statusCode, String statusMessage, String responseBody)
    {
        super(cause);

        setResponse(statusCode, statusMessage, responseBody, null);
    }


    /**
     * Constructor with the cause and HTTP response information.
     *
     * @param cause
     *         The cause of this exception.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     *
     * @param responseHeaders
     *         HTTP response headers.
     *
     * @since 1.13
     */
    public AuthleteApiException(Throwable cause, int statusCode, String statusMessage, String responseBody, Map<String, List<String>> responseHeaders)
    {
        super(cause);

        setResponse(statusCode, statusMessage, responseBody, responseHeaders);
    }


    /**
     * Constructor with an error message and the cause.
     *
     * @param message
     *         Error message.
     *
     * @param cause
     *         The cause of this exception.
     */
    public AuthleteApiException(String message, Throwable cause)
    {
        super(message, cause);
    }


    /**
     * Constructor with an error message, the cause and HTTP response information.
     *
     * @param message
     *         Error message.
     *
     * @param cause
     *         The cause of this exception.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     */
    public AuthleteApiException(String message, Throwable cause, int statusCode, String statusMessage, String responseBody)
    {
        super(message, cause);

        setResponse(statusCode, statusMessage, responseBody, null);
    }


    /**
     * Constructor with an error message, the cause and HTTP response information.
     *
     * @param message
     *         Error message.
     *
     * @param cause
     *         The cause of this exception.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     *
     * @param responseHeaders
     *         HTTP response headers.
     *
     * @since 1.13
     */
    public AuthleteApiException(String message, Throwable cause, int statusCode, String statusMessage, String responseBody, Map<String, List<String>> responseHeaders)
    {
        super(message, cause);

        setResponse(statusCode, statusMessage, responseBody, responseHeaders);
    }


    /**
     * Set the data fields related to HTTP response information.
     *
     * @param statusCode
     *         HTTP status code.
     *
     * @param statusMessage
     *         HTTP status message.
     *
     * @param responseBody
     *         HTTP response body.
     */
    private void setResponse(int statusCode, String statusMessage, String responseBody, Map<String, List<String>> responseHeaders)
    {
        mStatusCode      = statusCode;
        mStatusMessage   = statusMessage;
        mResponseBody    = responseBody;
        mResponseHeaders = responseHeaders;
    }


    /**
     * Get the HTTP status code contained in the response from Authlete server.
     *
     * <p>
     * Note that this method may return 0 if this exception was raised
     * before the response from Authlete server was obtained.
     * </p>
     *
     * @return
     *         HTTP status code.
     */
    public int getStatusCode()
    {
        return mStatusCode;
    }


    /**
     * Get the HTTP status message contained in the response from Authlete server.
     *
     * <p>
     * Note that this method may return {@code null} if this exception was raised
     * before the response from Authlete server was obtained.
     * </p>
     *
     * @return
     *         HTTP status message.
     */
    public String getStatusMessage()
    {
        return mStatusMessage;
    }


    /**
     * Get the response body contained in the response from Authlete server.
     *
     * <p>
     * Note that this method may return {@code null} if this exception was raised
     * before the response from Authlete server was obtained or if the response
     * did not contain any content.
     * </p>
     *
     * @return
     *         HTTP response body.
     */
    public String getResponseBody()
    {
        return mResponseBody;
    }


    /**
     * Get the response headers contained in the response from Authlete server.
     *
     * <p>
     * Note that this method may return {@code null} if this exception was raised
     * before the response from Authlete server was obtained.
     * </p>
     *
     * @return
     *         HTTP response headers.
     *
     * @since 1.13
     */
    public Map<String, List<String>> getResponseHeaders()
    {
        return mResponseHeaders;
    }
}
