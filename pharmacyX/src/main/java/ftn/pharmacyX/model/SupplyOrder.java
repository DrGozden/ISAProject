package ftn.pharmacyX.model;

import java.time.LocalDateTime;
import java.util.Map;

public class SupplyOrder {

	private long id;
	private Map<Drug, Integer> supplies;
	private LocalDateTime deadline;
	private Pharmacy pharmacy;
	private boolean deleted = false;
	
	public SupplyOrder() {
		
	}
	
	
}
