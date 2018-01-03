package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.EJB;

import org.app.service.ejb.BenefitService;
import org.app.service.ejb.BenefitServiceEJB;
import org.app.service.entities.Benefit;
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
public class TestBenefitServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestBenefitServiceEJBArq.class.getName());
	
	//Arquilian infrastructure
	@EJB
	private static BenefitService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "msd-test.war")
				.addPackage(Benefit.class.getPackage())
				.addClass(BenefitService.class)
				.addClass(BenefitServiceEJB.class)
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
	public void test4_GetBenefits() {
		logger.info("DEBUG: Junit TESTING: test2_GetBenefit ...");
		
		Collection<Benefit> benefits = service.getBenefit();
		assertTrue("Fail to add benefits!", benefits.size() > 0);
	}
	
	@Test
	public void test3_AddBenefit() {
		logger.info("DEBUG: Junit TESTING: test3_AddBenefit ...");
		
		Integer benefitsToAdd = 4;
		for (int i=1; i <=benefitsToAdd; i++) {
			service.addBenefit(new Benefit(100+i, 2.00+i, "Benefit_"+(100+i)));
		}
		Collection<Benefit> benefits = service.getBenefit();
		assertTrue("Fail to add benefits!", benefits.size() == benefitsToAdd);
	}
	
	@Test
	public void test2_DeleteBenefit() {
		logger.info("DEBUG: Junit TESTING: test4_DeleteBenefit ...");
		
		Collection<Benefit> benefits = service.getBenefit();
		for (Benefit b: benefits) {
			service.removeBenefit(b);
			Collection<Benefit> benefitsAfterDelete = service.getBenefit();
			assertTrue("Fail to read benefits!", benefitsAfterDelete.size()==0);
		}
	}
}
