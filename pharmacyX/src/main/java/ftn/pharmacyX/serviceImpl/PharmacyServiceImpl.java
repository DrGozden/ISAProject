package ftn.pharmacyX.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		return pharmacyRepo.findByDeleted(false);
	}

	@Override
	public Pharmacy getPharmacy(long id) {
		return pharmacyRepo.findByIdAndDeletedIsFalse(id);
		
	}
	
	public double calculateRating(List<Integer> ratings) {
		int ret = 0;
		for (Integer rating : ratings) {
			ret += rating;
		}
		if (ratings.size() == 0) {
			return ret;
		}
		return ret / ratings.size();
		
	}

	@Override
	public List<Pharmacy> searchPharmacies(Map<String, String> queryParams) {
		List<Pharmacy> allPharmacies = pharmacyRepo.findByDeleted(false);
		List<Pharmacy> searched = new ArrayList<Pharmacy>();
		List<Pharmacy> ret = new ArrayList<Pharmacy>();
		
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
			for (Pharmacy pharmacy : allPharmacies) {
				if (pharmacy.getAddress().getCity().toLowerCase().contains(searchParam) ||
						pharmacy.getAddress().getStreet().toLowerCase().contains(searchParam) ||
						pharmacy.getName().toLowerCase().contains(searchParam)) {
					searched.add(pharmacy);
				}
			}
		}
		
		if (filterParam != null) {
			if (!searched.isEmpty()) {
				for (Pharmacy pharmacy : searched) {
					double rating = this.calculateRating(pharmacy.getRatings());
					if (rating > Double.parseDouble(filterParam)) {
						ret.add(pharmacy);
					}
				}
			} else {
				for (Pharmacy pharmacy : allPharmacies) {
					double rating = this.calculateRating(pharmacy.getRatings());
					if (rating > Double.parseDouble(filterParam)) {
						ret.add(pharmacy);
					}
				}
			}
			return ret;
		} else {
			return searched;
		}
		
	}
	
}
