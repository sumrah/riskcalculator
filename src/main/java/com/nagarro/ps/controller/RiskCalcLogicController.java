package com.nagarro.ps.controller;

import java.util.List;

import com.nagarro.ps.model.CompanyRiskScore;
import com.nagarro.ps.model.RiskCalculationLogic;
import com.nagarro.ps.model.RiskScoreLevel;
import com.nagarro.ps.service.RiskCalcLogicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/riskcalclogic")
public class RiskCalcLogicController {

	@Autowired
	private RiskCalcLogicService riskCalcLogicService;
	
	@PostMapping
	public ResponseEntity<RiskCalculationLogic> createRiskCalcLogicFormulas(@RequestBody RiskCalculationLogic riskCalcLogic) {
		return new ResponseEntity<>(riskCalcLogicService.createCalcFormulas(riskCalcLogic),
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<RiskCalculationLogic>> getAllRiskCalcLogicFormula() {
		return new ResponseEntity<>(riskCalcLogicService.getAllCalcLogicFormulas(), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<RiskCalculationLogic> updateRiskCalcLogicFormula(@RequestBody RiskCalculationLogic riskCalcLogic ) {

		return new ResponseEntity<>(riskCalcLogicService.updateRiskCalcFormula(riskCalcLogic), HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElementLogic(@PathVariable int id) {
        boolean isDeleted = riskCalcLogicService.deleteElement(id);

        if (isDeleted) {
            return ResponseEntity.ok("Element deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Element not found");
        }
    }
	
	
	
	
	
	
	
	
	
}
