package ftn.pharmacyX.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ftn.pharmacyX.model.users.Patient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "appointment_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private LocalDateTime dateTime;
	@ManyToOne(fetch = FetchType.LAZY)
	private Pharmacy pharmacy;
	@ManyToOne(fetch = FetchType.LAZY)
	private Patient patient;
	@Column
	private String therapyDescription;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Drug> therapyDrugs;
	@Column
	private double price;
	@Column
	private boolean deleted = false;
	
	public Appointment() {
		
	}

	public Appointment(Long id, LocalDateTime dateTime, Pharmacy pharmacy, Patient patient, String therapyDescription,
			List<Drug> therapyDrugs, double price, boolean deleted) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.pharmacy = pharmacy;
		this.patient = patient;
		this.therapyDescription = therapyDescription;
		this.therapyDrugs = therapyDrugs;
		this.price = price;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getTherapyDescription() {
		return therapyDescription;
	}

	public void setTherapyDescription(String therapyDescription) {
		this.therapyDescription = therapyDescription;
	}

	public List<Drug> getTherapyDrugs() {
		return therapyDrugs;
	}

	public void setTherapyDrugs(List<Drug> therapyDrugs) {
		this.therapyDrugs = therapyDrugs;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
