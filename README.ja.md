Java 用 Authlete 共通ライブラリ
===============================

概要
----

このライブラリは [Authlete Web API][2] のラッパーです。

[Authlete][1] は [OAuth 2.0][3] と [OpenID Connect][4] の実装を提供するクラウドサービスです
([overview][5])。 Authlete が提供する Web API を使い、DB-less (データベース無し)
の認可サーバーを構築することができます。「DB-less」とは、認可データ (アクセストークン等)、
認可サーバーの設定、クライアントアプリケーションの設定を保存するデータベースを持つ必要が無い、という意味です。
これらのデータはクラウド上にある Authlete サーバーに保存されます。

[java-oauth-server][6] は、このライブラリおよび [authlete-java-jaxrs][7]
ライブラリを用いて書かれた、認可サーバーのリファレンス実装です。
あなたの認可サーバー実装の開始点として活用してください。


ライセンス
----------

  Apache License, Version 2.0


Maven
-----

```xml
<dependency>
    <groupId>com.authlete</groupId>
    <artifactId>authlete-java-common</artifactId>
    <version>2.29</version>
</dependency>
```


ソースコード
------------

  <code>https://github.com/authlete/authlete-java-common</code>


JavaDoc
-------

  <code>http://authlete.github.io/authlete-java-common/</code>


説明
----

#### AuthleteApi の取得方法

[Authlete Web API][2] とやりとりするメソッドは全て `AuthleteApi` インターフェースに集められています。
`AuthleteApiFactory` クラスの `create()` メソッドを呼ぶと、`AuthleteApi` の実装を取得できます。
このメソッドには二つのバリアントがあります。

```java
public static AuthleteApi
    create(AuthleteConfiguration configuration);

public static AuthleteApi
    create(AuthleteConfiguration configuration, String className);
```

どちらのメソッドも `AuthleteConfiguration` を第一引数として受け取ります。
`AuthleteConfiguration` は、Authlete サーバーの URL やサービスの API クレデンシャルズなどの、Authlete
Web API にアクセスするのに必要な設定値を保持するインターフェースです。
具体的には、このインターフェースには次のようなメソッド群があります。

| メソッド                     | 説明                                  |
|:-----------------------------|:--------------------------------------|
| `getBaseUrl()`               | Authlete サーバーの URL               |
| `getServiceApiKey()`         | サービスの API キー                   |
| `getServiceApiSecret()`      | サービスの API シークレット           |
| `getServiceOwnerApiKey()`    | あなたのアカウントの API キー         |
| `getServiceOwnerApiSecret()` | あなたのアカウントの API シークレット |

authlete-java-common ライブラリには、`AuthleteConfiguration` インターフェースの実装が三つ含まれています。

| クラス                            | 説明                           |
|:----------------------------------|:-------------------------------|
| `AuthleteEnvConfiguration`        | 環境変数による設定             |
| `AuthletePropertiesConfiguration` | プロパティーファイルによる設定 |
| `AuthleteSimpleConfiguration`     | POJO による設定                |

これらの実装を使うこともできますし、インターフェースの実装を自分で作成することもできます。
いずれにしても、`AuthleteConfiguration` インスタンスを `AuthleteApiFactory` クラスの
`create()` メソッドに渡すことで、`AuthleteApi` の実装を取得することができます。

まとめると、`AuthleteApi` の実装を取得する流れは次のようになります。

```java
// AuthleteConfiguration インターフェースのインスタンスを用意する。
AuthleteConfiguration configuration = ...;

// AuthleteApi インターフェースのインスタンスを取得する。
AuthleteApi api = AuthleteApiFactory.create(configuration);
```

より簡単な方法がよければ、`AuthleteApiFactory.getDefaultApi()` メソッドを使用してください。
このメソッドは、ファイルシステムとクラスパスから、`authlete.properties`
という名前のプロパティーファイルを探し、`AuthletePropertiesConfiguration`
クラスを使ってそのファイルの内容を読み込みます。

```java
//ファイルシステムとクラスパスから "authlete.properties" を探す。
AuthleteApi api = AuthleteApiFactory.getDefaultApi();
```

`AuthleteApiFactory.getDefaultApi()` メソッドは結果をキャッシュするので、
ファイルを読み込む処理のオーバーヘッドを気にせずに何回でも呼ぶことができます。


#### AuthletePropertiesConfiguration

`AuthleteConfiguration` インターフェースの三つの実装のうち、ここでは
`AuthletePropertiesConfiguration` クラスについて説明します。

