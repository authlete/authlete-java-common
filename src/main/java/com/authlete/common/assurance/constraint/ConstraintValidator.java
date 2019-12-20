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
 * Validator for constraints in {@code verified_claims}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class ConstraintValidator
{
    // OpenID Connect for Identity Assurance 1.0
    // 5.1. Requesting End-User Claims
    //
    //   ... The purpose MUST NOT be shorter than 3 characters or
    //   longer than 300 characters. ...
    //
    private static final int PURPOSE_LEN_MIN = 3;
    private static final int PURPOSE_LEN_MAX = 300;


    /**
     * Validate a constraint that represents an object which includes
     * {@code verified_claims}.
     *
     * @param container
     *         A constraint which includes {@code verified_claims}.
     *
     * @throws ConstraintException
     *         The given constraint violates one or more requirements in the
     *         specification.
     */
    public void validate(VerifiedClaimsContainerConstraint container) throws ConstraintException
    {
        if (container == null)
        {
            return;
        }

        // If the container itself does not exist.
        if (container.exists() == false)
        {
            return;
        }

        // Validate "verified_claims".
        validate(container.getVerifiedClaims());
    }


    /**
     * Validate a constraint that represents {@code verified_claims}.
     *
     * @param verifiedClaims
     *         A constraint which represents {@code verified_claims}.
     *
     * @throws ConstraintException
     *         The given constraint violates one or more requirements in the
     *         specification.
     */
    public void validate(VerifiedClaimsConstraint verifiedClaims) throws ConstraintException
    {
        if (verifiedClaims == null)
        {
            return;
        }

        // If "verified_claims" does not exist.
        if (verifiedClaims.exists() == false)
        {
            return;
        }

        // If "verified_claims" is null.
        if (verifiedClaims.isNull())
        {
            return;
        }

        // Nothing special to validate on "verification".

        // Validate "claims".
        validate(verifiedClaims.getClaims());
    }


    /**
     * Validate a constraint that represents {@code verified_claims/claims}.
     *
     * @param claims
     *         A constraint which represents {@code verified_claims/claims}.
     *
     * @throws ConstraintException
     *         The given constraint violates one or more requirements in the
     *         specification.
     */
    public void validate(ClaimsConstraint claims) throws ConstraintException
    {
        if (claims == null)
        {
            return;
        }

        // If "claims" does not exist.
        if (claims.exists() == false)
        {
            return;
        }

        // If "claims" is null.
        if (claims.isNull())
        {
            return;
        }

        // If "claims" is empty.
        if (claims.size() == 0)
        {
            // OpenID Connect for Identity Assurance 1.0,
            // 5.1. Requesting End-User Claims, the last paragraph
            //
            //   Note: If the claims sub-element is empty or contains a Claim
            //   not fulfilling the requirements defined in Section 4.2, the
            //   OP will abort the transaction with an invalid_request error.
            throw Helper.exception("'claims' is empty.");
        }

        // For each claim.
        for (Map.Entry<String, VerifiedClaimConstraint> entry : claims.entrySet())
        {
            validate(entry.getKey(), entry.getValue());
        }
    }


    /**
     * Validate a constraint that represents a claim in {@code verified_claims/claims}.
     *
     * @param verifiedClaims
     *         A constraint which represents a claim in {@code verified_claims/claims}.
     *
     * @throws ConstraintException
     *         The given constraint violates one or more requirements in the
     *         specification.
     */
    public void validate(String key, VerifiedClaimConstraint verifiedClaim) throws ConstraintException
    {
        if (verifiedClaim == null)
        {
            return;
        }

        // If the claim itself does not exist.
        if (verifiedClaim.exists() == false)
        {
            return;
        }

        // If the claim is null.
        if (verifiedClaim.isNull())
        {
            return;
        }

        // Validate "purpose".
        validatePurpose(key, verifiedClaim.getPurpose());
    }


    /**
     * Validate {@code purpose} of a claim in {@code verified_claims/claims}.
     *
     * @param key
     *         The name of a claim.
     *
     * @param purpose
     *         The {@code purpose} of the claim.
     *
     * @throws ConstraintException
     *         The length of the purpose is shorter than 3 or longer than 300.
     *         When {@code purpose} is null, no exception is thrown.
     */
    public void validatePurpose(String key, String purpose) throws ConstraintException
    {
        if (purpose == null)
        {
            return;
        }

        int len = purpose.length();

        if (len < PURPOSE_LEN_MIN)
        {
            throw Helper.exception(
                    "The purpose of '%s' is shorter than %d.", key, PURPOSE_LEN_MIN);
        }

        if (PURPOSE_LEN_MAX < len)
        {
            throw Helper.exception(
                    "The purpose of '%s' is longer than %d.", key, PURPOSE_LEN_MAX);
        }
    }
}
