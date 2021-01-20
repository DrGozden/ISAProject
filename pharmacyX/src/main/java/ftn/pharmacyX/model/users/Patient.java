package ftn.pharmacyX.model.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.Appointment;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.DrugReservation;

@Entity
@DiscriminatorValue("patient_user")
public class Patient extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany
	@JoinTable(joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "appointment_id"))
	private List<Appointment> appointmentHistory;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "drug_id"))
	private List<Drug> drugHistory;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "drug_id"))
	private List<Drug> allergies;
	@OneToMany
	@JoinTable(joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "drug_reservation_id"))
	private List<DrugReservation> drugReservations;

	public Patient() {

	}

	public Patient(List<Appointment> appointmentHistory, List<Drug> drugHistory, List<Drug> allergies, List<DrugReservation> drugReservations) {
		super();
		this.appointmentHistory = appointmentHistory;
		this.drugHistory = drugHistory;
		this.allergies = allergies;
		this.drugReservations = drugReservations;
	}

	public Patient(Long id, String firstName, String lastName, String email, String password, String phone,
			Address address, boolean deleted, UserRole userRole, String uuid) {
		super(id, firstName, lastName, email, password, phone, address, deleted, userRole, uuid);
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
	
	public void addToAppointmentHistory(Appointment appointment) {
		this.appointmentHistory.add(appointment);
	}
	
	public void addAllergy(Drug drug) {
		this.allergies.add(drug);
	}
	
	public void addToDrugHistory(Drug drug) {
		this.allergies.add(drug);
	}

	public List<DrugReservation> getDrugReservations() {
		return drugReservations;
	}

	public void setDrugReservations(List<DrugReservation> drugReservations) {
		this.drugReservations = drugReservations;
	}
	
	

}
