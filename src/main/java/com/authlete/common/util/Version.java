package com.authlete.common.util;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;


public class Version implements Comparable<Version>, Serializable
{

    private int major;
    private int minor;
    private int patch;

    private static final long serialVersionUID = 1L;


    public Version(int major, int minor, int patch)
    {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }


    public int getMajor()
    {
        return major;
    }


    public int getMinor()
    {
        return minor;
    }


    public int getPatch()
    {
        return patch;
    }


    @Override
    public String toString()
    {
        return major + "." + minor + "." + patch;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Version version = (Version) o;
        return major == version.major && minor == version.minor && patch == version.patch;
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

}
