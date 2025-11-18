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
package com.authlete.common.types;


/**
 * The source of a client.
 *
 * <blockquote>
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *   <tr bgcolor="orange">
 *     <th>enum value</th>
 *     <th>description</th>
 *   </tr>
 *   <tr>
 *     <td>{@link #STATIC_REGISTRATION}</td>
 *     <td>Static registration.</td>
 *   </tr>
 *   <tr>
 *     <td>{@link #DYNAMIC_REGISTRATION}</td>
 *     <td>
 *       Dynamic registration defined in
 *       <a href="https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591:
 *       OAuth 2.0 Dynamic Client Registration Protocol</a> or
 *       <a href="https://openid.net/specs/openid-connect-registration-1_0.html">
 *       OpenID Connect Dynamic Client Registration 1.0</a>.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@link #AUTOMATIC_REGISTRATION}</td>
 *     <td>
 *       Automatic registration defined in
 *       <a href="https://openid.net/specs/openid-federation-1_0.html">OpenID
 *       Federation 1.0</a>.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@link #EXPLICIT_REGISTRATION}</td>
 *     <td>
 *       Explicit registration defined in
 *       <a href="https://openid.net/specs/openid-federation-1_0.html">OpenID
 *       Federation 1.0</a>.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>{@link #METADATA_DOCUMENT}</td>
 *     <td>
 *       Discovery using
 *       <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
 *       OAuth Client ID Metadata Document</a>.
 *     </td>
 *   </tr>
 * </table>
 * </blockquote>
 *
 * @since 4.29
 * @since Authlete 3.0.22
 */
public enum ClientSource
{
    /**
     * Static registration.
     */
    STATIC_REGISTRATION,


    /**
     * Dynamic registration defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc7591.html">RFC 7591: OAuth
     * 2&#x2E;0 Dynamic Client Registration Protocol</a> or
     * <a href="https://openid.net/specs/openid-connect-registration-1_0.html"
     * >OpenID Connect Dynamic Client Registration 1&#x2E;0</a>.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc7591.html">
     *      RFC 7591: OAuth 2.0 Dynamic Client Registration Protocol</a>
     *
     * @see <a href="https://openid.net/specs/openid-connect-registration-1_0.html">
     *      OpenID Connect Dynamic Client Registration 1.0</a>
     */
    DYNAMIC_REGISTRATION,


    /**
     * Automatic registration defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x2E;0</a>.
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html">
     *      OpenID Federation 1.0</a>
     */
    AUTOMATIC_REGISTRATION,


    /**
     * Explicit registration defined in
     * <a href="https://openid.net/specs/openid-federation-1_0.html"
     * >OpenID Federation 1&#x2E;0</a>.
     *
     * @see <a href="https://openid.net/specs/openid-federation-1_0.html">
     *      OpenID Federation 1.0</a>
     */
    EXPLICIT_REGISTRATION,


    /**
     * Discovery using
     * <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/"
     * >OAuth Client ID Metadata Document</a>.
     *
     * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-client-id-metadata-document/">
     *      OAuth Client ID Metadata Document</a>
     */
    METADATA_DOCUMENT,
    ;
}
