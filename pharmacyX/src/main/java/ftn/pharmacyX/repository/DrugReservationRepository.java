package ftn.pharmacyX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.DrugReservation;

@Repository
public interface DrugReservationRepository extends JpaRepository<DrugReservation, Long> {
	
}
