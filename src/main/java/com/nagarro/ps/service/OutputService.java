/*fetches the elementName from the database, retrieves the formula,
 *  evaluates it using the Javaluator library, 
 * stores the result back in the element, and saves it in the database.
 * */

package com.nagarro.ps.service;

import java.util.List;

import com.nagarro.ps.model.Output;
import com.nagarro.ps.repository.OutputRepository;
import com.nagarro.ps.repository.RiskCalculationLogicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputService {

	 @Autowired
	 private OutputRepository outputRepo;
	 
//fetching all records
	 public List<Output>getAllRecords(){
		 return outputRepo.findAll();
	 }
//Creating a method to save an output object to the table
	    public void save(Output output) {
	    	
	        outputRepo.save(output);
	    }
	    
}
