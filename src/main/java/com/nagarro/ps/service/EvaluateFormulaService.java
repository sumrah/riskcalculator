package com.nagarro.ps.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.nagarro.ps.model.CompanyRiskScore;
import com.nagarro.ps.model.Dimension;
import com.nagarro.ps.model.RiskCalculationLogic;
import com.nagarro.ps.model.RiskDimensionWeights;
import com.nagarro.ps.repository.CompanyRiskScoreRepository;
import com.nagarro.ps.repository.RiskCalculationLogicRepository;
import com.nagarro.ps.repository.RiskDimensionWeightRepository;
import com.nagarro.ps.repository.OutputRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EvaluateFormulaService {

	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;

	@Autowired
	private CompanyRiskScoreRepository companyRiskScoreRepo;

	@Autowired
	private RiskCalculationLogicRepository riskCalcRepo;

	@Autowired
	private RiskCalcLogicService riskCalcLogicService;

	@Autowired
	private RiskDimensionWeightRepository riskDimensionWeightRepo;

	@Autowired
	private ScoreCapService scoreCapService;
	
	@Autowired
	private OutputRepository outputRepo;

	public void evaluateFormula(String companyName) {
		CompanyRiskScore companyDimensions = companyRiskScoreRepo.findById(companyName).orElse(null);
		// bring dimension and its value
		Map<String, Double> dimensionScoreMap = new HashMap<>();

		// store the weightage of each dimension
		Map<String, Double> dimensionWeightage = new HashMap<>();

		List<RiskCalculationLogic> calcFormulasList = riskCalcRepo.findAll();// list of formulas
		// sort the list according to values which are independent first and then
		// dependent on each other ones
		List<RiskCalculationLogic> sortedFormulaList = riskCalcLogicService.getSortedListLogic(calcFormulasList);// make
			
		// Map for storing the individual calculation result
		Map<String, Double> formulaCalcResults = new HashMap<>();

		if (companyDimensions != null) {
			List<Dimension> dimensions = companyDimensions.getDimensions();
			for (Dimension dimension : dimensions) {
				String dimensionName = dimension.getDimensionName();
				String dimensionValue = dimension.getDimensionValue();
				double value = Double.parseDouble(dimensionValue);
				log.info("Name of dimension:{},Value:{}", dimensionName, dimensionValue);
				dimensionScoreMap.put(dimensionName, value);

				RiskDimensionWeights dimensionWeights = riskDimensionWeightRepo.findById(dimensionName).get();
				// ?what to do to store dimensionname(informationsecurity)
				// as informatonsecurityweight in the table
				dimensionWeightage.put(dimensionWeights.getDimension(), Double.valueOf(dimensionWeights.getWeight()));
			}
		}

		// calculation of formula using Javaluator class
		DoubleEvaluator evaluator = new DoubleEvaluator();
		StaticVariableSet<Double> variables = new StaticVariableSet<>();
		/*
		 * create and then return a 'set' of the same elements that are already present
		 * in the HashMap. It can be used with a loop to iterate over all the entries of
		 * a HashMap.
		 */
		// set variables for calculating dimensionWeightage
		for (Map.Entry<String, Double> weightVar : dimensionWeightage.entrySet()) {
			variables.set(weightVar.getKey(), weightVar.getValue());
		}

		// scoreMap for a particular company dimensions
		Double totalriskCappedScore = scoreCapService.totalRiskCappedScore(dimensionScoreMap);
		// totalriskscorecap value for a company
		variables.set("totalRiskCappedScore", totalriskCappedScore);

		// looping thru each formula in riskcalclogic table
		for (RiskCalculationLogic calcFormulas : sortedFormulaList) {
			//creating the expression to be evaluated inside formula
			String formula = calcFormulas.getFormula();// how this formula is getting extracted without elementname?

			// informationsecurity_dimensionValue(ist table)
			// replacing the values in formula with user given values
			for (Map.Entry<String, Double> scoreVar : dimensionScoreMap.entrySet()) {
				variables.set(scoreVar.getKey(), Double.valueOf(scoreVar.getValue()));
			}

			for (Map.Entry<String, Double> formulaEntry : formulaCalcResults.entrySet()) {
				variables.set(formulaEntry.getKey(), formulaEntry.getValue());
			}

			// evaluating the formula
			Double result = evaluator.evaluate(formula, variables);
			log.info("Result",result);
			
			try {
				 outputRepo.set
				outputService.save(companyName,dimensionWeightage,totalriskCappedScore,result.doubleValue());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}

		}

	}
}
