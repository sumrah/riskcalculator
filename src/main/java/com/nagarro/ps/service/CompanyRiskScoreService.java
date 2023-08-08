package com.nagarro.ps.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.nagarro.ps.model.CompanyRiskScore;
import com.nagarro.ps.model.Dimension;
import com.nagarro.ps.repository.CompanyRiskScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyRiskScoreService {

	/*
	 * use this class to write the logic when we want to have dynamic columns in our
	 * db that is we will delete the information security column and add some other
	 * dimention
	 */
	@Autowired
	private CompanyRiskScoreRepository companyRiskScoreRepo;

	public CompanyRiskScore createCompanyRiskScore(CompanyRiskScore companyRiskScore) {

//	CompanyRiskScore existingCompanyRiskScore = companyRiskScoreRepo.findById(companyRiskScore.getCompanyName()).get();
		// logic if duplicat company exists with same id
		// companyRiskScore.getCompanyName //log the company name
//		if ( existingCompanyRiskScore.exists) {
//			return ( existingCompanyRiskScore).get();
//		}
//		List<Dimension> dimensionList= Arrays.asList(
//							new Dimension("informationsecurity","80"),
//							new Dimension("conduct","70"),
//							new Dimension("resilience","60"));
//				
//		CompanyRiskScore companyriskscore= new CompanyRiskScore("tcs",dimensionList);
		
		
		return companyRiskScoreRepo.save(companyRiskScore);
	}
    
	
	
	
	
// Creating a method to find all the companies riskscore from the table
	public List<CompanyRiskScore> getAllCompanyRiskScore() {
		return this.companyRiskScoreRepo.findAll();
	}

	
	public CompanyRiskScore getCompanyRiskScore(String companyName) {
		return companyRiskScoreRepo.findById(companyName).orElse(null) ;
	}

//update api
	public CompanyRiskScore updateCompanyRiskScore(
					CompanyRiskScore companyRiskScore) {
		CompanyRiskScore existingCompanyRiskScore = companyRiskScoreRepo.findById(companyRiskScore.getCompanyName()).get();
			
			//update dimensions
			existingCompanyRiskScore.setDimensions(companyRiskScore.getDimensions());
			return companyRiskScoreRepo.save(existingCompanyRiskScore);
	}
	
	public void deleteCompanyRiskScore(String companyName) {
		companyRiskScoreRepo.deleteCompanyRiskScoreById(companyName);
	}
	
	
	
	
	
	
}
