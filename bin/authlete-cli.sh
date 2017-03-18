#!/bin/bash
#
# LICENSE
# -------
#
#   Copyright (C) 2017 Authlete, Inc.
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.
#
#
# OVERVIEW
# --------
#
#   Command line interface for Authlete API.
#
#
# USAGE
# -----
#
#   authlete-cli.sh [options] {API name} [arguments]
#
#
# API NAME AND ARGUMENTS
# ----------------------
#
#   getClient {clientId}
#   getClientAuthorizationList subject={subject} [developer={developer}] [start={start}] [end={end}]
#   getClientList [developer={developer}] [start={start}] [end={end}]
#   getService {serviceApiKey}
#   getServiceConfiguration [pretty={true|false}]
#   getServiceJwks [pretty={true|false}] [includePrivateKeys={true|false}]
#   getServiceList [start={start}] [end={end}]
#
#   Note: API name is case-insensitive.
#
#
# EXAMPLES
# --------
#
#   $ bin/authlete-cli.sh getClient 4326385670
#   $ bin/authlete-cli.sh getClientAuthorizationList subject=authlete_5526908833
#   $ bin/authlete-cli.sh getClientList developer=authlete_5526908833
#   $ bin/authlete-cli.sh getService 5526908833
#   $ bin/authlete-cli.sh getServiceConfiguration pretty=true
#   $ bin/authlete-cli.sh getServiceJwks pretty=true includePrivateKeys=true
#   $ bin/authlete-cli.sh getServiceList start=1
#
#
# NOTE
# ----
#
#   "authlete.properties" must exist in the top directory.
#


#--------------------------------------------------
# Entry point
#--------------------------------------------------
__main()
{
    # Top directory of this source tree.
    local top_dir=$(dirname $(dirname $0))

    # Move to the top directory.
    cd "${top_dir}"

    # Check if "authlete.properties" exists.
    if [ ! -e "authlete.properties" ]; then
        echo "ERROR: 'authlete.properties' file is not found."
        exit 1
    fi

    # Command line to execute.
    local command_line=(mvn -q exec:java -Dexec.args="$*")

    # Execute the command line.
    exec "${command_line[@]}"
}


#--------------------------------------------------
# S T A R T
#--------------------------------------------------
__main "$@"
