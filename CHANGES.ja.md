変更点
======

2.99 (2021 年 08 月 17 日)
--------------------------

- `AuthorizationIssueRequest` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetails)` メソッドを追加。

- `AuthzDetailsElement` クラス
    * `setOtherFieldsFromMap(Map)` メソッドを追加。

- `TokenCreateRequest` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetails)` メソッドを追加。
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。

- `TokenCreateResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetails)` メソッドを追加。

- `TokenUpdateRequest` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetails)` メソッドを追加。

- `TokenUpdateResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetails)` メソッドを追加。


2.98 (2021 年 07 月 20 日)
--------------------------

- `Service` クラス
    * `isRefreshTokenDurationReset()` メソッドを追加。
    * `setRefreshTokenDurationReset(boolean)` メソッドを追加。


2.97 (2021 年 07 月 09 日)
--------------------------

- `AuthleteApi` インターフェース
    * `hskCreate(HskCreateRequest)` メソッドを追加。
    * `hskDelete(String)` メソッドを追加。
    * `hskGet(String)` メソッドを追加。
    * `hskGetList()` メソッドを追加。

- `Service` クラス
    * `isHsmEnabled()` メソッドを追加。
    * `setHsmEnabled(boolean)` メソッドを追加。
    * `getHsks()` メソッドを追加。
    * `setHsks(Hsk[])` メソッドを追加。

- 新しいクラスとインターフェース
    * `Hsk`
    * `HskCreateRequest`
    * `HskListResponse`
    * `HskResponse`
    * `HSM`


2.96 (2021 年 06 月 26 日)
--------------------------

- `Client` クラス
    * `isFrontChannelRequestObjectEncryptionRequired()` メソッドを追加。
    * `setFrontChannelRequestObjectEncryptionRequired(boolean)` メソッドを追加。
    * `isRequestObjectEncryptionAlgMatchRequired()` メソッドを追加。
    * `setRequestObjectEncryptionAlgMatchRequired(boolean)` メソッドを追加。
    * `isRequestObjectEncryptionEncMatchRequired()` メソッドを追加。
    * `setRequestObjectEncryptionEncMatchRequired(boolean)` メソッドを追加。

- `Service` クラス
    * `isFrontChannelRequestObjectEncryptionRequired()` メソッドを追加。
    * `setFrontChannelRequestObjectEncryptionRequired(boolean)` メソッドを追加。
    * `isRequestObjectEncryptionAlgMatchRequired()` メソッドを追加。
    * `setRequestObjectEncryptionAlgMatchRequired(boolean)` メソッドを追加。
    * `isRequestObjectEncryptionEncMatchRequired()` メソッドを追加。
    * `setRequestObjectEncryptionEncMatchRequired(boolean)` メソッドを追加。


2.95 (2021 年 06 月 22 日)
--------------------------

- `Service` クラス
    * `isTokenExpirationLinked()` メソッドを追加。
    * `setTokenExpirationLinked(boolean)` メソッドを追加。


2.94 (2021 年 06 月 19 日)
--------------------------

- 新しいクラス
    * `FapiUtils`


2.93 (2021 年 06 月 18 日)
--------------------------

- `Client` クラス
    * `getCustomMetadata()` メソッドを追加。
    * `setCustomMetadata(String)` メソッドを追加。

- `Service` クラス
    * `getSupportedCustomClientMetadata()` メソッドを追加。
    * `setSupportedCustomClientMetadata(String[])` メソッドを追加。


2.92 (2021 年 06 月 02 日)
--------------------------

- `AuthorizationResponse` クラス
    * `getDynamicScopes()` メソッドを追加。
    * `setDynamicScopes(DynamicScope[])` メソッドを追加。

- `BackchannelAuthenticationResponse` クラス
    * `getDynamicScopes()` メソッドを追加。
    * `setDynamicScopes(DynamicScope[])` メソッドを追加。

- `DeviceAuthorizationResponse` クラス
    * `getDynamicScopes()` メソッドを追加。
    * `setDynamicScopes(DynamicScope[])` メソッドを追加。

- `DeviceVerificationResponse` クラス
    * `getDynamicScopes()` メソッドを追加。
    * `setDynamicScopes(DynamicScope[])` メソッドを追加。

- 新しいクラス
    * `DynamicScope`


2.91 (2021 年 05 月 18 日)
--------------------------

- `AuthzDetailsElement` クラス
   * `getPrivileges()` メソッドを追加。
   * `setPrivileges(String[])` メソッドを追加。

- `Client` クラス
   * `getAuthorizationDataTypes()` メソッドを `getAuthorizationDetailsTypes()` へ名称変更。
   * `setAuthorizationDataTypes(String[])` メソッドを `setAuthorizationDetailsTypes(String[])` へ名称変更。

- `Service` クラス
    * `getSupportedAuthorizationDataTypes()` メソッドを `getSupportedAuthorizationDetailsTypes()` へ名称変更。
    * `setSupportedAuthorizationDataTypes(String[])` メソッドを `getSupportedAuthorizationDetailsTypes(String[])` へ名称変更。


2.90 (2021 年 05 月 05 日)
--------------------------

- `pom.xml`
    * `javax.annotation:javax.annotation-api` 依存を削除。
    * `javax.xml.bind:jaxb-api` 依存を削除。
    * `org.glassfish.jaxb:jaxb-runtime` 依存を削除。


2.89 (2021 年 04 月 15 日)
--------------------------

- `Client` クラス
    * `setAttributes(Iterable<Pair>)` メソッドを `loadAttributes(Iterable<Pair>)` へ名称変更。

- `Service` クラス
    * `setAttributes(Iterable<Pair>)` メソッドを `loadAttributes(Iterable<Pair>)` へ名称変更。


2.88 (2021 年 02 月 21 日)
--------------------------

- `BackchannelAuthenticationCompleteResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。

- `BackchannelAuthenticationResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。

- `DeviceAuthorizationResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。

- `DeviceVerificationResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。

- `IntrospectionResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。

- `RevocationRequest` クラス
    * `getClientCertificate()` メソッドを追加。
    * `setClientCertificate(String)` メソッドを追加。
    * `getClientCertificatePath()` メソッドを追加。
    * `setClientCertificatePath(String[])` メソッドを追加。

- `TokenIssueResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。

- `TokenResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。

- `UserInfoResponse` クラス
    * `getClientAttributes()` メソッドを追加。
    * `setClientAttributes(Pair[])` メソッドを追加。
    * `getServiceAttributes()` メソッドを追加。
    * `setServiceAttributes(Pair[])` メソッドを追加。


2.87 (2021 年 02 月 19 日)
--------------------------

- `Client` クラス
    * `getAttributes()` メソッドを追加。
    * `setAttributes(Pair[])` メソッドを追加。
    * `setAttributes(Iterable<Pair>)` メソッドを追加。

- `Service` クラス
    * `getAttributes()` メソッドを追加。
    * `setAttributes(Pair[])` メソッドを追加。
    * `setAttributes(Iterable<Pair>)` メソッドを追加。


2.86 (2021 年 01 月 23 日)
--------------------------

- `Service` クラス
    * `isIssSuppressed()` メソッドを追加。
    * `setIssSuppressed(boolean)` メソッドを追加。
    * `isNbfOptional()` メソッドを追加。
    * `setNbfOptional(boolean)` メソッドを追加。


