package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Departament;
import org.app.service.entities.Employee;

public interface DepartamentEmployeeEvaluationService extends EntityRepository<Departament>{
	//create aggregate entity
	Departament createNewDepartament(Integer id);
	//Query method on Employee components
	Employee getEmployeeById(Integer employeeid);
	//Other
	String getMessage();
}
