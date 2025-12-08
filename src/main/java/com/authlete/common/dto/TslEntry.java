package com.authlete.common.dto;

import com.authlete.common.types.TslTokenStatus;

/**
 * Represents a single token entry used in constructing a Token Status List (TSL).
 *
 * <p>
 * Each entry corresponds to an issued VC/token and includes its index, unique
 * token ID, current status, and usage flag. These objects are typically returned
 * as part of a list in {@code /tsl/entries/list} responses.
 * </p>
 *
 * @since 4.33
 * @since Authlete 3.0.22
 */
public class TslEntry
{
    private static final long serialVersionUID = 1L;

    /**
     * The token index associated with the issued VC/token.
     * This index helps uniquely identify the token within a service.
     */
    private int tokenIndex;

    /**
     * The unique token ID assigned at issuance time.
     * This value is globally unique per token/VC.
     */
    private String tokenId;

    /**
     * The current status of the issued VC/token.
     */
    private TslTokenStatus tokenStatus;

    /**
     * Indicates whether this token entry has been marked as used.
     */
    private boolean used;

    /**
     * Set the token index associated with this entry.
     *
     * @param tokenIndex
     *          The token index.
     *
     * @return
     *          {@code this} object for method chaining.
     */
    public TslEntry setTokenIndex(int tokenIndex)
    {
        this.tokenIndex = tokenIndex;

        return this;
    }

    /**
     * Get the token index associated with this entry.
     *
     * @return
     *          The token index.
     */
    public int getTokenIndex()
    {
        return tokenIndex;
    }

    /**
     * Set the unique token ID for this entry.
     *
     * @param tokenId
     *          The unique token identifier.
     *
     * @return
     *          {@code this} object for method chaining.
     */
    public TslEntry setTokenId(String tokenId)
    {
        this.tokenId = tokenId;

        return this;
    }

    /**
     * Get the unique token ID associated with this entry.
     *
     * @return
     *          The token ID.
     */
    public String getTokenId()
    {
        return tokenId;
    }

    /**
     * Set the current token status.
     *
     * @param tokenStatus
     *          The status of the token.
     *
     * @return
     *          {@code this} object for method chaining.
     */
    public TslEntry setTokenStatus(TslTokenStatus tokenStatus)
    {
        this.tokenStatus = tokenStatus;

        return this;
    }

    /**
     * Get the current status of the issued token.
     *
     * @return
     *          The token status.
     */
    public TslTokenStatus getTokenStatus()
    {
        return tokenStatus;
    }

    /**
     * Set the usage flag for this token entry.
     *
     * @param used
     *          {@code true} if the entry is already used; {@code false} otherwise.
     *
     * @return
     *          {@code this} object for method chaining.
     */
    public TslEntry setUsed(boolean used)
    {
        this.used = used;

        return this;
    }

    /**
     * Check whether this token entry has been marked as used.
     *
     * @return
     *          {@code true} if the entry is used; {@code false} otherwise.
     */
    public boolean getUsed()
    {
        return used;
    }
}
