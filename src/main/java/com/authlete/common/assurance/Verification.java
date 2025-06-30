/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.assurance;


import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * The class that represents {@code verified_claims/verification}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class Verification extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;


    private static final String TRUST_FRAMEWORK      = "trust_framework";
    private static final String TIME                 = "time";
    private static final String VERIFICATION_PROCESS = "verification_process";
    private static final String EVIDENCE             = "evidence";
    private static final Set<String> KEYS;


    static
    {
        KEYS = new HashSet<String>();
        KEYS.add(TRUST_FRAMEWORK);
        KEYS.add(TIME);
        KEYS.add(VERIFICATION_PROCESS);
        KEYS.add(EVIDENCE);
    }


    /**
     * Get the trust framework governing the identity verification process
     * and the identity assurance level of the OP.
     *
     * @return
     *         The trust framework.
     */
    public String getTrustFramework()
    {
        return (String)get(TRUST_FRAMEWORK);
    }


    /**
     * Set the trust framework governing the identity verification process
     * and the identity assurance level of the OP.
     *
     * @param framework
     *         The trust framework.
     *
     * @return
     *         {@code this} object.
     */
    public Verification setTrustFramework(String framework)
    {
        put(TRUST_FRAMEWORK, framework);

        return this;
    }


    /**
     * Check if this object contains {@code "trust_framework"}.
     *
     * @return
     *         {@code true} if this object contains {@code "trust_framework"}.
     */
    public boolean containsTrustFramework()
    {
        return containsKey(TRUST_FRAMEWORK);
    }


    /**
     * Remove {@code "trust_framework"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeTrustFramework()
    {
        return (String)remove(TRUST_FRAMEWORK);
    }


    /**
     * Get the date and time when identity verification took place.
     *
     * @return
     *         The date and time when identity verification took place.
     */
    public String getTime()
    {
        return (String)get(TIME);
    }


    /**
     * Set the date and time when identity verification took place.
     *
     * @param time
     *         The date and time when identity verification took place.
     *
     * @return
     *         {@code this} object.
     */
    public Verification setTime(String time)
    {
        put(TIME, time);

        return this;
    }


    /**
     * Check if this object contains {@code "time"}.
     *
     * @return
     *         {@code true} if this object contains {@code "time"}.
     */
    public boolean containsTime()
    {
        return containsKey(TIME);
    }


    /**
     * Remove {@code "time"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeTime()
    {
        return (String)remove(TIME);
    }


    /**
     * Get the identity verification process.
     *
     * @return
     *         The identity verification process.
     */
    public String getVerificationProcess()
    {
        return (String)get(VERIFICATION_PROCESS);
    }


    /**
     * Set the identity verification process.
     *
     * @param process
     *         The identity verification process.
     *
     * @return
     *         {@code this} object.
     */
    public Verification setVerificationProcess(String process)
    {
        put(VERIFICATION_PROCESS, process);

        return this;
    }


    /**
     * Check if this object contains {@code "verification_process"}.
     *
     * @return
     *         {@code true} if this object contains {@code "verification_process"}.
     */
    public boolean containsVerificationProcess()
    {
        return containsKey(VERIFICATION_PROCESS);
    }


    /**
     * Remove {@code "verification_process"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public String removeVerificationProcess()
    {
        return (String)remove(VERIFICATION_PROCESS);
    }


    /**
     * Get the evidence the OP used to verify the user's identity.
     *
     * @return
     *         The evidence the OP used to verify the user's identity.
     */
    public EvidenceArray getEvidence()
    {
        return (EvidenceArray)get(EVIDENCE);
    }


    /**
     * Set the evidence the OP used to verify the user's identity.
     *
     * @param evidence
     *         The evidence the OP used to verify the user's identity.
     *
     * @return
     *         {@code this} object.
     */
    public Verification setEvidence(EvidenceArray evidence)
    {
        put(EVIDENCE, evidence);

        return this;
    }


    /**
     * Check if this object contains {@code "evidence"}.
     *
     * @return
     *         {@code true} if this object contains {@code "evidence"}.
     */
    public boolean containsEvidence()
    {
        return containsKey(EVIDENCE);
    }


    /**
     * Remove {@code "evidence"} from this object.
     *
     * @return
     *         The old value that may have existed before removal.
     */
    public EvidenceArray removeEvidence()
    {
        return (EvidenceArray)remove(EVIDENCE);
    }


    /**
     * Add the evidence the OP used to verify the user's identity.
     *
     * @param evidence
     *         The evidence the OP used to verify the user's identity.
     *
     * @return
     *         {@code this} object.
     */
    public Verification addEvidence(Evidence evidence)
    {
        EvidenceArray list = getEvidence();

        if (list == null)
        {
            list = new EvidenceArray();
            put(EVIDENCE, list);
        }

        list.add(evidence);

        return this;
    }


    /**
     * Create a {@code Verification} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "verification"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "verification"}.
     *
     * @return
     *         A {@code Verification} instance that represents {@code "verification"}.
     *         If the map does not contain the given key, null is returned.
     *
     * @throws IdentityAssuranceException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static Verification extract(Map<?, ?> map, String key) throws IdentityAssuranceException
    {
        Object object = map.get(key);

        if (object == null)
        {
            return null;
        }

        Verification verification = new Verification();

        fill(verification, object, key);

        return verification;
    }


    private static void fill(Verification instance, Object object, String key)
    {
        Map<?, ?> map = Helper.ensureMap(object, key);

        // "trust_framework": required
        fillTrustFramework(instance, map, key);

        // "time": optional
        fillTime(instance, map, key);

        // verification_process": optional
        fillVerificationProcess(instance, map, key);

        // "evidence": optional
        fillEvidence(instance, map, key);

        // "additionalProperties": false
        Helper.ensureNoAdditionalProperties(map, key, KEYS);
    }


    private static void fillTrustFramework(Verification instance, Map<?, ?> map, String key)
    {
        // The value of "trust_framework" in the map.
        String value = Helper.extractString(map, TRUST_FRAMEWORK, key, true);

        instance.setTrustFramework(value);
    }


    private static void fillTime(Verification instance, Map<?, ?> map, String key)
    {
        // The value of "time" in the map.
        String value = Helper.extractDateTime(map, TIME, key, false);

        instance.setTime(value);
    }


    private static void fillVerificationProcess(Verification instance, Map<?, ?> map, String key)
    {
        // The value of "verification_process" in the map.
        String value = Helper.extractString(map, VERIFICATION_PROCESS, key, false);

        instance.setVerificationProcess(value);
    }


    private static void fillEvidence(Verification instance, Map<?, ?> map, String key)
    {
        // The value of "evidence" in the map.
        EvidenceArray value = EvidenceArray.extract(map, EVIDENCE);

        instance.setEvidence(value);
    }
}
