package org.app.service.ejb;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.*;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Employee;

@Path("employees")
@Stateless
@LocalBean
public class EmployeeServiceEJB extends EntityRepositoryBase<Employee> implements EmployeeService {
	
	//@SuppressWarnings("deprecation")
	public static Logger logger = Logger.getLogger(EmployeeServiceEJB.class.getName());
	
//	// 2. Inject resource 
//	@PersistenceContext(unitName="MSD")
//	private EntityManager em;
//
//	public EmployeeServiceEJB() {
//	}

	@EJB // injected DataService
	private EmployeeService employeeService; 
	
	// Local component-entity-repository
	private EntityRepository<Employee> employeeRepository;
	
	@PostConstruct 
	public void init() {
		employeeRepository = new EntityRepositoryBase<Employee>(this.em,Employee.class);
		logger.info("POSTCONSTRUCT-INIT releaseRepository: " + this.employeeRepository);
		logger.info("POSTCONSTRUCT-INIT featureDataService: " + this.employeeService);
}

//	//CRUD Operation Implementation
//	
//	//CREATE OR UPDATE
//	@Override
//	public Employee addEmployee(Employee employeeToAdd) {
//		em.persist(employeeToAdd);
//		em.flush();
//		em.refresh(employeeToAdd);
//		return employeeToAdd;
//	}
//	
//	//REMOVE
//	@DELETE 					/* SCRUM/data/features		REST-resource: Feature-entity */
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Override
//	public String removeEmployee(Employee employeeToDelete) {
//		employeeToDelete = em.merge(employeeToDelete);
//		em.remove(employeeToDelete);
//		em.flush();
//		return "True";
//	}
//
//	//READ
//	@GET @Path("/{id}")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//	@Override
//	public Employee getEmployeeByEmployeeID(@PathParam("id")Integer employeeID) {
//
//		return em.find(Employee.class, employeeID);
//	}
//	
//	@GET
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Override
//	public Collection<Employee> getEmployee() {
//		List <Employee> employee= em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
//		return employee;
//		
//	}
//	
//
//	
//	//CUSTOM READ: CUSTOM QUERY
//	@Override
//	public Employee getEmployeeByName(String EmployeeName) {
//		return em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class)
//				.setParameter("name", EmployeeName).getSingleResult();
//	}
//	
	//OTHERS
	@GET @Path("/test") // Check if resource is up ...
	@Produces({ MediaType.TEXT_PLAIN})
	@Override
	public String sayRest() {
		// TODO Auto-generated method stub
		return "Employee Service is on...";
	}
	
	@Override
	@GET 					/* MSD-S4/data/projects 		REST-resource: projects-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Employee> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}
	
	@GET @Path("/{id}") 	/* MSD-S4/data/projects/data/{id} 	REST-resource: project-entity*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Employee getById(@PathParam("id") Integer id){ 
		Employee employee = super.getById(id);
		logger.info("**** DEBUG REST getById(" + id +") = " + employee);
		return employee;
	}
	
//	@Override
	@DELETE 				/* MSD-S4/data/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Collection<Employee> removeFromCollection(Employee employee) {
		logger.info("DEBUG: called REMOVE - project: " + employee);
		super.remove(employee);
		return super.toCollection();
	}
	
	@DELETE @Path("/{id}") 	/* MSD-S4/data/projects/{id} 	REST-resource: project-entity*/	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public void remove(@PathParam("id")Integer id) {
		logger.info("DEBUG: called REMOVE - ById() for employee >>>>>>>>>>>>>> simplified ! + id");
		Employee employee = super.getById(id);
		super.remove(employee);
	}
	@POST 					/* MSD-S4/data/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Employee> addIntoCollection(Employee employee) {
		// save aggregate
		super.add(employee);
		logger.info("**** DEBUG REST save aggregate POST");
		// return updated collection
		return super.toCollection();
	}
		
}
