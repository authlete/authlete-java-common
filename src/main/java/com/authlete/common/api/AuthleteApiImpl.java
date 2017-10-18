/*
 * Copyright (C) 2017 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.api;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.authlete.common.conf.AuthleteConfiguration;
import com.authlete.common.dto.ApiResponse;
import com.authlete.common.dto.AuthorizationFailRequest;
import com.authlete.common.dto.AuthorizationFailResponse;
import com.authlete.common.dto.AuthorizationIssueRequest;
import com.authlete.common.dto.AuthorizationIssueResponse;
import com.authlete.common.dto.AuthorizationRequest;
import com.authlete.common.dto.AuthorizationResponse;
import com.authlete.common.dto.AuthorizedClientListResponse;
import com.authlete.common.dto.Client;
import com.authlete.common.dto.ClientAuthorizationDeleteRequest;
import com.authlete.common.dto.ClientAuthorizationGetListRequest;
import com.authlete.common.dto.ClientAuthorizationUpdateRequest;
import com.authlete.common.dto.ClientListResponse;
import com.authlete.common.dto.GrantedScopesGetResponse;
import com.authlete.common.dto.IntrospectionRequest;
import com.authlete.common.dto.IntrospectionResponse;
import com.authlete.common.dto.RevocationRequest;
import com.authlete.common.dto.RevocationResponse;
import com.authlete.common.dto.Service;
import com.authlete.common.dto.ServiceListResponse;
import com.authlete.common.dto.StandardIntrospectionRequest;
import com.authlete.common.dto.StandardIntrospectionResponse;
import com.authlete.common.dto.TokenCreateRequest;
import com.authlete.common.dto.TokenCreateResponse;
import com.authlete.common.dto.TokenFailRequest;
import com.authlete.common.dto.TokenFailResponse;
import com.authlete.common.dto.TokenIssueRequest;
import com.authlete.common.dto.TokenIssueResponse;
import com.authlete.common.dto.TokenRequest;
import com.authlete.common.dto.TokenResponse;
import com.authlete.common.dto.TokenUpdateRequest;
import com.authlete.common.dto.TokenUpdateResponse;
import com.authlete.common.dto.UserInfoIssueRequest;
import com.authlete.common.dto.UserInfoIssueResponse;
import com.authlete.common.dto.UserInfoRequest;
import com.authlete.common.dto.UserInfoResponse;
import com.authlete.common.util.Utils;
import com.authlete.common.web.BasicCredentials;


/**
 * An implementation of {@link AuthleteApi} interface using {@link HttpURLConnection}.
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.0
 */
class AuthleteApiImpl implements AuthleteApi
{
    private static String UTF_8 = "UTF-8";


    /** HTTP methods used in this source code. */
    private enum HttpMethod
    {
        GET,
        POST,
        DELETE
    }


