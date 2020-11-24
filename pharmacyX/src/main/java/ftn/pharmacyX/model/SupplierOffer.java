package ftn.pharmacyX.model;

import java.time.LocalDateTime;

import ftn.pharmacyX.enums.OfferStatus;
import ftn.pharmacyX.model.users.Supplier;

public class SupplierOffer {

	private long id;
	private OfferStatus status;
	private double totalPrice;
	private LocalDateTime deliveryDeadline;
	private Supplier supplier;
	private SupplyOrder order;
	private boolean deleted = false;
	
	public SupplierOffer() {
		
	}
	
	
}
