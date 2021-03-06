package ftn.pharmacyX.service;

import java.util.List;
import java.util.Map;

import ftn.pharmacyX.dto.*;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.users.Dermatologist;
import ftn.pharmacyX.model.users.Pharmacist;

public interface PharmacyService {

	public List<Pharmacy> getAllPharmacies();

	public Pharmacy getPharmacy(long id);

	public List<Pharmacy> searchPharmacies(Map<String, String> queryParams);
	public List<Pharmacy> getPharmaciesContainingDrug(Long drugId);
	List<Pharmacist> getAvailablePharmacist(FilterDatePharmacistDTO filterDto);

	public Pharmacy updatePharmacy(UpdatePharmacyDTO dto);
	
	public boolean deleteDrugFromPharmacy(Long drugId, Long pharmacyId);

	public boolean addDrugToPharmacy(AddDrugDTO dto, Long pharmacyId);
	public Pharmacist addPharmacist(EmployeeDTO dto);

	public Dermatologist addDermatologist(EmployeeDTO dto);

	public Pharmacist removePharmacist(Long id);

	public Dermatologist removeDermatologist(Long id);

	public double calculateRating(List<Integer> ratings);

	public Pharmacy createPharmacy(PharmacyDTO dto);
}
