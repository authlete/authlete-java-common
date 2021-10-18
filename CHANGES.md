CHANGES
=======

- `IntrospectionRequest` class
    * Renamed `setResource(URI[])` to `setResources(URI[])`.


3.2 (2021-10-18)
----------------

- `GMAction` enum
    * Correct the value of the first argument for `UPDATE`.


3.1 (2021-10-15)
----------------

- `AuthleteApi` interface
    * Added `gm(GMRequest)` method.

- `AuthorizationResponse` class
    * Added `getGmAction()` method.
    * Added `setGmAction(GMAction)` method.
    * Added `getGrant()` method.
    * Added `setGrant(Grant)` method.
    * Added `getGrantId()` method.
    * Added `setGrantId(String)` method.
    * Added `getGrantSubject()` method.
    * Added `setGrantSubject(String)` method.

- `BackchannelAuthenticationCompleteResponse` class
    * Added `getGrantId()` method.
    * Added `setGrantId(String)` method.

- `BackchannelAuthenticationResponse` class
    * Added `getGmAction()` method.
    * Added `setGmAction(GMAction)` method.
    * Added `getGrant()` method.
    * Added `setGrant(Grant)` method.
    * Added `getGrantId()` method.
    * Added `setGrantId(String)` method.
    * Added `getGrantSubject()` method.
    * Added `setGrantSubject(String)` method.

- `DeviceAuthorizationResponse` class
    * Added `getGmAction()` method.
    * Added `setGmAction(GMAction)` method.
    * Added `getGrant()` method.
    * Added `setGrant(Grant)` method.
    * Added `getGrantId()` method.
    * Added `setGrantId(String)` method.
    * Added `getGrantSubject()` method.
    * Added `setGrantSubject(String)` method.

- `ErrorCode` enum
    * Added `invalid_grant_id`.

- `IntrospectionRequest` class
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `IntrospectionResponse` class
    * Added `getGrantId()` method.
    * Added `setGrantId(String)` method.
    * Added `getGrant()` method.
    * Added `setGrant(Grant)` method.

- `Service` class
    * Added `getGrantManagementEndpoint()` method.
    * Added `setGrantManagementEndpoint(URI)` method.
    * Added `isGrantManagementActionRequired()` method.
    * Added `setGrantManagementActionRequired(boolean)` method.

- `TokenResponse` class
    * Added `getGrantId()` method.
    * Added `setGrantId(String)` method.

- New types
    * `GMAction` enum
    * `GMRequest` class
    * `GMResponse` class
    * `Grant` class
    * `GrantDeserializer` class
    * `GrantScope` class
    * `GrantSerializer` class


3.0 (2021-08-25)
----------------

- `AuthleteApi` interface
    * Added `echo(Map<String, String>)` method.


2.99 (2021-08-17)
-----------------

- `AuthorizationIssueRequest` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetails)` method.

- `AuthzDetailsElement` class
    * Added `setOtherFieldsFromMap(Map)` method.

- `TokenCreateRequest` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetails)` method.
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `TokenCreateResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetails)` method.

