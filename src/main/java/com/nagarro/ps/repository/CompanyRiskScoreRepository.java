package com.nagarro.ps.repository;

import com.nagarro.ps.model.CompanyRiskScore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRiskScoreRepository extends JpaRepository<CompanyRiskScore,String> {

	//CompanyRiskScore findByCompanyName(String companyName);
	
	@Query(value = "DELETE FROM CompanyRiskScore c WHERE c.companyName = :companyName")
	void deleteCompanyRiskScoreById(String companyName);

	
}