2.85 (2020 年 12 月 14 日)
--------------------------

- `AuthzDetailsElement` クラス
    * `getDataTypes()` メソッドを追加。
    * `setDataTypes(String[])` メソッドを追加。


2.84 (2020 年 12 月 01 日)
--------------------------

- `AuthleteApiImpl` クラス
    * `HttpURLConnection.disconnect()` メソッドコールを、ストリーム群の `close()`
      メソッドコールに置き換え。


2.83 (2020 年 11 月 25 日)
--------------------------

- `StandardIntrospectionRequest` クラス
    * `isWithHiddenProperties()` メソッドを追加。
    * `setWithHiddenProperties(boolean)` メソッドを追加。


2.82 (2020 年 11 月 02 日)
--------------------------

- `Service` クラス
    * `isScopeRequired(boolean)` を `setScopeRequired(boolean)` に名称変更。


2.81 (2020 年 11 月 02 日)
--------------------------

- `AuthleteApi` インターフェース
    * `tokenDelete(String)` メソッドを追加。

- `Service` クラス
    * `isClaimShortcutRestrictive()` メソッドを追加。
    * `setClaimShortcutRestrictive(boolean)` メソッドを追加。
    * `isScopeRequired()` メソッドを追加。
    * `setScopeRequired(boolean)` メソッドを追加。


2.80 (2020 年 09 月 16 日)
--------------------------

- `Client` クラス
    * `isRequestObjectRequired()` メソッドを追加。
    * `setRequestObjectRequired(boolean)` メソッドを追加。

- `Service` クラス
    * `isRequestObjectRequired()` メソッドを追加。
    * `setRequestObjectRequired(boolean)` メソッドを追加。
    * `isTraditionalRequestObjectProcessingApplied()` メソッドを追加。
    * `setTraditionalRequestObjectProcessingApplied(boolean)` メソッドを追加。


2.79 (2020 年 08 月 30 日)
--------------------------

- `BackchannelAuthenticationCompleteRequest` クラス
    * `getIdtHeaderParams()` メソッドを追加。
    * `setIdtHeaderParams(String)` メソッドを追加。

- `DeviceCompleteRequest` クラス
    * `getIdtHeaderParams()` メソッドを追加。
    * `setIdtHeaderParams(String)` メソッドを追加。


2.78 (2020 年 07 月 19 日)
--------------------------

- `Service` クラス
    * `isRefreshTokenDurationKept()` メソッドを追加。
    * `setRefreshTokenDurationKept(boolean)` メソッドを追加。


2.77 (2020 年 07 月 14 日)
--------------------------

- `Client` クラス
    * `isParRequired()` メソッドを追加。
    * `setParRequired(boolean)` メソッドを追加。

- `Service` クラス
    * `isParRequired()` メソッドを追加。
    * `setParRequired(boolean)` メソッドを追加。


2.76 (2020 年 06 月 19 日)
--------------------------

- `AuthorizationIssueRequest` クラス
    * `getIdtHeaderParams()` メソッドを追加。
    * `setIdtHeaderParams(String)` メソッドを追加。


2.75 (2020 年 06 月 04 日)
--------------------------

- `ConstraintValidator` クラス
    * `validate(ClaimsConstraint)` メソッドの動作を変更。OpenID Connect for
      Identity Assurance 1.0 が Implementer's Draft 1 から 2 へと更新される際、
      `"claims":null` に与えられていた特別なルールが削除されたため。

- `Document` クラス
    * `removeDateOfExpir()` を `removeDateOfExpiry()` に名称変更。

- `JWSAlg` 列挙型
    * `ES256K` を追加。
    * `EdDSA` を追加。

- `VerifiedClaimsConstraint` クラス
    * `isAllClaimsRequested()` メソッドを deprecated 化。OpenID Connect for
      Identity Assurance 1.0 が Implementer's Draft 1 から 2 へと更新される際、
      `"claims":null` に与えられていた特別なルールが削除されたため。


2.74 (2020 年 05 月 03 日)
--------------------------

- `ErrorCode` 列挙型
    * `invalid_dpop_proof` を追加。


2.73 (2020 年 04 月 08 日)
--------------------------

- `AuthleteConfiguration` インターフェース
    * `getDpopKey()` メソッドを追加。
    * `getClientCertificate()` メソッドを追加。


2.72 (2020 年 03 月 20 日)
--------------------------

- `TokenCreateRequest` クラス
    * `getCertificateThumbprint()` メソッドを追加。
    * `setCertificateThumbprint(String)` メソッドを追加。
    * `getDpopKeyThumbprint()` メソッドを追加。
    * `setDpopKeyThumbprint(String)` メソッドを追加。

- `TokenUpdateRequest` クラス
    * `getCertificateThumbprint()` メソッドを追加。
    * `setCertificateThumbprint(String)` メソッドを追加。
    * `getDpopKeyThumbprint()` メソッドを追加。
    * `setDpopKeyThumbprint(String)` メソッドを追加。


2.71 (2020 年 03 月 07 日)
--------------------------

- `DpopToken` クラス
    * JavaDoc 修正。


2.70 (2020 年 03 月 06 日)
--------------------------

- `IntrospectionRequest` クラス
    * `getDpop()` メソッドを追加。
    * `setDpop(String)` メソッドを追加。
    * `getHtm()` メソッドを追加。
    * `setHtm(String)` メソッドを追加。
    * `getHtu()` メソッドを追加。
    * `setHtu(String)` メソッドを追加。

- `TokenRequest` クラス
    * `getDpop()` メソッドを追加。
    * `setDpop(String)` メソッドを追加。
    * `getHtm()` メソッドを追加。
    * `setHtm(String)` メソッドを追加。
    * `getHtu()` メソッドを追加。
    * `setHtu(String)` メソッドを追加。

- `UserInfoRequest` クラス
    * `getDpop()` メソッドを追加。
    * `setDpop(String)` メソッドを追加。
    * `getHtm()` メソッドを追加。
    * `setHtm(String)` メソッドを追加。
    * `getHtu()` メソッドを追加。
    * `setHtu(String)` メソッドを追加。

- 新しいクラス
    * `DpopToken`


2.69 (2020 年 02 月 13 日)
--------------------------

- `Service` クラス
    * `getEndSessionEndpoint()` メソッドを追加。
    * `setEndSessionEndpoint(URI)` メソッドを追加。


2.68 (2020 年 01 月 22 日)
--------------------------

- `Service` クラス
    * `isMissingClientIdAllowed()` メソッドを追加。
    * `setMissingClientIdAllowed(boolean)` メソッドを追加。


2.67 (2020 年 01 月 14 日)
--------------------------

- `Provider` クラス
    * `getFormatted()` メソッドを追加。
    * `setFormatted(String)` メソッドを追加。
    * `containsFormatted()` メソッドを追加。
    * `removeFormatted()` メソッドを追加。
    * `getLocality()` メソッドを追加。
    * `setLocality(String)` メソッドを追加。
    * `containsLocality()` メソッドを追加。
    * `removeLocality()` メソッドを追加。
    * `getPostalCode()` メソッドを追加。
    * `setPostalCode(String)` メソッドを追加。
    * `containsPostalCode()` メソッドを追加。
    * `removePostalCode()` メソッドを追加。

