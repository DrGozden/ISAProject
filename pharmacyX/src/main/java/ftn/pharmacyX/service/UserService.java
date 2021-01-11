package ftn.pharmacyX.service;

import ftn.pharmacyX.dto.EditPatientDTO;
import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;

public interface UserService {
	

	public User saveUser(UserDTO userDTO);
	
	public User findByUuid(String uuid); 
	
	public User findByEmail(String email);
	
	public User getLoggedUser();
	
	public Patient editPatient(EditPatientDTO editedPatient);

	public User editEmployee(User editEmployee);

}
