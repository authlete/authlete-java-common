/*
 * Copyright (C) 2021 Authlete, Inc.
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
package com.authlete.common.types;


import java.io.IOException;
import java.util.Map;
import com.authlete.common.dto.Hsk;
import com.authlete.common.types.JWEAlg;
import com.authlete.common.types.JWSAlg;


/**
 * Hardware Security Module.
 *
 * <p>
 * This is the interface that needs to be implemented for an HSM. Authlete
 * loads implementations of this interface dynamically. The way Authlete
 * finds implementations is disclosed only to customers who use on-premises
 * Authlete as necessary.
 * </p>
 *
 * @since 2.97
 */
public interface HSM
{
    /**
     * The name of the HSM.
     *
     * <p>
     * The name is used as a value of the {@code hsmName} request parameter
     * of the {@code /api/hsk/create} API (cf. {@link
     * com.authlete.common.dto.HskCreateRequest HskCreateRequest}{@code .}{@link
     * com.authlete.common.dto.HskCreateRequest#getHsmName() getHsmName()}).
     * </p>
     *
     * <p>
     * The name must match the regular expression "<code>^[a-zA-Z][0-9a-zA-Z_]{0,29}</code>".
     * </p>
     *
     * @return
     *         The name of the HSM.
     */
    String getName();


    /**
     * Check if the HSM supports the specified signing algorithm.
     *
     * <p>
     * This method is called from within the implementation of the
     * {@code /api/hsk/create} API. If this method returns {@code false},
     * the API call is rejected.
     * </p>
     *
     * @param alg
     *         A signing algorithm.
     *
     * @return
     *         {@code true} if the HSM supports the signing algorithm.
     */
    boolean supportsJwsAlg(JWSAlg alg);


    /**
     * Check if the HSM supports the specified encryption algorithm.
     *
     * <p>
     * This method is called from within the implementation of the
     * {@code /api/hsk/create} API. If this method returns {@code false},
     * the API call is rejected.
     * </p>
     *
     * @param alg
     *         An encryption algorithm.
     *
     * @return
     *         {@code true} if the HSM supports the encryption algorithm.
     */
    boolean supportsJweAlg(JWEAlg alg);


    /**
     * Create a key on the HSM.
     *
     * <p>
     * The argument {@code hsk} holds conditions for a key to be created.
     * All properties of {@code hsk} except {@code handle} and
     * {@code publicKey} properties have non-null values.
     * </p>
     *
     * <p>
     * The implementation of this method is required to create a key on the
     * HSM that meets the conditions. Then, if possible, the implementation
     * should retrieve the public key that corresponds to the created key,
     * convert the format of the public key into base64-encoded DER, and set
     * it back to {@code hsk} by calling {@link Hsk#setPublicKey(String)}.
     * If the implementation did not set the public key, Authlete will call
     * {@link #getPublicKey(Hsk, Map)} after this method finishes.
     * </p>
     *
     * <p>
     * The implementation of this method may return information associated
     * with the created key as a {@link Map}. It is highly likely that the
     * HSM returns an identifier of the created key which will be needed
     * later for signing or decrypting. Such information can be embedded in
     * the map. For example, like below.
     * </p>
     *
     * <blockquote>
     * <pre>
     * Map&lt;String, Object&gt; info = new HashMap&lt;String, Object&gt;();
     * info.put("identifier", identifier);
     * </pre>
     * </blockquote>
     *
     * <p>
     * The map returned from {@code createKey()} is converted into JSON,
     * encrypted, and then stored into Authlete's database. The map will be
     * used later as the {@code info} argument of other methods such as
     * {@link #sign(Hsk, Map, byte[])}.
     * </p>
     *
     * @param hsk
     *         Conditions for a key to be created.
     *
     * @return
     *         HSM-specific information associated with the newly created key.
     *
     * @throws IOException
     *         Failed to create a key on the HSM.
     */
    Map<String, Object> createKey(Hsk hsk) throws IOException;


    /**
     * Delete a key on the HSM.
     *
     * <p>
     * In the case where the target key does not exist on the HSM, the
     * implementation of this method does not have to raise an exception.
     * </p>
     *
     * @param hsk
     *         Information about the key.
     *
     * @param info
     *         HSM-specific information associated with the key. This is
     *         the map returned from {@link #createKey(Hsk)}.
     *
     * @throws IOException
     *         Failed to delete the key.
     */
    void deleteKey(Hsk hsk, Map<String, Object> info) throws IOException;


    /**
     * Get the public key that corresponds to the key on the HSM.
     *
     * <p>
     * The format of the returned public key should be base64-encoded DER. See
     * "<a href="https://darutk.medium.com/illustrated-x-509-certificate-84aece2c5c2e"
     * >Illustrated X.509 Certificate</a>" about the format.
     * </p>
     *
     * @param hsk
     *         Information about the key.
     *
     * @param info
     *         HSM-specific information associated with the key. This is
     *         the map returned from {@link #createKey(Hsk)}.
     *
     * @return
     *         The public key in base64-encoded DER format.
     *
     * @throws IOException
     *         Failed to get the public key.
     */
    String getPublicKey(Hsk hsk, Map<String, Object> info) throws IOException;


    /**
     * Sign data and return the signature.
     *
     * @param hsk
     *         Information about the key.
     *
     * @param info
     *         HSM-specific information associated with the key. This is
     *         the map returned from {@link #createKey(Hsk)}.
     *
     * @param data
     *         The target data to sign.
     *
     * @return
     *         The signature generated by the signing.
     *
     * @throws IOException
     *         Failed to sign the data.
     */
    byte[] sign(Hsk hsk, Map<String, Object> info, byte[] data) throws IOException;


    /**
     * Decrypt data.
     *
     * @param hsk
     *         Information about the key.
     *
     * @param info
     *         HSM-specific information associated with the key. This is
     *         the map returned from {@link #createKey(Hsk)}.
     *
     * @param data
     *         Data encrypted with the public key that corresponds to the
     *         key on the HSM.
     *
     * @return
     *         Decrypted data.
     *
     * @throws IOException
     *         Failed to decrypt the data.
     */
    byte[] decrypt(Hsk hsk, Map<String, Object> info, byte[] data) throws IOException;
}
