package org.app.service.entities;
import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="benefit")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Benefit implements Serializable {

	@Id 
	//@GeneratedValue
	private Integer benefitId;
	private Double amount;
	private String description;
	
	@ManyToOne
	private Employee employee;

	@XmlElement
	public Integer getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(Integer benefitId) {
		this.benefitId = benefitId;
	}

	@XmlElement
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Benefit(Integer benefitId, Double amount, String description) {
		super();
		this.benefitId = benefitId;
		this.amount = amount;
		this.description = description;
	}

	public Benefit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static String BASE_URL = "http://localhost:8080/SCRUM/data/benefit/";

	@XmlElement(name="link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL+ this.getBenefitId();
		return new AtomLink (restUrl, "get-benefit");
	}
}
