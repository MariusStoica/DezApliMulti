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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Evaluation;

@Path("evaluations")
@Stateless
@LocalBean
public class EvaluationServiceEJB implements EvaluationService {

	//@SuppressWarnings("deprecation")
	public static Logger logger = Logger.getLogger(EvaluationServiceEJB.class.getName());
	
	// 2. Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

	public EvaluationServiceEJB() {
	}
	
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT: "+ this.em);
	}
	
	//CRUD Operation Implementation
	
	//CREATE OR UPDATE
	@Override
	public Evaluation addEvaluation(Evaluation evaluationToAdd) {
		em.persist(evaluationToAdd);
		em.flush();
		em.refresh(evaluationToAdd);
		return evaluationToAdd;
	}

	//REMOVE
	@Override
	public String removeEvaluation(Evaluation evaluationToDelete) {
		evaluationToDelete =em.merge(evaluationToDelete);
		em.remove(evaluationToDelete);
		em.flush();
		return "True";
	}

	//READ
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Evaluation getEvaluationByEvaluationID(Integer evaluationID) {
		return em.find(Evaluation.class, evaluationID);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Collection<Evaluation> getEvaluation() {
		List <Evaluation> evaluations = em.createQuery("SELECT e FROM Evaluation e", Evaluation.class).getResultList();
		return evaluations;
	}

	//CUSTOM READ: CUSTOM QUERY
	@Override
	public Evaluation getEvaluationByName(String evaluationName) {
		return em.createQuery("SELECT e FROM Evaluation WHERE e.name = :name", Evaluation.class)
				.setParameter("name", evaluationName).getSingleResult();
	}
	
	//Others
	@GET @Path("/test")
	@Produces({MediaType.TEXT_PLAIN})
	@Override
	public String sayRest() {
		return "Evaluation Service is on...";
	}
}
