package com.claimManagement.insuranceCompany.dao;

import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SurveyorDAO {


    String addSurveyor(Surveyor surveyor);
    List<Surveyor> listOfSurveyors();
    Surveyor getSurveyorByEstimateLimit(int estimatelimit) throws CustomException;
    Surveyor getSurveyorById(int id);
    String addSurveyorByHardCode(); 
}
