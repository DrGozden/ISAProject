package ftn.pharmacyX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.dto.DrugReservationDTO;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.service.DrugReservationService;
import ftn.pharmacyX.service.UserService;

@RestController
@RequestMapping("/drug_reservation")
public class DrugReservationController {
	
	@Autowired
	private DrugReservationService drugReservationService;
	
	@Autowired
	private UserService userService;
	
	
	@PutMapping(value = "/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cancelDrugReservation(@PathVariable("id") Long reservationId) {

		DrugReservation canceled = drugReservationService.cancelReservation(reservationId);

		return new ResponseEntity<>(canceled, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DrugReservation> makeDrugReservation(@RequestBody DrugReservationDTO drugReservationDTO) {
		DrugReservation drugReservation = drugReservationService.makeReservation(drugReservationDTO);
		userService.addDrugReservation(drugReservation);
		return new ResponseEntity<>(drugReservation, HttpStatus.OK);
	}
	
	

}
