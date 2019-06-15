/*
 * Copyright (C) 2019 Authlete, Inc.
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
 * Request to Authlete's {@code /api/device/verification} API. The API is used
 * to get information associated with a user code.
 *
 * <p>
 * A response from the device authorization endpoint of the authorization server
 * is JSON which contains the following response parameters (excerpted from
 * "3.2. Device Authorization Response" of <a
 * href="https://datatracker.ietf.org/doc/draft-ietf-oauth-device-flow/?include_text=1"
 * >OAuth 2.0 Device Authorization Grant</a>).
 * </p>
 *
 * <blockquote>
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *   <thead style="background-color: orange;">
 *     <tr>
 *       <td>parameter</td>
 *       <td>description</td>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>{@code device_code}</td>
 *       <td>
 *         REQUIRED. The device verification code.
 *       </td>
 *     </tr>
 *     <tr>
 *       <td>{@code user_code}</td>
 *       <td>
 *         REQUIRED. The end-user verification code.
 *       </td>
 *     </tr>
 *     <tr>
 *       <td>{@code verification_uri}</td>
 *       <td>
 *         REQUIRED. The end-user verification URI on the authorization server.
 *         The URI should be short and easy to remember as end users will be
 *         asked to manually type it into their user-agent.
 *       </td>
 *     </tr>
 *     <tr>
 *       <td>{@code verification_uri_complete}</td>
 *       <td>
 *         OPTIONAL. A verification URI that includes the "user_code" (or other
 *         information with the same function as the "user_code"), designed for
 *         non-textual transmission.
 *       </td>
 *     </tr>
 *     <tr>
 *       <td>{@code expires_in}</td>
 *       <td>
 *         REQUIRED. The lifetime in seconds of the "device_code" and "user_code".
 *       </td>
 *     </tr>
 *     <tr>
 *       <td>{@code interval}</td>
 *       <td>
 *         OPTIONAL. The minimum amount of time in seconds that the client SHOULD
 *         wait between polling requests to the token endpoint. If no value is
 *         provided, clients MUST use 5 as the default.
 *       </td>
 *     </tr>
 *   </tbody>
 * </table>
 * </blockquote>
 *
 * <p>
 * The following is an example of device authorization response.
 * </p>
 *
 * <blockquote>
 * <pre style="padding: 5px; border: 1px solid black;">
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * {
 *   "device_code": "GmRhmhcxhwAzkoEqiMEg_DnyEysNkuNhszIySk9eS",
 *   "user_code": "WDJB-MJHT",
 *   "verification_uri": "https://example.com/device",
 *   "verification_uri_complete":
 *       "https://example.com/device?user_code=WDJB-MJHT",
 *   "expires_in": 1800,
 *   "interval": 5
 * }</pre>
 * </blockquote>
 *
 * <p>
 * After receiving a response from the device authorization endpoint of the
 * authorization server, the client application shows the end-user the user
 * code and the verification URI which are included in the device
 * authorization response. Then, the end-user will access the verification
 * URI using a web browser on another device (typically, a smart phone).
 * In normal implementations, the verification endpoint will return an HTML
 * page with an input form where the end-user inputs a user code. The
 * authorization server will receive a user code from the form.
 * </p>
 *
 * <p>
 * After receiving a user code, the authorization server should call Authlete's
 * {@code /api/device/verification} API with the user code. The API will return
 * information associated with the user code such as client information and
 * requested scopes. Using the information, the authorization server should
 * generate an HTML page that confirms the end-user's consent and send the page
 * back to the web browser.
 * </p>
 *
 * @since 2.42
 */
public class DeviceVerificationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The user code.
     */
    private String userCode;


    /**
     * Get the user code.
     *
     * @return
     *         The user code.
     */
    public String getUserCode()
    {
        return userCode;
    }


    /**
     * Set the user code.
     *
     * @param userCode
     *         The user code.
     *
     * @return
     *         {@code this} object.
     */
    public DeviceVerificationRequest setUserCode(String userCode)
    {
        this.userCode = userCode;

        return this;
    }
}
