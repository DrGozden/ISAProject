package ftn.pharmacyX.dto;

import java.util.HashMap;
import java.util.Map;

public class SupplyOrderDTO {

	private Map<Long, Integer> supplies = new HashMap<Long, Integer>();
	private String deadline;
	private Long pharmacyId;
	
	public SupplyOrderDTO() {
		
	}

	public SupplyOrderDTO(Map<Long, Integer> supplies, String deadline, Long pharmacyId) {
		super();
		this.supplies = supplies;
		this.deadline = deadline;
		this.pharmacyId = pharmacyId;
	}

	public Map<Long, Integer> getSupplies() {
		return supplies;
	}

	public void setSupplies(Map<Long, Integer> supplies) {
		this.supplies = supplies;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
	
	
}
