変更点
======

2.23 (2018 年 07 月 20 日)
--------------------------

- `AuthleteApi` インターフェース
    * `verifyJose(JoseVerifyRequest)` メソッドを追加。

- 新しいクラス
    * `JoseVerifyRequest`
    * `JoseVerifyResponse`


2.22 (2018 年 07 月 03 日)
--------------------------

- `AuthleteApi` インターフェース
    * `registerClient(ClientRegistrationRequest)` メソッドを追加。

- `AuthorizationResponse` クラス
    * `getRequestObjectPayload()` メソッドを追加。
    * `setRequestObjectPayload(String)` メソッドを追加。

- `ErrorCode` 列挙型
    * `invalid_redirect_uri` を追加。
    * `invalid_client_metadata` を追加。
    * `invalid_software_statement` を追加。
    * `unapproved_software_statement` を追加。

- 新しいクラス
    * `AccessToken`
    * `ClientRegistrationRequest`
    * `ClientRegistrationResponse`
    * `TokenListResponse`


2.21 (2018 年 06 月 12 日)
--------------------------

- 新しい列挙型
    * `HokMethod`


2.20 (2018 年 05 月 16 日)
--------------------------

- `Client` クラス
    * `getSelfSignedCertificateKeyId()` メソッドを追加。
    * `setSignedCertificateKeyId(String)` メソッドを追加。


2.19 (2018 年 05 月 10 日)
--------------------------

- `Client` クラス
    * `isTlsClientCertificateBoundAccessTokens()` メソッドを追加。
    * `setTlsClientCertificateBoundAccessTokens(boolean)` メソッドを追加。
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを削除。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを削除。

- `Service` クラス
    * `isTlsClientCertificateBoundAccessTokens()` メソッドを追加。
    * `setTlsClientCertificateBoundAccessTokens(boolean)` メソッドを追加。
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを削除。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを削除。


2.18 (2018 年 05 月 09 日)
--------------------------

- `AuthleteApi` インターフェース
    * `createService(Service)` メソッドを追加。
    * `createServie(Service)` メソッドを deprecated 化。


2.17 (2018 年 04 月 18 日)
--------------------------

- `JWEAlg` 列挙型
    * `getName()` メソッドを追加。

- `JWEEnc` 列挙型
    * `getName()` メソッドを追加。

- `JWSAlg` 列挙型
    * `getName()` メソッドを追加。


2.16 (2018 年 04 月 18 日)
--------------------------

- `JWSAlg` 列挙型
    * `isSymmetric()` メソッドを追加。
    * `isAsymmetric()` メソッドを追加。


2.15 (2018 年 04 月 12 日)
--------------------------

- `Service` クラス
    * `isMutualTlsValidatePkiCertChain()` メソッドを追加。
    * `setMutualTlsValidatePkiCertChain(boolean)` メソッドを追加。
    * `getTrustedRootCertificates()` メソッドを追加。
    * `setTrustedRootCertificates(String[])` メソッドを追加。

- `TokenRequest` クラス
    * `getClientCertificatePath()` メソッドを追加。
    * `setClientCertificatePath(String[])` メソッドを追加。


2.14 (2018 年 03 月 14 日)
--------------------------

- `IntrospectionRequest` クラス
    * `getClientCertificate()` メソッドを追加。
    * `setClientCertificate(String)` メソッドを追加。

- `IntrospectionResponse` クラス
    * `getCertificateThumbprint()` メソッドを追加。
    * `setCertificateThumbprint(String)` メソッドを追加。


2.13 (2018 年 03 月 13 日)
--------------------------

- `Client` クラス
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを追加。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを追加。

- `Service` クラス
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを追加。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを追加。
    * `getSupportedRevocationAuthMethods()` メソッドを追加。
    * `setSupportedRevocationAuthMethods()` メソッドを追加。
    * `getSupportedRevocationAuthSigningAlgorithms()` メソッドを追加。
    * `setSupportedRevocationAuthSigningAlgorithms(JWSAlg[])` メソッドを追加。
    * `getIntrospectionEndpoint()` メソッドを追加。
    * `setIntrospectionEndpoint()` メソッドを追加。
    * `getSupportedIntrospectionAuthMethods()` メソッドを追加。
    * `setSupportedIntrospectionAuthMethods()` メソッドを追加。
    * `getSupportedIntrospectionAuthSigningAlgorithms()` メソッドを追加。
    * `setSupportedIntrospectionAuthSigningAlgorithms(JWSAlg[])` メソッドを追加。


2.12 (2018 年 03 月 03 日)
--------------------------

- `JWSAlg` 列挙型
    * `isSymmetric(JWSAlg)` メソッドを追加。
    * `isAsymmetric(JWSAlg)` メソッドを追加。

- `Scope` クラス
    * `getAttributes()` メソッドを追加。
    * `setAttributes(Pair[])` メソッドを追加。
    * `setAttributes(Iterable<Pair>)` メソッドを追加。

