/*
 * Copyright (C) 2014 Authlete, Inc.
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


/**
 * Authlete API exception.
 *
 * @author Takahiko Kawasaki
 */
@SuppressWarnings("serial")
public class AuthleteApiException extends RuntimeException
{
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
        setResponse(statusCode, statusMessage, responseBody);
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

        setResponse(statusCode, statusMessage, responseBody);
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

        setResponse(statusCode, statusMessage, responseBody);
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

        setResponse(statusCode, statusMessage, responseBody);
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
    private void setResponse(int statusCode, String statusMessage, String responseBody)
    {
        mStatusCode    = statusCode;
        mStatusMessage = statusMessage;
        mResponseBody  = responseBody;
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
}
