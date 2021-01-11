package ftn.pharmacyX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.service.DrugService;

@RestController
@RequestMapping("/drugs")
public class DrugController {
	
	@Autowired
	private DrugService drugService;
	
	

}
