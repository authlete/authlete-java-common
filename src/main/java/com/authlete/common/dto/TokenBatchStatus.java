/*
 * Copyright (C) 2024 Authlete, Inc.
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
 * The status of token batch.
 *
 * @author Hideki Ikeda
 *
 * @since 3.96
 */
public class TokenBatchStatus implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * Batch Kind.
     */
    public enum BatchKind
    {
        /**
         * The token create batch.
         */
        CREATE((short)1),
        ;


        private static final BatchKind[] sValues = values();
        private final short mValue;


        private BatchKind(short value)
        {
            mValue = value;
        }


        /**
         * Get the integer representation of this enum instance.
         */
        public short getValue()
        {
            return mValue;
        }


        /**
         * Find an instance of this enum by a value.
         *
         * @param value
         *         The integer representation of the instance to find.
         *
         * @return
         *         An instance of this enum, or {@code null} if not found.
         */
        public static BatchKind getByValue(short value)
        {
            if (value < 1 || sValues.length < value)
            {
                // Not found.
                return null;
            }

            return sValues[value - 1];
        }
    }


    /**
     * Batch result.
     */
    public enum Result
    {
        /**
         * The token batch process has been successfully completed. All tokens have
         * been processed as expected, with no errors or interruptions.
         */
        SUCCEEDED((short)1),


        /**
         * The token batch process has not completed successfully due to an issue
         * encountered during execution. This status is used when the process starts
         * but is interrupted before completion, often because of errors such as
         * exceptions thrown during runtime. The batch process may have partially
         * completed before the failure occurred.
         */
        FAILED((short)2),
        ;


        private static final Result[] sValues = values();
        private final short mValue;


        private Result(short value)
        {
            mValue = value;
        }


        /**
         * Get the integer representation of this enum instance.
         */
        public short getValue()
        {
            return mValue;
        }


        /**
         * Find an instance of this enum by a value.
         *
         * @param value
         *         The integer representation of the instance to find.
         *
         * @return
         *         An instance of this enum, or {@code null} if not found.
         */
        public static Result getByValue(short value)
        {
            if (value < 1 || sValues.length < value)
            {
                // Not found.
                return null;
            }

            return sValues[value - 1];
        }
    }


    private BatchKind batchKind;
    private String requestId;
    private Result result;
    private long tokenCount;
    private String errorCode;
    private String errorDescription;
    private long createdAt;
    private long modifiedAt;


    /**
     * Get the kind of the batch.
     *
     * @return
     *         The kind of the batch.
     */
    public BatchKind getBatchKind()
    {
        return batchKind;
    }


    /**
     * Set the kind of the batch.
     *
     * @param batchKind
     *         The kind of the batch.
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setBatchKind(BatchKind batchKind)
    {
        this.batchKind = batchKind;

        return this;
    }


    /**
     * Get the request ID associated with the status.
     *
     * @return
     *         The request ID associated with the status.
     */
    public String getRequestId()
    {
        return requestId;
    }


    /**
     * Set the request ID associated with the status.
     *
     * @param requestId
     *         The request ID associated with the status.
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setRequestId(String requestId)
    {
        this.requestId = requestId;

        return this;
    }


    /**
     * Get the result of the token batch.
     *
     * @return
     *         The result of the token batch.
     */
    public Result getResult()
    {
        return result;
    }


    /**
     * Set the result of the token batch.
     *
     *
     * @param result
     *         The result of the token batch.
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setResult(Result result)
    {
        this.result = result;

        return this;
    }


    /**
     * Get the number of access tokens processed by the batch.
     *
     * @return
     *         The number of access tokens processed by the batch.
     */
    public long getTokenCount()
    {
        return tokenCount;
    }


    /**
     * Set the number of access tokens processed by the batch.
     *
     * @param tokenCount
     *         The number of access tokens processed by the batch.
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setTokenCount(long tokenCount)
    {
        this.tokenCount = tokenCount;

        return this;
    }


    /**
     * Get the error code.
     *
     * @return
     *         The error code.
     */
    public String getErrorCode()
    {
        return errorCode;
    }


    /**
     * Set the error code.
     *
     * @param errorCode
     *         The error code.
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;

        return this;
    }


    /**
     * Get the error description.
     *
     * @return
     *         The error description.
     */
    public String getErrorDescription()
    {
        return errorDescription;
    }


    /**
     * Set the error description.
     *
     * @param errorDescription
     *         The error description.
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setErrorDescription(String errorDescription)
    {
        this.errorDescription = errorDescription;

        return this;
    }


    /**
     * Get the time when this status was created.
     *
     * @return
     *         The time at which this status was created. The value is represented
     *         as milliseconds since the UNIX epoch (1970-01-01).
     */
    public long getCreatedAt()
    {
        return createdAt;
    }


    /**
     * Set the time when this status was created.
     *
     * @param createdAt
     *         The time at which this status was created. The value is represented
     *         as milliseconds since the UNIX epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setCreatedAt(long createdAt)
    {
        this.createdAt = createdAt;

        return this;
    }


    /**
     * Get the time when this status was last modified.
     *
     * @return
     *         The time at which this status was last modified. The value is represented
     *         as milliseconds since the UNIX epoch (1970-01-01).
     */
    public long getModifiedAt()
    {
        return modifiedAt;
    }


    /**
     * Set the time when this status was last modified.
     *
     * @param modifiedAt
     *         The time at which this status was last modified. The value is represented
     *         as milliseconds since the UNIX epoch (1970-01-01).
     *
     * @return
     *         {@code this} object.
     */
    public TokenBatchStatus setModifiedAt(long modifiedAt)
    {
        this.modifiedAt = modifiedAt;

        return this;
    }
}
