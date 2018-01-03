package org.app.service.ejb;
import java.util.Collection;
import javax.ejb.Remote;
import org.app.service.entities.Evaluation;

@Remote
public interface EvaluationService {
	//CREATE or UPDATE
	Evaluation addEvaluation(Evaluation evaluationToAdd);
	
	//DELETE
	String removeEvaluation(Evaluation evaluationToDelete);
	
	//READ
	Evaluation getEvaluationByEvaluationID(Integer evaluationID);
	Collection<Evaluation> getEvaluation();
	
	//Custom READ: custom query
	Evaluation getEvaluationByName(String evaluationName);
	
	//Others
	String sayRest();
}