- `ProviderConstraint` クラス
    * `getFormatted()` メソッドを追加。
    * `setFormatted(LeafConstraint)` メソッドを追加。
    * `getLocality()` メソッドを追加。
    * `setLocality(LeafConstraint)` メソッドを追加。
    * `getPostalCode()` メソッドを追加。
    * `setPostalCode(LeafConstraint)` メソッドを追加。


2.66 (2019 年 12 月 28 日)
--------------------------

- `Helper` クラス (`com.authlete.common.assurance` パッケージ)
    * 小数部を受け付けるように日時の正規表現を更新。


2.65 (2019 年 12 月 23 日)
--------------------------

- `Claims` クラス
    * `putClaim(String, Object)` メソッドを追加。

- `ClaimsConstraint` クラス
    * `putClaim(String, VerifiedClaimConstraint)` メソッドを追加。


2.64 (2019 年 12 月 20 日)
--------------------------

- `UserInfoResponse` クラス
    * `getUserInfoClaims()` メソッドを追加。
    * `setUserInfoClaims(String)` メソッドを追加。
    * `getVerifiedClaims()` メソッドを削除。
    * `setVerifiedClaims(String)` メソッドを削除。


2.63 (2019 年 12 月 20 日)
--------------------------

- `AuthorizationResponse` クラス
    * `getPurpose()` メソッドを追加。
    * `setPurpose(String)` メソッドを追加。

- `Service` クラス
    * `getSupportedTrustFrameworks()` メソッドを追加。
    * `setSupportedTrustFrameworks(String[])` メソッドを追加。
    * `getSupportedEvidence()` メソッドを追加。
    * `setSupportedEvidence(String[])` メソッドを追加。
    * `getSupportedIdentityDocuments()` メソッドを追加。
    * `setSupportedIdentityDocuments(String[])` メソッドを追加。
    * `getSupportedVerificationMethods()` メソッドを追加。
    * `setSupportedVerificationMethods(String[])` メソッドを追加。
    * `getSupportedVerifiedClaims()` メソッドを追加。
    * `setSupportedVerifiedClaims(String[])` メソッドを追加。

- `UserInfoResponse` クラス
    * `getVerifiedClaims()` メソッドを追加。
    * `setVerifiedClaims(String)` メソッドを追加。

- 新しいパッケージ
    * `com.authlete.common.assurance`
    * `com.authlete.common.assurance.constraint`


2.62 (2019 年 12 月 07 日)
--------------------------

- `AuthorizationFailRequest.Reason` 列挙型
    * `INVALID_TARGET` を追加。

- `AuthorizationResponse` クラス
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。

- `BackchannelAuthenticationCompleteResponse` クラス
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。

- `BackchannelAuthenticationFailRequest.Reason` 列挙型
    * `INVALID_TARGET` を追加。

- `BackchannelAuthenticationResponse` クラス
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。

- `DeviceAuthorizationResponse` クラス
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。

- `DeviceVerificationResponse` クラス
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。

- `ErrorCode` 列挙型
    * `invalid_target` を追加。

- `IntrospectionResponse` クラス
    * `getAccessTokenResources()` メソッドを追加。
    * `setAccessTokenResources(URI[])` メソッドを追加。
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。

- `TokenFailRequest.Reason` 列挙型
    * `INVALID_TARGET` を追加。

- `TokeIssuenResponse` クラス
    * `getAccessTokenResources()` メソッドを追加。
    * `setAccessTokenResources(URI[])` メソッドを追加。

- `TokenResponse` クラス
    * `getAccessTokenResources()` メソッドを追加。
    * `setAccessTokenResources(URI[])` メソッドを追加。
    * `getResources()` メソッドを追加。
    * `setResources(URI[])` メソッドを追加。


2.61 (2019 年 12 月 03 日)
--------------------------

- `Client` クラス
    * `getDerivedSectorIdentifier()` メソッドを追加。
    * `setDerivedSectorIdentifier(String)` メソッドを追加。
    * `getSectorIdentifier()` メソッドを非推奨 (deprecated) に変更。
      当メソッドを `getSectorIdentifierUri()` メソッドの別名に変更。
    * `setSectorIdentifier(URI)` メソッドを非推奨 (deprecated) に変更。
      当メソッドを `setSectorIdentifierUri(URI)` メソッドの別名に変更。
    * プライベートフィールド `sectorIdentifier` を削除。


2.60 (2019 年 11 月 21 日)
--------------------------

- `ClientExtension` クラス
    * `accessTokenDuration` の型を `int` から `long` に変更。
    * `refreshTokenDuration` の型を `int` から `long` に変更。


2.59 (2019 年 11 月 21 日)
--------------------------

- `ClientExtension` クラス
    * `getAccessTokenDuration()` メソッドを追加。
    * `setAccessTokenDuration(int)` メソッドを追加。
    * `getRefreshTokenDuration()` メソッドを追加。
    * `setRefreshTokenDuration(int)` メソッドを追加。


2.58 (2019 年 11 月 19 日)
--------------------------

- `DeviceAuthorizationResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetails)` メソッドを追加。


2.57 (2019 年 11 月 12 日)
--------------------------

- `AuthorizationResponse` クラス
    * `authorizationDetails` の型を `AuthzDetailsElement[]` から `AuthzDetails` へ変更。

- `AuthzDetailsElement` クラス
    * `fromJson(String)` メソッドを追加。
    * `toJson()` メソッドを追加。

- `BackchannelAuthenticationCompleteResponse` クラス
    * `authorizationDetails` の型を `AuthzDetailsElement[]` から `AuthzDetails` へ変更。

- `BackchannelAuthenticationResponse` クラス
    * `authorizationDetails` の型を `AuthzDetailsElement[]` から `AuthzDetails` へ変更。

- `DeviceVerificationResponse` クラス
    * `authorizationDetails` の型を `AuthzDetailsElement[]` から `AuthzDetails` へ変更。

- `IntrospectionResponse` クラス
    * `authorizationDetails` の型を `AuthzDetailsElement[]` から `AuthzDetails` へ変更。

- `TokenIssueResponse` クラス
    * `authorizationDetails` の型を `AuthzDetailsElement[]` から `AuthzDetails` へ変更。

- `TokenResponse` クラス
    * `authorizationDetails` の型を `AuthzDetailsElement[]` から `AuthzDetails` へ変更。

- 新しいクラス
    * `AuthzDetails`
    * `AuthzDetailsDeserializer`
    * `AuthzDetailsElementDeserializer`
    * `AuthzDetailsElementSerializer`
    * `AuthzDetailsSerializer`
    * `BaseJsonDeserializer`
    * `BaseJsonSerializer`


2.56 (2019 年 11 月 11 日)
--------------------------

- `AuthorizationResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetailsElement[])` メソッドを追加。

- `BackchannelAuthenticationCompleteResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetailsElement[])` メソッドを追加。

- `BackchannelAuthenticationResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetailsElement[])` メソッドを追加。

- `DeviceVerificationResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetailsElement[])` メソッドを追加。

- `ErrorCode` 列挙型
    * `invalid_authorization_details` を追加。

