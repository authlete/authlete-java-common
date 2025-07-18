/*
 * Copyright (C) 2022 Authlete, Inc.
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
package com.authlete.common.ida;


import static com.authlete.common.ida.DatasetExtractorMessageCode.DE01;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE02;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE03;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE04;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE05;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE06;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE07;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE08;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE09;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE10;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE11;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE12;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE13;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE14;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE15;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE16;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE17;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE18;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE19;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE20;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE21;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE22;
import static com.authlete.common.ida.DatasetExtractorMessageCode.DE23;
import static java.time.ZoneOffset.UTC;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;


/**
 * A utility to extract a dataset that meets conditions specified by a
 * {@code verified_claims} request. The specification of the request is defined
 * in <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 * >OpenID Connect for Identity Assurance 1.0</a>.
 *
 * <p>
 * The {@link #extract(Map, Map) extract()} method of this class builds a new
 * dataset that satisfies conditions of a {@code verified_claims} request from
 * the given original dataset. What the method does is not just to return the
 * entire dataset when it meets conditions of a {@code verified_claims} request.
 * Instead, the method extracts only necessary parts from the original dataset
 * and omits unnecessary parts in order to conform to the filtering rules and
 * the data minimization policy that are written in the specification.
 * </p>
 *
 * <p>
 * For example, when the original dataset holds the following:
 * </p>
 *
 * <pre>
 * {
 *   "verification": {
 *     "trust_framework": "uk_tfida",
 *     "evidence": [
 *       {
 *         "type": "electronic_record",
 *         "check_details": [
 *           {
 *             "check_method": "kbv",
 *             "organization": "TheCreditBureau",
 *             "txn": "kbv1-hf934hn09234ng03jj3"
 *           }
 *         ],
 *         "time": "2021-04-09T14:12Z",
 *         "record": {
 *           "type": "mortgage_account",
 *           "source": {
 *             "name": "TheCreditBureau"
 *           }
 *         }
 *       },
 *       {
 *         "type": "electronic_record",
 *         "check_details": [
 *           {
 *             "check_method": "kbv",
 *             "organization": "OpenBankingTPP",
 *             "txn": "kbv2-nm0f23u9459fj38u5j6"
 *           }
 *         ],
 *         "time": "2021-04-09T14:12Z",
 *         "record": {
 *           "type": "bank_account",
 *           "source": {
 *             "name": "TheBank"
 *           }
 *         }
 *       }
 *     ]
 *   },
 *   "claims": {
 *     "given_name": "Sarah",
 *     "family_name": "Meredyth",
 *     "birthdate": "1976-03-11",
 *     "place_of_birth": {
 *       "country": "UK"
 *     },
 *     "address": {
 *       "locality": "Edinburgh",
 *       "postal_code": "EH1 9GP",
 *       "country": "UK",
 *       "street_address": "122 Burns Crescent"
 *     }
 *   }
 * }
 * </pre>
 *
 * <p>
 * and the following {@code verified_claims} request is made:
 * </p>
 *
 * <pre>
 * {
 *   "verification": {
 *     "trust_framework": null,
 *     "evidence": [
 *       {
 *         "type": {
 *           "value": "electronic_record"
 *         },
 *         "check_details": [
 *           {
 *             "check_method": null,
 *             "organization": {
 *               "value": "OpenBankingTPP"
 *             },
 *             "txn": null
 *           }
 *         ]
 *       }
 *     ]
 *   },
 *   "claims": {
 *     "given_name": null,
 *     "family_name": {
 *       "value": "Unknown"
 *     },
 *     "address": {
 *       "locality": null
 *     }
 *   }
 * }
 * </pre>
 *
 * the {@code extract()} method generates a new dataset as shown below:
 *
 * <pre>
 * {
 *   "verification": {
 *     "trust_framework": "uk_tfida",
 *     "evidence": [
 *       {
 *         "type": "electronic_record",
 *         "check_details": [
 *           {
 *             "check_method": "kbv",
 *             "organization": "OpenBankingTPP",
 *             "txn": "kbv2-nm0f23u9459fj38u5j6"
 *           }
 *         ]
 *       }
 *     ]
 *   },
 *   "claims": {
 *     "given_name": "Sarah",
 *     "address": {
 *       "locality": "Edinburgh"
 *     }
 *   }
 * }
 * </pre>
 *
 * <p>
 * Below are points to note:
 * </p>
 *
 * <ol>
 * <li>
 * The original dataset contains two elements in the {@code "evidence"} array,
 * but the generated dataset contains only one element. It is because the
 * constraint, <code>check_details/&#x002A;/organization == "OpenBankingTPP"</code>,
 * filtered out the unmatched element.
 *
 * <li style="margin-top: 1em;">
 * The generated dataset does not contain the {@code "family_name"} claim.
 * It is because the value of the {@code "family_name"} claim in the original
 * dataset is not {@code "Unknown"}. When the {@code "value"}, {@code "values"}
 * and {@code "max_age"} constraints are not satisfied under {@code "verification"},
 * the entire {@code "verified_claims"} is omitted. On the other hand, under
 * {@code "claims"}, unmatched claims are just omitted without affecting the
 * entire {@code "verified_claims"}.
 *
 * <li style="margin-top: 1em;">
 * Many properties are omitted from the generated dataset for the data
 * minimization policy. For example, {@code "address"} contains only
 * {@code "locality"}. Other properties such as {@code "postal_code"},
 * {@code "country"} and {@code "street_address"} are omitted.
 * </ol>
 *
 * <p>
 * The {@code extract()} method returns a new dataset or {@code null} silently.
 * No exception is raised in any case. If you want to view the internal process
 * of the method, set a logger by the {@link #setLogger(Logger) setLogger()}
 * method. The logging levels of {@code warn}, {@code debug} and {@code trace}
 * are used according to the following criteria.
 * </p>
 *
 * <blockquote>
 * <dl>
 * <dt>{@code warn}</dt>
 * <dd>
 * <p>
 * The {@code warn} level is used when the structure of the request seems
 * invalid (specification violation) or when the structure of the original
 * dataset seems inappropriate.
 * </p>
 * </dd>
 *
 * <dt>{@code debug}</dt>
 * <dd>
 * <p>
 * The {@code debug} level is used when the property being checked causes
 * failure of matching.
 * </p>
 * </dd>
 *
 * <dt>{@code trace}</dt>
 * <dd>
 * The {@code trace} level is used when it is determined how to process a
 * property regardless of whether the property is adopted or omitted.
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @since 3.17
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 */
public class DatasetExtractor
{
    /**
     * The key "verification" which appears in "verified_claims".
     */
    private static final String KEY_VERIFICATION = "verification";


