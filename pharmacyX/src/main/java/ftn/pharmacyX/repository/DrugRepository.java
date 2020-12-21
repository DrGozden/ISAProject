package ftn.pharmacyX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

}
