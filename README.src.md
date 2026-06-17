<!--@nrg.languages=en,ja-->
<!--@nrg.defaultLanguage=en-->

<!--@ref5=https://www.authlete.com/developers/overview/-->
<!--@ref5.ja=https://www.authlete.com/ja/developers/overview/-->
<!--@ref10=https://medium.com/@darutk/new-architecture-of-oauth-2-0-and-openid-connect-implementation-18f408f9338d-->
<!--@ref10.ja=https://qiita.com/TakahikoKawasaki/items/b2a4fc39e0c1a1949aab-->

${en:'Authlete Common Library for Java', ja:'Java 用 Authlete 共通ライブラリ'}
================================

${en:'Overview', ja:'概要'}
--------

This is a wrapper library for [Authlete Web APIs][2].<!--en-->
このライブラリは [Authlete Web API][2] のラッパーです。<!--ja-->

[Authlete][1] is a cloud service that provides an implementation of<!--en-->
[OAuth 2.0][3] & [OpenID Connect][4] ([overview][5]). By using the Web APIs<!--en-->
provided by Authlete, you can build a _DB-less_ authorization server.<!--en-->
"DB-less" here means that you don't have to prepare a database server that<!--en-->
stores authorization data (e.g. access tokens), settings of authorization<!--en-->
servers and settings of client applications. These data are stored in the<!--en-->
Authlete server on cloud.<!--en-->
[Authlete][1] は [OAuth 2.0][3] と [OpenID Connect][4] の実装を提供するクラウドサービスです<!--ja-->
([overview][5])。 Authlete が提供する Web API を使い、DB-less (データベース無し)<!--ja-->
の認可サーバーを構築することができます。「DB-less」とは、認可データ (アクセストークン等)、<!--ja-->
認可サーバーの設定、クライアントアプリケーションの設定を保存するデータベースを持つ必要が無い、という意味です。<!--ja-->
これらのデータはクラウド上にある Authlete サーバーに保存されます。<!--ja-->

[java-oauth-server][6] is the reference implementation of an authorization<!--en-->
server written using this library and [authlete-java-jaxrs][7] library.<!--en-->
It is a good starting point for your own authorization server implementation.<!--en-->
[java-oauth-server][6] は、このライブラリおよび [authlete-java-jaxrs][7]<!--ja-->
ライブラリを用いて書かれた、認可サーバーのリファレンス実装です。<!--ja-->
あなたの認可サーバー実装の開始点として活用してください。<!--ja-->


${en:'License', ja:'ライセンス'}
-------

  Apache License, Version 2.0

  JSON files under `src/test/resources/ekyc-ida` have been copied from<!--en-->
  https://bitbucket.org/openid/ekyc-ida/src/master/examples/response/ .<!--en-->
  Regarding their license, ask the eKYC-IDA WG of OpenID Foundation.<!--en-->
  `src/test/resources/ekyc-ida` 以下の JSON ファイル群は<!--ja-->
  https://bitbucket.org/openid/ekyc-ida/src/master/examples/response/<!--ja-->
  からコピーしたものです。それらのライセンスについては、OpenID Foundation の<!--ja-->
  eKYC-IDA ワーキンググループにお尋ねください。<!--ja-->


Maven
-----

```xml
<dependency>
    <groupId>com.authlete</groupId>
    <artifactId>authlete-java-common</artifactId>
    <version>\${authlete-java-common.version}</version>
</dependency>
```

Please refer to the [CHANGES.md](CHANGES.md) file to know the latest version<!--en-->
to write in place of `\${authlete-java-common.version}`.<!--en-->
`\${authlete-java-common.version}` の箇所に書き込む最新バージョンについては<!--ja-->
[CHANGES.ja.md](CHANGES.ja.md) ファイルを参照してください。<!--ja-->


${en:'Source Code', ja:'ソースコード'}
-----------

  <code>https://github.com/authlete/authlete-java-common</code>


JavaDoc
-------

  <code>https://authlete.github.io/authlete-java-common/</code>


${en:'Description', ja:'説明'}
-----------

#### ${en:'How To Get AuthleteApi', ja:'AuthleteApi の取得方法'}

All the methods to communicate with [Authlete Web APIs][2] are gathered in<!--en-->
`AuthleteApi` interface. To get an implementation of the interface, you need to<!--en-->
call `create()` method of `AuthleteApiFactory` class. There are two variants<!--en-->
of the method as shown below.<!--en-->
[Authlete Web API][2] とやりとりするメソッドは全て `AuthleteApi` インターフェースに集められています。<!--ja-->
`AuthleteApiFactory` クラスの `create()` メソッドを呼ぶと、`AuthleteApi` の実装を取得できます。<!--ja-->
このメソッドには二つのバリアントがあります。<!--ja-->

```java
public static AuthleteApi
    create(AuthleteConfiguration configuration);

public static AuthleteApi
    create(AuthleteConfiguration configuration, String className);
```