- `Service` クラス
    * `getSupportedServiceProfiles()` メソッドを追加。
    * `setSupportedServiceProfiles(ServiceProfile[])` メソッドを追加。
    * `setSupportedServiceProfiles(Iterable<ServiceProfile>)` メソッドを追加。
    * `supports(ServiceProfile)` メソッドを追加。
    * `supportsAll(ServiceProfile...)` メソッドを追加。
    * `supportsAll(Iterable<ServiceProfile>)` メソッドを追加。
    * `supportsAny(ServiceProfile...)` メソッドを追加。
    * `supportsAny(Iterable<ServiceProfile>)` メソッドを追加。

- `TokenRequest` クラス
    * `getClientCertificate()` メソッドを追加。
    * `setClientCertificate(String)` メソッドを追加。

- 新しい列挙型
    * `ServiceProfie`


2.11 (2017 年 11 月 16 日)
--------------------------

- `AuthleteApi` インターフェース
    * `refreshClientSecret(long)` メソッドを追加。
    * `refreshClientSecret(String)` メソッドを追加。
    * `updateClientSecret(long, String)` メソッドを追加。
    * `updateClientSecret(String, String)` メソッドを追加。

- `AuthorizationFailRequest.Reason` 列挙型
    * `ACCOUNT_SELECTION_REQUIRED` を追加。
    * `CONSENT_REQUIRED` を追加。
    * `INTERACTION_REQUIRED` を追加。

- `AuthorizationResponse` クラス
    * JavaDoc を更新。
    * `getLowestPrompt()` メソッドを非推奨とマーク。

- `Client` クラス
    * `tlsClientAuthRootDn` プロパティーを削除。

- `ClientAuthMethod` 列挙型
    * `SELF_SIGNED_TLS_CLIENT_AUTH` を追加。

- 新しいクラス
    * `ClientSecretRefreshResponse` クラス
    * `ClientSecretUpdateRequest` クラス
    * `ClientSecretUpdateResponse` クラス


2.10 (2017 年 10 月 18 日)
--------------------------

- `Settings` クラス
    * `getReadTimeout()` メソッドを追加。
    * `setReadTimeout(int)` メソッドを追加。


2.9 (2017 年 10 月 13 日)
-------------------------

- `AuthleteApi` インターフェース
    * `getSettings()` メソッドを追加。

- 新しいクラス
    * `Settings` クラス


2.8 (2017 年 10 月 13 日)
-------------------------

- `TokenResponse` クラス
    * `grantType` プロパティーを追加。
    * `clientId` プロパティーを追加。
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAliasUsed` プロパティーを追加。
    * `subject` プロパティーを追加。
    * `scopes` プロパティーを追加。
    * `properties` プロパティーを追加。

- `TokenIssueResponse` クラス
    * `clientId` プロパティーを追加。
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAliasUsed` プロパティーを追加。
    * `subject` プロパティーを追加。
    * `scopes` プロパティーを追加。
    * `properties` プロパティーを追加。


2.7 (2017 年 7 月 20 日)
------------------------

- `AuthleteApi` インターフェース
    * `standardIntrospection(StandardIntrospectionRequest)` メソッドを追加。

- `Client` クラス
    * `tlsClientAuthSubjectDn` プロパティーを追加。
    * `tlsClientAuthRootDn` プロパティーを追加。

- `ClientAuthMethod` 列挙型
    * `TLS_CLIENT_AUTH` を追加。

- 新しいクラス
    * `StandardIntrospectionRequest` クラス
    * `StandardIntrospectionResponse` クラス


2.6 (2017 年 6 月 10 日)
------------------------

- `TokenCreateRequest` クラス
    * `accessToken` プロパティーを追加。
    * `refreshToken` プロパティーを追加。


2.5 (2017 年 4 月 19 日)
------------------------

- `UserInfoResponse` クラス
    * `properties` プロパティーを追加。
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAilasUsed` プロパティーを追加。

- `Utils` クラス
    * `stringifyPrompts(Prompt[])` メソッドを追加。
    * `stringifyProperties(Property[])` メソッドを追加。
    * `stringifyScopeNames(Scope[])` メソッドを追加。


2.4 (2017 年 4 月 19 日)
------------------------

- `Service` クラス
    * `idTokenEncryptionKeyId` プロパティーを削除。
    * `userInfoEncryptionKeyId` プロパティーを削除。


2.3 (2017 年 4 月 6 日)
-----------------------

- `AuthorizationResponse` クラス
    * `clientIdAliasUsed` プロパティーを追加。

- `IntrospectionResponse` クラス
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAliasUsed` プロパティーを追加。

- `TokenCreateRequest` クラス
    * `clientIdAliasUsed` プロパティーを追加。


2.2 (2017 年 4 月 2 日)
-----------------------

- `Service` クラス
    * `clientIdAliasEnabled` プロパティーを追加。

- `Client` クラス
    * `clientIdAliasEnabled` プロパティーを追加。


2.1 (2017 年 3 月 18 日)
------------------------

- `AuthleteApi` インターフェース
    * `deleteClientAuthorization(long, String)` メソッドを追加。
    * `getClientAuthorizationList(ClientAuthorizationGetListRequest)` メソッドを追加。
    * `updateClientAuthorization(long, ClientAuthorizationUpdateRequest)` メソッドを追加。

