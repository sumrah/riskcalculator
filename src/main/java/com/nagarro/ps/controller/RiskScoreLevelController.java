package com.nagarro.ps.controller;

import java.util.List;

import com.nagarro.ps.model.RiskScoreLevel;
import com.nagarro.ps.service.RiskScoreLevelService;

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
@RequestMapping("/riskscorelevel")
public class RiskScoreLevelController {

	@Autowired
	private RiskScoreLevelService riskScoreLevelService;

	
	@PostMapping
	public ResponseEntity<RiskScoreLevel> createScoreLevel(@RequestBody RiskScoreLevel riskScoreLevel) {
		return new ResponseEntity<>(riskScoreLevelService.createRiskScoreLevel(riskScoreLevel), HttpStatus.OK);
	}

//fetches all risk score levels
	@GetMapping("/all")
	public ResponseEntity<List<RiskScoreLevel>> getAllRiskScoreLevels() {
		return new ResponseEntity<>(riskScoreLevelService.getAllRiskScoreLevels(), HttpStatus.OK);
	}

//updating the criteria for riskScores
	@PutMapping("/update")
	public ResponseEntity<RiskScoreLevel> updateRiskScoreLevel(@RequestBody RiskScoreLevel riskScoreLevel) {

		return new ResponseEntity<>(riskScoreLevelService.updateRiskScoreLevel(riskScoreLevel), HttpStatus.ACCEPTED);
	}

	// check this
//deleting records
	@DeleteMapping("{rangeStart}")
	public ResponseEntity<String> deleteRiskScoreLevel(@PathVariable("rangeStart") int rangeStart) {
		riskScoreLevelService.deleteRiskScoreLevel(rangeStart);
		return new ResponseEntity<>("riskScore Level successfully deleted!", HttpStatus.OK);
	}
}
