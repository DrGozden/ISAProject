package ftn.pharmacyX.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ftn.pharmacyX.model.users.Pharmacist;

@Entity
@DiscriminatorValue("pharmacist_appointment")
public class PharmacistConsultation extends Appointment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pharmacist pharmacist;
	@Column
	private String pharmacistOpinion;
	
	
	public PharmacistConsultation() {
		
	}


	public PharmacistConsultation(Pharmacist pharmacist, String pharmacistOpinion) {
		super();
		this.pharmacist = pharmacist;
		this.pharmacistOpinion = pharmacistOpinion;
	}


	public Pharmacist getPharmacist() {
		return pharmacist;
	}


	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}


	public String getPharmacistOpinion() {
		return pharmacistOpinion;
	}


	public void setPharmacistOpinion(String pharmacistOpinion) {
		this.pharmacistOpinion = pharmacistOpinion;
	}
	
	
}
