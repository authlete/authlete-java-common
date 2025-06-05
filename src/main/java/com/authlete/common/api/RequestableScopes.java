/*
 * Copyright (C) 2016 Authlete, Inc.
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
package com.authlete.common.api;


import java.io.Serializable;


/**
 * The model class for <code>/api/client/extension/requestable_scopes/<i>*</i></code> API.
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.4
 */
class RequestableScopes implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String[] requestableScopes;


    /**
     * Get the requestable scopes per client.
     *
     * @return
     *         <dl>
     *           <dt>null</dt>
     *           <dd>
     *             {@code null} means that no special settings about requestable
     *             scopes are applied to the client, meaning that the client can
     *             request any scopes supported by the service.
     *           </dd>
     *           <dt>An empty array</dt>
     *           <dd>
     *             The client cannot request any scopes, meaning that values included
     *             in the {@code scope} request parameter in authorization requests
     *             and token requests are all ignored.
     *           </dd>
     *           <dt>An array of scope names</dt>
     *           <dd>
     *             It represents a set of scopes that the client can request.
     *             Scopes that are not listed will be ignored on the server side
     *             even when the client includes them in the {@code scope} request
     *             parameter in authorization requests and token requests.
     *           </dd>
     *         </dl>
     */
    public String[] getRequestableScopes()
    {
        return requestableScopes;
    }


    /**
     * Set the requestable scopes per client.
     *
     * @param requestableScopes
     *         <dl>
     *           <dt>null</dt>
     *           <dd>
     *             {@code null} means that no special settings about requestable
     *             scopes are applied to the client, meaning that the client can
     *             request any scopes supported by the service.
     *           </dd>
     *           <dt>An empty array</dt>
     *           <dd>
     *             The client cannot request any scopes, meaning that values included
     *             in the {@code scope} request parameter in authorization requests
     *             and token requests are all ignored.
     *           </dd>
     *           <dt>An array of scope names</dt>
     *           <dd>
     *             It represents a set of scopes that the client can request.
     *             Scopes that are not listed will be ignored on the server side
     *             even when the client includes them in the {@code scope} request
     *             parameter in authorization requests and token requests.
     *           </dd>
     *         </dl>
     *
     * @return
     *         {@code this} object.
     */
    public RequestableScopes setRequestableScopes(String[] requestableScopes)
    {
        this.requestableScopes = requestableScopes;

        return this;
    }
}
