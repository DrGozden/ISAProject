package ftn.pharmacyX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Drug;
import ftn.pharmacyX.model.Pharmacy;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
	
	public List<Drug> findByDeleted(boolean deleted);

}
