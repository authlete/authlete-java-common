/*
 * Copyright (C) 2021 Authlete, Inc.
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
 * Request to Authlete's {@code /api/hsk/create} API.
 *
 * <p>
 * Note that parameter values specified in the request to the API cannot be
 * changed later. Especially, the key ID cannot be changed later. Therefore,
 * if you explicitly specify a key ID in the request, the value has to be
 * determined carefully before calling the {@code /api/hsk/create} API.
 * </p>
 *
 * @since 2.97
 */
public class HskCreateRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String kty;
    private String use;
    private String alg;
    private String kid;
    private String hsmName;


    /**
     * Get the key type.
     *
     * @return
     *         The key type. {@code "EC"} or {@code "RSA"}.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.1"
     *      >RFC 7517 JSON Web Key (JWK), 4.1. "kty" (Key Type) Parameter</a>
     */
    public String getKty()
    {
        return kty;
    }


    /**
     * Set the key type.
     *
     * @param kty
     *         The key type. {@code "EC"} or {@code "RSA"}.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.1"
     *      >RFC 7517 JSON Web Key (JWK), 4.1. "kty" (Key Type) Parameter</a>
     */
    public HskCreateRequest setKty(String kty)
    {
        this.kty = kty;

        return this;
    }


    /**
     * Get the use of the key on the HSM.
     *
     * <p>
     * When the key use is {@code "sig"} (signature), the private key on the
     * HSM is used to sign data and the corresponding public key is used to
     * verify the signature.
     * </p>
     *
     * <p>
     * When the key use is {@code "enc"} (encryption), the private key on the
     * HSM is used to decrypt encrypted data which have been encrypted with the
     * corresponding public key.
     * </p>
     *
     * @return
     *         The key use. {@code "sig"} (signature) or {@code "enc"}
     *         (encryption).
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.2"
     *      >RFC 7517 JSON Web Key (JWK), 4.2. "use" (Public Key Use) Parameter</a>
     */
    public String getUse()
    {
        return use;
    }


    /**
     * Set the use of the key on the HSM.
     *
     * <p>
     * When the key use is {@code "sig"} (signature), the private key on the
     * HSM is used to sign data and the corresponding public key is used to
     * verify the signature.
     * </p>
     *
     * <p>
     * When the key use is {@code "enc"} (encryption), the private key on the
     * HSM is used to decrypt encrypted data which have been encrypted with the
     * corresponding public key.
     * </p>
     *
     * @param use
     *         The key use. {@code "sig"} (signature) or {@code "enc"}
     *         (encryption).
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.2"
     *      >RFC 7517 JSON Web Key (JWK), 4.2. "use" (Public Key Use) Parameter</a>
     */
    public HskCreateRequest setUse(String use)
    {
        this.use = use;

        return this;
    }


    /**
     * Get the algorithm of the key on the HSM.
     *
     * <p>
     * When the key use is {@code "sig"}, the algorithm represents a signing
     * algorithm such as {@code "ES256"}.
     * </p>
     *
     * <p>
     * When the key use is {@code "enc"}, the algorithm represents an
     * encryption algorithm such as {@code "RSA-OAEP-256"}.
     * </p>
     *
     * <p>
     * It is rare that HSMs support all the algorithms listed in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7518.html">RFC 7518 JSON Web
     * Algorithms (JWA)</a>. When the specified algorithm is not supported
     * by the HSM, the request to the {@code /api/hsk/create} API fails.
     * </p>
     *
     * @return
     *         The algorithm.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.4"
     *      >RFC 7517 JSON Web Key (JWK), 4.4. "alg" (Algorithm) Parameter</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-3.1"
     *      >RFC 7518 JSON Web Algorithms (JWA), 3.1. "alg" (Algorithm) Header Parameter Values for JWS</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.1"
     *      >RFC 7518 JSON Web Algorithms (JWA), 4.1. "alg" (Algorithm) Header Parameter Values for JWE</a>
     */
    public String getAlg()
    {
        return alg;
    }


    /**
     * Set the algorithm of the key on the HSM.
     *
     * <p>
     * When the key use is {@code "sig"}, the algorithm represents a signing
     * algorithm such as {@code "ES256"}.
     * </p>
     *
     * <p>
     * When the key use is {@code "enc"}, the algorithm represents an
     * encryption algorithm such as {@code "RSA-OAEP-256"}.
     * </p>
     *
     * <p>
     * It is rare that HSMs support all the algorithms listed in <a href=
     * "https://www.rfc-editor.org/rfc/rfc7518.html">RFC 7518 JSON Web
     * Algorithms (JWA)</a>. When the specified algorithm is not supported
     * by the HSM, the request to the {@code /api/hsk/create} API fails.
     * </p>
     *
     * @param alg
     *         The algorithm.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.4"
     *      >RFC 7517 JSON Web Key (JWK), 4.4. "alg" (Algorithm) Parameter</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-3.1"
     *      >RFC 7518 JSON Web Algorithms (JWA), 3.1. "alg" (Algorithm) Header Parameter Values for JWS</a>
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.1"
     *      >RFC 7518 JSON Web Algorithms (JWA), 4.1. "alg" (Algorithm) Header Parameter Values for JWE</a>
     */
    public HskCreateRequest setAlg(String alg)
    {
        this.alg = alg;

        return this;
    }


    /**
     * Get the key ID for the key on the HSM.
     *
     * <p>
     * Note that the key ID cannot be changed later. Determine the key ID
     * carefully before calling the {@code /api/hsk/create} API. If the
     * {@code kid} request parameter is missing or its value is empty,
     * the API generates a random key ID (base64url-encoded 256-bit random
     * value) for the request.
     * </p>
     *
     * <p>
     * Also note that Authlete does not check duplication of key IDs.
     * </p>
     *
     * @return
     *         The key ID.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.5"
     *      >RFC 7517 JSON Web Key (JWK), 4.5. "kid" (Key ID) Parameter</a>
     */
    public String getKid()
    {
        return kid;
    }


    /**
     * Set the key ID for the key on the HSM.
     *
     * <p>
     * Note that the key ID cannot be changed later. Determine the key ID
     * carefully before calling the {@code /api/hsk/create} API. If the
     * {@code kid} request parameter is missing or its value is empty,
     * the API generates a random key ID (base64url-encoded 256-bit random
     * value) for the request.
     * </p>
     *
     * <p>
     * Also note that Authlete does not check duplication of key IDs.
     * </p>
     *
     * @param kid
     *         The key ID.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.5"
     *      >RFC 7517 JSON Web Key (JWK), 4.5. "kid" (Key ID) Parameter</a>
     */
    public HskCreateRequest setKid(String kid)
    {
        this.kid = kid;

        return this;
    }


    /**
     * Get the name of the HSM.
     *
     * <p>
     * The identifier for the HSM that sits behind the Authlete server.
     * For example, {@code "google"}. If the {@code hsmName} request parameter
     * is missing or its value is empty, the API uses the default HSM. The
     * value of the default HSM varies depending on the configuration of the
     * Authlete server.
     * </p>
     *
     * @return
     *         The name of the HSM.
     */
    public String getHsmName()
    {
        return hsmName;
    }


    /**
     * Set the name of the HSM.
     *
     * <p>
     * The identifier for the HSM that sits behind the Authlete server.
     * For example, {@code "google"}. If the {@code hsmName} request parameter
     * is missing or its value is empty, the API uses the default HSM. The
     * value of the default HSM varies depending on the configuration of the
     * Authlete server.
     * </p>
     *
     * @param hsmName
     *         The name of the HSM.
     *
     * @return
     *         {@code this} object.
     */
    public HskCreateRequest setHsmName(String hsmName)
    {
        this.hsmName = hsmName;

        return this;
    }
}
