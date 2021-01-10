package ftn.pharmacyX.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.dto.UserDTO;
import ftn.pharmacyX.enums.UserStatus;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.Appointment;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.service.UserService;

@RestController
//@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;
	
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

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser() {
		User user = userService.getLoggedUser();
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		UserDTO userDTO = new UserDTO(user);
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
}
