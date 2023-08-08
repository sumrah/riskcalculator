package com.nagarro.ps.repository;

import com.nagarro.ps.model.Output;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository extends JpaRepository<Output,String>{// can be taken as long also if id is added
	
}


