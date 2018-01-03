package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.ejb.*;
import org.app.service.entities.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.*;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.*;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestEmployeeServiceEJBArq.class.getName());
	
	//Arquilian infrastructure
	@EJB
	private static EmployeeService service;
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Employee.class.getPackage())
				.addClass(EmployeeService.class)
				.addClass(EmployeeServiceEJB.class)
				.addClass(EntityRepository.class)
				.addClass(EntityRepositoryBase.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING ...");
		String response = service.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test4_GetEmployees() {
		logger.info("DEBUG: Junit TESTING test2_GetEmployees ...");
		
		Collection<Employee> employees = service.toCollection();
		assertTrue("Fail to add Employees!", employees.size() > 0);
	}
	
	
	@Test
	public void test3_AddEmployees() {
		logger.info("DEBUG: Junit TESTING: test3_AddEmployees ...");
		
		Integer employeesToAdd = 4;
		for(int i = 1; i <= employeesToAdd; i++) {
			service.add(new Employee(i, "EmployeeName_" + (i + 100), "PNC" + (100+i), "0743123423" + (i + 100) , "EmailEmployee_"  + i + "@gmail.com"));
		}
		Collection<Employee> employees = service.toCollection();
		assertTrue("Fail to add Employees!", employees.size() == employeesToAdd);
		
	}
	
	@Test
	public void test2_DeleteEmployee() {
		logger.info("DEBUG: Junit TESTING: test4_DeleteEmployee ...");
		
		Collection<Employee> employees = service.toCollection();
		for(Employee e: employees) {
			service.remove(e);
			Collection<Employee> employeesAfterDelete = service.toCollection();
			assertTrue("Fail to read employees!", employeesAfterDelete.size()==0);
		}
	}
	
}
