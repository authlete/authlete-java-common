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
servers and settings of client applications because they are stored in the
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
    <version>1.26</version>
</dependency>
```


Source Code
-----------

  https://github.com/authlete/authlete-java-common


JavaDoc
-------

  http://authlete.github.io/authlete-java-common/


Description
-----------

#### How To Get AuthleteApi

All the methods to communicate with [Authlete Web APIs][2] are gathered in
`AuthleteApi` interface. To get an implementation of the interface, you need to
call `getInstance()` method of `AuthleteApiFactory` class. There are two variants
of the method as shown below.

```java
public static AuthleteApi
    getInstance(AuthleteConfiguration configuration);

public static AuthleteApi
    getInstance(AuthleteConfiguration configuration, String className);
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
either case, you can get an implementation of `AutleteApi` interface by passing
an `AuthleteConfiguration` instance to `getInstance()` method of
`AuthleteApiFactory` class.

In summary, the flow to get an implementation of `AuthleteApi` becomes like below.

```java
// Get an instance of AuthleteConfiguration interface.
AuthleteConfiguration configuration = ...;

// Get an instance of AuthleteApi interface.
AuthleteApi api = AuthleteApiFactory.getInstance(configuration);
```

If you want to do it in an easier way, use `DefaultApiFactory.getInstance()`
method. This method searches the file system and the classpath for a properties
file named `authlete.properties` and loads the content of the file using
`AuthletePropertiesConfiguration` class.

```
// Search the file system and the classpath for "authlete.properties".
AuthleteApi api = DefaultApiFactory.getInstance();
```

`DefaultApiFactory.getInstance()` method caches the result, so you can call the
method as many times as you like without worrying about the overhead of file
loading.


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

As a matter of fact, authlete-java-common library does NOT include any
implementation of `AuthleteApi` interface. Therefore, you need another library
that includes an implementation of `AuthleteApi` interface. At the time of this
writing, [authlete-java-jaxrs][7] is the only such library.

`AuthleteApiFactory.getInstance()` method searches known locations for an
`AuthleteApi` implementation using reflection to avoid depending on specific
implementations (e.g. JAX-RS based implementation in authlete-java-jaxrs). And,
at the time of this writing, `com.authlete.jaxrs.api.AuthleteApiImpl` is the
only entry in the internal list of known locations of implementation classes.


#### AuthleteApi Method Categories

Methods in `AuthleteApi` interface can be divided into some categories.

  1. Methods for Authorization Endpoint Implementation

    - `authorization()`
    - `authorizationFail()`
    - `authorizationIssue()`

  2. Methods for Token Endpoint Implementation

    - `token()`
    - `tokenFail()`
    - `tokenIssue()`

  3. Methods for Service Management

    - `createService()`
    - `deleteService()`
    - `getService()`
    - `getServiceList()`
    - `updateService()`

  4. Methods for Client Application Management

    - `createClient()`
    - `deleteClient()`
    - `getClient()`
    - `getClientList()`
    - `updateClient()`

  5. Methods for Access Token Introspection

    - `introspection()`

  6. Methods for Revocation Endpoint Implementation

    - `revocation()`

  7. Methods for User Info Endpoint Implementation

    - `userinfo()`
    - `userinfoIssue()`

  8. Methods for JWK Set Endpoint Implementation

    - `getServiceJwks()`


#### Examples

The following code snippet is an example to get the list of the existing services
(each of which represents an authorization server) of yours.

```java
// Get an implementation of AuthleteApi interface.
AuthleteApi api = DefaultApiFactory.getInstance();

// Get the list of services.
ServiceListResponse response = api.getServiceList();
```


See Also
--------

- [Authlete][1] - Authlete Home Page
- [JavaDoc][8] - JavaDoc of this library
- [authlete-java-jaxrs][7] - Authlete Library for JAX-RS (Java)
- [java-oauth-server][6] - Authorization Server Implementation


Support
-------

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
