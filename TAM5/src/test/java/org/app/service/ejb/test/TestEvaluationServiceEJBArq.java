package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;

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
public class TestEvaluationServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestEvaluationServiceEJBArq.class.getName());
	
	//Arquilian infrastructure
	@EJB
	private static EvaluationService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Evaluation.class.getPackage())
				.addClass(EvaluationService.class)
				.addClass(EvaluationServiceEJB.class)
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
	public void test4_GetEvalution() {
		logger.info("DEBUG: Junit TESTING: test4_GetDepartaments ...");
		
		Collection<Evaluation> evaluations = service.getEvaluation();
		assertTrue("Fail to add evaluations!", evaluations.size() > 0);
	}
	
	@Test
	public void test3_AddEvaluation() {
		logger.info("DEBUG: Junit TESTING: test3_AddEvaluation ...");
		
		Integer evaluationsToAdd = 4;
		for(int i = 0; i<evaluationsToAdd; i++) {
			service.addEvaluation(new Evaluation(i, "Evaluation_"+(i), new Date("2017-07-26"), "commentForEvaluation_"+i, "type2", 8.00+i));
		}
		Collection<Evaluation> evaluations = service.getEvaluation();
		assertTrue("Fail to add evaluations!", evaluations.size() == evaluationsToAdd);
		}
	
	@Test
	public void test2_DeleteEvaluation() {
		logger.info("DEBUG: Junit TESTING: test2_DeleteEvaluation ...");
		
		Collection<Evaluation> evaluations = service.getEvaluation();
		for(Evaluation e: evaluations) {
			service.removeEvaluation(e);
			Collection<Evaluation> evaluationsAfterDelete = service.getEvaluation();
			assertTrue("Fail to read evaluations!", evaluationsAfterDelete.size()==0);
		}
	}
}
