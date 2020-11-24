package ftn.pharmacyX.model;

import java.time.LocalDate;

public class Promotion {

	private long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	private boolean deleted = false;
	
	public Promotion() {
		
	}
}
