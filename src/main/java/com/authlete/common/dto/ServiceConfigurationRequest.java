/*
 * Copyright (C) 2022 Authlete, Inc.
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
 * The request parameters of Authlete's {@code /service/configuration} API.
 *
 * <h3>pretty</h3>
 *
 * <p>
 * If {@code pretty=true} is given to the Authlete API, the returned JSON is
 * formatted in a human-friendly way.
 * </p>
 *
 * <h3>patch</h3>
 *
 * <p>
 * The {@code /service/configuration} API returns JSON that conforms to
 * <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
 * >OpenID Connect Discovery 1.0</a>. Implementations of the discovery
 * endpoint may modify the JSON before returning it as a discovery response.
 * </p>
 *
 * <p>
 * Although implementations of the discovery endpoint have perfect control
 * over the JSON, in some cases implementations may want to make the Authlete
 * API execute adjustments on Authlete side so that the implementations can
 * avoid modifying the received JSON after the API call.
 * </p>
 *
 * <p>
 * The {@code patch} request parameter has been added for the purpose.
 * </p>
 *
 * <p>
 * Implementations of the discovery endpoint may specify a <b>JSON Patch</b>
 * by the {@code patch} request parameter. If the request parameter is given,
 * the Authlete API applies the patch to the JSON before returning it to the
 * API caller.
 * </p>
 *
 * <p>
 * The value of the {@code patch} request parameter must conform to the format
 * that is defined in <a href="https://www.rfc-editor.org/rfc/rfc6902">RFC 6902
 * JavaScript Object Notation (JSON) Patch</a>.
 * </p>
 *
 * <h4>Example 1: Replace</h4>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   | jq .subject_types_supported</b>
 * <font color="darkgreen">[
 *   "public",
 *   "pairwise"
 * ]</font>
 * </pre>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *      -d <font color="darkred">patch</font>='[{"op":<font color="darkred">"replace"</font>,"path":"/subject_types_supported","value":["public"]}]'
 *      | jq .subject_types_supported</b>
 * <font color="darkgreen">[
 *   "public"
 * ]</font>
 * </pre>
 *
 * <h4>Example 2: Add</h4>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   | jq .custom_metadata</b>
 * <font color="darkgreen">null</font>
 * </pre>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   -d <font color="darkred">patch</font>='[{"op":<font color="darkred">"add"</font>,"path":"/custom_metadata","value":"custom_value"}]'
 *   | jq .custom_metadata</b>
 * <font color="darkgreen">"custom_value"</font>
 * </pre>
 *
 * <h4>Example 3: Add Array Elements</h4>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   | jq .acr_values_supported</b>
 * <font color="darkgreen">[
 *   "acr1"
 * ]</font>
 * </pre>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   -d <font color="darkred">patch</font>='[{"op":<font color="darkred">"add"</font>,"path":"/acr_values_supported/0","value":"acr0"}]'
 *   | jq .acr_values_supported</b>
 * <font color="darkgreen">[
 *   "acr0",
 *   "acr1"
 * ]</font>
 * </pre>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   -d <font color="darkred">patch</font>='[{"op":<font color="darkred">"add"</font>,"path":"/acr_values_supported/0","value":"acr0"},
 *              {"op":<font color="darkred">"add"</font>,"path":"/acr_values_supported/-","value":"acr2"}]'
 *   | jq .acr_values_supported</b>
 * <font color="darkgreen">[
 *   "acr0",
 *   "acr1",
 *   "acr2"
 * ]</font>
 * </pre>
 *
 * <h4>Example 4: Add Object Elements</h4>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   | jq .mtls_endpoint_aliases</b>
 * <font color="darkgreen">{
 *   "authorization_endpoint": "https://as.example.com/mtls/authorize"
 * }</font>
 * </pre>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   -d <font color="darkred">patch</font>='[{"op":<font color="darkred">"add"</font>,"path":"/mtls_endpoint_aliases/token_endpoint","value":"https://as.example.com/mtls/token"}]'
 *   | jq .mtls_endpoint_aliases</b>
 * <font color="darkgreen">{
 *   "authorization_endpoint": "https://as.example.com/mtls/authorize",
 *   "token_endpoint": "https://as.example.com/mtls/token"
 * }</font>
 * </pre>
 *
 * <h4>Example 5: Remove</h4>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   | jq .acr_values_supported</b>
 * <font color="darkgreen">[
 *   "acr1"
 * ]</font>
 * </pre>
 *
 * <pre style="border: solid 1px black;" cellpadding="5">
 * $ <b>curl -s -u $SERVICE_API_KEY:$SERVICE_API_SECRET $BASE_URL/api/service/configuration
 *   -d <font color="darkred">patch</font>='[{"op":<font color="darkred">"remove"</font>,"path":"/acr_values_supported"}]'
 *   | jq .acr_values_supported</b>
 * <font color="darkgreen">null</font>
 * </pre>
 *
 * <h4>Note</h4>
 *
 * <p>
 * The {@code patch} request parameter is supported since Authlete 2.2.36.
 * </p>
 *
 * @since 3.43
 *
 * @see <a href="https://openid.net/specs/openid-connect-discovery-1_0.html"
 *      >OpenID Connect Discovery 1.0</a>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6902"
 *      >RFC 6902 JavaScript Object Notation (JSON) Patch</a>
 */
public class ServiceConfigurationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private boolean pretty;
    private String patch;


    /**
     * Get the flag indicating whether the JSON returned from the API is
     * formatted in a human-friendly way.
     *
     * @return
     *         {@code true} if the JSON is formatted in a human-friendly way.
     */
    public boolean isPretty()
    {
        return pretty;
    }


    /**
     * Set the flag indicating whether the JSON returned from the API is
     * formatted in a human-friendly way.
     *
     * @param pretty
     *         {@code true} to format the JSON in a human-friendly way.
     *
     * @return
     *         {@code this} object.
     */
    public ServiceConfigurationRequest setPretty(boolean pretty)
    {
        this.pretty = pretty;

        return this;
    }


    /**
     * Get the JSON Patch (<a href="https://www.rfc-editor.org/rfc/rfc6902"
     * >RFC 6902 JavaScript Object Notation (JSON) Patch</a>) to be applied.
     *
     * @return
     *         The JSON Patch.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc6902"
     *      >RFC 6902 JavaScript Object Notation (JSON) Patch</a>
     */
    public String getPatch()
    {
        return patch;
    }


    /**
     * Set a JSON Patch (<a href="https://www.rfc-editor.org/rfc/rfc6902"
     * >RFC 6902 JavaScript Object Notation (JSON) Patch</a>) to be applied.
     *
     * @param patch
     *         A JSON Patch.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc6902"
     *      >RFC 6902 JavaScript Object Notation (JSON) Patch</a>
     */
    public ServiceConfigurationRequest setPatch(String patch)
    {
        this.patch = patch;

        return this;
    }
}
