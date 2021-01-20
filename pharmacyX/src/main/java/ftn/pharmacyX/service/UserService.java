package ftn.pharmacyX.service;

import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.users.User;

public interface UserService {
	

	public User saveUser(User user);
	
	public User findByUuid(String uuid); 
	
	public User findByEmail(String email);
	
	public User getLoggedUser();

	public UserDTO editUser(UserDTO editUser);
	
	public User addDrugReservation(DrugReservation drugReservation);

}
