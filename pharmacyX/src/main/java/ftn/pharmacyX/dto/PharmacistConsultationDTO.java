package ftn.pharmacyX.dto;

import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.PharmacistConsultation;

import java.time.LocalDateTime;
import java.util.List;

public class PharmacistConsultationDTO {
	private Long id;
	private LocalDateTime dateTime;
	private Long pharmacyId;
	private Long patientId;
	private String therapyDescription;
	private List<Drug> therapyDrugs;
	private Long pharmacistId;
	private String pharmacistOpinion;
	private double price;
	private boolean deleted = false;
	private String pharmacistName;
	private String pharmacyName;

	public PharmacistConsultationDTO() {
	}

	public PharmacistConsultationDTO(Long id, LocalDateTime dateTime, Long pharmacyId, Long patientId, String therapyDescription, List<Drug> therapyDrugs, Long pharmacistId, String pharmacistOpinion, double price, boolean deleted) {
		this.id = id;
		this.dateTime = dateTime;
		this.pharmacyId = pharmacyId;
		this.patientId = patientId;
		this.therapyDescription = therapyDescription;
		this.therapyDrugs = therapyDrugs;
		this.pharmacistId = pharmacistId;
		this.pharmacistOpinion = pharmacistOpinion;
		this.price = price;
		this.deleted = deleted;
	}

	public PharmacistConsultationDTO(PharmacistConsultation consultation){
		this.id = consultation.getId();
		this.dateTime = consultation.getDateTime();
		this.pharmacyId = consultation.getPharmacy().getId();
		if(consultation.getPatient() == null){
			this.patientId = null;
		}
		else{
			this.patientId = consultation.getPatient().getId();
		}
		this.therapyDescription = consultation.getTherapyDescription();
		this.therapyDrugs = consultation.getTherapyDrugs();
		this.pharmacistId = consultation.getPharmacist().getId();
		this.pharmacistOpinion = consultation.getPharmacistOpinion();
		this.price = consultation.getPrice();
		this.deleted = consultation.isDeleted();
		this.pharmacistName = consultation.getPharmacist().getFirstName() + " " + consultation.getPharmacist().getLastName();
		this.pharmacyName = consultation.getPharmacy().getName();
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

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public Long getPharmacistId() {
		return pharmacistId;
	}

	public void setPharmacistId(Long pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	public String getPharmacistOpinion() {
		return pharmacistOpinion;
	}

	public void setPharmacistOpinion(String pharmacistOpinion) {
		this.pharmacistOpinion = pharmacistOpinion;
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

	public String getPharmacistName() {
		return pharmacistName;
	}

	public void setPharmacistName(String pharmacistName) {
		this.pharmacistName = pharmacistName;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
}