package ftn.pharmacyX.service;

import java.util.List;

import ftn.pharmacyX.model.Drug;

public interface DrugService {

	public void searchDrugs(String searchText);
	public Drug getDrug(long id);
	public List<Drug> getAllDrugs();
	
	
	// (**SystemAdmin**)
	public void addDrug(Drug newDrug);
	public void editDrug(long id, Drug updatedDrug);
	public void deleteDrug(long id);
}
