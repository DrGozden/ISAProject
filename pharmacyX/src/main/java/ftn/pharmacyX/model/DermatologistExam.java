package ftn.pharmacyX.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ftn.pharmacyX.model.users.Dermatologist;


@Entity
@DiscriminatorValue("dermatologist_appointment")
public class DermatologistExam extends Appointment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Dermatologist dermatologist;
	@Column
	private String diagnosis;
	
	
	public DermatologistExam() {
		
	}


	public DermatologistExam(Dermatologist dermatologist, String diagnosis) {
		super();
		this.dermatologist = dermatologist;
		this.diagnosis = diagnosis;
	}


	public Dermatologist getDermatologist() {
		return dermatologist;
	}


	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}


	public String getDiagnosis() {
		return diagnosis;
	}


	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	
	
	
}
