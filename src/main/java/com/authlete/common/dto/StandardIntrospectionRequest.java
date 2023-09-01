/*
 * Copyright (C) 2017-2023 Authlete, Inc.
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
import java.net.URI;
import com.authlete.common.api.AuthleteApi;
import com.authlete.common.types.JWEAlg;
import com.authlete.common.types.JWEEnc;
import com.authlete.common.types.JWSAlg;


/**
 * Request to Authlete's {@code /api/auth/introspection/standard} API.
 * Note that the API and {@code /api/auth/introspection} API are different.
 * {@code /api/auth/introspection/standard} API exists to help your
 * authorization server provide its own introspection API which complies
 * with <a href="https://tools.ietf.org/html/rfc7662">RFC 7662</a> (OAuth
 * 2.0 Token Introspection).
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>parameters</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * Request parameters which comply with the introspection request
 * defined in "<a href="https://tools.ietf.org/html/rfc7662#section-2.1"
 * >2.1. Introspection Request</a>" in RFC 7662. The following is
 * an example value of {@code parameters}.
 * </p>
 * <pre style="border: solid 1px black; padding: 0.5em;"
 * ><b>token</b>=pNj1h24a4geA_YHilxrshkRkxJDsyXBZWKp3hZ5ND7A&<b
 * >token_type_hint</b>=access_token</pre>
 * <p>
 * The implementation of the introspection endpoint of your
 * authorization server will receive an HTTP POST [<a href=
 * "https://tools.ietf.org/html/rfc7231">RFC 7231</a>] request with
 * parameters in the "{@code application/x-www-form-urlencoded}"
 * format. It is the entity body of the request that Authlete's
 * {@code /api/auth/introspection/standard} API expects as the
 * value of {@code parameters}.
 * </p>
 * </dd>
 *
 * <dt><b><code>withHiddenProperties</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Flag indicating whether to include hidden properties in the output.
 * </p>
 * <p>
 * Authlete has a mechanism whereby to associate arbitrary key-value pairs
 * with an access token. Each key-value pair has a {@code hidden} attribute.
 * By default, key-value pairs whose {@code hidden} attribute is true are
 * not embedded in the standard introspection output.
 * </p>
 * <p>
 * If the {@code withHiddenProperties} request parameter is given and its
 * value is {@code true}, {@code /api/auth/introspection/standard} API
 * includes all the associated key-value pairs into the output regardless
 * of the value of the {@code hidden} attribute.
 * </p>
 * </dd>
 *
 * <dt><b><code>rsUri</code></b> (CONDITIONALLY REQUIRED)</dt>
 * <dd>
 * <p>
 * The URI of the resource server making the introspection request.
 * </p>
 * <p>
 * If the {@code rsUri} request parameter is given and the token has
 * audience values, Authlete checks if the value of the {@code rsUri}
 * request parameter is contained in the audience values. If not contained,
 * Authlete generates an introspection response with the {@code active}
 * property set to {@code false}.
 * </p>
 * <p>
 * The {@code rsUri} request parameter is required when the resource server
 * requests a JWT introspection response, i.e., when the value of the
 * {@code httpAcceptHeader} request parameter is set to {@code "application/token-introspection+jwt"}.
 * </p>
 * </dd>
 *
 * <dt><b><code>httpAcceptHeader</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the HTTP {@code Accept} header in the introspection
 * request.
 * </p>
 * <p>
 * If the value of the {@code httpAcceptHeader} request parameter is
 * {@code "application/token-introspection+jwt"}, Authlete generates
 * a JWT introspection response. See "<a href="
 * https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-4">
 * 4. Requesting a JWT Response</a>" of "<a href="
 * https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response">
 * JWT Response for OAuth Token Introspection</a>" for more details.
 * </p>
 * </dd>
 *
 * <dt><b><code>introspectionSignAlg</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The JWS {@code alg} algorithm for signing the introspection response.
 * This parameter corresponds to {@code introspection_signed_response_alg}
 * defined in "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-6">
 * 6. Client Metadata</a>" of "JWT Response for OAuth Token Introspection".
 * </p>
 * <p>
 * The default value is {@code RS256}.
 * </p>
 * </dd>
 *
 * <dt><b><code>introspectionEncryptionAlg</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The JWE {@code alg} algorithm for encrypting the introspection response.
 * This parameter corresponds to {@code introspection_encrypted_response_alg}
 * defined in "6. Client Metadata" of "JWT Response for OAuth Token Introspection".
 * </p>
 * <p>
 * If the {@code introspectionEncryptionAlg} request parameter is specified,
 * Authlete generates a JWT introspection response encrypted with the
 * algorithm by this property and the algorithm specified by the {@code
 * introspectionEncryptionEnc} request parameter.
 * </p>
 * </dd>
 *
 * <dt><b><code>introspectionEncryptionEnc</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The JWE {@code enc} algorithm for encrypting the introspection
 * response. This parameter corresponds to {@code introspection_encrypted_response_enc}
 * defined in "6. Client Metadata" of "JWT Response for OAuth Token Introspection".
 * </p>
 * <p>
 * The default value is {@code A128CBC_HS256}.
 * </p>
 * </dd>
 *
 * <dt><b><code>sharedKeyForSign</code></b> (CONDITIONALLY REQUIRED)</dt>
 * <dd>
 * <p>
 * The shared key for signing the introspection response with a symmetric
 * algorithm.
 * </p>
 * <p>
 * The {@code sharedKeyForSign} request parameter is required when the
 * introspection response is requested to be signed with a symmetric
 * algorithm.
 * </p>
 * </dd>
 *
 * <dt><b><code>sharedKeyForEncryption</code></b> (CONDITIONALLY REQUIRED)</dt>
 * <dd>
 * <p>
 * The shared key for encrypting the introspection response with a symmetric
 * algorithm.
 * </p>
 * <p>
 * The {@code sharedKeyForEncryption} request parameter is required
 * when the introspection response is requested to be encrypted with a
 * symmetric algorithm.
 * </p>
 * </dd>
 *
 * <dt><b><code>publicKeyForEncryption</code></b> (CONDITIONALLY REQUIRED)</dt>
 * <dd>
 * <p>
 * The public key for signing the introspection response with an asymmetric
 * algorithm.
 * </p>
 * <p>
 * The {@code publicKeyForEncryption} request parameter is required
 * when the introspection response is requested to be encrypted with an
 * asymmetric algorithm.
 * </p>
 * </dd>
 *
 * <dt><b><code>introspectionSignatureKeyId</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The key ID of the key for signing the introspection response.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see <a href="https://tools.ietf.org/html/rfc7662">RFC 7662, OAuth 2.0 Token Introspection</a>
 * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
 *      >JWT Response for OAuth Token Introspection</a>
 * @see StandardIntrospectionResponse
 * @see AuthleteApi#standardIntrospection(StandardIntrospectionRequest)
 *
 * @author Takahiko Kawasaki
 * @author Hideki Ikeda
 *
 * @since 2.7
 */
