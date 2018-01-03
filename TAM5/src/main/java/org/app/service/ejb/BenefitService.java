package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Benefit;

@Remote
public interface BenefitService {

	//CREATE or UPDATE
	Benefit addBenefit(Benefit benefitToAdd);
	
	//DELETE
	String removeBenefit(Benefit benefitToDelete);
	
	//READ
	Benefit getBenefitByBenefitID(Integer benefitID);
	Collection<Benefit> getBenefit();
	
	//Custom READ: custom query
	Benefit getBenefitByName(String benefitName);
	
	//Others
	String sayRest();
}