    /**
     * The key "assurance_details" which may appear in
     * "verified_claims/verification/assurance_process".
     */
    private static final String KEY_ASSURANCE_DETAILS = "assurance_details";


    /**
     * Time used for the "max_age" constraint.
     */
    private final OffsetDateTime mCurrentTime;


    /**
     * Whether to recognize transformed claims or not.
     */
    private boolean mTransformedClaimAware = true;


    /**
     * Logger that processes logs emitted by this instance.
     */
    private Logger mLogger;


    /**
     * The default constructor.
     *
     * <p>
     * This is an alias of <code>{@link #DatasetExtractor(OffsetDateTime)
     * DatasetExtractor}(({@link OffsetDateTime})null)</code>.
     * </p>
     */
    public DatasetExtractor()
    {
        this(null);
    }


    /**
     * Constructor with time which is used for the {@code "max_age"} constraint.
     *
     * <p>
     * The {@code "max_age"} constraint here is not the one defined in <a href=
     * "https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect
     * Core 1.0</a> but the one defined in <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>.
     * </p>
     *
     * @param currentTime
     *         Time which is used for the {@code "max_age"} constraint.
     *         If {@code null} is given, <code>{@link OffsetDateTime}.{@link
     *         OffsetDateTime#now(java.time.ZoneId) now}({@link
     *         java.time.ZoneOffset#UTC UTC})</code> is used.
     */
    public DatasetExtractor(OffsetDateTime currentTime)
    {
        mCurrentTime = (currentTime != null)
                     ? currentTime : OffsetDateTime.now(UTC);
    }