- `AuthleteApiImpl` クラス
    * `AuthleteApi` インターフェースに新規追加されたメソッドを実装。
    * POST リクエスト時に `Content-Type:application/json` が設定されていない不具合を修正。

- `Service` クラス
    * `idTokenSignatureKeyId` プロパティーを追加。
    * `idTokenEncryptionKeyId` プロパティーを追加。
    * `userInfoSignatureKeyId` プロパティーを追加。
    * `userInfoEncryptionKeyId` プロパティーを追加。

- `Client` クラス
    * `clientIdAlias` プロパティーを追加。

- `ClientAuthorizationUpdateRequest` クラス
    * `ClientAuthorizationUpdateRequest(String, String[])` コンストラクタを追加。

- `ClientAuthorizationDeleteRequest` クラス
    * 新規追加。

- `ClientAuthorizationGetListRequest` クラス
    * 新規追加。

- `CLI` クラス
    * `getClientAuthorizationList` API をサポート。


2.0 (2017 年 2 月 27 日)
------------------------

- `HttpURLConnection` による `AuthleteApi` インターフェースの実装を追加。
  `AuthleteApiFactory` 内にある既知の実装リストに新しい実装を追加。

- Authlete API のコマンドラインインターフェースを実装。
  `CLI` クラスと `authlete-cli.sh` スクリプトを追加。

- `Utils` クラス
    * `toJson(Object, boolean)` メソッドを追加。
    * `fromJson(String, class)` メソッドを追加。

- `authlete.properties` を `.gitignore` に追加。


1.41 (2017 年 2 月 19 日)
-------------------------

- `ClientExtension` クラスに `setRequestableScopes(Set)` メソッドを追加。


1.40 (2017 年 2 月 17 日)
-------------------------

- `AuthleteApi` インターフェースに `deleteGrantedScopes(long, String)`
  メソッドを追加。

- `Service` クラスから `properties` プロパティーを削除。


1.39 (2017 年 2 月 13 日)
-------------------------

- 新しいクラスを追加
    * `ClientExtension` クラス
    * `Pair` クラス

- `AuthleteApi` インターフェース
    * `getGrantedScopes(long, String)` メソッドを追加。

- `Client` クラス
    * `extension` フィールドを追加。

- `Service` クラス
    * `directIntrospectionEndpointEnabled` フィールドを追加。
    * `errorDescriptionOmitted` フィールドを追加。
    * `errorUriOmitted` フィールドを追加。
    * `metadata` フィールドを追加。
    * `properties` プロパティーを deprecated 化。


1.38 (2016 年 9 月 20 日)
-------------------------

- `GrantedScopesGetResponse` クラスを追加。


1.37 (2016 年 9 月 12 日)
-------------------------

- `ClientAuthorizationUpdateRequest` クラスを追加。


1.36 (2016 年 9 月 6 日)
------------------------

- `AuthorizedClientListResponse` クラスを追加。


1.35 (2016 年 8 月 15 日)
-------------------------

- `AuthorizationIssueRequest`
    * `sub` クレームの値を調整するための `sub` フィールドを追加。

- `UserInfoIssueRequest`
    * `sub` クレームの値を調整するための `sub` フィールドを追加。


1.34 (2016 年 7 月 30 日)
-------------------------

- 新しいクラス
    * `TokenUpdateRequest` クラスを追加。
    * `TokenUpdateResponse` クラスを追加。

- `AuthleteApi`
    * `tokenUpdate(TokenUpdateRequest)` メソッドを追加。
    * `getRequestableScopes(long clientId)` メソッドを追加。
    * `setRequestableScopes(long clientId, String[] scopes)` メソッドを追加。
    * `deleteRequestableScopes(long clientId)` メソッドを追加。

- `AuthorizationIssueRequest`
    * 元の認可リクエストに含まれているスコープ群を置き換えるための
      `scopes` フィールドを追加。

- `AuthorizationIssueResponse`
    * `accessToken` フィールドを追加。
    * `accessTokenExpiresAt` フィールドを追加。
    * `accessTokenDuration` フィールドを追加。
    * `idToken` フィールドを追加。
    * `authorizationCode` フィールドを追加。

- `AuthorizationResponse`
    * 元の認可リクエストに含まれる `prompt` パラメーターの値を示す
      `prompts` フィールドを追加。

- `TokenCreateResponse`
    * `properties` フィールドを追加。

- `TokenIssueResponse`
    * `accessToken` フィールドを追加。
    * `accessTokenExpiresAt` フィールドを追加。
    * `accessTokenDuration` フィールドを追加。
    * `refreshToken` フィールドを追加。
    * `refreshTokenExpiresAt` フィールドを追加。
    * `refreshTokenDuration` フィールドを追加。

- `TokenResponse`
    * `accessToken` フィールドを追加。
    * `accessTokenExpiresAt` フィールドを追加。
    * `accessTokenDuration` フィールドを追加。
    * `refreshToken` フィールドを追加。
    * `refreshTokenExpiresAt` フィールドを追加。
    * `refreshTokenDuration` フィールドを追加。
    * `idToken` フィールドを追加。
