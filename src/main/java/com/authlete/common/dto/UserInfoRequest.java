/*
 * Copyright (C) 2015-2023 Authlete, Inc.
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
 * Request to Authlete's {@code /auth/userinfo} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>token</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * An access token to get user information.
 * </p>
 * </dd>
 *
 * <dt><b><code>clientCertificate</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The client certificate from the MTLS of the userinfo request from
 * the client application.
 * See <a href="https://tools.ietf.org/html/rfc8705">RFC 8705</a>
 * (OAuth 2.0 Mutual-TLS Client Authentication and Certificate-Bound Access
 * Tokens) for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpop</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code DPoP} HTTP header.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htm</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The HTTP method of the userinfo request. In normal cases, the value should
 * be either {@code "GET"} or {@code "POST"}.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>htu</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The URL of the userinfo endpoint, without query or path components.
 * If omitted, the {@code userInfoEndpoint}
 * property of {@link Service} is used as the default value.
 * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
 * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
 * </p>
 * </dd>
 *
 * <dt><b><code>uri</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The full URL of the userinfo endpoint. If omitted, the {@code userInfoEndpoint}
 * property of {@link Service} is used as the default value.
 * </p>
 * </dd>
 *
 * <dt><b><code>headers</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The HTTP headers to be included in processing the signature. If this is
 * a signed request, this must include the {@code Signature} and
 * {@code Signature-Input} headers, as well as any additional headers
 * covered by the signature.
 * </p>
 * </dd>
 *
 * <dt><b><code>message</code></b> (OPTIONAL; Authlete 2.3 onwards)</dt>
 * <dd>
 * <p>
 * The HTTP message body of the request, if present. If supplied,
 * this is used to validate the value of the {@code Content-Digest} header,
 * which must in turn be covered in the HTTP Message Signature.
 * </p>
 * </dd>
 *
 * <dt><b><code>dpopNonceRequired</code></b> (OPTIONAL; Authlete 3.0 onwards)</dt>
 * <dd>
 * <p>
 * The flag indicating whether to require the DPoP proof JWT to include
 * the {@code nonce} claim. Even if the service's {@code dpopNonceRequired}
 * property is false, calling the {@code /auth/userinfo} API with this
 * {@code dpopNonceRequired} parameter true will force the Authlete API to
 * check whether the DPoP proof JWT includes the expected nonce value.
 * </p>
 * </dd>
 *
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 */
public class UserInfoRequest implements Serializable
{
    private static final long serialVersionUID = 4L;


    /**
     * The access token.
     */
    private String token;


    /**
     * The client certificate used in the TLS connection established
     * between the client application and the userinfo endpoint.
     *
     * @since 2.37
     */
    private String clientCertificate;


    /**
     * The {@code DPoP} header.
     *
     * @since 2.70
     */
    private String dpop;


    /**
     * The HTTP method of the userinfo request.
     *
     * @since 2.70
     */
    private String htm;


    /**
     * The URL of the userinfo endpoint,
     * without query or fragment components.
     *
     * @since 2.70
     */
    private String htu;


    /**
     * The full URL of the userinfo endpoint.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    private String uri;


    /**
     * The HTTP headers to be included in processing the signature. If this is
     * a signed request, this must include the {@code Signature} and
     * {@code Signature-Input} headers, as well as any additional headers
     * covered by the signature.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    private Pair[] headers;


    /**
     * The HTTP message body of the request, if present.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    private String message;


    /**
     * Whether to check if the DPoP proof JWT includes the expected nonce value.
     *
     * @since 3.82
     * @since Authlete 3.0
     */
    private boolean dpopNonceRequired;


    /**
     * Get the access token which has come along with the userinfo
     * request from the client application.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token which has been issued by Authlete.
     * The access token is the one that has come along with the
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfoRequest"
     * >userinfo request</a> from the client application.
     */
    public UserInfoRequest setToken(String token)
    {
        this.token = token;

        return this;
    }


    /**
     * Get the client certificate used in the TLS connection established
     * between the client application and the userinfo endpoint.
     *
     * @return
     *         The client certificate in PEM format.
     */
    public String getClientCertificate()
    {
        return clientCertificate;
    }


    /**
     * Set the client certificate used in the TLS connection established
     * between the client application and the userinfo endpoint.
     *
     * <p>
     * The value of this request parameter is referred to when the access
     * token given to the userinfo endpoint was bound to a client certificate
     * when it was issued. See <a href=
     * "https://www.rfc-editor.org/rfc/rfc8705.html">RFC 8705 OAuth 2.0
     * Mutual-TLS Client Authentication and Certificate-Bound Access Tokens</a>
     * for details about the specification of certificate-bound access tokens.
     * </p>
     *
     * @param certificate
     *         The client certificate in PEM format.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.37
     */
    public UserInfoRequest setClientCertificate(String certificate)
    {
        this.clientCertificate = certificate;

        return this;
    }