    /**
     * Get the flag which indicates whether transformed claims are recognized.
     * The initial value when an instance of this class is created is
     * {@code true}.
     *
     * <p>
     * When transformed claims are recognized, they are not omitted from the
     * dataset generated by the {@link #extract(Map, Map) extract()} method.
     * Note that, however, the {@code extract()} method does not compute
     * values of transformed claims. The computation is performed by Authlete
     * when you pass the generated dataset to Authlete's
     * {@code /api/auth/authorization/issue} API or
     * {@code /api/auth/userinfo/issue} API.
     * </p>
     *
     * @return
     *         {@code true} if transformed claims are recognized.
     *
     * @see <a href="https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
     *      >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a>
     */
    public boolean isTransformedClaimAware()
    {
        return mTransformedClaimAware;
    }


    /**
     * Set the flag which indicates whether transformed claims are recognized.
     *
     * <p>
     * See the description of {@link #isTransformedClaimAware()} for details.
     * </p>
     *
     * @param aware
     *         {@code true} to be aware of transformed claims.
     *
     * @return
     *         {@code this} object.
     */
    public DatasetExtractor setTransformedClaimAware(boolean aware)
    {
        mTransformedClaimAware = aware;

        return this;
    }


    /**
     * Set a logger that processes logs emitted by this instance.
     *
     * @param logger
     *         A logger that processes logs emitted by this instance.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="https://www.slf4j.org/"
     *      >Simple Logging Facade for Java (SLF4J)</a>
     */
    public DatasetExtractor setLogger(Logger logger)
    {
        mLogger = logger;

        return this;
    }


    /**
     * Get the time which is used for the {@code "max_age"} constraint.
     *
     * @return
     *         Time which is used for the {@code "max_age"} constraint.
     *
     * @see #DatasetSelector(OffsetDateTime)
     */
    private OffsetDateTime getCurrentTime()
    {
        return mCurrentTime;
    }


    /**
     * Repeat to call the {@link #extract(Map, Map)} method for each element
     * in {@code originalDatasets} until the method succeeds in generating a
     * dataset that meets conditions of the request.
     *
     * @param request
     *         A {@code Map} instance that represents the content of a
     *         {@code "verified_claims"} request.
     *
     * @param originalDatasets
     *         A list of original datasets.
     *
     * @return
     *         A new dataset built from one of the given original datasets.
     *         If none of the original datasets satisfy the conditions of the
     *         {@code "verified_claims"} request, {@code null} is returned.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> extract(
            Map<String, Object> request, List<Map<String, Object>> originalDatasets)
    {
        if (request == null || originalDatasets == null)
        {
            return null;
        }

        // Try a original dataset in the list one by one.
        for (Object original : originalDatasets)
        {
            if (!(original instanceof Map))
            {
                continue;
            }

            // Build a new dataset that meets conditions of the request
            // from the original dataset.
            Map<String, Object> dataset =
                    extract(null, request, (Map<String, Object>)original);

            // If a new dataset was built successfully.
            if (dataset != null)
            {
                return dataset;
            }
        }

        // None of the original datasets satisfy conditions of the request.
        return null;
    }


    /**
     * Extract a dataset that meets conditions of the request from the
     * original dataset.
     *
     * @param request
     *         A {@code Map} instance that represents the content of a
     *         {@code "verified_claims"} request.
     *
     * @param original
     *         The original dataset from which this method builds a new dataset
     *         that satisfies conditions of the {@code "verified_claims"}
     *         request.
     *
     * @return
     *         A new dataset built from the original dataset. If the original
     *         dataset cannot satisfy conditions of the {@code "verified_claims"}
     *         request, {@code null} is returned.
     */
    public Map<String, Object> extract(
            Map<String, Object> request, Map<String, Object> original)
    {
        return extract(null, request, original);
    }


    private Map<String, Object> extract(
            DatasetContext context,
            Map<String, Object> request, Map<String, Object> original)
    {
        // Target to which data extracted from the original dataset are copied.
        Map<String, Object> copy = new HashMap<>();

        if (context == null)
        {
            context = new DatasetContext();
        }

        // Process the request as Map. The processMap() method returns
        // true if a new dataset that satisfies conditions of the request
        // could be built from the original dataset.
        //
        // NOTE: The processMap() method is called recursively.
        boolean continued = processMap(context, request, original, copy);

        return (continued ? copy : null);
    }


