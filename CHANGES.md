CHANGES
=======

- `GrantType` enum
    * Added `DEVICE_CODE`.

- `Service` class
    * Added `getDeviceAuthorizationEndpoint()` method.
    * Added `setDeviceAuthorizationEndpoint(URI)` method.


2.41 (2019-05-30)
-----------------

- `AuthleteApi` interface
    * Added `dynamicClientDelete(ClientRegistrationRequest)` method.
    * Added `dynamicClientGet(ClientRegistrationRequest)` method.
    * Added `dynamicClientRegister(ClientRegistrationRequest)` method.
    * Added `dynamicClientUpdate(ClientRegistrationRequest)` method.
    * Removed `registerClient(ClientRegistrationRequest)` method.


2.40 (2019-05-24)
-----------------

- `ErrorCode` enum
    * Added `invalid_binding_message`.


2.39 (2019-05-20)
-----------------

- `Client` class
    * `isDynamicallyRegistered` method.
    * `setDynamicallyRegistered(boolean)` method.
    * `getRegistrationAccessTokenHash` method.
    * `setRegistrationAccessTokenHash(String)` method.

- `ClientRegistrationRequest` class
    * Removed `getMetadata()` method.
    * Removed `setMetadata(String)` method.
    * Added `getJson()` method.
    * Added `setJson(String)` method.
    * Added `getToken()` method.
    * Added `setToken(String)` method.
    * Added `getClientId` method.
    * Added `setClientId(String)` method.

- `ClientRegistrationResponse.Action` enum
    * Added `UPDATED`.
    * Added `DELETED`.
    * Added `OK`.

- `Service`
    * Added `getRegistrationManagementEndpoint()` method.
    * Added `setRegistrationManagementEndpoint(URI)` method.

- New enums
    * `AssertionTarget`
    * `ClaimRuleOperation`

- New classes
    * `AssertionProcessor`
    * `ClaimRule`


2.38 (2019-04-09)
-----------------

- `Client` class
    * Added `getTlsClientAuthSanDns()` method.
    * Added `setTlsClientAuthSanDns(String)` method.
    * Added `getTlsClientAuthSanUri()` method.
    * Added `setTlsClientAuthSanUri(URI)` method.
    * Added `getTlsClientAuthSanIp()` method.
    * Added `setTlsClientAuthSanIp(String)` method.
    * Added `getTlsClientAuthSanEmail()` method.
    * Added `setTlsClientAuthSanEmail(String)` method.


2.37 (2019-03-16)
-----------------

- `AuthorizationIssueResponse` class
    * Added `getJwtAccessToken()` method.
    * Added `setJwtAccessToken(String)` method.

- `BackchannelAuthenticationCompleteResponse` class
    * Added `getJwtAccessToken()` method.
    * Added `setJwtAccessToken(String)` method.

- `Service` class
    * Added `getAccessTokenSignAlg()` method.
    * Added `setAccessTokenSignAlg(JWSAlg)` method.
    * Added `getAccessTokenSignatureKeyId()` method.
    * Added `setAccessTokenSignatureKeyId(String)` method.

- `TokenIssueResponse` class
    * Added `getJwtAccessToken()` method.
    * Added `setJwtAccessToken(String)` method.

- `TokenResponse` class
    * Added `getJwtAccessToken()` method.
    * Added `setJwtAccessToken(String)` method.

- `UserInfoRequest` class
    * Added `getClientCertificate()` method.
    * Added `setClientCertificate(String)` method.


2.36 (2019-01-17)
-----------------

- `BackchannelAuthenticationCompleteRequest.Result` enum
    * Renamed `ERROR` to `TRANSACTION_FAILED`.

- `ErrorCode` enum
    * Added `transaction_failed`.


2.35 (2019-01-12)
-----------------

- `BackchannelAuthenticationFailRequest.Reason` enum
    * Added `INVALID_BINDING_MESSAGE`.

- `BackchannelAuthenticationResponse` class
    * Added `getRequestedExpiry()` method.
    * Added `setRequestedExpiry(int)` method.


2.34 (2019-01-12)
-----------------

- `GrantType` enum
    * Renamed `BACKCHANNEL_REQUEST` to `CIBA`.


2.33 (2019-01-04)
-----------------

- `BackchannelAuthenticationResponse` class
    * Added `isUserCodeRequired()` method.
    * Added `setUserCodeRequired(boolean)` method.

