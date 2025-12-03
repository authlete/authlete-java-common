package com.authlete.common.dto;

/**
 * Response from Authlete's {@code /tsl/entries/list} API.
 *
 * <p>
 * This class represents the result of a query that retrieves
 * a list of TSL (Token Status List) token entries. It contains
 * information about pagination (start and end indexes), total
 * entry count, and the actual list of {@link TslEntry} objects.
 * </p>
 *
 * @since 4.31
 * @since Authlete 3.0.22
 */
public class TslEntriesResponse
{
    private static final long serialVersionUID = 1L;

    /**
     * The start index (inclusive) for the result set of the query.
     *
     * @return The start index.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int start;

    /**
     * The end index (exclusive) for the result set of the query.
     *
     * @return The end index.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int end;

    /**
     * The total number of entries available for the query, regardless of pagination.
     *
     * @return The total count of entries.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private int totalCount;

    /**
     * The array of TSL token entries returned by the query.
     *
     * @return An array of {@link TslEntry} objects.
     *
     * @since 4.31
     * @since Authlete 3.0.22
     */
    private TslEntry[] tslEntries;


    /**
     * Set the start index (inclusive) for the result set.
     *
     * @param start
     *         The start index.
     *
     * @return {@code this} object for method chaining.
     */
    public TslEntriesResponse setStart(int start)
    {
        this.start = start;
        return this;
    }

    /**
     * Get the start index (inclusive) for the result set.
     *
     * @return The start index.
     */
    public int getStart()
    {
        return start;
    }

    /**
     * Set the end index (exclusive) for the result set.
     *
     * @param end
     *         The end index.
     *
     * @return {@code this} object for method chaining.
     */
    public TslEntriesResponse setEnd(int end)
    {
        this.end = end;
        return this;
    }

    /**
     * Get the end index (exclusive) for the result set.
     *
     * @return The end index.
     */
    public int getEnd()
    {
        return end;
    }

    /**
     * Set the total count of all entries matching the query.
     *
     * @param totalCount
     *         The total number of matching entries.
     *
     * @return {@code this} object for method chaining.
     */
    public TslEntriesResponse setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * Get the total number of entries matching the query.
     *
     * @return The total count.
     */
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * Set the list of TSL token entries.
     *
     * @param tslEntries
     *         An array of {@link TslEntry} objects.
     *
     * @return {@code this} object for method chaining.
     */
    public TslEntriesResponse setTslEntries(TslEntry[] tslEntries)
    {
        this.tslEntries = tslEntries;
        return this;
    }

    /**
     * Get the list of TSL token entries returned by the query.
     *
     * @return An array of {@link TslEntry} objects, or {@code null} if none.
     */
    public TslEntry[] getTslEntries()
    {
        return tslEntries;
    }
}
