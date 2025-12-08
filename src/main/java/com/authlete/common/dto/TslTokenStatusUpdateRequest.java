package com.authlete.common.dto;

import com.authlete.common.types.TslTokenStatus;

import java.io.Serializable;

/**
 * Request to Authlete's {@code /tsl/token/status} API.
 *
 * <p>
 * This class represents a request to update the status of an issued
 * Verifiable Credential (VC) or token. The updated status will later
 * be included in the issued Token Status List (TSL). The request contains
 * the following information:
 * </p>
 *
 * <ul>
 *   <li>{@code tokenId} – The unique identifier of the issued token/VC.</li>
 *   <li>{@code tokenStatus} – The new status to assign to the token.</li>
 *   <li>{@code tokenIndex} – The index of the token in the issued TSL.</li>
 * </ul>
 *
 * <p>
 * For more details about Token Status Lists (TSL), see:
 * </p>
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/">
 *      Token Status List (TSL) Specification</a>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslTokenStatusUpdateRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The token ID used to identify the issued VC/token whose status is to be changed.
     */
    private String tokenId;

    /**
     * The token status to be set.
     */
    private TslTokenStatus tokenStatus;

    /**
     * The index of the token in the issued TSL.
     */
    private int tokenIndex;

    /**
     * Set the token ID of the issued VC/token whose status is to be updated.
     *
     * @param tokenId
     *         The unique token identifier.
     *
     * @return
     *         {@code this} object.
     */
    public TslTokenStatusUpdateRequest setTokenId(String tokenId)
    {
        this.tokenId = tokenId;

        return this;
    }

    /**
     * Get the token ID of the issued VC/token whose status is being updated.
     *
     * @return
     *          The token ID.
     */
    public String getTokenId()
    {
        return tokenId;
    }

    /**
     * Set the token status to assign to the issued token/VC.
     *
     * @param tokenStatus
     *         The new token status.
     *
     * @return
     *         {@code this} object.
     */
    public TslTokenStatusUpdateRequest setTokenStatus(TslTokenStatus tokenStatus)
    {
        this.tokenStatus = tokenStatus;

        return this;
    }

    /**
     * Get the token status currently set for this update request.
     *
     * @return
     *          The token status.
     */
    public TslTokenStatus getTokenStatus()
    {
        return tokenStatus;
    }

    /**
     * Set the token index in the issued TSL.
     *
     * @param tokenIndex
     *         The index of the token.
     *
     * @return
     *         {@code this} object.
     */
    public TslTokenStatusUpdateRequest setTokenIndex(int tokenIndex)
    {
        this.tokenIndex = tokenIndex;

        return this;
    }

    /**
     * Get the token index in the issued TSL.
     *
     * @return
     *          The token index.
     */
    public int getIndex()
    {
        return tokenIndex;
    }
}
