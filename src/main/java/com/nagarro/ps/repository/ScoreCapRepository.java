package com.nagarro.ps.repository;

import com.nagarro.ps.model.ScoreCap;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreCapRepository extends JpaRepository<ScoreCap,String>{

	//ScoreCap findFirstByOrderByCapDesc();

}
