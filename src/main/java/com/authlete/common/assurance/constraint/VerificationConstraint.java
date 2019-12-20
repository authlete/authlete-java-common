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
package com.authlete.common.assurance.constraint;


import java.util.Map;


/**
 * The class that represents the constraint for {@code verified_claims/verification}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class VerificationConstraint extends BaseConstraint
{
    private LeafConstraint trustFramework;
    private TimeConstraint time;
    private LeafConstraint verificationProcess;
    private EvidenceArrayConstraint evidence;


    /**
     * Get the constraint for {@code trust_framework}.
     *
     * @return
     *         The constraint for {@code trust_framework}.
     */
    public LeafConstraint getTrustFramework()
    {
        return trustFramework;
    }


    /**
     * Set the constraint for {@code trust_framework}.
     *
     * @param constraint
     *         The constraint for {@code trust_framework}.
     */
    public void setTrustFramework(LeafConstraint constraint)
    {
        this.trustFramework = constraint;
    }


    /**
     * Get the constraint for {@code time}.
     *
     * @return
     *         The constraint for {@code time}.
     */
    public TimeConstraint getTime()
    {
        return time;
    }


    /**
     * Set the constraint for {@code time}.
     *
     * @param constraint
     *         The constraint for {@code time}.
     */
    public void setTime(TimeConstraint constraint)
    {
        this.time = constraint;
    }


    /**
     * Get the constraint for {@code verification_process}.
     *
     * @return
     *         The constraint for {@code verification_process}.
     */
    public LeafConstraint getVerificationProcess()
    {
        return verificationProcess;
    }


    /**
     * Set the constraint for {@code verification_process}.
     *
     * @param constraint
     *         The constraint for {@code verification_process}.
     */
    public void setVerificationProcess(LeafConstraint constraint)
    {
        this.verificationProcess = constraint;
    }


    /**
     * Get the constraint for {@code evidence}.
     *
     * @return
     *         The constraint for {@code evidence}.
     */
    public EvidenceArrayConstraint getEvidence()
    {
        return evidence;
    }


    /**
     * Set the constraint for {@code evidence}.
     *
     * @param constraint
     *         The constraint for {@code evidence}.
     */
    public void setEvidence(EvidenceArrayConstraint constraint)
    {
        this.evidence = constraint;
    }


    /**
     * Create a {@code VerificationConstraint} instance from an object in the given map.
     *
     * @param map
     *         A map that may contain {@code "verification"}.
     *
     * @param key
     *         The key that identifies the object in the map. In normal cases,
     *         the key is {@code "verification"}.
     *
     * @return
     *         A {@code VerificationConstraint} instance that represents
     *         {@code "verification"}. Even if the map does not contain the
     *         given key, an instance of {@code VerificationConstraint} is
     *         returned.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static VerificationConstraint extract(Map<?,?> map, String key) throws ConstraintException
    {
        VerificationConstraint instance = new VerificationConstraint();
        instance.setExists(map.containsKey(key));

        if (instance.exists())
        {
            fill(instance, map.get(key), key);
        }

        return instance;
    }


    private static void fill(VerificationConstraint instance, Object object, String key)
    {
        if (object == null)
        {
            instance.setNull(true);
            return;
        }

        Map<?,?> map = Helper.ensureMap(object, key);

        instance.trustFramework      = LeafConstraint.extract(         map, "trust_framework");
        instance.time                = TimeConstraint.extract(         map, "time");
        instance.verificationProcess = LeafConstraint.extract(         map, "verification_process");
        instance.evidence            = EvidenceArrayConstraint.extract(map, "evidence");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "trust_framework",      trustFramework);
        addIfAvailable(map, "time",                 time);
        addIfAvailable(map, "verification_process", verificationProcess);

        if (evidence != null && evidence.exists())
        {
            map.put("evidence", evidence.toList());
        }

        return map;
    }
}
