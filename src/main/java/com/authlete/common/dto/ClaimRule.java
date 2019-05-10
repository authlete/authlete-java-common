package com.authlete.common.dto;

import com.authlete.common.types.ClaimRuleOperation;

public class ClaimRule
{

    private ClaimRuleOperation operation;
    private String claimName;
    private String comparisonValue;


    public ClaimRuleOperation getOperation()
    {
        return operation;
    }


    public ClaimRule setOperation(ClaimRuleOperation operation)
    {
        this.operation = operation;
        return this;
    }
    public String getClaimName()
    {
        return claimName;
    }


    public ClaimRule setClaimName(String claimName)
    {
        this.claimName = claimName;
        return this;
    }
    public String getComparisonValue()
    {
        return comparisonValue;
    }


    public ClaimRule setComparisonValue(String comparisonValue)
    {
        this.comparisonValue = comparisonValue;
        return this;
    }

}
