package ftn.pharmacyX.service;

import java.util.List;

import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.PharmacistConsultation;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.users.User;

public interface PharmacyService {
	
	
	public List<Pharmacy> getAllPharmacies();
	public Pharmacy getPharmacy(long id);
	
	public DermatologistExam scheduleExam(User patient, DermatologistExam exam);
	public void cancelExam(DermatologistExam exam);
	
	
	public PharmacistConsultation scheduleConsultation(User patient, PharmacistConsultation consultation);
	public void cancelConsultation(PharmacistConsultation consultation);
	
	public DrugReservation reserveDrug(Drug drug);
	public void cancelDrugReservation(Drug drug);
	
	public List<Pharmacy> search(String criteria);
	public List<Pharmacy> filter(String filter);	
	

	
	
	
	
	
	/*
	
	// (**SystemAdmin**)
	public void addPharmacy(Pharmacy pharmacy);
	// (**PharmacyAdmin**)
	public void editPharmacy(long id, Pharmacy updatedPharmacy);
	public void deletePharmacy(long id);
	
	// create available dates for dermatologist exam
	
	// (**PharmacyAdmin**)
	public void createPharmacyReport();
	
	// (**PharmacyAdmin**)
	public void searchDrugs(long pharmacyId, String searchText);
	public void stockDrug(long pharmacyId, Drug newDrug);
	
	//  (**PharmacyAdmin**)
	public void searchPharmacists(long pharmacyId);
	public void searchDermatologists(long pharmacyId);
	public void addPharmacist(long pharmacyId, Pharmacist pharmacist);				// vodi racuna da se doda i radno vreme
	public void addDermatologist(long pharmacyId, Dermatologist dermatologist);		// vodi racuna da se doda i radno vreme
	public void removePharmacist(long pharmacyId, long pharmacistId);
	public void removeDermatologist(long pharmacyId, long dermatologistId);
	
	public void createSupplyOrder(long pharmacyId, SupplyOrder order);
	public void getAllSupplyOrders(long pharmacyId);
	
	public void createPromotion(long pharmacyId, Promotion newPromotion);
	
	*/

	
}
