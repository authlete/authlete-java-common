package com.authlete.common.dto;

import com.authlete.common.types.TslTokenStatus;

import java.io.Serializable;

public class TslTokenStatusUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private TslTokenStatus tokenStatus;

    private int tokenIndex;

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenStatus(TslTokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    public TslTokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenIndex(int tokenIndex) {
        this.tokenIndex = tokenIndex;
    }

    public int getIndex() {
        return tokenIndex;
    }

}
