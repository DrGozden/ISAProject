package ftn.pharmacyX.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.dto.PharmacyDTO;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.service.PharmacyService;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private DTOConverter converter;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		List<PharmacyDTO> dtos = new ArrayList<>();
		for (Pharmacy pharmacy: pharmacies) {
			dtos.add(converter.pharmacyToDTO(pharmacy));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PharmacyDTO> getPharmacy(@PathVariable("id") Long id) {
		Pharmacy pharmacy = pharmacyService.getPharmacy(id);
		PharmacyDTO dto = converter.pharmacyToDTO(pharmacy);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> searchPharmacies(@RequestParam Map<String, String> queryParams) {
		List<Pharmacy> found = pharmacyService.searchPharmacies(queryParams);
		List<PharmacyDTO> dtos = new ArrayList<>();
		for (Pharmacy pharmacy: found) {
			dtos.add(converter.pharmacyToDTO(pharmacy));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping(value = "/containingDrug/{drugId}")
	public ResponseEntity<?> getPharmaciesContainingDrug(@PathVariable("drugId") Long drugId) {
		List<Pharmacy> found = pharmacyService.getPharmaciesContainingDrug(drugId);
		List<PharmacyDTO> dtos = new ArrayList<>();
		for (Pharmacy pharmacy: found) {
			dtos.add(converter.pharmacyToDTO(pharmacy));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