    private boolean processMap(
            DatasetContext context, Map<String, Object> request,
            Map<String, Object> original, Map<String, Object> copy)
    {
        // Loop for each property in the map. Each property is one of the
        // following.
        //
        // Case 1: Leaf claim which may be null or have a detailed requirement
        //         such as "value", "values" and "max_age".
        //
        //     "leaf_claim_1": null,
        //     "leaf_claim_2": {
        //       "values": [ ... ]
        //     }
        //
        // Case 2: Keyword for a constraint. (i.e. "value", "values" or "max_age")
        //
        //     "values": [ ... ]
        //
        // Case 3: Intermediate node which may have sub properties.
        //
        //     "intermediate_node": {
        //         "leaf_claim_1": ...
        //
        for (Map.Entry<String, Object> requestEntry : request.entrySet())
        {
            // The name of the property.
            String requestKey = requestEntry.getKey();

            // If the property is a reserved one which describes a constraint
            // for the target claim. (e.g. "values")
            if (ClaimRequirement.getReservedKeys().contains(requestKey))
            {
                // The property is not processed here. Instead, it is processed
                // in processEntryValueAsMap() later.
                continue;
            }

            // The current state of 'constraintAsFilter' which will be
            // restored later.
            boolean asFilter = context.isConstraintAsFilter();

            // If the key is "verification".
            if (requestKey.equals(KEY_VERIFICATION))
            {
                // OpenID Connect for Identity Assurance 1.0
                // Data not Matching Requirements
                //
                //   When the available data does not fulfill the requirements
                //   of the RP expressed through "value", "values", or "max_age",
                //   the following logic applies:
                //
                //     * If the respective requirement was expressed for a Claim
                //       within "verified_claims/verification", the whole
                //       "verified_claims" element MUST be omitted.
                //
                //     * Otherwise, the respective Claim MUST be omitted from
                //       the response.
                //

                // Under "verified_claims/verification", the "value", "values"
                // and "max_age" constraints are used as filters. On the other
                // hand, at other places, they are used to omit unmatched claims.
                context.setConstraintAsFilter(true);
            }

            // Update the positions of the request and the original dataset.
            //
            // NOTE:
            // The positions are referenced only for logging. They don't affect
            // the logic of data extraction implemented in this class.
            context.updatePointers(requestKey);

            // Process the property.
            boolean continued = processEntry(
                    context, requestKey, requestEntry.getValue(), original, copy);

            // Restore the previous positions of the request and the original dataset.
            context.restorePointers();

            // Restore the previous state of 'constraintAsFilter'.
            context.setConstraintAsFilter(asFilter);

            // If processing should be terminated.
            //
            // processEntry() (and other similar methods) returns false when
            // it judges that conditions of the request cannot be satisfied.
            if (continued == false)
            {
                // Return recursively.
                return false;
            }
        }

        // All the properties in the map have been processed without causing
        // failure of matching.
        return true;
    }


