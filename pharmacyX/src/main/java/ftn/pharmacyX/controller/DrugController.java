package ftn.pharmacyX.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.service.DrugService;

@RestController
@RequestMapping("/drugs")
public class DrugController {
	
	@Autowired
	private DrugService drugService;
	
	

}
