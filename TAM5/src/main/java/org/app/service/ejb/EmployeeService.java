package org.app.service.ejb;
import java.util.Collection;
import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Employee;


@Remote
public interface EmployeeService extends EntityRepository<Employee>{
//		//CREATE or UPDATE
//		Employee addEmployee(Employee employeeToAdd);
//		
//		//DELETE
//		String removeEmployee(Employee employeeToDelete);
//			
//		//READ
//		Employee getEmployeeByEmployeeID(Integer employeeID);
//		Collection<Employee> getEmployee();
//			
//		//Custom READ: custom query
//		Employee getEmployeeByName(String EmployeeName);
		
	//Others
	String sayRest();
}
