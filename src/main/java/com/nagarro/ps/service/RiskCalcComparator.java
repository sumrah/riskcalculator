package com.nagarro.ps.service;

import java.util.Comparator;

import com.nagarro.ps.model.RiskCalculationLogic;

public class RiskCalcComparator implements Comparator<RiskCalculationLogic>{

	
	@Override
	public int compare(RiskCalculationLogic o1, RiskCalculationLogic o2) {
		
		// Check if r1's formula contains r2's elementName
        if (o1.getFormula().contains(o2.getElementName())) {
            return -1; // Move r1 ahead of r2
        }

        // Check if r2's formula contains r1's elementName
        if (o2.getFormula().contains(o1.getElementName())) {
            return 1; // Move r2 ahead of r1
        }
		return 0;
	}
	
	

}