- `TokenUpdateRequest` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetails)` method.

- `TokenUpdateResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetails)` method.


2.98 (2021-07-20)
-----------------

- `Service` class
    * Added `isRefreshTokenDurationReset()` method.
    * Added `setRefreshTokenDurationReset(boolean)` method.


2.97 (2021-07-09)
-----------------

- `AuthleteApi` interface
    * Added `hskCreate(HskCreateRequest)` method.
    * Added `hskDelete(String)` method.
    * Added `hskGet(String)` method.
    * Added `hskGetList()` method.

- `Service` class
    * Added `isHsmEnabled()` method.
    * Added `setHsmEnabled(boolean)` method.
    * Added `getHsks()` method.
    * Added `setHsks(Hsk[])` method.

- New classes and interfaces
    * `Hsk`
    * `HskCreateRequest`
    * `HskListResponse`
    * `HskResponse`
    * `HSM`


2.96 (2021-06-26)
-----------------

- `Client` class
    * Added `isFrontChannelRequestObjectEncryptionRequired()` method.
    * Added `setFrontChannelRequestObjectEncryptionRequired(boolean)` method.
    * Added `isRequestObjectEncryptionAlgMatchRequired()` method.
    * Added `setRequestObjectEncryptionAlgMatchRequired(boolean)` method.
    * Added `isRequestObjectEncryptionEncMatchRequired()` method.
    * Added `setRequestObjectEncryptionEncMatchRequired(boolean)` method.

- `Service` class
    * Added `isFrontChannelRequestObjectEncryptionRequired()` method.
    * Added `setFrontChannelRequestObjectEncryptionRequired(boolean)` method.
    * Added `isRequestObjectEncryptionAlgMatchRequired()` method.
    * Added `setRequestObjectEncryptionAlgMatchRequired(boolean)` method.
    * Added `isRequestObjectEncryptionEncMatchRequired()` method.
    * Added `setRequestObjectEncryptionEncMatchRequired(boolean)` method.


2.95 (2021-06-22)
-----------------

- `Service` class
    * Added `isTokenExpirationLinked()` method.
    * Added `setTokenExpirationLinked(boolean)` method.


2.94 (2021-06-19)
-----------------

- New classes
    * `FapiUtils`


2.93 (2021-06-18)
-----------------

- `Client` class
    * Added `getCustomMetadata()` method.
    * Added `setCustomMetadata(String)` method.

- `Service` class
    * Added `getSupportedCustomClientMetadata()` method.
    * Added `setSupportedCustomClientMetadata(String[])` method.


2.92 (2021-06-02)
-----------------

- `AuthorizationResponse` class
    * Added `getDynamicScopes()` method.
    * Added `setDynamicScopes(DynamicScope[])` method.

- `BackchannelAuthenticationResponse` class
    * Added `getDynamicScopes()` method.
    * Added `setDynamicScopes(DynamicScope[])` method.

- `DeviceAuthorizationResponse` class
    * Added `getDynamicScopes()` method.
    * Added `setDynamicScopes(DynamicScope[])` method.

- `DeviceVerificationResponse` class
    * Added `getDynamicScopes()` method.
    * Added `setDynamicScopes(DynamicScope[])` method.

- New classes
    * `DynamicScope`


2.91 (2021-05-18)
-----------------

- `AuthzDetailsElement` class
   * Added `getPrivileges()` method.
   * Added `setPrivileges(String[])` method.

- `Client` class
   * Renamed `getAuthorizationDataTypes()` method to `getAuthorizationDetailsTypes()`.
   * Renamed `setAuthorizationDataTypes(String[])` method to `setAuthorizationDetailsTypes(String[])`.

- `Service` class
    * Renamed `getSupportedAuthorizationDataTypes()` method to `getSupportedAuthorizationDetailsTypes()`.
    * Renamed `setSupportedAuthorizationDataTypes(String[])` method to `getSupportedAuthorizationDetailsTypes(String[])`.


2.90 (2021-05-05)
-----------------

- `pom.xml`
    * Removed `javax.annotation:javax.annotation-api` dependency.
    * Removed `javax.xml.bind:jaxb-api` dependency.
    * Removed `org.glassfish.jaxb:jaxb-runtime` dependency.


2.89 (2021-04-15)
-----------------

- `Client` class
    * Renamed `setAttributes(Iterable<Pair>)` method to `loadAttributes(Iterable<Pair>)`.

- `Service` class
    * Renamed `setAttributes(Iterable<Pair>)` method to `loadAttributes(Iterable<Pair>)`.


2.88 (2021-02-21)
-----------------

- `BackchannelAuthenticationCompleteResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.

- `BackchannelAuthenticationResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.

- `DeviceAuthorizationResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.

- `DeviceVerificationResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.

- `IntrospectionResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.

