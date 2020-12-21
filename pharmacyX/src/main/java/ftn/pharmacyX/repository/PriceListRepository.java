package ftn.pharmacyX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.PriceList;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {

}
