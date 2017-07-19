/*
 * Copyright (C) 2017 Authlete, Inc.
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
import com.authlete.common.api.AuthleteApi;


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
 * </dl>
 * </blockquote>
 *
 * @see <a href="http://tools.ietf.org/html/rfc7662">RFC 7662, OAuth 2.0 Token Introspection</a>
 * @see StandardIntrospectionResponse
 * @see AuthleteApi#standardIntrospection(StandardIntrospectionRequest)
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.7
 */
public class StandardIntrospectionRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * OAuth 2.0 token introspection request parameters.
     */
    private String parameters;


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
}