- `RevocationRequest` class
    * Added `getClientCertificate()` method.
    * Added `setClientCertificate(String)` method.
    * Added `getClientCertificatePath()` method.
    * Added `setClientCertificatePath(String[])` method.

- `TokenIssueResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.

- `TokenResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.

- `UserInfoResponse` class
    * Added `getClientAttributes()` method.
    * Added `setClientAttributes(Pair[])` method.
    * Added `getServiceAttributes()` method.
    * Added `setServiceAttribute(Pair[])` method.


2.87 (2021-02-19)
-----------------

- `Client` class
    * Added `getAttributes()` method.
    * Added `setAttributes(Pair[])` method.
    * Added `setAttributes(Iterable<Pair>)` method.

- `Service` class
    * Added `getAttributes()` method.
    * Added `setAttributes(Pair[])` method.
    * Added `setAttributes(Iterable<Pair>)` method.


2.86 (2021-01-23)
-----------------

- `Service` class
    * Added `isIssSuppressed()` method.
    * Added `setIssSuppressed(boolean)` method.
    * Added `isNbfOptional()` method.
    * Added `setNbfOptional(boolean)` method.


2.85 (2020-12-14)
-----------------

- `AuthzDetailsElement` class
    * Added `getDataTypes()` method.
    * Added `setDataTypes(String[])` method.


2.84 (2020-12-01)
-----------------

- `AuthleteApiImpl` class
    * Replaced the call of `HttpURLConnection.disconnect()` with calls of
      `close()` method of underlying streams.


2.83 (2020-11-25)
-----------------

- `StandardIntrospectionRequest` class
    * Added `isWithHiddenProperties()` method.
    * Added `setWithHiddenProperties(boolean)` method.


2.82 (2020-11-02)
-----------------

- `Service` class
    * Renamed `isScopeRequired(boolean)` to `setScopeRequired(boolean)`.


2.81 (2020-11-02)
-----------------

- `AuthleteApi` interface
    * Added `tokenDelete(String)` method.

- `Service` class
    * Added `isClaimShortcutRestrictive()` method.
    * Added `setClaimShortcutRestrictive(boolean)` method.
    * Added `isScopeRequired()` method.
    * Added `setScopeRequired(boolean)` method.


2.80 (2020-09-16)
-----------------

- `Client` class
    * Added `isRequestObjectRequired()` method.
    * Added `setRequestObjectRequired(boolean)` method.

- `Service` class
    * Added `isRequestObjectRequired()` method.
    * Added `setRequestObjectRequired(boolean)` method.
    * Added `isTraditionalRequestObjectProcessingApplied()` method.
    * Added `setTraditionalRequestObjectProcessingApplied(boolean)` method.


2.79 (2020-08-30)
-----------------

- `BackchannelAuthenticationCompleteRequest` class
    * Added `getIdtHeaderParams()` method.
    * Added `setIdtHeaderParams(String)` method.

- `DeviceCompleteRequest` class
    * Added `getIdtHeaderParams()` method.
    * Added `setIdtHeaderParams(String)` method.


2.78 (2020-07-19)
-----------------

- `Service` class
    * Added `isRefreshTokenDurationKept()` method.
    * Added `setRefreshTokenDurationKept(boolean)` method.


2.77 (2020-07-14)
-----------------

- `Client` class
    * Added `isParRequired()` method.
    * Added `setParRequired(boolean)` method.

- `Service` class
    * Added `isParRequired()` method.
    * Added `setParRequired(boolean)` method.


2.76 (2020-06-19)
-----------------

- `AuthorizationIssueRequest` class
    * Added `getIdtHeaderParams()` method.
    * Added `setIdtHeaderParams(String)` method.


2.75 (2020-06-04)
-----------------

- `ConstraintValidator` class
    * Changed the behavior of the `validate(ClaimsConstraint)` method because
      the special rule given to the case of `"claims":null` was removed when
      OpenID Connect for Identity Assurance 1.0 was updated from Implementer's
      Draft 1 to 2.

