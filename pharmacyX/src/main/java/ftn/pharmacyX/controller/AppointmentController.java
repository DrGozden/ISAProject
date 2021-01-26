package ftn.pharmacyX.controller;

import java.util.List;

import ftn.pharmacyX.dto.NewConsultationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.dto.CreateExamDTO;
import ftn.pharmacyX.dto.DermatologistExamDTO;
import ftn.pharmacyX.dto.PharmacistConsultationDTO;
import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.PharmacistConsultation;
import ftn.pharmacyX.model.users.Patient;
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

		List<DermatologistExamDTO> exams = apptService.getDermatologistExamsForPharmacy(pharmacyId);

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

		List<PharmacistConsultationDTO> consultations = apptService.getPharmacistConsutationsForPharmacy(pharmacyId);

		return new ResponseEntity<>(consultations, HttpStatus.OK);
	}

	@PostMapping(value = "/exams/{id}")
	public ResponseEntity<?> scheduleExam(@PathVariable("id") Long examId) {

		User loggedUser = userService.getLoggedUser();

		apptService.scheduleExam(loggedUser, examId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/consultations/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> scheduleConsultation(@RequestBody  NewConsultationDTO consultationDTO) {
		User loggedUser = userService.getLoggedUser();
		apptService.scheduleConsultation(loggedUser, consultationDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "/exams/{id}/cancel")
	public ResponseEntity<?> cancelExam(@PathVariable("id") Long examId) {

		apptService.cancelExam(examId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "/consultations/{id}/cancel")
	public ResponseEntity<?> cancelConsultation(@PathVariable("id") Long consultationId) {

		apptService.cancelConsultation(consultationId);

		return new ResponseEntity<>(HttpStatus.OK);
	}


	@GetMapping(value = "/my-consultations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getConsultationsForUser() {
		Patient patient = (Patient) userService.getLoggedUser();
		
		List<PharmacistConsultationDTO> consultations = apptService.getPharmacistConsutationsForUser(patient);

		return new ResponseEntity<>(consultations, HttpStatus.OK);
	}
	
	@GetMapping(value = "/my-exams", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getExamsForUser() {
		Patient patient = (Patient) userService.getLoggedUser();
		
			
		List<DermatologistExamDTO> exams = apptService.getDermatologistExamsForUser(patient);

		return new ResponseEntity<>(exams, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create-exam", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createExam(@RequestBody CreateExamDTO dto) {
		DermatologistExam exam = apptService.createExam(dto);
		
		// ako pokusa da doda exam dermatologu izvan njehovog radnog vremena
		if (exam == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
