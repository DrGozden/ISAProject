package ftn.pharmacyX.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.CreateDrugDTO;
import ftn.pharmacyX.enums.UserRole;
import ftn.pharmacyX.helpers.DTOConverter;
import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.users.PharmacyAdmin;
import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.repository.DrugRepository;
import ftn.pharmacyX.repository.DrugSpecificationRepository;
import ftn.pharmacyX.service.DrugService;
import ftn.pharmacyX.service.UserService;

@Service
public class DrugServiceImpl implements DrugService {
	
	@Autowired
	private DrugRepository drugRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DTOConverter converter;
	
	@Autowired
	private DrugSpecificationRepository drugSpecRepo;
	
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
		List<Drug> allDrugs = null;
		User user = userService.getLoggedUser();
		
		if(user != null && user.getUserRole() == UserRole.PHARMACY_ADMIN) {
			PharmacyAdmin admin = (PharmacyAdmin) user;
			allDrugs = new ArrayList<Drug>(admin.getPharmacy().getDrugsInStock().keySet());
		}
		else {
			allDrugs = drugRepo.findByDeleted(false);
		}
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

	@Override
	public Drug addNewDrug(CreateDrugDTO dto) {
		Drug newDrug = converter.dtoToDrug(dto);
		if (newDrug == null) {
			return null;
		}
		
		drugSpecRepo.save(newDrug.getSpecification());
		
		return drugRepo.save(newDrug);
	}


}
