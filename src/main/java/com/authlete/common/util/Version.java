/*
 * Copyright (C) 2023 Authlete, Inc.
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
package com.authlete.common.util;


import java.io.Serializable;
import java.util.Objects;


/**
 * A class that represents a version number consisting of a major number,
 * a minor number and a patch number.
 *
 * @since 3.54
 *
 * @see <a href="https://semver.org/">Semantic Versioning</a>
 */
public class Version implements Comparable<Version>, Serializable
{
    private static final long serialVersionUID = 1L;


    private final int major;
    private final int minor;
    private final int patch;
    private final String string;


    /**
     * A constructor with a major number and a minor number.
     *
     * <p>
     * This constructor is an alias of {@link #Version(int, int, int)
     * Version}{@code (major, minor, 0)}.
     * </p>
     *
     * @param major
     *         A major number.
     *
     * @param minor
     *         A minor number.
     */
    public Version(int major, int minor)
    {
        this(major, minor, 0);
    }


    /**
     * A constructor with a major number, a minor number and a patch number.
     *
     * @param major
     *         A major number.
     *
     * @param minor
     *         A minor number.
     *
     * @param patch
     *         A patch number.
     */
    public Version(int major, int minor, int patch)
    {
        this.major  = major;
        this.minor  = minor;
        this.patch  = patch;
        this.string = String.format("%d.%d.%d", major, minor, patch);
    }


    /**
     * Get the major number.
     *
     * @return
     *         The major number.
     */
    public int getMajor()
    {
        return major;
    }


    /**
     * Get the minor number.
     *
     * @return
     *         The minor number.
     */
    public int getMinor()
    {
        return minor;
    }


    /**
     * Get the patch number.
     *
     * @return
     *         The patch number.
     */
    public int getPatch()
    {
        return patch;
    }


    @Override
    public String toString()
    {
        return string;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Version that = (Version)o;

        return major == that.major &&
               minor == that.minor &&
               patch == that.patch;
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(major, minor, patch);
    }


    @Override
    public int compareTo(Version version)
    {
        if (this.major != version.major)
        {
            return Integer.compare(this.major, version.major);
        }

        if (this.minor != version.minor)
        {
            return Integer.compare(this.minor, version.minor);
        }

        return Integer.compare(this.patch, version.patch);
    }


    /**
     * Judge whether this version is greater than the given version.
     *
     * @param other
     *         A version to compare to.
     *
     * @return
     *         {@code true} if this version is greater than the given version.
     */
    public boolean greaterThan(Version other)
    {
        return compareTo(other) > 0;
    }


    /**
     * Judge whether this version is greater than or equal to the given version.
     *
     * @param other
     *         A version to compare to.
     *
     * @return
     *         {@code true} if this version is greater than or equal to
     *         the given version.
     */
    public boolean greaterThanOrEqualTo(Version other)
    {
        return compareTo(other) >= 0;
    }


    /**
     * Judge whether this version is less than the given version.
     *
     * @param other
     *         A version to compare to.
     *
     * @return
     *         {@code true} if this version is less than the given version.
     */
    public boolean lessThan(Version other)
    {
        return compareTo(other) < 0;
    }


    /**
     * Judge whether this version is less than or equal to the given version.
     *
     * @param other
     *         A version to compare to.
     *
     * @return
     *         {@code true} if this version is less than or equal to
     *         the given version.
     */
    public boolean lessThanOrEqualTo(Version other)
    {
        return compareTo(other) <= 0;
    }
}
