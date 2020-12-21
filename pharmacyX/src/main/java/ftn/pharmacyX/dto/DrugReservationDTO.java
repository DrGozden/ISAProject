package ftn.pharmacyX.dto;

public class DrugReservationDTO {
	
	private Long drugId;
	private Long pharmacyId;
	private String deadlineDateTime;
	
	public DrugReservationDTO() {
		
	}

	public DrugReservationDTO(Long drugId, Long pharmacyId, String deadlineDateTime) {
		super();
		this.drugId = drugId;
		this.pharmacyId = pharmacyId;
		this.deadlineDateTime = deadlineDateTime;
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
	


}
