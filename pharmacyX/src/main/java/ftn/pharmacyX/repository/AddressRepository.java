package ftn.pharmacyX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
