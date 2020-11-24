package ftn.pharmacyX.serviceImpl;

import java.util.List;

import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.DrugReservation;
import ftn.pharmacyX.model.PharmacistConsultation;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.service.PharmacyService;

public class PharmacyServiceImpl implements PharmacyService {

	@Override
	public List<Pharmacy> getAllPharmacies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pharmacy getPharmacy(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DermatologistExam scheduleExam(User patient, DermatologistExam exam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelExam(DermatologistExam exam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PharmacistConsultation scheduleConsultation(User patient, PharmacistConsultation consultation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelConsultation(PharmacistConsultation consultation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DrugReservation reserveDrug(Drug drug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelDrugReservation(Drug drug) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pharmacy> search(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pharmacy> filter(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
