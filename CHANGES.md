CHANGES
=======

2.21 (2018-06-12)
-----------------

- New enum
    * `HokMethod`


2.20 (2018-05-16)
-----------------

- `Client` class
    * Added `getSelfSignedCertificateKeyId()` method.
    * Added `setSelfSignedCertificateKeyId(String)` method.


2.19 (2018-05-10)
-----------------

- `Client` class
    * Added `isTlsClientCertificateBoundAccessTokens()` method.
    * Added `setTlsClientCertificateBoundAccessTokens(boolean)` method.
    * Removed `isMutualTlsSenderConstrainedAccessTokens()` method.
    * Removed `setMutualTlsSenderConstrainedAccessTokens(boolean)` method.

- `Service` class
    * Added `isTlsClientCertificateBoundAccessTokens()` method.
    * Added `setTlsClientCertificateBoundAccessTokens(boolean)` method.
    * Removed `isMutualTlsSenderConstrainedAccessTokens()` method.
    * Removed `setMutualTlsSenderConstrainedAccessTokens(boolean)` method.


2.18 (2018-05-09)
-----------------

- `AuthleteApi` interface
    * Added `createService(Service)` method.
    * Marked `createServie(Service)` method as deprecated.


2.17 (2018-04-18)
-----------------

- `JWEAlg` enum
    * Added `getName()` method.

- `JWEEnc` enum
    * Added `getName()` method.

- `JWSAlg` enum
    * Added `getName()` method.


2.16 (2018-04-18)
-----------------
- `JWSAlg` enum
    * Added `isSymmetric()` method.
    * Added `isAsymmetric()` method.


2.15 (2018-04-12)
-----------------

- `Service` class
    * Added `isMutualTlsValidatePkiCertChain()` method.
    * Added `setMutualTlsValidatePkiCertChain(boolean)` method.
    * Added `getTrustedRootCertificates()` method.
    * Added `setTrustedRootCertificates(String[])` method.

- `TokenRequest` class
    * Added `getClientCertificatePath()` method.
    * Added `setClientCertificatePath(String[])` method.


2.14 (2018-03-14)
-----------------

- `IntrospectionRequest` class
    * Added `getClientCertificate()` method.
    * Added `setClientCertificate(String)` method.

- `IntrospectionResponse` class
    * Added `getCertificateThumbprint()` method.
    * Added `setCertificateThumbprint(String)` method.


2.13 (2018-03-13)
-----------------

- `Client` class
    * Added `isMutualTlsSenderConstrainedAccessTokens()` method.
    * Added `setMutualTlsSenderConstrainedAccessTokens(boolean)` method.

- `Service` class
    * Added `isMutualTlsSenderConstrainedAccessTokens()` method.
    * Added `setMutualTlsSenderConstrainedAccessTokens(boolean)` method.
    * Added `getSupportedRevocationAuthMethods()` method.
    * Added `setSupportedRevocationAuthMethods()` method.
    * Added `getSupportedRevocationAuthSigningAlgorithms()` method.
    * Added `setSupportedRevocationAuthSigningAlgorithms(JWSAlg[])` method.
    * Added `getIntrospectionEndpoint()` method.
    * Added `setIntrospectionEndpoint()` method.
    * Added `getSupportedIntrospectionAuthMethods()` method.
    * Added `setSupportedIntrospectionAuthMethods()` method.
    * Added `getSupportedIntrospectionAuthSigningAlgorithms()` method.
    * Added `setSupportedIntrospectionAuthSigningAlgorithms(JWSAlg[])` method.


2.12 (2018-03-03)
-----------------

- `JWSAlg` enum
    * Added `isSymmetric(JWSAlg)` method.
    * Added `isAsymmetric(JWSAlg)` method.

- `Scope` class
    * Added `getAttributes()` method.
    * Added `setAttributes(Pair[])` method.
    * Added `setAttributes(Iterable<Pair>)` method.

- `Service` class
    * Added `getSupportedServiceProfiles()` method.
    * Added `setSupportedServiceProfiles(ServiceProfile[])` method.
    * Added `setSupportedServiceProfiles(Iterable<ServiceProfile>)` method.
    * Added `supports(ServiceProfile)` method.
    * Added `supportsAll(ServiceProfile...)` method.
    * Added `supportsAll(Iterable<ServiceProfile>)` method.
    * Added `supportsAny(ServiceProfile...)` method.
    * Added `supportsAny(Iterable<ServiceProfile>)` method.

