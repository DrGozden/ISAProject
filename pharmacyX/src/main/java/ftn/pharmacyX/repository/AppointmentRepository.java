package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