    private static final String AUTH_AUTHORIZATION_API_PATH            = "/api/auth/authorization";
    private static final String AUTH_AUTHORIZATION_FAIL_API_PATH       = "/api/auth/authorization/fail";
    private static final String AUTH_AUTHORIZATION_ISSUE_API_PATH      = "/api/auth/authorization/issue";
    private static final String AUTH_TOKEN_API_PATH                    = "/api/auth/token";
    private static final String AUTH_TOKEN_CREATE_API_PATH             = "/api/auth/token/create";
    private static final String AUTH_TOKEN_FAIL_API_PATH               = "/api/auth/token/fail";
    private static final String AUTH_TOKEN_ISSUE_API_PATH              = "/api/auth/token/issue";
    private static final String AUTH_TOKEN_UPDATE_API_PATH             = "/api/auth/token/update";
    private static final String AUTH_REVOCATION_API_PATH               = "/api/auth/revocation";
    private static final String AUTH_USERINFO_API_PATH                 = "/api/auth/userinfo";
    private static final String AUTH_USERINFO_ISSUE_API_PATH           = "/api/auth/userinfo/issue";
    private static final String AUTH_INTROSPECTION_API_PATH            = "/api/auth/introspection";
    private static final String AUTH_INTROSPECTION_STANDARD_API_PATH   = "/api/auth/introspection/standard";
    private static final String SERVICE_CONFIGURATION_API_PATH         = "/api/service/configuration";
    private static final String SERVICE_CREATE_API_PATH                = "/api/service/create";
    private static final String SERVICE_DELETE_API_PATH                = "/api/service/delete/%d";
    private static final String SERVICE_GET_API_PATH                   = "/api/service/get/%d";
    private static final String SERVICE_GET_LIST_API_PATH              = "/api/service/get/list";
    private static final String SERVICE_JWKS_GET_API_PATH              = "/api/service/jwks/get";
    private static final String SERVICE_UPDATE_API_PATH                = "/api/service/update/%d";
    private static final String CLIENT_CREATE_API_PATH                 = "/api/client/create";
    private static final String CLIENT_DELETE_API_PATH                 = "/api/client/delete/%d";
    private static final String CLIENT_GET_API_PATH                    = "/api/client/get/%d";
    private static final String CLIENT_GET_LIST_API_PATH               = "/api/client/get/list";
    private static final String CLIENT_UPDATE_API_PATH                 = "/api/client/update/%d";
    private static final String REQUESTABLE_SCOPES_DELETE_API_PATH     = "/api/client/extension/requestable_scopes/delete/%d";
    private static final String REQUESTABLE_SCOPES_GET_API_PATH        = "/api/client/extension/requestable_scopes/get/%d";
    private static final String REQUESTABLE_SCOPES_UPDATE_API_PATH     = "/api/client/extension/requestable_scopes/update/%d";
    private static final String GRANTED_SCOPES_GET_API_PATH            = "/api/client/granted_scopes/get/%d";
    private static final String GRANTED_SCOPES_DELETE_API_PATH         = "/api/client/granted_scopes/delete/%d";
    private static final String CLIENT_AUTHORIZATION_DELETE_API_PATH   = "/api/client/authorization/delete/%d";
    private static final String CLIENT_AUTHORIZATION_GET_LIST_API_PATH = "/api/client/authorization/get/list";
    private static final String CLIENT_AUTHORIZATION_UPDATE_API_PATH   = "/api/client/authorization/update/%d";


    private final String mBaseUrl;
    private final BasicCredentials mServiceOwnerCredentials;
    private final BasicCredentials mServiceCredentials;
    private final Settings mSettings;


    /**
     * The constructor with an instance of {@link AuthleteConfiguration}.
     *
     * <p>
     * The existence of a constructor of this type is a required by
     * {@link com.authlete.common.api.AuthleteApiFactory AuthleteApiFactory}.
     * </p>
     *
     * @param configuration
     *         An instance of {@link AuthleteConfiguration}.
     */
    public AuthleteApiImpl(AuthleteConfiguration configuration)
    {
        if (configuration == null)
        {
            throw new IllegalArgumentException("configuration is null.");
        }

        mServiceOwnerCredentials = createServiceOwnerCredentials(configuration);
        mServiceCredentials      = createServiceCredentials(configuration);
        mBaseUrl                 = createBaseUrl(configuration);
        mSettings                = new Settings();
    }


    /**
     * Create a {@link BasicCredentials} for the service owner.
     */
    private static BasicCredentials createServiceOwnerCredentials(AuthleteConfiguration configuration)
    {
        // API key and API secret of a Service Owner.
        String key    = configuration.getServiceOwnerApiKey();
        String secret = configuration.getServiceOwnerApiSecret();

        return new BasicCredentials(key, secret);
    }


    /**
     * Create a {@link BasicCredentials} for the service.
     */
    private static BasicCredentials createServiceCredentials(AuthleteConfiguration configuration)
    {
        // API key and API secret of a Service.
        String key    = configuration.getServiceApiKey();
        String secret = configuration.getServiceApiSecret();

        return new BasicCredentials(key, secret);
    }


