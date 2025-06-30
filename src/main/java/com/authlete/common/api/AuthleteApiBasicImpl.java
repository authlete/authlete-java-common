/*
 * Copyright (C) 2014-2025 Authlete, Inc.
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
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import com.authlete.common.conf.AuthleteConfiguration;
import com.authlete.common.dto.ApiResponse;
import com.authlete.common.dto.ClientListResponse;
import com.authlete.common.dto.ServiceListResponse;
import com.authlete.common.dto.TokenListResponse;
import com.authlete.common.types.TokenStatus;
import com.authlete.common.util.Utils;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.factories.DefaultJWSSignerFactory;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;


public abstract class AuthleteApiBasicImpl implements AuthleteApi
{
    private static String UTF_8 = "UTF-8";





    protected interface AuthleteApiCall<TResponse>
    {
        TResponse call();
    }


    /** HTTP methods used in this source code. */
    protected enum HttpMethod
    {
        GET,
        POST,
        DELETE
    }


    /** Strategy for handling 404 responses */
    protected enum NotFoundHandling
    {
        /** Throw exception for 404 (default behavior) */
        THROW_EXCEPTION,
        
        /** Return null for 404 responses */
        RETURN_NULL,
        
        /** Parse 404 response body as business response */
        PARSE_AS_RESPONSE,
        
        /** Return default success response for 404 (useful for delete operations) */
        RETURN_SUCCESS_RESPONSE
    }


    /** Strategy for handling client error responses (4xx) */
    protected enum ClientErrorHandling
    {
        /** Throw exception for 4xx (default behavior) */
        THROW_EXCEPTION,
        
        /** Parse 4xx response body as business response */
        PARSE_AS_RESPONSE,
        
        /** Parse 4xx response body, return default response if parsing fails */
        PARSE_OR_DEFAULT_RESPONSE
    }


    private class HttpHeader
    {
        private static final String ACCEPT        = "Accept";
        private static final String AUTHORIZATION = "Authorization";
        private static final String CONTENT_TYPE  = "Content-Type";
    }


    private final String mBaseUrl;
    private final Settings mSettings;
    private JWK mDpopJwk;
    private JWSSigner mJwsSigner;


    public AuthleteApiBasicImpl(AuthleteConfiguration configuration)
    {
        if (configuration == null)
        {
            throw new IllegalArgumentException("configuration is null.");
        }
        mBaseUrl = configuration.getBaseUrl();
        extractDpop(configuration); // this has to be done before the credentials calls
        mSettings = new Settings();
    }


    private void extractDpop(AuthleteConfiguration configuration)
    {
        if (configuration.getDpopKey() != null)
        {
            try
            {
                mDpopJwk = JWK.parse(configuration.getDpopKey());
                if (mDpopJwk.getAlgorithm() == null)
                {
                    throw new IllegalArgumentException("DPoP JWK must contain an 'alg' field.");
                }
                mJwsSigner = new DefaultJWSSignerFactory().createJWSSigner(mDpopJwk);
            }
            catch (ParseException | JOSEException e)
            {
                throw new IllegalArgumentException("DPoP JWK is not valid.");
            }
        }
    }


    /**
     * Call an API with HTTP GET method.
     */
    protected <TResponse> TResponse callGetApi(
            String auth, String path, Class<TResponse> responseClass, Map<String, Object[]> queryParams,
             Options options) throws AuthleteApiException
    {
        return callApi(AuthleteApiBasicImpl.HttpMethod.GET, auth, path, queryParams, (Object)null, responseClass, options);
    }


    /**
     * Call an API with HTTP POST method.
     */
    protected <TResponse> TResponse callPostApi(
            String auth, String path,
            Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callApi(AuthleteApiBasicImpl.HttpMethod.POST, auth, path, null, requestBody, responseClass, options);
    }


    /**
     * Call an API with HTTP DELETE method.
     */
    protected void callDeleteApi(
            String auth, String path, Options options) throws AuthleteApiException
    {
        callApi(AuthleteApiBasicImpl.HttpMethod.DELETE, auth, path, null, null, null, options);
    }


    /**
     * Execute an Authlete API call.
     * 
     * Note: All context handling (creation, exception wrapping, cleanup) is now
     * centralized in the callApi method, eliminating the need for ThreadLocal context.
     */
    protected <TResponse> TResponse executeApiCall(AuthleteApiCall<TResponse> apiCall) throws AuthleteApiException
    {
        // Simply invoke the API call. All context handling is now done in callApi.
        return apiCall.call();
    }


    /**
     * Call an API with centralized context handling.
     */
    protected  <TResponse> TResponse callApi(
            AuthleteApiBasicImpl.HttpMethod method, String auth,
            String path, Map<String, Object[]> queryParams,
            Object requestBody, Class<TResponse> responseClass, Options options) throws AuthleteApiException
    {
        return callApiWithNotFoundHandling(method, auth, path, queryParams, requestBody, responseClass, options, NotFoundHandling.THROW_EXCEPTION);
    }


    /**
     * Call an API with centralized context handling and specified 404 handling strategy.
     */
    protected  <TResponse> TResponse callApiWithNotFoundHandling(
            AuthleteApiBasicImpl.HttpMethod method, String auth,
            String path, Map<String, Object[]> queryParams,
            Object requestBody, Class<TResponse> responseClass, Options options, NotFoundHandling notFoundHandling) throws AuthleteApiException
    {
        return callApiWith4xxHandling(method, auth, path, queryParams, requestBody, responseClass, options, notFoundHandling, ClientErrorHandling.THROW_EXCEPTION);
    }


    /**
     * Call an API with centralized context handling and specified 4xx handling strategies.
     */
    protected  <TResponse> TResponse callApiWith4xxHandling(
            AuthleteApiBasicImpl.HttpMethod method, String auth,
            String path, Map<String, Object[]> queryParams,
            Object requestBody, Class<TResponse> responseClass, Options options, 
            NotFoundHandling notFoundHandling, ClientErrorHandling clientErrorHandling) throws AuthleteApiException
    {
        String dpopHeader = wrapWithDpop(method, path);
        if (dpopHeader != null)
        {
            if (options == null)
            {
                options = new Options();
            }
            Map<String, String> headers = options.getHeaders();
            if (headers == null)
            {
                headers = new HashMap<>();
                options.setHeaders(headers);
            }
            headers.put("DPoP", dpopHeader);
        }
        
        ConnectionContext ctx = null;
        try
        {
            // Create a connection to the Authlete API.
            ctx = createConnection(method, auth, mBaseUrl, path, queryParams, options, mSettings);
            
            // Communicate with the API and get a response
            return communicate(ctx, requestBody, responseClass, notFoundHandling, clientErrorHandling);
        }
        catch (AuthleteApiException e)
        {
            // Re-throw AuthleteApiException as-is (it already has context)
            throw e;
        }
        catch (Throwable t)
        {
            // Wrap any other throwable with HTTP context
            throw createAuthleteApiException(t, ctx);
        }
        finally
        {
            // Always close the connection context if it was created
            if (ctx != null)
            {
                ctx.close();
            }
        }
    }


    protected TokenListResponse callGetTokenList(
            String auth, String path, String clientIdentifier, String subject, int start, int end, boolean rangeGiven, TokenStatus tokenStatus, Options options)
    {
        Map<String, Object[]> queryParams = new LinkedHashMap<>();

        if (clientIdentifier != null)
        {
            queryParams.put("clientIdentifier", new Object[]{ clientIdentifier });
        }

        if (subject != null)
        {
            queryParams.put("subject", new Object[]{ subject });
        }

        if (rangeGiven)
        {
            queryParams.put("start", new Object[]{ start });
            queryParams.put("end",   new Object[]{ end   });
        }

        queryParams.put("tokenStatus", new Object[]{ tokenStatus.toString() });

        return callApi(HttpMethod.GET, auth, path, queryParams, null, TokenListResponse.class, options);
    }


    protected ServiceListResponse callGetServiceList(String auth, String path, int start, int end, boolean rangeGiven, Options options)
    {
        Map<String, Object[]> queryParams = new LinkedHashMap<>();
        if (rangeGiven)
        {
            queryParams.put("start", new Object[]{ start });
            queryParams.put("end",   new Object[]{ end });
        }

        return callApi(HttpMethod.GET, auth, path, queryParams, null, ServiceListResponse.class, options);
    }


    protected ClientListResponse callGetClientList(String auth, String path, String developer, int start, int end, boolean rangeGiven, Options options)
    {
        Map<String, Object[]> queryParams = new LinkedHashMap<>();
        if (developer != null)
        {
            queryParams.put("developer", new Object[]{ developer });
        }
        if (rangeGiven)
        {
            queryParams.put("start", new Object[]{ start });
            queryParams.put("end",   new Object[]{ end   });
        }

        return callApi(HttpMethod.GET, auth, path, queryParams, null, ClientListResponse.class, options);
    }


    @SuppressWarnings("unchecked")
    protected Map<String, String> callEcho(String path, Map<String, String> parameters, Options options)
    {
        // Convert single‐valued Map<String,String> into Map<String,Object[]> for callApi
        Map<String, Object[]> queryParams = null;
        if (parameters != null && !parameters.isEmpty())
        {
            queryParams = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : parameters.entrySet())
            {
                queryParams.put(entry.getKey(), new Object[]{ entry.getValue() });
            }
        }

        return callApi(HttpMethod.GET, null, path, queryParams, null, (Class<Map<String, String>>)(Class<?>)Map.class, options);
    }


    private static ConnectionContext createConnection(
            AuthleteApiBasicImpl.HttpMethod method, String auth,
            String baseUrl, String path, Map<String, Object[]> queryParams, Options options,
            Settings settings) throws AuthleteApiException
    {
        try
        {
            // Open a connection to the Authlete API.
            return openConnection(
                    method, auth, baseUrl, path, queryParams, options, settings);
        }
        catch (Throwable cause)
        {
            // Failed to open a connection.
            throw createAuthleteApiException(cause, null);
        }
    }


    private static ConnectionContext openConnection(
            AuthleteApiBasicImpl.HttpMethod method, String auth, String baseUrl,
            String path, Map<String, Object[]> queryParams, Options options,
            Settings settings) throws IOException
    {
        // URL of an Authlete API.
        URL url = buildUrl(baseUrl, path, queryParams);

        // Open a connection to the Authlete API.
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        // Set HTTP method.
        con.setRequestMethod(method.name());

        if (auth != null)
        {
            // Set 'Authorization' HTTP header to access the Authlete API.
            con.setRequestProperty(HttpHeader.AUTHORIZATION, auth);
        }

        // Set 'Accept' HTTP header for a JSON response.
        con.setRequestProperty(AuthleteApiBasicImpl.HttpHeader.ACCEPT, "application/json");

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
            String baseUrl, String path, Map<String, Object[]> queryParams) throws MalformedURLException
    {
        // {scheme}://{host}{path}
        StringBuffer buf = new StringBuffer(baseUrl).append(path);

        // ?key1=value1&key2=value2&...
        appendQueryParams(buf, queryParams);

        return new URL(buf.toString());
    }


    private static void appendQueryParams(StringBuffer buf, Map<String, Object[]> params)
    {
        if (params == null || params.isEmpty())
        {
            return;
        }

        buf.append('?');
        int appendedCount = 0;

        for (Map.Entry<String, Object[]> entry : params.entrySet())
        {
            String rawKey = entry.getKey();
            String encodedKey = toQueryParamKey(rawKey);
            if (encodedKey == null)
            {
                // Skip invalid key
                continue;
            }

            Object[] values = entry.getValue();
            if (values == null || values.length == 0)
            {
                // Append key with an empty value
                buf.append(encodedKey).append("=").append("&");
                appendedCount++;
            }
            else
            {
                for (Object valObj : values)
                {
                    String rawValue = (valObj == null) ? "" : valObj.toString();
                    String encodedValue = toQueryParamValue(rawValue);
                    buf.append(encodedKey)
                            .append("=")
                            .append(encodedValue)
                            .append("&");
                    appendedCount++;
                }
            }
        }

        // Remove trailing '&' if we appended anything; otherwise remove '?'.
        if (appendedCount > 0)
        {
            buf.setLength(buf.length() - 1);
        }
        else
        {
            buf.setLength(buf.length() - 1);
        }
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
        return key.equalsIgnoreCase(AuthleteApiBasicImpl.HttpHeader.ACCEPT) ||
                key.equalsIgnoreCase(HttpHeader.AUTHORIZATION) ||
                key.equalsIgnoreCase(AuthleteApiBasicImpl.HttpHeader.CONTENT_TYPE);
    }


    private String wrapWithDpop(HttpMethod method, String path) throws AuthleteApiException
    {
        if (mDpopJwk == null)
        {
            // No DPoP configured → caller should skip setting a DPoP header
            return null;
        }

        String htu = mBaseUrl + path;
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(new JOSEObjectType("dpop+jwt"))
                .jwk(mDpopJwk)
                .build();

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .claim("htm", method.name())
                .claim("htu", htu)
                .jwtID(UUID.randomUUID().toString())
                .issueTime(new Date())
                .build();

        JWSObject dpop = new SignedJWT(header, claims);
        try
        {
            dpop.sign(mJwsSigner);
        }
        catch (JOSEException e)
        {
            // We'll rethrow as AuthleteApiException so that our caller can wrap it properly
            throw new AuthleteApiException("Failed to sign DPoP token.", e);
        }

        return dpop.serialize();
    }


    @SuppressWarnings("unchecked")
    private <TResponse> TResponse communicate(
            ConnectionContext ctx, Object requestBody, Class<TResponse> responseClass) throws AuthleteApiException
    {
        return communicate(ctx, requestBody, responseClass, NotFoundHandling.THROW_EXCEPTION);
    }


    @SuppressWarnings("unchecked")
    private <TResponse> TResponse communicate(
            ConnectionContext ctx, Object requestBody, Class<TResponse> responseClass, 
            NotFoundHandling notFoundHandling) throws AuthleteApiException
    {
        return communicate(ctx, requestBody, responseClass, notFoundHandling, ClientErrorHandling.THROW_EXCEPTION);
    }


    @SuppressWarnings("unchecked")
    private <TResponse> TResponse communicate(
            ConnectionContext ctx, Object requestBody, Class<TResponse> responseClass, 
            NotFoundHandling notFoundHandling, ClientErrorHandling clientErrorHandling) throws AuthleteApiException
    {
        // If the request has a request body.
        if (requestBody != null)
        {
            // Write the request body.
            writeRequestBody(ctx, requestBody);
        }

        // Check HTTP status code: handle 404s and other 4xx based on strategy, treat 5xx as server errors.
        try
        {
            int status = ctx.connection().getResponseCode();
            if (responseClass != null && status >= HttpURLConnection.HTTP_BAD_REQUEST)
            {
                // Special handling for 404 responses
                if (status == HttpURLConnection.HTTP_NOT_FOUND)
                {
                    return handle404Response(ctx, responseClass, notFoundHandling);
                }
                
                // Handle other 4xx responses based on strategy
                if (status >= 400 && status < 500)
                {
                    return handle4xxResponse(ctx, responseClass, status, clientErrorHandling);
                }
                
                // For 5xx errors, always throw exception
                String errorBody = extractErrorData(ctx);
                String statusMsg = extractStatusMessage(ctx.connection());
                Map<String, List<String>> headers = ctx.connection().getHeaderFields();
                throw new AuthleteApiException("HTTP " + status + " " + statusMsg,
                        status, statusMsg, errorBody, headers);
            }
        }
        catch (IOException ioe)
        {
            // If we can't get status, fallback to normal flow
        }

        // Read the response body. (JSON is expected)
        String responseBody = readResponseBody(ctx);

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


    /**
     * Handle 404 responses based on the specified strategy.
     */
    @SuppressWarnings("unchecked")
    private <TResponse> TResponse handle404Response(
            ConnectionContext ctx, Class<TResponse> responseClass, 
            NotFoundHandling notFoundHandling) throws AuthleteApiException
    {
        if (notFoundHandling == NotFoundHandling.RETURN_NULL)
        {
            return null;
        }
        else if (notFoundHandling == NotFoundHandling.PARSE_AS_RESPONSE)
        {
            String errorBody = extractErrorData(ctx);
            if (errorBody != null && responseClass != null)
            {
                try
                {
                    return Utils.fromJson(errorBody, responseClass);
                }
                catch (Exception ignored)
                {
                    // parse failed → fall back to success response
                }
            }
            return createDefaultSuccessResponse(responseClass);
        }
        else if (notFoundHandling == NotFoundHandling.RETURN_SUCCESS_RESPONSE)
        {
            return createDefaultSuccessResponse(responseClass);
        }
        else
        {
            // THROW_EXCEPTION (default)
            String statusMsg = extractStatusMessage(ctx.connection());
            Map<String, List<String>> headers = ctx.connection().getHeaderFields();
            String body       = extractErrorData(ctx);
            throw new AuthleteApiException(
                    "HTTP 404 " + statusMsg,
                    HttpURLConnection.HTTP_NOT_FOUND,
                    statusMsg,
                    body,
                    headers
            );
        }
    }


    /**
     * Handle 4xx responses (other than 404) based on the specified strategy.
     */
    @SuppressWarnings("unchecked")
    private <TResponse> TResponse handle4xxResponse(
            ConnectionContext ctx, Class<TResponse> responseClass, int status,
            ClientErrorHandling clientErrorHandling) throws AuthleteApiException
    {
        // 1) Try to parse, or else throw
        if (clientErrorHandling == ClientErrorHandling.PARSE_AS_RESPONSE)
        {
            String errorBody = extractErrorData(ctx);
            if (errorBody != null && responseClass != null)
            {
                try
                {
                    return Utils.fromJson(errorBody, responseClass);
                }
                catch (Exception ignored)
                {
                    // parsing failed → fall through to exception
                }
            }
            // parsing not performed or failed → throw exception
            String statusMsg = extractStatusMessage(ctx.connection());
            Map<String, List<String>> headers = ctx.connection().getHeaderFields();
            String body       = extractErrorData(ctx);
            throw new AuthleteApiException(
                    "HTTP " + status + " " + statusMsg,
                    status, statusMsg, body, headers
            );
        }
        // 2) Parse, or return default error response
        else if (clientErrorHandling == ClientErrorHandling.PARSE_OR_DEFAULT_RESPONSE)
        {
            String responseBody = extractErrorData(ctx);
            if (responseBody != null && responseClass != null)
            {
                try
                {
                    return Utils.fromJson(responseBody, responseClass);
                }
                catch (Exception e)
                {
                    // parsing failed → return default error response
                    return createDefaultErrorResponse(responseClass, status);
                }
            }
            // no body or no class → default error response
            return createDefaultErrorResponse(responseClass, status);
        }
        // 3) THROW_EXCEPTION (default)
        else
        {
            String statusMsg = extractStatusMessage(ctx.connection());
            Map<String, List<String>> headers = ctx.connection().getHeaderFields();
            String body       = extractErrorData(ctx);
            throw new AuthleteApiException(
                    "HTTP " + status + " " + statusMsg,
                    status, statusMsg, body, headers
            );
        }
    }


    /**
     * Create a default success response for the given response class.
     */
    @SuppressWarnings("unchecked")
    private <TResponse> TResponse createDefaultSuccessResponse(Class<TResponse> responseClass)
    {
        if (responseClass == null)
        {
            return null;
        }
        
        // Special handling for ApiResponse
        if (com.authlete.common.dto.ApiResponse.class.isAssignableFrom(responseClass))
        {
            com.authlete.common.dto.ApiResponse response = new com.authlete.common.dto.ApiResponse();
            response.setResultCode("CLIENT_GENERATED_404_RESPONSE"); // Clearly not from API
            response.setResultMessage("404 Not Found - Resource may have been already deleted");
            return (TResponse)response;
        }
        
        // For other types, return null
        return null;
    }


    /**
     * Create a default error response for the given response class and status code.
     */
    @SuppressWarnings("unchecked")
    private <TResponse> TResponse createDefaultErrorResponse(Class<TResponse> responseClass, int statusCode)
    {
        if (responseClass == null)
        {
            return null;
        }
        
        // Special handling for ApiResponse
        if (com.authlete.common.dto.ApiResponse.class.isAssignableFrom(responseClass))
        {
            com.authlete.common.dto.ApiResponse response = new com.authlete.common.dto.ApiResponse();
            response.setResultCode("CLIENT_GENERATED_" + statusCode + "_RESPONSE"); // Clearly not from API
            response.setResultMessage("HTTP " + statusCode + " response with empty/invalid body");
            return (TResponse)response;
        }
        
        // For other types, return null
        return null;
    }


    private static void writeRequestBody(ConnectionContext ctx, Object requestBody) throws AuthleteApiException
    {
        // Set 'Content-Type' to send JSON.
        ctx.property(AuthleteApiBasicImpl.HttpHeader.CONTENT_TYPE, "application/json");

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
        byte[] buf = new byte[8096];
        int len;

        while ((len = in.read(buf)) != -1)
        {
            out.write(buf, 0, len);
        }
    }


    /**
     * Create an {@link AuthleteApiException} instance.
     */
    private static AuthleteApiException createAuthleteApiException(Throwable cause, ConnectionContext ctx)
    {
        // Error message.
        String message = cause.getMessage();

        if (ctx == null)
        {
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


    @Override
    public Settings getSettings()
    {
        return mSettings;
    }


    protected boolean isDpopEnabled()
    {
        return mDpopJwk != null;
    }


    /**
     * Perform a DELETE and return the ApiResponse (business result),
     * treating 404 as success and other 4xx with graceful handling for empty bodies.
     */
    public ApiResponse deleteApiResponse(
            String auth, String path, Options options) throws AuthleteApiException
    {
        // Use enhanced strategies:
        // - 404: Return success response (resource already deleted)
        // - Other 4xx: Try to parse, return default response if body is null/invalid
        return callApiWith4xxHandling(HttpMethod.DELETE, auth, path, null, null,
                com.authlete.common.dto.ApiResponse.class, options, 
                NotFoundHandling.RETURN_SUCCESS_RESPONSE, 
                ClientErrorHandling.PARSE_OR_DEFAULT_RESPONSE);
    }
}