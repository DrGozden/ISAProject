package ftn.pharmacyX.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.repository.PharmacyRepository;
import ftn.pharmacyX.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	
	@Override
	public List<Pharmacy> getAllPharmacies() {
		//return pharmacyRepo.findAll();
		return pharmacyRepo.findByDeleted(false);
	}

	@Override
	public Pharmacy getPharmacy(long id) {
		return pharmacyRepo.findByIdAndDeletedIsFalse(id);
		
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