- `Document` class
    * Renamed `removeDateOfExpir()` to `removeDateOfExpiry()`.

- `JWSAlg` enum
    * Added `ES256K`.
    * Added `EdDSA`.

- `VerifiedClaimsConstraint` class
    * Marked the `isAllClaimsRequested()` method as deprecated because
      the special rule given to the case of `"claims":null` was removed when
      OpenID Connect for Identity Assurance 1.0 was updated from Implementer's
      Draft 1 to 2.


2.74 (2020-05-03)
-----------------

- `ErrorCode` enum
    * Added `invalid_dpop_proof`.


2.73 (2020-04-08)
-----------------

- `AuthleteConfiguration` interface
    * Added `getDpopKey()` method.
    * Added `getClientCertificate()` method.


2.72 (2020-03-20)
-----------------

- `TokenCreateRequest` class
    * Added `getCertificateThumbprint()` method.
    * Added `setCertificateThumbprint(String)` method.
    * Added `getDpopKeyThumbprint()` method.
    * Added `setDpopKeyThumbprint(String)` method.

- `TokenUpdateRequest` class
    * Added `getCertificateThumbprint()` method.
    * Added `setCertificateThumbprint(String)` method.
    * Added `getDpopKeyThumbprint()` method.
    * Added `setDpopKeyThumbprint(String)` method.


2.71 (2020-03-07)
-----------------

- `DpopToken` class
    * Fixed JavaDoc.


2.70 (2020-03-06)
-----------------

- `IntrospectionRequest` class
    * Added `getDpop()` method.
    * Added `setDpop(String)` method.
    * Added `getHtm()` method.
    * Added `setHtm(String)` method.
    * Added `getHtu()` method.
    * Added `setHtu(String)` method.

- `TokenRequest` class
    * Added `getDpop()` method.
    * Added `setDpop(String)` method.
    * Added `getHtm()` method.
    * Added `setHtm(String)` method.
    * Added `getHtu()` method.
    * Added `setHtu(String)` method.

- `UserInfoRequest` class
    * Added `getDpop()` method.
    * Added `setDpop(String)` method.
    * Added `getHtm()` method.
    * Added `setHtm(String)` method.
    * Added `getHtu()` method.
    * Added `setHtu(String)` method.

- New classes
    * `DpopToken`


2.69 (2020-02-13)
-----------------

- `Service` class
    * Added `getEndSessionEndpoint()` method.
    * Added `setEndSessionEndpoint(URI)` method.


2.68 (2020-01-22)
-----------------

- `Service` class
    * Added `isMissingClientIdAllowed()` method.
    * Added `setMissingClientIdAllowed(boolean)` method.


2.67 (2020-01-14)
-----------------

- `Provider` class
    * Added `getFormatted()` method.
    * Added `setFormatted(String)` method.
    * Added `containsFormatted()` method.
    * Added `removeFormatted()` method.
    * Added `getLocality()` method.
    * Added `setLocality(String)` method.
    * Added `containsLocality()` method.
    * Added `removeLocality()` method.
    * Added `getPostalCode()` method.
    * Added `setPostalCode(String)` method.
    * Added `containsPostalCode()` method.
    * Added `removePostalCode()` method.

- `ProviderConstraint` class
    * Added `getFormatted()` method.
    * Added `setFormatted(LeafConstraint)` method.
    * Added `getLocality()` method.
    * Added `setLocality(LeafConstraint)` method.
    * Added `getPostalCode()` method.
    * Added `setPostalCode(LeafConstraint)` method.


2.66 (2019-12-28)
-----------------

- `Helper` class (`com.authlete.common.assurance` package)
    * Updated the datetime regular expression to accept the decimal fraction part.


2.65 (2019-12-23)
-----------------

- `Claims` class
    * Added `putClaim(String, Object)` method.

