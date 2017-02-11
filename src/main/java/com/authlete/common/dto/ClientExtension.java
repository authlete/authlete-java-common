/*
 * Copyright (C) 2017 Authlete, Inc.
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
 * Client extension.
 *
 * <p>
 * There are some attributes that belong to a client application but
 * should not be changed by the developer of the client application.
 * Basically, this class holds such attributes.
 * </p>
 *
 * <p>
 * For example, an authorization server may narrow the range of scopes
 * (permissions) that a particular client application can request.
 * In this case, it is meaningless if the developer of the client
 * application can freely decide the set of requestable scopes. It is
 * not the developer of the client application but the administrator
 * of the authorization server that should be allowed to define the
 * set of scopes that the client application can request.
 * </p>
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.39
 */
public class ClientExtension implements Serializable
{
    private static final long serialVersionUID = 1L;


    private boolean requestableScopesEnabled;
    private String[] requestableScopes;


    /**
     * Check whether <i>"Requestable Scopes per Client"</i> is enabled or not.
     *
     * <p>
     * If this method returns {@code true}, a special set of scopes (permissions)
     * is defined on the server side (the {@code requestableScopes} array
     * represents the special set) and scopes which this client application can
     * request are limited to the scopes listed in the set. In other words, this
     * application cannot request scopes that are not included in the set. To be
     * specific, this client application cannot list other scopes in the {@code
     * scope} request parameter when it makes an authorization request. To be
     * exact, other scopes can be listed but will be ignored by the authorization
     * server.
     * </p>
     *
     * <p>
     * On the other hand, if this method returns {@code false}, the valid set of
     * scopes (permissions) that this client application can request is equal to
     * the whole scope set defined by the authorization server.
     * </p>
     *
     * @return
     *         {@code true} if <i>"Requestable Scopes per Client"</i> is enabled
     *         for this client. Otherwise, {@code false}.
     */
    public boolean isRequestableScopesEnabled()
    {
        return requestableScopesEnabled;
    }


    /**
     * Enable or disable <i>"Requestable Scopes per Client"</i>.
     *
     * <p>
     * See the description of {@link #isRequestableScopesEnabled()} for details
     * about <i>"Requestable Scopes per Client"</i>.
     * </p>
     *
     * @param enabled
     *         {@code true} to enable <i>"Requestable Scopes per Client"</i>.
     *         {@code false} to disable it.
     *
     * @return
     *         {@code this} object.
     */
    public ClientExtension setRequestableScopesEnabled(boolean enabled)
    {
        this.requestableScopesEnabled = enabled;

        return this;
    }


    /**
     * Get the set of scopes that this client application can request when
     * <i>"Requestable Scopes per Client"</i> is enabled (= when {@link
     * #isRequestableScopesEnabled()} returns {@code true}).
     *
     * <p>
     * See the description of {@link #isRequestableScopesEnabled()} for details
     * about <i>"Requestable Scopes per Client"</i>.
     * </p>
     *
     * @return
     *         The set of scopes that this client application can request
     *         when <i>"Requestable Scopes per Client"</i> is enabled.
     */
    public String[] getRequestableScopes()
    {
        return requestableScopes;
    }


    /**
     * Set the set of scopes that this client application can request when
     * <i>"Requestable Scopes per Client"</i> is enabled (= when {@link
     * #isRequestableScopesEnabled()} returns {@code true}).
     *
     * <p>
     * See the description of {@link #isRequestableScopesEnabled()} for details
     * about <i>"Requestable Scopes per Client"</i>.
     * </p>
     *
     * @param scopes
     *         A set of scopes.
     *
     * @return
     *         {@code this} object.
     */
    public ClientExtension setRequestableScopes(String[] scopes)
    {
        this.requestableScopes = scopes;

        return this;
    }
}
