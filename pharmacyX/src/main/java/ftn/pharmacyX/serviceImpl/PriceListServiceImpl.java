package ftn.pharmacyX.serviceImpl;

import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.PriceList;
import ftn.pharmacyX.repository.PriceListRepository;
import ftn.pharmacyX.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    PriceListRepository priceListRepo;

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
}
