package com.authlete.common.api.migration;


import com.authlete.common.api.AuthleteApi;
import com.authlete.common.api.AuthleteApiException;
import com.authlete.common.api.Options;
import com.authlete.common.api.Settings;
import com.authlete.common.dto.AuthorizationFailRequest;
import com.authlete.common.dto.AuthorizationFailResponse;
import com.authlete.common.dto.AuthorizationIssueRequest;
import com.authlete.common.dto.AuthorizationIssueResponse;
import com.authlete.common.dto.AuthorizationRequest;
import com.authlete.common.dto.AuthorizationResponse;
import com.authlete.common.dto.AuthorizationTicketInfoRequest;
import com.authlete.common.dto.AuthorizationTicketInfoResponse;
import com.authlete.common.dto.AuthorizationTicketUpdateRequest;
import com.authlete.common.dto.AuthorizationTicketUpdateResponse;
import com.authlete.common.dto.AuthorizedClientListResponse;
import com.authlete.common.dto.BackchannelAuthenticationCompleteRequest;
import com.authlete.common.dto.BackchannelAuthenticationCompleteResponse;
import com.authlete.common.dto.BackchannelAuthenticationFailRequest;
import com.authlete.common.dto.BackchannelAuthenticationFailResponse;
import com.authlete.common.dto.BackchannelAuthenticationIssueRequest;
import com.authlete.common.dto.BackchannelAuthenticationIssueResponse;
import com.authlete.common.dto.BackchannelAuthenticationRequest;
import com.authlete.common.dto.BackchannelAuthenticationResponse;
import com.authlete.common.dto.Client;
import com.authlete.common.dto.ClientAuthorizationGetListRequest;
import com.authlete.common.dto.ClientAuthorizationUpdateRequest;
import com.authlete.common.dto.ClientListResponse;
import com.authlete.common.dto.ClientRegistrationRequest;
import com.authlete.common.dto.ClientRegistrationResponse;
import com.authlete.common.dto.ClientSecretRefreshResponse;
import com.authlete.common.dto.ClientSecretUpdateResponse;
import com.authlete.common.dto.CredentialBatchIssueRequest;
import com.authlete.common.dto.CredentialBatchIssueResponse;
import com.authlete.common.dto.CredentialBatchParseRequest;
import com.authlete.common.dto.CredentialBatchParseResponse;
import com.authlete.common.dto.CredentialDeferredIssueRequest;
import com.authlete.common.dto.CredentialDeferredIssueResponse;
import com.authlete.common.dto.CredentialDeferredParseRequest;
import com.authlete.common.dto.CredentialDeferredParseResponse;
import com.authlete.common.dto.CredentialIssuerJwksRequest;
import com.authlete.common.dto.CredentialIssuerJwksResponse;
import com.authlete.common.dto.CredentialIssuerMetadataRequest;
import com.authlete.common.dto.CredentialIssuerMetadataResponse;
import com.authlete.common.dto.CredentialJwtIssuerMetadataRequest;
import com.authlete.common.dto.CredentialJwtIssuerMetadataResponse;
import com.authlete.common.dto.CredentialOfferCreateRequest;
import com.authlete.common.dto.CredentialOfferCreateResponse;
import com.authlete.common.dto.CredentialOfferInfoRequest;
import com.authlete.common.dto.CredentialOfferInfoResponse;
import com.authlete.common.dto.CredentialSingleIssueRequest;
import com.authlete.common.dto.CredentialSingleIssueResponse;
import com.authlete.common.dto.CredentialSingleParseRequest;
import com.authlete.common.dto.CredentialSingleParseResponse;
import com.authlete.common.dto.DeviceAuthorizationRequest;
import com.authlete.common.dto.DeviceAuthorizationResponse;
import com.authlete.common.dto.DeviceCompleteRequest;
import com.authlete.common.dto.DeviceCompleteResponse;
import com.authlete.common.dto.DeviceVerificationRequest;
import com.authlete.common.dto.DeviceVerificationResponse;
import com.authlete.common.dto.FederationConfigurationRequest;
import com.authlete.common.dto.FederationConfigurationResponse;
import com.authlete.common.dto.FederationRegistrationRequest;
import com.authlete.common.dto.FederationRegistrationResponse;
import com.authlete.common.dto.GMRequest;
import com.authlete.common.dto.GMResponse;
import com.authlete.common.dto.GrantedScopesGetResponse;
import com.authlete.common.dto.HskCreateRequest;
import com.authlete.common.dto.HskListResponse;
import com.authlete.common.dto.HskResponse;
import com.authlete.common.dto.IDTokenReissueRequest;
import com.authlete.common.dto.IDTokenReissueResponse;
import com.authlete.common.dto.IntrospectionRequest;
import com.authlete.common.dto.IntrospectionResponse;
import com.authlete.common.dto.JoseVerifyRequest;
import com.authlete.common.dto.JoseVerifyResponse;
import com.authlete.common.dto.NativeSsoLogoutRequest;
import com.authlete.common.dto.NativeSsoLogoutResponse;
import com.authlete.common.dto.NativeSsoRequest;
import com.authlete.common.dto.NativeSsoResponse;
import com.authlete.common.dto.PushedAuthReqRequest;
import com.authlete.common.dto.PushedAuthReqResponse;
import com.authlete.common.dto.RevocationRequest;
import com.authlete.common.dto.RevocationResponse;
import com.authlete.common.dto.Service;
import com.authlete.common.dto.ServiceConfigurationRequest;
import com.authlete.common.dto.ServiceListResponse;
import com.authlete.common.dto.StandardIntrospectionRequest;
import com.authlete.common.dto.StandardIntrospectionResponse;
import com.authlete.common.dto.TokenCreateBatchResponse;
import com.authlete.common.dto.TokenCreateBatchStatusResponse;
import com.authlete.common.dto.TokenCreateRequest;
import com.authlete.common.dto.TokenCreateResponse;
import com.authlete.common.dto.TokenFailRequest;
import com.authlete.common.dto.TokenFailResponse;
import com.authlete.common.dto.TokenIssueRequest;
import com.authlete.common.dto.TokenIssueResponse;
import com.authlete.common.dto.TokenListResponse;
import com.authlete.common.dto.TokenRequest;
import com.authlete.common.dto.TokenResponse;
import com.authlete.common.dto.TokenRevokeRequest;
import com.authlete.common.dto.TokenRevokeResponse;
import com.authlete.common.dto.TokenUpdateRequest;
import com.authlete.common.dto.TokenUpdateResponse;
import com.authlete.common.dto.UserInfoIssueRequest;
import com.authlete.common.dto.UserInfoIssueResponse;
import com.authlete.common.dto.UserInfoRequest;
import com.authlete.common.dto.UserInfoResponse;
import com.authlete.common.types.TokenStatus;