    @SuppressWarnings("unchecked")
    private boolean processEntry(
            DatasetContext context, String requestKey, Object requestValue,
            Map<String, Object> original, Map<String, Object> copy)
    {
        // The value of the property in the original dataset.
        Object originalValue = original.get(requestKey);

        // If the property is a transformed claim.
        if (isTransformedClaim(requestKey))
        {
            // A transformed claim is a virtual claim. Its value is computed
            // based on an existing claim. Because the computation process
            // is complex, Authlete computes values of transformed claims
            // on behalf of authorization server implementations (by the
            // /auth/authorization/issue API and /auth/userinfo/issue API).
            // Therefore, it is enough here to put the transformed claim to
            // the copy as is without the computation.
            //
            // If you are thinking of porting this implementation to other
            // systems which do not use Authlete, the logic here needs to
            // be modified because it is highly likely that support of
            // "Transformed Claims" is implemented very differently. For
            // example, you may have to compute the value of the transformed
            // claim very right here.
            //
            // Note that "predefined" transformed claims are configured per
            // authorization server and they are different from ones which
            // may be defined in "transformed_claims" in an authorization
            // request at runtime. This implies that the configuration of
            // the authorization server must be available here in order to
            // compute values of predefined transformed claims here.

            // A transformed claim was found.
            trace(context, requestValue, originalValue, DE01);

            // Intentionally copy the value of 'request' (not 'original').
            // This implementation delegates processing of transformed claims
            // to Authlete.
            copy.put(requestKey, requestValue);

            return true;
        }

        // If the original dataset does not have the property or its value is null.
        if (originalValue == null)
        {
            // The property is unavailable, and therefore omitted.
            trace(context, requestValue, originalValue, DE02);

            return true;
        }

        // If the key is "assurance_details".
        if (requestKey.equals(KEY_ASSURANCE_DETAILS))
        {
            // "assurance_details" has a special rule.
            //
            // OpenID Connect for Identity Assurance 1.0
            // 6.2. Requesting Verification Data
            //
            //   assurance_details is an array representing how the evidence and
            //   check_details meets the requirements of the trust_framework. RP
            //   SHOULD only request this where they need to know this information.
            //   Where assurance_details have been requested by an RP the OP MUST
            //   return the assurance_details element along with all sub-elements
            //   that it has. If an RP wants to filter what types of evidence and
            //   check_methods they MUST use those methods to do so, e.g.
            //   requesting an assurance_type should have no filtering effect.

            // All available sub-elements under 'assurance_details' are
            // unconditionally returned based on the special rule for the property.
            trace(context, requestValue, originalValue, DE23);

            // Copy all available sub-elements under 'assurance_details' without
            // applying any filtering rules.
            copy.put(requestKey, originalValue);

            return true;
        }

        // If the request does not have constraints for the claim.
        if (requestValue == null)
        {
            if (originalValue instanceof Map || originalValue instanceof List)
            {
                // The property is available as array or object, but omitted
                // for the data minimization policy.
                trace(context, requestValue, originalValue, DE03);

                return true;
            }

            // The request does not have constraints for the property, and
            // therefore the property is put in the copy unconditionally.
            trace(context, requestValue, originalValue, DE04);
            copy.put(requestKey, originalValue);

            return true;
        }

        // If the request value is a JSON object.
        if (requestValue instanceof Map)
        {
            return processEntryValueAsMap(
                    context, requestKey, (Map<String, Object>)requestValue,
                    originalValue, copy);
        }

        // If the request value is a JSON array.
        if (requestValue instanceof List)
        {
            return processEntryValueAsList(
                    context, requestKey, (List<?>)requestValue,
                    originalValue, copy);
        }

        // The request value is a non-null single value.

        // The request format is invalid, so matching fails.
        warn(context, requestValue, originalValue, DE05);

        return false;
    }


    private boolean isTransformedClaim(String claimName)
    {
        // If this DatasetExtractor is configured not to recognize
        // transformed claims.
        if (isTransformedClaimAware() == false)
        {
            // The claim is treated as a normal claim even if it is
            // a transformed claim.
            return false;
        }

        // "Transformed Claims" (TC) is a specification which is defined in:
        //
        //   OpenID Connect Advanced Syntax for Claims (ASC) 1.0
        //   https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md
        //
        // A verified_claims request may contain virtual claims called
        // "transformed claims" whose names start with ":" or "::".
        // Values of transformed claims are computed based on existing claims.
        //
        // NOTE: Because the computation process of transformed claims is so
        //       complex, Authlete computes values of transformed claims on
        //       behalf of authorization server implementations.
        //
        return claimName.startsWith(":");
    }


