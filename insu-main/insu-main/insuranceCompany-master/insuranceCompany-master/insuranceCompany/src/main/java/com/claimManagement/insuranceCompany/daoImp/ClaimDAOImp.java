package com.claimManagement.insuranceCompany.daoImp;

import com.claimManagement.insuranceCompany.dao.ClaimDAO;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimDAOImp implements ClaimDAO {

    @Autowired
    ClaimRepository claimRepository;

    static   String generateClaimId(ClaimDetails claimDetails)
    {

        String policyNo = claimDetails.getPolicy().getPolicyNo();
        String year = String.valueOf(claimDetails.getDateOfAccident().getYear());
        
        return "CL" +
                policyNo.substring(policyNo.length()-4) +
                year;
    }

    @Override
    public List<ClaimDetails> getClaims() {
        return claimRepository.findAll();
    }

    @Override
    public String AddNewClaim(ClaimDetails claimDetails) {
        claimDetails.setClaimId(generateClaimId(claimDetails));
        claimRepository.save(claimDetails);
        return "added";
    }


    @Override
    public String UpdateByClaimID(String claimId,ClaimDetails claimDetails) {
        claimRepository.findByClaimId(claimId);
        return "updated";
    }

    @Override
    public ClaimDetails getClaimById(String id) {
        return claimRepository.findByClaimId(id);
    }
}