    /**
     * Get 'Base URL' from the configuration. If the base URL ends with '/',
     * it is dropped.
     */
    private static String createBaseUrl(AuthleteConfiguration configuration)
    {
        String baseUrl = configuration.getBaseUrl();

        // If the configuration does not contain a base URL.
        if (baseUrl == null)
        {
            throw new IllegalArgumentException("The configuration does not have information about the base URL.");
        }

        try
        {
            // Check whether the format of the given URL is valid.
            new URL(baseUrl);
        }
        catch (MalformedURLException e)
        {
            // The format of the base URL is wrong.
            throw new IllegalArgumentException("The base URL is malformed.", e);
        }

        if (baseUrl.endsWith("/"))
        {
            // Drop the '/' at the end of the URL.
            return baseUrl.substring(0, baseUrl.length() - 1);
        }
        else
        {
            // Use the value of the base URL without modification.
            return baseUrl;
        }
    }


    /**
     * Call an API with HTTP GET method and Service Owner credentials (without query parameters).
     */
    private <TResponse> TResponse callServiceOwnerGetApi(
            String path, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callServiceOwnerGetApi(path, (Map<String, String>)null, responseClass);
    }


    /**
     * Call an API with HTTP GET method and Service Owner credentials.
     */
    private <TResponse> TResponse callServiceOwnerGetApi(
            String path, Map<String, String> queryParams,
            Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callGetApi(mServiceOwnerCredentials, path, queryParams, responseClass);
    }


    /**
     * Call an API with HTTP GET method and Service credentials (without query parameters).
     */
    private <TResponse> TResponse callServiceGetApi(
            String path, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callServiceGetApi(path, (Map<String, String>)null, responseClass);
    }


    /**
     * Call an API with HTTP GET method and Service credentials.
     */
    private <TResponse> TResponse callServiceGetApi(
            String path, Map<String, String> queryParams,
            Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callGetApi(mServiceCredentials, path, queryParams, responseClass);
    }


    /**
     * Call an API with HTTP GET method.
     */
    private <TResponse> TResponse callGetApi(
            BasicCredentials credentials, String path, Map<String, String> queryParams,
            Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callApi(HttpMethod.GET, credentials, path, queryParams, (Object)null, responseClass);
    }


    /**
     * Call an API with HTTP POST method and Service Owner credentials (without query parameters).
     */
    private <TResponse> TResponse callServiceOwnerPostApi(
            String path, Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callServiceOwnerPostApi(path, (Map<String, String>)null, requestBody, responseClass);
    }


    /**
     * Call an API with HTTP POST method and Service Owner credentials.
     */
    private <TResponse> TResponse callServiceOwnerPostApi(
            String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callPostApi(mServiceOwnerCredentials, path, queryParams, requestBody, responseClass);
    }


    /**
     * Call an API with HTTP POST method and Service credentials (without query parameters).
     */
    private <TResponse> TResponse callServicePostApi(
            String path, Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callServicePostApi(path, (Map<String, String>)null, requestBody, responseClass);
    }


    /**
     * Call an API with HTTP POST method and Service credentials.
     */
    private <TResponse> TResponse callServicePostApi(
            String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callPostApi(mServiceCredentials, path, queryParams, requestBody, responseClass);
    }


    /**
     * Call an API with HTTP POST method.
     */
    private <TResponse> TResponse callPostApi(
            BasicCredentials credentials, String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return callApi(HttpMethod.POST, credentials, path, queryParams, requestBody, responseClass);
    }


    /**
     * Call an API with HTTP DELETE method and Service Owner credentials (without query parameters).
     */
    private <TResponse> void callServiceOwnerDeleteApi(String path) throws AuthleteApiException
    {
        callServiceOwnerDeleteApi(path, (Map<String, String>)null);
    }


    /**
     * Call an API with HTTP DELETE method and Service Owner credentials.
     */
    private <TResponse> void callServiceOwnerDeleteApi(
            String path, Map<String, String> queryParams) throws AuthleteApiException
    {
        callDeleteApi(mServiceOwnerCredentials, path, queryParams);
    }


