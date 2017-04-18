変更点
======

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
