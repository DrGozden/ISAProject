package ftn.pharmacyX.service;

import java.util.List;
import java.util.Map;

import ftn.pharmacyX.model.Pharmacy;

public interface PharmacyService {
	
	
	public List<Pharmacy> getAllPharmacies();
	public Pharmacy getPharmacy(long id);
	
	public List<Pharmacy> searchPharmacies(Map<String, String> queryParams);
	public List<Pharmacy> getPharmaciesContainingDrug(Long drugId);
	
}
