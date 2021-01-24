package ftn.pharmacyX.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.repository.DrugRepository;
import ftn.pharmacyX.service.DrugService;

@Service
public class DrugServiceImpl implements DrugService {
	
	@Autowired
	DrugRepository drugRepo;
	
	@Override
	public List<Drug> getAllDrugs() {
		//return pharmacyRepo.findAll();
		return drugRepo.findByDeleted(false);
	}

	@Override
	public Drug getDrug(Long id) {
		return drugRepo.findByIdAndDeletedIsFalse(id);
	}


}
