package ftn.pharmacyX.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ftn.pharmacyX.dto.*;
import ftn.pharmacyX.model.Pharmacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.enums.UserStatus;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.Appointment;
import ftn.pharmacyX.model.Vacation;
import ftn.pharmacyX.model.users.Dermatologist;
import ftn.pharmacyX.model.users.Patient;
import ftn.pharmacyX.model.users.Pharmacist;
import ftn.pharmacyX.model.users.PharmacyAdmin;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.service.DrugReservationService;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.UserService;
import ftn.pharmacyX.service.VacationService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private DrugReservationService drugReservationService;
	
	@Autowired
	private VacationService vacationService;
	
	@Autowired
	private DTOConverter converter;
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
		Patient p = converter.userDtoToPatient(userDTO);
		User newUser = userService.saveUser(p);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@PostMapping(value = "/me/password-change", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> changePassword(@RequestBody UserDTO userDTO) {
		User newUser = userService.changePassword(userDTO);
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
		userService.editUser(userDTO);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/me/history/drugreservations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDrugReservations() {
		User user = userService.getLoggedUser();
		List<DrugReservationDTO> reservations = drugReservationService.getDrugReservationsForUser(user);
		
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@GetMapping(value = "/pharmacists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllPharmacists(@RequestParam Map<String, String> queryParams) {
		List<User> users = userService.findAllPharmacists();
		List<UserDTO> ret = new ArrayList<UserDTO>();
		PharmacyAdmin admin = null;
		if (userService.getLoggedUser() != null) {
			if (userService.getLoggedUser().getUserRole() == UserRole.PHARMACY_ADMIN) {
				admin = (PharmacyAdmin) userService.getLoggedUser();
			}
		}
		
		List<User> found;
		
		if (admin == null) {
			found = userService.searchDermatologistsAndPharmacists(queryParams, users);
		} else {
			List<User> pharmacyPharmacists = new ArrayList<User>();
			for (User phPharmacist : admin.getPharmacy().getPharmacists()) {
				pharmacyPharmacists.add(phPharmacist);
			}
			found = userService.searchDermatologistsAndPharmacists(queryParams, pharmacyPharmacists);
		}
		
		for (User user : found) {
			Pharmacist ph = (Pharmacist) user;
			double rating = pharmacyService.calculateRating(ph.getRatings());
			UserDTO dto = new UserDTO(user);
			dto.setRating(rating);
			ret.add(dto);
		}
		return new ResponseEntity<List<UserDTO>>(ret, HttpStatus.OK);
	}

	@GetMapping(value = "/dermatologists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllDermatologistsForSpecificPharmacy(@RequestParam Map<String, String> queryParams) {
		List<UserDTO> ret = new ArrayList<UserDTO>();
		List<User> dermatologists = userService.findAllDermatologists();
		PharmacyAdmin admin = null;
		if (userService.getLoggedUser() != null) {
			if (userService.getLoggedUser().getUserRole() == UserRole.PHARMACY_ADMIN) {
				admin = (PharmacyAdmin) userService.getLoggedUser();
			} 
		}
		
		List<User> found;
		
		if (admin == null) {
			found = userService.searchDermatologistsAndPharmacists(queryParams, dermatologists);
		} else {
			List<User> pharmacyDermatologists = new ArrayList<User>();
			for (User phUser : admin.getPharmacy().getDermatologists()) {
				pharmacyDermatologists.add(phUser);
			}
			found = userService.searchDermatologistsAndPharmacists(queryParams, pharmacyDermatologists);
		}

		for (User user : found) {
			Dermatologist de = (Dermatologist) user;
			double rating = pharmacyService.calculateRating(de.getRatings());
			UserDTO dto = new UserDTO(user);
			dto.setRating(rating);
			ret.add(dto);
		}
		
		return new ResponseEntity<List<UserDTO>>(ret, HttpStatus.OK);
	}
	
	@GetMapping(value = "/vacation-requests")
	public ResponseEntity<?> getVacationRequests() {
		List<Vacation> requests = vacationService.getVacationRequests();
		List<VacationWithUserDTO> dtos = new ArrayList<VacationWithUserDTO>();
		for (Vacation v : requests) {
			VacationWithUserDTO dto = converter.vacationToDto(v);
			if (dto == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			dtos.add(dto);
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PutMapping(value = "/approve-vacation/{vacationId}")
	public ResponseEntity<?> approveVacation(@PathVariable("vacationId") Long vacationId) {
		boolean ret = vacationService.approveVacation(vacationId);
		if (ret) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/reject-vacation", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rejectVacation(@RequestBody VacationRejectDTO dto) {
		boolean ret = vacationService.rejectVacation(dto.getVacationId(), dto.getRejectDescription());
		if (ret) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/create-pharmacy-admin")
	public ResponseEntity<?> createPharmacy(@RequestBody UserDTO dto) {
		PharmacyAdmin admin = userService.createPharmacyAdmin(dto);
		return new ResponseEntity<>(admin,HttpStatus.OK);
	}
}

