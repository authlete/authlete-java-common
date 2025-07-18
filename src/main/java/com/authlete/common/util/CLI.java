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
package com.authlete.common.util;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.authlete.common.api.AuthleteApi;
import com.authlete.common.api.AuthleteApiException;
import com.authlete.common.api.AuthleteApiFactory;
import com.authlete.common.dto.AuthorizedClientListResponse;
import com.authlete.common.dto.Client;
import com.authlete.common.dto.ClientAuthorizationGetListRequest;
import com.authlete.common.dto.ClientListResponse;
import com.authlete.common.dto.Service;
import com.authlete.common.dto.ServiceListResponse;


/**
 * Command line interface for Authlete API.
 *
 * <h3>USAGE</h3>
 * <pre>
 * java -cp {classpath} com.authlete.common.util.CLI [options] {API name} [arguments]
 *
 *   <i>or</i>
 *
 * mvn -q exec:java -Dexec.args='[options] {API name} [arguments]'
 *
 *   <i>or</i>
 *
 * bin/authlete-cli.sh [options] {API name} [arguments]
 * </pre>
 *
 * <h3>OPTIONS</h3>
 * <pre>
 * -h | --help : Show the help and exit.
 * -v | --verbose : Verbose output.
 * </pre>
 *
 * <h3>API NAME AND ARGUMENTS</h3>
 * <pre>
 * getClient {clientId}
 * getClientAuthorizationList subject={subject} [developer={developer}] [start={start}] [end={end}]
 * getClientList [developer={developer}] [start={start}] [end={end}]
 * getService {serviceApiKey}
 * getServiceConfiguration [pretty={true|false}]
 * getServiceJwks [pretty={true|false}] [includePrivateKeys={true|false}]
 * getServiceList [start={start}] [end={end}]
 *
 * # API name is case-insensitive.
 * </pre>
 *
 * <h3>EXAMPLES</h3>
 * <pre>
 * $ bin/authlete-cli.sh --help
 * $ bin/authlete-cli.sh getClient 4326385670
 * $ bin/authlete-cli.sh getClientAuthorizationList subject=authlete_5526908833
 * $ bin/authlete-cli.sh getClientList developer=authlete_5526908833
 * $ bin/authlete-cli.sh getService 5526908833
 * $ bin/authlete-cli.sh getServiceConfiguration pretty=true
 * $ bin/authlete-cli.sh getServiceJwks pretty=true includePrivateKeys=true
 * $ bin/authlete-cli.sh getServiceList start=1
 * </pre>
 *
 * <h3>NOTE</h3>
 * <blockquote>
 * <p>
 * {@code authlete.properties} must exist. See JavaDoc of
 * {@link com.authlete.common.conf.AuthletePropertiesConfiguration
 * AuthletePropertiesConfiguration}.
 * </p>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 *
 * @since 2.0
 */
public class CLI
{
    private static final String HELP =
            "OVERVIEW:\n\n" +
            "  Command line interface for Authlete API.\n" +
            "\n\n" +
            "USAGE:\n\n" +
            "  java -cp {classpath} com.authlete.common.util.CLI [options] {API name} [arguments]\n\n" +
            "    or\n\n" +
            "  mvn -q exec:java -Dexec.args='[options] {API name} [arguments]'\n\n" +
            "    or\n\n" +
            "  bin/authlete-cli.sh [options] {API name} [arguments]\n" +
            "\n\n" +
            "OPTIONS:\n\n" +
            "  -h | --help : Show this help and exit.\n" +
            "  -v | --verbose : Verbose output.\n" +
            "\n\n" +
            "API NAME AND ARGUMENTS:\n\n" +
            "  getClient {clientId}\n" +
            "  getClientAuthorizationList subject={subject} [developer={developer}] [start={start}] [end={end}]\n" +
            "  getClientList [developer={developer}] [start={start}] [end={end}]\n" +
            "  getService {serviceApiKey}\n" +
            "  getServiceConfiguration [pretty={true|false}]\n" +
            "  getServiceJwks [pretty={true|false}] [includePrivateKeys={true|false}]\n" +
            "  getServiceList [start={start}] [end={end}]\n" +
            "\n" +
            "  # API name is case-insensitive.\n" +
            "\n\n" +
            "EXAMPLES:\n\n" +
            "  $ bin/authlete-cli.sh --help\n" +
            "  $ bin/authlete-cli.sh getClient 4326385670\n" +
            "  $ bin/authlete-cli.sh getClientAuthorizationList subject=authlete_5526908833\n" +
            "  $ bin/authlete-cli.sh getClientList developer=authlete_5526908833\n" +
            "  $ bin/authlete-cli.sh getService 5526908833\n" +
            "  $ bin/authlete-cli.sh getServiceConfiguration pretty=true\n" +
            "  $ bin/authlete-cli.sh getServiceJwks pretty=true includePrivateKeys=true\n" +
            "  $ bin/authlete-cli.sh getServiceList start=1\n" +
            "\n\n" +
            "NOTE:\n\n" +
            "  'authlete.properties' must exist. See JavaDoc of AuthletePropertiesConfiguration."
            ;


