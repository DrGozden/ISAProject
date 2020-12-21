package ftn.pharmacyX.dto;

import java.util.List;

public class EditPatientDTO {

	private String firstName;
	private String lastName;
	private String password;
	private String phone;
	private String street;
	private String city;
	private String country;
	private String postalCode;
	private List<Long> allergies;
	
	public EditPatientDTO() {
		
	}

	public EditPatientDTO(String firstName, String lastName, String password, String phone, String street, String city,
			String country, String postalCode, List<Long> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
		this.allergies = allergies;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Long> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Long> allergies) {
		this.allergies = allergies;
	}
	
	
	
	
}
