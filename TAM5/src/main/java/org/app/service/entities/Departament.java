package org.app.service.entities;
import static javax.persistence.CascadeType.ALL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="departament") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Departament implements Serializable{
	@Id 
//	@GeneratedValue
	private Integer departamentId;
	private String name;
	
	@OneToMany(mappedBy="departament" ,cascade=ALL, fetch=FetchType.EAGER, orphanRemoval = false)
	private List<Employee> employee = new ArrayList<>();
	
	@XmlElement
	public Integer getDepartamentId() {
		return departamentId;
	}
	
	public void setDepartamentId(Integer departamentId) {
		this.departamentId = departamentId;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElementWrapper(name = "departamente") @XmlElement(name = "departament")
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public Departament(Integer departamentId, String name) {
		super();
		this.departamentId = departamentId;
		this.name = name;
	}
	public Departament() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static String BASE_URL = "http://localhost:8080/SCRUM/data/departamente/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getDepartamentId();
        return new AtomLink(restUrl, "get-departament");
    }	
}
