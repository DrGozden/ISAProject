package ftn.pharmacyX.service;

import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.model.users.User;

public interface UserService {
	

	public User saveUser(UserDTO userDTO);
	
	public User findByuuid(String uuid); 
	
	public User findByEmail(String email);
	
	public User getLoggedUser();

}
