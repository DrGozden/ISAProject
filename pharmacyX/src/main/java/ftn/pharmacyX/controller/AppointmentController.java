package ftn.pharmacyX.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.PharmacistConsultation;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.service.AppointmentService;
import ftn.pharmacyX.service.UserService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService apptService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/pharmacies/{id}/exams", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getExamsForPharmacy(@PathVariable("id") Long pharmacyId) {

		List<DermatologistExam> exams = apptService.getDermatologistExamsForPharmacy(pharmacyId);

		return new ResponseEntity<>(exams, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pharmacies/{id}/unreserved_exams", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUnreservedExamsForPharmacy(@PathVariable("id") Long pharmacyId) {

		List<DermatologistExam> exams = apptService.getUnreservedDermatologistExamsForPharmacy(pharmacyId);

		return new ResponseEntity<>(exams, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pharmacies/{id}/unreserved_consultation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUnreservedConsultationsForPharmacy(@PathVariable("id") Long pharmacyId) {

		List<PharmacistConsultation> consultations = apptService.getUnreservedConsultationsForPharmacy(pharmacyId);

		return new ResponseEntity<>(consultations, HttpStatus.OK);
	}

	@GetMapping(value = "/pharmacies/{id}/consultations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getconsultationsForPharmacy(@PathVariable("id") Long pharmacyId) {

		List<PharmacistConsultation> consultations = apptService.getPharmacistConsutationsForPharmacy(pharmacyId);

		return new ResponseEntity<>(consultations, HttpStatus.OK);
	}

	@PostMapping(value = "/exams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> scheduleExam(@PathVariable("id") Long examId) {

		User loggedUser = userService.getLoggedUser();

		DermatologistExam exam = apptService.scheduleExam(loggedUser, examId);
		return new ResponseEntity<>(exam, HttpStatus.OK);
	}

	@PostMapping(value = "/consultations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> scheduleConsultation(@PathVariable("id") Long consultationId) {
		User loggedUser = userService.getLoggedUser();

		PharmacistConsultation consultation = apptService.scheduleConsultation(loggedUser, consultationId);

		return new ResponseEntity<>(consultation, HttpStatus.OK);
	}

	@PutMapping(value = "/exams/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cancelExam(@PathVariable("id") Long examId) {

		DermatologistExam canceledExam = apptService.cancelExam(examId);

		return new ResponseEntity<>(canceledExam, HttpStatus.OK);
	}

	@PutMapping(value = "/consultations/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cancelConsultation(@PathVariable("id") Long consultationId) {

		PharmacistConsultation canceled = apptService.cancelConsultation(consultationId);

		return new ResponseEntity<>(canceled, HttpStatus.OK);
	}

	//PROBLEM POSTOJI
	/*
	@GetMapping(value = "/my-consultations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getConsultationsForUser() {
		Patient patient = (Patient) userService.getLoggedUser();
		
		List<PharmacistConsultation> consultations = apptService.getPharmacistConsutationsForUser(patient);

		return new ResponseEntity<>(consultations, HttpStatus.OK);
	}
	
	@GetMapping(value = "/my-exams", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getExamsForUser() {
		Patient patient = (Patient) userService.getLoggedUser();
		
			
		List<DermatologistExam> exams = apptService.getDermatologistExamsForUser(patient);

		return new ResponseEntity<>(exams, HttpStatus.OK);
	}
	*/
}
