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

import ftn.pharmacyX.dto.DrugDTO;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.service.DrugService;

@RestController
@RequestMapping("/drugs")
public class DrugController {
	
	@Autowired
	private DrugService drugService;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllDrugs() {
		List<Drug> drugs = drugService.getAllDrugs();
		return new ResponseEntity<>(drugs, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DrugDTO> getDrug(@PathVariable("id") Long id) {
		Drug drug = drugService.getDrug(id);
		DrugDTO dto = new DrugDTO(drug);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> searchDrugs(@RequestParam Map<String, String> queryParams) {
		List<Drug> found = drugService.searchDrugs(queryParams);
		//List<DrugDTO> dtos = new ArrayList<DrugDTO>();
		return new ResponseEntity<>(found, HttpStatus.OK);
	}
	

}
