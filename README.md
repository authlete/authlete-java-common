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

  JSON files under `src/test/resources/ekyc-ida` have been copied from
  https://bitbucket.org/openid/ekyc-ida/src/master/examples/response/ .
  Regarding their license, ask the eKYC-IDA WG of OpenID Foundation.


Maven
-----

```xml
<dependency>
    <groupId>com.authlete</groupId>
    <artifactId>authlete-java-common</artifactId>
    <version>${authlete-java-common.version}</version>
</dependency>
```

Please refer to the [CHANGES.md](CHANGES.md) file to know the latest version
to write in place of `${authlete-java-common.version}`.


Source Code
-----------

  <code>https://github.com/authlete/authlete-java-common</code>


JavaDoc
-------

  <code>https://authlete.github.io/authlete-java-common/</code>

  <code>https://authlete.github.io/authlete-java-common/index.html?overview-summary.html</code> [FRAMES]


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

| Method                       | Authlete Version  | Description                |
|:-----------------------------|:------------------|:---------------------------|
| `getBaseUrl()`               | Common            | URL of Authlete server     |
| `getServiceApiKey()`         | Common            | API key of a service       |
| `getServiceApiSecret()`      | Up to version 2.x | API secret of a service    |
| `getServiceOwnerApiKey()`    | Up to version 2.x | API key of your account    |
| `getServiceOwnerApiSecret()` | Up to version 2.x | API secret of your account |
| `getApiVersion()`            | Since version 3.0 | API version                |
| `getServiceAccessToken()`    | Since version 3.0 | API access token           |

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
call the method as many times as you like without worrying about the overhead of
file loading.


#### AuthletePropertiesConfiguration

Among the three implementations of `AuthleteConfiguration` interface, this section
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
| `api_version`                        | API version. "V3" for Authlete 3.0   |
| `service.access_token`               | API access token                     |

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


#### AuthleteApi Settings

`getSettings()` method of `AuthleteApi` interface has been available since
the version 2.9. By configuring the instance returned by the method, you can
change behaviours of the implementation of `AuthleteApi` interface.

*Examples*

```java
// An implementation of AuthleteApi interface.
AuthleteApi api = ...;

// Get the instance which holds settings of the AuthleteApi implementation.
Settings settings = api.getSettings();

// Set a connection timeout in milliseconds.
//
//   Note:
//     There is no standard way to set a connection timeout value
//     before JAX-RS API 2.1 (which is a part of Java EE 8).
//     Therefore, if authlete-java-jaxrs is used as AuthleteApi
//     implementation and if the JAX-RS Client implementation is
//     not supported by the implementation of setConnectionTimeout()
//     of authlete-java-jaxrs, the value given to setConnectionTimeout()
//     won't have any effect. See README in authlete-java-jaxrs
//     for details.
//
settings.setConnectionTimeout(5000);

// Set a read timeout in milliseconds.
//
//   Note:
//     There is no standard way to set a read timeout value before
//     JAX-RS API 2.1 (which is a part of Java EE 8). Therefore,
//     if authlete-java-jaxrs is used as AuthleteApi implementation
//     and if the JAX-RS Client implementation is not supported by
//     the implementation of setReadTimeout() of authlete-java-jaxrs,
//     the value given to setReadTimeout() won't have any effect.
//     See README in authlete-java-jaxrs for details.
//
settings.setReadTimeout(5000);
```


#### AuthleteApi Method Categories