    @SuppressWarnings("unchecked")
    private boolean processEntryValueAsMap(
            DatasetContext context,
            String requestKey, Map<String, Object> requestValue,
            Object originalValue, Map<String, Object> copy)
    {
        // The value of the property in the request may contain constraints
        // for the target claim. (e.g. "values")
        ClaimRequirement requirement = ClaimRequirement.parse(requestValue);

        // If the request contains constraints for the target claim.
        if (requirement != null)
        {
            // If the property in the original dataset does not satisfy
            // the constraints.
            if (checkRequirement(requirement, originalValue) == false)
            {
                if (context.isConstraintAsFilter())
                {
                    // The property does not satisfy the constraint,
                    // so matching fails.
                    debug(context, requirement, originalValue, DE06);

                    return false;
                }
                else
                {
                    // The property does not satisfy the constraint, and
                    // therefore the property is omitted.
                    trace(context, requirement, originalValue, DE07);

                    return true;
                }
            }

            // If the request value (JSON object) does not contain other
            // properties than the reserved ones such as "values".
            if (requirement.hasUnreservedKeys() == false)
            {
                // The property satisfies the constraint, and therefore
                // the property is put in the copy.
                trace(context, requirement, originalValue, DE08);

                // Copy the value from the original dataset. It is certain that
                // the value is a single value (neither array nor object)
                // because it has passed the value/values/max_age constraint.
                copy.put(requestKey, originalValue);

                // No need to go down because the request value does not have
                // sub properties.
                return true;
            }
        }

        // If the original value is also a JSON object.
        if (originalValue instanceof Map)
        {
            return processEntryValueAsMapWithOriginalAsMap(
                    context, requestKey, requestValue,
                    (Map<String, Object>)originalValue, copy);
        }

        // If the original value is a JSON array.
        if (originalValue instanceof List)
        {
            return processEntryValueAsMapWithOriginalAsList(
                    context, requestKey, requestValue,
                    (List<?>)originalValue, copy);
        }

        // The original value is a single value.

        // Special case. When the request value is "{}".
        // "{}" is interpreted in the same way as "null".
        if (requestValue.size() == 0)
        {
            if (originalValue != null)
            {
                // The request has no constraint for the property, and
                // therefore the property is put in the copy unconditionally.
                trace(context, requestValue, originalValue, DE09);
                copy.put(requestKey, originalValue);
            }
            else
            {
                // The request has no constraint for the property, but the
                // property is omitted because its value is unavailable.
                trace(context, requestValue, originalValue, DE10);
            }

            return true;
        }

        // The request has sub properties but the actual data in the original
        // dataset is a single value, so matching fails.
        warn(context, requestValue, originalValue, DE11);

        return false;
    }


    private boolean checkRequirement(ClaimRequirement requirement, Object originalValue)
    {
        ClaimRequirementChecker checker =
                new ClaimRequirementChecker(requirement, getCurrentTime());

        // Check whether the original value satisfies the constraint (value,
        // values and/or max_age). True when the value satisfies the constraint.
        return checker.check(originalValue);
    }


    private boolean processEntryValueAsMapWithOriginalAsMap(
            DatasetContext context,
            String requestKey, Map<String, Object> requestValue,
            Map<String, Object> originalValue, Map<String, Object> copy)
    {
        // Prepare a target to which sub properties are copied.
        Map<String, Object> holder = new HashMap<>();

        // Process the sub properties.
        boolean continued = processMap(context, requestValue, originalValue, holder);

        // If the sub properties meet the request.
        if (continued)
        {
            copy.put(requestKey, holder);
        }

        return continued;
    }


    @SuppressWarnings("unchecked")
    private boolean processEntryValueAsMapWithOriginalAsList(
            DatasetContext context,
            String requestKey, Map<String, Object> requestValue,
            List<?> originalValue, Map<String, Object> copy)
    {
        int originalListSize = originalValue.size();

        for (int index = 0; index < originalListSize; index++)
        {
            // Update the position of the original dataset.
            context.getOriginalPointer().append(index);

            Object originalElement = originalValue.get(index);

            if (!(originalElement instanceof Map))
            {
                // The element in the array in the original dataset is not
                // an object. The element is ignored.
                warn(context, requestValue, originalElement, DE12);

                // Restore the previous position of the original dataset.
                context.getOriginalPointer().remove();

                continue;
            }

            // Prepare a target to which sub properties are copied.
            Map<String, Object> holder = new HashMap<>();

            // Process the sub properties.
            boolean continued = processMap(
                    context, requestValue,
                    (Map<String, Object>)originalElement, holder);

            if (continued)
            {
                // The element in the array in the original dataset meets
                // conditions of the request, so the element is put in the copy.
                trace(context, requestValue, originalElement, DE13);
                copy.put(requestKey, holder);
            }

            // Restore the previous position of the original dataset.
            context.getOriginalPointer().remove();

            if (continued)
            {
                // Found an element that satisfies the request.
                return true;
            }
        }

        // None of the elements in the array in the original dataset meet
        // conditions of the request, so matching fails.
        debug(context, requestValue, originalValue, DE14);

        return false;
    }


