package ftn.pharmacyX.service;

import ftn.pharmacyX.dto.PriceListDTO;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.PriceList;


public interface PriceListService {
    public PriceList getPriceList(long id);

    PriceList getActivePriceList(Pharmacy pharmacy);
    
    PriceList createPriceList(PriceListDTO priceListDTO);

}