- `IntrospectionResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetailsElement[])` メソッドを追加。

- `TokenIssueResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetailsElement[])` メソッドを追加。

- `TokenResponse` クラス
    * `getAuthorizationDetails()` メソッドを追加。
    * `setAuthorizationDetails(AuthzDetailsElement[])` メソッドを追加。

- 新しいクラス
    * `AuthzDetailsElement`


2.55 (2019 年 10 月 31 日)
--------------------------

- `Client` クラス
    * `getAuthorizationDataTypes()` メソッドを追加。
    * `setAuthorizationDataTypes(String[])` メソッドを追加。

- `Service` クラス
    * `getSupportedAuthorizationDataTypes()` メソッドを追加。
    * `setSupportedAuthorizationDataTypes(String[])` メソッドを追加。


2.54 (2019 年 10 月 08 日)
--------------------------

- 変更無しで再度リリース


2.53 (2019 年 10 月 08 日)
--------------------------

- 変更無しで再度リリース


2.52 (2019 年 10 月 08 日)
--------------------------

- `Service` クラス
    * `getPushedAuthReqEndpoint()` メソッドを追加。
    * `setPushedAuthReqEndpoint(URI)` メソッドを追加。
    * `getRequestObjectEndpoint()` メソッドを削除。
    * `setRequestObjectEndpoint(URI)` メソッドを削除。


2.51 (2019 年 10 月 05 日)
--------------------------

- `AuthleteApi` インターフェース
    * `deleteClient(String)` メソッドを追加。
    * `getClient(String)` メソッドを追加。
    * `registerRequestObject(RequestObjectRequest)` メソッドの名前を
      `pushAuthorizationRequest(PushedAuthReqRequest)` に変更。

- `Service` クラス
    * `getPushedAuthReqDuration()` メソッドを追加。
    * `setPushedAuthReqDuration(long)` メソッドを追加。

- 新しいクラス
    * `PushedAuthReqRequest` (`RequestObjectRequest` から名称変更)
    * `PushedAuthReqResponse` (`RequestObjectResponse` から名称変更)


2.50 (2019 年 08 月 24 日)
--------------------------

- `AuthleteApi` インターフェース
    * `registerRequestObject(RequestObjectRequest)` メソッドを追加。

- `BackchannelAuthenticationResponse` クラス
    * `getClientAuthMethod()` メソッドを追加。
    * `setClientAuthMethod(ClientAuthMethod)` メソッドを追加。

- `Client` クラス
    * `getSectorIdentifierUri()` メソッドを追加。
    * `setSectorIdentifierUri(URI)` メソッドを追加。

- `DeviceAuthorizationResponse` クラス
    * `getClientAuthMethod()` メソッドを追加。
    * `setClientAuthMethod(ClientAuthMethod)` メソッドを追加。

- `TokenResponse` クラス
    * `getClientAuthMethod()` メソッドを追加。
    * `setClientAuthMethod(ClientAuthMethod)` メソッドを追加。

- 新しいクラス
    * `RequestObjectRequest`
    * `RequestObjectResponse`


2.49 (2019 年 07 月 10 日)
--------------------------

- `Service` クラス
    * `getMtlsEndpointAliases()` メソッドを追加。
    * `setMtlsEndpointAliases(NamedUri[])` メソッドを追加。

- 新しいクラス
    * `NamedUri`


2.48 (2019 年 07 月 03 日)
--------------------------

- `Service` クラス
    * `isBackchannelBindingMessageRequiredInFapi()` メソッドを追加。
    * `setBackchannelBindingMessageRequiredInFapi(boolean)` メソッドを追加。


2.47 (2019 年 07 月 03 日)
--------------------------

- `ClientAuthMethod` 列挙型
    * `isSecretBased()` メソッドを追加。
    * `isJwtBased()` メソッドを追加。
    * `isCertificateBased()` メソッドを追加。


2.46 (2019 年 07 月 03 日)
--------------------------

- `Service` クラス
    * `isPkceS256Required()` メソッドを追加。
    * `setPkceS256Required(boolean)` メソッドを追加。
    * `getRequestObjectEndpoint()` メソッドを追加。
    * `setRequestObjectEndpoint(URI)` メソッドを追加。


2.45 (2019 年 06 月 27 日)
--------------------------

- `BackchannelAuthenticationResponse` クラス
    * `getRequestContext()` メソッドを追加。
    * `setRequestContext(String)` メソッドを追加。


2.44 (2019 年 06 月 26 日)
--------------------------

- `DeviceAuthorizationResponse` クラス
    * `getAcrs()` メソッドを追加。
    * `setAcrs(String[])` メソッドを追加。
    * `getClaimNames()` メソッドを追加。
    * `setClaimNames(String[])` メソッドを追加。

- `DeviceCompleteRequest` クラス
    * `getAcr()` メソッドを追加。
    * `setAcr(String)` メソッドを追加。
    * `getAuthTime()` メソッドを追加。
    * `setAuthTime(long)` メソッドを追加。
    * `getClaims()` メソッドを追加。
    * `setClaims(String)` メソッドを追加。
    * `setClaims(Map)` メソッドを追加。
    * `getSub()` メソッドを追加。
    * `setSub(String)` メソッドを追加。

- `DeviceVerificationResponse` クラス
    * `getAcrs()` メソッドを追加。
    * `setAcrs(String[])` メソッドを追加。
    * `getClaimNames()` メソッドを追加。
    * `setClaimNames(String[])` メソッドを追加。
    * `getExpiresAt()` メソッドを追加。
    * `setExpiresAt(long)` メソッドを追加。


2.43 (2019 年 06 月 17 日)
--------------------------

- `Service` クラス
    * `getUserCodeCharset()` メソッドを追加。
    * `setUserCodeCharset(UserCodeCharset)` メソッドを追加。
    * `getUserCodeLength()` メソッドを追加。
    * `setUserCodeLength(int)` メソッドを追加。

- 新しい列挙型
    * `UserCodeCharset`

- 新しいクラス
    * `UserCodeGenerator`


2.42 (2019 年 06 月 16 日)
--------------------------

- `AuthleteApi` インターフェース
    * `deviceAuthorization(DeviceAuthorizationRequest)` メソッドを追加。
    * `deviceComplete(DeviceCompleteRequest)` メソッドを追加。
    * `deviceVerification(DeviceVerificationRequest)` メソッドを追加。

- `GrantType` 列挙型
    * `DEVICE_CODE` を追加。

- `Service` クラス
    * `getDeviceAuthorizationEndpoint()` メソッドを追加。
    * `setDeviceAuthorizationEndpoint(URI)` メソッドを追加。
    * `getDeviceFlowCodeDuration()` メソッドを追加。
    * `setDeviceFlowCodeDuration(int)` メソッドを追加。
    * `getDeviceFlowPollingInterval()` メソッドを追加。
    * `setDeviceFlowPollingInterval(int)` メソッドを追加。
    * `getDeviceVerificationUri()` メソッドを追加。
    * `setDeviceVerificationUri(URI)` メソッドを追加。
    * `getDeviceVerificationUriComplete()` メソッドを追加。
    * `setDeviceVerificationUriComplete(URI)` メソッドを追加。