As you can see, both methods take `AuthleteConfiguration` as their first argument.<!--en-->
`AuthleteConfiguration` is an interface that holds configuration values to access<!--en-->
Authlete Web APIs such as the URL of Authlete server and API credentials of a<!--en-->
service. To be concrete, the interface has the following methods.<!--en-->
どちらのメソッドも `AuthleteConfiguration` を第一引数として受け取ります。<!--ja-->
`AuthleteConfiguration` は、Authlete サーバーの URL やサービスの API クレデンシャルズなどの、Authlete<!--ja-->
Web API にアクセスするのに必要な設定値を保持するインターフェースです。<!--ja-->
具体的には、このインターフェースには次のようなメソッド群があります。<!--ja-->

| Method                       | Authlete Version  | Description                |<!--en-->
|:-----------------------------|:------------------|:---------------------------|<!--en-->
| `getBaseUrl()`               | Common            | URL of Authlete server     |<!--en-->
| `getServiceApiKey()`         | Common            | API key of a service       |<!--en-->
| `getServiceApiSecret()`      | Up to version 2.x | API secret of a service    |<!--en-->
| `getServiceOwnerApiKey()`    | Up to version 2.x | API key of your account    |<!--en-->
| `getServiceOwnerApiSecret()` | Up to version 2.x | API secret of your account |<!--en-->
| `getApiVersion()`            | Since version 3.0 | API version                |<!--en-->
| `getServiceAccessToken()`    | Since version 3.0 | API access token           |<!--en-->
| メソッド                     | Authlete バージョン | 説明                                  |<!--ja-->
|:-----------------------------|:--------------------|:--------------------------------------|<!--ja-->
| `getBaseUrl()`               | 共通                | Authlete サーバーの URL               |<!--ja-->
| `getServiceApiKey()`         | 共通                | サービスの API キー                   |<!--ja-->
| `getServiceApiSecret()`      | バージョン 2.x まで | サービスの API シークレット           |<!--ja-->
| `getServiceOwnerApiKey()`    | バージョン 2.x まで | あなたのアカウントの API キー         |<!--ja-->
| `getServiceOwnerApiSecret()` | バージョン 2.x まで | あなたのアカウントの API シークレット |<!--ja-->
| `getApiVersion()`            | バージョン 3.0 以降 | Authlete API のバージョン             |<!--ja-->
| `getServiceAccessToken()`    | バージョン 3.0 以降 | あなたの API アクセストークン         |<!--ja-->

authlete-java-common library includes three implementations of<!--en-->
`AuthleteConfiguration` interface as listed below.<!--en-->
authlete-java-common ライブラリには、`AuthleteConfiguration` インターフェースの実装が三つ含まれています。<!--ja-->

| Class                             | Description                             |<!--en-->
|:----------------------------------|:----------------------------------------|<!--en-->
| `AuthleteEnvConfiguration`        | Configuration via environment variables |<!--en-->
| `AuthletePropertiesConfiguration` | Configuration via a properties file     |<!--en-->
| `AuthleteSimpleConfiguration`     | Configuration via POJO                  |<!--en-->
| クラス                            | 説明                           |<!--ja-->
|:----------------------------------|:-------------------------------|<!--ja-->
| `AuthleteEnvConfiguration`        | 環境変数による設定             |<!--ja-->
| `AuthletePropertiesConfiguration` | プロパティーファイルによる設定 |<!--ja-->
| `AuthleteSimpleConfiguration`     | POJO による設定                |<!--ja-->

You can use one of these or create your own implementation of the interface. In<!--en-->
either case, you can get an implementation of `AuthleteApi` interface by passing<!--en-->
an `AuthleteConfiguration` instance to `create()` method of `AuthleteApiFactory`<!--en-->
class.<!--en-->
これらの実装を使うこともできますし、インターフェースの実装を自分で作成することもできます。<!--ja-->
いずれにしても、`AuthleteConfiguration` インスタンスを `AuthleteApiFactory` クラスの<!--ja-->
`create()` メソッドに渡すことで、`AuthleteApi` の実装を取得することができます。<!--ja-->

In summary, the flow to get an implementation of `AuthleteApi` becomes like below.<!--en-->
まとめると、`AuthleteApi` の実装を取得する流れは次のようになります。<!--ja-->

```java
// Prepare an instance of AuthleteConfiguration interface.<!--en-->
// AuthleteConfiguration インターフェースのインスタンスを用意する。<!--ja-->
AuthleteConfiguration configuration = ...;

// Get an instance of AuthleteApi interface.<!--en-->
// AuthleteApi インターフェースのインスタンスを取得する。<!--ja-->
AuthleteApi api = AuthleteApiFactory.create(configuration);
```

If you want to do it in an easier way, use `AuthleteApiFactory.getDefaultApi()`<!--en-->
method. This method searches the file system and the classpath for a properties<!--en-->
file named `authlete.properties` and loads the content of the file using<!--en-->
`AuthletePropertiesConfiguration` class.<!--en-->
より簡単な方法がよければ、`AuthleteApiFactory.getDefaultApi()` メソッドを使用してください。<!--ja-->
このメソッドは、ファイルシステムとクラスパスから、`authlete.properties`<!--ja-->
という名前のプロパティーファイルを探し、`AuthletePropertiesConfiguration`<!--ja-->
クラスを使ってそのファイルの内容を読み込みます。<!--ja-->