- `ClaimsConstraint` class
    * Added `putClaim(String, VerifiedClaimConstraint)` method.


2.64 (2019-12-20)
-----------------

- `UserInfoResponse` class
    * Added `getUserInfoClaims()` method.
    * Added `setUserInfoClaims(String)` method.
    * Removed `getVerifiedClaims()` method.
    * Removed `setVerifiedClaims(String)` method.


2.63 (2019-12-20)
-----------------

- `AuthorizationResponse` class
    * Added `getPurpose()` method.
    * Added `setPurpose(String)` method.

- `Service` class
    * Added `getSupportedTrustFrameworks()` method.
    * Added `setSupportedTrustFrameworks(String[])` method.
    * Added `getSupportedEvidence()` method.
    * Added `setSupportedEvidence(String[])` method.
    * Added `getSupportedIdentityDocuments()` method.
    * Added `setSupportedIdentityDocuments(String[])` method.
    * Added `getSupportedVerificationMethods()` method.
    * Added `setSupportedVerificationMethods(String[])` method.
    * Added `getSupportedVerifiedClaims()` method.
    * Added `setSupportedVerifiedClaims(String[])` method.

- `UserInfoResponse` class
    * Added `getVerifiedClaims()` method.
    * Added `setVerifiedClaims(String)` method.

- New packages
    * `com.authlete.common.assurance`
    * `com.authlete.common.assurance.constraint`


2.62 (2019-12-07)
-----------------

- `AuthorizationFailRequest.Reason` enum
    * Added `INVALID_TARGET`.

- `AuthorizationResponse` class
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `BackchannelAuthenticationCompleteResponse` class
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `BackchannelAuthenticationFailRequest.Reason` enum
    * Added `INVALID_TARGET`.

- `BackchannelAuthenticationResponse` class
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `DeviceAuthorizationResponse` class
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `DeviceVerificationResponse` class
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `ErrorCode` enum
    * Added `invalid_target`.

- `IntrospectionResponse` class
    * Added `getAccessTokenResources()` method.
    * Added `setAccessTokenResources(URI[])` method.
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.

- `TokenFailRequest.Reason` enum
    * Added `INVALID_TARGET`.

- `TokenIssueResponse` class
    * Added `getAccessTokenResources()` method.
    * Added `setAccessTokenResources(URI[])` method.

- `TokenResponse` class
    * Added `getAccessTokenResources()` method.
    * Added `setAccessTokenResources(URI[])` method.
    * Added `getResources()` method.
    * Added `setResources(URI[])` method.


2.61 (2019-12-03)
-----------------

- `Client` class
    * Added `getDerivedSectorIdentifier()` method.
    * Added `setDerivedSectorIdentifier(String)` method.
    * Marked `getSectorIdentifier()` method as deprecated.
      The method became an alias of `getSectorIdentifierUri()` method.
    * Marked `setSectorIdentifier(URI)` method as deprecated.
      The method became an alias of `setSectorIdentifierUri(URI)` method.
    * Removed the private field `sectorIdentifier`.


2.60 (2019-11-21)
-----------------

- `ClientExtension` class
    * Changed the type of `accessTokenDuration` from `int` to `long`.
    * Changed the type of `refreshTokenDuration` from `int` to `long`.


2.59 (2019-11-21)
-----------------

- `ClientExtension` class
    * Added `getAccessTokenDuration()` method.
    * Added `setAccessTokenDuration(int)` method.
    * Added `getRefreshTokenDuration()` method.
    * Added `setRefreshTokenDuration(int)` method.


2.58 (2019-11-19)
-----------------

- `DeviceAuthorizationResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetails)` method.


2.57 (2019-11-12)
-----------------

- `AuthorizationResponse` class
    * Change the type of `authorizationDetails` from `AuthzDetailsElement[]` to `AuthzDetails`.

- `AuthzDetailsElement` class
    * Added `fromJson(String)` method.
    * Added `toJson()` method.