- 新しいクラス
    * `DeviceAuthorizationRequest`
    * `DeviceAuthorizationResponse`
    * `DeviceCompleteRequest`
    * `DeviceCompleteResponse`
    * `DeviceVerificationRequest`
    * `DeviceVerificationResponse`


2.41 (2019 年 05 月 30 日)
--------------------------

- `AuthleteApi` インターフェース
    * `dynamicClientDelete(ClientRegistrationRequest)` メソッドを追加。
    * `dynamicClientGet(ClientRegistrationRequest)` メソッドを追加。
    * `dynamicClientRegister(ClientRegistrationRequest)` メソッドを追加。
    * `dynamicClientUpdate(ClientRegistrationRequest)` メソッドを追加。
    * `registerClient(ClientRegistrationRequest)` メソッドを削除。


2.40 (2019 年 05 月 24 日)
--------------------------

- `ErrorCode` 列挙型
    * `invalid_binding_message` を追加。


2.39 (2019 年 05 月 20 日)
--------------------------

- `Client` クラス
    * `isDynamicallyRegistered` メソッドを追加。
    * `setDynamicallyRegistered(boolean)` メソッドを追加。
    * `getRegistrationAccessTokenHash` メソッドを追加。
    * `setRegistrationAccessTokenHash(String)` メソッドを追加。

- `ClientRegistrationRequest` クラス
    * Removed `getMetadata()` メソッドを追加。
    * Removed `setMetadata(String)` メソッドを追加。
    * Added `getJson()` メソッドを追加。
    * Added `setJson(String)` メソッドを追加。
    * Added `getToken()` メソッドを追加。
    * Added `setToken(String)` メソッドを追加。
    * Added `getClientId` メソッドを追加。
    * Added `setClientId(String)` メソッドを追加。

- `ClientRegistrationResponse.Action` 列挙型
    * `UPDATED` を追加。
    * `DELETED` を追加。
    * `OK` を追加。

- `Service` クラス
    * `getRegistrationManagementEndpoint()` メソッドを追加。
    * `setRegistrationManagementEndpoint(URI)` メソッドを追加。

- 新しい列挙型
    * `AssertionTarget`
    * `ClaimRuleOperation`

- 新しいクラス
    * `AssertionProcessor`
    * `ClaimRule`


2.38 (2019 年 04 月 09 日)
--------------------------

- `Client` クラス
    * `getTlsClientAuthSanDns()` メソッドを追加。
    * `setTlsClientAuthSanDns(String)` メソッドを追加。
    * `getTlsClientAuthSanUri()` メソッドを追加。
    * `setTlsClientAuthSanUri(URI)` メソッドを追加。
    * `getTlsClientAuthSanIp()` メソッドを追加。
    * `setTlsClientAuthSanIp(String)` メソッドを追加。
    * `getTlsClientAuthSanEmail()` メソッドを追加。
    * `setTlsClientAuthSanEmail(String)` メソッドを追加。


2.37 (2019 年 03 月 16 日)
--------------------------

- `AuthorizationIssueResponse` クラス
    * `getJwtAccessToken()` メソッドを追加。
    * `setJwtAccessToken(String)` メソッドを追加。

- `BackchannelAuthenticationCompleteResponse` クラス
    * `getJwtAccessToken()` メソッドを追加。
    * `setJwtAccessToken(String)` メソッドを追加。

- `Service` クラス
    * `getAccessTokenSignAlg()` メソッドを追加。
    * `setAccessTokenSignAlg(JWSAlg)` メソッドを追加。
    * `getAccessTokenSignatureKeyId()` メソッドを追加。
    * `setAccessTokenSignatureKeyId(String)` メソッドを追加。

- `TokenIssueResponse` クラス
    * `getJwtAccessToken()` メソッドを追加。
    * `setJwtAccessToken(String)` メソッドを追加。

- `TokenResponse` クラス
    * `getJwtAccessToken()` メソッドを追加。
    * `setJwtAccessToken(String)` メソッドを追加。

- `UserInfoRequest` クラス
    * `getClientCertificate()` メソッドを追加。
    * `setClientCertificate(String)` メソッドを追加。


2.36 (2019 年 01 月 17 日)
--------------------------

- `BackchannelAuthenticationCompleteRequest.Result` 列挙型
    * `ERROR` を `TRANSACTION_FAILED` へ名称変更。

- `ErrorCode` 列挙型
    * `transaction_failed` を追加。


2.35 (2019 年 01 月 12 日)
--------------------------

- `BackchannelAuthenticationFailRequest.Reason` 列挙型
    * `INVALID_BINDING_MESSAGE` を追加。

- `BackchannelAuthenticationResponse` クラス
    * `getRequestedExpiry()` メソッドを追加。
    * `setRequestedExpiry(int)` メソッドを追加。


2.34 (2019 年 01 月 12 日)
--------------------------

- `GrantType` 列挙型
    * `BACKCHANNEL_REQUEST` を `CIBA` へ名称変更。


2.33 (2019 年 01 月 04 日)
--------------------------

- `BackchannelAuthenticationResponse` クラス
    * `isUserCodeRequired()` メソッドを追加。
    * `setUserCodeRequired(boolean)` メソッドを追加。

- `User` インターフェース
    * `getAttribute(String)` メソッドを追加。


2.32 (2018 年 12 月 29 日)
--------------------------

- `AuthleteApi` インターフェース
    * `backchannelAuthentication(BackchannelAuthenticationRequest)` メソッドを追加。
    * `backchannelAuthenticationIssue(BackchannelAuthenticationIssueRequest)` メソッドを追加。
    * `backchannelAuthenticationFail(BackchannelAuthenticationFailRequest)` メソッドを追加。
    * `backchannelAuthenticationComplete(BackchannelAuthenticationCompleteRequest)` メソッドを追加。

- `Client` クラス
    * `getBcDeliveryMode()` メソッドを追加。
    * `setBcDeliveryMode(DeliveryMode)` メソッドを追加。
    * `getBcNotificationEndpoint()` メソッドを追加。
    * `setBcNotificationEndpoint(URI)` メソッドを追加。
    * `getBcRequestSignAlg()` メソッドを追加。
    * `setBcRequestSignAlg(JWSAlg)` メソッドを追加。
    * `isBcUserCodeRequired()` メソッドを追加。
    * `setBcUserCodeRequired(boolean)` メソッドを追加。

- `ErrorCode` 列挙型
    * `expired_login_hint_token` を追加。
    * `unknown_user_id` を追加。
    * `missing_user_code` を追加。
    * `invalid_user_code` を追加。
    * `authorization_pending` を追加。
    * `slow_down` を追加。
    * `expired_token` を追加。

- `GrantType` 列挙型
    * `BACKCHANNEL_REQUEST` を追加。

- `Service` クラス
    * `getSupportedBackchannelTokenDeliveryModes()` メソッドを追加。
    * `setSupportedBackchannelTokenDeliveryModes(DeliveryMode[])` メソッドを追加。
    * `getBackchannelAuthenticationEndpoint()` メソッドを追加。
    * `setBackchannelAuthenticationEndpoint(URI)` メソッドを追加。
    * `isBackchannelUserCodeParameterSupported()` メソッドを追加。
    * `setBackchannelUserCodeParameterSupported(boolean)` メソッドを追加。
    * `getBackchannelAuthReqIdDuration()` メソッドを追加。
    * `setBackchannelAuthReqIdDuration(int)` メソッドを追加。
    * `getBackchannelPollingInterval()` メソッドを追加。
    * `setBackchannelPollingInterval(int)` メソッドを追加。
    * `getAllowableClockSkew()` メソッドを追加。
    * `setAllowableClockSkew(int)` メソッドを追加。

