package com.nagarro.ps.controller;

import java.util.List;

import com.nagarro.ps.model.ScoreCap;
import com.nagarro.ps.service.ScoreCapService;

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
@RequestMapping("/scorecap")
public class ScoreCapController {

	@Autowired
	private ScoreCapService scoreCapService;

	@PostMapping
	public ResponseEntity<ScoreCap> createScoreCap(@RequestBody ScoreCap scoreCapData) {
		return new ResponseEntity<>(scoreCapService.createScoreCapValues(scoreCapData), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ScoreCap>> getAllScoreCap() {
		return new ResponseEntity<>(scoreCapService.getAllScoreCapRecords(), HttpStatus.OK);
	}

// updating the criteria for scoreCap
	@PutMapping("/update")
	public ResponseEntity<ScoreCap> updateScoreCap(@RequestBody ScoreCap scoreCap) {

		return new ResponseEntity<>(scoreCapService.updateScoreCap(scoreCap), HttpStatus.ACCEPTED);
	}

// deleting records
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteScoreCap(@PathVariable("id") String id) {

		boolean isDeleted = scoreCapService.deleteScoreCapById(id);

        if (isDeleted) {
            return ResponseEntity.ok("scoreCap value deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }

}
}
