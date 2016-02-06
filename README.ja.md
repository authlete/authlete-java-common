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
    <version>1.29</version>
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

実は、authlete-java-common ライブラリには `AuthleteApi` インターフェースの実装は含まれて_いません_。
ですので、`AuthleteApi` インターフェースの実装を含む別のライブラリが必要となります。
この文章を書いている時点では、[authlete-java-jaxrs][7] だけが該当するライブラリです。

`AuthleteApiFactory.create()` メソッドは既知の場所を探し、`AuhleteApi`
の実装をリフレクションを使ってロードします。 リフレクションを使用する理由は、特定の実装
(例えば authlete-java-jaxrs 内の JAX-RS ベースの実装) に依存しないようにするためです。
そしてまた、この文章を書いている時点では、実装クラスの既知の場所として内部リストに入っているのは
`com.authlete.jaxrs.api.AuthleteApiImpl` のみです。


#### AuthleteApi メソッドのカテゴリー

`AuthleteApi` インターフェースのメソッド群は幾つかのカテゴリーに分けることができます。

  1. 認可エンドポイント実装のためのメソッド群

    - `authorization()`
    - `authorizationFail()`
    - `authorizationIssue()`

  2. トークンエンドポイント実装のためのメソッド群

    - `token()`
    - `tokenFail()`
    - `tokenIssue()`

  3. サービス管理のためのメソッド群

    - `createService()`
    - `deleteService()`
    - `getService()`
    - `getServiceList()`
    - `updateService()`

  4. クライアントアプリケーション管理のためのメソッド群

    - `createClient()`
    - `deleteClient()`
    - `getClient()`
    - `getClientList()`
    - `updateClient()`

  5. アクセストークンの情報取得のためのメソッド群

    - `introspection()`

  6. アクセストークン取り消しエンドポイント実装のためのメソッド群

    - `revocation()`

  7. ユーザー情報エンドポイント実装のためのメソッド群

    - `userinfo()`
    - `userinfoIssue()`

  8. JWK セットエンドポイント実装のためのメソッド群

    - `getServiceJwks()`

  9. OpenID Connect Discovery のためのメソッド群

    - `getServiceConfiguration()`


例
--

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


サポート
--------

[Authlete, Inc.][1]<br/>
support@authlete.com


[1]: https://www.authlete.com/
[2]: https://www.authlete.com/documents/apis
[3]: http://tools.ietf.org/html/rfc6749
[4]: http://openid.net/connect/
[5]: https://www.authlete.com/documents/overview
[6]: https://github.com/authlete/java-oauth-server
[7]: https://github.com/authlete/authlete-java-jaxrs
[8]: http://authlete.github.io/authlete-java-common/
