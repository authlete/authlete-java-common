/*
 * Copyright (C) 2018 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.dto;


import java.io.Serializable;


/**
 * Request to Authlete's {@code /api/jose/verify} API.
 *
 * @since 2.23
 */
public class JoseVerifyRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The JOSE object that will be verified.
     */
    private String jose;


    /**
     * Mandatory claims that must be included in the JOSE object.
     * This assumes that the payload part of the JOSE object can be parsed
     * as JSON.
     */
    private String[] mandatoryClaims;


    /**
     * Allowable clock skew in seconds for verification of exp, nbf and
     * iat claims.
     */
    private int clockSkew;


    /**
     * The identifier of the client application.
     */
    private String clientIdentifier;


    /**
     * The flag which indicates whether the JOSE object has been signed
     * by a client application with the client's private key or a shared
     * symmetric key.
     */
    private boolean signedByClient;


    /**
     * Get the JOSE object that will be verified.
     *
     * @return
     *         The JOSE object. For example, a <a href=
     *         "https://tools.ietf.org/html/rfc7519">JWT</a> in <a href=
     *         "https://tools.ietf.org/html/rfc7515#section-3.1">JWS compact
     *         serialization</a> format.
     */
    public String getJose()
    {
        return jose;
    }


    /**
     * Set a JOSE object that will be verified. This request parameter is
     * mandatory.
     *
     * <p>
     * If the payload part of the JOSE object can be parsed as JSON and the
     * JSON has an <code><a href=
     * "https://tools.ietf.org/html/rfc7519#section-4.1.4">exp</a></code>
     * claim (Expiration Time Claim), an <code><a href=
     * "https://tools.ietf.org/html/rfc7519#section-4.1.5">nbf</a></code>
     * claim (Not Before Claim) and/or an <code><a href=
     * "https://tools.ietf.org/html/rfc7519#section-4.1.6">iat</a></code>
     * claim (Issued At Claim), their values are checked based on the rules
     * described in <a href="https://tools.ietf.org/html/rfc7519">RFC 7519</a>.
     * </p>
     *
     * <p>
     * If {@link #getMandatoryClaims()} method returns a non-empty array,
     * the payload part of the JOSE object must be able to be parsed as JSON
     * and the JSON must contain the mandatory claims.
     * </p>
     *
     * @param jose
     *         A JOSE object. For example, a <a href=
     *         "https://tools.ietf.org/html/rfc7519">JWT</a> in <a href=
     *         "https://tools.ietf.org/html/rfc7515#section-3.1">JWS compact
     *         serialization</a> format.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyRequest setJose(String jose)
    {
        this.jose = jose;

        return this;
    }


    /**
     * Get the mandatory claims that are required to be included in the JOSE
     * object.
     *
     * @return
     *         The mandatory claims.
     */
    public String[] getMandatoryClaims()
    {
        return mandatoryClaims;
    }


    /**
     * Set mandatory claims that are required to be included in the JOSE
     * object. This request parameter is optional.
     *
     * <p>
     * When this parameter is specified, it is assumed that the payload part
     * of the JOSE object can be parsed as JSON. The implementation of {@code
     * /api/jose/verify} API checks whether the JSON contains the mandatory
     * claims.
     * </p>
     *
     * <p>
     * When the Content-Type of the request is {@code
     * application/x-www-form-urlencoded}, the value of this request parameter
     * should be a string of space-delimited list of claim names.
     * </p>
     *
     * @param mandatoryClaims
     *         Mandatory claims.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyRequest setMandatoryClaims(String[] mandatoryClaims)
    {
        this.mandatoryClaims = mandatoryClaims;

        return this;
    }


    /**
     * Get the allowable clock skew in seconds.
     *
     * @return
     *         Allowable clock skew in seconds.
     */
    public int getClockSkew()
    {
        return clockSkew;
    }


    /**
     * Set allowable clock skew in seconds. This request parameter is optional
     * and its default value is 0.
     *
     * <p>
     * This parameter has a meaning only when the payload part of the JOSE
     * object can be parsed as JSON and the JSON has an <code><a href=
     * "https://tools.ietf.org/html/rfc7519#section-4.1.4">exp</a></code>
     * claim (Expiration Time Claim), an <code><a href=
     * "https://tools.ietf.org/html/rfc7519#section-4.1.5">nbf</a></code>
     * claim (Not Before Claim) and/or an <code><a href=
     * "https://tools.ietf.org/html/rfc7519#section-4.1.6">iat</a></code>
     * claim (Issued At Claim). The claims are time-related ones defined
     * in <a href="https://tools.ietf.org/html/rfc7519">RFC 7519</a> (JSON
     * Web Token (JWT)). When the values of the claims are checked, the
     * clock skew specified by this request parameter is taken into
     * consideration.
     * </p>
     *
     * <p>
     * For example, if the value of the {@code exp} claim is 1531193168 and
     * the current time of the system on which the Authlete server runs is
     * 1531193169, the JWT is regarded as invalid because the current time
     * exceeds the expiration time. However, if 2 or a larger value is given
     * as the clock skew, the value of the {@code exp} claim will not cause
     * a verification error because the current time does not exceed
     * (1531193168 + the clock skew).
     * </p>
     *
     * @param clockSkew
     *         Allowable clock skew in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyRequest setClockSkew(int clockSkew)
    {
        this.clockSkew = clockSkew;

        return this;
    }


    /**
     * Get the identifier of the client application whose keys are required
     * for verification of the JOSE object.
     *
     * @return
     *         The client identifier.
     */
    public String getClientIdentifier()
    {
        return clientIdentifier;
    }


    /**
     * Set the identifier of the client application whose keys are required
     * for verification of the JOSE object. This request parameter can be
     * omitted under some conditions.
     *
     * <p>
     * If the JOSE object has been signed by a client application with the
     * client's private key or a shared symmetric key (i.e. if {@link
     * #isSignedByClient()} returns {@code true}), or if the signature
     * algorithm is symmetric, a key that corresponds to the key used for the
     * signature needs to be looked up or computed in order to verify the
     * signature.
     * </p>
     *
     * <p>
     * When the algorithm of the signature is asymmetric, the implementation
     * of {@code /api/jose/verify} API searches the JWK Set Document of the
     * client application for the public key which corresponds to the private
     * key used to digitally sign the JOSE object. On the other hand, when the
     * algorithm of the signature is symmetric, the implementation of the API
     * computes the shared symmetric key based on the client secret of the
     * client application by following the rule described in OpenID Connect
     * Core 1.0, <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#Signing">10.1.
     * Signing</a>. In either case, information to identify the client
     * application is needed. This request parameter ({@code clientIdentifier})
     * exists for the purpose.
     * </p>
     *
     * <p>
     * In addition, if the JOSE object has been encrypted with a symmetric
     * algorithm, the client secret of the client application is needed to
     * compute the symmetric shared key (OpenID Connect Core 1.0, <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html#Encryption"
     * >10.2. Encryption</a>). For this case, information to identify the
     * client application is needed, too. Note that the implementation of
     * {@code /api/jose/verify} API cannot decrypt the JOSE object if the
     * encryption algorithm is asymmetric and a client's public key has been
     * used because Authlete cannot access the private key of the client
     * application (as Authlete does not manage private keys of client
     * applications).
     * </p>
     *
     * <p>
     * When this request parameter is omitted, the implementation of {@code
     * /api/jose/verify} API may try to use the value of the <code><a href=
     * "https://tools.ietf.org/html/rfc7519#section-4.1.1">iss</a></code>
     * claim as the value of the client identifier. This may happen only when
     * {@link #isSignedByClient()} returns {@code true}.
     * </p>
     *
     * @param clientIdentifier
     *         The client identifier.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyRequest setClientIdentifier(String clientIdentifier)
    {
        this.clientIdentifier = clientIdentifier;

        return this;
    }


    /**
     * Get the flag which indicates whether the signature of the JOSE object
     * has been signed by a client application with the client's private key
     * or a shared symmetric key.
     *
     * @return
     *         {@code true} if the JOSE object has been signed by a client
     *         application.
     */
    public boolean isSignedByClient()
    {
        return signedByClient;
    }


    /**
     * Set the flag which indicates whether the signature of the JOSE object
     * has been signed by a client application with the client's private key
     * or a shared symmetric key. This request parameter is optional and its
     * default value is {@code false}.
     *
     * <p>
     * When {@code true} is set to this request parameter, the identifier of
     * the client application needs to be set by {@link
     * #setClientIdentifier(String)} unless the JOSE object is not encrypted
     * and the {@code iss} claim holds the client identifier.
     * </p>
     *
     * @param signedByClient
     *         {@code true} to indicate that the JOSE object has been signed
     *         by a client application.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyRequest setSignedByClient(boolean signedByClient)
    {
        this.signedByClient = signedByClient;

        return this;
    }
}