```java
// Search the file system and the classpath for "authlete.properties".<!--en-->
//ファイルシステムとクラスパスから "authlete.properties" を探す。<!--ja-->
AuthleteApi api = AuthleteApiFactory.getDefaultApi();
```

`AuthleteApiFactory.getDefaultApi()` method caches the search result, so you can<!--en-->
call the method as many times as you like without worrying about the overhead of<!--en-->
file loading.<!--en-->
`AuthleteApiFactory.getDefaultApi()` メソッドは結果をキャッシュするので、<!--ja-->
ファイルを読み込む処理のオーバーヘッドを気にせずに何回でも呼ぶことができます。<!--ja-->


#### AuthletePropertiesConfiguration

Among the three implementations of `AuthleteConfiguration` interface, this section<!--en-->
explains `AuthletePropertiesConfiguration` class.<!--en-->
`AuthleteConfiguration` インターフェースの三つの実装のうち、ここでは<!--ja-->
`AuthletePropertiesConfiguration` クラスについて説明します。<!--ja-->

`AuthletePropertiesConfiguration` class provides a mechanism to use a properties<!--en-->
file to set configuration values to access Authlete Web APIs. The class searches<!--en-->
the file system and the classpath for a specified file.<!--en-->
`AuthletePropertiesConfiguration` クラスは、Authlete Web API<!--ja-->
へのアクセスに必要な設定をプロパティーファイルでおこなう仕組みを提供します。<!--ja-->
このクラスは、ファイルシステムとクラスパスから指定されたファイルを探します。<!--ja-->

Valid property keys in a properties file and their meanings are as follows.<!--en-->
プロパティーファイル内で有効なプロパティーキーとその意味は次のとおりです。<!--ja-->

| Property Key                         | Description                          |<!--en-->
|:-------------------------------------|:-------------------------------------|<!--en-->
| `base_url`                           | URL of Authlete server               |<!--en-->
| `service.api_key`                    | API key of a service                 |<!--en-->
| `service.api_secret`                 | API secret of a service              |<!--en-->
| `service.api_secret.encrypted`       | Encrypted API secret of a service    |<!--en-->
| `service_owner.api_key`              | API key of your account              |<!--en-->
| `service_owner.api_secret`           | API secret of your account           |<!--en-->
| `service_owner.api_secret.encrypted` | Encrypted API secret of your account |<!--en-->
| `api_version`                        | API version. "V3" for Authlete 3.0   |<!--en-->
| `service.access_token`               | API access token                     |<!--en-->
| プロパティーキー                     | 説明                                                  |<!--ja-->
|:-------------------------------------|:------------------------------------------------------|<!--ja-->
| `base_url`                           | Authlete サーバーの URL                               |<!--ja-->
| `service.api_key`                    | サービスの API キー                                   |<!--ja-->
| `service.api_secret`                 | サービスの API シークレット                           |<!--ja-->
| `service.api_secret.encrypted`       | サービスの API シークレットを暗号化したもの           |<!--ja-->
| `service_owner.api_key`              | あなたのアカウントの API キー                         |<!--ja-->
| `service_owner.api_secret`           | あなたのアカウントの API シークレット                 |<!--ja-->
| `service_owner.api_secret.encrypted` | あなたのアカウントの API シークレットを暗号化したもの |<!--ja-->
| `api_version`                        | Authlete API バージョン。3.0 用には "V3" を指定する。 |<!--ja-->
| `service.access_token`               | あなたの API アクセストークン                         |<!--ja-->

If you don't want to write API secrets in plain text, use<!--en-->
`*.api_secret.encrypted` keys instead of `*.api_secret` keys. You can set<!--en-->
encrypted secrets to the `*.encrypted` keys. But in this case, you have to pass<!--en-->
the encryption key and the initial vector to a constructor of<!--en-->
`AuthletePropertiesConfiguration` so that the loader can decode the encrypted<!--en-->
values. See the [JavaDoc][8] for details.<!--en-->
API シークレットを平文で書きたくなければ、`*.api_secret` キーのかわりに<!--ja-->
`*.api_secret.encrypted` キーを使用してください。 暗号化したシークレットを<!--ja-->
`*.encrypted` キーに設定することができます。<!--ja-->
ただしこの場合、暗号化された値を復号化するため、`AuthletePropertiesConfiguration`<!--ja-->
のコンストラクタに暗号化キーと初期ベクターを渡す必要があります。 詳細は<!--ja-->
[JavaDoc][8] を参照してください。<!--ja-->


#### ${en:'AuthleteApi Implementation', ja:'AuthleteApi の実装'}

Since version 2.0, authlete-java-common library includes an implementation of<!--en-->
`AuthleteApi` interface using `HttpURLConnection`. Before version 2.0,<!--en-->
[authlete-java-jaxrs][7] which contains an implementation of `AuthleteApi` was<!--en-->
additionally needed.<!--en-->
authlete-java-common ライブラリのバージョン 2.0 以降には、`HttpURLConnection` による<!--ja-->
`AuthleteApi` インターフェースの実装が含まれています。バージョン 2.0 以前は `AuthleteApi`<!--ja-->
インターフェースの実装を含む [authlete-java-jaxrs][7] ライブラリが別途必要でした。<!--ja-->

