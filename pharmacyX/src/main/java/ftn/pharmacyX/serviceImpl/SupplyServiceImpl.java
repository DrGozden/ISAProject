package ftn.pharmacyX.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.SupplyOrderDTO;
import ftn.pharmacyX.enums.OfferStatus;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.SupplierOffer;
import ftn.pharmacyX.model.SupplyOrder;
import ftn.pharmacyX.repository.PharmacyRepository;
import ftn.pharmacyX.repository.SupplyOfferRepository;
import ftn.pharmacyX.repository.SupplyOrderRepository;
import ftn.pharmacyX.service.PharmacyService;
import ftn.pharmacyX.service.SupplyService;

@Service
public class SupplyServiceImpl implements SupplyService {

	@Autowired
	private DTOConverter converter;
	
	@Autowired
	private SupplyOrderRepository supplyOrderRepository;
	
	@Autowired
	private SupplyOfferRepository supplyOfferRepository;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	
	@Override
	public boolean createSupplyOrder(SupplyOrderDTO dto) {
		SupplyOrder order = converter.dtoToOrder(dto);
		if (order == null) {
			return false;
		}
		
		supplyOrderRepository.save(order);
		return true;
	}
	
	@Override
	public List<SupplyOrder> getAllOrders() {
		return supplyOrderRepository.findAllByDeleted(false);
	}


	@Override
	public SupplyOrder getOrderById(Long id) {
		return supplyOrderRepository.findById(id).orElse(null);
	}

	@Override
	public SupplierOffer getOfferById(Long id) {
		return supplyOfferRepository.findById(id).orElse(null);
	}

	@Override
	public List<SupplierOffer> getAllPendingOffersForOrder(Long orderId) {
		List<SupplierOffer> allOffers = supplyOfferRepository.findAll();
		List<SupplierOffer> pendingOffers = new ArrayList<SupplierOffer>();
		for (SupplierOffer offer : allOffers) {
			if (offer.getId().equals(orderId) && offer.getStatus().equals(OfferStatus.PENDING)) {
				pendingOffers.add(offer);
			}
		}
		return pendingOffers;
	}
	
	

	@Override
	public boolean acceptOffer(Long offerId) {
		SupplierOffer offer = supplyOfferRepository.findById(offerId).orElse(null);
		if (offer == null) {
			return false;
		}
		offer.setStatus(OfferStatus.ACCEPTED);
		
		List<SupplierOffer> allForThisOrder = getAllPendingOffersForOrder(offer.getOrder().getId());
		for (SupplierOffer off : allForThisOrder) {
			if (!off.getId().equals(offerId)) {
				off.setStatus(OfferStatus.REJECTED);
				supplyOfferRepository.save(off);
			}
		}
		
		supplyOfferRepository.save(offer);
		
		
		SupplyOrder order = offer.getOrder();
		Map<Drug, Integer> supplies = order.getSupplies();
		
		Pharmacy pharmacy = pharmacyService.getPharmacy(order.getPharmacy().getId());
		
		for (Drug supplyDrug : supplies.keySet()) {
			boolean flag = false;
			for (Drug drugInStock : order.getPharmacy().getDrugsInStock().keySet()) {
				if (supplyDrug.getId().equals(drugInStock.getId())) {
					// stari quantity
					int quantity = pharmacy.getDrugsInStock().get(supplyDrug);
					// uveaj ga za onoliko koliko je trazio u orderu
					quantity += supplies.get(supplyDrug);
					pharmacy.getDrugsInStock().put(supplyDrug, quantity);
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				pharmacy.getDrugsInStock().put(supplyDrug, supplies.get(supplyDrug));
			}
		}
		
		pharmacyRepo.save(pharmacy);
		
		return true;
	}

	@Override
	public List<SupplyOrder> getAllOrdersForPharmacy(Long pharmacyId) {
		List<SupplyOrder> all = getAllOrders();
		List<SupplyOrder> ret = new ArrayList<SupplyOrder>();
		for (SupplyOrder supplyOrder : all) {
			if (supplyOrder.getPharmacy().getId().equals(pharmacyId)) {
				ret.add(supplyOrder);
			}
		}
		return ret;
	}

}
