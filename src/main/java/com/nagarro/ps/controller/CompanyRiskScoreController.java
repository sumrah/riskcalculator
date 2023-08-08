package com.nagarro.ps.controller;

import java.util.List;

import com.nagarro.ps.model.CompanyRiskScore;
import com.nagarro.ps.service.CompanyRiskScoreService;
import com.nagarro.ps.service.RiskCalcLogicService;
import com.nagarro.ps.service.RiskDimensionWeightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companyriskscore")
public class CompanyRiskScoreController {

	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;

	/*
	 * @Autowired private RiskCalcLogicService riskCalcLogicService;
	 * 
	 * @Autowired private RiskDimensionWeightService riskDimensionService;
	 */

	@PostMapping("/add")
	public ResponseEntity<CompanyRiskScore> createCompanyRiskScore(@RequestBody CompanyRiskScore companyRiskScore) {
		return new ResponseEntity<>(companyRiskScoreService.createCompanyRiskScore(companyRiskScore),
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CompanyRiskScore>> getAllCompanyRiskScore() {

		return new ResponseEntity<>(companyRiskScoreService.getAllCompanyRiskScore(), HttpStatus.OK);
	}

	@GetMapping("/{companyName}")
	public ResponseEntity<CompanyRiskScore> getCompanyRiskScore(@PathVariable("companyName") String companyName) {
		return new ResponseEntity<>(companyRiskScoreService.getCompanyRiskScore(companyName), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<CompanyRiskScore> updateCompanyRiskScore(
			@RequestBody CompanyRiskScore companyRiskScore) {
		 
		return new ResponseEntity<>(companyRiskScoreService.updateCompanyRiskScore(companyRiskScore), HttpStatus.OK);
	}
	
//deleting records
	@DeleteMapping("{companyName}")
    public ResponseEntity<String> deleteCompanyRiskScore(
    		@PathVariable("companyName")String companyName){
		companyRiskScoreService.deleteCompanyRiskScore(companyName);
		return new ResponseEntity<>("company successfully deleted!", HttpStatus.OK);
    }

	/*
	 * public void calculateRiskScore() { List<CompanyRiskScore> companies =
	 * companyRiskScoreService.getCompanyNames(); System.out.println(companies);
	 * 
	 * // Looping through each company for (CompanyRiskScore company : companies) {
	 * // Getting the company name String companyName = company.getCompanyName();
	 * 
	 * // Getting the dimensions and scores for the company // currently columns are
	 * hardcoded,need to devise logic for their dynamic // fetching String
	 * informationSecurity = company.getInformationSecurity(); String resilience =
	 * company.getResilience(); String conduct = company.getConduct();
	 * 
	 * // Getting the weights for each dimension from the risk dimension table
	 * double informationSecurityWeight =
	 * riskDimensionService.findByDimensionName(informationSecurity) .getWeight();
	 * double resilienceWeight =
	 * riskDimensionService.findByDimensionName(resilience).getWeight(); double
	 * conductWeight =
	 * riskDimensionService.findByDimensionName(conduct).getWeight();
	 * 
	 * // Getting the formulas for each calculation from the risk calc logic table
	 * 
	 * String informationSecurityFormula =
	 * riskCalcLogicService.informationSecurityFormula(); String resilienceFormula =
	 * riskCalcLogicService.resilienceFormula(); String conductFormula =
	 * riskCalcLogicService.conductFormula();
	 * 
	 * String totalRiskScoreFormula = riskCalcLogicService.totalRiskScoreFormula();
	 * String totalRiskCappedScoreFormula =
	 * riskCalcLogicService.totalRiskCappedScoreFormula(); String
	 * compositeRiskScoreFormula = riskCalcLogicService.compositeRiskScoreFormula();
	 * 
	 * double informationSecurityWeightValue =
	 * riskCalcLogicService.evaluateFormula(informationSecurityFormula,
	 * informationSecurity, informationSecurityWeight); double resilienceWeightValue
	 * = riskCalcLogicService.evaluateFormula(resilienceFormula, resilience,
	 * resilienceWeight); double conductWeightValue =
	 * riskCalcLogicService.evaluateFormula(conductFormula, conduct, conductWeight);
	 * //double totalRiskScoreValue =
	 * riskCalcLogicService.evaluateFormula(totalRiskScoreFormula,
	 * informationSecurityWeightValue, resilienceWeightValue, conductWeightValue);
	 * 
	 * 
	 * 
	 * // now we need to calculate the values of formula individually // creating
	 * calculation logic in calculation service
	 * 
	 * } }
	 */
}