`AuthleteApiFactory.create()` method searches known locations for an<!--en-->
`AuthleteApi` implementation and loads one using reflection. The reason to use<!--en-->
reflection is to avoid depending on specific implementations (e.g. JAX-RS based<!--en-->
implementation in authlete-java-jaxrs).<!--en-->
`AuthleteApiFactory.create()` メソッドは既知の場所を探し、`AuthleteApi`<!--ja-->
の実装をリフレクションを使ってロードします。 リフレクションを使用する理由は、特定の実装<!--ja-->
(例えば authlete-java-jaxrs 内の JAX-RS ベースの実装) に依存しないようにするためです。<!--ja-->

As of this writing, known implementations of `AuthleteApi` interface are as<!--en-->
follows.<!--en-->
現時点で `AuthleteApi` インターフェースの既知の実装は次の二つです。<!--ja-->

  1. `com.authlete.jaxrs.api.AuthleteApiImpl` (in [authlete-java-jaxrs][7])
  2. `com.authlete.common.api.AuthleteApiImpl` (in authlete-java-common)

`AuthleteApiFactory` checks existence of the above classes in this order.<!--en-->
`AuthleteApiFactory` は上記の順番で実装を検索しにいきます。<!--ja-->


#### AuthleteApi Settings<!--en-->
### AuthleteApi の設定<!--ja-->

`getSettings()` method of `AuthleteApi` interface has been available since<!--en-->
the version 2.9. By configuring the instance returned by the method, you can<!--en-->
change behaviours of the implementation of `AuthleteApi` interface.<!--en-->
バージョン 2.9 で `AuthleteApi` に `getSettings()` メソッドが追加されました。<!--ja-->
このメソッドが返す `Settings` インスタンスの設定を変更することにより、`AuthleteApi`<!--ja-->
インターフェースの実装の動作を変更することができます。<!--ja-->

*${en:'Examples', ja:'例'}*

```java
// An implementation of AuthleteApi interface.<!--en-->
// AuthleteApi インターフェースの実装<!--ja-->
AuthleteApi api = ...;

// Get the instance which holds settings of the AuthleteApi implementation.<!--en-->
// AuthleteApi 実装の設定を保持するインスタンスを取得する。<!--ja-->
Settings settings = api.getSettings();

// Set a connection timeout in milliseconds.<!--en-->
//<!--en-->
//   Note:<!--en-->
//     There is no standard way to set a connection timeout value<!--en-->
//     before JAX-RS API 2.1 (which is a part of Java EE 8).<!--en-->
//     Therefore, if authlete-java-jaxrs is used as AuthleteApi<!--en-->
//     implementation and if the JAX-RS Client implementation is<!--en-->
//     not supported by the implementation of setConnectionTimeout()<!--en-->
//     of authlete-java-jaxrs, the value given to setConnectionTimeout()<!--en-->
//     won't have any effect. See README in authlete-java-jaxrs<!--en-->
//     for details.<!--en-->
//<!--en-->
// 接続タイムアウト値をミリ秒単位で設定する。<!--ja-->
//<!--ja-->
// 注意：<!--ja-->
//   Java EE 8 の一部となっている JAX-RS API 2.1 より前は、接続タイムアウトを<!--ja-->
//   設定する方法が標準化されていません。 そのため、AuthleteApi の実装として<!--ja-->
//   authlete-java-jaxrs が使用されていて、かつ authlete-java-jaxrs の<!--ja-->
//   setConnectionTimeout() の実装がサポートしていない JAX-RS Client 実装が<!--ja-->
//   使われている場合、setConnectionTimeout() の設定は効きません。詳細は<!--ja-->
//   authlete-java-jaxrs の README を参照してください。<!--ja-->
//<!--ja-->
settings.setConnectionTimeout(5000);

// Set a read timeout in milliseconds.<!--en-->
//<!--en-->
//   Note:<!--en-->
//     There is no standard way to set a read timeout value before<!--en-->
//     JAX-RS API 2.1 (which is a part of Java EE 8). Therefore,<!--en-->
//     if authlete-java-jaxrs is used as AuthleteApi implementation<!--en-->
//     and if the JAX-RS Client implementation is not supported by<!--en-->
//     the implementation of setReadTimeout() of authlete-java-jaxrs,<!--en-->
//     the value given to setReadTimeout() won't have any effect.<!--en-->
//     See README in authlete-java-jaxrs for details.<!--en-->
//<!--en-->
// リードタイムアウト値をミリ秒単位で設定する。<!--ja-->
//<!--ja-->
// 注意：<!--ja-->
//   Java EE 8 の一部となっている JAX-RS API 2.1 より前は、リードタイムアウトを<!--ja-->
//   設定する方法が標準化されていません。 そのため、AuthleteApi の実装として<!--ja-->
//   authlete-java-jaxrs が使用されていて、かつ authlete-java-jaxrs の<!--ja-->
//   setReadTimeout() の実装がサポートしていない JAX-RS Client 実装が使われて<!--ja-->
//   いる場合、setReadTimeout() の設定は効きません。 詳細は authlete-java-jaxrs<!--ja-->
//   の README を参照してください。<!--ja-->
//<!--ja-->
settings.setReadTimeout(5000);
```