- `User` interface
    * Added `getAttribute(String)` method.


2.32 (2018-12-29)
-----------------

- `AuthleteApi` interface
    * Added `backchannelAuthentication(BackchannelAuthenticationRequest)` method.
    * Added `backchannelAuthenticationIssue(BackchannelAuthenticationIssueRequest)` method.
    * Added `backchannelAuthenticationFail(BackchannelAuthenticationFailRequest)` method.
    * Added `backchannelAuthenticationComplete(BackchannelAuthenticationCompleteRequest)` method.

- `Client` class
    * Added `getBcDeliveryMode()` method.
    * Added `setBcDeliveryMode(DeliveryMode)` method.
    * Added `getBcNotificationEndpoint()` method.
    * Added `setBcNotificationEndpoint(URI)` method.
    * Added `getBcRequestSignAlg()` method.
    * Added `setBcRequestSignAlg(JWSAlg)` method.
    * Added `isBcUserCodeRequired()` method.
    * Added `setBcUserCodeRequired(boolean)` method.

- `ErrorCode` enum
    * Added `expired_login_hint_token`.
    * Added `unknown_user_id`.
    * Added `missing_user_code`.
    * Added `invalid_user_code`.
    * Added `authorization_pending`.
    * Added `slow_down`.
    * Added `expired_token`.

- `GrantType` enum
    * Added `BACKCHANNEL_REQUEST`.

- `Service` class
    * Added `getSupportedBackchannelTokenDeliveryModes()` method.
    * Added `setSupportedBackchannelTokenDeliveryModes(DeliveryMode[])` method.
    * Added `getBackchannelAuthenticationEndpoint()` method.
    * Added `setBackchannelAuthenticationEndpoint(URI)` method.
    * Added `isBackchannelUserCodeParameterSupported()` method.
    * Added `setBackchannelUserCodeParameterSupported(boolean)` method.
    * Added `getBackchannelAuthReqIdDuration()` method.
    * Added `setBackchannelAuthReqIdDuration(int)` method.
    * Added `getBackchannelPollingInterval()` method.
    * Added `setBackchannelPollingInterval(int)` method.
    * Added `getAllowableClockSkew()` method.
    * Added `setAllowableClockSkew(int)` method.

- New enums
    * `DeliveryMode`
    * `UserIdentificationHintType`

- New classes
    * `BackchannelAuthenticationRequest`
    * `BackchannelAuthenticationResponse`
    * `BackchannelAuthenticationIssueRequest`
    * `BackchannelAuthenticationIssueResponse`
    * `BackchannelAuthenticationFailRequest`
    * `BackchannelAuthenticationFailResponse`
    * `BackchannelAuthenticationCompleteRequest`
    * `BackchannelAuthenticationCompleteResponse`


2.31 (2018-10-19)
-----------------

- `TokenUpdateRequest` class
    * Added `getAccessTokenHash()` method.
    * Added `setAccessTokenHash(String)` method.
    * Added `isAccessTokenValueUpdated()` method.
    * Added `setAccessTokenValueUpdated(boolean)` method.

- `TokenUpdateResponse` class
    * Added `getTokenType()` method.
    * Added `setTokenType(String)` method.


2.30 (2018-10-10)
-----------------

- `AuthleteConfiguration` interface
    * Added `getServiceAccessToken()` method.
    * Added `getServiceOwnerAccessToken()` method.

- `TokenCreateRequest` class
    * Added `isAccessTokenPersistent()` method.
    * Added `setAccessTokenPersistent(boolean)` method.

- `TokenUpdateRequest` class
    * Added `isAccessTokenPersistent()` method.
    * Added `setAccessTokenPersistent(boolean)` method.


2.29 (2018-10-05)
-----------------

- `AuthleteApi` interface
    * Added `getTokenList()` method.
    * Added `getTokenList(String, String)` method.
    * Added `getTokenList(int, int)` method.
    * Added `getTokenList(String, String, int, int)` method.

- `TokenUpdateRequest` class
    * Added `isAccessTokenExpiresAtUpdatedOnScopeUpdate()` method.
    * Added `setAccessTokenExpiresAtUpdatedOnScopeUpdate(boolean)` method.


2.28 (2018-09-25)
-----------------

