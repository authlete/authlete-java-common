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


import com.authlete.common.util.MutableJsonPointer;


/**
 * Context for the processing of {@link DatasetExtractor}.
 *
 * @since 3.17
 */
class DatasetContext
{
    private boolean mConstraintAsFilter;
    private MutableJsonPointer mRequestPointer  = new MutableJsonPointer();
    private MutableJsonPointer mOriginalPointer = new MutableJsonPointer();


    /**
     * Get the flag indicating whether constraints by {@code "value"},
     * {@code "values"} or {@code "max_age"} should be treated as filters.
     *
     * <p>
     * From "Data not Matching Requirements" of <a href=
     * "https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     * >OpenID Connect for Identity Assurance 1.0</a>:
     * </p>
     *
     * <blockquote>
     * <p>
     * When the available data does not fulfill the requirements of the RP
     * expressed through {@code value}, {@code values}, or {@code max_age},
     * the following logic applies:
     * </p>
     *
     * <ul>
     * <li>
     * If the respective requirement was expressed for a Claim within
     * {@code verified_claims/verification}, the whole
     * {@code verified_claims} element MUST be omitted.
     *
     * <li style="margin-top: 1em;">
     * Otherwise, the respective Claim MUST be omitted from the response.
     * </ul>
     *
     * <p>
     * In both cases, the OP MUST NOT return an error to the RP.
     * </p>
     * </blockquote>
     *
     * <p>
     * This flag is used to switch the behaviors. While {@link DatasetExtractor}
     * is processing data under {@code verified_claims/verification}, this flag
     * should return {@code true}.
     * </p>
     *
     * @return
     *         {@code true} when constraints by {@code "value"}, {@code "values"}
     *         and {@code "max_age"} should be treated as filters.
     */
    public boolean isConstraintAsFilter()
    {
        return mConstraintAsFilter;
    }


    /**
     * Set the flag indicating whether constraints by {@code "value"},
     * {@code "values"} or {@code "max_age"} should be treated as filters.
     *
     * <p>
     * See the description of {@link #isConstraintAsFilter()} for details.
     * </p>
     *
     * @param asFilter
     *         {@code true} to make {@link DatasetExtractor} treat constraints
     *         by {@code "value"}, {@code "values"} and {@code "max_age"} as
     *         filters.
     *
     * @return
     *         {@code this} object.
     */
    public DatasetContext setConstraintAsFilter(boolean asFilter)
    {
        mConstraintAsFilter = asFilter;

        return this;
    }


    /**
     * Get the pointer that points to a position in the request.
     *
     * <p>
     * This pointer is used only for logging. The logic of data extraction
     * implemented in {@link DatasetExtractor} does not depend on this pointer.
     * </p>
     *
     * @return
     *         The pointer that points to a position in the request.
     */
    public MutableJsonPointer getRequestPointer()
    {
        return mRequestPointer;
    }


    /**
     * Get the pointer that points to a position in the original dataset.
     *
     * <p>
     * This pointer is used only for logging. The logic of data extraction
     * implemented in {@link DatasetExtractor} does not depend on this pointer.
     * </p>
     *
     * @return
     *         The pointer that points to a position in the original dataset.
     */
    public MutableJsonPointer getOriginalPointer()
    {
        return mOriginalPointer;
    }


    /**
     * Update both the request pointer and the original pointer with the
     * given reference token. This method is equivalent to the following.
     *
     * <pre>
     * {@link #getRequestPointer()}.{@link MutableJsonPointer#append(String) append}(referenceToken);
     * {@link #getOriginalPointer()}.{@link MutableJsonPointer#append(String) append}(referenceToken);
     * </pre>
     *
     * @param referenceToken
     *         A reference token to be appended to the pointers.
     */
    public void updatePointers(String referenceToken)
    {
        getRequestPointer().append(referenceToken);
        getOriginalPointer().append(referenceToken);
    }


    /**
     * Remove the last reference token from both the request pointer and the
     * original pointer. This method is equivalent to the following.
     *
     * <pre>
     * {@link #getRequestPointer()}.{@link MutableJsonPointer#remove() remove()};
     * {@link #getOriginalPointer()}.{@link MutableJsonPointer#remove() remove()};
     * </pre>
     */
    public void restorePointers()
    {
        getRequestPointer().remove();
        getOriginalPointer().remove();
    }
}
