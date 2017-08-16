Authlete Common Library for Java
================================

Overview
--------

This is a wrapper library for [Authlete Web APIs][2].

[Authlete][1] is a cloud service that provides an implementation of
[OAuth 2.0][3] & [OpenID Connect][4] ([overview][5]). By using the Web APIs
provided by Authlete, you can build a _DB-less_ authorization server.
"DB-less" here means that you don't have to prepare a database server that
stores authorization data (e.g. access tokens), settings of authorization
servers and settings of client applications. These data are stored in the
Authlete server on cloud.

[java-oauth-server][6] is the reference implementation of an authorization
server written using this library and [authlete-java-jaxrs][7] library.
It is a good starting point for your own authorization server implementation.


License
-------

  Apache License, Version 2.0


Maven
-----

```xml
<dependency>
    <groupId>com.authlete</groupId>
    <artifactId>authlete-java-common</artifactId>
    <version>2.8</version>
</dependency>
```


Source Code
-----------

  <code>https://github.com/authlete/authlete-java-common</code>


JavaDoc
-------

  <code>http://authlete.github.io/authlete-java-common/</code>


Description
-----------

#### How To Get AuthleteApi

All the methods to communicate with [Authlete Web APIs][2] are gathered in
`AuthleteApi` interface. To get an implementation of the interface, you need to
call `create()` method of `AuthleteApiFactory` class. There are two variants
of the method as shown below.

```java
public static AuthleteApi
    create(AuthleteConfiguration configuration);

public static AuthleteApi
    create(AuthleteConfiguration configuration, String className);
```

As you can see, both methods take `AuthleteConfiguration` as their first argument.
`AuthleteConfiguration` is an interface that holds configuration values to access
Authlete Web APIs such as the URL of Authlete server and API credentials of a
service. To be concrete, the interface has the following methods.

| Method                       | Description                |
|:-----------------------------|:---------------------------|
| `getBaseUrl()`               | URL of Authlete server     |
| `getServiceApiKey()`         | API key of a service       |
| `getServiceApiSecret()`      | API secret of a service    |
| `getServiceOwnerApiKey()`    | API key of your account    |
| `getServiceOwnerApiSecret()` | API secret of your account |

authlete-java-common library includes three implementations of
`AuthleteConfiguration` interface as listed below.

| Class                             | Description                             |
|:----------------------------------|:----------------------------------------|
| `AuthleteEnvConfiguration`        | Configuration via environment variables |
| `AuthletePropertiesConfiguration` | Configuration via a properties file     |
| `AuthleteSimpleConfiguration`     | Configuration via POJO                  |

You can use one of these or create your own implementation of the interface. In
either case, you can get an implementation of `AuthleteApi` interface by passing
an `AuthleteConfiguration` instance to `create()` method of `AuthleteApiFactory`
class.

In summary, the flow to get an implementation of `AuthleteApi` becomes like below.

```java
// Prepare an instance of AuthleteConfiguration interface.
AuthleteConfiguration configuration = ...;

// Get an instance of AuthleteApi interface.
AuthleteApi api = AuthleteApiFactory.create(configuration);
```

If you want to do it in an easier way, use `AuthleteApiFactory.getDefaultApi()`
method. This method searches the file system and the classpath for a properties
file named `authlete.properties` and loads the content of the file using
`AuthletePropertiesConfiguration` class.

```java
// Search the file system and the classpath for "authlete.properties".
AuthleteApi api = AuthleteApiFactory.getDefaultApi();
```

`AuthleteApiFactory.getDefaultApi()` method caches the search result, so you can
call th emethod as many times as you like without worrying about the overhead of
file loading.


#### AuthletePropertiesConfiguration

Among the three implementations of `AuthleteConfiguration` interface, this secion
explains `AuthletePropertiesConfiguration` class.

`AuthletePropertiesConfiguration` class provides a mechanism to use a properties
file to set configuration values to access Authlete Web APIs. The class searches
the file system and the classpath for a specified file.

Valid property keys in a properties file and their meanings are as follows.

| Property Key                         | Description                          |
|:-------------------------------------|:-------------------------------------|
| `base_url`                           | URL of Authlete server               |
| `service.api_key`                    | API key of a service                 |
| `service.api_secret`                 | API secret of a service              |
| `service.api_secret.encrypted`       | Encrypted API secret of a service    |
| `service_owner.api_key`              | API key of your account              |
| `service_owner.api_secret`           | API secret of your account           |
| `service_owner.api_secret.encrypted` | Encrypted API secret of your account |

If you don't want to write API secrets in plain text, use
`*.api_secret.encrypted` keys instead of `*.api_secret` keys. You can set
encrypted secrets to the `*.encrypted` keys. But in this case, you have to pass
the encryption key and the initial vector to a constructor of
`AuthletePropertiesConfiguration` so that the loader can decode the encrypted
values. See the [JavaDoc][8] for details.


#### AuthleteApi Implementation