- `BackchannelAuthenticationCompleteResponse` class
    * Changed the type of `authorizationDetails` from `AuthzDetailsElement[]` to `AuthzDetails`.

- `BackchannelAuthenticationResponse` class
    * Changed the type of `authorizationDetails` from `AuthzDetailsElement[]` to `AuthzDetails`.

- `DeviceVerificationResponse` class
    * Changed the type of `authorizationDetails` from `AuthzDetailsElement[]` to `AuthzDetails`.

- `IntrospectionResponse` class
    * Changed the type of `authorizationDetails` from `AuthzDetailsElement[]` to `AuthzDetails`.

- `TokenIssueResponse` class
    * Changed the type of `authorizationDetails` from `AuthzDetailsElement[]` to `AuthzDetails`.

- `TokenResponse` class
    * Changed the type of `authorizationDetails` from `AuthzDetailsElement[]` to `AuthzDetails`.

- New classes
    * `AuthzDetails`
    * `AuthzDetailsDeserializer`
    * `AuthzDetailsElementDeserializer`
    * `AuthzDetailsElementSerializer`
    * `AuthzDetailsSerializer`
    * `BaseJsonDeserializer`
    * `BaseJsonSerializer`


2.56 (2019-11-11)
-----------------

- `AuthorizationResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetailsElement[])` method.

- `BackchannelAuthenticationCompleteResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetailsElement[])` method.

- `BackchannelAuthenticationResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetailsElement[])` method.

- `DeviceVerificationResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetailsElement[])` method.

- `ErrorCode` enum
    * Added `invalid_authorization_details`.

- `IntrospectionResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetailsElement[])` method.

- `TokenIssueResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetailsElement[])` method.

- `TokenResponse` class
    * Added `getAuthorizationDetails()` method.
    * Added `setAuthorizationDetails(AuthzDetailsElement[])` method.

- New classes
    * `AuthzDetailsElement`


2.55 (2019-10-31)
-----------------

- `Client` class
    * Added `getAuthorizationDataTypes()` method.
    * Added `setAuthorizationDataTypes(String[])` method.

- `Service` class
    * Added `getSupportedAuthorizationDataTypes()` method.
    * Added `setSupportedAuthorizationDataTypes(String[])` method.


2.54 (2019-10-08)
-----------------

- Just releasing again without any changes.


2.53 (2019-10-08)
-----------------

- Just releasing again without any changes.


2.52 (2019-10-08)
-----------------

- `Service` class
    * Added `getPushedAuthReqEndpoint()` method.
    * Added `setPushedAuthReqEndpoint(URI)` method.
    * Removed `getRequestObjectEndpoint()` method.
    * Removed `setRequestObjectEndpoint(URI)` method.


2.51 (2019-10-05)
-----------------

- `AuthleteApi` interface
    * Added `deleteClient(String)` method.
    * Added `getClient(String)` method.
    * Renamed `registerRequestObject(RequestObjectRequest)` to
      `pushAuthorizationRequest(PushedAuthReqRequest)`.

- `Service` class
    * Added `getPushedAuthReqDuration()` method.
    * Added `setPushedAuthReqDuration(long)` method.

- New classes
    * `PushedAuthReqRequest` (renamed from `RequestObjectRequest`)
    * `PushedAuthReqResponse` (renamed from `RequestObjectResponse`)


2.50 (2019-08-24)
-----------------

- `AuthleteApi` interface
    * Added `registerRequestObject(RequestObjectRequest)` method.

- `BackchannelAuthenticationResponse` class
    * `getClientAuthMethod()` method.
    * `setClientAuthMethod(ClientAuthMethod)` method.

- `Client` class
    * Added `getSectorIdentifierUri()` method.
    * Added `setSectorIdentifierUri(URI)` method.

- `DeviceAuthorizationResponse` class
    * `getClientAuthMethod()` method.
    * `setClientAuthMethod(ClientAuthMethod)` method.

