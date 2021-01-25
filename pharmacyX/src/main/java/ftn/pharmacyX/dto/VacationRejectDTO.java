package ftn.pharmacyX.dto;

public class VacationRejectDTO {

	private Long vacationId;
	private String rejectDescription;
	
	public VacationRejectDTO() {
		
	}

	public VacationRejectDTO(Long vacationId, String rejectDescription) {
		super();
		this.vacationId = vacationId;
		this.rejectDescription = rejectDescription;
	}

	public Long getVacationId() {
		return vacationId;
	}

	public void setVacationId(Long vacationId) {
		this.vacationId = vacationId;
	}

	public String getRejectDescription() {
		return rejectDescription;
	}

	public void setRejectDescription(String rejectDescription) {
		this.rejectDescription = rejectDescription;
	}
	
	
}
