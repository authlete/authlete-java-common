CHANGES
=======

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
