package com.claimManagement.insuranceCompany.daoImp;

import com.claimManagement.insuranceCompany.dao.SurveyorDAO;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.SurveyorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyorDAOImp implements SurveyorDAO {

    @Autowired
    private SurveyorRepository surveyorRepository;

    @Override
    public List<Surveyor> listOfSurveyors() {
        return surveyorRepository.findAll();
    }

    @Override
    public Surveyor getSurveyorByEstimateLimit(int estimatelimit) throws CustomException {
        if (estimatelimit!=0)
        {
            if(surveyorRepository.existsSurveyorByEstimateLimit(estimatelimit))
            {
                return surveyorRepository.findByEstimateLimit(estimatelimit);
            }
            else
            {
                throw  new CustomException("No Surveyor found for estimateLoss:"+estimatelimit);
            }

        }else {
            throw  new CustomException("estimateLoss should be greater than zero");
        }

    }

    @Override
    public Surveyor getSurveyorById(int id) {
        return surveyorRepository.findSurveyorBySurveyorId(id);
    }

    @Override
    public String addSurveyor(Surveyor surveyor) {
        surveyorRepository.save(surveyor);
        return "added succesfully";
    }

	@Override
	public String addSurveyorByHardCode() {
		Surveyor s=new Surveyor(1, "Jane", "Smith", 10000);
		surveyorRepository.save(s);
		return "hardcoded data into Surveyor database";
	}
}
