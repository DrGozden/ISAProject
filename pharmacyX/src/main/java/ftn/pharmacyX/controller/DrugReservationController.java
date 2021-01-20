package ftn.pharmacyX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.service.DrugReservationService;

@RestController
@RequestMapping("/drug_reservation")
public class DrugReservationController {
	
	@Autowired
	private DrugReservationService drugReservationService;
	
	
	@PutMapping(value = "/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cancelDrugReservation(@PathVariable("id") Long reservationId) {

		DrugReservation canceled = drugReservationService.cancelReservation(reservationId);

		return new ResponseEntity<>(canceled, HttpStatus.OK);
	}
	
	

}
