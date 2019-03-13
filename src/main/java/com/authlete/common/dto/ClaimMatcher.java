package com.authlete.common.dto;

import com.authlete.common.types.ClaimMatchOperation;

public class ClaimMatcher
{

    private ClaimMatchOperation operation;
    private String claimName;
    private String comparisonValue;


    public ClaimMatchOperation getOperation()
    {
        return operation;
    }


    public ClaimMatcher setOperation(ClaimMatchOperation operation)
    {
        this.operation = operation;
        return this;
    }
    public String getClaimName()
    {
        return claimName;
    }


    public ClaimMatcher setClaimName(String claimName)
    {
        this.claimName = claimName;
        return this;
    }
    public String getComparisonValue()
    {
        return comparisonValue;
    }


    public ClaimMatcher setComparisonValue(String comparisonValue)
    {
        this.comparisonValue = comparisonValue;
        return this;
    }

}