    /**
     * The entry point of this command line interface.
     */
    public static void main(String[] args)
    {
        new CLI().execute(args);
    }


    private void execute(String[] args)
    {
        // Parse the command line arguments.
        Settings settings = parseArguments(args);

        // Validate the settings.
        validateSettings(settings);

        // Load "authlete.properties" and create an AuthleteApi instance.
        AuthleteApi api = AuthleteApiFactory.getDefaultApi();

        try
        {
            // Execute the specified API.
            executeApi(api, settings);
        }
        catch (AuthleteApiException e)
        {
            // Report the error.
            reportError(e);
        }
    }


    private Settings parseArguments(String[] args)
    {
        Settings settings = new Settings();

        int len = args.length;

        for (int i = 0; i < len; ++i)
        {
            String argv = args[i];

            // Help
            if (argv.equals("-h") || argv.equals("--help"))
            {
                settings.help = true;
                break;
            }
            // Verbose
            else if (argv.equals("-v") || argv.equals("--verbose"))
            {
                settings.verbose++;
                continue;
            }
            // Unknown option
            else if (argv.startsWith("-"))
            {
                settings.setError("Unknown option: '%s'", argv);
                break;
            }

            // API and parameters.
            settings.apiName = ApiName.parse(argv);
            settings.setParameters(args, i + 1);

            if (settings.apiName == null)
            {
                settings.setError("Unknown API name: '%s'", argv);
            }

            break;
        }

        return settings;
    }


    private void validateSettings(Settings settings)
    {
        // If "-h" or "--help" is included in the command line.
        if (settings.help)
        {
            // Show the help text and terminate this application.
            System.out.println(HELP);
            System.exit(0);
        }

        // If something wrong has happened.
        if (settings.error)
        {
            // Show the error message and terminate this application.
            showErrorAndExit(settings.errorMessage);
        }

        // If an API name is not included in the command line.
        if (settings.apiName == null)
        {
            showErrorAndExit("API name is not given.");
        }
    }


    private static enum ApiName
    {
        GET_CLIENT("getClient"),
        GET_CLIENT_AUTHORIZATION_LIST("getClientAuthorizationList"),
        GET_CLIENT_LIST("getClientList"),
        GET_SERVICE("getService"),
        GET_SERVICE_CONFIGURATION("getServiceConfiguration"),
        GET_SERVICE_JWKS("getServiceJwks"),
        GET_SERVICE_LIST("getServiceList"),
        ;


        private final String name;


        private ApiName(String name)
        {
            this.name = name;
        }


        public static ApiName parse(String name)
        {
            for (ApiName api : values())
            {
                if (api.name.equalsIgnoreCase(name))
                {
                    return api;
                }
            }

            return null;
        }
    }


    private static class Settings
    {
        public boolean help;
        public boolean error;
        public int verbose;
        public String errorMessage;
        public ApiName apiName;
        public Map<String, String> parameters = new LinkedHashMap<String, String>();


        public void setError(String format, Object... args)
        {
            error = true;
            errorMessage = String.format(format, args);
        }


        public void setParameters(String[] args, int from)
        {
            // For each argument.
            for (int i = from; i < args.length; ++i)
            {
                // Expect the argument is in the format of "key[=value]".
                String[] pair = args[i].split("=", 2);
                String key    = null;
                String value  = null;

                switch (pair.length)
                {
                    case 2:
                        key   = pair[0];
                        value = pair[1];
                        break;

                    case 1:
                        key = pair[0];
                        break;

                    default:
                        // Ignore.
                        continue;
                }

                parameters.put(key, value);
            }
        }
    }


    private void showErrorAndExit(String message)
    {
        System.err.println("ERROR: " + message);
        System.err.println("HELP: Use --help option.");
        System.exit(1);
    }


