package org.app.service.ejb;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Departament;
import org.app.service.entities.Employee;

@Stateless
@LocalBean
public class DepartamentEmployeeEvaluationEJB extends EntityRepositoryBase<Departament> 
implements DepartamentEmployeeEvaluationService, Serializable {

	private static Logger logger = Logger.getLogger(DepartamentEmployeeEvaluationEJB.class.getName());
	
	@EJB 
	private EvaluationService evaluationService;
	//Local component-entity-repository
	private EntityRepository<Employee> employeeRepository;
	
	@PostConstruct
	public void init() {
		// local instantiation of local component-entity-repository
		employeeRepository = new EntityRepositoryBase<Employee>(this.em, Employee.class);
		logger.info("POSTCONSTRUCT-INIT employeeRepository: " + this.employeeRepository);
		logger.info("POSTCONSTRUCT-INIT evaluationService: " + this.evaluationService);
	}

	@Override
	public Departament createNewDepartament(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeById(Integer employeeid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
