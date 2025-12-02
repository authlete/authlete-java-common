package com.authlete.common.dto;

/**
 * Response from Authlete's {@code /tsl/entries/list} API.
 *
 * This class contains list of TSL token entries.
 *
 * @since 4.31
 * @since Authlete 3.0.22
 *
 */
public class TslEntriesResponse
{

    private static final long serialVersionUID = 1L;

    /**
     * The start index (inclusive) for the result set of the query.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int start;

    /**
     * The end index (exclusive) for the result set of the query.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int end;

    /**
     * Total count of the entries
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int totalCount;

    /**
     * TSL token entries
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */

    private TslEntry[] tslEntries;

    public TslEntriesResponse setStart(int start)
    {
        this.start = start;

        return this;
    }

    public int getStart()
    {
        return start;
    }

    public TslEntriesResponse setEnd(int end)
    {
        this.end = end;

        return this;
    }

    public int getEnd()
    {
        return end;
    }

    public TslEntriesResponse setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;

        return this;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public TslEntriesResponse setTslEntries(TslEntry[] tslEntries)
    {
        this.tslEntries = tslEntries;

        return this;
    }

    public TslEntry[] getTslEntries()
    {
        return tslEntries;
    }
}
