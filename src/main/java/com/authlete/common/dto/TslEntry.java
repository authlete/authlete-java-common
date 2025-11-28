package com.authlete.common.dto;

import com.authlete.common.types.TslTokenStatus;

public class TslEntry {

    private static final long serialVersionUID = 1L;

    private int tokenIndex;

    private String tokenId;

    private TslTokenStatus tokenStatus;

    private boolean isUsed;

    public TslEntry setTokenIndex(int tokenIndex) {
        this.tokenIndex = tokenIndex;

        return this;
    }

    public int getTokenIndex() {
        return tokenIndex;
    }

    public TslEntry setTokenId(String tokenId) {
        this.tokenId = tokenId;

        return this;
    }

    public String getTokenId() {
        return tokenId;
    }

    public TslEntry setTokenStatus(TslTokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;

        return this;
    }

    public TslTokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public TslEntry setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;

        return this;
    }

    public boolean isUsed() {
        return isUsed;
    }

}
