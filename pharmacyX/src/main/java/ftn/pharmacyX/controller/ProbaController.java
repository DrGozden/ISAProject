package ftn.pharmacyX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.service.PharmacyService;

@RestController
@RequestMapping("/proba")
public class ProbaController {

	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/encoder")
	public ResponseEntity<?> testProba() {
		System.out.println(passwordEncoder.encode("123"));
		return null;
	}
}
