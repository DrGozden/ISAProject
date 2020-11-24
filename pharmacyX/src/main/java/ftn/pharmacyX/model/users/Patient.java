package ftn.pharmacyX.model.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.Appointment;
import ftn.pharmacyX.model.Drug;

@Entity
@DiscriminatorValue("patient_user")
public class Patient extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@OneToMany
	private List<Appointment> appointmentHistory;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Drug> drugHistory;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Drug> allergies;
	
	public Patient() {
		
	}

	public Patient(List<Appointment> appointmentHistory, List<Drug> drugHistory, List<Drug> allergies) {
		super();
		this.appointmentHistory = appointmentHistory;
		this.drugHistory = drugHistory;
		this.allergies = allergies;
	}

	public Patient(Long id, String firstName, String lastName, String email, String password, String phone,
			Address address, boolean deleted) {
		super(id, firstName, lastName, email, password, phone, address, deleted);
		// TODO Auto-generated constructor stub
	}

	public List<Appointment> getAppointmentHistory() {
		return appointmentHistory;
	}

	public void setAppointmentHistory(List<Appointment> appointmentHistory) {
		this.appointmentHistory = appointmentHistory;
	}

	public List<Drug> getDrugHistory() {
		return drugHistory;
	}

	public void setDrugHistory(List<Drug> drugHistory) {
		this.drugHistory = drugHistory;
	}

	public List<Drug> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Drug> allergies) {
		this.allergies = allergies;
	}
	
	
	
	
	
}
