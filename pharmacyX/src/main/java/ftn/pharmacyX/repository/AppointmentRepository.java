package ftn.pharmacyX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Appointment;
import ftn.pharmacyX.model.DermatologistExam;
import ftn.pharmacyX.model.PharmacistConsultation;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(value = "SELECT * FROM Appointment WHERE appointment_type = dermatologist_appointment and pharmacy_id = ?1", nativeQuery = true)
	public List<DermatologistExam> findDermatologistExamsByPharmacyId(Long id);
	
	@Query(value = "SELECT * FROM Appointment WHERE appointment_type = pharmacist_appointment and pharmacy_id = ?1", nativeQuery = true)
	public List<PharmacistConsultation> findConsultationsByPharmacyId(Long id); 
}