public class StandardIntrospectionRequest implements Serializable
{
    private static final long serialVersionUID = 3L;


    /**
     * OAuth 2.0 token introspection request parameters.
     */
    private String parameters;


    /**
     * Flag indicating whether to include hidden properties in the output.
     *
     * @since 2.83
     */
    private boolean withHiddenProperties;


    /**
     * The URI of the resource server making the introspection request.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private URI rsUri;


    /**
     * The value of the HTTP {@code Accept} header in the introspection request.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private String httpAcceptHeader;


    /**
     * The JWS {@code alg} algorithm for signing the introspection
     * response.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private JWSAlg introspectionSignAlg;


    /**
     * The JWE {@code alg} algorithm for encrypting the content encryption
     * key for the introspection response.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private JWEAlg introspectionEncryptionAlg;


    /**
     * The JWE {@code enc} algorithm for encrypting the content of the
     * introspection response.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private JWEEnc introspectionEncryptionEnc;


    /**
     * The shared key for signing the introspection response with a symmetric
     * algorithm.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private String sharedKeyForSign;


    /**
     * The shared key for encrypting the introspection response with a
     * symmetric algorithm.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private String sharedKeyForEncryption;


    /**
     * The public key for encrypting the introspection response with an
     * asymmetric algorithm.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private String publicKeyForEncryption;


    /**
     * The key ID of the key for signing the introspection response.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    private String introspectionSignatureKeyId;


    /**
     * Get the value of {@code parameters} that represents the
     * request parameters which the introspection endpoint of
     * the authorization server received.
     *
     * @return
     *         Request parameters which comply with <a href=
     *         "https://tools.ietf.org/html/rfc7662">RFC 7662</a>.
     *         For example, "{@code
     *         token=pNj1h24a4geA_YHilxrshkRkxJDsyXBZWKp3hZ5ND7A}".
     */
    public String getParameters()
    {
        return parameters;
    }


