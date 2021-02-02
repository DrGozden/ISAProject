package ftn.pharmacyX.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import ftn.pharmacyX.dto.SupplyOrderDTO;
import ftn.pharmacyX.dto.FilterDatePharmacistDTO;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.PriceList;
import ftn.pharmacyX.model.users.Pharmacist;

public interface PharmacyService {
	
	
	public List<Pharmacy> getAllPharmacies();
	public Pharmacy getPharmacy(long id);
	
	public List<Pharmacy> searchPharmacies(Map<String, String> queryParams);
	public List<Pharmacy> getPharmaciesContainingDrug(Long drugId);

	public List<Pharmacist> getAvailablePharmacist(LocalDateTime dateTime);
	
	List<Pharmacist> getAvailablePharmacist(FilterDatePharmacistDTO filterDto);


}
