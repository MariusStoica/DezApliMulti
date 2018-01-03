package org.app.service.ejb;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.*;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Departament;
import org.app.service.entities.Employee;

@Path("departamente")
@Stateless 
@LocalBean
public class DepartamentServiceEJB extends EntityRepositoryBase<Departament> implements DepartamentService {
	
	//	@SuppressWarnings("deprecation")
	public static Logger logger = Logger.getLogger(DepartamentServiceEJB.class.getName());
	
	 //2. Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public DepartamentServiceEJB() {	
	}
	
	@EJB // injected DataService
	private DepartamentService departamentService; 
	
	// Local component-entity-repository
	private EntityRepository<Departament> departamentRepository;
	
	@PostConstruct 
	public void init() {
		departamentRepository = new EntityRepositoryBase<Departament>(this.em,Departament.class);
		logger.info("POSTCONSTRUCT-INIT releaseRepository: " + this.departamentRepository);
		logger.info("POSTCONSTRUCT-INIT featureDataService: " + this.departamentService);
}
//	
//	//CRUD Operation Implementation
//
//	//CREATE OR UPDATE
//	
//	@Override
//	public Departament addDepartament(Departament departamentToAdd) {
//		em.persist(departamentToAdd);
//		em.flush();
//		em.refresh(departamentToAdd);
//		return departamentToAdd;
//	}
//	
//	//REMOVE
//	@Override
//	public String removeDepartament(Departament departamanetToDelete) {
//		departamanetToDelete = em.merge(departamanetToDelete);
//		em.remove(departamanetToDelete);
//		em.flush();
//		return "True";
//	}
//	
//	//READ
//	@GET @Path("/{id}")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//	@Override
//	public Departament getDepartamentByDepartamentID(@PathParam("id")Integer departamentID) {
//		logger.info("**** DEBUG REST getFeatureByID(): id = " + departamentID);
//		return em.find(Departament.class, departamentID);
//	}
//	
//	@GET
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Override
//	public Collection<Departament> getDepartaments() {
//		List<Departament>departaments=em.createQuery("SELECT d FROM Departament d", Departament.class).getResultList();
//		return departaments;
//	}
//	
//	//CUSTOM READ: CUSTOM QUERY
//	@Override
//	public Departament getDepartamentByName(String departamentName) {
//		return em.createQuery("SELECT d FROM Departament d WHERE d.name= :name", Departament.class)
//				.setParameter("name", departamentName).getSingleResult();
//	}
//	
	@GET @Path("/test") // Check if resource is up ...
	@Produces({ MediaType.TEXT_PLAIN})
	@Override
	public String sayRest() {
		return "Departament Service is on...";
	}
	
	@Override
	@GET 					/* MSD-S4/data/projects 		REST-resource: projects-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Departament> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}
	
	@GET @Path("/{id}") 	/* MSD-S4/data/projects/data/{id} 	REST-resource: project-entity*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Departament getById(@PathParam("id") Integer id){ 
		Departament departament = super.getById(id);
		logger.info("**** DEBUG REST getById(" + id +") = " + departament);
		return departament;
	}
	
//	@Override
	@DELETE 				/* MSD-S4/data/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Collection<Departament> removeFromCollection(Departament departament) {
		logger.info("DEBUG: called REMOVE - project: " + departament);
		super.remove(departament);
		return super.toCollection();
	}
	
	@DELETE @Path("/{id}") 	/* MSD-S4/data/projects/{id} 	REST-resource: project-entity*/	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public void remove(@PathParam("id")Integer id) {
		logger.info("DEBUG: called REMOVE - ById() for departament >>>>>>>>>>>>>> simplified ! + id");
		Departament departament = super.getById(id);
		super.remove(departament);
	}
	@POST 					/* MSD-S4/data/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Departament> addIntoCollection(Departament departament) {
		// save aggregate
		super.add(departament);
		logger.info("**** DEBUG REST save aggregate POST");
		// return updated collection
		return super.toCollection();
	}
}