- `TokenResponse` class
    * Added `getClientAuthMethod()` method.
    * Added `setClientAuthMethod(ClientAuthMethod)` method.

- New classes
    * `RequestObjectRequest`
    * `RequestObjectResponse`


2.49 (2019-07-10)
-----------------

- `Service` class
    * Added `getMtlsEndpointAliases()` method.
    * Added `setMtlsEndpointAliases(NamedUri[])` method.

- New class
    * `NamedUri`


2.48 (2019-07-03)
-----------------

- `Service` class
    * Added `isBackchannelBindingMessageRequiredInFapi()` method.
    * Added `setBackchannelBindingMessageRequiredInFapi(boolean)` method.


2.47 (2019-07-03)
-----------------

- `ClientAuthMethod` enum
    * Added `isSecretBased()` method.
    * Added `isJwtBased()` method.
    * Added `isCertificateBased()` method.


2.46 (2019-07-03)
-----------------

- `Service` class
    * Added `isPkceS256Required()` method.
    * Added `setPkceS256Required(boolean)` method.
    * Added `getRequestObjectEndpoint()` method.
    * Added `setRequestObjectEndpoint(URI)` method.


2.45 (2019-06-27)
-----------------

- `BackchannelAuthenticationResponse` class
    * Added `getRequestContext()` method.
    * Added `setRequestContext(String)` method.


2.44 (2019-06-26)
-----------------

- `DeviceAuthorizationResponse` class
    * Added `getAcrs()` method.
    * Added `setAcrs(String[])` method.
    * Added `getClaimNames()` method.
    * Added `setClaimNames(String[])` method.

- `DeviceCompleteRequest` class
    * Added `getAcr()` method.
    * Added `setAcr(String)` method.
    * Added `getAuthTime()` method.
    * Added `setAuthTime(long)` method.
    * Added `getClaims()` method.
    * Added `setClaims(String)` method.
    * Added `setClaims(Map)` method.
    * Added `getSub()` method.
    * Added `setSub(String)` method.

- `DeviceVerificationResponse` class
    * Added `getAcrs()` method.
    * Added `setAcrs(String[])` method.
    * Added `getClaimNames()` method.
    * Added `setClaimNames(String[])` method.
    * Added `getExpiresAt()` method.
    * Added `setExpiresAt(long)` method.


2.43 (2019-06-17)
-----------------

- `Service` class
    * Added `getUserCodeCharset()` method.
    * Added `setUserCodeCharset(UserCodeCharset)` method.
    * Added `getUserCodeLength()` method.
    * Added `setUserCodeLength(int)` method.

- New enum
    * `UserCodeCharset`

- New class
    * `UserCodeGenerator`


2.42 (2019-06-16)
-----------------

- `AuthleteApi` interface
    * Added `deviceAuthorization(DeviceAuthorizationRequest)` method.
    * Added `deviceComplete(DeviceCompleteRequest)` method.
    * Added `deviceVerification(DeviceVerificationRequest)` method.

- `GrantType` enum
    * Added `DEVICE_CODE`.

- `Service` class
    * Added `getDeviceAuthorizationEndpoint()` method.
    * Added `setDeviceAuthorizationEndpoint(URI)` method.
    * Added `getDeviceFlowCodeDuration()` method.
    * Added `setDeviceFlowCodeDuration(int)` method.
    * Added `getDeviceFlowPollingInterval()` method.
    * Added `setDeviceFlowPollingInterval(int)` method.
    * Added `getDeviceVerificationUri()` method.
    * Added `setDeviceVerificationUri(URI)` method.
    * Added `getDeviceVerificationUriComplete()` method.
    * Added `setDeviceVerificationUriComplete(URI)` method.

- New classes
    * `DeviceAuthorizationRequest`
    * `DeviceAuthorizationResponse`
    * `DeviceCompleteRequest`
    * `DeviceCompleteResponse`
    * `DeviceVerificationRequest`
    * `DeviceVerificationResponse`


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
