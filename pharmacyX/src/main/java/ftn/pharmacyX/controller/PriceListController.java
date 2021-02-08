package ftn.pharmacyX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.dto.PriceListDTO;
import ftn.pharmacyX.model.PriceList;
import ftn.pharmacyX.service.PriceListService;

@RestController
@RequestMapping("/priceLists")
public class PriceListController {
	
	@Autowired
	private PriceListService priceListService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPriceList(@RequestBody PriceListDTO dto) {
		System.out.println(dto.getStartDateString());
		PriceList pricelist = priceListService.createPriceList(dto);
		return new ResponseEntity<>(pricelist,HttpStatus.OK);
	}

}
