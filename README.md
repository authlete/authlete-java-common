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

All the methods to communicate with Authlete Web APIs are gathered in
`AuthleteApi` interface. To get an implementation of the interface, you need to
call `getInstance` method of `AuthleteApiFactory` class. There are two variants
of the method as shown below.

```java
public static AuthleteApi
    getInstance(AuthleteConfiguration configuration);

public static AuthleteApi
    getInstance(AuthleteConfiguration configuration, String className);
```

As you can see, both methods take `AuthleteConfiguration` as their first argument.
`AuthleteConfiguration` is an interface that holds configuration values to access
Authlete Web APIs such as the URL of Authlete server and API credentials of a service.

authlete-java-common library includes three implementations of
`AuthleteConfiguration` interface as listed below.

| Class                             | Description                             |
|:----------------------------------|:----------------------------------------|
| `AuthleteEnvConfiguration`        | Configuration via environment variables |
| `AuthletePropertiesConfiguration` | Configuration via a properties file     |
| `AuthleteSimpleConfiguration`     | Configuration via POJO                  |

You can use one of these or define your own implementation of the interface. In
either case, you can get an implementation of `AutleteApi` interface by passing
an `AuthleteConfiguration` instance to `getInstance` method of
`AuthleteApiFactory` class.

In summary, the flow to get an implementation of `AuthleteApi` becomes like below.

```java
// Obtain an instance of AuthleteConfiguration interface.
AuthleteConfiguration configuration = ...;

// Obtain an instance of AuthleteApi interface.
AuthleteApi api = AuthleteApiFactory.getInstance(configuration);
```


#### DefaultApiFactory

The steps to get `AuthleteApi` described in the previous section may seem
troublesome. If you feel so, use `DefaultApiFactory.getInstance()` method.

TBW

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
