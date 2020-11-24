package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Pharmacy;

@Repository
public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {

}