    /**
     * Call an API with HTTP DELETE method and Service credentials (without query parameters).
     */
    private <TResponse> void callServiceDeleteApi(String path) throws AuthleteApiException
    {
        callServiceDeleteApi(path, (Map<String, String>)null);
    }


    /**
     * Call an API with HTTP DELETE method and Service credentials.
     */
    private <TResponse> void callServiceDeleteApi(
            String path, Map<String, String> queryParams) throws AuthleteApiException
    {
        callDeleteApi(mServiceCredentials, path, queryParams);
    }


    /**
     * Call an API with HTTP DELETE method.
     */
    private <TResponse> void callDeleteApi(
            BasicCredentials credentials, String path,
            Map<String, String> queryParams) throws AuthleteApiException
    {
        callApi(HttpMethod.DELETE, credentials, path, queryParams, (Object)null, (Class<TResponse>)null);
    }


    /**
     * Call an API.
     */
    private <TResponse> TResponse callApi(
            HttpMethod method, BasicCredentials credentials,
            String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        // Create a connection to the Authlete API.
        HttpURLConnection con = createConnection(
                method, credentials, mBaseUrl, path, queryParams, mSettings);

        try
        {
            // Communicate with the API and get a response.
            return communicate(con, requestBody, responseClass);
        }
        finally
        {
            // Disconnect the connection in any case.
            con.disconnect();
        }
    }


    private static HttpURLConnection createConnection(
            HttpMethod method, BasicCredentials credentials,
            String baseUrl, String path, Map<String, String> queryParams,
            Settings settings) throws AuthleteApiException
    {
        try
        {
            // Open a connection to the Authlete API.
            return openConnection(
                    method, credentials, baseUrl, path, queryParams, settings);
        }
        catch (Throwable cause)
        {
            // Failed to open a connection.
            throw createAuthleteApiException(cause, null);
        }
    }


    private static HttpURLConnection openConnection(
            HttpMethod method, BasicCredentials credentials, String baseUrl,
            String path, Map<String, String> queryParams,
            Settings settings) throws IOException
    {
        // URL of an Authlete API.
        URL url = buildUrl(baseUrl, path, queryParams);

        // Open a connection to the Authlete API.
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        // Set HTTP method.
        con.setRequestMethod(method.name());

        // Set 'Authorization' HTTP header to access the Authlete API.
        con.setRequestProperty("Authorization", credentials.format());

        // Set 'Accept' HTTP header for a JSON response.
        con.setRequestProperty("Accept", "application/json");

        // Expect a response body.
        con.setDoInput(true);

        // Set a connection timeout in milliseconds.
        con.setConnectTimeout(settings.getConnectionTimeout());

        // Set a read timeout in milliseconds.
        con.setReadTimeout(settings.getReadTimeout());

        return con;
    }


    private static URL buildUrl(
            String baseUrl, String path, Map<String, String> queryParams) throws MalformedURLException
    {
        // {scheme}://{host}{path}
        StringBuffer buf = new StringBuffer(baseUrl).append(path);

        // ?key1=value1&key2=value2&...
        appendQueryParams(buf, queryParams);

        return new URL(buf.toString());
    }


    private static void appendQueryParams(StringBuffer buf, Map<String, String> params)
    {
        if (params == null || params.size() == 0)
        {
            // Nothing to append.
            return;
        }

        buf.append('?');

        for (Map.Entry<String, String> entry : params.entrySet())
        {
            // Key
            String key = toQueryParamKey(entry.getKey());

            if (key == null)
            {
                // Ignore this invalid key.
                continue;
            }

            // Value
            String value = toQueryParamValue(entry.getValue());

            // Key=Value&
            buf.append(key).append('=').append(value).append('&');
        }

        // Remove the last '&' (or '?' if none of the entries in 'params' has a valid key).
        buf.setLength(buf.length() - 1);
    }


    private static String toQueryParamKey(String str)
    {
        if (str == null || str.length() == 0)
        {
            // Invalid key.
            return null;
        }

        return urlEncode(str);
    }