    @SuppressWarnings("unchecked")
    private boolean processEntryValueAsList(
            DatasetContext context,
            String requestKey, List<?> requestValue,
            Object originalValue, Map<String, Object> copy)
    {
        // OpenID Connect for Identity Assurance 1.0
        // Requesting Verification Data
        //
        //   A single entry in the "evidence" array represents a filter over
        //   elements of a certain evidence type. The RP therefore MUST specify
        //   this type by including the "type" field including a suitable
        //   "value" sub-element value. The "values" sub-element MUST NOT be
        //   used for the "evidence/type" field.
        //
        //   If multiple entries are present in "evidence", these filters are
        //   linked by a logical OR.
        //
        // This implementation handles not only "evidence" but also other
        // properties of array type in the same way as described above.
        // See also:
        //
        //   Issue 1304: [IDA] Does the same rule apply to other properties of array type?
        //   https://bitbucket.org/openid/ekyc-ida/issues/1304

        // If the value in the original data set is a JSON object.
        if (originalValue instanceof Map)
        {
            return processEntryValueAsListWithOriginalAsMap(
                    context, requestKey, requestValue,
                    (Map<String, Object>)originalValue, copy);
        }

        // If the value in the original dataset is a JSON array.
        if (originalValue instanceof List)
        {
            return processEntryValueAsListWithOriginalAsList(
                    context, requestKey, requestValue,
                    (List<?>)originalValue, copy);
        }

        // The request is an array, but the property in the original
        // dataset is neither an object nor an array. Therefore,
        // matching fails.
        warn(context, requestValue, originalValue, DE15);

        return false;
    }


    @SuppressWarnings("unchecked")
    private boolean processEntryValueAsListWithOriginalAsMap(
            DatasetContext context,
            String requestKey, List<?> requestValue,
            Map<String, Object> originalValue, Map<String, Object> copy)
    {
        int requestListSize = requestValue.size();

        // The "logical OR" logic. When the original dataset satisfies any
        // of the elements in the array in the request, data are extracted
        // from the original dataset.
        for (int index = 0; index < requestListSize; index++)
        {
            Object requestElement = requestValue.get(index);

            if (!(requestElement instanceof Map))
            {
                // The element in the array in the request is not an object.
                // It is a specification violation. The element is ignored.
                warn(context, requestElement, originalValue, DE16);

                continue;
            }

            // Update the position of the request.
            context.getRequestPointer().append(index);

            // Build a sub dataset that meets conditions of 'requestElement'.
            Map<String, Object> subCopy =
                    extract(context, (Map<String, Object>)requestElement, originalValue);

            // If a sub dataset was built successfully.
            if (subCopy != null)
            {
                // The property in the original dataset satisfies conditions of
                // the element in the array in the request.
                trace(context, requestElement, originalValue, DE17);
                copy.put(requestKey, Arrays.asList(subCopy));
            }

            // Restore the previous position of the request.
            context.getRequestPointer().remove();

            if (subCopy != null)
            {
                // Succeeded in building a sub dataset from the original
                // dataset that satisfies conditions of the element in the
                // array in the request.
                return true;
            }
        }

        // The original dataset do not satisfy conditions of any element
        // in the array in the request.
        return false;
    }


