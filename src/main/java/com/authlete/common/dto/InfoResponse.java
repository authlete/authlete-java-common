/*
 * Copyright (C) 2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.util.List;


/**
 * Response from Authlete's {@code /api/info} API.
 *
 * <p>
 * The API is used to get the server information
 * from the {@code /api/info} API.
 * </p>
 *
 * @since 3.99
 */
public class InfoResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    private List<String> features;
    private String version;

    /**
     * Get the list of features retrieved from the /api/info API.
     *
     * @return
     *         The list of features retrieved from the /api/info API.
     */
    public List<String> getFeatures()
    {
        return features;
    }

    /**
     * Sets the list of features for the {@link InfoResponse} object.
     *
     * @param features
     *         The list of features to set. Each feature is represented as a {@link String}.
     *
     * @return
     *         {@code this} object.
     */
    public InfoResponse setFeatures(List<String> features)
    {
        this.features = features;

        return this;
    }

    /**
     * Retrieves the version of the Authlete API server.
     *
     * @return
     *         The version of the Authlete API server as a string.
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * Set the version of the response.
     *
     * @param version
     *         The version to set. For example, "3.0.0".
     *
     * @return
     *         {@code this} object.
     */
    public InfoResponse setVersion(String version)
    {
        this.version = version;

        return this;
    }
}
