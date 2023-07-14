/*
 * Copyright (C) 2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.api;


import java.util.Arrays;
import java.util.List;


/**
 * Utility class to describe the possible values used in the RAR objects
 * when accessing the Authlete API with an access token.
 *
 * The enum values each represent a specific possible value of the
 * <code>action</code> field of the RAR object. Each AccessRight value in turn
 * is aware of whether the action is specific to a particular service or
 * client, which necessitate the presence of the <code>service</code> and
 * <code>client</code> fields of the RAR object, respectively. Each AccessRight
 * value also knows which other AccessRights it subsumes. For example,
 * the <code>USE_SERVICE</code> value also includes the <code>VIEW_SERVICE</code>
 * and <code>VIEW_CLIENT</code> actions inherently.
 *
 * The <code>AUTHLETE_API_V3</code> constant is used as the <code>type</code>
 * value for all RAR objects addressing the Authlete API.
 *
 * @since 3.73
 *
 * @author jricher
 *
 */
public enum AccessRight
{
    /** can view client details on this service or client */
    VIEW_CLIENT
        (true, true),
    /** can modify existing clients on this service or client */
    MODIFY_CLIENT
        (true, true, VIEW_CLIENT),
    /** can view the details of this service */
    VIEW_SERVICE
        (false, true, VIEW_CLIENT),
    /** can use the non-destructive service API calls (auth endpoint, token endpoint, etc.) */
    USE_SERVICE
        (false, true, VIEW_SERVICE, VIEW_CLIENT),
    /** can create new clients on this service */
    CREATE_CLIENT
        (false, true, USE_SERVICE, VIEW_SERVICE, MODIFY_CLIENT, VIEW_CLIENT),
    /** can modify this service */
    MODIFY_SERVICE
        (false, true, USE_SERVICE, VIEW_SERVICE, CREATE_CLIENT, MODIFY_CLIENT, VIEW_CLIENT),
    /** can view default service parameters */
    VIEW_DEFAULT_SERVICE
        (false, false),
    /** can create additional services */
    CREATE_SERVICE
        (false, false, VIEW_DEFAULT_SERVICE),
    /** can delete a specific service */
    DELETE_SERVICE
        (false, true),
    /** can call administrative functions on the Authlete server */
    ADMIN
        (false, false, VIEW_DEFAULT_SERVICE, CREATE_SERVICE, DELETE_SERVICE, USE_SERVICE, VIEW_SERVICE, MODIFY_SERVICE, CREATE_CLIENT, MODIFY_CLIENT, VIEW_CLIENT)
    ;


    /**
     * The <code>type</code> value of the RAR object for the authlete API.
     */
    public static final String AUTHLETE_API_V3 = "https://api.authlete.com/v3/";

    private final boolean serviceSpecific;
    private final boolean clientSpecific;
    private final List<AccessRight> includes;


    private AccessRight(boolean clientSpecific, boolean serviceSpecific, AccessRight... includes)
    {
        this.serviceSpecific = serviceSpecific || clientSpecific; // client-specific implies also service-specific
        this.clientSpecific = clientSpecific;
        this.includes = Arrays.asList(includes);
    }


    /**
     * Return true if this access right requires a specific service to be applied.
     */
    public boolean isServiceSpecific()
    {
        return serviceSpecific;
    }


    /**
     * Return true if this access right requires a specific service and client to be applied.
     * @return
     */
    public boolean isClientSpecific()
    {
        return clientSpecific;
    }


    /**
     * Return true if this access right has at least the amount of access of the
     * compared access right. This checks whether the clientId and serviceId
     * parameters are present if they are required for the given access right
     * to function.
     */
    public boolean canDo(long clientId, long serviceId, AccessRight compare)
    {
        if (this.serviceSpecific && serviceId <= 0)
        {
            return false;
        }
        if (this.clientSpecific && (clientId <= 0 || serviceId <= 0)) // client specific also needs a service ID
        {
            return false;
        }
        if (this.equals(compare) || this.includes.contains(compare))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * Look up and fetch an access right value based on its
     * (lowercased) name from a JSON object.
     */
    public static AccessRight fromJson(String key)
    {
        try
        {
            return key == null ? null : valueOf(key.toUpperCase());
        }
        catch (IllegalArgumentException e)
        {
            // FIXME: for now, hide/ignore unsupported values
            return null;
        }
    }


    /**
     * Return the name of this access right in lowercase form,
     * appropriate for use in JSON objects (such as RAR object values).
     */
    public String toJson()
    {
        return name().toLowerCase();
    }
}
