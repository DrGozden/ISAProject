package ftn.pharmacyX.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.pharmacyX.dto.SupplyOrderDTO;
import ftn.pharmacyX.model.SupplierOffer;
import ftn.pharmacyX.model.SupplyOrder;
import ftn.pharmacyX.model.users.PharmacyAdmin;
import ftn.pharmacyX.service.SupplyService;
import ftn.pharmacyX.service.UserService;

@RestController
@RequestMapping("/supplies")
public class SupplyController {
	
	@Autowired
	private SupplyService supplyService;
	
	@Autowired
	private UserService userService;


	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@PostMapping("/create-order")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createOrder(@RequestBody SupplyOrderDTO dto) {
		boolean ret = supplyService.createSupplyOrder(dto);
		if (!ret) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@GetMapping("/orders")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOrders() {
		PharmacyAdmin admin = (PharmacyAdmin) userService.getLoggedUser();
		List<SupplyOrder> orders = supplyService.getAllOrdersForPharmacy(admin.getPharmacy().getId());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@GetMapping("/pending-offers/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllPendingOffersForOrder(@PathVariable("id") Long orderId) {
		List<SupplierOffer> offers = supplyService.getAllPendingOffersForOrder(orderId);
		return new ResponseEntity<>(offers, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('PHARMACY_ADMIN')")
	@PostMapping("/offers/{id}/accept")
	public ResponseEntity<?> acceptOffer(@PathVariable("id") Long offerId) {
		boolean ret = supplyService.acceptOffer(offerId);
		if (!ret) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
