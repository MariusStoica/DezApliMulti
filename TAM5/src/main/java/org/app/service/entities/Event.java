package org.app.service.entities;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.*;
import java.util.*;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Event {
	@Id @GeneratedValue
	private Integer eventID;
	
	private String eventName;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
}
