package ftn.pharmacyX.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.DrugSpecification;

@Repository
public interface DrugSpecificationRepository extends CrudRepository<DrugSpecification, Long> {

}
