/*
 * Copyright (C) 2025 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * A request to Authlete's {@code /nativesso} API.
 *
 * <p>
 * The API is expected to be called only when the value of the "{@code action}"
 * parameter in a response from the {@code /auth/token} API is {@link
 * TokenResponse.Action#NATIVE_SSO NATIVE_SSO}. The purpose of the
 * {@code /nativesso} API is to generate a token response that includes a new,
 * <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html">Native
 * SSO</a>-compliant ID token together with a new access token and an optional
 * refresh token.
 * </p>
 *
 * <p>
 * To comply with the <a href=
 * "https://openid.net/specs/openid-connect-native-sso-1_0.html">OpenID Connect
 * Native SSO for Mobile Apps 1.0</a> specification, the generated ID token
 * includes the {@code sid} and {@code ds_hash} claims. The session ID
 * associated with the provided access token is used as the value of the
 * {@code sid} claim. The value of the {@code deviceSecretHash} request
 * parameter is used as the value of the {@code ds_hash} claim. If the
 * {@code deviceSecretHash} request parameter is omitted, the SHA-256 hash of
 * the {@code deviceSecret} request parameter is computed, and the
 * base64url-encoded string of that hash is used as the value of the
 * {@code ds_hash} claim.
 * </p>
 *
 * <p>
 * The value of the {@code deviceSecret} request parameter is used as the value
 * of the {@code device_secret} property in the token response prepared by the
 * {@code /nativesso} API. Additionally, as mentioned above, if the
 * {@code deviceSecretHash} request parameter is omitted, the value of the
 * {@code deviceSecret} request parameter is used to compute the value of the
 * {@code ds_hash} claim.
 * </p>
 *
 * @since 4.18
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-connect-native-sso-1_0.html"
 *      >OpenID Connect Native SSO for Mobile Apps 1.0</a>
 *
 * @see TokenResponse
 *
 * @see NativeSsoResponse
 */
