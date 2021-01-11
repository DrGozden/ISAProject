package ftn.pharmacyX.dto;


import ftn.pharmacyX.model.Drug;

public class DrugsInStockDTO {

	private Drug drug;
	private int quantity;
	
	public DrugsInStockDTO() {
		
	}

	public DrugsInStockDTO(Drug drug, int quantity) {
		super();
		this.drug = drug;
		this.quantity = quantity;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
