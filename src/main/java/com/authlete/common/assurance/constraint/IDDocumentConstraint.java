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
 * The class that represents the constraint for {@code id_document}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class IDDocumentConstraint extends EvidenceConstraint
{
    private LeafConstraint method;
    private VerifierConstraint verifier;
    private TimeConstraint time;
    private DocumentConstraint document;


    /**
     * Get the constraint for {@code method}.
     *
     * @return
     *         The constraint for {@code method}.
     */
    public LeafConstraint getMethod()
    {
        return method;
    }


    /**
     * Set the constraint for {@code method}.
     *
     * @param constraint
     *         The constraint for {@code method}.
     */
    public void setMethod(LeafConstraint constraint)
    {
        this.method = constraint;
    }


    /**
     * Get the constraint for {@code verifier}.
     *
     * @return
     *         The constraint for {@code verifier}.
     */
    public VerifierConstraint getVerifier()
    {
        return verifier;
    }


    /**
     * Set the constraint for {@code verifier}.
     *
     * @param constraint
     *         The constraint for {@code verifier}.
     */
    public void setVerifier(VerifierConstraint constraint)
    {
        this.verifier = constraint;
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
     * Get the constraint for {@code document}.
     *
     * @return
     *         The constraint for {@code document}.
     */
    public DocumentConstraint getDocument()
    {
        return document;
    }


    /**
     * Set the constraint for {@code document}.
     *
     * @param constraint
     *         The constraint for {@code document}.
     */
    public void setDocument(DocumentConstraint constraint)
    {
        this.document = constraint;
    }


    /**
     * Create an {@code IDDocumentConstraint} instance from the given object.
     *
     * @param map
     *         A map that represents {@code "id_document"}.
     *
     * @return
     *         An {@code IDDocumentConstraint} instance that represents
     *         {@code "id_document"}.
     *
     * @throws ConstraintException
     *         The structure of the map does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static IDDocumentConstraint extract(Map<?,?> map) throws ConstraintException
    {
        IDDocumentConstraint instance = new IDDocumentConstraint();
        instance.setExists(true);

        fill(instance, map);

        return instance;
    }


    private static void fill(IDDocumentConstraint instance, Map<?,?> map)
    {
        EvidenceConstraint.fill(instance, map);

        instance.method   = LeafConstraint.extract(    map, "method");
        instance.verifier = VerifierConstraint.extract(map, "verifier");
        instance.time     = TimeConstraint.extract    (map, "time");
        instance.document = DocumentConstraint.extract(map, "document");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "method",   method);
        addIfAvailable(map, "verifier", verifier);
        addIfAvailable(map, "time",     time);
        addIfAvailable(map, "document", document);

        return map;
    }
}
