package org.app.service.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.DepartamentService;
import org.app.service.entities.Departament;
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

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDepartamentServiceRestArq {
private static Logger logger = Logger.getLogger(TestDepartamentServiceRestArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/msd-s4-test/data/departamente";
	
	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-s4-test.war")
	                .addPackage(Departament.class.getPackage())
	                .addPackage(DepartamentService.class.getPackage())
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
	public void test4_GetDepartament() {
		logger.info("DEBUG: Junit TESTING: test4_GetEmployee ...");
		Collection<Departament> departament = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Departament>>(){});
		assertTrue("Fail to read Departament!", departament.size() > 0);
		departament.stream().forEach(System.out::println);
	}
	

	@Test
	public void test2_DeleteProject() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test2_DeleteDepartament ...");
		Client client = ClientBuilder.newClient();
		Collection<Departament> departament = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Departament>>(){});		
		
		for (Departament e: departament) {
			client.target(resourceURL + e.getDepartamentId()).request().delete();
		}
		
		Collection<Departament> departamentAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Departament>>(){});	
		assertTrue("Fail to read Departament!", departamentAfterDelete.size() == 0);
	}
}