`AuthletePropertiesConfiguration` クラスは、Authlete Web API
へのアクセスに必要な設定をプロパティーファイルでおこなう仕組みを提供します。
このクラスは、ファイルシステムとクラスパスから指定されたファイルを探します。

プロパティーファイル内で有効なプロパティーキーとその意味は次のとおりです。

| プロパティーキー                     | 説明                                                  |
|:-------------------------------------|:------------------------------------------------------|
| `base_url`                           | Authlete サーバーの URL                               |
| `service.api_key`                    | サービスの API キー                                   |
| `service.api_secret`                 | サービスの API シークレット                           |
| `service.api_secret.encrypted`       | サービスの API シークレットを暗号化したもの           |
| `service_owner.api_key`              | あなたのアカウントの API キー                         |
| `service_owner.api_secret`           | あなたのアカウントの API シークレット                 |
| `service_owner.api_secret.encrypted` | あなたのアカウントの API シークレットを暗号化したもの |

API シークレットを平文で書きたくなければ、`*.api_secret` キーのかわりに
`*.api_secret.encrypted` キーを使用してください。 暗号化したシークレットを
`*.encrypted` キーに設定することができます。
ただしこの場合、暗号化された値を復号化するため、`AuthletePropertiesConfiguration`
のコンストラクタに暗号化キーと初期ベクターを渡す必要があります。 詳細は
[JavaDoc][8] を参照してください。


#### AuthleteApi の実装

authlete-java-common ライブラリのバージョン 2.0 以降には、`HttpURLConnection` による
`AuthleteApi` インターフェースの実装が含まれています。バージョン 2.0 以前は `AuthleteApi`
インターフェースの実装を含む [authlete-java-jaxrs][7] ライブラリが別途必要でした。

`AuthleteApiFactory.create()` メソッドは既知の場所を探し、`AuthleteApi`
の実装をリフレクションを使ってロードします。 リフレクションを使用する理由は、特定の実装
(例えば authlete-java-jaxrs 内の JAX-RS ベースの実装) に依存しないようにするためです。

現時点で `AuthleteApi` インターフェースの既知の実装は次の二つです。

  1. `com.authlete.jaxrs.api.AuthleteApiImpl` (in [authlete-java-jaxrs][7])
  2. `com.authlete.common.api.AuthleteApiImpl` (in authlete-java-common)

`AuthleteApiFactory` は上記の順番で実装を検索しにいきます。


### AuthleteApi の設定

バージョン 2.9 で `AuthleteApi` に `getSettings()` メソッドが追加されました。
このメソッドが返す `Settings` インスタンスの設定を変更することにより、`AuthleteApi`
インターフェースの実装の動作を変更することができます。

*例*

```java
// AuthleteApi インターフェースの実装
AuthleteApi api = ...;

// AuthleteApi 実装の設定を保持するインスタンスを取得する。
Settings settings = api.getSettings();

// 接続タイムアウト値をミリ秒単位で設定する。
//
// 注意：
//   Java EE 8 の一部となっている JAX-RS API 2.1 より前は、接続タイムアウトを
//   設定する方法が標準化されていません。 そのため、AuthleteApi の実装として
//   authlete-java-jaxrs が使用されていて、かつ authlete-java-jaxrs の
//   setConnectionTimeout() の実装がサポートしていない JAX-RS Client 実装が
//   使われている場合、setConnectionTimeout() の設定は効きません。詳細は
//   authlete-java-jaxrs の README を参照してください。
//
settings.setConnectionTimeout(5000);

// リードタイムアウト値をミリ秒単位で設定する。
//
// 注意：
//   Java EE 8 の一部となっている JAX-RS API 2.1 より前は、リードタイムアウトを
//   設定する方法が標準化されていません。 そのため、AuthleteApi の実装として
//   authlete-java-jaxrs が使用されていて、かつ authlete-java-jaxrs の
//   setReadTimeout() の実装がサポートしていない JAX-RS Client 実装が使われて
//   いる場合、setReadTimeout() の設定は効きません。 詳細は authlete-java-jaxrs
//   の README を参照してください。
//
settings.setReadTimeout(5000);
```


#### AuthleteApi メソッドのカテゴリー