import java.util.Map;
import java.util.function.Function;

import static jdk.internal.org.jline.utils.Colors.s;


/**
 * @author kylegonzalez
 */
public class MigrationSupportedAuthleteApiImpl implements AuthleteApi
{
    private final AuthleteApi primaryApi;
    private final AuthleteApi secondaryApi;

    public MigrationSupportedAuthleteApiImpl(AuthleteApi primaryApi, AuthleteApi secondaryApi)
    {
        this.primaryApi = primaryApi;
        this.secondaryApi = secondaryApi;
    }

    public <T> T withApis(Function<AuthleteApi, T> function)
    {
        T primaryResponse = null;
        AuthleteApiException primaryError = null;
        try
        {
            primaryResponse = function.apply(primaryApi);
            if (secondaryApi == null)
            {
                return primaryResponse;
            }
        }
        catch (AuthleteApiException ex)
        {
            if (secondaryApi == null)
            {
                throw ex;
            }
            primaryError = ex;
        }

        try
        {
            return function.apply(secondaryApi);
        }
        catch (AuthleteApiException ex)
        {
            if (primaryError != null)
            {
                throw primaryError;
            }
            return primaryResponse;
        }
    }


    public <T> T withBothApis(Function<AuthleteApi, T> function)
    {
        T primaryResponse = null;
        AuthleteApiException primaryError = null;

        try
        {
            primaryResponse = function.apply(primaryApi);
        }
        catch (AuthleteApiException ex)
        {
            if (secondaryApi == null)
            {
                throw ex;
            }
            primaryError = ex;
        }

        if (secondaryApi == null)
        {
            return primaryResponse;
        }

        try
        {
            return function.apply(secondaryApi);
        }
        catch (AuthleteApiException ex)
        {
            if (primaryError != null)
            {
                throw primaryError;
            }
            return primaryResponse;
        }
    }

