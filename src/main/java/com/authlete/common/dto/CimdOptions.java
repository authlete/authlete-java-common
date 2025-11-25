/*
 * Copyright (C) 2025 Authlete, Inc.
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
 * Options for <a href=
 * "https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
 * >OAuth Client ID Metadata Document</a> (CIMD).
 *
 * @since 4.30
 * @since Authlete 3.0.22
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
 *      OAuth Client ID Metadata Document</a>
 */
public class CimdOptions implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * Whether to always retrieve client metadata in the CIMD context
     * regardless of the cache's validity.
     */
    private boolean alwaysRetrieved;


    /**
     * Whether to allow the {@code http} scheme in client IDs in the CIMD
     * context.
     */
    private boolean httpPermitted;


    /**
     * Whether to allow a query component in client IDs in the CIMD context.
     */
    private boolean queryPermitted;


    /**
     * Get the flag that indicates whether Authlete should always fetch the
     * client metadata from the location specified by the client ID, regardless
     * of whether a cached copy already exists and has not yet expired.
     *
     * <p>
     * Under normal circumstances, client metadata retrieved from the location
     * referenced by the client ID is stored in the database with an expiration
     * time calculated using HTTP caching mechanisms (see <a href=
     * "https://www.rfc-editor.org/rfc/rfc9111.html">RFC 9111 HTTP Caching</a>).
     * Until that expiration time is reached, Authlete does not attempt to
     * retrieve the client metadata again.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, Authlete retrieves the client
     * metadata regardless of the cache's validity.
     * </p>
     *
     * <p>
     * If this flag is included in an Authlete API call and its value is
     * {@code true}, it takes precedence over the corresponding service
     * configuration (see {@link Service#isCimdAlwaysRetrieved()}).
     * </p>
     *
     * <p>
     * This flag is effective only when the service supports CIMD (see {@link
     * Service#isClientIdMetadataDocumentSupported()}) and CIMD is actually
     * used to resolve client metadata. For example, if the client ID in a
     * request does not appear to be a valid URI, CIMD will not be used even
     * if the service is configured to support it. In such cases, this flag
     * has no effect.
     * </p>
     *
     * <p>
     * Client metadata retrieval is performed only in the initiating request
     * of an authorization flow, and not in any subsequent requests. For
     * example, in the authorization code flow, metadata may be retrieved
     * during the authorization request, but not during the subsequent token
     * request. In contrast, in the client credentials flow, metadata retrieval
     * may occur because the token request itself is the initiating request
     * in the flow.
     * </p>
     *
     * @return
     *         {@code true} if Authlete attempts to retrieve client metadata
     *         regardless of the cache's validity.
     */
    public boolean isAlwaysRetrieved()
    {
        return alwaysRetrieved;
    }


    /**
     * Set the flag that indicates whether Authlete should always fetch the
     * client metadata from the location specified by the client ID, regardless
     * of whether a cached copy already exists and has not yet expired.
     *
     * <p>
     * Under normal circumstances, client metadata retrieved from the location
     * referenced by the client ID is stored in the database with an expiration
     * time calculated using HTTP caching mechanisms (see <a href=
     * "https://www.rfc-editor.org/rfc/rfc9111.html">RFC 9111 HTTP Caching</a>).
     * Until that expiration time is reached, Authlete does not attempt to
     * retrieve the client metadata again.
     * </p>
     *
     * <p>
     * When this flag is set to {@code true}, Authlete retrieves the client
     * metadata regardless of the cache's validity.
     * </p>
     *
     * <p>
     * If this flag is included in an Authlete API call and its value is
     * {@code true}, it takes precedence over the corresponding service
     * configuration (see {@link Service#isCimdAlwaysRetrieved()}).
     * </p>
     *
     * <p>
     * This flag is effective only when the service supports CIMD (see {@link
     * Service#isClientIdMetadataDocumentSupported()}) and CIMD is actually
     * used to resolve client metadata. For example, if the client ID in a
     * request does not appear to be a valid URI, CIMD will not be used even
     * if the service is configured to support it. In such cases, this flag
     * has no effect.
     * </p>
     *
     * <p>
     * Client metadata retrieval is performed only in the initiating request
     * of an authorization flow, and not in any subsequent requests. For
     * example, in the authorization code flow, metadata may be retrieved
     * during the authorization request, but not during the subsequent token
     * request. In contrast, in the client credentials flow, metadata retrieval
     * may occur because the token request itself is the initiating request
     * in the flow.
     * </p>
     *
     * @param always
     *         {@code true} to instruct Authlete to retrieve client metadata
     *         regardless of the cache's validity.
     *
     * @return
     *         {@code this} object.
     */
    public CimdOptions setAlwaysRetrieved(boolean always)
    {
        this.alwaysRetrieved = always;

        return this;
    }


    /**
     * Get the flag that indicates whether the {@code http} scheme in the client
     * ID is permitted.
     *
     * <p>
     * The specification requires the {@code https} scheme, but if this flag is
     * set to {@code true}, Authlete also allows the {@code http} scheme. The
     * main purpose of this option is to make development easier for developers
     * who run CIMD-enabled servers and a web server publishing client metadata
     * on their local machines without TLS.
     * </p>
     *
     * <p>
     * Given this purpose, it is not recommended to enable this option in
     * production environments unless an allowlist is used (see {@link
     * Service#isCimdAllowlistEnabled()}).
     * </p>
     *
     * <p>
     * If this flag is included in an Authlete API call and its value is
     * {@code true}, it takes precedence over the corresponding service
     * configuration (see {@link Service#isCimdHttpPermitted()}).
     * </p>
     *
     * @return
     *         {@code true} if the {@code http} scheme in the client ID is
     *         permitted.
     */
    public boolean isHttpPermitted()
    {
        return httpPermitted;
    }


    /**
     * Set the flag that indicates whether the {@code http} scheme in the client
     * ID is permitted.
     *
     * <p>
     * The specification requires the {@code https} scheme, but if this flag is
     * set to {@code true}, Authlete also allows the {@code http} scheme. The
     * main purpose of this option is to make development easier for developers
     * who run CIMD-enabled servers and a web server publishing client metadata
     * on their local machines without TLS.
     * </p>
     *
     * <p>
     * Given this purpose, it is not recommended to enable this option in
     * production environments unless an allowlist is used (see {@link
     * Service#isCimdAllowlistEnabled()}).
     * </p>
     *
     * <p>
     * If this flag is included in an Authlete API call and its value is
     * {@code true}, it takes precedence over the corresponding service
     * configuration (see {@link Service#isCimdHttpPermitted()}).
     * </p>
     *
     * @param permitted
     *         {@code true} to permit the {@code http} scheme in the client ID.
     *
     * @return
     *         {@code this} object.
     */
    public CimdOptions setHttpPermitted(boolean permitted)
    {
        this.httpPermitted = permitted;

        return this;
    }


    /**
     * Get the flag that indicates whether a query component in the client ID
     * is permitted.
     *
     * <p>
     * Although the specification states that a client ID <i>"SHOULD NOT
     * include a query string component,"</i> it does technically allow it.
     * However, query components are prone to misuse. Therefore, Authlete does
     * not allow them by default. Setting this flag to {@code true} relaxes
     * that restriction.
     * </p>
     *
     * <p>
     * If this flag is included in an Authlete API call and its value is
     * {@code true}, it takes precedence over the corresponding service
     * configuration (see {@link Service#isCimdQueryPermitted()}).
     * </p>
     *
     * @return
     *         {@code true} if a query component in the client ID is permitted.
     */
    public boolean isQueryPermitted()
    {
        return queryPermitted;
    }


    /**
     * Set the flag that indicates whether a query component in the client ID
     * is permitted.
     *
     * <p>
     * Although the specification states that a client ID <i>"SHOULD NOT
     * include a query string component,"</i> it does technically allow it.
     * However, query components are prone to misuse. Therefore, Authlete does
     * not allow them by default. Setting this flag to {@code true} relaxes
     * that restriction.
     * </p>
     *
     * <p>
     * If this flag is included in an Authlete API call and its value is
     * {@code true}, it takes precedence over the corresponding service
     * configuration (see {@link Service#isCimdQueryPermitted()}).
     * </p>
     *
     * @param permitted
     *         {@code true} to permit a query component in the client ID.
     *
     * @return
     *         {@code this} object.
     */
    public CimdOptions setQueryPermitted(boolean permitted)
    {
        this.queryPermitted = permitted;

        return this;
    }
}
