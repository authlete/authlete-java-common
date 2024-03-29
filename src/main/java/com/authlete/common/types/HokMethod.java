/*
 * Copyright (C) 2018-2023 Authlete, Inc.
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
 * Values for Holder-of-Key method.
 *
 * @since 2.21
 */
public enum HokMethod
{
    /**
     * This represents <a href="https://www.rfc-editor.org/rfc/rfc8705.html#section-3">
     * Mutual-TLS Client Certificate-Bound Access Tokens</a>.
     */
    MTLS,


    /**
     * This represents <a href="https://tools.ietf.org/html/draft-ietf-oauth-token-binding">
     * OAuth 2.0 Token Binding</a>
     */
    OAUTB,
    ;
}
