package com.claimManagement.insuranceCompany.daoImp;

import com.claimManagement.insuranceCompany.dao.PolicyDAO;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.PolicyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class PolicyDAOImp implements PolicyDAO {

   //injection of PolicyRepository
    @Autowired
     PolicyRepository policyRepository;

    //inserting into policy
    @Override
    public String addPolicy(Policy p) {

        p.setPolicyNo(generatePolicyNo(p));
        Policy p1=policyRepository.save(p);
        return "added";
    }

    @Override
    public Policy getById(String policyNo) throws CustomException {
       if(policyRepository.existsPolicyByPolicyNo(policyNo))
       {
           return policyRepository.findByPolicyNo(policyNo);
       }
       else
       {
           throw new CustomException("No policy with id:"+policyNo);
       }
    }

    @Override
    public List<Policy> getAllPolicies() {

//        return  policyRepository.findAllById()
        List<Policy> it= policyRepository.findAll();
        return it;
    }

    //generating policyNo
    static   String generatePolicyNo(Policy p)
    {
        String ln = p.getInsuredLastName();
        String vn = p.getVehicleNo();
        String year = String.valueOf(p.getDateOfInsurance().getYear());
        return ln.substring(0,2) +
                vn.substring(vn.length() - 3) +
                year.substring(year.length() - 2);
    }

	@Override
	public String AddPolicyByHardCode() {
		Policy p=new Policy( "Shaik", "Ameer",LocalDate.of(2022,1,1),"ameersuhail@gmail.com", "ABC123",true);
		p.setPolicyNo(generatePolicyNo(p));
		policyRepository.save(p);
		return "data hard coded to policy database";
	}

}