#### ${en:'AuthleteApi Method Categories', ja:'AuthleteApi メソッドのカテゴリー'}

Methods in `AuthleteApi` interface can be divided into some categories.<!--en-->
`AuthleteApi` インターフェースのメソッド群は幾つかのカテゴリーに分けることができます。<!--ja-->

  1. Methods for Authorization Endpoint Implementation<!--en-->
  1. 認可エンドポイント実装のためのメソッド群<!--ja-->

  - `authorization(AuthorizationRequest request)`
  - `authorizationFail(AuthorizationFailRequest request)`
  - `authorizationIssue(AuthorizationIssueRequest request)`

  2. Methods for Token Endpoint Implementation<!--en-->
  2. トークンエンドポイント実装のためのメソッド群<!--ja-->

  - `token(TokenRequest request)`
  - `tokenFail(TokenFailRequest request)`
  - `tokenIssue(TokenIssueRequest request)`
  - `idTokenReissue(IDTokenReissueRequest request)`

  3. Methods for Service Management<!--en-->
  3. サービス管理のためのメソッド群<!--ja-->

  - `createService(Service service)`
  - `deleteService(long serviceApiKey)`
  - `getService(long serviceApiKey)`
  - `getServiceList()`
  - `getServiceList(int start, int end)`
  - `updateService(Service service)`

  4. Methods for Client Application Management<!--en-->
  4. クライアントアプリケーション管理のためのメソッド群<!--ja-->

  - `createClient(Client client)`
  - `deleteClient(long clientId)`
  - `deleteClient(String clientId)`
  - `getClient(long clientId)`
  - `getClient(String clientId)`
  - `getClientList()`
  - `getClientList(int start, int end)`
  - `updateClient(Client client)`
  - `refreshClientSecret(long clientId)`
  - `refreshClientSecret(String clientIdentifier)`
  - `updateClientSecret(long clientId, String clientSecret)`
  - `updateClientSecret(String clientIdentifier, String clientSecret)`

  5. Methods for Access Token Introspection<!--en-->
  5. アクセストークンの情報取得のためのメソッド群<!--ja-->

  - `introspection(IntrospectionRequest request)`
  - `standardIntrospection(StandardIntrospectionRequest request)`
  - `getTokenList()`
  - `getTokenList(String clientIdentifier, String subject)`
  - `getTokenList(int start, int end)`
  - `getTokenList(String clientIdentifier, String subject, int start, int end)`
  - `getTokenList(TokenStatus)`
  - `getTokenList(int start, int end, TokenStatus tokenStatus)`
  - `getTokenList(String clientIdentifier, String subject, TokenStatus tokenStatus)`
  - `getTokenList(String clientIdentifier, String subject, int start, int end, TokenStatus tokenStatus)`

  6. Methods for Revocation Endpoint Implementation<!--en-->
  6. アクセストークン取り消しエンドポイント実装のためのメソッド群<!--ja-->

  - `revocation(RevocationRequest request)`

  7. Methods for User Info Endpoint Implementation<!--en-->
  7. ユーザー情報エンドポイント実装のためのメソッド群<!--ja-->

  - `userinfo(UserInfoRequest request)`
  - `userinfoIssue(UserInfoIssueRequest request)`

  8. Methods for JWK Set Endpoint Implementation<!--en-->
  8. JWK セットエンドポイント実装のためのメソッド群<!--ja-->

  - `getServiceJwks()`
  - `getServiceJwks(boolean pretty, boolean includePrivateKeys)`

  9. Methods for OpenID Connect Discovery<!--en-->
  9. OpenID Connect Discovery のためのメソッド群<!--ja-->

  - `getServiceConfiguration()`
  - `getServiceConfiguration(boolean pretty)`

  10. Methods for Token Operations<!--en-->
  10. トークン操作のためのメソッド群<!--ja-->

  - `tokenCreate(TokenCreateRequest request)`
  - `tokenDelete(String token)`
  - `tokenRevoke(TokenRevokeRequest request)`
  - `tokenUpdate(TokenUpdateRequest request)`
  - `tokenCreateBatch(TokenCreateRequest[] request)`
  - `getTokenCreateBatchStatus(String requestId)`

  11. Methods for Requestable Scopes per Client (deprecated; Client APIs suffice)<!--en-->
  11. クライアント毎の要求可能スコープ群に関するメソッド群 (非推奨; Client API で代替可能)<!--ja-->

  - `getRequestableScopes(long clientId)`
  - `setRequestableScopes(long clientId, String[] scopes)`
  - `deleteRequestableScopes(long clientId)`

  12. Methods for Records of Granted Scopes<!--en-->
  12. 付与されたスコープの記録に関するメソッド群<!--ja-->

  - `getGrantedScopes(long clientId, String subject)`
  - `deleteGrantedScopes(long clientId, String subject)`

  13. Methods for Authorization Management on a User-Client Combination Basis<!--en-->
  13. ユーザー・クライアント単位の認可管理に関するメソッド群<!--ja-->

  - `deleteClientAuthorization(long clientId, String subject)`
  - `getClientAuthorizationList(ClientAuthorizationGetListRequest request)`
  - `updateClientAuthorization(long clientId, ClientAuthorizationUpdateRequest request)`

  14. Methods for JOSE<!--en-->
  14. JOSE に関するメソッド群<!--ja-->

  - `verifyJose(JoseVerifyRequest)`

  15. Methods for CIBA (Client Initiated Backchannel Authentication)<!--en-->
  15. CIBA (Client Initiated Backchannel Authentication) に関するメソッド群<!--ja-->

  - `backchannelAuthentication(BackchannelAuthenticationRequest)`
  - `backchannelAuthenticationIssue(BackchannelAuthenticationIssueRequest)`
  - `backchannelAuthenticationFail(BackchannelAuthenticationFailRequest)`
  - `backchannelAuthenticationComplete(BackchannelAuthenticationCompleteRequest)`

  16. Methods for OpenID Connect Dynamic Client Registration<!--en-->
  16. OpenID Connect Dynamic Client Registration に関するメソッド群<!--ja-->

  - `dynamicClientRegister(ClientRegistrationRequest)`
  - `dynamicClientGet(ClientRegistrationRequest)`
  - `dynamicClientUpdate(ClientRegistrationRequest)`
  - `dynamicClientDelete(ClientRegistrationRequest)`

  17. Methods for Device Flow<!--en-->
  17. Device Flow に関するメソッド群<!--ja-->

  - `deviceAuthorization(DeviceAuthorizationRequest)`
  - `deviceComplete(DeviceCompleteRequest)`
  - `deviceVerification(DeviceVerificationRequest)`

  18. Methods for Pushed Authorization Requests<!--en-->
  18. Pushed Authorization Requests に関するメソッド群<!--ja-->

  - `pushAuthorizationRequest(PushedAuthReqRequest)`

  19. Methods for Grant Management for OAuth 2.0<!--en-->
  19. Grant Management for OAuth 2.0 に関するメソッド群<!--ja-->

  - `gm(GMRequest)`

  20. Methods for OpenID Connect Federation 1.0<!--en-->
  20. OpenID Connect Federation 1.0 に関するメソッド群<!--ja-->

  - `federationConfiguration(FederationConfigurationRequest)`
  - `federationRegistration(FederationRegistrationRequest)`

  21. Methods for Verifiable Credentials<!--en-->
  21. Verifiable Credentials に関するメソッド群<!--ja-->

  - `credentialIssuerMetadata(CredentialIssuerMetadataRequest)`
  - `credentialIssuerJwks(CredentialIssuerJwksRequest)`
  - `credentialJwtIssuerMetadata(CredentialJwtIssuerMetadataRequest)`
  - `credentialOfferCreate(CredentialOfferCreateRequest)`
  - `credentialOfferInfo(CredentialOfferInfoRequest)`
  - `credentialSingleParse(CredentialSingleParseRequest)`
  - `credentialSingleIssue(CredentialSingleIssueRequest)`
  - `credentialBatchParse(CredentialBatchParseRequest)`
  - `credentialBatchIssue(CredentialBatchIssueRequest)`
  - `credentialDeferredParse(CredentialDeferredParseRequest)`
  - `credentialDeferredIssue(CredentialDeferredIssueRequest)`
  - `credentialNonce(CredentialNonceRequest)`

  22. Methods for OpenID Connect Native SSO for Mobile Apps 1.0<!--en-->
  22. OpenID Connect Native SSO for Mobile Apps 1.0 に関するメソッド群<!--ja-->

  - `nativeSso(NativeSsoRequest)`
  - `nativeSsoLogout(NativeSsoLogoutRequest)`

  23. Methods for OAuth 2.0 Attestation-Based Client Authentication<!--en-->
  23. OAuth 2.0 Attestation-Based Client Authentication に関するメソッド群<!--ja-->

  - `attestationChallenge(AttestationChallengeRequest)`

