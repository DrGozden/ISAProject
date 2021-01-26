package ftn.pharmacyX.service;

import java.util.List;

import ftn.pharmacyX.dto.SupplyOfferDTO;
import ftn.pharmacyX.dto.SupplyOrderDTO;
import ftn.pharmacyX.model.SupplierOffer;
import ftn.pharmacyX.model.SupplyOrder;

public interface SupplyService {

	public boolean createSupplyOrder(SupplyOrderDTO dto);
	
	public List<SupplyOrder> getAllOrders();
	
	public SupplyOrder getOrderById(Long id);
	
	public SupplierOffer getOfferById(Long id);
	
	public List<SupplierOffer> getAllPendingOffersForOrder(Long orderId);
	
	public boolean acceptOffer(Long offerId);
}