- `TokenRequest` class
    * Added `getClientCertificate()` method.
    * Added `setClientCertificate(String)` method.

- New enum
    * `ServiceProfie`


2.11 (2017-11-16)
-----------------

- `AuthleteApi` interface
    * Added `refreshClientSecret(long)` method.
    * Added `refreshClientSecret(String)` method.
    * Added `updateClientSecret(long, String)` method.
    * Added `updateClientSecret(String, String)` method.

- `AuthorizationFailRequest.Reason` enum
    * Added `ACCOUNT_SELECTION_REQUIRED`.
    * Added `CONSENT_REQUIRED`.
    * Added `INTERACTION_REQUIRED`.

- `AuthorizationResponse` class
    * Updated the JavaDoc.
    * Marked `getLowestPrompt()` method as 'deprecated'.

- `Client` class
    * Removed `tlsClientAuthRootDn` property.

- `ClientAuthMethod` enum
    * Added `SELF_SIGNED_TLS_CLIENT_AUTH`.

- New classes
    * `ClientSecretRefreshResponse` class.
    * `ClientSecretUpdateRequest` class.
    * `ClientSecretUpdateResponse` class.


2.10 (2017-10-18)
-----------------

- `Settings` class
    * Added `getReadTimeout()` method.
    * Added `setReadTimeout(int)` method.


2.9 (2017-10-13)
----------------

- `AuthleteApi` interface
    * Added `getSettings()` method.

- New classes
    * `Settings` class.


2.8 (2017-10-13)
----------------

- `TokenResponse` class
    * Added `grantType` property.
    * Added `clientId` property.
    * Added `clientIdAlias` property.
    * Added `clientIdAliasUsed` property.
    * Added `subject` property.
    * Added `scopes` property.
    * Added `properties` property.

- `TokenIssueResponse` class
    * Added `clientId` property.
    * Added `clientIdAlias` property.
    * Added `clientIdAliasUsed` property.
    * Added `subject` property.
    * Added `scopes` property.
    * Added `properties` property.


2.7 (2017-07-20)
----------------

- `AuthleteApi` interface
    * Added `standardIntrospection(StandardIntrospectionRequest)` method.

- `Client` class
    * Added `tlsClientAuthSubjectDn` property.
    * Added `tlsClientAuthRootDn` property.

- `ClientAuthMethod` enum
    * Added `TLS_CLIENT_AUTH`.

- New classes
    * `StandardIntrospectionRequest` class
    * `StandardIntrospectionResponse` class


2.6 (2017-06-10)
----------------

- `TokenCreateRequst` class
    * Added `accessToken` property.
    * Added `refreshToken` property.


2.5 (2017-04-19)
----------------

- `UserInfoResponse` class
    * Added `properties` property.
    * Added `clientIdAlias` property.
    * Added `clientIdAilasUsed` property.

- `Utils` class
    * Added `stringifyPrompts(Prompt[])` method.
    * Added `stringifyProperties(Property[])` method.
    * Added `stringifyScopeNames(Scope[])` method.


2.4 (2017-04-19)
----------------

- `Service` class
    * Removed `idTokenEncryptionKeyId` property.
    * Removed `userInfoEncryptionKeyId` property.


2.3 (2017-04-06)
----------------

- `AuthorizationResponse` class
    * Added `clientIdAliasUsed` property.

- `IntrospectionResponse` class
    * Added `clientIdAlias` property.
    * Added `clientIdAliasUsed` property.

- `TokenCreateRequest` class
    * Added `clientIdAliasUsed` property.


2.2 (2017-04-02)
----------------

- `Service` class
    * Added `clientIdAliasEnabled` property.

- `Client` class
    * Added `clientIdAliasEnabled` property.


2.1 (2017-03-18)
----------------

- `AuthleteApi` interface
    * Added `deleteClientAuthorization(long, String)` method.
    * Added `getClientAuthorizationList(ClientAuthorizationGetListRequest)` method.
    * Added `updateClientAuthorization(long, ClientAuthorizationUpdateRequest)` method.

