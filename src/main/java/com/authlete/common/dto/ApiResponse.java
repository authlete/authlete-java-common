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
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * The base class of an API response from an Authlete API call.
 *
 * @author Takahiko Kawasaki
 */
public class ApiResponse implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String resultCode;
    private String resultMessage;


    /**
     * Get the code of the result of an Authlete API call.
     *
     * @return
     *         The result code. For example, "<code>A004001</code>".
     */
    public String getResultCode()
    {
        return resultCode;
    }


    /**
     * Set the code of the result of an Authlete API call.
     *
     * @param code
     *         The result code.
     */
    public void setResultCode(String code)
    {
        this.resultCode = code;
    }


    /**
     * Get the message of the result of an Authlete API call.
     *
     * @return
     *         The result message. For example,
     *         "<code>[A001202] /client/get/list, Authorization header is missing.</code>"
     */
    public String getResultMessage()
    {
        return resultMessage;
    }


    /**
     * Set the message of the result of an Authlete API call.
     *
     * @param message
     *         The result message.
     */
    public void setResultMessage(String message)
    {
        this.resultMessage = message;
    }
}
