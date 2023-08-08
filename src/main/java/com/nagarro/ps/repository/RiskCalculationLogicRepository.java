package com.nagarro.ps.repository;

import com.nagarro.ps.model.RiskCalculationLogic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskCalculationLogicRepository extends JpaRepository<RiskCalculationLogic,Integer>{

	//RiskCalculationLogic findByFormulaName(String formulaName);
	
	

}
