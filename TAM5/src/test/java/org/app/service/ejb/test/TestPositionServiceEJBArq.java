package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;

import org.app.service.ejb.PositionService;
import org.app.service.ejb.PositionServiceEJB;
import org.app.service.entities.Position;
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
public class TestPositionServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestPositionServiceEJBArq.class.getName());
	
	//Arquilian infrastructure
	@EJB
	private static PositionService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class,"msd-test.war")
				.addPackage(Position.class.getPackage())
				.addClass(PositionService.class)
				.addClass(PositionServiceEJB.class)
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
	public void test4_GetPositions() {
		logger.info("DEBUG: Junit TESTING: test4_GetPositions ...");
		
		Collection<Position> positions = service.getPosition();
		assertTrue("Fail to add positions!", positions.size() > 0);
	}
	
	@Test
	public void test3_AddPosition() {
		logger.info("DEBUG: Junit TESTING: test3_AddPosition ...");
		
		Integer positionsToAdd = 4;
		for(int i = 0; i<positionsToAdd; i++) {
			service.addPosition(new Position(i,"Position_"+i));
		}
		Collection<Position> positions = service.getPosition();
		assertTrue("Fail to add positions!", positions.size() == positionsToAdd);
		}
	
//	@Test
//	public void test2_DeletePosition() {
//		logger.info("DEBUG: Junit TESTING: test2_DeletePosition ...");
//		
//		Collection<Position> evaluations = service.getEvaluation();
//		for(Evaluation e: evaluations) {
//			service.removeEvaluation(e);
//			Collection<Evaluation> evaluationsAfterDelete = service.getEvaluation();
//			assertTrue("Fail to read evaluations!", evaluationsAfterDelete.size()==0);
//		}
}
