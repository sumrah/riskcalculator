package com.nagarro.ps.service;

import java.util.List;

import com.nagarro.ps.model.RiskDimensionWeights;
import com.nagarro.ps.repository.RiskDimensionWeightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RiskDimensionWeightService {

	@Autowired
	private RiskDimensionWeightRepository riskDimensionRepo;

//service api  to save the dimentions and its corresponding weights where totalweight!>100 
	public RiskDimensionWeights createRiskDimensionWeight(RiskDimensionWeights dimensionWeight) {
		log.info("creating the weights info {}", dimensionWeight.getDimension());

		if (dimensionWeight.getWeight() > 100) {
			throw new IllegalArgumentException("Weight should be less than or equal to 100");
		}
	// checking the totalsum of weights of the column
		double totalWeight = riskDimensionRepo.findAll().stream().mapToDouble(RiskDimensionWeights::getWeight).sum()
				+ dimensionWeight.getWeight();
		if (totalWeight > 100) {
			throw new IllegalArgumentException("Total weight should not exceed 100");
		}
		return riskDimensionRepo.save(dimensionWeight);
	}

//fetching all weights and its dimensions
	public List<RiskDimensionWeights> getAllRiskDimensionWeight() {
		log.info("fetching the dimentions and their weights ");
		return this.riskDimensionRepo.findAll();
	}

//fetching weights corresponding to dimension provided by user
	public RiskDimensionWeights getRiskDimensionWeightByName(String dimension) {

		return riskDimensionRepo.findById(dimension).orElse(null);
	}

//update api	
	public RiskDimensionWeights updateRiskDimensionWeight(RiskDimensionWeights riskDimensionWeight) {

		RiskDimensionWeights updatingRiskDimensionWeight = riskDimensionRepo
				.findById(riskDimensionWeight.getDimension()).get();

		updatingRiskDimensionWeight.setWeight(riskDimensionWeight.getWeight());
		return riskDimensionRepo.save(updatingRiskDimensionWeight);
	}

//deleting the records based on dimension name given by user
	public boolean deleteByDimensionName(String dimension) {
		RiskDimensionWeights existingDimension = riskDimensionRepo.findById(dimension).orElse(null);
		if (existingDimension != null) {
			riskDimensionRepo.delete(existingDimension);
			return true;
		} else {
			return false;
		}

	}
}
