package ftn.pharmacyX.dto;

public class CreateExamDTO {

	private String dateStart;
	private double price;
	private Long dermatologistId;
	private Long pharmacyId;
	
	public CreateExamDTO() {
		
	}

	public CreateExamDTO(String dateStart, double price, Long dermatologistId, Long pharmacyId) {
		super();
		this.dateStart = dateStart;
		this.price = price;
		this.dermatologistId = dermatologistId;
		this.pharmacyId = pharmacyId;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(Long dermatologistId) {
		this.dermatologistId = dermatologistId;
	}
	
	
	
	
}
