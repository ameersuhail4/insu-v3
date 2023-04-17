package com.claimManagement.insuranceCompany.controllers;

import com.claimManagement.insuranceCompany.daoImp.ClaimDAOImp;
import com.claimManagement.insuranceCompany.daoImp.PolicyDAOImp;
import com.claimManagement.insuranceCompany.daoImp.SurveyorDAOImp;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClaimController {

    @Autowired
    ClaimDAOImp claimDAOImp;
    @Autowired
    PolicyDAOImp policyDAOImp;
    @Autowired
    SurveyorDAOImp surveyorDAOImp;

    @GetMapping("/api/claims")
    List<ClaimDetails> getClaims() throws CustomException
    {
    	List<ClaimDetails> claimList=claimDAOImp.getClaims();
    	if(claimList.size()==0)
    	{
    		throw new CustomException("No Claims exist");
    	}
        return claimList;
    }

    @GetMapping("/api/claims/{id}")
    ClaimDetails getClaim(@PathVariable String id)
    {
        return claimDAOImp.getClaimById((id));
    }

    @PostMapping("/api/claims/new")

    ResponseEntity addNewClaim(@RequestBody ClaimDetails claimDetails) throws CustomException {
//        if (claimDetails == null) {
//            throw new IllegalArgumentException("Request body cannot be null");
//        }
        Policy policy;
        Surveyor surveyor;

            policy=policyDAOImp.getById(claimDetails.getPolicy().getPolicyNo());
        System.out.println("test"+policy);
            claimDetails.setPolicy(policy);
            int estimateLimit=claimDetails.getEstimatedLoss();
            surveyor= surveyorDAOImp.getSurveyorByEstimateLimit(estimateLimit);
            claimDAOImp.AddNewClaim(claimDetails);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("New Claim Added with id:"+claimDetails.getClaimId());
    }

    @PutMapping("/api/claims/{claimId}/update")
    String updateClaimById(@PathVariable String claimId,@RequestBody ClaimDetails claimDetails)
    {
        claimDAOImp.UpdateByClaimID(claimId,claimDetails);
        return "updated claim";
    }
}