    private static String toQueryParamValue(String str)
    {
        if (str == null || str.length() == 0)
        {
            return "";
        }

        return urlEncode(str);
    }


    private static String urlEncode(String str)
    {
        try
        {
            return URLEncoder.encode(str, UTF_8);
        }
        catch (UnsupportedEncodingException e)
        {
            // This never happens because UTF-8 is always supported.
            return str;
        }
    }


    @SuppressWarnings("unchecked")
    private <TResponse> TResponse communicate(
            HttpURLConnection con, Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        // If the request has a request body.
        if (requestBody != null)
        {
            // Write the request body.
            writeRequestBody(con, requestBody);
        }

        // Read the response body. (JSON is expected)
        String responseBody = readResponseBody(con);

        // If the response does not include any entity.
        if (responseBody == null)
        {
            // No response body.
            return null;
        }

        if (responseClass == String.class || responseClass == null)
        {
            // No need to convert the type.
            return (TResponse)responseBody;
        }

        // Convert the JSON into an object.
        return Utils.fromJson(responseBody, responseClass);
    }


    private static void writeRequestBody(HttpURLConnection con, Object requestBody) throws AuthleteApiException
    {
        // Set 'Content-Type' to send JSON.
        con.setRequestProperty("Content-Type", "application/json");

        // Use the connection as an output stream.
        con.setDoOutput(true);

        try
        {
            // Write the request body.
            writeContent(con, requestBody);
        }
        catch (Throwable cause)
        {
            // Failed to write the request body.
            throw createAuthleteApiException(cause, con);
        }
    }


    private static void writeContent(HttpURLConnection con, Object requestBody) throws IOException
    {
        // Convert the object to JSON.
        String json = Utils.toJson(requestBody);

        // Get the UTF-8 representation.
        byte[] bytes = getBytesUTF8(json);

        // Open the stream to send data to Authlete server.
        OutputStream out = con.getOutputStream();

        // Write the request body.
        out.write(bytes);
        out.flush();
    }


    private static byte[] getBytesUTF8(String str)
    {
        try
        {
            // Get the UTF-8 representation.
            return str.getBytes(UTF_8);
        }
        catch (UnsupportedEncodingException e)
        {
            // This will never happen because UTF-8 is always supported.
            return null;
        }
    }


    private static String readResponseBody(HttpURLConnection con) throws AuthleteApiException
    {
        try
        {
            // Read the response body.
            return readContent(con);
        }
        catch (Throwable cause)
        {
            // Failed to read the response body.
            throw createAuthleteApiException(cause, con);
        }
    }


    private static String readContent(HttpURLConnection con) throws IOException
    {
        // Call readInputStream() with the stream, the expected length and charset.
        return readInputStream(con.getInputStream(), con.getContentLength(), UTF_8);
    }


    private static String readInputStream(
            InputStream in, int expectedLength, String charset) throws IOException
    {
        // Create a ByteArrayOutputStream instance to store the content in the response.
        ByteArrayOutputStream out = createByteArrayOutputStream(expectedLength);

        // Read all bytes from the input stream of the connection and write them to out.
        copy(new BufferedInputStream(in), out);

        // Convert the bytes into a String instance.
        return out.toString(charset);
    }


    private static ByteArrayOutputStream createByteArrayOutputStream(int expectedLength)
    {
        if (expectedLength < 0)
        {
            // Create an instance with the default buffer size.
            return new ByteArrayOutputStream();
        }
        else
        {
            // Specify the initial buffer size for better performance.
            return new ByteArrayOutputStream(expectedLength);
        }
    }


    private static void copy(InputStream in, OutputStream out) throws IOException
    {
        byte buf[] = new byte[8096];
        int len;

        while ((len = in.read(buf)) != -1)
        {
            out.write(buf, 0, len);
        }
    }


