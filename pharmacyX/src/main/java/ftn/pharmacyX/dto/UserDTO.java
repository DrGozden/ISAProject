package ftn.pharmacyX.dto;


import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.model.Address;
import ftn.pharmacyX.model.users.User;

public class UserDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private Address address;
	private boolean deleted = false;
	private UserRole userRole;
	private String JWTToken;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.deleted = user.isDeleted();
		this.userRole = user.getUserRole();
		
	}
	
	

	public UserDTO(Long id, String firstName, String lastName, String email, String password, String phone,
			Address address, boolean deleted, UserRole userRole, String jWTToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.deleted = deleted;
		this.userRole = userRole;
		JWTToken = jWTToken;
	}



	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getJWTToken() {
		return JWTToken;
	}

	public void setJWTToken(String jWTToken) {
		JWTToken = jWTToken;
	}
	
	

}
