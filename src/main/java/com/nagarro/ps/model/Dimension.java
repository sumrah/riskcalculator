/*This class is embedded into the entity-CompanyRiskScore
 * */

package com.nagarro.ps.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Dimension {

	
	private String dimensionName;
	private String dimensionValue;
	
	
}
