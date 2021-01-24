package ftn.pharmacyX.dto;

import ftn.pharmacyX.model.DrugReservation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DrugReservationDTO {
	
	private Long drugId;
	private Long pharmacyId;
	private String deadlineDateTime;
	private LocalDateTime dateTimeDeadline;
	private String pharmacyName;
	private String drugName;
	
	public DrugReservationDTO() {
		
	}

	public DrugReservationDTO(Long drugId, Long pharmacyId, String deadlineDateTime) {
		super();
		this.drugId = drugId;
		this.pharmacyId = pharmacyId;
		this.deadlineDateTime = deadlineDateTime;
	}

    public DrugReservationDTO(DrugReservation drugReservation) {
		this.drugId = drugReservation.getId();
		this.pharmacyName = drugReservation.getPharmacy().getName();
		this.drugName = drugReservation.getDrug().getName();
		this.dateTimeDeadline = drugReservation.getDeadline();
    }

    public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getDeadlineDateTime() {
		return deadlineDateTime;
	}

	public void setDeadlineDateTime(String deadlineDateTime) {
		this.deadlineDateTime = deadlineDateTime;
	}

	public LocalDateTime getDateTimeDeadline() {
		return dateTimeDeadline;
	}

	public void setDateTimeDeadline(LocalDateTime dateTimeDeadline) {
		this.dateTimeDeadline = dateTimeDeadline;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
}