public class NativeSsoRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String accessToken;
    private String refreshToken;
    private String sub;
    private String claims;
    private String idtHeaderParams;
    private String idTokenAudType;
    private String deviceSecret;
    private String deviceSecretHash;


    /**
     * Get the access token.
     *
     * <p>
     * The value of this parameter should be (a) the value of the
     * "{@code jwtAccessToken}" parameter in a response from the
     * {@code /auth/token} API when the value is available, or (b)
     * the value of the "{@code accessToken}" parameter in the
     * response from the {@code /auth/token} API when the value of
     * the "{@code jwtAccessToken}" parameter is not available.
     * </p>
     *
     * @return
     *         The access token that has been newly issued as the
     *         result of the {@code /auth/token} API call.
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token.
     *
     * <p>
     * The value of this parameter should be (a) the value of the
     * "{@code jwtAccessToken}" parameter in a response from the
     * {@code /auth/token} API when the value is available, or (b)
     * the value of the "{@code accessToken}" parameter in the
     * response from the {@code /auth/token} API when the value of
     * the "{@code jwtAccessToken}" parameter is not available.
     * </p>
     *
     * @param accessToken
     *         The access token that has been newly issued as the
     *         result of the {@code /auth/token} API call.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the refresh token.
     *
     * <p>
     * The value of this parameter should be the value of the
     * "{@code refreshToken}" parameter in a response from the
     * {@code /auth/token} API.
     * </p>
     *
     * @return
     *         The refresh token that has been prepared as the result
     *         of the {@code /auth/token} API call. It may be a new
     *         refresh token or the same refresh token included in the
     *         token request, depending on the service configuration.
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the refresh token.
     *
     * <p>
     * The value of this parameter should be the value of the
     * "{@code refreshToken}" parameter in a response from the
     * {@code /auth/token} API.
     * </p>
     *
     * @param refreshToken
     *         The refresh token that has been prepared as the result
     *         of the {@code /auth/token} API call. It may be a new
     *         refresh token or the same refresh token included in the
     *         token request, depending on the service configuration.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;

        return this;
    }


    /**
     * Get the value that should be used as the value of the "{@code sub}"
     * claim of the ID token.
     *
     * <p>
     * This parameter is optional. When omitted, the value of the subject
     * associated with the access token is used.
     * </p>
     *
     * @return
     *         The value that should be used as the value of the "{@code sub}"
     *         claim of the ID token.
     */
    public String getSub()
    {
        return sub;
    }


    /**
     * Set the value that should be used as the value of the "{@code sub}"
     * claim of the ID token.
     *
     * <p>
     * This parameter is optional. When omitted, the value of the subject
     * associated with the access token is used.
     * </p>
     *
     * @param sub
     *         The value that should be used as the value of the "{@code sub}"
     *         claim of the ID token.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setSub(String sub)
    {
        this.sub = sub;

        return this;
    }


    /**
     * Get additional claims that should be embedded in the payload part of
     * the ID token. The format is a JSON object.
     *
     * <p>
     * This parameter is optional.
     * </p>
     *
     * @return
     *         Additional claims that should be embedded in the payload part
     *         of the ID token.
     */
    public String getClaims()
    {
        return claims;
    }


    /**
     * Set additional claims that should be embedded in the payload part of
     * the ID token. The format must be a JSON object.
     *
     * <p>
     * This parameter is optional.
     * </p>
     *
     * @param claims
     *         Additional claims that should be embedded in the payload part
     *         of the ID token.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setClaims(String claims)
    {
        this.claims = claims;

        return this;
    }


    /**
     * Get additional parameters that should be embedded in the JWS header of
     * the ID token. The format is a JSON object.
     *
     * <p>
     * This parameter is optional.
     * </p>
     *
     * @return
     *         Additional parameters that should be embedded in the JWS header
     *         of the ID token.
     */
    public String getIdtHeaderParams()
    {
        return idtHeaderParams;
    }


    /**
     * Set additional parameters that should be embedded in the JWS header of
     * the ID token. The format must be a JSON object.
     *
     * <p>
     * This parameter is optional.
     * </p>
     *
     * @param params
     *         Additional parameters that should be embedded in the JWS header
     *         of the ID token.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setIdtHeaderParams(String params)
    {
        this.idtHeaderParams = params;

        return this;
    }


    /**
     * Get the type of the "{@code aud}" claim of the ID token being issued.
     *
     * <p>
     * Valid values of this parameter are as follows.
     * </p>
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>"{@code array}"</td>
     *     <td>The type of the {@code aud} claim becomes an array of strings.</td>
     *   </tr>
     *   <tr>
     *     <td>"{@code string}"</td>
     *     <td>The type of the {@code aud} claim becomes a single string.</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * <p>
     * This parameter is optional, and the default value on omission is
     * "{@code array}".
     * </p>
     *
     * <p>
     * This parameter takes precedence over the {@code idTokenAudType} property
     * of {@link Service} (cf. {@link Service#getIdTokenAudType()}).
     * </p>
     *
     * @return
     *         The type of the {@code aud} claim of the ID token.
     */
    public String getIdTokenAudType()
    {
        return idTokenAudType;
    }


    /**
     * Set the type of the "{@code aud}" claim of the ID token being issued.
     *
     * <p>
     * Valid values of this parameter are as follows.
     * </p>
     *
     * <blockquote>
     * <table border="1" cellpadding="5" style="border-collapse: collapse;">
     *   <tr bgcolor="orange">
     *     <th>Value</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>"{@code array}"</td>
     *     <td>The type of the {@code aud} claim becomes an array of strings.</td>
     *   </tr>
     *   <tr>
     *     <td>"{@code string}"</td>
     *     <td>The type of the {@code aud} claim becomes a single string.</td>
     *   </tr>
     * </table>
     * </blockquote>
     *
     * <p>
     * This parameter is optional, and the default value on omission is
     * "{@code array}".
     * </p>
     *
     * <p>
     * This parameter takes precedence over the {@code idTokenAudType} property
     * of {@link Service} (cf. {@link Service#getIdTokenAudType()}).
     * </p>
     *
     * @param type
     *         The type of the {@code aud} claim of the ID token.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setIdTokenAudType(String type)
    {
        this.idTokenAudType = type;

        return this;
    }


    /**
     * Get the device secret.
     *
     * <p>
     * The value of this parameter should be the value of the
     * {@code deviceSecret} parameter in the response from the
     * {@code /auth/token} API, if the parameter is present. Otherwise, the
     * authorization server should generate a new device secret and specify
     * it as the value of this parameter.
     * </p>
     *
     * <p>
     * The specified device secret is included as the value of the
     * {@code device_secret} property in the token response prepared by the
     * {@code /nativesso} API.
     * </p>
     *
     * <p>
     * Additionally, if the {@code deviceSecretHash} request parameter is
     * omitted, the device secret is used to compute the value of the
     * {@code ds_hash} claim. In this case, the {@code ds_hash} claim will
     * be the base64url-encoded SHA-256 hash of the device secret.
     * </p>
     *
     * @return
     *         The device secret.
     */
    public String getDeviceSecret()
    {
        return deviceSecret;
    }


    /**
     * Set the device secret.
     *
     * <p>
     * The value of this parameter should be the value of the
     * {@code deviceSecret} parameter in the response from the
     * {@code /auth/token} API, if the parameter is present. Otherwise, the
     * authorization server should generate a new device secret and specify
     * it as the value of this parameter.
     * </p>
     *
     * <p>
     * The specified device secret is included as the value of the
     * {@code device_secret} property in the token response prepared by the
     * {@code /nativesso} API.
     * </p>
     *
     * <p>
     * Additionally, if the {@code deviceSecretHash} request parameter is
     * omitted, the device secret is used to compute the value of the
     * {@code ds_hash} claim. In this case, the {@code ds_hash} claim will
     * be the base64url-encoded SHA-256 hash of the device secret.
     * </p>
     *
     * @param deviceSecret
     *         The device secret.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setDeviceSecret(String deviceSecret)
    {
        this.deviceSecret = deviceSecret;

        return this;
    }


    /**
     * Get the device secret hash.
     *
     * <p>
     * The specified device secret hash is included as the value of the
     * {@code ds_hash} claim in the ID token generated by the
     * {@code /nativesso} API.
     * </p>
     *
     * <p>
     * If the {@code deviceSecretHash} request parameter is omitted, the value
     * of the {@code deviceSecret} request parameter is used to compute the
     * hash.
     * </p>
     *
     * @return
     *         The device secret hash.
     */
    public String getDeviceSecretHash()
    {
        return deviceSecretHash;
    }


    /**
     * Set the device secret hash.
     *
     * <p>
     * The specified device secret hash is included as the value of the
     * {@code ds_hash} claim in the ID token generated by the
     * {@code /nativesso} API.
     * </p>
     *
     * <p>
     * If the {@code deviceSecretHash} request parameter is omitted, the value
     * of the {@code deviceSecret} request parameter is used to compute the
     * hash.
     * </p>
     *
     * @param deviceSecretHash
     *         The device secret hash.
     *
     * @return
     *         {@code this} object.
     */
    public NativeSsoRequest setDeviceSecretHash(String deviceSecretHash)
    {
        this.deviceSecretHash = deviceSecretHash;

        return this;
    }
}
