package com.nagarro.ps.repository;

import com.nagarro.ps.model.RiskDimensionWeights;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskDimensionWeightRepository extends JpaRepository<RiskDimensionWeights, String> {

//	RiskDimensionWeights findByDimensionName(String dimension);
//	
//	void deleteByRiskDimensionName(String companyName);
}