    @Override
    public AuthorizationResponse authorization(AuthorizationRequest authorizationRequest, Options options) throws AuthleteApiException
    {
       return withApis(api -> api.authorization(authorizationRequest, options));
    }

    @Override
    public AuthorizationFailResponse authorizationFail(AuthorizationFailRequest authorizationFailRequest, Options options) throws AuthleteApiException
    {
        return withApis(api -> api.authorizationFail(authorizationFailRequest, options));
    }

    @Override
    public AuthorizationIssueResponse authorizationIssue(AuthorizationIssueRequest authorizationIssueRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.authorizationIssue(authorizationIssueRequest, options));
    }

    @Override
    public TokenResponse token(TokenRequest tokenRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.token(tokenRequest, options));
    }

    @Override
    public TokenCreateResponse tokenCreate(TokenCreateRequest tokenCreateRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.tokenCreate(tokenCreateRequest, options));
    }

    @Override
    public void tokenDelete(String s, Options options) throws AuthleteApiException {
        withBothApis(api -> {
            api.tokenDelete(s, options);
            return null;
        });
    }

    @Override
    public TokenFailResponse tokenFail(TokenFailRequest tokenFailRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.tokenFail(tokenFailRequest, options));
    }

    @Override
    public TokenIssueResponse tokenIssue(TokenIssueRequest tokenIssueRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.tokenIssue(tokenIssueRequest, options));
    }

    @Override
    public TokenRevokeResponse tokenRevoke(TokenRevokeRequest tokenRevokeRequest, Options options) throws AuthleteApiException {
        return withBothApis(api -> api.tokenRevoke(tokenRevokeRequest, options));
    }

    @Override
    public TokenUpdateResponse tokenUpdate(TokenUpdateRequest tokenUpdateRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.tokenUpdate(tokenUpdateRequest, options));
    }

    @Override
    public TokenListResponse getTokenList(Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(options));
    }

    @Override
    public TokenListResponse getTokenList(TokenStatus tokenStatus, Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(tokenStatus, options));
    }

    @Override
    public TokenListResponse getTokenList(String s, String s1, Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(s, s1, options));
    }

    @Override
    public TokenListResponse getTokenList(String s, String s1, TokenStatus tokenStatus, Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(s, s1, options));
    }

    @Override
    public TokenListResponse getTokenList(int i, int i1, Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(i, i1, options));
    }

    @Override
    public TokenListResponse getTokenList(int i, int i1, TokenStatus tokenStatus, Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(i, i1, tokenStatus, options));
    }

    @Override
    public TokenListResponse getTokenList(String s, String s1, int i, int i1, Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(s, s1, i, i1, options));
    }

    @Override
    public TokenListResponse getTokenList(String s, String s1, int i, int i1, TokenStatus tokenStatus, Options options) throws AuthleteApiException {
        return withApis(api -> api.getTokenList(s, s1, i, i1, tokenStatus, options));
    }

    @Override
    public RevocationResponse revocation(RevocationRequest revocationRequest, Options options) throws AuthleteApiException {
        return withBothApis(api -> api.revocation(revocationRequest, options));
    }

    @Override
    public UserInfoResponse userinfo(UserInfoRequest userInfoRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.userinfo(userInfoRequest, options));
    }

    @Override
    public UserInfoIssueResponse userinfoIssue(UserInfoIssueRequest userInfoIssueRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.userinfoIssue(userInfoIssueRequest, options));
    }

    protected boolean isFailedIntrospection(IntrospectionResponse response, AuthleteApiException error)
    {
        return error != null || response.getAction() != IntrospectionResponse.Action.OK;
    }

    @Override
    public IntrospectionResponse introspection(IntrospectionRequest introspectionRequest, Options options) throws AuthleteApiException
    {
        // TODO
        IntrospectionResponse primaryResponse = null;
        AuthleteApiException ex = null;
        try
        {
            primaryResponse = primaryApi.introspection(introspectionRequest, options);
        }
        catch (AuthleteApiException e)
        {
            ex = e;
        }

        if (secondaryApi != null && isFailedIntrospection(primaryResponse, ex))
        {
            IntrospectionResponse secondaryResponse = null;
            try
            {
                secondaryResponse = secondaryApi.introspection(introspectionRequest, options);
            }
            catch (AuthleteApiException e)
            {
                ex = e;
            }

            if (isFailedIntrospection(secondaryResponse, ex))
            {
                return primaryResponse;
            }
            return secondaryResponse;
        }
        return primaryResponse;
    }

    @Override
    public StandardIntrospectionResponse standardIntrospection(StandardIntrospectionRequest standardIntrospectionRequest, Options options) throws AuthleteApiException {
        // TODO - May need to update "failure"
        return withApis(api -> api.standardIntrospection(standardIntrospectionRequest, options));
    }

    @Override
    public Service createService(Service service, Options options) throws AuthleteApiException {
        return withApis(api -> api.createService(service, options));
    }

    @Override
    public Service createServie(Service service) throws AuthleteApiException {
        return withApis(api -> api.createServie(service));
    }

    @Override
    public void deleteService(long l, Options options) throws AuthleteApiException {
        withApis(api -> {
            api.deleteService(l, options);
            return null;
        });
    }

    @Override
    public Service getService(long l, Options options) throws AuthleteApiException {
        return withApis(api -> api.getService(l, options));
    }

    @Override
    public ServiceListResponse getServiceList(Options options) throws AuthleteApiException {
        return withApis(api -> api.getServiceList(options));
    }

    @Override
    public ServiceListResponse getServiceList(int i, int i1, Options options) throws AuthleteApiException {
        return withApis(api -> api.getServiceList(i, i1, options));
    }

    @Override
    public Service updateService(Service service, Options options) throws AuthleteApiException {
        return withApis(api -> api.updateService(service, options));
    }

    @Override
    public String getServiceJwks(Options options) throws AuthleteApiException {
        return withApis(api -> api.getServiceJwks(options));
    }

    @Override
    public String getServiceJwks(boolean b, boolean b1, Options options) throws AuthleteApiException {
        return withApis(api -> api.getServiceJwks(b, b1, options));
    }

    @Override
    public String getServiceConfiguration(Options options) throws AuthleteApiException {
        return withApis(api -> api.getServiceConfiguration(options));
    }

    @Override
    public String getServiceConfiguration(boolean b, Options options) throws AuthleteApiException {
        return withApis(api -> api.getServiceConfiguration(b, options));
    }

    @Override
    public String getServiceConfiguration(ServiceConfigurationRequest serviceConfigurationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.getServiceConfiguration(serviceConfigurationRequest, options));
    }

    @Override
    public Client createClient(Client client, Options options) throws AuthleteApiException {
        return withApis(api -> api.createClient(client, options));
    }

    @Override
    public ClientRegistrationResponse dynamicClientRegister(ClientRegistrationRequest clientRegistrationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.dynamicClientRegister(clientRegistrationRequest, options));
    }

    @Override
    public ClientRegistrationResponse dynamicClientGet(ClientRegistrationRequest clientRegistrationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.dynamicClientGet(clientRegistrationRequest, options));
    }

    @Override
    public ClientRegistrationResponse dynamicClientUpdate(ClientRegistrationRequest clientRegistrationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.dynamicClientUpdate(clientRegistrationRequest, options));
    }

    @Override
    public ClientRegistrationResponse dynamicClientDelete(ClientRegistrationRequest clientRegistrationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.dynamicClientDelete(clientRegistrationRequest, options));
    }

    @Override
    public void deleteClient(long l, Options options) throws AuthleteApiException {
        withApis(api -> {
            api.deleteClient(l, options);
            return null;
        });
    }

    @Override
    public void deleteClient(String s, Options options) throws AuthleteApiException {
        withApis(api -> {
            api.deleteClient(s, options);
            return null;
        });
    }

    @Override
    public Client getClient(long l, Options options) throws AuthleteApiException {
        return withApis(api -> api.getClient(l, options));
    }

    @Override
    public Client getClient(String s, Options options) throws AuthleteApiException {
        return withApis(api -> api.getClient(s, options));
    }

    @Override
    public ClientListResponse getClientList(Options options) throws AuthleteApiException {
        return withApis(api -> api.getClientList(options));
    }

    @Override
    public ClientListResponse getClientList(String s, Options options) throws AuthleteApiException {
        return withApis(api -> api.getClientList(s, options));
    }

    @Override
    public ClientListResponse getClientList(int i, int i1, Options options) throws AuthleteApiException {
        return withApis(api -> api.getClientList(i, i1, options));
    }

    @Override
    public ClientListResponse getClientList(String s, int i, int i1, Options options) throws AuthleteApiException {
        return withApis(api -> api.getClientList(s, i, i1, options));
    }

    @Override
    public Client updateClient(Client client, Options options) throws AuthleteApiException {
        return withApis(api -> api.updateClient(client, options));
    }

    @Override
    public String[] getRequestableScopes(long l, Options options) throws AuthleteApiException {
        return withApis(api -> api.getRequestableScopes(l, options));
    }

    @Override
    public String[] setRequestableScopes(long l, String[] strings, Options options) throws AuthleteApiException {
        return withApis(api -> api.setRequestableScopes(l, strings, options));
    }

    @Override
    public void deleteRequestableScopes(long l, Options options) throws AuthleteApiException
    {
        withApis(api -> {
            api.deleteRequestableScopes(l, options);
            return null;
        });
    }

    @Override
    public GrantedScopesGetResponse getGrantedScopes(long clientId, String userIdentifier, Options options)
    {
        return withApis(api -> api.getGrantedScopes(clientId, userIdentifier, options));
    }

    @Override
    public void deleteGrantedScopes(long clientId, String userIdentifier, Options options)
    {
        // TODO - should this delete from both?
        withApis(api -> {
            api.deleteGrantedScopes(clientId, userIdentifier, options);
            return null;
        });
    }

    @Override
    public void deleteClientAuthorization(long clientId, String subject, Options options) throws AuthleteApiException {
        withApis(api -> {
            api.deleteClientAuthorization(clientId, subject, options);
            return null;
        });
    }

    @Override
    public AuthorizedClientListResponse getClientAuthorizationList(ClientAuthorizationGetListRequest clientAuthorizationGetListRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.getClientAuthorizationList(clientAuthorizationGetListRequest, options));
    }

    @Override
    public void updateClientAuthorization(long l, ClientAuthorizationUpdateRequest clientAuthorizationUpdateRequest, Options options) throws AuthleteApiException {
        withApis(api -> {
            api.updateClientAuthorization(l, clientAuthorizationUpdateRequest, options);
            return null;
        });
    }

    @Override
    public ClientSecretRefreshResponse refreshClientSecret(long l, Options options) throws AuthleteApiException {
        // TODO - should this refresh both?
        return withApis(api -> api.refreshClientSecret(l, options));
    }

    @Override
    public ClientSecretRefreshResponse refreshClientSecret(String s, Options options) throws AuthleteApiException {
        // TODO - should this refresh both?
        return withApis(api -> api.refreshClientSecret(s, options));
    }

    @Override
    public ClientSecretUpdateResponse updateClientSecret(long l, String s, Options options) throws AuthleteApiException {
        // TODO - should this update both?
        return withApis(api -> api.updateClientSecret(l, s, options));
    }

    @Override
    public ClientSecretUpdateResponse updateClientSecret(String s, String s1, Options options) throws AuthleteApiException {
        // TODO - should this update both?
        return withApis(api -> api.updateClientSecret(s, s1, options));
    }

    @Override
    public Settings getSettings() {
        // TODO
        return withApis(AuthleteApi::getSettings);
    }

    @Override
    public JoseVerifyResponse verifyJose(JoseVerifyRequest joseVerifyRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.verifyJose(joseVerifyRequest, options));
    }

    @Override
    public BackchannelAuthenticationResponse backchannelAuthentication(BackchannelAuthenticationRequest backchannelAuthenticationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.backchannelAuthentication(backchannelAuthenticationRequest, options));
    }

    @Override
    public BackchannelAuthenticationIssueResponse backchannelAuthenticationIssue(BackchannelAuthenticationIssueRequest backchannelAuthenticationIssueRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.backchannelAuthenticationIssue(backchannelAuthenticationIssueRequest, options));
    }

    @Override
    public BackchannelAuthenticationFailResponse backchannelAuthenticationFail(BackchannelAuthenticationFailRequest backchannelAuthenticationFailRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.backchannelAuthenticationFail(backchannelAuthenticationFailRequest, options));
    }

    @Override
    public BackchannelAuthenticationCompleteResponse backchannelAuthenticationComplete(BackchannelAuthenticationCompleteRequest backchannelAuthenticationCompleteRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.backchannelAuthenticationComplete(backchannelAuthenticationCompleteRequest, options));
    }

    @Override
    public DeviceAuthorizationResponse deviceAuthorization(DeviceAuthorizationRequest deviceAuthorizationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.deviceAuthorization(deviceAuthorizationRequest, options));
    }

    @Override
    public DeviceCompleteResponse deviceComplete(DeviceCompleteRequest deviceCompleteRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.deviceComplete(deviceCompleteRequest, options));
    }

    @Override
    public DeviceVerificationResponse deviceVerification(DeviceVerificationRequest deviceVerificationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.deviceVerification(deviceVerificationRequest, options));
    }

    @Override
    public PushedAuthReqResponse pushAuthorizationRequest(PushedAuthReqRequest pushedAuthReqRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.pushAuthorizationRequest(pushedAuthReqRequest, options));
    }

    @Override
    public HskResponse hskCreate(HskCreateRequest hskCreateRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.hskCreate(hskCreateRequest, options));
    }

    @Override
    public HskResponse hskDelete(String s, Options options) throws AuthleteApiException {
        return withApis(api -> api.hskDelete(s, options));
    }

    @Override
    public HskResponse hskGet(String s, Options options) throws AuthleteApiException {
        return withApis(api -> api.hskGet(s, options));
    }

    @Override
    public HskListResponse hskGetList(Options options) throws AuthleteApiException {
        return withApis(api -> api.hskGetList(options));
    }

    @Override
    public Map<String, String> echo(Map<String, String> map, Options options) throws AuthleteApiException {
        return withApis(api -> api.echo(map, options));
    }

    @Override
    public GMResponse gm(GMRequest gmRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.gm(gmRequest, options));
    }

    @Override
    public void updateClientLockFlag(String s, boolean b, Options options) throws AuthleteApiException {
        // TODO - should this update and call both?
    }

    @Override
    public FederationConfigurationResponse federationConfiguration(FederationConfigurationRequest federationConfigurationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.federationConfiguration(federationConfigurationRequest, options));
    }

    @Override
    public FederationRegistrationResponse federationRegistration(FederationRegistrationRequest federationRegistrationRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.federationRegistration(federationRegistrationRequest, options));
    }

    @Override
    public CredentialIssuerMetadataResponse credentialIssuerMetadata(CredentialIssuerMetadataRequest credentialIssuerMetadataRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialIssuerMetadata(credentialIssuerMetadataRequest, options);
    }

    @Override
    public CredentialJwtIssuerMetadataResponse credentialJwtIssuerMetadata(CredentialJwtIssuerMetadataRequest credentialJwtIssuerMetadataRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialJwtIssuerMetadata(credentialJwtIssuerMetadataRequest);
    }

    @Override
    public CredentialIssuerJwksResponse credentialIssuerJwks(CredentialIssuerJwksRequest credentialIssuerJwksRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialIssuerJwks(credentialIssuerJwksRequest, options);
    }

    @Override
    public CredentialOfferCreateResponse credentialOfferCreate(CredentialOfferCreateRequest credentialOfferCreateRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialOfferCreate(credentialOfferCreateRequest, options);
    }

    @Override
    public CredentialOfferInfoResponse credentialOfferInfo(CredentialOfferInfoRequest credentialOfferInfoRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialOfferInfo(credentialOfferInfoRequest);
    }

    @Override
    public CredentialSingleParseResponse credentialSingleParse(CredentialSingleParseRequest credentialSingleParseRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialSingleParse(credentialSingleParseRequest, options);
    }

    @Override
    public CredentialSingleIssueResponse credentialSingleIssue(CredentialSingleIssueRequest credentialSingleIssueRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialSingleIssue(credentialSingleIssueRequest, options);
    }

    @Override
    public CredentialBatchParseResponse credentialBatchParse(CredentialBatchParseRequest credentialBatchParseRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialBatchParse(credentialBatchParseRequest, options);
    }

    @Override
    public CredentialBatchIssueResponse credentialBatchIssue(CredentialBatchIssueRequest credentialBatchIssueRequest, Options options) throws AuthleteApiException {
        // TODO? Is this primary only - assuming all vci is 3.0 only
        return primaryApi.credentialBatchIssue(credentialBatchIssueRequest, options);
    }

    @Override
    public CredentialDeferredParseResponse credentialDeferredParse(CredentialDeferredParseRequest credentialDeferredParseRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialDeferredParse(credentialDeferredParseRequest, options);
    }

    @Override
    public CredentialDeferredIssueResponse credentialDeferredIssue(CredentialDeferredIssueRequest credentialDeferredIssueRequest, Options options) throws AuthleteApiException {
        return primaryApi.credentialDeferredIssue(credentialDeferredIssueRequest, options);
    }

    @Override
    public IDTokenReissueResponse idTokenReissue(IDTokenReissueRequest idTokenReissueRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.idTokenReissue(idTokenReissueRequest, options));
    }

    @Override
    public AuthorizationTicketInfoResponse authorizationTicketInfo(AuthorizationTicketInfoRequest authorizationTicketInfoRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.authorizationTicketInfo(authorizationTicketInfoRequest, options));
    }

    @Override
    public AuthorizationTicketUpdateResponse authorizationTicketUpdate(AuthorizationTicketUpdateRequest authorizationTicketUpdateRequest, Options options) throws AuthleteApiException {
        return withApis(api -> api.authorizationTicketUpdate(authorizationTicketUpdateRequest, options));
    }

    @Override
    public TokenCreateBatchResponse tokenCreateBatch(TokenCreateRequest[] tokenCreateRequests, boolean b, Options options) throws AuthleteApiException {
        return primaryApi.tokenCreateBatch(tokenCreateRequests, b, options);
    }

    @Override
    public TokenCreateBatchStatusResponse getTokenCreateBatchStatus(String s, Options options) throws AuthleteApiException {
        return primaryApi.getTokenCreateBatchStatus(s, options);
    }

    @Override
    public NativeSsoResponse nativeSso(NativeSsoRequest nativeSsoRequest, Options options) throws AuthleteApiException {
        // TODO
        return withApis(api -> api.nativeSso(nativeSsoRequest, options));
    }

    @Override
    public NativeSsoLogoutResponse nativeSsoLogout(NativeSsoLogoutRequest nativeSsoLogoutRequest, Options options) throws AuthleteApiException {
        // TODO
        return withApis(api -> api.nativeSsoLogout(nativeSsoLogoutRequest, options));
    }
}
