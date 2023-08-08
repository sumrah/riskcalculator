package com.nagarro.ps.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nagarro.ps.model.CompanyRiskScore;
import com.nagarro.ps.model.RiskScoreLevel;
import com.nagarro.ps.model.ScoreCap;
import com.nagarro.ps.repository.CompanyRiskScoreRepository;
import com.nagarro.ps.repository.RiskScoreLevelRepository;
import com.nagarro.ps.repository.ScoreCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScoreCapService {

	@Autowired
	private ScoreCapRepository scoreCapRepo;
	
	@Autowired
	private RiskScoreLevelRepository riskScoreLevelRepo;
	
	@Autowired
	private RiskScoreLevelService riskLevelService;
	
	@Autowired
	private CompanyRiskScoreRepository companyDimensionScoreRepo;
	
//storing records in db
	public ScoreCap createScoreCapValues(ScoreCap scoreCapData) {
		return scoreCapRepo.save(scoreCapData);
	}

//fetches all records
	public List<ScoreCap> getAllScoreCapRecords() {
		return scoreCapRepo.findAll();
	}

//updating values
	public ScoreCap updateScoreCap(ScoreCap scoreCap) {
		ScoreCap existingScoreCap = scoreCapRepo.findById(scoreCap.getRiskCondition()).get();
		existingScoreCap.setRiskCondition(scoreCap.getRiskCondition());
		existingScoreCap.setConditionQuantity(scoreCap.getConditionQuantity());
		existingScoreCap.setTotalRiskCappedScore(scoreCap.getTotalRiskCappedScore());
		return scoreCapRepo.save(existingScoreCap);
	}

//deleting records from db table
	public boolean deleteScoreCapById(String id) {
		ScoreCap existingScoreCap= scoreCapRepo.findById(id).orElse(null);
		if(existingScoreCap!=null)
		{scoreCapRepo.delete(existingScoreCap);
		return true;
	} else {
		return false;
	}
	}
	
//calculating the totalriskcapped score for particular company's every dimension
//map contains all dimensions of a particular company and we iterate through it	
	public Double totalRiskCappedScore(Map<String,Double>dimension) {
		
		List<RiskScoreLevel> riskLevelList= riskScoreLevelRepo.findAll();//assume every company has same risk levels
		List<ScoreCap>scoreCapList= getAllScoreCapRecords();
		//List<CompanyRiskScore> companyDimensionsList = companyDimensionScoreRepo.findAll();for all companies list
//		CompanyRiskScore companyDimensions = companyDimensionScoreRepo.findById(companyName).get();	
		Map<String,Integer>levelCounter= new HashMap<>();
		for(Map.Entry<String,Double> entry:dimension.entrySet()) {
			String dimensionName= entry.getKey();
			Double dimensionValue = entry.getValue();
			 log.info("Name of dimension:{},Value:{}",dimensionName,dimensionValue);
			 String riskLevel = riskLevelService.findRiskLevel(dimensionValue,riskLevelList);
			 levelCounter.put(riskLevel, levelCounter.getOrDefault(riskLevel, 0)+1);//counts which risklevel,how many times for a company
		}
		double totalriskCappedScore= calcTotalRiskCapScore(levelCounter,scoreCapList);
		
		return  totalriskCappedScore;
	}
	
	public Double calcTotalRiskCapScore(Map<String,Integer> levelCounter,List<ScoreCap> scoreCapList) {
		double totalRiskCappedScore = 100.0; //default
		boolean check= false;
		for(ScoreCap scoreCap:scoreCapList) { //table where is written 1 "high risk" then gives total riskcapscore
			String condition = scoreCap.getRiskCondition();
			int quantity= scoreCap.getConditionQuantity();
			int cappedScore = scoreCap.getTotalRiskCappedScore();
			//looping for each risklevel  in the map that we have found foe company's all dimensions collectively
			for(Map.Entry<String,Integer> entry:levelCounter.entrySet()) {
				String levelString = entry.getKey();//gives high risk
				Integer count = entry.getValue();//gives number of high risk 
				
				if(condition.equalsIgnoreCase(levelString)&& quantity == count) {
					if(!check || cappedScore < totalRiskCappedScore) {
						totalRiskCappedScore = cappedScore;
						check= true;
						
					}
					
				}
				
			}
		}
		return totalRiskCappedScore;
	}

	// Creating a method to find the first score cap by descending order from the
	// table
//	    public ScoreCap findFirstByOrderByCapDesc() {
//	        return scoreCapRepo.findFirstByOrderByCapDesc();
	
//	    }

}
