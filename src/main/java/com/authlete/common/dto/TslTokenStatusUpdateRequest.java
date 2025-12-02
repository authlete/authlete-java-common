package com.authlete.common.dto;

import com.authlete.common.types.TslTokenStatus;

import java.io.Serializable;

/**
 *
 * Request to Authlete's {@code /tsl/token/status} API.
 *
 * A class that updates the status of the issued VCs/tokens which later added in the issued TSL.
 * The set consists of the following.
 *
 * <ul>
 * <li>{@code tokenId}
 * <li>{@code tokenStatus}
 * <li>{@code tokenIndex}
 * </ul>
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 * @see <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-status-list/13/"
 *      >Token Status List (TSL)</a>
 */
public class TslTokenStatusUpdateRequest implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * Token ID used to identity the issued VC/token whose status can be changed.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private String tokenId;

    /**
     * Token status to change.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */

    private TslTokenStatus tokenStatus;

    /**
     * Token index in the issued TSL.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int tokenIndex;

    public void setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
    }

    public String getTokenId()
    {
        return tokenId;
    }

    public void setTokenStatus(TslTokenStatus tokenStatus)
    {
        this.tokenStatus = tokenStatus;
    }

    public TslTokenStatus getTokenStatus()
    {
        return tokenStatus;
    }

    public void setTokenIndex(int tokenIndex)
    {
        this.tokenIndex = tokenIndex;
    }

    public int getIndex()
    {
        return tokenIndex;
    }
}
