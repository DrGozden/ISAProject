package ftn.pharmacyX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.DrugSpecification;

@Repository
public interface DrugSpecificationRepository extends JpaRepository<DrugSpecification, Long> {

}