- `JWEAlg` enum
    * Added `isSymmetric(JWEAlg)` static method.
    * Added `isSymmetric()` instance method.
    * Added `isAsymmetric(JWEAlg)` static method.
    * Added `isAsymmetric()` instance method.

- `ResponseMode` enum
    * Added `withJwt()` method.
    * Added `withoutJwt()` method.

- `Service` class
    * Added `getAuthorizationResponseDuration()` method.
    * Added `setAuthorizationResponseDuration(long)` method.
    * Added `getAuthorizationSignatureKeyId()` method.
    * Added `setAuthorizationSignatureKeyId(String)` method.


2.27 (2018-09-22)
-----------------

- `Client` class
    * Added `getAuthorizationSignAlg()` method.
    * Added `setAuthorizationSignAlg(JWSAlg)` method.
    * Added `getAuthorizationEncryptionAlg()` method.
    * Added `setAuthorizationEncryptionAlg(JWEAlg)` method.
    * Added `getAuthorizationEncryptionEnc()` method.
    * Added `setAuthorizationEncryptionEnc(JWEEnc)` method.

- `ResponseMode` enum
    * Added `JWT`.
    * Added `QUERY_JWT`.
    * Added `FRAGMENT_JWT`.
    * Added `FORM_POST_JWT`.
    * Added `isJwtRequired()` method.
    * Added `isQueryRequired()` method.
    * Added `isFragmentRequired()` method.
    * Added `isFormPostRequired()` method.

- `Service` class
    * Removed `getSupportedIntrospectionAuthSigningAlgorithms()` method which was added in version 2.13.
    * Removed `setSupportedIntrospectionAuthSigningAlgorithms(JWSAlg[])` method which was added in version 2.13.
    * Removed `getSupportedRevocationAuthSigningAlgorithms()` method which was added in version 2.13.
    * Removed `setSupportedRevocationAuthSigningAlgorithms(JWSAlg[])` method which was added in version 2.13.
    * Removed `getSupportedTokenAuthSigningAlgorithms()` method which was added in version 2.26.
    * Removed `setSupportedTokenAuthSigningAlgorithms(JWSAlg[])` method which was added in version 2.26.


2.26 (2018-08-28)
-----------------

- `Service` class
    * Added `getSupportedTokenAuthSigningAlgorithms()` method.
    * Added `setSupportedTokenAuthSigningAlgorithms(JWSAlg[])` method.


2.25 (2018-08-28)
-----------------

- `AuthorizationResponse` class
    * Added `getIdTokenClaims()` method.
    * Added `setIdTokenClaims(String)` method.
    * Added `getUserInfoClaims()` method.
    * Added `setUserInfoClaims(String)` method.

- `ServiceProfile` enum
    * Added `OPEN_BANKING`.


2.24 (2018-08-06)
-----------------

- `Client` class
    * Added `getSoftwareId()` method.
    * Added `setSoftwareId(String)` method.
    * Added `getSoftwareVersion()` method.
    * Added `setSoftwareVersion(String)` method.


2.23 (2018-07-20)
-----------------

- `AuthleteApi` interface
    * Added `verifyJose(JoseVerifyRequest)` method.

- New classes
    * `JoseVerifyRequest`
    * `JoseVerifyResponse`


2.22 (2018-07-03)
-----------------

- `AuthleteApi` interface
    * Added `registerClient(ClientRegistrationRequest)` method.

- `AuthorizationResponse` class
    * Added `getRequestObjectPayload()` method.
    * Added `setRequestObjectPayload(String)` method.

- `ErrorCode` enum
    * Added `invalid_redirect_uri`.
    * Added `invalid_client_metadata`.
    * Added `invalid_software_statement`.
    * Added `unapproved_software_statement`.

- New classes
    * `AccessToken`
    * `ClientRegistrationRequest`
    * `ClientRegistrationResponse`
    * `TokenListResponse`


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
    * Added `setSupportedRevocationAuthMethods(ClientAuthMethod[])` method.
    * Added `getSupportedRevocationAuthSigningAlgorithms()` method.
    * Added `setSupportedRevocationAuthSigningAlgorithms(JWSAlg[])` method.
    * Added `getIntrospectionEndpoint()` method.
    * Added `setIntrospectionEndpoint(URI)` method.
    * Added `getSupportedIntrospectionAuthMethods()` method.
    * Added `setSupportedIntrospectionAuthMethods(ClientAuthMethod[])` method.
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
