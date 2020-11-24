package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.PriceList;

@Repository
public interface PriceListRepository extends CrudRepository<PriceList, Long> {

}
