package com.authlete.common.dto;


import com.authlete.common.types.AssertionTarget;


public class AssertionProcessor
{

    private int number;
    private String jwks;
    private AssertionTarget target;
    private ClaimMatcher[] claimMatchers;
    private int serviceNumber;

    public String getJwks()
    {
        return jwks;
    }


    public AssertionProcessor setJwks(String jwks)
    {
        this.jwks = jwks;
        return this;
    }
    public AssertionTarget getTarget()
    {
        return target;
    }


    public AssertionProcessor setTarget(AssertionTarget target)
    {
        this.target = target;
        return this;
    }
    public ClaimMatcher[] getClaimMatchers()
    {
        return claimMatchers;
    }


    public AssertionProcessor setClaimMatchers(ClaimMatcher[] claimMatchers)
    {
        this.claimMatchers = claimMatchers;
        return this;
    }


    public int getServiceNumber()
    {
        return this.serviceNumber;
    }


    public AssertionProcessor setServiceNumber(int serviceNumber)
    {
        this.serviceNumber = serviceNumber;
        return this;
    }


    public int getNumber()
    {
        return number;
    }


    public AssertionProcessor setNumber(int number)
    {
        this.number = number;
        return this;
    }

}
