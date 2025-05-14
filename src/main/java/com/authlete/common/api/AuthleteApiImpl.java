/*
 * Copyright (C) 2017-2024 Authlete, Inc.
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
import java.util.Map.Entry;
import com.authlete.common.conf.AuthleteConfiguration;
import com.authlete.common.dto.*;
import com.authlete.common.types.TokenStatus;
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


    private class HttpHeader
    {
        private static final String ACCEPT        = "Accept";
        private static final String AUTHORIZATION = "Authorization";
        private static final String CONTENT_TYPE  = "Content-Type";
    }


    private static final String AUTH_AUTHORIZATION_API_PATH               = "/api/auth/authorization";
    private static final String AUTH_AUTHORIZATION_FAIL_API_PATH          = "/api/auth/authorization/fail";
    private static final String AUTH_AUTHORIZATION_ISSUE_API_PATH         = "/api/auth/authorization/issue";
    private static final String AUTH_AUTHORIZATION_TICKET_INFO_API_PATH   = "/api/auth/authorization/ticket/info";
    private static final String AUTH_AUTHORIZATION_TICKET_UPDATE_API_PATH = "/api/auth/authorization/ticket/update";
    private static final String AUTH_TOKEN_API_PATH                       = "/api/auth/token";
    private static final String AUTH_TOKEN_CREATE_API_PATH                = "/api/auth/token/create";
    private static final String AUTH_TOKEN_DELETE_API_PATH                = "/api/auth/token/delete/%s";
    private static final String AUTH_TOKEN_FAIL_API_PATH                  = "/api/auth/token/fail";
    private static final String AUTH_TOKEN_ISSUE_API_PATH                 = "/api/auth/token/issue";
    private static final String AUTH_TOKEN_REVOKE_API_PATH                = "/api/auth/token/revoke";
    private static final String AUTH_TOKEN_UPDATE_API_PATH                = "/api/auth/token/update";
    private static final String AUTH_TOKEN_GET_LIST_API_PATH              = "/api/auth/token/get/list";
    private static final String AUTH_REVOCATION_API_PATH                  = "/api/auth/revocation";
    private static final String AUTH_USERINFO_API_PATH                    = "/api/auth/userinfo";
    private static final String AUTH_USERINFO_ISSUE_API_PATH              = "/api/auth/userinfo/issue";
    private static final String AUTH_INTROSPECTION_API_PATH               = "/api/auth/introspection";
    private static final String AUTH_INTROSPECTION_STANDARD_API_PATH      = "/api/auth/introspection/standard";
    private static final String SERVICE_CONFIGURATION_API_PATH            = "/api/service/configuration";
    private static final String SERVICE_CREATE_API_PATH                   = "/api/service/create";
    private static final String SERVICE_DELETE_API_PATH                   = "/api/service/delete/%d";
    private static final String SERVICE_GET_API_PATH                      = "/api/service/get/%d";
    private static final String SERVICE_GET_LIST_API_PATH                 = "/api/service/get/list";
    private static final String SERVICE_JWKS_GET_API_PATH                 = "/api/service/jwks/get";
    private static final String SERVICE_UPDATE_API_PATH                   = "/api/service/update/%d";
    private static final String CLIENT_CREATE_API_PATH                    = "/api/client/create";
    private static final String CLIENT_REGISTRATION_API_PATH              = "/api/client/registration";
    private static final String CLIENT_REGISTRATION_GET_API_PATH          = "/api/client/registration/get";
    private static final String CLIENT_REGISTRATION_UPDATE_API_PATH       = "/api/client/registration/update";
    private static final String CLIENT_REGISTRATION_DELETE_API_PATH       = "/api/client/registration/delete";
    private static final String CLIENT_DELETE_API_PATH                    = "/api/client/delete/%s";
    private static final String CLIENT_GET_API_PATH                       = "/api/client/get/%s";
    private static final String CLIENT_GET_LIST_API_PATH                  = "/api/client/get/list";
    private static final String CLIENT_SECRET_REFRESH_API_PATH            = "/api/client/secret/refresh/%s";
    private static final String CLIENT_SECRET_UPDATE_API_PATH             = "/api/client/secret/update/%s";
    private static final String CLIENT_UPDATE_API_PATH                    = "/api/client/update/%d";
    private static final String REQUESTABLE_SCOPES_DELETE_API_PATH        = "/api/client/extension/requestable_scopes/delete/%d";
    private static final String REQUESTABLE_SCOPES_GET_API_PATH           = "/api/client/extension/requestable_scopes/get/%d";
    private static final String REQUESTABLE_SCOPES_UPDATE_API_PATH        = "/api/client/extension/requestable_scopes/update/%d";
    private static final String GRANTED_SCOPES_GET_API_PATH               = "/api/client/granted_scopes/get/%d";
    private static final String GRANTED_SCOPES_DELETE_API_PATH            = "/api/client/granted_scopes/delete/%d";
    private static final String CLIENT_AUTHORIZATION_DELETE_API_PATH      = "/api/client/authorization/delete/%d";
    private static final String CLIENT_AUTHORIZATION_GET_LIST_API_PATH    = "/api/client/authorization/get/list";
    private static final String CLIENT_AUTHORIZATION_UPDATE_API_PATH      = "/api/client/authorization/update/%d";
    private static final String JOSE_VERIFY_API_PATH                      = "/api/jose/verify";
    private static final String BC_AUTHENTICATION_API_PATH                = "/api/backchannel/authentication";
    private static final String BC_AUTHENTICATION_COMPLETE_API_PATH       = "/api/backchannel/authentication/complete";
    private static final String BC_AUTHENTICATION_FAIL_API_PATH           = "/api/backchannel/authentication/fail";
    private static final String BC_AUTHENTICATION_ISSUE_API_PATH          = "/api/backchannel/authentication/issue";
    private static final String DEVICE_AUTHORIZATION_API_PATH             = "/api/device/authorization";
    private static final String DEVICE_COMPLETE_API_PATH                  = "/api/device/complete";
    private static final String DEVICE_VERIFICATION_API_PATH              = "/api/device/verification";
    private static final String PUSHED_AUTH_REQ_API_PATH                  = "/api/pushed_auth_req";
    private static final String HSK_CREATE_API_PATH                       = "/api/hsk/create";
    private static final String HSK_DELETE_API_PATH                       = "/api/hsk/delete/%s";
    private static final String HSK_GET_API_PATH                          = "/api/hsk/get/%s";
    private static final String HSK_GET_LIST_API_PATH                     = "/api/hsk/get/list";
    private static final String ECHO_API_PATH                             = "/api/misc/echo";
    private static final String GM_API_PATH                               = "/api/gm";
    private static final String CLIENT_LOCK_FLAG_UPDATE_API_PATH          = "/api/client/lock_flag/update";
    private static final String FEDERATION_CONFIGURATION_API_PATH         = "/api/federation/configuration";
    private static final String FEDERATION_REGISTRATION_API_PATH          = "/api/federation/registration";
    private static final String VCI_METADATA_API_PATH                     = "/api/vci/metadata";
    private static final String VCI_JWT_ISSUER_API_PATH                   = "/api/vci/jwtissuer";
    private static final String VCI_JWKS_API_PATH                         = "/api/vci/jwks";
    private static final String VCI_OFFER_CREATE_API_PATH                 = "/api/vci/offer/create";
    private static final String VCI_OFFER_INFO_API_PATH                   = "/api/vci/offer/info";
    private static final String VCI_SINGLE_PARSE_API_PATH                 = "/api/vci/single/parse";
    private static final String VCI_SINGLE_ISSUE_API_PATH                 = "/api/vci/single/issue";
    private static final String VCI_BATCH_PARSE_API_PATH                  = "/api/vci/batch/parse";
    private static final String VCI_BATCH_ISSUE_API_PATH                  = "/api/vci/batch/issue";
    private static final String VCI_DEFERRED_PARSE_API_PATH               = "/api/vci/deferred/parse";
    private static final String VCI_DEFERRED_ISSUE_API_PATH               = "/api/vci/deferred/issue";
    private static final String ID_TOKEN_REISSUE_API_PATH                 = "/api/idtoken/reissue";
    private static final String NATIVE_SSO_API_PATH                       = "/api/nativesso";
    private static final String NATIVE_SSO_LOGOUT_API_PATH                = "/api/nativesso/logout";

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
        System.out.println("165: AuthleteApiImpl");
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
            String path, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callServiceOwnerGetApi(path, (Map<String, String>)null, responseClass, options);
    }


    /**
     * Call an API with HTTP GET method and Service Owner credentials.
     */
    private <TResponse> TResponse callServiceOwnerGetApi(
            String path, Map<String, String> queryParams,
            Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callGetApi(mServiceOwnerCredentials, path, queryParams, responseClass, options);
    }


    /**
     * Call an API with HTTP GET method and Service credentials (without query parameters).
     */
    private <TResponse> TResponse callServiceGetApi(
            String path, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(path, (Map<String, String>)null, responseClass, options);
    }


    /**
     * Call an API with HTTP GET method and Service credentials.
     */
    private <TResponse> TResponse callServiceGetApi(
            String path, Map<String, String> queryParams,
            Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callGetApi(mServiceCredentials, path, queryParams, responseClass, options);
    }


    /**
     * Call an API with HTTP GET method.
     */
    private <TResponse> TResponse callGetApi(
            BasicCredentials credentials, String path, Map<String, String> queryParams,
            Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callApi(HttpMethod.GET, credentials, path, queryParams, (Object)null, responseClass, options);
    }


    /**
     * Call an API with HTTP POST method and Service Owner credentials (without query parameters).
     */
    private <TResponse> TResponse callServiceOwnerPostApi(
            String path, Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callServiceOwnerPostApi(path, (Map<String, String>)null, requestBody, responseClass, options);
    }


    /**
     * Call an API with HTTP POST method and Service Owner credentials.
     */
    private <TResponse> TResponse callServiceOwnerPostApi(
            String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callPostApi(mServiceOwnerCredentials, path, queryParams, requestBody, responseClass, options);
    }


    /**
     * Call an API with HTTP POST method and Service credentials (without query parameters).
     */
    private <TResponse> TResponse callServicePostApi(
            String path, Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callServicePostApi(path, (Map<String, String>)null, requestBody, responseClass, options);
    }


    /**
     * Call an API with HTTP POST method and Service credentials.
     */
    private <TResponse> TResponse callServicePostApi(
            String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callPostApi(mServiceCredentials, path, queryParams, requestBody, responseClass, options);
    }


    /**
     * Call an API with HTTP POST method.
     */
    private <TResponse> TResponse callPostApi(
            BasicCredentials credentials, String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callApi(HttpMethod.POST, credentials, path, queryParams, requestBody, responseClass, options);
    }


    /**
     * Call an API with HTTP DELETE method and Service Owner credentials (without query parameters).
     */
    private <TResponse> void callServiceOwnerDeleteApi(String path, Options options) throws AuthleteApiException
    {
        callServiceOwnerDeleteApi(path, (Map<String, String>)null, options);
    }


    /**
     * Call an API with HTTP DELETE method and Service Owner credentials.
     */
    private <TResponse> void callServiceOwnerDeleteApi(
            String path, Map<String, String> queryParams, Options options) throws AuthleteApiException
    {
        callDeleteApi(mServiceOwnerCredentials, path, queryParams, options);
    }


    /**
     * Call an API with HTTP DELETE method and Service credentials (without query parameters).
     */
    private <TResponse> void callServiceDeleteApi(String path, Options options) throws AuthleteApiException
    {
        callServiceDeleteApi(path, (Map<String, String>)null, options);
    }


    /**
     * Call an API with HTTP DELETE method and Service credentials.
     */
    private <TResponse> void callServiceDeleteApi(
            String path, Map<String, String> queryParams, Options options) throws AuthleteApiException
    {
        callDeleteApi(mServiceCredentials, path, queryParams, options);
    }


    /**
     * Call an API with HTTP DELETE method.
     */
    private <TResponse> void callDeleteApi(
            BasicCredentials credentials, String path,
            Map<String, String> queryParams, Options options) throws AuthleteApiException
    {
        callApi(HttpMethod.DELETE, credentials, path, queryParams, (Object)null, (Class<TResponse>)null, options);
    }


    /**
     * Call an API.
     */
    private <TResponse> TResponse callApi(
            HttpMethod method, BasicCredentials credentials,
            String path, Map<String, String> queryParams,
            Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        // Create a connection to the Authlete API.
        ConnectionContext ctx = createConnection(
                method, credentials, mBaseUrl, path, queryParams, options, mSettings);

        try
        {
            // Communicate with the API and get a response.
            return communicate(ctx, requestBody, responseClass);
        }
        finally
        {
            // Close the connection in any case.
            ctx.close();
        }
    }


    private static ConnectionContext createConnection(
            HttpMethod method, BasicCredentials credentials,
            String baseUrl, String path, Map<String, String> queryParams, Options options,
            Settings settings) throws AuthleteApiException
    {
        try
        {
            // Open a connection to the Authlete API.
            return openConnection(
                    method, credentials, baseUrl, path, queryParams, options, settings);
        }
        catch (Throwable cause)
        {
            // Failed to open a connection.
            throw createAuthleteApiException(cause, null);
        }
    }


    private static ConnectionContext openConnection(
            HttpMethod method, BasicCredentials credentials, String baseUrl,
            String path, Map<String, String> queryParams, Options options,
            Settings settings) throws IOException
    {
        // URL of an Authlete API.
        URL url = buildUrl(baseUrl, path, queryParams);

        // Open a connection to the Authlete API.
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        // Set HTTP method.
        con.setRequestMethod(method.name());

        if (credentials != null)
        {
            // Set 'Authorization' HTTP header to access the Authlete API.
            con.setRequestProperty(HttpHeader.AUTHORIZATION, credentials.format());
        }

        // Set 'Accept' HTTP header for a JSON response.
        con.setRequestProperty(HttpHeader.ACCEPT, "application/json");

        // Set custom request headers if needed.
        setCustomRequestHeaders(con, options);

        // Expect a response body.
        con.setDoInput(true);

        // Set a connection timeout in milliseconds.
        con.setConnectTimeout(settings.getConnectionTimeout());

        // Set a read timeout in milliseconds.
        con.setReadTimeout(settings.getReadTimeout());

        return new ConnectionContext(con);
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


    private static void setCustomRequestHeaders(HttpURLConnection con, Options options)
    {
        // Request options are not specified.
        if (options == null)
        {
            return;
        }

        // Custom headers.
        Map<String, String> headers = options.getHeaders();

        if (headers == null)
        {
            return;
        }

        for (Entry<String, String> e : headers.entrySet())
        {
            // The key of the header.
            String key = e.getKey();

            // Some header keys are reserved.
            if (isReservedRequestHeader(key))
            {
                continue;
            }

            con.setRequestProperty(key, e.getValue());
        }
    }


    private static boolean isReservedRequestHeader(String key)
    {
        return key.equalsIgnoreCase(HttpHeader.ACCEPT) ||
               key.equalsIgnoreCase(HttpHeader.AUTHORIZATION) ||
               key.equalsIgnoreCase(HttpHeader.CONTENT_TYPE);
    }


    @SuppressWarnings("unchecked")
    private <TResponse> TResponse communicate(
            ConnectionContext ctx, Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        System.out.println("606 communicate method called!");
        // If the request has a request body.
        if (requestBody != null)
        {
            // Write the request body.
            writeRequestBody(ctx, requestBody);
        }

        // Read the response body. (JSON is expected)
        String responseBody = readResponseBody(ctx);

        Map<String, List<String>> headers = ctx.connection().getHeaderFields();

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
        TResponse response = Utils.fromJson(responseBody, responseClass);

        if (response instanceof ApiResponse) {
           ((ApiResponse) response).setResponseHeaders(headers);
        }

        return response;
    }


    private static void writeRequestBody(ConnectionContext ctx, Object requestBody) throws AuthleteApiException
    {
        // Set 'Content-Type' to send JSON.
        ctx.property(HttpHeader.CONTENT_TYPE, "application/json");

        // Use the connection as an output stream.
        ctx.doOutput(true);

        try
        {
            // Write the request body.
            writeContent(ctx, requestBody);
        }
        catch (Throwable cause)
        {
            // Failed to write the request body.
            throw createAuthleteApiException(cause, ctx);
        }
    }


    private static void writeContent(ConnectionContext ctx, Object requestBody) throws IOException
    {
        // Convert the object to JSON.
        String json = Utils.toJson(requestBody);

        // Get the UTF-8 representation.
        byte[] bytes = getBytesUTF8(json);

        // Open the stream to send data to Authlete server.
        OutputStream out = ctx.outputStream();

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


    private static String readResponseBody(ConnectionContext ctx) throws AuthleteApiException
    {
        try
        {
            // Read the response body.
            return readContent(ctx);
        }
        catch (Throwable cause)
        {
            // Failed to read the response body.
            throw createAuthleteApiException(cause, ctx);
        }
    }


    private static String readContent(ConnectionContext ctx) throws IOException
    {
        // Call readInputStream() with the stream, the expected length and charset.
        return readInputStream(ctx.inputStream(), ctx.contentLength(), UTF_8);
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


    private static AuthleteApiException createAuthleteApiException(Throwable cause, ConnectionContext ctx)
    {
        // Error message.
        String message = cause.getMessage();

        if (ctx == null) {
            return new AuthleteApiException(message, cause);
        }

        HttpURLConnection con = ctx.connection();

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
        String responseBody = extractErrorData(ctx);

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


    private static String extractErrorData(ConnectionContext ctx)
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
            InputStream in = ctx.errorStream();

            if (in != null)
            {
                // Read data from the error stream.
                return readInputStream(in, -1, UTF_8);
            }
            else
            {
                // Read the request body. Note that this may have already been tried.
                return readContent(ctx);
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
    public AuthorizationResponse authorization(AuthorizationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_AUTHORIZATION_API_PATH, request, AuthorizationResponse.class, options);
    }


    @Override
    public AuthorizationFailResponse authorizationFail(AuthorizationFailRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_AUTHORIZATION_FAIL_API_PATH, request, AuthorizationFailResponse.class, options);
    }


    @Override
    public AuthorizationIssueResponse authorizationIssue(AuthorizationIssueRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_AUTHORIZATION_ISSUE_API_PATH, request, AuthorizationIssueResponse.class, options);
    }


    @Override
    public TokenResponse token(TokenRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_API_PATH, request, TokenResponse.class, options);
    }


    @Override
    public TokenCreateResponse tokenCreate(TokenCreateRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_CREATE_API_PATH, request, TokenCreateResponse.class, options);
    }


    @Override
    public void tokenDelete(String token, Options options) throws AuthleteApiException
    {
        callServiceDeleteApi(
                String.format(AUTH_TOKEN_DELETE_API_PATH, token), options);
    }


    @Override
    public TokenFailResponse tokenFail(TokenFailRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_FAIL_API_PATH, request, TokenFailResponse.class, options);
    }


    @Override
    public TokenIssueResponse tokenIssue(TokenIssueRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_ISSUE_API_PATH, request, TokenIssueResponse.class, options);
    }


    @Override
    public TokenRevokeResponse tokenRevoke(TokenRevokeRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_REVOKE_API_PATH, request, TokenRevokeResponse.class, options);
    }


    @Override
    public TokenUpdateResponse tokenUpdate(TokenUpdateRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_TOKEN_UPDATE_API_PATH, request, TokenUpdateResponse.class, options);
    }


    @Override
    public TokenListResponse getTokenList(Options options) throws AuthleteApiException
    {
        return getTokenList(TokenStatus.ALL, options);
    }


    @Override
    public TokenListResponse getTokenList(TokenStatus tokenStatus, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                AUTH_TOKEN_GET_LIST_API_PATH,
                buildMap("tokenStatus", tokenStatus),
                TokenListResponse.class, options);
    }


    @Override
    public TokenListResponse getTokenList(String clientIdentifier, String subject, Options options) throws AuthleteApiException
    {
        return getTokenList(clientIdentifier, subject, TokenStatus.ALL, options);
    }


    @Override
    public TokenListResponse getTokenList(String clientIdentifier, String subject, TokenStatus tokenStatus, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                AUTH_TOKEN_GET_LIST_API_PATH,
                buildMap("clientIdentifier", clientIdentifier, "subject", subject, "tokenStatus", tokenStatus),
                TokenListResponse.class, options);
    }


    @Override
    public TokenListResponse getTokenList(int start, int end, Options options) throws AuthleteApiException
    {
        return getTokenList(start, end, TokenStatus.ALL, options);
    }


    @Override
    public TokenListResponse getTokenList(int start, int end, TokenStatus tokenStatus, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                AUTH_TOKEN_GET_LIST_API_PATH,
                buildMap("start", start, "end", end, "tokenStatus", tokenStatus),
                TokenListResponse.class, options);
    }


    @Override
    public TokenListResponse getTokenList(String clientIdentifier, String subject,
                                          int start, int end, Options options) throws AuthleteApiException
    {
        return getTokenList(clientIdentifier, subject, start, end, TokenStatus.ALL, options);
    }


    @Override
    public TokenListResponse getTokenList(String clientIdentifier, String subject,
            int start, int end, TokenStatus tokenStatus, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                AUTH_TOKEN_GET_LIST_API_PATH,
                buildMap("clientIdentifier", clientIdentifier, "subject", subject, "start", start, "end", end, "tokenStatus", tokenStatus),
                TokenListResponse.class, options);
    }


    @Override
    public RevocationResponse revocation(RevocationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_REVOCATION_API_PATH, request, RevocationResponse.class, options);
    }


    @Override
    public UserInfoResponse userinfo(UserInfoRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_USERINFO_API_PATH, request, UserInfoResponse.class, options);
    }


    @Override
    public UserInfoIssueResponse userinfoIssue(UserInfoIssueRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_USERINFO_ISSUE_API_PATH, request, UserInfoIssueResponse.class, options);
    }


    @Override
    public IntrospectionResponse introspection(IntrospectionRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_INTROSPECTION_API_PATH, request, IntrospectionResponse.class, options);
    }


    @Override
    public StandardIntrospectionResponse standardIntrospection(
            StandardIntrospectionRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                AUTH_INTROSPECTION_STANDARD_API_PATH, request, StandardIntrospectionResponse.class, options);
    }


    @Override
    public Service createService(Service service, Options options) throws AuthleteApiException
    {
        return callServiceOwnerPostApi(
                SERVICE_CREATE_API_PATH, service, Service.class, options);
    }


    @Override
    public Service createServie(Service service) throws AuthleteApiException
    {
        return createService(service);
    }


    @Override
    public void deleteService(long apiKey, Options options) throws AuthleteApiException
    {
        callServiceOwnerDeleteApi(
                String.format(SERVICE_DELETE_API_PATH, apiKey), options);
    }


    @Override
    public Service getService(long apiKey, Options options) throws AuthleteApiException
    {
        return callServiceOwnerGetApi(
                String.format(SERVICE_GET_API_PATH, apiKey), Service.class, options);
    }


    @Override
    public ServiceListResponse getServiceList(Options options) throws AuthleteApiException
    {
        return callServiceOwnerGetApi(
                SERVICE_GET_LIST_API_PATH, ServiceListResponse.class, options);
    }


    @Override
    public ServiceListResponse getServiceList(int start, int end, Options options) throws AuthleteApiException
    {
        return callServiceOwnerGetApi(
                SERVICE_GET_LIST_API_PATH,
                buildMap("start", start, "end", end),
                ServiceListResponse.class, options);
    }


    @Override
    public Service updateService(Service service, Options options) throws AuthleteApiException
    {
        return callServiceOwnerPostApi(
                String.format(SERVICE_UPDATE_API_PATH, service.getApiKey()),
                service, Service.class, options);
    }


    @Override
    public String getServiceJwks(Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_JWKS_GET_API_PATH, String.class, options);
    }


    @Override
    public String getServiceJwks(boolean pretty, boolean includePrivateKeys, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_JWKS_GET_API_PATH,
                buildMap("pretty", pretty, "includePrivateKeys", includePrivateKeys),
                String.class, options);
    }


    @Override
    public String getServiceConfiguration(Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_CONFIGURATION_API_PATH, String.class, options);
    }


    @Override
    public String getServiceConfiguration(boolean pretty, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                SERVICE_CONFIGURATION_API_PATH,
                buildMap("pretty", pretty),
                String.class, options);
    }


    @Override
    public String getServiceConfiguration(ServiceConfigurationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                SERVICE_CONFIGURATION_API_PATH, request, String.class, options);
    }


    @Override
    public Client createClient(Client client, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                CLIENT_CREATE_API_PATH, client, Client.class, options);
    }


    @Override
    public ClientRegistrationResponse dynamicClientRegister(
            ClientRegistrationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                CLIENT_REGISTRATION_API_PATH, request, ClientRegistrationResponse.class, options);
    }


    @Override
    public ClientRegistrationResponse dynamicClientGet(
            ClientRegistrationRequest request, Options options)
    {
        return callServicePostApi(
                CLIENT_REGISTRATION_GET_API_PATH, request, ClientRegistrationResponse.class, options);
    }


    @Override
    public ClientRegistrationResponse dynamicClientUpdate(
            ClientRegistrationRequest request, Options options)
    {
        return callServicePostApi(
                CLIENT_REGISTRATION_UPDATE_API_PATH, request, ClientRegistrationResponse.class, options);
    }


    @Override
    public ClientRegistrationResponse dynamicClientDelete(
            ClientRegistrationRequest request, Options options)
    {
        return callServicePostApi(
                CLIENT_REGISTRATION_DELETE_API_PATH, request, ClientRegistrationResponse.class, options);
    }


    @Override
    public void deleteClient(long clientId, Options options) throws AuthleteApiException
    {
        deleteClient(String.valueOf(clientId), options);
    }


    @Override
    public void deleteClient(String clientId, Options options) throws AuthleteApiException
    {
        callServiceDeleteApi(
                String.format(CLIENT_DELETE_API_PATH, clientId), options);
    }


    @Override
    public Client getClient(long clientId, Options options) throws AuthleteApiException
    {
        return getClient(String.valueOf(clientId), options);
    }


    @Override
    public Client getClient(String clientId, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                String.format(CLIENT_GET_API_PATH, clientId), Client.class, options);
    }


    @Override
    public ClientListResponse getClientList(Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH, ClientListResponse.class, options);
    }


    @Override
    public ClientListResponse getClientList(String developer, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH,
                buildMap("developer", developer),
                ClientListResponse.class, options);
    }


    @Override
    public ClientListResponse getClientList(int start, int end, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH,
                buildMap("start", start, "end", end),
                ClientListResponse.class, options);
    }


    @Override
    public ClientListResponse getClientList(String developer, int start, int end, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                CLIENT_GET_LIST_API_PATH,
                buildMap("developer", developer, "start", start, "end", end),
                ClientListResponse.class, options);
    }


    @Override
    public Client updateClient(Client client, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                String.format(CLIENT_UPDATE_API_PATH, client.getClientId()),
                client, Client.class, options);
    }


    @Override
    public String[] getRequestableScopes(long clientId, Options options) throws AuthleteApiException
    {
        // Call the API.
        RequestableScopes response = callServiceGetApi(
                String.format(REQUESTABLE_SCOPES_GET_API_PATH, clientId),
                RequestableScopes.class, options);

        // Extract 'requestableScopes' from the response.
        return extractRequestableScopes(response);
    }


    @Override
    public String[] setRequestableScopes(long clientId, String[] scopes, Options options) throws AuthleteApiException
    {
        // Prepare a request body.
        RequestableScopes request = new RequestableScopes(scopes);

        // Call the API.
        RequestableScopes response = callServicePostApi(
                String.format(REQUESTABLE_SCOPES_UPDATE_API_PATH, clientId),
                request, RequestableScopes.class, options);

        // Extract 'requestableScopes' from the response.
        return extractRequestableScopes(response);
    }


    @Override
    public void deleteRequestableScopes(long clientId, Options options) throws AuthleteApiException
    {
        callServiceDeleteApi(
                String.format(REQUESTABLE_SCOPES_DELETE_API_PATH, clientId), options);
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
    public GrantedScopesGetResponse getGrantedScopes(long clientId, String subject, Options options)
    {
        // Prepare a request body.
        GrantedScopesRequest request = new GrantedScopesRequest(subject);

        return callServicePostApi(
                String.format(GRANTED_SCOPES_GET_API_PATH, clientId),
                request, GrantedScopesGetResponse.class, options);
    }


    @Override
    public void deleteGrantedScopes(long clientId, String subject, Options options)
    {
        // Prepare a request body.
        GrantedScopesRequest request = new GrantedScopesRequest(subject);

        callServicePostApi(
                String.format(GRANTED_SCOPES_DELETE_API_PATH, clientId),
                request, ApiResponse.class, options);
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
    public void deleteClientAuthorization(long clientId, String subject, Options options) throws AuthleteApiException
    {
        // Prepare a request body.
        ClientAuthorizationDeleteRequest request = new ClientAuthorizationDeleteRequest(subject);

        callServicePostApi(
                String.format(CLIENT_AUTHORIZATION_DELETE_API_PATH, clientId),
                request, ApiResponse.class, options);
    }


    @Override
    public AuthorizedClientListResponse getClientAuthorizationList(
            ClientAuthorizationGetListRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                CLIENT_AUTHORIZATION_GET_LIST_API_PATH,
                request, AuthorizedClientListResponse.class, options);
    }


    @Override
    public void updateClientAuthorization(
            long clientId, ClientAuthorizationUpdateRequest request, Options options) throws AuthleteApiException
    {
        callServicePostApi(
                String.format(CLIENT_AUTHORIZATION_UPDATE_API_PATH, clientId),
                request, ApiResponse.class, options);
    }


    @Override
    public ClientSecretRefreshResponse refreshClientSecret(
            long clientId, Options options) throws AuthleteApiException
    {
        return refreshClientSecret(String.valueOf(clientId), options);
    }


    @Override
    public ClientSecretRefreshResponse refreshClientSecret(
            String clientIdentifier, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                String.format(CLIENT_SECRET_REFRESH_API_PATH, clientIdentifier),
                ClientSecretRefreshResponse.class, options);
    }


    @Override
    public ClientSecretUpdateResponse updateClientSecret(
            long clientId, String clientSecret, Options options) throws AuthleteApiException
    {
        return updateClientSecret(String.valueOf(clientId), clientSecret, options);
    }


    @Override
    public ClientSecretUpdateResponse updateClientSecret(
            String clientIdentifier, String clientSecret, Options options) throws AuthleteApiException
    {
        // Prepare a request body. setClientSecret(String) method
        // throws IllegalArgumentException if the given client secret
        // does not comply with the format.
        ClientSecretUpdateRequest request
            = new ClientSecretUpdateRequest().setClientSecret(clientSecret);

        return callServicePostApi(
                String.format(CLIENT_SECRET_UPDATE_API_PATH, clientIdentifier),
                request, ClientSecretUpdateResponse.class, options);
    }


    @Override
    public Settings getSettings()
    {
        return mSettings;
    }


    @Override
    public JoseVerifyResponse verifyJose(JoseVerifyRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                JOSE_VERIFY_API_PATH, request, JoseVerifyResponse.class, options);
    }


    @Override
    public BackchannelAuthenticationResponse backchannelAuthentication(
            BackchannelAuthenticationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                BC_AUTHENTICATION_API_PATH, request,
                BackchannelAuthenticationResponse.class, options);
    }


    @Override
    public BackchannelAuthenticationIssueResponse backchannelAuthenticationIssue(
            BackchannelAuthenticationIssueRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                BC_AUTHENTICATION_ISSUE_API_PATH, request,
                BackchannelAuthenticationIssueResponse.class, options);
    }


    @Override
    public BackchannelAuthenticationFailResponse backchannelAuthenticationFail(
            BackchannelAuthenticationFailRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                BC_AUTHENTICATION_FAIL_API_PATH, request,
                BackchannelAuthenticationFailResponse.class, options);
    }


    @Override
    public BackchannelAuthenticationCompleteResponse backchannelAuthenticationComplete(
            BackchannelAuthenticationCompleteRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                BC_AUTHENTICATION_COMPLETE_API_PATH, request,
                BackchannelAuthenticationCompleteResponse.class, options);
    }


    @Override
    public DeviceAuthorizationResponse deviceAuthorization(
            DeviceAuthorizationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                DEVICE_AUTHORIZATION_API_PATH, request,
                DeviceAuthorizationResponse.class, options);
    }


    @Override
    public DeviceCompleteResponse deviceComplete(
            DeviceCompleteRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                DEVICE_COMPLETE_API_PATH, request,
                DeviceCompleteResponse.class, options);
    }


    @Override
    public DeviceVerificationResponse deviceVerification(
            DeviceVerificationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                DEVICE_VERIFICATION_API_PATH, request,
                DeviceVerificationResponse.class, options);
    }


    @Override
    public PushedAuthReqResponse pushAuthorizationRequest(
            PushedAuthReqRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                PUSHED_AUTH_REQ_API_PATH, request,
                PushedAuthReqResponse.class, options);
    }


    @Override
    public HskResponse hskCreate(HskCreateRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                HSK_CREATE_API_PATH, request,
                HskResponse.class, options);
    }


    @Override
    public HskResponse hskDelete(String handle, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                String.format(HSK_DELETE_API_PATH, handle),
                HskResponse.class, options);
    }


    @Override
    public HskResponse hskGet(String handle, Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                String.format(HSK_GET_API_PATH, handle),
                HskResponse.class, options);
    }


    @Override
    public HskListResponse hskGetList(Options options) throws AuthleteApiException
    {
        return callServiceGetApi(
                HSK_GET_LIST_API_PATH,
                HskListResponse.class, options);
    }


    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String> echo(Map<String, String> parameters, Options options) throws AuthleteApiException
    {
        return callGetApi(
                null, ECHO_API_PATH, parameters, Map.class, options);
    }


    @Override
    public GMResponse gm(GMRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                GM_API_PATH, request,
                GMResponse.class, options);
    }


    @Override
    public void updateClientLockFlag(
            String clientIdentifier, boolean clientLocked, Options options) throws AuthleteApiException
    {
        // Prepare a request body.
        ClientLockFlagUpdateRequest request =
                new ClientLockFlagUpdateRequest().setClientLocked(clientLocked);

        callServicePostApi(
                CLIENT_LOCK_FLAG_UPDATE_API_PATH, request,
                ApiResponse.class, options);
    }


    @Override
    public FederationConfigurationResponse federationConfiguration(
            FederationConfigurationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                FEDERATION_CONFIGURATION_API_PATH, request,
                FederationConfigurationResponse.class, options);
    }


    @Override
    public FederationRegistrationResponse federationRegistration(
            FederationRegistrationRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                FEDERATION_REGISTRATION_API_PATH, request,
                FederationRegistrationResponse.class, options);
    }


    @Override
    public CredentialIssuerMetadataResponse credentialIssuerMetadata(
            CredentialIssuerMetadataRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/metadata API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_METADATA_API_PATH, request,
                CredentialIssuerMetadataResponse.class, options);
    }


    @Override
    public CredentialJwtIssuerMetadataResponse credentialJwtIssuerMetadata(
            CredentialJwtIssuerMetadataRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/jwtissuer API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_JWT_ISSUER_API_PATH, request,
                CredentialJwtIssuerMetadataResponse.class, options);
    }


    @Override
    public CredentialIssuerJwksResponse credentialIssuerJwks(
            CredentialIssuerJwksRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/jwks API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_JWKS_API_PATH, request,
                CredentialIssuerJwksResponse.class, options);
    }


    @Override
    public CredentialOfferCreateResponse credentialOfferCreate(
            CredentialOfferCreateRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/offer/create API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_OFFER_CREATE_API_PATH, request,
                CredentialOfferCreateResponse.class, options);
    }


    @Override
    public CredentialOfferInfoResponse credentialOfferInfo(
            CredentialOfferInfoRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/offer/info API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_OFFER_INFO_API_PATH, request,
                CredentialOfferInfoResponse.class, options);
    }


    @Override
    public CredentialSingleParseResponse credentialSingleParse(
            CredentialSingleParseRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/single/parse API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_SINGLE_PARSE_API_PATH, request,
                CredentialSingleParseResponse.class, options);
    }


    @Override
    public CredentialSingleIssueResponse credentialSingleIssue(
            CredentialSingleIssueRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/single/issue API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_SINGLE_ISSUE_API_PATH, request,
                CredentialSingleIssueResponse.class, options);
    }


    @Override
    public CredentialBatchParseResponse credentialBatchParse(
            CredentialBatchParseRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/batch/parse API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_BATCH_PARSE_API_PATH, request,
                CredentialBatchParseResponse.class, options);
    }


    @Override
    public CredentialBatchIssueResponse credentialBatchIssue(
            CredentialBatchIssueRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/batch/issue API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_BATCH_ISSUE_API_PATH, request,
                CredentialBatchIssueResponse.class, options);
    }


    @Override
    public CredentialDeferredParseResponse credentialDeferredParse(
            CredentialDeferredParseRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/deferred/parse API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_DEFERRED_PARSE_API_PATH, request,
                CredentialDeferredParseResponse.class, options);
    }


    @Override
    public CredentialDeferredIssueResponse credentialDeferredIssue(
            CredentialDeferredIssueRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /vci/deferred/issue API is unavailable
        // in Authlete 2.x and older versions.
        return callServicePostApi(
                VCI_DEFERRED_ISSUE_API_PATH, request,
                CredentialDeferredIssueResponse.class, options);
    }


    @Override
    public IDTokenReissueResponse idTokenReissue(
            IDTokenReissueRequest request, Options options) throws AuthleteApiException
    {
        return callServicePostApi(
                ID_TOKEN_REISSUE_API_PATH, request,
                IDTokenReissueResponse.class, options);
    }


    @Override
    public AuthorizationTicketInfoResponse authorizationTicketInfo(
            AuthorizationTicketInfoRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /auth/authorization/ticket/info API
        // is unavailable in Authlete 2.x and older versions.
        return callServicePostApi(
                AUTH_AUTHORIZATION_TICKET_INFO_API_PATH, request,
                AuthorizationTicketInfoResponse.class, options);
    }


    @Override
    public AuthorizationTicketUpdateResponse authorizationTicketUpdate(
            AuthorizationTicketUpdateRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /auth/authorization/ticket/update API
        // is unavailable in Authlete 2.x and older versions.
        return callServicePostApi(
                AUTH_AUTHORIZATION_TICKET_UPDATE_API_PATH, request,
                AuthorizationTicketUpdateResponse.class, options);
    }


    @Override
    public TokenCreateBatchResponse tokenCreateBatch(
            TokenCreateRequest[] request, boolean isDryRun, Options options) throws AuthleteApiException
    {
        // This is unavailable in Authlete 2.x and older versions.
        throw new AuthleteApiException(
                "This method can't be invoked since the corresponding API is not supported.");
    }


    @Override
    public TokenCreateBatchStatusResponse getTokenCreateBatchStatus(
            String requestId, Options options) throws AuthleteApiException
    {
        // This is unavailable in Authlete 2.x and older versions.
        throw new AuthleteApiException(
                "This method can't be invoked since the corresponding API is not supported.");
    }


    @Override
    public NativeSsoResponse nativeSso(
            NativeSsoRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /nativesso API
        // is unavailable in Authlete 2.x and older versions.
        return callServicePostApi(
                NATIVE_SSO_API_PATH, request,
                NativeSsoResponse.class, options);
    }


    @Override
    public NativeSsoLogoutResponse nativeSsoLogout(
            NativeSsoLogoutRequest request, Options options) throws AuthleteApiException
    {
        // This API call fails because the /nativesso/logout API
        // is unavailable in Authlete 2.x and older versions.
        return callServicePostApi(
                NATIVE_SSO_LOGOUT_API_PATH, request,
                NativeSsoLogoutResponse.class, options);
    }
}