    /**
     * Get the {@code DPoP} header presented by the client during the request
     * to the userinfo endpoint. The header contains a signed JWT which
     * includes the public key that is paired with the private key used to
     * sign the JWT.
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The {@code DPoP} header string.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getDpop()
    {
        return dpop;
    }


    /**
     * Set the {@code DPoP} header presented by the client during the request
     * to the userinfo endpoint. The header contains a signed JWT which
     * includes the public key that is paired with the private key used to
     * sign the JWT.
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param dpop
     *         The {@code DPoP} header string.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public UserInfoRequest setDpop(String dpop)
    {
        this.dpop = dpop;

        return this;
    }


    /**
     * Get the HTTP method of the userinfo request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is either {@code "GET"} or {@code "POST"}.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The HTTP method as a string. For example, {@code "GET"}.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtm()
    {
        return htm;
    }


    /**
     * Set the HTTP method of the userinfo request. This field is used to
     * validate the {@code DPoP} header.
     *
     * <p>
     * In normal cases, the value is either {@code "GET"} or {@code "POST"}.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param htm
     *         The HTTP method as a string. For example, {@code "GET"}.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public UserInfoRequest setHtm(String htm)
    {
        this.htm = htm;

        return this;
    }


    /**
     * Get the URL of the userinfo endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code userInfoEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @return
     *         The URL of the userinfo endpoint.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public String getHtu()
    {
        return htu;
    }


    /**
     * Set the URL of the userinfo endpoint. This field is used to validate
     * the {@code DPoP} header.
     *
     * <p>
     * If this parameter is omitted, the {@code userInfoEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * <p>
     * See <a href="https://www.rfc-editor.org/rfc/rfc9449.html">RFC 9449 OAuth
     * 2.0 Demonstrating Proof of Possession (DPoP)</a> for details.
     * </p>
     *
     * @param htu
     *         The URL of the userinfo endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.70
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public UserInfoRequest setHtu(String htu)
    {
        this.htu = htu;

        return this;
    }


    /**
     * Get the URL of the userinfo endpoint. This field is used to validate
     * the HTTP Message Signature.
     *
     * <p>
     * If this parameter is omitted, the {@code userInfoEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * @return
     *         The URL of the userinfo endpoint.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public String getUri()
    {
        return uri;
    }


    /**
     * Set the URL of the userinfo endpoint. This field is used to validate
     * the HTTP Message Signature.
     *
     * <p>
     * If this parameter is omitted, the {@code userInfoEndpoint} property
     * of the {@link Service} is used as the default value.
     * </p>
     *
     * @param uri
     *         The URL of the userinfo endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public UserInfoRequest setUri(String uri)
    {
        this.uri = uri;
        return this;
    }


    /**
     * Get the HTTP headers to be included in processing the signature. If this is
     * a signed request, this must include the {@code Signature} and
     * {@code Signature-Input} headers, as well as any additional headers
     * covered by the signature.
     *
     * @return
     *         The HTTP headers.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public Pair[] getHeaders()
    {
        return headers;
    }


    /**
     * Set the HTTP headers to be included in processing the signature. If this is
     * a signed request, this must include the {@code Signature} and
     * {@code Signature-Input} headers, as well as any additional headers
     * covered by the signature.
     *
     * @param headers
     *         The HTTP headers.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public UserInfoRequest setHeaders(Pair[] headers)
    {
        this.headers = headers;
        return this;
    }


    /**
     * Get the HTTP message body, if present. If provided, this will be used to calculate
     * the expected value of the {@code Content-Digest} in the headers of the request
     * covered by the HTTP Message Signature.
     *
     * @return
     *         The HTTP message body.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public String getMessage()
    {
        return message;
    }


    /**
     * Set the HTTP message body, if present. If provided, this will be used to calculate
     * the expected value of the {@code Content-Digest} in the headers of the request
     * covered by the HTTP Message Signature.
     *
     * @param message
     *         The HTTP message body.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.38
     * @since Authlete 2.3
     */
    public UserInfoRequest setMessage(String message)
    {
        this.message = message;
        return this;
    }


    /**
     * Get the flag indicating whether to check if the DPoP proof JWT includes
     * the expected {@code nonce} value.
     *
     * <p>
     * If this request parameter is {@code true} or if the service's
     * {@code dpopNonceRequired} property ({@link Service#isDpopNonceRequired()})
     * is {@code true}, the {@code /auth/userinfo} API checks if the DPoP proof
     * JWT includes the expected {@code nonce} value. In this case, the response
     * from the {@code /auth/userinfo} API will include the {@code dpopNonce}
     * response parameter, which should be used as the value of the
     * {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @return
     *         {@code true} if the {@code /auth/userinfo} API checks whether the
     *         DPoP proof JWT includes the expected {@code nonce} value, even
     *         if the service's {@code dpopNonceRequired} property is false.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public boolean isDpopNonceRequired()
    {
        return dpopNonceRequired;
    }


    /**
     * Set the flag indicating whether to check if the DPoP proof JWT includes
     * the expected {@code nonce} value.
     *
     * <p>
     * If this request parameter is {@code true} or if the service's
     * {@code dpopNonceRequired} property ({@link Service#isDpopNonceRequired()})
     * is {@code true}, the {@code /auth/userinfo} API checks if the DPoP proof
     * JWT includes the expected {@code nonce} value. In this case, the response
     * from the {@code /auth/userinfo} API will include the {@code dpopNonce}
     * response parameter, which should be used as the value of the
     * {@code DPoP-Nonce} HTTP header.
     * </p>
     *
     * @param required
     *         {@code true} to have the {@code /auth/userinfo} API check whether
     *         the DPoP proof JWT includes the expected {@code nonce} value,
     *         even if the service's {@code dpopNonceRequired} property is false.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.82
     * @since Authlete 3.0
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc9449.html"
     *      >RFC 9449 OAuth 2.0 Demonstrating Proof of Possession (DPoP)</a>
     */
    public UserInfoRequest setDpopNonceRequired(boolean required)
    {
        this.dpopNonceRequired = required;

        return this;
    }
}
