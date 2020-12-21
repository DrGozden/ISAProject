package ftn.pharmacyX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Pharmacy;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

	public List<Pharmacy> findByDeleted(boolean deleted);
}
