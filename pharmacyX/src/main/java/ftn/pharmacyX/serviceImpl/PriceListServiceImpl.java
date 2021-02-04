package ftn.pharmacyX.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.dto.PriceListDTO;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.PriceList;
import ftn.pharmacyX.model.users.PharmacyAdmin;
import ftn.pharmacyX.repository.PharmacyRepository;
import ftn.pharmacyX.repository.PriceListRepository;
import ftn.pharmacyX.service.PriceListService;
import ftn.pharmacyX.service.UserService;
@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    PriceListRepository priceListRepo;
    
    @Autowired
    PharmacyRepository pharmacyRepository;
    
    @Autowired
    UserService userService;
    
   

    @Override
    public PriceList getPriceList(long id) {
        PriceList priceList = priceListRepo.getOne(id);
        return priceList;
    }

    @Override
    public PriceList getActivePriceList(Pharmacy pharmacy) {
        List<PriceList> priceLists = pharmacy.getPriceList();
        PriceList activePriceList = null;
        for (PriceList priceList: priceLists) {
            if(priceList.getStartDate().isBefore(LocalDate.now()) && priceList.getEndDate().isAfter(LocalDate.now())){
                activePriceList = priceList;
            }
        }
        return activePriceList;
    }

	@Override
	public PriceList createPriceList(PriceListDTO priceListDTO) {
		PharmacyAdmin admin = (PharmacyAdmin) userService.getLoggedUser();
		Pharmacy pharmacy = admin.getPharmacy();
		PriceList currentActivePriceList = getActivePriceList(pharmacy);
		if(priceListDTO.getStartDate() == null) {
			priceListDTO.setStartDate(LocalDate.now().plusDays(1));
		}
		else if(priceListDTO.getStartDate().isBefore(LocalDate.now().plusDays(1))) {
			priceListDTO.setStartDate(LocalDate.now().plusDays(1));
			
		}
		currentActivePriceList.setEndDate(priceListDTO.getStartDate().minusDays(1)); 
		
		priceListRepo.save(currentActivePriceList);
		
		PriceList newPriceList = new PriceList(priceListDTO);
		pharmacy.getPriceList().add(newPriceList);
		priceListRepo.save(newPriceList);
		pharmacyRepository.save(pharmacy);
		
		return newPriceList;
		
	}
    
    
}