    @SuppressWarnings("unchecked")
    private boolean processEntryValueAsListWithOriginalAsList(
            DatasetContext context,
            String requestKey, List<?> requestValue,
            List<?> originalValue, Map<String, Object> copy)
    {
        int requestListSize  = requestValue.size();
        int originalListSize = originalValue.size();

        // Prepare a target to which elements are copied.
        List<Map<String, Object>> holder = new ArrayList<>(requestValue.size());

        for (int oIndex = 0; oIndex < originalListSize; oIndex++)
        {
            Object originalElement = originalValue.get(oIndex);

            if (!(originalElement instanceof Map))
            {
                // The element in the array in the original dataset is not
                // an object. The element is ignored.
                warn(context, requestValue, originalElement, DE18);

                continue;
            }

            // Update the position of the original dataset.
            context.getOriginalPointer().append(oIndex);

            // The "logical OR" logic. If the original element matches any of
            // elements in the request, the original element is put in the copy.

            for (int rIndex = 0; rIndex < requestListSize; rIndex++)
            {
                Object requestElement = requestValue.get(rIndex);

                if (!(requestElement instanceof Map))
                {
                    // The element in the array in the request is not an
                    // object. It is a specification violation. The element
                    // is ignored.
                    warn(context, requestElement, originalElement, DE19);

                    continue;
                }

                // Update the position of the request.
                context.getRequestPointer().append(rIndex);

                // Build a sub dataset that meets conditions of 'requestElement'.
                Map<String, Object> subCopy = extract(context,
                        (Map<String, Object>)requestElement, (Map<String, Object>)originalElement);

                // If a sub dataset was built successfully.
                if (subCopy != null)
                {
                    // The element in the array in the original dataset satisfies
                    // conditions of the element in the array in the request.
                    trace(context, requestElement, originalElement, DE20);
                    holder.add(subCopy);
                }

                // Restore the previous position of the request.
                context.getRequestPointer().remove();

                if (subCopy != null)
                {
                    // Succeeded in building a sub dataset from the original
                    // dataset that satisfies conditions of the element in the
                    // array in the request.
                    break;
                }
            }

            // Restore the previous position of the original dataset.
            context.getOriginalPointer().remove();
        }

        if (holder.size() == 0)
        {
            // None of the elements in the array in the original dataset
            // satisfy any of the elements in the array in the request.
            // Therefore, matching fails.
            debug(context, requestValue, originalValue, DE21);

            return false;
        }

        // Some elements in the array in the original dataset satisfy any
        // of the elements in the array in the request.
        trace(context, requestValue, originalValue, DE22);
        copy.put(requestKey, holder);

        return true;
    }


    private void warn(
            DatasetContext context, Object request, Object original,
            DatasetExtractorMessageCode code, Object... args)
    {
        if (mLogger != null)
        {
            mLogger.warn(prefix(context, request, original) + code.format(args));
        }
    }


    private void debug(
            DatasetContext context, Object request, Object original,
            DatasetExtractorMessageCode code, Object... args)
    {
        if (mLogger != null)
        {
            mLogger.debug(prefix(context, request, original) + code.format(args));
        }
    }


    private void trace(
            DatasetContext context, Object request, Object original,
            DatasetExtractorMessageCode code, Object... args)
    {
        if (mLogger != null)
        {
            mLogger.trace(prefix(context, request, original) + code.format(args));
        }
    }


    private static String prefix(DatasetContext context, Object request, Object original)
    {
        return String.format("<request:%s=%s, original:%s=%s> ",
                context.getRequestPointer(), str(request),
                context.getOriginalPointer(), str(original));
    }


    private static String str(Object object)
    {
        if (object == null)
        {
            return "null";
        }

        if (object instanceof Map)
        {
            return "{object}";
        }

        if (object instanceof List)
        {
            return "[array]";
        }

        if (object instanceof String)
        {
            return String.format("\"%s\"", (String)object);
        }

        if (object instanceof Number)
        {
            return object.toString();
        }

        if (object instanceof ClaimRequirement)
        {
            return str((ClaimRequirement)object);
        }

        // Unexpected type.

        return object.getClass().toString();
    }


    private static String str(ClaimRequirement requirement)
    {
        StringBuilder sb = new StringBuilder("{");
        boolean haveConstraints = false;

        // If the requirement contains the "value" constraint.
        String value = requirement.getValue();
        if (value != null)
        {
            // value=...
            sb.append("value=\"").append(value).append("\"");
            haveConstraints = true;
        }

        // If the requirement contains the "values" constraint.
        List<String> values = requirement.getValues();
        if (values != null)
        {
            if (haveConstraints)
            {
                sb.append(",");
            }

            // values=[...]
            sb.append("values=[");
            values.stream().forEach(element -> sb.append("\"").append(element).append("\""));
            sb.append("]");

            haveConstraints = true;
        }

        // If the requirement contains the "max_age" constraint.
        Long maxAge = requirement.getMaxAge();
        if (maxAge != null)
        {
            if (haveConstraints)
            {
                sb.append(",");
            }

            // max_age=...
            sb.append("max_age=").append(maxAge);
        }

        sb.append("}");

        return sb.toString();
    }
}
