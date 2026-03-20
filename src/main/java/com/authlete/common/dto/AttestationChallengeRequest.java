/*
 * Copyright (C) 2026 Authlete, Inc.
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


import java.io.Serializable;


/**
 * Request to Authlete's {@code /attestation/challenge} API.
 *
 * <p>
 * The Authlete API is supposed to be used from within the implementation of
 * the challenge endpoint of the authorization server.
 * </p>
 *
 * @since 4.39
 * @since Authlete 3.0.28
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-attestation-based-client-auth/">
 *      OAuth 2.0 Attestation-Based Client Authentication</a>
 */
public class AttestationChallengeRequest implements Serializable
{
    private static final long serialVersionUID = 1L;


    private boolean pretty;



    /**
     * Get the flag indicating whether the challenge response is written in the
     * pretty format or not.
     *
     * @return
     *         {@code true} if the challenge response is written in the pretty
     *         format.
     */
    public boolean isPretty()
    {
        return pretty;
    }


    /**
     * Set the flag indicating whether the challenge response is written in the
     * pretty format or not.
     *
     * @param pretty
     *         {@code true} to write the challenge response in the pretty format.
     *
     * @return
     *         {@code this} object.
     */
    public AttestationChallengeRequest setPretty(boolean pretty)
    {
        this.pretty = pretty;

        return this;
    }
}