- 新しい列挙型
    * `DeliveryMode`
    * `UserIdentificationHintType`

- 新しいクラス
    * `BackchannelAuthenticationRequest`
    * `BackchannelAuthenticationResponse`
    * `BackchannelAuthenticationIssueRequest`
    * `BackchannelAuthenticationIssueResponse`
    * `BackchannelAuthenticationFailRequest`
    * `BackchannelAuthenticationFailResponse`
    * `BackchannelAuthenticationCompleteRequest`
    * `BackchannelAuthenticationCompleteResponse`


2.31 (2018 年 10 月 19 日)
--------------------------

- `TokenUpdateRequest` クラス
    * `getAccessTokenHash()` メソッドを追加。
    * `setAccessTokenHash(String)` メソッドを追加。
    * `isAccessTokenValueUpdated()` メソッドを追加。
    * `setAccessTokenValueUpdated(boolean)` メソッドを追加。

- `TokenUpdateResponse` クラス
    * `getTokenType()` メソッドを追加。
    * `setTokenType(String)` メソッドを追加。


2.30 (2018 年 10 月 10 日)
--------------------------

- `AuthleteConfiguration` インターフェース
    * `getServiceAccessToken()` メソッドを追加。
    * `getServiceOwnerAccessToken()` メソッドを追加。

- `TokenCreateRequest` クラス
    * `isAccessTokenPersistent()` メソッドを追加。
    * `setAccessTokenPersistent(boolean)` メソッドを追加。

- `TokenUpdateRequest` クラス
    * `isAccessTokenPersistent()` メソッドを追加。
    * `setAccessTokenPersistent(boolean)` メソッドを追加。


2.29 (2018 年 10 月 05 日)
--------------------------

- `AuthleteApi` インターフェース
    * `getTokenList()` メソッドを追加。
    * `getTokenList(String, String)` メソッドを追加。
    * `getTokenList(int, int)` メソッドを追加。
    * `getTokenList(String, String, int, int)` メソッドを追加。

- `TokenUpdateRequest` クラス
    * `isAccessTokenExpiresAtUpdatedOnScopeUpdate()` メソッドを追加。
    * `setAccessTokenExpiresAtUpdatedOnScopeUpdate(boolean)` メソッドを追加。


2.28 (2018 年 09 月 25 日)
--------------------------

- `JWEAlg` 列挙型
    * `isSymmetric(JWEAlg)` スタティックメソッドを追加。
    * `isSymmetric()` インスタンスメソッドを追加。
    * `isAsymmetric(JWEAlg)` スタティックメソッドを追加。
    * `isAsymmetric()` インスタンスメソッドを追加。

- `ResponseMode` 列挙型
    * `withJwt()` メソッドを追加。
    * `withoutJwt()` メソッドを追加。

- `Service` クラス
    * `getAuthorizationResponseDuration()` メソッドを追加。
    * `setAuthorizationResponseDuration(long)` メソッドを追加。
    * `getAuthorizationSignatureKeyId()` メソッドを追加。
    * `setAuthorizationSignatureKeyId(String)` メソッドを追加。


2.27 (2018 年 09 月 22 日)
--------------------------

- `Client` クラス
    * `getAuthorizationSignAlg()` メソッドを追加。
    * `setAuthorizationSignAlg(JWSAlg)` メソッドを追加。
    * `getAuthorizationEncryptionAlg()` メソッドを追加。
    * `setAuthorizationEncryptionAlg(JWEAlg)` メソッドを追加。
    * `getAuthorizationEncryptionEnc()` メソッドを追加。
    * `setAuthorizationEncryptionEnc(JWEEnc)` メソッドを追加。

- `ResponseMode` 列挙型
    * `JWT` を追加。
    * `QUERY_JWT` を追加。
    * `FRAGMENT_JWT` を追加。
    * `FORM_POST_JWT` を追加。
    * `isJwtRequired()` メソッドを追加。
    * `isQueryRequired()` メソッドを追加。
    * `isFragmentRequired()` メソッドを追加。
    * `isFormPostRequired()` メソッドを追加。

- `Service` クラス
    * バージョン 2.13 で追加された `getSupportedIntrospectionAuthSigningAlgorithms()` メソッドを削除。
    * バージョン 2.13 で追加された `setSupportedIntrospectionAuthSigningAlgorithms(JWSAlg[])` メソッドを削除。
    * バージョン 2.13 で追加された `getSupportedRevocationAuthSigningAlgorithms()` メソッドを削除。
    * バージョン 2.13 で追加された `setSupportedRevocationAuthSigningAlgorithms(JWSAlg[])` メソッドを削除。
    * バージョン 2.26 で追加された `getSupportedTokenAuthSigningAlgorithms()` メソッドを削除。
    * バージョン 2.26 で追加された `setSupportedTokenAuthSigningAlgorithms(JWSAlg[])` メソッドを削除。


2.26 (2018 年 08 月 28 日)
--------------------------

- `Service` クラス
    * `getSupportedTokenAuthSigningAlgorithms()` メソッドを追加。
    * `setSupportedTokenAuthSigningAlgorithms(JWSAlg[])` メソッドを追加。


2.25 (2018 年 08 月 28 日)
--------------------------

- `AuthorizationResponse` クラス
    * `getIdTokenClaims()` メソッドを追加。
    * `setIdTokenClaims(String)` メソッドを追加。
    * `getUserInfoClaims()` メソッドを追加。
    * `setUserInfoClaims(String)` メソッドを追加。

- `ServiceProfile` 列挙型
    * `OPEN_BANKING` を追加。


2.24 (2018 年 08 月 06 日)
--------------------------

- `Client` クラス
    * `getSoftwareId()` メソッドを追加。
    * `setSoftwareId(String)` メソッドを追加。
    * `getSoftwareVersion()` メソッドを追加。
    * `setSoftwareVersion(String)` メソッドを追加。


2.23 (2018 年 07 月 20 日)
--------------------------

- `AuthleteApi` インターフェース
    * `verifyJose(JoseVerifyRequest)` メソッドを追加。

- 新しいクラス
    * `JoseVerifyRequest`
    * `JoseVerifyResponse`


2.22 (2018 年 07 月 03 日)
--------------------------

- `AuthleteApi` インターフェース
    * `registerClient(ClientRegistrationRequest)` メソッドを追加。

- `AuthorizationResponse` クラス
    * `getRequestObjectPayload()` メソッドを追加。
    * `setRequestObjectPayload(String)` メソッドを追加。

- `ErrorCode` 列挙型
    * `invalid_redirect_uri` を追加。
    * `invalid_client_metadata` を追加。
    * `invalid_software_statement` を追加。
    * `unapproved_software_statement` を追加。

- 新しいクラス
    * `AccessToken`
    * `ClientRegistrationRequest`
    * `ClientRegistrationResponse`
    * `TokenListResponse`


2.21 (2018 年 06 月 12 日)
--------------------------

