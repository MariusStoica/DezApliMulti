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

import org.app.service.entities.Position;

@Path("positions")
@Stateless
@LocalBean
public class PositionServiceEJB implements PositionService {
	
	//@SuppressWarnings("deprecation")
	public static Logger logger = Logger.getLogger(PositionServiceEJB.class.getName());
	
	// 2. Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

	public PositionServiceEJB() {
	}
	
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT: " + this.em);
	}

	
	//CRUD Operation Implementation
	
	//CREATE OR UPDATE
	@Override
	public Position addPosition(Position positionToAdd) {
		em.persist(positionToAdd);
		em.flush();
		em.refresh(positionToAdd);
		return positionToAdd;
	}
	
	//REMOVE
	@Override
	public String deletePosition(Position positionToDelete) {
		positionToDelete = em.merge(positionToDelete);
		em.remove(positionToDelete);
		em.flush();
		return "True";
	}
	
	//READ
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Position getPositionByPositionID(@PathParam("id")Integer positionID) {
		logger.info("**** DEBUG REST getFeatureByID(): id = " + positionID);
		return em.find(Position.class, positionID);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Collection<Position> getPosition() {
		List <Position> positions = em.createQuery("SELECT p FROM Position p", Position.class).getResultList();
		return positions;
	}
	
	//CUSTOM Read: custom query
	@Override
	public Position getPositionByName(String positionName) {
		return em.createQuery("SELECT p FROM Position p WHERE p.name = :name", Position.class)
				.setParameter("name", positionName).getSingleResult();
	}
	
	//OTHERS
	@GET @Path ("/test")
	@Produces ({MediaType.TEXT_PLAIN})
	@Override
	public String sayRest() {
		return "Position Service is on...";
	}
}