Since version 2.0, authlete-java-common library includes an implementation of
`AuthleteApi` interface using `HttpURLConnection`. Before version 2.0,
[authlete-java-jaxrs][7] which contains an implementation of `AuthleteApi` was
additionally needed.

`AuthleteApiFactory.create()` method searches known locations for an
`AuthleteApi` implementation and loads one using reflection. The reason to use
reflection is to avoid depending on specific implementations (e.g. JAX-RS based
implementation in authlete-java-jaxrs).

As of this writing, known implementations of `AuthleteApi` interface are as
follows.

  1. `com.authlete.jaxrs.api.AuthleteApiImpl` (in [authlete-java-jaxrs][7])
  2. `com.authlete.common.api.AuthleteApiImpl` (in authlete-java-common)

`AuthleteApiFactory` checks existence of the above classes in this order.


#### AuthleteApi Method Categories

Methods in `AuthleteApi` interface can be divided into some categories.

  1. Methods for Authorization Endpoint Implementation

    - `authorization(AuthorizationRequest)`
    - `authorizationFail(AuthorizationFailRequest)`
    - `authorizationIssue(AuthorizationIssueRequest)`

  2. Methods for Token Endpoint Implementation

    - `token(TokenRequest)`
    - `tokenFail(TokenFailRequest)`
    - `tokenIssue(TokenIssueRequest)`

  3. Methods for Service Management

    - `createService(Service)`
    - `deleteService(long serviceApiKey)`
    - `getService(long serviceApiKey)`
    - `getServiceList()`
    - `getServiceList(int start, int end)`
    - `updateService(Service)`

  4. Methods for Client Application Management

    - `createClient(Client)`
    - `deleteClient(long clientId)`
    - `getClient(long clientId)`
    - `getClientList()`
    - `getClientList(int start, int end)`
    - `updateClient(Client)`

  5. Methods for Access Token Introspection

    - `introspection(IntrospectionRequest)`
    - `standardIntrospection(StandardIntrospectionRequest)`

  6. Methods for Revocation Endpoint Implementation

    - `revocation(RevocationRequest)`

  7. Methods for User Info Endpoint Implementation

    - `userinfo(UserInfoRequest)`
    - `userinfoIssue(UserInfoIssueRequest)`

  8. Methods for JWK Set Endpoint Implementation

    - `getServiceJwks()`
    - `getServiceJwks(boolean pretty, boolean includePrivateKeys)`

  9. Methods for OpenID Connect Discovery

    - `getServiceConfiguration()`
    - `getServiceConfiguration(boolean pretty)`

  10. Methods for Token Operations

    - `tokenCreate(TokenCreateRequest)`
    - `tokenUpdate(TokenUpdateRequest)`

  11. Methods for Requestable Scopes per Client (deprecated; Client APIs suffice)

    - `getRequestableScopes(long clientId)`
    - `setRequestableScopes(long clientId, String[] scopes)`
    - `deleteRequestableScopes(long clientId)`

  12. Methods for Records of Granted Scopes

    - `getGrantedScopes(long clientId, String subject)`
    - `deleteGrantedScopes(long clientId, String subject)`

  13. Methods for Authorization Management on a User-Client Combination Basis

    - `deleteClientAuthorization(long clientId)`
    - `getClientAuthorizationList(ClientAuthorizationGetListRequest request)`
    - `updateClientAuthorization(long clientId, ClientAuthorizationUpdateRequest request)`


Examples
--------

The following code snippet is an example to get the list of your existing
services. Each service corresponds to an authorization server.

```java
// Get an implementation of AuthleteApi interface.
AuthleteApi api = AuthleteApiFactory.getDefaultApi();

// Get the list of services.
ServiceListResponse response = api.getServiceList();
```


Note
----

You can write an authorization server using the methods in `AuthleteApi`
interface only, but the task will become much easier if you use utility classes
in [authlete-java-jaxrs][7] library. See [java-oauth-server][6] for an example of
an authorization server implementation written using the utility classes.


See Also
--------

- [Authlete][1] - Authlete Home Page
- [JavaDoc][8] - JavaDoc of this library
- [authlete-java-jaxrs][7] - Authlete Library for JAX-RS (Java)
- [java-oauth-server][6] - Authorization Server Implementation
- [java-resource-server][9] - Resource Server Implementation


Contact
-------

| Purpose   | Email Address        |
|:----------|:---------------------|
| General   | info@authlete.com    |
| Sales     | sales@authlete.com   |
| PR        | pr@authlete.com      |
| Technical | support@authlete.com |



[1]: https://www.authlete.com/
[2]: https://www.authlete.com/documents/apis
[3]: http://tools.ietf.org/html/rfc6749
[4]: http://openid.net/connect/
[5]: https://www.authlete.com/documents/overview
[6]: https://github.com/authlete/java-oauth-server
[7]: https://github.com/authlete/authlete-java-jaxrs
[8]: http://authlete.github.io/authlete-java-common/
[9]: https://github.com/authlete/java-resource-server
