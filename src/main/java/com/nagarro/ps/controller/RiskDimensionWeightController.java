package com.nagarro.ps.controller;

import java.util.List;
import com.nagarro.ps.model.RiskDimensionWeights;
import com.nagarro.ps.service.RiskDimensionWeightService;
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

@RequestMapping("/riskdimensionweight")
public class RiskDimensionWeightController {

	@Autowired
	private RiskDimensionWeightService riskDimensionWeightService;

//api to store the risk dimension and its corresponding weight
	@PostMapping
	public ResponseEntity<RiskDimensionWeights> createDimensionWeights(
			@RequestBody RiskDimensionWeights dimensionWeight) {
		return new ResponseEntity<>(riskDimensionWeightService.createRiskDimensionWeight(dimensionWeight),
				HttpStatus.CREATED);
	}

//api for fetching all weights corresponding to risk dimensions
	@GetMapping
	public ResponseEntity<List<RiskDimensionWeights>> getAllRiskDimensionWeights() {

		return new ResponseEntity<>(riskDimensionWeightService.getAllRiskDimensionWeight(), HttpStatus.OK);
	}

//api to fetch weight corresponding to a particular dimension required by user
	@GetMapping("/{dimension}")
	public ResponseEntity<RiskDimensionWeights> getRiskDimesionWeight(@PathVariable("dimension") String dimension) {
		return new ResponseEntity<>(riskDimensionWeightService.getRiskDimensionWeightByName(dimension), HttpStatus.OK);
	}

//update api,incase user entered the wrong weight corresponding to a particular dimension & wants updation
	@PutMapping("/update")
	public ResponseEntity<RiskDimensionWeights> updateRiskDimensionWeight(
			@RequestBody RiskDimensionWeights riskDimensionWeight) {

		return new ResponseEntity<>(riskDimensionWeightService.updateRiskDimensionWeight(riskDimensionWeight),
				HttpStatus.ACCEPTED);
	}

//delete particular weight
	@DeleteMapping("/{dimension}")
	public ResponseEntity<String> deleteRiskDimension(@PathVariable("dimension") String dimension) {
		
		boolean isDeleted = riskDimensionWeightService.deleteByDimensionName(dimension);

        if (isDeleted) {
            return ResponseEntity.ok("Element deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Element not found");
        }
		
		
	}
}