    /**
     * Set the value of {@code parameters} that represents the
     * request parameters which the introspection endpoint of
     * the authorization server received.
     *
     * @param parameters
     *         Request parameters which comply with <a href=
     *         "https://tools.ietf.org/html/rfc7662">RFC 7662</a>.
     *         For example, "{@code
     *         token=pNj1h24a4geA_YHilxrshkRkxJDsyXBZWKp3hZ5ND7A}".
     *
     * @return
     *         {@code this} object.
     */
    public StandardIntrospectionRequest setParameters(String parameters)
    {
        this.parameters = parameters;

        return this;
    }


    /**
     * Get the flag which indicates whether to include hidden properties
     * associated with the token in the output.
     *
     * <p>
     * Authlete has a mechanism whereby to associate arbitrary key-value pairs
     * with an access token. Each key-value pair has a {@code hidden} attribute.
     * By default, key-value pairs whose {@code hidden} attribute is true are
     * not embedded in the standard introspection output.
     * </p>
     *
     * <p>
     * If the {@code withHiddenProperties} request parameter is given and its
     * value is {@code true}, {@code /api/auth/introspection/standard} API
     * includes all the associated key-value pairs into the output regardless
     * of the value of the {@code hidden} attribute.
     * </p>
     *
     * @return
     *         {@code true} if hidden properties are included in the output.
     *
     * @since 2.83
     */
    public boolean isWithHiddenProperties()
    {
        return withHiddenProperties;
    }


    /**
     * Set the flag which indicates whether to include hidden properties
     * associated with the token in the output.
     *
     * <p>
     * See the description of {@link #isWithHiddenProperties()} for details.
     * </p>
     *
     * @param with
     *         {@code true} to include hidden properties in the output.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.83
     */
    public StandardIntrospectionRequest setWithHiddenProperties(boolean with)
    {
        this.withHiddenProperties = with;

        return this;
    }


    /**
     * Get the URI of the resource server making the introspection request.
     *
     * @return The URI of the resource server making the introspection
     *         request.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    public URI getRsUri()
    {
        return rsUri;
    }


    /**
     * Set the URI of the resource server making the introspection request.
     *
     * @param rsUri
     *         The URI of the resource server making the introspection
     *         request.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    public StandardIntrospectionRequest setRsUri(URI uri)
    {
        this.rsUri = uri;

        return this;
    }


    /**
     * Get the value of the HTTP {@code Accept} header in the introspection
     * request.
     *
     * @return
     *         The value of the HTTP {@code Accept} header in the introspection
     *         request.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    public String getHttpAcceptHeader()
    {
        return httpAcceptHeader;
    }


    /**
     * Set the value of the HTTP {@code Accept} header in the introspection
     * request.
     *
     * @param header
     *         The value of the HTTP {@code Accept} header in the introspection
     *         request.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     */
    public StandardIntrospectionRequest setHttpAcceptHeader(String header)
    {
        this.httpAcceptHeader = header;

        return this;
    }


