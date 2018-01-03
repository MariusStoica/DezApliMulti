package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.*;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Benefit;

@Path("benefits")
@Stateless
@LocalBean
public class BenefitServiceEJB implements BenefitService {
	
	//	@SuppressWarnings("deprecation")
	public static Logger logger = Logger.getLogger(BenefitServiceEJB.class.getName());
	
	// 2. Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

	public BenefitServiceEJB() {
		
	}
	
	@PostConstruct 
	public void init() {
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
		
	}
	
	//CRUD Operation Implementation
	
	//CREATE OR UPDATE
	@Override
	public Benefit addBenefit(Benefit benefitToAdd) {
		em.persist(benefitToAdd);
		em.flush();
		em.refresh(benefitToAdd);
		return benefitToAdd;
	}
	
	//REMOVE
	@Override
	public String removeBenefit(Benefit benefitToDelete) {
		benefitToDelete = em.merge(benefitToDelete);
		em.remove(benefitToDelete);
		em.flush();
		return "True";
	}
	
	//READ
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Override
	public Benefit getBenefitByBenefitID(@PathParam("id")Integer benefitID) {
		logger.info("**** DEBUG REST getFeatureByID(): id = " + benefitID);
		return em.find(Benefit.class, benefitID);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Collection<Benefit> getBenefit() {
		List <Benefit> benefit = em.createQuery("SELECT b FROM Benefit b", Benefit.class).getResultList();
		return benefit;
	}
	
	//CUSTOM READ: CUSTOM QUERY
	@Override
	public Benefit getBenefitByName(String benefitName) {
		return em.createQuery("SELECT b FROM Benefits b WHERE b.description = :description", Benefit.class)
				.setParameter("description", benefitName).getSingleResult();
	}
	
	//Others
	@GET @Path("/test") // Check if resource is up ...
	@Produces({ MediaType.TEXT_PLAIN})
	@Override
	public String sayRest() {
		return "Benefit Service is on...";
	}
}

		
