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
 * Hardware-secured key.
 *
 * <p>
 * This class holds information about a key managed in an HSM (Hardware
 * Security Module).
 * </p>
 *
 * @since 2.97
 */
public class Hsk implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String kty;
    private String use;
    private String alg;
    private String kid;
    private String hsmName;
    private String handle;
    private String publicKey;


    /**
     * Get the key type of the key on the HSM.
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
     * Set the key type of the key on the HSM.
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
    public Hsk setKty(String kty)
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
    public Hsk setUse(String use)
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
    public Hsk setAlg(String alg)
    {
        this.alg = alg;

        return this;
    }


    /**
     * Get the key ID for the key on the HSM.
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
     * @param kid
     *         The key ID.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7517.html#section-4.5"
     *      >RFC 7517 JSON Web Key (JWK), 4.5. "kid" (Key ID) Parameter</a>
     */
    public Hsk setKid(String kid)
    {
        this.kid = kid;

        return this;
    }


    /**
     * Get the name of the HSM.
     *
     * <p>
     * The identifier for the HSM that sits behind the Authlete server.
     * For example, {@code "google"}.
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
     * For example, {@code "google"}.
     * </p>
     *
     * @param hsmName
     *         The name of the HSM.
     *
     * @return
     *         {@code this} object.
     */
    public Hsk setHsmName(String hsmName)
    {
        this.hsmName = hsmName;

        return this;
    }


    /**
     * Get the handle for the key on the HSM.
     *
     * <p>
     * A handle is a base64url-encoded 256-bit random value (43 letters)
     * which is assigned by Authlete on the call of the {@code /api/hsk/create}
     * API.
     * </p>
     *
     * <p>
     * A handle is needed to call the <code>/api/hsk/get/{handle}</code> API
     * and the <code>/api/hsk/delete/{handle}</code> API.
     * </p>
     *
     * @return
     *         The handle.
     */
    public String getHandle()
    {
        return handle;
    }


    /**
     * Set the handle for the key on the HSM.
     *
     * <p>
     * A handle is a base64url-encoded 256-bit random value (43 letters)
     * which is assigned by Authlete on the call of the {@code /api/hsk/create}
     * API.
     * </p>
     *
     * <p>
     * A handle is needed to call the <code>/api/hsk/get/{handle}</code> API
     * and the <code>/api/hsk/delete/{handle}</code> API.
     * </p>
     *
     * @param handle
     *         The handle.
     *
     * @return
     *         {@code this} object.
     */
    public Hsk setHandle(String handle)
    {
        this.handle = handle;

        return this;
    }


    /**
     * Get the public key that corresponds to the key on the HSM.
     *
     * @return
     *         The public key in base64-encoded DER format.
     */
    public String getPublicKey()
    {
        return publicKey;
    }


    /**
     * Set the public key that corresponds to the key on the HSM.
     *
     * @param publicKey
     *         The public key in base64-encoded DER format.
     *
     * @return
     *         {@code this} object.
     */
    public Hsk setPublicKey(String publicKey)
    {
        this.publicKey = publicKey;

        return this;
    }
}
