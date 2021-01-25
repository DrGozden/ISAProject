package ftn.pharmacyX.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Drug> searchDrugs(Map<String, String> queryParams) {
		List<Drug> allDrugs = drugRepo.findByDeleted(false);
		List<Drug> searched = new ArrayList<Drug>();
		List<Drug> ret = new ArrayList<Drug>();
		
		String searchParam = null;
		String filterParam = null;
		
		if (queryParams.get("search") != null) {
			searchParam = queryParams.get("search").toLowerCase();
		}
		
		if (queryParams.get("filter") != null) {
			filterParam = queryParams.get("filter").toLowerCase();
		}
		
		if (searchParam == null && filterParam == null) {
			return ret;
		}
		
		if (searchParam != null) {
			for (Drug d : allDrugs) {
				if (d.getName().toLowerCase().contains(searchParam)) {
					searched.add(d);
				}
			}
		}
		
		if (filterParam != null) {
			if (!searched.isEmpty()) {
				for (Drug d : searched) {
					if (d.getSpecification().getDrugType().name().equalsIgnoreCase(filterParam)) {
						ret.add(d);
					}
				}
			} else {
				for (Drug d : allDrugs) {
					if (d.getSpecification().getDrugType().name().equalsIgnoreCase(filterParam)) {
						ret.add(d);
					}
				}
			}
			return ret;
		} else {
			return searched;
		}
	}


}
