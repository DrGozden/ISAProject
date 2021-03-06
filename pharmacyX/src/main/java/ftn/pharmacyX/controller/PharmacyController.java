package ftn.pharmacyX.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ftn.pharmacyX.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.users.Dermatologist;
import ftn.pharmacyX.model.users.Pharmacist;
import ftn.pharmacyX.model.users.PharmacyAdmin;
import ftn.pharmacyX.service.DrugReservationService;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.UserService;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private DTOConverter converter;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DrugReservationService drugResService;


	
	@GetMapping()
	public ResponseEntity<?> getAllPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		List<PharmacyDTO> dtos = new ArrayList<>();
		for (Pharmacy pharmacy: pharmacies) {
			dtos.add(converter.pharmacyToDTO(pharmacy));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
	@PostMapping(value = "/create-pharmacy")
	public ResponseEntity<?> createPharmacy(@RequestBody PharmacyDTO dto) {
		Pharmacy pharmacy = pharmacyService.createPharmacy(dto);
		return new ResponseEntity<>(pharmacy,HttpStatus.OK);
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
		return new ResponseEntity<>(availablePharmacist,HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePharmacy(@RequestBody UpdatePharmacyDTO dto) {
		Pharmacy pharmacy = pharmacyService.updatePharmacy(dto);
		return new ResponseEntity<>(pharmacy,HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/drugs/{id}")
	public ResponseEntity<?> deleteDrugFromPharmacy(@PathVariable("id") Long drugId) {
		PharmacyAdmin admin = (PharmacyAdmin) userService.getLoggedUser();
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		boolean isReserved = drugResService.checkIfDrugIsReserved(drugId, admin.getPharmacy());
		
		// ako postoji rezervacija, ne dozvoli brisanje
		if (isReserved) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		pharmacyService.deleteDrugFromPharmacy(drugId, admin.getPharmacy().getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	public ResponseEntity<?> addDrugToPharmacy(@RequestBody AddDrugDTO dto) {
		PharmacyAdmin admin = (PharmacyAdmin) userService.getLoggedUser();
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		boolean isAdded = pharmacyService.addDrugToPharmacy(dto, admin.getPharmacy().getId());
		
		if (isAdded) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@PostMapping(value = "/add-pharmacist")
	public ResponseEntity<?> addPharmacist(@RequestBody EmployeeDTO dto){
		pharmacyService.addPharmacist(dto);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@PostMapping(value = "/add-dermatologist")
	public ResponseEntity<?> addDermatologist(@RequestBody EmployeeDTO dto){
		pharmacyService.addDermatologist(dto);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@DeleteMapping(value = "/remove-pharmacist/{id}")
	public ResponseEntity<?> removePharmacist(@PathVariable("id") Long id){
		Pharmacist pharmacist = pharmacyService.removePharmacist(id);
		if(pharmacist == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@DeleteMapping(value = "/remove-dermatologist/{id}")
	public ResponseEntity<?> removeDermatologist(@PathVariable("id") Long id){
		Dermatologist dermatologist = pharmacyService.removeDermatologist(id);
		if(dermatologist == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}


}
