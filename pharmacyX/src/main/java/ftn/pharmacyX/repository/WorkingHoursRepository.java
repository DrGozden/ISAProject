package ftn.pharmacyX.repository;

import ftn.pharmacyX.model.Vacation;
import ftn.pharmacyX.model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {


}
