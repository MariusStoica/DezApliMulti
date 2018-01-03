package org.app.service.rest.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.EmployeeService;
import org.app.service.entities.Employee;
import org.app.service.rest.ApplicationConfig;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeServiceRestArq {
	private static Logger logger = Logger.getLogger(TestEmployeeServiceRestArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/msd-s4-test/data/employee";
	
	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-s4-test.war")
	                .addPackage(Employee.class.getPackage())
	                .addPackage(EmployeeService.class.getPackage())
	                .addPackage(EntityRepository.class.getPackage())
	                .addPackage(ApplicationConfig.class.getPackage())
	                .addAsResource("META-INF/persistence.xml")
	                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); // all mode by default
	}
	
	@Test
	public void test1_GetMessage() {
		String resourceURL = serviceURL + "/test";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		String response = ClientBuilder.newClient().target(resourceURL)
				.request().get()
				.readEntity(String.class);
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test4_GetEmployee() {
		logger.info("DEBUG: Junit TESTING: test4_GetEmployee ...");
		Collection<Employee> employee = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employee>>(){});
		assertTrue("Fail to read Employee!", employee.size() > 0);
		employee.stream().forEach(System.out::println);
	}
	
	@Test
	public void test2_DeleteProject() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test2_DeleteEmployee ...");
		Client client = ClientBuilder.newClient();
		Collection<Employee> employee = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employee>>(){});		
		
		for (Employee e: employee) {
			client.target(resourceURL + e.getId()).request().delete();
		}
		
		Collection<Employee> employeeAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employee>>(){});	
		assertTrue("Fail to read Employee!", employeeAfterDelete.size() == 0);
	}
	
}
