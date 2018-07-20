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


/**
 * Response from Authlete's {@code /api/jose/verify} API.
 *
 * @since 2.23
 */
public class JoseVerifyResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The result of the verification on the JOSE object.
     */
    private boolean valid;


    /**
     * The result of the signature verification.
     */
    private boolean signatureValid;


    /**
     * Claims that are not included although they are specified by the
     * mandatoryClaims request parameter.
     */
    private String[] missingClaims;


    /**
     * Invalid claims.
     */
    private String[] invalidClaims;


    /**
     * Error messages.
     */
    private String[] errorDescriptions;


    /**
     * Get the result of the verification on the JOSE object.
     *
     * @return
     *         {@code true} if the JOSE object passed to Authlete's
     *         {@code /api/jose/verify} API is valid.
     */
    public boolean isValid()
    {
        return valid;
    }


    /**
     * Set the result of the verification on the JOSE object.
     *
     * @param valid
     *         {@code true} to indicate that the JOSE object passed to
     *         Authlete's {@code /api/jose/verify} API is valid.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyResponse setValid(boolean valid)
    {
        this.valid = valid;

        return this;
    }


    /**
     * Get the result of the signature verification.
     *
     * <p>
     * If the given JOSE object is signed and its signature has been
     * successfully verified, this method returns {@code true}.
     * </p>
     *
     * @return
     *         {@code true} if the signature of the JOSE is valid.
     */
    public boolean isSignatureValid()
    {
        return signatureValid;
    }


    /**
     * Set the result of the signature verification.
     *
     * @param valid
     *         {@code true} to indicate the signature of the JOSE is valid.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyResponse setSignatureValid(boolean valid)
    {
        this.signatureValid = valid;

        return this;
    }


    /**
     * Get the list of missing claims; the claims that are not included in the
     * payload part of the JOSE object although they are listed in the {@code
     * mandatoryClaims} request parameter.
     *
     * <p>
     * For example, if the value of the {@code mandatoryClaims} parameter of
     * the request was {@code ["exp", "iat"]} and if the payload part of the
     * JOSE object contains the {@code exp} claim but does not contain the
     * {@code iat} claim, this method returns {@code ["iat"]}.
     * </p>
     *
     * <p>
     * Note that this method returns {@code null} if the payload part of the
     * JOSE object could not be retrieved. For example, in the case that the
     * value of the {@code jose} request parameter could not be parsed as JOSE.
     * </p>
     *
     * @return
     *         Missing claims.
     */
    public String[] getMissingClaims()
    {
        return missingClaims;
    }


    /**
     * Set the list of missing claims.
     *
     * @param claims
     *         Missing claims.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyResponse setMissingClaims(String[] claims)
    {
        this.missingClaims = claims;

        return this;
    }


    /**
     * Get the list of invalid claims.
     *
     * <p>
     * For example, if the payload of the JOSE object can be parsed as JSON
     * and the JSON has an {@code exp} claim and if the value of the claim
     * indicates that the JOSE object has expired, {@code "exp"} will be
     * included in the list of invalid claims returned from this method.
     * </p>
     *
     * @return
     *         Invalid claims.
     */
    public String[] getInvalidClaims()
    {
        return invalidClaims;
    }


    /**
     * Set the list of invalid claims.
     *
     * @param claims
     *         Invalid claims.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyResponse setInvalidClaims(String[] claims)
    {
        this.invalidClaims = claims;

        return this;
    }


    /**
     * Get the list of error messages.
     *
     * <p>
     * When the JOSE object is valid (= when {@link #isValid()} method returns
     * {@code true}), this method returns {@code null}.
     * </p>
     *
     * @return
     *         List of error messages.
     */
    public String[] getErrorDescriptions()
    {
        return errorDescriptions;
    }


    /**
     * Set the list of error messages.
     *
     * @param descriptions
     *         List of error messages.
     *
     * @return
     *         {@code this} object.
     */
    public JoseVerifyResponse setErrorDescriptions(String[] descriptions)
    {
        this.errorDescriptions = descriptions;

        return this;
    }
}
