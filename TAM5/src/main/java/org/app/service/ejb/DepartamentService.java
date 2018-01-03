package org.app.service.ejb;

import java.util.Collection;
import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Departament;

@Remote
public interface DepartamentService extends EntityRepository<Departament>{
	//CREATE OR UPDATE
//	Departament addDepartament(Departament departamentToAdd);
//	
//	//DELETE
//	String removeDepartament(Departament departamanetToDelete);
//	
//	//READ
//	Departament getDepartamentByDepartamentID(Integer departamentID);
//	
//	Collection<Departament> getDepartaments();
//	
//	//CUSTOM READ: CUSTOM QUERY
//	Departament getDepartamentByName(String departamentName);
	
	//OTHERS
	String sayRest();

}
