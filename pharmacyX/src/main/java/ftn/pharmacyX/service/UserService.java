package ftn.pharmacyX.service;

import java.util.List;
import java.util.Map;

import ftn.pharmacyX.dto.EditPatientDTO;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.users.User;

public interface UserService {
	

	public User saveUser(User user);
	
	public User findByUuid(String uuid); 
	
	public User findByEmail(String email);
	
	public User getLoggedUser();

	public UserDTO editUser(UserDTO editUser);
	
	public List<User> searchDermatologistsAndPharmacists(Map<String, String> searchParams, List<User> users);
	
	public List<User> findAllPharmacists();
	
	public List<User> findAllDermatologists();
	
	public List<User> findAllDermatologistsForPharmacy(Pharmacy pharmacy);
	public User addDrugReservation(DrugReservation drugReservation);

}
