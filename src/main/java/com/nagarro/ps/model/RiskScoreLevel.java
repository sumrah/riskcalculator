package com.nagarro.ps.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RiskScoreLevel {

	@Id
	private int rangeStart;
	private String riskLevel;
	private int rangeEnd;
}
//assume that it is same for every company calculation