*${en:'Examples', ja:'例'}*

The following code snippet is an example to get the list of your existing<!--en-->
services. Each service corresponds to an authorization server.<!--en-->
次のコードは既存のサービスのリストを取得する例です。<!--ja-->
各サービスは一つの認可サーバーに対応します。<!--ja-->

```java
// Get an implementation of AuthleteApi interface.<!--en-->
// AuthleteApi インターフェースの実装を取得する。<!--ja-->
AuthleteApi api = AuthleteApiFactory.getDefaultApi();

// Get the list of services.<!--en-->
// サービスのリストを取得する。<!--ja-->
ServiceListResponse response = api.getServiceList();
```


${en:'Authlete Version', ja:'Authlete バージョン'}
----------------

Some APIs and features don't work (even if they are defined in the `AuthleteApi`<!--en-->
interface) if Authlete API server you use doesn't support them. For example,<!--en-->
CIBA works only in Authlete 2.1 onwards. Please contact us if you want to use<!--en-->
newer Authlete versions.<!--en-->
幾つかの API や機能は、使用されている Authlete API サーバーがサポートしていなければ（たとえ<!--ja-->
`AuthleteApi` インターフェースで定義されているとしても）使うことができません。例えば、CIBA は<!--ja-->
Authlete 2.1 以降でのみ機能します。新しい Authlete バージョンを使用されたい場合は、ご連絡ください。<!--ja-->

