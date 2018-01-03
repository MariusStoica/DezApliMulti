package org.app.service.entities;
import static javax.persistence.CascadeType.ALL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TypeBenefit extends Benefit {
	
	private Double mealTickets;
	private String medicalLeave;
	private String healthInsurance;
	private Integer paidHolidays;
	
	public Double getMealTickets() {
		return mealTickets;
	}
	public void setMealTickets(Double mealTickets) {
		this.mealTickets = mealTickets;
	}
	public String getMedicalLeave() {
		return medicalLeave;
	}
	public void setMedicalLeave(String medicalLeave) {
		this.medicalLeave = medicalLeave;
	}
	public String getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public Integer getPaidHolidays() {
		return paidHolidays;
	}
	public void setPaidHolidays(Integer paidHolidays) {
		this.paidHolidays = paidHolidays;
	}
	public TypeBenefit(Integer benefitId, Double amount, String description, Double mealTickets, String medicalLeave,
			String healthInsurance, Integer paidHolidays) {
		super(benefitId, amount, description);
		this.mealTickets = mealTickets;
		this.medicalLeave = medicalLeave;
		this.healthInsurance = healthInsurance;
		this.paidHolidays = paidHolidays;
	}
	public TypeBenefit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TypeBenefit(Integer benefitId, Double amount, String description) {
		super(benefitId, amount, description);
		// TODO Auto-generated constructor stub
	}
	
	
}