`AuthleteApi` インターフェースのメソッド群は幾つかのカテゴリーに分けることができます。

  1. 認可エンドポイント実装のためのメソッド群

    - `authorization(AuthorizationRequest request)`
    - `authorizationFail(AuthorizationFailRequest request)`
    - `authorizationIssue(AuthorizationIssueRequest request)`

  2. トークンエンドポイント実装のためのメソッド群

    - `token(TokenRequest request)`
    - `tokenFail(TokenFailRequest request)`
    - `tokenIssue(TokenIssueRequest request)`

  3. サービス管理のためのメソッド群

    - `createService(Service service)`
    - `deleteService(long serviceApiKey)`
    - `getService(long serviceApiKey)`
    - `getServiceList()`
    - `getServiceList(int start, int end)`
    - `updateService(Service service)`

  4. クライアントアプリケーション管理のためのメソッド群

    - `createClient(Client client)`
    - `deleteClient(long clientId)`
    - `getClient(long clientId)`
    - `getClientList()`
    - `getClientList(int start, int end)`
    - `updateClient(Client client)`
    - `refreshClientSecret(long clientId)`
    - `refreshClientSecret(String clientIdentifier)`
    - `updateClientSecret(long clientId, String clientSecret)`
    - `updateClientSecret(String clientIdentifier, String clientSecret)`

  5. アクセストークンの情報取得のためのメソッド群

    - `introspection(IntrospectionRequest request)`
    - `standardIntrospection(StandardIntrospectionRequest request)`

  6. アクセストークン取り消しエンドポイント実装のためのメソッド群

    - `revocation(RevocationRequest request)`

  7. ユーザー情報エンドポイント実装のためのメソッド群

    - `userinfo(UserInfoRequest request)`
    - `userinfoIssue(UserInfoIssueRequest request)`

  8. JWK セットエンドポイント実装のためのメソッド群

    - `getServiceJwks()`
    - `getServiceJwks(boolean pretty, boolean includePrivateKeys)`

  9. OpenID Connect Discovery のためのメソッド群

    - `getServiceConfiguration()`
    - `getServiceConfiguration(boolean pretty)`

  10. トークン操作のためのメソッド群

    - `tokenCreate(TokenCreateRequest request)`
    - `tokenUpdate(TokenUpdateRequest request)`

  11. クライアント毎の要求可能スコープ群に関するメソッド群 (非推奨; Client API で代替可能)

    - `getRequestableScopes(long clientId)`
    - `setRequestableScopes(long clientId, String[] scopes)`
    - `deleteRequestableScopes(long clientId)`

  12. 付与されたスコープの記録に関するメソッド群

    - `getGrantedScopes(long clientId, String subject)`
    - `deleteGrantedScopes(long clientId, String subject)`

  13. ユーザー・クライアント単位の認可管理に関するメソッド群

    - `deleteClientAuthorization(long clientId, String subject)`
    - `getClientAuthorizationList(ClientAuthorizationGetListRequest request)`
    - `updateClientAuthorization(long clientId, ClientAuthorizationUpdateRequest request)`

  14. JOSE に関するメソッド群

    - `verifyJose(JoseVerifyRequest)`

*例*

次のコードは既存のサービスのリストを取得する例です。
各サービスは一つの認可サーバーに対応します。

```java
// AuthleteApi インターフェースの実装を取得する。
AuthleteApi api = AuthleteApiFactory.getDefaultApi();

// サービスのリストを取得する。
ServiceListResponse response = api.getServiceList();
```


メモ
----

`AuthleteApi` インターフェースのメソッドだけを用いて認可サーバーを書くこともできますが、
[authlete-java-jaxrs][7] ライブラリのユーティリティークラス群を用いれば、作業はずっと楽になります。
ユーティリティークラス群を使って書かれた認可サーバー実装の例については、[java-oauth-server][6]
を参照してください。


その他の情報
------------

- [Authlete][1] - Authlete ホームページ
- [JavaDoc][8] - このライブラリの JavaDoc
- [authlete-java-jaxrs][7] - JAX-RS (Java) 用 Authlete ライブラリ
- [java-oauth-server][6] - 認可サーバー実装
- [java-resource-server][9] - リソースサーバー実装
- [OAuth 2.0 / OIDC 実装の新アーキテクチャー][10] - Authlete アーキテクチャー解説


コンタクト
----------

| 目的 | メールアドレス       |
|:-----|:---------------------|
| 一般 | info@authlete.com    |
| 営業 | sales@authlete.com   |
| 広報 | pr@authlete.com      |
| 技術 | support@authlete.com |


[1]: https://www.authlete.com/
[2]: https://www.authlete.com/documents/apis
[3]: http://tools.ietf.org/html/rfc6749
[4]: http://openid.net/connect/
[5]: https://www.authlete.com/documents/overview
[6]: https://github.com/authlete/java-oauth-server
[7]: https://github.com/authlete/authlete-java-jaxrs
[8]: http://authlete.github.io/authlete-java-common/
[9]: https://github.com/authlete/java-resource-server
[10]: https://qiita.com/TakahikoKawasaki/items/b2a4fc39e0c1a1949aab
