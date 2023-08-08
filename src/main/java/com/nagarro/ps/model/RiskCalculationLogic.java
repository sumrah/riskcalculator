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
public class RiskCalculationLogic {

	@Id
	private int id;
	private String elementName;
	private String formula;
}