    private static AuthleteApiException createAuthleteApiException(Throwable cause, HttpURLConnection con)
    {
        // Error message.
        String message = cause.getMessage();

        if (con == null)
        {
            // Create an exception without HTTP response information.
            return new AuthleteApiException(message, cause);
        }

        // Collect information to build an AuthleteApiException instance.

        // HTTP status code. 0 if not available.
        int statusCode = extractStatusCode(con);

        // HTTP status message. null if not available.
        String statusMessage = extractStatusMessage(con);

        // Response body.
        String responseBody = extractErrorData(con);

        // HTTP response headers.
        Map<String, List<String>> headers = con.getHeaderFields();

        // Build an AuthleteApiException instance with the collected information.
        return new AuthleteApiException(message, cause, statusCode, statusMessage, responseBody, headers);
    }


    private static int extractStatusCode(HttpURLConnection con)
    {
        int statusCode;

        try
        {
            // Get the status code of the HTTP response.
            statusCode = con.getResponseCode();
        }
        catch (Throwable cause)
        {
            // Failed to get the status code.
            // Status code is not available.
            return 0;
        }

        if (statusCode < 0)
        {
            // Status code is not available.
            return 0;
        }

        // The status code of the HTTP response.
        return statusCode;
    }


    private static String extractStatusMessage(HttpURLConnection con)
    {
        try
        {
            // Get the status message of the HTTP response.
            return con.getResponseMessage();
        }
        catch (Throwable cause)
        {
            // Failed to get the status message.
            // Status message is not available.
            return null;
        }
    }


    private static String extractErrorData(HttpURLConnection con)
    {
        // JavaDoc of HttpURLConnection.getErrorStream() says as follows.
        //
        //   Returns the error stream if the connection failed but the server
        //   sent useful data nonetheless. The typical example is when an HTTP
        //   server responds with a 404, which will cause a FileNotFoundException
        //   to be thrown in connect, but the server sent an HTML help page with
        //   suggestions as to what to do.
        //
        //   This method will not cause a connection to be initiated. If the
        //   connection was not connected, or if the server did not have an
        //   error while connecting or if the server had an error but no error
        //   data was sent, this method will return null. This is the default.
        //

        try
        {
            InputStream in = con.getErrorStream();

            if (in != null)
            {
                // Read data from the error stream.
                return readInputStream(in, -1, UTF_8);
            }
            else
            {
                // Read the request body. Note that this may have already been tried.
                return readContent(con);
            }
        }
        catch (Throwable cause)
        {
            // Give up getting error data.
            return null;
        }
    }


    private static Map<String, String> buildMap(Object... pairs)
    {
        int len = pairs.length;
        Map<String, String> map = new LinkedHashMap<String, String>();

        for (int i = 0; i < len; ++i)
        {
            String key = (String)pairs[i];
            Object obj = (len <= ++i)  ? null : pairs[i];
            String val = (obj == null) ? null : obj.toString();

            map.put(key, val);
        }

        return map;
    }


