package com.nagarro.ps.model;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CompanyRiskScore {

	@Id
	@Column(name = "companyName")
	private String companyName;

	// FetchType.Eager- will by default, load ALL of the relationships related to a
	// particular object
	@ElementCollection(fetch = FetchType.EAGER) // Must be specified if the collection is to be mapped by means of non-entities										
	@Column(name = "dimension")
	@CollectionTable(name = "dimension_attributes", joinColumns = @JoinColumn(name = "companyName"))
	private List<Dimension> dimensions;

	/*@Id 
	 * private String companyName;
	 * @ElementCollection(fetch = FetchType.EAGER)
	 * @CollectionTable(name = "dimensions_attribute", joinColumns = @JoinColumn(name = "companyName")) 
	 * @Column(name = "dimension") 
	 * private List<String> dimensions;
	 * 
	 * 
	 * 
	 * */
	

}