    /**
     * Get the JWS {@code alg} algorithm for signing the introspection
     * response. This property corresponds to {@code introspection_signed_response_alg}
     * defined in "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-6">
     * 6. Client Metadata</a>" of "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response">
     * JWT Response for OAuth Token Introspection</a>".
     *
     * @return
     *         The JWS {@code alg} algorithm for signing the introspection
     *         response.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public JWSAlg getIntrospectionSignAlg()
    {
        return introspectionSignAlg;
    }


    /**
     * Set the JWS {@code alg} algorithm for signing the introspection
     * response. This property corresponds to {@code introspection_signed_response_alg}
     * defined in "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-6">
     * 6. Client Metadata</a>" of "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response">
     * JWT Response for OAuth Token Introspection</a>".
     *
     * @param alg
     *         The JWS {@code alg} algorithm for signing the introspection
     *         response.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public StandardIntrospectionRequest setIntrospectionSignAlg(JWSAlg alg)
    {
        this.introspectionSignAlg = alg;

        return this;
    }


    /**
     * Get the JWE {@code alg} algorithm for encrypting the introspection
     * response. This property corresponds to {@code introspection_encrypted_response_alg}
     * defined in "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-6">
     * 6. Client Metadata</a>" of "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response">
     * JWT Response for OAuth Token Introspection</a>".
     *
     * @return
     *         The JWE {@code alg} algorithm for encrypting the
     *         introspection response.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public JWEAlg getIntrospectionEncryptionAlg()
    {
        return introspectionEncryptionAlg;
    }


    /**
     * Set the JWE {@code alg} algorithm for encrypting the introspection
     * response. This property corresponds to {@code introspection_encrypted_response_alg}
     * defined in "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-6">
     * 6. Client Metadata</a>" of "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response">
     * JWT Response for OAuth Token Introspection</a>".
     *
     * @param alg
     *         The JWE {@code alg} algorithm for encrypting the
     *         introspection response.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public StandardIntrospectionRequest setIntrospectionEncryptionAlg(JWEAlg alg)
    {
        this.introspectionEncryptionAlg = alg;

        return this;
    }


    /**
     * Get the JWE {@code enc} algorithm for encrypting the introspection
     * response. This property corresponds to {@code introspection_encrypted_response_enc}
     * defined in "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-6">
     * 6. Client Metadata</a>" of "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response">
     * JWT Response for OAuth Token Introspection</a>".
     *
     * @return
     *         The JWE {@code enc} algorithm for encrypting the introspection
     *         response.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public JWEEnc getIntrospectionEncryptionEnc()
    {
        return introspectionEncryptionEnc;
    }


    /**
     * Set the JWE {@code enc} algorithm for encrypting the introspection
     * response. This property corresponds to {@code introspection_encrypted_response_enc}
     * defined in "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response#section-6">
     * 6. Client Metadata</a>" of "<a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response">
     * JWT Response for OAuth Token Introspection</a>".
     *
     * @param enc
     *         The JWE {@code enc} algorithm for encrypting the introspection
     *         response.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public StandardIntrospectionRequest setIntrospectionEncryptionEnc(JWEEnc enc)
    {
        this.introspectionEncryptionEnc = enc;

        return this;
    }


    /**
     * Get the shared key for signing the introspection response with
     * a symmetric algorithm.
     *
     * @return
     *         The shared key for signing the introspection response
     *         with a symmetric algorithm.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public String getSharedKeyForSign()
    {
        return sharedKeyForSign;
    }


    /**
     * Set the shared key for signing the introspection response with
     * a symmetric algorithm.
     *
     * @param key
     *         The shared key for signing the introspection response
     *         with a symmetric algorithm.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public StandardIntrospectionRequest setSharedKeyForSign(String key)
    {
        this.sharedKeyForSign = key;

        return this;
    }


    /**
     * Get the shared key for encrypting the introspection response with
     * a symmetric algorithm.
     *
     * @return
     *         The shared key for encrypting the introspection response
     *         with a symmetric algorithm.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public String getSharedKeyForEncryption()
    {
        return sharedKeyForEncryption;
    }


    /**
     * Set the shared key for encrypting the introspection response with
     * a symmetric algorithm.
     *
     * @param key
     *         The shared key for encrypting the introspection response
     *         with a symmetric algorithm.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public StandardIntrospectionRequest setSharedKeyForEncryption(String key)
    {
        this.sharedKeyForEncryption = key;

        return this;
    }


    /**
     * Get the public key for encrypting the introspection response with
     * an asymmetric algorithm.
     *
     * @return
     *         The public key for encrypting the introspection response
     *         with an asymmetric algorithm.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public String getPublicKeyForEncryption()
    {
        return publicKeyForEncryption;
    }


    /**
     * Set the public key for encrypting the introspection response with
     * an asymmetric algorithm.
     *
     * @param key
     *         The public key for encrypting the introspection response
     *         with an asymmetric algorithm.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public StandardIntrospectionRequest setPublicKeyForEncryption(String key)
    {
        this.publicKeyForEncryption = key;

        return this;
    }


    /**
     * Get the key ID of the key for signing the introspection response.
     *
     * @return
     *         The key ID of the key for signing the introspection response.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public String getIntrospectionSignatureKeyId()
    {
        return introspectionSignatureKeyId;
    }


    /**
     * Set the key ID of the key for signing the introspection response.
     *
     * @param
     *         The key ID of the key for signing the introspection response.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.76
     * @since Authlete 3.0
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/draft-ietf-oauth-jwt-introspection-response"
     *      >JWT Response for OAuth Token Introspection</a>
     */
    public StandardIntrospectionRequest setIntrospectionSignatureKeyId(String keyId)
    {
        this.introspectionSignatureKeyId = keyId;

        return this;
    }
}
