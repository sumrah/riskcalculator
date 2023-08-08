package com.nagarro.ps.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
	
	@Id
	private String companyName;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name="dimensionWeight")
	@CollectionTable(name="final_dimension_weights",joinColumns=@JoinColumn(name="companyName"))
	private List<OutputValues>dimensionWeights;
	private float totalRiskScore;
	private float totalRiskCappedScore;
	private float compositeRiskScore;
}
