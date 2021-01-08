package ftn.pharmacyX.controller;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.service.AppointmentService;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.UserService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/exams/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> makeReservationForExam(@PathVariable("id") Long id) {
		User loggedPatient = userService.getLoggedUser();
		System.out.println(loggedPatient.getEmail());
		DermatologistExam scheduled = appointmentService.scheduleExam(loggedPatient, id);
		
		return new ResponseEntity<DermatologistExam>(scheduled, HttpStatus.OK);
		
	}
	
}
