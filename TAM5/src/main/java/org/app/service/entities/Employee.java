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

@XmlRootElement(name="employee") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Employee implements Serializable {
	
	@Id 
	//@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String PNC;
	
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@Temporal(TemporalType.DATE)
	private Date hireDate;
	
	private String phone;
	private String email;
	
	@OneToMany(mappedBy="employee" ,cascade=ALL, fetch=FetchType.EAGER, orphanRemoval = false)
	private List<Benefit> benefits = new ArrayList<>();
	
	@ManyToOne()
	private Departament departament;
	
	@OneToMany(mappedBy= "employee", cascade=ALL, fetch=FetchType.LAZY, orphanRemoval = false)
	private List<Evaluation> evaluations = new ArrayList<>();
//	
	@ManyToOne()
	private Position position;
	
	@XmlElement
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getPNC() {
		return PNC;
	}
	public void setPNC(String pNC) {
		PNC = pNC;
	}
	@XmlElement
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	@XmlElement
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElementWrapper(name="employees")
	@XmlElement(name="employee")
	public List<Benefit> getBenefits() {
		return benefits;
	}
	public void setBenefits(List<Benefit> benefits) {
		this.benefits = benefits;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Employee(Integer id, String name, String pNC, Date birthdate, Date hireDate, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.PNC = pNC;
		this.birthdate = birthdate;
		this.hireDate = hireDate;
		this.phone = phone;
		this.email = email;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Integer id, String name, String pNC, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.PNC = pNC;
		this.phone = phone;
		this.email = email;
	}
	public static String BASE_URL = "http://localhost:8080/SCRUM/data/employees/";
	
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getId();
		return new AtomLink(restUrl, "get-employee");
		}
}
