package ftn.pharmacyX.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Vacation;
@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {

	Vacation findByUserId(Long userId);
	
	Optional<Vacation> findByIdAndDeleted(Long id, boolean deleted);
	
	List<Vacation> findAllByDeleted(boolean deleted);
}
