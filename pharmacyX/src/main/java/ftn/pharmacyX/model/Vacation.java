package ftn.pharmacyX.model;

import java.time.LocalDate;

import ftn.pharmacyX.model.users.User;

public class Vacation {

	private long id;
	private User user;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean deleted = false;
	
	public Vacation() {
		
	}
}
