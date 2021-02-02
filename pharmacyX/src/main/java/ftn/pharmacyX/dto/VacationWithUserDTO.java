package ftn.pharmacyX.dto;

import ftn.pharmacyX.model.Vacation;

public class VacationWithUserDTO {

	private Vacation vacation;
	private String firstName;
	private String lastName;
	private String email;
	
	public VacationWithUserDTO() {
		
	}

	public Vacation getVacation() {
		return vacation;
	}

	public void setVacation(Vacation vacation) {
		this.vacation = vacation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
