package com.claimManagement.insuranceCompany.dao;

import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.exceptions.CustomException;

import java.util.List;

public interface PolicyDAO {

	String addPolicy(Policy p);

    Policy getById(String policyNo) throws CustomException;

    List<Policy> getAllPolicies();
    
    String AddPolicyByHardCode();

}