Features available in Authlete 2.0 and onwards:<!--en-->
Authlete 2.0 以降で利用できる機能：<!--ja-->

- Financial-grade API (FAPI)
- OAuth 2.0 Mutual TLS Client Authentication and Certificate Bound Access Tokens (MTLS)
- JWT-based Client Authentication (RFC 7523)
- Scope attributes
- UK Open Banking Security Profile

Features available in Authlete 2.1 and onwards:<!--en-->
Authlete 2.1 以降で利用できる機能：<!--ja-->

- Client Initiated Backchannel Authentication (CIBA)
- JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)
- Dynamic Client Registration (RFC 7591 & RFC 7592)
- OAuth 2.0 Device Authorization Grant (Device Flow)
- JWT-based Access Token

See [Spec Sheet](https://www.authlete.com/legal/spec_sheet/) for further details.<!--en-->
詳細情報は [スペックシート](https://www.authlete.com/ja/legal/spec_sheet/)<!--ja-->
を参照してください。<!--ja-->


${en:'Note', ja:'メモ'}
----

You can write an authorization server using the methods in `AuthleteApi`<!--en-->
interface only, but the task will become much easier if you use utility classes<!--en-->
in [authlete-java-jaxrs][7] library. See [java-oauth-server][6] for an example of<!--en-->
an authorization server implementation written using the utility classes.<!--en-->
`AuthleteApi` インターフェースのメソッドだけを用いて認可サーバーを書くこともできますが、<!--ja-->
[authlete-java-jaxrs][7] ライブラリのユーティリティークラス群を用いれば、作業はずっと楽になります。<!--ja-->
ユーティリティークラス群を使って書かれた認可サーバー実装の例については、[java-oauth-server][6]<!--ja-->
を参照してください。<!--ja-->


${en:'Automatic Annotation of Ambiguous Json Setter Methods', ja:'曖昧なセッターメソッド群に対する自動アノテーション'}
-----------------------------------------------------

To address the "multiple ambiguous setter methods" json deserialization issue. <!--en-->
An annotation processor has been added to the maven build step and will run <!--en-->
during the `process-classes` maven phase.<!--en-->
The `JsonSetterAnnotationProcessor` class is responsible for this process. <!--en-->
The process involves iterating over all classes in the DTO package. Then <!--en-->
finding any ambiguously defined setter methods (setter methods defined with <!--en-->
the same name) which do not have any `@JsonSetter` annotation defined. If <!--en-->
there are setters found that match these criteria, the correct setter method <!--en-->
to annotate will be determined by matching the first argument of the setter <!--en-->
method with the return type of the matching getter method.<!--en-->
The new annotation is then added to the identified setter method and written <!--en-->
to the existing class file.<!--en-->
JSON デシリアライズに複数のセッターメソッドが存在する問題を解決するために、maven の build ステップに<!--ja-->
アノテーションプロセッサー (`JsonSetterAnnotationProcessor` クラス) が追加されました。<!--ja-->
本プロセッサーは `process-classes` フェーズ時に実行され、DTO パッケージ内の全クラスを走査し、<!--ja-->
曖昧なセッターメソッド (`@JsonSetter` アノテーションが定義されていないセッターメソッド) を検出します。<!--ja-->
仮に当該クラス内にそのようなメソッドが複数存在する場合、最初の引数の型が「ゲッターメソッドの返り値の型」<!--ja-->
と合致するセッターメソッドに対してアノテーションが付与され、当該クラスに書き込まれます。<!--ja-->


Code Quality Tools<!--en-->
-----------------<!--en-->
<!--en-->
The project now includes several code quality tools to maintain high standards:<!--en-->
<!--en-->
1. **Checkstyle**<!--en-->
   - Added via maven-checkstyle-plugin<!--en-->
   - Custom rules defined in `checkstyle.xml`<!--en-->
   - Enforces coding standards and improves code quality<!--en-->
<!--en-->
2. **SpotBugs**<!--en-->
   - Configuration split into include and exclude files<!--en-->
   - `spotbugs-exclude.xml`: Defines patterns to exclude from analysis<!--en-->
   - `spotbugs-include.xml`: Contains rules for various bug categories<!--en-->
<!--en-->
3. **Dependencies**<!--en-->
   - Updated nimbus-jose-jwt to version 9.31<!--en-->
   - Added new dependencies for enhanced security and functionality<!--en-->
<!--en-->
These tools are configured to run during the build process and help maintain code quality across the project.<!--en-->
<!--en-->
**Quality Standards for Contributors:**<!--en-->
<!--en-->
- **Code Review**: All changes must pass automated quality checks before submission<!--en-->
- **Testing**: Maintain test coverage and ensure all tests pass<!--en-->
- **Documentation**: Keep code documentation current and comprehensive<!--en-->
- **Performance**: Consider impact on performance and optimize where necessary<!--en-->
- **Security**: Follow security best practices and validate all inputs<!--en-->
- **Compatibility**: Maintain backward compatibility unless breaking changes are necessary<!--en-->
- **Standards**: Follow established coding conventions and project guidelines<!--en-->
<!--en-->
<!--en-->
Contributing Guidelines<!--en-->
----------------------<!--en-->
<!--en-->
To maintain high code quality standards, please follow these guidelines when contributing to the project:<!--en-->
<!--en-->
1. **Code Style**<!--en-->
   - Follow the Checkstyle rules defined in `checkstyle.xml`<!--en-->
   - Use consistent indentation and formatting<!--en-->
   - Follow Java naming conventions<!--en-->
   - Keep methods and classes focused and well-documented<!--en-->
<!--en-->
2. **Code Quality Checks**<!--en-->
   - Run `mvn checkstyle:check` to verify code style compliance<!--en-->
   - Run `mvn spotbugs:check` to detect potential bugs<!--en-->
   - Ensure all tests pass before submitting changes<!--en-->
   - Address any warnings or errors from quality tools<!--en-->
<!--en-->
3. **Documentation**<!--en-->
   - Update relevant documentation when adding new features<!--en-->
   - Include JavaDoc comments for public methods and classes<!--en-->
   - Update README.md if introducing new functionality<!--en-->
   - Provide clear commit messages<!--en-->
<!--en-->
4. **Testing**<!--en-->
   - Write unit tests for new functionality<!--en-->
   - Ensure existing tests continue to pass<!--en-->
   - Test with different Authlete API versions when applicable<!--en-->
<!--en-->
5. **Security**<!--en-->
   - Follow security best practices<!--en-->
   - Validate all inputs<!--en-->
   - Use secure coding patterns<!--en-->
   - Review for potential vulnerabilities<!--en-->
<!--en-->
6. **Performance**<!--en-->
   - Consider performance implications of changes<!--en-->
   - Use efficient algorithms and data structures<!--en-->
   - Avoid unnecessary object creation<!--en-->
   - Profile critical code paths when needed<!--en-->
<!--en-->
7. **Compatibility**<!--en-->
   - Maintain backward compatibility when possible<!--en-->
   - Test with supported Java versions<!--en-->
   - Verify compatibility with Authlete API versions<!--en-->
   - Document any breaking changes<!--en-->
<!--en-->
8. **Review Process**<!--en-->
   - Self-review your changes before submission<!--en-->
   - Ensure code follows project conventions<!--en-->
   - Address feedback from code reviews promptly<!--en-->
   - Keep pull requests focused and manageable<!--en-->
<!--en-->
For questions about these guidelines or the development process, please reach out to the development team.<!--en-->


${en:'See Also', ja:'その他の情報'}
--------

- [Authlete][1] - Authlete Home Page<!--en-->
- [JavaDoc][8] - JavaDoc of this library<!--en-->
- [authlete-java-jaxrs][7] - Authlete Library for JAX-RS (Java)<!--en-->
- [java-oauth-server][6] - Authorization Server Implementation<!--en-->
- [java-resource-server][9] - Resource Server Implementation<!--en-->
- [New Architecture of OAuth 2.0 and OpenID Connect Implementation][10] - Explanation about Authlete Architecture<!--en-->
- [Authlete][1] - Authlete ホームページ<!--ja-->
- [JavaDoc][8] - このライブラリの JavaDoc<!--ja-->
- [authlete-java-jaxrs][7] - JAX-RS (Java) 用 Authlete ライブラリ<!--ja-->
- [java-oauth-server][6] - 認可サーバー実装<!--ja-->
- [java-resource-server][9] - リソースサーバー実装<!--ja-->
- [OAuth 2.0 / OIDC 実装の新アーキテクチャー][10] - Authlete アーキテクチャー解説<!--ja-->


${en:'Contact', ja:'コンタクト'}
-------

| ${en:'Purpose  ', ja:'目的'} | ${en:'Email Address       ', ja:'メールアドレス      '} |
|:----------|:---------------------|
| ${en:'General  ', ja:'一般'} | info@authlete.com    |
| ${en:'Sales    ', ja:'営業'} | sales@authlete.com   |
| ${en:'PR       ', ja:'広報'} | pr@authlete.com      |
| ${en:'Technical', ja:'技術'} | support@authlete.com |



[1]: https://www.authlete.com/
[2]: https://docs.authlete.com/
[3]: https://www.rfc-editor.org/rfc/rfc6749.html
[4]: https://openid.net/connect/
[5]: ${ref5}
[6]: https://github.com/authlete/java-oauth-server
[7]: https://github.com/authlete/authlete-java-jaxrs
[8]: https://authlete.github.io/authlete-java-common/
[9]: https://github.com/authlete/java-resource-server
[10]: ${ref10}