    @Override
    public AuthorizationResponse authorization(AuthorizationRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_AUTHORIZATION_API_PATH, request, AuthorizationResponse.class);
    }


    @Override
    public AuthorizationFailResponse authorizationFail(AuthorizationFailRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_AUTHORIZATION_FAIL_API_PATH, request, AuthorizationFailResponse.class);
    }


    @Override
    public AuthorizationIssueResponse authorizationIssue(AuthorizationIssueRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_AUTHORIZATION_ISSUE_API_PATH, request, AuthorizationIssueResponse.class);
    }


    @Override
    public TokenResponse token(TokenRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_API_PATH, request, TokenResponse.class);
    }


    @Override
    public TokenCreateResponse tokenCreate(TokenCreateRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_CREATE_API_PATH, request, TokenCreateResponse.class);
    }


    @Override
    public TokenFailResponse tokenFail(TokenFailRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_FAIL_API_PATH, request, TokenFailResponse.class);
    }


    @Override
    public TokenIssueResponse tokenIssue(TokenIssueRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_ISSUE_API_PATH, request, TokenIssueResponse.class);
    }


    @Override
    public TokenUpdateResponse tokenUpdate(TokenUpdateRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_UPDATE_API_PATH, request, TokenUpdateResponse.class);
    }


    @Override
    public RevocationResponse revocation(RevocationRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_REVOCATION_API_PATH, request, RevocationResponse.class);
    }


    @Override
    public UserInfoResponse userinfo(UserInfoRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_USERINFO_API_PATH, request, UserInfoResponse.class);
    }


    @Override
    public UserInfoIssueResponse userinfoIssue(UserInfoIssueRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_USERINFO_ISSUE_API_PATH, request, UserInfoIssueResponse.class);
    }


    @Override
    public IntrospectionResponse introspection(IntrospectionRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_INTROSPECTION_API_PATH, request, IntrospectionResponse.class);
    }


    @Override
    public StandardIntrospectionResponse standardIntrospection(
            StandardIntrospectionRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_INTROSPECTION_STANDARD_API_PATH, request, StandardIntrospectionResponse.class);
    }


    @Override
    public Service createServie(Service service) throws AuthleteApiException
    {
        return callServiceOwnerPostApi(
                SERVICE_CREATE_API_PATH, service, Service.class);
    }


    @Override
    public void deleteService(long apiKey) throws AuthleteApiException
    {
        callServiceOwnerDeleteApi(
                String.format(SERVICE_DELETE_API_PATH, apiKey));
    }


    @Override
    public Service getService(long apiKey) throws AuthleteApiException
    {
        return callServiceOwnerGetApi(
                String.format(SERVICE_GET_API_PATH, apiKey), Service.class);
    }


    @Override
    public ServiceListResponse getServiceList() throws AuthleteApiException
    {
        return callServiceOwnerGetApi(
                SERVICE_GET_LIST_API_PATH, ServiceListResponse.class);
    }


    @Override
    public ServiceListResponse getServiceList(int start, int end) throws AuthleteApiException
    {
        return callServiceOwnerGetApi(
                SERVICE_GET_LIST_API_PATH,
                buildMap("start", start, "end", end),
                ServiceListResponse.class);
    }


    @Override
    public Service updateService(Service service) throws AuthleteApiException
    {
        return callServiceOwnerPostApi(
                String.format(SERVICE_UPDATE_API_PATH, service.getApiKey()),
                service, Service.class);
    }


    @Override
    public String getServiceJwks() throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_JWKS_GET_API_PATH, String.class);
    }


    @Override
    public String getServiceJwks(boolean pretty, boolean includePrivateKeys) throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_JWKS_GET_API_PATH,
                buildMap("pretty", pretty, "includePrivateKeys", includePrivateKeys),
                String.class);
    }


    @Override
    public String getServiceConfiguration() throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_CONFIGURATION_API_PATH, String.class);
    }


    @Override
    public String getServiceConfiguration(boolean pretty) throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_CONFIGURATION_API_PATH,
                buildMap("pretty", pretty),
                String.class);
    }


    @Override
    public Client createClient(Client client) throws AuthleteApiException
    {
        return callServicePostApi(
                CLIENT_CREATE_API_PATH, client, Client.class);
    }


    @Override
    public void deleteClient(long clientId) throws AuthleteApiException
    {
        callServiceDeleteApi(
                String.format(CLIENT_DELETE_API_PATH, clientId));
    }


    @Override
    public Client getClient(long clientId) throws AuthleteApiException
    {
        return callServiceGetApi(
                String.format(CLIENT_GET_API_PATH, clientId), Client.class);
    }


    @Override
    public ClientListResponse getClientList() throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH, ClientListResponse.class);
    }


    @Override
    public ClientListResponse getClientList(String developer) throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH,
                buildMap("developer", developer),
                ClientListResponse.class);
    }


    @Override
    public ClientListResponse getClientList(int start, int end) throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH,
                buildMap("start", start, "end", end),
                ClientListResponse.class);
    }


    @Override
    public ClientListResponse getClientList(String developer, int start, int end) throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH,
                buildMap("developer", developer, "start", start, "end", end),
                ClientListResponse.class);
    }


    @Override
    public Client updateClient(Client client) throws AuthleteApiException
    {
        return callServicePostApi(
                String.format(CLIENT_UPDATE_API_PATH, client.getClientId()),
                client, Client.class);
    }


    @Override
    public String[] getRequestableScopes(long clientId) throws AuthleteApiException
    {
        // Call the API.
        RequestableScopes response = callServiceGetApi(
                String.format(REQUESTABLE_SCOPES_GET_API_PATH, clientId),
                RequestableScopes.class);

        // Extract 'requestableScopes' from the response.
        return extractRequestableScopes(response);
    }


    @Override
    public String[] setRequestableScopes(long clientId, String[] scopes) throws AuthleteApiException
    {
        // Prepare a request body.
        RequestableScopes request = new RequestableScopes(scopes);

        // Call the API.
        RequestableScopes response = callServicePostApi(
                String.format(REQUESTABLE_SCOPES_UPDATE_API_PATH, clientId),
                request, RequestableScopes.class);

        // Extract 'requestableScopes' from the response.
        return extractRequestableScopes(response);
    }


    @Override
    public void deleteRequestableScopes(long clientId) throws AuthleteApiException
    {
        callServiceDeleteApi(
                String.format(REQUESTABLE_SCOPES_DELETE_API_PATH, clientId));
    }


    private static String[] extractRequestableScopes(RequestableScopes object)
    {
        if (object != null)
        {
            return object.getRequestableScopes();
        }
        else
        {
            return null;
        }
    }


    @Override
    public GrantedScopesGetResponse getGrantedScopes(long clientId, String subject)
    {
        // Prepare a request body.
        GrantedScopesRequest request = new GrantedScopesRequest(subject);

        return callServicePostApi(
                String.format(GRANTED_SCOPES_GET_API_PATH, clientId),
                request, GrantedScopesGetResponse.class);
    }


    @Override
    public void deleteGrantedScopes(long clientId, String subject)
    {
        // Prepare a request body.
        GrantedScopesRequest request = new GrantedScopesRequest(subject);

        callServicePostApi(
                String.format(GRANTED_SCOPES_DELETE_API_PATH, clientId),
                request, ApiResponse.class);
    }


    private static final class RequestableScopes
    {
        private String[] requestableScopes;


        @SuppressWarnings("unused")
        public RequestableScopes()
        {
        }


        public RequestableScopes(String[] requestableScopes)
        {
            this.requestableScopes = requestableScopes;
        }


        public String[] getRequestableScopes()
        {
            return requestableScopes;
        }


        @SuppressWarnings("unused")
        public void setRequestableScopes(String[] requestableScopes)
        {
            this.requestableScopes = requestableScopes;
        }
    }


    private static final class GrantedScopesRequest
    {
        private String subject;


        public GrantedScopesRequest(String subject)
        {
            this.subject = subject;
        }


        @SuppressWarnings("unused")
        public String getSubject()
        {
            return subject;
        }


        @SuppressWarnings("unused")
        public void setSubject(String subject)
        {
            this.subject = subject;
        }
    }


    @Override
    public void deleteClientAuthorization(long clientId, String subject) throws AuthleteApiException
    {
        // Prepare a request body.
        ClientAuthorizationDeleteRequest request = new ClientAuthorizationDeleteRequest(subject);

        callServicePostApi(
                String.format(CLIENT_AUTHORIZATION_DELETE_API_PATH, clientId),
                request, ApiResponse.class);
    }


    @Override
    public AuthorizedClientListResponse getClientAuthorizationList(ClientAuthorizationGetListRequest request) throws AuthleteApiException
    {
        return callServicePostApi(
                CLIENT_AUTHORIZATION_GET_LIST_API_PATH,
                request, AuthorizedClientListResponse.class);
    }


    @Override
    public void updateClientAuthorization(long clientId, ClientAuthorizationUpdateRequest request) throws AuthleteApiException
    {
        callServicePostApi(
                String.format(CLIENT_AUTHORIZATION_UPDATE_API_PATH, clientId),
                request, ApiResponse.class);
    }


    @Override
    public Settings getSettings()
    {
        return mSettings;
    }
}
