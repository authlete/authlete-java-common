package com.authlete.common.dto;

import com.authlete.common.types.TslTokenStatus;

/**
 * This class contains token entries on which to build the TSL
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 */
public class TslEntry
{
    private static final long serialVersionUID = 1L;

    /**
     * Token index that represents the token in the issued VC/token.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int tokenIndex;

    /**
     * Unique ID assigned to each issued VC/token.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private String tokenId;

    /**
     * Current status of the issued VC/token.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */

    private TslTokenStatus tokenStatus;

    private boolean isUsed;

    public TslEntry setTokenIndex(int tokenIndex)
    {
        this.tokenIndex = tokenIndex;

        return this;
    }

    public int getTokenIndex()
    {
        return tokenIndex;
    }

    public TslEntry setTokenId(String tokenId)
    {
        this.tokenId = tokenId;

        return this;
    }

    public String getTokenId()
    {
        return tokenId;
    }

    public TslEntry setTokenStatus(TslTokenStatus tokenStatus)
    {
        this.tokenStatus = tokenStatus;

        return this;
    }

    public TslTokenStatus getTokenStatus()
    {
        return tokenStatus;
    }

    public TslEntry setIsUsed(boolean isUsed)
    {
        this.isUsed = isUsed;

        return this;
    }

    public boolean isUsed()
    {
        return isUsed;
    }
}