    private void executeApi(AuthleteApi api, Settings settings)
    {
        switch (settings.apiName)
        {
            case GET_CLIENT:
                executeGetClientApi(api, settings);
                break;

            case GET_CLIENT_AUTHORIZATION_LIST:
                executeGetClientAuthorizationListApi(api, settings);
                break;

            case GET_CLIENT_LIST:
                executeGetClientListApi(api, settings);
                break;

            case GET_SERVICE:
                executeGetServiceApi(api, settings);
                break;

            case GET_SERVICE_CONFIGURATION:
                executeGetServiceConfigurationApi(api, settings);
                break;

            case GET_SERVICE_JWKS:
                executeGetServiceJwksApi(api, settings);
                break;

            case GET_SERVICE_LIST:
                executeGetServiceListApi(api, settings);
                break;

            default:
                // Not implemented unexpectedly.
                throw new AssertionError(settings.apiName.name());
        }
    }


    private void reportError(AuthleteApiException exception)
    {
        System.err.println("ERROR: " + exception.getMessage());
        System.err.format("%d %s\n", exception.getStatusCode(), exception.getStatusMessage());
        reportHeaders(exception);
        reportResponseBody(exception);
    }


    private void reportHeaders(AuthleteApiException exception)
    {
        Map<String, List<String>> headers = exception.getResponseHeaders();

        if (headers == null)
        {
            return;
        }

        for (Map.Entry<String, List<String>> entry : headers.entrySet())
        {
            String name = entry.getKey();
            List<String> list = entry.getValue();

            if (name == null)
            {
                continue;
            }

            if (list.size() == 0)
            {
                System.err.format("%s:\n", name);
                continue;
            }

            for (String value : list)
            {
                System.err.format("%s: %s\n", name, value);
            }
        }
    }


    private void reportResponseBody(AuthleteApiException exception)
    {
        String responseBody = exception.getResponseBody();

        if (responseBody == null)
        {
            return;
        }

        System.err.println();
        System.err.print(responseBody);
    }


    private int getIntegerOrExitIfNeeded(Map<String, String> parameters, String key, int defaultValue)
    {
        if (parameters.containsKey(key) == false)
        {
            return defaultValue;
        }

        String value = parameters.get(key);

        if (value == null || value.length() == 0)
        {
            showErrorAndExit(String.format("'%s' needs its value.", key));
        }

        try
        {
            return Integer.parseInt(value);
        }
        catch (Exception e)
        {
            showErrorAndExit(String.format("Failed to parse the value of '%s' as an integer.", key));

            // Not reach here.
            return defaultValue;
        }
    }


    private boolean getBoolean(Map<String, String> parameters, String key, boolean defaultValue)
    {
        if (parameters.containsKey(key) == false)
        {
            return defaultValue;
        }

        String value = parameters.get(key);

        if (value == null || value.length() == 0)
        {
            // Regard this case as 'true'.
            return true;
        }

        // True if 'value' is "true" (case-insensitive).
        return Boolean.parseBoolean(value);
    }


    private String getFirstKeyOrExit(Map<String, String> parameters, String apiName, String parameterName)
    {
        int size = parameters.size();

        if (size == 0)
        {
            showErrorAndExit(String.format("%s requires {%s}.", apiName, parameterName));
        }
        else if (size != 1)
        {
            showErrorAndExit(String.format("Too many arguments for %s.", apiName));
        }

        return getFirstKey(parameters);
    }


    private String getFirstKey(Map<String, String> parameters)
    {
        try
        {
            return parameters.keySet().iterator().next();
        }
        catch (Exception e)
        {
            return null;
        }
    }


    private void verbose(Settings settings, String format, Object... args)
    {
        if (settings.verbose <= 0)
        {
            return;
        }

        System.out.format(format, args);
        System.out.println();
    }


    private void executeGetClientApi(AuthleteApi api, Settings settings)
    {
        String value = getFirstKeyOrExit(settings.parameters, "getClient", "clientId");

        long clientId;

        try
        {
            clientId = Long.parseLong(value);
        }
        catch (Exception e)
        {
            showErrorAndExit("The value of {clientId} is invalid.");
            return;
        }

        // Get the client information.
        verbose(settings, "Calling getClient(clientId=%d)", clientId);
        Client client = api.getClient(clientId);

        // Dump the client information.
        System.out.println(Utils.toJson(client, true));
    }


