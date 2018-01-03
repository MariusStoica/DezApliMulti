package org.app.service.entities;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="evaluation")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Evaluation implements Serializable {

	@Id 
	//@GeneratedValue
	private Integer evaluationid;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date evaluationdate;
	
	private String evaluationcomment;
	private String evaluationtype;
	private Double evaluationnote;
	
	@ManyToOne
	private Employee employee;

	@XmlElement
	public Integer getEvaluationid() {
		return evaluationid;
	}

	public void setEvaluationid(Integer evaluationid) {
		this.evaluationid = evaluationid;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Date getEvaluationdate() {
		return evaluationdate;
	}

	public void setEvaluationdate(Date evaluationdate) {
		this.evaluationdate = evaluationdate;
	}

	@XmlElement
	public String getEvaluationcomment() {
		return evaluationcomment;
	}

	public void setEvaluationcomment(String evaluationcomment) {
		this.evaluationcomment = evaluationcomment;
	}

	@XmlElement
	public String getEvaluationtype() {
		return evaluationtype;
	}

	public void setEvaluationtype(String evaluationtype) {
		this.evaluationtype = evaluationtype;
	}

	@XmlElement
	public Double getEvaluationnote() {
		return evaluationnote;
	}

	public void setEvaluationnote(Double evaluationnote) {
		this.evaluationnote = evaluationnote;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Evaluation(Integer evaluationid, String name, Date evaluationdate, String evaluationcomment,
			String evaluationtype, Double evaluationnote) {
		super();
		this.evaluationid = evaluationid;
		this.name = name;
		this.evaluationdate = evaluationdate;
		this.evaluationcomment = evaluationcomment;
		this.evaluationtype = evaluationtype;
		this.evaluationnote = evaluationnote;
	}

	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static String BASE_URL = "http://localhost:8080/SCRUM/data/evaluation/";
	
	@XmlElement(name= "link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getEvaluationid();
		return new AtomLink(restUrl, "get-evaluation");
	}
}

