package ftn.pharmacyX.dto;

public class AddDrugDTO {

	private Long drugId;
	private int quantity;
	
	public AddDrugDTO() {
		
	}

	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
