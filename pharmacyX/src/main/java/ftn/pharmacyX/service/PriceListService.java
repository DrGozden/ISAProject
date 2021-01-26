package ftn.pharmacyX.service;

import ftn.pharmacyX.dto.FilterDatePharmacistDTO;
import ftn.pharmacyX.model.Pharmacy;
import ftn.pharmacyX.model.PriceList;
import ftn.pharmacyX.model.users.Pharmacist;

import java.util.List;
import java.util.Map;


public interface PriceListService {
    public PriceList getPriceList(long id);

    PriceList getActivePriceList(Pharmacy pharmacy);

}
