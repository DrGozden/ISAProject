package ftn.pharmacyX.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ftn.pharmacyX.dto.CreateExamDTO;
import ftn.pharmacyX.dto.FilterDatePharmacistDTO;
import ftn.pharmacyX.dto.PharmacistConsultationDTO;
import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.users.Pharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	
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


	@PostMapping(value = "/availablePharmacist")
	public ResponseEntity<?> getAvailablePharmacist(@RequestBody FilterDatePharmacistDTO dto) {
		List<Pharmacist> availablePharmacist = pharmacyService.getAvailablePharmacist(dto);
		System.out.println(dto.toString());
		return new ResponseEntity<>(availablePharmacist,HttpStatus.OK);
	}

}
