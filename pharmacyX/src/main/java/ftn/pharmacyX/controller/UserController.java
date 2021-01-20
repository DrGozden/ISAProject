package ftn.pharmacyX.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.enums.UserStatus;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.Appointment;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	PharmacyService pharmacyService;
	
	@Autowired
	private DTOConverter converter;
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
		Patient p = converter.userDtoToPatient(userDTO);
		User newUser = userService.saveUser(p);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/users/activate/{uuid}")
	public ResponseEntity<String> activateUser(@PathVariable("uuid") String uuid) {
		User user = userService.findByUuid(uuid);
		if (user != null) {
			if (user.getUserStatus() == UserStatus.PENDING) {
				user.setUserStatus(UserStatus.ACTIVATED);
				userService.saveUser(user);
				String msg = String.format("Succesfully activated! %s %s welcome to PharmacyX!", user.getFirstName(), user.getLastName());
				return new ResponseEntity<String>(msg, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("User already activated!", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>("Bad activation link!", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser() {
		User user = userService.getLoggedUser();
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		UserDTO userDTO = new UserDTO(user);
		if (user.getUserRole() == UserRole.PATIENT) {
			Patient p = (Patient) user;
			userDTO.setAllergies(p.getAllergies());
		}
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/me/history/exams", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getExamHistory() {
		User user = userService.getLoggedUser();
		Patient patient = (Patient) user;
		
		List<Appointment> appts = patient.getAppointmentHistory();
		
		return new ResponseEntity<>(appts, HttpStatus.OK);
	}
	
	@GetMapping(value = "/me/history/consultations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getConsultationHistory() {
		User user = userService.getLoggedUser();
		Patient patient = (Patient) user;
		
		List<Appointment> appts = patient.getAppointmentHistory();
		
		return new ResponseEntity<>(appts, HttpStatus.OK);
	}
	
	@PutMapping(value = "/me", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.editUser(userDTO), HttpStatus.OK);
	}
	
	/*
	@PutMapping(value = "/patient/edit")
	public ResponseEntity<Patient> editPatient(@RequestBody EditPatientDTO editedPatient) {
		return new ResponseEntity<>(userService.editPatient(editedPatient), HttpStatus.OK);
	}
	/*
	@PutMapping(value = "/emloyee/edit")
	public ResponseEntity<User> editEmployee(@RequestBody UserDTO editEmployee) {
		return new ResponseEntity<>(userService.editEmployee(editEmployee), HttpStatus.OK);
	}
	
	/*
	PRAVI PROBLEM ZBOG MAIL-A, VEROVATNO TREBA DA SE STAVI @REQUESTPARAM UMESTO @PATHVARIABLE
	@GetMapping(value = "/patient/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> getPatient(@PathVariable("email") String email) {
		Patient patient = (Patient) userService.findByEmail(email);
		if (patient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getEmployee(@PathVariable("email") String email) {
		UserDTO employee = new UserDTO(userService.findByEmail(email));
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	*/
	
	@GetMapping(value = "/pharmacists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllPharmacists(@RequestParam Map<String, String> queryParams) {
		List<User> users = userService.findAllPharmacists();
		List<UserDTO> ret = new ArrayList<UserDTO>();
		List<User> found = userService.searchDermatologistsAndPharmacists(queryParams, users);
		for (User user : found) {
			ret.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(ret, HttpStatus.OK);
	}
	
	@GetMapping(value = "/dermatologists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllDermatologistsForSpecificPharmacy(@RequestParam Map<String, String> queryParams) {
		String pharmacyId = queryParams.get("pharmacyId");
		List<UserDTO> ret = new ArrayList<UserDTO>();
		
		if (pharmacyId == null) {
			return new ResponseEntity<List<UserDTO>>(ret, HttpStatus.BAD_REQUEST);
		}
		
		Pharmacy foundPharmacy = pharmacyService.getPharmacy(Long.parseLong(pharmacyId));
		List<User> dermatologists = userService.findAllDermatologistsForPharmacy(foundPharmacy);
		List<User> found = userService.searchDermatologistsAndPharmacists(queryParams, dermatologists);
		
		for (User user : found) {
			ret.add(new UserDTO(user));
		}
		
		return new ResponseEntity<List<UserDTO>>(ret, HttpStatus.OK);
	}
}

