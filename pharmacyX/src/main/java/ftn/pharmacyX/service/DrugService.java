package ftn.pharmacyX.service;

import java.util.List;
import java.util.Map;

import ftn.pharmacyX.dto.CreateDrugDTO;
import ftn.pharmacyX.model.Drug;

public interface DrugService {

	List<Drug> getAllDrugs();


    Drug getDrug(Long id);
    
    List<Drug> searchDrugs(Map<String, String> queryParams);
    
    Drug addNewDrug(CreateDrugDTO dto);
}