- 新しい列挙型
    * `HokMethod`


2.20 (2018 年 05 月 16 日)
--------------------------

- `Client` クラス
    * `getSelfSignedCertificateKeyId()` メソッドを追加。
    * `setSignedCertificateKeyId(String)` メソッドを追加。


2.19 (2018 年 05 月 10 日)
--------------------------

- `Client` クラス
    * `isTlsClientCertificateBoundAccessTokens()` メソッドを追加。
    * `setTlsClientCertificateBoundAccessTokens(boolean)` メソッドを追加。
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを削除。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを削除。

- `Service` クラス
    * `isTlsClientCertificateBoundAccessTokens()` メソッドを追加。
    * `setTlsClientCertificateBoundAccessTokens(boolean)` メソッドを追加。
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを削除。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを削除。


2.18 (2018 年 05 月 09 日)
--------------------------

- `AuthleteApi` インターフェース
    * `createService(Service)` メソッドを追加。
    * `createServie(Service)` メソッドを deprecated 化。


2.17 (2018 年 04 月 18 日)
--------------------------

- `JWEAlg` 列挙型
    * `getName()` メソッドを追加。

- `JWEEnc` 列挙型
    * `getName()` メソッドを追加。

- `JWSAlg` 列挙型
    * `getName()` メソッドを追加。


2.16 (2018 年 04 月 18 日)
--------------------------

- `JWSAlg` 列挙型
    * `isSymmetric()` メソッドを追加。
    * `isAsymmetric()` メソッドを追加。


2.15 (2018 年 04 月 12 日)
--------------------------

- `Service` クラス
    * `isMutualTlsValidatePkiCertChain()` メソッドを追加。
    * `setMutualTlsValidatePkiCertChain(boolean)` メソッドを追加。
    * `getTrustedRootCertificates()` メソッドを追加。
    * `setTrustedRootCertificates(String[])` メソッドを追加。

- `TokenRequest` クラス
    * `getClientCertificatePath()` メソッドを追加。
    * `setClientCertificatePath(String[])` メソッドを追加。


2.14 (2018 年 03 月 14 日)
--------------------------

- `IntrospectionRequest` クラス
    * `getClientCertificate()` メソッドを追加。
    * `setClientCertificate(String)` メソッドを追加。

- `IntrospectionResponse` クラス
    * `getCertificateThumbprint()` メソッドを追加。
    * `setCertificateThumbprint(String)` メソッドを追加。


2.13 (2018 年 03 月 13 日)
--------------------------

- `Client` クラス
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを追加。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを追加。

- `Service` クラス
    * `isMutualTlsSenderConstrainedAccessTokens()` メソッドを追加。
    * `setMutualTlsSenderConstrainedAccessTokens(boolean)` メソッドを追加。
    * `getSupportedRevocationAuthMethods()` メソッドを追加。
    * `setSupportedRevocationAuthMethods(ClientAuthMethod[])` メソッドを追加。
    * `getSupportedRevocationAuthSigningAlgorithms()` メソッドを追加。
    * `setSupportedRevocationAuthSigningAlgorithms(JWSAlg[])` メソッドを追加。
    * `getIntrospectionEndpoint()` メソッドを追加。
    * `setIntrospectionEndpoint(URI)` メソッドを追加。
    * `getSupportedIntrospectionAuthMethods()` メソッドを追加。
    * `setSupportedIntrospectionAuthMethods(ClientAuthMethod[])` メソッドを追加。
    * `getSupportedIntrospectionAuthSigningAlgorithms()` メソッドを追加。
    * `setSupportedIntrospectionAuthSigningAlgorithms(JWSAlg[])` メソッドを追加。


2.12 (2018 年 03 月 03 日)
--------------------------

- `JWSAlg` 列挙型
    * `isSymmetric(JWSAlg)` メソッドを追加。
    * `isAsymmetric(JWSAlg)` メソッドを追加。

- `Scope` クラス
    * `getAttributes()` メソッドを追加。
    * `setAttributes(Pair[])` メソッドを追加。
    * `setAttributes(Iterable<Pair>)` メソッドを追加。

- `Service` クラス
    * `getSupportedServiceProfiles()` メソッドを追加。
    * `setSupportedServiceProfiles(ServiceProfile[])` メソッドを追加。
    * `setSupportedServiceProfiles(Iterable<ServiceProfile>)` メソッドを追加。
    * `supports(ServiceProfile)` メソッドを追加。
    * `supportsAll(ServiceProfile...)` メソッドを追加。
    * `supportsAll(Iterable<ServiceProfile>)` メソッドを追加。
    * `supportsAny(ServiceProfile...)` メソッドを追加。
    * `supportsAny(Iterable<ServiceProfile>)` メソッドを追加。

- `TokenRequest` クラス
    * `getClientCertificate()` メソッドを追加。
    * `setClientCertificate(String)` メソッドを追加。

- 新しい列挙型
    * `ServiceProfie`


2.11 (2017 年 11 月 16 日)
--------------------------

- `AuthleteApi` インターフェース
    * `refreshClientSecret(long)` メソッドを追加。
    * `refreshClientSecret(String)` メソッドを追加。
    * `updateClientSecret(long, String)` メソッドを追加。
    * `updateClientSecret(String, String)` メソッドを追加。

- `AuthorizationFailRequest.Reason` 列挙型
    * `ACCOUNT_SELECTION_REQUIRED` を追加。
    * `CONSENT_REQUIRED` を追加。
    * `INTERACTION_REQUIRED` を追加。

- `AuthorizationResponse` クラス
    * JavaDoc を更新。
    * `getLowestPrompt()` メソッドを非推奨とマーク。

- `Client` クラス
    * `tlsClientAuthRootDn` プロパティーを削除。

- `ClientAuthMethod` 列挙型
    * `SELF_SIGNED_TLS_CLIENT_AUTH` を追加。

- 新しいクラス
    * `ClientSecretRefreshResponse` クラス
    * `ClientSecretUpdateRequest` クラス
    * `ClientSecretUpdateResponse` クラス


2.10 (2017 年 10 月 18 日)
--------------------------

- `Settings` クラス
    * `getReadTimeout()` メソッドを追加。
    * `setReadTimeout(int)` メソッドを追加。


2.9 (2017 年 10 月 13 日)
-------------------------

- `AuthleteApi` インターフェース
    * `getSettings()` メソッドを追加。

- 新しいクラス
    * `Settings` クラス


2.8 (2017 年 10 月 13 日)
-------------------------

- `TokenResponse` クラス
    * `grantType` プロパティーを追加。
    * `clientId` プロパティーを追加。
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAliasUsed` プロパティーを追加。
    * `subject` プロパティーを追加。
    * `scopes` プロパティーを追加。
    * `properties` プロパティーを追加。

- `TokenIssueResponse` クラス
    * `clientId` プロパティーを追加。
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAliasUsed` プロパティーを追加。
    * `subject` プロパティーを追加。
    * `scopes` プロパティーを追加。
    * `properties` プロパティーを追加。


2.7 (2017 年 7 月 20 日)
------------------------

- `AuthleteApi` インターフェース
    * `standardIntrospection(StandardIntrospectionRequest)` メソッドを追加。

