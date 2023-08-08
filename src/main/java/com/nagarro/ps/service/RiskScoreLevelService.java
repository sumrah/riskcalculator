package com.nagarro.ps.service;

import java.util.Arrays;
import java.util.List;

import com.nagarro.ps.model.CompanyRiskScore;
import com.nagarro.ps.model.RiskDimensionWeights;
import com.nagarro.ps.model.RiskScoreLevel;
import com.nagarro.ps.repository.RiskScoreLevelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
public class RiskScoreLevelService {

	@Autowired
	private RiskScoreLevelRepository riskScoreRepo;

//saving the data entered by user
	public RiskScoreLevel createRiskScoreLevel(RiskScoreLevel riskScoreData) {
		return riskScoreRepo.save(riskScoreData);
	}

//fetches all records
	public List<RiskScoreLevel> getAllRiskScoreLevels() {
		return this.riskScoreRepo.findAll();
	}

//updates the existing record
	public RiskScoreLevel updateRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
		RiskScoreLevel existingRiskScoreLevel = riskScoreRepo.findById(riskScoreLevel.getRangeStart()).get();
		existingRiskScoreLevel.setRangeStart(riskScoreLevel.getRangeStart());
		existingRiskScoreLevel.setRangeEnd(riskScoreLevel.getRangeEnd());
		existingRiskScoreLevel.setRiskLevel(riskScoreLevel.getRiskLevel());
		return riskScoreRepo.save(existingRiskScoreLevel);
	}

//deletes records based on Id which is rangeStart here
	public void deleteRiskScoreLevel(int rangeStart) {
		riskScoreRepo.deleteById(rangeStart);

	}

	public String findRiskLevel(Double score, List<RiskScoreLevel> riskLevelList) {
		// extract list here for a particular company dimensions

		for (RiskScoreLevel riskScoreLevel : riskLevelList) {
			if (score >= riskScoreLevel.getRangeStart() && score < riskScoreLevel.getRangeEnd()) {
				return riskScoreLevel.getRiskLevel();
			} else
				continue;
		}
		return "dimensionScore out of range"; //handle this condition:-what if score doesnt lie in the range

	}

//	public void riskScore() {
//	List<RiskScoreLevel> tableRiskScore= Arrays.asList(new RiskScoreLevel("81-100","very_low_risk"),
//			new RiskScoreLevel("61-80","low_risk"),
//			new RiskScoreLevel("41-60","intermediate"),
//			new RiskScoreLevel("21-40","high_risk"),
//			new RiskScoreLevel("01-20","very_high_risk")
//			);

}
