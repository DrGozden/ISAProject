package ftn.pharmacyX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.SupplierOffer;
@Repository
public interface SupplyOfferRepository extends JpaRepository<SupplierOffer, Long> {

}
