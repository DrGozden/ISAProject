package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