- `Client` クラス
    * `tlsClientAuthSubjectDn` プロパティーを追加。
    * `tlsClientAuthRootDn` プロパティーを追加。

- `ClientAuthMethod` 列挙型
    * `TLS_CLIENT_AUTH` を追加。

- 新しいクラス
    * `StandardIntrospectionRequest` クラス
    * `StandardIntrospectionResponse` クラス


2.6 (2017 年 6 月 10 日)
------------------------

- `TokenCreateRequest` クラス
    * `accessToken` プロパティーを追加。
    * `refreshToken` プロパティーを追加。


2.5 (2017 年 4 月 19 日)
------------------------

- `UserInfoResponse` クラス
    * `properties` プロパティーを追加。
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAilasUsed` プロパティーを追加。

- `Utils` クラス
    * `stringifyPrompts(Prompt[])` メソッドを追加。
    * `stringifyProperties(Property[])` メソッドを追加。
    * `stringifyScopeNames(Scope[])` メソッドを追加。


2.4 (2017 年 4 月 19 日)
------------------------

- `Service` クラス
    * `idTokenEncryptionKeyId` プロパティーを削除。
    * `userInfoEncryptionKeyId` プロパティーを削除。


2.3 (2017 年 4 月 6 日)
-----------------------

- `AuthorizationResponse` クラス
    * `clientIdAliasUsed` プロパティーを追加。

- `IntrospectionResponse` クラス
    * `clientIdAlias` プロパティーを追加。
    * `clientIdAliasUsed` プロパティーを追加。

- `TokenCreateRequest` クラス
    * `clientIdAliasUsed` プロパティーを追加。


2.2 (2017 年 4 月 2 日)
-----------------------

- `Service` クラス
    * `clientIdAliasEnabled` プロパティーを追加。

- `Client` クラス
    * `clientIdAliasEnabled` プロパティーを追加。


2.1 (2017 年 3 月 18 日)
------------------------

- `AuthleteApi` インターフェース
    * `deleteClientAuthorization(long, String)` メソッドを追加。
    * `getClientAuthorizationList(ClientAuthorizationGetListRequest)` メソッドを追加。
    * `updateClientAuthorization(long, ClientAuthorizationUpdateRequest)` メソッドを追加。

- `AuthleteApiImpl` クラス
    * `AuthleteApi` インターフェースに新規追加されたメソッドを実装。
    * POST リクエスト時に `Content-Type:application/json` が設定されていない不具合を修正。

- `Service` クラス
    * `idTokenSignatureKeyId` プロパティーを追加。
    * `idTokenEncryptionKeyId` プロパティーを追加。
    * `userInfoSignatureKeyId` プロパティーを追加。
    * `userInfoEncryptionKeyId` プロパティーを追加。

- `Client` クラス
    * `clientIdAlias` プロパティーを追加。

- `ClientAuthorizationUpdateRequest` クラス
    * `ClientAuthorizationUpdateRequest(String, String[])` コンストラクタを追加。

- `ClientAuthorizationDeleteRequest` クラス
    * 新規追加。

- `ClientAuthorizationGetListRequest` クラス
    * 新規追加。

- `CLI` クラス
    * `getClientAuthorizationList` API をサポート。


2.0 (2017 年 2 月 27 日)
------------------------

- `HttpURLConnection` による `AuthleteApi` インターフェースの実装を追加。
  `AuthleteApiFactory` 内にある既知の実装リストに新しい実装を追加。

- Authlete API のコマンドラインインターフェースを実装。
  `CLI` クラスと `authlete-cli.sh` スクリプトを追加。

- `Utils` クラス
    * `toJson(Object, boolean)` メソッドを追加。
    * `fromJson(String, class)` メソッドを追加。

- `authlete.properties` を `.gitignore` に追加。


1.41 (2017 年 2 月 19 日)
-------------------------

- `ClientExtension` クラスに `setRequestableScopes(Set)` メソッドを追加。


1.40 (2017 年 2 月 17 日)
-------------------------

- `AuthleteApi` インターフェースに `deleteGrantedScopes(long, String)`
  メソッドを追加。

- `Service` クラスから `properties` プロパティーを削除。


1.39 (2017 年 2 月 13 日)
-------------------------

- 新しいクラスを追加
    * `ClientExtension` クラス
    * `Pair` クラス

- `AuthleteApi` インターフェース
    * `getGrantedScopes(long, String)` メソッドを追加。

- `Client` クラス
    * `extension` フィールドを追加。

- `Service` クラス
    * `directIntrospectionEndpointEnabled` フィールドを追加。
    * `errorDescriptionOmitted` フィールドを追加。
    * `errorUriOmitted` フィールドを追加。
    * `metadata` フィールドを追加。
    * `properties` プロパティーを deprecated 化。


1.38 (2016 年 9 月 20 日)
-------------------------

- `GrantedScopesGetResponse` クラスを追加。


1.37 (2016 年 9 月 12 日)
-------------------------

- `ClientAuthorizationUpdateRequest` クラスを追加。


1.36 (2016 年 9 月 6 日)
------------------------

- `AuthorizedClientListResponse` クラスを追加。


1.35 (2016 年 8 月 15 日)
-------------------------

- `AuthorizationIssueRequest`
    * `sub` クレームの値を調整するための `sub` フィールドを追加。

- `UserInfoIssueRequest`
    * `sub` クレームの値を調整するための `sub` フィールドを追加。


1.34 (2016 年 7 月 30 日)
-------------------------

- 新しいクラス
    * `TokenUpdateRequest` クラスを追加。
    * `TokenUpdateResponse` クラスを追加。

- `AuthleteApi`
    * `tokenUpdate(TokenUpdateRequest)` メソッドを追加。
    * `getRequestableScopes(long clientId)` メソッドを追加。
    * `setRequestableScopes(long clientId, String[] scopes)` メソッドを追加。
    * `deleteRequestableScopes(long clientId)` メソッドを追加。

- `AuthorizationIssueRequest`
    * 元の認可リクエストに含まれているスコープ群を置き換えるための
      `scopes` フィールドを追加。

- `AuthorizationIssueResponse`
    * `accessToken` フィールドを追加。
    * `accessTokenExpiresAt` フィールドを追加。
    * `accessTokenDuration` フィールドを追加。
    * `idToken` フィールドを追加。
    * `authorizationCode` フィールドを追加。

- `AuthorizationResponse`
    * 元の認可リクエストに含まれる `prompt` パラメーターの値を示す
      `prompts` フィールドを追加。

- `TokenCreateResponse`
    * `properties` フィールドを追加。

- `TokenIssueResponse`
    * `accessToken` フィールドを追加。
    * `accessTokenExpiresAt` フィールドを追加。
    * `accessTokenDuration` フィールドを追加。
    * `refreshToken` フィールドを追加。
    * `refreshTokenExpiresAt` フィールドを追加。
    * `refreshTokenDuration` フィールドを追加。

- `TokenResponse`
    * `accessToken` フィールドを追加。
    * `accessTokenExpiresAt` フィールドを追加。
    * `accessTokenDuration` フィールドを追加。
    * `refreshToken` フィールドを追加。
    * `refreshTokenExpiresAt` フィールドを追加。
    * `refreshTokenDuration` フィールドを追加。
    * `idToken` フィールドを追加。
