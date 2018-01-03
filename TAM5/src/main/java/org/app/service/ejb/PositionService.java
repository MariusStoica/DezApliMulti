package org.app.service.ejb;

import java.util.Collection;
import javax.ejb.Remote;

import org.app.service.entities.Position;


@Remote
public interface PositionService {
	//CREATE or UPDATE
	Position addPosition(Position positionToAdd);
	
	//DELETE
	String deletePosition(Position positionToDelete);
	
	//READ
	Position getPositionByPositionID(Integer positionID);
	Collection<Position> getPosition();
	
	//Custom READ: custom query
	Position getPositionByName(String positionName);
	
	//Others
	String sayRest();
}
