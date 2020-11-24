package ftn.pharmacyX.model;

import java.time.LocalDateTime;

import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;

public class Complaint {

	private long id;
	private String description;
	private Patient complainant;
	private User complainee;
	private Pharmacy pharmacy;
	private LocalDateTime dateOfComplaint;
	private boolean deleted = false;
	
	public Complaint() {
		
	}
	
	
}

