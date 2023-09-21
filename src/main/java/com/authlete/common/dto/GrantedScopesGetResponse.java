/*
 * Copyright (C) 2016 Authlete, Inc.
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
package com.authlete.common.dto;


/**
 * Response from Authlete's <code>/client/granted_scopes/get/{clientId}</code> API.
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.38
 * @since Authlete 1.1
 */
public class GrantedScopesGetResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;

    private long serviceApiKey;
    private long clientId;
    private String subject;
    private String[] latestGrantedScopes;
    private String[] mergedGrantedScopes;
    private long modifiedAt;


    /**
     * Get the API key of the service.
     *
     * @return
     *         The API key of the service.
     */
    public long getServiceApiKey()
    {
        return serviceApiKey;
    }


    /**
     * Set the API key of the service.
     *
     * @param key
     *         The API key of the service.
     */
    public void setServiceApiKey(long key)
    {
        this.serviceApiKey = key;
    }


    /**
     * Get the client ID.
     *
     * @return
     *         The client ID.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID.
     *
     * @param clientId
     *         The client ID.
     */
    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }


    /**
     * Get the subject (= unique identifier) of the user
     * who has granted authorization to the client.
     *
     * @return
     *         The subject of the user.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= unique identifier) of the user
     * who has granted authorization to the client.
     *
     * @param subject
     *         The subject of the user.
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    /**
     * Get the scopes granted to the client application by the last
     * authorization process by the user (who is identified by the
     * subject).
     *
     * <p>
     * {@code null} means that there is no record about granted scopes.
     * An empty array means that there exists a record about granted
     * scopes but no scope has been granted to the client application.
     * If the returned array holds some elements, they are the scopes
     * granted to the client application by the last authorization
     * process.
     * </p>
     *
     * @return
     *         The scopes granted to the client application by the
     *         last authorization process.
     */
    public String[] getLatestGrantedScopes()
    {
        return latestGrantedScopes;
    }


    /**
     * Set the scopes granted to the client application by the last
     * authorization process by the user (who is identified by the
     * subject).
     *
     * @param scopes
     *         The scopes granted to the client application by the
     *         last authorization process.
     */
    public void setLatestGrantedScopes(String[] scopes)
    {
        this.latestGrantedScopes = scopes;
    }


    /**
     * Get the scopes granted to the client application by all the
     * past authorization processes. Note that revoked scopes are
     * not included.
     *
     * @return
     *         The scopes granted to the client application by all
     *         the past authorization processes.
     */
    public String[] getMergedGrantedScopes()
    {
        return mergedGrantedScopes;
    }


    /**
     * Set the scopes granted to the client application by all the
     * past authorization processes. Note that revoked scopes are
     * not included.
     *
     * @param scopes
     *         The scopes granted to the client application by all
     *         the past authorization processes.
     */
    public void setMergedGrantedScopes(String[] scopes)
    {
        this.mergedGrantedScopes = scopes;
    }


    /**
     * Get the timestamp in milliseconds since Unix epoch
     * at which this record was modified.
     *
     * @return
     *         The timestamp at which this record was modified.
     */
    public long getModifiedAt()
    {
        return modifiedAt;
    }


    /**
     * Set the timestamp in milliseconds since Unix epoch
     * at which this record was modified.
     *
     * @param modifiedAt
     *         The timestamp at which this record was modified.
     */
    public void setModifiedAt(long modifiedAt)
    {
        this.modifiedAt = modifiedAt;
    }
}