- `AuthleteApiImpl` class
    * Implemented newly-added methods of `AuthleteApi` interface.
    * Fixed a bug where `Content-Type:application/json` was not set for POST requests.

- `Service` class
    * Added `idTokenSignatureKeyId` property.
    * Added `idTokenEncryptionKeyId` property.
    * Added `userInfoSignatureKeyId` property.
    * Added `userInfoEncryptionKeyId` property.

- `Client` class
    * Added `clientIdAlias` property.

- `ClientAuthorizationUpdateRequest` class
    * Added `ClientAuthorizationUpdateRequest(String, String[])` constructor.

- `ClientAuthorizationDeleteRequest` class
    * Newly added.

- `ClientAuthorizationGetListRequest` class
    * Newly added.

- `CLI` class
    * Supported `getClientAuthorizationList` API.


2.0 (2017-02-27)
----------------

- Added an implementation of `AuthleteApi` interface using
  `HttpURLConnection`. The new implementation has been added
  to the list of known implementations in `AuthleteApiFactory`.

- Implemented command line interface for Authlete API.
  `CLI` class and `authlete-cli.sh` script have been added.

- `Utils` class
    * Added `toJson(Object, boolean)` method.
    * Added `fromJson(String, Class)` method.

- Added `authlete.properties` in `.gitignore`.


1.41 (2017-02-19)
-----------------

- Added `setRequestableScopes(Set)` method to 'ClientExtension' class.


1.40 (2017-02-17)
-----------------

- Added `deleteGrantedScopes(long, String)` method to
  `AuthleteApi` interface.

- Deleted `properties` property from `Service` class.


1.39 (2017-02-13)
-----------------

- Added new classes
    * `ClientExtension` class
    * `Pair` class

- `AuthleteApi` interface
    * Added `getGrantedScopes(long, String)` method.

- `Client` class
    * Added `extension` field.

- `Service` class
    * Added `directIntrospectionEndpointEnabled` field.
    * Added `errorDescriptionOmitted` field.
    * Added `errorUriOmitted` field.
    * Added `metadata` field.
    * Marked `properties` property as deprecated.


1.38 (2016-09-20)
-----------------

- Added `GrantedScopesGetResponse` class.


1.37 (2016-09-12)
-----------------

- Added `ClientAuthorizationUpdateRequest` class.


1.36 (2016-09-06)
-----------------

- Added `AuthorizedClientListResponse` class.


1.35 (2016-08-15)
-----------------

- `AuthorizationIssueRequest`
    * Added `sub` field to adjust the value of the `sub` claim.

- `UserInfoIssueRequest`
    * Added `sub` field to adjust the value of the `sub` claim.


1.34 (2016-07-30)
-----------------

- New classes
    * Added `TokenUpdateRequest` class.
    * Added `TokenUpdateResponse` class.

- `AuthleteApi`
    * Added `tokenUpdate(TokenUpdateRequest)` method.
    * Added `getRequestableScopes(long clientId)` method.
    * Added `setRequestableScopes(long clientId, String[] scopes)` method.
    * Added `deleteRequestableScopes(long clientId)` method.

- `AuthorizationIssueRequest`
    * Added `scopes` field to replace the scopes contained in
      the original authorization request.

- `AuthorizationIssueResponse`
    * Added `accessToken` field.
    * Added `accessTokenExpiresAt` field.
    * Added `accessTokenDuration` field.
    * Added `idToken` field.
    * Added `authorizationCode` field.

- `AuthorizationResponse`
    * Added `prompts` field which represents the value of `prompt`
      request parameter in the original authorization request.

- `TokenCreateResponse`
    * Added `properties` field.

- `TokenIssueResponse`
    * Added `accessToken` field.
    * Added `accessTokenExpiresAt` field.
    * Added `accessTokenDuration` field.
    * Added `refreshToken` field.
    * Added `refreshTokenExpiresAt` field.
    * Added `refreshTokenDuration` field.

- `TokenResponse`
    * Added `accessToken` field.
    * Added `accessTokenExpiresAt` field.
    * Added `accessTokenDuration` field.
    * Added `refreshToken` field.
    * Added `refreshTokenExpiresAt` field.
    * Added `refreshTokenDuration` field.
    * Added `idToken` field.