    private void executeGetClientAuthorizationListApi(AuthleteApi api, Settings settings)
    {
        // Request parameters for /api/client/authorization/get/list API.
        ClientAuthorizationGetListRequest request = new ClientAuthorizationGetListRequest();

        // Request parameter: subject (mandatory)
        String subject = settings.parameters.get("subject");
        if (subject == null || subject.length() == 0)
        {
            showErrorAndExit("getClientAuthorizationList requires a 'subject' value.");
            return;
        }
        request.setSubject(subject);

        // Request parameter: developer (optional)
        if (settings.parameters.containsKey("developer"))
        {
            request.setDeveloper(settings.parameters.get("developer"));
        }

        // Request parameter: start (optional)
        if (settings.parameters.containsKey("start"))
        {
            request.setStart(getIntegerOrExitIfNeeded(settings.parameters, "start", 0));
        }

        // Request parameter: end (optional)
        if (settings.parameters.containsKey("end"))
        {
            request.setStart(getIntegerOrExitIfNeeded(settings.parameters, "end", 5));
        }

        verbose(settings,
                "Calling getClientAuthorizationList(request) (subject=%s, developer=%s, start=%d, end=%d)",
                request.getSubject(), request.getDeveloper(), request.getStart(), request.getEnd());

        AuthorizedClientListResponse response = api.getClientAuthorizationList(request);

        // Dump the list.
        System.out.println(Utils.toJson(response, true));
    }


    private void executeGetClientListApi(AuthleteApi api, Settings settings)
    {
        boolean useRange = false;
        int start = 0;
        int end = 5;

        if (settings.parameters.containsKey("start") || settings.parameters.containsKey("end"))
        {
            start    = getIntegerOrExitIfNeeded(settings.parameters, "start", 0);
            end      = getIntegerOrExitIfNeeded(settings.parameters, "end", 5);
            useRange = true;
        }

        String developer = settings.parameters.get("developer");

        ClientListResponse response;

        if (developer != null)
        {
            if (useRange)
            {
                verbose(settings, "Calling getClientList(developer=%s, start=%d, end=%d)", developer, start, end);
                response = api.getClientList(developer, start, end);
            }
            else
            {
                verbose(settings, "Calling getClientList(developer=%s)", developer);
                response = api.getClientList(developer);
            }
        }
        else
        {
            if (useRange)
            {
                verbose(settings, "Calling getClientList(start=%d, end=%d)", start, end);
                response = api.getClientList(start, end);
            }
            else
            {
                verbose(settings, "Calling getClientList()");
                response = api.getClientList();
            }
        }

        // Dump the list.
        System.out.println(Utils.toJson(response, true));
    }


    private void executeGetServiceConfigurationApi(AuthleteApi api, Settings settings)
    {
        boolean pretty = getBoolean(settings.parameters, "pretty", true);

        // Get the service configuration.
        verbose(settings, "Calling getServiceConfiguration(pretty=%s)", pretty);
        String configuration = api.getServiceConfiguration(pretty);

        // Dump the configuration.
        System.out.println(configuration);
    }


    private void executeGetServiceJwksApi(AuthleteApi api, Settings settings)
    {
        boolean pretty  = getBoolean(settings.parameters, "pretty", true);
        boolean include = getBoolean(settings.parameters, "includePrivateKeys", false);

        // Get the JWKS of the service.
        verbose(settings, "Calling getServiceJwks(pretty=%s, includePrivateKeys=%s)", pretty, include);
        String jwks = api.getServiceJwks(pretty, include);

        // Dump the JWKS.
        System.out.println(jwks);
    }


    private void executeGetServiceApi(AuthleteApi api, Settings settings)
    {
        String value = getFirstKeyOrExit(settings.parameters, "getService", "serviceApiKey");

        long serviceApiKey;

        try
        {
            serviceApiKey = Long.parseLong(value);
        }
        catch (Exception e)
        {
            showErrorAndExit("The value of {serviceApiKey} is invalid.");
            return;
        }

        // Get the service information.
        verbose(settings, "Calling getService(serviceApiKey=%d)", serviceApiKey);
        Service service = api.getService(serviceApiKey);

        // Dump the service information.
        System.out.println(Utils.toJson(service, true));
    }


    private void executeGetServiceListApi(AuthleteApi api, Settings settings)
    {
        ServiceListResponse response;

        if (settings.parameters.containsKey("start") || settings.parameters.containsKey("end"))
        {
            int start = getIntegerOrExitIfNeeded(settings.parameters, "start", 0);
            int end   = getIntegerOrExitIfNeeded(settings.parameters, "end", 5);

            // Get the service list.
            verbose(settings, "Calling getServiceList(start=%d, end=%d)", start, end);
            response = api.getServiceList(start, end);
        }
        else
        {
            // Get the service list.
            verbose(settings, "Calling getServiceList()");
            response = api.getServiceList();
        }

        // Dump the list.
        System.out.println(Utils.toJson(response, true));
    }
}
