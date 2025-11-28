package com.authlete.common.dto;

public class TslEntriesResponse {

    private static final long serialVersionUID = 1L;

    private int start;

    private int end;

    private int totalCount;

    private TslEntry[] tslEntries;

    public TslEntriesResponse setStart(int start) {
        this.start = start;

        return this;
    }

    public int getStart() {
        return start;
    }

    public TslEntriesResponse setEnd(int end) {
        this.end = end;

        return this;
    }

    public int getEnd() {
        return end;
    }

    public TslEntriesResponse setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public TslEntriesResponse setTslEntries(TslEntry[] tslEntries) {
        this.tslEntries = tslEntries;

        return this;
    }

    public TslEntry[] getTslEntries() {
        return tslEntries;
    }
}
