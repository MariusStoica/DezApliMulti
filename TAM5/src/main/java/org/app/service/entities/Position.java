package org.app.service.entities;
import static javax.persistence.CascadeType.ALL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="position")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Position implements Serializable {
	
	@Id
	//@GeneratedValue
	private Integer positionId;
	
	private String name;
	
	@OneToMany(mappedBy="position", cascade=ALL, fetch=FetchType.EAGER, orphanRemoval = false)
	List<Employee> employee = new ArrayList<Employee>();
	
	@XmlElement
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
	@XmlElement		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElementWrapper(name="positions")
	@XmlElement(name="position")
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public Position(Integer positionId, String name) {
		super();
		this.positionId = positionId;
		this.name = name;
	}
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static String BASE_URL = "http://localhost:8080/SCRUM/data/position/";
	
	@XmlElement (name= "link" )
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getPositionId();
		return new AtomLink(restUrl, "get-position");
	}
}
