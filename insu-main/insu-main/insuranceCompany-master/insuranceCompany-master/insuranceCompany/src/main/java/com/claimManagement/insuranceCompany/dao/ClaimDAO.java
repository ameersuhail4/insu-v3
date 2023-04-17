package com.claimManagement.insuranceCompany.dao;

import com.claimManagement.insuranceCompany.entities.ClaimDetails;

import java.util.List;

public interface ClaimDAO {
    List<ClaimDetails> getClaims();
    String AddNewClaim(ClaimDetails claimDetails);
    String UpdateByClaimID(String claimId,ClaimDetails claimDetails);
    ClaimDetails getClaimById(String id);
}
