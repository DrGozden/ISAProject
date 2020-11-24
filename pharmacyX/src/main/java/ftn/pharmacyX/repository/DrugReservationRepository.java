package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.DrugReservation;

@Repository
public interface DrugReservationRepository extends CrudRepository<DrugReservation, Long> {

}