Methods in `AuthleteApi` interface can be divided into some categories.

  1. Methods for Authorization Endpoint Implementation

  - `authorization(AuthorizationRequest request)`
  - `authorizationFail(AuthorizationFailRequest request)`
  - `authorizationIssue(AuthorizationIssueRequest request)`

  2. Methods for Token Endpoint Implementation

  - `token(TokenRequest request)`
  - `tokenFail(TokenFailRequest request)`
  - `tokenIssue(TokenIssueRequest request)`
  - `idTokenReissue(IDTokenReissueRequest request)`

  3. Methods for Service Management

  - `createService(Service service)`
  - `deleteService(long serviceApiKey)`
  - `getService(long serviceApiKey)`
  - `getServiceList()`
  - `getServiceList(int start, int end)`
  - `updateService(Service service)`

  4. Methods for Client Application Management

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

  5. Methods for Access Token Introspection

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

  6. Methods for Revocation Endpoint Implementation

  - `revocation(RevocationRequest request)`

  7. Methods for User Info Endpoint Implementation

  - `userinfo(UserInfoRequest request)`
  - `userinfoIssue(UserInfoIssueRequest request)`

  8. Methods for JWK Set Endpoint Implementation

  - `getServiceJwks()`
  - `getServiceJwks(boolean pretty, boolean includePrivateKeys)`

  9. Methods for OpenID Connect Discovery

  - `getServiceConfiguration()`
  - `getServiceConfiguration(boolean pretty)`

  10. Methods for Token Operations

  - `tokenCreate(TokenCreateRequest request)`
  - `tokenDelete(String token)`
  - `tokenRevoke(TokenRevokeRequest request)`
  - `tokenUpdate(TokenUpdateRequest request)`
  - `tokenCreateBatch(TokenCreateRequest[] request)`
  - `getTokenCreateBatchStatus(String requestId)`

  11. Methods for Requestable Scopes per Client (deprecated; Client APIs suffice)

  - `getRequestableScopes(long clientId)`
  - `setRequestableScopes(long clientId, String[] scopes)`
  - `deleteRequestableScopes(long clientId)`

  12. Methods for Records of Granted Scopes

  - `getGrantedScopes(long clientId, String subject)`
  - `deleteGrantedScopes(long clientId, String subject)`

  13. Methods for Authorization Management on a User-Client Combination Basis

  - `deleteClientAuthorization(long clientId, String subject)`
  - `getClientAuthorizationList(ClientAuthorizationGetListRequest request)`
  - `updateClientAuthorization(long clientId, ClientAuthorizationUpdateRequest request)`

  14. Methods for JOSE

  - `verifyJose(JoseVerifyRequest)`

  15. Methods for CIBA (Client Initiated Backchannel Authentication)

  - `backchannelAuthentication(BackchannelAuthenticationRequest)`
  - `backchannelAuthenticationIssue(BackchannelAuthenticationIssueRequest)`
  - `backchannelAuthenticationFail(BackchannelAuthenticationFailRequest)`
  - `backchannelAuthenticationComplete(BackchannelAuthenticationCompleteRequest)`

  16. Methods for OpenID Connect Dynamic Client Registration

  - `dynamicClientRegister(ClientRegistrationRequest)`
  - `dynamicClientGet(ClientRegistrationRequest)`
  - `dynamicClientUpdate(ClientRegistrationRequest)`
  - `dynamicClientDelete(ClientRegistrationRequest)`

  17. Methods for Device Flow

  - `deviceAuthorization(DeviceAuthorizationRequest)`
  - `deviceComplete(DeviceCompleteRequest)`
  - `deviceVerification(DeviceVerificationRequest)`

  18. Methods for Pushed Authorization Requests

  - `pushAuthorizationRequest(PushedAuthReqRequest)`

  19. Methods for Grant Management for OAuth 2.0

  - `gm(GMRequest)`

  20. Methods for OpenID Connect Federation 1.0

  - `federationConfiguration(FederationConfigurationRequest)`
  - `federationRegistration(FederationRegistrationRequest)`

  21. Methods for Verifiable Credentials

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

  22. Methods for OpenID Connect Native SSO for Mobile Apps 1.0

  - `nativeSso(NativeSsoRequest)`
  - `nativeSsoLogout(NativeSsoLogoutRequest)`

*Examples*

The following code snippet is an example to get the list of your existing
services. Each service corresponds to an authorization server.

```java
// Get an implementation of AuthleteApi interface.
AuthleteApi api = AuthleteApiFactory.getDefaultApi();

// Get the list of services.
ServiceListResponse response = api.getServiceList();
```


Authlete Version
----------------

Some APIs and features don't work (even if they are defined in the `AuthleteApi`
interface) if Authlete API server you use doesn't support them. For example,
CIBA works only in Authlete 2.1 onwards. Please contact us if you want to use
newer Authlete versions.

Features available in Authlete 2.0 and onwards:

- Financial-grade API (FAPI)
- OAuth 2.0 Mutual TLS Client Authentication and Certificate Bound Access Tokens (MTLS)
- JWT-based Client Authentication (RFC 7523)
- Scope attributes
- UK Open Banking Security Profile

Features available in Authlete 2.1 and onwards:

- Client Initiated Backchannel Authentication (CIBA)
- JWT Secured Authorization Response Mode for OAuth 2.0 (JARM)
- Dynamic Client Registration (RFC 7591 & RFC 7592)
- OAuth 2.0 Device Authorization Grant (Device Flow)
- JWT-based Access Token

