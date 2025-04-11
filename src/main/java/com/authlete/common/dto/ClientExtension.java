/*
 * Copyright (C) 2017-2022 Authlete, Inc.
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
import java.util.Set;


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
    private static final long serialVersionUID = 5L;


    private boolean requestableScopesEnabled;
    private String[] requestableScopes;
    private long accessTokenDuration;
    private long refreshTokenDuration;
    private boolean tokenExchangePermitted;

    /**
     * @since Authlete 3.0.14
     */
    private long idTokenDuration;

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
     *
     * @since 1.41
     */
    public ClientExtension setRequestableScopes(Set<String> scopes)
    {
        if (scopes == null)
        {
            this.requestableScopes = null;

            return this;
        }

        int size = scopes.size();

        this.requestableScopes = new String[size];

        if (size != 0)
        {
            scopes.toArray(this.requestableScopes);
        }

        return this;
    }


    /**
     * Get the value of the duration of access tokens per client in seconds.
     *
     * <p>
     * In normal cases, the value of the {@link Service service}'s
     * {@code accessTokenDuration} property is used as the duration of access
     * tokens issued by the service. However, if this {@code accessTokenDuration}
     * property holds a non-zero positive number and its value is less than the
     * duration configured by the service, the value is used as the duration of
     * access tokens issued to the client application.
     * </p>
     *
     * <p>
     * Note that the duration of access tokens can be controlled by the scope
     * attribute {@code "access_token.duration"}, too. Authlete chooses the
     * minimum value among the candidates.
     * </p>
     *
     * @return
     *         The duration of access tokens per client in seconds.
     *
     * @since 2.59
     */
    public long getAccessTokenDuration()
    {
        return accessTokenDuration;
    }


    /**
     * Set the value of the duration of access tokens per client in seconds.
     *
     * <p>
     * In normal cases, the value of the {@link Service service}'s
     * {@code accessTokenDuration} property is used as the duration of access
     * tokens issued by the service. However, if this {@code accessTokenDuration}
     * property holds a non-zero positive number and its value is less than the
     * duration configured by the service, the value is used as the duration of
     * access tokens issued to the client application.
     * </p>
     *
     * <p>
     * Note that the duration of access tokens can be controlled by the scope
     * attribute {@code "access_token.duration"}, too. Authlete chooses the
     * minimum value among the candidates.
     * </p>
     *
     * @param duration
     *         The duration of access tokens per client in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.59
     */
    public ClientExtension setAccessTokenDuration(long duration)
    {
        this.accessTokenDuration = duration;

        return this;
    }


    /**
     * Get the value of the duration of refresh tokens per client in seconds.
     *
     * <p>
     * In normal cases, the value of the {@link Service service}'s
     * {@code refreshTokenDuration} property is used as the duration of refresh
     * tokens issued by the service. However, if this {@code refreshTokenDuration}
     * property holds a non-zero positive number and its value is less than the
     * duration configured by the service, the value is used as the duration of
     * refresh tokens issued to the client application.
     * </p>
     *
     * <p>
     * Note that the duration of refresh tokens can be controlled by the scope
     * attribute {@code "refresh_token.duration"}, too. Authlete chooses the
     * minimum value among the candidates.
     * </p>
     *
     * @return
     *         The duration of refresh tokens per client in seconds.
     *
     * @since 2.59
     */
    public long getRefreshTokenDuration()
    {
        return refreshTokenDuration;
    }


    /**
     * Set the value of the duration of refresh tokens per client in seconds.
     *
     * <p>
     * In normal cases, the value of the {@link Service service}'s
     * {@code refreshTokenDuration} property is used as the duration of refresh
     * tokens issued by the service. However, if this {@code refreshTokenDuration}
     * property holds a non-zero positive number and its value is less than the
     * duration configured by the service, the value is used as the duration of
     * refresh tokens issued to the client application.
     * </p>
     *
     * <p>
     * Note that the duration of refresh tokens can be controlled by the scope
     * attribute {@code "refresh_token.duration"}, too. Authlete chooses the
     * minimum value among the candidates.
     * </p>
     *
     * @param duration
     *         The duration of refresh tokens per client in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 2.59
     */
    public ClientExtension setRefreshTokenDuration(long duration)
    {
        this.refreshTokenDuration = duration;

        return this;
    }


    /**
     * Get the flag indicating whether the client is explicitly given a
     * permission to make token exchange requests (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * This flag is referred to only when the
     * {@code tokenExchangeByPermittedClientOnly} flag of the service which
     * the client belongs to is {@code true}.
     * </p>
     *
     * @return
     *         {@code true} when the client is explicitly given a permission
     *         to make token exchange requests.
     *
     * @see Service#isTokenExchangeByPermittedClientsOnly()
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public boolean isTokenExchangePermitted()
    {
        return tokenExchangePermitted;
    }


    /**
     * Set the flag indicating whether the client is explicitly given a
     * permission to make token exchange requests (cf&#x002E; <a href=
     * "https://www.rfc-editor.org/rfc/rfc8693.html">RFC 8693</a>).
     *
     * <p>
     * This flag is referred to only when the
     * {@code tokenExchangeByPermittedClientOnly} flag of the service which
     * the client belongs to is {@code true}.
     * </p>
     *
     * @param permitted
     *         {@code true} to explicitly give the client a permission to
     *         make token exchange requests.
     *
     * @return
     *         {@code this} instance.
     *
     * @see Service#setTokenExchangeByPermittedClientsOnly(boolean)
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc8693.html"
     *      >RFC 8693 OAuth 2.0 Token Exchange</a>
     *
     * @since 3.26
     * @since Authlete 2.3
     */
    public ClientExtension setTokenExchangePermitted(boolean permitted)
    {
        this.tokenExchangePermitted = permitted;

        return this;
    }

    /**
     * Get the value of the duration of ID tokens per client in seconds.
     *
     * <p>
     * In normal cases, the value of the {@link Service service}'s
     * {@code idTokenDuration} property is used as the duration of ID
     * tokens issued by the service. However, if this {@code idTokenDuration}
     * property holds a non-zero positive number and its value is less than the
     * duration configured by the service, the value is used as the duration of
     * ID tokens issued to the client application.
     * </p>
     *
     * <p>
     * Note that the duration of ID tokens can be controlled by the scope
     * attribute {@code "id_token.duration"}, too. Authlete chooses the
     * minimum value among the candidates.
     * </p>
     *
     * @return
     *         The duration of ID tokens per client in seconds.
     *
     * @since 4.18
     * @since Authlete 3.0.14
     */
    public long getIdTokenDuration()
    {
        return idTokenDuration;
    }


    /**
     * Set the value of the duration of ID tokens per client in seconds.
     *
     * <p>
     * In normal cases, the value of the {@link Service service}'s
     * {@code idTokenDuration} property is used as the duration of ID
     * tokens issued by the service. However, if this {@code idTokenDuration}
     * property holds a non-zero positive number and its value is less than the
     * duration configured by the service, the value is used as the duration of
     * access tokens issued to the client application.
     * </p>
     *
     * <p>
     * Note that the duration of access tokens can be controlled by the scope
     * attribute {@code "id_token.duration"}, too. Authlete chooses the
     * minimum value among the candidates.
     * </p>
     *
     * @param duration
     *         The duration of ID tokens per client in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 4.18
     * @since Authlete 3.0.14
     */
    public ClientExtension setIdTokenDuration(long duration)
    {
        this.idTokenDuration = duration;

        return this;
    }
}
