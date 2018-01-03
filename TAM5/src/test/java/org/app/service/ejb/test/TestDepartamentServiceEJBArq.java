package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
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
public class TestDepartamentServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestDepartamentServiceEJBArq.class.getName());
	
	//Arquilian infrastructure
	@EJB
	private static DepartamentService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Departament.class.getPackage())
				.addClass(DepartamentService.class)
				.addClass(DepartamentServiceEJB.class)
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
	public void test4_GetDepartaments() {
		logger.info("DEBUG: Junit TESTING: test4_GetDepartaments ...");
		
		Collection<Departament> departaments = service.toCollection();
		assertTrue("Fail to add departaments!", departaments.size() > 0);
	}
	
	@Test
	public void test3_AddDepartament() {
		logger.info("DEBUG: Junit TESTING: test3_AddDepartament ...");
		
		Integer departamentsToAdd = 4;
		for(int i = 0; i<departamentsToAdd; i++) {
			service.add(new Departament(i, "Departament_"+(i)));
		}
		Collection<Departament> departaments = service.toCollection();
		assertTrue("Fail to add departaments!", departaments.size() == departamentsToAdd);
		}
	
	@Test
	public void test2_DeleteDepartament() {
		logger.info("DEBUG: Junit TESTING: test2_DeleteDepartament ...");
		
		Collection<Departament> departaments = service.toCollection();
		for(Departament d: departaments) {
			service.remove(d);
			Collection<Departament> departamentsAfterDelete = service.toCollection();
			assertTrue("Fail to read departaments!", departamentsAfterDelete.size()==0);
		}
	}
}
