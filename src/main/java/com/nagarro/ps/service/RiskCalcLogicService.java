package com.nagarro.ps.service;

import java.util.List;

import com.nagarro.ps.model.RiskCalculationLogic;
import com.nagarro.ps.repository.RiskCalculationLogicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskCalcLogicService {

// calc the weights and other requirements according to the formula
	@Autowired
	private RiskCalculationLogicRepository riskCalcRepo;

	public RiskCalculationLogic createCalcFormulas(RiskCalculationLogic riskCalcLogic) {
		return riskCalcRepo.save(riskCalcLogic);
	}

//fetching all stored formula
	public List<RiskCalculationLogic> getAllCalcLogicFormulas() {
		return riskCalcRepo.findAll();
	}

//update the formulas
	public RiskCalculationLogic updateRiskCalcFormula(RiskCalculationLogic riskCalcLogic) {
		RiskCalculationLogic existingLogic = riskCalcRepo.findById(riskCalcLogic.getId()).get();
		existingLogic.setElementName(riskCalcLogic.getElementName());
		existingLogic.setFormula(riskCalcLogic.getFormula());
		return riskCalcRepo.save(existingLogic);
	}

	public boolean deleteElement(int id) {
		RiskCalculationLogic existingElement = riskCalcRepo.findById(id).orElse(null);

		if (existingElement != null) {
			riskCalcRepo.delete(existingElement);
			return true;
		} else {
			return false;
		}
	}
//function to sort the formulalist
	 public List<RiskCalculationLogic> getSortedListLogic(List<RiskCalculationLogic> calcFormulasList) {
	      	// Sort the list using the RiscCalccomparator 
		 //here sorting is based on previous use of elementName in the formula
		 calcFormulasList.sort(new RiskCalcComparator());
	        return calcFormulasList;
	    }
	

}

//
//	// Creating a method to find a formula by its name from the table
//	public RiskCalculationLogic findByFormulaName(String formulaName) {
//		return riskCalcRepo.findByFormulaName(formulaName);
//	}
//
//	
//
//	public String totalRiskScoreFormula() {
//		return findByFormulaName("TotalRiskScore").getFormula();
//	}
//
//	public String totalRiskCappedScoreFormula() {
//		return findByFormulaName("TotalRiskScore").getFormula();
//	}
//
//	public String compositeRiskScoreFormula() {
//		return findByFormulaName("CompositeRiskScore").getFormula();
//
//	}
//
////		//calculation of formula using Javaluator class
////		DoubleEvaluator eval = new DoubleEvaluator();
////		//expression-->informationSecurityFormula
////		StaticVariableSet<Double> variables = new StaticVariableSet<Double>();//static means that the values of variables are set before starting to evaluate the expressions.
////	    variables.set("informationSecurity", 2.0);
////	    variables.set("weight", 3.0);		
////		//eval.evaluate(expression, variables);
////    }
//
//	public double evaluateFormula(String informationSecurityFormula, String informationSecurity,
//			double informationSecurityWeight) {
//		DoubleEvaluator eval = new DoubleEvaluator();
//		StaticVariableSet<String> variables = new StaticVariableSet<String>();
//		 variables.set("informationSecurity",String.valueOf(informationSecurity) );
//		 variables.set("weight",String.valueOf(informationSecurityWeight));
//		
//		
//		return 0;
//	}
//	
