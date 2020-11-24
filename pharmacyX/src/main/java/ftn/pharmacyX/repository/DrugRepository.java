package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.Drug;

@Repository
public interface DrugRepository extends CrudRepository<Drug, Long> {

}