See [Spec Sheet](https://www.authlete.com/legal/spec_sheet/) for further details.


Note
----

You can write an authorization server using the methods in `AuthleteApi`
interface only, but the task will become much easier if you use utility classes
in [authlete-java-jaxrs][7] library. See [java-oauth-server][6] for an example of
an authorization server implementation written using the utility classes.


Automatic Annotation of Ambiguous Json Setter Methods
-----------------------------------------------------

To address the "multiple ambiguous setter methods" json deserialization issue. 
An annotation processor has been added to the maven build step and will run 
during the `process-classes` maven phase.
The `JsonSetterAnnotationProcessor` class is responsible for this process. 
The process involves iterating over all classes in the DTO package. Then 
finding any ambiguously defined setter methods (setter methods defined with 
the same name) which do not have any `@JsonSetter` annotation defined. If 
there are setters found that match these criteria, the correct setter method 
to annotate will be determined by matching the first argument of the setter 
method with the return type of the matching getter method.
The new annotation is then added to the identified setter method and written 
to the existing class file.


Code Quality Tools
-----------------

The project now includes several code quality tools to maintain high standards:

1. **Checkstyle**
   - Added via maven-checkstyle-plugin
   - Custom rules defined in `checkstyle.xml`
   - Enforces coding standards and improves code quality

2. **SpotBugs**
   - Configuration split into include and exclude files
   - `spotbugs-exclude.xml`: Defines patterns to exclude from analysis
   - `spotbugs-include.xml`: Contains rules for various bug categories

3. **Dependencies**
   - Updated nimbus-jose-jwt to version 9.31
   - Added new dependencies for enhanced security and functionality

These tools are configured to run during the build process and help maintain code quality across the project.

**Quality Standards for Contributors:**

- **Code Review**: All changes must pass automated quality checks before submission
- **Testing**: Maintain test coverage and ensure all tests pass
- **Documentation**: Keep code documentation current and comprehensive
- **Performance**: Consider impact on performance and optimize where necessary
- **Security**: Follow security best practices and validate all inputs
- **Compatibility**: Maintain backward compatibility unless breaking changes are necessary
- **Standards**: Follow established coding conventions and project guidelines


Contributing Guidelines
----------------------

To maintain high code quality standards, please follow these guidelines when contributing to the project:

1. **Code Style**
   - Follow the Checkstyle rules defined in `checkstyle.xml`
   - Use consistent indentation and formatting
   - Follow Java naming conventions
   - Keep methods and classes focused and well-documented

2. **Code Quality Checks**
   - Run `mvn checkstyle:check` to verify code style compliance
   - Run `mvn spotbugs:check` to detect potential bugs
   - Ensure all tests pass before submitting changes
   - Address any warnings or errors from quality tools

3. **Documentation**
   - Update relevant documentation when adding new features
   - Include JavaDoc comments for public methods and classes
   - Update README.md if introducing new functionality
   - Provide clear commit messages

4. **Testing**
   - Write unit tests for new functionality
   - Ensure existing tests continue to pass
   - Test with different Authlete API versions when applicable

5. **Security**
   - Follow security best practices
   - Validate all inputs
   - Use secure coding patterns
   - Review for potential vulnerabilities

6. **Performance**
   - Consider performance implications of changes
   - Use efficient algorithms and data structures
   - Avoid unnecessary object creation
   - Profile critical code paths when needed

7. **Compatibility**
   - Maintain backward compatibility when possible
   - Test with supported Java versions
   - Verify compatibility with Authlete API versions
   - Document any breaking changes

8. **Review Process**
   - Self-review your changes before submission
   - Ensure code follows project conventions
   - Address feedback from code reviews promptly
   - Keep pull requests focused and manageable

For questions about these guidelines or the development process, please reach out to the development team.


See Also
--------

- [Authlete][1] - Authlete Home Page
- [JavaDoc][8] - JavaDoc of this library
- [authlete-java-jaxrs][7] - Authlete Library for JAX-RS (Java)
- [java-oauth-server][6] - Authorization Server Implementation
- [java-resource-server][9] - Resource Server Implementation
- [New Architecture of OAuth 2.0 and OpenID Connect Implementation][10] - Explanation about Authlete Architecture


Contact
-------

| Purpose   | Email Address        |
|:----------|:---------------------|
| General   | info@authlete.com    |
| Sales     | sales@authlete.com   |
| PR        | pr@authlete.com      |
| Technical | support@authlete.com |



[1]: https://www.authlete.com/
[2]: https://docs.authlete.com/
[3]: https://www.rfc-editor.org/rfc/rfc6749.html
[4]: https://openid.net/connect/
[5]: https://www.authlete.com/developers/overview/
[6]: https://github.com/authlete/java-oauth-server
[7]: https://github.com/authlete/authlete-java-jaxrs
[8]: https://authlete.github.io/authlete-java-common/
[9]: https://github.com/authlete/java-resource-server
[10]: https://medium.com/@darutk/new-architecture-of-oauth-2-0-and-openid-connect-implementation-18f408f9338d
